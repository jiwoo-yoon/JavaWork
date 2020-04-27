-- SELECT 결과물중 맨 위의 5개만 출력해보고 싶으면 어케 해야 하나?
--   ex) 게시판.. 페이징

-- DBMS 마다 구현 방법 다름
--	MYSQL : LIMIT
-- 	MS SQL server : TOP
-- 	ORACLE : ROWNUM 


SELECT EMPNO, ENAME, SAL FROM T_EMP;

--자동적으로 오라클에서 붙여주는 행번호 객체(ROWNUM)
SELECT ROWNUM, EMPNO, ENAME, SAL FROM T_EMP;

--역순으로 정의했지만 ROWNUM은 1부터
SELECT ROWNUM, EMPNO, ENAME, SAL FROM T_EMP ORDER BY EMPNO DESC;

--상위 5개만
SELECT ROWNUM, EMPNO, ENAME, SAL FROM T_EMP 
WHERE ROWNUM <= 5 
ORDER BY EMPNO DESC
;

--SELECT에 ROWNUM 없이도 동작
SELECT EMPNO, ENAME, SAL FROM T_EMP 
WHERE ROWNUM <= 5 
ORDER BY EMPNO DESC
;

-- ROWNUM > 5 동작안함 ~ ROWNUM 범위에 1이 포함안되면 동작을 안함.
SELECT ROWNUM, EMPNO, ENAME, SAL FROM T_EMP 
WHERE ROWNUM > 5 
ORDER BY EMPNO DESC
;

--상위5개출력
--row1 ~ row5 까지 출력(1page)
SELECT ROWNUM, EMPNO, ENAME, SAL
FROM T_EMP
WHERE ROWNUM >= 1 AND ROWNUM < 1 + 5
ORDER BY EMPNO DESC;

--phonebook 확장
SELECT * FROM PHONEBOOK;
--INSERT INTO PHONEBOOK (SELECT * FROM PHONEBOOK); 에러 PRIMARY KEY 중복!

INSERT INTO phonebook
	(SELECT phonebook_seq.nextval, pb_name, pb_phonenum, pb_memo, SYSDATE FROM phonebook)
;

--ROWNUM rev.
--1.
SELECT pb_uid, pb_name, pb_phonenum FROM PHONEBOOK ORDER BY pb_uid DESC;

--2.
SELECT T.* 
FROM (SELECT pb_uid, pb_name, pb_phonenum FROM PHONEBOOK ORDER BY pb_uid DESC) "T" --인라인뷰
;

--3.
SELECT ROWNUM "RNUM", T.* 
FROM (SELECT pb_uid, pb_name, pb_phonenum FROM PHONEBOOK ORDER BY pb_uid DESC) "T"
;

--4. 한페이지당 5개 데이터, 2번째 페이지 뽑는 쿼리
SELECT * FROM 
(
SELECT ROWNUM "RNUM", T.* 
FROM (SELECT pb_uid, pb_name, pb_phonenum FROM PHONEBOOK ORDER BY pb_uid DESC) "T"
)
WHERE RNUM >= 6 AND RNUM < 6 + 5
;

--3번째 페이지
SELECT * FROM 
(
SELECT ROWNUM "RNUM", T.* 
FROM (SELECT pb_uid, pb_name, pb_phonenum FROM PHONEBOOK ORDER BY pb_uid DESC) "T"
)
WHERE RNUM >= 11 AND RNUM < 11 + 5
;


-- 한페이지당 10개 데이터, 3번째 페이지
SELECT * FROM 
(
SELECT ROWNUM "RNUM", T.* 
FROM (SELECT pb_uid, pb_name, pb_phonenum FROM PHONEBOOK ORDER BY pb_uid DESC) "T"
)
WHERE RNUM >= 21 AND RNUM < 21 + 10
;





