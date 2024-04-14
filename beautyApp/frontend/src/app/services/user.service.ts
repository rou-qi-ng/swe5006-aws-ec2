import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private basUrl = "http://localhost:8401/beautyApp/api/login"
  private basUrl2 = "http://localhost:8401/beautyApp/api/"

  constructor(private httpClient: HttpClient) {
  }

  getUserList(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.basUrl}`);
  }

  createUser(user: User): Observable<Object> {
    return this.httpClient.post(`${this.basUrl}`, user);
  }

  getUserById(id: number): Observable<User>{
    return this.httpClient.get<User>(`${this.basUrl}/${id}`);
  }

  updateUser(id:number, user:User): Observable<Object>{
    return this.httpClient.put(`${this.basUrl}/${id}`, user);
  }

  deleteUser(id:number): Observable<Object>{
    return this.httpClient.delete(`${this.basUrl}/${id}`);
  }

  getCode(): Observable<any>{
    this.basUrl2 = this.basUrl2 + 'getReferralCode';
    return this.httpClient.get(`${this.basUrl2}`);
  }

  getUserIdByToken(token: string): Observable<any> {
    return this.httpClient.get<any>(`${this.basUrl2}getUserId`, { params: { token } });
  }

  getUsernameById(id: number): Observable<any>{
    return this.httpClient.get<any>(`${this.basUrl2}getUsername`, { params: { userId: id } });
  }

  
  
  
}
