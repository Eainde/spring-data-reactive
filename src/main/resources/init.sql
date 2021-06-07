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