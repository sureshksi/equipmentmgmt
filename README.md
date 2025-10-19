School Equipment Lending Platform
School Equipment Lending Platform that allows: • Students and teachers to request or borrow equipment • Lab assistants/admins to approve, issue, and track items • Administrators to monitor usage and availability

equipment-lending-system/
│
├── controller/
│   ├── AuthController.java
│   ├── EquipmentController.java
│   ├── RequestController.java
│
├── model/
│   ├── User.java
│   ├── Equipment.java
│   ├── BorrowRequest.java
│   ├── enums/Role.java
│   └── enums/RequestStatus.java
│
├── repository/
│   ├── UserRepository.java
│   ├── EquipmentRepository.java
│   ├── BorrowRequestRepository.java
│
├── service/
│   ├── AuthService.java
│   ├── EquipmentService.java
│   ├── BorrowService.java
│
├── dto/
│   ├── AuthDTO.java
│   ├── EquipmentDTO.java
│   ├── BorrowRequestDTO.java
│
├── config/
│   ├── WebSecurityConfig.java
│
├── EquipmentLendingSystemApplication.java
