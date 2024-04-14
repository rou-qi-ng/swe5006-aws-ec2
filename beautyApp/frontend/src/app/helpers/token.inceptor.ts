// import { AuthenticationService } from './../services/authentication.service';
// import {
//   HttpEvent,
//   HttpHandler,
//   HttpInterceptor,
//   HttpRequest,
// } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import { Observable } from 'rxjs';

// @Injectable()
// export class TokenInterceptor implements HttpInterceptor {
//   constructor(public authenticationService: AuthenticationService) {}
//   intercept(
//     request: HttpRequest<any>,
//     next: HttpHandler
//   ): Observable<HttpEvent<any>> {
//     if (this.authenticationService.isLoggedIn()) {
//       let newRequest = request.clone({
//         setHeaders: {
//           Authorization: `Bearer ${this.authenticationService.getToken()}`,
//         },
//       });
//       return next.handle(newRequest);
//     }
//     return next.handle(request);
//   }
// }

import { AuthenticationService } from './../services/authentication.service';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';


@Injectable()
export class TokenInterceptor implements HttpInterceptor {
//   constructor(public authenticationService: AuthenticationService) {}
constructor(
    public authenticationService: AuthenticationService,
    private router: Router
  ) {}
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let newRequest = request;
  
    if (this.authenticationService.isLoggedIn()) {
      newRequest = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.authenticationService.getToken()}`,
          'Access-Control-Allow-Origin': '*'

        },
      }
      );
      
    
    }
  
    return next.handle(newRequest).pipe(
      catchError((error) => {
        if (error instanceof HttpErrorResponse && error.status === 403) {
          this.router.navigate(['/forbidden']);
          return throwError(error); // Re-throw the error for other handlers
        }
        return throwError(error);
      })
    );
  }
}