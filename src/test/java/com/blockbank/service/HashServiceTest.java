package com.blockbank.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HashServiceTest {

    HashService hashService = new HashService();

    @Test
    void hashServiceCreateHashWithSaltAndPepper() {
        String password = "test 19";
        String salt = "594ea4b2";
        String actual = hashService.hash(password,salt);
        String expected = "0f9187e33456e9c08d6e112685316714d75d9d703c0802434305a335b7a01d4a25d94fc3cf001d18d707d35ed80308831e9562f04f4ebf249091848f764d98a6";
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void hashServiceNotNull(){
        assertThat(hashService).isNotNull();
    }

    @Test
    public void hashServiceCreateHash(){
        String password = "test 17";
        String salt = "80a23d61";
        String actual = hashService.hash(password, salt);
        assertThat(actual).contains("a").contains("b").contains("c").contains("d").contains("e");

    }
    @Test
    public void hashServiceUltimateHash(){
        String password = "test 20";
        String salt = "ef0c7192a5b85a5af6a905629a961713";
        String actual = hashService.ultimateHash(password,salt);
        String expected = "e6cb955ef8e3fa94be53e571dcca5f3abfca62fb5c318e1a4ff745c3082b73e1a504ca69d4a7a6fd9b3f4a18c088a76222bd2ceeb29f979a699f3a47bb76efe9";
        assertThat(actual).isEqualTo(expected);
    }

}