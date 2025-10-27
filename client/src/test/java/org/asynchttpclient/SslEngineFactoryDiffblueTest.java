package org.asynchttpclient;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;

class SslEngineFactoryDiffblueTest {
  /**
   * Method under test: {@link SslEngineFactory#destroy()}
   */
  @Test
  void testDestroy() {
    // Arrange
    SslEngineFactory sslEngineFactory = mock(SslEngineFactory.class);
    doNothing().when(sslEngineFactory).destroy();

    // Act
    sslEngineFactory.destroy();

    // Assert
    verify(sslEngineFactory).destroy();
  }
}
