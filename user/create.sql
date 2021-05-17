create table tb_role (id bigint not null auto_increment, role_name varchar(255), primary key (id)) engine=InnoDB
create table tb_user (id bigint not null auto_increment, email varchar(130) not null, name varchar(200) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_user_role (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id)) engine=InnoDB
alter table tb_user add constraint UK_4vih17mube9j7cqyjlfbcrk4m unique (email)
alter table tb_user_role add constraint FKea2ootw6b6bb0xt3ptl28bymv foreign key (role_id) references tb_role (id)
alter table tb_user_role add constraint FK7vn3h53d0tqdimm8cp45gc0kl foreign key (user_id) references tb_user (id)
create table tb_role (id bigint not null auto_increment, role_name varchar(255), primary key (id)) engine=InnoDB
create table tb_user (id bigint not null auto_increment, email varchar(130) not null, name varchar(200) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_user_role (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id)) engine=InnoDB
alter table tb_user add constraint UK_4vih17mube9j7cqyjlfbcrk4m unique (email)
alter table tb_user_role add constraint FKea2ootw6b6bb0xt3ptl28bymv foreign key (role_id) references tb_role (id)
alter table tb_user_role add constraint FK7vn3h53d0tqdimm8cp45gc0kl foreign key (user_id) references tb_user (id)
create table tb_role (id bigint not null auto_increment, role_name varchar(255), primary key (id)) engine=InnoDB
create table tb_user (id bigint not null auto_increment, email varchar(130) not null, name varchar(200) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_user_role (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id)) engine=InnoDB
alter table tb_user add constraint UK_4vih17mube9j7cqyjlfbcrk4m unique (email)
alter table tb_user_role add constraint FKea2ootw6b6bb0xt3ptl28bymv foreign key (role_id) references tb_role (id)
alter table tb_user_role add constraint FK7vn3h53d0tqdimm8cp45gc0kl foreign key (user_id) references tb_user (id)
create table tb_role (id bigint not null auto_increment, role_name varchar(255), primary key (id)) engine=InnoDB
create table tb_user (id bigint not null auto_increment, email varchar(130) not null, name varchar(200) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_user_role (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id)) engine=InnoDB
alter table tb_user add constraint UK_4vih17mube9j7cqyjlfbcrk4m unique (email)
alter table tb_user_role add constraint FKea2ootw6b6bb0xt3ptl28bymv foreign key (role_id) references tb_role (id)
alter table tb_user_role add constraint FK7vn3h53d0tqdimm8cp45gc0kl foreign key (user_id) references tb_user (id)
