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
        String expected = "0f9187e33456e9c08d6e112685316714d75d9d703c0802434305a335b7a01d4a25d94fc3cf001d18d707d35ed803" +
                "08831e9562f04f4ebf249091848f764d98a6";
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
        String password = "test 19";
        String salt = "994ad5318deaf6273ff858c4fa2225ef";
        String actual = hashService.ultimateHash(password,salt);
        String expected = "a75083ea0ca57e10cc51ce1f3770b8d333f9bbacf29555dd495b84c9de18b1ceb13b9e5ce9a26f934ff2f3bbc1a33d52ac4cb5776f6f5cd791f1d30c557760cf";
        assertThat(expected).isEqualTo(actual);
    }


}