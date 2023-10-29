# Authentication Controller API Documentation

# Swagger UI

- URL: [http://localhost:3100/swagger-ui/index.html](http://localhost:3100/swagger-ui/index.html)

## /api/client/auth/verifyOtp (POST)

### Description
This endpoint is used to verify an OTP (One-Time Password) for authentication. It takes the OTP and phone number as input and returns a JWT (JSON Web Token) if the OTP is valid.

### Request
- Method: POST
- Request Body:

```json
{
  "otp": "string",
  "phoneNo": "string"
}
```

### Example Request
```http
POST /api/client/auth/verifyOtp
Content-Type: application/json

{
  "otp": "123456",
  "phoneNo": "+1234567890"
}
```

### Responses
- 200 OK

```json
{
  "status": "string",
  "message": "string",
  "jwt": "string"
}
```

### Example Response
```json
{
  "status": "Success",
  "message": "OTP verified successfully",
  "jwt": "eyJeyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY7ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yLad"
}
```

## /api/client/auth/requestOtp/{phoneNo} (GET)

### Description
This endpoint is used to request an OTP to be sent to a specified phone number. It's typically used for initiating the authentication process.

### Request
- Method: GET
- Path Parameter:

    - phoneNo (string)

### Example Request
```http
GET /api/client/auth/requestOtp/+1234567890
```

### Responses
- 200 OK

```json
{
  "otp": "string",
  "status": "string",
  "message": "string"
}
```

### Example Response
```json
{
  "otp": "123456",
  "status": "Success",
  "message": "OTP sent to the provided phone number"
}
```

## /api/client/auth/phoneNumber (GET)

### Description
This endpoint is used to retrieve the phone number associated with the currently authenticated user. It's helpful for users to confirm the phone number used for their account.

### Request
- Method: GET
- Header:

    - Authorization (string)

### Example Request
```http
GET /api/client/auth/phoneNumber
Authorization: Bearer <Your_JWT_Token>
```

### Responses
- 200 OK

```json
"string"
```

### Example Response
```json
"+1234567890"
```

## Schemas

### AuthRequest
```json
{
  "otp": "string",
  "phoneNo": "string"
}
```

### VerifyOtpResponseDTO
```json
{
  "status": "string",
  "message": "string",
  "jwt": "string"
}
```

### OtpResponseDTO
```json
{
  "otp": "string",
  "status": "string",
  "message": "string"
}
```
```
