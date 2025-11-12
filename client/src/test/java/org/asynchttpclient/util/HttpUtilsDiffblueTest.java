package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.NameResolver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Request;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class HttpUtilsDiffblueTest {
  /**
   * Test {@link HttpUtils#hostHeader(Uri)}.
   *
   * <p>Method under test: {@link HttpUtils#hostHeader(Uri)}
   */
  @Test
  @DisplayName("Test hostHeader(Uri)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.hostHeader(Uri)"})
  void testHostHeader() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            -1,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertEquals("https://example.org/example", HttpUtils.hostHeader(uri));
  }

  /**
   * Test {@link HttpUtils#hostHeader(Uri)}.
   *
   * <p>Method under test: {@link HttpUtils#hostHeader(Uri)}
   */
  @Test
  @DisplayName("Test hostHeader(Uri)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.hostHeader(Uri)"})
  void testHostHeader2() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            80,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertEquals("https://example.org/example", HttpUtils.hostHeader(uri));
  }

  /**
   * Test {@link HttpUtils#hostHeader(Uri)}.
   *
   * <ul>
   *   <li>Then return {@code https://example.org/example:8080}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#hostHeader(Uri)}
   */
  @Test
  @DisplayName("Test hostHeader(Uri); then return 'https://example.org/example:8080'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.hostHeader(Uri)"})
  void testHostHeader_thenReturnHttpsExampleOrgExample8080() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertEquals("https://example.org/example:8080", HttpUtils.hostHeader(uri));
  }

  /**
   * Test {@link HttpUtils#originHeader(Uri)}.
   *
   * <ul>
   *   <li>Then return {@code http://https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#originHeader(Uri)}
   */
  @Test
  @DisplayName("Test originHeader(Uri); then return 'http://https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.originHeader(Uri)"})
  void testOriginHeader_thenReturnHttpHttpsExampleOrgExample() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            -1,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertEquals("http://https://example.org/example", HttpUtils.originHeader(uri));
  }

  /**
   * Test {@link HttpUtils#originHeader(Uri)}.
   *
   * <ul>
   *   <li>Then return {@code http://https://example.org/example:8080}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#originHeader(Uri)}
   */
  @Test
  @DisplayName("Test originHeader(Uri); then return 'http://https://example.org/example:8080'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.originHeader(Uri)"})
  void testOriginHeader_thenReturnHttpHttpsExampleOrgExample8080() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertEquals("http://https://example.org/example:8080", HttpUtils.originHeader(uri));
  }

  /**
   * Test {@link HttpUtils#originHeader(Uri)}.
   *
   * <ul>
   *   <li>Then return {@code https://https://example.org/example:8080}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#originHeader(Uri)}
   */
  @Test
  @DisplayName("Test originHeader(Uri); then return 'https://https://example.org/example:8080'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.originHeader(Uri)"})
  void testOriginHeader_thenReturnHttpsHttpsExampleOrgExample8080() {
    // Arrange
    Uri uri =
        new Uri(
            Uri.HTTPS,
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertEquals("https://https://example.org/example:8080", HttpUtils.originHeader(uri));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeCharsetAttribute(String)}.
   *
   * <ul>
   *   <li>When {@code charset=}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#extractContentTypeCharsetAttribute(String)}
   */
  @Test
  @DisplayName(
      "Test extractContentTypeCharsetAttribute(String); when 'charset='; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Charset HttpUtils.extractContentTypeCharsetAttribute(String)"})
  void testExtractContentTypeCharsetAttribute_whenCharset_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeCharsetAttribute("charset="));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeCharsetAttribute(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#extractContentTypeCharsetAttribute(String)}
   */
  @Test
  @DisplayName(
      "Test extractContentTypeCharsetAttribute(String); when 'https://example.org/example'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Charset HttpUtils.extractContentTypeCharsetAttribute(String)"})
  void testExtractContentTypeCharsetAttribute_whenHttpsExampleOrgExample_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeCharsetAttribute("https://example.org/example"));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeCharsetAttribute(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#extractContentTypeCharsetAttribute(String)}
   */
  @Test
  @DisplayName("Test extractContentTypeCharsetAttribute(String); when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Charset HttpUtils.extractContentTypeCharsetAttribute(String)"})
  void testExtractContentTypeCharsetAttribute_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeCharsetAttribute(null));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}.
   *
   * <ul>
   *   <li>Then return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}
   */
  @Test
  @DisplayName(
      "Test extractContentTypeBoundaryAttribute(String); then return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.extractContentTypeBoundaryAttribute(String)"})
  void testExtractContentTypeBoundaryAttribute_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example",
        HttpUtils.extractContentTypeBoundaryAttribute("boundary=https://example.org/example"));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}.
   *
   * <ul>
   *   <li>Then return {@code Not}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}
   */
  @Test
  @DisplayName("Test extractContentTypeBoundaryAttribute(String); then return 'Not'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.extractContentTypeBoundaryAttribute(String)"})
  void testExtractContentTypeBoundaryAttribute_thenReturnNot() {
    // Arrange, Act and Assert
    assertEquals(
        "Not",
        HttpUtils.extractContentTypeBoundaryAttribute("boundary=Not all who wander are lost"));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}.
   *
   * <ul>
   *   <li>When {@code boundary=}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}
   */
  @Test
  @DisplayName(
      "Test extractContentTypeBoundaryAttribute(String); when 'boundary='; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.extractContentTypeBoundaryAttribute(String)"})
  void testExtractContentTypeBoundaryAttribute_whenBoundary_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeBoundaryAttribute("boundary="));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}
   */
  @Test
  @DisplayName(
      "Test extractContentTypeBoundaryAttribute(String); when 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.extractContentTypeBoundaryAttribute(String)"})
  void testExtractContentTypeBoundaryAttribute_whenHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeBoundaryAttribute("https://example.org/example"));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}
   */
  @Test
  @DisplayName("Test extractContentTypeBoundaryAttribute(String); when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.extractContentTypeBoundaryAttribute(String)"})
  void testExtractContentTypeBoundaryAttribute_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeBoundaryAttribute(null));
  }

  /**
   * Test {@link HttpUtils#patchContentTypeWithBoundaryAttribute(String, byte[])}.
   *
   * <p>Method under test: {@link HttpUtils#patchContentTypeWithBoundaryAttribute(String, byte[])}
   */
  @Test
  @DisplayName("Test patchContentTypeWithBoundaryAttribute(String, byte[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.patchContentTypeWithBoundaryAttribute(String, byte[])"})
  void testPatchContentTypeWithBoundaryAttribute() throws UnsupportedEncodingException {
    // Arrange and Act
    String actualPatchContentTypeWithBoundaryAttributeResult =
        HttpUtils.patchContentTypeWithBoundaryAttribute(
            "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(
        "https://example.org/example; boundary=AXAXAXAX",
        actualPatchContentTypeWithBoundaryAttributeResult);
  }

  /**
   * Test {@link HttpUtils#patchContentTypeWithBoundaryAttribute(String, byte[])}.
   *
   * <ul>
   *   <li>Then return {@code boundary=AXAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#patchContentTypeWithBoundaryAttribute(String, byte[])}
   */
  @Test
  @DisplayName(
      "Test patchContentTypeWithBoundaryAttribute(String, byte[]); then return 'boundary=AXAXAXAX'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String HttpUtils.patchContentTypeWithBoundaryAttribute(String, byte[])"})
  void testPatchContentTypeWithBoundaryAttribute_thenReturnBoundaryAxaxaxax() {
    // Arrange and Act
    String actualPatchContentTypeWithBoundaryAttributeResult =
        HttpUtils.patchContentTypeWithBoundaryAttribute(
            "", new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals(" boundary=AXAXAXAX", actualPatchContentTypeWithBoundaryAttributeResult);
  }

  /**
   * Test {@link HttpUtils#followRedirect(AsyncHttpClientConfig, Request)}.
   *
   * <p>Method under test: {@link HttpUtils#followRedirect(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName("Test followRedirect(AsyncHttpClientConfig, Request)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean HttpUtils.followRedirect(AsyncHttpClientConfig, Request)"})
  void testFollowRedirect() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    Uri uri = mock(Uri.class);
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
    boolean actualFollowRedirectResult = HttpUtils.followRedirect(config, request);

    // Assert
    assertTrue(actualFollowRedirectResult);
  }

  /**
   * Test {@link HttpUtils#followRedirect(AsyncHttpClientConfig, Request)}.
   *
   * <ul>
   *   <li>Then calls {@link AsyncHttpClientConfig#isFollowRedirect()}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#followRedirect(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName("Test followRedirect(AsyncHttpClientConfig, Request); then calls isFollowRedirect()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean HttpUtils.followRedirect(AsyncHttpClientConfig, Request)"})
  void testFollowRedirect_thenCallsIsFollowRedirect() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isFollowRedirect()).thenReturn(true);
    Uri uri = mock(Uri.class);
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
            null,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    boolean actualFollowRedirectResult = HttpUtils.followRedirect(config, request);

    // Assert
    verify(config).isFollowRedirect();
    assertTrue(actualFollowRedirectResult);
  }

  /**
   * Test {@link HttpUtils#followRedirect(AsyncHttpClientConfig, Request)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#followRedirect(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName("Test followRedirect(AsyncHttpClientConfig, Request); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean HttpUtils.followRedirect(AsyncHttpClientConfig, Request)"})
  void testFollowRedirect_thenReturnFalse() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    Uri uri = mock(Uri.class);
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
            false,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    boolean actualFollowRedirectResult = HttpUtils.followRedirect(config, request);

    // Assert
    assertFalse(actualFollowRedirectResult);
  }

  /**
   * Test {@link HttpUtils#urlEncodeFormParams(List, Charset)}.
   *
   * <p>Method under test: {@link HttpUtils#urlEncodeFormParams(List, Charset)}
   */
  @Test
  @DisplayName("Test urlEncodeFormParams(List, Charset)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer HttpUtils.urlEncodeFormParams(List, Charset)"})
  void testUrlEncodeFormParams() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Param> params = new ArrayList<>();
    Param param = new Param("https://example.org/example", null);
    params.add(param);

    // Act
    ByteBuffer actualUrlEncodeFormParamsResult =
        HttpUtils.urlEncodeFormParams(params, Charset.forName("UTF-8"));

    // Assert
    byte[] expectedArrayResult = "https%3A%2F%2Fexample.org%2Fexample".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualUrlEncodeFormParamsResult.array());
    assertEquals(35, actualUrlEncodeFormParamsResult.capacity());
    ByteBuffer actualFlipResult = actualUrlEncodeFormParamsResult.flip();
    assertSame(actualUrlEncodeFormParamsResult, actualFlipResult);
    assertTrue(actualUrlEncodeFormParamsResult.hasArray());
    assertFalse(actualUrlEncodeFormParamsResult.hasRemaining());
    assertEquals(0, actualUrlEncodeFormParamsResult.limit());
    assertEquals(0, actualUrlEncodeFormParamsResult.position());
  }

  /**
   * Test {@link HttpUtils#urlEncodeFormParams(List, Charset)}.
   *
   * <ul>
   *   <li>Then return capacity is seventy-one.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#urlEncodeFormParams(List, Charset)}
   */
  @Test
  @DisplayName("Test urlEncodeFormParams(List, Charset); then return capacity is seventy-one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer HttpUtils.urlEncodeFormParams(List, Charset)"})
  void testUrlEncodeFormParams_thenReturnCapacityIsSeventyOne() {
    // Arrange
    ArrayList<Param> params = new ArrayList<>();
    params.add(new Param("https://example.org/example", "https://example.org/example"));

    // Act
    ByteBuffer actualUrlEncodeFormParamsResult =
        HttpUtils.urlEncodeFormParams(params, Charset.forName("UTF-8"));

    // Assert
    assertEquals(71, actualUrlEncodeFormParamsResult.capacity());
    assertEquals(71, actualUrlEncodeFormParamsResult.array().length);
  }

  /**
   * Test {@link HttpUtils#filterOutBrotliFromAcceptEncoding(String)}.
   *
   * <ul>
   *   <li>Then return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#filterOutBrotliFromAcceptEncoding(String)}
   */
  @Test
  @DisplayName(
      "Test filterOutBrotliFromAcceptEncoding(String); then return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.CharSequence HttpUtils.filterOutBrotliFromAcceptEncoding(String)"})
  void testFilterOutBrotliFromAcceptEncoding_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example",
        HttpUtils.filterOutBrotliFromAcceptEncoding("https://example.org/example"));
  }

  /**
   * Test {@link HttpUtils#filterOutBrotliFromAcceptEncoding(String)}.
   *
   * <ul>
   *   <li>When {@code , br}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#filterOutBrotliFromAcceptEncoding(String)}
   */
  @Test
  @DisplayName(
      "Test filterOutBrotliFromAcceptEncoding(String); when ', br'; then return empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.CharSequence HttpUtils.filterOutBrotliFromAcceptEncoding(String)"})
  void testFilterOutBrotliFromAcceptEncoding_whenBr_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", HttpUtils.filterOutBrotliFromAcceptEncoding(", br"));
  }

  /**
   * Test {@link HttpUtils#filterOutZstdFromAcceptEncoding(String)}.
   *
   * <ul>
   *   <li>Then return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#filterOutZstdFromAcceptEncoding(String)}
   */
  @Test
  @DisplayName(
      "Test filterOutZstdFromAcceptEncoding(String); then return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.CharSequence HttpUtils.filterOutZstdFromAcceptEncoding(String)"})
  void testFilterOutZstdFromAcceptEncoding_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example",
        HttpUtils.filterOutZstdFromAcceptEncoding("https://example.org/example"));
  }

  /**
   * Test {@link HttpUtils#filterOutZstdFromAcceptEncoding(String)}.
   *
   * <ul>
   *   <li>When {@code , zstd}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link HttpUtils#filterOutZstdFromAcceptEncoding(String)}
   */
  @Test
  @DisplayName(
      "Test filterOutZstdFromAcceptEncoding(String); when ', zstd'; then return empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.CharSequence HttpUtils.filterOutZstdFromAcceptEncoding(String)"})
  void testFilterOutZstdFromAcceptEncoding_whenZstd_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", HttpUtils.filterOutZstdFromAcceptEncoding(", zstd"));
  }
}
