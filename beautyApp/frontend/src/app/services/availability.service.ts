import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { Availability } from '../model/availability.model';
import { Appointment } from '../model/appointment.model';

@Injectable({
  providedIn: 'root'
})

export class AvailabilityService {
  private baseUrl = "http://localhost:8401/beautyApp/api"; 

  constructor(private httpClient: HttpClient) {}

  // getAllAvailabilitys(): Observable<Availability[]> {
  //   return this.httpClient.get<Availability[]>(`${this.baseUrl}/availability`);
  // }

  getAvailabilities(serviceId: number): Observable<Availability[]> {
    return this.httpClient.get<Availability[]>(`${this.baseUrl}/serviceProfile/${serviceId}/availability`);
  }

  getAppointments(serviceId: number): Observable<Appointment[]> {
    return this.httpClient.get<Appointment[]>(`${this.baseUrl}/serviceProfile/${serviceId}/appointment`);
  }

  getServiceStatus(serviceId: number): Observable<any> {
    return this.httpClient.get<any>(`${this.baseUrl}/serviceProfile/getServiceStatus?serviceId=${serviceId}`);
  }

  updateServiceStatus(serviceId: number): Observable<any> {
    return this.httpClient.get<any>(`${this.baseUrl}/serviceProfile/updateServiceStatus?serviceId=${serviceId}`);
  }

  bookAppointmentService(serviceId: number, appointmentData: any): Observable<any> {
    return this.httpClient.post<any>(`${this.baseUrl}/serviceProfile/${serviceId}/availability/book-appointment`, appointmentData);
  }
  
}