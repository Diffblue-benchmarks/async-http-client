package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import java.util.Properties;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Request;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyServerSelector;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProxyUtilsDiffblueTest {
  /**
   * Test {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link ProxyServer} {@link ProxyServer#isIgnoredForHost(String)}
   * return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName("Test getProxyServer(AsyncHttpClientConfig, Request); given 'false'; when ProxyServer isIgnoredForHost(String) return 'false'")
  void testGetProxyServer_givenFalse_whenProxyServerIsIgnoredForHostReturnFalse() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    Uri uri = mock(Uri.class);
    when(uri.getHost()).thenReturn("https://example.org/example");
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(false);
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
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    ProxyUtils.getProxyServer(config,
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(proxyServer).isIgnoredForHost(eq("https://example.org/example"));
    verify(uri).getHost();
  }

  /**
   * Test {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName("Test getProxyServer(AsyncHttpClientConfig, Request); given 'true'; then return 'null'")
  void testGetProxyServer_givenTrue_thenReturnNull() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    Uri uri = mock(Uri.class);
    when(uri.getHost()).thenReturn("https://example.org/example");
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.isIgnoredForHost(Mockito.<String>any())).thenReturn(true);
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
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    ProxyServer actualProxyServer = ProxyUtils.getProxyServer(config,
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(proxyServer).isIgnoredForHost(eq("https://example.org/example"));
    verify(uri).getHost();
    assertNull(actualProxyServer);
  }

  /**
   * Test {@link ProxyUtils#createProxyServerSelector(Properties)} with
   * {@code properties}.
   * <p>
   * Method under test: {@link ProxyUtils#createProxyServerSelector(Properties)}
   */
  @Test
  @DisplayName("Test createProxyServerSelector(Properties) with 'properties'")
  void testCreateProxyServerSelectorWithProperties() {
    // Arrange and Act
    ProxyServerSelector actualCreateProxyServerSelectorResult = ProxyUtils.createProxyServerSelector(new Properties());

    // Assert
    assertNull(actualCreateProxyServerSelectorResult
        .select(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link ProxyUtils#getJdkDefaultProxyServerSelector()}.
   * <p>
   * Method under test: {@link ProxyUtils#getJdkDefaultProxyServerSelector()}
   */
  @Test
  @DisplayName("Test getJdkDefaultProxyServerSelector()")
  void testGetJdkDefaultProxyServerSelector() {
    // Arrange and Act
    ProxyServerSelector actualJdkDefaultProxyServerSelector = ProxyUtils.getJdkDefaultProxyServerSelector();

    // Assert
    assertNull(actualJdkDefaultProxyServerSelector
        .select(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link ProxyUtils#getJdkDefaultProxyServerSelector()}.
   * <p>
   * Method under test: {@link ProxyUtils#getJdkDefaultProxyServerSelector()}
   */
  @Test
  @DisplayName("Test getJdkDefaultProxyServerSelector()")
  void testGetJdkDefaultProxyServerSelector2() {
    // Arrange and Act
    ProxyServerSelector actualJdkDefaultProxyServerSelector = ProxyUtils.getJdkDefaultProxyServerSelector();

    // Assert
    assertNull(actualJdkDefaultProxyServerSelector
        .select(new Uri("://", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }
}
