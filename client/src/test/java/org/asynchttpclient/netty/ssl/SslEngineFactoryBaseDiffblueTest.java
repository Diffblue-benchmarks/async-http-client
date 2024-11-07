package org.asynchttpclient.netty.ssl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SslEngineFactoryBaseDiffblueTest {
  /**
   * Test {@link SslEngineFactoryBase#domain(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SslEngineFactoryBase#domain(String)}
   */
  @Test
  @DisplayName("Test domain(String); when 'https://example.org/example'; then return 'https://example.org/example'")
  void testDomain_whenHttpsExampleOrgExample_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example", (new DefaultSslEngineFactory()).domain("https://example.org/example"));
  }
}
