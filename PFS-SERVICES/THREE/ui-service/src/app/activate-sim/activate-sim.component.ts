import { Component, ElementRef, Renderer2 } from '@angular/core';
import { MobilePlanService } from '../Api-Service/api.service';
import { AuthService } from '../Auth-Service/auth.service';
import { Router } from '@angular/router';
import { SharedDataService } from '../Payment-Service/shared-data.service';
import { GetMobileNumberDTO } from '../DTO/sim.models';
import { NgToastService } from 'ng-angular-popup';

@Component({
  selector: 'app-activate-sim',
  templateUrl: './activate-sim.component.html',
  styleUrls: ['./activate-sim.component.css']
})
export class ActivateSimComponent {
  simCardNumber: string = '';
  message: string = '';
  progressWidth = 0;
  orderId: string = '';
  showTooltipText: boolean = false;
  tooltipText: string = '';

  constructor(
    private mobilePlanService: MobilePlanService,
    private authService: AuthService,
    private router: Router,
    public sharedDataService: SharedDataService,
    private toast: NgToastService
  ) {
  }

  activateSimCard() {
    this.mobilePlanService.activateSimCard(this.simCardNumber)
      .subscribe(
        response => {
          this.message = response.message;
          this.showSuccess(this.message);
          this.sharedDataService.setCurrentStep(4);
          setTimeout(() => {
            this.router.navigate(['/login']);
            this.sharedDataService.setCurrentStep(1);
          }, 7000);
        },
        (error) => {
          this.showError('Something Went Wrong');
          console.error('Error:', error);
        }
      );
  }

  getMobileNumber(orderId: string) {
    this.mobilePlanService.getMobileNumber(orderId)
      .subscribe((data: GetMobileNumberDTO) => {
        this.showInfo(data.mobileNumber);
        this.sharedDataService.setCurrentStep(3);
      },
      (error) => {
        this.showError('Wrong OrderId');
        console.error('Error:', error);
      });
  }

  showSuccess(message: string) {
    this.toast.success({ detail: 'SUCCESS', summary: message, duration: 5000 });
  }

  showSuccessTopCenter(message: string) {
    this.toast.success({ detail: 'SUCCESS', summary: message, duration: 5000, position: 'topCenter' });
  }

  showError(message: string) {
    this.toast.error({ detail: 'ERROR', summary: message, duration: 5000 });
  }

  showInfo(message: string) {
    this.toast.info({ detail: 'INFO', summary: message, sticky: true });
  }
}
