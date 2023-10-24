import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AuthService } from '../Auth-Service/auth.service';
import { Router } from '@angular/router';
import { OTPResponse, VerifyOTPResponse } from '../DTO/auth.models';
import { MobileNumberDTO } from '../DTO/payment.models';
import { SharedDataService } from '../Payment-Service/shared-data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  phoneNo: string = '';
  otp: string = '';
  error: string = '';

  mobileNumber: MobileNumberDTO = {
    simCardNumber: ''
  };

  constructor(private http: HttpClient, private authService: AuthService, private router: Router, private sharedDataService: SharedDataService) {}

generateOTP() {
  this.authService.requestOTP(this.phoneNo).subscribe(
    (response: OTPResponse) => {
      console.log(response.status);
      if (response.status === 'success' && response.otp) {
        this.otp = response.otp;
        console.log(this.otp);
      } else {
        this.error = 'Unable to generate OTP. Please check your phone number.';
      }
    },
    (error) => {
      console.error('Error:', error);
      this.error = 'Unable to generate OTP. Please try again later.';
    }
  );
}

getMobileNumber(): MobileNumberDTO { 
  return { simCardNumber: this.phoneNo }; 
}

onSubmit() {
  if (this.phoneNo && this.otp) {
    this.authService.verifyOTP(this.phoneNo, this.otp).subscribe(
      (response: VerifyOTPResponse) => {
        if (response.status === 'success' && response.jwt) {
          this.authService.saveToken(response.jwt);
          console.log(response.jwt);
          this.mobileNumber.simCardNumber = this.phoneNo;
          this.sharedDataService.setMobileNumber(this.mobileNumber);
          localStorage.setItem('userMobileNumber', JSON.stringify(this.mobileNumber));

          console.log(this.mobileNumber);
          
          this.router.navigate(['/mobile-plans']);
        } else {
          this.error = 'An error occurred. Please try again later.';
        }
      },
      (error) => {
        console.error('Error:', error);
        this.error = 'Login failed. Please check your OTP and try again.';
      }
    );
  } else {
    this.error = 'Please enter your phone number and OTP.';
  }
}


  logout() {
    this.authService.removeToken();
    localStorage.removeItem('userMobileNumber');
  }
}
