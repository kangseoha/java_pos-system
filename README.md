# 포스 시스템 자바 프로젝트

## 1. 개발환경
* Oracle Database 11g
* JAVA (JDK1.8.0_181)
* Eclipse IDE for Java Developers Version: 2019-06

## 2. 요구사항(기능)
* 사원 로그인
* 사원번호 찾기
* 주문,결제
* 사원 추가, 삭제
* 사원 스케쥴표 및 급여 계산
* 매출현황
* 메뉴관리
* 재고관리

## 3. 테이블 

```sql
create table sale(
sno number primary key,
menu varchar2(20) not null,
count number,
price number not null,
reg_date date default sysdate);

create sequence sno_seq;
insert into sale values(sno_seq.nextval,'떡볶이','1','5000');
```

```sql
create table menu(
mno number not null, 
menu varchar2(20) not null,
price number not null);

insert into menu values(1,'떡볶이',3000);
insert into menu values(2,'치즈떡볶이',3500);
insert into menu values(3,'크림떡볶이',4000);
insert into menu values(4,'김밥',2000);
insert into menu values(5,'순대',3000);
insert into menu values(6,'어묵',3000);
insert into menu values(7,'참치마요컵밥',3500);
insert into menu values(8,'튀김',3000);
insert into menu values(9,'쿨피스',1500);
```

```sql
create table Inventory(
ino number primary key,
element varchar2(20) not null,
count number);


create sequence ino_seq;

insert into Inventory values(ino_seq.nextval,'떡',1000);
insert into Inventory values(ino_seq.nextval,'어묵',1000);
insert into Inventory values(ino_seq.nextval,'소스',1000);
insert into Inventory values(ino_seq.nextval,'치즈',1000);
insert into Inventory values(ino_seq.nextval,'크림',1000);
insert into Inventory values(ino_seq.nextval,'김밥',1000);
insert into Inventory values(ino_seq.nextval,'순대',1000);
insert into Inventory values(ino_seq.nextval,'어묵',1000);
insert into Inventory values(ino_seq.nextval,'참치마요컵밥',1000);
insert into Inventory values(ino_seq.nextval,'튀김',1000);
insert into Inventory values(ino_seq.nextval,'쿨피스',1000);
```

```sql
create table bill(
bno number primary key,
menu1 varchar2(20) not null,
count1 varchar2(20),
price1 varchar2(20), 
menu2 varchar2(20),
count2 varchar2(20),
price2 varchar2(20), 
menu3 varchar2(20),
count3 varchar2(20),
price3 varchar2(20), 
menu4 varchar2(20),
count4 varchar2(20),
price4 varchar2(20), 
menu5 varchar2(20),
count5 varchar2(20),
price5 varchar2(20),
sumprice number,
reg_date date default sysdate);
create sequence bno_seq;
``` 

```sql
CREATE TABLE EMPLOYEE
   (   ENO NUMBER, 
   EPW VARCHAR2(20 BYTE), 
   ENAME VARCHAR2(20 BYTE), 
   EPOSITION VARCHAR2(20 BYTE), 
   ETEL VARCHAR2(20 BYTE)
   )  ;

Insert into EMPLOYEE (ENO,EPW,ENAME,EPOSITION,ETEL) values (1000,'1234','강서하','A2','010-0000-0000');
Insert into EMPLOYEE (ENO,EPW,ENAME,EPOSITION,ETEL) values (1001,'1234','이동관','A1','010-1111-1111');
```


```sql
create table schedule(
ename varchar2(20),
start_time number,
end_time number
);

Insert into schedule values('강서하',15,22);
Insert into schedule values('이동관',15,22);
```





