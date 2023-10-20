import { Component, OnInit } from '@angular/core';
import { MobilePlanService } from '../Api-Service/api.service';
import { AuthService } from '../Auth-Service/auth.service';

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

  constructor(private mobilePlanService: MobilePlanService, private authService: AuthService) { }

  ngOnInit(): void {
    this.token = this.authService.getToken() || ''; 
    console.log(this.token + "jhjhjh");
    this.getAllPlans();
  }

  getAllPlans() {
    this.mobilePlanService.getAllPlans().subscribe((response: any) => {
      this.allPlans = response;
    });
  }

  selectPlan(planId: number) {
    this.mobilePlanService.selectMobilePlan(planId).subscribe((response: any) => {
      console.log("selected");
      
    });
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

  

}
