INSERT INTO `user` (`username`, `password`, `role`, `salt`) VALUES ('Harold', 'dlorah', 'client', '123');
INSERT INTO `user` (`username`, `password`, `role`, `salt`) VALUES ('Hannah', 'hannah', 'client', '345');
INSERT INTO `user` (`username`, `password`, `role`, `salt`) VALUES ('Alex', 'xela', 'client', '234');

INSERT INTO `client` (`firstname`, `lastname`, `address`, `houseNumber`, `zipcode`, `city`, `dateOfBirth`, `bsn`, `clientID`, `emailaddress`) VALUES ('Harold', 'Stevens', 'Pieter Woutersstraat', '26', '2215MC', 'Voorhout', '1973-09-25', '123456007', '3', 'info@hjstevens.nl');
INSERT INTO `client` (`firstname`, `prefix`, `lastname`, `address`, `houseNumber`, `zipcode`, `city`, `dateOfBirth`, `bsn`, `clientID`, `emailaddress`) VALUES ('Alex', '', 'Shijan', 'Koningstraat', '10', '1010DS', 'Amsterdam', '1990-08-25', '123465992', '5', 'alex@hva.nl');
INSERT INTO `client` (`firstname`, `prefix`, `lastname`, `address`, `houseNumber`, `zipcode`, `city`, `dateOfBirth`, `bsn`, `clientID`, `emailaddress`) VALUES ('Hannah', 'van', 'Dam', 'Prinsessenstraat', '29', '1300PT', 'Rotterdam', '1999-01-07', '123477759', '4', 'hannah@hva.nl');

INSERT INTO `account` (`IBAN`, `balance`, `ClientID`) VALUES ('NL22RABO9999999991', '10000', '3');
INSERT INTO `account` (`IBAN`, `balance`, `ClientID`) VALUES ('NL33ABNA9999999992', '5000', '4');
INSERT INTO `account` (`IBAN`, `balance`, `ClientID`) VALUES ('NL44INGB9999999993', '50000', '5');


INSERT INTO `portfolio` (`clientID`, `asset(temp)`, `aantal`) VALUES ('3', 'Bitcoin', '10');
INSERT INTO `portfolio` (`clientID`, `asset(temp)`, `aantal`) VALUES ('3', 'Ethereum', '2');
INSERT INTO `portfolio` (`clientID`, `asset(temp)`, `aantal`) VALUES ('3', 'Cardano', '4');
INSERT INTO `portfolio` (`clientID`, `asset(temp)`, `aantal`) VALUES ('3', 'Stellar', '3');
INSERT INTO `portfolio` (`clientID`, `asset(temp)`, `aantal`) VALUES ('4', 'Bitcoin', '3');
INSERT INTO `portfolio` (`clientID`, `asset(temp)`, `aantal`) VALUES ('4', 'Cardano', '7');
INSERT INTO `portfolio` (`clientID`, `asset(temp)`, `aantal`) VALUES ('4', 'Stellar', '2');
INSERT INTO `portfolio` (`clientID`, `asset(temp)`, `aantal`) VALUES ('5', 'Bitcoin', '4');
INSERT INTO `portfolio` (`clientID`, `asset(temp)`, `aantal`) VALUES ('5', 'Stellar', '2');
