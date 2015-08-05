drop database elearning;
create database elearning;
use elearning;

-----�û���
drop table t_user;
create table t_user (
	user_id 	varchar(20) not null primary key,
	user_name 	varchar(30),
	user_pass 	char(20),
	birthday	datetime,
	sex		char(1),
	email		varchar(50),
	phone		varchar(20),
	company		varchar(50),
	group_id	char(4),
	area		varchar(20),
	signature	varchar(50),
	user_right	int,
	user_status	char(1),
	register_time	datetime not null default getdate()
	);

-----�û���¼��
drop table t_user_log;
create table t_user_log (
	user_id		varchar(20),
	login_time	datetime not null default getdate(),
	ip_address	char(15)
	);

-----�γ̱�
drop table t_class;
create table t_class (
	class_id	varchar(14) not null primary key,
	class_name	varchar(50),
	class_type	char(1),
	keywords	varchar(100),
	description	varchar(200),
	class_time	int,
	pass_stander	int,
	set_time	datetime,
	peoples		int,
	pass_count	int,
	class_status	char(1)
	);
	
-----�û��γ̱�
drop table t_user_class;
create table t_user_class (
	user_id 	varchar(20) not null,
	class_id	varchar(14) not null,
	start_time	datetime,
	end_time	datetime,
	status		char(1),
	primary key (user_id, class_id)
	);
	
-----�û�����γ�
drop table t_user_apply;
create table t_user_apply (
	user_id		varchar(20) not null,
	class_id	varchar(14) not null,
	apply_time	datetime,
	apply_reason	varchar(200),
	approval_time	datetime,
	approval_id	varchar(20),
	apply_status	char(1),
	primary key (user_id, class_id)
	);
	

-----�γ����ݱ�
drop table t_lesson;
create table t_lesson (
	class_id	varchar(14) not null,
	lesson_id	varchar(6) not null,
	lesson_name	varchar(50),
	lesson_url	varchar(100),
	primary key (class_id, lesson_id)
	);
	
-----�û�ѧϰ�ʼǼ�¼
drop table t_lesson_log;
create table t_lesson_log (
	user_id		varchar(20) not null,
	class_id	varchar(14) not null,
	lesson_id	varchar(6) not null,
	lesson_count	int,
	notes		text,
	primary key (user_id, class_id, lesson_id)
	);
	
-----�û�����/�ռǱ�
drop table t_user_daily;
create table t_user_daily (
	user_id		varchar(20) not null,
	user_day	datetime,
	notes		text
	);
	
-----�����
drop table t_code;
create table t_code (
	code_id		char(4) not null,
	code_value	char(4)	not null,
	code_namec	varchar(50),
	code_namee	varchar(50),
	primary key (code_id, code_value)
	);

insert into t_code values ('0000', '0001', '�û�״̬', 'User Status');
insert into t_code values ('0001', '0', '������', 'Applying');	
insert into t_code values ('0001', '1', '����', 'Normal');	
insert into t_code values ('0001', '9', '�ر�', 'Closed');	
insert into t_code values ('0000', '0002', '�û���', 'User Group');	
insert into t_code values ('0002', '01', '�쵼��', 'Manager Group');	
insert into t_code values ('0002', '02', '��Ʋ�', 'Accountant Group');	
insert into t_code values ('0002', '03', '�з���', 'Develpoer Group');	
insert into t_code values ('0000', '0003', '�û���ɫ', 'User Role');	
insert into t_code values ('0003', '1', '����û�', 'Normal User');	
insert into t_code values ('0003', '2', 'ע���û�', 'Register User');	
insert into t_code values ('0003', '3', '�γ̹���Ա', 'Lesson Manager');	
insert into t_code values ('0003', '4', '��������', 'Chat owner');	
insert into t_code values ('0003', '5', 'BBS����', 'BBS Manager');	
insert into t_code values ('0003', '6', 'ϵͳ����Ա', 'Administrator');	
insert into t_code values ('0000', '0004', '�γ�����', 'Lesson types');	
insert into t_code values ('0004', '0', '����', 'Open');	
insert into t_code values ('0004', '1', 'ע��', 'Register');	
insert into t_code values ('0004', '2', '����', 'fee');	
insert into t_code values ('0000', '0005', '�γ�״̬', 'Lesson Status');	
insert into t_code values ('0005', '0', '������', 'Adding');	
insert into t_code values ('0005', '1', '����', 'Normal');	
insert into t_code values ('0005', '9', '�ر�', 'Closed');	
insert into t_code values ('0000', '0006', '�û�����γ�״̬', 'User apply lesson Status');	
insert into t_code values ('0006', '0', '������', 'Appling');	
insert into t_code values ('0006', '1', '��׼', 'Approved');	
insert into t_code values ('0006', '9', '����', 'Cancel');	
insert into t_code values ('0000', '0007', '�û��γ̱�״̬', 'User Lesson Status');	
insert into t_code values ('0007', '0', '����ѧϰ', 'Studing');	
insert into t_code values ('0007', '1', 'ͨ��', 'Pass');	
insert into t_code values ('0007', '2', '������', 'Fail');	
insert into t_code values ('0007', '8', '�ر�', 'Close');	
insert into t_code values ('0007', '9', '����', 'Cancel');	
insert into t_code values ('0000', '0008', '�������', 'News Type');
insert into t_code values ('0008', '1', '��˾����', 'Company News');	
insert into t_code values ('0008', '2', '�ڲ�����', 'Internal News');	
insert into t_code values ('0000', '0009', '����״̬', 'News Status');	
insert into t_code values ('0009', '1', '��ʾ', 'Dispaly');	
insert into t_code values ('0009', '2', 'ɾ��', 'Delete');	
insert into t_code values ('0000', '0010', '���ߴ���״̬', 'OnlineCall Status');	
insert into t_code values ('0010', '1', '���ʼ�', 'New Message');	
insert into t_code values ('0010', '2', '�Ѷ��ʼ�', 'Old Message');	
insert into t_code values ('0010', '3', '�ݸ�', 'Draft');	
insert into t_code values ('0010', '4', '�ѷ����ʼ�', 'Send Message');	
insert into t_code values ('0000', '0011', '��Ŀ����', 'Topic type');	
insert into t_code values ('0011', '0', '��ѡ��', 'Single answer');	
insert into t_code values ('0011', '1', '��ѡ��', 'More answers');	
insert into t_code values ('0000', '0012', '��Ŀ״̬', 'Topic status');	
insert into t_code values ('0012', '0', '������', 'Adding');	
insert into t_code values ('0012', '1', '����', 'Normal');	
insert into t_code values ('0012', '2', '�ر�', 'Closed');	

-----�˵������
drop table t_menu;
create table t_menu (
	menu_id		char(4) not null primary key,
	menu_namec	varchar(50),
	menu_namee	varchar(50),
	menu_url	varchar(100),
	menu_right	int
	);
	
-----���ű�
drop table t_news;
create table t_news (
	news_id		varchar(20) not null primary key,
	news_type	char(4),
	news_title	varchar(100),
	news_key	varchar(255),
	news_content	varchar(100),
	news_image	varchar(100),
	news_date	datetime,
	news_count	int,
	news_man	varchar(20),
	news_satuts	char(1)
	);
	
-----���ߴ���
drop table t_onlinecall;
create table t_onlinecall (
	oc_id		varchar(20) not null primary key,
	oc_name		varchar(20),
	oc_obj		varchar(20),
	oc_title	varchar(100),
	oc_content	text,
	oc_datetime	datetime,
	oc_status	char(1)
	);
	
-----������Ŀ��
drop table t_test;
create table t_test (
	topic_id	varchar(20) not null,
	class_id	varchar(14) not null,
	topic_type	char(1),
	topic_mark	int,
	topic_status	char(1),
	user_id		varchar(20),
	topic_date	datetime,
	primary key (topic_id, class_id)
	);
	
-----���������
drop table t_testlib;
create table t_testlib (
	topic_id		varchar(20) not null,
	topic_answer		char(1) not null,
	answer_description	varchar(255),
	is_answer		char(1),
	primary key (topic_id, topic_answer)
	);
	
-----���Գɼ���
drop table t_result;
create table t_result (
	user_id 	varchar(20),
	class_id	char(14),
	test_date	datetime,
	test_mark	int
	);

-----�û�ģ���
drop table t_userstyle;
create table t_userstyle(
	user_id			varchar(20) not null,
	font 			varchar(40) not null,
	defaultfont 		varchar(40) not null,
	defaulttitlefont 	varchar(40) not null,
	headerfont 		varchar(40) not null,
	bgcolor 		varchar(20) not null,
	fontcolor 		varchar(20) not null,
	linkcolor 		varchar(20) not null,
	linkstyle 		varchar(30) not null,
	onmousecolor 		varchar(20) not null,
	titlecolor 		varchar(20) not null,
	tablecolor 		varchar(20) not null,
	firstrowcolor 		varchar(20) not null,
	bbscolor 		varchar(20) not null,
	bbslinkcolor 		varchar(20) not null,
	tablebordercolor 	varchar(20) not null,
	stressfont 		varchar(20) not null,
	tabelwidth 		varchar(20) not null,
	lines_in_page		char(3) not null,
	primary key (user_id)
);

insert into t_userstyle values ('test',	'font',	'defaultfont',	'defaulttitlefont','headerfont',
'bgcolor',
'fontcolor',
'linkcolor',
'linkstyle',
'onmousecolor',
'onmouselink',
'onmousestyle',
'titlecolor',
'titlefontcolor',
'catfontcolor',
'tablecolor',
'firstrowcolor',
'tworowcolor',
'bbscolor',
'bbslinkcolor',
'tablebordercolor',
'stressfont',
'newfont',
'tabelwidth',
'mainleftwidth',
'1',
'1',
'10'
);


----�����
create table t_board (
	call_id	char(12) not null primary key,
	user_id	varchar(20),
        call_name varchar(32);
	call_content	varchar(256),
	call_time	datetime
);