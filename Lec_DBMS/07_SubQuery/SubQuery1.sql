--SubQuery
--일단 조건절에 들어갈 조건을 만들고 그다음에 만들기
--#7101
-- t_emp 테이블에서 scott 보다 급여를 많이 받는 사람의 이름과 급여 출력
SELECT * FROM t_emp;

SELECT sal FROM t_emp WHERE ename = 'SCOTT'; -- scott의 급여를 먼저 구하고, 이게 subquery

SELECT ENAME, SAL 
FROM T_EMP
WHERE SAL > (SELECT sal FROM t_emp WHERE ename = 'SCOTT') -- 조건절에 대입
;

--#7102
--t_student 테이블에서 가장 키 큰 학생의 '이름'과 '키'를 출력
SELECT MAX(height) FROM T_STUDENT ; -- 학생테이블에서 가장 큰키를 구하고

SELECT NAME , HEIGHT 
FROM T_STUDENT 
WHERE HEIGHT = (SELECT MAX(height) FROM T_STUDENT) -- 대입
;

--단일행 Sub Query
--#7103
SELECT DEPTNO1 FROM T_STUDENT WHERE name = '이윤나';

SELECT s.NAME , d.DNAME 
FROM T_STUDENT s, T_DEPARTMENT d
WHERE s.DEPTNO1 = d.DEPTNO AND 
	s.DEPTNO1 = (SELECT DEPTNO1 FROM T_STUDENT WHERE name = '이윤나')
;

--#7104
SELECT HIREDATE FROM T_PROFESSOR WHERE NAME = '송도권';

SELECT P.NAME , P.HIREDATE , D.DNAME 
FROM T_PROFESSOR P, T_DEPARTMENT D
WHERE P.DEPTNO = D.DEPTNO AND 
	P.HIREDATE > (SELECT HIREDATE FROM T_PROFESSOR WHERE NAME = '송도권')
	;

--#7105
SELECT AVG(WEIGHT) FROM T_STUDENT WHERE DEPTNO1 = '101';

SELECT NAME , WEIGHT 
FROM T_STUDENT 
WHERE WEIGHT > (SELECT AVG(WEIGHT) FROM T_STUDENT WHERE DEPTNO1 = '101')
;

--#7106
SELECT HIREDATE FROM T_PROFESSOR WHERE NAME = '심슨';
SELECT PAY FROM T_PROFESSOR WHERE NAME = '조인형';

SELECT NAME , PAY , HIREDATE 
FROM T_PROFESSOR 
WHERE HIREDATE = (SELECT HIREDATE FROM T_PROFESSOR WHERE NAME = '심슨') AND
	PAY < (SELECT PAY FROM T_PROFESSOR WHERE NAME = '조인형')
	;


--다중행 Sub Query
/*
 *  IN : 같은 값을 찾음
 * 
>ANY :	최소값을 반환함
		서브쿼리 결과중 가장작은것보다 큰
		애니 오른쪽에 조건절값에 있는 것들보다 크기만 하면 TRUE(조건절의 최소값보다 크면된다.)
		 (서브쿼리 결과중 가장작은것보다 큰)
<ANY :	최대값을 반환함
		서브쿼리 결과중 가장큰것보다 작은
		애니 오른쪽에 조건절값에 있는 것들보다 작으면 TRUE(조건절의 최댓값보다 작으면된다.)
		(서브쿼리 결과중 가장큰것보다 작은)
<ALL :	최소값을 반환함
		서브쿼리 결과중 가장작은것보다 작은 (조건절의 최소값보다 작으면된다.)
		(서브쿼리 결과중 가장작은것보다 작은)
>ALL :	최대값을 반환함
 		서브쿼리 결과중 가장큰것보다 큰 (조건절의 최대값보다 크면된다.)
 		(서브쿼리 결과중 가장큰것보다 큰)
EXIST :	Sub Query 값이 있을 경우 반환
*/

--#7107
SELECT * FROM T_EMP2;
SELECT * FROM T_DEPT2;

SELECT DCODE FROM T_DEPT2 WHERE AREA = '서울지사';

SELECT EMPNO , NAME , DEPTNO 
FROM T_EMP2 
WHERE DEPTNO IN(SELECT DCODE FROM T_DEPT2 WHERE AREA = '서울지사')
;

--#7108
SELECT PAY FROM T_EMP2 WHERE POST = '과장';

SELECT NAME , POST , TO_CHAR(PAY, '999,999,999')||'원' "연봉" 
FROM T_EMP2 
WHERE PAY >ANY (SELECT PAY FROM T_EMP2 WHERE POST = '과장')
-- >= ANY, ALL 다 가능
;


--#7109
SELECT WEIGHT FROM T_STUDENT WHERE GRADE = 4;

SELECT NAME , GRADE , WEIGHT 
FROM T_STUDENT 
WHERE WEIGHT <ALL (SELECT WEIGHT FROM T_STUDENT WHERE GRADE = 4)
;












