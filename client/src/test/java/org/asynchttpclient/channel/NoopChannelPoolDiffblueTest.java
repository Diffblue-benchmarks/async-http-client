package org.asynchttpclient.channel;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoopChannelPoolDiffblueTest {
  /**
   * Test {@link NoopChannelPool#getIdleChannelCountPerHost()}.
   * <p>
   * Method under test: {@link NoopChannelPool#getIdleChannelCountPerHost()}
   */
  @Test
  @DisplayName("Test getIdleChannelCountPerHost()")
  void testGetIdleChannelCountPerHost() {
    // Arrange, Act and Assert
    assertTrue(NoopChannelPool.INSTANCE.getIdleChannelCountPerHost().isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NoopChannelPool#destroy()}
   *   <li>{@link NoopChannelPool#isOpen()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters() {
    // Arrange
    NoopChannelPool valueOfResult = NoopChannelPool.valueOf("INSTANCE");

    // Act
    valueOfResult.destroy();

    // Assert that nothing has changed
    assertTrue(valueOfResult.isOpen());
  }

  /**
   * Test {@link NoopChannelPool#poll(Object)}.
   * <p>
   * Method under test: {@link NoopChannelPool#poll(Object)}
   */
  @Test
  @DisplayName("Test poll(Object)")
  void testPoll() {
    // Arrange, Act and Assert
    assertNull(NoopChannelPool.INSTANCE.poll("Partition Key"));
  }
}
