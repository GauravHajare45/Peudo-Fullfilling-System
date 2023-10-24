import { Component, OnInit } from '@angular/core';
import { MobilePlanService } from '../Api-Service/api.service';
import { AuthService } from '../Auth-Service/auth.service';
import { Router } from '@angular/router';
import { SharedDataService } from '../Payment-Service/shared-data.service';
import { MobilePlanDTO } from '../DTO/payment.models';

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
    talkTime: 0,
    category:'a',
    offers: ''
  };

  constructor(private mobilePlanService: MobilePlanService, private authService: AuthService, private router: Router, private sharedDataService: SharedDataService) { }

  ngOnInit(): void {
    this.token = this.authService.getToken() || ''; 
    console.log(this.token);
    this.getAllPlans();
  }

  getAllPlans() {
    this.mobilePlanService.getAllPlans().subscribe((response: any) => {
      this.allPlans = response;
    });
  }

  // selectPlan(planId: number) {
  //   this.mobilePlanService.selectMobilePlan(planId).subscribe((response: any) => {
  //     this.mobilePlanDTO = response;
  //     this.mobilePlanServe.category = this.mobilePlanDTO.category;
  //     console.log(this.mobilePlanServe.category + "cat");
      
  //     console.log(this.mobilePlanDTO , "response");
  //     this.sharedDataService.setSelectedMobilePlan(this.mobilePlanDTO);
  //     this.router.navigate(['/payment']);
  //   });
  // }

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

logout() {
  this.authService.removeToken();
  this.router.navigate(['/login']);
}
  

}
