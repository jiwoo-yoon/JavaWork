--#7201
SELECT * FROM T_STUDENT;
SELECT GRADE, MAX(HEIGHT) FROM T_STUDENT GROUP BY GRADE;

SELECT GRADE, NAME, MAX(HEIGHT)
FROM T_STUDENT
WHERE (GRADE, HEIGHT) 
	IN(SELECT GRADE, MAX(HEIGHT) FROM T_STUDENT GROUP BY GRADE)
GROUP BY GRADE, NAME
ORDER BY GRADE ASC
;

--#7202
SELECT DEPTNO, MIN(HIREDATE) FROM T_PROFESSOR GROUP BY DEPTNO;

SELECT P.PROFNO , P.NAME , MIN(P.HIREDATE) , D.DNAME 
FROM T_PROFESSOR P, T_DEPARTMENT D
WHERE P.DEPTNO = D.DEPTNO AND 
	(P.DEPTNO ,P.HIREDATE) IN(SELECT DEPTNO, MIN(HIREDATE) FROM T_PROFESSOR GROUP BY DEPTNO)
	GROUP BY P.PROFNO , P.NAME , D.DNAME
	ORDER BY D.DNAME ASC	
	;

--#7203
SELECT POST, MAX(PAY) FROM T_EMP2 GROUP BY POST;

SELECT NAME , POST, MAX(PAY) 
FROM T_EMP2
WHERE (POST, PAY) IN(SELECT POST, MAX(PAY) FROM T_EMP2 GROUP BY POST)
GROUP BY NAME, POST 
ORDER BY MAX(PAY) ASC 
;

--#7204
SELECT * FROM T_EMP2;
SELECT * FROM T_DEPT2;

SELECT AVG(PAY) FROM T_EMP2 GROUP BY DEPTNO;

SELECT D.DNAME , E.NAME , E.PAY 
FROM T_EMP2 E, T_DEPT2 D
WHERE E.DEPTNO = D.DCODE AND 
	PAY <ALL (SELECT AVG(PAY) FROM T_EMP2 GROUP BY DEPTNO)
ORDER BY E.PAY ASC 
;

--#7205
SELECT A.NAME "사원이름", NVL(A.POST, ' ') "직급", A.PAY "급여"
FROM T_EMP2 A
WHERE A.PAY >=
	(SELECT AVG(B.PAY) FROM T_EMP2 B WHERE NVL(A.POST, ' ') = NVL(B.POST, ' ')) --특정직급의 평균연봉

;


--스칼라 서브쿼리(SELECT절) ~ 1행만 출력
--인라인 뷰(FROM절)

--#7206
--Scalar Sub Query
SELECT 
NAME "사원이름",
(SELECT D.DNAME FROM T_DEPT2 D
	WHERE E.DEPTNO = D.DCODE ) "부서이름"
FROM T_EMP2 E;

