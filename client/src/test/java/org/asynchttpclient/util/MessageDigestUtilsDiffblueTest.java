package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.security.MessageDigest;
import java.security.Provider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MessageDigestUtilsDiffblueTest {
  /**
   * Test {@link MessageDigestUtils#pooledMd5MessageDigest()}.
   * <p>
   * Method under test: {@link MessageDigestUtils#pooledMd5MessageDigest()}
   */
  @Test
  @DisplayName("Test pooledMd5MessageDigest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageDigest MessageDigestUtils.pooledMd5MessageDigest()"})
  void testPooledMd5MessageDigest() {
    // Arrange and Act
    MessageDigest actualPooledMd5MessageDigestResult = MessageDigestUtils.pooledMd5MessageDigest();

    // Assert
    Provider provider = actualPooledMd5MessageDigestResult.getProvider();
    assertEquals(194, provider.size());
    assertEquals("3072", provider.get("Signature.SHA3-384withDSA KeySize"));
    assertEquals("3072", provider.get("Signature.SHA384withDSA KeySize"));
    assertEquals("DSA", provider.get("Alg.Alias.KeyPairGenerator.1.2.840.10040.4.1"));
    assertEquals("MD5", actualPooledMd5MessageDigestResult.getAlgorithm());
    assertEquals("SHA-512/224", provider.get("Alg.Alias.MessageDigest.SHA512/224"));
    assertEquals("Software", provider.get("Signature.SHA3-384withDSA ImplementedIn"));
    assertEquals("sun.security.provider.certpath.PKIXCertPathValidator", provider.get("CertPathValidator.PKIX"));
    assertEquals(Short.SIZE, actualPooledMd5MessageDigestResult.getDigestLength());
  }

  /**
   * Test {@link MessageDigestUtils#pooledSha1MessageDigest()}.
   * <p>
   * Method under test: {@link MessageDigestUtils#pooledSha1MessageDigest()}
   */
  @Test
  @DisplayName("Test pooledSha1MessageDigest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MessageDigest MessageDigestUtils.pooledSha1MessageDigest()"})
  void testPooledSha1MessageDigest() {
    // Arrange and Act
    MessageDigest actualPooledSha1MessageDigestResult = MessageDigestUtils.pooledSha1MessageDigest();

    // Assert
    Provider provider = actualPooledSha1MessageDigestResult.getProvider();
    assertEquals(194, provider.size());
    assertEquals("3072", provider.get("Signature.SHA3-384withDSA KeySize"));
    assertEquals("3072", provider.get("Signature.SHA384withDSA KeySize"));
    assertEquals("DSA", provider.get("Alg.Alias.KeyPairGenerator.1.2.840.10040.4.1"));
    assertEquals("SHA-512/224", provider.get("Alg.Alias.MessageDigest.SHA512/224"));
    assertEquals("SHA1", actualPooledSha1MessageDigestResult.getAlgorithm());
    assertEquals("Software", provider.get("Signature.SHA3-384withDSA ImplementedIn"));
    assertEquals("sun.security.provider.certpath.PKIXCertPathValidator", provider.get("CertPathValidator.PKIX"));
    assertEquals(20, actualPooledSha1MessageDigestResult.getDigestLength());
  }
}
