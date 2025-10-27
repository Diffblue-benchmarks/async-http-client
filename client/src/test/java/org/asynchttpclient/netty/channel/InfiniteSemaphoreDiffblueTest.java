package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class InfiniteSemaphoreDiffblueTest {
  /**
   * Method under test: {@link InfiniteSemaphore#tryAcquire()}
   */
  @Test
  void testTryAcquire() {
    // Arrange, Act and Assert
    assertTrue(InfiniteSemaphore.INSTANCE.tryAcquire());
    assertTrue(InfiniteSemaphore.INSTANCE.tryAcquire(1));
    assertTrue(InfiniteSemaphore.INSTANCE.tryAcquire(1, 10L, TimeUnit.NANOSECONDS));
    assertTrue(InfiniteSemaphore.INSTANCE.tryAcquire(10L, TimeUnit.NANOSECONDS));
  }

  /**
   * Method under test: {@link InfiniteSemaphore#availablePermits()}
   */
  @Test
  void testAvailablePermits() {
    // Arrange, Act and Assert
    assertEquals(Integer.MAX_VALUE, InfiniteSemaphore.INSTANCE.availablePermits());
  }

  /**
   * Method under test: {@link InfiniteSemaphore#drainPermits()}
   */
  @Test
  void testDrainPermits() {
    // Arrange, Act and Assert
    assertEquals(Integer.MAX_VALUE, InfiniteSemaphore.INSTANCE.drainPermits());
  }

  /**
   * Method under test: {@link InfiniteSemaphore#getQueuedThreads()}
   */
  @Test
  void testGetQueuedThreads() {
    // Arrange and Act
    Collection<Thread> actualQueuedThreads = InfiniteSemaphore.INSTANCE.getQueuedThreads();

    // Assert
    assertTrue(actualQueuedThreads instanceof List);
    assertTrue(actualQueuedThreads.isEmpty());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link InfiniteSemaphore#acquire()}
   *   <li>{@link InfiniteSemaphore#acquire(int)}
   *   <li>{@link InfiniteSemaphore#acquireUninterruptibly()}
   *   <li>{@link InfiniteSemaphore#acquireUninterruptibly(int)}
   *   <li>{@link InfiniteSemaphore#reducePermits(int)}
   *   <li>{@link InfiniteSemaphore#release()}
   *   <li>{@link InfiniteSemaphore#release(int)}
   *   <li>{@link InfiniteSemaphore#isFair()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    InfiniteSemaphore infiniteSemaphore = InfiniteSemaphore.INSTANCE;

    // Act
    infiniteSemaphore.acquire();
    infiniteSemaphore.acquire(1);
    infiniteSemaphore.acquireUninterruptibly();
    infiniteSemaphore.acquireUninterruptibly(1);
    infiniteSemaphore.reducePermits(1);
    infiniteSemaphore.release();
    infiniteSemaphore.release(1);

    // Assert that nothing has changed
    assertTrue(infiniteSemaphore.isFair());
  }
}
