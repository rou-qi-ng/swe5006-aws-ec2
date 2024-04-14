import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
// import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserRegistrationComponent } from './home-pages/user-registration/user-registration.component';
// import { FormsModule } from '@angular/forms';
import { TokenInterceptor } from './helpers/token.inceptor';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { LoginPageComponent } from './home-pages/login-page/login-page.component';
import { RegisterPageComponent } from './home-pages/register-page/register-page.component';
import { ForbiddenPageComponent } from './forbidden-page/forbidden-page.component';
import { BaseHomeComponent } from './home-pages/base-home/base-home.component';
// import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
// import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSidenavModule} from '@angular/material/sidenav';
import { BusinessPagesComponent } from './business-pages/business-pages.component';
import { PortfolioPageComponent } from './portfolio-page/portfolio-page.component';
// import { UserUpdateComponent } from './user-update/user-update.component';
// import { UserDetailsComponent } from './user-details/user-details.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

import { DashboardPageComponent } from './customer-pages/dashboard-page/dashboard-page.component';
import { SearchPageComponent } from './customer-pages/search-page/search-page.component';
import { ServiceProfilePageComponent } from './customer-pages/serviceProfile-page/serviceProfile-page.component';
import { PricingPageComponent } from './customer-pages/pricing-page/pricing-page.component';
import { AvailabilityPageComponent } from './customer-pages/availability-page/availability-page.component';
import { ReviewPageComponent } from './customer-pages/review-page/review-page.component';
import { NewReviewPageComponent } from './customer-pages/review-page/review-new-page/review-new-page.component';
import { SettingsPageComponent } from './settings-page/settings-page.component';
import { ManageBusinessPagesComponent } from './manage-business-pages/manage-business-pages.component';
import { UpdatePricingPageComponent } from './business-pages/update-pricing-page/update-pricing-page.component';



@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserRegistrationComponent,
    LoginPageComponent,
    RegisterPageComponent,
    ForbiddenPageComponent,
    BaseHomeComponent,
    DashboardPageComponent,
    SearchPageComponent,
    ServiceProfilePageComponent,
    PricingPageComponent,
    AvailabilityPageComponent,
    ReviewPageComponent,
    NewReviewPageComponent,
    PortfolioPageComponent,
    SettingsPageComponent,
    ManageBusinessPagesComponent,
    UpdatePricingPageComponent,
    // UserUpdateComponent,
    // UserDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MatButtonModule,
    MatFormFieldModule,
    MatCardModule,
    MatInputModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }, provideAnimationsAsync(),],
  bootstrap: [AppComponent]
})
export class AppModule { }
