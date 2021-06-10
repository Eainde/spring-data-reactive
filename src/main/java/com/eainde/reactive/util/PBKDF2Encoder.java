package com.eainde.reactive.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PBKDF2Encoder implements PasswordEncoder {
  private final String secret;
  private final int iteration;
  private final int keyLength;
  private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

  PBKDF2Encoder(
      @Value("${jjwt.password.encoder.secret}") final String secret,
      @Value("${jjwt.password.encoder.iteration}") final int iteration,
      @Value("${jjwt.password.encoder.keylength}") final int keyLength) {
    this.secret = secret;
    this.iteration = iteration;
    this.keyLength = keyLength;
  }

  @Override
  public String encode(CharSequence cs) {
    try {
      byte[] result =
          SecretKeyFactory.getInstance(ALGORITHM)
              .generateSecret(
                  new PBEKeySpec(
                      cs.toString().toCharArray(), secret.getBytes(), iteration, keyLength))
              .getEncoded();
      String pass = Base64.getEncoder().encodeToString(result);
      return Base64.getEncoder().encodeToString(result);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public boolean matches(CharSequence cs, String string) {
    return encode(cs).equals(string);
  }
}
