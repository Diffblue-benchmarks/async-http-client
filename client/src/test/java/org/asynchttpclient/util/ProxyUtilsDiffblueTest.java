package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import java.util.List;
import java.util.Properties;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilderBase;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyServerSelector;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;

class ProxyUtilsDiffblueTest {
  /**
   * Method under test:
   * {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}
   */
  @Test
  void testGetProxyServer() throws UnsupportedEncodingException {
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
    ArrayList<String> nonProxyHosts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, nonProxyHosts,
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);

    // Act
    ProxyServer actualProxyServer = ProxyUtils.getProxyServer(config,
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER));

    // Assert
    assertEquals("https://example.org/example", actualProxyServer.getHost());
    assertNull(actualProxyServer.getCustomHeaders());
    assertEquals(8080, actualProxyServer.getPort());
    assertEquals(8080, actualProxyServer.getSecuredPort());
    assertEquals(ProxyType.HTTP, actualProxyServer.getProxyType());
    List<String> nonProxyHosts2 = actualProxyServer.getNonProxyHosts();
    assertTrue(nonProxyHosts2.isEmpty());
    assertSame(nonProxyHosts, nonProxyHosts2);
  }

  /**
   * Method under test: {@link ProxyUtils#createProxyServerSelector(Properties)}
   */
  @Test
  void testCreateProxyServerSelector() {
    // Arrange and Act
    ProxyServerSelector actualCreateProxyServerSelectorResult = ProxyUtils.createProxyServerSelector(new Properties());

    // Assert
    assertNull(actualCreateProxyServerSelectorResult
        .select(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Method under test: {@link ProxyUtils#getJdkDefaultProxyServerSelector()}
   */
  @Test
  void testGetJdkDefaultProxyServerSelector() {
    // Arrange and Act
    ProxyServerSelector actualJdkDefaultProxyServerSelector = ProxyUtils.getJdkDefaultProxyServerSelector();

    // Assert
    assertNull(actualJdkDefaultProxyServerSelector
        .select(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Method under test: {@link ProxyUtils#getJdkDefaultProxyServerSelector()}
   */
  @Test
  void testGetJdkDefaultProxyServerSelector2() {
    // Arrange and Act
    ProxyServerSelector actualJdkDefaultProxyServerSelector = ProxyUtils.getJdkDefaultProxyServerSelector();

    // Assert
    assertNull(actualJdkDefaultProxyServerSelector
        .select(new Uri("://", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }
}
