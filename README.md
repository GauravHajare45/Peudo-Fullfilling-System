## Pseudo Fulfillment System 
- Author- Gaurav Hajare<br>

### Table of Contents
1. Introduction<br>
   1.1 Purpose<br>
   1.2 Scope<br>
   1.3 Document Conventions<br>
   1.4 Intended Audience<br>
   1.5 System Overview<br>

2. Functional Requirements<br>
   2.1 User Interface<br>
   2.2 Plan Selection<br>
   2.3 Phone Number Validation<br>
   2.4 SIM Card Handling<br>
   2.5 Billing Information<br>
   2.6 Payment Processing<br>
   2.7 Confirmation<br>
   2.8 Plan Activation<br>
   2.9 Error Handling<br>
   2.10 Testing<br>

3. Non-Functional Requirements<br>
   3.1 Usability<br>
   3.2 Performance<br>
   3.3 Security<br>
   3.4 Reliability<br>

4. System Architecture<br>
   4.1 Frontend (Angular)<br>
   4.2 Backend (Spring Boot)<br>
   4.3 Database (MySQL)<br>

5. Data Flow Diagram<br>
   5.1 User Registration and Activation Flow<br>
   5.2 Payment Processing Flow<br>

6. User Interface Design<br>
   6.1 Login/Registration Page<br>
   6.2 Plan Selection Page<br>
   6.3 Billing Information Page<br>
   6.4 Confirmation Page<br>

7. Deployment<br>
   7.1 Deployment Environment<br>
   7.2 Installation and Configuration<br>
   7.3 Backup and Recovery<br>

8. Maintenance and Support<br>
   8.1 Bug Tracking and Resolution<br>
   8.2 Feature Updates<br>
   8.3 User Support<br>

9. Glossary<br>

---

## 1. Introduction

### 1.1 Purpose
The purpose of this Software Requirements Specification (SRS) document is to define the functional and non-functional requirements for the Pseudo Change Fulfillment System. This system allows Telco customers to activate new mobile plans, including selecting plans, validating phone numbers, handling SIM cards, providing billing information, processing payments, and confirming plan activations.

### 1.2 Scope
The Pseudo Fulfillment System encompasses both the frontend and backend components, serving Telco customers. It covers plan selection, payment processing, plan activation, and error handling.

### 1.3 Document Conventions
- Use of square brackets "[ ]" indicates optional items.
- Use of angle brackets "< >" indicates user input.
- Use of capital letters indicates placeholders for specific values (e.g., PLAN_NAME).

### 1.4 Intended Audience
The primary audience for this document includes software developers, testers, and project stakeholders involved in the development, testing, and deployment of the Mobile Plan Change Fulfillment System.

### 1.5 System Overview
The system comprises a frontend developed using Angular, a backend developed using Spring Boot, and a MySQL database. It allows users to activate new mobile plans by selecting plans, validating phone numbers, handling SIM cards, providing billing information, processing payments, and confirming plan activations.

---

## 2. Functional Requirements

### 2.1 User Interface
- The system must provide a user-friendly web interface for customers.
- The interface should facilitate easy navigation and data entry.

### 2.2 Plan Selection
- Customers can view a list of available mobile plans.
- Each plan should display plan name, data limits, talk time, and pricing.
- Customers can select a plan for activation.

### 2.3 Phone Number Validation
- Customers must enter their existing mobile phone number.
- The system should validate phone numbers for correct format (e.g., XXX-XXX-XXXX).
- Ensure the phone number is not already in use for another active plan.

### 2.4 SIM Card Handling
- Customers can request a new SIM card if needed.
- If the customer has a compatible SIM card, they can enter the SIM card number.

### 2.5 Billing Information
- Customers provide billing information, including name and address.
- Select a payment method (credit card, PayPal, etc.).

### 2.6 Payment Processing
- The system processes payments based on the selected plan and payment method.
- Provides confirmation of payment status (success or failure).

### 2.7 Confirmation
- Upon successful payment, customers receive a confirmation email or SMS.
- Confirmation includes details of the activated plan, billing information, and next steps.

### 2.8 Plan Activation
- The selected mobile plan is activated immediately upon successful payment.
- Customers can make calls, send texts, and use data with the new plan.

### 2.9 Error Handling
- The system handles errors gracefully (e.g., failed payments, network issues, incorrect input data).
- Clear error messages are provided to the customer for resolution.

### 2.10 Testing
- Comprehensive testing will be performed, including unit tests, integration tests, and user acceptance testing (UAT).

---

## 3. Non-Functional Requirements

### 3.1 Usability
- The user interface must be intuitive and user-friendly.
- Response times for user interactions should be minimal.

### 3.2 Performance
- The system should handle concurrent user requests efficiently.
- Response times for payment processing should be fast.
- Scalability should be considered for future growth.

### 3.3 Security
- Customer data must be protected and stored securely.
- Payment information must be encrypted during transmission.
- Access controls should limit authorized user access.

### 3.4 Reliability
- The system should be highly available with minimal downtime.
- Automated backups and disaster recovery plans should be in place.

---

## 4. System Architecture

### 4.1 Frontend (Angular)
- Angular will be used for the frontend.
- The user interface will be responsive and accessible.
- @angular-devkit/architect       0.1602.2
- @angular-devkit/build-angular   16.2.2
- @angular-devkit/core            16.2.2
- @angular-devkit/schematics      16.2.2
- @angular/cli                    16.2.2
- @schematics/angular             16.2.2
- rxjs                            7.8.1
- typescript                      5.1.6
- zone.js                         0.13.3

### 4.2 Backend (Spring Boot)
- Spring Boot will serve as the backend.
- It will handle user requests, plan activation, and payment processing.
- Project- Maven
- Language- Java
- Spring Boot- 3.1.4
- Java- 17

### 4.3 Database (MySQL)
- MySQL will be used to store customer data, plan information, and payment records.
- Data integrity and security measures will be implemented.

---

## 5. Data Flow

- 5.1 User Registration and Activation
- 5.2 Payment Processing 

---

## 6. User Interface Design

### 6.1 Login/Registration Page
- Login/Registration Page option<br>
- Auth 2.0 Login Option

### 6.2 Plan Selection Page
- Plan Selection Page

### 6.3 Billing Information Page
- Billing Information Page

### 6.4 Confirmation Page
- Confirmation Page

---

## 7. Deployment

### 7.1 Deployment Environment
- The system will be deployed on a secure server infrastructure.
- Deployment environments (development, staging, production) will be defined.

### 7.2 Installation and Configuration
- Installation and configuration guides will be provided for administrators.
- Automated deployment scripts may be used for ease of setup.

### 7.3 Backup and Recovery
- Automated backup procedures will be implemented.
- A recovery plan will be in place in case of system failure.

---

## 8. Maintenance and Support

### 8.1 Bug Tracking and Resolution
- A bug tracking system will be used to log
 and prioritize issues.

### 8.2 Feature Updates
- Future feature updates and enhancements will be planned and documented.

### 8.3 User Support
- User support channels (e.g., helpdesk, email) will be available for customer assistance.

---

## 9. Glossary
- Telco: Telecommunications Company
- SRS: Software Requirements Specification
- UI: User Interface
- API: Application Programming Interface

---

