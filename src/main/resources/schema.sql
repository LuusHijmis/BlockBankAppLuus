CREATE TABLE `User` (
    `UserID` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `salt` VARCHAR(45) NOT NULL,
    `role` VARCHAR(45) NOT NULL,
    `emailaddress` VARCHAR(100) NOT NULL,
    `firstname` VARCHAR(45) NULL,
    `prefix` VARCHAR(25) NULL,
    `lastname` VARCHAR(45) NULL,
    `address` VARCHAR(45) NULL,
    `houseNumber` INT NULL,
    `affix` VARCHAR(15) NULL,
    `postalcode` VARCHAR(10) NULL,
    `city` VARCHAR(45) NULL,
    `country` VARCHAR(45) NULL,
    `dateOfBirth` DATE NULL,
    `bsn` INT NULL,
    PRIMARY KEY (`UserID`),
    UNIQUE INDEX `emailaddress_UNIQUE` (`emailaddress` ASC),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC),
    UNIQUE INDEX `salt_UNIQUE` (`salt` ASC));

CREATE TABLE `Account` (
    `IBAN` VARCHAR(20) NOT NULL,
    `balance` DOUBLE NOT NULL,
    `UserID` INT NOT NULL,
    PRIMARY KEY (`IBAN`)
    --INDEX `fk_Account_User1_idx` (`UserID` ASC),
    --CONSTRAINT `fk_Account_User1`,
    --FOREIGN KEY (`UserID`)
    --REFERENCES `blockBank`.`User` (`UserID`)
    );

CREATE TABLE `Portfolio` (
    `asset(temp)` VARCHAR(30) NOT NULL,
    `aantal` INT NOT NULL,
    `UserID` INT NOT NULL,
    --INDEX `fk_Portfolio_User1_idx` (`UserID` ASC),
    --CONSTRAINT `fk_Portfolio_User1`,
    PRIMARY KEY (`UserID`, `asset(temp)`)
    --FOREIGN KEY (`UserID`)
    --REFERENCES `blockBank`.`User` (`UserID`)
    );






