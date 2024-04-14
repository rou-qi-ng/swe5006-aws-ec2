// review-page.component.ts
import { ChangeDetectorRef, Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { FormGroup } from '@angular/forms';
import { ReviewService } from '../../services/review.service'; // Import the service
import { Review } from '../../model/review.model';
import { ServiceProfileService } from '../../services/serviceProfile.service';
import { ServiceProfile } from '../../model/serviceProfile.model';
import { User } from '../../model/user.model';
import { forkJoin, map } from 'rxjs';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-review-page',
  templateUrl: './review-page.component.html',
  styleUrls: ['./review-page.component.css'],
})
export class ReviewPageComponent implements OnInit {
  public reviewForm!: FormGroup;
  serviceId: number | null = null; 
  serviceDetails: any;
  profileLogos: any[] = [];
  reviews: Review[] = [];
  loading: boolean = true;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService,
    private reviewService: ReviewService,
    private serviceProfileService: ServiceProfileService,
    private cdr: ChangeDetectorRef,
    private ngZone: NgZone,
    private userService: UserService
    
  ) {}

  ngOnInit(): void {
    // Extract service ID from route parameters
    this.route.paramMap.subscribe(params => {
      const serviceIdString = params.get('serviceId');
      if (serviceIdString) {
        this.serviceId = parseInt(serviceIdString, 10); // Convert string to number
        // Fetch review details based on service ID
        this.getReviewDetails();
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


  
  getReviewDetails(): void {
    this.loading = true;
    if (this.serviceId) {
      this.reviewService.getReviews(this.serviceId).subscribe(
        (reviews: Review[]) => {
          const reviewObservables = reviews.map(review => {
            console.log(review.reviewUserId);
            return this.userService.getUsernameById(review.reviewUserId).pipe(
              map((username: string) => {
                console.log(username);
                review.reviewUsername = username;
                return review;
              })
            );
          });
  
          forkJoin(reviewObservables).subscribe(
            (reviewsWithUsername: Review[]) => {
              this.reviews = reviewsWithUsername;
              console.log('Review Details:', this.reviews);
              this.loading = false;
            },
            (error: any) => {
              console.error('Error fetching review:', error);
              this.loading = false;
            }
          );
        },
        (error: any) => {
          console.error('Error fetching review:', error);
          this.loading = false;
        }
      );
    }
  }

  navigateToAddReview(): void {
    this.router.navigate(['/serviceProfile', this.serviceId, 'review', 'new']);
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
