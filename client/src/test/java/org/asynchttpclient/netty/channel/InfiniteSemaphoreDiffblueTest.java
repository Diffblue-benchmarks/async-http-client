package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InfiniteSemaphoreDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
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
  @DisplayName("Test getters and setters")
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

  /**
   * Test {@link InfiniteSemaphore#tryAcquire()}.
   * <p>
   * Method under test: {@link InfiniteSemaphore#tryAcquire()}
   */
  @Test
  @DisplayName("Test tryAcquire()")
  void testTryAcquire() {
    // Arrange, Act and Assert
    assertTrue(InfiniteSemaphore.INSTANCE.tryAcquire());
  }

  /**
   * Test {@link InfiniteSemaphore#tryAcquire(int)} with {@code permits}.
   * <p>
   * Method under test: {@link InfiniteSemaphore#tryAcquire(int)}
   */
  @Test
  @DisplayName("Test tryAcquire(int) with 'permits'")
  void testTryAcquireWithPermits() {
    // Arrange, Act and Assert
    assertTrue(InfiniteSemaphore.INSTANCE.tryAcquire(1));
  }

  /**
   * Test {@link InfiniteSemaphore#tryAcquire(int, long, TimeUnit)} with
   * {@code permits}, {@code timeout}, {@code unit}.
   * <p>
   * Method under test: {@link InfiniteSemaphore#tryAcquire(int, long, TimeUnit)}
   */
  @Test
  @DisplayName("Test tryAcquire(int, long, TimeUnit) with 'permits', 'timeout', 'unit'")
  void testTryAcquireWithPermitsTimeoutUnit() {
    // Arrange, Act and Assert
    assertTrue(InfiniteSemaphore.INSTANCE.tryAcquire(1, 10L, TimeUnit.NANOSECONDS));
  }

  /**
   * Test {@link InfiniteSemaphore#tryAcquire(long, TimeUnit)} with
   * {@code timeout}, {@code unit}.
   * <p>
   * Method under test: {@link InfiniteSemaphore#tryAcquire(long, TimeUnit)}
   */
  @Test
  @DisplayName("Test tryAcquire(long, TimeUnit) with 'timeout', 'unit'")
  void testTryAcquireWithTimeoutUnit() {
    // Arrange, Act and Assert
    assertTrue(InfiniteSemaphore.INSTANCE.tryAcquire(10L, TimeUnit.NANOSECONDS));
  }

  /**
   * Test {@link InfiniteSemaphore#availablePermits()}.
   * <p>
   * Method under test: {@link InfiniteSemaphore#availablePermits()}
   */
  @Test
  @DisplayName("Test availablePermits()")
  void testAvailablePermits() {
    // Arrange, Act and Assert
    assertEquals(Integer.MAX_VALUE, InfiniteSemaphore.INSTANCE.availablePermits());
  }

  /**
   * Test {@link InfiniteSemaphore#drainPermits()}.
   * <p>
   * Method under test: {@link InfiniteSemaphore#drainPermits()}
   */
  @Test
  @DisplayName("Test drainPermits()")
  void testDrainPermits() {
    // Arrange, Act and Assert
    assertEquals(Integer.MAX_VALUE, InfiniteSemaphore.INSTANCE.drainPermits());
  }

  /**
   * Test {@link InfiniteSemaphore#getQueuedThreads()}.
   * <p>
   * Method under test: {@link InfiniteSemaphore#getQueuedThreads()}
   */
  @Test
  @DisplayName("Test getQueuedThreads()")
  void testGetQueuedThreads() {
    // Arrange and Act
    Collection<Thread> actualQueuedThreads = InfiniteSemaphore.INSTANCE.getQueuedThreads();

    // Assert
    assertTrue(actualQueuedThreads instanceof List);
    assertTrue(actualQueuedThreads.isEmpty());
  }
}
