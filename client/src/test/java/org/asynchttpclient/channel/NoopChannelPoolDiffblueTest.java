package org.asynchttpclient.channel;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NoopChannelPoolDiffblueTest {
  /**
   * Test {@link NoopChannelPool#poll(Object)}.
   * <p>
   * Method under test: {@link NoopChannelPool#poll(Object)}
   */
  @Test
  @DisplayName("Test poll(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"io.netty.channel.Channel NoopChannelPool.poll(Object)"})
  void testPoll() {
    // Arrange, Act and Assert
    assertNull(NoopChannelPool.INSTANCE.poll("Partition Key"));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NoopChannelPool#destroy()}
   *   <li>{@link NoopChannelPool#flushPartitions(Predicate)}
   *   <li>{@link NoopChannelPool#isOpen()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NoopChannelPool.destroy()", "void NoopChannelPool.flushPartitions(Predicate)",
      "boolean NoopChannelPool.isOpen()"})
  void testGettersAndSetters() {
    // Arrange
    NoopChannelPool valueOfResult = NoopChannelPool.valueOf("INSTANCE");

    // Act
    valueOfResult.destroy();
    valueOfResult.flushPartitions(mock(Predicate.class));

    // Assert
    assertTrue(valueOfResult.isOpen());
  }

  /**
   * Test {@link NoopChannelPool#getIdleChannelCountPerHost()}.
   * <p>
   * Method under test: {@link NoopChannelPool#getIdleChannelCountPerHost()}
   */
  @Test
  @DisplayName("Test getIdleChannelCountPerHost()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Map NoopChannelPool.getIdleChannelCountPerHost()"})
  void testGetIdleChannelCountPerHost() {
    // Arrange, Act and Assert
    assertTrue(NoopChannelPool.INSTANCE.getIdleChannelCountPerHost().isEmpty());
  }
}
