select * from LovingPeople
select * from User where user_type = 1  0==false
select * from User
select * from ClothOrder
select * from Cloth  状态1：未领取
delete from User where user_id=115
delete from Cloth where cloth_id>58
select * from ClothPicture
select * from ClothOrder
select * from ClothDetailInfo
select * from ClothOrder
select * from ShippingAddress
select * from Activity
insert into Cloth (cloth_name, cloth_type1,cloth_type2,cloth_type3,cloth_detailinfo,user_id,cloth_mainpic_path,cloth_intime,cloth_state,cloth_is_delete) 
values ('儿童运动女孩中大童三件套','3','2','1','3','2','cloth3.jpg','2016-05-06 09:52:21.0',1,1)
insert into Cloth (cloth_name, cloth_type1,cloth_type2,cloth_type3,cloth_detailinfo,user_id,cloth_mainpic_path,cloth_intime,cloth_state,cloth_is_delete) 
values ('GESGOO2016春装新款短款外套女百搭休闲薄款棒球服长袖上衣','3','2','1','3','2','cloth2.jpg','2016-05-06 09:52:21.0',1,1)
insert into Cloth (cloth_name, cloth_type1,cloth_type2,cloth_type3,cloth_detailinfo,user_id,cloth_mainpic_path,cloth_intime,cloth_state,cloth_is_delete) 
values ('秋比韩版拉链收腰休闲春秋风衣','3','2','1','3','2','cloth4.jpg','2016-05-06 09:52:21.0',1,1)
insert into Cloth (cloth_name, cloth_type1,cloth_type2,cloth_type3,cloth_detailinfo,user_id,cloth_mainpic_path,cloth_intime,cloth_state,cloth_is_delete) 
values ('高腰女士春夏黑色弹力小脚裤','3','2','1','3','2','cloth1.jpg','2016-05-06 09:52:21.0',1,1)
insert into ClothPicture(cloth_id,cloth_pic_type,cloth_pic_path,cloth_pic_intime,cloth_pic_is_delete)
values ('1',0,'cloth4.jpg','2016-05-06 09:52:21.0',1)
insert into ClothPicture(cloth_id,cloth_pic_type,cloth_pic_path,cloth_pic_intime,cloth_pic_is_delete)
values ('1',0,'cloth5.jpg','2016-05-06 09:52:21.0',1)
insert into ClothPicture(cloth_id,cloth_pic_type,cloth_pic_path,cloth_pic_intime,cloth_pic_is_delete)
values ('1',0,'cloth1.jpg','2016-05-06 09:52:21.0',1)
insert into ClothPicture(cloth_id,cloth_pic_type,cloth_pic_path,cloth_pic_intime,cloth_pic_is_delete)
values ('1',0,'cloth2.jpg','2016-05-06 09:52:21.0',1)
insert into ClothDetailInfo(cloth_detailinfo,cloth_detailinfo_intime,cloth_detailinfo_is_delete)
values ('希望这条裤子能帮助到需要它的人，发挥它更大的价值！','2016-05-06 09:52:21.0',1)
insert into ClothOrder (cloth_id,poor_id,cloth_order_state,cloth_detailinfo_id,loving_id,cloth_mainpic_path,cloth_intime,cloth_state,cloth_is_delete) 
values ('时尚小短裙','1','1','1','1','1','cloth1.jpg','2016-05-06 09:52:21.0',1,1)
delete from PoorPeople where poor_id = 1
update User as u set u.user_wc_id="" where u.user_id=6
--添加一个字段
alter table User add user_name varchar(50) after user_id 
alter table ClothComment add cloth_com_state tinyint after cloth_com_content
update User set user_type = true where user_id = 132  true:贫困人士 false：爱心人士
update Cloth set cloth_state = 2 where cloth_id=20
update ClothOrder set cloth_order_state = 2 where cloth_id = 20
select * from Cloth where cloth_state = '1' and cloth_is_delete = 1 and cloth_id not in (select cloth_id from(select * from Cloth limit 2) as t)limit 2
select * from ClothComment where cloth_com_state =1
delete from ClothComment where cloth_com_id = 8
update ClothComment set cloth_com_state = 0 where cloth_com_id = 8
update ClothPicture set cloth_pic_path = '/resource/upload/image/20160604/BH4wMbJuh0bLon7KrybLflFzEvO-aHNwBO5zZZdp7cKkkm7dxQbbLSKTcKakKMRK.jpg' where cloth_pic_id=86
select * from ClothPicture
delete  from ClothPicture where cloth_pic_id > 58
--修改一个字段
alter table ClothPicture change  column address address1 varchar(30)
update ClothPicture set cloth_id = 11 where cloth_pic_id = 58
select * from Cloth where 0=0 cloth_type1 = ''
delete from Cloth where cloth_id=10
--删除一个字段
alter table User drop column user_name
SELECT
    c.cloth_name,
    u.user_wc_nickname AS setname,
    o.getname
FROM
    Cloth AS c
LEFT JOIN User u
ON c.user_id=u.user_id
LEFT JOIN
    (
        SELECT
            cloth_id,
            user_wc_nickname AS getname
        FROM
            ClothOrder AS c,
            User       AS u
        WHERE
            c.user_id=u.user_id) AS o
ON
    o.cloth_id = c.cloth_id
    SELECT c.cloth_id,c.cloth_intime,c.cloth_mainpic_path,c.cloth_name,ct.cloth_type_name,c.cloth_state,u.user_wc_nickname AS setname,o.getname FROM Cloth AS c LEFT JOIN User u ON c.user_id=u.user_id LEFT JOIN (SELECT c.cloth_id,u.user_wc_nickname AS getname FROM ClothOrder AS c, User AS u  WHERE c.user_id=u.user_id) AS o ON o.cloth_id = c.cloth_id LEFT JOIN ClothType As ct on ct.cloth_type_id = c.cloth_type1 where c.cloth_is_delete = 0 and  DATE_SUB(CURDATE(),INTERVAL 1 HOUR) <= date(c.cloth_intime) order by cloth_intime desc 
    c.cloth_state='' and c.cloth_name like '%%' and c.cloth_type1=''
SELECT c.clothId,c.clothIntime,c.clothMainpicPath,c.clothName,c.clothType1,c.clothState,u.userWcNickname As setname,o.getname FROM Cloth c LEFT JOIN User u ON c.userId=u.userId LEFT JOIN (SELECT c.clothId,u.userWcNickname AS getname FROM ClothOrder c, User u  WHERE c.userId=u.userId) AS o ON o.clothId = c.clothId
SELECT c.cloth_id,c.cloth_intime,c.cloth_mainpic_path,c.cloth_name,c.cloth_type1,c.cloth_state,u.user_wc_nickname AS setname,o.getname FROM Cloth AS c LEFT JOIN User u ON c.user_id=u.user_id LEFT JOIN (SELECT c.cloth_id,u.user_wc_nickname AS getname FROM ClothOrder AS c, User AS u  WHERE c.user_id=u.user_id) AS o ON o.cloth_id = c.cloth_id
select * from (select c.cloth_id,c.cloth_mainpic_path,c.cloth_type1,c.cloth_name,user.user_wc_nickname from Cloth c,User user where c.user_id = user.user_id) as cu,(select user.user_wc_nickname,co.cloth_id from User user,ClothOrder co where user.user_id = co.user_id) as cou where cou.cloth_id = cu.cloth_id
insert into Rule (rule_name, get_credict_num) values ('贫困人士每年领取捐赠物品数量','20')
insert into Rule (rule_name, get_credict_num) values ('爱心人士分享可获积分','5')
insert into Rule (rule_name, get_credict_num) values ('爱心人士捐赠物品已被领取可获积分','10')
select * from Rule

select user.user_id,user.user_wc_nickname,user.user_wc_avatar,cc.cloth_com_content,cc.cloth_com_intime,cloth.cloth_name from ClothComment as cc,User as user,Cloth as cloth where user.user_id = cc.poor_id and cc.cloth_id = cloth.cloth_id and cc.cloth_id = 20 
SELECT c.cloth_id,c.cloth_intime,c.cloth_mainpic_path,c.cloth_name,ct.cloth_type_name,c.cloth_state,u.user_wc_nickname AS setname,o.getname FROM Cloth AS c LEFT JOIN User u ON c.user_id=u.user_id LEFT JOIN (SELECT c.cloth_id,u.user_wc_nickname AS getname FROM ClothOrder AS c, User AS u WHERE c.user_id=u.user_id) AS o ON o.cloth_id = c.cloth_id LEFT JOIN ClothType As ct on ct.cloth_type_id = c.cloth_type1 where c.cloth_is_delete = 0 and c.cloth_intime between Thu Jun 02 02:10:56 CST 2016 and Tue Jun 07 09:25:56 CST 2016
SELECT c.cloth_id,c.cloth_intime,c.cloth_mainpic_path,c.cloth_name,ct.cloth_type_name,c.cloth_state,u.user_wc_nickname AS setname,o.getname,o.gettime  FROM com.huituopin.yjya.entity.Cloth AS c LEFT JOIN User u ON c.user_id=u.user_id LEFT JOIN (SELECT c.cloth_id,u.user_wc_nickname AS getname,c.cloth_order_intime As gettime,c.user_id As getuserId FROM com.huituopin.yjya.entity.ClothOrder AS c, com.huituopin.user.entity.User AS u WHERE c.user_id=u.user_id) AS o ON o.cloth_id = c.cloth_id LEFT JOIN ClothType As ct on ct.cloth_type_id = c.cloth_type1 where c.cloth_is_delete = 0 and c.getuserId = 132
--根据贫困人士Id获取他领取的衣物信息
SELECT c.cloth_id,c.cloth_intime,c.cloth_mainpic_path,c.cloth_name,ct.cloth_type_name,c.cloth_state,u.user_wc_nickname AS setname,o.getname,o.gettime,o.getuserId FROM Cloth AS c LEFT JOIN User u ON c.user_id=u.user_id LEFT JOIN (SELECT c.cloth_id,u.user_wc_nickname AS getname,c.cloth_order_intime As gettime,c.user_id As getuserId FROM ClothOrder AS c, User AS u WHERE c.user_id=u.user_id) AS o ON o.cloth_id = c.cloth_id LEFT JOIN ClothType As ct on ct.cloth_type_id = c.cloth_type1 where c.cloth_is_delete = 0 and getuserId = 132
--CLothInfo倒叙排列
SELECT c.cloth_id,c.cloth_intime,c.cloth_mainpic_path,c.cloth_name,ct.cloth_type_name,c.cloth_state,u.user_wc_nickname AS setname,o.getname FROM Cloth AS c LEFT JOIN User u ON c.user_id=u.user_id LEFT JOIN (SELECT c.cloth_id,u.user_wc_nickname AS getname FROM ClothOrder AS c, User AS u  WHERE c.user_id=u.user_id) AS o ON o.cloth_id = c.cloth_id LEFT JOIN ClothType As ct on ct.cloth_type_id = c.cloth_type1 where c.cloth_is_delete = 0  order by cloth_intime desc 

select c.cloth_com_intime,c.cloth_com_content,user.user_wc_nickname,cloth.cloth_name,cloth.cloth_id,c.cloth_com_id,c.cloth_com_state from ClothComment as c left join User as user on c.poor_id = user.user_id left join Cloth as cloth on c.cloth_id = cloth.cloth_id
select c.clothComIntime,comment.clothComContent,user.userWcNickname,cloth.clothName,cloth.clothId,comment.clothComId,comment.clothComState from ClothComment comment,User user,Cloth cloth where comment.poorId=user.userId and comment.clothId=cloth.clothId

select user.user_id from User as user,LovingPeople as lp where user.user_id = lp.user_id
select user.user_wc_nickname from PoorPeople as pp left join User as user on pp.user_id = user.user_id








----------------------------注释版--------------------------------------
--爱心银行
create table huituopin.Credicts(
	credicts_id int primary key auto_increment,		--ID
	user_id int,									--用户ID
	product_id int,									--物品或商品ID
	credicts_type int,								--积分类型  1：支出   0 ：收入
	credicts_img varchar(200),						--图片 （如果是物品 则统一用默认图片，如果是商品则用商品主图）
	credicts_name varchar(200),						--名称  （如果是物品 则统一用默认名称，如果是商品则用商品名称）
	credicts_changes varchar(50),					--变化的积分值
	credicts_date datetime,						    --产生日期
	credicts_remark	varchar(100),					--备注				
	credicts_is_delete bit,							--删除标记 1(true)
	credicts_c1 varchar(200),
    credicts_c2 varchar(200)
);
--爱心银行
drop table huituopin.Credicts
create table huituopin.Credicts(
	credicts_id int primary key auto_increment,
	user_id int,
	product_id int,
	credicts_type int,
	credicts_img varchar(200),
    credicts_name varchar(200),
    credicts_changes varchar(50),
    credicts_date datetime,
    credicts_remark	varchar(100),
    credicts_is_delete bit,
    credicts_c1 varchar(200),
    credicts_c2 varchar(200)
);	



--爱购 我的收藏表
create table huituopin.Collection(
	collection_id int primary key auto_increment,  	--ID
	user_id int,									--用户ID
	product_Id int,									--商品ID
	collection_date varchar(50),					--收藏时间
	collection_is_delete bit,						--删除标记  1(true): 删除，0(false):未删除
	collection_c1 varchar(200),
	collection_c2 varchar(200)
);
create table huituopin.Collection(
	collection_id int primary key auto_increment,   
	user_id int,			 
	product_Id int,									 
	collection_date varchar(50),				 
	collection_is_delete bit,					 
	collection_c1 varchar(200),
	collection_c2 varchar(200)
);




--物流工具表
create table huituopin.Logistics(
	 logistics_id int primary key auto_increment,
	 logistics_name varchar(50),
	 logistics_datatime varchar(50),
	 logistics_areas varchar(200),
	 logistics_firstcount int,
	 logistics_firstprice double,
	 logistics_lastconut int,
	 logistics_lastprice double,
	 logistics_is_delete bit,
	 logistics_c1 varchar(200),
	 logistics_c2 varchar(200)
);







--管理员表
create table huituopin.Adminuser(      				
	adminuser_id int primary key auto_increment,	--管理员ID
	adminuser_name varchar(50) not null unique,		--管理员登录名
	adminuser_pwd varchar(100) not null				--管理员密码
);

--用户表
create table huituopin.User(
	user_id int primary key auto_increment,   --用户id
	user_phone varchar(50) not null unique,   --用户电话号码
	user_pwd varchar(100) not null,	  	 	  --登录密码
	user_wc_id varchar(200) unique            --用户微信id
	user_wc_nickname varchar(200),            --昵称
	user_wc_avatar varchar(200),              --头像
	user_wc_gender bit,                       --性别，true：男，false：女
	user_type bit not null,			 		  --用户类型：true(1)：贫困人士 ，false(0)： 爱心人士
	user_signtime datetime not null,          --注册时间
	user_is_delete smallint not null,         --该记录是否删除，用户删除标记    1 ：未删除，2：拉黑，3：销户，4：删除
	user_is_online bit not null,              --改用户是否在线，true：在线，false：未上线
	user_c1 varchar(200),                     --备用字段
	user_c2 varchar(200)
);	
--用户详细表（贫困人员）
drop table huituopin.PoorPeople;
select * from PoorPeople;
create table huituopin.PoorPeople(
	poor_id int primary key auto_increment,   --贫困人士ID
	user_id int not null unique,              --对应的userID
	poor_Age varchar(20) not null,       	  --年龄
	poor_address varchar(50) not null,        --地址
	poor_detail_address varchar(100) not null,--详细地址
	poor_nation varchar(20) not null,         --民族
	poor_postal varchar(50) not null,         --邮政编码
	poor_state int not null,                  --审核状态，1：未审核，2：审核通过，3：不通过
	poor_is_delete bit not null,              --该记录是否删除，true：删除（审核失败），false：未删除
	poor_c1 varchar(200),                     --备用字段
	poor_c2 varchar(200)
);
create table huituopin.PoorPeople(
	poor_id int primary key auto_increment,   
	user_id int not null unique,              
	poor_Age varchar(20) not null,       	  
	poor_address varchar(50) not null,        
	poor_detail_address varchar(100) not null,
	poor_nation varchar(20) not null,         
	poor_postal varchar(50) not null,         
	poor_state int not null,                  
	poor_is_delete bit not null,              
	poor_c1 varchar(200),                     
	poor_c2 varchar(200)
);
insert into PoorPeople(poor_id,user_id,poor_Age,poor_address,poor_detail_address,poor_nation,poor_postal,poor_state,poor_is_delete,poor_c1,poor_c2)
values ('125',12,'1','1','12','123','1','1','123','123')




create table huituopin.PoorPeople(
	poor_id int primary key auto_increment,   --贫困人士ID
	user_id int not null unique,              --对应的userID
	poor_realname varchar(50) not null,       --真实姓名
	poor_age tinyint not null,                --年龄
	poor_school varchar(50),                  --学校
	poor_province varchar(50) not null,       --省份
	poor_city varchar(50) not null,           --市
	poor_district varchar(50) not null,       --区或县
	poor_detail_address varchar(100) not null,--街道信息
	poor_background varchar(1000) not null,   --家庭情况
	poor_state int not null,                  --审核状态，1：未审核，2：审核通过，3：不通过
	poor_is_delete bit not null,              --该记录是否删除，true：删除（审核失败），false：未删除
	poor_c1 varchar(200),                     --备用字段
	poor_c2 varchar(200)
);

--用户详细表（爱心人士）
create table huituopin.LovingPeople(
	love_id int primary key auto_increment,   --爱心人士ID
	user_id int not null unique,              --对应的userID
	love_readname varchar(50) not null,
	love_age tinyint,                         --年龄
	love_credicts smallint,                   --积分
	love_state bit not null,                  --状态，true：正常，false:禁用
	love_is_delete bit not null,              --该记录是否删除，true：删除，false：未删除
	love_c1 varchar(200),                     --备用字段
	love_c2 varchar(200)
);

--重大救助类别表
create table huituopin.ActivityCategory(                 
	act_category_id smallint primary key auto_increment,  --重大救助类别ID
	act_category_name varchar(50) not null,               --重大救助类别名
	act_category_intime datetime not null,                --记录添加时间
	act_category_is_delete bit not null,                  --该记录是否删除，true：删除，false：未删除
	act_category_c1 varchar(200),
	act_category_c2 varchar(200)
);

--重大救助项目表--version 1
create table huituopin.Activity(
	activity_id int primary key auto_increment,           --重大救助项目ID
	act_category_id smallint not null unique,             --所属类别ID
	activity_name varchar(200) not null,                  --项目名称
	activity_main_pic varchar(200) not null,              --项目主图路径
	activity_summary varchar(2000),                       --项目简介
	activity_total_fund float not null,                   --目标筹款金额
	activity_launch_time datetime not null,               --发布时间
	activity_stop_time datetime not null,                 --活动结束时间
	activity_pay_bound float,                             --支付限额
	activity_raised float not null,                       --已筹金额
	activity_suport_num int not null,                     --支持人数
	activity_state smallint not null,                     --活动状态，0：未发布，1：已发布，2：已结束
	activity_intime datetime not null,                    --该记录添加时间
	activity_is_delete bit not null,                      --该记录是否删除
	activity_c1 varchar(200),                             --备用字段
	activity_c2 varchar(200)
);
--重大救助项目表--version 2
create table huituopin.Activity(
	activity_id int primary key auto_increment,           
	activity_name varchar(200) not null,                  
	activity_main_pic varchar(200),             		  
	activity_summary varchar(2000),                      
	activity_total_fund int not null,                     
	activity_launch_time datetime not null,               
	activity_stop_time datetime not null,                
	activity_pay_bound int,                                     
	activity_raised smallint default 0,                     
	activity_suport_num smallint default 0,                  
	activity_state smallint,                  
	activity_detail_info mediumtext ,					  
	activity_intime datetime ,  
    activity_is_delete smallint  default 0,    			
	activity_c1 varchar(200),                             
	activity_c2 varchar(200)
);
--滴水之恩项目表--version 2
create table huituopin.Dsze(
	dsze_id int primary key auto_increment,           
	dsze_name varchar(200) not null,                  
	dsze_main_pic varchar(200),             		  
	dsze_summary varchar(2000),                      
	dsze_total_fund int not null,                     
	dsze_launch_time datetime not null,               
	dsze_stop_time datetime not null,                
	dsze_pay_bound int,                                     
	dsze_raised smallint default 0,                     
	dsze_suport_num smallint default 0,                  
	dsze_state smallint,                  
	dsze_detail_info mediumtext ,					  
	dsze_intime datetime ,  
    dsze_is_delete smallint  default 0,    			
	dsze_c1 varchar(200),                             
	dsze_c2 varchar(200)
);
--重大救助详情表
create table huituopin.ActivityDetailInfo(
	act_detailinfo_id int primary key auto_increment,     --重大救助详情ID
	activity_id int unique,                               --所属重大项目ID
	act_detailinfo mediumtext not null,                   --重大救助详情
	act_detailinfo_intime datetime not null,			  --该记录添加时间
	act_detailinfo_is_delete bit not null,                --该记录是否删除
	act_detailinfo_c1 varchar(200),                       --备用字段
	act_detailinfo_c2 varchar(200)
);

--重大救助评论表
create table huituopin.ActivityComment(
	act_comment_id int primary key auto_increment,        --重大救助评论ID
	activity_id int,                                      --重大救助项目ID
	user_id int,                                          --用户ID
	act_comment varchar(500),                             --评论内容
	act_support_money float not null,                     --支持金额
	act_comment_intime datetime not null,                 --评论时间
	act_comment_is_delete bit not null,                   --该记录是否删除
	act_comment_c1 varchar(200),                          --备用字段
	act_comment_c2 varchar(200)
);

--20160518
--收货地址表
create table huituopin.ShippingAddress(
	shipping_add_id int primary key auto_increment, 	   --收货地址ID
	user_id int not null,								   --用户ID
	shipping_add_name varchar(100) not null, 			   --收件人姓名
	shipping_add_phone varchar(100) not null,			   --收件人联系方式
	shipping_add_province varchar(100) not null, 		   --省
	shipping_add_city varchar(100) not null,			   --市
	shipping_add_district varchar(100) not null,	       --区
	shipping_add_detail varchar(200) not null,			   --街道信息
	shipping_add_is_main bit not null, 					   --标记该记录是否为默认收货地址
	shipping_add_is_delete bit not null,				   --该记录是否删除
	shipping_add_c1  varchar(200),						   --备用字段
	shipping_add_c2  varchar(200)	
);

--物流公司表
create table huituopin.LogisticsCompany(
	logistics_company_id tinyint primary key auto_increment,--物流公司ID
	logistics_company_name varchar(200) not null,			--物流公司名称
	logistics_company_is_delete bit not null,				--该记录是否删除
	logistics_company_c1 varchar(200),						--备用字段
	logistics_company_c2 varchar(200)
);

--衣物类别表
create table huituopin.ClothType(
	cloth_type_id tinyint primary key auto_increment,         --衣物类别ID
	cloth_type_name varchar(200) not null,				  --衣物类别名称
	cloth_type_num int,									  --对应类别数量
	cloth_type_intime datetime not null,     			  --添加时间
	cloth_type_is_delete bit not null,					  --该记录是否删除
	cloth_type_c1 varchar(200),							  --备用字段
	cloth_type_c2 varchar(200)							  
);

--衣物表 v1.0
create table huituopin.Cloth(
	cloth_id int primary key auto_increment,               --衣服ID
	cloth_name varchar(200) not null,                      --衣物名称
	cloth_type1 tinyint,                                   --衣物类别1，上衣or下装
    cloth_type2 tinyint,								   --衣物类别2，男装or女装
    cloth_type3 tinyint, 								   --衣服类别3，适合年龄阶段，
    cloth_detailinfo_id int,                               --衣物详情ID
	loving_id int,								   		   --爱心人士ID
	cloth_mainpic_path varchar(200),					   --衣物主图路径
	cloth_intime datetime not null,						   --添加事假时间
	cloth_state tinyint,								   --衣物状态，未领取，未发货，已收货，未评价
	cloth_is_delete bit not null,  						   --改记录是否删除
	cloth_c1 varchar(200),								   --备用字段
	cloth_c2 varchar(200)								                        
	
);
--衣物表 v1.1
create table huituopin.Cloth(
	cloth_id int primary key auto_increment,               --衣服ID
	cloth_name varchar(200) not null,                      --衣物名称
	cloth_type1 tinyint,                                   --衣物类别1，上衣or下装
    cloth_type2 tinyint,								   --衣物类别2，男装or女装
    cloth_type3 tinyint, 								   --衣服类别3，适合年龄阶段，
    cloth_detailinfo_id int,                               --衣物详情ID
	user_id int,								   		   --爱心人士ID
	cloth_mainpic_path varchar(200),					   --衣物主图路径
	cloth_intime datetime not null,						   --添加事假时间
	cloth_state tinyint,								   --衣物状态，未领取，未发货，已收货，未评价
	cloth_is_delete bit not null,  						   --改记录是否删除
	cloth_c1 varchar(200),								   --备用字段
	cloth_c2 varchar(200)								                        
	
);

--衣物表 v1.2
create table huituopin.Cloth(
	cloth_id int primary key auto_increment,               --衣服ID
	cloth_name varchar(200) not null,                      --衣物名称
	cloth_type1 tinyint,                                   --衣物类别1，上衣or下装
    cloth_type2 tinyint,								   --衣物类别2，男装or女装
    cloth_type3 tinyint, 								   --衣服类别3，适合年龄阶段，
    cloth_detailinfo mediumtext,                           --衣物详情ID
	user_id int,								   		   --爱心人士ID
	cloth_mainpic_path varchar(200),					   --衣物主图路径
	cloth_intime datetime not null,						   --添加事假时间
	cloth_state tinyint,								   --衣物状态，未领取，未发货，已收货，未评价，已评价
	cloth_is_delete bit not null,  						   --改记录是否删除
	cloth_c1 varchar(200),								   --备用字段
	cloth_c2 varchar(200)								                        
	
);
--衣物详情表(无效)
create table huituopin.ClothDetailInfo(
	cloth_detailinfo_id int primary key auto_increment,    --衣物详情ID
	cloth_detailinfo mediumtext not null,				   --衣物详情
	cloth_detailinfo_intime datetime not null,			   --该记录添加时间
	cloth_detailinfo_is_delete bit not null,               --该记录是否删除
	cloth_detailinfo_c1 varchar(200),                      --备用字段
	cloth_detailinfo_c2 varchar(200)
);

--衣物图片表
create table huituopin.ClothPicture(
	cloth_pic_id int primary key auto_increment,		   --衣物图片ID
	cloth_id int not null,								   --衣物ID
	cloth_pic_type bit not null,						   --图片类型，衣服or评论,0代表衣服，1代表评论
	cloth_pic_path varchar(200) not null,				   --图片路径
	cloth_pic_intime datetime not null,					   --图片添加时间
	cloth_pic_is_delete bit not null,					   --该记录是否删除
	cloth_pic_c1 varchar(200),                      	   --备用字段
	cloth_pic_c2 varchar(200)
);

--衣物订单表v1.0
create table huituopin.ClothOrder(
	clothOrder_id int primary key auto_increment,          --订单ID
	clothOrder_num varchar(200) not null,				   --订单号
	cloth_id int not null, 								   --衣物ID
	poor_id int not null,								   --贫困人士ID
	shipping_add_id int not null,						   --收货地址ID
	cloth_order_state tinyint not null,					   --订单状态，未发货，已发货，已收货，未评价
	cloth_order_intime datetime not null,				   --订单产生时间
	logistics_company_id tinyint,						   --物流公司ID
	logistics_num varchar(500),							   --快递单号
	cloth_order_is_delete bit not null,					   --该记录是否删除
	cloth_order_c1 varchar(200),                      	   --备用字段
	cloth_order_c2 varchar(200)
);
--衣物订单表v1.1
create table huituopin.ClothOrder(
	clothOrder_id int primary key auto_increment,          --订单ID
	clothOrder_num varchar(200) not null,				   --订单号
	cloth_id int not null, 								   --衣物ID
	user_id int not null,								   --用户ID
	shipping_add_id int not null,						   --收货地址ID
	cloth_order_state tinyint not null,					   --订单状态，未发货，已发货，已收货，未评价
	cloth_order_intime datetime not null,				   --订单产生时间
	logistics_company_id tinyint,						   --物流公司ID
	logistics_num varchar(500),							   --快递单号
	cloth_order_is_delete bit not null,					   --该记录是否删除
	cloth_order_c1 varchar(200),                      	   --备用字段
	cloth_order_c2 varchar(200)
);
--衣物评论表
create table huituopin.ClothComment(
	cloth_com_id int primary key auto_increment,	       --衣物评论ID
	cloth_id int not null,								   --衣物ID
	poor_id int not null,								   --评价人ID
	cloth_com_content varchar(500),						   --评论内容
	cloth_com_intime datetime not null,					   --评价时间
	cloth_com_is_delete bit not null,					   --改记录是否删除
	cloth_com_c1 varchar(200),							   --备用字段
	cloth_com_c2 varchar(200)
);
---20160604
--规则表
create table huituopin.Rule(
	rule_id int primary key auto_increment,					--规则Id
	rule_name varchar(500),								    --规则描述
	get_credict_num int,									--领取或积分数量
	rule_intime datetime,									--规则修改时间
);

-----------------爱购-------------------
--产品表
create table huituopin.Product(
	product_Id int primary key auto_increment,				--产品ID
	product_name varchar(200) not null;						--产品名称
	product_category_id tinyint not null,					--产品类别Id
	product_price float not null,							--产品单价
	product_credit int,										--产品积分
	product_mainpic_path varchar(200),						--主图路径
	product_specification_name varchar(100),				--产品规格名称
	product_specification_value varchar(100),				--产品规格值
	product_stock int not null,								--产品库存
	product_isDispaly_stock bit not null,					--是否在页面显示库存数量
	product_freight_type bit,								--运费类型，统一运费or运费模块
	product freight_same float,								--统一运费价格
	product_freight_template_id tinyint,					--运费模板Id
	product_stopbuy_num tinyint,							--没人限购数量
	product_onsale_time_type tinyint not null,				--开售时间类型
	product_onsale_timing date,								--定时开售时间，product_onsale_time_type为定时开售，则此字段有意义
	product_intime datetime not null,						--产品添加时间
	product_is_delete bit not null,							--该记录是否删除
	product_c1 varchar(200),								--备用字段
	product_c2 varchar(200)
);

--产品类别表
create table huituopin.ProductType(
	product_type_id tinyint primary key auto_increment,         --产品类别ID
	product_type_name varchar(200) not null,				  	--产品类别名称
	product_type_num int,									  	--对应类别数量
	product_type_intime datetime not null,     			  		--添加时间
	product_type_is_delete bit not null,					  	--该记录是否删除
	product_type_c1 varchar(200),							  	--备用字段
	product_type_c2 varchar(200)							  
);
--产品图片表
create table huituopin.ProductPicture(
	product_pic_id int primary key auto_increment,		   --产品图片ID
	product_id int not null,							   --衣物ID,如果type=评论，则此字段存的是订单号
	product_pic_type bit not null,						   --图片类型，衣服or评论,0代表衣服，1代表评论
	product_pic_path varchar(200) not null,				   --图片路径
	product_pic_intime datetime not null,				   --图片添加时间
	product_pic_is_delete bit not null,					   --该记录是否删除
);

--产品订单表
create table huituopin.ProductOrder(
	productOrder_id int primary key auto_increment,        --订单ID
	productOrder_num varchar(200) not null,				   --订单号
	user_id int not null,								   --用户ID
	products_num tinyint not null,						   --该订单包含的产品总数量
	product_order_AllCount float,  						   --该订单总金额
	shipping_add_id int not null,						   --收货地址ID
	product_order_state tinyint not null,				   --订单状态
	product_order_intime datetime not null,                --下订单时间
	product_order_isNeedLogistics boolean,				   --是否需要物流
	logistics_company_id tinyint,						   --物流公司ID
	logistics_num varchar(500),							   --快递单号	   
	product_order_is_delete bit not null,				   --该订单是否删除		   
);

--订单详情表（每个订单包含的产品）
create table huituopin.ProductOrderDetail(
	po_detail_id int primary key auto_increment,           --订单详情ID
	po_num varchar(200),								   --订单号
	product_id int,										   --产品ID
	product_num tinyint,							       --产品数量
	product_is_comment bit, 							   --该订单中的该产品是否已经评价
	po_detail_is_delete bit,							   --该记录是否删除
);

--订单评论表
create table huituopin.ProductComment(
	product_com_id int primary key auto_increment,	       --评论ID
	product_id int not null,							   --产品ID
	user_id int not null,								   --用户ID
	product_com_content varchar(500),					   --评价内容
	product_com_intime datetime not null,					   --评价时间
	product_com_is_delete bit not null,					   --该评价是否删除
);


-------------------------------------执行代码----------------------------------------

--管理员表
create table huituopin.Adminuser(      				
	adminuser_id int primary key auto_increment,
	adminuser_name varchar(50) not null unique,
	adminuser_pwd varchar(100) not null			
);


--用户表
create table huituopin.User(
	user_id int primary key auto_increment,   
	user_phone varchar(50) not null unique,          
	user_pwd varchar(100) not null,	  	 	  
	user_wc_id varchar(200) unique,                  
	user_wc_nickname varchar(200),            
	user_wc_avatar varchar(200),             
	user_wc_gender bit,                       
	user_type bit not null,			 		 
	user_signtime datetime not null,          
	user_is_delete bit not null,             
	user_is_online bit not null,              
	user_c1 varchar(200),                     
	user_c2 varchar(200)
);	

--用户详细表（贫困人员）
create table huituopin.PoorPeople(
	poor_id int primary key auto_increment,   
	user_id int not null unique,             
	poor_birthday varchar(20) not null,       
	poor_address varchar(50) not null,        
	poor_detail_address varchar(100) not null,
	poor_nation varchar(20) not null,         
	poor_postal varchar(50) not null,          
	poor_state int not null,                   
	poor_is_delete bit not null,              
	poor_c1 varchar(200),                      
	poor_c2 varchar(200)
);

--用户详细表（爱心人士）
create table huituopin.LovingPeople(
	love_id int primary key auto_increment,  
	user_id int not null unique,    
	love_readname varchar(50) not null,                
	love_age tinyint,                         
	love_credicts smallint,                  
	love_state bit not null,                
	love_is_delete bit not null,              
	love_c1 varchar(200),                     
	love_c2 varchar(200)
);

--重大救助类别表
create table huituopin.ActivityCategory(
	act_category_id smallint primary key auto_increment,
	act_category_name varchar(50) not null,
	act_category_intime datetime not null,
	act_category_is_delete bit not null,
	act_category_c1 varchar(200),
	act_category_c2 varchar(200)
);

--重大救助项目表
create table huituopin.Activity(
	activity_id int primary key auto_increment,
	act_category_id smallint not null unique,
	activity_name varchar(200) not null,
	activity_main_pic varchar(200) not null,
	activity_summary varchar(2000),
	activity_total_fund float not null,
	activity_launch_time datetime not null,
	activity_stop_time datetime not null,
	activity_pay_bound float,
	activity_raised float not null,
	activity_suport_num int not null,
	activity_state smallint not null,
	activity_intime datetime not null,
	activity_is_delete bit not null,
	activity_c1 varchar(200),
	activity_c2 varchar(200)
);

--重大救助详情表
create table huituopin.ActivityDetailInfo(
	act_detailinfo_id int primary key auto_increment,
	activity_id int unique,
	act_detailinfo mediumtext not null,
	act_detailinfo_intime datetime not null,
	act_detailinfo_is_delete bit not null,
	act_detailinfo_c1 varchar(200),
	act_detailinfo_c2 varchar(200)
);

--重大救助评论表
create table huituopin.ActivityComment(
	act_comment_id int primary key auto_increment,
	activity_id int,
	user_id int,
	act_comment varchar(500),
	act_support_money float not null,
	act_comment_intime datetime not null,
	act_comment_is_delete bit not null,
	act_comment_c1 varchar(200),
	act_comment_c2 varchar(200)
);

--收货地址表
create table huituopin.ShippingAddress(
	shipping_add_id int primary key auto_increment, 	   
	user_id int not null,								  
	shipping_add_name varchar(100) not null, 			   
	shipping_add_phone varchar(100) not null,			 
	shipping_add_province varchar(100) not null, 		   
	shipping_add_city varchar(100) not null,			  
	shipping_add_district varchar(100) not null,	      
	shipping_add_detail varchar(200) not null,	
	shipping_add_is_main bit not null,		   
	shipping_add_is_delete bit not null,				   
	shipping_add_c1  varchar(200),						   
	shipping_add_c2  varchar(200)	
);

--物流公司表
create table huituopin.LogisticsCompany(
	logistics_company_id tinyint primary key auto_increment,
	logistics_company_name varchar(200) not null,			
	logistics_company_is_delete bit not null,				
	logistics_company_c1 varchar(200),						
	logistics_company_c2 varchar(200)
);

--衣物类别表
create table huituopin.ClothType(
	cloth_type_id tinyint primary key auto_increment,         
	cloth_type_name varchar(200) not null,				  
	cloth_type_num int,									  
	cloth_type_intime datetime not null,     			  
	cloth_type_is_delete bit not null,
	cloth_type_c1 varchar(200),							  
	cloth_type_c2 varchar(200)							  
);

--衣服表
create table huituopin.Cloth(
	cloth_id int primary key auto_increment,               
	cloth_name varchar(200) not null,                      
	cloth_type1 tinyint,                                   
    cloth_type2 tinyint,								   
    cloth_type3 tinyint, 								   
    cloth_detailinfo mediumtext,                        
	user_id int,	
	cloth_mainpic_path varchar(200),							   
	cloth_intime datetime not null,						   
	cloth_state tinyint,								   
	cloth_is_delete bit not null,  						   
	cloth_c1 varchar(200),								   
	cloth_c2 varchar(200)								                        
);

--衣物详情表
create table huituopin.ClothDetailInfo(
	cloth_detailinfo_id int primary key auto_increment,    
	cloth_detailinfo mediumtext not null,				  
	cloth_detailinfo_intime datetime not null,			   
	cloth_detailinfo_is_delete bit not null,             
	cloth_detailinfo_c1 varchar(200),                      
	cloth_detailinfo_c2 varchar(200)
);

--衣物图片表
create table huituopin.ClothPicture(
	cloth_pic_id int primary key auto_increment,		   
	cloth_id int not null,		
	cloth_pic_type bit not null,						   
	cloth_pic_path varchar(200) not null,				   
	cloth_pic_intime datetime not null,					  
	cloth_pic_is_delete bit not null,					   
	cloth_pic_c1 varchar(200),                      	   
	cloth_pic_c2 varchar(200)
);

--衣物订单表
create table huituopin.ClothOrder(
	clothOrder_id int primary key auto_increment,          
	clothOrder_num varchar(200) not null,				   
	cloth_id int not null, 								   
	user_id int not null,								   
	shipping_add_id int not null,						   
	cloth_order_state tinyint not null,					   
	cloth_order_intime datetime not null,  
	logistics_company_id tinyint,						   
	logistics_num varchar(500),							   				   
	cloth_order_is_delete bit not null,					   
	cloth_order_c1 varchar(200),                      	   
	cloth_order_c2 varchar(200)
);

--衣物评论表
create table huituopin.ClothComment(
	cloth_com_id int primary key auto_increment,	       
	cloth_id int not null,								   
	poor_id int not null,								   
	cloth_com_content varchar(500),						   
	cloth_com_intime datetime not null,					   
	cloth_com_is_delete bit not null,					   
	cloth_com_c1 varchar(200),							   
	cloth_com_c2 varchar(200)
);
create table huituopin.Rule(
	rule_id int primary key auto_increment,					
	rule_name varchar(500),								    
	get_credict_num int,									
	rule_intime datetime									
);

-----------------爱购-------------------
--产品表
create table huituopin.Product(
	product_Id int primary key auto_increment,				
	product_name varchar(200) not null,					
	product_category_id tinyint not null,					
	product_price float not null,							
	product_credit int,										
	product_mainpic_path varchar(200),						
	product_specification_name varchar(100),				
	product_specification_value varchar(100),				
	product_stock int not null,								
	product_isDispaly_stock bit not null,					
	product_freight_type bit,								
	product_freight_same float,								
	product_freight_template_id tinyint,					
	product_stopbuy_num tinyint,							
	product_onsale_time_type tinyint not null,				
	product_onsale_timing date,								
	product_intime datetime not null,
	product_is_delete bit not null,						
	product_c1 varchar(200),								
	product_c2 varchar(200)
);

--产品类别表
create table huituopin.ProductType(
	product_type_id tinyint primary key auto_increment,         
	product_type_name varchar(200) not null,				  	
	product_type_num int,									  	
	product_type_intime datetime not null,     			  		
	product_type_is_delete bit not null,					  	
	product_type_c1 varchar(200),							  	
	product_type_c2 varchar(200)							  
);
--20160607
--产品图片表
create table huituopin.ProductPicture(
	product_pic_id int primary key auto_increment,		   
	product_id int not null,							   
	product_pic_type bit not null,						   
	product_pic_path varchar(200) not null,				   
	product_pic_intime datetime not null,				   
	product_pic_is_delete bit not null					   
);

--产品订单表
create table huituopin.ProductOrder(
	productOrder_id int primary key auto_increment,        
	productOrder_num varchar(200) not null,				   
	user_id int not null,								   
	products_num tinyint not null,						   
	product_order_AllCount float,  						   
	shipping_add_id int not null,						   
	product_order_state tinyint not null,				   
	product_order_intime datetime not null,                
	product_order_isNeedLogistics boolean,				   
	logistics_company_id tinyint,						   
	logistics_num varchar(500),							   	   
	product_order_is_delete bit not null				   		   
);

--订单详情表（每个订单包含的产品）
create table huituopin.ProductOrderDetail(
	po_detail_id int primary key auto_increment,          
	po_num varchar(200),								   
	product_id int,										   
	product_num tinyint,							       
	product_is_comment bit, 							   
	po_detail_is_delete bit							   
);

--产品评论表
create table huituopin.ProductComment(
	product_com_id int primary key auto_increment,	       
	product_id int not null,							   
	user_id int not null,								   
	product_com_content varchar(500),					   
	product_com_intime datetime not null,					   
	product_com_is_delete bit not null					   
);
----
/*用户支持项目金额*/
create table huituopin.ActivitySupport(
	activity_sup_id int primary key auto_increment, //支持id
	activity_id int not null,						//支持项目id
	user_id int not null,							//支持者id
	act_sup_money float not null,					//支持金额
	act_sup_comment varchar(500),					//支持时，评论
	act_sup_time datetime not null,					//支持时间
	act_sup_is_del int not null default '0',		//是否删除
	act_sup_c1 varchar(200),						
	act_sup_c2 varchar(200)
);




