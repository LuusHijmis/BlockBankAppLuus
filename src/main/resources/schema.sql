CREATE TABLE User (
    `userID` int auto_increment primary key,
    `username` VARCHAR(25) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `role` VARCHAR(45) NOT NULL,
    `salt` VARCHAR(45) NOT NULL,
    UNIQUE INDEX `username_UNIQUE` (`username` ASC)
    );

CREATE TABLE Client (
    `firstname` VARCHAR(45) NOT NULL,
    `prefix` VARCHAR(25) NULL,
    `lastname` VARCHAR(45) NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    `houseNumber` INT NOT NULL,
    `affix` VARCHAR(15) NULL,
    `zipcode` VARCHAR(10) NOT NULL,
    `city` VARCHAR(45) NOT NULL,
    `dateOfBirth` DATE NOT NULL,
    `bsn` INT NOT NULL,
    `clientID` INT NOT NULL,
    `emailaddress` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`clientID`),
    INDEX `fk_Client_User1_idx` (`clientID` ASC),
    UNIQUE INDEX `emailaddress_UNIQUE` (`emailaddress` ASC)
    --CONSTRAINT `fk_Client_User1`
    --constraint fk_Client_User1
    --foreign key (clientID) references User (userID)
    --FOREIGN KEY (`clientID`),
    --REFERENCES `blockBank`.`User` (`userID`),
    --ON DELETE NO ACTION,
    --ON UPDATE NO ACTION
    );

CREATE TABLE Account(
    `IBAN` VARCHAR(20) NOT NULL,
    `balance` DOUBLE NOT NULL,
    `ClientID` INT NOT NULL,
     PRIMARY KEY (`IBAN`),
     INDEX `fk_Account_Client1_idx` (`ClientID` ASC)
    --CONSTRAINT `fk_Account_Client1`
    --FOREIGN KEY (`ClientID`)
    --REFERENCES `blockBank`.`Client` (`clientID`)
    --ON DELETE NO ACTION
    --ON UPDATE NO ACTION
    );


CREATE TABLE Administrator(
    `afdeling` VARCHAR(45) NOT NULL,
    `adminID` INT NOT NULL,
    PRIMARY KEY (`adminID`)
    --CONSTRAINT `fk_Administrator_User1`
    --FOREIGN KEY (`adminID`)
    --REFERENCES `blockBank`.`User` (`userID`)
    --ON DELETE NO ACTION
    --ON UPDATE NO ACTION
    );


CREATE TABLE Portfolio (
    `clientID` INT NOT NULL,
    `asset(temp)` VARCHAR(10),
    `aantal` INT NOT NULL,
    PRIMARY KEY (`asset(temp)`, `clientID`),
    INDEX `fk_Portfolio_Client1_idx` (`clientID` ASC)
    --CONSTRAINT `fk_Portfolio_Client1`
    --FOREIGN KEY (`clientID`)

    --REFERENCES `blockBank`.`Client` (`clientID`)
    --ON DELETE NO ACTION
    --ON UPDATE NO ACTION
    );




