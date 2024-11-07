package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import org.asynchttpclient.exception.TooManyConnectionsPerHostException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PerHostConnectionSemaphoreDiffblueTest {
  /**
   * Test {@link PerHostConnectionSemaphore#PerHostConnectionSemaphore(int, int)}.
   * <p>
   * Method under test:
   * {@link PerHostConnectionSemaphore#PerHostConnectionSemaphore(int, int)}
   */
  @Test
  @DisplayName("Test new PerHostConnectionSemaphore(int, int)")
  void testNewPerHostConnectionSemaphore() {
    // Arrange and Act
    PerHostConnectionSemaphore actualPerHostConnectionSemaphore = new PerHostConnectionSemaphore(3, 10);

    // Assert
    IOException ioException = actualPerHostConnectionSemaphore.tooManyConnectionsPerHost;
    assertTrue(ioException instanceof TooManyConnectionsPerHostException);
    assertEquals("Too many connections: 3", ioException.getLocalizedMessage());
    assertEquals("Too many connections: 3", ioException.getMessage());
    assertNull(ioException.getCause());
    assertEquals(0, ioException.getSuppressed().length);
    assertEquals(10, actualPerHostConnectionSemaphore.acquireTimeout);
    assertEquals(3, actualPerHostConnectionSemaphore.maxConnectionsPerHost);
    assertTrue(actualPerHostConnectionSemaphore.freeChannelsPerHost.isEmpty());
  }

  /**
   * Test {@link PerHostConnectionSemaphore#acquireChannelLock(Object)}.
   * <p>
   * Method under test:
   * {@link PerHostConnectionSemaphore#acquireChannelLock(Object)}
   */
  @Test
  @DisplayName("Test acquireChannelLock(Object)")
  void testAcquireChannelLock() throws IOException {
    // Arrange
    PerHostConnectionSemaphore perHostConnectionSemaphore = new PerHostConnectionSemaphore(3, 10);

    // Act
    perHostConnectionSemaphore.acquireChannelLock("Partition Key");

    // Assert
    ConcurrentHashMap<Object, Semaphore> objectSemaphoreMap = perHostConnectionSemaphore.freeChannelsPerHost;
    assertEquals(1, objectSemaphoreMap.size());
    Semaphore getResult = objectSemaphoreMap.get("Partition Key");
    assertEquals(0, getResult.getQueueLength());
    assertFalse(getResult.hasQueuedThreads());
    assertFalse(getResult.isFair());
  }

  /**
   * Test {@link PerHostConnectionSemaphore#acquireChannelLock(Object)}.
   * <p>
   * Method under test:
   * {@link PerHostConnectionSemaphore#acquireChannelLock(Object)}
   */
  @Test
  @DisplayName("Test acquireChannelLock(Object)")
  void testAcquireChannelLock2() throws IOException {
    // Arrange
    PerHostConnectionSemaphore perHostConnectionSemaphore = new PerHostConnectionSemaphore(0, 10);

    // Act
    perHostConnectionSemaphore.acquireChannelLock("Partition Key");

    // Assert that nothing has changed
    assertTrue(perHostConnectionSemaphore.freeChannelsPerHost.isEmpty());
  }

  /**
   * Test {@link PerHostConnectionSemaphore#releaseChannelLock(Object)}.
   * <p>
   * Method under test:
   * {@link PerHostConnectionSemaphore#releaseChannelLock(Object)}
   */
  @Test
  @DisplayName("Test releaseChannelLock(Object)")
  void testReleaseChannelLock() {
    // Arrange
    PerHostConnectionSemaphore perHostConnectionSemaphore = new PerHostConnectionSemaphore(3, 10);

    // Act
    perHostConnectionSemaphore.releaseChannelLock("Partition Key");

    // Assert
    ConcurrentHashMap<Object, Semaphore> objectSemaphoreMap = perHostConnectionSemaphore.freeChannelsPerHost;
    assertEquals(1, objectSemaphoreMap.size());
    Semaphore getResult = objectSemaphoreMap.get("Partition Key");
    assertEquals(0, getResult.getQueueLength());
    assertFalse(getResult.hasQueuedThreads());
    assertFalse(getResult.isFair());
  }

  /**
   * Test {@link PerHostConnectionSemaphore#releaseChannelLock(Object)}.
   * <p>
   * Method under test:
   * {@link PerHostConnectionSemaphore#releaseChannelLock(Object)}
   */
  @Test
  @DisplayName("Test releaseChannelLock(Object)")
  void testReleaseChannelLock2() {
    // Arrange
    PerHostConnectionSemaphore perHostConnectionSemaphore = new PerHostConnectionSemaphore(0, 10);

    // Act
    perHostConnectionSemaphore.releaseChannelLock("Partition Key");

    // Assert that nothing has changed
    assertTrue(perHostConnectionSemaphore.freeChannelsPerHost.isEmpty());
  }

  /**
   * Test {@link PerHostConnectionSemaphore#getFreeConnectionsForHost(Object)}.
   * <p>
   * Method under test:
   * {@link PerHostConnectionSemaphore#getFreeConnectionsForHost(Object)}
   */
  @Test
  @DisplayName("Test getFreeConnectionsForHost(Object)")
  void testGetFreeConnectionsForHost() {
    // Arrange
    PerHostConnectionSemaphore perHostConnectionSemaphore = new PerHostConnectionSemaphore(0, 10);

    // Act
    Semaphore actualFreeConnectionsForHost = perHostConnectionSemaphore.getFreeConnectionsForHost("Partition Key");

    // Assert
    assertTrue(perHostConnectionSemaphore.freeChannelsPerHost.isEmpty());
    assertSame(((InfiniteSemaphore) actualFreeConnectionsForHost).INSTANCE, actualFreeConnectionsForHost);
  }

  /**
   * Test {@link PerHostConnectionSemaphore#getFreeConnectionsForHost(Object)}.
   * <ul>
   *   <li>Then return QueueLength is zero.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link PerHostConnectionSemaphore#getFreeConnectionsForHost(Object)}
   */
  @Test
  @DisplayName("Test getFreeConnectionsForHost(Object); then return QueueLength is zero")
  void testGetFreeConnectionsForHost_thenReturnQueueLengthIsZero() {
    // Arrange
    PerHostConnectionSemaphore perHostConnectionSemaphore = new PerHostConnectionSemaphore(3, 10);

    // Act
    Semaphore actualFreeConnectionsForHost = perHostConnectionSemaphore.getFreeConnectionsForHost("Partition Key");

    // Assert
    assertEquals(0, actualFreeConnectionsForHost.getQueueLength());
    ConcurrentHashMap<Object, Semaphore> objectSemaphoreMap = perHostConnectionSemaphore.freeChannelsPerHost;
    assertEquals(1, objectSemaphoreMap.size());
    assertFalse(actualFreeConnectionsForHost.hasQueuedThreads());
    assertFalse(actualFreeConnectionsForHost.isFair());
    assertTrue(objectSemaphoreMap.containsKey("Partition Key"));
  }
}
