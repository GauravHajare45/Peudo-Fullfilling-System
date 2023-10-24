export interface MobilePlanDTO {
    pricing: number;
    validity: string;
    dataLimit:string;
    talkTime:number;
    category:string;
    offers:string;
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
  
  