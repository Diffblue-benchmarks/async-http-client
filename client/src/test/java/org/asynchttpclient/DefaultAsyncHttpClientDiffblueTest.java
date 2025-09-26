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
import com.diffblue.cover.annotations.ManagedByDiffblue;
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
import io.netty.util.concurrent.EventExecutor;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.fileupload2.util.mime.RFC2231Utility;
import org.asynchttpclient.ListenableFuture.CompletedFailure;
import org.asynchttpclient.channel.ChannelPool;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.channel.ChannelPoolPartitioning.PerHostChannelPoolPartitioning;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.asynchttpclient.netty.NettyResponseFuture;
import org.asynchttpclient.netty.channel.ChannelManager;
import org.asynchttpclient.netty.channel.DefaultChannelPool;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#DefaultAsyncHttpClient()}
   */
  @Test
  @DisplayName("Test new DefaultAsyncHttpClient()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DefaultAsyncHttpClient#channelManager()}
   *   <li>{@link DefaultAsyncHttpClient#getConfig()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ChannelManager DefaultAsyncHttpClient.channelManager()",
    "org.asynchttpclient.AsyncHttpClientConfig DefaultAsyncHttpClient.getConfig()"
  })
  void testGettersAndSetters() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    // Act
    ChannelManager actualChannelManagerResult = defaultAsyncHttpClient.channelManager();

    // Assert
    assertTrue(actualChannelManagerResult.getEventLoopGroup() instanceof NioEventLoopGroup);
    assertTrue(defaultAsyncHttpClient.getConfig() instanceof DefaultAsyncHttpClientConfig);
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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
    Iterator<EventExecutor> iteratorResult = eventLoopGroup.iterator();
    EventExecutor nextResult2 = iteratorResult.next();
    assertTrue(nextResult2 instanceof NioEventLoop);
    EventExecutor nextResult3 = iteratorResult.next();
    assertTrue(nextResult3 instanceof NioEventLoop);
    assertTrue(eventLoopGroup instanceof NioEventLoopGroup);
    assertTrue(nextResult.isShuttingDown());
    assertTrue(nextResult2.isShuttingDown());
    assertTrue(nextResult3.isShuttingDown());
    assertTrue(eventLoopGroup.isShuttingDown());
    assertTrue(iteratorResult.hasNext());
    assertTrue(defaultAsyncHttpClient.isClosed());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#isClosed()}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#isClosed()}
   */
  @Test
  @DisplayName("Test isClosed()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DefaultAsyncHttpClient.isClosed()"})
  void testIsClosed() {
    // Arrange, Act and Assert
    assertFalse(new DefaultAsyncHttpClient().isClosed());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#setSignatureCalculator(SignatureCalculator)}.
   *
   * <p>Method under test: {@link
   * DefaultAsyncHttpClient#setSignatureCalculator(SignatureCalculator)}
   */
  @Test
  @DisplayName("Test setSignatureCalculator(SignatureCalculator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DefaultAsyncHttpClient DefaultAsyncHttpClient.setSignatureCalculator(SignatureCalculator)"
  })
  void testSetSignatureCalculator() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    // Act
    DefaultAsyncHttpClient actualSetSignatureCalculatorResult =
        defaultAsyncHttpClient.setSignatureCalculator(mock(SignatureCalculator.class));

    // Assert
    assertSame(defaultAsyncHttpClient, actualSetSignatureCalculatorResult);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepare(String, String)}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepare(String, String)}
   */
  @Test
  @DisplayName("Test prepare(String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepare(String, String)"})
  void testPrepare() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareResult =
        new DefaultAsyncHttpClient()
            .prepare("https://example.org/example", "https://example.org/example");

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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareGet(String)}
   */
  @Test
  @DisplayName("Test prepareGet(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareGet(String)"})
  void testPrepareGet() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareGetResult =
        new DefaultAsyncHttpClient().prepareGet("https://example.org/example");

    // Assert
    assertTrue(actualPrepareGetResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareGetResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualPrepareGetResult.channelPoolPartitioning;
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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareConnect(String)}
   */
  @Test
  @DisplayName("Test prepareConnect(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareConnect(String)"})
  void testPrepareConnect() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareConnectResult =
        new DefaultAsyncHttpClient().prepareConnect("https://example.org/example");

    // Assert
    assertTrue(actualPrepareConnectResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareConnectResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualPrepareConnectResult.channelPoolPartitioning;
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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareOptions(String)}
   */
  @Test
  @DisplayName("Test prepareOptions(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareOptions(String)"})
  void testPrepareOptions() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareOptionsResult =
        new DefaultAsyncHttpClient().prepareOptions("https://example.org/example");

    // Assert
    assertTrue(actualPrepareOptionsResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareOptionsResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualPrepareOptionsResult.channelPoolPartitioning;
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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareHead(String)}
   */
  @Test
  @DisplayName("Test prepareHead(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareHead(String)"})
  void testPrepareHead() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();
    defaultAsyncHttpClient.setSignatureCalculator(mock(SignatureCalculator.class));

    // Act
    BoundRequestBuilder actualPrepareHeadResult =
        defaultAsyncHttpClient.prepareHead("https://example.org/example");

    // Assert
    assertTrue(actualPrepareHeadResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareHeadResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualPrepareHeadResult.channelPoolPartitioning;
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
    assertNull(actualPrepareHeadResult.proxyServer);
    assertNull(actualPrepareHeadResult.bodyGenerator);
    assertEquals(0L, actualPrepareHeadResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPrepareHeadResult.uriEncoder);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareHead(String)}.
   *
   * <ul>
   *   <li>Given {@link DefaultAsyncHttpClient#DefaultAsyncHttpClient()}.
   *   <li>Then return {@link RequestBuilderBase#signatureCalculator} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareHead(String)}
   */
  @Test
  @DisplayName(
      "Test prepareHead(String); given DefaultAsyncHttpClient(); then return signatureCalculator is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareHead(String)"})
  void testPrepareHead_givenDefaultAsyncHttpClient_thenReturnSignatureCalculatorIsNull() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareHeadResult =
        new DefaultAsyncHttpClient().prepareHead("https://example.org/example");

    // Assert
    assertTrue(actualPrepareHeadResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareHeadResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualPrepareHeadResult.channelPoolPartitioning;
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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#preparePost(String)}
   */
  @Test
  @DisplayName("Test preparePost(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.preparePost(String)"})
  void testPreparePost() {
    // Arrange and Act
    BoundRequestBuilder actualPreparePostResult =
        new DefaultAsyncHttpClient().preparePost("https://example.org/example");

    // Assert
    assertTrue(actualPreparePostResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPreparePostResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualPreparePostResult.channelPoolPartitioning;
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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#preparePut(String)}
   */
  @Test
  @DisplayName("Test preparePut(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.preparePut(String)"})
  void testPreparePut() {
    // Arrange and Act
    BoundRequestBuilder actualPreparePutResult =
        new DefaultAsyncHttpClient().preparePut("https://example.org/example");

    // Assert
    assertTrue(actualPreparePutResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPreparePutResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualPreparePutResult.channelPoolPartitioning;
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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareDelete(String)}
   */
  @Test
  @DisplayName("Test prepareDelete(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareDelete(String)"})
  void testPrepareDelete() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareDeleteResult =
        new DefaultAsyncHttpClient().prepareDelete("https://example.org/example");

    // Assert
    assertTrue(actualPrepareDeleteResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareDeleteResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualPrepareDeleteResult.channelPoolPartitioning;
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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#preparePatch(String)}
   */
  @Test
  @DisplayName("Test preparePatch(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.preparePatch(String)"})
  void testPreparePatch() {
    // Arrange and Act
    BoundRequestBuilder actualPreparePatchResult =
        new DefaultAsyncHttpClient().preparePatch("https://example.org/example");

    // Assert
    assertTrue(actualPreparePatchResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPreparePatchResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualPreparePatchResult.channelPoolPartitioning;
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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareTrace(String)}
   */
  @Test
  @DisplayName("Test prepareTrace(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareTrace(String)"})
  void testPrepareTrace() {
    // Arrange and Act
    BoundRequestBuilder actualPrepareTraceResult =
        new DefaultAsyncHttpClient().prepareTrace("https://example.org/example");

    // Assert
    assertTrue(actualPrepareTraceResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPrepareTraceResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualPrepareTraceResult.channelPoolPartitioning;
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
   * Test {@link DefaultAsyncHttpClient#prepareRequest(Request)} with {@code request}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(Request)}
   */
  @Test
  @DisplayName("Test prepareRequest(Request) with 'request'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(Request)"})
  void testPrepareRequestWithRequest() throws UnsupportedEncodingException {
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualPrepareRequestResult = defaultAsyncHttpClient.prepareRequest(request);

    // Assert
    verify(headers).iterator();
    assertSame(actualPrepareRequestResult.address, request.getAddress());
    assertSame(actualPrepareRequestResult.bodyGenerator, request.getBodyGenerator());
    assertSame(actualPrepareRequestResult.byteBufData, request.getByteBufData());
    assertSame(actualPrepareRequestResult.byteBufferData, request.getByteBufferData());
    assertSame(actualPrepareRequestResult.byteData, request.getByteData());
    assertSame(
        actualPrepareRequestResult.channelPoolPartitioning, request.getChannelPoolPartitioning());
    assertSame(actualPrepareRequestResult.charset, request.getCharset());
    assertSame(actualPrepareRequestResult.compositeByteData, request.getCompositeByteData());
    assertSame(actualPrepareRequestResult.file, request.getFile());
    assertSame(actualPrepareRequestResult.localAddress, request.getLocalAddress());
    assertSame(actualPrepareRequestResult.nameResolver, request.getNameResolver());
    assertSame(actualPrepareRequestResult.proxyServer, request.getProxyServer());
    assertSame(actualPrepareRequestResult.readTimeout, request.getReadTimeout());
    assertSame(actualPrepareRequestResult.realm, request.getRealm());
    assertSame(actualPrepareRequestResult.requestTimeout, request.getRequestTimeout());
    assertSame(actualPrepareRequestResult.streamData, request.getStreamData());
    assertSame(actualPrepareRequestResult.uri, request.getUri());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualPrepareRequestResult.byteData);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(Request)} with {@code request}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(Request)}
   */
  @Test
  @DisplayName("Test prepareRequest(Request) with 'request'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(Request)"})
  void testPrepareRequestWithRequest2() throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "A=AXAXAX".getBytes("UTF-8");
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualPrepareRequestResult = defaultAsyncHttpClient.prepareRequest(request);

    // Assert
    verify(headers).iterator();
    assertSame(actualPrepareRequestResult.address, request.getAddress());
    assertSame(actualPrepareRequestResult.bodyGenerator, request.getBodyGenerator());
    assertSame(actualPrepareRequestResult.byteBufData, request.getByteBufData());
    assertSame(actualPrepareRequestResult.byteBufferData, request.getByteBufferData());
    assertSame(actualPrepareRequestResult.byteData, request.getByteData());
    assertSame(
        actualPrepareRequestResult.channelPoolPartitioning, request.getChannelPoolPartitioning());
    assertSame(actualPrepareRequestResult.charset, request.getCharset());
    assertSame(actualPrepareRequestResult.compositeByteData, request.getCompositeByteData());
    assertSame(actualPrepareRequestResult.file, request.getFile());
    assertSame(actualPrepareRequestResult.localAddress, request.getLocalAddress());
    assertSame(actualPrepareRequestResult.nameResolver, request.getNameResolver());
    assertSame(actualPrepareRequestResult.proxyServer, request.getProxyServer());
    assertSame(actualPrepareRequestResult.readTimeout, request.getReadTimeout());
    assertSame(actualPrepareRequestResult.realm, request.getRealm());
    assertSame(actualPrepareRequestResult.requestTimeout, request.getRequestTimeout());
    assertSame(actualPrepareRequestResult.streamData, request.getStreamData());
    assertSame(actualPrepareRequestResult.uri, request.getUri());
    assertArrayEquals("A=AXAXAX".getBytes("UTF-8"), actualPrepareRequestResult.byteData);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(Request)} with {@code request}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(Request)}
   */
  @Test
  @DisplayName("Test prepareRequest(Request) with 'request'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(Request)"})
  void testPrepareRequestWithRequest3() throws UnsupportedEncodingException {
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
    Duration requestTimeout = Duration.ofSeconds(0L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualPrepareRequestResult = defaultAsyncHttpClient.prepareRequest(request);

    // Assert
    verify(headers).iterator();
    assertSame(actualPrepareRequestResult.address, request.getAddress());
    assertSame(actualPrepareRequestResult.bodyGenerator, request.getBodyGenerator());
    assertSame(actualPrepareRequestResult.byteBufData, request.getByteBufData());
    assertSame(actualPrepareRequestResult.byteBufferData, request.getByteBufferData());
    assertSame(actualPrepareRequestResult.byteData, request.getByteData());
    assertSame(
        actualPrepareRequestResult.channelPoolPartitioning, request.getChannelPoolPartitioning());
    assertSame(actualPrepareRequestResult.charset, request.getCharset());
    assertSame(actualPrepareRequestResult.compositeByteData, request.getCompositeByteData());
    assertSame(actualPrepareRequestResult.file, request.getFile());
    assertSame(actualPrepareRequestResult.localAddress, request.getLocalAddress());
    assertSame(actualPrepareRequestResult.nameResolver, request.getNameResolver());
    assertSame(actualPrepareRequestResult.proxyServer, request.getProxyServer());
    assertSame(actualPrepareRequestResult.readTimeout, request.getReadTimeout());
    assertSame(actualPrepareRequestResult.realm, request.getRealm());
    assertSame(actualPrepareRequestResult.requestTimeout, request.getRequestTimeout());
    assertSame(actualPrepareRequestResult.streamData, request.getStreamData());
    assertSame(actualPrepareRequestResult.uri, request.getUri());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualPrepareRequestResult.byteData);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName("Test prepareRequest(RequestBuilder) with 'requestBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    assertNull(requestBuilder.streamData);
    assertNull(actualPrepareRequestResult.streamData);
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(0L, actualPrepareRequestResult.rangeOffset);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName("Test prepareRequest(RequestBuilder) with 'requestBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder2() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertNull(requestBuilder.streamData);
    assertNull(actualPrepareRequestResult.streamData);
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(0L, actualPrepareRequestResult.rangeOffset);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName("Test prepareRequest(RequestBuilder) with 'requestBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder3() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    ByteArrayPart bodyPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    requestBuilder.addBodyPart(bodyPart);
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertEquals(requestBuilder.bodyParts, actualPrepareRequestResult.bodyParts);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName("Test prepareRequest(RequestBuilder) with 'requestBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder4() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.AGGREGATE_WEBSOCKET_FRAME_FRAGMENTS_CONFIG,
        "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertNull(requestBuilder.streamData);
    assertNull(actualPrepareRequestResult.streamData);
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(0L, actualPrepareRequestResult.rangeOffset);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName("Test prepareRequest(RequestBuilder) with 'requestBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder5() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.DISABLE_URL_ENCODING_FOR_BOUND_REQUESTS_CONFIG,
        "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertNull(requestBuilder.streamData);
    assertNull(actualPrepareRequestResult.streamData);
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(0L, actualPrepareRequestResult.rangeOffset);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName("Test prepareRequest(RequestBuilder) with 'requestBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder6() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    ByteArrayPart bodyPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    bodyPart.addCustomHeader("https://example.org/example", "https://example.org/example");

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addBodyPart(bodyPart);
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    List<Part> partList = actualPrepareRequestResult.bodyParts;
    assertEquals(1, partList.size());
    Part getResult = partList.get(0);
    assertTrue(getResult instanceof ByteArrayPart);
    List<Param> customHeaders = getResult.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult2 = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult2.getName());
    assertEquals("https://example.org/example", getResult2.getValue());
    assertArrayEquals(
        new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1}, ((ByteArrayPart) getResult).getBytes());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName("Test prepareRequest(RequestBuilder) with 'requestBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder7() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    ByteArrayPart bodyPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    bodyPart.addCustomHeader("https://example.org/example", "https://example.org/example");

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setVirtualHost("https://example.org/example");
    requestBuilder.addBodyPart(bodyPart);
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    List<Part> partList = actualPrepareRequestResult.bodyParts;
    assertEquals(1, partList.size());
    Part getResult = partList.get(0);
    assertTrue(getResult instanceof ByteArrayPart);
    List<Param> customHeaders = getResult.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult2 = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult2.getName());
    assertEquals("https://example.org/example", getResult2.getValue());
    assertEquals("https://example.org/example", actualPrepareRequestResult.virtualHost);
    assertArrayEquals(
        new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1}, ((ByteArrayPart) getResult).getBytes());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName("Test prepareRequest(RequestBuilder) with 'requestBuilder'; given ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_givenArrayList() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    ByteArrayPart bodyPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    bodyPart.addCustomHeader("https://example.org/example", "https://example.org/example");

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
        (Iterable<?>) new ArrayList<>());
    requestBuilder.addBodyPart(bodyPart);
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    List<Part> partList = actualPrepareRequestResult.bodyParts;
    assertEquals(1, partList.size());
    Part getResult = partList.get(0);
    assertTrue(getResult instanceof ByteArrayPart);
    List<Param> customHeaders = getResult.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult2 = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult2.getName());
    assertEquals("https://example.org/example", getResult2.getValue());
    assertArrayEquals(
        new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1}, ((ByteArrayPart) getResult).getBytes());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Given array of {@code byte} with {@code A} and one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; given array of byte with 'A' and one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_givenArrayOfByteWithAAndOne() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setBody(new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    ByteArrayPart bodyPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    requestBuilder.addBodyPart(bodyPart);
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.CONNECTION_POOL_CLEANER_PERIOD_CONFIG,
        "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertEquals(requestBuilder.bodyParts, actualPrepareRequestResult.bodyParts);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientConfigDefaults#ASYNC_CLIENT_CONFIG_ROOT}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; given ASYNC_CLIENT_CONFIG_ROOT")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_givenAsync_client_config_root() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ASYNC_CLIENT_CONFIG_ROOT, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertNull(requestBuilder.streamData);
    assertNull(actualPrepareRequestResult.streamData);
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(0L, actualPrepareRequestResult.rangeOffset);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientConfigDefaults#COMPRESSION_ENFORCED_CONFIG}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; given COMPRESSION_ENFORCED_CONFIG")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_givenCompression_enforced_config() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.COMPRESSION_ENFORCED_CONFIG, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertNull(requestBuilder.streamData);
    assertNull(actualPrepareRequestResult.streamData);
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(0L, actualPrepareRequestResult.rangeOffset);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientConfigDefaults#CONNECTION_POOL_CLEANER_PERIOD_CONFIG}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; given CONNECTION_POOL_CLEANER_PERIOD_CONFIG")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_givenConnection_pool_cleaner_period_config() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    ByteArrayPart bodyPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    requestBuilder.addBodyPart(bodyPart);
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.CONNECTION_POOL_CLEANER_PERIOD_CONFIG,
        "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertEquals(requestBuilder.bodyParts, actualPrepareRequestResult.bodyParts);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientConfigDefaults#CONNECTION_TIMEOUT_CONFIG}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; given CONNECTION_TIMEOUT_CONFIG")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_givenConnection_timeout_config() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.CONNECTION_TIMEOUT_CONFIG, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertNull(requestBuilder.streamData);
    assertNull(actualPrepareRequestResult.streamData);
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(0L, actualPrepareRequestResult.rangeOffset);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientConfigDefaults#CONNECTION_TTL_CONFIG}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; given CONNECTION_TTL_CONFIG")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_givenConnection_ttl_config() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.CONNECTION_TTL_CONFIG, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertNull(requestBuilder.streamData);
    assertNull(actualPrepareRequestResult.streamData);
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(0L, actualPrepareRequestResult.rangeOffset);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Given decodeText {@code secret}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; given decodeText 'secret'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_givenDecodeTextSecret()
      throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(RFC2231Utility.decodeText("secret"), "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertNull(requestBuilder.streamData);
    assertNull(actualPrepareRequestResult.streamData);
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(0L, actualPrepareRequestResult.rangeOffset);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientConfigDefaults#DISABLE_ZERO_COPY_CONFIG}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; given DISABLE_ZERO_COPY_CONFIG")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_givenDisable_zero_copy_config() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.DISABLE_ZERO_COPY_CONFIG, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertNull(requestBuilder.streamData);
    assertNull(actualPrepareRequestResult.streamData);
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(0L, actualPrepareRequestResult.rangeOffset);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Given forName {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName("Test prepareRequest(RequestBuilder) with 'requestBuilder'; given forName 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_givenForNameUtf8() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setCharset(Charset.forName("UTF-8"));
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    assertNull(requestBuilder.streamData);
    assertNull(actualPrepareRequestResult.streamData);
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(0L, actualPrepareRequestResult.rangeOffset);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>Then return {@link RequestBuilderBase#rangeOffset} is one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; given one; then return rangeOffset is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_givenOne_thenReturnRangeOffsetIsOne()
      throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setRangeOffset(1L);
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(RFC2231Utility.decodeText("secret"), "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertNull(requestBuilder.streamData);
    assertNull(actualPrepareRequestResult.streamData);
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(1L, actualPrepareRequestResult.rangeOffset);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Given {@code scheme}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName("Test prepareRequest(RequestBuilder) with 'requestBuilder'; given 'scheme'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_givenScheme() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    ByteArrayPart bodyPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    requestBuilder.addBodyPart(bodyPart);
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "scheme");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    assertEquals(requestBuilder.bodyParts, actualPrepareRequestResult.bodyParts);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#charset} name is
   *       {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; then RequestBuilder() charset name is 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_thenRequestBuilderCharsetNameIsUtf8() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setBody(mock(BodyGenerator.class));
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act and Assert
    HttpHeaders httpHeaders = defaultAsyncHttpClient.prepareRequest(requestBuilder).headers;
    assertTrue(((DefaultHttpHeaders) httpHeaders).unwrap() instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals("UTF-8", requestBuilder.charset.name());
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#byteBufferData} position is zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; then return byteBufferData position is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_thenReturnByteBufferDataPositionIsZero() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setBody(ByteBuffer.wrap(new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1}));
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.COMPRESSION_ENFORCED_CONFIG, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    ByteBuffer byteBuffer = actualPrepareRequestResult.byteBufferData;
    assertEquals(0, byteBuffer.position());
    assertEquals(8, byteBuffer.capacity());
    assertEquals(8, byteBuffer.limit());
    assertTrue(byteBuffer.hasRemaining());
    assertTrue(byteBuffer.hasArray());
    assertArrayEquals(new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1}, byteBuffer.array());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#cookies} is {@link RequestBuilder#RequestBuilder()}
   *       {@link RequestBuilderBase#cookies}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; then return cookies is RequestBuilder() cookies")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_thenReturnCookiesIsRequestBuilderCookies() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addCookie(
        new DefaultCookie("https://example.org/example", "https://example.org/example"));
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    assertEquals(requestBuilder.cookies, actualPrepareRequestResult.cookies);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#file} Name is {@code test.txt}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; then return file Name is 'test.txt'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_thenReturnFileNameIsTestTxt() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setBody(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    ByteArrayPart bodyPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    requestBuilder.addBodyPart(bodyPart);
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "scheme");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    List<Part> partList = actualPrepareRequestResult.bodyParts;
    assertEquals(1, partList.size());
    Part getResult = partList.get(0);
    assertTrue(getResult instanceof ByteArrayPart);
    File file = actualPrepareRequestResult.file;
    assertEquals("test.txt", file.getName());
    assertNull(getResult.getCustomHeaders());
    assertTrue(file.isAbsolute());
    assertArrayEquals(
        new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1}, ((ByteArrayPart) getResult).getBytes());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#headers} is {@link
   *       DefaultHttpHeaders#DefaultHttpHeaders()}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; then return headers is DefaultHttpHeaders()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_thenReturnHeadersIsDefaultHttpHeaders() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    ByteArrayPart bodyPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    bodyPart.addCustomHeader("https://example.org/example", "https://example.org/example");

    RequestBuilder requestBuilder = new RequestBuilder();
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    requestBuilder.setHeaders(headers);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
        (Iterable<?>) new ArrayList<>());
    requestBuilder.addBodyPart(bodyPart);
    requestBuilder.setSignatureCalculator(signatureCalculator);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    HttpHeaders httpHeaders = actualPrepareRequestResult.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    List<Part> partList = actualPrepareRequestResult.bodyParts;
    assertEquals(1, partList.size());
    Part getResult = partList.get(0);
    assertTrue(getResult instanceof ByteArrayPart);
    List<Param> customHeaders = getResult.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult2 = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult2.getName());
    assertEquals("https://example.org/example", getResult2.getValue());
    assertEquals(headers, httpHeaders);
    assertArrayEquals(
        new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1}, ((ByteArrayPart) getResult).getBytes());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; then return headers unwrap size is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    // Act and Assert
    HttpHeaders httpHeaders = defaultAsyncHttpClient.prepareRequest(new RequestBuilder()).headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)} with {@code requestBuilder}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#streamData} read is eight.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(RequestBuilder)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(RequestBuilder) with 'requestBuilder'; then return streamData read is eight")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(RequestBuilder)"})
  void testPrepareRequestWithRequestBuilder_thenReturnStreamDataReadIsEight() throws IOException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    RequestBuilder requestBuilder = new RequestBuilder();
    ByteArrayInputStream stream =
        new ByteArrayInputStream(new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    requestBuilder.setBody(stream);
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    BoundRequestBuilder actualPrepareRequestResult =
        defaultAsyncHttpClient.prepareRequest(requestBuilder);

    // Assert
    assertNull(actualPrepareRequestResult.bodyGenerator);
    assertEquals(0L, actualPrepareRequestResult.rangeOffset);
    byte[] byteArray = new byte[8];
    assertEquals(8, actualPrepareRequestResult.streamData.read(byteArray));
    assertArrayEquals(new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1}, byteArray);
    int actualReadResult = requestBuilder.streamData.read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(Request)} with {@code request}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then return {@link RequestBuilderBase#compositeByteData} size is one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(Request)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(Request) with 'request'; given 'A'; then return compositeByteData size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(Request)"})
  void testPrepareRequestWithRequest_givenA_thenReturnCompositeByteDataSizeIsOne()
      throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    compositeByteData.add(new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf byteBufData = mock(ByteBuf.class);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualPrepareRequestResult = defaultAsyncHttpClient.prepareRequest(request);

    // Assert
    verify(headers).iterator();
    List<byte[]> byteArrayList = actualPrepareRequestResult.compositeByteData;
    assertEquals(1, byteArrayList.size());
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualPrepareRequestResult.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualPrepareRequestResult.byteData);
    assertArrayEquals(new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1}, byteArrayList.get(0));
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(Request)} with {@code request}.
   *
   * <ul>
   *   <li>Then {@code AXAXXXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(Request)}
   */
  @Test
  @DisplayName("Test prepareRequest(Request) with 'request'; then 'AXAXXXAX' Bytes is 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(Request)"})
  void testPrepareRequestWithRequest_thenAxaxxxaxBytesIsUtf8() throws IOException {
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
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXXXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualPrepareRequestResult = defaultAsyncHttpClient.prepareRequest(request);

    // Assert
    verify(headers).iterator();
    byte[] byteArray = new byte[8];
    assertEquals(8, actualPrepareRequestResult.streamData.read(byteArray));
    assertSame(actualPrepareRequestResult.address, request.getAddress());
    assertSame(actualPrepareRequestResult.bodyGenerator, request.getBodyGenerator());
    assertSame(actualPrepareRequestResult.byteBufData, request.getByteBufData());
    assertSame(actualPrepareRequestResult.byteBufferData, request.getByteBufferData());
    assertSame(actualPrepareRequestResult.byteData, request.getByteData());
    assertSame(
        actualPrepareRequestResult.channelPoolPartitioning, request.getChannelPoolPartitioning());
    assertSame(actualPrepareRequestResult.charset, request.getCharset());
    assertSame(actualPrepareRequestResult.compositeByteData, request.getCompositeByteData());
    assertSame(actualPrepareRequestResult.file, request.getFile());
    assertSame(actualPrepareRequestResult.localAddress, request.getLocalAddress());
    assertSame(actualPrepareRequestResult.nameResolver, request.getNameResolver());
    assertSame(actualPrepareRequestResult.proxyServer, request.getProxyServer());
    assertSame(actualPrepareRequestResult.readTimeout, request.getReadTimeout());
    assertSame(actualPrepareRequestResult.realm, request.getRealm());
    assertSame(actualPrepareRequestResult.requestTimeout, request.getRequestTimeout());
    assertSame(actualPrepareRequestResult.streamData, request.getStreamData());
    assertSame(actualPrepareRequestResult.uri, request.getUri());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualPrepareRequestResult.byteData);
    assertArrayEquals("AXAXXXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(Request)} with {@code request}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilderBase#headers} return {@link DefaultHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(Request)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(Request) with 'request'; then headers return DefaultHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(Request)"})
  void testPrepareRequestWithRequest_thenHeadersReturnDefaultHttpHeaders()
      throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);

    HttpHeaders headers = mock(HttpHeaders.class);
    SimpleEntry<String, String> simpleEntry = new SimpleEntry<>("foo", "foo");

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    entryList.add(simpleEntry);
    when(headers.iterator()).thenReturn(entryList.iterator());
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualPrepareRequestResult = defaultAsyncHttpClient.prepareRequest(request);

    // Assert
    assertArrayEquals(
        "AXAXAXAX".getBytes("UTF-8"), actualPrepareRequestResult.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualPrepareRequestResult.byteData);
    HttpHeaders httpHeaders = actualPrepareRequestResult.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertFalse(httpHeaders.isEmpty());
    assertEquals(1, httpHeaders.size());
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertFalse(unwrapResult.isEmpty());
    Iterator<Entry<CharSequence, CharSequence>> iteratorResult = unwrapResult.iterator();
    Entry<CharSequence, CharSequence> actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertEquals(simpleEntry, actualNextResult);
    assertEquals(1, unwrapResult.size());
    verify(headers).iterator();
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(Request)} with {@code request}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#cookies} is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(Request)}
   */
  @Test
  @DisplayName("Test prepareRequest(Request) with 'request'; then return cookies is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(Request)"})
  void testPrepareRequestWithRequest_thenReturnCookiesIsArrayList()
      throws UnsupportedEncodingException {
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualPrepareRequestResult = defaultAsyncHttpClient.prepareRequest(request);

    // Assert
    verify(headers).iterator();
    assertEquals(cookies, actualPrepareRequestResult.cookies);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualPrepareRequestResult.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualPrepareRequestResult.byteData);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(Request)} with {@code request}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#rangeOffset} is {@link Long#MIN_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(Request)}
   */
  @Test
  @DisplayName("Test prepareRequest(Request) with 'request'; then return rangeOffset is MIN_VALUE")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(Request)"})
  void testPrepareRequestWithRequest_thenReturnRangeOffsetIsMin_value()
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            Long.MIN_VALUE,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualPrepareRequestResult = defaultAsyncHttpClient.prepareRequest(request);

    // Assert
    verify(headers).iterator();
    assertEquals(Long.MIN_VALUE, actualPrepareRequestResult.rangeOffset);
    assertSame(actualPrepareRequestResult.address, request.getAddress());
    assertSame(actualPrepareRequestResult.bodyGenerator, request.getBodyGenerator());
    assertSame(actualPrepareRequestResult.byteBufData, request.getByteBufData());
    assertSame(actualPrepareRequestResult.byteBufferData, request.getByteBufferData());
    assertSame(actualPrepareRequestResult.byteData, request.getByteData());
    assertSame(
        actualPrepareRequestResult.channelPoolPartitioning, request.getChannelPoolPartitioning());
    assertSame(actualPrepareRequestResult.charset, request.getCharset());
    assertSame(actualPrepareRequestResult.compositeByteData, request.getCompositeByteData());
    assertSame(actualPrepareRequestResult.file, request.getFile());
    assertSame(actualPrepareRequestResult.localAddress, request.getLocalAddress());
    assertSame(actualPrepareRequestResult.nameResolver, request.getNameResolver());
    assertSame(actualPrepareRequestResult.proxyServer, request.getProxyServer());
    assertSame(actualPrepareRequestResult.readTimeout, request.getReadTimeout());
    assertSame(actualPrepareRequestResult.realm, request.getRealm());
    assertSame(actualPrepareRequestResult.requestTimeout, request.getRequestTimeout());
    assertSame(actualPrepareRequestResult.streamData, request.getStreamData());
    assertSame(actualPrepareRequestResult.uri, request.getUri());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualPrepareRequestResult.byteData);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#prepareRequest(Request)} with {@code request}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then array of {@code byte} with {@code A} and {@code X}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#prepareRequest(Request)}
   */
  @Test
  @DisplayName(
      "Test prepareRequest(Request) with 'request'; when 'A'; then array of byte with 'A' and 'X'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.prepareRequest(Request)"})
  void testPrepareRequestWithRequest_whenA_thenArrayOfByteWithAAndX() throws IOException {
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
    ByteArrayInputStream streamData =
        new ByteArrayInputStream(new byte[] {'A', 'X', 'A', 'X', 'A', 'X', -1, 'X'});
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualPrepareRequestResult = defaultAsyncHttpClient.prepareRequest(request);

    // Assert
    verify(headers).iterator();
    byte[] byteArray = new byte[8];
    assertEquals(8, actualPrepareRequestResult.streamData.read(byteArray));
    assertSame(actualPrepareRequestResult.address, request.getAddress());
    assertSame(actualPrepareRequestResult.bodyGenerator, request.getBodyGenerator());
    assertSame(actualPrepareRequestResult.byteBufData, request.getByteBufData());
    assertSame(actualPrepareRequestResult.byteBufferData, request.getByteBufferData());
    assertSame(actualPrepareRequestResult.byteData, request.getByteData());
    assertSame(
        actualPrepareRequestResult.channelPoolPartitioning, request.getChannelPoolPartitioning());
    assertSame(actualPrepareRequestResult.charset, request.getCharset());
    assertSame(actualPrepareRequestResult.compositeByteData, request.getCompositeByteData());
    assertSame(actualPrepareRequestResult.file, request.getFile());
    assertSame(actualPrepareRequestResult.localAddress, request.getLocalAddress());
    assertSame(actualPrepareRequestResult.nameResolver, request.getNameResolver());
    assertSame(actualPrepareRequestResult.proxyServer, request.getProxyServer());
    assertSame(actualPrepareRequestResult.readTimeout, request.getReadTimeout());
    assertSame(actualPrepareRequestResult.realm, request.getRealm());
    assertSame(actualPrepareRequestResult.requestTimeout, request.getRequestTimeout());
    assertSame(actualPrepareRequestResult.streamData, request.getStreamData());
    assertSame(actualPrepareRequestResult.uri, request.getUri());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualPrepareRequestResult.byteData);
    assertArrayEquals(new byte[] {'A', 'X', 'A', 'X', 'A', 'X', -1, 'X'}, byteArray);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#executeRequest(Request)} with {@code request}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#executeRequest(Request)}
   */
  @Test
  @DisplayName("Test executeRequest(Request) with 'request'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    ListenableFuture<Response> actualExecuteRequestResult =
        defaultAsyncHttpClient.executeRequest(request);

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
   * Test {@link DefaultAsyncHttpClient#executeRequest(RequestBuilder, AsyncHandler)} with {@code
   * requestBuilder}, {@code handler}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#executeRequest(RequestBuilder,
   * AsyncHandler)}
   */
  @Test
  @DisplayName("Test executeRequest(RequestBuilder, AsyncHandler) with 'requestBuilder', 'handler'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ListenableFuture DefaultAsyncHttpClient.executeRequest(RequestBuilder, AsyncHandler)"
  })
  void testExecuteRequestWithRequestBuilderHandler() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    ListenableFuture<Object> actualExecuteRequestResult =
        defaultAsyncHttpClient.executeRequest(requestBuilder, null);

    // Assert
    assertTrue(actualExecuteRequestResult instanceof NettyResponseFuture);
    assertEquals("UTF-8", requestBuilder.charset.name());
    assertTrue(actualExecuteRequestResult.isDone());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#executeRequest(Request, AsyncHandler)} with {@code request},
   * {@code handler}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#executeRequest(Request, AsyncHandler)}
   */
  @Test
  @DisplayName("Test executeRequest(Request, AsyncHandler) with 'request', 'handler'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ListenableFuture DefaultAsyncHttpClient.executeRequest(Request, AsyncHandler)"
  })
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteRequestResult =
        defaultAsyncHttpClient.executeRequest(request, handler);

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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#getChannelPool()}
   */
  @Test
  @DisplayName("Test getChannelPool()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ChannelPool DefaultAsyncHttpClient.getChannelPool()"})
  void testGetChannelPool() {
    // Arrange and Act
    ChannelPool actualChannelPool = new DefaultAsyncHttpClient().getChannelPool();

    // Assert
    assertTrue(actualChannelPool instanceof DefaultChannelPool);
    assertTrue(actualChannelPool.getIdleChannelCountPerHost().isEmpty());
    assertTrue(actualChannelPool.isOpen());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#getEventLoopGroup()}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#getEventLoopGroup()}
   */
  @Test
  @DisplayName("Test getEventLoopGroup()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"EventLoopGroup DefaultAsyncHttpClient.getEventLoopGroup()"})
  void testGetEventLoopGroup() {
    // Arrange and Act
    EventLoopGroup actualEventLoopGroup = new DefaultAsyncHttpClient().getEventLoopGroup();

    // Assert
    assertTrue(actualEventLoopGroup.next() instanceof NioEventLoop);
    assertTrue(actualEventLoopGroup instanceof NioEventLoopGroup);
    assertTrue(actualEventLoopGroup.terminationFuture() instanceof DefaultPromise);
    assertFalse(actualEventLoopGroup.isShuttingDown());
    assertFalse(actualEventLoopGroup.isShutdown());
    assertFalse(actualEventLoopGroup.isTerminated());
    assertTrue(actualEventLoopGroup.iterator().hasNext());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#getClientStats()}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#getClientStats()}
   */
  @Test
  @DisplayName("Test getClientStats()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ClientStats DefaultAsyncHttpClient.getClientStats()"})
  void testGetClientStats() {
    // Arrange and Act
    ClientStats actualClientStats = new DefaultAsyncHttpClient().getClientStats();

    // Assert
    assertEquals(0L, actualClientStats.getTotalActiveConnectionCount());
    assertEquals(0L, actualClientStats.getTotalConnectionCount());
    assertEquals(0L, actualClientStats.getTotalIdleConnectionCount());
    assertTrue(actualClientStats.getStatsPerHost().isEmpty());
  }

  /**
   * Test {@link DefaultAsyncHttpClient#requestBuilder(String, String)} with {@code method}, {@code
   * url}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#requestBuilder(String, String)}
   */
  @Test
  @DisplayName("Test requestBuilder(String, String) with 'method', 'url'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.requestBuilder(String, String)"})
  void testRequestBuilderWithMethodUrl() {
    // Arrange and Act
    BoundRequestBuilder actualRequestBuilderResult =
        new DefaultAsyncHttpClient()
            .requestBuilder("https://example.org/example", "https://example.org/example");

    // Assert
    assertTrue(actualRequestBuilderResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilderResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualRequestBuilderResult.channelPoolPartitioning;
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
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#requestBuilder(Request)}
   */
  @Test
  @DisplayName("Test requestBuilder(Request) with 'prototype'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.requestBuilder(Request)"})
  void testRequestBuilderWithPrototype() throws UnsupportedEncodingException {
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest prototype =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualRequestBuilderResult =
        defaultAsyncHttpClient.requestBuilder(prototype);

    // Assert
    verify(headers).iterator();
    assertSame(actualRequestBuilderResult.address, prototype.getAddress());
    assertSame(actualRequestBuilderResult.bodyGenerator, prototype.getBodyGenerator());
    assertSame(actualRequestBuilderResult.byteBufData, prototype.getByteBufData());
    assertSame(actualRequestBuilderResult.byteBufferData, prototype.getByteBufferData());
    assertSame(actualRequestBuilderResult.byteData, prototype.getByteData());
    assertSame(
        actualRequestBuilderResult.channelPoolPartitioning, prototype.getChannelPoolPartitioning());
    assertSame(actualRequestBuilderResult.charset, prototype.getCharset());
    assertSame(actualRequestBuilderResult.compositeByteData, prototype.getCompositeByteData());
    assertSame(actualRequestBuilderResult.file, prototype.getFile());
    assertSame(actualRequestBuilderResult.localAddress, prototype.getLocalAddress());
    assertSame(actualRequestBuilderResult.nameResolver, prototype.getNameResolver());
    assertSame(actualRequestBuilderResult.proxyServer, prototype.getProxyServer());
    assertSame(actualRequestBuilderResult.readTimeout, prototype.getReadTimeout());
    assertSame(actualRequestBuilderResult.realm, prototype.getRealm());
    assertSame(actualRequestBuilderResult.requestTimeout, prototype.getRequestTimeout());
    assertSame(actualRequestBuilderResult.streamData, prototype.getStreamData());
    assertSame(actualRequestBuilderResult.uri, prototype.getUri());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRequestBuilderResult.byteData);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#requestBuilder(Request)} with {@code prototype}.
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#requestBuilder(Request)}
   */
  @Test
  @DisplayName("Test requestBuilder(Request) with 'prototype'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.requestBuilder(Request)"})
  void testRequestBuilderWithPrototype2() throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest prototype =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            new byte[] {},
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualRequestBuilderResult =
        defaultAsyncHttpClient.requestBuilder(prototype);

    // Assert
    verify(headers).iterator();
    assertSame(actualRequestBuilderResult.address, prototype.getAddress());
    assertSame(actualRequestBuilderResult.bodyGenerator, prototype.getBodyGenerator());
    assertSame(actualRequestBuilderResult.byteBufData, prototype.getByteBufData());
    assertSame(actualRequestBuilderResult.byteBufferData, prototype.getByteBufferData());
    assertSame(actualRequestBuilderResult.byteData, prototype.getByteData());
    assertSame(
        actualRequestBuilderResult.channelPoolPartitioning, prototype.getChannelPoolPartitioning());
    assertSame(actualRequestBuilderResult.charset, prototype.getCharset());
    assertSame(actualRequestBuilderResult.compositeByteData, prototype.getCompositeByteData());
    assertSame(actualRequestBuilderResult.file, prototype.getFile());
    assertSame(actualRequestBuilderResult.localAddress, prototype.getLocalAddress());
    assertSame(actualRequestBuilderResult.nameResolver, prototype.getNameResolver());
    assertSame(actualRequestBuilderResult.proxyServer, prototype.getProxyServer());
    assertSame(actualRequestBuilderResult.readTimeout, prototype.getReadTimeout());
    assertSame(actualRequestBuilderResult.realm, prototype.getRealm());
    assertSame(actualRequestBuilderResult.requestTimeout, prototype.getRequestTimeout());
    assertSame(actualRequestBuilderResult.streamData, prototype.getStreamData());
    assertSame(actualRequestBuilderResult.uri, prototype.getUri());
    assertArrayEquals(new byte[] {}, actualRequestBuilderResult.byteData);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#requestBuilder(Request)} with {@code prototype}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilderBase#headers} return {@link DefaultHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#requestBuilder(Request)}
   */
  @Test
  @DisplayName(
      "Test requestBuilder(Request) with 'prototype'; then headers return DefaultHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.requestBuilder(Request)"})
  void testRequestBuilderWithPrototype_thenHeadersReturnDefaultHttpHeaders()
      throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);

    HttpHeaders headers = mock(HttpHeaders.class);
    SimpleEntry<String, String> simpleEntry = new SimpleEntry<>("foo", "foo");

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    entryList.add(simpleEntry);
    when(headers.iterator()).thenReturn(entryList.iterator());
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest prototype =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualRequestBuilderResult =
        defaultAsyncHttpClient.requestBuilder(prototype);

    // Assert
    assertArrayEquals(
        "AXAXAXAX".getBytes("UTF-8"), actualRequestBuilderResult.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRequestBuilderResult.byteData);
    HttpHeaders httpHeaders = actualRequestBuilderResult.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertFalse(httpHeaders.isEmpty());
    assertEquals(1, httpHeaders.size());
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertFalse(unwrapResult.isEmpty());
    Iterator<Entry<CharSequence, CharSequence>> iteratorResult = unwrapResult.iterator();
    Entry<CharSequence, CharSequence> actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertEquals(simpleEntry, actualNextResult);
    assertEquals(1, unwrapResult.size());
    verify(headers).iterator();
  }

  /**
   * Test {@link DefaultAsyncHttpClient#requestBuilder(Request)} with {@code prototype}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#cookies} is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#requestBuilder(Request)}
   */
  @Test
  @DisplayName("Test requestBuilder(Request) with 'prototype'; then return cookies is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.requestBuilder(Request)"})
  void testRequestBuilderWithPrototype_thenReturnCookiesIsArrayList()
      throws UnsupportedEncodingException {
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest prototype =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualRequestBuilderResult =
        defaultAsyncHttpClient.requestBuilder(prototype);

    // Assert
    verify(headers).iterator();
    assertEquals(cookies, actualRequestBuilderResult.cookies);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualRequestBuilderResult.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRequestBuilderResult.byteData);
  }

  /**
   * Test {@link DefaultAsyncHttpClient#requestBuilder(Request)} with {@code prototype}.
   *
   * <ul>
   *   <li>Then return {@code Virtual Host}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultAsyncHttpClient#requestBuilder(Request)}
   */
  @Test
  @DisplayName("Test requestBuilder(Request) with 'prototype'; then return 'Virtual Host'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BoundRequestBuilder DefaultAsyncHttpClient.requestBuilder(Request)"})
  void testRequestBuilderWithPrototype_thenReturnVirtualHost() throws UnsupportedEncodingException {
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest prototype =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "Virtual Host",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    BoundRequestBuilder actualRequestBuilderResult =
        defaultAsyncHttpClient.requestBuilder(prototype);

    // Assert
    verify(headers).iterator();
    assertEquals("Virtual Host", actualRequestBuilderResult.virtualHost);
    assertSame(actualRequestBuilderResult.address, prototype.getAddress());
    assertSame(actualRequestBuilderResult.bodyGenerator, prototype.getBodyGenerator());
    assertSame(actualRequestBuilderResult.byteBufData, prototype.getByteBufData());
    assertSame(actualRequestBuilderResult.byteBufferData, prototype.getByteBufferData());
    assertSame(actualRequestBuilderResult.byteData, prototype.getByteData());
    assertSame(
        actualRequestBuilderResult.channelPoolPartitioning, prototype.getChannelPoolPartitioning());
    assertSame(actualRequestBuilderResult.charset, prototype.getCharset());
    assertSame(actualRequestBuilderResult.compositeByteData, prototype.getCompositeByteData());
    assertSame(actualRequestBuilderResult.file, prototype.getFile());
    assertSame(actualRequestBuilderResult.localAddress, prototype.getLocalAddress());
    assertSame(actualRequestBuilderResult.nameResolver, prototype.getNameResolver());
    assertSame(actualRequestBuilderResult.proxyServer, prototype.getProxyServer());
    assertSame(actualRequestBuilderResult.readTimeout, prototype.getReadTimeout());
    assertSame(actualRequestBuilderResult.realm, prototype.getRealm());
    assertSame(actualRequestBuilderResult.requestTimeout, prototype.getRequestTimeout());
    assertSame(actualRequestBuilderResult.streamData, prototype.getStreamData());
    assertSame(actualRequestBuilderResult.uri, prototype.getUri());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRequestBuilderResult.byteData);
  }
}
