DROP TABLE IF EXISTS department;
CREATE TABLE department(
  id int(11) NOT NULL AUTO_INCREMENT,
  departmentName varchar(255) DEFAULT NULL,
  constraint department_id_pk PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;