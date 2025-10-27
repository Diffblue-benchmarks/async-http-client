package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class UriEncoderDiffblueTest {
  /**
   * Method under test: {@link UriEncoder#uriEncoder(boolean)}
   */
  @Test
  void testUriEncoder() {
    // Arrange, Act and Assert
    assertEquals(UriEncoder.RAW, UriEncoder.uriEncoder(true));
    assertEquals(UriEncoder.FIXING, UriEncoder.uriEncoder(false));
  }
}
