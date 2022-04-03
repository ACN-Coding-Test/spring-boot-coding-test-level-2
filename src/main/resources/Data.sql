CREATE TABLE USER (id varchar,user_name varchar(150) unique,password varchar(50));
CREATE TABLE PROJECT (id varchar,name varchar(150));
CREATE TABLE TASK (id varchar,title varchar(250),description varchar,status varchar,project_id varchar,user_id varchar);
