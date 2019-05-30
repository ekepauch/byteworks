CREATE TABLE account_validation (
  id                   BIGINT IDENTITY (1, 1) NOT NULL,
  system        VARCHAR(32)                 DEFAULT NULL,
  name                 TEXT,
  account_number       VARCHAR(24)                     DEFAULT NULL,
  response_code        VARCHAR(4)                      DEFAULT NULL,
  response_description TEXT,
  created_at           DATETIME               NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at           DATETIME               NULL     DEFAULT GETDATE(),
  expired_at           DATETIME               NULL     DEFAULT GETDATE(),
  PRIMARY KEY (id)
);
