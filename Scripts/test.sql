select user(),database ();

show tables;
desc department;
desc employee ;
desc title ;

select tno, tname from title;
select * from department;

select tno, tname from title where tno =1;
select empno, empname, title, manager, salary, dept from employee;
select deptno, deptname, floor from department;

insert into title values (6,'인턴');

delete from title where tno = 6;
delete from department where deptno =5;


update title set tname = '계약직' where tno = 6;



update department set deptname = '생기', floor = where deptno = 5;

select deptno,deptname,floor from department ;

select deptno,deptname,floor from department where deptno = 3;

insert into department values (5,'보전',10);

delete from department where deptno = 5;


select empno,empname,title,manager,salary,dept from employee  ;

select empno,empname,title,manager,salary,dept from employee where empno = 1003;


delete from employee where empno = 8888;

CREATE OR REPLACE VIEW 
VW_EMPLOYEE
as;
SELECT e.empno, 
	   e.empname, 
	   t.tno , t.tname, 
	   e.manager as manager_no, 
	   m.empname as manager_name, 
	   e.salary,
	   d.deptno, d.deptname ,
	   d.floor 
FROM EMPLOYEE e JOIN TITLE t ON e.TITLE = t.TNO
	LEFT JOIN EMPLOYEE m ON e.MANAGER = m.EMPNO
	JOIN DEPARTMENT d ON e.dept = d.DEPTNO ;

select * from vw_employee;

drop view vw_employee 

select *
	from department d where d




-- 부서가 1인 사원정보를 출력

select* 
	from employee e 
	where dept =1;

select* 
	from employee e 
	where dept = (select deptno from department d2 where deptno=2);



