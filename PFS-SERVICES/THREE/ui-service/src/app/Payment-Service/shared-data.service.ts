// shared-data.service.ts

import { Injectable } from '@angular/core';
import { MobilePlanDTO, MobileNumberDTO } from '../DTO/payment.models';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SharedDataService {

  private invoiceData = new BehaviorSubject<any>(null);
  invoiceData$ = this.invoiceData.asObservable();

  constructor() {
    this.currentStep = this.loadCurrentStep();
  }

  currentStep = 1;

  private selectedMobilePlan!: MobilePlanDTO;
  private mobileNumber!: MobileNumberDTO;

  setSelectedMobilePlan(plan: MobilePlanDTO) {
    this.selectedMobilePlan = plan;
    console.log(this.selectedMobilePlan , "setwala");
  }

  generateInvoice(invoice: any) {
    this.invoiceData.next(invoice);
  }

  getSelectedMobilePlan(): MobilePlanDTO {
    console.log(this.selectedMobilePlan , "jjkjkjkjkjkjkj");
    
    return this.selectedMobilePlan;
  }

  setMobileNumber(string: MobileNumberDTO) {
    console.log(string);
    
    this.mobileNumber = string;
  }

  // getMobileNumber(): MobileNumberDTO {
  //   return this.mobileNumber;
  // }

  getMobileNumber(): MobileNumberDTO {
    if (this.mobileNumber) {
      return this.mobileNumber;
    } else {
      const storedMobileNumber = localStorage.getItem('userMobileNumber');
      if (storedMobileNumber) {
        this.mobileNumber = JSON.parse(storedMobileNumber);
        return this.mobileNumber;
      }
    }
    return { simCardNumber: '' };
  }

  private saveCurrentStep() {
    localStorage.setItem('currentStep', this.currentStep.toString());
  }

  // Load the currentStep from localStorage
  private loadCurrentStep(): number {
    const storedStep = localStorage.getItem('currentStep');
    return storedStep ? parseInt(storedStep, 10) : 1;
  }

  setCurrentStep(step: number) {
    this.currentStep = step;
    this.saveCurrentStep();
  }

  getCurrentStep(): number {
    return this.currentStep;
  }
  
}
