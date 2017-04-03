# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table book_issued (
  id                        serial not null,
  date_of_submission        timestamp,
  date_of_issue             timestamp,
  books_id                  integer,
  student_id                integer,
  library_admin_id          integer,
  constraint pk_book_issued primary key (id))
;

create table books (
  id                        serial not null,
  isbn                      integer,
  quantity                  integer,
  name                      varchar(255),
  author                    varchar(255),
  domain                    varchar(255),
  is_issued                 boolean,
  availability              boolean,
  constraint pk_books primary key (id))
;

create table fine (
  id                        serial not null,
  is_paid                   boolean,
  days                      integer,
  amount                    integer,
  books_id                  integer,
  student_id                integer,
  constraint pk_fine primary key (id))
;

create table library_admin (
  id                        serial not null,
  email                     varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_library_admin primary key (id))
;

create table session (
  id                        serial not null,
  session_id                varchar(255),
  is_active                 boolean,
  user_id                   integer,
  created_on                timestamp not null,
  updated_on                timestamp not null,
  constraint pk_session primary key (id))
;

create table student (
  id                        serial not null,
  semester                  integer,
  year                      integer,
  student_name              varchar(255),
  roll_number               varchar(255),
  student_email             varchar(255),
  password                  varchar(255),
  branch                    varchar(255),
  created_on                timestamp not null,
  constraint pk_student primary key (id))
;

alter table book_issued add constraint fk_book_issued_books_1 foreign key (books_id) references books (id);
create index ix_book_issued_books_1 on book_issued (books_id);
alter table book_issued add constraint fk_book_issued_student_2 foreign key (student_id) references student (id);
create index ix_book_issued_student_2 on book_issued (student_id);
alter table book_issued add constraint fk_book_issued_libraryAdmin_3 foreign key (library_admin_id) references library_admin (id);
create index ix_book_issued_libraryAdmin_3 on book_issued (library_admin_id);
alter table fine add constraint fk_fine_books_4 foreign key (books_id) references books (id);
create index ix_fine_books_4 on fine (books_id);
alter table fine add constraint fk_fine_student_5 foreign key (student_id) references student (id);
create index ix_fine_student_5 on fine (student_id);



# --- !Downs

drop table if exists book_issued cascade;

drop table if exists books cascade;

drop table if exists fine cascade;

drop table if exists library_admin cascade;

drop table if exists session cascade;

drop table if exists student cascade;

