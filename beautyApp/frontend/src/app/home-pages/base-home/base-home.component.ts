import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-base-home',
  templateUrl: './base-home.component.html',
  styleUrl: './base-home.component.css'
})
export class BaseHomeComponent {
  
  constructor(private router: Router) {

 }
  public login() {
    this.router.navigate(['/login']);
  }
}
