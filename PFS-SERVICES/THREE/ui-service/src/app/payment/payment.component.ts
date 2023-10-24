import { Component, OnInit} from '@angular/core';
import { MobileNumberDTO, MobilePlanDTO, PaymentRequestDTO, PaymentResponseDTO } from '../DTO/payment.models';
import { PaymentService } from '../Payment-Service/order.service';
import { SharedDataService } from '../Payment-Service/shared-data.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit{

  rechargeAmount: number = 0;

  paymentRequestDTO: PaymentRequestDTO = {
    mobilePlanDTO:{
        pricing: 0,
        validity: '',
        dataLimit: '',
        talkTime: 0,
        category:'',
        offers: ''
      },
      mobileNumberDTO:{
        simCardNumber: ''
      },
      paymentResponseDTO:{
        success: true
      }
  };

  userMobileNumber:MobileNumberDTO = {
    simCardNumber: ''
  };
  

  constructor(private paymentService: PaymentService, private sharedDataService: SharedDataService) {}

  ngOnInit(): void {
    this.userMobileNumber = this.sharedDataService.getMobileNumber();
    console.log(this.userMobileNumber.simCardNumber + "nummmmm");
    
  }

  makePayment() {

    this.paymentRequestDTO.mobilePlanDTO = this.sharedDataService.getSelectedMobilePlan();
    this.paymentRequestDTO.mobileNumberDTO = this.userMobileNumber;

    console.log("f",this.paymentRequestDTO.mobilePlanDTO);
    console.log("s",this.paymentRequestDTO.mobileNumberDTO);
    console.log("t",this.paymentRequestDTO.mobilePlanDTO.pricing);
    
    const planPrice = this.paymentRequestDTO.mobilePlanDTO.pricing;
    
    console.log("f",this.paymentRequestDTO.paymentResponseDTO);
    

        if (this.rechargeAmount === planPrice) {
            this.paymentRequestDTO.paymentResponseDTO.success = true;
        } else {
            this.paymentRequestDTO.paymentResponseDTO.success = false;
        }
        console.log(this.paymentRequestDTO);
        

    this.paymentService.processPayment(this.paymentRequestDTO).subscribe(
      (response) => {
        console.log("sssss");
        
      },
      (error) => {
        console.log("fffffff");
      }
    );
  }
  }


