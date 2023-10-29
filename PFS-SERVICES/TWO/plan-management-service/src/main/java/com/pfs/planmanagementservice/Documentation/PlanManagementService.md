# Mobile Plan Controller API Documentation

This documentation provides an overview of the API endpoints for the Mobile Plan Controller, including their descriptions, request and response formats, and the data structures used in the API.

## Base URL

- Base URL: [http://localhost:3300](http://localhost:3300)
- Controller: `mobile-plan-controller`

## Table of Contents

1. [Select a Mobile Plan](#select-a-mobile-plan)
2. [Search for Mobile Plans](#search-for-mobile-plans)
3. [Fill a Mobile Plan](#fill-a-mobile-plan)
4. [Retrieve Plans by Category](#retrieve-plans-by-category)
5. [Get All Mobile Plans](#get-all-mobile-plans)

## Select a Mobile Plan

### 1. Select a Mobile Plan

- **Method:** POST
- **Endpoint:** `/api/plans/selectPlan`
- **Description:** Select a mobile plan by providing its plan ID.
- **Request Body:**

```json
{
  "planId": 0
}
```

- **Example Request:**

```http
POST /api/plans/selectPlan
Content-Type: application/json

{
  "planId": 1
}
```

- **Example Response:**

```json
{
  "planId": 1,
  "dataLimit": "5GB",
  "talkTime": "Unlimited",
  "pricing": 25.99,
  "category": "Unlimited Data",
  "validity": "30 days",
  "offers": "Free SMS"
}
```

## Search for Mobile Plans

### 2. Search for Mobile Plans

- **Method:** POST
- **Endpoint:** `/api/plans/searchPlans`
- **Description:** Search for mobile plans based on a search term and optional category filter.
- **Request Body:**

```json
{
  "searchTerm": "string",
  "category": "string"
}
```

- **Example Request:**

```http
POST /api/plans/searchPlans
Content-Type: application/json

{
  "searchTerm": "unlimited",
  "category": "Data"
}
```

- **Example Response:**

```json
[
  {
    "planId": 1,
    "planName": "Unlimited Data Plan",
    "dataLimit": "10GB",
    "talkTime": "Unlimited",
    "pricing": 39.99,
    "category": "Data",
    "validity": "30 days",
    "offers": "Free SMS"
  },
  {
    "planId": 4,
    "planName": "Family Unlimited Plan",
    "dataLimit": "20GB",
    "talkTime": "Unlimited",
    "pricing": 59.99,
    "category": "Data",
    "validity": "30 days",
    "offers": "Free Calls"
  }
]
```

## Fill a Mobile Plan

### 3. Fill a Mobile Plan

- **Method:** POST
- **Endpoint:** `/api/plans/fillPlan`
- **Description:** Fill the details of a new mobile plan.
- **Request Body:**

```json
{
  "planId": 0,
  "planName": "string",
  "dataLimit": "string",
  "talkTime": "string",
  "pricing": 0,
  "category": "string",
  "validity": "string",
  "offers": "string"
}
```

- **Example Request:**

```http
POST /api/plans/fillPlan
Content-Type: application/json

{
  "planId": 789,
  "planName": "Premium Data Plan",
  "dataLimit": "30GB",
  "talkTime": "Unlimited",
  "pricing": 49.99,
  "category": "Data",
  "validity": "30 days",
  "offers": "Free Roaming"
}
```

- **Example Response:**

```json
{
  "message": "New mobile plan details filled successfully",
  "planId": 789
}
```

## Retrieve Plans by Category

### 4. Retrieve Plans by Category

- **Method:** POST
- **Endpoint:** `/api/plans/categoryPlans`
- **Description:** Retrieve mobile plans by category.
- **Request Body:**

```json
{
  "category": "string"
}
```

- **Example Request:**

```http
POST /api/plans/categoryPlans
Content-Type: application/json

{
  "category": "Data"
}
```

- **Example Response:**

```json
[
  {
    "planId": 1,
    "planName": "Data Plan A",
    "dataLimit": "10GB",
    "talkTime": "Unlimited",
    "pricing": 29.99,
    "category": "Data",
    "validity": "30 days",
    "offers": "Free SMS"
  },
  {
    "planId": 4,
    "planName": "Data Plan B",
    "dataLimit": "20GB",
    "talkTime": "Unlimited",
    "pricing": 49.99,
    "category": "Data",
    "validity": "30 days",
    "offers": "Free Calls"
  }
]
```

## Get All Mobile Plans

### 5. Get All Mobile Plans

- **Method:** GET
- **Endpoint:** `/api/plans/allPlans`
- **Description:** Retrieve all available mobile plans.

- **Example Request:**

```http
GET /api/plans/allPlans
```

- **Example Response:**

```json
[
  {
    "planId": 1,
    "planName": "Plan X",
    "dataLimit": "5GB",
    "talkTime": "Unlimited",
    "pricing": 25.99,
    "category": "Data",
    "validity": "30 days",
    "offers": "Free SMS"
  },
  {
    "planId": 4,
    "planName": "Plan Y",
    "dataLimit": "10GB",
    "talkTime": "Unlimited",
    "pricing": 39.99,
    "category": "Data",
    "validity": "30 days",
    "offers": "Free Calls"
  }
]
```

## Schemas

### PlanIdDTO

```json
{
  "planId": 0
}
```

### MobilePlanDTO

```json
{
  "planId": 0,
  "dataLimit": "string",
  "talkTime": "string",
  "pricing": 0,
  "category": "string",
  "validity": "string",
  "offers": "string"
}
```

### SearchDTO

```json
{
  "searchTerm": "string",
  "category": "string"
}
```

### MobilePlan

```json
{
  "planId": 0,
  "planName": "string",
  "dataLimit": "string",
  "talkTime": "string",
  "pricing": 0,
  "category": "string",
  "validity": "string",
  "offers": "string"
}
```

### Category

DTO

```json
{
  "category": "string"
}
```