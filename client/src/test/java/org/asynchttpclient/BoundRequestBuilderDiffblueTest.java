package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.DefaultNameResolver;
import io.netty.resolver.NameResolver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map;
import org.asynchttpclient.ListenableFuture.CompletedFailure;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.netty.NettyResponseFuture;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BoundRequestBuilderDiffblueTest {
  /**
   * Test
   * {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String, boolean, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return {@link RequestBuilderBase#uriEncoder} is {@code FIXING}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String, boolean, boolean)}
   */
  @Test
  @DisplayName("Test new BoundRequestBuilder(AsyncHttpClient, String, boolean, boolean); when 'false'; then return uriEncoder is 'FIXING'")
  void testNewBoundRequestBuilder_whenFalse_thenReturnUriEncoderIsFixing() {
    // Arrange and Act
    BoundRequestBuilder actualBoundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(),
        "https://example.org/example", false, false);

    // Assert
    HttpHeaders httpHeaders = actualBoundRequestBuilder.headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertTrue(actualBoundRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualBoundRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof ChannelPoolPartitioning.PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualBoundRequestBuilder.method);
    assertNull(actualBoundRequestBuilder.byteData);
    assertNull(actualBoundRequestBuilder.byteBufData);
    assertNull(actualBoundRequestBuilder.file);
    assertNull(actualBoundRequestBuilder.streamData);
    assertNull(actualBoundRequestBuilder.followRedirect);
    assertNull(actualBoundRequestBuilder.stringData);
    assertNull(actualBoundRequestBuilder.virtualHost);
    assertNull(actualBoundRequestBuilder.address);
    assertNull(actualBoundRequestBuilder.localAddress);
    assertNull(actualBoundRequestBuilder.byteBufferData);
    assertNull(actualBoundRequestBuilder.charset);
    assertNull(actualBoundRequestBuilder.readTimeout);
    assertNull(actualBoundRequestBuilder.requestTimeout);
    assertNull(actualBoundRequestBuilder.cookies);
    assertNull(actualBoundRequestBuilder.compositeByteData);
    assertNull(actualBoundRequestBuilder.formParams);
    assertNull(actualBoundRequestBuilder.queryParams);
    assertNull(actualBoundRequestBuilder.bodyParts);
    assertNull(actualBoundRequestBuilder.realm);
    assertNull(actualBoundRequestBuilder.signatureCalculator);
    assertNull(actualBoundRequestBuilder.proxyServer);
    assertNull(actualBoundRequestBuilder.bodyGenerator);
    assertNull(actualBoundRequestBuilder.uri);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertEquals(0L, actualBoundRequestBuilder.rangeOffset);
    assertEquals(ChannelPoolPartitioning.PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualBoundRequestBuilder.uriEncoder);
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Test
   * {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String, boolean, boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then return {@link RequestBuilderBase#uriEncoder} is {@code RAW}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String, boolean, boolean)}
   */
  @Test
  @DisplayName("Test new BoundRequestBuilder(AsyncHttpClient, String, boolean, boolean); when 'true'; then return uriEncoder is 'RAW'")
  void testNewBoundRequestBuilder_whenTrue_thenReturnUriEncoderIsRaw() {
    // Arrange and Act
    BoundRequestBuilder actualBoundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(),
        "https://example.org/example", true, true);

    // Assert
    HttpHeaders httpHeaders = actualBoundRequestBuilder.headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertTrue(actualBoundRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualBoundRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof ChannelPoolPartitioning.PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualBoundRequestBuilder.method);
    assertNull(actualBoundRequestBuilder.byteData);
    assertNull(actualBoundRequestBuilder.byteBufData);
    assertNull(actualBoundRequestBuilder.file);
    assertNull(actualBoundRequestBuilder.streamData);
    assertNull(actualBoundRequestBuilder.followRedirect);
    assertNull(actualBoundRequestBuilder.stringData);
    assertNull(actualBoundRequestBuilder.virtualHost);
    assertNull(actualBoundRequestBuilder.address);
    assertNull(actualBoundRequestBuilder.localAddress);
    assertNull(actualBoundRequestBuilder.byteBufferData);
    assertNull(actualBoundRequestBuilder.charset);
    assertNull(actualBoundRequestBuilder.readTimeout);
    assertNull(actualBoundRequestBuilder.requestTimeout);
    assertNull(actualBoundRequestBuilder.cookies);
    assertNull(actualBoundRequestBuilder.compositeByteData);
    assertNull(actualBoundRequestBuilder.formParams);
    assertNull(actualBoundRequestBuilder.queryParams);
    assertNull(actualBoundRequestBuilder.bodyParts);
    assertNull(actualBoundRequestBuilder.realm);
    assertNull(actualBoundRequestBuilder.signatureCalculator);
    assertNull(actualBoundRequestBuilder.proxyServer);
    assertNull(actualBoundRequestBuilder.bodyGenerator);
    assertNull(actualBoundRequestBuilder.uri);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertEquals(0L, actualBoundRequestBuilder.rangeOffset);
    assertEquals(ChannelPoolPartitioning.PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.RAW, actualBoundRequestBuilder.uriEncoder);
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute()")
  void testExecute() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);
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
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("", uri, address, localAddress, headers, cookies, byteData,
        compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
        formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
        Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    // Act
    ListenableFuture<Response> actualExecuteResult = (new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype))
        .execute();

    // Assert
    verify(headers).iterator();
    verify(proxyServer).isIgnoredForHost(eq("https://example.org/example"));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute()")
  void testExecute2() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ArrayList<Cookie> cookies = new ArrayList<>();
    cookies.add(new DefaultCookie("https://example.org/example", "https://example.org/example"));
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);
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
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    // Act
    ListenableFuture<Response> actualExecuteResult = (new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype))
        .execute();

    // Assert
    verify(headers).iterator();
    verify(proxyServer).isIgnoredForHost(eq("https://example.org/example"));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with
   * {@code AsyncHandler}.
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'")
  void testExecuteWithAsyncHandler() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("wss");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ArrayList<Cookie> cookies = new ArrayList<>();
    cookies.add(new DefaultCookie("https://example.org/example", "https://example.org/example"));
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with
   * {@code AsyncHandler}.
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'")
  void testExecuteWithAsyncHandler2() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("wss");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ArrayList<Param> formParams = new ArrayList<>();
    formParams.add(new Param("https://example.org/example", "https://example.org/example"));
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf byteBufData = mock(ByteBuf.class);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with
   * {@code AsyncHandler}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return {@link CompletedFailure}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'; given 'A'; then return CompletedFailure")
  void testExecuteWithAsyncHandler_givenA_thenReturnCompletedFailure() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("wss");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ArrayList<Part> bodyParts = new ArrayList<>();
    bodyParts.add(new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));
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
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with
   * {@code AsyncHandler}.
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getPath()} return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'; given Uri getPath() return empty string")
  void testExecuteWithAsyncHandler_givenUriGetPathReturnEmptyString() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("");
    when(uri.getScheme()).thenReturn("http");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
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
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), channelPoolPartitioning, mock(NameResolver.class));

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onTcpConnectAttempt(Mockito.<InetSocketAddress>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onTcpConnectAttempt(isA(InetSocketAddress.class));
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1)).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isNull());
    verify(proxyServer).isIgnoredForHost(eq("https://example.org/example"));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with
   * {@code AsyncHandler}.
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getPort()} return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'; given Uri getPort() return minus one")
  void testExecuteWithAsyncHandler_givenUriGetPortReturnMinusOne() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(-1);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
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
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), channelPoolPartitioning, mock(NameResolver.class));

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onTcpConnectAttempt(Mockito.<InetSocketAddress>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onTcpConnectAttempt(isA(InetSocketAddress.class));
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1)).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isNull());
    verify(proxyServer).isIgnoredForHost(eq("https://example.org/example"));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with
   * {@code AsyncHandler}.
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getQuery()} return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'; given Uri getQuery() return empty string")
  void testExecuteWithAsyncHandler_givenUriGetQueryReturnEmptyString() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
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
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), channelPoolPartitioning, mock(NameResolver.class));

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onTcpConnectAttempt(Mockito.<InetSocketAddress>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onTcpConnectAttempt(isA(InetSocketAddress.class));
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1)).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isNull());
    verify(proxyServer).isIgnoredForHost(eq("https://example.org/example"));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with
   * {@code AsyncHandler}.
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getScheme()} return {@code https}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'; given Uri getScheme() return 'https'")
  void testExecuteWithAsyncHandler_givenUriGetSchemeReturnHttps() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("https");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
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
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), channelPoolPartitioning, mock(NameResolver.class));

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onTcpConnectAttempt(Mockito.<InetSocketAddress>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onTcpConnectAttempt(isA(InetSocketAddress.class));
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1)).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isNull());
    verify(proxyServer).isIgnoredForHost(eq("https://example.org/example"));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with
   * {@code AsyncHandler}.
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getScheme()} return {@code ws}.</li>
   *   <li>Then return {@link CompletedFailure}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'; given Uri getScheme() return 'ws'; then return CompletedFailure")
  void testExecuteWithAsyncHandler_givenUriGetSchemeReturnWs_thenReturnCompletedFailure()
      throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("ws");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
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

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with
   * {@code AsyncHandler}.
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getScheme()} return {@code wss}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'; given Uri getScheme() return 'wss'")
  void testExecuteWithAsyncHandler_givenUriGetSchemeReturnWss() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("wss");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
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

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with
   * {@code AsyncHandler}.
   * <ul>
   *   <li>Then calls
   * {@link AsyncHandler#onTcpConnectAttempt(InetSocketAddress)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'; then calls onTcpConnectAttempt(InetSocketAddress)")
  void testExecuteWithAsyncHandler_thenCallsOnTcpConnectAttempt() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
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
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), channelPoolPartitioning, mock(NameResolver.class));

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onTcpConnectAttempt(Mockito.<InetSocketAddress>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onTcpConnectAttempt(isA(InetSocketAddress.class));
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1)).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isNull());
    verify(proxyServer).isIgnoredForHost(eq("https://example.org/example"));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with
   * {@code AsyncHandler}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'; then throw IllegalArgumentException")
  void testExecuteWithAsyncHandler_thenThrowIllegalArgumentException() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
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

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype))
            .<Object>execute(mock(AsyncHandler.class)));
    verify(headers).iterator();
    verify(uri).getScheme();
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add
   * {@link SimpleEntry#SimpleEntry(Object, Object)} with {@code charset=} and
   * {@code charset=}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given ArrayList() add SimpleEntry(Object, Object) with 'charset=' and 'charset='")
  void testExecute_givenArrayListAddSimpleEntryWithCharsetAndCharset() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    entryList.add(new AbstractMap.SimpleEntry<>("charset=", "charset="));
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.iterator()).thenReturn(entryList.iterator());
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);
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
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    // Act
    ListenableFuture<Response> actualExecuteResult = (new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype))
        .execute();

    // Assert
    verify(headers).iterator();
    verify(proxyServer).isIgnoredForHost(eq("https://example.org/example"));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getPath()} return empty string.</li>
   *   <li>Then return {@link NettyResponseFuture}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given Uri getPath() return empty string; then return NettyResponseFuture")
  void testExecute_givenUriGetPathReturnEmptyString_thenReturnNettyResponseFuture()
      throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("");
    when(uri.getScheme()).thenReturn("http");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
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
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), channelPoolPartitioning, mock(NameResolver.class));

    // Act
    ListenableFuture<Response> actualExecuteResult = (new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype))
        .execute();

    // Assert
    verify(headers).iterator();
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1)).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isNull());
    verify(proxyServer).isIgnoredForHost(eq("https://example.org/example"));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getScheme()} return {@code ws}.</li>
   *   <li>Then return {@link CompletedFailure}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given Uri getScheme() return 'ws'; then return CompletedFailure")
  void testExecute_givenUriGetSchemeReturnWs_thenReturnCompletedFailure() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("ws");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
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
    ListenableFuture<Response> actualExecuteResult = (new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype))
        .execute();

    // Assert
    verify(headers).iterator();
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getScheme()} return {@code wss}.</li>
   *   <li>Then return {@link CompletedFailure}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given Uri getScheme() return 'wss'; then return CompletedFailure")
  void testExecute_givenUriGetSchemeReturnWss_thenReturnCompletedFailure() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("wss");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
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
    ListenableFuture<Response> actualExecuteResult = (new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype))
        .execute();

    // Assert
    verify(headers).iterator();
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   * <ul>
   *   <li>Then return {@link NettyResponseFuture}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); then return NettyResponseFuture")
  void testExecute_thenReturnNettyResponseFuture() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
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
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), channelPoolPartitioning, mock(NameResolver.class));

    // Act
    ListenableFuture<Response> actualExecuteResult = (new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype))
        .execute();

    // Assert
    verify(headers).iterator();
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1)).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isNull());
    verify(proxyServer).isIgnoredForHost(eq("https://example.org/example"));
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); then throw IllegalArgumentException")
  void testExecute_thenThrowIllegalArgumentException() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
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

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype)).execute());
    verify(headers).iterator();
    verify(uri).getScheme();
  }
}
