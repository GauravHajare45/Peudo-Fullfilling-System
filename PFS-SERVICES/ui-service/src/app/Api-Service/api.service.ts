import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MobilePlanService {

  private planApiUrl = 'http://localhost:3300/api/plans';
  constructor(private httpClient: HttpClient) { }

  getAllPlans(): Observable<any>{
    return this.httpClient.get(`${this.planApiUrl}/allPlans`);
  }

  getCategoryPlans(category: string): Observable<any> {
    const requestBody = {
      category: category
    };
    return this.httpClient.post(`${this.planApiUrl}/categoryPlans`, requestBody);
  }  

  selectMobilePlan(planId: number): Observable<any> {
    const requestBody = { planId: planId };
    return this.httpClient.post(`${this.planApiUrl}/selectPlan`, requestBody);
  }

  searchPlans(searchTerm: string, category: string): Observable<any>{
    const requestBody = {
      searchTerm: searchTerm,
      category: category
    }
    return this.httpClient.post(`${this.planApiUrl}/searchPlans`, requestBody);
  }

}


