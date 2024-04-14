// import { Component } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { FormBuilder, FormGroup } from '@angular/forms';

import { SearchService } from '../../services/search.service'; 
import { ServiceProfile } from '../../model/serviceProfile.model';
import { ServiceProfileService } from '../../services/serviceProfile.service';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrl: './search-page.component.css',
})
export class SearchPageComponent implements OnInit {
  public searchForm!: FormGroup;
  public search: ServiceProfile[] = [];
  public searchSuccess: Boolean = true;
  service: string | undefined;

  searchResults: ServiceProfile[] = [];
  serviceType: string = '';
  // serviceProfileService: any;
  
  constructor(
    private formBuilder: FormBuilder,
    private router: Router, 
    private route: ActivatedRoute, 
    private authenticationService: AuthenticationService,
    private searchService: SearchService,
    private serviceProfileService: ServiceProfileService) {}

   ngOnInit(): void {
    this.searchForm = this.formBuilder.group({
      shopName: ['']
    });
    this.route.params.subscribe(params => {
      this.serviceType = params['serviceType'];
      this.service = params["serviceType"];
      if (this.serviceType) {
        console.log("testttttttttttt", this.service);
        // Call your search method here, assuming you have one
        this.startingSearch();
        // this.onSubmit();
      }
    });
  }


  startingSearch(): void {
    if (this.serviceType) {
      this.searchService.getResultsByType(this.serviceType).subscribe(
        (data: ServiceProfile[]) => {
          this.searchResults = data;
          this.search = Array.isArray(data) ? data : [data];
          this.searchSuccess = this.search && this.search[0] != null;
          console.log('Service Details:', this.searchResults);
        },
        (error: any) => {
          console.error('Error fetching service profile:', error);
        }
      );
    }
  }



  routeTo(serviceName: string) {
    this.router.navigate(['service', serviceName]);
  }

  returnToDashBoard():void{
    this.router.navigate([""]);
  }
 
  logout(): void {
    this.authenticationService.logout();
  }

  onSubmit(): void {
    const shopName = this.searchForm.value.shopName;
    console.log('Searching for shop:', shopName);
    const serviceProfile = new ServiceProfile();
    serviceProfile.serviceName = shopName;
    
    serviceProfile.serviceType = this.service ?? '';
    console.log("stupid project: ", serviceProfile);
    this.searchService.search(shopName, this.service ?? '').subscribe(
      (response) => {
        console.log('New ServiceProfile added successfully:', response);
        this.search = Array.isArray(response) ? response : [response];
        console.log(this.search);
        this.searchSuccess = this.search && this.search[0] != null;
        console.log('test ', this.searchSuccess);
      },
      (error) => {
        console.error('Error adding ServiceProfile:', error);
        // Handle error response here
      }
    );
  }

  shopPath(serviceId: number) {
    this.router.navigate(['serviceProfile', serviceId]);
  }


}
