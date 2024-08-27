CREATE TABLE app_user (
    id serial primary key,
    username varchar(64) unique not null,
    password varchar(64) not null,
    email varchar(64) unique not null
);

CREATE TABLE sprint (
    id serial primary key,
    name varchar(64) unique not null,
    duration_in_weeks int not null,
    sprint_state varchar(20) not null,
    start_date timestamp
);

CREATE TABLE task (
    id serial primary key,
    name varchar(64) unique not null,
    description varchar(64) not null,
    comment varchar(64),
    task_type varchar(20) not null,
    task_state varchar(20) not null,
    user_id int,
    foreign key(user_id) references app_user(id),
    sprint_id int,
    foreign key(sprint_id) references sprint(id)
);
CREATE TABLE project (
    project_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    start_date DATE,
    end_date DATE,
    status VARCHAR(255)
);
CREATE TABLE team (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    project_id INT,
    FOREIGN KEY (project_id) REFERENCES project(project_id)
);
CREATE TABLE user_team (
    user_team_id SERIAL PRIMARY KEY,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES app_user(id),
    team_id INT,
    FOREIGN KEY (team_id) REFERENCES team(id),
    role VARCHAR(50)
);
