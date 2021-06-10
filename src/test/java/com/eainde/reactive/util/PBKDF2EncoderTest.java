package com.eainde.reactive.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PBKDF2EncoderTest {

  @Autowired private PBKDF2Encoder encoder;

  @Test
  void encode() {
    String password = encoder.encode("SUPERADMIN");
    System.out.println(password);
  }
}
