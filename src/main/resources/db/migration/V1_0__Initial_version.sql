CREATE TABLE log (
  id            BIGINT IDENTITY (1, 1) NOT NULL,
  system        VARCHAR(32)                 DEFAULT NULL,
  request_body  TEXT,
  response_body TEXT,
  response_code VARCHAR(4)                  DEFAULT NULL,
  reference_number VARCHAR(50)                     DEFAULT NULL,
  status        VARCHAR(16)                 DEFAULT NULL,
  created_at    DATETIME               NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    DATETIME               NULL DEFAULT GETDATE(),
  PRIMARY KEY (id)
)

