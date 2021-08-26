package com.blockbank.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SaltGeneratorTest {

    SaltGenerator saltGenerator= new SaltGenerator();

    @Test
    void generateSaltNotNull() {
            assertThat(saltGenerator.generateSalt()).isNotNull();
    }
    @Test
    public void generateSalt() {

        assertThat(saltGenerator.generateSalt()).isBetween("0", "f");
    }


}