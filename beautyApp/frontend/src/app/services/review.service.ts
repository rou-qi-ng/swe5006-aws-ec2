import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Review } from '../model/review.model';

@Injectable({
  providedIn: 'root'
})

export class ReviewService {
  private baseUrl = "http://localhost:8401/beautyApp/api"; 

  constructor(private httpClient: HttpClient) {}

  // getAllReviews(): Observable<Review[]> {
  //   return this.httpClient.get<Review[]>(`${this.baseUrl}/review`);
  // }

  getReviews(serviceId: number): Observable<Review[]> {
    return this.httpClient.get<Review[]>(`${this.baseUrl}/serviceProfile/${serviceId}/review`);
  }

  addReview(serviceId: number, reviewData: any): Observable<any> {
    return this.httpClient.post<any>(`${this.baseUrl}/serviceProfile/${serviceId}/review/new`, reviewData);
  }

}