package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isA;
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
import java.util.List;
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
import org.mockito.Mockito;

class AuthenticatorUtilsDiffblueTest {
  /**
   * Test {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  @DisplayName("Test getHeaderWithPrefix(List, String); given '42'; when ArrayList() add '42'; then return 'null'")
  void testGetHeaderWithPrefix_given42_whenArrayListAdd42_thenReturnNull() {
    // Arrange
    ArrayList<String> authenticateHeaders = new ArrayList<>();
    authenticateHeaders.add("42");
    authenticateHeaders.add("foo");

    // Act and Assert
    assertNull(AuthenticatorUtils.getHeaderWithPrefix(authenticateHeaders, "https://example.org/example"));
  }

  /**
   * Test {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  @DisplayName("Test getHeaderWithPrefix(List, String); given 'foo'; when ArrayList() add 'foo'; then return 'null'")
  void testGetHeaderWithPrefix_givenFoo_whenArrayListAddFoo_thenReturnNull() {
    // Arrange
    ArrayList<String> authenticateHeaders = new ArrayList<>();
    authenticateHeaders.add("foo");

    // Act and Assert
    assertNull(AuthenticatorUtils.getHeaderWithPrefix(authenticateHeaders, "https://example.org/example"));
  }

  /**
   * Test {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  @DisplayName("Test getHeaderWithPrefix(List, String); then return 'https://example.org/example'")
  void testGetHeaderWithPrefix_thenReturnHttpsExampleOrgExample() {
    // Arrange
    ArrayList<String> authenticateHeaders = new ArrayList<>();
    authenticateHeaders.add("https://example.org/example");

    // Act and Assert
    assertEquals("https://example.org/example",
        AuthenticatorUtils.getHeaderWithPrefix(authenticateHeaders, "https://example.org/example"));
  }

  /**
   * Test {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  @DisplayName("Test getHeaderWithPrefix(List, String); when ArrayList(); then return 'null'")
  void testGetHeaderWithPrefix_whenArrayList_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(AuthenticatorUtils.getHeaderWithPrefix(new ArrayList<>(), "https://example.org/example"));
  }

  /**
   * Test {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  @DisplayName("Test getHeaderWithPrefix(List, String); when 'null'; then return 'null'")
  void testGetHeaderWithPrefix_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(AuthenticatorUtils.getHeaderWithPrefix(null, "https://example.org/example"));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean)")
  void testComputeRealmURI() {
    // Arrange, Act and Assert
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
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean); then return a string")
  void testComputeRealmURI_thenReturnAString() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example",
        AuthenticatorUtils.computeRealmURI(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            true, true));
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example?https://example.org/example",
        AuthenticatorUtils.computeRealmURI(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            true, false));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean); then return 'https://example.org/example'")
  void testComputeRealmURI_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        AuthenticatorUtils.computeRealmURI(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            false, true));
    assertEquals("https://example.org/example",
        AuthenticatorUtils.computeRealmURI(new Uri("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, "https://example.org/example", "", "https://example.org/example"),
            false, false));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   * <ul>
   *   <li>Then return
   * {@code https://example.org/example?https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean); then return 'https://example.org/example?https://example.org/example'")
  void testComputeRealmURI_thenReturnHttpsExampleOrgExampleHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example?https://example.org/example",
        AuthenticatorUtils.computeRealmURI(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            false, false));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   * <ul>
   *   <li>Then return {@code /}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean); then return '/'")
  void testComputeRealmURI_thenReturnSlash() {
    // Arrange, Act and Assert
    assertEquals("/",
        AuthenticatorUtils.computeRealmURI(new Uri("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, "", "https://example.org/example", "https://example.org/example"),
            false, true));
  }

  /**
   * Test
   * {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}.
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}
   */
  @Test
  @DisplayName("Test perConnectionProxyAuthorizationHeader(Request, Realm)")
  void testPerConnectionProxyAuthorizationHeader() throws UnsupportedEncodingException {
    // Arrange
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(new ArrayList<>());
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
    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.NTLM);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionProxyAuthorizationHeaderResult = AuthenticatorUtils
        .perConnectionProxyAuthorizationHeader(request, proxyRealm);

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    verify(proxyRealm).getScheme();
    verify(proxyRealm).isUsePreemptiveAuth();
    assertEquals("NTLM TlRMTVNTUAABAAAAAYIIogAAAAAoAAAAAAAAACgAAAAFASgKAAAADw==",
        actualPerConnectionProxyAuthorizationHeaderResult);
  }

  /**
   * Test
   * {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then calls {@link HttpHeaders#getAll(CharSequence)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}
   */
  @Test
  @DisplayName("Test perConnectionProxyAuthorizationHeader(Request, Realm); given ArrayList() add 'foo'; then calls getAll(CharSequence)")
  void testPerConnectionProxyAuthorizationHeader_givenArrayListAddFoo_thenCallsGetAll()
      throws UnsupportedEncodingException {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    stringList.add("NTLM");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(stringList);
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
    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.NTLM);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionProxyAuthorizationHeaderResult = AuthenticatorUtils
        .perConnectionProxyAuthorizationHeader(request, proxyRealm);

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    verify(proxyRealm).getScheme();
    verify(proxyRealm).isUsePreemptiveAuth();
    assertNull(actualPerConnectionProxyAuthorizationHeaderResult);
  }

  /**
   * Test
   * {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code NTLM}.</li>
   *   <li>Then calls {@link HttpHeaders#getAll(CharSequence)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}
   */
  @Test
  @DisplayName("Test perConnectionProxyAuthorizationHeader(Request, Realm); given ArrayList() add 'NTLM'; then calls getAll(CharSequence)")
  void testPerConnectionProxyAuthorizationHeader_givenArrayListAddNtlm_thenCallsGetAll()
      throws UnsupportedEncodingException {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("NTLM");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(stringList);
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
    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.NTLM);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionProxyAuthorizationHeaderResult = AuthenticatorUtils
        .perConnectionProxyAuthorizationHeader(request, proxyRealm);

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    verify(proxyRealm).getScheme();
    verify(proxyRealm).isUsePreemptiveAuth();
    assertNull(actualPerConnectionProxyAuthorizationHeaderResult);
  }

  /**
   * Test
   * {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}.
   * <ul>
   *   <li>Given {@code BASIC}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}
   */
  @Test
  @DisplayName("Test perConnectionProxyAuthorizationHeader(Request, Realm); given 'BASIC'")
  void testPerConnectionProxyAuthorizationHeader_givenBasic() throws UnsupportedEncodingException {
    // Arrange
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
    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

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
   * Test
   * {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}
   */
  @Test
  @DisplayName("Test perConnectionProxyAuthorizationHeader(Request, Realm); when 'null'; then return 'null'")
  void testPerConnectionProxyAuthorizationHeader_whenNull_thenReturnNull() throws UnsupportedEncodingException {
    // Arrange
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
    assertNull(AuthenticatorUtils.perConnectionProxyAuthorizationHeader(
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)),
        null));
  }

  /**
   * Test
   * {@link AuthenticatorUtils#perRequestProxyAuthorizationHeader(Request, Realm)}.
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#perRequestProxyAuthorizationHeader(Request, Realm)}
   */
  @Test
  @DisplayName("Test perRequestProxyAuthorizationHeader(Request, Realm)")
  void testPerRequestProxyAuthorizationHeader() throws UnsupportedEncodingException {
    // Arrange
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
    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getPassword()).thenReturn("https://example.org/example");
    when(proxyRealm.getPrincipal()).thenReturn("https://example.org/example");
    when(proxyRealm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerRequestProxyAuthorizationHeaderResult = AuthenticatorUtils
        .perRequestProxyAuthorizationHeader(request, proxyRealm);

    // Assert
    verify(proxyRealm).getCharset();
    verify(proxyRealm).getPassword();
    verify(proxyRealm).getPrincipal();
    verify(proxyRealm).getScheme();
    verify(proxyRealm).isUsePreemptiveAuth();
    assertEquals("Basic aHR0cHM6Ly9leGFtcGxlLm9yZy9leGFtcGxlOmh0dHBzOi8vZXhhbXBsZS5vcmcvZXhhbXBsZQ==",
        actualPerRequestProxyAuthorizationHeaderResult);
  }

  /**
   * Test
   * {@link AuthenticatorUtils#perRequestProxyAuthorizationHeader(Request, Realm)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#perRequestProxyAuthorizationHeader(Request, Realm)}
   */
  @Test
  @DisplayName("Test perRequestProxyAuthorizationHeader(Request, Realm); when 'null'; then return 'null'")
  void testPerRequestProxyAuthorizationHeader_whenNull_thenReturnNull() throws UnsupportedEncodingException {
    // Arrange
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
    assertNull(AuthenticatorUtils.perRequestProxyAuthorizationHeader(
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)),
        null));
  }

  /**
   * Test
   * {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request, ProxyServer, Realm)}.
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request, ProxyServer, Realm)}
   */
  @Test
  @DisplayName("Test perConnectionAuthorizationHeader(Request, ProxyServer, Realm)")
  void testPerConnectionAuthorizationHeader() {
    // Arrange
    Request request = mock(Request.class);
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.NTLM);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionAuthorizationHeaderResult = AuthenticatorUtils.perConnectionAuthorizationHeader(request,
        proxyServer, realm);

    // Assert
    verify(realm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    assertEquals("NTLM TlRMTVNTUAABAAAAAYIIogAAAAAoAAAAAAAAACgAAAAFASgKAAAADw==",
        actualPerConnectionAuthorizationHeaderResult);
  }

  /**
   * Test
   * {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request, ProxyServer, Realm)}.
   * <ul>
   *   <li>Given {@code BASIC}.</li>
   *   <li>When {@link Realm} {@link Realm#getScheme()} return {@code BASIC}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request, ProxyServer, Realm)}
   */
  @Test
  @DisplayName("Test perConnectionAuthorizationHeader(Request, ProxyServer, Realm); given 'BASIC'; when Realm getScheme() return 'BASIC'")
  void testPerConnectionAuthorizationHeader_givenBasic_whenRealmGetSchemeReturnBasic() {
    // Arrange
    Request request = mock(Request.class);
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionAuthorizationHeaderResult = AuthenticatorUtils.perConnectionAuthorizationHeader(request,
        proxyServer, realm);

    // Assert
    verify(realm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    assertNull(actualPerConnectionAuthorizationHeaderResult);
  }

  /**
   * Test
   * {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request, ProxyServer, Realm)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request, ProxyServer, Realm)}
   */
  @Test
  @DisplayName("Test perConnectionAuthorizationHeader(Request, ProxyServer, Realm); given 'false'")
  void testPerConnectionAuthorizationHeader_givenFalse() {
    // Arrange
    Request request = mock(Request.class);
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    when(realm.isUsePreemptiveAuth()).thenReturn(false);

    // Act
    String actualPerConnectionAuthorizationHeaderResult = AuthenticatorUtils.perConnectionAuthorizationHeader(request,
        proxyServer, realm);

    // Assert
    verify(realm).isUsePreemptiveAuth();
    assertNull(actualPerConnectionAuthorizationHeaderResult);
  }

  /**
   * Test
   * {@link AuthenticatorUtils#perRequestAuthorizationHeader(Request, Realm)}.
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#perRequestAuthorizationHeader(Request, Realm)}
   */
  @Test
  @DisplayName("Test perRequestAuthorizationHeader(Request, Realm)")
  void testPerRequestAuthorizationHeader() throws UnsupportedEncodingException {
    // Arrange
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
    DefaultRequest request = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerRequestAuthorizationHeaderResult = AuthenticatorUtils.perRequestAuthorizationHeader(request,
        realm2);

    // Assert
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    assertEquals("Basic aHR0cHM6Ly9leGFtcGxlLm9yZy9leGFtcGxlOmh0dHBzOi8vZXhhbXBsZS5vcmcvZXhhbXBsZQ==",
        actualPerRequestAuthorizationHeaderResult);
  }

  /**
   * Test
   * {@link AuthenticatorUtils#perRequestAuthorizationHeader(Request, Realm)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AuthenticatorUtils#perRequestAuthorizationHeader(Request, Realm)}
   */
  @Test
  @DisplayName("Test perRequestAuthorizationHeader(Request, Realm); when 'null'; then return 'null'")
  void testPerRequestAuthorizationHeader_whenNull_thenReturnNull() throws UnsupportedEncodingException {
    // Arrange
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
    assertNull(AuthenticatorUtils.perRequestAuthorizationHeader(
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)),
        null));
  }
}
