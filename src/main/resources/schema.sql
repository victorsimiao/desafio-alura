DROP TABLE IF EXISTS Enrollment;
DROP TABLE IF EXISTS User;

CREATE TABLE User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS Course;

CREATE TABLE Course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(20) NOT NULL UNIQUE,
    description VARCHAR(500)
);


CREATE TABLE Enrollment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id bigint not null,
    user_id bigint not null,
    created_at datetime NOT NULL,

   constraint fk_enrrol_course_id foreign key(course_id) references Course(id),
   constraint fk_enrrol_user_id foreign key(user_id) references `User`(id)

);