insert into user(username,password,role,is_enabled) values('user1','$2a$12$7sYraBm3w1BCxb8Fs../Z.icQVly84Ejr3auoFMDzjLDhptAMIgr2','ROLE_EMP',1);
insert into user(username,password,role,is_enabled) values('user2','$2a$12$7sYraBm3w1BCxb8Fs../Z.icQVly84Ejr3auoFMDzjLDhptAMIgr2','ROLE_EMP',1);
insert into user(username,password,role,is_enabled) values('user3','$2a$12$7sYraBm3w1BCxb8Fs../Z.icQVly84Ejr3auoFMDzjLDhptAMIgr2','ROLE_EMP',0);
insert into user(username,password,role,is_enabled) values('user4','$2a$12$7sYraBm3w1BCxb8Fs../Z.icQVly84Ejr3auoFMDzjLDhptAMIgr2','ROLE_EMP',0);
insert into user(username,password,role,is_enabled) values('user5','$2a$12$7sYraBm3w1BCxb8Fs../Z.icQVly84Ejr3auoFMDzjLDhptAMIgr2','ROLE_EMP',1);
insert into user(username,password,role,is_enabled) values('user6','$2a$12$7sYraBm3w1BCxb8Fs../Z.icQVly84Ejr3auoFMDzjLDhptAMIgr2','ROLE_EMP',1);
insert into user(username,password,role,is_enabled) values('user7','$2a$12$7sYraBm3w1BCxb8Fs../Z.icQVly84Ejr3auoFMDzjLDhptAMIgr2','ROLE_EMP',1);
insert into user(username,password,role,is_enabled) values('user8','$2a$12$7sYraBm3w1BCxb8Fs../Z.icQVly84Ejr3auoFMDzjLDhptAMIgr2','ROLE_EMP',1);
insert into user(username,password,role,is_enabled) values('user9','$2a$12$7sYraBm3w1BCxb8Fs../Z.icQVly84Ejr3auoFMDzjLDhptAMIgr2','ROLE_EMP',0);



insert into employee(emp_id, emp_name, work_email, designation, empstatus,department_dept_id,gender) values(1, "Jyoti Shankar","jyotis@nrifintech.com", "ASE", "ACTIVE",1,"male");
insert into employee(emp_id, emp_name, work_email, designation, empstatus,department_dept_id,gender) values(2, "Kaveri Devi","kaverid@nrifintech.com", "DBA", "ACTIVE",1,"female");
insert into employee(emp_id, emp_name, work_email, designation, empstatus,department_dept_id,gender) values(3, "Dcosta Aloysus","dcostaa@nrifintech.com", "HR", "INACTIVE",3,"male");
insert into employee(emp_id, emp_name, work_email, designation, empstatus,department_dept_id,gender) values(4, "Jayant Srivastava","jayants@nrifintech.com", "BA", "INACTIVE",2,"male");
insert into employee(emp_id, emp_name, work_email, designation, empstatus,department_dept_id,gender) values(5, "Zingchou Chou","zingchouc@nrifintech.com", "DBA", "ACTIVE",2,"female");
insert into employee(emp_id, emp_name, work_email, designation, empstatus,department_dept_id,gender) values(6, "Paramita Mandal","paramitam@nrifintech.com", "HR", "ACTIVE",2,"female");
insert into employee(emp_id, emp_name, work_email, designation, empstatus,department_dept_id,gender) values(7, "Shekhar Murthy","shekharm@nrifintech.com", "ASE", "ACTIVE",3,"male");
insert into employee(emp_id, emp_name, work_email, designation, empstatus,department_dept_id,gender) values(8, "Kingshuk Goyal","kingshukg@nrifintech.com", "ASE", "ACTIVE",4,"male");
insert into employee(emp_id, emp_name, work_email, designation, empstatus,department_dept_id,gender) values(9, "Dures Mukherjee","duresm@nrifintech.com", "HR", "INACTIVE",4,"male");
insert into employee(emp_id, emp_name, work_email, designation, empstatus,department_dept_id,gender) values(10, "Rajesh Kumar","rajeshk@nrifintech.com", "DBA", "ACTIVE",5,"male");
insert into employee(emp_id, emp_name, work_email, designation, empstatus,department_dept_id,gender) values(11, "Amish Deb","amishd@nrifintech.com", "ASE", "ACTIVE",5,"male");
insert into employee(emp_id, emp_name, work_email, designation, empstatus,department_dept_id,gender) values(12, "Ritik Jha","ritikj@nrifintech.com", "HR", "INACTIVE",2,"male");

insert into user(username,password,role,is_enabled, table_pk) values('admin','$2a$12$StespNsylzyMn3ac1CKzrOBF5gAfoohX70SIXPALD/Ts0owJ9A0W2','ROLE_ADMIN',1,1);
insert into admin(admin_id, admin_name, admin_email) values(1,"Pallab Patel","pallabpatel@admin.nrifintech.com");

insert into department(department_name) values('unassigned');