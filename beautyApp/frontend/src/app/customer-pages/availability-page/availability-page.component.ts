// availability-page.component.ts
import { ChangeDetectorRef, Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { FormGroup } from '@angular/forms';
import { AvailabilityService } from '../../services/availability.service'; // Import the service
import { Availability } from '../../model/availability.model';
import { DateAdapter } from '@angular/material/core';
import { Appointment } from '../../model/appointment.model';
import { MatCalendarCellClassFunction, MatCalendarCellCssClasses } from '@angular/material/datepicker';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { DomSanitizer } from '@angular/platform-browser';
import { ServiceProfileService } from '../../services/serviceProfile.service';
import { ServiceProfile } from '../../model/serviceProfile.model';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-availability-page',
  templateUrl: './availability-page.component.html',
  styleUrls: ['./availability-page.component.css'],
})
export class AvailabilityPageComponent implements OnInit {
  public availabilityForm!: FormGroup;
  serviceId: number | null = null; 
  serviceDetails: any;
  profileLogos: any[] = [];
  availabilities: Availability[] = [];
  selected: Date | null; // Initialize selected property
  selectedTimeSlot: string | null;
  timeSlots: string[]; // Array to hold time slots
  appointments: Appointment[] = [];
  userId: number | null = null; 
  existingAppointments: Appointment[] = []; 
  minDate: Date; // Property to store minimum allowed date
  
  addHour(timeSlot: string): string {
    const [hour, minute] = timeSlot.split(':').map(Number);
    const nextHour = hour === 23 ? 0 : hour + 1;
    return `${nextHour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}`;
  }

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService,
    private availabilityService: AvailabilityService, // Inject the service
    private dateAdapter: DateAdapter<Date>,
    private serviceProfileService: ServiceProfileService,
    private cdr: ChangeDetectorRef,
    private ngZone: NgZone,
    private snackBar: MatSnackBar,
    private sanitizer: DomSanitizer,
    private userService: UserService,
  ) {
    this.selected = null; // Initialize selected property
    this.dateAdapter.setLocale('en'); // Set locale
    this.selectedTimeSlot = null;
    this.timeSlots = this.generateTimeSlots(); // Generate time slots
    this.minDate = new Date(); // Initialize minDate to today
    console.log('minDate:', this.minDate);

  }

  bookAppointment(): void {
    console.log('Selected Date:', this.selected);
    console.log('Selected Time Slot:', this.selectedTimeSlot);
    const token = localStorage.getItem('token');
    console.log('Token:', token); 
    if (this.selected && this.selectedTimeSlot && this.serviceId !== null) {
      // Convert the selected date to UTC timezone
      //const utcDate = new Date(this.selected.toISOString());
      const localDate = new Date(this.selected);
      // Format the date to exclude time and timezone
      //const formattedDate = utcDate.toISOString().split('T')[0];
      const formattedDate = this.formatDate(localDate);
      
      const appointmentDetails = `Date: ${formattedDate}\nTime Slot: ${this.selectedTimeSlot}`;
      const snackBarRef = this.snackBar.open(`Your appointment has been successfully booked!\n${appointmentDetails}`, 'Close', {
        duration: 0, 
        panelClass: ['success-snackbar'] // Custom CSS class for styling
      });

      const appointmentData = {
        appointmentServiceId: this.serviceId,
        appointmentUserId: this.userId,   // change this to userid, now no login ppl      
        //appointmentDate: this.selected,
        appointmentDate: formattedDate,
        appointmentTime: this.selectedTimeSlot
      };


      console.log('appointmentData:', appointmentData);
  
      // Call the bookAppointment method of AvailabilityService to send the appointment data to the backend
      this.availabilityService.bookAppointmentService(this.serviceId, appointmentData).subscribe(
        (response) => {
          console.log('Appointment booked successfully:', response);
          // Optionally, perform any additional actions after successful booking
        },
        (error) => {
          console.error('Error booking appointment:', error);
          // Optionally, handle error and display message to the user
        }
      );
    } else {
      console.error('Please select a date and time slot before booking.');
      // Optionally, display a message to the user indicating that a date and time slot must be selected before booking
    }
  }
  // Function to format the date to exclude time and timezone
  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  // Function to generate time slots
  generateTimeSlots(): string[] {
    const timeSlots: string[] = [];
    for (let i = 9; i < 21; i++) { // From 9 am to 9 pm (24-hour format)
      timeSlots.push(this.formatTime(i) + ':00'); // Add start of time slot
    }
    return timeSlots;
  }

  // Function to format time in HH:mm format
  formatTime(hour: number): string {
    return ('0' + hour).slice(-2); // Add leading zero if single digit
  }

  // Function to handle selection of time slot
  selectTimeSlot(timeSlot: string): void {
    console.log('Selected time slot:', timeSlot);
    this.selectedTimeSlot = timeSlot; // Update selected time slot property
  }

  
  // ngOnInit(): void {
  //   this.getAllAvailabilitys();

  //   // this.route.params.subscribe((params) => {
  //   //   this.serviceId = params['serviceId'];
  //   //   if (this.serviceId) {
  //   //     this.getServiceDetails(this.serviceId);
  //   //   }
  //   // });
  // }

  ngOnInit(): void {

    const token = localStorage.getItem("token");
    console.log('token:', token);
    if (token) {
      this.userService.getUserIdByToken(token).subscribe(
        (userId: number) => {
          this.userId = userId;
          console.log('User ID:', userId);
          
        },
        (error: any) => {
          console.error('Error fetching user ID:', error);
        }
      );
    }

    // Extract service ID from route parameters
    this.selected = new Date();
    this.cdr.detectChanges();
    this.route.paramMap.subscribe(params => {
      const serviceIdString = params.get('serviceId');
      if (serviceIdString) {
        this.serviceId = parseInt(serviceIdString, 10); // Convert string to number
        // Fetch availability details based on service ID
        this.getAvailabilityDetails();
        this.getAppointmentDetails();
        this.getServiceDetails();
        this.getProfileImageBlob();
      } else {
        // Handle the case when 'serviceId' is null
        console.error('Service ID is null');
      }
    });
  }
  
  
  getAvailabilityDetails(): void {
    if (this.serviceId) {
      this.availabilityService.getAvailabilities(this.serviceId).subscribe(
        (data: Availability[]) => { // Expect an array of Availability
          this.availabilities = data; // Assign array of availability to availabilityDetailsList
          console.log('Availability Details List:', this.availabilities);
        },
        (error: any) => {
          console.error('Error fetching availability details:', error);
        }
      );
    }
  }

  getAppointmentDetails(): void {
    if (this.serviceId) {
      this.availabilityService.getAppointments(this.serviceId).subscribe(
        (data: Appointment[]) => {
          this.appointments = data;
          console.log('Appointment Details:', this.appointments);
          // Now that we have appointments data, mark unavailable time slots
        this.markUnavailableTimeSlots();
        },
        (error: any) => {
          console.error('Error fetching appointment:', error);
        }
      );
    }
  }

  // Function to handle selection of date
  selectDate(selectedDate: Date): void {
    // Set time to 00:00:00 to ignore time component
    selectedDate.setHours(0, 0, 0, 0);
    console.log('Selected date:', selectedDate);
    this.selected = selectedDate;
    console.log('Updated selected date:', this.selected);
    this.markUnavailableTimeSlots();
  }
  // Function to check if a date is in the past
  isPastDate(date: Date): boolean {
    const today = new Date();
    today.setHours(0, 0, 0, 0); // Set time to 00:00:00 to ignore time component
    return date >= today;
  }

  dateClassPredicate: MatCalendarCellClassFunction<any> = (date: Date) => {
    const isPast = date.getTime() < this.minDate.getTime();
    console.log('Date:', date, 'is past:', isPast);
    return isPast ? 'past-date' : '';
  };
  


  // Function to mark unavailable time slots
  markUnavailableTimeSlots(): void {
    if (this.appointments.length > 0 && this.selected) {
      const selectedDate = this.formatDate(this.selected); // Format the selected date
      this.timeSlots.forEach(timeSlot => {
        const slotExists = this.appointments.some(appointment => {
          const appointmentDate = this.formatDate(new Date(appointment.appointmentDate)); // Format the appointment date
          return appointmentDate === selectedDate && appointment.appointmentTime === timeSlot;
        });
        if (slotExists) {
          console.log('Time slot not available:', timeSlot);
        }
      });
    }
  }
  // Function to check if a time slot is unavailable
  isTimeSlotUnavailable(timeSlot: string): boolean {
    if (this.appointments.length > 0 && this.selected) {
      const selectedDate = this.formatDate(this.selected);
      return this.appointments.some(appointment => {
        const appointmentDate = this.formatDate(new Date(appointment.appointmentDate));
        return appointmentDate === selectedDate && appointment.appointmentTime === timeSlot;
      });
    }
    return false;
  }

  getServiceDetails(): void {
    if (this.serviceId) {
      this.serviceProfileService.getServiceDetails(this.serviceId).subscribe(
        (data: ServiceProfile) => {
          this.serviceDetails = data;
          console.log('Service Details:', this.serviceDetails);
        },
        (error: any) => {
          console.error('Error fetching service profile:', error);
        }
      );
    }
  }

  getProfileImageBlob(): void {
    if (this.serviceId) {
        this.serviceProfileService.getProfileImageBlob(this.serviceId).subscribe(
            (data: any[]) => {
                this.profileLogos = data;
                console.log('Profile Details:', this.profileLogos);

                this.ngZone.run(() => {
                    this.cdr.detectChanges();
                });
            },
            (error: any) => {
                console.error('Error fetching Profile Images:', error);
            }
        );
    }
  }

  isServiceAvailable(): boolean {
    return this.availabilities && this.availabilities.every(availability => availability.availabilityStatus !== 'N');
  }
  getBlobUrl(base64Data: string): string {
    if (base64Data) {
        const binaryData = Uint8Array.from(atob(base64Data), c => c.charCodeAt(0));
        const blob = new Blob([binaryData], { type: 'image/png' });
        return URL.createObjectURL(blob);
    } else {
        console.error("Base64 data is null or empty");
        return "";
    }
  }

  returnToDashBoard():void{
    this.router.navigate([""]);
  }
  
  routeTo(serviceName: string) {
    this.router.navigate(['service', serviceName]);
  }

  // routeToProfile(serviceId: number) {
  //   this.router.navigate(['/availability', serviceId]);
  // }

  logout(): void {
    this.authenticationService.logout();
  }
}
