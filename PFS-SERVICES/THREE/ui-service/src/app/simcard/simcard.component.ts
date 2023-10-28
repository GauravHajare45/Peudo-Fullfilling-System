import { Component } from '@angular/core';
import { MobilePlanService } from '../Api-Service/api.service';
import { AuthService } from '../Auth-Service/auth.service';
import { Router } from '@angular/router';
import { SharedDataService } from '../Payment-Service/shared-data.service';
import { MessageDTO } from '../DTO/sim.models';
import { NgToastService } from 'ng-angular-popup';

@Component({
  selector: 'app-simcard',
  templateUrl: './simcard.component.html',
  styleUrls: ['./simcard.component.css']
})
export class SimcardComponent {

  name: string = '';
  simCompanyName: string = 'Redapt';
  address: string = '';
  dob: string = '';
  adhaarNumber: string = '';
  simCardNumber: string = '';
  message: MessageDTO = {
    message: '',
    orderId: ''
  }
  orderId: string = '';

  constructor(private mobilePlanService: MobilePlanService, private router: Router, private sharedDataService: SharedDataService, private toast: NgToastService) {}

  requestNewSimCard() {
    this.mobilePlanService.requestNewSimCard(this.name, this.simCompanyName, this.address, this.dob, this.adhaarNumber)
      .subscribe(
        response => {
          this.message = response;
          this.sharedDataService.setCurrentStep(2);
          this.orderId = this.message.orderId;
          console.log(this.orderId);
          this.showSuccess('Simcard Requested Successful');
          setTimeout(() => {
            this.showInfo('Order Id: ' + this.orderId);
          }, 2000);
        }
      );
  }

  showSuccess(message: string) {
    this.toast.success({ detail: "SUCCESS", summary: message, duration: 5000 });
  }

  showSuccessTopCenter(message: string) {
    this.toast.success({detail:"SUCCESS",summary:message,duration:5000, position:'topCenter'});
  }
  
  showError(message: string) {
    this.toast.error({detail:"ERROR",summary:message,duration:5000});
  }

  showInfo(message: string) {
    this.toast.info({detail:"INFO",summary:message,sticky:true});
  }

}
