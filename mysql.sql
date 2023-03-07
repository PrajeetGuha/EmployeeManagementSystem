CREATE TABLE EMPLOYEE(
    EMPID INT PRIMARY KEY AUTOINCREMENT,
    NAME VARCHAR(50),
    GENDER VARCHAR(6) CHECK CONSTRAINT(GENDER IN ('MALE','FEMALE','OTHER')),
    GL CHAR(2),
    DOJ DATE,
    DESIGNATION VARCHAR(50),
    EMPTYPE VARCHAR(30),
    EMPSTATUS VARCHAR(8) CHECK CONSTRAINT(EMPSTATUS IN ('ACTIVE','INACTIVE')),
    PROBPERIOD INT,
    PROBCOMPDATE DATE,
    TRAINPERIOD INT,
    CONTRACTEND DATE,
    SERVPERIOD INT,
    ISGM TINYINT(1),
    ISEXPAT TINYINT(1),
    RELEASEDATE DATE,
    LOCID REFERENCES LOCATION(LOCID),
    DOMID REFERENCES DOMAIN(DOMID),
    WORKSTATIONID VARCHAR(4),
    COMPID REFERENCES COMPENSATION(COMPID),
    GRPID REFERENCES GROUP(GRPID)
);

CREATE TABLE TEAM(
    TEAMID INT PRIMARY KEY AUTOINCREMENT,
    TM VARCHAR(50)
    DEPTID REFERENCES DEPARTMENT(DEPTID),
    PROJID REFERENCES PROJECT(PROJID)
)

CREATE TABLE DEPARTMENT(
    DEPTID INT PRIMARY KEY AUTOINCREMENT,
    DEPARTMENT_NAME VARCHAR(30),
    HOD VARCHAR(50)
)


CREATE TABLE DOMAIN(
    DOMID INT PRIMARY KEY AUTOINCREMENT,
    USERID VARCHAR(10),
    EMAIL VARCHAR(50)
)

CREATE TABLE LOCATION(
    LOCID INT PRIMARY KEY AUTOINCREMENT,
    BRANCH VARCHAR(50),
    OFFICE VARCHAR(50)
);

CREATE TABLE COMPENSATION(
    COMPID INT PRIMARY KEY AUTOINCREMENT,
    CTC DECIMAL
);

CREATE TABLE PROJECT(
    PROJID INT PRIMARY KEY AUTOINCREMENT,
    PROJECT_NAME VARCHAR(50),
    PM VARCHAR(50)
);

drop table employee;
drop table team;
drop table department;
drop table domain;
drop table location;
drop table compensation;
drop table project;

desc employee;
desc team;
desc department;
desc domain;
desc location;
desc compensation;
desc project;

drop database ems;
create database ems;
use ems;

insert into user(username,password,role) values('user1','$2a$12$7sYraBm3w1BCxb8Fs../Z.icQVly84Ejr3auoFMDzjLDhptAMIgr2','ROLE_EMP');
insert into user(username,password,role) values('user2','$2a$12$7sYraBm3w1BCxb8Fs../Z.icQVly84Ejr3auoFMDzjLDhptAMIgr2','ROLE_EMP');
insert into user(username,password,role) values('user3','$2a$12$7sYraBm3w1BCxb8Fs../Z.icQVly84Ejr3auoFMDzjLDhptAMIgr2','ROLE_EMP');
insert into user(username,password,role) values('admin','$2a$12$StespNsylzyMn3ac1CKzrOBF5gAfoohX70SIXPALD/Ts0owJ9A0W2','ROLE_ADMIN');

insert into employee(emp_id, emp_name, designation, empstatus) values(1, "Jyoti Shankar", "ASE", "ACTIVE");
insert into employee(emp_id, emp_name, designation, empstatus) values(2, "Kaveri Devi", "DBA", "ACTIVE");
insert into employee(emp_id, emp_name, designation, empstatus) values(3, "Dcosta Aloysus", "HR", "INACTIVE");
insert into admin(admin_id, admin_name, admin_email) values(4,"Pallab Patel","pallabpatel@nrifintech.com");