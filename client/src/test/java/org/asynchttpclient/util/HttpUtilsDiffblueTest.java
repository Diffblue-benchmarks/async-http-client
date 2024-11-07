package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
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
import org.junit.jupiter.api.Test;

class HttpUtilsDiffblueTest {
  /**
   * Test {@link HttpUtils#hostHeader(Uri)}.
   * <p>
   * Method under test: {@link HttpUtils#hostHeader(Uri)}
   */
  @Test
  @DisplayName("Test hostHeader(Uri)")
  void testHostHeader() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        HttpUtils.hostHeader(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
                "https://example.org/example", "https://example.org/example", "https://example.org/example")));
    assertEquals("https://example.org/example",
        HttpUtils.hostHeader(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 80,
                "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link HttpUtils#hostHeader(Uri)}.
   * <ul>
   *   <li>Then return {@code https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpUtils#hostHeader(Uri)}
   */
  @Test
  @DisplayName("Test hostHeader(Uri); then return 'https://example.org/example:8080'")
  void testHostHeader_thenReturnHttpsExampleOrgExample8080() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example:8080",
        HttpUtils.hostHeader(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link HttpUtils#originHeader(Uri)}.
   * <ul>
   *   <li>Then return {@code http://https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpUtils#originHeader(Uri)}
   */
  @Test
  @DisplayName("Test originHeader(Uri); then return 'http://https://example.org/example'")
  void testOriginHeader_thenReturnHttpHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("http://https://example.org/example",
        HttpUtils.originHeader(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
                "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link HttpUtils#originHeader(Uri)}.
   * <ul>
   *   <li>Then return {@code http://https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpUtils#originHeader(Uri)}
   */
  @Test
  @DisplayName("Test originHeader(Uri); then return 'http://https://example.org/example:8080'")
  void testOriginHeader_thenReturnHttpHttpsExampleOrgExample8080() {
    // Arrange, Act and Assert
    assertEquals("http://https://example.org/example:8080",
        HttpUtils.originHeader(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link HttpUtils#originHeader(Uri)}.
   * <ul>
   *   <li>Then return {@code https://https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpUtils#originHeader(Uri)}
   */
  @Test
  @DisplayName("Test originHeader(Uri); then return 'https://https://example.org/example:8080'")
  void testOriginHeader_thenReturnHttpsHttpsExampleOrgExample8080() {
    // Arrange, Act and Assert
    assertEquals("https://https://example.org/example:8080",
        HttpUtils.originHeader(new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeCharsetAttribute(String)}.
   * <ul>
   *   <li>When {@code charset=}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link HttpUtils#extractContentTypeCharsetAttribute(String)}
   */
  @Test
  @DisplayName("Test extractContentTypeCharsetAttribute(String); when 'charset='; then return 'null'")
  void testExtractContentTypeCharsetAttribute_whenCharset_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeCharsetAttribute("charset="));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeCharsetAttribute(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link HttpUtils#extractContentTypeCharsetAttribute(String)}
   */
  @Test
  @DisplayName("Test extractContentTypeCharsetAttribute(String); when 'https://example.org/example'; then return 'null'")
  void testExtractContentTypeCharsetAttribute_whenHttpsExampleOrgExample_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeCharsetAttribute("https://example.org/example"));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeCharsetAttribute(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link HttpUtils#extractContentTypeCharsetAttribute(String)}
   */
  @Test
  @DisplayName("Test extractContentTypeCharsetAttribute(String); when 'null'; then return 'null'")
  void testExtractContentTypeCharsetAttribute_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeCharsetAttribute(null));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}
   */
  @Test
  @DisplayName("Test extractContentTypeBoundaryAttribute(String); then return 'https://example.org/example'")
  void testExtractContentTypeBoundaryAttribute_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        HttpUtils.extractContentTypeBoundaryAttribute("boundary=https://example.org/example"));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}.
   * <ul>
   *   <li>Then return {@code Not}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}
   */
  @Test
  @DisplayName("Test extractContentTypeBoundaryAttribute(String); then return 'Not'")
  void testExtractContentTypeBoundaryAttribute_thenReturnNot() {
    // Arrange, Act and Assert
    assertEquals("Not", HttpUtils.extractContentTypeBoundaryAttribute("boundary=Not all who wander are lost"));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}.
   * <ul>
   *   <li>When {@code boundary=}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}
   */
  @Test
  @DisplayName("Test extractContentTypeBoundaryAttribute(String); when 'boundary='; then return 'null'")
  void testExtractContentTypeBoundaryAttribute_whenBoundary_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeBoundaryAttribute("boundary="));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}
   */
  @Test
  @DisplayName("Test extractContentTypeBoundaryAttribute(String); when 'https://example.org/example'")
  void testExtractContentTypeBoundaryAttribute_whenHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeBoundaryAttribute("https://example.org/example"));
  }

  /**
   * Test {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}
   */
  @Test
  @DisplayName("Test extractContentTypeBoundaryAttribute(String); when 'null'; then return 'null'")
  void testExtractContentTypeBoundaryAttribute_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeBoundaryAttribute(null));
  }

  /**
   * Test {@link HttpUtils#patchContentTypeWithBoundaryAttribute(String, byte[])}.
   * <p>
   * Method under test:
   * {@link HttpUtils#patchContentTypeWithBoundaryAttribute(String, byte[])}
   */
  @Test
  @DisplayName("Test patchContentTypeWithBoundaryAttribute(String, byte[])")
  void testPatchContentTypeWithBoundaryAttribute() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example; boundary=AXAXAXAX",
        HttpUtils.patchContentTypeWithBoundaryAttribute("https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link HttpUtils#patchContentTypeWithBoundaryAttribute(String, byte[])}.
   * <ul>
   *   <li>Then return {@code boundary=AXAXAXAX}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link HttpUtils#patchContentTypeWithBoundaryAttribute(String, byte[])}
   */
  @Test
  @DisplayName("Test patchContentTypeWithBoundaryAttribute(String, byte[]); then return 'boundary=AXAXAXAX'")
  void testPatchContentTypeWithBoundaryAttribute_thenReturnBoundaryAxaxaxax() {
    // Arrange, Act and Assert
    assertEquals(" boundary=AXAXAXAX",
        HttpUtils.patchContentTypeWithBoundaryAttribute("", new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}));
  }

  /**
   * Test {@link HttpUtils#followRedirect(AsyncHttpClientConfig, Request)}.
   * <p>
   * Method under test:
   * {@link HttpUtils#followRedirect(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName("Test followRedirect(AsyncHttpClientConfig, Request)")
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

    // Act and Assert
    assertTrue(HttpUtils.followRedirect(config,
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class))));
  }

  /**
   * Test {@link HttpUtils#urlEncodeFormParams(List, Charset)}.
   * <ul>
   *   <li>Then return capacity is one hundred forty-three.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpUtils#urlEncodeFormParams(List, Charset)}
   */
  @Test
  @DisplayName("Test urlEncodeFormParams(List, Charset); then return capacity is one hundred forty-three")
  void testUrlEncodeFormParams_thenReturnCapacityIsOneHundredFortyThree() {
    // Arrange
    ArrayList<Param> params = new ArrayList<>();
    params.add(new Param("https://example.org/example", "https://example.org/example"));
    params.add(new Param("https://example.org/example", "https://example.org/example"));

    // Act
    ByteBuffer actualUrlEncodeFormParamsResult = HttpUtils.urlEncodeFormParams(params, Charset.forName("UTF-8"));

    // Assert
    assertEquals(143, actualUrlEncodeFormParamsResult.capacity());
    assertEquals(143, actualUrlEncodeFormParamsResult.limit());
    byte[] arrayResult = actualUrlEncodeFormParamsResult.array();
    assertEquals(143, arrayResult.length);
    assertEquals('%', arrayResult[119]);
    assertEquals('%', arrayResult[133]);
    assertEquals('.', arrayResult[129]);
    assertEquals('2', arrayResult[120]);
    assertEquals('2', arrayResult[134]);
    assertEquals('F', arrayResult[118]);
    assertEquals('F', arrayResult[121]);
    assertEquals('F', arrayResult[135]);
    assertEquals('a', arrayResult[124]);
    assertEquals('a', arrayResult[138]);
    assertEquals('e', arrayResult[122]);
    assertEquals('e', arrayResult[128]);
    assertEquals('e', arrayResult[136]);
    assertEquals('e', arrayResult[142]);
    assertEquals('g', arrayResult[132]);
    assertEquals('l', arrayResult[141]);
    assertEquals('l', arrayResult[Float.MAX_EXPONENT]);
    assertEquals('m', arrayResult[125]);
    assertEquals('m', arrayResult[139]);
    assertEquals('o', arrayResult[130]);
    assertEquals('p', arrayResult[126]);
    assertEquals('p', arrayResult[140]);
    assertEquals('r', arrayResult[131]);
    assertEquals('x', arrayResult[123]);
    assertEquals('x', arrayResult[137]);
  }

  /**
   * Test {@link HttpUtils#urlEncodeFormParams(List, Charset)}.
   * <ul>
   *   <li>Then return capacity is seventy-one.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpUtils#urlEncodeFormParams(List, Charset)}
   */
  @Test
  @DisplayName("Test urlEncodeFormParams(List, Charset); then return capacity is seventy-one")
  void testUrlEncodeFormParams_thenReturnCapacityIsSeventyOne() {
    // Arrange
    ArrayList<Param> params = new ArrayList<>();
    params.add(new Param("https://example.org/example", "https://example.org/example"));

    // Act
    ByteBuffer actualUrlEncodeFormParamsResult = HttpUtils.urlEncodeFormParams(params, Charset.forName("UTF-8"));

    // Assert
    assertEquals(71, actualUrlEncodeFormParamsResult.capacity());
    assertEquals(71, actualUrlEncodeFormParamsResult.limit());
    byte[] arrayResult = actualUrlEncodeFormParamsResult.array();
    assertEquals(71, arrayResult.length);
    assertEquals('%', arrayResult[47]);
    assertEquals('%', arrayResult[61]);
    assertEquals('.', arrayResult[57]);
    assertEquals('2', arrayResult[48]);
    assertEquals('2', arrayResult[62]);
    assertEquals('F', arrayResult[46]);
    assertEquals('F', arrayResult[49]);
    assertEquals('F', arrayResult[63]);
    assertEquals('a', arrayResult[52]);
    assertEquals('a', arrayResult[66]);
    assertEquals('e', arrayResult[50]);
    assertEquals('e', arrayResult[56]);
    assertEquals('e', arrayResult[70]);
    assertEquals('e', arrayResult[Double.SIZE]);
    assertEquals('g', arrayResult[60]);
    assertEquals('l', arrayResult[55]);
    assertEquals('l', arrayResult[69]);
    assertEquals('m', arrayResult[53]);
    assertEquals('m', arrayResult[67]);
    assertEquals('o', arrayResult[58]);
    assertEquals('p', arrayResult[54]);
    assertEquals('p', arrayResult[68]);
    assertEquals('r', arrayResult[59]);
    assertEquals('x', arrayResult[51]);
    assertEquals('x', arrayResult[65]);
  }

  /**
   * Test {@link HttpUtils#urlEncodeFormParams(List, Charset)}.
   * <ul>
   *   <li>Then return capacity is thirty-five.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpUtils#urlEncodeFormParams(List, Charset)}
   */
  @Test
  @DisplayName("Test urlEncodeFormParams(List, Charset); then return capacity is thirty-five")
  void testUrlEncodeFormParams_thenReturnCapacityIsThirtyFive() {
    // Arrange
    ArrayList<Param> params = new ArrayList<>();
    params.add(new Param("https://example.org/example", null));

    // Act
    ByteBuffer actualUrlEncodeFormParamsResult = HttpUtils.urlEncodeFormParams(params, Charset.forName("UTF-8"));

    // Assert
    assertEquals(35, actualUrlEncodeFormParamsResult.capacity());
    assertEquals(35, actualUrlEncodeFormParamsResult.limit());
    byte[] arrayResult = actualUrlEncodeFormParamsResult.array();
    assertEquals(35, arrayResult.length);
    assertEquals('%', arrayResult[25]);
    assertEquals('2', arrayResult[26]);
    assertEquals('F', arrayResult[27]);
    assertEquals('a', arrayResult[30]);
    assertEquals('e', arrayResult[28]);
    assertEquals('e', arrayResult[34]);
    assertEquals('l', arrayResult[33]);
    assertEquals('m', arrayResult[31]);
    assertEquals('p', arrayResult[Integer.SIZE]);
    assertEquals('x', arrayResult[29]);
  }

  /**
   * Test {@link HttpUtils#filterOutBrotliFromAcceptEncoding(String)}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link HttpUtils#filterOutBrotliFromAcceptEncoding(String)}
   */
  @Test
  @DisplayName("Test filterOutBrotliFromAcceptEncoding(String); then return 'https://example.org/example'")
  void testFilterOutBrotliFromAcceptEncoding_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        HttpUtils.filterOutBrotliFromAcceptEncoding("https://example.org/example"));
  }

  /**
   * Test {@link HttpUtils#filterOutBrotliFromAcceptEncoding(String)}.
   * <ul>
   *   <li>When {@code , br}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link HttpUtils#filterOutBrotliFromAcceptEncoding(String)}
   */
  @Test
  @DisplayName("Test filterOutBrotliFromAcceptEncoding(String); when ', br'; then return empty string")
  void testFilterOutBrotliFromAcceptEncoding_whenBr_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", HttpUtils.filterOutBrotliFromAcceptEncoding(", br"));
  }

  /**
   * Test {@link HttpUtils#filterOutZstdFromAcceptEncoding(String)}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpUtils#filterOutZstdFromAcceptEncoding(String)}
   */
  @Test
  @DisplayName("Test filterOutZstdFromAcceptEncoding(String); then return 'https://example.org/example'")
  void testFilterOutZstdFromAcceptEncoding_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        HttpUtils.filterOutZstdFromAcceptEncoding("https://example.org/example"));
  }

  /**
   * Test {@link HttpUtils#filterOutZstdFromAcceptEncoding(String)}.
   * <ul>
   *   <li>When {@code , zstd}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpUtils#filterOutZstdFromAcceptEncoding(String)}
   */
  @Test
  @DisplayName("Test filterOutZstdFromAcceptEncoding(String); when ', zstd'; then return empty string")
  void testFilterOutZstdFromAcceptEncoding_whenZstd_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", HttpUtils.filterOutZstdFromAcceptEncoding(", zstd"));
  }
}
