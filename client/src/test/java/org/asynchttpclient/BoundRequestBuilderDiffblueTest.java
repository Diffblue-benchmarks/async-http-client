package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.DefaultEventLoop;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.DefaultNameResolver;
import io.netty.resolver.NameResolver;
import io.netty.util.concurrent.DefaultProgressivePromise;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import org.asynchttpclient.ListenableFuture.CompletedFailure;
import org.asynchttpclient.Realm.AuthScheme;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.channel.ChannelPoolPartitioning.PerHostChannelPoolPartitioning;
import org.asynchttpclient.netty.NettyResponseFuture;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyServer.Builder;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BoundRequestBuilderDiffblueTest {
  /**
   * Test {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, Request)}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then return {@link RequestBuilderBase#bodyParts} is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, Request)}
   */
  @Test
  @DisplayName(
      "Test new BoundRequestBuilder(AsyncHttpClient, Request); given 'A'; then return bodyParts is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BoundRequestBuilder.<init>(AsyncHttpClient, Request)"})
  void testNewBoundRequestBuilder_givenA_thenReturnBodyPartsIsArrayList()
      throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient client = new DefaultAsyncHttpClient();

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ArrayList<Part> bodyParts = new ArrayList<>();
    ByteArrayPart byteArrayPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    bodyParts.add(byteArrayPart);
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
    BoundRequestBuilder actualBoundRequestBuilder = new BoundRequestBuilder(client, prototype);

    // Assert
    verify(headers).iterator();
    assertEquals(bodyParts, actualBoundRequestBuilder.bodyParts);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualBoundRequestBuilder.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBoundRequestBuilder.byteData);
  }

  /**
   * Test {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, Request)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} iterator.
   *   <li>Then return {@link RequestBuilderBase#cookies} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, Request)}
   */
  @Test
  @DisplayName(
      "Test new BoundRequestBuilder(AsyncHttpClient, Request); given ArrayList() iterator; then return cookies is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BoundRequestBuilder.<init>(AsyncHttpClient, Request)"})
  void testNewBoundRequestBuilder_givenArrayListIterator_thenReturnCookiesIsNull()
      throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient client = new DefaultAsyncHttpClient();

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
    BoundRequestBuilder actualBoundRequestBuilder = new BoundRequestBuilder(client, prototype);

    // Assert
    verify(headers).iterator();
    HttpHeaders httpHeaders = actualBoundRequestBuilder.headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    HttpHeaders httpHeaders2 = prototype.toBuilder().headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertNull(actualBoundRequestBuilder.cookies);
    assertNull(actualBoundRequestBuilder.formParams);
    assertNull(actualBoundRequestBuilder.bodyParts);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
    assertEquals(actualBoundRequestBuilder.headers, httpHeaders2);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualBoundRequestBuilder.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBoundRequestBuilder.byteData);
  }

  /**
   * Test {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, Request)}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#cookies} is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, Request)}
   */
  @Test
  @DisplayName(
      "Test new BoundRequestBuilder(AsyncHttpClient, Request); then return cookies is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BoundRequestBuilder.<init>(AsyncHttpClient, Request)"})
  void testNewBoundRequestBuilder_thenReturnCookiesIsArrayList()
      throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient client = new DefaultAsyncHttpClient();

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
    BoundRequestBuilder actualBoundRequestBuilder = new BoundRequestBuilder(client, prototype);

    // Assert
    verify(headers).iterator();
    assertEquals(cookies, actualBoundRequestBuilder.cookies);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualBoundRequestBuilder.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBoundRequestBuilder.byteData);
  }

  /**
   * Test {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, Request)}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#formParams} is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, Request)}
   */
  @Test
  @DisplayName(
      "Test new BoundRequestBuilder(AsyncHttpClient, Request); then return formParams is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BoundRequestBuilder.<init>(AsyncHttpClient, Request)"})
  void testNewBoundRequestBuilder_thenReturnFormParamsIsArrayList()
      throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient client = new DefaultAsyncHttpClient();

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ArrayList<Param> formParams = new ArrayList<>();
    formParams.add(new Param("https://example.org/example", "https://example.org/example"));
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
    BoundRequestBuilder actualBoundRequestBuilder = new BoundRequestBuilder(client, prototype);

    // Assert
    verify(headers).iterator();
    assertEquals(formParams, actualBoundRequestBuilder.formParams);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualBoundRequestBuilder.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBoundRequestBuilder.byteData);
  }

  /**
   * Test {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, Request)}.
   *
   * <ul>
   *   <li>Then return not {@link RequestBuilderBase#headers} Empty.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, Request)}
   */
  @Test
  @DisplayName(
      "Test new BoundRequestBuilder(AsyncHttpClient, Request); then return not headers Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BoundRequestBuilder.<init>(AsyncHttpClient, Request)"})
  void testNewBoundRequestBuilder_thenReturnNotHeadersEmpty() throws UnsupportedEncodingException {
    // Arrange
    DefaultAsyncHttpClient client = new DefaultAsyncHttpClient();
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);

    HttpHeaders headers = mock(HttpHeaders.class);
    SimpleEntry<String, String> simpleEntry = new SimpleEntry<>("Key", "42");

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
    BoundRequestBuilder actualBoundRequestBuilder = new BoundRequestBuilder(client, prototype);

    // Assert
    assertArrayEquals(
        "AXAXAXAX".getBytes("UTF-8"), actualBoundRequestBuilder.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBoundRequestBuilder.byteData);
    HttpHeaders httpHeaders = actualBoundRequestBuilder.headers;
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
    assertTrue(prototype.toBuilder().headers instanceof DefaultHttpHeaders);
  }

  /**
   * Test {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@link RequestBuilderBase#uriEncoder} is {@code FIXING}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String,
   * boolean)}
   */
  @Test
  @DisplayName(
      "Test new BoundRequestBuilder(AsyncHttpClient, String, boolean); when 'false'; then return uriEncoder is 'FIXING'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BoundRequestBuilder.<init>(AsyncHttpClient, String, boolean)"})
  void testNewBoundRequestBuilder_whenFalse_thenReturnUriEncoderIsFixing() {
    // Arrange and Act
    BoundRequestBuilder actualBoundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), "https://example.org/example", false);

    // Assert
    assertTrue(actualBoundRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualBoundRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualBoundRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
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
    assertEquals(0L, actualBoundRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualBoundRequestBuilder.uriEncoder);
  }

  /**
   * Test {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@link RequestBuilderBase#uriEncoder} is {@code FIXING}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String,
   * boolean, boolean)}
   */
  @Test
  @DisplayName(
      "Test new BoundRequestBuilder(AsyncHttpClient, String, boolean, boolean); when 'false'; then return uriEncoder is 'FIXING'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BoundRequestBuilder.<init>(AsyncHttpClient, String, boolean, boolean)"})
  void testNewBoundRequestBuilder_whenFalse_thenReturnUriEncoderIsFixing2() {
    // Arrange and Act
    BoundRequestBuilder actualBoundRequestBuilder =
        new BoundRequestBuilder(
            new DefaultAsyncHttpClient(), "https://example.org/example", false, false);

    // Assert
    assertTrue(actualBoundRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualBoundRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualBoundRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
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
    assertEquals(0L, actualBoundRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualBoundRequestBuilder.uriEncoder);
  }

  /**
   * Test {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@link RequestBuilderBase#uriEncoder} is {@code RAW}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String,
   * boolean)}
   */
  @Test
  @DisplayName(
      "Test new BoundRequestBuilder(AsyncHttpClient, String, boolean); when 'true'; then return uriEncoder is 'RAW'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BoundRequestBuilder.<init>(AsyncHttpClient, String, boolean)"})
  void testNewBoundRequestBuilder_whenTrue_thenReturnUriEncoderIsRaw() {
    // Arrange and Act
    BoundRequestBuilder actualBoundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), "https://example.org/example", true);

    // Assert
    assertTrue(actualBoundRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualBoundRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualBoundRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
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
    assertEquals(0L, actualBoundRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.RAW, actualBoundRequestBuilder.uriEncoder);
  }

  /**
   * Test {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@link RequestBuilderBase#uriEncoder} is {@code RAW}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String,
   * boolean, boolean)}
   */
  @Test
  @DisplayName(
      "Test new BoundRequestBuilder(AsyncHttpClient, String, boolean, boolean); when 'true'; then return uriEncoder is 'RAW'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BoundRequestBuilder.<init>(AsyncHttpClient, String, boolean, boolean)"})
  void testNewBoundRequestBuilder_whenTrue_thenReturnUriEncoderIsRaw2() {
    // Arrange and Act
    BoundRequestBuilder actualBoundRequestBuilder =
        new BoundRequestBuilder(
            new DefaultAsyncHttpClient(), "https://example.org/example", true, true);

    // Assert
    assertTrue(actualBoundRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualBoundRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning =
        actualBoundRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
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
    assertEquals(0L, actualBoundRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.RAW, actualBoundRequestBuilder.uriEncoder);
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute() throws UnsupportedEncodingException {
    // Arrange
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

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    Uri uri2 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    boundRequestBuilder.setUri(uri2);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> boundRequestBuilder.execute());
    verify(headers).iterator();
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
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
            channelPoolPartitioning,
            mock(NameResolver.class));
    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isNull());
    verify(proxyServer).isIgnoredForHost("https://example.org/example");
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
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute3() throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.addCookie(
        new DefaultCookie("https://example.org/example", "https://example.org/example"));

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(proxyServer).isIgnoredForHost("https://example.org/example");
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof CompletedFailure);
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute4() throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
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
            channelPoolPartitioning,
            mock(NameResolver.class));

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.addQueryParam("charset=", "42");

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isNull());
    verify(proxyServer).isIgnoredForHost("https://example.org/example");
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
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute5() throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
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
            channelPoolPartitioning,
            mock(NameResolver.class));

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.addFormParam("charset=", "42");

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isNull());
    verify(proxyServer).isIgnoredForHost("https://example.org/example");
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
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute6() throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setFormParams(new ArrayList<>());

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(proxyServer).isIgnoredForHost("https://example.org/example");
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof CompletedFailure);
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute7() throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(new Builder("https://example.org/example", 8080));

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
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
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute8() throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.addCookie(
        new DefaultCookie("https://example.org/example", "https://example.org/example"));
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(mock(ChannelPoolPartitioning.class));
    boundRequestBuilder.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof CompletedFailure);
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute9() throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.addFormParam("charset=", "42");
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(
            new DefaultAsyncHttpClient(), "setUrl hasn't been invoked. Using {}", true);

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(handler).onThrowable(isA(Throwable.class));
    assertTrue(actualExecuteResult instanceof CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler2() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

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

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    Uri uri2 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    boundRequestBuilder.setUri(uri2);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> boundRequestBuilder.execute(mock(AsyncHandler.class)));
    verify(headers).iterator();
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler3() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
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
            channelPoolPartitioning,
            mock(NameResolver.class));
    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onTcpConnectAttempt(Mockito.<InetSocketAddress>any());
    doNothing().when(handler).onConnectionPoolAttempt();

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
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isNull());
    verify(proxyServer).isIgnoredForHost("https://example.org/example");
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
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler4() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.addCookie(
        new DefaultCookie("https://example.org/example", "https://example.org/example"));

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(proxyServer).isIgnoredForHost("https://example.org/example");
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler5() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setFormParams(new ArrayList<>());

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(proxyServer).isIgnoredForHost("https://example.org/example");
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    assertTrue(actualExecuteResult instanceof CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler6() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.addCookie(
        new DefaultCookie("https://example.org/example", "https://example.org/example"));
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(mock(ChannelPoolPartitioning.class));
    boundRequestBuilder.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler7() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.addFormParam("https://example.org/example", "https://example.org/example");
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onHostnameResolutionAttempt(Mockito.<String>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onHostnameResolutionAttempt("https://example.org/example");
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then calls {@link NameResolver#resolveAll(String)}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName(
      "Test execute(AsyncHandler) with 'AsyncHandler'; given 'A'; then calls resolveAll(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler_givenA_thenCallsResolveAll()
      throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    ByteArrayPart bodyPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    boundRequestBuilder.addBodyPart(bodyPart);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onHostnameResolutionAttempt(Mockito.<String>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onHostnameResolutionAttempt("https://example.org/example");
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <ul>
   *   <li>Given {@link Function} {@link Function#apply(Object)} return {@link
   *       DefaultHttpHeaders#DefaultHttpHeaders()}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName(
      "Test execute(AsyncHandler) with 'AsyncHandler'; given Function apply(Object) return DefaultHttpHeaders()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler_givenFunctionApplyReturnDefaultHttpHeaders()
      throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    Function<Request, HttpHeaders> customHeaders = mock(Function.class);
    when(customHeaders.apply(Mockito.<Request>any())).thenReturn(new DefaultHttpHeaders());

    Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setCustomHeaders(customHeaders);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(proxyServerBuilder);

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onHostnameResolutionAttempt(Mockito.<String>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(customHeaders).apply(isA(Request.class));
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onHostnameResolutionAttempt("https://example.org/example");
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getScheme()} return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName(
      "Test execute(AsyncHandler) with 'AsyncHandler'; given Uri getScheme() return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler_givenUriGetSchemeReturnHttpsExampleOrgExample()
      throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> boundRequestBuilder.execute(mock(AsyncHandler.class)));
    verify(headers).iterator();
    verify(uri).getScheme();
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getScheme()} return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName(
      "Test execute(AsyncHandler) with 'AsyncHandler'; given Uri getScheme() return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler_givenUriGetSchemeReturnHttpsExampleOrgExample2()
      throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(mock(SignatureCalculator.class));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> boundRequestBuilder.execute(mock(AsyncHandler.class)));
    verify(headers).iterator();
    verify(uri).getScheme();
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <ul>
   *   <li>Then calls {@link EmptyHttpHeaders#iterator()}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'; then calls iterator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler_thenCallsIterator() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    EmptyHttpHeaders emptyHttpHeaders = mock(EmptyHttpHeaders.class);

    ArrayList<Entry<String, String>> entryList2 = new ArrayList<>();
    when(emptyHttpHeaders.iterator()).thenReturn(entryList2.iterator());

    Function<Request, HttpHeaders> customHeaders = mock(Function.class);
    when(customHeaders.apply(Mockito.<Request>any())).thenReturn(emptyHttpHeaders);

    Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setCustomHeaders(customHeaders);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(proxyServerBuilder);

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onHostnameResolutionAttempt(Mockito.<String>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(emptyHttpHeaders).iterator();
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(customHeaders).apply(isA(Request.class));
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onHostnameResolutionAttempt("https://example.org/example");
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
  }

  /**
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <ul>
   *   <li>Then calls {@link NameResolver#resolveAll(String)}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'; then calls resolveAll(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler_thenCallsResolveAll() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            channelPoolPartitioning,
            nameResolver);

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onHostnameResolutionAttempt(Mockito.<String>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onHostnameResolutionAttempt("https://example.org/example");
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
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
   * Test {@link BoundRequestBuilder#execute(AsyncHandler)} with {@code AsyncHandler}.
   *
   * <ul>
   *   <li>Then calls {@link NameResolver#resolveAll(String)}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  @DisplayName("Test execute(AsyncHandler) with 'AsyncHandler'; then calls resolveAll(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute(AsyncHandler)"})
  void testExecuteWithAsyncHandler_thenCallsResolveAll2() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onHostnameResolutionAttempt(Mockito.<String>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onHostnameResolutionAttempt("https://example.org/example");
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then calls {@link ProxyServer#isIgnoredForHost(String)}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given 'A'; then calls isIgnoredForHost(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_givenA_thenCallsIsIgnoredForHost() throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
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
            channelPoolPartitioning,
            mock(NameResolver.class));

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    ByteArrayPart bodyPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    boundRequestBuilder.addBodyPart(bodyPart);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isNull());
    verify(proxyServer).isIgnoredForHost("https://example.org/example");
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
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then calls {@link NameResolver#resolveAll(String)}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given 'A'; then calls resolveAll(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_givenA_thenCallsResolveAll() throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    ByteArrayPart bodyPart =
        new ByteArrayPart(
            "https://example.org/example", new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    boundRequestBuilder.addBodyPart(bodyPart);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Given {@link Function} {@link Function#apply(Object)} return {@link
   *       DefaultHttpHeaders#DefaultHttpHeaders()}.
   *   <li>Then calls {@link Function#apply(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName(
      "Test execute(); given Function apply(Object) return DefaultHttpHeaders(); then calls apply(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_givenFunctionApplyReturnDefaultHttpHeaders_thenCallsApply()
      throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    Function<Request, HttpHeaders> customHeaders = mock(Function.class);
    when(customHeaders.apply(Mockito.<Request>any())).thenReturn(new DefaultHttpHeaders());

    Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setCustomHeaders(customHeaders);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(proxyServerBuilder);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(customHeaders).apply(isA(Request.class));
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Given {@link Realm} {@link Realm#isOmitQuery()} return {@code true}.
   *   <li>Then calls {@link Realm#getAlgorithm()}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given Realm isOmitQuery() return 'true'; then calls getAlgorithm()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_givenRealmIsOmitQueryReturnTrue_thenCallsGetAlgorithm()
      throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.isOmitQuery()).thenReturn(true);
    when(realm.isUseAbsoluteURI()).thenReturn(true);
    when(realm.isUseCanonicalHostname()).thenReturn(true);
    when(realm.getAlgorithm()).thenReturn("https://example.org/example");
    when(realm.getLoginContextName()).thenReturn("https://example.org/example");
    when(realm.getNc()).thenReturn("https://example.org/example");
    when(realm.getNtlmDomain()).thenReturn("https://example.org/example");
    when(realm.getNtlmHost()).thenReturn("https://example.org/example");
    when(realm.getOpaque()).thenReturn("https://example.org/example");
    when(realm.getQop()).thenReturn("https://example.org/example");
    when(realm.getRealmName()).thenReturn("https://example.org/example");
    when(realm.getServicePrincipalName()).thenReturn("https://example.org/example");
    when(realm.getCustomLoginConfig()).thenReturn(new HashMap<>());
    Uri uri2 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    when(realm.getUri()).thenReturn(uri2);
    when(realm.getNonce()).thenReturn("https://example.org/example");
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.DIGEST);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
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

    Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setCustomHeaders(mock(Function.class));

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(mock(ChannelPoolPartitioning.class));
    boundRequestBuilder.setProxyServer(proxyServerBuilder);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(realm).getAlgorithm();
    verify(realm).getCharset();
    verify(realm).getCustomLoginConfig();
    verify(realm).getLoginContextName();
    verify(realm).getNc();
    verify(realm, atLeast(1)).getNonce();
    verify(realm).getNtlmDomain();
    verify(realm).getNtlmHost();
    verify(realm).getOpaque();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm).getQop();
    verify(realm).getRealmName();
    verify(realm, atLeast(1)).getScheme();
    verify(realm).getServicePrincipalName();
    verify(realm).getUri();
    verify(realm).isOmitQuery();
    verify(realm).isUseAbsoluteURI();
    verify(realm).isUseCanonicalHostname();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof CompletedFailure);
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getPort()} return minus one.
   *   <li>Then calls {@link EmptyHttpHeaders#iterator()}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given Uri getPort() return minus one; then calls iterator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_givenUriGetPortReturnMinusOne_thenCallsIterator()
      throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    EmptyHttpHeaders emptyHttpHeaders = mock(EmptyHttpHeaders.class);

    ArrayList<Entry<String, String>> entryList2 = new ArrayList<>();
    when(emptyHttpHeaders.iterator()).thenReturn(entryList2.iterator());

    Function<Request, HttpHeaders> customHeaders = mock(Function.class);
    when(customHeaders.apply(Mockito.<Request>any())).thenReturn(emptyHttpHeaders);

    Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setCustomHeaders(customHeaders);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(proxyServerBuilder);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(emptyHttpHeaders).iterator();
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(customHeaders).apply(isA(Request.class));
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getQuery()} return empty string.
   *   <li>Then calls {@link Realm#getAlgorithm()}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName(
      "Test execute(); given Uri getQuery() return empty string; then calls getAlgorithm()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_givenUriGetQueryReturnEmptyString_thenCallsGetAlgorithm()
      throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.isOmitQuery()).thenReturn(true);
    when(realm.isUseAbsoluteURI()).thenReturn(true);
    when(realm.isUseCanonicalHostname()).thenReturn(true);
    when(realm.getAlgorithm()).thenReturn("https://example.org/example");
    when(realm.getLoginContextName()).thenReturn("https://example.org/example");
    when(realm.getNc()).thenReturn("https://example.org/example");
    when(realm.getNtlmDomain()).thenReturn("https://example.org/example");
    when(realm.getNtlmHost()).thenReturn("https://example.org/example");
    when(realm.getOpaque()).thenReturn("https://example.org/example");
    when(realm.getQop()).thenReturn("https://example.org/example");
    when(realm.getRealmName()).thenReturn("https://example.org/example");
    when(realm.getServicePrincipalName()).thenReturn("https://example.org/example");
    when(realm.getCustomLoginConfig()).thenReturn(new HashMap<>());
    Uri uri2 =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    when(realm.getUri()).thenReturn(uri2);
    when(realm.getNonce()).thenReturn("https://example.org/example");
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.DIGEST);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
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

    Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setCustomHeaders(mock(Function.class));

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(mock(ChannelPoolPartitioning.class));
    boundRequestBuilder.setProxyServer(proxyServerBuilder);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(realm).getAlgorithm();
    verify(realm).getCharset();
    verify(realm).getCustomLoginConfig();
    verify(realm).getLoginContextName();
    verify(realm).getNc();
    verify(realm, atLeast(1)).getNonce();
    verify(realm).getNtlmDomain();
    verify(realm).getNtlmHost();
    verify(realm).getOpaque();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm).getQop();
    verify(realm).getRealmName();
    verify(realm, atLeast(1)).getScheme();
    verify(realm).getServicePrincipalName();
    verify(realm).getUri();
    verify(realm).isOmitQuery();
    verify(realm).isUseAbsoluteURI();
    verify(realm).isUseCanonicalHostname();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof CompletedFailure);
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getQuery()} return empty string.
   *   <li>Then calls {@link EmptyHttpHeaders#iterator()}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given Uri getQuery() return empty string; then calls iterator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_givenUriGetQueryReturnEmptyString_thenCallsIterator()
      throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    EmptyHttpHeaders emptyHttpHeaders = mock(EmptyHttpHeaders.class);

    ArrayList<Entry<String, String>> entryList2 = new ArrayList<>();
    when(emptyHttpHeaders.iterator()).thenReturn(entryList2.iterator());

    Function<Request, HttpHeaders> customHeaders = mock(Function.class);
    when(customHeaders.apply(Mockito.<Request>any())).thenReturn(emptyHttpHeaders);

    Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setCustomHeaders(customHeaders);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(proxyServerBuilder);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(emptyHttpHeaders).iterator();
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(customHeaders).apply(isA(Request.class));
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getScheme()} return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given Uri getScheme() return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_givenUriGetSchemeReturnHttpsExampleOrgExample()
      throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> boundRequestBuilder.execute());
    verify(headers).iterator();
    verify(uri).getScheme();
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getScheme()} return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given Uri getScheme() return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_givenUriGetSchemeReturnHttpsExampleOrgExample2()
      throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");

    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(mock(SignatureCalculator.class));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> boundRequestBuilder.execute());
    verify(headers).iterator();
    verify(uri).getScheme();
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getScheme()} return {@code https}.
   *   <li>Then calls {@link EmptyHttpHeaders#iterator()}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given Uri getScheme() return 'https'; then calls iterator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_givenUriGetSchemeReturnHttps_thenCallsIterator()
      throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    EmptyHttpHeaders emptyHttpHeaders = mock(EmptyHttpHeaders.class);

    ArrayList<Entry<String, String>> entryList2 = new ArrayList<>();
    when(emptyHttpHeaders.iterator()).thenReturn(entryList2.iterator());

    Function<Request, HttpHeaders> customHeaders = mock(Function.class);
    when(customHeaders.apply(Mockito.<Request>any())).thenReturn(emptyHttpHeaders);

    Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setCustomHeaders(customHeaders);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(proxyServerBuilder);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(emptyHttpHeaders).iterator();
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(customHeaders).apply(isA(Request.class));
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getScheme()} return {@code ws}.
   *   <li>Then return {@link CompletedFailure}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given Uri getScheme() return 'ws'; then return CompletedFailure")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_givenUriGetSchemeReturnWs_thenReturnCompletedFailure()
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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

    Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setCustomHeaders(mock(Function.class));

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(mock(ChannelPoolPartitioning.class));
    boundRequestBuilder.setProxyServer(proxyServerBuilder);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof CompletedFailure);
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getScheme()} return {@code wss}.
   *   <li>Then return {@link CompletedFailure}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); given Uri getScheme() return 'wss'; then return CompletedFailure")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_givenUriGetSchemeReturnWss_thenReturnCompletedFailure()
      throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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

    Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setCustomHeaders(mock(Function.class));

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(mock(ChannelPoolPartitioning.class));
    boundRequestBuilder.setProxyServer(proxyServerBuilder);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof CompletedFailure);
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Then calls {@link EmptyHttpHeaders#iterator()}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); then calls iterator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_thenCallsIterator() throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    EmptyHttpHeaders emptyHttpHeaders = mock(EmptyHttpHeaders.class);

    ArrayList<Entry<String, String>> entryList2 = new ArrayList<>();
    when(emptyHttpHeaders.iterator()).thenReturn(entryList2.iterator());

    Function<Request, HttpHeaders> customHeaders = mock(Function.class);
    when(customHeaders.apply(Mockito.<Request>any())).thenReturn(emptyHttpHeaders);

    Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setCustomHeaders(customHeaders);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(proxyServerBuilder);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(emptyHttpHeaders).iterator();
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(customHeaders).apply(isA(Request.class));
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
  }

  /**
   * Test {@link BoundRequestBuilder#execute()}.
   *
   * <ul>
   *   <li>Then calls {@link NameResolver#resolveAll(String)}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); then calls resolveAll(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_thenCallsResolveAll() throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            channelPoolPartitioning,
            nameResolver);

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
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
   *
   * <ul>
   *   <li>Then calls {@link NameResolver#resolveAll(String)}.
   * </ul>
   *
   * <p>Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  @DisplayName("Test execute(); then calls resolveAll(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ListenableFuture BoundRequestBuilder.execute()"})
  void testExecute_thenCallsResolveAll2() throws UnsupportedEncodingException {
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
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
            nameResolver);

    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(
            Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");

    SignatureCalculator signatureCalculator = mock(SignatureCalculator.class);
    doNothing()
        .when(signatureCalculator)
        .calculateAndAddSignature(Mockito.<Request>any(), Mockito.<RequestBuilderBase<?>>any());

    BoundRequestBuilder boundRequestBuilder =
        new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.setSignatureCalculator(signatureCalculator);
    boundRequestBuilder.setChannelPoolPartitioning(channelPoolPartitioning);
    boundRequestBuilder.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(headers).iterator();
    verify(nameResolver).resolveAll("https://example.org/example");
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm, atLeast(1)).getScheme();
    verify(realm, atLeast(1)).isUsePreemptiveAuth();
    verify(signatureCalculator)
        .calculateAndAddSignature(isA(Request.class), isA(RequestBuilderBase.class));
    verify(channelPoolPartitioning, atLeast(1))
        .getPartitionKey(isA(Uri.class), eq("https://example.org/example"), isA(ProxyServer.class));
    verify(uri, atLeast(1)).getFragment();
    verify(uri, atLeast(1)).getHost();
    verify(uri, atLeast(1)).getPath();
    verify(uri, atLeast(1)).getPort();
    verify(uri, atLeast(1)).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri, atLeast(1)).getUserInfo();
    assertTrue(actualExecuteResult instanceof NettyResponseFuture);
  }
}
