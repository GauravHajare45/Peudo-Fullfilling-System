# SIM Card Controller API Documentation
- Swagger Documentation: [http://localhost:3400/swagger-ui/index.html](#http://localhost:3400/swagger-ui/index.html)

This documentation provides an overview of the API endpoints for the SIM Card Controller, including their descriptions, request and response formats, and the data structures used in the API.

## Table of Contents

1. [Get SIM Card Details](#get-sim-card-details)
2. [Order a New SIM Card](#order-a-new-sim-card)
3. [Get Mobile Number by Order ID](#get-mobile-number-by-order-id)
4. [Check SIM Card Activation](#check-sim-card-activation)
5. [Activate SIM Card](#activate-sim-card)

## Get SIM Card Details

## Base URL

- Base URL: [http://localhost:3400](http://localhost:3400)

### 1. Get SIM Card Details

- **Method:** POST
- **Endpoint:** `/simCard/simDetails`
- **Description:** Retrieve details of a SIM card using its SIM card number.
- **Request Body:**

  ```json
  {
    "simCardNumber": "string"
  }
  ```

- **Example Request:**

  ```http
  POST /simCard/simDetails
  Content-Type: application/json

  {
    "simCardNumber": "+917350031551"
  }
  ```

- **Example Response:**

  ```json
  {
    "simCardNumber": "+917350031551",
    "activated": true,
    "name": "John Doe",
    "simCompanyName": "Redapt",
    "address": "123 Main Street",
    "dob": "1990-01-15",
    "adhaarNumber": "123456789012",
    "orderId": "789",
    "iccid": "ICCID12345",
    "imsi": "IMSI67890"
  }
  ```

### 2. Order a New SIM Card

- **Method:** POST
- **Endpoint:** `/simCard/newSim`
- **Description:** Place an order for a new SIM card.
- **Request Body:**

  ```json
  {
    "name": "string",
    "simCompanyName": "string",
    "address": "string",
    "dob": "string",
    "adhaarNumber": "string",
    "email": "string"
  }
  ```

- **Example Request:**

  ```http
  POST /simCard/newSim
  Content-Type: application/json

  {
    "name": "Jane Smith",
    "simCompanyName": "XYZ Telecom",
    "address": "456 Oak Street",
    "dob": "1985-06-20",
    "adhaarNumber": "987654321098",
    "email": "jane.smith@example.com"
  }
  ```

- **Example Response:**

  ```json
  {
    "message": "New SIM card order placed successfully",
    "orderId": "345"
  }
  ```

### 3. Get Mobile Number by Order ID

- **Method:** POST
- **Endpoint:** `/simCard/getNumByOrdId`
- **Description:** Retrieve the mobile number associated with a specific order ID.
- **Request Body:**

  ```json
  "string"
  ```

- **Example Request:**

  ```http
  POST /simCard/getNumByOrdId
  Content-Type: application/json

  "345"
  ```

- **Example Response:**

  ```json
  {
    "mobileNumber": "+917350031551",
    "message": "Mobile number retrieved successfully"
  }
  ```

### 4. Check SIM Card Activation

- **Method:** POST
- **Endpoint:** `/simCard/checkSimActivation`
- **Description:** Check if a SIM card is activated or not.
- **Request Body:**

  ```json
  {
    "simCardNumber": "string"
  }
  ```

- **Example Request:**

  ```http
  POST /simCard/checkSimActivation
  Content-Type: application/json

  {
    "simCardNumber": "+917350031551"
  }
  ```

- **Example Response:**

  ```json
  true
  ```

### 5. Activate SIM Card

- **Method:** POST
- **Endpoint:** `/simCard/activate`
- **Description:** Activate a SIM card using its SIM card number.
- **Request Body:**

  ```json
  {
    "simCardNumber": "string"
  }
  ```

- **Example Request:**

  ```http
  POST /simCard/activate
  Content-Type: application/json

  {
    "simCardNumber": "+917350031551"
  }
  ```

- **Example Response:**

  ```json
  {
    "message": "SIM card activated successfully",
    "orderId": "ORDER12345"
  }
  ```

## Schemas

### CheckActiveNumber

```json
{
  "simCardNumber": "string"
}
```

### SimCardDTO

```json
{
  "simCardNumber": "string",
  "activated": true,
  "name": "string",
  "simCompanyName": "string",
  "address": "string",
  "dob": "string",
  "adhaarNumber": "string",
  "orderId": "string",
  "iccid": "string",
  "imsi": "string"
}
```

### NewSimCardRequest

```json
{
  "name": "string",
  "simCompanyName": "string",
  "address": "string",
  "dob": "string",
  "adhaarNumber": "string",
  "email": "string"
}
```

### MessageDTO

```json
{
  "message": "string",
  "orderId": "string"
}
```

### MobileNumberDTO

```json
{
  "mobileNumber": "string",
  "message": "string"
}
```

### SimCardActivationRequest

```json
{
  "simCardNumber": "string"
}
```

This documentation provides an overview of the SIM Card Controller API, its endpoints, request/response formats.
```
