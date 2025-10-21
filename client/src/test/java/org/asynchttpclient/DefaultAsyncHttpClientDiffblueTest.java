package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.DefaultNameResolver;
import io.netty.resolver.NameResolver;
import io.netty.util.concurrent.DefaultPromise;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import org.asynchttpclient.ListenableFuture.CompletedFailure;
import org.asynchttpclient.channel.ChannelPool;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.channel.ChannelPoolPartitioning.PerHostChannelPoolPartitioning;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.asynchttpclient.netty.channel.ChannelManager;
import org.asynchttpclient.netty.channel.DefaultChannelPool;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DefaultAsyncHttpClientDiffblueTest {
  /**
   * Test {@link DefaultAsyncHttpClient#DefaultAsyncHttpClient()}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#DefaultAsyncHttpClient()}
   */
  @Test
  @DisplayName("Test new DefaultAsyncHttpClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DefaultAsyncHttpClient.<init>()"})
  void testNewDefaultAsyncHttpClient() {
    // Arrange and Act
    DefaultAsyncHttpClient actualDefaultAsyncHttpClient = new DefaultAsyncHttpClient();

    // Assert
    assertTrue(actualDefaultAsyncHttpClient.getEventLoopGroup() instanceof NioEventLoopGroup);
    assertTrue(actualDefaultAsyncHttpClient.getConfig() instanceof DefaultAsyncHttpClientConfig);
    assertTrue(actualDefaultAsyncHttpClient.getChannelPool() instanceof DefaultChannelPool);
    assertFalse(actualDefaultAsyncHttpClient.isClosed());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DefaultAsyncHttpClient#channelManager()}
   *   <li>{@link DefaultAsyncHttpClient#getConfig()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ChannelManager DefaultAsyncHttpClient.channelManager()",
      "AsyncHttpClientConfig DefaultAsyncHttpClient.getConfig()"})
  void testGettersAndSetters() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    // Act
    ChannelManager actualChannelManagerResult = defaultAsyncHttpClient.channelManager();
    AsyncHttpClientConfig actualConfig = defaultAsyncHttpClient.getConfig();

    // Assert
    assertTrue(actualChannelManagerResult.getEventLoopGroup() instanceof NioEventLoopGroup);
    assertTrue(actualConfig instanceof DefaultAsyncHttpClientConfig);
    assertTrue(actualChannelManagerResult.getChannelPool() instanceof DefaultChannelPool);
    ClientStats clientStats = actualChannelManagerResult.getClientStats();
    assertEquals(0L, clientStats.getTotalActiveConnectionCount());
    assertEquals(0L, clientStats.getTotalConnectionCount());
    assertEquals(0L, clientStats.getTotalIdleConnectionCount());
    assertTrue(clientStats.getStatsPerHost().isEmpty());
    assertTrue(actualChannelManagerResult.isOpen());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#close()}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DefaultAsyncHttpClient.close()"})
  void testClose() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    // Act
    defaultAsyncHttpClient.close();

    // Assert
    EventLoopGroup eventLoopGroup = defaultAsyncHttpClient.getEventLoopGroup();
    EventLoop nextResult = eventLoopGroup.next();
    assertTrue(nextResult instanceof NioEventLoop);
    assertTrue(eventLoopGroup instanceof NioEventLoopGroup);
    assertFalse(nextResult.isShutdown());
    assertFalse(eventLoopGroup.isShutdown());
    assertFalse(nextResult.isTerminated());
    assertFalse(eventLoopGroup.isTerminated());
    assertTrue(defaultAsyncHttpClient.isClosed());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#isClosed()}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#isClosed()}
   */
  @Test
  @DisplayName("Test isClosed()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DefaultAsyncHttpClient.isClosed()"})
  void testIsClosed() {
    // Arrange, Act and Assert
    assertFalse((new DefaultAsyncHttpClient()).isClosed());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#setSignatureCalculator(SignatureCalculator)}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#setSignatureCalculator(SignatureCalculator)}
   */
  @Test
  @DisplayName("Test setSignatureCalculator(SignatureCalculator)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DefaultAsyncHttpClient DefaultAsyncHttpClient.setSignatureCalculator(SignatureCalculator)"})
  void testSetSignatureCalculator() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    // Act and Assert
    assertSame(defaultAsyncHttpClient, defaultAsyncHttpClient.setSignatureCalculator(mock(SignatureCalculator.class)));
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepare(String, String)}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#prepare(String, String)}
   */
  @Test
  @DisplayName("Test prepare(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepare(String, String)"})
  void testPrepare() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareResult = (new DefaultAsyncHttpClient()).prepare("https://example.org/example",
        "https://example.org/example");

    // Assert
    assertTrue(actualPrepareResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPrepareResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualPrepareResult.method);
    assertNull(actualPrepareResult.byteData);
    assertNull(actualPrepareResult.byteBufData);
    assertNull(actualPrepareResult.file);
    assertNull(actualPrepareResult.streamData);
    assertNull(actualPrepareResult.followRedirect);
    assertNull(actualPrepareResult.stringData);
    assertNull(actualPrepareResult.virtualHost);
    assertNull(actualPrepareResult.address);
    assertNull(actualPrepareResult.localAddress);
    assertNull(actualPrepareResult.byteBufferData);
    assertNull(actualPrepareResult.charset);
    assertNull(actualPrepareResult.readTimeout);
    assertNull(actualPrepareResult.requestTimeout);
    assertNull(actualPrepareResult.cookies);
    assertNull(actualPrepareResult.compositeByteData);
    assertNull(actualPrepareResult.formParams);
    assertNull(actualPrepareResult.queryParams);
    assertNull(actualPrepareResult.bodyParts);
    assertNull(actualPrepareResult.realm);
    assertNull(actualPrepareResult.signatureCalculator);
    assertNull(actualPrepareResult.proxyServer);
    assertNull(actualPrepareResult.bodyGenerator);
    assertEquals(0L, actualPrepareResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPrepareResult.uriEncoder);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareGet(String)}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#prepareGet(String)}
   */
  @Test
  @DisplayName("Test prepareGet(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareGet(String)"})
  void testPrepareGet() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareGetResult = (new DefaultAsyncHttpClient())
        .prepareGet("https://example.org/example");

    // Assert
    assertTrue(actualPrepareGetResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareGetResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPrepareGetResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("GET", actualPrepareGetResult.method);
    assertNull(actualPrepareGetResult.byteData);
    assertNull(actualPrepareGetResult.byteBufData);
    assertNull(actualPrepareGetResult.file);
    assertNull(actualPrepareGetResult.streamData);
    assertNull(actualPrepareGetResult.followRedirect);
    assertNull(actualPrepareGetResult.stringData);
    assertNull(actualPrepareGetResult.virtualHost);
    assertNull(actualPrepareGetResult.address);
    assertNull(actualPrepareGetResult.localAddress);
    assertNull(actualPrepareGetResult.byteBufferData);
    assertNull(actualPrepareGetResult.charset);
    assertNull(actualPrepareGetResult.readTimeout);
    assertNull(actualPrepareGetResult.requestTimeout);
    assertNull(actualPrepareGetResult.cookies);
    assertNull(actualPrepareGetResult.compositeByteData);
    assertNull(actualPrepareGetResult.formParams);
    assertNull(actualPrepareGetResult.queryParams);
    assertNull(actualPrepareGetResult.bodyParts);
    assertNull(actualPrepareGetResult.realm);
    assertNull(actualPrepareGetResult.signatureCalculator);
    assertNull(actualPrepareGetResult.proxyServer);
    assertNull(actualPrepareGetResult.bodyGenerator);
    assertEquals(0L, actualPrepareGetResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPrepareGetResult.uriEncoder);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareConnect(String)}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#prepareConnect(String)}
   */
  @Test
  @DisplayName("Test prepareConnect(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareConnect(String)"})
  void testPrepareConnect() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareConnectResult = (new DefaultAsyncHttpClient())
        .prepareConnect("https://example.org/example");

    // Assert
    assertTrue(actualPrepareConnectResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareConnectResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPrepareConnectResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("CONNECT", actualPrepareConnectResult.method);
    assertNull(actualPrepareConnectResult.byteData);
    assertNull(actualPrepareConnectResult.byteBufData);
    assertNull(actualPrepareConnectResult.file);
    assertNull(actualPrepareConnectResult.streamData);
    assertNull(actualPrepareConnectResult.followRedirect);
    assertNull(actualPrepareConnectResult.stringData);
    assertNull(actualPrepareConnectResult.virtualHost);
    assertNull(actualPrepareConnectResult.address);
    assertNull(actualPrepareConnectResult.localAddress);
    assertNull(actualPrepareConnectResult.byteBufferData);
    assertNull(actualPrepareConnectResult.charset);
    assertNull(actualPrepareConnectResult.readTimeout);
    assertNull(actualPrepareConnectResult.requestTimeout);
    assertNull(actualPrepareConnectResult.cookies);
    assertNull(actualPrepareConnectResult.compositeByteData);
    assertNull(actualPrepareConnectResult.formParams);
    assertNull(actualPrepareConnectResult.queryParams);
    assertNull(actualPrepareConnectResult.bodyParts);
    assertNull(actualPrepareConnectResult.realm);
    assertNull(actualPrepareConnectResult.signatureCalculator);
    assertNull(actualPrepareConnectResult.proxyServer);
    assertNull(actualPrepareConnectResult.bodyGenerator);
    assertEquals(0L, actualPrepareConnectResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPrepareConnectResult.uriEncoder);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareOptions(String)}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#prepareOptions(String)}
   */
  @Test
  @DisplayName("Test prepareOptions(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareOptions(String)"})
  void testPrepareOptions() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareOptionsResult = (new DefaultAsyncHttpClient())
        .prepareOptions("https://example.org/example");

    // Assert
    assertTrue(actualPrepareOptionsResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareOptionsResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPrepareOptionsResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("OPTIONS", actualPrepareOptionsResult.method);
    assertNull(actualPrepareOptionsResult.byteData);
    assertNull(actualPrepareOptionsResult.byteBufData);
    assertNull(actualPrepareOptionsResult.file);
    assertNull(actualPrepareOptionsResult.streamData);
    assertNull(actualPrepareOptionsResult.followRedirect);
    assertNull(actualPrepareOptionsResult.stringData);
    assertNull(actualPrepareOptionsResult.virtualHost);
    assertNull(actualPrepareOptionsResult.address);
    assertNull(actualPrepareOptionsResult.localAddress);
    assertNull(actualPrepareOptionsResult.byteBufferData);
    assertNull(actualPrepareOptionsResult.charset);
    assertNull(actualPrepareOptionsResult.readTimeout);
    assertNull(actualPrepareOptionsResult.requestTimeout);
    assertNull(actualPrepareOptionsResult.cookies);
    assertNull(actualPrepareOptionsResult.compositeByteData);
    assertNull(actualPrepareOptionsResult.formParams);
    assertNull(actualPrepareOptionsResult.queryParams);
    assertNull(actualPrepareOptionsResult.bodyParts);
    assertNull(actualPrepareOptionsResult.realm);
    assertNull(actualPrepareOptionsResult.signatureCalculator);
    assertNull(actualPrepareOptionsResult.proxyServer);
    assertNull(actualPrepareOptionsResult.bodyGenerator);
    assertEquals(0L, actualPrepareOptionsResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPrepareOptionsResult.uriEncoder);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareHead(String)}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#prepareHead(String)}
   */
  @Test
  @DisplayName("Test prepareHead(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareHead(String)"})
  void testPrepareHead() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareHeadResult = (new DefaultAsyncHttpClient())
        .prepareHead("https://example.org/example");

    // Assert
    assertTrue(actualPrepareHeadResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareHeadResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPrepareHeadResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("HEAD", actualPrepareHeadResult.method);
    assertNull(actualPrepareHeadResult.byteData);
    assertNull(actualPrepareHeadResult.byteBufData);
    assertNull(actualPrepareHeadResult.file);
    assertNull(actualPrepareHeadResult.streamData);
    assertNull(actualPrepareHeadResult.followRedirect);
    assertNull(actualPrepareHeadResult.stringData);
    assertNull(actualPrepareHeadResult.virtualHost);
    assertNull(actualPrepareHeadResult.address);
    assertNull(actualPrepareHeadResult.localAddress);
    assertNull(actualPrepareHeadResult.byteBufferData);
    assertNull(actualPrepareHeadResult.charset);
    assertNull(actualPrepareHeadResult.readTimeout);
    assertNull(actualPrepareHeadResult.requestTimeout);
    assertNull(actualPrepareHeadResult.cookies);
    assertNull(actualPrepareHeadResult.compositeByteData);
    assertNull(actualPrepareHeadResult.formParams);
    assertNull(actualPrepareHeadResult.queryParams);
    assertNull(actualPrepareHeadResult.bodyParts);
    assertNull(actualPrepareHeadResult.realm);
    assertNull(actualPrepareHeadResult.signatureCalculator);
    assertNull(actualPrepareHeadResult.proxyServer);
    assertNull(actualPrepareHeadResult.bodyGenerator);
    assertEquals(0L, actualPrepareHeadResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPrepareHeadResult.uriEncoder);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#preparePost(String)}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#preparePost(String)}
   */
  @Test
  @DisplayName("Test preparePost(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.preparePost(String)"})
  void testPreparePost() {
    // Arrange and Act
    BoundRequestBuilder actualPreparePostResult = (new DefaultAsyncHttpClient())
        .preparePost("https://example.org/example");

    // Assert
    assertTrue(actualPreparePostResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPreparePostResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPreparePostResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("POST", actualPreparePostResult.method);
    assertNull(actualPreparePostResult.byteData);
    assertNull(actualPreparePostResult.byteBufData);
    assertNull(actualPreparePostResult.file);
    assertNull(actualPreparePostResult.streamData);
    assertNull(actualPreparePostResult.followRedirect);
    assertNull(actualPreparePostResult.stringData);
    assertNull(actualPreparePostResult.virtualHost);
    assertNull(actualPreparePostResult.address);
    assertNull(actualPreparePostResult.localAddress);
    assertNull(actualPreparePostResult.byteBufferData);
    assertNull(actualPreparePostResult.charset);
    assertNull(actualPreparePostResult.readTimeout);
    assertNull(actualPreparePostResult.requestTimeout);
    assertNull(actualPreparePostResult.cookies);
    assertNull(actualPreparePostResult.compositeByteData);
    assertNull(actualPreparePostResult.formParams);
    assertNull(actualPreparePostResult.queryParams);
    assertNull(actualPreparePostResult.bodyParts);
    assertNull(actualPreparePostResult.realm);
    assertNull(actualPreparePostResult.signatureCalculator);
    assertNull(actualPreparePostResult.proxyServer);
    assertNull(actualPreparePostResult.bodyGenerator);
    assertEquals(0L, actualPreparePostResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPreparePostResult.uriEncoder);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#preparePut(String)}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#preparePut(String)}
   */
  @Test
  @DisplayName("Test preparePut(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.preparePut(String)"})
  void testPreparePut() {
    // Arrange and Act
    BoundRequestBuilder actualPreparePutResult = (new DefaultAsyncHttpClient())
        .preparePut("https://example.org/example");

    // Assert
    assertTrue(actualPreparePutResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPreparePutResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPreparePutResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("PUT", actualPreparePutResult.method);
    assertNull(actualPreparePutResult.byteData);
    assertNull(actualPreparePutResult.byteBufData);
    assertNull(actualPreparePutResult.file);
    assertNull(actualPreparePutResult.streamData);
    assertNull(actualPreparePutResult.followRedirect);
    assertNull(actualPreparePutResult.stringData);
    assertNull(actualPreparePutResult.virtualHost);
    assertNull(actualPreparePutResult.address);
    assertNull(actualPreparePutResult.localAddress);
    assertNull(actualPreparePutResult.byteBufferData);
    assertNull(actualPreparePutResult.charset);
    assertNull(actualPreparePutResult.readTimeout);
    assertNull(actualPreparePutResult.requestTimeout);
    assertNull(actualPreparePutResult.cookies);
    assertNull(actualPreparePutResult.compositeByteData);
    assertNull(actualPreparePutResult.formParams);
    assertNull(actualPreparePutResult.queryParams);
    assertNull(actualPreparePutResult.bodyParts);
    assertNull(actualPreparePutResult.realm);
    assertNull(actualPreparePutResult.signatureCalculator);
    assertNull(actualPreparePutResult.proxyServer);
    assertNull(actualPreparePutResult.bodyGenerator);
    assertEquals(0L, actualPreparePutResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPreparePutResult.uriEncoder);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareDelete(String)}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#prepareDelete(String)}
   */
  @Test
  @DisplayName("Test prepareDelete(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareDelete(String)"})
  void testPrepareDelete() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareDeleteResult = (new DefaultAsyncHttpClient())
        .prepareDelete("https://example.org/example");

    // Assert
    assertTrue(actualPrepareDeleteResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareDeleteResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPrepareDeleteResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("DELETE", actualPrepareDeleteResult.method);
    assertNull(actualPrepareDeleteResult.byteData);
    assertNull(actualPrepareDeleteResult.byteBufData);
    assertNull(actualPrepareDeleteResult.file);
    assertNull(actualPrepareDeleteResult.streamData);
    assertNull(actualPrepareDeleteResult.followRedirect);
    assertNull(actualPrepareDeleteResult.stringData);
    assertNull(actualPrepareDeleteResult.virtualHost);
    assertNull(actualPrepareDeleteResult.address);
    assertNull(actualPrepareDeleteResult.localAddress);
    assertNull(actualPrepareDeleteResult.byteBufferData);
    assertNull(actualPrepareDeleteResult.charset);
    assertNull(actualPrepareDeleteResult.readTimeout);
    assertNull(actualPrepareDeleteResult.requestTimeout);
    assertNull(actualPrepareDeleteResult.cookies);
    assertNull(actualPrepareDeleteResult.compositeByteData);
    assertNull(actualPrepareDeleteResult.formParams);
    assertNull(actualPrepareDeleteResult.queryParams);
    assertNull(actualPrepareDeleteResult.bodyParts);
    assertNull(actualPrepareDeleteResult.realm);
    assertNull(actualPrepareDeleteResult.signatureCalculator);
    assertNull(actualPrepareDeleteResult.proxyServer);
    assertNull(actualPrepareDeleteResult.bodyGenerator);
    assertEquals(0L, actualPrepareDeleteResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPrepareDeleteResult.uriEncoder);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#preparePatch(String)}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#preparePatch(String)}
   */
  @Test
  @DisplayName("Test preparePatch(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.preparePatch(String)"})
  void testPreparePatch() {
    // Arrange and Act
    BoundRequestBuilder actualPreparePatchResult = (new DefaultAsyncHttpClient())
        .preparePatch("https://example.org/example");

    // Assert
    assertTrue(actualPreparePatchResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPreparePatchResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPreparePatchResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("PATCH", actualPreparePatchResult.method);
    assertNull(actualPreparePatchResult.byteData);
    assertNull(actualPreparePatchResult.byteBufData);
    assertNull(actualPreparePatchResult.file);
    assertNull(actualPreparePatchResult.streamData);
    assertNull(actualPreparePatchResult.followRedirect);
    assertNull(actualPreparePatchResult.stringData);
    assertNull(actualPreparePatchResult.virtualHost);
    assertNull(actualPreparePatchResult.address);
    assertNull(actualPreparePatchResult.localAddress);
    assertNull(actualPreparePatchResult.byteBufferData);
    assertNull(actualPreparePatchResult.charset);
    assertNull(actualPreparePatchResult.readTimeout);
    assertNull(actualPreparePatchResult.requestTimeout);
    assertNull(actualPreparePatchResult.cookies);
    assertNull(actualPreparePatchResult.compositeByteData);
    assertNull(actualPreparePatchResult.formParams);
    assertNull(actualPreparePatchResult.queryParams);
    assertNull(actualPreparePatchResult.bodyParts);
    assertNull(actualPreparePatchResult.realm);
    assertNull(actualPreparePatchResult.signatureCalculator);
    assertNull(actualPreparePatchResult.proxyServer);
    assertNull(actualPreparePatchResult.bodyGenerator);
    assertEquals(0L, actualPreparePatchResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPreparePatchResult.uriEncoder);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareTrace(String)}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#prepareTrace(String)}
   */
  @Test
  @DisplayName("Test prepareTrace(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareTrace(String)"})
  void testPrepareTrace() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareTraceResult = (new DefaultAsyncHttpClient())
        .prepareTrace("https://example.org/example");

    // Assert
    assertTrue(actualPrepareTraceResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareTraceResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPrepareTraceResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("TRACE", actualPrepareTraceResult.method);
    assertNull(actualPrepareTraceResult.byteData);
    assertNull(actualPrepareTraceResult.byteBufData);
    assertNull(actualPrepareTraceResult.file);
    assertNull(actualPrepareTraceResult.streamData);
    assertNull(actualPrepareTraceResult.followRedirect);
    assertNull(actualPrepareTraceResult.stringData);
    assertNull(actualPrepareTraceResult.virtualHost);
    assertNull(actualPrepareTraceResult.address);
    assertNull(actualPrepareTraceResult.localAddress);
    assertNull(actualPrepareTraceResult.byteBufferData);
    assertNull(actualPrepareTraceResult.charset);
    assertNull(actualPrepareTraceResult.readTimeout);
    assertNull(actualPrepareTraceResult.requestTimeout);
    assertNull(actualPrepareTraceResult.cookies);
    assertNull(actualPrepareTraceResult.compositeByteData);
    assertNull(actualPrepareTraceResult.formParams);
    assertNull(actualPrepareTraceResult.queryParams);
    assertNull(actualPrepareTraceResult.bodyParts);
    assertNull(actualPrepareTraceResult.realm);
    assertNull(actualPrepareTraceResult.signatureCalculator);
    assertNull(actualPrepareTraceResult.proxyServer);
    assertNull(actualPrepareTraceResult.bodyGenerator);
    assertEquals(0L, actualPrepareTraceResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPrepareTraceResult.uriEncoder);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#cookies} is {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#cookies}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName("Test prepareRequest(RequestBuilder) with 'requestBuilder'; then return cookies is RequestBuilder() cookies")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_thenReturnCookiesIsRequestBuilderCookies() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addCookie(new DefaultCookie("https://example.org/example", "https://example.org/example"));
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act and Assert
    assertEquals(requestBuilder.cookies, defaultAsyncHttpClient.prepareRequest(requestBuilder).cookies);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName("Test prepareRequest(RequestBuilder) with 'requestBuilder'; then return headers unwrap size is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    // Act and Assert
    HttpHeaders httpHeaders = defaultAsyncHttpClient.prepareRequest(new RequestBuilder()).headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(Request)} with {@code request}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} iterator.</li>
   *   <li>Then return {@link RequestBuilderBase#cookies} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#prepareRequest(Request)}
   */
  @Test
  @DisplayName("Test prepareRequest(Request) with 'request'; given ArrayList() iterator; then return cookies is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(Request)"})
  void testPrepareRequestWithRequest_givenArrayListIterator_thenReturnCookiesIsNull()
      throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf byteBufData = mock(ByteBuf.class);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualPrepareRequestResult = defaultAsyncHttpClient.prepareRequest(request);

    // Assert
    verify(headers).iterator();
    HttpHeaders httpHeaders = actualPrepareRequestResult.headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    HttpHeaders httpHeaders2 = request.toBuilder().headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertNull(actualPrepareRequestResult.cookies);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
    assertEquals(actualPrepareRequestResult.headers, httpHeaders2);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualPrepareRequestResult.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualPrepareRequestResult.byteData);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(Request)} with {@code request}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#cookies} is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#prepareRequest(Request)}
   */
  @Test
  @DisplayName("Test prepareRequest(Request) with 'request'; then return cookies is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(Request)"})
  void testPrepareRequestWithRequest_thenReturnCookiesIsArrayList() throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ArrayList<Cookie> cookies = new ArrayList<>();
    cookies.add(new DefaultCookie("https://example.org/example", "https://example.org/example"));
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf byteBufData = mock(ByteBuf.class);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    BoundRequestBuilder actualPrepareRequestResult = defaultAsyncHttpClient
        .prepareRequest(new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies,
            byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
            bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null,
            null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(headers).iterator();
    assertEquals(cookies, actualPrepareRequestResult.cookies);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualPrepareRequestResult.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualPrepareRequestResult.byteData);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#executeRequest(Request)} with {@code request}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#executeRequest(Request)}
   */
  @Test
  @DisplayName("Test executeRequest(Request) with 'request'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ListenableFuture DefaultAsyncHttpClient.executeRequest(Request)"})
  void testExecuteRequestWithRequest() throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();
    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");
    when(uri.isSecured()).thenReturn(true);
    when(uri.isWebSocket()).thenReturn(true);
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    HttpHeaders headers = mock(HttpHeaders.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf byteBufData = mock(ByteBuf.class);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    ListenableFuture<Response> actualExecuteRequestResult = defaultAsyncHttpClient
        .executeRequest(new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies,
            byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
            bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null,
            null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(uri).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri).getScheme();
    verify(uri).isSecured();
    verify(uri).isWebSocket();
    assertTrue(actualExecuteRequestResult instanceof CompletedFailure);
    assertTrue(actualExecuteRequestResult.isDone());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#executeRequest(Request, AsyncHandler)} with {@code request}, {@code handler}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#executeRequest(Request, AsyncHandler)}
   */
  @Test
  @DisplayName("Test executeRequest(Request, AsyncHandler) with 'request', 'handler'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ListenableFuture DefaultAsyncHttpClient.executeRequest(Request, AsyncHandler)"})
  void testExecuteRequestWithRequestHandler() throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();
    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");
    when(uri.isSecured()).thenReturn(true);
    when(uri.isWebSocket()).thenReturn(true);
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    HttpHeaders headers = mock(HttpHeaders.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf byteBufData = mock(ByteBuf.class);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteRequestResult = defaultAsyncHttpClient.executeRequest(request, handler);

    // Assert
    verify(handler).onThrowable(isA(Throwable.class));
    verify(uri).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri).getScheme();
    verify(uri).isSecured();
    verify(uri).isWebSocket();
    assertTrue(actualExecuteRequestResult instanceof CompletedFailure);
    assertTrue(actualExecuteRequestResult.isDone());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#getChannelPool()}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#getChannelPool()}
   */
  @Test
  @DisplayName("Test getChannelPool()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ChannelPool DefaultAsyncHttpClient.getChannelPool()"})
  void testGetChannelPool() {
    // Arrange and Act
    ChannelPool actualChannelPool = (new DefaultAsyncHttpClient()).getChannelPool();

    // Assert
    assertTrue(actualChannelPool instanceof DefaultChannelPool);
    assertTrue(actualChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#getEventLoopGroup()}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#getEventLoopGroup()}
   */
  @Test
  @DisplayName("Test getEventLoopGroup()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"EventLoopGroup DefaultAsyncHttpClient.getEventLoopGroup()"})
  void testGetEventLoopGroup() {
    // Arrange and Act
    EventLoopGroup actualEventLoopGroup = (new DefaultAsyncHttpClient()).getEventLoopGroup();

    // Assert
    assertTrue(actualEventLoopGroup.next() instanceof NioEventLoop);
    assertTrue(actualEventLoopGroup instanceof NioEventLoopGroup);
    assertTrue(actualEventLoopGroup.terminationFuture() instanceof DefaultPromise);
    assertTrue(actualEventLoopGroup.iterator().hasNext());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#getClientStats()}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#getClientStats()}
   */
  @Test
  @DisplayName("Test getClientStats()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ClientStats DefaultAsyncHttpClient.getClientStats()"})
  void testGetClientStats() {
    // Arrange and Act
    ClientStats actualClientStats = (new DefaultAsyncHttpClient()).getClientStats();

    // Assert
    assertEquals(0L, actualClientStats.getTotalActiveConnectionCount());
    assertEquals(0L, actualClientStats.getTotalConnectionCount());
    assertEquals(0L, actualClientStats.getTotalIdleConnectionCount());
    assertTrue(actualClientStats.getStatsPerHost().isEmpty());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#requestBuilder(String, String)} with {@code method}, {@code url}.
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#requestBuilder(String, String)}
   */
  @Test
  @DisplayName("Test requestBuilder(String, String) with 'method', 'url'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.requestBuilder(String, String)"})
  void testRequestBuilderWithMethodUrl() {
    // Arrange and Act
    BoundRequestBuilder actualRequestBuilderResult = (new DefaultAsyncHttpClient())
        .requestBuilder("https://example.org/example", "https://example.org/example");

    // Assert
    assertTrue(actualRequestBuilderResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilderResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilderResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualRequestBuilderResult.method);
    assertNull(actualRequestBuilderResult.byteData);
    assertNull(actualRequestBuilderResult.byteBufData);
    assertNull(actualRequestBuilderResult.file);
    assertNull(actualRequestBuilderResult.streamData);
    assertNull(actualRequestBuilderResult.followRedirect);
    assertNull(actualRequestBuilderResult.stringData);
    assertNull(actualRequestBuilderResult.virtualHost);
    assertNull(actualRequestBuilderResult.address);
    assertNull(actualRequestBuilderResult.localAddress);
    assertNull(actualRequestBuilderResult.byteBufferData);
    assertNull(actualRequestBuilderResult.charset);
    assertNull(actualRequestBuilderResult.readTimeout);
    assertNull(actualRequestBuilderResult.requestTimeout);
    assertNull(actualRequestBuilderResult.cookies);
    assertNull(actualRequestBuilderResult.compositeByteData);
    assertNull(actualRequestBuilderResult.formParams);
    assertNull(actualRequestBuilderResult.queryParams);
    assertNull(actualRequestBuilderResult.bodyParts);
    assertNull(actualRequestBuilderResult.realm);
    assertNull(actualRequestBuilderResult.signatureCalculator);
    assertNull(actualRequestBuilderResult.proxyServer);
    assertNull(actualRequestBuilderResult.bodyGenerator);
    assertEquals(0L, actualRequestBuilderResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualRequestBuilderResult.uriEncoder);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#requestBuilder(Request)} with {@code prototype}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} iterator.</li>
   *   <li>Then return {@link RequestBuilderBase#cookies} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#requestBuilder(Request)}
   */
  @Test
  @DisplayName("Test requestBuilder(Request) with 'prototype'; given ArrayList() iterator; then return cookies is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.requestBuilder(Request)"})
  void testRequestBuilderWithPrototype_givenArrayListIterator_thenReturnCookiesIsNull()
      throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf byteBufData = mock(ByteBuf.class);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualRequestBuilderResult = defaultAsyncHttpClient.requestBuilder(prototype);

    // Assert
    verify(headers).iterator();
    HttpHeaders httpHeaders = actualRequestBuilderResult.headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    HttpHeaders httpHeaders2 = prototype.toBuilder().headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertNull(actualRequestBuilderResult.cookies);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
    assertEquals(actualRequestBuilderResult.headers, httpHeaders2);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualRequestBuilderResult.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRequestBuilderResult.byteData);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#requestBuilder(Request)} with {@code prototype}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#cookies} is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultAsyncHttpClient#requestBuilder(Request)}
   */
  @Test
  @DisplayName("Test requestBuilder(Request) with 'prototype'; then return cookies is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.requestBuilder(Request)"})
  void testRequestBuilderWithPrototype_thenReturnCookiesIsArrayList() throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ArrayList<Cookie> cookies = new ArrayList<>();
    cookies.add(new DefaultCookie("https://example.org/example", "https://example.org/example"));
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf byteBufData = mock(ByteBuf.class);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    BoundRequestBuilder actualRequestBuilderResult = defaultAsyncHttpClient
        .requestBuilder(new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies,
            byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
            bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null,
            null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(headers).iterator();
    assertEquals(cookies, actualRequestBuilderResult.cookies);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualRequestBuilderResult.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRequestBuilderResult.byteData);
  }
}
