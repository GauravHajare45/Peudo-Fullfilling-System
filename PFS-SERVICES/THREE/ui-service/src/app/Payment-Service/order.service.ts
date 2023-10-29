import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MobileNumberDTO, MobilePlanDTO, PaymentRequestDTO, PaymentResponseDTO } from '../DTO/payment.models';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private baseUrl: string = 'http://localhost:8084/payment'; 

  constructor(private http: HttpClient) {}

  processPayment(paymentRequestDTO: PaymentRequestDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}/process`, paymentRequestDTO);
  }

  getValidityLeft(mobileNumberDTO: MobileNumberDTO): Observable<any>{
    return this.http.post(`${this.baseUrl}/getRemainingValidity`, mobileNumberDTO);
  }
}
