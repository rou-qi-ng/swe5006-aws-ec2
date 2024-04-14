import { ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';
import { ManageBusinessPagesComponent } from './manage-business-pages.component';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from '../services/authentication.service';
import { ServiceProfileService } from '../services/serviceProfile.service';
import { AvailabilityService } from '../services/availability.service';
import { of } from 'rxjs';
import { ServiceProfile } from '../model/serviceProfile.model';
import { Router } from '@angular/router';
import { HttpClientTestingModule } from '@angular/common/http/testing'; // Import HttpClientTestingModule

describe('ManageBusinessPagesComponent', () => {
  let component: ManageBusinessPagesComponent;
  let fixture: ComponentFixture<ManageBusinessPagesComponent>;
  let authService: AuthenticationService;
  let serviceProfileService: ServiceProfileService;
  let availabilityService: AvailabilityService;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ManageBusinessPagesComponent],
      imports: [RouterTestingModule, HttpClientTestingModule], // Add HttpClientTestingModule here
      providers: [
        AuthenticationService,
        ServiceProfileService,
        AvailabilityService
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageBusinessPagesComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthenticationService);
    serviceProfileService = TestBed.inject(ServiceProfileService);
    availabilityService = TestBed.inject(AvailabilityService);
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call getService on init', fakeAsync(() => {
    const getServiceSpy = spyOn<ManageBusinessPagesComponent, any>(component, 'getService').and.callThrough();
    
    // Trigger the ngOnInit lifecycle hook
    component.ngOnInit();
    
    // Simulate the passage of time until all asynchronous tasks are completed
    tick();
    
    // Force change detection to update the view
    fixture.detectChanges();
    
    expect(getServiceSpy).toHaveBeenCalled();
  }));

  it('should logout', () => {
    const logoutSpy = spyOn(authService, 'logout').and.stub();

    component.logout();

    expect(logoutSpy).toHaveBeenCalled();
  });

  it('should update service', () => {
    const navigateSpy = spyOn(router, 'navigate').and.stub();
    const id = 1;

    component.updateService(id);

    expect(navigateSpy).toHaveBeenCalledWith(['business', id]);
  });

  it('should disable service', fakeAsync(() => {
    const updateServiceStatusSpy = spyOn(availabilityService, 'updateServiceStatus').and.returnValue(of(null));
    const getServiceSpy = spyOn<ManageBusinessPagesComponent, any>(component, 'getService').and.callThrough();
    const id = 1;

    component.disableService(id);
    tick();

    expect(updateServiceStatusSpy).toHaveBeenCalledWith(11);
    expect(getServiceSpy).toHaveBeenCalled();
  }));

  it('should enable service', fakeAsync(() => {
    const updateServiceStatusSpy = spyOn(availabilityService, 'updateServiceStatus').and.returnValue(of(null));
    const getServiceSpy = spyOn<ManageBusinessPagesComponent, any>(component, 'getService').and.callThrough();
    const id = 1;

    component.enableService(id);
    tick();

    expect(updateServiceStatusSpy).toHaveBeenCalledWith(11);
    expect(getServiceSpy).toHaveBeenCalled();
  }));

  it('should delete service', fakeAsync(() => {
    const deleteServiceSpy = spyOn(serviceProfileService, 'deleteService').and.returnValue(of({} as ServiceProfile));
    const getServiceSpy = spyOn<ManageBusinessPagesComponent, any>(component, 'getService').and.callThrough();
    const id = 1;

    component.deleteService(id);
    tick();

    expect(deleteServiceSpy).toHaveBeenCalledWith(11, id);
    expect(getServiceSpy).toHaveBeenCalled();
  }));
});
