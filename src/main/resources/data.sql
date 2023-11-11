CREATE TABLE users (
                       id BIGINT UNSIGNED AUTO_INCREMENT,
                       username VARCHAR(30) NOT NULL,
                       password VARCHAR(80) NOT NULL,
                       email VARCHAR(50) UNIQUE,
                       PRIMARY KEY (id)
);

CREATE TABLE roles (
                       id INT UNSIGNED AUTO_INCREMENT,
                       name VARCHAR(50) NOT NULL,
                       PRIMARY KEY (id)
);

CREATE TABLE users_roles (
                             user_id BIGINT UNSIGNED NOT NULL,
                             role_id INT UNSIGNED NOT NULL,
                             PRIMARY KEY (user_id, role_id),
                             FOREIGN KEY (user_id) REFERENCES users(id),
                             FOREIGN KEY (role_id) REFERENCES roles(id)
);

INSERT IGNORE INTO users (username, password, email) VALUES ('user1', '$2a$12$b7Z3qZJNIAIP1sEFvtwcmuV4l6aFd1jKSNYSVauuvJV2Y4bt/XyXu', 'user1@example.com');
INSERT IGNORE INTO users (username, password, email) VALUES ('admin1', '$2a$12$b7Z3qZJNIAIP1sEFvtwcmuV4l6aFd1jKSNYSVauuvJV2Y4bt/XyXu', 'admin1@example.com');

INSERT IGNORE INTO roles (name) VALUES ('ROLE_USER');
INSERT IGNORE INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT IGNORE INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT IGNORE INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT IGNORE INTO users_roles (user_id, role_id) VALUES (2, 1);
