package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Semaphore;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.exception.TooManyConnectionsException;
import org.asynchttpclient.exception.TooManyConnectionsPerHostException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DefaultConnectionSemaphoreFactoryDiffblueTest {
  /**
   * Test {@link DefaultConnectionSemaphoreFactory#newConnectionSemaphore(AsyncHttpClientConfig)}.
   *
   * <p>Method under test: {@link
   * DefaultConnectionSemaphoreFactory#newConnectionSemaphore(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test newConnectionSemaphore(AsyncHttpClientConfig)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectionSemaphore DefaultConnectionSemaphoreFactory.newConnectionSemaphore(AsyncHttpClientConfig)"
  })
  void testNewConnectionSemaphore() {
    // Arrange
    DefaultConnectionSemaphoreFactory defaultConnectionSemaphoreFactory =
        new DefaultConnectionSemaphoreFactory();

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getMaxConnections()).thenReturn(1);
    when(config.getMaxConnectionsPerHost()).thenReturn(1);
    when(config.getAcquireFreeChannelTimeout()).thenReturn(10);

    // Act
    ConnectionSemaphore actualNewConnectionSemaphoreResult =
        defaultConnectionSemaphoreFactory.newConnectionSemaphore(config);

    // Assert
    verify(config).getAcquireFreeChannelTimeout();
    verify(config).getMaxConnections();
    verify(config).getMaxConnectionsPerHost();
    IOException ioException =
        ((CombinedConnectionSemaphore) actualNewConnectionSemaphoreResult)
            .tooManyConnectionsPerHost;
    assertTrue(ioException instanceof TooManyConnectionsPerHostException);
    assertTrue(actualNewConnectionSemaphoreResult instanceof CombinedConnectionSemaphore);
    assertEquals("Too many connections: 1", ioException.getLocalizedMessage());
    assertEquals("Too many connections: 1", ioException.getMessage());
    assertNull(ioException.getCause());
    assertEquals(0, ioException.getSuppressed().length);
    assertEquals(
        1,
        ((CombinedConnectionSemaphore) actualNewConnectionSemaphoreResult).maxConnectionsPerHost);
    assertEquals(
        10,
        ((CombinedConnectionSemaphore) actualNewConnectionSemaphoreResult)
            .globalMaxConnectionSemaphore
            .acquireTimeout);
    assertTrue(
        ((CombinedConnectionSemaphore) actualNewConnectionSemaphoreResult)
            .freeChannelsPerHost.isEmpty());
  }

  /**
   * Test {@link DefaultConnectionSemaphoreFactory#newConnectionSemaphore(AsyncHttpClientConfig)}.
   *
   * <p>Method under test: {@link
   * DefaultConnectionSemaphoreFactory#newConnectionSemaphore(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test newConnectionSemaphore(AsyncHttpClientConfig)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectionSemaphore DefaultConnectionSemaphoreFactory.newConnectionSemaphore(AsyncHttpClientConfig)"
  })
  void testNewConnectionSemaphore2() {
    // Arrange
    DefaultConnectionSemaphoreFactory defaultConnectionSemaphoreFactory =
        new DefaultConnectionSemaphoreFactory();

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getMaxConnections()).thenReturn(1);
    when(config.getMaxConnectionsPerHost()).thenReturn(0);
    when(config.getAcquireFreeChannelTimeout()).thenReturn(10);

    // Act
    ConnectionSemaphore actualNewConnectionSemaphoreResult =
        defaultConnectionSemaphoreFactory.newConnectionSemaphore(config);

    // Assert
    verify(config).getAcquireFreeChannelTimeout();
    verify(config).getMaxConnections();
    verify(config).getMaxConnectionsPerHost();
    IOException ioException =
        ((MaxConnectionSemaphore) actualNewConnectionSemaphoreResult).tooManyConnections;
    assertTrue(ioException instanceof TooManyConnectionsException);
    assertTrue(actualNewConnectionSemaphoreResult instanceof MaxConnectionSemaphore);
    assertEquals("Too many connections: 1", ioException.getLocalizedMessage());
    assertEquals("Too many connections: 1", ioException.getMessage());
    assertNull(ioException.getCause());
    Semaphore semaphore =
        ((MaxConnectionSemaphore) actualNewConnectionSemaphoreResult).freeChannels;
    assertEquals(0, semaphore.getQueueLength());
    assertEquals(0, ioException.getSuppressed().length);
    assertFalse(semaphore.hasQueuedThreads());
    assertFalse(semaphore.isFair());
  }

  /**
   * Test {@link DefaultConnectionSemaphoreFactory#newConnectionSemaphore(AsyncHttpClientConfig)}.
   *
   * <p>Method under test: {@link
   * DefaultConnectionSemaphoreFactory#newConnectionSemaphore(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test newConnectionSemaphore(AsyncHttpClientConfig)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectionSemaphore DefaultConnectionSemaphoreFactory.newConnectionSemaphore(AsyncHttpClientConfig)"
  })
  void testNewConnectionSemaphore3() {
    // Arrange
    DefaultConnectionSemaphoreFactory defaultConnectionSemaphoreFactory =
        new DefaultConnectionSemaphoreFactory();

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getMaxConnections()).thenReturn(0);
    when(config.getMaxConnectionsPerHost()).thenReturn(1);
    when(config.getAcquireFreeChannelTimeout()).thenReturn(10);

    // Act
    ConnectionSemaphore actualNewConnectionSemaphoreResult =
        defaultConnectionSemaphoreFactory.newConnectionSemaphore(config);

    // Assert
    verify(config).getAcquireFreeChannelTimeout();
    verify(config).getMaxConnections();
    verify(config).getMaxConnectionsPerHost();
    MaxConnectionSemaphore maxConnectionSemaphore =
        ((CombinedConnectionSemaphore) actualNewConnectionSemaphoreResult)
            .globalMaxConnectionSemaphore;
    Semaphore semaphore = maxConnectionSemaphore.freeChannels;
    Collection<Thread> queuedThreads = ((InfiniteSemaphore) semaphore).getQueuedThreads();
    assertTrue(queuedThreads instanceof List);
    IOException ioException = maxConnectionSemaphore.tooManyConnections;
    assertTrue(ioException instanceof TooManyConnectionsException);
    assertTrue(actualNewConnectionSemaphoreResult instanceof CombinedConnectionSemaphore);
    assertTrue(semaphore instanceof InfiniteSemaphore);
    assertEquals("Too many connections: 0", ioException.getLocalizedMessage());
    assertEquals("Too many connections: 0", ioException.getMessage());
    assertTrue(queuedThreads.isEmpty());
    assertTrue(semaphore.isFair());
  }

  /**
   * Test {@link DefaultConnectionSemaphoreFactory#newConnectionSemaphore(AsyncHttpClientConfig)}.
   *
   * <ul>
   *   <li>Then return {@link NoopConnectionSemaphore}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DefaultConnectionSemaphoreFactory#newConnectionSemaphore(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName(
      "Test newConnectionSemaphore(AsyncHttpClientConfig); then return NoopConnectionSemaphore")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ConnectionSemaphore DefaultConnectionSemaphoreFactory.newConnectionSemaphore(AsyncHttpClientConfig)"
  })
  void testNewConnectionSemaphore_thenReturnNoopConnectionSemaphore() {
    // Arrange
    DefaultConnectionSemaphoreFactory defaultConnectionSemaphoreFactory =
        new DefaultConnectionSemaphoreFactory();

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getMaxConnections()).thenReturn(0);
    when(config.getMaxConnectionsPerHost()).thenReturn(0);
    when(config.getAcquireFreeChannelTimeout()).thenReturn(10);

    // Act
    ConnectionSemaphore actualNewConnectionSemaphoreResult =
        defaultConnectionSemaphoreFactory.newConnectionSemaphore(config);

    // Assert
    verify(config).getAcquireFreeChannelTimeout();
    verify(config).getMaxConnections();
    verify(config).getMaxConnectionsPerHost();
    assertTrue(actualNewConnectionSemaphoreResult instanceof NoopConnectionSemaphore);
  }
}
