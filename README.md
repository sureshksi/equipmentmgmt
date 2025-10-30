School Equipment Lending Platform
School Equipment Lending Platform that allows: • Students and teachers to request or borrow equipment • Lab assistants/admins to approve, issue, and track items • Administrators to monitor usage and availability

equipment-lending-service
│
├── src/main/java
│   └── com.education.lending
│       ├── EquipmentLendingSystemService.java
│       │
│       ├── config
│       │   ├── CorsConfig.java
│       │   ├── OpenAPIConfig.java
│       │   └── SecurityConfig.java
│       │
│       ├── controller
│       │   ├── AuthController.java
│       │   ├── BorrowRequestController.java
│       │   └── EquipmentController.java
│       │
│       ├── entity
│       │   ├── BorrowRequest.java
│       │   ├── Equipment.java
│       │   └── User.java
│       │
│       ├── entity.enums
│       │   ├── RequestStatus.java
│       │   └── Role.java
│       │
│       ├── pojo
│       │   ├── AuthRequest.java
│       │   ├── AuthResponse.java
│       │   └── SignupRequest.java
│       │
│       ├── repository
│       │   ├── BorrowRequestRepository.java
│       │   ├── EquipmentRepository.java
│       │   └── UserRepository.java
│       │
│       ├── security
│       │   ├── CustomUserDetailsService.java
│       │   ├── JwtAuthenticationFilter.java
│       │   ├── JwtAuthEntryPoint.java
│       │   └── JwtUtil.java
│       │
│       ├── service
│       │   ├── BorrowService.java
│       │   ├── EquipmentService.java
│       │   └── UserService.java
│       │
│       └── service.impl
│           ├── BorrowRequestServiceImpl.java
│           ├── EquipmentServiceImpl.java
│           └── UserServiceImpl.java
│
├── src/main/resources
│   └── application.properties
│
├── JRE System Library [JavaSE-17]

