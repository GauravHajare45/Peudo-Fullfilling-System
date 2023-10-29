import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MobilePlansComponent } from './mobile-plans/mobile-plans.component';
import { SimcardComponent } from './simcard/simcard.component';
import { PaymentComponent } from './payment/payment.component';
import { ActivateSimComponent } from './activate-sim/activate-sim.component';
import { BillComponent } from './bill/bill.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [{ path: 'login', component: LoginComponent },
{ path: 'mobile-plans', component: MobilePlansComponent },
{ path: 'simcard', component: SimcardComponent },
{ path: 'payment', component: PaymentComponent },
{ path: 'activate-sim', component: ActivateSimComponent },
{ path: 'bill', component: BillComponent },
{ path: 'home', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
