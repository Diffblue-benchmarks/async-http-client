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
import org.junit.jupiter.api.Test;

class PerHostConnectionSemaphoreDiffblueTest {
  /**
   * Method under test:
   * {@link PerHostConnectionSemaphore#acquireChannelLock(Object)}
   */
  @Test
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
   * Method under test:
   * {@link PerHostConnectionSemaphore#releaseChannelLock(Object)}
   */
  @Test
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
   * Method under test:
   * {@link PerHostConnectionSemaphore#getFreeConnectionsForHost(Object)}
   */
  @Test
  void testGetFreeConnectionsForHost() {
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

  /**
   * Method under test:
   * {@link PerHostConnectionSemaphore#getFreeConnectionsForHost(Object)}
   */
  @Test
  void testGetFreeConnectionsForHost2() {
    // Arrange and Act
    Semaphore actualFreeConnectionsForHost = (new PerHostConnectionSemaphore(0, 10))
        .getFreeConnectionsForHost("Partition Key");

    // Assert
    assertSame(((InfiniteSemaphore) actualFreeConnectionsForHost).INSTANCE, actualFreeConnectionsForHost);
  }

  /**
   * Method under test:
   * {@link PerHostConnectionSemaphore#PerHostConnectionSemaphore(int, int)}
   */
  @Test
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
}
