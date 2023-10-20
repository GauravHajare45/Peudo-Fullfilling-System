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

  getCategoryPlans(categoryId: string) {
    if (categoryId) { 
      console.log(categoryId);
      
      this.mobilePlanService.getCategoryPlans(categoryId).subscribe((response: any) => {
        this.categoryPlans = response;
      });
    }
  }

  selectPlan(planId: number) {
    this.mobilePlanService.selectMobilePlan(planId).subscribe((response: any) => {
      console.log("selected");
      
    });
  }

}
