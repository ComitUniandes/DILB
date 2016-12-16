# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table regla (
  id                            bigint not null,
  pagina_fuente                 varchar(255),
  pagina_destino                varchar(255),
  constraint pk_regla primary key (id)
);
create sequence regla_seq;

create table urldestino (
  id                            bigint not null,
  tag_name                      varchar(255),
  link                          varchar(255),
  action                        varchar(255),
  constraint pk_urldestino primary key (id)
);
create sequence urldestino_seq;


# --- !Downs

drop table if exists regla cascade;
drop sequence if exists regla_seq;

drop table if exists urldestino cascade;
drop sequence if exists urldestino_seq;

