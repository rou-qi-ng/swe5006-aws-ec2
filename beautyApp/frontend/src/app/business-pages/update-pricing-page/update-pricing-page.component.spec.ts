import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UpdatePricingPageComponent } from './update-pricing-page.component';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';
import { PricingService } from '../../services/pricing.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Pricing } from '../../model/pricing.model';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // Import BrowserAnimationsModule


describe('UpdatePricingPageComponent', () => {
  let component: UpdatePricingPageComponent;
  let fixture: ComponentFixture<UpdatePricingPageComponent>;
  let pricingService: jasmine.SpyObj<PricingService>;
  let router: jasmine.SpyObj<Router>;
  let route: ActivatedRoute;
  let formBuilder: FormBuilder;

  beforeEach(async () => {
    pricingService = jasmine.createSpyObj('PricingService', ['getPricings', 'updatePricing']);
    router = jasmine.createSpyObj('Router', ['navigate']);

    // Stubbing the methods for getPricings and updatePricing
    pricingService.getPricings.and.returnValue(of([])); // Return an empty observable
    pricingService.updatePricing.and.returnValue(of({})); // Return an empty observable

    await TestBed.configureTestingModule({
      declarations: [UpdatePricingPageComponent],
      imports: [
        ReactiveFormsModule,
        HttpClientTestingModule,
        MatCardModule,
        MatFormFieldModule,
        MatSelectModule,
        MatInputModule,
        BrowserAnimationsModule

      ],
      providers: [
        FormBuilder,
        { provide: PricingService, useValue: pricingService },
        { provide: Router, useValue: router },
        { provide: ActivatedRoute, useValue: { paramMap: of({ get: () => '1' }) } }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatePricingPageComponent);
    component = fixture.componentInstance;
    route = TestBed.inject(ActivatedRoute);
    formBuilder = TestBed.inject(FormBuilder);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize form with pricing data', () => {
    const mockPricing: Pricing = {
      pricingId: 1,
      pricingName: 'Mock Pricing',
      pricingCost: 100,
      pricingAddon: 'Mock Addon',
      pricingServiceId: 1
    };

    // Return mock data from the service
    pricingService.getPricings.and.returnValue(of([mockPricing]));

    // Manually trigger ngOnInit
    component.ngOnInit();

    // Trigger change detection
    fixture.detectChanges();

    // Expect getPricings to have been called
    expect(pricingService.getPricings).toHaveBeenCalled();

    // Check if the form has been initialized with the correct data
    expect(component.loginForm.value).toEqual({
      pricingName: 'Mock Pricing',
      pricingCost: 100,
      pricingAddon: 'Mock Addon'
    });
  });


//   it('should call updatePricing and navigate on form submission', () => {
//     // Set form values
//     component.loginForm.setValue({
//       pricingName: 'Updated Pricing',
//       pricingCost: 200,
//       pricingAddon: 'Updated Addon'
//     });
  
//     const updatedPricing: Pricing = {
//       pricingId: 1,
//       pricingName: 'Updated Pricing',
//       pricingCost: 200,
//       pricingAddon: 'Updated Addon',
//       pricingServiceId: 1
//     };
  
//     // Manually trigger the form submission
//     component.onSubmit();
  
//     // Expect updatePricing to have been called with the updated pricing
//     expect(pricingService.updatePricing).toHaveBeenCalledWith(updatedPricing);
  
//     // Expect router.navigate to have been called with the correct route
//     expect(router.navigate).toHaveBeenCalledWith(['business', updatedPricing.pricingServiceId]);
//   });
  
  
});
