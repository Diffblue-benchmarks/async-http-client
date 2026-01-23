package org.asynchttpclient.channel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NoopChannelPoolDiffblueTest {
  /**
   * Test {@link NoopChannelPool#offer(Channel, Object)}.
   *
   * <p>Method under test: {@link NoopChannelPool#offer(Channel, Object)}
   */
  @Test
  @DisplayName("Test offer(Channel, Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NoopChannelPool.offer(Channel, Object)"})
  void testOffer() {
    // Arrange, Act and Assert
    assertFalse(NoopChannelPool.INSTANCE.offer(new EmbeddedChannel(), "Partition Key"));
  }

  /**
   * Test {@link NoopChannelPool#poll(Object)}.
   *
   * <p>Method under test: {@link NoopChannelPool#poll(Object)}
   */
  @Test
  @DisplayName("Test poll(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Channel NoopChannelPool.poll(Object)"})
  void testPoll() {
    // Arrange, Act and Assert
    assertNull(NoopChannelPool.INSTANCE.poll("Partition Key"));
  }

  /**
   * Test {@link NoopChannelPool#removeAll(Channel)}.
   *
   * <p>Method under test: {@link NoopChannelPool#removeAll(Channel)}
   */
  @Test
  @DisplayName("Test removeAll(Channel)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NoopChannelPool.removeAll(Channel)"})
  void testRemoveAll() {
    // Arrange, Act and Assert
    assertFalse(NoopChannelPool.INSTANCE.removeAll(new EmbeddedChannel()));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NoopChannelPool#destroy()}
   *   <li>{@link NoopChannelPool#flushPartitions(Predicate)}
   *   <li>{@link NoopChannelPool#isOpen()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoopChannelPool.destroy()",
    "void NoopChannelPool.flushPartitions(Predicate)",
    "boolean NoopChannelPool.isOpen()"
  })
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
   *
   * <p>Method under test: {@link NoopChannelPool#getIdleChannelCountPerHost()}
   */
  @Test
  @DisplayName("Test getIdleChannelCountPerHost()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map NoopChannelPool.getIdleChannelCountPerHost()"})
  void testGetIdleChannelCountPerHost() {
    // Arrange, Act and Assert
    assertTrue(NoopChannelPool.INSTANCE.getIdleChannelCountPerHost().isEmpty());
  }
}
