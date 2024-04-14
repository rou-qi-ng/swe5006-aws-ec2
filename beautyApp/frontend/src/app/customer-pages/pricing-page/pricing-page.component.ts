// pricing-page.component.ts
import { ChangeDetectorRef, Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { FormGroup } from '@angular/forms';
import { PricingService } from '../../services/pricing.service'; // Import the service
import { Pricing } from '../../model/pricing.model';
import { ServiceProfileService } from '../../services/serviceProfile.service';
import { ServiceProfile } from '../../model/serviceProfile.model';

@Component({
  selector: 'app-pricing-page',
  templateUrl: './pricing-page.component.html',
  styleUrls: ['./pricing-page.component.css'],
})
export class PricingPageComponent implements OnInit {
  public pricingForm!: FormGroup;
  serviceId: number | null = null; 
  serviceDetails: any;
  profileLogos: any[] = [];
  pricings: Pricing[] = [];
  regularPricings: Pricing[] = [];
  addOns: Pricing[] = [];
  loading: boolean = true;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService,
    private pricingService: PricingService,
    private serviceProfileService: ServiceProfileService,
    private cdr: ChangeDetectorRef,
    private ngZone: NgZone
    
  ) {}

  ngOnInit(): void {
    // Extract service ID from route parameters
    this.route.paramMap.subscribe(params => {
      const serviceIdString = params.get('serviceId');
      if (serviceIdString) {
        this.serviceId = parseInt(serviceIdString, 10); // Convert string to number
        // Fetch pricing details based on service ID
        this.getPricingDetails();
        this.getServiceDetails();
        this.getProfileImageBlob();
      } else {
        // Handle the case when 'serviceId' is null
        console.error('Service ID is null');
      }
    });
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

  getPricingDetails(): void {
    this.loading = true; // Set loading to true before fetching data
    if (this.serviceId) {
      this.pricingService.getPricings(this.serviceId).subscribe(
        (data: Pricing[]) => {
          this.pricings = data;
          this.regularPricings = this.pricings.filter(pricing => pricing.pricingAddon === 'N');
          this.addOns = this.pricings.filter(pricing => pricing.pricingAddon === 'Y');
          console.log('Regular Pricings:', this.regularPricings);
          console.log('Add Ons:', this.addOns);
          this.loading = false; // Set loading to false after data is fetched
        },
        (error: any) => {
          console.error('Error fetching pricing details:', error);
          this.loading = false; // Set loading to false in case of error
        }
      );
    }
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
