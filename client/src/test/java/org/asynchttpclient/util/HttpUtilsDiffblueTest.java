package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.handler.codec.http.DefaultHttpHeaders;
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

class HttpUtilsDiffblueTest {
  /**
   * Method under test: {@link HttpUtils#hostHeader(Uri)}
   */
  @Test
  void testHostHeader() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example:8080",
        HttpUtils.hostHeader(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example")));
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
   * Method under test: {@link HttpUtils#originHeader(Uri)}
   */
  @Test
  void testOriginHeader() {
    // Arrange, Act and Assert
    assertEquals("http://https://example.org/example:8080",
        HttpUtils.originHeader(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example")));
    assertEquals("https://https://example.org/example:8080",
        HttpUtils.originHeader(new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")));
    assertEquals("http://https://example.org/example",
        HttpUtils.originHeader(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
                "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Method under test:
   * {@link HttpUtils#extractContentTypeCharsetAttribute(String)}
   */
  @Test
  void testExtractContentTypeCharsetAttribute() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeCharsetAttribute("https://example.org/example"));
    assertNull(HttpUtils.extractContentTypeCharsetAttribute(null));
    assertNull(HttpUtils.extractContentTypeCharsetAttribute("charset="));
  }

  /**
   * Method under test:
   * {@link HttpUtils#extractContentTypeBoundaryAttribute(String)}
   */
  @Test
  void testExtractContentTypeBoundaryAttribute() {
    // Arrange, Act and Assert
    assertNull(HttpUtils.extractContentTypeBoundaryAttribute("https://example.org/example"));
    assertNull(HttpUtils.extractContentTypeBoundaryAttribute(null));
    assertNull(HttpUtils.extractContentTypeBoundaryAttribute("boundary="));
    assertEquals("https://example.org/example",
        HttpUtils.extractContentTypeBoundaryAttribute("boundary=https://example.org/example"));
    assertEquals("Not", HttpUtils.extractContentTypeBoundaryAttribute("boundary=Not all who wander are lost"));
  }

  /**
   * Method under test:
   * {@link HttpUtils#patchContentTypeWithBoundaryAttribute(String, byte[])}
   */
  @Test
  void testPatchContentTypeWithBoundaryAttribute() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example; boundary=AXAXAXAX",
        HttpUtils.patchContentTypeWithBoundaryAttribute("https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));
    assertEquals(" boundary=AXAXAXAX",
        HttpUtils.patchContentTypeWithBoundaryAttribute("", new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}));
  }

  /**
   * Method under test:
   * {@link HttpUtils#followRedirect(AsyncHttpClientConfig, Request)}
   */
  @Test
  void testFollowRedirect() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);

    // Act and Assert
    assertTrue(HttpUtils.followRedirect(config,
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER)));
  }

  /**
   * Method under test:
   * {@link HttpUtils#filterOutBrotliFromAcceptEncoding(String)}
   */
  @Test
  void testFilterOutBrotliFromAcceptEncoding() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        HttpUtils.filterOutBrotliFromAcceptEncoding("https://example.org/example"));
    assertEquals("", HttpUtils.filterOutBrotliFromAcceptEncoding(", br"));
  }

  /**
   * Method under test: {@link HttpUtils#filterOutZstdFromAcceptEncoding(String)}
   */
  @Test
  void testFilterOutZstdFromAcceptEncoding() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        HttpUtils.filterOutZstdFromAcceptEncoding("https://example.org/example"));
    assertEquals("", HttpUtils.filterOutZstdFromAcceptEncoding(", zstd"));
  }
}
