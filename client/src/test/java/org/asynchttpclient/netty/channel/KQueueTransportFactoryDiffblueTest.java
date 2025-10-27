package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

class KQueueTransportFactoryDiffblueTest {
  /**
   * Method under test: {@link KQueueTransportFactory#isAvailable()}
   */
  @Test
  void testIsAvailable() {
    // Arrange, Act and Assert
    assertFalse(KQueueTransportFactory.isAvailable());
  }
}
