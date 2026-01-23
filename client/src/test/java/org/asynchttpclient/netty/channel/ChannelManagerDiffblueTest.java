package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.ThreadFactory;
import javax.net.ssl.SSLException;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.SslEngineFactory;
import org.asynchttpclient.channel.NoopChannelPool;
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
    SslEngineFactory sslEngineFactory = mock(SslEngineFactory.class);
    doNothing().when(sslEngineFactory).init(Mockito.<AsyncHttpClientConfig>any());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getEventLoopGroup()).thenThrow(new RuntimeException());
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));
    when(config.getSslEngineFactory()).thenReturn(sslEngineFactory);
    when(config.getChannelPool()).thenReturn(NoopChannelPool.INSTANCE);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new ChannelManager(config, new HashedWheelTimer()));
    verify(config).getChannelPool();
    verify(config).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config, atLeast(1)).getSslEngineFactory();
    verify(config, atLeast(1)).getThreadFactory();
    verify(sslEngineFactory).init(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}.
   *
   * <ul>
   *   <li>Given {@link DefaultEventLoop#DefaultEventLoop()}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}
   */
  @Test
  @DisplayName(
      "Test new ChannelManager(AsyncHttpClientConfig, Timer); given DefaultEventLoop(); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ChannelManager.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewChannelManager_givenDefaultEventLoop_thenThrowIllegalArgumentException()
      throws SSLException {
    // Arrange
    SslEngineFactory sslEngineFactory = mock(SslEngineFactory.class);
    doNothing().when(sslEngineFactory).init(Mockito.<AsyncHttpClientConfig>any());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getEventLoopGroup()).thenReturn(new DefaultEventLoop());
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));
    when(config.getSslEngineFactory()).thenReturn(sslEngineFactory);
    when(config.getChannelPool()).thenReturn(NoopChannelPool.INSTANCE);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new ChannelManager(config, new HashedWheelTimer()));
    verify(config).getChannelPool();
    verify(config, atLeast(1)).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config, atLeast(1)).getSslEngineFactory();
    verify(config, atLeast(1)).getThreadFactory();
    verify(sslEngineFactory).init(isA(AsyncHttpClientConfig.class));
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
   *   <li>Given {@link SslEngineFactory} {@link SslEngineFactory#init(AsyncHttpClientConfig)} throw
   *       {@link SSLException#SSLException(String)} with {@code Just cause}.
   * </ul>
   *
   * <p>Method under test: {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}
   */
  @Test
  @DisplayName(
      "Test new ChannelManager(AsyncHttpClientConfig, Timer); given SslEngineFactory init(AsyncHttpClientConfig) throw SSLException(String) with 'Just cause'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ChannelManager.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewChannelManager_givenSslEngineFactoryInitThrowSSLExceptionWithJustCause()
      throws SSLException {
    // Arrange
    SslEngineFactory sslEngineFactory = mock(SslEngineFactory.class);
    doThrow(new SSLException("Just cause"))
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
   *   <li>Then calls {@link AsyncHttpClientConfig#isKeepAlive()}.
   * </ul>
   *
   * <p>Method under test: {@link ChannelManager#ChannelManager(AsyncHttpClientConfig, Timer)}
   */
  @Test
  @DisplayName("Test new ChannelManager(AsyncHttpClientConfig, Timer); then calls isKeepAlive()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ChannelManager.<init>(AsyncHttpClientConfig, Timer)"})
  void testNewChannelManager_thenCallsIsKeepAlive() throws SSLException {
    // Arrange
    SslEngineFactory sslEngineFactory = mock(SslEngineFactory.class);
    doNothing().when(sslEngineFactory).init(Mockito.<AsyncHttpClientConfig>any());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getEventLoopGroup()).thenReturn(new DefaultEventLoop());
    when(config.isKeepAlive()).thenReturn(false);
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));
    when(config.getSslEngineFactory()).thenReturn(sslEngineFactory);
    when(config.getChannelPool()).thenReturn(null);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new ChannelManager(config, new HashedWheelTimer()));
    verify(config).getChannelPool();
    verify(config, atLeast(1)).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config, atLeast(1)).getSslEngineFactory();
    verify(config, atLeast(1)).getThreadFactory();
    verify(config).isKeepAlive();
    verify(sslEngineFactory).init(isA(AsyncHttpClientConfig.class));
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
    when(config.getSoLinger()).thenReturn(0);
    when(config.getSoRcvBuf()).thenReturn(0);
    when(config.getSoSndBuf()).thenReturn(0);
    when(config.isSoKeepAlive()).thenReturn(true);
    when(config.isSoReuseAddress()).thenReturn(true);
    when(config.isTcpNoDelay()).thenReturn(true);
    when(config.getAllocator()).thenReturn(new AdaptiveByteBufAllocator());
    NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
    when(config.getEventLoopGroup()).thenReturn(nioEventLoopGroup);
    when(config.getConnectTimeout()).thenReturn(Duration.ofSeconds(1L));
    when(config.getChannelOptions()).thenReturn(new HashMap<>());
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));
    when(config.getSslEngineFactory()).thenReturn(sslEngineFactory);
    when(config.getChannelPool()).thenReturn(NoopChannelPool.INSTANCE);

    // Act
    ChannelManager actualChannelManager = new ChannelManager(config, new HashedWheelTimer());

    // Assert
    verify(config, atLeast(1)).getAllocator();
    verify(config, atLeast(1)).getChannelOptions();
    verify(config).getChannelPool();
    verify(config, atLeast(1)).getConnectTimeout();
    verify(config, atLeast(1)).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config, atLeast(1)).getSoLinger();
    verify(config, atLeast(1)).getSoRcvBuf();
    verify(config, atLeast(1)).getSoSndBuf();
    verify(config, atLeast(1)).getSslEngineFactory();
    verify(config, atLeast(1)).getThreadFactory();
    verify(config, atLeast(1)).isSoKeepAlive();
    verify(config, atLeast(1)).isSoReuseAddress();
    verify(config, atLeast(1)).isTcpNoDelay();
    verify(sslEngineFactory).init(isA(AsyncHttpClientConfig.class));
    assertSame(nioEventLoopGroup, actualChannelManager.getEventLoopGroup());
  }

  /**
   * Test {@link ChannelManager#isSslHandlerConfigured(ChannelPipeline)}.
   *
   * <ul>
   *   <li>Given {@link ChannelInboundHandlerAdapter} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ChannelManager#isSslHandlerConfigured(ChannelPipeline)}
   */
  @Test
  @DisplayName(
      "Test isSslHandlerConfigured(ChannelPipeline); given ChannelInboundHandlerAdapter (default constructor); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ChannelManager.isSslHandlerConfigured(ChannelPipeline)"})
  void testIsSslHandlerConfigured_givenChannelInboundHandlerAdapter_thenReturnTrue() {
    // Arrange
    ChannelPipeline pipeline = mock(ChannelPipeline.class);
    when(pipeline.get(Mockito.<String>any())).thenReturn(new ChannelInboundHandlerAdapter());

    // Act
    boolean actualIsSslHandlerConfiguredResult = ChannelManager.isSslHandlerConfigured(pipeline);

    // Assert
    verify(pipeline).get("ssl");
    assertTrue(actualIsSslHandlerConfiguredResult);
  }

  /**
   * Test {@link ChannelManager#isSslHandlerConfigured(ChannelPipeline)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ChannelManager#isSslHandlerConfigured(ChannelPipeline)}
   */
  @Test
  @DisplayName("Test isSslHandlerConfigured(ChannelPipeline); given 'null'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ChannelManager.isSslHandlerConfigured(ChannelPipeline)"})
  void testIsSslHandlerConfigured_givenNull_thenReturnFalse() {
    // Arrange
    ChannelPipeline pipeline = mock(ChannelPipeline.class);
    when(pipeline.get(Mockito.<String>any())).thenReturn(null);

    // Act
    boolean actualIsSslHandlerConfiguredResult = ChannelManager.isSslHandlerConfigured(pipeline);

    // Assert
    verify(pipeline).get("ssl");
    assertFalse(actualIsSslHandlerConfiguredResult);
  }

  /**
   * Test {@link ChannelManager#isSslHandlerConfigured(ChannelPipeline)}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link ChannelManager#isSslHandlerConfigured(ChannelPipeline)}
   */
  @Test
  @DisplayName(
      "Test isSslHandlerConfigured(ChannelPipeline); given RuntimeException(); then throw RuntimeException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ChannelManager.isSslHandlerConfigured(ChannelPipeline)"})
  void testIsSslHandlerConfigured_givenRuntimeException_thenThrowRuntimeException() {
    // Arrange
    ChannelPipeline pipeline = mock(ChannelPipeline.class);
    when(pipeline.get(Mockito.<String>any())).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> ChannelManager.isSslHandlerConfigured(pipeline));
    verify(pipeline).get("ssl");
  }
}
