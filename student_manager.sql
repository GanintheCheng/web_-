create table admin
(
    id       int auto_increment comment '管理员编号'
        primary key,
    name     varchar(10) default 'admin' not null comment '姓名',
    account  varchar(50)                 not null comment '账号',
    password varchar(20)                 not null comment '密码',
    constraint account
        unique (account)
);

create table class
(
    id   int         not null comment '班级主键'
        primary key,
    name varchar(10) not null comment '班级名称'
);

create table student
(
    id          varchar(50)                                 not null comment '学号'
        primary key,
    account     varchar(50)                                 not null comment '账号',
    password    varchar(12) default '123456'                not null comment '密码',
    name        varchar(20)                                 not null comment '姓名',
    sex         varchar(4)                                  not null comment '性别',
    school_date varchar(20)                                 not null comment '入学日期',
    major       varchar(10)                                 not null comment '专业',
    email       varchar(20)                                 null comment '邮箱',
    img         varchar(50) default 'stu/userImg/默认.jpeg' null comment '头像',
    class_id    int                                         not null comment '班级id',
    constraint fk_student_class
        foreign key (class_id) references class (id)
);

create table score
(
    id      varchar(50)            not null comment '外键,对应学生学号'
        primary key,
    dat     varchar(10) default '' null comment '数据库',
    android varchar(10) default '' null comment '安卓',
    jsp     varchar(10) default '' null comment 'javaweb',
    constraint id
        foreign key (id) references student (id)
);

create table teacher
(
    id       varchar(50)                                 not null comment '教师编号'
        primary key,
    account  varchar(50)                                 null comment '账号',
    password varchar(50)                                 not null comment '密码',
    name     varchar(50) default ''                      null comment '姓名',
    sex      varchar(50) default ''                      null comment '性别',
    email    varchar(50)                                 not null comment '邮箱',
    img      varchar(50) default 'stu/userImg/默认.jpeg' null comment '头像'
);

create table teacher_class
(
    teacher_id varchar(50) null,
    class_id   int         not null,
    constraint teacher_class_class_id_fk
        foreign key (class_id) references class (id),
    constraint teacher_class_teacher_id_fk
        foreign key (teacher_id) references teacher (id)
);

