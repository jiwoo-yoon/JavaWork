SELECT * FROM T_PROFESSOR;

SELECT name, pay, BONUS, pay+bonus --일반함수에서는 null이랑 연산하면 무조건 null
FROM T_PROFESSOR ;

--그룹함수에서는 동작, null은 연산에서 제외되어 동작
SELECT sum(pay), sum(BONUS) FROM T_PROFESSOR;

--nvl() 함수
SELECT name, pay, BONUS,
	   pay+bonus 총지급액,
	   pay + NVL(BONUS, 0) 총지급액 
FROM T_PROFESSOR ;

--#4201
SELECT NAME, pay, NVL(BONUS, 0) BONUS,
	   pay*12 + nvl(BONUS, 0) 연봉 
FROM T_PROFESSOR 
WHERE DEPTNO = 101;

--#4202
SELECT NAME, pay, NVL2(BONUS, BONUS, 0) BONUS,
	   NVL2(BONUS, pay*12+BONUS, pay*12) 연봉 
FROM T_PROFESSOR 
WHERE DEPTNO = 101;
















