# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table deposit (
  somekey                   integer not null,
  value                     double,
  constraint pk_deposit primary key (somekey))
;

create table user (
  email                     varchar(255) not null,
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create sequence deposit_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists deposit;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists deposit_seq;

drop sequence if exists user_seq;

