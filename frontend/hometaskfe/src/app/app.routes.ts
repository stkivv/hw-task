import { Routes } from '@angular/router';
import { UserSearchPageComponent } from './user-search-page/user-search-page.component';
import { CarSearchPageComponent } from './car-search-page/car-search-page.component';

export const routes: Routes = [
  { path: '', redirectTo: 'users', pathMatch: 'full' },
  { path: 'users', component: UserSearchPageComponent },
  { path: 'cars', component: CarSearchPageComponent },
];
