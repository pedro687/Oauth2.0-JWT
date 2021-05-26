create database if not exists creepy;

CREATE TABLE IF NOT EXISTS users(
    id bigint(20) auto_increment primary key,
    username varchar(50) not null,
    email varchar(255) unique not null,
    password varchar(255) not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS permissions(
    id bigint(20) auto_increment primary key,
    description varchar(50) not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE IF NOT EXISTS user_permissions(
    id_user bigint(20) auto_increment NOT NULL,
    id_permission bigint(20) NOT NULL,
    PRIMARY KEY(id_user, id_permission),
    FOREIGN KEY(id_user) REFERENCES users(id),
    FOREIGN KEY(id_permission) REFERENCES permissions(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO users (id, username, email, password) values (1, 'Administrador', 'admin@creepy.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO users (id, username, email, password) values (2, 'Pedro Emanoel', 'pedro@creepy.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permissions (id, description) values (1, 'ROLE_USER');
INSERT INTO permissions (id, description) values (2, 'ROLE_ADMIN');

INSERT INTO user_permissions (id_user, id_permission) values (1, 2);
INSERT INTO user_permissions (id_user, id_permission) values (1, 1);
