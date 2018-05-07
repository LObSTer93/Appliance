drop table if exists status;

create table status (
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(50) not null,
  state varchar(50) not null
);