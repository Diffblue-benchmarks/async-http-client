package org.asynchttpclient.proxy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ProxyTypeDiffblueTest {
  /**
   * Method under test: {@link ProxyType#isHttp()}
   */
  @Test
  void testIsHttp() {
    // Arrange, Act and Assert
    assertTrue(ProxyType.valueOf("HTTP").isHttp());
  }

  /**
   * Method under test: {@link ProxyType#isSocks()}
   */
  @Test
  void testIsSocks() {
    // Arrange, Act and Assert
    assertFalse(ProxyType.HTTP.isSocks());
    assertTrue(ProxyType.SOCKS_V4.isSocks());
  }
}
