CREATE SEQUENCE reactive.book_id_seq
	INCREMENT 1
	START 1
	MINVALUE 1
	MAXVALUE 2147483647
	CACHE 1;

ALTER SEQUENCE reactive.book_id_seq
OWNER to postgres;

create TABLE reactive.book
(
	id integer NOT NULL DEFAULT nextval('reactive.book_id_seq'::regclass),
	title text COLLATE pg_catalog."default" NOT NULL,
	author text COLLATE pg_catalog."default" NOT NULL,
	CONSTRAINT book_pkey PRIMARY KEY (id)
)
TABLESPACE pg_default;

ALTER TABLE reactive.book
OWNER to postgres;

-- Table: reactive.appl_role

-- DROP TABLE reactive.appl_role;

CREATE TABLE IF NOT EXISTS reactive.appl_role
(
    role_id integer NOT NULL,
    role_name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (role_id),
    CONSTRAINT appl_role_i01u UNIQUE (role_id),
    CONSTRAINT appl_role_i02u UNIQUE (role_name)
)

TABLESPACE pg_default;

ALTER TABLE reactive.appl_role
    OWNER to postgres;

-- Table: reactive.appl_user

-- DROP TABLE reactive.appl_user;

CREATE TABLE IF NOT EXISTS reactive.appl_user
(
    user_id integer NOT NULL,
    country text COLLATE pg_catalog."default" NOT NULL,
    age integer NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    enabled boolean NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (user_id),
    CONSTRAINT appl_user_i01u UNIQUE (name),
    CONSTRAINT appl_user_i02u UNIQUE (user_id)
)

TABLESPACE pg_default;

ALTER TABLE reactive.appl_user
    OWNER to postgres;

-- Table: reactive.user_role

-- DROP TABLE reactive.user_role;

CREATE TABLE IF NOT EXISTS reactive.user_role
(
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT user_role_i01p PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_role_f02 FOREIGN KEY (user_id)
        REFERENCES reactive.appl_user (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_role_f03 FOREIGN KEY (role_id)
        REFERENCES reactive.appl_role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE reactive.user_role
    OWNER to postgres;