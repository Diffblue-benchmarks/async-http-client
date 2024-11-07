package org.asynchttpclient.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WebSocketUtilsDiffblueTest {
  /**
   * Test {@link WebSocketUtils#getAcceptKey(String)}.
   * <p>
   * Method under test: {@link WebSocketUtils#getAcceptKey(String)}
   */
  @Test
  @DisplayName("Test getAcceptKey(String)")
  void testGetAcceptKey() {
    // Arrange, Act and Assert
    assertEquals("jjBb/h4G0xabWNaKzkyRU/jf9o4=", WebSocketUtils.getAcceptKey("https://example.org/example"));
  }
}
