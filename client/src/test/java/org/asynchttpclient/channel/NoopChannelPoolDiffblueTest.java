package org.asynchttpclient.channel;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class NoopChannelPoolDiffblueTest {
  /**
   * Method under test: {@link NoopChannelPool#getIdleChannelCountPerHost()}
   */
  @Test
  void testGetIdleChannelCountPerHost() {
    // Arrange, Act and Assert
    assertTrue(NoopChannelPool.INSTANCE.getIdleChannelCountPerHost().isEmpty());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link NoopChannelPool#destroy()}
   *   <li>{@link NoopChannelPool#isOpen()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    NoopChannelPool valueOfResult = NoopChannelPool.valueOf("INSTANCE");

    // Act
    valueOfResult.destroy();

    // Assert that nothing has changed
    assertTrue(valueOfResult.isOpen());
  }

  /**
   * Method under test: {@link NoopChannelPool#poll(Object)}
   */
  @Test
  void testPoll() {
    // Arrange, Act and Assert
    assertNull(NoopChannelPool.INSTANCE.poll("Partition Key"));
  }
}
