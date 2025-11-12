package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import org.asynchttpclient.exception.TooManyConnectionsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CombinedConnectionSemaphoreDiffblueTest {
  /**
   * Test {@link CombinedConnectionSemaphore#CombinedConnectionSemaphore(int, int, int)}.
   *
   * <p>Method under test: {@link CombinedConnectionSemaphore#CombinedConnectionSemaphore(int, int,
   * int)}
   */
  @Test
  @DisplayName("Test new CombinedConnectionSemaphore(int, int, int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CombinedConnectionSemaphore.<init>(int, int, int)"})
  void testNewCombinedConnectionSemaphore() {
    // Arrange, Act and Assert
    MaxConnectionSemaphore maxConnectionSemaphore =
        new CombinedConnectionSemaphore(3, 3, 10).globalMaxConnectionSemaphore;
    IOException ioException = maxConnectionSemaphore.tooManyConnections;
    assertTrue(ioException instanceof TooManyConnectionsException);
    assertEquals("Too many connections: 3", ioException.getLocalizedMessage());
    assertEquals("Too many connections: 3", ioException.getMessage());
    assertFalse(maxConnectionSemaphore.freeChannels.isFair());
  }

  /**
   * Test {@link CombinedConnectionSemaphore#CombinedConnectionSemaphore(int, int, int)}.
   *
   * <p>Method under test: {@link CombinedConnectionSemaphore#CombinedConnectionSemaphore(int, int,
   * int)}
   */
  @Test
  @DisplayName("Test new CombinedConnectionSemaphore(int, int, int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CombinedConnectionSemaphore.<init>(int, int, int)"})
  void testNewCombinedConnectionSemaphore2() {
    // Arrange, Act and Assert
    MaxConnectionSemaphore maxConnectionSemaphore =
        new CombinedConnectionSemaphore(0, 3, 10).globalMaxConnectionSemaphore;
    Semaphore semaphore = maxConnectionSemaphore.freeChannels;
    Collection<Thread> queuedThreads = ((InfiniteSemaphore) semaphore).getQueuedThreads();
    assertTrue(queuedThreads instanceof List);
    IOException ioException = maxConnectionSemaphore.tooManyConnections;
    assertTrue(ioException instanceof TooManyConnectionsException);
    assertTrue(semaphore instanceof InfiniteSemaphore);
    assertEquals("Too many connections: 0", ioException.getLocalizedMessage());
    assertEquals("Too many connections: 0", ioException.getMessage());
    assertTrue(queuedThreads.isEmpty());
    assertTrue(semaphore.isFair());
  }

  /**
   * Test {@link CombinedConnectionSemaphore#acquireChannelLock(Object)}.
   *
   * <p>Method under test: {@link CombinedConnectionSemaphore#acquireChannelLock(Object)}
   */
  @Test
  @DisplayName("Test acquireChannelLock(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CombinedConnectionSemaphore.acquireChannelLock(Object)"})
  void testAcquireChannelLock() throws IOException {
    // Arrange
    CombinedConnectionSemaphore combinedConnectionSemaphore =
        new CombinedConnectionSemaphore(3, 3, 0);

    // Act
    combinedConnectionSemaphore.acquireChannelLock("Partition Key");

    // Assert
    ConcurrentHashMap<Object, Semaphore> objectSemaphoreMap =
        combinedConnectionSemaphore.freeChannelsPerHost;
    assertEquals(1, objectSemaphoreMap.size());
    Semaphore getResult = objectSemaphoreMap.get("Partition Key");
    assertEquals(0, getResult.getQueueLength());
    assertFalse(getResult.hasQueuedThreads());
    assertFalse(getResult.isFair());
  }

  /**
   * Test {@link CombinedConnectionSemaphore#acquireGlobal(Object)}.
   *
   * <p>Method under test: {@link CombinedConnectionSemaphore#acquireGlobal(Object)}
   */
  @Test
  @DisplayName("Test acquireGlobal(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long CombinedConnectionSemaphore.acquireGlobal(Object)"})
  void testAcquireGlobal() throws IOException {
    // Arrange, Act and Assert
    assertEquals(0L, new CombinedConnectionSemaphore(3, 3, 10).acquireGlobal("Partition Key"));
  }

  /**
   * Test {@link CombinedConnectionSemaphore#releaseChannelLock(Object)}.
   *
   * <p>Method under test: {@link CombinedConnectionSemaphore#releaseChannelLock(Object)}
   */
  @Test
  @DisplayName("Test releaseChannelLock(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CombinedConnectionSemaphore.releaseChannelLock(Object)"})
  void testReleaseChannelLock() {
    // Arrange
    CombinedConnectionSemaphore combinedConnectionSemaphore =
        new CombinedConnectionSemaphore(3, 3, 10);

    // Act
    combinedConnectionSemaphore.releaseChannelLock("Partition Key");

    // Assert
    ConcurrentHashMap<Object, Semaphore> objectSemaphoreMap =
        combinedConnectionSemaphore.freeChannelsPerHost;
    assertEquals(1, objectSemaphoreMap.size());
    Semaphore getResult = objectSemaphoreMap.get("Partition Key");
    assertEquals(0, getResult.getQueueLength());
    assertFalse(getResult.hasQueuedThreads());
    assertFalse(getResult.isFair());
  }

  /**
   * Test {@link CombinedConnectionSemaphore#releaseChannelLock(Object)}.
   *
   * <p>Method under test: {@link CombinedConnectionSemaphore#releaseChannelLock(Object)}
   */
  @Test
  @DisplayName("Test releaseChannelLock(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CombinedConnectionSemaphore.releaseChannelLock(Object)"})
  void testReleaseChannelLock2() {
    // Arrange
    CombinedConnectionSemaphore combinedConnectionSemaphore =
        new CombinedConnectionSemaphore(3, 0, 10);

    // Act
    combinedConnectionSemaphore.releaseChannelLock("Partition Key");

    // Assert that nothing has changed
    assertTrue(combinedConnectionSemaphore.freeChannelsPerHost.isEmpty());
  }
}
