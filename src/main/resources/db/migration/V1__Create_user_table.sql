create table user
(
	id int(100) not null auto_increment,
	name varchar(255) null,
	accountId varchar(255) null,
	token varchar(255) null,
	gmtCreate BIGINT(255) null,
	gmtModified BIGINT(255) null,
	constraint user_pk
		primary key (id)
);

