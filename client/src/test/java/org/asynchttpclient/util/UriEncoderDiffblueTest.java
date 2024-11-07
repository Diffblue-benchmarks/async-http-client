package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UriEncoderDiffblueTest {
  /**
   * Test {@link UriEncoder#uriEncoder(boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return {@code FIXING}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriEncoder#uriEncoder(boolean)}
   */
  @Test
  @DisplayName("Test uriEncoder(boolean); when 'false'; then return 'FIXING'")
  void testUriEncoder_whenFalse_thenReturnFixing() {
    // Arrange, Act and Assert
    assertEquals(UriEncoder.FIXING, UriEncoder.uriEncoder(false));
  }

  /**
   * Test {@link UriEncoder#uriEncoder(boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then return {@code RAW}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriEncoder#uriEncoder(boolean)}
   */
  @Test
  @DisplayName("Test uriEncoder(boolean); when 'true'; then return 'RAW'")
  void testUriEncoder_whenTrue_thenReturnRaw() {
    // Arrange, Act and Assert
    assertEquals(UriEncoder.RAW, UriEncoder.uriEncoder(true));
  }
}
