# Mobile Number Controller API Documentation

This documentation provides an overview of the API endpoints for the Mobile Number Controller, including their descriptions, request and response formats, and the data structures used in the API.

## Swagger UI

- Swagger Documentation: [http://localhost:3200/swagger-ui/index.html](http://localhost:3200/swagger-ui/index.html)

## Table of Contents

1. [Format Mobile Number](#format-mobile-number)
2. [Check Existing Mobile Number](#check-existing-mobile-number)
3. [Check Active Plan for Mobile Number](#check-active-plan-for-mobile-number)

## Format Mobile Number

### 1. Format Mobile Number

- **Method:** POST
- **Endpoint:** `/mobileNumber/format`
- **Description:** Format a mobile number for display.
- **Request Body:**

  ```json
  {
    "mobileNumber": "string",
    "activePlan": true,
    "mobileNumberExists": true,
    "mobileNumberValid": true
  }
  ```

- **Example Request:**

  ```http
  POST /mobileNumber/format
  Content-Type: application/json

  {
    "mobileNumber": "+917350031551",
    "activePlan": true,
    "mobileNumberExists": true,
    "mobileNumberValid": true
  }
  ```

- **Example Response:**

  ```json
  "+91 73500 31551"
  ```

## Check Existing Mobile Number

### 2. Check Existing Mobile Number

- **Method:** POST
- **Endpoint:** `/mobileNumber/existingNumber`
- **Description:** Check if a mobile number already exists.
- **Request Body:**

  ```json
  {
    "mobileNumber": "string",
    "activePlan": true,
    "mobileNumberExists": true,
    "mobileNumberValid": true
  }
  ```

- **Example Request:**

  ```http
  POST /mobileNumber/existingNumber
  Content-Type: application/json

  {
    "mobileNumber": "+917350031551",
    "activePlan": true,
    "mobileNumberExists": true,
    "mobileNumberValid": true
  }
  ```

- **Example Response:**

  ```json
  true
  ```

## Check Active Plan for Mobile Number

### 3. Check Active Plan for Mobile Number

- **Method:** POST
- **Endpoint:** `/mobileNumber/activePlan`
- **Description:** Check if a mobile number has an active plan.
- **Request Body:**

  ```json
  {
    "mobileNumber": "string",
    "activePlan": true,
    "mobileNumberExists": true,
    "mobileNumberValid": true
  }
  ```

- **Example Request:**

  ```http
  POST /mobileNumber/activePlan
  Content-Type: application/json

  {
    "mobileNumber": "+917350031551",
    "activePlan": true,
    "mobileNumberExists": true,
    "mobileNumberValid": true
  }
  ```

- **Example Response:**

  ```json
  true
  ```

## Schemas

### MobileNumberDTO

```json
{
  "mobileNumber": "string",
  "activePlan": true,
  "mobileNumberExists": true,
  "mobileNumberValid": true
}
```

This documentation provides an overview of the Mobile Number Controller API, its endpoints, request/response formats, and data structures used in the API.
```