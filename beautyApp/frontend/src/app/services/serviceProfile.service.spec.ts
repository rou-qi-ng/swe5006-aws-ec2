import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { ServiceProfileService } from './serviceProfile.service';
import { ServiceProfile } from '../model/serviceProfile.model'; // Import the ServiceProfile model

describe('ServiceProfileService', () => {
  let service: ServiceProfileService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ServiceProfileService]
    });
    service = TestBed.inject(ServiceProfileService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should retrieve service details', () => {
    const serviceId = 1;
    const mockServiceProfile: ServiceProfile = {
      serviceId: 0,
      serviceLocation: '',
      serviceType: '',
      serviceDescription: '',
      serviceName: ''
    }; // Use the ServiceProfile model

    service.getServiceDetails(serviceId).subscribe(response => {
      expect(response).toEqual(mockServiceProfile);
    });

    const req = httpTestingController.expectOne(`${service.baseUrl}/serviceProfile/${serviceId}`);
    expect(req.request.method).toEqual('GET');

    req.flush(mockServiceProfile); // Pass the mockServiceProfile object
  });

  // Add similar test cases for other methods in ServiceProfileService
});
