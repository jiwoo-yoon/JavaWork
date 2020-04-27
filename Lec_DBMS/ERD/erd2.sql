
/* Drop Tables */

DROP TABLE NEW_TABLE CASCADE CONSTRAINTS;
DROP TABLE class CASCADE CONSTRAINTS;
DROP TABLE professor CASCADE CONSTRAINTS;
DROP TABLE student CASCADE CONSTRAINTS;
DROP TABLE department CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE class
(
	classnum number NOT NULL,
	cname varchar2(10),
	cscore number,
	cpersonnel number,
	croom varchar2(10),
	cdate number,
	profno number NOT NULL,
	PRIMARY KEY (classnum)
);


CREATE TABLE department
(
	deptno number NOT NULL,
	deptpn number,
	deptoffice varchar2(10),
	deptname varchar2(20),
	PRIMARY KEY (deptno)
);


CREATE TABLE NEW_TABLE
(
	classnum number NOT NULL,
	studno number NOT NULL
);


CREATE TABLE professor
(
	profno number NOT NULL,
	profrs number,
	profaddr varchar2(20),
	profname varchar2(10) NOT NULL,
	position varchar2(10),
	profpn number,
	hiredate number,
	deptno number NOT NULL,
	PRIMARY KEY (profno)
);


CREATE TABLE student
(
	studno number NOT NULL,
	name varchar2(10) NOT NULL,
	grade number,
	Rs number,
	Pn number,
	addr clob,
	deptno number NOT NULL,
	PRIMARY KEY (studno)
);



/* Create Foreign Keys */

ALTER TABLE NEW_TABLE
	ADD FOREIGN KEY (classnum)
	REFERENCES class (classnum)
;


ALTER TABLE professor
	ADD FOREIGN KEY (deptno)
	REFERENCES department (deptno)
;


ALTER TABLE student
	ADD FOREIGN KEY (deptno)
	REFERENCES department (deptno)
;


ALTER TABLE class
	ADD FOREIGN KEY (profno)
	REFERENCES professor (profno)
;


ALTER TABLE NEW_TABLE
	ADD FOREIGN KEY (studno)
	REFERENCES student (studno)
;



