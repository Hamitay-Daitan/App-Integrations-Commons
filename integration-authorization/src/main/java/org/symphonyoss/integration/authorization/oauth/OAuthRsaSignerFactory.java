package org.symphonyoss.integration.authorization.oauth;

import com.google.api.client.auth.oauth.OAuthRsaSigner;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.symphonyoss.integration.authorization.oauth.v1.OAuth1Exception;
import org.symphonyoss.integration.logging.LogMessageSource;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Builds a RSA Signer based on a PKCS8 private key.
 *
 * Created by campidelli on 25-jul-2017.
 */
@Component
public class OAuthRsaSignerFactory {

  private static final String RSA_NOT_FOUND = "integration.authorization.rsa.notfound";

  private static final String RSA_NOT_FOUND_SOLUTION = RSA_NOT_FOUND + ".solution";

  private static final String INVALID_PRIVATE_KEY = "integration.authorization.invalid.privatekey";

  private static final String INVALID_PRIVATE_KEY_SOLUTION = INVALID_PRIVATE_KEY + ".solution";

  private static final String INVALID_PUBLIC_KEY = "integration.authorization.invalid.publickey";

  private static final String INVALID_PUBLIC_KEY_SOLUTION = INVALID_PUBLIC_KEY + ".solution";

  @Autowired
  private LogMessageSource logMessage;

  /**
   * @param privateKey private key in PKCS8 syntax.
   * @return OAuthRsaSigner
   */
  public OAuthRsaSigner getOAuthRsaSigner(String privateKey) {
    OAuthRsaSigner oAuthRsaSigner = new OAuthRsaSigner();
    oAuthRsaSigner.privateKey = getPrivateKey(privateKey);
    return oAuthRsaSigner;
  }

  public PrivateKey getPrivateKey(String privateKey) {
    byte[] privateBytes = Base64.decodeBase64(privateKey);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);

    try {
      KeyFactory kf = KeyFactory.getInstance("RSA");
      return kf.generatePrivate(keySpec);
    } catch (NoSuchAlgorithmException e) {
      throw new OAuth1Exception(logMessage.getMessage(RSA_NOT_FOUND), e,
          logMessage.getMessage(RSA_NOT_FOUND_SOLUTION));
    } catch (InvalidKeySpecException e) {
      throw new OAuth1Exception(logMessage.getMessage(INVALID_PRIVATE_KEY), e,
          logMessage.getMessage(INVALID_PRIVATE_KEY_SOLUTION));
    }
  }

  public PublicKey getPublicKey(String publicKey) {
    try {
      byte[] encoded = Base64.decodeBase64(publicKey);
      X509EncodedKeySpec spec = new X509EncodedKeySpec(encoded);

      KeyFactory kf = KeyFactory.getInstance("RSA");
      return kf.generatePublic(spec);
    } catch (NoSuchAlgorithmException e) {
      throw new OAuth1Exception(logMessage.getMessage(RSA_NOT_FOUND), e,
          logMessage.getMessage(RSA_NOT_FOUND_SOLUTION));
    } catch (InvalidKeySpecException e) {
      throw new OAuth1Exception(logMessage.getMessage(INVALID_PUBLIC_KEY), e,
          logMessage.getMessage(INVALID_PUBLIC_KEY_SOLUTION));
    }
  }
}
