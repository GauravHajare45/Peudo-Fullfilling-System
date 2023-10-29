import { Component, OnInit } from '@angular/core';
import { MobileNumberDTO, MobilePlanDTO, PaymentRequestDTO, PaymentResponseDTO } from '../DTO/payment.models';
import { PaymentService } from '../Payment-Service/order.service';
import { SharedDataService } from '../Payment-Service/shared-data.service';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  rechargeAmount: number = 0;

  planPrice:number = 0;
  totalAmount:number = 0;

  paymentRequestDTO: PaymentRequestDTO = {
    mobilePlanDTO: {
      pricing: 0,
      validity: '',
      dataLimit: '',
      talkTime: '',
      category: '',
      offers: '',
      planName: '',
      paymentMethod: '',
      paymentStatus: ''
    },
    mobileNumberDTO: {
      simCardNumber: ''
    },
    paymentResponseDTO: {
      success: true
    }
  };

  userMobileNumber: MobileNumberDTO = {
    simCardNumber: ''
  };

  selectedPaymentMethod: string = ''; // To store the selected payment method
  upiId: string = ''; // UPI ID input
  cardNumber: any;
  expirationDate: any;
  cvv: any;
  invoiceService: any;


  constructor(private paymentService: PaymentService, private sharedDataService: SharedDataService , private router: Router, private toast: NgToastService) { }

  ngOnInit(): void {
    this.userMobileNumber = this.sharedDataService.getMobileNumber();
    console.log(this.userMobileNumber.simCardNumber + "nummmmm");
    console.log(this.selectedPaymentMethod);
    this.paymentRequestDTO.mobilePlanDTO = this.sharedDataService.getSelectedMobilePlan();
    this.planPrice = this.paymentRequestDTO.mobilePlanDTO.pricing;
    this.totalAmount = this.planPrice + 3;
  }

  upiPattern = /^[A-Za-z0-9._%+-]+@.*bank$/;
  cardNumberPattern = /^[0-9]{16}$/; 
  cvvPattern = /^[0-9]{3}$/; 
  expirationDatePattern = /^(0[1-9]|1[0-2])\/(20[2-9][0-9]|203[0-5])$/;


  validateUPI() {
    console.log(this.upiId); 
    if (this.upiId && !this.upiId.match(this.upiPattern)) {
      console.log('Invalid UPI'); 
    } else {
      console.log('UPI is valid'); 
    }
  }
  
  validateCardNumber() {
    if (this.cardNumber && !this.cardNumber.match(this.cardNumberPattern)) {
      console.log("Invalid Card Number");
    }
  }
  
  validateCVV() {
    if (this.cvv && !this.cvv.match(this.cvvPattern)) {
      console.log("Invalid CVV");
    }
  }
  
  validateExpirationDate() {
    if (this.expirationDate && !this.expirationDate.match(this.expirationDatePattern)) {
      console.log("Invalid Expiration Date");
    }
  }
  

  makePayment() {

    this.paymentRequestDTO.mobilePlanDTO = this.sharedDataService.getSelectedMobilePlan();
    this.paymentRequestDTO.mobileNumberDTO = this.userMobileNumber;

    console.log("f", this.paymentRequestDTO.mobilePlanDTO);
    console.log("s", this.paymentRequestDTO.mobileNumberDTO);
    console.log("t", this.paymentRequestDTO.mobilePlanDTO.pricing);

    const planPrice = this.paymentRequestDTO.mobilePlanDTO.pricing + 3;

    console.log("f", this.paymentRequestDTO.paymentResponseDTO);

    const invoice = {
      planName: this.paymentRequestDTO.mobilePlanDTO.planName,
      planPrice: this.planPrice,
      additionalCharge: 3,
      totalAmount: this.totalAmount,
      paymentMethod: this.selectedPaymentMethod
    };

    if (this.rechargeAmount === planPrice) {
      this.paymentRequestDTO.paymentResponseDTO.success = true;
      this.paymentRequestDTO.mobilePlanDTO.paymentStatus = 'Completed';
      this.paymentRequestDTO.mobilePlanDTO.paymentMethod = this.selectedPaymentMethod;
      localStorage.setItem('invoiceData', JSON.stringify(invoice));
      console.log(invoice);
      
    } else {
      this.paymentRequestDTO.paymentResponseDTO.success = false;
      this.paymentRequestDTO.mobilePlanDTO.paymentStatus = 'Incomplete';
    }
    console.log(this.paymentRequestDTO);


    this.paymentService.processPayment(this.paymentRequestDTO).subscribe(
      (response) => {
        this.showSuccess('Payment Successful');
        setTimeout(() => {
          this.router.navigate(['/bill']);
        }, 2800);
      },
      (error) => {
        this.showError('Payment Failed, Incorrect Amount');
      }
    );
  }

  paymentMethodChanged() {
    if (this.selectedPaymentMethod === 'upi') {
      this.selectedPaymentMethod = 'Upi';
    } else if (this.selectedPaymentMethod === 'card') {
      this.selectedPaymentMethod = 'Card';
    }

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
    this.toast.info({detail:"INFO",summary:message,duration:5000});
  }

}





