## Spring Security System

참조: https://jooky.tistory.com/5

```sql
docker run -p 3306:3306 --name security -e MYSQL_ROOT_PASSWORD=1q2w3e4r -e MYSQL_DATABASE="user" -d mysql:latest --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
mysql -u root -p
CREATE DATABASE security;
```

```sql
CREATE TABLE member(
                       member_id bigint PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(300) NOT NULL
);

CREATE TABLE member_role(
                            member_role_id bigint PRIMARY KEY AUTO_INCREMENT,
                            role_name VARCHAR(100) NOT NULL,
                            role_code VARCHAR(100) NOT NULL
);

CREATE TABLE member_role_mapping(
    member_role_mapping_id bigint  PRIMARY KEY AUTO_INCREMENT,
    member_id bigint NOT NULL,
    member_role_id bigint NOT NULL
);

INSERT INTO member_role(role_name, role_code) VALUES ('기본 유저', 'ROLE_USER');
```