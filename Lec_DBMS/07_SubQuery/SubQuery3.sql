CREATE TABLE test_emp_a(
	emp_id NUMBER,
	emp_name VARCHAR2(100)
);

CREATE TABLE test_emp_b(
	emp_id NUMBER,
	emp_name VARCHAR2(100)
);

DELETE FROM test_emp_a;
DELETE FROM test_emp_b;

--한 row씩 저장
INSERT INTO test_emp_a VALUES(101, '장윤성');
INSERT INTO test_emp_b VALUES(201, '고유성');

SELECT * FROM test_emp_a;
SELECT * FROM test_emp_b;

--동시에 여러개 테이블에 insert하기
--다중 테이블 INSERT 구문에 sub query 필요
INSERT ALL
	INTO test_emp_a VALUES(102, '조현주')
	INTO test_emp_b VALUES(202, '최현주')
SELECT * FROM DUAL;

--sub query 로 INSERT 가능
INSERT INTO test_emp_a (SELECT 400, '안성원' FROM DUAL);
INSERT INTO test_emp_b (SELECT 500, '김성원' FROM DUAL);

--테이블 확장시키기
INSERT INTO test_emp_a (SELECT * FROM test_emp_a);
SELECT * FROM test_emp_a;

INSERT INTO test_emp_b(emp_name) (SELECT emp_name FROM test_emp_b);
SELECT * FROM test_emp_b;