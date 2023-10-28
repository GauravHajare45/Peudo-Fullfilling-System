export interface MobilePlanDTO {
    pricing: number;
    validity: string;
    dataLimit:string;
    talkTime:string;
    category:string;
    offers:string;
    planName:string;
    paymentMethod:string;
    paymentStatus:string;
  }
  
  export interface MobileNumberDTO {
    simCardNumber: string;
  }
  
  export interface PaymentResponseDTO {
    success: boolean;
  }

  export interface PaymentRequestDTO {
    mobilePlanDTO: MobilePlanDTO;
    mobileNumberDTO: MobileNumberDTO;
    paymentResponseDTO: PaymentResponseDTO;
  }
  
  