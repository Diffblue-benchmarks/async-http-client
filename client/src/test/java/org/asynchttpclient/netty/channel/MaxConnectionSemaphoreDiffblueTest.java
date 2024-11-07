package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Semaphore;
import org.asynchttpclient.exception.TooManyConnectionsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MaxConnectionSemaphoreDiffblueTest {
  /**
   * Test {@link MaxConnectionSemaphore#MaxConnectionSemaphore(int, int)}.
   * <p>
   * Method under test:
   * {@link MaxConnectionSemaphore#MaxConnectionSemaphore(int, int)}
   */
  @Test
  @DisplayName("Test new MaxConnectionSemaphore(int, int)")
  void testNewMaxConnectionSemaphore() {
    // Arrange and Act
    MaxConnectionSemaphore actualMaxConnectionSemaphore = new MaxConnectionSemaphore(3, 10);

    // Assert
    IOException ioException = actualMaxConnectionSemaphore.tooManyConnections;
    assertTrue(ioException instanceof TooManyConnectionsException);
    assertEquals("Too many connections: 3", ioException.getLocalizedMessage());
    assertEquals("Too many connections: 3", ioException.getMessage());
    assertNull(ioException.getCause());
    Semaphore semaphore = actualMaxConnectionSemaphore.freeChannels;
    assertEquals(0, semaphore.getQueueLength());
    assertEquals(0, ioException.getSuppressed().length);
    assertEquals(10, actualMaxConnectionSemaphore.acquireTimeout);
    assertFalse(semaphore.hasQueuedThreads());
    assertFalse(semaphore.isFair());
  }

  /**
   * Test {@link MaxConnectionSemaphore#MaxConnectionSemaphore(int, int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then {@link MaxConnectionSemaphore#freeChannels} QueuedThreads return
   * {@link List}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MaxConnectionSemaphore#MaxConnectionSemaphore(int, int)}
   */
  @Test
  @DisplayName("Test new MaxConnectionSemaphore(int, int); when zero; then freeChannels QueuedThreads return List")
  void testNewMaxConnectionSemaphore_whenZero_thenFreeChannelsQueuedThreadsReturnList() {
    // Arrange and Act
    MaxConnectionSemaphore actualMaxConnectionSemaphore = new MaxConnectionSemaphore(0, 10);

    // Assert
    Semaphore semaphore = actualMaxConnectionSemaphore.freeChannels;
    Collection<Thread> queuedThreads = ((InfiniteSemaphore) semaphore).getQueuedThreads();
    assertTrue(queuedThreads instanceof List);
    IOException ioException = actualMaxConnectionSemaphore.tooManyConnections;
    assertTrue(ioException instanceof TooManyConnectionsException);
    assertTrue(semaphore instanceof InfiniteSemaphore);
    assertEquals("Too many connections: 0", ioException.getLocalizedMessage());
    assertEquals("Too many connections: 0", ioException.getMessage());
    assertTrue(queuedThreads.isEmpty());
    assertTrue(semaphore.isFair());
  }
}
