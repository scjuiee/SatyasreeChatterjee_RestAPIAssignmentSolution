/*CREATE TABLE students (
 id INT NOT NULL AUTO_INCREMENT,
 first_name VARCHAR(25),
 last_name VARCHAR(25),
 department VARCHAR(25),
 country VARCHAR(25),
 PRIMARY KEY(id)
 );
 
 CREATE TABLE roles(
 role_id INT(11) NOT NULL AUTO_INCREMENT,
 name VARCHAR(25),
 PRIMARY KEY(role_id)
 );
 
 CREATE TABLE users(
 user_id INT NOT NULL AUTO_INCREMENT,
 username VARCHAR(25),
 password  VARCHAR(100),
 PRIMARY KEY (user_id)
 );
 
 CREATE TABLE users_roles (
  user_id int NOT NULL,
  role_id int(11) NOT NULL,
  KEY user_fk_idx (user_id),
  KEY role_fk_idx (role_id),
  CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES roles (role_id),
  CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES `users` (user_id)
);*/

---Queries to insert data in roles table
--INSERT INTO roles(name) values('USER');
--INSERT INTO roles(name) values('ADMIN');
 
 -----Queries to insert data in students table
 --INSERT INTO students(first_name, last_name, department, country) values('Satyasree','Chatterjee', 'B.Tech', 'India');
 --INSERT INTO students(first_name, last_name, department, country) values('Nandeeta','Chatterjee', 'B.Tech', 'India');
 --INSERT INTO students(first_name, last_name, department, country) values('Sabyasachi','Chatterjee', 'B.Tech', 'India');
 --INSERT INTO students(first_name, last_name, department, country) values('Anindita','Chatterjee', 'B.Com', 'India');
 --INSERT INTO students(first_name, last_name, department, country) values('Sarbani','Chatterjee', 'B.A', 'India');
 
 ------Queries to insert data in users table
 INSERT INTO users(username,password) values('ELEVATED','$2a$10$2VGUqSgeLF0/Pc3In1gk2OExYisZ.N1oZYE2SVbwqMRr8cYov2eU.');
 --INSERT INTO users(username,password) values('ADMIN','$2a$10$ZSJJqaJ5miTVCAhHyVxc3.PzF4PHZoky5MJGY1N6MztJ7LsYOs46m');
 
 ---Actual passwords for USER -- user123 and ADMIN ----admin123
 
 -----Queries to insert data in users_role table
 --INSERT INTO users_roles (user_id, role_id) VALUES (1, 1); 
--INSERT INTO users_roles (user_id, role_id) VALUES (2, 2); 
--INSERT INTO users_roles (user_id, role_id) VALUES (2, 1)