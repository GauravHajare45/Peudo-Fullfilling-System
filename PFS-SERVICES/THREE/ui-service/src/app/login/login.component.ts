import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { AuthService } from '../Auth-Service/auth.service';
import { Router } from '@angular/router';
import { OTPResponse, VerifyOTPResponse } from '../DTO/auth.models';
import { MobileNumberDTO } from '../DTO/payment.models';
import { SharedDataService } from '../Payment-Service/shared-data.service';
import { NgToastService } from 'ng-angular-popup';

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

  isActive: number = 0;

  constructor(private http: HttpClient, private authService: AuthService, private router: Router, private sharedDataService: SharedDataService , private toast: NgToastService) { }

  generateOTP() {
    this.authService.requestOTP(this.phoneNo).subscribe(
      (response: OTPResponse) => {
        console.log(response.status);
        if (response.status === 'success' && response.otp) {
          this.showSuccess('OTP Sent Successfully');
          this.otp = response.otp;
          this.isActive = 1;
          console.log(this.otp);
        } else {
          this.showError('Unable to generate OTP');
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
            this.showSuccess('LoggedIn Successfully')
            this.authService.saveToken(response.jwt);
            console.log(response.jwt);
            this.mobileNumber.simCardNumber = this.phoneNo;
            this.sharedDataService.setMobileNumber(this.mobileNumber);
            localStorage.setItem('userMobileNumber', JSON.stringify(this.mobileNumber));

            console.log(this.mobileNumber);

            setTimeout(() => {
              this.router.navigate(['/mobile-plans']);
            }, 2000);
          } else {
            this.showError('Something Went Wrong');
            this.error = 'An error occurred. Please try again later.';
          }
        },
        (error) => {
          console.error('Error:', error);
          this.showError('Login failed. Please check your OTP and try again');
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


  toggleActive(index: number) {
    this.isActive = index;
  }

  moveToNext(event: any, nextInputId: number) {
    const inputValue = event.target.value;

    if (inputValue.length === 1) {
      event.target.blur();

      if (nextInputId <= 6) {
        const nextInput = document.getElementById('digit' + nextInputId) as HTMLInputElement;
        nextInput.focus();
      }
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
