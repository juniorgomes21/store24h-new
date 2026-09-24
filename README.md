# Store24h

Backend application developed from scratch with Java and Spring Boot for a real-world platform focused on phone number activation and SMS verification services.

The system was developed for a real client and includes user management, authentication and authorization, service management, phone number inventory, balance management, activation workflows, SMS processing, scheduled operations, and a public API for external integrations.

> This is a historical project developed for a client and is maintained in this repository as part of my professional portfolio.

---

## Overview

Store24h is a backend platform designed to manage the complete lifecycle of phone number activations.

The application manages the interaction between users, services, phone numbers, activations, SMS messages, purchases, and account balances.

The backend is responsible for enforcing the business rules involved in these operations, from balance validation and number selection to SMS reception, activation completion, cancellation, and refunds.

The system also provides a public API that allows external applications to interact with the platform programmatically.

---

## Main Features

### User Management

- User registration and management
- User authentication
- Role-based authorization
- Account balance management
- Administrative operations
- Password hashing with BCrypt

### Service Management

- Service registration
- Service activation and deactivation
- Service price management
- Quantity and availability control
- Service-specific configuration
- Dynamic service selection

### Phone Number Management

- Phone number registration
- Number availability control
- Number reservation
- Number status management
- Association between phone numbers and services
- Number inventory management
- Automatic selection of available numbers

### Activation Management

- Activation requests
- Balance validation
- Phone number selection
- Number reservation
- Activation creation
- SMS waiting state
- SMS reception
- Retry operations
- Activation completion
- Activation cancellation
- Refund processing when applicable

### SMS Processing

- SMS reception and storage
- Service-specific SMS processing
- Verification code extraction
- Regular expression-based code identification
- Activation status updates based on received SMS

### Public API

The system provides a public API for external integrations.

Supported operations include:

- Balance queries
- Phone number requests
- Activation queries
- SMS queries
- Activation status updates
- Activation cancellation
- Retry operations
- Service information
- Price and availability queries

The public API uses API Key authentication.

---

## Architecture

The application follows a layered backend architecture:

```text
                    ┌─────────────────┐
                    │     Client      │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │    REST API     │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   Controllers   │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │    Services     │
                    │ Business Logic  │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   Repositories  │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │     MySQL       │
                    └─────────────────┘
