

IF NOT EXISTS(SELECT *
              FROM sys.objects
              WHERE object_id = OBJECT_ID(N'transactions') AND type IN (N'U'))
BEGIN
    CREATE TABLE transactions
    (
        id                      BIGINT      IDENTITY (1, 1) PRIMARY KEY,
        beneficiaryName                  VARCHAR(100)   NULL,
        amount                           BIGINT NOT NULL ,
        beneficiaryAccountNumber         VARCHAR(100)  NOT NULL,
        debitAccountNo                   VARCHAR(100)   NULL,
        beneficiaryBankCode              VARCHAR(50)NOT NULL,
        narration                        VARCHAR(200)NOT NULL,
        transactionDate                  DATETIME     NOT NULL DEFAULT GETDATE(),
        responseCode                     VARCHAR(50) NULL,
        responseMessage                  VARCHAR(100) NULL,
        referenceNumber                  VARCHAR(100) NULL,
        scheduleId                  VARCHAR(100)NOT NULL



      )

    CREATE NONCLUSTERED  INDEX IX_transactions ON transactions (beneficiaryBankCode,beneficiaryName);
    PRINT 'Transactions Table created successfully'
END
ELSE
BEGIN
    PRINT 'Transactions table already exist....'
END
GO







