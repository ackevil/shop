
show tables

show databases;


--用户表
create table huituopin.User(
	user_id int primary key auto_increment,  --用户id
	user_phone varchar(50) not null,		 --用户电话号码
	user_pwd varchar(100) not null,	  	     --登录密码     	 
	user_wc_id varchar(200),                 --用户微信id
	user_wc_nickname varchar(200),           --昵称
	user_wc_avatar varchar(200),             --头像,本地路径
	user_wc_gender bit,                      --性别
	user_type bit not null,			 		 --用户类型：1 贫困人士 2 爱心人士	 
	user_signtime datetime not null,         --注册时间
	user_is_delete bit not null,             --该记录是否删除
	user_is_online bit not null,             --该用户是否在线
	user_c1 varchar(200),                    --备用字段
	user_c2 varchar(200)
);	

--用户详细表（贫困人员）
create table huituopin.PoorPeople(
	poor_id int primary key auto_increment,   --贫困人员详细信息主键 
	user_id int not null,                     --贫困人员详细信息对应的用户ID
	poor_realname varchar(50) not null,       --贫困人员真实姓名
	poor_age tinyint not null,                --贫困人员年龄
	poor_school varchar(50),                  --贫困人员学校名称
	poor_province varchar(50) not null,       --贫困人员省份
	poor_city varchar(50) not null,           --贫困人员城市
	poor_district varchar(50) not null,       --贫困人员县/区
	poor_detail_address varchar(100) not null,
	poor_background varchar(1000) not null,
	poor_state bit not null,
	poor_is_delete bit not null,
	poor_c1 varchar(200),
	poor_c2 varchar(200)
);

create table detailinfopoor(
	id int primary key,   		--贫困人员详细信息主键 	
	uId int,					--贫困人员详细信息对应的用户ID
	age int,					--贫困人员年龄
	shoolName varchar(50),		--贫困人员学校名称
	province varchar(50),		--贫困人员省
	city varchar(50),			--贫困人员市
	county varchar(50),			--贫困人员县
	address varchar(200),		--贫困人员详细地址
	briefInfo varchar(1000),	--贫困人员简单介绍
);

--用户详细表（爱心人士）
create table detailinforich(
	id int primary key,			--爱心人士详细信息ID
	uId int,					--爱心人士对应用户表的ID
	age int,					--爱心人士年龄
	integral int,				--爱心人士积分
);
------------------------执行代码----------------------------
--用户表
create table user(
	id int primary key,    	 	 
	wId varchar(66),			 
	uType int,					 
	tel varchar(15), 
	pwd varchar(50),			
	headImg varchar(50),		
	nikeName varchar(50),		
	registTime varchar(30),		
	detailInfoId int;			
);
--用户详细表（贫困人员）
create table detailinfopoor(
	id int primary key,    	
	uId int,					
	age int,					
	shoolName varchar(50),	
	province varchar(50),		
	city varchar(50),			
	county varchar(50),			
	address varchar(200),		
	briefInfo varchar(1000),	
);

--用户详细表（爱心人士）
create table detailinforich(
	id int primary key,			
	uId int,					
	age int,					
	integral int,				
);