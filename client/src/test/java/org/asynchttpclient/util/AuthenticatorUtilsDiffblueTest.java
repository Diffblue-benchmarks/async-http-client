package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import ch.qos.logback.core.util.COWArrayList;
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

class AuthenticatorUtilsDiffblueTest {
  /**
   * Method under test:
   * {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  void testGetHeaderWithPrefix() {
    // Arrange, Act and Assert
    assertNull(AuthenticatorUtils.getHeaderWithPrefix(new ArrayList<>(), "https://example.org/example"));
    assertNull(AuthenticatorUtils.getHeaderWithPrefix(null, "https://example.org/example"));
  }

  /**
   * Method under test:
   * {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  void testGetHeaderWithPrefix2() {
    // Arrange
    ArrayList<String> authenticateHeaders = new ArrayList<>();
    authenticateHeaders.add("https://example.org/example");

    // Act and Assert
    assertEquals("https://example.org/example",
        AuthenticatorUtils.getHeaderWithPrefix(authenticateHeaders, "https://example.org/example"));
  }

  /**
   * Method under test:
   * {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  void testGetHeaderWithPrefix3() {
    // Arrange
    ArrayList<String> authenticateHeaders = new ArrayList<>();
    authenticateHeaders.add("foo");

    // Act and Assert
    assertNull(AuthenticatorUtils.getHeaderWithPrefix(authenticateHeaders, "https://example.org/example"));
  }

  /**
   * Method under test:
   * {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  void testGetHeaderWithPrefix4() {
    // Arrange
    ArrayList<String> authenticateHeaders = new ArrayList<>();
    authenticateHeaders.add("42");
    authenticateHeaders.add("foo");

    // Act and Assert
    assertNull(AuthenticatorUtils.getHeaderWithPrefix(authenticateHeaders, "https://example.org/example"));
  }

  /**
   * Method under test:
   * {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  void testGetHeaderWithPrefix5() {
    // Arrange
    COWArrayList<String> authenticateHeaders = mock(COWArrayList.class);

    ArrayList<String> stringList = new ArrayList<>();
    when(authenticateHeaders.iterator()).thenReturn(stringList.iterator());

    // Act
    String actualHeaderWithPrefix = AuthenticatorUtils.getHeaderWithPrefix(authenticateHeaders,
        "https://example.org/example");

    // Assert
    verify(authenticateHeaders).iterator();
    assertNull(actualHeaderWithPrefix);
  }

  /**
   * Method under test:
   * {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  void testComputeRealmURI() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example",
        AuthenticatorUtils.computeRealmURI(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            true, true));
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example",
        AuthenticatorUtils.computeRealmURI(
            new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            true, true));
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example",
        AuthenticatorUtils.computeRealmURI(
            new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            true, true));
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example",
        AuthenticatorUtils.computeRealmURI(
            new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            true, true));
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/examplehttps://example"
            + ".org/example",
        AuthenticatorUtils.computeRealmURI(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            true, true));
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example?",
        AuthenticatorUtils.computeRealmURI(new Uri("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, "https://example.org/example", "", "https://example.org/example"),
            true, true));
    assertEquals("https://example.org/example",
        AuthenticatorUtils.computeRealmURI(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            false, true));
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example?https://example.org/example",
        AuthenticatorUtils.computeRealmURI(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            true, false));
    assertEquals("/",
        AuthenticatorUtils.computeRealmURI(new Uri("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, "", "https://example.org/example", "https://example.org/example"),
            false, true));
    assertEquals("https://example.org/example?https://example.org/example",
        AuthenticatorUtils.computeRealmURI(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            false, false));
    assertEquals("https://example.org/example",
        AuthenticatorUtils.computeRealmURI(new Uri("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, "https://example.org/example", "", "https://example.org/example"),
            false, false));
  }

  /**
   * Method under test:
   * {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}
   */
  @Test
  void testPerConnectionProxyAuthorizationHeader() throws UnsupportedEncodingException {
    // Arrange
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
    assertNull(AuthenticatorUtils
        .perConnectionProxyAuthorizationHeader(new DefaultRequest("https://example.org/example", uri, address,
            localAddress, headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData,
            byteBufData, streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer,
            realm2, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER), null));
  }

  /**
   * Method under test:
   * {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}
   */
  @Test
  void testPerConnectionProxyAuthorizationHeader2() throws UnsupportedEncodingException {
    // Arrange
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
    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionProxyAuthorizationHeaderResult = AuthenticatorUtils
        .perConnectionProxyAuthorizationHeader(request, proxyRealm);

    // Assert
    verify(proxyRealm).getScheme();
    verify(proxyRealm).isUsePreemptiveAuth();
    assertNull(actualPerConnectionProxyAuthorizationHeaderResult);
  }

  /**
   * Method under test:
   * {@link AuthenticatorUtils#perRequestProxyAuthorizationHeader(Request, Realm)}
   */
  @Test
  void testPerRequestProxyAuthorizationHeader() throws UnsupportedEncodingException {
    // Arrange
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
    assertNull(AuthenticatorUtils.perRequestProxyAuthorizationHeader(new DefaultRequest("https://example.org/example",
        uri, address, localAddress, headers, cookies, byteData, compositeByteData, "https://example.org/example",
        byteBufferData, byteBufData, streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example",
        proxyServer, realm2, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L,
        null, mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER), null));
  }

  /**
   * Method under test:
   * {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request, ProxyServer, Realm)}
   */
  @Test
  void testPerConnectionAuthorizationHeader() throws UnsupportedEncodingException {
    // Arrange
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
    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    Realm realm3 = mock(Realm.class);
    ProxyServer proxyServer2 = new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm4 = mock(Realm.class);
    when(realm4.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm4.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionAuthorizationHeaderResult = AuthenticatorUtils.perConnectionAuthorizationHeader(request,
        proxyServer2, realm4);

    // Assert
    verify(realm4).getScheme();
    verify(realm4).isUsePreemptiveAuth();
    assertNull(actualPerConnectionAuthorizationHeaderResult);
  }

  /**
   * Method under test:
   * {@link AuthenticatorUtils#perRequestAuthorizationHeader(Request, Realm)}
   */
  @Test
  void testPerRequestAuthorizationHeader() throws UnsupportedEncodingException {
    // Arrange
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
    assertNull(AuthenticatorUtils.perRequestAuthorizationHeader(new DefaultRequest("https://example.org/example", uri,
        address, localAddress, headers, cookies, byteData, compositeByteData, "https://example.org/example",
        byteBufferData, byteBufData, streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example",
        proxyServer, realm2, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L,
        null, mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER), null));
  }
}
