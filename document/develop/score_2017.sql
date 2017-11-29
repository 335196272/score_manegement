/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/11/29 14:54:34                          */
/*==============================================================*/


drop table if exists classes;

drop table if exists exam;

drop table if exists score;

drop table if exists student;

drop table if exists subject;

/*==============================================================*/
/* Table: classes                                               */
/*==============================================================*/
create table classes
(
   classes_id           int(11) not null auto_increment comment '�༶ID',
   name                 varchar(20) comment '�༶���ƣ����磺һ�꼶1�ࣩ',
   create_by            int(11) comment '������',
   create_date          datetime comment '����ʱ��',
   update_by            int(11) comment '������',
   update_date          datetime comment '����ʱ��',
   primary key (classes_id)
);

alter table classes comment '�༶��';

/*==============================================================*/
/* Table: exam                                                  */
/*==============================================================*/
create table exam
(
   exam_id              int(11) not null auto_increment comment '����ID',
   name                 varchar(20) comment '�������ƣ����磺2017��10�·��¿���',
   exam_time            datetime comment '����ʱ��',
   create_by            int(11) comment '������',
   create_date          datetime comment '����ʱ��',
   update_by            int(11) comment '������',
   update_date          datetime comment '����ʱ��',
   primary key (exam_id)
);

alter table exam comment '���Ա�';

/*==============================================================*/
/* Table: score                                                 */
/*==============================================================*/
create table score
(
   score_id             int(11) not null auto_increment comment '�ɼ�ID',
   student_no           int(11) comment '����',
   student_name         varchar(20) comment 'ѧ������',
   classes_id           int(11) not null comment '�༶ID',
   exam_id              int(11) comment '����ʱ��',
   score                decimal(9,1) not null comment '�ɼ�',
   create_by            int(11) comment '������',
   create_date          datetime comment '����ʱ��',
   update_by            int(11) comment '������',
   update_date          datetime comment '����ʱ��',
   primary key (score_id)
);

alter table score comment '�ɼ���';

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   student_id           int(11) not null auto_increment comment 'ѧ��ID',
   student_number       int(11) comment 'ѧ��',
   name                 varchar(20) comment 'ѧ������',
   sex                  int(4) comment '�Ա�0=Ů��1=��',
   entrance_time        datetime comment '��ѧʱ�䣨�������ֲ�ͬ���ѧ����ֻ������ݣ�',
   create_by            int(11) comment '������',
   create_date          datetime comment '����ʱ��',
   update_by            int(11) comment '������',
   update_date          datetime comment '����ʱ��',
   primary key (student_id)
);

alter table student comment 'ѧ����';

/*==============================================================*/
/* Table: subject                                               */
/*==============================================================*/
create table subject
(
   subject_id           int(11) not null auto_increment comment '��ĿID',
   name                 varchar(20) comment '��Ŀ����',
   seq                  int(4) comment '����ֵ',
   create_by            int(11) comment '������',
   create_date          datetime comment '����ʱ��',
   update_by            int(11) comment '������',
   update_date          datetime comment '����ʱ��',
   primary key (subject_id)
);

alter table subject comment '��Ŀ��';

