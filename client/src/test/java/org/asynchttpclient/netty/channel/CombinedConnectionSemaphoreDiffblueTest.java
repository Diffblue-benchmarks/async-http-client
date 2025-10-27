package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import org.asynchttpclient.exception.TooManyConnectionsException;
import org.asynchttpclient.exception.TooManyConnectionsPerHostException;
import org.junit.jupiter.api.Test;

class CombinedConnectionSemaphoreDiffblueTest {
  /**
   * Method under test:
   * {@link CombinedConnectionSemaphore#acquireChannelLock(Object)}
   */
  @Test
  void testAcquireChannelLock() throws IOException {
    // Arrange
    CombinedConnectionSemaphore combinedConnectionSemaphore = new CombinedConnectionSemaphore(3, 3, 0);

    // Act
    combinedConnectionSemaphore.acquireChannelLock("Partition Key");

    // Assert
    ConcurrentHashMap<Object, Semaphore> objectSemaphoreMap = combinedConnectionSemaphore.freeChannelsPerHost;
    assertEquals(1, objectSemaphoreMap.size());
    Semaphore getResult = objectSemaphoreMap.get("Partition Key");
    assertEquals(0, getResult.getQueueLength());
    assertFalse(getResult.hasQueuedThreads());
    assertFalse(getResult.isFair());
  }

  /**
   * Method under test: {@link CombinedConnectionSemaphore#acquireGlobal(Object)}
   */
  @Test
  void testAcquireGlobal() throws IOException {
    // Arrange, Act and Assert
    assertEquals(0L, (new CombinedConnectionSemaphore(3, 3, 10)).acquireGlobal("Partition Key"));
  }

  /**
   * Method under test:
   * {@link CombinedConnectionSemaphore#releaseChannelLock(Object)}
   */
  @Test
  void testReleaseChannelLock() {
    // Arrange
    CombinedConnectionSemaphore combinedConnectionSemaphore = new CombinedConnectionSemaphore(3, 3, 10);

    // Act
    combinedConnectionSemaphore.releaseChannelLock("Partition Key");

    // Assert
    ConcurrentHashMap<Object, Semaphore> objectSemaphoreMap = combinedConnectionSemaphore.freeChannelsPerHost;
    assertEquals(1, objectSemaphoreMap.size());
    Semaphore getResult = objectSemaphoreMap.get("Partition Key");
    assertEquals(0, getResult.getQueueLength());
    assertFalse(getResult.hasQueuedThreads());
    assertFalse(getResult.isFair());
  }

  /**
   * Method under test:
   * {@link CombinedConnectionSemaphore#CombinedConnectionSemaphore(int, int, int)}
   */
  @Test
  void testNewCombinedConnectionSemaphore() {
    // Arrange and Act
    CombinedConnectionSemaphore actualCombinedConnectionSemaphore = new CombinedConnectionSemaphore(3, 3, 10);

    // Assert
    MaxConnectionSemaphore maxConnectionSemaphore = actualCombinedConnectionSemaphore.globalMaxConnectionSemaphore;
    IOException ioException = maxConnectionSemaphore.tooManyConnections;
    assertTrue(ioException instanceof TooManyConnectionsException);
    IOException ioException2 = actualCombinedConnectionSemaphore.tooManyConnectionsPerHost;
    assertTrue(ioException2 instanceof TooManyConnectionsPerHostException);
    assertEquals("Too many connections: 3", ioException.getLocalizedMessage());
    assertEquals("Too many connections: 3", ioException2.getLocalizedMessage());
    assertEquals("Too many connections: 3", ioException.getMessage());
    assertEquals("Too many connections: 3", ioException2.getMessage());
    assertNull(ioException.getCause());
    assertNull(ioException2.getCause());
    Semaphore semaphore = maxConnectionSemaphore.freeChannels;
    assertEquals(0, semaphore.getQueueLength());
    Throwable[] suppressed = ioException2.getSuppressed();
    assertEquals(0, suppressed.length);
    assertEquals(10, maxConnectionSemaphore.acquireTimeout);
    assertEquals(10, actualCombinedConnectionSemaphore.acquireTimeout);
    assertEquals(3, actualCombinedConnectionSemaphore.maxConnectionsPerHost);
    assertFalse(semaphore.hasQueuedThreads());
    assertFalse(semaphore.isFair());
    assertTrue(actualCombinedConnectionSemaphore.freeChannelsPerHost.isEmpty());
    assertSame(suppressed, ioException.getSuppressed());
  }

  /**
   * Method under test:
   * {@link CombinedConnectionSemaphore#CombinedConnectionSemaphore(int, int, int)}
   */
  @Test
  void testNewCombinedConnectionSemaphore2() {
    // Arrange and Act
    CombinedConnectionSemaphore actualCombinedConnectionSemaphore = new CombinedConnectionSemaphore(0, 3, 10);

    // Assert
    MaxConnectionSemaphore maxConnectionSemaphore = actualCombinedConnectionSemaphore.globalMaxConnectionSemaphore;
    Semaphore semaphore = maxConnectionSemaphore.freeChannels;
    Collection<Thread> queuedThreads = ((InfiniteSemaphore) semaphore).getQueuedThreads();
    assertTrue(queuedThreads instanceof List);
    IOException ioException = maxConnectionSemaphore.tooManyConnections;
    assertTrue(ioException instanceof TooManyConnectionsException);
    IOException ioException2 = actualCombinedConnectionSemaphore.tooManyConnectionsPerHost;
    assertTrue(ioException2 instanceof TooManyConnectionsPerHostException);
    assertTrue(semaphore instanceof InfiniteSemaphore);
    assertEquals("Too many connections: 0", ioException.getLocalizedMessage());
    assertEquals("Too many connections: 0", ioException.getMessage());
    assertEquals("Too many connections: 3", ioException2.getLocalizedMessage());
    assertEquals("Too many connections: 3", ioException2.getMessage());
    assertNull(ioException.getCause());
    assertNull(ioException2.getCause());
    assertEquals(0, semaphore.getQueueLength());
    Throwable[] suppressed = ioException2.getSuppressed();
    assertEquals(0, suppressed.length);
    assertEquals(10, maxConnectionSemaphore.acquireTimeout);
    assertEquals(10, actualCombinedConnectionSemaphore.acquireTimeout);
    assertEquals(3, actualCombinedConnectionSemaphore.maxConnectionsPerHost);
    assertFalse(semaphore.hasQueuedThreads());
    assertTrue(queuedThreads.isEmpty());
    assertTrue(actualCombinedConnectionSemaphore.freeChannelsPerHost.isEmpty());
    assertTrue(semaphore.isFair());
    assertSame(suppressed, ioException.getSuppressed());
  }
}
