--제약조건(Constraint)
--테이블 생성시 동시에 설정하기

--t_dept2.dcode 참조예정
SELECT * FROM T_DEPT2;

--#9001
--제약조건명을 명시하지 않는 방법
DROP TABLE t_emp3 CASCADE CONSTRAINT; -- 테이블에 연관된 제약조건도 다 지운다.
CREATE TABLE t_emp3(
	no NUMBER(4) PRIMARY KEY,
	name VARCHAR2(10) NOT NULL,
	jumin VARCHAR2(13) NOT NULL UNIQUE,
	area NUMBER(1) CHECK(area < 5),
	deptno VARCHAR2(6) REFERENCES T_DEPT2(dcode)
);


--별도의 항목으로 제약조건 
DROP TABLE t_emp4 CASCADE CONSTRAINT;

CREATE TABLE t_emp4(
	no NUMBER(4),
	name VARCHAR2(10) NOT NULL,
	jumin VARCHAR2(13) NOT NULL,
	area NUMBER(1),
	deptno VARCHAR2(6),
	PRIMARY KEY(no),
	UNIQUE(jumin),
	CHECK(area < 5),
	FOREIGN KEY(deptno) REFERENCES T_DEPT2(dcode)
);


--#9002
--제약조건명을 명시하여 정의
DROP TABLE t_emp3 CASCADE CONSTRAINT;

CREATE TABLE t_emp3(
	no NUMBER(4) CONSTRAINT emp3_no_pk PRIMARY KEY,
	name VARCHAR2(10) CONSTRAINT emp3_name_nn NOT NULL,
	jumin VARCHAR2(13) CONSTRAINT emp3_jumin_nn NOT NULL 
						CONSTRAINT emp3_jumin_uk UNIQUE,
	area NUMBER(1) CONSTRAINT emp3_area_ck CHECK(area < 5),
	deptno VARCHAR2(6) CONSTRAINT emp3_deptno_fk REFERENCES T_DEPT2(dcode)
);


DROP TABLE t_emp3 CASCADE CONSTRAINT;

CREATE TABLE t_emp3(
	no NUMBER(4),
	name VARCHAR2(10) CONSTRAINT emp3_name_nn NOT NULL,
	jumin VARCHAR2(13) CONSTRAINT emp3_jumin_nn NOT NULL, 
	area NUMBER(1) ,
	deptno VARCHAR2(6),
	CONSTRAINT emp3_no_pk PRIMARY KEY(no),
	CONSTRAINT emp3_jumin_uk UNIQUE(jumin),
	CONSTRAINT emp3_area_ck CHECK(area < 5),
	CONSTRAINT emp3_deptno_fk FOREIGN KEY(deptno) REFERENCES T_DEPT2(dcode)
);


--#9003 제약조건조회하기
SELECT owner, constraint_name, constraint_type, status
FROM USER_CONSTRAINTS
WHERE table_name = 'T_EMP4'; --테이블명 대문자로


SELECT owner, constraint_name, constraint_type, status
FROM USER_CONSTRAINTS
WHERE table_name = 'T_EMP3';


--제약조건에 맞는/ 위배되는 DML 시도해보기
INSERT INTO t_emp3 VALUES(1, '오라클', '1234561234567', 4, 1000);
--두번실행하면  ORA-00001: unique constraint (SCOTT0316.EMP3_NO_PK) violated

INSERT INTO t_emp3 VALUES(2, '오라클', '1234561234567', 4, 1000);
--EMP3_JUMIN_UK, UNIQUE 에러

INSERT INTO t_emp3 VALUES(2, '오라클', '2222222222222222', 4, 1000);
--JUMIN의 MAXIMUM을 넘어서 에러

INSERT INTO t_emp3 VALUES(2, '오라클', '2222222222222', 10, 1000);
--CHECK 에러 :  value larger than specified precision allowed for this column

INSERT INTO t_emp3 VALUES(2, '오라클', '2222222222222', 3, 2000);
--EMP3_DEPTNO_FK, FOREGIN KEY 에러

INSERT INTO t_emp3 (NO, jumin, area, deptno) VALUES(2, '3333333333333', 4, 1001);
--EMP3 의 NAME이 NOT NULL인데 VALUES에 이름이 없어서 에러


--INSERT뿐 아니라 UPDATE/DELETE 에서도 오류 발생 가능
UPDATE t_emp3 SET area = 10 WHERE NO = 1; --CHECK 값 오류

SELECT * FROM t_emp3;
DELETE FROM T_DEPT2 WHERE dcode = 1000; --참조되고 있는 값이 부모값인데 부모를 먼저 삭제하면 위배된다.


--#9005)테이블생성후에 alter 명령 사용하여 제약조건 추가 가능
--t_emp4 의 name컬럼에 unique 제약조건 추가
ALTER TABLE t_emp4 ADD CONSTRAINT emp4_name_uk UNIQUE(name);

--#9006)t_emp4 테이블의 area 컬럼에 NOT NULL 제약조건 추가
--이미 컬럼의 디폴트값이 null을 허용하고 있어서 add로는 추가 못함
--그래서 modify로 해주야댐
ALTER TABLE t_emp4 ADD CONSTRAINT emp4_area_nn NOT NULL; --안댐

ALTER TABLE t_emp4 MODIFY (area CONSTRAINT emp4_area_nn NOT NULL);


--#9007
ALTER TABLE t_emp4 
ADD CONSTRAINT emp4_name_fk FOREIGN KEY(name) REFERENCES t_emp2(name);
--에러 : 참조되는 부모테이블의 컬럼은 primary key 이거나 unique 해야한다.
-- 부모쪽에 unique를 추가해주고 다시 실행시키면 에러 안남
ALTER TABLE t_emp2 ADD CONSTRAINT emp2_name_uk UNIQUE(name);

--#9008
DROP TABLE t_emp3 CASCADE CONSTRAINT;

CREATE TABLE t_emp3(
	no NUMBER(4) CONSTRAINT emp3_no_pk PRIMARY KEY,
	name VARCHAR2(10) CONSTRAINT emp3_name_nn NOT NULL,
	jumin VARCHAR2(13) CONSTRAINT emp3_jumin_nn NOT NULL 
						CONSTRAINT emp3_jumin_uk UNIQUE,
	area NUMBER(1) CONSTRAINT emp3_area_ck CHECK(area < 5),
	deptno VARCHAR2(6) 
	CONSTRAINT emp3_deptno_fk REFERENCES T_DEPT2(dcode)
	ON DELETE CASCADE --부모삭제되면 자식도 같이 삭제
	--ON DELETE SET NULL --부모삭제되면 자식은 NULL값으로 남겠다.
);

--#9009
--t_emp4 테이블의 name필드의 제약조건에
--부모테이블이 삭제되면 null이 되도록 설정하기 (alter사용)
ALTER TABLE t_emp4 DROP CONSTRAINT emp4_name_fk; -- 일단 기존 제약조건 삭제

ALTER TABLE t_emp4
ADD CONSTRAINT emp4_name_fk FOREIGN KEY(name)
	REFERENCES t_emp2(name)
	ON DELETE SET NULL
;


--DISABLE NOVALIDATE
--#9010
SELECT * FROM T_NOVALIDATE;
SELECT * FROM T_VALIDATE;

SELECT OWNER , CONSTRAINT_NAME, CONSTRAINT_TYPE, STATUS -- CONSTRAINT타입 확인
FROM USER_CONSTRAINTS
WHERE table_name = 'T_VALIDATE';

SELECT OWNER , CONSTRAINT_NAME, CONSTRAINT_TYPE, STATUS -- CONSTRAINT타입 확인
FROM USER_CONSTRAINTS
WHERE table_name = 'T_NOVALIDATE';

INSERT INTO t_novalidate VALUES(1, 'DDD'); 

ALTER TABLE t_novalidate -- PK끄기
DISABLE NOVALIDATE CONSTRAINT SYS_C007023;

SELECT * FROM T_NOVALIDATE;

ALTER TABLE t_novalidate
ENABLE NOVALIDATE CONSTRAINT SYS_C007023;

DELETE FROM T_NOVALIDATE WHERE NAME = 'DDD';




