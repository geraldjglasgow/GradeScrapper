use school;

create table course
(
	course_name varchar(25) not null
		primary key,
	absences int null,
	tardies int null
);

create index course_course_name_index
	on course (course_name);