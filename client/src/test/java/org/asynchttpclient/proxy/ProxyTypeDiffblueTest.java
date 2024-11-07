package org.asynchttpclient.proxy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProxyTypeDiffblueTest {
  /**
   * Test {@link ProxyType#isHttp()}.
   * <p>
   * Method under test: {@link ProxyType#isHttp()}
   */
  @Test
  @DisplayName("Test isHttp()")
  void testIsHttp() {
    // Arrange, Act and Assert
    assertTrue(ProxyType.valueOf("HTTP").isHttp());
  }

  /**
   * Test {@link ProxyType#isSocks()}.
   * <ul>
   *   <li>Given {@code HTTP}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProxyType#isSocks()}
   */
  @Test
  @DisplayName("Test isSocks(); given 'HTTP'; then return 'false'")
  void testIsSocks_givenHttp_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ProxyType.HTTP.isSocks());
  }

  /**
   * Test {@link ProxyType#isSocks()}.
   * <ul>
   *   <li>Given {@code SOCKS_V4}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProxyType#isSocks()}
   */
  @Test
  @DisplayName("Test isSocks(); given 'SOCKS_V4'; then return 'true'")
  void testIsSocks_givenSocksV4_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ProxyType.SOCKS_V4.isSocks());
  }
}
