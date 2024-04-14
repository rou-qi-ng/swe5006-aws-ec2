import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserRegistrationComponent } from './home-pages/user-registration/user-registration.component';
import { UserListComponent } from './user-list/user-list.component';
//import { UserUpdateComponent } from './user-update/user-update.component';
import { RegisterPageComponent } from './home-pages/register-page/register-page.component';
import { LoginPageComponent } from './home-pages/login-page/login-page.component';
import { authGuard } from './helpers/auth.guard';
import { ForbiddenPageComponent } from './forbidden-page/forbidden-page.component';
import { BaseHomeComponent } from './home-pages/base-home/base-home.component';

import { DashboardPageComponent } from './customer-pages/dashboard-page/dashboard-page.component';
import { ServiceProfilePageComponent } from './customer-pages/serviceProfile-page/serviceProfile-page.component';
import { SearchPageComponent } from './customer-pages/search-page/search-page.component';
import { BusinessPagesComponent } from './business-pages/business-pages.component';
import { PricingPageComponent } from './customer-pages/pricing-page/pricing-page.component';
import { AvailabilityPageComponent } from './customer-pages/availability-page/availability-page.component';
import { ReviewPageComponent } from './customer-pages/review-page/review-page.component';
import { PortfolioPageComponent } from './portfolio-page/portfolio-page.component';
import { ManageBusinessPagesComponent } from './manage-business-pages/manage-business-pages.component';
import { UpdatePricingPageComponent } from './business-pages/update-pricing-page/update-pricing-page.component';
import { NewReviewPageComponent } from './customer-pages/review-page/review-new-page/review-new-page.component';



const routes: Routes = [
  { path: 'forbidden', component: ForbiddenPageComponent },
  // { path: '', redirectTo: 'users', pathMatch: 'full' },
  // { path: 'create-user', component: UserRegistrationComponent },
  {
    path: 'login',
    component: LoginPageComponent,
  },
  {
    path: 'register',
    component: RegisterPageComponent,
  },
  {
    path: 'home',
    component: BaseHomeComponent,
  },
  {
    path: '',
    component: DashboardPageComponent,
    canActivate: [authGuard],
    data: {
      role: ['admin','customer']
    }
  },
  {
    path: 'users',
    component: UserListComponent,
    canActivate: [authGuard],
  },
  {
      path: 'business',
      component: BusinessPagesComponent,
      canActivate: [authGuard],
    data: {
      role:  ['admin','customer','business']
    }
  },
  {
    path: 'business/:serviceId',
    component: BusinessPagesComponent,
    canActivate: [authGuard],
  data: {
    role:  ['admin','customer','business']
  }
},
{
  path: 'updatePricing/:pricingId',
  component: UpdatePricingPageComponent,
  canActivate: [authGuard],
  data: {
    role:  ['admin', 'customer','business']
  }
},
  {
      path: 'manage',
      component: ManageBusinessPagesComponent,
      canActivate: [authGuard],
    data: {
      role:  ['admin','customer','business']
    }
  },
  {
    path: 'service/:serviceType',
    component: SearchPageComponent,
    canActivate: [authGuard],
    data: {
      role:  ['admin','customer']
    }
  },
  {
    path: 'serviceProfile/:serviceId',
    component: ServiceProfilePageComponent,
    canActivate: [authGuard],
    data: {
      role:  ['admin','customer']
    }
  },
  {
    path: 'serviceProfile/:serviceId/pricing',
    component: PricingPageComponent,
    canActivate: [authGuard],
    data: {
      role:  ['admin', 'customer']
    }
  },
  {
    path: 'serviceProfile/:serviceId/availability',
    component: AvailabilityPageComponent,
    canActivate: [authGuard],
    data: {
      role:  ['admin','customer']
    }
  },
  {
    path: 'serviceProfile/:serviceId/review',
    component: ReviewPageComponent,
    canActivate: [authGuard],
    data: {
      role:  ['admin','customer']
    }
  },
  {
    path: 'serviceProfile/:serviceId/review/new',
    component: NewReviewPageComponent,
    canActivate: [authGuard],
    data: {
      role:  ['admin','customer']
    }
  },

  {
    path: 'portfolio',
    component: PortfolioPageComponent,
    canActivate: [authGuard],
    data: {
      role:  ['admin','customer']
    }

  }
  
  // { path: 'update-user/:id', component: UserUpdateComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

