import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MobilePlansComponent } from './mobile-plans/mobile-plans.component';

const routes: Routes = [{ path: 'login', component: LoginComponent },
{ path: 'mobile-plans', component: MobilePlansComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
