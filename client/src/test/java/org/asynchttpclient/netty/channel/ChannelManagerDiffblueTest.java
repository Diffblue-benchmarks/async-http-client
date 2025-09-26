package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.DefaultEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.ssl.JdkSslClientContext;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLException;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.SslEngineFactory;
import org.asynchttpclient.channel.ChannelPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ChannelManagerDiffblueTest {
  /**
   * Test {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}
   */
  @Test
  @DisplayName("Test new ChannelManager(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ChannelManager.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewChannelManager() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getSslEngineFactory()).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new ChannelManager(config, new HashedWheelTimer()));
    verify(config).getSslEngineFactory();
  }

  /**
   * Test {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}
   */
  @Test
  @DisplayName("Test new ChannelManager(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ChannelManager.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewChannelManager2() throws SSLException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getEventLoopGroup()).thenThrow(new RuntimeException());
    when(config.isKeepAlive()).thenReturn(false);
    when(config.getThreadFactory()).thenReturn(null);
    when(config.getSslEngineFactory()).thenReturn(null);
    when(config.getChannelPool()).thenReturn(null);
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getSslContext()).thenReturn(new JdkSslClientContext());
    when(config.getThreadPoolName()).thenReturn("https://example.org/example");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new ChannelManager(config, new HashedWheelTimer()));
    verify(config).getChannelPool();
    verify(config).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config, atLeast(1)).getSslContext();
    verify(config).getSslEngineFactory();
    verify(config).getThreadFactory();
    verify(config).getThreadPoolName();
    verify(config).isKeepAlive();
  }

  /**
   * Test {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}.
   *
   * <p>Method under test: {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}
   */
  @Test
  @DisplayName("Test new ChannelManager(AsyncHttpClientConfig, Timer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ChannelManager.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewChannelManager3() throws SSLException {
    // Arrange
    SslEngineFactory sslEngineFactory = mock(SslEngineFactory.class);
    doThrow(new SSLException("asyncHttpClient"))
        .when(sslEngineFactory)
        .init(Mockito.<AsyncHttpClientConfig>any());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getSslEngineFactory()).thenReturn(sslEngineFactory);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new ChannelManager(config, new HashedWheelTimer()));
    verify(config, atLeast(1)).getSslEngineFactory();
    verify(sslEngineFactory).init(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Given {@link DefaultEventLoop#DefaultEventLoop()}.
   * </ul>
   *
   * <p>Method under test: {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}
   */
  @Test
  @DisplayName("Test new ChannelManager(AsyncHttpClientConfig, Timer); given DefaultEventLoop()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ChannelManager.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewChannelManager_givenDefaultEventLoop() throws SSLException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getEventLoopGroup()).thenReturn(new DefaultEventLoop());
    when(config.isKeepAlive()).thenReturn(false);
    when(config.getThreadFactory()).thenReturn(null);
    when(config.getSslEngineFactory()).thenReturn(null);
    when(config.getChannelPool()).thenReturn(null);
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getSslContext()).thenReturn(new JdkSslClientContext());
    when(config.getThreadPoolName()).thenReturn("https://example.org/example");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new ChannelManager(config, new HashedWheelTimer()));
    verify(config).getChannelPool();
    verify(config, atLeast(1)).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config, atLeast(1)).getSslContext();
    verify(config).getSslEngineFactory();
    verify(config).getThreadFactory();
    verify(config).getThreadPoolName();
    verify(config).isKeepAlive();
  }

  /**
   * Test {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Given {@link SslEngineFactory} {@link SslEngineFactory#init(AsyncHttpClientConfig)} throw
   *       {@link RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}
   */
  @Test
  @DisplayName(
      "Test new ChannelManager(AsyncHttpClientConfig, Timer); given SslEngineFactory init(AsyncHttpClientConfig) throw RuntimeException()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ChannelManager.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewChannelManager_givenSslEngineFactoryInitThrowRuntimeException() throws SSLException {
    // Arrange
    SslEngineFactory sslEngineFactory = mock(SslEngineFactory.class);
    doThrow(new RuntimeException())
        .when(sslEngineFactory)
        .init(Mockito.<AsyncHttpClientConfig>any());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getSslEngineFactory()).thenReturn(sslEngineFactory);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new ChannelManager(config, new HashedWheelTimer()));
    verify(config, atLeast(1)).getSslEngineFactory();
    verify(sslEngineFactory).init(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Then ChannelPool return {@link DefaultChannelPool}.
   * </ul>
   *
   * <p>Method under test: {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}
   */
  @Test
  @DisplayName(
      "Test new ChannelManager(AsyncHttpClientConfig, Timer); then ChannelPool return DefaultChannelPool")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ChannelManager.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewChannelManager_thenChannelPoolReturnDefaultChannelPool() throws SSLException {
    // Arrange
    SslEngineFactory sslEngineFactory = mock(SslEngineFactory.class);
    doNothing().when(sslEngineFactory).init(Mockito.<AsyncHttpClientConfig>any());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    when(config.isUseNativeTransport()).thenReturn(false);
    when(config.getSoLinger()).thenReturn(0);
    when(config.getSoRcvBuf()).thenReturn(0);
    when(config.getSoSndBuf()).thenReturn(0);
    when(config.getAllocator()).thenReturn(null);
    when(config.getEventLoopGroup()).thenReturn(null);
    when(config.isSoKeepAlive()).thenReturn(true);
    when(config.isSoReuseAddress()).thenReturn(true);
    when(config.isTcpNoDelay()).thenReturn(true);
    when(config.getIoThreadsCount()).thenReturn(3);
    when(config.getConnectTimeout()).thenReturn(Duration.ofSeconds(1L));
    when(config.getChannelOptions()).thenReturn(new HashMap<>());
    when(config.isKeepAlive()).thenReturn(true);
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));
    when(config.getSslEngineFactory()).thenReturn(sslEngineFactory);
    when(config.getChannelPool()).thenReturn(null);
    HashedWheelTimer nettyTimer = new HashedWheelTimer();

    // Act
    ChannelManager actualChannelManager = new ChannelManager(config, nettyTimer);

    // Assert
    verify(config, atLeast(1)).getAllocator();
    verify(config, atLeast(1)).getChannelOptions();
    verify(config).getChannelPool();
    verify(config, atLeast(1)).getConnectTimeout();
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config).getIoThreadsCount();
    verify(config).getPooledConnectionIdleTimeout();
    verify(config, atLeast(1)).getSoLinger();
    verify(config, atLeast(1)).getSoRcvBuf();
    verify(config, atLeast(1)).getSoSndBuf();
    verify(config, atLeast(1)).getSslEngineFactory();
    verify(config, atLeast(1)).getThreadFactory();
    verify(config).isKeepAlive();
    verify(config, atLeast(1)).isSoKeepAlive();
    verify(config, atLeast(1)).isSoReuseAddress();
    verify(config, atLeast(1)).isTcpNoDelay();
    verify(config).isUseNativeTransport();
    verify(sslEngineFactory).init(isA(AsyncHttpClientConfig.class));
    ChannelPool channelPool = actualChannelManager.getChannelPool();
    assertTrue(channelPool instanceof DefaultChannelPool);
    assertEquals(1L, nettyTimer.pendingTimeouts());
    assertTrue(channelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(channelPool.isOpen());
  }

  /**
   * Test {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Then return EventLoopGroup is {@link NioEventLoopGroup#NioEventLoopGroup()}.
   * </ul>
   *
   * <p>Method under test: {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}
   */
  @Test
  @DisplayName(
      "Test new ChannelManager(AsyncHttpClientConfig, Timer); then return EventLoopGroup is NioEventLoopGroup()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ChannelManager.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewChannelManager_thenReturnEventLoopGroupIsNioEventLoopGroup() throws SSLException {
    // Arrange
    SslEngineFactory sslEngineFactory = mock(SslEngineFactory.class);
    doNothing().when(sslEngineFactory).init(Mockito.<AsyncHttpClientConfig>any());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(Duration.ofSeconds(1L));
    when(config.getConnectionTtl()).thenReturn(Duration.ofSeconds(1L));
    when(config.getPooledConnectionIdleTimeout()).thenReturn(Duration.ofSeconds(1L));
    when(config.getSoLinger()).thenReturn(0);
    when(config.getSoRcvBuf()).thenReturn(0);
    when(config.getSoSndBuf()).thenReturn(0);
    when(config.getAllocator()).thenReturn(null);
    NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
    when(config.getEventLoopGroup()).thenReturn(nioEventLoopGroup);
    when(config.isSoKeepAlive()).thenReturn(true);
    when(config.isSoReuseAddress()).thenReturn(true);
    when(config.isTcpNoDelay()).thenReturn(true);
    when(config.getConnectTimeout()).thenReturn(Duration.ofSeconds(1L));
    when(config.getChannelOptions()).thenReturn(new HashMap<>());
    when(config.isKeepAlive()).thenReturn(true);
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));
    when(config.getSslEngineFactory()).thenReturn(sslEngineFactory);
    when(config.getChannelPool()).thenReturn(null);

    Timer nettyTimer = mock(Timer.class);
    when(nettyTimer.newTimeout(Mockito.<TimerTask>any(), anyLong(), Mockito.<TimeUnit>any()))
        .thenReturn(mock(Timeout.class));

    // Act
    ChannelManager actualChannelManager = new ChannelManager(config, nettyTimer);

    // Assert
    verify(nettyTimer).newTimeout(isA(TimerTask.class), eq(1000L), eq(TimeUnit.MILLISECONDS));
    verify(config, atLeast(1)).getAllocator();
    verify(config, atLeast(1)).getChannelOptions();
    verify(config).getChannelPool();
    verify(config, atLeast(1)).getConnectTimeout();
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionTtl();
    verify(config, atLeast(1)).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config).getPooledConnectionIdleTimeout();
    verify(config, atLeast(1)).getSoLinger();
    verify(config, atLeast(1)).getSoRcvBuf();
    verify(config, atLeast(1)).getSoSndBuf();
    verify(config, atLeast(1)).getSslEngineFactory();
    verify(config, atLeast(1)).getThreadFactory();
    verify(config).isKeepAlive();
    verify(config, atLeast(1)).isSoKeepAlive();
    verify(config, atLeast(1)).isSoReuseAddress();
    verify(config, atLeast(1)).isTcpNoDelay();
    verify(sslEngineFactory).init(isA(AsyncHttpClientConfig.class));
    assertSame(nioEventLoopGroup, actualChannelManager.getEventLoopGroup());
  }
}
