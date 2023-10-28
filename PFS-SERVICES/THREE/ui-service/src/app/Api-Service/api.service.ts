import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { GetMobileNumberDTO } from "../DTO/sim.models";

@Injectable({
  providedIn: 'root'
})
export class MobilePlanService {

  private planApiUrl = 'http://localhost:8084/api/plans';
  private simCardApiUrl = 'http://localhost:8084/simCard';

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

  getMobileNumber(orderId: string): Observable<GetMobileNumberDTO> {
    return this.httpClient.post<GetMobileNumberDTO>(`${this.simCardApiUrl}/getNumByOrdId`, orderId);
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

  requestNewSimCard(name: string, simCompanyName: string, address: string, dob: string, adhaarNumber: string): Observable<any>{
    const requestBody = {
      name: name,
      simCompanyName: simCompanyName,
      address: address,
      dob: dob,
      adhaarNumber: adhaarNumber
    }
    return this.httpClient.post(`${this.simCardApiUrl}/newSim`, requestBody);
  }

  activateSimCard(simCardNumber: string): Observable<any>{
    const requestBody = {
      simCardNumber: simCardNumber
    }
    return this.httpClient.post(`${this.simCardApiUrl}/activate`, requestBody);
  }

}


