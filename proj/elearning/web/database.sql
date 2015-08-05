drop database elearning;
create database elearning;
use elearning;

-----用户表
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

-----用户登录表
drop table t_user_log;
create table t_user_log (
	user_id		varchar(20),
	login_time	datetime not null default getdate(),
	ip_address	char(15)
	);

-----课程表
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
	
-----用户课程表
drop table t_user_class;
create table t_user_class (
	user_id 	varchar(20) not null,
	class_id	varchar(14) not null,
	start_time	datetime,
	end_time	datetime,
	status		char(1),
	primary key (user_id, class_id)
	);
	
-----用户申请课程
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
	

-----课程内容表
drop table t_lesson;
create table t_lesson (
	class_id	varchar(14) not null,
	lesson_id	varchar(6) not null,
	lesson_name	varchar(50),
	lesson_url	varchar(100),
	primary key (class_id, lesson_id)
	);
	
-----用户学习笔记记录
drop table t_lesson_log;
create table t_lesson_log (
	user_id		varchar(20) not null,
	class_id	varchar(14) not null,
	lesson_id	varchar(6) not null,
	lesson_count	int,
	notes		text,
	primary key (user_id, class_id, lesson_id)
	);
	
-----用户日历/日记表
drop table t_user_daily;
create table t_user_daily (
	user_id		varchar(20) not null,
	user_day	datetime,
	notes		text
	);
	
-----代码表
drop table t_code;
create table t_code (
	code_id		char(4) not null,
	code_value	char(4)	not null,
	code_namec	varchar(50),
	code_namee	varchar(50),
	primary key (code_id, code_value)
	);

insert into t_code values ('0000', '0001', '用户状态', 'User Status');
insert into t_code values ('0001', '0', '申请中', 'Applying');	
insert into t_code values ('0001', '1', '正常', 'Normal');	
insert into t_code values ('0001', '9', '关闭', 'Closed');	
insert into t_code values ('0000', '0002', '用户组', 'User Group');	
insert into t_code values ('0002', '01', '领导组', 'Manager Group');	
insert into t_code values ('0002', '02', '会计部', 'Accountant Group');	
insert into t_code values ('0002', '03', '研发部', 'Develpoer Group');	
insert into t_code values ('0000', '0003', '用户角色', 'User Role');	
insert into t_code values ('0003', '1', '浏览用户', 'Normal User');	
insert into t_code values ('0003', '2', '注册用户', 'Register User');	
insert into t_code values ('0003', '3', '课程管理员', 'Lesson Manager');	
insert into t_code values ('0003', '4', '聊天室主', 'Chat owner');	
insert into t_code values ('0003', '5', 'BBS版主', 'BBS Manager');	
insert into t_code values ('0003', '6', '系统管理员', 'Administrator');	
insert into t_code values ('0000', '0004', '课程类型', 'Lesson types');	
insert into t_code values ('0004', '0', '公开', 'Open');	
insert into t_code values ('0004', '1', '注册', 'Register');	
insert into t_code values ('0004', '2', '付费', 'fee');	
insert into t_code values ('0000', '0005', '课程状态', 'Lesson Status');	
insert into t_code values ('0005', '0', '制作中', 'Adding');	
insert into t_code values ('0005', '1', '正常', 'Normal');	
insert into t_code values ('0005', '9', '关闭', 'Closed');	
insert into t_code values ('0000', '0006', '用户申请课程状态', 'User apply lesson Status');	
insert into t_code values ('0006', '0', '申请中', 'Appling');	
insert into t_code values ('0006', '1', '批准', 'Approved');	
insert into t_code values ('0006', '9', '撤销', 'Cancel');	
insert into t_code values ('0000', '0007', '用户课程表状态', 'User Lesson Status');	
insert into t_code values ('0007', '0', '正在学习', 'Studing');	
insert into t_code values ('0007', '1', '通过', 'Pass');	
insert into t_code values ('0007', '2', '不及格', 'Fail');	
insert into t_code values ('0007', '8', '关闭', 'Close');	
insert into t_code values ('0007', '9', '撤销', 'Cancel');	
insert into t_code values ('0000', '0008', '新闻类别', 'News Type');
insert into t_code values ('0008', '1', '公司新闻', 'Company News');	
insert into t_code values ('0008', '2', '内部新闻', 'Internal News');	
insert into t_code values ('0000', '0009', '新闻状态', 'News Status');	
insert into t_code values ('0009', '1', '显示', 'Dispaly');	
insert into t_code values ('0009', '2', '删除', 'Delete');	
insert into t_code values ('0000', '0010', '在线传呼状态', 'OnlineCall Status');	
insert into t_code values ('0010', '1', '新邮件', 'New Message');	
insert into t_code values ('0010', '2', '已读邮件', 'Old Message');	
insert into t_code values ('0010', '3', '草稿', 'Draft');	
insert into t_code values ('0010', '4', '已发送邮件', 'Send Message');	
insert into t_code values ('0000', '0011', '题目类型', 'Topic type');	
insert into t_code values ('0011', '0', '单选题', 'Single answer');	
insert into t_code values ('0011', '1', '多选题', 'More answers');	
insert into t_code values ('0000', '0012', '题目状态', 'Topic status');	
insert into t_code values ('0012', '0', '出题中', 'Adding');	
insert into t_code values ('0012', '1', '正常', 'Normal');	
insert into t_code values ('0012', '2', '关闭', 'Closed');	

-----菜单项管理
drop table t_menu;
create table t_menu (
	menu_id		char(4) not null primary key,
	menu_namec	varchar(50),
	menu_namee	varchar(50),
	menu_url	varchar(100),
	menu_right	int
	);
	
-----新闻表
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
	
-----在线传呼
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
	
-----考试题目表
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
	
-----考试试题表
drop table t_testlib;
create table t_testlib (
	topic_id		varchar(20) not null,
	topic_answer		char(1) not null,
	answer_description	varchar(255),
	is_answer		char(1),
	primary key (topic_id, topic_answer)
	);
	
-----考试成绩表
drop table t_result;
create table t_result (
	user_id 	varchar(20),
	class_id	char(14),
	test_date	datetime,
	test_mark	int
	);

-----用户模版表
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


----公告板
create table t_board (
	call_id	char(12) not null primary key,
	user_id	varchar(20),
        call_name varchar(32);
	call_content	varchar(256),
	call_time	datetime
);