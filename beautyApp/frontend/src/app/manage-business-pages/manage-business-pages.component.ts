import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceProfile } from '../model/serviceProfile.model';
import { AuthenticationService } from '../services/authentication.service';
import { ServiceProfileService } from '../services/serviceProfile.service';
import { AvailabilityService } from '../services/availability.service';

@Component({
  selector: 'app-manage-business-pages',
  templateUrl: './manage-business-pages.component.html',
  styleUrl: './manage-business-pages.component.css'
})
export class ManageBusinessPagesComponent {
  service: ServiceProfile[] | undefined;
  serviceStatus: string;
  constructor(private serviceProfileService: ServiceProfileService, 
    private authenticationService: AuthenticationService,
    private availabilityService: AvailabilityService,
     private router: Router) {
      this.serviceStatus = "";
  }

  ngOnInit(): void {
    this.getService();
  }

  logout(): void {
    this.authenticationService.logout();
  }

  private getService() {
    this.serviceProfileService.getServiceList(11).subscribe(data => {
      this.service = data;
    });
    this.availabilityService.getServiceStatus(11).subscribe(data => {
      this.serviceStatus = data['availabilityStatus'];
    });
  }

  updateService(id: number) {
    this.router.navigate(['business', id]);
  }

  disableService(id: number) {
    this.availabilityService.updateServiceStatus(11).subscribe(data => {
      this.getService();
    });
  }
  enableService(id: number) {
    this.availabilityService.updateServiceStatus(11).subscribe(data => {
      this.getService();
    });
  }

  deleteService(id: number) {
    this.serviceProfileService.deleteService(11, id).subscribe(data => {
      console.log(data);
      this.getService();
    });

  }

}
