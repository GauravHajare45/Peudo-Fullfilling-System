import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpInterceptorService } from './Auth-Service/HttpInterceptorService';
import { LoginComponent } from './login/login.component';
import { MobilePlansComponent } from './mobile-plans/mobile-plans.component';
import { SimcardComponent } from './simcard/simcard.component';
import { PaymentComponent } from './payment/payment.component';
import { NgToastModule } from 'ng-angular-popup';
import { ActivateSimComponent } from './activate-sim/activate-sim.component';
import { BillComponent } from './bill/bill.component';
import { HomeComponent } from './home/home.component';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MobilePlansComponent,
    SimcardComponent,
    PaymentComponent,
    ActivateSimComponent,
    BillComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgToastModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
