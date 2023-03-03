CREATE TABLE EMPLOYEE(
    EMP_ID INT,
    EMPNAME VARCHAR(50),
    GENDER ENUM ('M','F','O'),
    GRADE_LEVEL VARCHAR(2),
    DOJ DATE,
    DESIGNATION VARCHAR(50),
    EMPTYPE ENUM('FULL-TIME','PART-TIME','CONTRACT'),
    EMPSTATUS ENUM('ACIVE','INACTIVE'),
    PROB_PERIOD INT,
    PROB_COMP_DATE DATE,
    TRAIN_PERIOD INT,
    CONTRACT_END_DATE DATE,
    SERV_PERIOD INT,
    WORK_EMAIL VARCHAR(50) UNIQUE,
    BRANCH VARCHAR(50),
    OFFICE VARCHAR(50),
    WORKSTATIONID VARCHAR(5),
    EMP_ROLE VARCHAR(4),
    CL_LEFT INT,
    PL_LEFT INT,
    SL_LEFT INT,
    MORE_LEAVE INT,
    TOTAL_LEAVE INT,
    CTC DECIMAL,
    DEPT_ID REFERENCES DEPARTMENT(DEPT_ID),
    TEAM_ID REFERENCES TEAM(TEAM_ID),
    EMP_DET_ID REFERENCES EMPLOYEEDETAILS(EMP_DET_ID),
);

CREATE TABLE TEAM(
    TEAM_ID INT PRIMARY KEY AUTO_INCREMENT,
    TM VARCHAR(50)
    PROJ_ID REFERENCES PROJECT(PROJ_ID),
)

CREATE TABLE DEPARTMENT(
    DEPT_ID INT PRIMARY KEY AUTO_INCREMENT,
    DEPARTMENT_NAME VARCHAR(30),
    HOD VARCHAR(50)
);

CREATE TABLE PROJECT(
    PROJ_ID INT PRIMARY KEY AUTO_INCREMENT,
    PROJECT_NAME VARCHAR(50),
    START_DATE DATE,
    END_DATE DATE,
    PM VARCHAR(50)
);

CREATE TABLE ATTENDANCE(
    EMP_ATT_ID INT PRIMARY KEY AUTO_INCREMENT,
    LEAVE_DATE DATE,
    IS_APPROVED ENUM('Y','N'),
    APPROVAL_DONE_BY_ID REFERENCES ADMIN(ADMIN_ID),
    LEAVE_TYPE ENUM('CL','PL','SL','OL')
    LEAVE_REASON VARCHAR(255),
    EMP_ID INT REFERENCES EMPLOYEE(EMP_ID)
)

CREATE TABLE PAYROLL(
    PAYROLL_ID INT PRIMARY KEY AUTO_INCREMENT,
    CTC_PREV DECIMAL,
    HIKE DECIMAL,
    CTC_NEW DECIMAL,
    HIKE_DT DATE,
    EMP_ID INT REFERENCES EMPLOYEE(EMP_ID)
)

CREATE TABLE EMPLOYEEDETAILS(
    EMP_DET_ID INT PRIMARY KEY AUTO_INCREMENT ON DELETE CASCADE,
    PROFILE_PIC_DOC_ID INT REFERENCES FILE(FILE_ID),
    PERMA_ADDR VARCHAR(100),
    PRIMARY_CONTACTNO VARCHAR(10),
    EMERGENCY_CONTACTNO VARCHAR(10),
    EMAIL_ID VARCHAR(30),
    DOB DATE,
    PRESENT_ADDR VARCHAR(100),
    NATIONALITY VARCHAR(20),
    BLOOD_GRP VARCHAR(3),
    PANCARD_DOC_ID INT REFERENCES FILE(FILE_ID),
    PANCARDNO VARCHAR(10),
    ADHAAR_DOC_ID INT REFERENCES FILE(FILE_ID),
    ADHAARNO VARCHAR(12),
    PASSPORT_DOC_ID INT REFERENCES FILE(FILE_ID),
    PASSPORTNO VARACHAR(10)
);

CREATE TABLE FAMILYDETAILS(
    FAMILY_ID INT PRIMARY KEY AUTOINCREMENT ON DELETE CASCADE,
    MARITAL_STAT ENUM ('SINGLE','MARRIED'),
    RELATION ENUM('SPOUSE', 'FATHER','MOTHER','BROTHER','SISTER','CHILD'),
    FAM_NAME VARCHAR(30),
    DOB DATE,
    OCCUPATION VARCHAR(30),
    CONTACTNO VARCHAR(10),
    EMP_DET_ID INT REFERENCES EMPLOYEEDETAILS(EMP_DET_ID)
);

CREATE TABLE QUALIFICATION_DETAILS(
    QD_ID INT PRIMARY KEY AUTO_INCREMENT ON DELETE CASCADE,
    QUAL VARCHAR(50),
    QUAL_DOC_ID INT REFERENCES FILE(FILE_ID),
    PERCENT DECIMAL,
    EMP_DET_ID INT REFERENCES EMPLOYEEDETAILS(EMP_DET_ID)
);

CREATE TABLE PROF_DETAILS(
    PROF_DEL_ID INT PRIMARY KEY AUTO_INCREMENT ON DELETE CASCADE,
    NAME_OF_PREV_ORG VARCHAR(50),
    DESIGNATION VARCHAR(30),
    FROM_DATE DATE,
    TO_DATE DATE,
    RELEASE_DOC_ID INT REFERENCES FILE(FILE_ID),
    EMP_ID INT REFERENCES EMPDETAILS(EMP_ID)
)

CREATE TABLE FILE(
    FILE_ID PRIMARY KEY AUTO_INCREMENT,
    FILE_TYPE ENUM('IMG','PDF'),
    FILE_NAME VARCHAR(255),
    FILE_NUM INT
)

CREATE TABLE USER(
    USER_ID INT PRIMARY KEY AUTO_INCREMENT,
    UNAME VARCHAR(30) UNIQUE,
    PASS VARCHAR(50),
    USER_ROLE ENUM('ADMIN','USER'),
    EMP_ID INT REFERENCES EMPLOYEE(EMP_ID)
)

CREATE TABLE ADMIN(
    ADMIN_ID INT,
    ADMIN_NAME VARCHAR(50),
    ADMIN_EMAIL VARCHAR(50)
)