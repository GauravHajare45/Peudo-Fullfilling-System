# Payment Controller API Documentation

This documentation provides details for the Payment Controller API, which allows you to manage mobile plan payments and check plan statuses. The API is accessible at the following base URL: [http://localhost:3500](http://localhost:3500).

## Table of Contents

1. [Process Payment](#process-payment)
2. [Get Remaining Validity](#get-remaining-validity)
3. [Check Plan Status](#check-plan-status)

## Process Payment

### 1. Process Payment

- **Method:** POST
- **Endpoint:** `/payment/process`
- **Description:** Process a mobile plan payment.
- **Request Body:**

  ```json
  {
    "mobilePlanDTO": {
      "dataLimit": "string",
      "talkTime": "string",
      "pricing": 0,
      "category": "string",
      "validity": "string",
      "offers": "string",
      "paymentMethod": "string",
      "paymentStatus": "string"
    },
    "mobileNumberDTO": {
      "simCardNumber": "string"
    },
    "paymentResponseDTO": {
      "success": true
    }
  }
  ```

- **Example Request:**

  ```http
  POST /payment/process
  Content-Type: application/json

  {
    "mobilePlanDTO": {
      "dataLimit": "5GB",
      "talkTime": "Unlimited",
      "pricing": 15.99,
      "category": "Premium",
      "validity": "30 days",
      "offers": "10% off",
      "paymentMethod": "Credit Card",
      "paymentStatus": "Success"
    },
    "mobileNumberDTO": {
      "simCardNumber": "+917350031551"
    },
    "paymentResponseDTO": {
      "success": true
    }
  }
  ```

- **Response:**

  - **HTTP Status Code:** 200
  - **Media Type:** */*
  - **Example Response:**

    ```json
    true
    ```

## Get Remaining Validity

### 2. Get Remaining Validity

- **Method:** POST
- **Endpoint:** `/payment/getRemainingValidity`
- **Description:** Retrieve the remaining validity of a mobile plan associated with a SIM card.
- **Request Body:**

  ```json
  {
    "simCardNumber": "string"
  }
  ```

- **Example Request:**

  ```http
  POST /payment/getRemainingValidity
  Content-Type: application/json

  {
    "simCardNumber": "+917350031551"
  }
  ```

- **Response:**

  - **HTTP Status Code:** 200
  - **Media Type:** */*
  - **Example Response:**

    ```json
    {
      "validityRemain": 15
    }
    ```

## Check Plan Status

### 3. Check Plan Status

- **Method:** POST
- **Endpoint:** `/payment/checkPlanStatus`
- **Description:** Check the payment status and validity of a mobile plan associated with a SIM card.
- **Request Body:**

  ```json
  {
    "simCardNumber": "string"
  }
  ```

- **Example Request:**

  ```http
  POST /payment/checkPlanStatus
  Content-Type: application/json

  {
    "simCardNumber": "+917350031551"
  }
  ```

- **Response:**

  - **HTTP Status Code:** 200
  - **Media Type:** */*
  - **Example Response:**

    ```json
    true
    ```

## Schemas

### MobileNumberDTO

```json
{
  "simCardNumber": "string"
}
```

### MobilePlanDTO

```json
{
  "dataLimit": "string",
  "talkTime": "string",
  "pricing": 0,
  "category": "string",
  "validity": "string",
  "offers": "string",
  "paymentMethod": "string",
  "paymentStatus": "string"
}
```

### PaymentRequestDTO

```json
{
  "mobilePlanDTO": { ... },
  "mobileNumberDTO": { ... },
  "paymentResponseDTO": { ... }
}
```

### PaymentResponseDTO

```json
{
  "success": true
}
```

### ValidityRemainDTO

```json
{
  "validityRemain": 0
}
```

This documentation provides an overview of the Payment Controller API, its endpoints, request/response formats, and schemas.
```
