import { ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';
import { BusinessPagesComponent } from './business-pages.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { HttpClientModule } from '@angular/common/http';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing'; // Import RouterTestingModule
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // Import BrowserAnimationsModule
import { FormBuilder } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { ServiceProfileService } from '../services/serviceProfile.service';
import { PricingService } from '../services/pricing.service';
import { ChangeDetectorRef } from '@angular/core';
import { Portfolio } from '../model/portfolio.model';
import { ServiceProfile } from '../model/serviceProfile.model';
import { Pricing } from '../model/pricing.model';

describe('BusinessPagesComponent', () => {
  let component: BusinessPagesComponent;
  let fixture: ComponentFixture<BusinessPagesComponent>;
  let authenticationService: AuthenticationService;
  let serviceProfileService: ServiceProfileService;
  let pricingService: PricingService;
  let cdr: ChangeDetectorRef;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        MatSelectModule,
        HttpClientModule,
        RouterTestingModule, // Include RouterTestingModule
        BrowserAnimationsModule // Add BrowserAnimationsModule
      ],
      providers: [
        AuthenticationService,
        ServiceProfileService,
        PricingService,
        ChangeDetectorRef,
        FormBuilder
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessPagesComponent);
    component = fixture.componentInstance;
    authenticationService = TestBed.inject(AuthenticationService);
    serviceProfileService = TestBed.inject(ServiceProfileService);
    pricingService = TestBed.inject(PricingService);
    cdr = TestBed.inject(ChangeDetectorRef);

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize form with empty values', () => {
    expect(component.loginForm.get('service_name')?.value).toEqual('');
    expect(component.loginForm.get('service_description')?.value).toEqual('');
    expect(component.loginForm.get('service_type')?.value).toEqual('');
    expect(component.loginForm.get('service_location')?.value).toEqual('');
  });

  // it('should load portfolio data', fakeAsync(() => {
  //   const portfolioData: Portfolio[] = [{ portfolioServiceId: 1, portfolioData: 'Portfolio 1' }];
  //   spyOn(serviceProfileService, 'getPortfolioByServiceId').and.returnValue(of(portfolioData));

  //   component.serviceId = 1;
  //   component.loadPortfolioData();
  //   tick();

  //   expect(component.portfolioData).toEqual(portfolioData);
  // }));

  it('should delete photo', fakeAsync(() => {
    const photoId = 1;
    spyOn(serviceProfileService, 'deletePortfolioPhoto').and.returnValue(of({}));
    spyOn(component, 'loadPortfolioData');

    component.deletePhoto(photoId);
    tick();

    expect(serviceProfileService.deletePortfolioPhoto).toHaveBeenCalledWith(photoId);
    expect(component.loadPortfolioData).toHaveBeenCalled();
  }));

  it('should add product', () => {
    const initialProductsLength = component.products.length;
    component.addProduct();
    expect(component.products.length).toBe(initialProductsLength + 1);
  });

  // Add more test cases as needed
});
