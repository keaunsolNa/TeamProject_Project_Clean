DROP TABLE "TBL_VACATION_COMMIT";
DROP TABLE TBL_REASON;
DROP TABLE TBL_ADMIN_IP_ADRESS;
DROP TABLE "TBL_NOTIFICATION";
DROP TABLE "TBL_EMPLOYEE_PAY";
DROP TABLE "TBL_VACATION";
DROP TABLE "TBL_SURCHARGE";
DROP TABLE "TBL_BOOKMARK";
DROP TABLE "TBL_ADMIN_PICTURE";
DROP TABLE TBL_CHECKLIST;
DROP TABLE TBL_APPLY_EMPLOYEE;
DROP TABLE "TBL_ADMIN_ADDRESS";
DROP TABLE "TBL_ADMIN_EMAIL";
DROP TABLE "TBL_ADMIN_PAY";
DROP TABLE "TBL_SUPPLEMENT_SERVICE";
DROP TABLE "TBL_RETIRE_ADMIN";
DROP TABLE "TBL_RETIRE_EMPLOYEE";
DROP TABLE TBL_ADMIN_MEMBER_ROLE;
DROP TABLE TBL_AUTHORITY;
DROP TABLE "TBL_RESERVATION_INFO" CASCADE CONSTRAINTS;
DROP TABLE PERSISTENT_LOGINS CASCADE CONSTRAINTS;
DROP TABLE "TBL_ADMIN";
DROP TABLE "TBL_EMPLOYEE";
-----------------------------------------------
DROP SEQUENCE SEQ_TBL_VACATION_COMMIT;
DROP SEQUENCE SEQ_TBL_VACATION;
DROP SEQUENCE SEQ_CHECK_NO;
DROP SEQUENCE SEQ_TBL_RESERVATION;
DROP SEQUENCE SEQ_TBL_BOOKMARK;
DROP SEQUENCE SEQ_TBL_NOTIFICATION;
DROP SEQUENCE SEQ_EMP_NO;
DROP SEQUENCE SEQ_EMP_RET_NO;
DROP SEQUENCE SEQ_TBL_RETIRE_ADMIN;
DROP SEQUENCE SEQ_TBL_ADMIN;
DROP SEQUENCE SEQ_TBL_ADMIN_PAY;
DROP SEQUENCE SEQ_TBL_EMPLOYEE_PAY;
DROP SEQUENCE SEQ_TBL_ADMIN_PICTURE;
DROP SEQUENCE SEQ_TBL_REST_COMMIT; -- 삭제한 시퀀스입니다. 최초 삭제 후 drop문 지워주세요~~

-----------------------------------------------
CREATE SEQUENCE SEQ_TBL_VACATION_COMMIT NOCACHE START WITH 1;
CREATE SEQUENCE SEQ_TBL_VACATION NOCACHE START WITH 1;
CREATE SEQUENCE SEQ_CHECK_NO NOCACHE;
CREATE SEQUENCE SEQ_TBL_RESERVATION NOCACHE;
CREATE SEQUENCE SEQ_TBL_BOOKMARK NOCACHE;
CREATE SEQUENCE SEQ_TBL_NOTIFICATION NOCACHE;
CREATE SEQUENCE SEQ_EMP_NO NOCACHE;
CREATE SEQUENCE SEQ_EMP_RET_NO NOCACHE;
CREATE SEQUENCE SEQ_TBL_RETIRE_ADMIN NOCACHE START WITH 1;
CREATE SEQUENCE SEQ_TBL_ADMIN NOCACHE START WITH 1;
CREATE SEQUENCE SEQ_TBL_ADMIN_PAY NOCACHE;
CREATE SEQUENCE SEQ_TBL_EMPLOYEE_PAY NOCACHE;
CREATE SEQUENCE SEQ_TBL_REST_COMMIT NOCACHE;
CREATE SEQUENCE SEQ_TBL_ADMIN_PICTURE NOCACHE;

CREATE TABLE PERSISTENT_LOGINS
(
username VARCHAR2(255),
series VARCHAR2(255),
token VARCHAR2(255),
last_used DATE
);

CREATE TABLE "TBL_EMPLOYEE" (
	"EMPLOYEE_NO"	NUMBER		NOT NULL,
	"EMPLOYEE_NAME"	NVARCHAR2(30)		NOT NULL,
	"EMPLOYEE_ID"	VARCHAR2(30)		NOT NULL,
	"EMPLOYEE_PWD"	VARCHAR2(255)		NOT NULL,
	"EMPLOYEE_BIRTH"	DATE		NOT NULL,
	"EMPLOYEE_GENDER"	CHAR(1)	DEFAULT 'F'	NOT NULL,
	"EMPLOYEE_PHONE"	VARCHAR2(30)		NOT NULL,
	"EMPLOYEE_HIRE_DATE"	DATE		NULL,
	"EMPLOYEE_RETIRE_DATE"	DATE		NULL,
	"EMPLOYEE_SUM_COUNT"	NUMBER	DEFAULT 0	NOT NULL,
	"EMPLOYEE_SUM_TIME"	NUMBER		NULL,
	"EMPLOYEE_LAST_LOGIN_DATE"	DATE		NULL,
	"EMPLOYEE_BLACKLIST_YN"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"EMPLOYEE_RETIRE_YN"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"REQUEST_DATE"	DATE		NULL,
	"EMPLOYEE_FIRST_CONFIRM_YN"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"EMPLOYEE_SECOND_CONFIRM_YN"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"EMPLOYEE_LAST_CONFIRM_YN"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"EMPLOYEE_LAST_CONFIRM_DATE"	DATE	NULL,
	"EMPLOYEE_REGIST_RETURN_YN"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"EMPLOYEE_EMAIL"	NVARCHAR2(255)	NOT NULL,
	"EMPLOYEE_ADDRESS"	NVARCHAR2(255)	NOT NULL,
	"EMPLOYEE_PICTURE_SAVE_NAME"	VARCHAR2(255)		NULL,
	"EMPLOYEE_PICTURE_SAVE_ROOT"	VARCHAR2(500)		NULL,
	"EMPLOYEE_PICTURE_THUMBNAIL"	VARCHAR2(500)		NULL,
   CONSTRAINT UK_EMPLOYEE_ID UNIQUE(EMPLOYEE_ID),
   CONSTRAINT UK_EMPLOYEE_PHONE UNIQUE(EMPLOYEE_PHONE)
);

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_NO" IS '직원번호';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_NAME" IS '이름';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_ID" IS 'ID';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_PWD" IS 'PWD';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_BIRTH" IS '생년월일';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_GENDER" IS '성별';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_PHONE" IS '전화번호';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_HIRE_DATE" IS '입사일';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_RETIRE_DATE" IS '퇴사일';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_SUM_COUNT" IS '누적경고횟수';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_SUM_TIME" IS '누적근무시간';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_LAST_LOGIN_DATE" IS '마지막로그인일자';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_BLACKLIST_YN" IS '블랙리스트여부';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_RETIRE_YN" IS '퇴사여부';

COMMENT ON COLUMN "TBL_EMPLOYEE"."REQUEST_DATE" IS '직원등록요청일';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_FIRST_CONFIRM_YN" IS '1차승인여부';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_SECOND_CONFIRM_YN" IS '2차승인여부';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_LAST_CONFIRM_YN" IS '최종승인여부';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_LAST_CONFIRM_DATE" IS '최종승인일';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_REGIST_RETURN_YN" IS '등록반려여부';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_EMAIL" IS '이메일';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_ADDRESS" IS '주소';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_PICTURE_SAVE_NAME" IS '사진 저장명';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_PICTURE_SAVE_ROOT" IS '저장경로';

COMMENT ON COLUMN "TBL_EMPLOYEE"."EMPLOYEE_PICTURE_THUMBNAIL" IS '썸네일 파일';






CREATE TABLE "TBL_AUTHORITY" (
  "AUTHORITY_CODE" NUMBER NOT NULL,
  "AUTHORITY_NAME" NVARCHAR2(30) NOT NULL,
  "AUTHORITY_DESC" NVARCHAR2(255) NOT NULL
 );

COMMENT ON COLUMN "TBL_AUTHORITY"."AUTHORITY_CODE" IS '권한코드';
COMMENT ON COLUMN "TBL_AUTHORITY"."AUTHORITY_NAME" IS '권한 명';
COMMENT ON COLUMN "TBL_AUTHORITY"."AUTHORITY_DESC" IS '권한 설명';

 CREATE TABLE "TBL_ADMIN_MEMBER_ROLE" (
  "AUTHORITY_CODE" NUMBER NOT NULL,
  "EMPLOYEE_NO" NUMBER NULL,
  "ADMIN_NO" NUMBER NULL
 );

COMMENT ON COLUMN "TBL_ADMIN_MEMBER_ROLE"."AUTHORITY_CODE" IS '권한코드';
COMMENT ON COLUMN "TBL_ADMIN_MEMBER_ROLE"."EMPLOYEE_NO" IS '직원번호';
COMMENT ON COLUMN "TBL_ADMIN_MEMBER_ROLE"."ADMIN_NO" IS '관리자번호';




CREATE TABLE "TBL_RESERVATION_INFO" (
 "RESERVATION_NO" NUMBER NOT NULL,
 "USER_NAME" NVARCHAR2(30) NOT NULL,
 "USER_PHONE_NO" VARCHAR2(30) NOT NULL,
 "USER_POSTAL_NO" NUMBER NOT NULL,
 "USER_ADDRESS" NVARCHAR2(255) NOT NULL,
 "USER_DETAIL_ADDRESS" NVARCHAR2(255) NOT NULL,
 "USER_HOUSE_SIZE" NUMBER NOT NULL,
 "USER_RESERVATION_DATE" DATE DEFAULT SYSDATE NOT NULL,
 "USER_REQUIREMENTS" NVARCHAR2(255) NOT NULL,
 "BUSINESS_DATE" DATE NOT NULL,
 "BUSINESS_START_TIME" TIMESTAMP NOT NULL,
 "BUSINESS_END_TIME" TIMESTAMP NOT NULL,
 "BUSINESS_FIXED_PEOPLE" NUMBER NOT NULL,
 "BUSINESS_APPLY_PEOPLE" NUMBER DEFAULT 0 NOT NULL,
 "TOTAL_PAYMENT" NUMBER NOT NULL,
 "APPLY_END_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "RESERVATION_CANCEL_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "PAYMENT_YN" CHAR(1) DEFAULT 'Y' NOT NULL,
 "GASHOOD_CLEAN_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "MOLD_CLEAN_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "FILTER_CLEAN_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "WAREHOUSE_CLEAN_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "PET_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "MULTIPLE_LAYER_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 
 CONSTRAINT CK_APPLY_END_YN CHECK(APPLY_END_YN IN('Y', 'N')),
 CONSTRAINT CK_RESERVATION_CANCEL_YN CHECK(RESERVATION_CANCEL_YN IN('Y', 'N')),
 CONSTRAINT CK_PAYMENT_YN CHECK(PAYMENT_YN IN('Y', 'N')),
 CONSTRAINT CK_GASHOOD_CLEAN_YN CHECK(GASHOOD_CLEAN_YN IN('Y', 'N')),
 CONSTRAINT CK_MOLD_CLEAN_YN CHECK(MOLD_CLEAN_YN IN('Y', 'N')),
 CONSTRAINT CK_FILTER_CLEAN_YN CHECK(FILTER_CLEAN_YN IN('Y', 'N')),
 CONSTRAINT CK_WAREHOUSE_CLEAN_YN CHECK(WAREHOUSE_CLEAN_YN IN('Y', 'N')),
 CONSTRAINT CK_PET_YN CHECK(PET_YN IN('Y', 'N')),
 CONSTRAINT CK_MULTIPLE_LAYER_YN CHECK(MULTIPLE_LAYER_YN IN('Y', 'N'))
);

COMMENT ON COLUMN "TBL_RESERVATION_INFO"."RESERVATION_NO" IS '예약번호';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."USER_NAME" IS '고객이름';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."USER_PHONE_NO" IS '핸드폰번호';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."USER_POSTAL_NO" IS '우편번호';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."USER_ADDRESS" IS '주소';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."USER_DETAIL_ADDRESS" IS '상세주소';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."USER_HOUSE_SIZE" IS '평수';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."USER_RESERVATION_DATE" IS '예약신청일자';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."USER_REQUIREMENTS" IS '요청사항';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."BUSINESS_DATE" IS '업무진행일자';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."BUSINESS_START_TIME" IS '시작시간';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."BUSINESS_END_TIME" IS '종료시간';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."BUSINESS_FIXED_PEOPLE" IS '정원';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."BUSINESS_APPLY_PEOPLE" IS '지원 인원';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."TOTAL_PAYMENT" IS '총 결제금액';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."APPLY_END_YN" IS '지원 마감 여부';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."RESERVATION_CANCEL_YN" IS '예약 취소 여부';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."PAYMENT_YN" IS '결제여부';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."GASHOOD_CLEAN_YN" IS '가스레인지 후드 청소 여부';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."MOLD_CLEAN_YN" IS '곰팡이 제거 여부';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."FILTER_CLEAN_YN" IS '필터 청소 여부';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."WAREHOUSE_CLEAN_YN" IS '창고 정리 여부';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."PET_YN" IS '반려동물 여부';
COMMENT ON COLUMN "TBL_RESERVATION_INFO"."MULTIPLE_LAYER_YN" IS '복층 여부';

CREATE TABLE "TBL_ADMIN" (
 "ADMIN_NO" NUMBER NOT NULL,
 "ADMIN_NAME" NVARCHAR2(30) NOT NULL,
 "ADMIN_ID" NVARCHAR2(255) NOT NULL,
 "ADMIN_PWD" NVARCHAR2(255) DEFAULT 000000 NOT NULL,
 "ADMIN_BIRTH" DATE NOT NULL,
 "ADMIN_GENDER" CHAR(1) NOT NULL,
 "ADMIN_PHONE" VARCHAR2(30) NOT NULL,
 "ADMIN_HIRE_DATE" DATE NOT NULL,
 "ADMIN_RETIRE_DATE" DATE NULL,
 "ADMIN_RETIRE_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "ADMIN_JOB" NVARCHAR2(255) NOT NULL,
 "ADMIN_LAST_LOGIN_DATE" DATE NULL,
 "ADMIN_SALARY" NUMBER NOT NULL,
 "ADMIN_USE_ANNUAL_VACATION" NUMBER	NULL,
 "ADMIN_EMAIL" NVARCHAR2(255) NOT NULL,
 "ADMIN_ADDRESS" NVARCHAR2(255)	NOT NULL,
 "ADMIN_PICTURE_SAVE_NAME" VARCHAR2(255) NULL,
 "ADMIN_PICTURE_SAVE_ROOT" VARCHAR2(255) NULL,
 "ADMIN_PICTURE_THUMBNAIL_NAME"	VARCHAR2(255) NULL,
  CONSTRAINT UK_ADMIN_ID UNIQUE(ADMIN_ID),
  CONSTRAINT UK_ADMIN_PHONE UNIQUE(ADMIN_PHONE),
  CONSTRAINT CK_ADMIN_RETIRE_YN CHECK(ADMIN_RETIRE_YN IN('Y', 'N')),
  CONSTRAINT CK_ADMIN_GENDER CHECK(ADMIN_GENDER IN('F', 'M')),
  CONSTRAINT CK_ADMIN_JOB CHECK(ADMIN_JOB IN('일반관리자', '인사관리자', '재정담당자', '보안담당자', '총관리자'))
);

COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_NO" IS '관리자 번호';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_NAME" IS '이름';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_ID" IS '아이디';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_PWD" IS '패스워드';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_BIRTH" IS '생년월일';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_GENDER" IS '성별';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_PHONE" IS '전화번호';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_HIRE_DATE" IS '입사일';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_RETIRE_DATE" IS '퇴사일';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_RETIRE_YN" IS '퇴사여부';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_JOB" IS '직급명';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_LAST_LOGIN_DATE" IS '마지막 로그인 시간';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_SALARY" IS '월급';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_USE_ANNUAL_VACATION" IS '연차사용횟수';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_EMAIL" IS '관리자 이메일';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_ADDRESS" IS '관리자 주소';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_PICTURE_SAVE_NAME" IS '관리자 파일 저장명';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_PICTURE_SAVE_ROOT" IS '관리자 썸네일 저장경로';
COMMENT ON COLUMN "TBL_ADMIN"."ADMIN_PICTURE_THUMBNAIL_NAME" IS '썸네일 파일 저장경로';

CREATE TABLE "TBL_RETIRE_EMPLOYEE" (
 "RETIRE_EMPLOYEE_NO" NUMBER NOT NULL,
 "EMPLOYEE_NAME" NVARCHAR2(30) NOT NULL,
 "RETIRE_EMPLOYEE_ID" VARCHAR2(30) NOT NULL,
 "RETIRE_EMPLOYEE_PWD" VARCHAR2(255) NOT NULL,
 "RETIRE_EMPLOYEE_BIRTH" DATE NOT NULL,
 "RETIRE_EMPLOYEE_GENDER" CHAR(1)NOT NULL,
 "RETIRE_EMPLOYEE_PHONE" VARCHAR2(30) NOT NULL,
 "RETIRE_EMPLOYEE_HIRE_DATE" DATE NOT NULL,
 "RETIRE_EMPLOYEE_RETIRE_DATE" DATE NULL,
 "RETIRE_EMPLOYEE_SUM_COUNT" NUMBER NOT NULL,
 "RETIRE_EMPLOYEE_SUM_TIME" NUMBER NOT NULL,
 "RETIRE_EMPLOYEE_EMAIL" NVARCHAR2(255) NOT NULL,
 "RETIRE_EMPLOYEE_RETIRE_YN" CHAR(1) DEFAULT 'Y' NOT NULL,
 "EMPLOYEE_LAST_LOGIN_DATE" DATE NULL,
  CONSTRAINT UK_RETIRE_EMPLOYEE_ID UNIQUE(RETIRE_EMPLOYEE_ID),
  CONSTRAINT UK_RETIRE_EMPLOYEE_PHONE UNIQUE(RETIRE_EMPLOYEE_PHONE),
  CONSTRAINT CK_RETIRE_EMPLOYEE_RETIRE_YN CHECK(RETIRE_EMPLOYEE_RETIRE_YN IN('Y', 'N')),
  CONSTRAINT CK_RETIRE_EMPLOYEE_GENDER CHECK(RETIRE_EMPLOYEE_GENDER IN('F', 'M'))
);

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."RETIRE_EMPLOYEE_NO" IS '탈퇴직원번호';

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."EMPLOYEE_NAME" IS '이름';

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."RETIRE_EMPLOYEE_ID" IS 'ID';

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."RETIRE_EMPLOYEE_PWD" IS 'PWD';

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."RETIRE_EMPLOYEE_BIRTH" IS '생년월일';

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."RETIRE_EMPLOYEE_PHONE" IS '전화번호';

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."RETIRE_EMPLOYEE_HIRE_DATE" IS '입사일';

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."RETIRE_EMPLOYEE_RETIRE_DATE" IS '퇴사일';

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."RETIRE_EMPLOYEE_SUM_COUNT" IS '누적경고횟수';

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."RETIRE_EMPLOYEE_SUM_TIME" IS '누적근무시간';

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."RETIRE_EMPLOYEE_EMAIL" IS '이메일';

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."RETIRE_EMPLOYEE_RETIRE_YN" IS '퇴사여부';

COMMENT ON COLUMN "TBL_RETIRE_EMPLOYEE"."EMPLOYEE_LAST_LOGIN_DATE" IS '마지막 로그인 일자';

CREATE TABLE "TBL_RETIRE_ADMIN" (
 "RETIRE_ADMIN_NO" NUMBER NOT NULL,
 "RETIRE_ADMIN_NAME" NVARCHAR2(30) NULL,
 "RETIRE_ADMIN_ID" VARCHAR2(30) NOT NULL,
 "RETIRE_ADMIN_PWD" VARCHAR2(255) NOT NULL,
 "RETIRE_ADMIN_BIRTH" DATE NOT NULL,
 "RETIRE_ADMIN_GENDER" CHAR(1) NOT NULL,
 "RETIRE_ADMIN_PHONE" VARCHAR2(30) NOT NULL,
 "RETIRE_ADMIN_HIRE_DATE" DATE NOT NULL,
 "RETIRE_ADMIN_RETIRE_DATE" DATE NULL,
 "RETIRE_ADMIN_RETIRE_YN" CHAR(1) DEFAULT 'Y' NOT NULL,
 "RETIRE_ADMIN_JOB" NVARCHAR2(255) NOT NULL,
 "RETIRE_ADMIN_LAST_LOGIN_DATE" DATE NULL,
 "RETIRE_ADMIN_SALARY" NUMBER NOT NULL,
 "RETIRE_ANNUAL_VACATION_USE" NUMBER NULL,
  CONSTRAINT UK_RETIRE_ADMIN_ID UNIQUE(RETIRE_ADMIN_ID),
  CONSTRAINT UK_RETIRE_ADMIN_PHONE UNIQUE(RETIRE_ADMIN_PHONE),
  CONSTRAINT CK_RETIRE_ADMIN_RETIRE_YN CHECK(RETIRE_ADMIN_RETIRE_YN IN('Y', 'N')),
  CONSTRAINT CK_RETIRE_ADMIN_GENDER CHECK(RETIRE_ADMIN_GENDER IN('F', 'M')),
  CONSTRAINT CK_RETIRE_ADMIN_JOB CHECK(RETIRE_ADMIN_JOB IN('일반관리자', '인사관리자', '재정담당자', '보안담당자', '총관리자'))
);

COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_NO" IS '관리자 번호';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_NAME" IS '이름';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_PWD" IS '아이디';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_PWD" IS '패스워드';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_BIRTH" IS '생년월일';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_GENDER" IS '성별';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_PHONE" IS '전화번호';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_HIRE_DATE" IS '입사일';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_RETIRE_DATE" IS '퇴사일';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_RETIRE_YN" IS '퇴사여부';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_JOB" IS '직급명';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_LAST_LOGIN_DATE" IS '마지막로그인일자';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ADMIN_SALARY" IS '월급';
COMMENT ON COLUMN "TBL_RETIRE_ADMIN"."RETIRE_ANNUAL_VACATION_USE" IS '사용연차횟수';

CREATE TABLE "TBL_ADMIN_PAY" (
 "PAY_HISTORY_ADMIN_NO" NUMBER NOT NULL,
 "PAY_ADMIN_NO" NUMBER NOT NULL,
 "PAY_ADMIN_DATE" DATE NOT NULL,
 "PAY_ADMIN_FINAL_SALARY" NUMBER NOT NULL
);

COMMENT ON COLUMN "TBL_ADMIN_PAY"."PAY_HISTORY_ADMIN_NO" IS '급여 내역 번호';
COMMENT ON COLUMN "TBL_ADMIN_PAY"."PAY_ADMIN_NO" IS '관리자 번호';
COMMENT ON COLUMN "TBL_ADMIN_PAY"."PAY_ADMIN_DATE" IS '지급날짜';
COMMENT ON COLUMN "TBL_ADMIN_PAY"."PAY_ADMIN_FINAL_SALARY" IS '최종 급여';

CREATE TABLE TBL_APPLY_EMPLOYEE (
  APPLY_EMPLOYEE_NO NUMBER CONSTRAINT NN_APPLY_EMPLOYEE_NO NOT NULL
, APPLY_RESERVATION_NO NUMBER CONSTRAINT NN_APPLY_RESERVATION_NO NOT NULL
, APPLY_CANCEL_YN CHAR(1) DEFAULT 'N' NOT NULL
, CHECK_EMPLOYEE_YN CHAR(1) DEFAULT 'N' NOT NULL
, CONSTRAINT CK_APPLY_CANCEL_YN CHECK (APPLY_CANCEL_YN IN ('Y', 'N'))
, CONSTRAINT CK_CHECK_EMPLOYEE_YN CHECK (CHECK_EMPLOYEE_YN IN ('Y', 'N'))
);

COMMENT ON COLUMN TBL_APPLY_EMPLOYEE.APPLY_EMPLOYEE_NO IS '직원번호';
COMMENT ON COLUMN TBL_APPLY_EMPLOYEE.APPLY_RESERVATION_NO IS '예약번호';
COMMENT ON COLUMN TBL_APPLY_EMPLOYEE.APPLY_CANCEL_YN IS '취소여부';
COMMENT ON COLUMN TBL_APPLY_EMPLOYEE.CHECK_EMPLOYEE_YN IS '체크리스트 작성 직원 여부';

CREATE TABLE TBL_CHECKLIST (
  CHECK_RESERVATION_NO NUMBER CONSTRAINT NN_CHECK_RESERVATION_NO NOT NULL
, CHECK_HTML CLOB 
, CHECK_STATUS CHAR(1) DEFAULT 'N' CONSTRAINT NN_CHECK_STATUS NOT NULL
, CHECK_ADMIN_NO NUMBER 
, CONSTRAINT CK_CHECK_STATUS CHECK (CHECK_STATUS IN ('N', 'R', 'A', 'D', 'B'))
);


COMMENT ON COLUMN TBL_CHECKLIST.CHECK_RESERVATION_NO IS '예약번호';
COMMENT ON COLUMN TBL_CHECKLIST.CHECK_HTML IS 'HTML 값';
COMMENT ON COLUMN TBL_CHECKLIST.CHECK_STATUS IS '처리 상태';
COMMENT ON COLUMN TBL_CHECKLIST.CHECK_ADMIN_NO IS '관리자 번호';

CREATE TABLE "TBL_BOOKMARK" (
 "BOOKMARK_NO" NUMBER NOT NULL,
 "BOOKMARK_CANCEL_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "BOOKMARK_EMPLOYEE_NO" NUMBER NOT NULL,
 "BOOKMARK_RESERVATION_NO" NUMBER NOT NULL,
  CONSTRAINT CK_BOOKMARK_CANCEL_YN CHECK(BOOKMARK_CANCEL_YN IN('Y', 'N'))
);

COMMENT ON COLUMN "TBL_BOOKMARK"."BOOKMARK_NO" IS '즐겨찾기 번호';
COMMENT ON COLUMN "TBL_BOOKMARK"."BOOKMARK_CANCEL_YN" IS '즐찾 취소 여부';
COMMENT ON COLUMN "TBL_BOOKMARK"."BOOKMARK_EMPLOYEE_NO" IS '직원번호';
COMMENT ON COLUMN "TBL_BOOKMARK"."BOOKMARK_RESERVATION_NO" IS '예약번호';

CREATE TABLE "TBL_SURCHARGE" (
 "SURCHARGE_INSURANCE" NUMBER NOT NULL,
 "SURCHARGE_COMMISSION" NUMBER NOT NULL,
 "SURCHARGE_BONUS" NUMBER NOT NULL
);

COMMENT ON COLUMN "TBL_SURCHARGE"."SURCHARGE_INSURANCE" IS '4대보험';
COMMENT ON COLUMN "TBL_SURCHARGE"."SURCHARGE_COMMISSION" IS '수수료';
COMMENT ON COLUMN "TBL_SURCHARGE"."SURCHARGE_BONUS" IS '이달의 우수직원 보너스';


CREATE TABLE "TBL_VACATION" (
 "VACATION_NO" NUMBER NOT NULL,
 "REQUEST_DATE" DATE NOT NULL,
 "REQUEST_ADMIN" NVARCHAR2(30) NOT NULL,
 "DRAFTER" NVARCHAR2(30) NULL,
 "VACATION_NAME"	NVARCHAR2(30)	NOT NULL,
 "VACATION_FIRST_CONFIRM_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "VACATION_SECOND_CONFIRM_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "VACATION_LAST_CONFIRM_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "VACATION_START_DATE" DATE NOT NULL,
 "VACATION_END_DATE" DATE NOT NULL,
 "VACATION_REASON" NVARCHAR2(255) NOT NULL,
 "VACATION_RETURN_YN"	CHAR(1) DEFAULT 'N' NOT NULL,
 "ADMIN_NO" NUMBER NOT NULL,
  CONSTRAINT CK_VACATION_FIRST_CONFIRM_YN CHECK(VACATION_FIRST_CONFIRM_YN IN('Y', 'N')),
  CONSTRAINT CK_VACATION_SECOND_CONFIRM_YN CHECK(VACATION_SECOND_CONFIRM_YN IN('Y', 'N')),
  CONSTRAINT CK_VACATION_LAST_CONFIRM_YN CHECK(VACATION_LAST_CONFIRM_YN IN('Y', 'N')),
  CONSTRAINT CK_VACATION_RETURN_YN CHECK(VACATION_RETURN_YN IN('Y', 'N')),
  CONSTRAINT CK_VACATION_NAME CHECK(VACATION_NAME IN('연차', '공가', '경조사', '기타'))
);

COMMENT ON COLUMN "TBL_VACATION"."VACATION_NO" IS '휴가번호';
COMMENT ON COLUMN "TBL_VACATION"."REQUEST_DATE" IS '요청일';
COMMENT ON COLUMN "TBL_VACATION"."DRAFTER" IS '기안자';
COMMENT ON COLUMN "TBL_VACATION"."VACATION_NAME" IS '휴가명';
COMMENT ON COLUMN "TBL_VACATION"."VACATION_FIRST_CONFIRM_YN" IS '1차승인여부';
COMMENT ON COLUMN "TBL_VACATION"."VACATION_SECOND_CONFIRM_YN" IS '2차승인여부';
COMMENT ON COLUMN "TBL_VACATION"."VACATION_START_DATE" IS '휴가시작일';
COMMENT ON COLUMN "TBL_VACATION"."VACATION_END_DATE" IS '휴가종료일';
COMMENT ON COLUMN "TBL_VACATION"."VACATION_REASON" IS '휴가사유';
COMMENT ON COLUMN "TBL_VACATION"."VACATION_RETURN_YN" IS '휴가반려여부';
COMMENT ON COLUMN "TBL_VACATION"."ADMIN_NO" IS '관리자번호';

CREATE TABLE "TBL_VACATION_COMMIT" (
	"ADMIN_NO"	NUMBER	NOT NULL,	
	"VACATION_NO"	NUMBER	NOT NULL,
	"CONFIRM_REASON" NVARCHAR2(255)	NULL,
	"CONFIRM_DATE"	DATE DEFAULT SYSDATE NOT NULL
);


COMMENT ON COLUMN "TBL_VACATION_COMMIT"."ADMIN_NO" IS '관리자번호';
COMMENT ON COLUMN "TBL_VACATION_COMMIT"."VACATION_NO" IS '휴가번호';
COMMENT ON COLUMN "TBL_VACATION_COMMIT"."CONFIRM_REASON" IS '반려사유';
COMMENT ON COLUMN "TBL_VACATION_COMMIT"."CONFIRM_DATE" IS '결재일';


CREATE TABLE "TBL_REASON" (
	"ADMIN_NO"	NUMBER	NOT NULL,
	"EMPLOYEE_NO"	NUMBER	NOT NULL,
	"REASON"	NVARCHAR2(255)	NULL,
	"REGIST_REASON_DATE"	DATE	DEFAULT SYSDATE	NOT NULL
);


COMMENT ON COLUMN "TBL_REASON"."ADMIN_NO" IS '관리자번호';
COMMENT ON COLUMN "TBL_REASON"."EMPLOYEE_NO" IS '직원번호';
COMMENT ON COLUMN "TBL_REASON"."REASON" IS '등록반려사유';
COMMENT ON COLUMN "TBL_REASON"."REGIST_REASON_DATE" IS '등록날짜';


CREATE TABLE "TBL_EMPLOYEE_PAY" (
 "PAY_HISTORY_EMPLOYEE_NO" NUMBER NOT NULL,
 "PAY_EMPLOYEE_DATE" DATE NOT NULL,
 "APPLY_RESERVATION_NO" NUMBER NOT NULL,
 "APPLY_EMPLOYEE_NO" NUMBER NOT NULL,
 "PAY_EMPLOYEE_FINAL_SALARY" NUMBER NOT NULL

);

COMMENT ON COLUMN "TBL_EMPLOYEE_PAY"."PAY_HISTORY_EMPLOYEE_NO" IS '급여 내역 번호';
COMMENT ON COLUMN "TBL_EMPLOYEE_PAY"."PAY_EMPLOYEE_DATE" IS '지급날짜';
COMMENT ON COLUMN "TBL_EMPLOYEE_PAY"."APPLY_RESERVATION_NO" IS '예약번호';
COMMENT ON COLUMN "TBL_EMPLOYEE_PAY"."APPLY_EMPLOYEE_NO" IS '직원번호';
COMMENT ON COLUMN "TBL_EMPLOYEE_PAY"."PAY_EMPLOYEE_FINAL_SALARY" IS '최종 급여';


CREATE TABLE "TBL_NOTIFICATION" (
 "NOTIFICATION_NO" NUMBER NOT NULL,
 "NOTIFIVATION_TEXT" NVARCHAR2(255) NOT NULL,
 "NOTIFIVATION_CREATE_TIME" DATE DEFAULT SYSDATE NOT NULL,
 "NOTIFIVATION_READ_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "NOTIFIVATION_ADMIN_YN" CHAR(1) DEFAULT 'N' NOT NULL,
 "NOTIFIVATION_EMPLOYEE_NO" NUMBER,
 "NOTIFIVATION_RESERVATION_NO" NUMBER NOT NULL,
 "NOTIFIVATION_ADMIN_NO" NUMBER NOT NULL,
 CONSTRAINT CK_NOTIFIVATION_READ_YN CHECK (NOTIFIVATION_READ_YN IN ('Y', 'N'))
);

COMMENT ON COLUMN "TBL_NOTIFICATION"."NOTIFICATION_NO" IS '알림번호';
COMMENT ON COLUMN "TBL_NOTIFICATION"."NOTIFIVATION_TEXT" IS '알림내용';
COMMENT ON COLUMN "TBL_NOTIFICATION"."NOTIFIVATION_CREATE_TIME" IS '생성시간';
COMMENT ON COLUMN "TBL_NOTIFICATION"."NOTIFIVATION_READ_YN" IS '읽음 여부';
COMMENT ON COLUMN "TBL_NOTIFICATION"."NOTIFIVATION_ADMIN_YN" IS '관리자 여부';
COMMENT ON COLUMN "TBL_NOTIFICATION"."NOTIFIVATION_EMPLOYEE_NO" IS '직원번호';
COMMENT ON COLUMN "TBL_NOTIFICATION"."NOTIFIVATION_RESERVATION_NO" IS '예약번호';
COMMENT ON COLUMN "TBL_NOTIFICATION"."NOTIFIVATION_ADMIN_NO" IS '관리자번호';

CREATE TABLE TBL_ADMIN_IP_ADRESS (
  ADMIN_NO NUMBER CONSTRAINT NN_ADMIN_NO NOT NULL
, ADMIN_IP_ADRESS_VALUE NVARCHAR2(255) 
);

COMMENT ON COLUMN TBL_ADMIN_IP_ADRESS.ADMIN_NO IS '관리자 번호';
COMMENT ON COLUMN TBL_ADMIN_IP_ADRESS.ADMIN_IP_ADRESS_VALUE IS '관리자 IP주소 값';

ALTER TABLE "TBL_EMPLOYEE" ADD CONSTRAINT "PK_TBL_EMPLOYEE" PRIMARY KEY (
 "EMPLOYEE_NO"
);



ALTER TABLE "TBL_RESERVATION_INFO" ADD CONSTRAINT "PK_TBL_RESERVATION_INFO" PRIMARY KEY (
 "RESERVATION_NO"
);

ALTER TABLE "TBL_ADMIN" ADD CONSTRAINT "PK_TBL_ADMIN" PRIMARY KEY (
 "ADMIN_NO"
);

ALTER TABLE "TBL_RETIRE_ADMIN" ADD CONSTRAINT "PK_TBL_RETIRE_ADMIN" PRIMARY KEY (
 "RETIRE_ADMIN_NO"
);

ALTER TABLE "TBL_RETIRE_EMPLOYEE" ADD CONSTRAINT "PK_TBL_RETIRE_EMPLOYEE" PRIMARY KEY (
 "RETIRE_EMPLOYEE_NO"
);

ALTER TABLE "TBL_ADMIN_PAY" ADD CONSTRAINT "PK_TBL_ADMIN_PAY" PRIMARY KEY (
 "PAY_HISTORY_ADMIN_NO"
);

ALTER TABLE "TBL_VACATION_COMMIT" ADD CONSTRAINT "PK_TBL_VACATION_COMMIT" PRIMARY KEY (
	"ADMIN_NO",
	"VACATION_NO"
);


ALTER TABLE TBL_APPLY_EMPLOYEE ADD CONSTRAINT PK_TBL_APPLY_EMPLOYEE PRIMARY KEY (
  APPLY_EMPLOYEE_NO
, APPLY_RESERVATION_NO
);

ALTER TABLE TBL_CHECKLIST ADD CONSTRAINT PK_TBL_CHECKLIST PRIMARY KEY (
 CHECK_RESERVATION_NO
);



ALTER TABLE "TBL_BOOKMARK" ADD CONSTRAINT "PK_TBL_BOOKMARK" PRIMARY KEY (
 "BOOKMARK_NO"
);

ALTER TABLE "TBL_VACATION" ADD CONSTRAINT "PK_TBL_VACATION" PRIMARY KEY (
 "VACATION_NO"
);


ALTER TABLE "TBL_NOTIFICATION" ADD CONSTRAINT "PK_TBL_NOTIFICATION" PRIMARY KEY (
 "NOTIFICATION_NO"
);

ALTER TABLE "TBL_EMPLOYEE_PAY" ADD CONSTRAINT "PK_TBL_PAY_HISTORY_EMPLOYEE_NO" PRIMARY KEY (
 "PAY_HISTORY_EMPLOYEE_NO"
);


 ALTER TABLE "TBL_AUTHORITY" ADD CONSTRAINT "PK_TBL_AUTHORITY" PRIMARY KEY (
  "AUTHORITY_CODE"
 );


ALTER TABLE "TBL_ADMIN_PAY" ADD CONSTRAINT "FK_TBL_ADMIN_TO_TBL_ADMIN_PAY_1" FOREIGN KEY (
 "PAY_ADMIN_NO"
)
REFERENCES "TBL_ADMIN" (
 "ADMIN_NO"
);



ALTER TABLE TBL_APPLY_EMPLOYEE ADD CONSTRAINT FK_TBL_EMPLOYEE_TO_TBL_APPLY_1 FOREIGN KEY (
 APPLY_EMPLOYEE_NO
)
REFERENCES TBL_EMPLOYEE (
 EMPLOYEE_NO
);

ALTER TABLE TBL_APPLY_EMPLOYEE ADD CONSTRAINT FK_TBL_RESERVATION_INFO_TO_TBL_APPLY_1 FOREIGN KEY (
 APPLY_RESERVATION_NO
)
REFERENCES TBL_RESERVATION_INFO (
 RESERVATION_NO
);

ALTER TABLE TBL_CHECKLIST ADD CONSTRAINT FK_TBL_RESERVATION_INFO_TO_TBL_CHECKLIST_1 FOREIGN KEY (
 CHECK_RESERVATION_NO
)
REFERENCES TBL_RESERVATION_INFO (
 RESERVATION_NO
);

ALTER TABLE TBL_CHECKLIST ADD CONSTRAINT FK_TBL_ADMIN_TO_TBL_CHECKLIST_1 FOREIGN KEY (
 CHECK_ADMIN_NO
)
REFERENCES TBL_ADMIN (
 ADMIN_NO
);


ALTER TABLE "TBL_BOOKMARK" ADD CONSTRAINT "FK_TBL_EMPLOYEE_TO_TBL_BOOKMARK_1" FOREIGN KEY (
 "BOOKMARK_EMPLOYEE_NO"
)
REFERENCES "TBL_EMPLOYEE" (
 "EMPLOYEE_NO"
);

ALTER TABLE "TBL_BOOKMARK" ADD CONSTRAINT "FK_TBL_RESERVATION_INFO_TO_TBL_BOOKMARK_1" FOREIGN KEY (
 "BOOKMARK_RESERVATION_NO"
)
REFERENCES "TBL_RESERVATION_INFO" (
 "RESERVATION_NO"
);

ALTER TABLE "TBL_VACATION" ADD CONSTRAINT "FK_TBL_ADMIN_TO_TBL_VACATION_1" FOREIGN KEY (
 "ADMIN_NO"
)
REFERENCES "TBL_ADMIN" (
 "ADMIN_NO"
);

ALTER TABLE "TBL_EMPLOYEE_PAY" ADD CONSTRAINT "FK_TBL_APPLY_EMPLOYEE_TO_TBL_EMPLOYEE_PAY_2" FOREIGN KEY (
	"APPLY_RESERVATION_NO",
  "APPLY_EMPLOYEE_NO"
)
REFERENCES "TBL_APPLY_EMPLOYEE" (
	"APPLY_RESERVATION_NO",
  "APPLY_EMPLOYEE_NO"
);

ALTER TABLE "TBL_NOTIFICATION" ADD CONSTRAINT "FK_TBL_EMPLOYEE_TO_TBL_NOTIFICATION_1" FOREIGN KEY (
 "NOTIFIVATION_EMPLOYEE_NO"
)
REFERENCES "TBL_EMPLOYEE" (
 "EMPLOYEE_NO"
);

ALTER TABLE "TBL_NOTIFICATION" ADD CONSTRAINT "FK_TBL_RESERVATION_INFO_TO_TBL_NOTIFICATION_1" FOREIGN KEY (
 "NOTIFIVATION_RESERVATION_NO"
)

REFERENCES "TBL_RESERVATION_INFO" (
 "RESERVATION_NO"
);

ALTER TABLE "TBL_NOTIFICATION" ADD CONSTRAINT "FK_TBL_ADMIN_TO_TBL_NOTIFICATION_1" FOREIGN KEY (
 "NOTIFIVATION_ADMIN_NO"
)
REFERENCES "TBL_ADMIN" (
 "ADMIN_NO"
);

ALTER TABLE TBL_ADMIN_IP_ADRESS ADD CONSTRAINT FK_TBL_ADMIN_TO_TBL_ADMIN_IP_ADRESS_1 FOREIGN KEY (
 ADMIN_NO
)
REFERENCES TBL_ADMIN (
 ADMIN_NO
);

ALTER TABLE "TBL_REASON" ADD CONSTRAINT "PK_ADMIN_NO_EMPLOYEE_NO" PRIMARY KEY (
	"ADMIN_NO",
	"EMPLOYEE_NO"
);

ALTER TABLE "TBL_REASON" ADD CONSTRAINT "FK_TBL_ADMIN_TO_REASON_1" FOREIGN KEY (
	"ADMIN_NO"
)
REFERENCES "TBL_ADMIN" (
	"ADMIN_NO"
);

ALTER TABLE "TBL_REASON" ADD CONSTRAINT "FK_TBL_EMPLOYEE_TO_REASON_1" FOREIGN KEY (
	"EMPLOYEE_NO"
)
REFERENCES "TBL_EMPLOYEE" (
	"EMPLOYEE_NO"
);

ALTER TABLE "TBL_VACATION_COMMIT" ADD CONSTRAINT "FK_TBL_ADMIN_TO_TBL_VACATION_COMMIT_1" FOREIGN KEY (
		"ADMIN_NO"
	)
	REFERENCES "TBL_ADMIN" (
		"ADMIN_NO"
	);

ALTER TABLE "TBL_VACATION_COMMIT" ADD CONSTRAINT "FK_TBL_TBL_VACATION_TO_TBL_VACATION_COMMIT_1" FOREIGN KEY (
		"VACATION_NO"
	)
	REFERENCES "TBL_VACATION" (
		"VACATION_NO"
	);
    
    ALTER TABLE "TBL_ADMIN_MEMBER_ROLE" ADD CONSTRAINT "FK_TBL_AUTHORITY_TO_TBL_ADMIN_MEMBER_ROLE_1" FOREIGN KEY (
	"AUTHORITY_CODE"
)
REFERENCES "TBL_AUTHORITY" (
	"AUTHORITY_CODE"
);

ALTER TABLE "TBL_ADMIN_MEMBER_ROLE" ADD CONSTRAINT "FK_TBL_EMPLOYEE_TO_TBL_ADMIN_MEMBER_ROLE_1" FOREIGN KEY (
	"EMPLOYEE_NO"
)
REFERENCES "TBL_EMPLOYEE" (
	"EMPLOYEE_NO"
);

ALTER TABLE "TBL_ADMIN_MEMBER_ROLE" ADD CONSTRAINT "FK_TBL_ADMIN_TO_TBL_ADMIN_MEMBER_ROLE_1" FOREIGN KEY (
	"ADMIN_NO"
)
REFERENCES "TBL_ADMIN" (
	"ADMIN_NO"
);

drop trigger TRG_ADMIN_ROLE;
drop trigger TRG_MEMBER_ROLE;

-- EMPLOYEE INSERT TRIGGER
CREATE OR REPLACE TRIGGER TRG_MEMBER_ROLE AFTER
  INSERT ON TBL_EMPLOYEE
  FOR EACH ROW
BEGIN
  IF :NEW.EMPLOYEE_NO = SEQ_EMP_NO.CURRVAL
    THEN 
      INSERT 
        INTO TBL_ADMIN_MEMBER_ROLE 
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO) 
      VALUES (2, SEQ_EMP_NO.CURRVAL, NULL);
      INSERT 
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
       VALUES (3, SEQ_EMP_NO.CURRVAL, NULL);      
  END IF;       
END;
/

-- INSERT ADMIN TRIGGER

CREATE OR REPLACE TRIGGER TRG_ADMIN_ROLE AFTER
  INSERT ON TBL_ADMIN
  FOR EACH ROW
BEGIN
  IF :NEW.ADMIN_JOB = '일반관리자'
    THEN 
      INSERT 
        INTO TBL_ADMIN_MEMBER_ROLE 
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO) 
      VALUES (2, NULL, SEQ_TBL_ADMIN.CURRVAL);
      INSERT 
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
       VALUES (4, NULL, SEQ_TBL_ADMIN.CURRVAL);      
  END IF;        
  IF :NEW.ADMIN_JOB = '인사관리자'
    THEN 
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (2, NULL, SEQ_TBL_ADMIN.CURRVAL);
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (4, NULL, SEQ_TBL_ADMIN.CURRVAL);
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (7, NULL, SEQ_TBL_ADMIN.CURRVAL);       
  END IF;
  IF :NEW.ADMIN_JOB = '재정담당자'
    THEN 
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (2, NULL, SEQ_TBL_ADMIN.CURRVAL);
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (4, NULL, SEQ_TBL_ADMIN.CURRVAL);
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (8, NULL, SEQ_TBL_ADMIN.CURRVAL);       
  END IF;  
  IF :NEW.ADMIN_JOB = '보안담당자'
    THEN 
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (2, NULL, SEQ_TBL_ADMIN.CURRVAL);
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (4, NULL, SEQ_TBL_ADMIN.CURRVAL);
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (6, NULL, SEQ_TBL_ADMIN.CURRVAL);       
  END IF;  
  IF :NEW.ADMIN_JOB = '총관리자'
    THEN 
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (2, NULL, SEQ_TBL_ADMIN.CURRVAL);
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (4, NULL, SEQ_TBL_ADMIN.CURRVAL);
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (5, NULL, SEQ_TBL_ADMIN.CURRVAL);
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (6, NULL, SEQ_TBL_ADMIN.CURRVAL);
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (7, NULL, SEQ_TBL_ADMIN.CURRVAL);
      INSERT
        INTO TBL_ADMIN_MEMBER_ROLE
             (AUTHORITY_CODE, EMPLOYEE_NO, ADMIN_NO)
      VALUES (8, NULL, SEQ_TBL_ADMIN.CURRVAL);       
  END IF;  
END;
/


COMMIT;