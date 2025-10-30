School Equipment Lending Platform
School Equipment Lending Platform that allows: • Students and teachers to request or borrow equipment • Lab assistants/admins to approve, issue, and track items • Administrators to monitor usage and availability

equipment-lending-service
│
├── src/main/java
│   └── com.education.lending
│       ├── EquipmentLendingSystemService.java          # Main application entry point
│       │
│       ├── config/                                     # All framework-related configurations
│       │   ├── CorsConfig.java
│       │   ├── OpenAPIConfig.java
│       │   ├── SecurityConfig.java
│       │
│       ├── constants/                                  # Central place for constant values
│       │   └── AppConstants.java
│       │
│       ├── controller/                                 # REST Controllers (API layer)
│       │   ├── AuthController.java
│       │   ├── BorrowRequestController.java
│       │   └── EquipmentController.java
│       │
│       ├── dto/                                        # DTOs (instead of `pojo`)
│       │   ├── request/
│       │   │   ├── AuthRequest.java
│       │   │   ├── SignupRequest.java
│       │   └── response/
│       │       ├── AuthResponse.java
│       │
│       ├── entity/                                     # JPA entities
│       │   ├── BorrowRequest.java
│       │   ├── Equipment.java
│       │   └── User.java
│       │
│       ├── enums/                                      # Enum types (flattened for clarity)
│       │   ├── RequestStatus.java
│       │   └── Role.java
│       │
│       ├── exception/                                  # Custom exception handling
│       │
│       ├── repository/                                 # Data access layer
│       │   ├── BorrowRequestRepository.java
│       │   ├── EquipmentRepository.java
│       │   └── UserRepository.java
│       │
│       ├── security/                                   # Authentication and security logic
│       │   ├── CustomUserDetailsService.java
│       │   ├── JwtAuthenticationFilter.java
│       │   ├── JwtAuthEntryPoint.java
│       │   └── JwtUtil.java
│       │
│       ├── service/                                    # Interfaces for business logic
│       │   ├── BorrowService.java
│       │   ├── EquipmentService.java
│       │   ├── UserService.java
│       │
│       └── service/impl/                               # Implementations of services
│           ├── BorrowServiceImpl.java
│           ├── EquipmentServiceImpl.java
│           ├── UserServiceImpl.java
│
├── src/main/resources/
│   ├── application.properties
│   ├── logback-spring.xml                              # Centralized logging configuration                                
│
├── JRE System Library [JavaSE-17]


