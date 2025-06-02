package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class KQueueTransportFactoryDiffblueTest {
  /**
   * Test {@link KQueueTransportFactory#isAvailable()}.
   * <p>
   * Method under test: {@link KQueueTransportFactory#isAvailable()}
   */
  @Test
  @DisplayName("Test isAvailable()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean KQueueTransportFactory.isAvailable()"})
  void testIsAvailable() {
    // Arrange, Act and Assert
    assertFalse(KQueueTransportFactory.isAvailable());
  }
}
