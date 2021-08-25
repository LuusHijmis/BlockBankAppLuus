INSERT INTO `user` (`username`, `password`, `salt`, `role`, `emailaddress`, `firstname`, `prefix`,`lastname`, `address`, `houseNumber`, `postalcode`, `city`, `country`,`dateOfBirth`, `bsn`) VALUES ('Harold', 'dlorah','123', 'client', 'info@hjstevens.nl', 'Harold', '', 'Stevens', 'Pieter Woutersstraat', '26', '2215MC', 'Voorhout', '', '1973-09-25', '123456007');
INSERT INTO `user` (`username`, `password`, `salt`, `role`, `emailaddress`, `firstname`,`prefix`, `lastname`, `address`, `houseNumber`, `postalcode`, `city`, `country`,`dateOfBirth`, `bsn`) VALUES ('Alex', 'xela', '234' ,'client', 'alex@hva.nl', 'Alex', '', 'Shijan', 'Koningstraat', '10', '1010DS', 'Amsterdam', '', '1990-08-25', '123465992');
INSERT INTO `user` (`username`, `password`, `salt`, `role`, `emailaddress`, `firstname`, `prefix`,`lastname`, `address`, `houseNumber`, `postalcode`, `city`, `country`, `dateOfBirth`, `bsn`) VALUES ('Hannah', 'hannah', '345','client', 'hannah@hva.nl', 'Hannah', 'van', 'Dam', 'Prinsessenstraat', '29', '1300PT', 'Rotterdam', '', '1999-01-07', '123477759');

INSERT INTO `account` (`IBAN`, `balance`, `UserID`) VALUES ('NL22RABO9999999991', '10000', '1');
INSERT INTO `account` (`IBAN`, `balance`, `UserID`) VALUES ('NL33ABNA9999999992', '5000', '2');
INSERT INTO `account` (`IBAN`, `balance`, `UserID`) VALUES ('NL44INGB9999999993', '50000', '3');

INSERT INTO `portfolio` (`asset(temp)`, `aantal`, `UserID`) VALUES ('Bitcoin', '10', '1');
INSERT INTO `portfolio` (`asset(temp)`, `aantal`, `UserID`) VALUES ('Ethereum', '2', '1');
INSERT INTO `portfolio` (`asset(temp)`, `aantal`, `UserID`) VALUES ('Cardano', '4', '1');
INSERT INTO `portfolio` (`asset(temp)`, `aantal`, `UserID`) VALUES ('Stellar', '3', '1');
INSERT INTO `portfolio` (`asset(temp)`, `aantal`, `UserID`) VALUES ('Bitcoin', '3', '2');
INSERT INTO `portfolio` (`asset(temp)`, `aantal`, `UserID`) VALUES ('Cardano', '7', '2');
INSERT INTO `portfolio` (`asset(temp)`, `aantal`, `UserID`) VALUES ('Stellar', '2', '2');
INSERT INTO `portfolio` (`asset(temp)`, `aantal`, `UserID`) VALUES ('Bitcoin', '4', '3');
INSERT INTO `portfolio` (`asset(temp)`, `aantal`, `UserID`) VALUES ('Stellar', '2', '3');
