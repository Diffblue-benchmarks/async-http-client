package org.asynchttpclient.netty.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.Cookie;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilderBase;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NettyRequestFactoryDiffblueTest {
  /**
   * Method under test:
   * {@link NettyRequestFactory#addAuthorizationHeader(HttpHeaders, String)}
   */
  @Test
  void testAddAuthorizationHeader() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.add(Mockito.<CharSequence>any(), Mockito.<Object>any())).thenReturn(new DefaultHttpHeaders());

    // Act
    nettyRequestFactory.addAuthorizationHeader(headers, "https://example.org/example");

    // Assert
    verify(headers).add(isA(CharSequence.class), isA(Object.class));
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#addAuthorizationHeader(HttpHeaders, String)}
   */
  @Test
  void testAddAuthorizationHeader2() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);

    // Act
    (new NettyRequestFactory(config)).addAuthorizationHeader(mock(EmptyHttpHeaders.class), null);

    // Assert that nothing has changed
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#setProxyAuthorizationHeader(HttpHeaders, String)}
   */
  @Test
  void testSetProxyAuthorizationHeader() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.set(Mockito.<CharSequence>any(), Mockito.<Object>any())).thenReturn(new DefaultHttpHeaders());

    // Act
    nettyRequestFactory.setProxyAuthorizationHeader(headers, "https://example.org/example");

    // Assert
    verify(headers).set(isA(CharSequence.class), isA(Object.class));
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#setProxyAuthorizationHeader(HttpHeaders, String)}
   */
  @Test
  void testSetProxyAuthorizationHeader2() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);

    // Act
    (new NettyRequestFactory(config)).setProxyAuthorizationHeader(mock(EmptyHttpHeaders.class), null);

    // Assert that nothing has changed
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  void testNewNettyRequest() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true,
        new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(), ProxyType.HTTP), null,
        null);

    // Assert
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    ByteBuf contentResult = ((DefaultFullHttpRequest) httpRequest).content();
    assertTrue(contentResult instanceof EmptyByteBuf);
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    HttpMethod method = httpRequest.getMethod();
    assertEquals("CONNECT", method.name());
    HttpVersion protocolVersion = httpRequest.getProtocolVersion();
    assertEquals("HTTP", protocolVersion.protocolName());
    assertEquals("HTTP/1.1", protocolVersion.text());
    assertEquals("https://example.org/example:8080", httpRequest.getUri());
    assertEquals("https://example.org/example:8080", httpRequest.uri());
    DecoderResult decoderResultResult = httpRequest.decoderResult();
    assertNull(decoderResultResult.cause());
    assertNull(actualNewNettyRequestResult.getBody());
    assertEquals(1, protocolVersion.majorVersion());
    assertEquals(1, protocolVersion.minorVersion());
    assertEquals(3, unwrapResult.size());
    assertEquals(3, headersResult.size());
    assertFalse(decoderResultResult.isFailure());
    assertFalse(unwrapResult.isEmpty());
    assertFalse(headersResult.isEmpty());
    assertTrue(decoderResultResult.isFinished());
    assertTrue(decoderResultResult.isSuccess());
    assertTrue(protocolVersion.isKeepAliveDefault());
    assertTrue(unwrapResult.iterator().hasNext());
    assertEquals(buffer, contentResult);
    assertSame(decoderResultResult, httpRequest.getDecoderResult());
    assertSame(method, httpRequest.method());
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  void testNewNettyRequest2() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(false);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true,
        new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(), ProxyType.HTTP), null,
        null);

    // Assert
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    ByteBuf contentResult = ((DefaultFullHttpRequest) httpRequest).content();
    assertTrue(contentResult instanceof EmptyByteBuf);
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    HttpMethod method = httpRequest.getMethod();
    assertEquals("CONNECT", method.name());
    HttpVersion protocolVersion = httpRequest.getProtocolVersion();
    assertEquals("HTTP", protocolVersion.protocolName());
    assertEquals("HTTP/1.1", protocolVersion.text());
    assertEquals("https://example.org/example:8080", httpRequest.getUri());
    assertEquals("https://example.org/example:8080", httpRequest.uri());
    DecoderResult decoderResultResult = httpRequest.decoderResult();
    assertNull(decoderResultResult.cause());
    assertNull(actualNewNettyRequestResult.getBody());
    assertEquals(1, protocolVersion.majorVersion());
    assertEquals(1, protocolVersion.minorVersion());
    assertEquals(4, unwrapResult.size());
    assertEquals(4, headersResult.size());
    assertFalse(decoderResultResult.isFailure());
    assertFalse(unwrapResult.isEmpty());
    assertFalse(headersResult.isEmpty());
    assertTrue(decoderResultResult.isFinished());
    assertTrue(decoderResultResult.isSuccess());
    assertTrue(protocolVersion.isKeepAliveDefault());
    assertTrue(unwrapResult.iterator().hasNext());
    assertEquals(buffer, contentResult);
    assertSame(decoderResultResult, httpRequest.getDecoderResult());
    assertSame(method, httpRequest.method());
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  void testNewNettyRequest3() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Uri uri = new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true,
        new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(), ProxyType.HTTP), null,
        null);

    // Assert
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    ByteBuf contentResult = ((DefaultFullHttpRequest) httpRequest).content();
    assertTrue(contentResult instanceof EmptyByteBuf);
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    HttpMethod method = httpRequest.getMethod();
    assertEquals("CONNECT", method.name());
    HttpVersion protocolVersion = httpRequest.getProtocolVersion();
    assertEquals("HTTP", protocolVersion.protocolName());
    assertEquals("HTTP/1.1", protocolVersion.text());
    assertEquals("https://example.org/example:8080", httpRequest.getUri());
    assertEquals("https://example.org/example:8080", httpRequest.uri());
    DecoderResult decoderResultResult = httpRequest.decoderResult();
    assertNull(decoderResultResult.cause());
    assertNull(actualNewNettyRequestResult.getBody());
    assertEquals(1, protocolVersion.majorVersion());
    assertEquals(1, protocolVersion.minorVersion());
    assertEquals(3, unwrapResult.size());
    assertEquals(3, headersResult.size());
    assertFalse(decoderResultResult.isFailure());
    assertFalse(unwrapResult.isEmpty());
    assertFalse(headersResult.isEmpty());
    assertTrue(decoderResultResult.isFinished());
    assertTrue(decoderResultResult.isSuccess());
    assertTrue(protocolVersion.isKeepAliveDefault());
    assertTrue(unwrapResult.iterator().hasNext());
    assertEquals(buffer, contentResult);
    assertSame(decoderResultResult, httpRequest.getDecoderResult());
    assertSame(method, httpRequest.method());
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  void testNewNettyRequest4() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true,
        new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(), ProxyType.HTTP), null,
        null);

    // Assert
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    ByteBuf contentResult = ((DefaultFullHttpRequest) httpRequest).content();
    assertTrue(contentResult instanceof EmptyByteBuf);
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    HttpMethod method = httpRequest.getMethod();
    assertEquals("CONNECT", method.name());
    HttpVersion protocolVersion = httpRequest.getProtocolVersion();
    assertEquals("HTTP", protocolVersion.protocolName());
    assertEquals("HTTP/1.1", protocolVersion.text());
    assertEquals("https://example.org/example:80", httpRequest.getUri());
    assertEquals("https://example.org/example:80", httpRequest.uri());
    DecoderResult decoderResultResult = httpRequest.decoderResult();
    assertNull(decoderResultResult.cause());
    assertNull(actualNewNettyRequestResult.getBody());
    assertEquals(1, protocolVersion.majorVersion());
    assertEquals(1, protocolVersion.minorVersion());
    assertEquals(3, unwrapResult.size());
    assertEquals(3, headersResult.size());
    assertFalse(decoderResultResult.isFailure());
    assertFalse(unwrapResult.isEmpty());
    assertFalse(headersResult.isEmpty());
    assertTrue(decoderResultResult.isFinished());
    assertTrue(decoderResultResult.isSuccess());
    assertTrue(protocolVersion.isKeepAliveDefault());
    assertTrue(unwrapResult.iterator().hasNext());
    assertEquals(buffer, contentResult);
    assertSame(decoderResultResult, httpRequest.getDecoderResult());
    assertSame(method, httpRequest.method());
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  void testNewNettyRequest5() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Uri uri = mock(Uri.class);
    when(uri.isSecured()).thenReturn(true);
    when(uri.getAuthority()).thenReturn("https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true,
        new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(), ProxyType.HTTP), null,
        null);

    // Assert
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(uri).getAuthority();
    verify(uri).isSecured();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    ByteBuf contentResult = ((DefaultFullHttpRequest) httpRequest).content();
    assertTrue(contentResult instanceof EmptyByteBuf);
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    HttpMethod method = httpRequest.getMethod();
    assertEquals("CONNECT", method.name());
    HttpVersion protocolVersion = httpRequest.getProtocolVersion();
    assertEquals("HTTP", protocolVersion.protocolName());
    assertEquals("HTTP/1.1", protocolVersion.text());
    assertEquals("https://example.org/example", httpRequest.getUri());
    assertEquals("https://example.org/example", httpRequest.uri());
    DecoderResult decoderResultResult = httpRequest.decoderResult();
    assertNull(decoderResultResult.cause());
    assertNull(actualNewNettyRequestResult.getBody());
    assertEquals(1, protocolVersion.majorVersion());
    assertEquals(1, protocolVersion.minorVersion());
    assertEquals(3, unwrapResult.size());
    assertEquals(3, headersResult.size());
    assertFalse(decoderResultResult.isFailure());
    assertFalse(unwrapResult.isEmpty());
    assertFalse(headersResult.isEmpty());
    assertTrue(decoderResultResult.isFinished());
    assertTrue(decoderResultResult.isSuccess());
    assertTrue(protocolVersion.isKeepAliveDefault());
    assertTrue(unwrapResult.iterator().hasNext());
    assertEquals(buffer, contentResult);
    assertSame(decoderResultResult, httpRequest.getDecoderResult());
    assertSame(method, httpRequest.method());
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  void testNewNettyRequest6() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Uri uri = mock(Uri.class);
    when(uri.isSecured()).thenReturn(true);
    when(uri.getAuthority()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(new ArrayList<>());
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true,
        new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(), ProxyType.HTTP), null,
        null);

    // Assert
    verify(headers, atLeast(1)).getAll(Mockito.<CharSequence>any());
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(uri).getAuthority();
    verify(uri).isSecured();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    ByteBuf contentResult = ((DefaultFullHttpRequest) httpRequest).content();
    assertTrue(contentResult instanceof EmptyByteBuf);
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    HttpMethod method = httpRequest.getMethod();
    assertEquals("CONNECT", method.name());
    HttpVersion protocolVersion = httpRequest.getProtocolVersion();
    assertEquals("HTTP", protocolVersion.protocolName());
    assertEquals("HTTP/1.1", protocolVersion.text());
    assertEquals("https://example.org/example", httpRequest.getUri());
    assertEquals("https://example.org/example", httpRequest.uri());
    DecoderResult decoderResultResult = httpRequest.decoderResult();
    assertNull(decoderResultResult.cause());
    assertNull(actualNewNettyRequestResult.getBody());
    assertEquals(1, protocolVersion.majorVersion());
    assertEquals(1, protocolVersion.minorVersion());
    assertEquals(3, unwrapResult.size());
    assertEquals(3, headersResult.size());
    assertFalse(decoderResultResult.isFailure());
    assertFalse(unwrapResult.isEmpty());
    assertFalse(headersResult.isEmpty());
    assertTrue(decoderResultResult.isFinished());
    assertTrue(decoderResultResult.isSuccess());
    assertTrue(protocolVersion.isKeepAliveDefault());
    assertTrue(unwrapResult.iterator().hasNext());
    assertEquals(buffer, contentResult);
    assertSame(decoderResultResult, httpRequest.getDecoderResult());
    assertSame(method, httpRequest.method());
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  void testNewNettyRequest7() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Uri uri = mock(Uri.class);
    when(uri.isSecured()).thenReturn(true);
    when(uri.getAuthority()).thenReturn("https://example.org/example");

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(stringList);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true,
        new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(), ProxyType.HTTP), null,
        null);

    // Assert
    verify(headers, atLeast(1)).getAll(Mockito.<CharSequence>any());
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(uri).getAuthority();
    verify(uri).isSecured();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    ByteBuf contentResult = ((DefaultFullHttpRequest) httpRequest).content();
    assertTrue(contentResult instanceof EmptyByteBuf);
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    HttpMethod method = httpRequest.getMethod();
    assertEquals("CONNECT", method.name());
    HttpVersion protocolVersion = httpRequest.getProtocolVersion();
    assertEquals("HTTP", protocolVersion.protocolName());
    assertEquals("HTTP/1.1", protocolVersion.text());
    assertEquals("https://example.org/example", httpRequest.getUri());
    assertEquals("https://example.org/example", httpRequest.uri());
    DecoderResult decoderResultResult = httpRequest.decoderResult();
    assertNull(decoderResultResult.cause());
    assertNull(actualNewNettyRequestResult.getBody());
    assertEquals(1, protocolVersion.majorVersion());
    assertEquals(1, protocolVersion.minorVersion());
    assertEquals(4, unwrapResult.size());
    assertEquals(4, headersResult.size());
    assertFalse(decoderResultResult.isFailure());
    assertFalse(unwrapResult.isEmpty());
    assertFalse(headersResult.isEmpty());
    assertTrue(decoderResultResult.isFinished());
    assertTrue(decoderResultResult.isSuccess());
    assertTrue(protocolVersion.isKeepAliveDefault());
    assertTrue(unwrapResult.iterator().hasNext());
    assertEquals(buffer, contentResult);
    assertSame(decoderResultResult, httpRequest.getDecoderResult());
    assertSame(method, httpRequest.method());
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  void testNewNettyRequest8() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getSchemeDefaultPort()).thenReturn(8080);
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.isSecured()).thenReturn(true);
    when(uri.getAuthority()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(new ArrayList<>());
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, null, proxyServer, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true,
        new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(), ProxyType.HTTP), null,
        null);

    // Assert
    verify(headers, atLeast(1)).getAll(Mockito.<CharSequence>any());
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(uri).getAuthority();
    verify(uri).getHost();
    verify(uri).getPort();
    verify(uri).getSchemeDefaultPort();
    verify(uri).isSecured();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    ByteBuf contentResult = ((DefaultFullHttpRequest) httpRequest).content();
    assertTrue(contentResult instanceof EmptyByteBuf);
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    HttpMethod method = httpRequest.getMethod();
    assertEquals("CONNECT", method.name());
    HttpVersion protocolVersion = httpRequest.getProtocolVersion();
    assertEquals("HTTP", protocolVersion.protocolName());
    assertEquals("HTTP/1.1", protocolVersion.text());
    assertEquals("https://example.org/example", httpRequest.getUri());
    assertEquals("https://example.org/example", httpRequest.uri());
    DecoderResult decoderResultResult = httpRequest.decoderResult();
    assertNull(decoderResultResult.cause());
    assertNull(actualNewNettyRequestResult.getBody());
    assertEquals(1, protocolVersion.majorVersion());
    assertEquals(1, protocolVersion.minorVersion());
    assertEquals(3, unwrapResult.size());
    assertEquals(3, headersResult.size());
    assertFalse(decoderResultResult.isFailure());
    assertFalse(unwrapResult.isEmpty());
    assertFalse(headersResult.isEmpty());
    assertTrue(decoderResultResult.isFinished());
    assertTrue(decoderResultResult.isSuccess());
    assertTrue(protocolVersion.isKeepAliveDefault());
    assertTrue(unwrapResult.iterator().hasNext());
    assertEquals(buffer, contentResult);
    assertSame(decoderResultResult, httpRequest.getDecoderResult());
    assertSame(method, httpRequest.method());
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  void testNewNettyRequest9() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(1);
    when(uri.getSchemeDefaultPort()).thenReturn(8080);
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.isSecured()).thenReturn(true);
    when(uri.getAuthority()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(new ArrayList<>());
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, null, proxyServer, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true,
        new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(), ProxyType.HTTP), null,
        null);

    // Assert
    verify(headers, atLeast(1)).getAll(Mockito.<CharSequence>any());
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(uri).getAuthority();
    verify(uri).getHost();
    verify(uri).getPort();
    verify(uri).getSchemeDefaultPort();
    verify(uri).isSecured();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    ByteBuf contentResult = ((DefaultFullHttpRequest) httpRequest).content();
    assertTrue(contentResult instanceof EmptyByteBuf);
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    HttpMethod method = httpRequest.getMethod();
    assertEquals("CONNECT", method.name());
    HttpVersion protocolVersion = httpRequest.getProtocolVersion();
    assertEquals("HTTP", protocolVersion.protocolName());
    assertEquals("HTTP/1.1", protocolVersion.text());
    assertEquals("https://example.org/example", httpRequest.getUri());
    assertEquals("https://example.org/example", httpRequest.uri());
    DecoderResult decoderResultResult = httpRequest.decoderResult();
    assertNull(decoderResultResult.cause());
    assertNull(actualNewNettyRequestResult.getBody());
    assertEquals(1, protocolVersion.majorVersion());
    assertEquals(1, protocolVersion.minorVersion());
    assertEquals(3, unwrapResult.size());
    assertEquals(3, headersResult.size());
    assertFalse(decoderResultResult.isFailure());
    assertFalse(unwrapResult.isEmpty());
    assertFalse(headersResult.isEmpty());
    assertTrue(decoderResultResult.isFinished());
    assertTrue(decoderResultResult.isSuccess());
    assertTrue(protocolVersion.isKeepAliveDefault());
    assertTrue(unwrapResult.iterator().hasNext());
    assertEquals(buffer, contentResult);
    assertSame(decoderResultResult, httpRequest.getDecoderResult());
    assertSame(method, httpRequest.method());
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  void testNewNettyRequest10() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(-1);
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.isSecured()).thenReturn(true);
    when(uri.getAuthority()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(new ArrayList<>());
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, null, proxyServer, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true,
        new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(), ProxyType.HTTP), null,
        null);

    // Assert
    verify(headers, atLeast(1)).getAll(Mockito.<CharSequence>any());
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(uri).getAuthority();
    verify(uri).getHost();
    verify(uri).getPort();
    verify(uri).isSecured();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    ByteBuf contentResult = ((DefaultFullHttpRequest) httpRequest).content();
    assertTrue(contentResult instanceof EmptyByteBuf);
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    HttpMethod method = httpRequest.getMethod();
    assertEquals("CONNECT", method.name());
    HttpVersion protocolVersion = httpRequest.getProtocolVersion();
    assertEquals("HTTP", protocolVersion.protocolName());
    assertEquals("HTTP/1.1", protocolVersion.text());
    assertEquals("https://example.org/example", httpRequest.getUri());
    assertEquals("https://example.org/example", httpRequest.uri());
    DecoderResult decoderResultResult = httpRequest.decoderResult();
    assertNull(decoderResultResult.cause());
    assertNull(actualNewNettyRequestResult.getBody());
    assertEquals(1, protocolVersion.majorVersion());
    assertEquals(1, protocolVersion.minorVersion());
    assertEquals(3, unwrapResult.size());
    assertEquals(3, headersResult.size());
    assertFalse(decoderResultResult.isFailure());
    assertFalse(unwrapResult.isEmpty());
    assertFalse(headersResult.isEmpty());
    assertTrue(decoderResultResult.isFinished());
    assertTrue(decoderResultResult.isSuccess());
    assertTrue(protocolVersion.isKeepAliveDefault());
    assertTrue(unwrapResult.iterator().hasNext());
    assertEquals(buffer, contentResult);
    assertSame(decoderResultResult, httpRequest.getDecoderResult());
    assertSame(method, httpRequest.method());
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#NettyRequestFactory(AsyncHttpClientConfig)}
   */
  @Test
  void testNewNettyRequestFactory() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);

    // Act
    new NettyRequestFactory(config);

    // Assert
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Method under test:
   * {@link NettyRequestFactory#NettyRequestFactory(AsyncHttpClientConfig)}
   */
  @Test
  void testNewNettyRequestFactory2() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(false);

    // Act
    new NettyRequestFactory(config);

    // Assert
    verify(config).isUseLaxCookieEncoder();
  }
}
