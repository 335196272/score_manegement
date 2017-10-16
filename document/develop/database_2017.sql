/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/1 17:14:53                            */
/*==============================================================*/


drop table if exists log;

drop table if exists permission;

drop table if exists role;

drop table if exists role_permission;

drop table if exists setting;

drop table if exists user;

drop table if exists user_role;

/*==============================================================*/
/* Table: log                                                   */
/*==============================================================*/
create table log
(
   log_id               int(11) not null auto_increment comment '日志ID',
   name                 varchar(50) comment '用户名',
   action               varchar(20) comment '操作：创建，修改，删除，更新状态等',
   detail               text comment '日志详情',
   ip                   varchar(100) comment 'IP地址',
   create_date          datetime comment '操作时间',
   primary key (log_id)
);

alter table log comment '系统日志表';

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   permission_id        int(11) not null auto_increment comment '权限ID',
   permission_name      varchar(32) not null comment '权限名',
   permission_sign      varchar(128) not null comment '权限标识',
   description          varchar(256) not null comment '权限描述（UI界面显示使用）',
   primary key (permission_id)
);

alter table permission comment '权限表';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   role_id              int(11) not null auto_increment comment '角色ID',
   role_name            varchar(32) not null comment '角色名',
   role_sign            varchar(128) not null comment '角色标识（程序中判断使用,如"admin"）',
   description          varchar(256) not null comment '角色描述（UI界面显示使用）',
   primary key (role_id)
);

alter table role comment '角色表';

/*==============================================================*/
/* Table: role_permission                                       */
/*==============================================================*/
create table role_permission
(
   role_permission_id   int(11) not null auto_increment comment '角色权限ID',
   role_id              int(11) not null comment '角色ID',
   permission_id        int(11) not null comment '权限ID',
   primary key (role_permission_id)
);

alter table role_permission comment '角色权限表';

/*==============================================================*/
/* Table: setting                                               */
/*==============================================================*/
create table setting
(
   setting_id           int(11) not null auto_increment comment '系统配置Id',
   name                 varchar(100) comment '系统配置名',
   value                varchar(255) comment '配置值',
   description          varchar(255) comment '配置描述',
   create_by            int(11) comment '创建者',
   create_date          datetime comment '创建时间',
   update_by            int(11) comment '更新者',
   update_date          datetime comment '更新时间',
   primary key (setting_id)
);

alter table setting comment '系统配置表';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              int(11) not null auto_increment comment '用户ID',
   user_name            varchar(50) not null comment '用户名',
   phone_number         varchar(20) not null comment '手机号',
   email                varchar(50) not null comment '邮箱',
   password             varchar(64) not null comment '密码',
   pic_url              varchar(255) comment '头像',
   status               int(4) comment '状态：-1=禁用，0=草稿，1=启用',
   create_date          datetime comment '注册时间',
   update_date          datetime comment '更新时间',
   last_login_date      datetime comment '最近登录时间',
   primary key (user_id)
);

alter table user comment '用户表';

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   user_role_id         int(11) not null auto_increment comment '用户角色ID',
   user_id              int(11) not null comment '用户ID',
   role_id              int(11) not null comment '角色ID',
   primary key (user_role_id)
);

alter table user_role comment '用户角色表';

