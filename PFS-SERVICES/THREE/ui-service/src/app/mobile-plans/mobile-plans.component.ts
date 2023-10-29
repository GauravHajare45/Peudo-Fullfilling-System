import { Component, OnInit } from '@angular/core';
import { MobilePlanService } from '../Api-Service/api.service';
import { AuthService } from '../Auth-Service/auth.service';
import { Router } from '@angular/router';
import { SharedDataService } from '../Payment-Service/shared-data.service';
import { MobileNumberDTO, MobilePlanDTO } from '../DTO/payment.models';
import { PaymentService } from '../Payment-Service/order.service';
import { ValidityRemainDTO } from '../DTO/plan.models';

@Component({
  selector: 'app-mobile-plans',
  templateUrl: './mobile-plans.component.html',
  styleUrls: ['./mobile-plans.component.css']
})
export class MobilePlansComponent implements OnInit{

  categoryPlans: any[] = [];
  categoryId!:string;
  allPlans: any[] = [];
  planId: number = 0;
  token: string = '';
  category: string = '';
  searchResults: any[] = [];
  searchTerm: string = '';
  combinedPlans: any[] = [];
  selectedPlan: any;

  mobilePlanDTO: MobilePlanDTO = {
    pricing: 0,
    validity: '',
    dataLimit: '',
    talkTime: '',
    category:'a',
    offers: '',
    planName:'',
    paymentMethod:'',
    paymentStatus:''
  };

  mobileNumberDTO: MobileNumberDTO = {
    simCardNumber: ''
  }
  
  validityRemainDTO: ValidityRemainDTO = {
    validityRemain: 0
  }

  constructor(private mobilePlanService: MobilePlanService, private authService: AuthService, private router: Router, private sharedDataService: SharedDataService, private paymentService: PaymentService) { }

  ngOnInit(): void {
    this.token = this.authService.getToken() || ''; 
    console.log(this.token);
    this.getCategoryPlans('Combo');
    this.getRemainingValidity();
  }

  togglePlanDetails(plan: { showDetails: boolean; }) {
    plan.showDetails = !plan.showDetails;
  }

  getAllPlans() {
    this.mobilePlanService.getAllPlans().subscribe((response: any) => {
      this.allPlans = response;
    });
  }

  selectPlan(planId: number) {
    this.mobilePlanService.selectMobilePlan(planId).subscribe((response: MobilePlanDTO) => {
      this.mobilePlanDTO = response;
      console.log(this.mobilePlanDTO, "response");
      console.log(this.mobilePlanDTO.pricing);
      console.log(this.mobilePlanDTO.validity); 
      console.log(this.mobilePlanDTO.dataLimit);
      
      this.sharedDataService.setSelectedMobilePlan(this.mobilePlanDTO);
      this.router.navigate(['/payment']);
    });
  }
  
  

  getSelectedPlanDetails() {    
    return this.selectedPlan;
  }

setSelectedCategory(category: string) {
  this.category = category;
  this.searchPlans();
}

getCategoryPlans(categoryId: string) {
  if (categoryId) {
    this.setSelectedCategory(categoryId);
    this.mobilePlanService.getCategoryPlans(categoryId).subscribe((response: any) => {
      this.combinedPlans = response;
      this.categoryPlans = response;
    });
  }
}

searchPlans(): void {
  if (this.searchTerm && this.searchTerm.trim() !== '') {
    this.mobilePlanService.searchPlans(this.searchTerm, this.category).subscribe((response: any) => {
      this.combinedPlans = response.filter((plan: any) => plan.category === this.category);
    });
  } else {
    this.combinedPlans = this.categoryPlans;
  }
} 

getRemainingValidity(): void {
  this.mobileNumberDTO = this.sharedDataService.getMobileNumber();
  console.log(this.mobileNumberDTO);
  
  this.paymentService.getValidityLeft(this.mobileNumberDTO).subscribe((response: any) => {
    this.validityRemainDTO = response;
  });
}

}
