
# Omnicart Development Guidelines

## Technology Stack
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Gradle
- MySQL

## Database Design
- omnicart_user_db.mwb
- omnicart_product_db.mwb
- omnicart_order_db.mwb

## Architecture
- Follow layered architecture.
- Keep controllers thin.
- Put business logic in service implementations.
- Use DTOs for API requests and responses.
- Use repositories for database operations.
- Follow SOLID principles.

## Security
- JWT-based stateless authentication.
- JWT signed using asymmetric keys.
- Use authorities for endpoint authorization.
- Retrieve authenticated user identity from SecurityContextHolder.
- Never trust userId supplied by the frontend.
- Validate JWT issuer, signature, expiration and audience.

## Coding Standards
- Follow layered architecture.
- Follow SOLID principles.
- Use constructor injection.
- Use Lombok where appropriate.
- Use DTOs for API requests and responses.
- Keep controllers thin.
- Implement business logic in service classes.
- Use repositories for persistence.
- Use proper exception handling.
- Use @Transactional where appropriate.

## Testing
- Write JUnit 5 and Mockito tests.
- Write integration tests for repository and API workflows.
- Run Gradle tests after modifications.
- Do not modify existing tests just to make them pass.

## Microservice Structure
1. omnicart-user-service : User Microservice
   - User Registration and Authentication
   - Authorization and Permission Management
   - Vendor Management

2. omnicart-product-service : Product Microservice
   - Brand Management
   - Category Management
   - Product Management
   - Inventory Management

3. omnicart-payment-service : Payment Microservice
   - Payment Processing
   - Razorpay Integration

4. omnicart-order-service : Order Microservice
   - Order Management
   - Order Tracking

5. omnicart-notification-service : Notification Microservice
   - Email and Notification Management

## AI Agent Rules
- Inspect existing code before implementation.
- Reuse existing classes and conventions.
- Do not duplicate existing functionality.
- Do not modify unrelated files.
- Explain implementation plans before large changes.
- Do not modify existing tests to hide failures.
- Review security implications of every change.