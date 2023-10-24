import { Component } from '@angular/core';
import { MobilePlanService } from '../Api-Service/api.service';
import { AuthService } from '../Auth-Service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-simcard',
  templateUrl: './simcard.component.html',
  styleUrls: ['./simcard.component.css']
})
export class SimcardComponent {

  name: string = '';
  simCompanyName: string = '';
  address: string = '';
  dob: string = '';
  adhaarNumber: string = '';
  simCardNumber: string = '';
  message: string = '';

  constructor(private mobilePlanService: MobilePlanService, private authService: AuthService, private router: Router) {}

  requestNewSimCard() {
    this.mobilePlanService.requestNewSimCard(this.name, this.simCompanyName, this.address, this.dob, this.adhaarNumber)
      .subscribe(
        response => {
          this.message = response.message;   
        }
      );
  }

  activateSimCard() {
    this.mobilePlanService.activateSimCard(this.simCardNumber)
      .subscribe(
        response => {
          this.message = response.message;      
        }
      );
  }

}
