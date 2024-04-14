
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationClient {
  constructor(private http: HttpClient) {}

  public login(username: string, password: string): Observable<string> {
    localStorage.setItem('username', username );
    return this.http.post(
    //   environment.apiUrl + '/user/login',
        environment.apiUrl + '/login',
      {
        username: username,
        password: password,
      },
      { responseType: 'text' }
    );
  
  }

  public register(
    username: string,
    email: string,
    password: string
  ): Observable<string> {
    return this.http.post(
      environment.apiUrl + '/register',
      {
        username: username,
        email: email,
        password: password,
      },
      { responseType: 'text' }
    );
  }

  
  public saveSession(
    token: string,
    username: string,
  ): Observable<string> {
    return this.http.post(
      environment.apiUrl + '/saveSession',
      {
        token: token,
        username: username,
      },
      { responseType: 'text' }
    );
  }


  public saveRole(token: string): Observable<string> {
    console.log("it went here");
    console.log(token);
    // localStorage.setItem('username', username );
    return this.http.post(
    //   environment.apiUrl + '/user/login',
      environment.apiUrl + '/findRole',
      {
        token: token,
      },
      { responseType: 'text'}
    );
  
  }


}