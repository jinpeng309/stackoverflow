CREATE TABLE IF NOT EXISTS user (
	id INTEGER PRIMARY KEY,
	reputation INTEGER NOT NULL DEFAULT 0,
  creationDate BIGINT,
  displayName VARCHAR(1024) NOT NULL DEFAULT "''",
  lastAccessDate BIGINT,
  websiteUrl VARCHAR(1024) NOT NULL DEFAULT "''",
  location VARCHAR(4096) NOT NULL DEFAULT "''",
  aboutMe VARCHAR(10240) NOT NULL DEFAULT "''",
  views INTEGER NOT NULL DEFAULT 0,
  upVotes INTEGER NOT NULL DEFAULT 0,
  downVotes INTEGER NOT NULL DEFAULT 0,
  accountId INTEGER NOT NULL DEFAULT -1,
  age INTEGER NOT NULL DEFAULT  -1
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;