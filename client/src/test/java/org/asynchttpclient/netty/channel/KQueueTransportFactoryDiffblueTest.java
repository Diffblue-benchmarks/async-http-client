package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KQueueTransportFactoryDiffblueTest {
  /**
   * Test {@link KQueueTransportFactory#isAvailable()}.
   * <p>
   * Method under test: {@link KQueueTransportFactory#isAvailable()}
   */
  @Test
  @DisplayName("Test isAvailable()")
  void testIsAvailable() {
    // Arrange, Act and Assert
    assertFalse(KQueueTransportFactory.isAvailable());
  }
}
