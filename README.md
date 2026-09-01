# Restful Booker API Tests

Automated REST API tests for the Restful Booker service using Page Object Model pattern.

## Project Information

| Field | Details |
|-------|---------|
| **Author** | Susan Laime Lucero |
| **Course** | Certification II |
| **Instructor** | Mauricio Terceros |

## Tech Stack

- **Java 17** - Programming language
- **Maven** - Build tool
- **JUnit 5** - Test framework
- **REST Assured** - API testing library
- **Jackson** - JSON serialization

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+

### Run Tests
```bash
mvn test
```

## Project Structure
```
src/test/java/
├── models/       # Data models (Booking, BookingDates, etc.)
├── pages/        # Page Object classes
└── tests/        # Test cases
```

## Features
- Booking creation, retrieval, update, and deletion
- Authentication tests
- Data validation and error handling
