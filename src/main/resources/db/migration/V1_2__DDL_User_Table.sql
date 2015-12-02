
ALTER TABLE APP_USER ADD city varchar(100) not null default '';
ALTER TABLE APP_USER ADD country varchar(2) not null default '';
ALTER TABLE APP_USER ADD email varchar(100) not null default '';
ALTER TABLE APP_USER ADD street varchar(100) not null default '';
ALTER TABLE APP_USER ADD zip varchar(100) not null default '';
ALTER TABLE APP_USER ADD date_of_birth date not null default '';

ALTER TABLE APP_USER ADD UNIQUE (username);

CREATE UNIQUE INDEX user_idx ON APP_USER (id);
