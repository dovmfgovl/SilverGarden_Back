## 사용방법 
 https://github.com/Suyeon12345/SilverGarden_Front 에서 프론트앤드를 받은 후 위 백앤드 프로젝트를 작동합니다.

## 사용 프로그램
<img width="382" alt="스크린샷 2024-03-12 오후 12 41 45" src="https://github.com/Suyeon12345/SilverGarden_Back/assets/144109053/373aedcd-724f-49f9-9612-b36d9bd482e5">
<br>

## 제작기간 

<img width="528" alt="스크린샷 2024-03-12 오전 9 24 04" src="https://github.com/Suyeon12345/SilverGarden_Back/assets/144109053/bd6ccc38-e22a-428a-b000-23a847b41790">


## 👋 소개

실버가든 프로젝트 백엔드입니다.

<br>

## 👥 멤버

<div align="center">

|                                                                **박정원**                                                                |                                                                **안수연**                                                                |                                                                **이지연**                                                                |                                                                **이슬기**                                                                |                                                                **고태규**                                                                |
| :--------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------: |
| <img src="https://github.com/Suyeon12345/SilverGarden_Front/assets/144109053/388fde3e-1c74-4f93-8d49-887ef2698df3" height=150 width=150> | <img src="https://github.com/Suyeon12345/SilverGarden_Front/assets/144109053/0330a1c3-0f60-4569-814f-540401ab0346" height=150 width=150> | <img src="https://github.com/Suyeon12345/SilverGarden_Front/assets/144109053/0146902c-087f-48c9-a601-b4cb2fd9ebdd" height=150 width=150> | <img src="https://github.com/Suyeon12345/SilverGarden_Front/assets/144109053/ec35ce79-e1ce-42a1-88ea-4270fa3e1a87" height=150 width=150> | <img src="https://github.com/Suyeon12345/SilverGarden_Front/assets/144109053/f90f98f4-1dbf-42e5-bbb1-520a7dd36cc4" height=150 width=150> |

</div>

<br>

## 💻ERD
<img width="698" alt="스크린샷 2024-03-12 오후 1 16 52" src="https://github.com/Suyeon12345/SilverGarden_Back/assets/144109053/e4583039-239b-4c29-bc88-ee88ea61eceb">



<br>

## 🧱프로젝트 아키텍처
<img width="698" alt="스크린샷 2024-03-13 오후 2 34 46" src="https://github.com/Suyeon12345/SilverGarden_Back/assets/144109053/d632f088-3c67-4b79-a713-e0efa069998c">


- 리액트에서 서버로 api 요청을 하면 앞서 말씀드린 axios 인터셉터를 거쳐  토큰을 헤더에 삽입하는 일괄처리를 거칩니다. 서버에서 반환되는 값이나 상태코드도 인터셉터를 통해 처리하도록 일괄적용하였습니다.

- 출처가 달라서 생기는 CORS 이슈를 해결하기 위해 proxy 미들웨어를 두었습니다. 

- 개발단계에서 팀원들이 동시에 db에 접근하도록 하기 위해 aws ec2 서버와 도커를 이용하여 db를 구성하였습니다. 


## 프로젝트 구성
```
├── README.md
├── .build.gradle
├── .gitignore
├── package-lock.json
├── package.json
│
├── main
│     │
│     ├── java/com.sg/silvergarden
│     │     ├── config
│     │     │      ├── login
│     │     │      │      ├── CorsConfig.java
│     │     │      │      ├── JwtAuthentification.java
│     │     │      │      └── SecurityConfiguration.java
│     │     │      ├── CorsConfiguration.java
│     │     │      ├── DatabaseConfiguration.java
│     │     │      └── YAMLConfiguration.java
│     │     └── controller
│     │     │      ├── approval
│     │     │      │      └── ApprovalController.java
│     │     │      ├── attendance
│     │     │      │      ├── AttendanceController.java
│     │     │      │      ├── HolidayCheck.java
│     │     │      │      └── HolidayCheckTest.java
│     │     │      ├── crawling
│     │     │      │      └── CrawlingController.java
│     │     │      ├── dept
│     │     │      │      └── DeptController.java
│     │     │      ├── empcreate
│     │     │      │      └── SignupController.java
│     │     │      ├── empinfo
│     │     │      │      └── EmpinfoController.java
│     │     │      ├── emplist
│     │     │      │      └── EmpListController.java
│     │     │      ├── login
│     │     │      │      ├── AdminController.java
│     │     │      │      ├── AuthentificationController.java
│     │     │      │      └── UserController.java
│     │     │      ├── member
│     │     │      │      └── MemberController.java
│     │     │      ├── message
│     │     │      │      └── MessageController.java
│     │     │      ├── mypage
│     │     │      │      └── MypageController.java
│     │     │      ├── notice
│     │     │      │      └── NoticeController.java
│     │     │      ├── payment
│     │     │      │      ├── PaymentController.java
│     │     │      │      ├── RedirectController.java
│     │     │      │      └── SmartUtil.java
│     │     │      ├── program
│     │     │      │      └── ProgramController2.java
│     │     │      ├── programcal
│     │     │      │      └── ProgramScheduleController.java
│     │     │      └── schedule
│     │     │             └── ScheduleController.java
│     │     ├──   dao
│     │     │      ├── approval
│     │     │      │      └── ApprovalDao.java
│     │     │      ├── attendance
│     │     │      │      └── AttendanceDao.java
│     │     │      ├── crawling
│     │     │      │      └── CrawlingDao.java
│     │     │      ├── dept
│     │     │      │      └── DeptDao.java
│     │     │      ├── empcreate
│     │     │      │      ├── SignupDao.java
│     │     │      │      └── SignupDaoImpl.java
│     │     │      ├── empinfo
│     │     │      │      └── EmpinfoDao.java
│     │     │      ├── emplist
│     │     │      │      └── EmpListDao.java
│     │     │      ├── login
│     │     │      │      ├── UserDao.java
│     │     │      │      └── UserDaoImpl.java
│     │     │      ├── member
│     │     │      │      └── MemberDao.java
│     │     │      ├── message
│     │     │      │      └── MessageDao.java
│     │     │      ├── mypage
│     │     │      │      └── MypageDao.java
│     │     │      ├── notice
│     │     │      │      └── NoticeDao.java
│     │     │      ├── payment
│     │     │      │      └── PaymemtDao.java
│     │     │      ├── program
│     │     │      │      └── ProgramDao2.java
│     │     │      ├── programcal
│     │     │      │      └── ProgramScheduleDao.java
│     │     │      └── schedule
│     │     │             └── ScheduleDao.java
│     │     ├── error
│     │     │      └── ErrorPageHandler.java
│     │     ├── exception
│     │     │     ├── ExceptionHandlerDao.java
│     │     │     ├── ExceptionHandlerService.java
│     │     │     └── GlobalExceptionHandler.java
│     │     ├── service
│     │     │      ├── approval
│     │     │      │      └── ApprovalService.java
│     │     │      ├── attendance
│     │     │      │      └── AttendanceService.java
│     │     │      ├── crawling
│     │     │      │      ├── CrawlingService.java
│     │     │      │      └── TriggerCrawlingService.java
│     │     │      │      └── pupperteer-script.js
│     │     │      ├── dept
│     │     │      │      └── DeptService.java
│     │     │      ├── empcreate
│     │     │      │      ├── SignupService.java
│     │     │      │      └── SignupServiceImpl.java
│     │     │      ├── empinfo
│     │     │      │      └── EmpinfoService.java
│     │     │      ├── emplist
│     │     │      │      └── EmpListService.java
│     │     │      ├── login
│     │     │      │      ├── AuthentificationService.java
│     │     │      │      ├── AutthentificationServiceImpl.java
│     │     │      │      ├── JWTService.java
│     │     │      │      ├── JWTServiceImpl.java
│     │     │      │      ├── UserService.java
│     │     │      │      └── UserServiceImpl.java
│     │     │      ├── member
│     │     │      │      └── MemberService.java
│     │     │      ├── message
│     │     │      │      └── MessageService.java
│     │     │      ├── mypage
│     │     │      │      └── MypageService.java
│     │     │      ├── notice
│     │     │      │      └── NoticeService.java
│     │     │      ├── payment
│     │     │      │      ├── PayUrlService.java
│     │     │      │      ├── PayUrlServiceImpl.java
│     │     │      │      └── PaymentService.java
│     │     │      ├── program
│     │     │      │      └── ProgramService2.java
│     │     │      ├── programcal
│     │     │      │      └── ProgramScheduleService.java
│     │     │      └── schedule
│     │     │             └── ScheduleService.java
│     │     ├── vo
│     │     │      ├── attendance
│     │     │      │      └── AttendanceVO.java
│     │     │      ├── notice
│     │     │      │       ├── EmpVO.java
│     │     │      │       ├── Role.java
│     │     │      │       └── SignupRequest.java
│     │     │      ├── login
│     │     │      │      ├── JwtAuthetificationResponse.java
│     │     │      │      ├── RefreshTokenRequest.java
│     │     │      │      └── SigninRequest.java
│     │     │      ├── payment
│     │     │      │      ├── PayTokenResponse.java
│     │     │      │      ├── PayTokenVO.java
│     │     │      │      ├── PaymentResponse.java
│     │     │      │      ├── RefundResponse.java
│     │     │      │      └── UrlResponse.java
│     │     │      └── programcal
│     │     │             └── ProgramSchedule.java
│     │     ├── ServletInitializer.java
│     │     └── SilvergardenApplication.java
│     │    
│     ├── resources
│     │     ├── mapper
│     │     │     ├── approval.xml
│     │     │     ├── attendance.xml
│     │     │     ├── crawling.xml
│     │     │     ├── dept.xml
│     │     │     ├── empInfo.xml
│     │     │     ├── empList.jsx
│     │     │     ├── empCreate.xml
│     │     │     ├── exceptionHandler.xml
│     │     │     ├── login.xml
│     │     │     ├── member.xml
│     │     │     ├── message.xml
│     │     │     ├── mypage.xml
│     │     │     ├── notice.xml
│     │     │     ├── payment.xml
│     │     │     ├── pgschedule.xml
│     │     │     ├── program.xml
│     │     │     ├── schedule.xml
│     │     │     ├── shuttle.xml
│     │     │     └── shuttleSchedule.xml
            └── logback-spring.xml
 
```
