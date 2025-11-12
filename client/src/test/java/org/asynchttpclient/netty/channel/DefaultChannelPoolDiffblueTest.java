package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.netty.channel.DefaultChannelPool.PoolLeaseStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DefaultChannelPoolDiffblueTest {
  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer, Duration)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer,
   * Duration)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(Duration, Duration, Timer, Duration)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(Duration, Duration, Timer, Duration)"})
  void testNewDefaultChannelPool() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(1L);
    Duration connectionTtl = Duration.ofSeconds(1L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS, 1000);

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(maxIdleTime, connectionTtl, nettyTimer, Duration.ofSeconds(-1L));

    // Assert
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer, Duration)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer,
   * Duration)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(Duration, Duration, Timer, Duration)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(Duration, Duration, Timer, Duration)"})
  void testNewDefaultChannelPool2() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(1L);
    Duration connectionTtl = Duration.ofSeconds(-1L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS, 1000);

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(maxIdleTime, connectionTtl, nettyTimer, Duration.ofSeconds(-1L));

    // Assert
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer, Duration)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer,
   * Duration)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(Duration, Duration, Timer, Duration)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(Duration, Duration, Timer, Duration)"})
  void testNewDefaultChannelPool3() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(1L);
    Duration connectionTtl = Duration.ofSeconds(-1L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(maxIdleTime, connectionTtl, nettyTimer, Duration.ofSeconds(-1L));

    // Assert
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer, Duration)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer,
   * Duration)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(Duration, Duration, Timer, Duration)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(Duration, Duration, Timer, Duration)"})
  void testNewDefaultChannelPool4() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(1L);
    Duration connectionTtl = Duration.ofSeconds(-1L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer(1000L, TimeUnit.SECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(maxIdleTime, connectionTtl, nettyTimer, Duration.ofSeconds(-1L));

    // Assert
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool5() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1L, TimeUnit.NANOSECONDS, 1);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool6() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1L, TimeUnit.MILLISECONDS, 1);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool7() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1L, TimeUnit.HOURS, 1);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool8() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1000L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool9() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1000L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool10() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(0L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool11() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1000L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1000L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool12() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1000L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.MILLISECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool13() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1000L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.SECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool14() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(0L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.MICROSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool15() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.MILLISECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool16() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1000L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(0L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.MILLISECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool17() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.SECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool18() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(0L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(2147483647L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool19() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(0L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.MICROSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool20() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1000L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1000L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.SECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool21() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.MILLISECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool22() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1000L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(0L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.MILLISECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool23() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(2147483647L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool24() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(2147483647L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(2147483647L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName("Test new DefaultChannelPool(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool25() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(2147483647L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS, 1000);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, PoolLeaseStrategy, Timer,
   * Duration)}.
   *
   * <ul>
   *   <li>Given ofEpochDay one thousand.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration,
   * PoolLeaseStrategy, Timer, Duration)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(Duration, Duration, PoolLeaseStrategy, Timer, Duration); given ofEpochDay one thousand")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultChannelPool.<init>(Duration, Duration, PoolLeaseStrategy, Timer, Duration)"
  })
  void testNewDefaultChannelPool_givenOfEpochDayOneThousand() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(0L);
    maxIdleTime.addTo(LocalDate.ofEpochDay(1000L));
    Duration connectionTtl = Duration.ofSeconds(1L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(
            maxIdleTime, connectionTtl, PoolLeaseStrategy.LIFO, nettyTimer, Duration.ofSeconds(1L));

    // Assert
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Given ofSeconds {@code 2147483647}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(AsyncHttpClientConfig, Timer); given ofSeconds '2147483647'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool_givenOfSeconds2147483647() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(0L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(2147483647L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Given ofSeconds {@code 2147483647}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(AsyncHttpClientConfig, Timer); given ofSeconds '2147483647'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool_givenOfSeconds21474836472() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(2147483647L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Given ofSeconds minus one thousand.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(AsyncHttpClientConfig, Timer); given ofSeconds minus one thousand")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool_givenOfSecondsMinusOneThousand() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(-1000L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(2147483647L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.NANOSECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Given ofSeconds zero addTo ofEpochDay minus one thousand.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(AsyncHttpClientConfig, Timer); given ofSeconds zero addTo ofEpochDay minus one thousand")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool_givenOfSecondsZeroAddToOfEpochDayMinusOneThousand() {
    // Arrange
    Duration ofSecondsResult = Duration.ofSeconds(0L);
    ofSecondsResult.addTo(LocalDate.ofEpochDay(-1000L));

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(0L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1000L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(ofSecondsResult);
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.MILLISECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Given ofSeconds zero addTo ofEpochDay one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(AsyncHttpClientConfig, Timer); given ofSeconds zero addTo ofEpochDay one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool_givenOfSecondsZeroAddToOfEpochDayOne() {
    // Arrange
    Duration ofSecondsResult = Duration.ofSeconds(0L);
    ofSecondsResult.addTo(LocalDate.ofEpochDay(1L));

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(ofSecondsResult);
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000L, TimeUnit.MILLISECONDS);

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Given ofSeconds zero addTo ofEpochDay one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(AsyncHttpClientConfig, Timer); given ofSeconds zero addTo ofEpochDay one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool_givenOfSecondsZeroAddToOfEpochDayOne2() {
    // Arrange
    Duration ofSecondsResult = Duration.ofSeconds(0L);
    ofSecondsResult.addTo(LocalDate.ofEpochDay(1L));

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(ofSecondsResult);
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer, Duration)}.
   *
   * <ul>
   *   <li>Then {@link HashedWheelTimer#HashedWheelTimer()} pendingTimeouts is one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer,
   * Duration)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(Duration, Duration, Timer, Duration); then HashedWheelTimer() pendingTimeouts is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(Duration, Duration, Timer, Duration)"})
  void testNewDefaultChannelPool_thenHashedWheelTimerPendingTimeoutsIsOne() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(1L);
    Duration connectionTtl = Duration.ofSeconds(1L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(maxIdleTime, connectionTtl, nettyTimer, Duration.ofSeconds(1L));

    // Assert
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer, Duration)}.
   *
   * <ul>
   *   <li>Then {@link HashedWheelTimer#HashedWheelTimer()} pendingTimeouts is one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer,
   * Duration)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(Duration, Duration, Timer, Duration); then HashedWheelTimer() pendingTimeouts is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(Duration, Duration, Timer, Duration)"})
  void testNewDefaultChannelPool_thenHashedWheelTimerPendingTimeoutsIsOne2() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(0L);
    Duration connectionTtl = Duration.ofSeconds(1L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(maxIdleTime, connectionTtl, nettyTimer, Duration.ofSeconds(1L));

    // Assert
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer, Duration)}.
   *
   * <ul>
   *   <li>Then {@link HashedWheelTimer#HashedWheelTimer()} pendingTimeouts is one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer,
   * Duration)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(Duration, Duration, Timer, Duration); then HashedWheelTimer() pendingTimeouts is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(Duration, Duration, Timer, Duration)"})
  void testNewDefaultChannelPool_thenHashedWheelTimerPendingTimeoutsIsOne3() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(1L);
    Duration connectionTtl = Duration.ofSeconds(0L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(maxIdleTime, connectionTtl, nettyTimer, Duration.ofSeconds(1L));

    // Assert
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, PoolLeaseStrategy, Timer,
   * Duration)}.
   *
   * <ul>
   *   <li>Then {@link HashedWheelTimer#HashedWheelTimer()} pendingTimeouts is one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration,
   * PoolLeaseStrategy, Timer, Duration)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(Duration, Duration, PoolLeaseStrategy, Timer, Duration); then HashedWheelTimer() pendingTimeouts is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultChannelPool.<init>(Duration, Duration, PoolLeaseStrategy, Timer, Duration)"
  })
  void testNewDefaultChannelPool_thenHashedWheelTimerPendingTimeoutsIsOne4() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(1L);
    Duration connectionTtl = Duration.ofSeconds(1L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(
            maxIdleTime, connectionTtl, PoolLeaseStrategy.LIFO, nettyTimer, Duration.ofSeconds(1L));

    // Assert
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Then {@link HashedWheelTimer#HashedWheelTimer()} pendingTimeouts is one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(AsyncHttpClientConfig, Timer); then HashedWheelTimer() pendingTimeouts is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool_thenHashedWheelTimerPendingTimeoutsIsOne5() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Then {@link HashedWheelTimer#HashedWheelTimer()} pendingTimeouts is one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(AsyncHttpClientConfig, Timer); then HashedWheelTimer() pendingTimeouts is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool_thenHashedWheelTimerPendingTimeoutsIsOne6() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(0L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Then {@link HashedWheelTimer#HashedWheelTimer()} pendingTimeouts is one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(AsyncHttpClientConfig, Timer); then HashedWheelTimer() pendingTimeouts is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool_thenHashedWheelTimerPendingTimeoutsIsOne7() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(0L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Then {@link HashedWheelTimer#HashedWheelTimer()} pendingTimeouts is one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(AsyncHttpClientConfig, Timer); then HashedWheelTimer() pendingTimeouts is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool_thenHashedWheelTimerPendingTimeoutsIsOne8() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(-1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(1L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer, Duration)}.
   *
   * <ul>
   *   <li>Then {@link HashedWheelTimer#HashedWheelTimer()} pendingTimeouts is zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, Timer,
   * Duration)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(Duration, Duration, Timer, Duration); then HashedWheelTimer() pendingTimeouts is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(Duration, Duration, Timer, Duration)"})
  void testNewDefaultChannelPool_thenHashedWheelTimerPendingTimeoutsIsZero() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(0L);
    Duration connectionTtl = Duration.ofSeconds(0L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(maxIdleTime, connectionTtl, nettyTimer, Duration.ofSeconds(1L));

    // Assert
    assertEquals(0L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, PoolLeaseStrategy, Timer,
   * Duration)}.
   *
   * <ul>
   *   <li>Then {@link HashedWheelTimer#HashedWheelTimer()} pendingTimeouts is zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration,
   * PoolLeaseStrategy, Timer, Duration)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(Duration, Duration, PoolLeaseStrategy, Timer, Duration); then HashedWheelTimer() pendingTimeouts is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultChannelPool.<init>(Duration, Duration, PoolLeaseStrategy, Timer, Duration)"
  })
  void testNewDefaultChannelPool_thenHashedWheelTimerPendingTimeoutsIsZero2() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(0L);
    Duration connectionTtl = Duration.ofSeconds(0L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(
            maxIdleTime, connectionTtl, PoolLeaseStrategy.LIFO, nettyTimer, Duration.ofSeconds(1L));

    // Assert
    assertEquals(0L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Then {@link HashedWheelTimer#HashedWheelTimer()} pendingTimeouts is zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(AsyncHttpClientConfig,
   * Timer)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(AsyncHttpClientConfig, Timer); then HashedWheelTimer() pendingTimeouts is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewDefaultChannelPool_thenHashedWheelTimerPendingTimeoutsIsZero3() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(0L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(0L));
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Assert
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertEquals(0L, hashedWheelTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, PoolLeaseStrategy, Timer,
   * Duration)}.
   *
   * <ul>
   *   <li>When ofSeconds one thousand.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration,
   * PoolLeaseStrategy, Timer, Duration)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(Duration, Duration, PoolLeaseStrategy, Timer, Duration); when ofSeconds one thousand")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultChannelPool.<init>(Duration, Duration, PoolLeaseStrategy, Timer, Duration)"
  })
  void testNewDefaultChannelPool_whenOfSecondsOneThousand() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(1L);
    Duration connectionTtl = Duration.ofSeconds(1000L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(
            maxIdleTime, connectionTtl, PoolLeaseStrategy.LIFO, nettyTimer, Duration.ofSeconds(1L));

    // Assert
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, PoolLeaseStrategy, Timer,
   * Duration)}.
   *
   * <ul>
   *   <li>When ofSeconds zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration,
   * PoolLeaseStrategy, Timer, Duration)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(Duration, Duration, PoolLeaseStrategy, Timer, Duration); when ofSeconds zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultChannelPool.<init>(Duration, Duration, PoolLeaseStrategy, Timer, Duration)"
  })
  void testNewDefaultChannelPool_whenOfSecondsZero() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(0L);
    Duration connectionTtl = Duration.ofSeconds(1L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(
            maxIdleTime, connectionTtl, PoolLeaseStrategy.LIFO, nettyTimer, Duration.ofSeconds(1L));

    // Assert
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration, PoolLeaseStrategy, Timer,
   * Duration)}.
   *
   * <ul>
   *   <li>When ofSeconds zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#DefaultChannelPool(Duration, Duration,
   * PoolLeaseStrategy, Timer, Duration)}
   */
  @Test
  @DisplayName(
      "Test new DefaultChannelPool(Duration, Duration, PoolLeaseStrategy, Timer, Duration); when ofSeconds zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultChannelPool.<init>(Duration, Duration, PoolLeaseStrategy, Timer, Duration)"
  })
  void testNewDefaultChannelPool_whenOfSecondsZero2() {
    // Arrange
    Duration maxIdleTime = Duration.ofSeconds(1L);
    Duration connectionTtl = Duration.ofSeconds(0L);
    HashedWheelTimer nettyTimer = new HashedWheelTimer();

    // Act
    DefaultChannelPool actualDefaultChannelPool =
        new DefaultChannelPool(
            maxIdleTime, connectionTtl, PoolLeaseStrategy.LIFO, nettyTimer, Duration.ofSeconds(1L));

    // Assert
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(actualDefaultChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualDefaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#offer(Channel, Object)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#offer(Channel, Object)}
   */
  @Test
  @DisplayName("Test offer(Channel, Object); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DefaultChannelPool.offer(Channel, Object)"})
  void testOffer_thenReturnTrue() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));

    Timer hashedWheelTimer = mock(Timer.class);
    when(hashedWheelTimer.newTimeout(Mockito.<TimerTask>any(), anyLong(), Mockito.<TimeUnit>any()))
        .thenReturn(mock(Timeout.class));

    DefaultChannelPool defaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Act
    boolean actualOfferResult = defaultChannelPool.offer(new EmbeddedChannel(), "Partition Key");

    // Assert
    verify(hashedWheelTimer).newTimeout(isA(TimerTask.class), eq(1000L), eq(TimeUnit.MILLISECONDS));
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertTrue(actualOfferResult);
  }

  /**
   * Test {@link DefaultChannelPool#offer(Channel, Object)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#offer(Channel, Object)}
   */
  @Test
  @DisplayName("Test offer(Channel, Object); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DefaultChannelPool.offer(Channel, Object)"})
  void testOffer_thenReturnTrue2() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(0L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));

    Timer hashedWheelTimer = mock(Timer.class);
    when(hashedWheelTimer.newTimeout(Mockito.<TimerTask>any(), anyLong(), Mockito.<TimeUnit>any()))
        .thenReturn(mock(Timeout.class));

    DefaultChannelPool defaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Act
    boolean actualOfferResult = defaultChannelPool.offer(new EmbeddedChannel(), "Partition Key");

    // Assert
    verify(hashedWheelTimer).newTimeout(isA(TimerTask.class), eq(1000L), eq(TimeUnit.MILLISECONDS));
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertTrue(actualOfferResult);
  }

  /**
   * Test {@link DefaultChannelPool#poll(Object)}.
   *
   * <ul>
   *   <li>When {@code Partition Key}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#poll(Object)}
   */
  @Test
  @DisplayName("Test poll(Object); when 'Partition Key'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Channel DefaultChannelPool.poll(Object)"})
  void testPoll_whenPartitionKey_thenReturnNull() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));

    Timer hashedWheelTimer = mock(Timer.class);
    when(hashedWheelTimer.newTimeout(Mockito.<TimerTask>any(), anyLong(), Mockito.<TimeUnit>any()))
        .thenReturn(mock(Timeout.class));

    DefaultChannelPool defaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Act
    Channel actualPollResult = defaultChannelPool.poll("Partition Key");

    // Assert
    verify(hashedWheelTimer).newTimeout(isA(TimerTask.class), eq(1000L), eq(TimeUnit.MILLISECONDS));
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertNull(actualPollResult);
  }

  /**
   * Test {@link DefaultChannelPool#removeAll(Channel)}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientConfig} {@link AsyncHttpClientConfig#getConnectionTtl()}
   *       return ofSeconds zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#removeAll(Channel)}
   */
  @Test
  @DisplayName(
      "Test removeAll(Channel); given AsyncHttpClientConfig getConnectionTtl() return ofSeconds zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DefaultChannelPool.removeAll(Channel)"})
  void testRemoveAll_givenAsyncHttpClientConfigGetConnectionTtlReturnOfSecondsZero() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(0L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));

    Timer hashedWheelTimer = mock(Timer.class);
    when(hashedWheelTimer.newTimeout(Mockito.<TimerTask>any(), anyLong(), Mockito.<TimeUnit>any()))
        .thenReturn(mock(Timeout.class));

    DefaultChannelPool defaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Act
    boolean actualRemoveAllResult = defaultChannelPool.removeAll(new EmbeddedChannel());

    // Assert
    verify(hashedWheelTimer).newTimeout(isA(TimerTask.class), eq(1000L), eq(TimeUnit.MILLISECONDS));
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertFalse(actualRemoveAllResult);
  }

  /**
   * Test {@link DefaultChannelPool#removeAll(Channel)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#removeAll(Channel)}
   */
  @Test
  @DisplayName("Test removeAll(Channel); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DefaultChannelPool.removeAll(Channel)"})
  void testRemoveAll_thenReturnFalse() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));

    Timer hashedWheelTimer = mock(Timer.class);
    when(hashedWheelTimer.newTimeout(Mockito.<TimerTask>any(), anyLong(), Mockito.<TimeUnit>any()))
        .thenReturn(mock(Timeout.class));

    DefaultChannelPool defaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Act
    boolean actualRemoveAllResult = defaultChannelPool.removeAll(new EmbeddedChannel());

    // Assert
    verify(hashedWheelTimer).newTimeout(isA(TimerTask.class), eq(1000L), eq(TimeUnit.MILLISECONDS));
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertFalse(actualRemoveAllResult);
  }

  /**
   * Test {@link DefaultChannelPool#isOpen()}.
   *
   * <p>Method under test: {@link DefaultChannelPool#isOpen()}
   */
  @Test
  @DisplayName("Test isOpen()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DefaultChannelPool.isOpen()"})
  void testIsOpen() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));

    Timer hashedWheelTimer = mock(Timer.class);
    when(hashedWheelTimer.newTimeout(Mockito.<TimerTask>any(), anyLong(), Mockito.<TimeUnit>any()))
        .thenReturn(mock(Timeout.class));

    DefaultChannelPool defaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Act
    boolean actualIsOpenResult = defaultChannelPool.isOpen();

    // Assert
    verify(hashedWheelTimer).newTimeout(isA(TimerTask.class), eq(1000L), eq(TimeUnit.MILLISECONDS));
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertTrue(actualIsOpenResult);
  }

  /**
   * Test {@link DefaultChannelPool#destroy()}.
   *
   * <p>Method under test: {@link DefaultChannelPool#destroy()}
   */
  @Test
  @DisplayName("Test destroy()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.destroy()"})
  void testDestroy() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));

    Timer hashedWheelTimer = mock(Timer.class);
    when(hashedWheelTimer.newTimeout(Mockito.<TimerTask>any(), anyLong(), Mockito.<TimeUnit>any()))
        .thenReturn(mock(Timeout.class));

    DefaultChannelPool defaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Act
    defaultChannelPool.destroy();

    // Assert
    verify(hashedWheelTimer).newTimeout(isA(TimerTask.class), eq(1000L), eq(TimeUnit.MILLISECONDS));
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertFalse(defaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#destroy()}.
   *
   * <ul>
   *   <li>Given ofSeconds one addTo {@link LocalDateTime} with one and one and one and one and one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultChannelPool#destroy()}
   */
  @Test
  @DisplayName(
      "Test destroy(); given ofSeconds one addTo LocalDateTime with one and one and one and one and one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultChannelPool.destroy()"})
  void testDestroy_givenOfSecondsOneAddToLocalDateTimeWithOneAndOneAndOneAndOneAndOne() {
    // Arrange
    Duration ofSecondsResult = Duration.ofSeconds(1L);
    LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
    ofSecondsResult.addTo(ofResult);

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(ofSecondsResult);
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));

    Timer hashedWheelTimer = mock(Timer.class);
    when(hashedWheelTimer.newTimeout(Mockito.<TimerTask>any(), anyLong(), Mockito.<TimeUnit>any()))
        .thenReturn(mock(Timeout.class));

    DefaultChannelPool defaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Act
    defaultChannelPool.destroy();

    // Assert
    verify(hashedWheelTimer).newTimeout(isA(TimerTask.class), eq(1000L), eq(TimeUnit.MILLISECONDS));
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertFalse(defaultChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultChannelPool#getIdleChannelCountPerHost()}.
   *
   * <p>Method under test: {@link DefaultChannelPool#getIdleChannelCountPerHost()}
   */
  @Test
  @DisplayName("Test getIdleChannelCountPerHost()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DefaultChannelPool.getIdleChannelCountPerHost()"})
  void testGetIdleChannelCountPerHost() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));

    Timer hashedWheelTimer = mock(Timer.class);
    when(hashedWheelTimer.newTimeout(Mockito.<TimerTask>any(), anyLong(), Mockito.<TimeUnit>any()))
        .thenReturn(mock(Timeout.class));

    DefaultChannelPool defaultChannelPool = new DefaultChannelPool(config, hashedWheelTimer);

    // Act
    Map<String, Long> actualIdleChannelCountPerHost =
        defaultChannelPool.getIdleChannelCountPerHost();

    // Assert
    verify(hashedWheelTimer).newTimeout(isA(TimerTask.class), eq(1000L), eq(TimeUnit.MILLISECONDS));
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getPooledConnectionIdleTimeout();
    assertTrue(actualIdleChannelCountPerHost.isEmpty());
  }
}
