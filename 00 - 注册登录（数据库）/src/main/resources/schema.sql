CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL COMMENT '账号',
  password varchar(255) DEFAULT '123456' COMMENT '密码',
  authorities varchar(255) DEFAULT 'USER' NOT NULL comment '权限',
  PRIMARY KEY (id)
);

insert into user(username, authorities) values ( 'Jon', 'USER' );
insert into user(username, authorities) values ( 'admin', 'USER ADMIN' );
insert into user(username, authorities) values ( 'Bob', 'USER VIP' );
insert into user(username, authorities) values ( 'MieBa', 'USER VIP SUPERVIP' );