--#8101

CREATE OR REPLACE VIEW v_prof -- REPLACE는 뷰가 있으면 지우고 다시만들어라
AS
SELECT profno, name, email, hpage FROM T_PROFESSOR;

SELECT * FROM v_prof;

SELECT tname FROM tab; -- view 확인가능
					
--View 생성시 별도의 컬럼이름을 지정해줄수 있다.
CREATE OR REPLACE VIEW v_prof(pfno, nm, em, hp)
AS
SELECT profno, name, email, hpage 
FROM T_PROFESSOR;

SELECT * FROM v_prof;

--#8102
CREATE VIEW v_prof_dept
AS
SELECT P.PROFNO , P.NAME , D.DNAME 
FROM T_PROFESSOR P, T_DEPARTMENT D
WHERE P.DEPTNO = D.DEPTNO;

SELECT * FROM v_prof_dept;


--#8103
SELECT
	d.dname "학과명", 
	s.max_height "최대키", 
	s.max_weight "최대몸무게"
FROM 
	( SELECT deptno1, MAX(height) max_height, MAX(weight) max_weight
	FROM t_student
	GROUP BY deptno1 ) s , 
	t_department d
WHERE 
	s.deptno1 = d.deptno;

--#8104) 연습
--t_student, t_department 테이블  학과별로 가장 키가 큰 학생들의 이름과 키, 
--학과이름을 인라인뷰 를 사용하여 다음과 같이 출력하세요
SELECT d.dname "학과명", a.max_height "최대키", s.name "학생이름", s.height "키"
FROM 
	(SELECT deptno1, MAX(height) max_height FROM t_student GROUP BY deptno1) a,
	t_student s, t_department d
WHERE 
	s.deptno1 = a.deptno1 AND s.height = a.max_height
	AND s.deptno1 =  d.deptno
;

--#8105) 연습
--t_student 테이블 : 
--학생의 키가 동일 학년의 평균 키보다 큰 학생들의 학년과 이름과 키, 해당 학년의 평균키를 출력하되, 
--inline view 를 사용해서 아래와 같이 출력하세요. 단 학년 칼럼은 오름 차순으로 정렬.

SELECT grade, avg(height) FROM t_student GROUP BY grade;

SELECT s.grade, s.name, s.height, a.avg_height
FROM 
(
SELECT grade, avg(height) avg_height FROM t_student GROUP BY grade
) a,
t_student s
WHERE 
a.grade = s.grade AND s.height > a.avg_height
ORDER BY GRADE
;

