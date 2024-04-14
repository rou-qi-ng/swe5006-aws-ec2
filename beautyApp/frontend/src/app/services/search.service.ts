import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ServiceProfile } from '../model/serviceProfile.model';

@Injectable({
  providedIn: 'root'
})

export class SearchService {
  private baseUrl = "http://localhost:8401/beautyApp/api"; 

  constructor(private httpClient: HttpClient) {}

  // getAllSearchs(): Observable<Search[]> {
  //   return this.httpClient.get<Search[]>(`${this.baseUrl}/search`);
  // }

  getResultsByType(serviceType: string): Observable<ServiceProfile[]> {
    return this.httpClient.get<ServiceProfile[]>(`${this.baseUrl}/service/${serviceType}`);
  }

  search(serviceName: String, serviceType: String): Observable<ServiceProfile[]> {
    return this.httpClient.get<ServiceProfile[]>(`${this.baseUrl}/service/${serviceType}/search/${serviceName}`);
  }
}