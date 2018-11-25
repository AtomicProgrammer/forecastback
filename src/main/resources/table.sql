DROP TABLE IF EXISTS "user_info";
DROP TABLE IF EXISTS "base_table";
CREATE TABLE "base_table" (
  "pid"         INT8 NOT NULL,
  "name"        TEXT,
  "create_by"   INT8,
  "update_by"   INT8,
  "deleted"     BOOLEAN DEFAULT FALSE ,
  "version"     INT4,
  "ext"         JSONB,
  "create_time" TIMESTAMP(0),
  "update_time" TIMESTAMP(0),
  PRIMARY KEY ("pid")
)
WITHOUT OIDS;

CREATE TABLE "user_info" (
  PRIMARY KEY ("pid")
)INHERITS (base_table)
WITHOUT OIDS;