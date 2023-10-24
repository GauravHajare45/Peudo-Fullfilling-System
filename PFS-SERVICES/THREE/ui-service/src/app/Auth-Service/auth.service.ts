import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { OTPResponse, VerifyOTPResponse } from '../DTO/auth.models';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private tokenKey = 'jwtToken';
  private apiUrl = 'http://localhost:8084/api/client/auth';

  constructor(private http: HttpClient) {}

  saveToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  removeToken(): void {
    localStorage.removeItem(this.tokenKey);
  }

  requestOTP(phoneNo: string) {
    return this.http.get<OTPResponse>(`${this.apiUrl}/requestOtp/${phoneNo}`);
  }

  verifyOTP(phoneNo: string, otp: string) {
    return this.http.post<VerifyOTPResponse>(`${this.apiUrl}/verifyOtp`, { phoneNo, otp });
  }
}
