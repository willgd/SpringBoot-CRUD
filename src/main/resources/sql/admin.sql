create table admin
(
	admin_id int auto_increment
		primary key,
	user_id int not null,
	create_time datetime null,
	update_time datetime null
);

