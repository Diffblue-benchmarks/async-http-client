package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
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
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.channel.ChannelPoolPartitioning.PerHostChannelPoolPartitioning;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RequestBuilderDiffblueTest {
  /**
   * Test {@link RequestBuilder#RequestBuilder()}.
   *
   * <p>Method under test: {@link RequestBuilder#RequestBuilder()}
   */
  @Test
  @DisplayName("Test new RequestBuilder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilder.<init>()"})
  void testNewRequestBuilder() {
    // Arrange and Act
    RequestBuilder actualRequestBuilder = new RequestBuilder();

    // Assert
    assertTrue(actualRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("GET", actualRequestBuilder.method);
    assertNull(actualRequestBuilder.byteData);
    assertNull(actualRequestBuilder.byteBufData);
    assertNull(actualRequestBuilder.file);
    assertNull(actualRequestBuilder.streamData);
    assertNull(actualRequestBuilder.followRedirect);
    assertNull(actualRequestBuilder.stringData);
    assertNull(actualRequestBuilder.virtualHost);
    assertNull(actualRequestBuilder.address);
    assertNull(actualRequestBuilder.localAddress);
    assertNull(actualRequestBuilder.byteBufferData);
    assertNull(actualRequestBuilder.charset);
    assertNull(actualRequestBuilder.readTimeout);
    assertNull(actualRequestBuilder.requestTimeout);
    assertNull(actualRequestBuilder.cookies);
    assertNull(actualRequestBuilder.compositeByteData);
    assertNull(actualRequestBuilder.formParams);
    assertNull(actualRequestBuilder.queryParams);
    assertNull(actualRequestBuilder.bodyParts);
    assertNull(actualRequestBuilder.realm);
    assertNull(actualRequestBuilder.signatureCalculator);
    assertNull(actualRequestBuilder.proxyServer);
    assertNull(actualRequestBuilder.bodyGenerator);
    assertNull(actualRequestBuilder.uri);
    assertEquals(0L, actualRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualRequestBuilder.uriEncoder);
  }

  /**
   * Test {@link RequestBuilder#RequestBuilder(String)}.
   *
   * <p>Method under test: {@link RequestBuilder#RequestBuilder(String)}
   */
  @Test
  @DisplayName("Test new RequestBuilder(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilder.<init>(String)"})
  void testNewRequestBuilder2() {
    // Arrange and Act
    RequestBuilder actualRequestBuilder = new RequestBuilder("https://example.org/example");

    // Assert
    assertTrue(actualRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualRequestBuilder.method);
    assertNull(actualRequestBuilder.byteData);
    assertNull(actualRequestBuilder.byteBufData);
    assertNull(actualRequestBuilder.file);
    assertNull(actualRequestBuilder.streamData);
    assertNull(actualRequestBuilder.followRedirect);
    assertNull(actualRequestBuilder.stringData);
    assertNull(actualRequestBuilder.virtualHost);
    assertNull(actualRequestBuilder.address);
    assertNull(actualRequestBuilder.localAddress);
    assertNull(actualRequestBuilder.byteBufferData);
    assertNull(actualRequestBuilder.charset);
    assertNull(actualRequestBuilder.readTimeout);
    assertNull(actualRequestBuilder.requestTimeout);
    assertNull(actualRequestBuilder.cookies);
    assertNull(actualRequestBuilder.compositeByteData);
    assertNull(actualRequestBuilder.formParams);
    assertNull(actualRequestBuilder.queryParams);
    assertNull(actualRequestBuilder.bodyParts);
    assertNull(actualRequestBuilder.realm);
    assertNull(actualRequestBuilder.signatureCalculator);
    assertNull(actualRequestBuilder.proxyServer);
    assertNull(actualRequestBuilder.bodyGenerator);
    assertNull(actualRequestBuilder.uri);
    assertEquals(0L, actualRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualRequestBuilder.uriEncoder);
  }

  /**
   * Test {@link RequestBuilder#RequestBuilder(Request)}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then return {@link RequestBuilderBase#bodyParts} is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilder#RequestBuilder(Request)}
   */
  @Test
  @DisplayName("Test new RequestBuilder(Request); given 'A'; then return bodyParts is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilder.<init>(Request)"})
  void testNewRequestBuilder_givenA_thenReturnBodyPartsIsArrayList()
      throws UnsupportedEncodingException {
    // Arrange
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
    RequestBuilder actualRequestBuilder = new RequestBuilder(prototype);

    // Assert
    verify(headers).iterator();
    assertEquals(bodyParts, actualRequestBuilder.bodyParts);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualRequestBuilder.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRequestBuilder.byteData);
  }

  /**
   * Test {@link RequestBuilder#RequestBuilder(Request)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} iterator.
   *   <li>Then return {@link RequestBuilderBase#cookies} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilder#RequestBuilder(Request)}
   */
  @Test
  @DisplayName(
      "Test new RequestBuilder(Request); given ArrayList() iterator; then return cookies is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilder.<init>(Request)"})
  void testNewRequestBuilder_givenArrayListIterator_thenReturnCookiesIsNull()
      throws UnsupportedEncodingException {
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

    // Act
    RequestBuilder actualRequestBuilder = new RequestBuilder(prototype);

    // Assert
    verify(headers).iterator();
    HttpHeaders httpHeaders = actualRequestBuilder.headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    HttpHeaders httpHeaders2 = prototype.toBuilder().headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertNull(actualRequestBuilder.cookies);
    assertNull(actualRequestBuilder.formParams);
    assertNull(actualRequestBuilder.bodyParts);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
    assertEquals(actualRequestBuilder.headers, httpHeaders2);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualRequestBuilder.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRequestBuilder.byteData);
  }

  /**
   * Test {@link RequestBuilder#RequestBuilder(Request)}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#cookies} is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilder#RequestBuilder(Request)}
   */
  @Test
  @DisplayName("Test new RequestBuilder(Request); then return cookies is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilder.<init>(Request)"})
  void testNewRequestBuilder_thenReturnCookiesIsArrayList() throws UnsupportedEncodingException {
    // Arrange
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
    RequestBuilder actualRequestBuilder = new RequestBuilder(prototype);

    // Assert
    verify(headers).iterator();
    assertEquals(cookies, actualRequestBuilder.cookies);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualRequestBuilder.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRequestBuilder.byteData);
  }

  /**
   * Test {@link RequestBuilder#RequestBuilder(Request)}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#formParams} is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilder#RequestBuilder(Request)}
   */
  @Test
  @DisplayName("Test new RequestBuilder(Request); then return formParams is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilder.<init>(Request)"})
  void testNewRequestBuilder_thenReturnFormParamsIsArrayList() throws UnsupportedEncodingException {
    // Arrange
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
    RequestBuilder actualRequestBuilder = new RequestBuilder(prototype);

    // Assert
    verify(headers).iterator();
    assertEquals(formParams, actualRequestBuilder.formParams);
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualRequestBuilder.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRequestBuilder.byteData);
  }

  /**
   * Test {@link RequestBuilder#RequestBuilder(Request)}.
   *
   * <ul>
   *   <li>Then return not {@link RequestBuilderBase#headers} Empty.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilder#RequestBuilder(Request)}
   */
  @Test
  @DisplayName("Test new RequestBuilder(Request); then return not headers Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilder.<init>(Request)"})
  void testNewRequestBuilder_thenReturnNotHeadersEmpty() throws UnsupportedEncodingException {
    // Arrange
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
    RequestBuilder actualRequestBuilder = new RequestBuilder(prototype);

    // Assert
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRequestBuilder.byteBufferData.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualRequestBuilder.byteData);
    HttpHeaders httpHeaders = actualRequestBuilder.headers;
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
   * Test {@link RequestBuilder#RequestBuilder(String, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@link RequestBuilderBase#uriEncoder} is {@code FIXING}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilder#RequestBuilder(String, boolean)}
   */
  @Test
  @DisplayName(
      "Test new RequestBuilder(String, boolean); when 'false'; then return uriEncoder is 'FIXING'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilder.<init>(String, boolean)"})
  void testNewRequestBuilder_whenFalse_thenReturnUriEncoderIsFixing() {
    // Arrange and Act
    RequestBuilder actualRequestBuilder = new RequestBuilder("https://example.org/example", false);

    // Assert
    assertTrue(actualRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualRequestBuilder.method);
    assertNull(actualRequestBuilder.byteData);
    assertNull(actualRequestBuilder.byteBufData);
    assertNull(actualRequestBuilder.file);
    assertNull(actualRequestBuilder.streamData);
    assertNull(actualRequestBuilder.followRedirect);
    assertNull(actualRequestBuilder.stringData);
    assertNull(actualRequestBuilder.virtualHost);
    assertNull(actualRequestBuilder.address);
    assertNull(actualRequestBuilder.localAddress);
    assertNull(actualRequestBuilder.byteBufferData);
    assertNull(actualRequestBuilder.charset);
    assertNull(actualRequestBuilder.readTimeout);
    assertNull(actualRequestBuilder.requestTimeout);
    assertNull(actualRequestBuilder.cookies);
    assertNull(actualRequestBuilder.compositeByteData);
    assertNull(actualRequestBuilder.formParams);
    assertNull(actualRequestBuilder.queryParams);
    assertNull(actualRequestBuilder.bodyParts);
    assertNull(actualRequestBuilder.realm);
    assertNull(actualRequestBuilder.signatureCalculator);
    assertNull(actualRequestBuilder.proxyServer);
    assertNull(actualRequestBuilder.bodyGenerator);
    assertNull(actualRequestBuilder.uri);
    assertEquals(0L, actualRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualRequestBuilder.uriEncoder);
  }

  /**
   * Test {@link RequestBuilder#RequestBuilder(String, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@link RequestBuilderBase#uriEncoder} is {@code FIXING}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilder#RequestBuilder(String, boolean, boolean)}
   */
  @Test
  @DisplayName(
      "Test new RequestBuilder(String, boolean, boolean); when 'false'; then return uriEncoder is 'FIXING'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilder.<init>(String, boolean, boolean)"})
  void testNewRequestBuilder_whenFalse_thenReturnUriEncoderIsFixing2() {
    // Arrange and Act
    RequestBuilder actualRequestBuilder =
        new RequestBuilder("https://example.org/example", false, false);

    // Assert
    assertTrue(actualRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualRequestBuilder.method);
    assertNull(actualRequestBuilder.byteData);
    assertNull(actualRequestBuilder.byteBufData);
    assertNull(actualRequestBuilder.file);
    assertNull(actualRequestBuilder.streamData);
    assertNull(actualRequestBuilder.followRedirect);
    assertNull(actualRequestBuilder.stringData);
    assertNull(actualRequestBuilder.virtualHost);
    assertNull(actualRequestBuilder.address);
    assertNull(actualRequestBuilder.localAddress);
    assertNull(actualRequestBuilder.byteBufferData);
    assertNull(actualRequestBuilder.charset);
    assertNull(actualRequestBuilder.readTimeout);
    assertNull(actualRequestBuilder.requestTimeout);
    assertNull(actualRequestBuilder.cookies);
    assertNull(actualRequestBuilder.compositeByteData);
    assertNull(actualRequestBuilder.formParams);
    assertNull(actualRequestBuilder.queryParams);
    assertNull(actualRequestBuilder.bodyParts);
    assertNull(actualRequestBuilder.realm);
    assertNull(actualRequestBuilder.signatureCalculator);
    assertNull(actualRequestBuilder.proxyServer);
    assertNull(actualRequestBuilder.bodyGenerator);
    assertNull(actualRequestBuilder.uri);
    assertEquals(0L, actualRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualRequestBuilder.uriEncoder);
  }

  /**
   * Test {@link RequestBuilder#RequestBuilder(String, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@link RequestBuilderBase#uriEncoder} is {@code RAW}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilder#RequestBuilder(String, boolean)}
   */
  @Test
  @DisplayName(
      "Test new RequestBuilder(String, boolean); when 'true'; then return uriEncoder is 'RAW'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilder.<init>(String, boolean)"})
  void testNewRequestBuilder_whenTrue_thenReturnUriEncoderIsRaw() {
    // Arrange and Act
    RequestBuilder actualRequestBuilder = new RequestBuilder("https://example.org/example", true);

    // Assert
    assertTrue(actualRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualRequestBuilder.method);
    assertNull(actualRequestBuilder.byteData);
    assertNull(actualRequestBuilder.byteBufData);
    assertNull(actualRequestBuilder.file);
    assertNull(actualRequestBuilder.streamData);
    assertNull(actualRequestBuilder.followRedirect);
    assertNull(actualRequestBuilder.stringData);
    assertNull(actualRequestBuilder.virtualHost);
    assertNull(actualRequestBuilder.address);
    assertNull(actualRequestBuilder.localAddress);
    assertNull(actualRequestBuilder.byteBufferData);
    assertNull(actualRequestBuilder.charset);
    assertNull(actualRequestBuilder.readTimeout);
    assertNull(actualRequestBuilder.requestTimeout);
    assertNull(actualRequestBuilder.cookies);
    assertNull(actualRequestBuilder.compositeByteData);
    assertNull(actualRequestBuilder.formParams);
    assertNull(actualRequestBuilder.queryParams);
    assertNull(actualRequestBuilder.bodyParts);
    assertNull(actualRequestBuilder.realm);
    assertNull(actualRequestBuilder.signatureCalculator);
    assertNull(actualRequestBuilder.proxyServer);
    assertNull(actualRequestBuilder.bodyGenerator);
    assertNull(actualRequestBuilder.uri);
    assertEquals(0L, actualRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.RAW, actualRequestBuilder.uriEncoder);
  }

  /**
   * Test {@link RequestBuilder#RequestBuilder(String, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@link RequestBuilderBase#uriEncoder} is {@code RAW}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilder#RequestBuilder(String, boolean, boolean)}
   */
  @Test
  @DisplayName(
      "Test new RequestBuilder(String, boolean, boolean); when 'true'; then return uriEncoder is 'RAW'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilder.<init>(String, boolean, boolean)"})
  void testNewRequestBuilder_whenTrue_thenReturnUriEncoderIsRaw2() {
    // Arrange and Act
    RequestBuilder actualRequestBuilder =
        new RequestBuilder("https://example.org/example", true, true);

    // Assert
    assertTrue(actualRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualRequestBuilder.method);
    assertNull(actualRequestBuilder.byteData);
    assertNull(actualRequestBuilder.byteBufData);
    assertNull(actualRequestBuilder.file);
    assertNull(actualRequestBuilder.streamData);
    assertNull(actualRequestBuilder.followRedirect);
    assertNull(actualRequestBuilder.stringData);
    assertNull(actualRequestBuilder.virtualHost);
    assertNull(actualRequestBuilder.address);
    assertNull(actualRequestBuilder.localAddress);
    assertNull(actualRequestBuilder.byteBufferData);
    assertNull(actualRequestBuilder.charset);
    assertNull(actualRequestBuilder.readTimeout);
    assertNull(actualRequestBuilder.requestTimeout);
    assertNull(actualRequestBuilder.cookies);
    assertNull(actualRequestBuilder.compositeByteData);
    assertNull(actualRequestBuilder.formParams);
    assertNull(actualRequestBuilder.queryParams);
    assertNull(actualRequestBuilder.bodyParts);
    assertNull(actualRequestBuilder.realm);
    assertNull(actualRequestBuilder.signatureCalculator);
    assertNull(actualRequestBuilder.proxyServer);
    assertNull(actualRequestBuilder.bodyGenerator);
    assertNull(actualRequestBuilder.uri);
    assertEquals(0L, actualRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.RAW, actualRequestBuilder.uriEncoder);
  }
}
