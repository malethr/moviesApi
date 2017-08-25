SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS movies (
 id int PRIMARY KEY auto_increment,
 title VARCHAR,
 description VARCHAR,
 releaseyear VARCHAR,
 directedby VARCHAR,
 trailer VARCHAR
);

CREATE TABLE IF NOT EXISTS movietypes (
 id int PRIMARY KEY auto_increment,
 type VARCHAR
);

CREATE TABLE IF NOT EXISTS reviews (
 id int PRIMARY KEY auto_increment,
 writtenby VARCHAR,
 rating VARCHAR,
 createdat TIMESTAMP,
 movieid INTEGER
);