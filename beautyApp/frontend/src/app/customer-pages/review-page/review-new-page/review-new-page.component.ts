// newReview-page.component.ts
import { ChangeDetectorRef, Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../../../services/authentication.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServiceProfileService } from '../../../services/serviceProfile.service';
import { ServiceProfile } from '../../../model/serviceProfile.model';
import { Review } from '../../../model/review.model';
import { ReviewService } from '../../../services/review.service';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-review-new-page',
  templateUrl: './review-new-page.component.html',
  styleUrls: ['./review-new-page.component.css'],
})
export class NewReviewPageComponent implements OnInit {
  newReviewForm!: FormGroup;
  serviceId: number | null = null; 
  serviceDetails: any;
  profileLogos: any[] = [];
  newReview: Review[] = [];
  loading: boolean = true;
  userId: number | null = null; 

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService,
    private reviewService: ReviewService,
    private serviceProfileService: ServiceProfileService,
    private cdr: ChangeDetectorRef,
    private ngZone: NgZone,
    private formBuilder: FormBuilder,
    private userService: UserService
    
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.getUserId();
    
    // Extract service ID from route parameters
    this.route.paramMap.subscribe(params => {
      const serviceIdString = params.get('serviceId');
      if (serviceIdString) {
        this.serviceId = parseInt(serviceIdString, 10);
        this.getServiceDetails();
        this.getProfileImageBlob();
      } else {
        // Handle the case when 'serviceId' is null
        console.error('Service ID is null');
      }
    });
  }

  initForm(): void {
    this.newReviewForm = this.formBuilder.group({
      rating: ['', [Validators.required]],
      description: ['', [Validators.required]],
    });
  }
  
  getUserId(): void {
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
  }
  
  submitReview(): void {
    if (this.newReviewForm.valid && this.serviceId) {
      const reviewData = {
        reviewUserId: this.userId, // Assuming you have access to the current user's id
        reviewServiceId: this.serviceId,
        reviewRating: this.newReviewForm.value.rating,
        reviewDescription: this.newReviewForm.value.description,
        reviewCreatedDate: new Date().toISOString(),
      };
      
      console.log(reviewData);

      this.reviewService.addReview(this.serviceId, reviewData).subscribe(
        (response: any) => {
          console.log('Review added successfully:', response);
          // Optionally, navigate to a different route or show a success message
          this.router.navigate(['/serviceProfile', this.serviceId, 'review']);
        },
        (error: any) => {
          console.error('Error adding review:', error);
          // Optionally, show an error message to the user
        }
      );
    } else {
      console.error('Form is invalid.');
      // Optionally, show a message to the user indicating that the form is invalid
    }
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



  navigateToAddNewReview(): void {
    this.router.navigate(['/serviceProfile', this.serviceId, 'newReview', 'new']);
  }

  returnToDashBoard():void{
    this.router.navigate([""]);
  }
  
  routeTo(serviceName: string) {
    this.router.navigate(['service', serviceName]);
  }

  logout(): void {
    this.authenticationService.logout();
  }
}
