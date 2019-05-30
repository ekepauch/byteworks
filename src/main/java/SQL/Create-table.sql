




CREATE DATABASE Zenithbank_Api


   CREATE TABLE out_flow
    (
        id                               BIGINT  IDENTITY (1, 1) PRIMARY KEY,
        merchant_account                 VARCHAR(100) NOT NULL,
        credit_account                   VARCHAR(100)NOT NULL,
        destination_bank_code            VARCHAR(50)  NULL,
        transaction_amount               VARCHAR(100) NOT NULL,
        payment_reference                VARCHAR(100) NULL,
        transaction_id                   VARCHAR(100) NULL,
        processor_id                     VARCHAR(MAX) NULL,
        response_code                    VARCHAR(100) NULL,
        response_message                 VARCHAR(255) NULL,
        created_at                       datetime NOT NULL,

    )



  CREATE TABLE in_flow
    (
        id                               BIGINT  IDENTITY (1, 1) PRIMARY KEY,
        merchant_account                 VARCHAR(100) NOT NULL,
        debit_account                    VARCHAR(100)NOT NULL,
        source_bank_code                 VARCHAR(50)  NULL,
        transaction_amount               VARCHAR(100) NOT NULL,
        payment_reference                VARCHAR(100) NULL,
        transaction_id                   VARCHAR(100) NULL,
        processor_id                     VARCHAR(255) NULL,
        otp                              VARCHAR(100) NULL,
        auth_type                        VARCHAR(100) NULL,
        response_code                    VARCHAR(100) NULL,
        response_message                 VARCHAR(255) NULL,
        created_at                       datetime NOT NULL,
    )




