-- liquibase formatted sql

--changeset gleidiin:#db.base-create-tables-db.sql:1

CREATE TABLE question (
    id serial NOT NULL,
    identifier varchar(60) NOT NULL,
    content varchar(1000) NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE answer (
    id serial NOT NULL,
    id_question bigint NOT NULL,
    content varchar(600) NOT NULL,
    correct boolean NOT NULL DEFAULT false,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (id_question) REFERENCES question(id)
);

CREATE TABLE template_challenge (
    id serial NOT NULL,
    name varchar(120) NOT NULL,
    description varchar(360) NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE template_challenge_question (
    id serial NOT NULL,
    id_template_challenge bigint NOT NULL,
    id_question bigint NOT NULL,
    "order" smallint NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (id_template_challenge) REFERENCES template_challenge(id),
    FOREIGN KEY (id_question) REFERENCES question(id),
    UNIQUE (id, id_question, "order"),
    UNIQUE (id_template_challenge, id_question)
);

CREATE TABLE challenge (
    id serial NOT NULL,
    id_template_challenge bigint NOT NULL,
    id_question bigint NOT NULL,
    reference_code varchar(30) NOT NULL,
    started boolean NOT NULL DEFAULT false,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    finished_at timestamp,
    PRIMARY KEY (id),
    UNIQUE (reference_code),
    FOREIGN KEY (id_template_challenge) REFERENCES template_challenge(id),
    FOREIGN KEY (id_question) REFERENCES question(id)
);


CREATE TABLE challenge_answer (
    id serial NOT NULL,
    id_challenge bigint NOT NULL,
    id_question bigint NOT NULL,
    nickname varchar(255) NOT NULL,
    "right" boolean NOT NULL DEFAULT false,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (id_challenge) REFERENCES challenge(id),
    FOREIGN KEY (id_question) REFERENCES question(id)
);