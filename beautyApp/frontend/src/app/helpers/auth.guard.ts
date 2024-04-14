import { AuthenticationService } from './../services/authentication.service';
import { Injectable } from '@angular/core';
import { AuthenticationClient } from '../clients/authentication.client';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
} from '@angular/router';

import { Observable } from 'rxjs';
import { UrlTree } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class authGuard implements CanActivate {
  private tokenKey = 'token';
  // private role = "idk";
  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private authenticationClient: AuthenticationClient,
  ) {}
  async canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Promise<boolean> {

    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);
      return false;
    } else {
      const storedToken = localStorage.getItem(this.tokenKey);
      if (storedToken) {
        try {
          const resp = await this.authenticationClient.saveRole(storedToken).toPromise();
          if (next.data['role'] && next.data['role'].indexOf(resp) === -1) {
            this.router.navigate(['/login']);
            return false;
          }
          if (resp == "admin" && state.url != "/users"){
            this.router.navigate(['/users']);
          }
          return true;
        } catch (error) {
          console.error('Error occurred:', error);
          this.router.navigate(['/login']);
          return false;
        }
      } else {
        this.router.navigate(['/login']);
        return false;
      }
    }
  }

}