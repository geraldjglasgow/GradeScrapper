use school;

create table assignment
(
	assignment_id int auto_increment
		primary key,
	due_date date null,
	score double null,
	worth double null,
	percentage double null,
	grade varchar(5) null,
	graded tinyint(1) null,
	crs_name varchar(30) null,
	name varchar(75) null,
	category varchar(50) null,
	constraint assignment_course_course_name_fk
		foreign key (crs_name) references course (course_name)
);

create index assignment_assignment_id_index
	on assignment (assignment_id);