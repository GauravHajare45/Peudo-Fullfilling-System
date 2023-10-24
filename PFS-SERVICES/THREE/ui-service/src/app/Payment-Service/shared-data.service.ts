// shared-data.service.ts

import { Injectable } from '@angular/core';
import { MobilePlanDTO, MobileNumberDTO } from '../DTO/payment.models';

@Injectable({
  providedIn: 'root',
})
export class SharedDataService {
  private selectedMobilePlan!: MobilePlanDTO;
  private mobileNumber!: MobileNumberDTO;

  setSelectedMobilePlan(plan: MobilePlanDTO) {
    this.selectedMobilePlan = plan;
    console.log(this.selectedMobilePlan , "setwala");
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
  
}
