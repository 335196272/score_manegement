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
   classes_id           int(11) not null auto_increment comment '班级ID',
   name                 varchar(20) comment '班级名称（例如：一年级1班）',
   create_by            int(11) comment '创建者',
   create_date          datetime comment '创建时间',
   update_by            int(11) comment '更新者',
   update_date          datetime comment '更新时间',
   primary key (classes_id)
);

alter table classes comment '班级表';

/*==============================================================*/
/* Table: exam                                                  */
/*==============================================================*/
create table exam
(
   exam_id              int(11) not null auto_increment comment '考试ID',
   name                 varchar(20) comment '考试名称（例如：2017年10月份月考）',
   exam_time            datetime comment '考试时间',
   create_by            int(11) comment '创建者',
   create_date          datetime comment '创建时间',
   update_by            int(11) comment '更新者',
   update_date          datetime comment '更新时间',
   primary key (exam_id)
);

alter table exam comment '考试表';

/*==============================================================*/
/* Table: score                                                 */
/*==============================================================*/
create table score
(
   score_id             int(11) not null auto_increment comment '成绩ID',
   student_no           int(11) comment '座号',
   student_name         varchar(20) comment '学生名字',
   classes_id           int(11) not null comment '班级ID',
   exam_id              int(11) comment '考试时间',
   score                decimal(9,1) not null comment '成绩',
   create_by            int(11) comment '创建者',
   create_date          datetime comment '创建时间',
   update_by            int(11) comment '更新者',
   update_date          datetime comment '更新时间',
   primary key (score_id)
);

alter table score comment '成绩表';

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   student_id           int(11) not null auto_increment comment '学生ID',
   student_number       int(11) comment '学号',
   name                 varchar(20) comment '学生姓名',
   sex                  int(4) comment '性别：0=女，1=男',
   entrance_time        datetime comment '入学时间（用于区分不同届的学生，只保存年份）',
   create_by            int(11) comment '创建者',
   create_date          datetime comment '创建时间',
   update_by            int(11) comment '更新者',
   update_date          datetime comment '更新时间',
   primary key (student_id)
);

alter table student comment '学生表';

/*==============================================================*/
/* Table: subject                                               */
/*==============================================================*/
create table subject
(
   subject_id           int(11) not null auto_increment comment '科目ID',
   name                 varchar(20) comment '科目名称',
   seq                  int(4) comment '排序值',
   create_by            int(11) comment '创建者',
   create_date          datetime comment '创建时间',
   update_by            int(11) comment '更新者',
   update_date          datetime comment '更新时间',
   primary key (subject_id)
);

alter table subject comment '科目表';

