SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS movies (
 id int PRIMARY KEY auto_increment,
 title VARCHAR,
 description VARCHAR,
 year VARCHAR,
 director VARCHAR,
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
 content VARCHAR,
 createdat TIMESTAMP,
 movieid INTEGER
);

CREATE TABLE IF NOT EXISTS movies_movietypes(
 id int PRIMARY KEY auto_increment,
 movieid INTEGER,
 movietypeid INTEGER
);