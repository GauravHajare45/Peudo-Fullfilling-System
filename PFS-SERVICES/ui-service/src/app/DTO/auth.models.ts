export class OTPResponse {
    constructor(public status: string, public otp?: string) {}
  }
  
  export class VerifyOTPResponse {
    constructor(public status: string, public jwt?: string) {}
  }
  