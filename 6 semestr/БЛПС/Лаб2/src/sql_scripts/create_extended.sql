CREATE TABLE extended.public.culinary_news
(
    id               SERIAL PRIMARY KEY,
    user_login       varchar(255)  NOT NULL,
    name             varchar(255)  NOT NULL,
    description      varchar(4096) NOT NULL,
    publication_date date          NOT NULL
);
