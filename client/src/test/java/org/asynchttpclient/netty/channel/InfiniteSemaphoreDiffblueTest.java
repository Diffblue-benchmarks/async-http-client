package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InfiniteSemaphore.acquire()", "void InfiniteSemaphore.acquire(int)",
      "void InfiniteSemaphore.acquireUninterruptibly()", "void InfiniteSemaphore.acquireUninterruptibly(int)",
      "boolean InfiniteSemaphore.isFair()", "void InfiniteSemaphore.reducePermits(int)",
      "void InfiniteSemaphore.release()", "void InfiniteSemaphore.release(int)"})
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

    // Assert
    assertTrue(infiniteSemaphore.isFair());
  }

  /**
   * Test {@link InfiniteSemaphore#tryAcquire()}.
   * <p>
   * Method under test: {@link InfiniteSemaphore#tryAcquire()}
   */
  @Test
  @DisplayName("Test tryAcquire()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InfiniteSemaphore.tryAcquire()"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InfiniteSemaphore.tryAcquire(int)"})
  void testTryAcquireWithPermits() {
    // Arrange, Act and Assert
    assertTrue(InfiniteSemaphore.INSTANCE.tryAcquire(1));
  }

  /**
   * Test {@link InfiniteSemaphore#tryAcquire(int, long, TimeUnit)} with {@code permits}, {@code timeout}, {@code unit}.
   * <p>
   * Method under test: {@link InfiniteSemaphore#tryAcquire(int, long, TimeUnit)}
   */
  @Test
  @DisplayName("Test tryAcquire(int, long, TimeUnit) with 'permits', 'timeout', 'unit'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InfiniteSemaphore.tryAcquire(int, long, TimeUnit)"})
  void testTryAcquireWithPermitsTimeoutUnit() {
    // Arrange, Act and Assert
    assertTrue(InfiniteSemaphore.INSTANCE.tryAcquire(1, 10L, TimeUnit.NANOSECONDS));
  }

  /**
   * Test {@link InfiniteSemaphore#tryAcquire(long, TimeUnit)} with {@code timeout}, {@code unit}.
   * <p>
   * Method under test: {@link InfiniteSemaphore#tryAcquire(long, TimeUnit)}
   */
  @Test
  @DisplayName("Test tryAcquire(long, TimeUnit) with 'timeout', 'unit'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InfiniteSemaphore.tryAcquire(long, TimeUnit)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int InfiniteSemaphore.availablePermits()"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int InfiniteSemaphore.drainPermits()"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Collection InfiniteSemaphore.getQueuedThreads()"})
  void testGetQueuedThreads() {
    // Arrange and Act
    Collection<Thread> actualQueuedThreads = InfiniteSemaphore.INSTANCE.getQueuedThreads();

    // Assert
    assertTrue(actualQueuedThreads instanceof List);
    assertTrue(actualQueuedThreads.isEmpty());
  }
}
