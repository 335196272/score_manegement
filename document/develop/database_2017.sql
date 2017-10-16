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
   log_id               int(11) not null auto_increment comment '��־ID',
   name                 varchar(50) comment '�û���',
   action               varchar(20) comment '�������������޸ģ�ɾ��������״̬��',
   detail               text comment '��־����',
   ip                   varchar(100) comment 'IP��ַ',
   create_date          datetime comment '����ʱ��',
   primary key (log_id)
);

alter table log comment 'ϵͳ��־��';

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   permission_id        int(11) not null auto_increment comment 'Ȩ��ID',
   permission_name      varchar(32) not null comment 'Ȩ����',
   permission_sign      varchar(128) not null comment 'Ȩ�ޱ�ʶ',
   description          varchar(256) not null comment 'Ȩ��������UI������ʾʹ�ã�',
   primary key (permission_id)
);

alter table permission comment 'Ȩ�ޱ�';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   role_id              int(11) not null auto_increment comment '��ɫID',
   role_name            varchar(32) not null comment '��ɫ��',
   role_sign            varchar(128) not null comment '��ɫ��ʶ���������ж�ʹ��,��"admin"��',
   description          varchar(256) not null comment '��ɫ������UI������ʾʹ�ã�',
   primary key (role_id)
);

alter table role comment '��ɫ��';

/*==============================================================*/
/* Table: role_permission                                       */
/*==============================================================*/
create table role_permission
(
   role_permission_id   int(11) not null auto_increment comment '��ɫȨ��ID',
   role_id              int(11) not null comment '��ɫID',
   permission_id        int(11) not null comment 'Ȩ��ID',
   primary key (role_permission_id)
);

alter table role_permission comment '��ɫȨ�ޱ�';

/*==============================================================*/
/* Table: setting                                               */
/*==============================================================*/
create table setting
(
   setting_id           int(11) not null auto_increment comment 'ϵͳ����Id',
   name                 varchar(100) comment 'ϵͳ������',
   value                varchar(255) comment '����ֵ',
   description          varchar(255) comment '��������',
   create_by            int(11) comment '������',
   create_date          datetime comment '����ʱ��',
   update_by            int(11) comment '������',
   update_date          datetime comment '����ʱ��',
   primary key (setting_id)
);

alter table setting comment 'ϵͳ���ñ�';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              int(11) not null auto_increment comment '�û�ID',
   user_name            varchar(50) not null comment '�û���',
   phone_number         varchar(20) not null comment '�ֻ���',
   email                varchar(50) not null comment '����',
   password             varchar(64) not null comment '����',
   pic_url              varchar(255) comment 'ͷ��',
   status               int(4) comment '״̬��-1=���ã�0=�ݸ壬1=����',
   create_date          datetime comment 'ע��ʱ��',
   update_date          datetime comment '����ʱ��',
   last_login_date      datetime comment '�����¼ʱ��',
   primary key (user_id)
);

alter table user comment '�û���';

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   user_role_id         int(11) not null auto_increment comment '�û���ɫID',
   user_id              int(11) not null comment '�û�ID',
   role_id              int(11) not null comment '��ɫID',
   primary key (user_role_id)
);

alter table user_role comment '�û���ɫ��';

