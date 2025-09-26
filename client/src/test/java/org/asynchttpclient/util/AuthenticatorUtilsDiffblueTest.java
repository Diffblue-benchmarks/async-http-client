package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
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
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Realm.AuthScheme;
import org.asynchttpclient.Request;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AuthenticatorUtilsDiffblueTest {
  /**
   * Test {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  @DisplayName(
      "Test getHeaderWithPrefix(List, String); given '42'; when ArrayList() add '42'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.getHeaderWithPrefix(List, String)"})
  void testGetHeaderWithPrefix_given42_whenArrayListAdd42_thenReturnNull() {
    // Arrange
    ArrayList<String> authenticateHeaders = new ArrayList<>();
    authenticateHeaders.add("42");
    authenticateHeaders.add("foo");

    // Act and Assert
    assertNull(
        AuthenticatorUtils.getHeaderWithPrefix(authenticateHeaders, "https://example.org/example"));
  }

  /**
   * Test {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  @DisplayName(
      "Test getHeaderWithPrefix(List, String); given 'foo'; when ArrayList() add 'foo'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.getHeaderWithPrefix(List, String)"})
  void testGetHeaderWithPrefix_givenFoo_whenArrayListAddFoo_thenReturnNull() {
    // Arrange
    ArrayList<String> authenticateHeaders = new ArrayList<>();
    authenticateHeaders.add("foo");

    // Act and Assert
    assertNull(
        AuthenticatorUtils.getHeaderWithPrefix(authenticateHeaders, "https://example.org/example"));
  }

  /**
   * Test {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}.
   *
   * <ul>
   *   <li>Then return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  @DisplayName("Test getHeaderWithPrefix(List, String); then return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.getHeaderWithPrefix(List, String)"})
  void testGetHeaderWithPrefix_thenReturnHttpsExampleOrgExample() {
    // Arrange
    ArrayList<String> authenticateHeaders = new ArrayList<>();
    authenticateHeaders.add("https://example.org/example");

    // Act and Assert
    assertEquals(
        "https://example.org/example",
        AuthenticatorUtils.getHeaderWithPrefix(authenticateHeaders, "https://example.org/example"));
  }

  /**
   * Test {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  @DisplayName("Test getHeaderWithPrefix(List, String); when ArrayList(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.getHeaderWithPrefix(List, String)"})
  void testGetHeaderWithPrefix_whenArrayList_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        AuthenticatorUtils.getHeaderWithPrefix(new ArrayList<>(), "https://example.org/example"));
  }

  /**
   * Test {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#getHeaderWithPrefix(List, String)}
   */
  @Test
  @DisplayName("Test getHeaderWithPrefix(List, String); when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.getHeaderWithPrefix(List, String)"})
  void testGetHeaderWithPrefix_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(AuthenticatorUtils.getHeaderWithPrefix(null, "https://example.org/example"));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   *
   * <p>Method under test: {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.computeRealmURI(Uri, boolean, boolean)"})
  void testComputeRealmURI() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "scheme",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertEquals(
        "https://example.org/example://scheme@https://example.org/example:8080https://example.org/example",
        AuthenticatorUtils.computeRealmURI(uri, true, true));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   *
   * <p>Method under test: {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.computeRealmURI(Uri, boolean, boolean)"})
  void testComputeRealmURI2() {
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
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/examplehttps://example"
            + ".org/example",
        AuthenticatorUtils.computeRealmURI(uri, true, true));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   *
   * <p>Method under test: {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.computeRealmURI(Uri, boolean, boolean)"})
  void testComputeRealmURI3() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "",
            "https://example.org/example");

    // Act and Assert
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example?",
        AuthenticatorUtils.computeRealmURI(uri, true, true));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   *
   * <p>Method under test: {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.computeRealmURI(Uri, boolean, boolean)"})
  void testComputeRealmURI4() {
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
    assertEquals(
        "https://https://example.org/example@https://example.org/example:8080https://example.org/example",
        AuthenticatorUtils.computeRealmURI(uri, true, true));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   *
   * <p>Method under test: {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.computeRealmURI(Uri, boolean, boolean)"})
  void testComputeRealmURI5() {
    // Arrange
    Uri uri =
        new Uri(
            Uri.WSS,
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertEquals(
        "wss://https://example.org/example@https://example.org/example:8080https://example.org/example",
        AuthenticatorUtils.computeRealmURI(uri, true, true));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   *
   * <p>Method under test: {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.computeRealmURI(Uri, boolean, boolean)"})
  void testComputeRealmURI6() {
    // Arrange
    Uri uri =
        new Uri(
            Uri.WS,
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertEquals(
        "ws://https://example.org/example@https://example.org/example:8080https://example.org/example",
        AuthenticatorUtils.computeRealmURI(uri, true, true));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean); then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.computeRealmURI(Uri, boolean, boolean)"})
  void testComputeRealmURI_thenReturnAString() {
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
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example",
        AuthenticatorUtils.computeRealmURI(uri, true, true));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean); then return a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.computeRealmURI(Uri, boolean, boolean)"})
  void testComputeRealmURI_thenReturnAString2() {
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
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example?https://example.org/example",
        AuthenticatorUtils.computeRealmURI(uri, true, false));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName(
      "Test computeRealmURI(Uri, boolean, boolean); then return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.computeRealmURI(Uri, boolean, boolean)"})
  void testComputeRealmURI_thenReturnHttpsExampleOrgExample() {
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
    assertEquals(
        "https://example.org/example", AuthenticatorUtils.computeRealmURI(uri, false, true));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName(
      "Test computeRealmURI(Uri, boolean, boolean); then return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.computeRealmURI(Uri, boolean, boolean)"})
  void testComputeRealmURI_thenReturnHttpsExampleOrgExample2() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "",
            "https://example.org/example");

    // Act and Assert
    assertEquals(
        "https://example.org/example", AuthenticatorUtils.computeRealmURI(uri, false, false));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code https://example.org/example?https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName(
      "Test computeRealmURI(Uri, boolean, boolean); then return 'https://example.org/example?https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.computeRealmURI(Uri, boolean, boolean)"})
  void testComputeRealmURI_thenReturnHttpsExampleOrgExampleHttpsExampleOrgExample() {
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
    assertEquals(
        "https://example.org/example?https://example.org/example",
        AuthenticatorUtils.computeRealmURI(uri, false, false));
  }

  /**
   * Test {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code /}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#computeRealmURI(Uri, boolean, boolean)}
   */
  @Test
  @DisplayName("Test computeRealmURI(Uri, boolean, boolean); then return '/'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.computeRealmURI(Uri, boolean, boolean)"})
  void testComputeRealmURI_thenReturnSlash() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "",
            "https://example.org/example",
            "https://example.org/example");

    // Act and Assert
    assertEquals("/", AuthenticatorUtils.computeRealmURI(uri, false, true));
  }

  /**
   * Test {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}.
   *
   * <p>Method under test: {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request,
   * Realm)}
   */
  @Test
  @DisplayName("Test perConnectionProxyAuthorizationHeader(Request, Realm)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AuthenticatorUtils.perConnectionProxyAuthorizationHeader(Request, Realm)"
  })
  void testPerConnectionProxyAuthorizationHeader() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("https://example.org/example");

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

    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getScheme()).thenReturn(AuthScheme.NTLM);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionProxyAuthorizationHeaderResult =
        AuthenticatorUtils.perConnectionProxyAuthorizationHeader(request, proxyRealm);

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    verify(proxyRealm).getScheme();
    verify(proxyRealm).isUsePreemptiveAuth();
    assertEquals(
        "NTLM TlRMTVNTUAABAAAAAYIIogAAAAAoAAAAAAAAACgAAAAFASgKAAAADw==",
        actualPerConnectionProxyAuthorizationHeaderResult);
  }

  /**
   * Test {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code NTLM}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request,
   * Realm)}
   */
  @Test
  @DisplayName(
      "Test perConnectionProxyAuthorizationHeader(Request, Realm); given ArrayList() add 'NTLM'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AuthenticatorUtils.perConnectionProxyAuthorizationHeader(Request, Realm)"
  })
  void testPerConnectionProxyAuthorizationHeader_givenArrayListAddNtlm_thenReturnNull()
      throws UnsupportedEncodingException {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("NTLM");
    stringList.add(null);

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

    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getScheme()).thenReturn(AuthScheme.NTLM);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionProxyAuthorizationHeaderResult =
        AuthenticatorUtils.perConnectionProxyAuthorizationHeader(request, proxyRealm);

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    verify(proxyRealm).getScheme();
    verify(proxyRealm).isUsePreemptiveAuth();
    assertNull(actualPerConnectionProxyAuthorizationHeaderResult);
  }

  /**
   * Test {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}.
   *
   * <ul>
   *   <li>Given {@code BASIC}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request,
   * Realm)}
   */
  @Test
  @DisplayName("Test perConnectionProxyAuthorizationHeader(Request, Realm); given 'BASIC'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AuthenticatorUtils.perConnectionProxyAuthorizationHeader(Request, Realm)"
  })
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

    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionProxyAuthorizationHeaderResult =
        AuthenticatorUtils.perConnectionProxyAuthorizationHeader(request, proxyRealm);

    // Assert
    verify(proxyRealm).getScheme();
    verify(proxyRealm).isUsePreemptiveAuth();
    assertNull(actualPerConnectionProxyAuthorizationHeaderResult);
  }

  /**
   * Test {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request,
   * Realm)}
   */
  @Test
  @DisplayName("Test perConnectionProxyAuthorizationHeader(Request, Realm); given 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AuthenticatorUtils.perConnectionProxyAuthorizationHeader(Request, Realm)"
  })
  void testPerConnectionProxyAuthorizationHeader_givenFalse() throws UnsupportedEncodingException {
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

    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(false);

    // Act
    String actualPerConnectionProxyAuthorizationHeaderResult =
        AuthenticatorUtils.perConnectionProxyAuthorizationHeader(request, proxyRealm);

    // Assert
    verify(proxyRealm).isUsePreemptiveAuth();
    assertNull(actualPerConnectionProxyAuthorizationHeaderResult);
  }

  /**
   * Test {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request,
   * Realm)}
   */
  @Test
  @DisplayName(
      "Test perConnectionProxyAuthorizationHeader(Request, Realm); then throw IllegalStateException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AuthenticatorUtils.perConnectionProxyAuthorizationHeader(Request, Realm)"
  })
  void testPerConnectionProxyAuthorizationHeader_thenThrowIllegalStateException()
      throws UnsupportedEncodingException {
    // Arrange
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenThrow(new IllegalStateException());
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

    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getScheme()).thenReturn(AuthScheme.NTLM);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> AuthenticatorUtils.perConnectionProxyAuthorizationHeader(request, proxyRealm));
    verify(headers).getAll(isA(CharSequence.class));
    verify(proxyRealm).getScheme();
    verify(proxyRealm).isUsePreemptiveAuth();
  }

  /**
   * Test {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}.
   *
   * <ul>
   *   <li>When {@link HttpHeaders} {@link HttpHeaders#getAll(CharSequence)} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request,
   * Realm)}
   */
  @Test
  @DisplayName(
      "Test perConnectionProxyAuthorizationHeader(Request, Realm); when HttpHeaders getAll(CharSequence) return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AuthenticatorUtils.perConnectionProxyAuthorizationHeader(Request, Realm)"
  })
  void testPerConnectionProxyAuthorizationHeader_whenHttpHeadersGetAllReturnNull()
      throws UnsupportedEncodingException {
    // Arrange
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(null);
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

    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getScheme()).thenReturn(AuthScheme.NTLM);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionProxyAuthorizationHeaderResult =
        AuthenticatorUtils.perConnectionProxyAuthorizationHeader(request, proxyRealm);

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    verify(proxyRealm).getScheme();
    verify(proxyRealm).isUsePreemptiveAuth();
    assertEquals(
        "NTLM TlRMTVNTUAABAAAAAYIIogAAAAAoAAAAAAAAACgAAAAFASgKAAAADw==",
        actualPerConnectionProxyAuthorizationHeaderResult);
  }

  /**
   * Test {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request, Realm)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#perConnectionProxyAuthorizationHeader(Request,
   * Realm)}
   */
  @Test
  @DisplayName(
      "Test perConnectionProxyAuthorizationHeader(Request, Realm); when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AuthenticatorUtils.perConnectionProxyAuthorizationHeader(Request, Realm)"
  })
  void testPerConnectionProxyAuthorizationHeader_whenNull_thenReturnNull()
      throws UnsupportedEncodingException {
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

    // Act and Assert
    assertNull(AuthenticatorUtils.perConnectionProxyAuthorizationHeader(request, null));
  }

  /**
   * Test {@link AuthenticatorUtils#perRequestProxyAuthorizationHeader(Request, Realm)}.
   *
   * <p>Method under test: {@link AuthenticatorUtils#perRequestProxyAuthorizationHeader(Request,
   * Realm)}
   */
  @Test
  @DisplayName("Test perRequestProxyAuthorizationHeader(Request, Realm)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AuthenticatorUtils.perRequestProxyAuthorizationHeader(Request, Realm)"
  })
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

    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getPassword()).thenReturn("https://example.org/example");
    when(proxyRealm.getPrincipal()).thenReturn("https://example.org/example");
    when(proxyRealm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(proxyRealm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerRequestProxyAuthorizationHeaderResult =
        AuthenticatorUtils.perRequestProxyAuthorizationHeader(request, proxyRealm);

    // Assert
    verify(proxyRealm).getCharset();
    verify(proxyRealm).getPassword();
    verify(proxyRealm).getPrincipal();
    verify(proxyRealm).getScheme();
    verify(proxyRealm).isUsePreemptiveAuth();
    assertEquals(
        "Basic aHR0cHM6Ly9leGFtcGxlLm9yZy9leGFtcGxlOmh0dHBzOi8vZXhhbXBsZS5vcmcvZXhhbXBsZQ==",
        actualPerRequestProxyAuthorizationHeaderResult);
  }

  /**
   * Test {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request, ProxyServer, Realm)}.
   *
   * <p>Method under test: {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request,
   * ProxyServer, Realm)}
   */
  @Test
  @DisplayName("Test perConnectionAuthorizationHeader(Request, ProxyServer, Realm)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AuthenticatorUtils.perConnectionAuthorizationHeader(Request, ProxyServer, Realm)"
  })
  void testPerConnectionAuthorizationHeader() {
    // Arrange
    Request request = mock(Request.class);
    ProxyServer proxyServer = mock(ProxyServer.class);

    Realm realm = mock(Realm.class);
    when(realm.getScheme()).thenReturn(AuthScheme.NTLM);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionAuthorizationHeaderResult =
        AuthenticatorUtils.perConnectionAuthorizationHeader(request, proxyServer, realm);

    // Assert
    verify(realm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    assertEquals(
        "NTLM TlRMTVNTUAABAAAAAYIIogAAAAAoAAAAAAAAACgAAAAFASgKAAAADw==",
        actualPerConnectionAuthorizationHeaderResult);
  }

  /**
   * Test {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request, ProxyServer, Realm)}.
   *
   * <ul>
   *   <li>Given {@code BASIC}.
   *   <li>When {@link Realm} {@link Realm#getScheme()} return {@code BASIC}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request,
   * ProxyServer, Realm)}
   */
  @Test
  @DisplayName(
      "Test perConnectionAuthorizationHeader(Request, ProxyServer, Realm); given 'BASIC'; when Realm getScheme() return 'BASIC'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AuthenticatorUtils.perConnectionAuthorizationHeader(Request, ProxyServer, Realm)"
  })
  void testPerConnectionAuthorizationHeader_givenBasic_whenRealmGetSchemeReturnBasic() {
    // Arrange
    Request request = mock(Request.class);
    ProxyServer proxyServer = mock(ProxyServer.class);

    Realm realm = mock(Realm.class);
    when(realm.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerConnectionAuthorizationHeaderResult =
        AuthenticatorUtils.perConnectionAuthorizationHeader(request, proxyServer, realm);

    // Assert
    verify(realm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    assertNull(actualPerConnectionAuthorizationHeaderResult);
  }

  /**
   * Test {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request, ProxyServer, Realm)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AuthenticatorUtils#perConnectionAuthorizationHeader(Request,
   * ProxyServer, Realm)}
   */
  @Test
  @DisplayName("Test perConnectionAuthorizationHeader(Request, ProxyServer, Realm); given 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AuthenticatorUtils.perConnectionAuthorizationHeader(Request, ProxyServer, Realm)"
  })
  void testPerConnectionAuthorizationHeader_givenFalse() {
    // Arrange
    Request request = mock(Request.class);
    ProxyServer proxyServer = mock(ProxyServer.class);

    Realm realm = mock(Realm.class);
    when(realm.isUsePreemptiveAuth()).thenReturn(false);

    // Act
    String actualPerConnectionAuthorizationHeaderResult =
        AuthenticatorUtils.perConnectionAuthorizationHeader(request, proxyServer, realm);

    // Assert
    verify(realm).isUsePreemptiveAuth();
    assertNull(actualPerConnectionAuthorizationHeaderResult);
  }

  /**
   * Test {@link AuthenticatorUtils#perRequestAuthorizationHeader(Request, Realm)}.
   *
   * <p>Method under test: {@link AuthenticatorUtils#perRequestAuthorizationHeader(Request, Realm)}
   */
  @Test
  @DisplayName("Test perRequestAuthorizationHeader(Request, Realm)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String AuthenticatorUtils.perRequestAuthorizationHeader(Request, Realm)"})
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

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm2.getScheme()).thenReturn(AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    String actualPerRequestAuthorizationHeaderResult =
        AuthenticatorUtils.perRequestAuthorizationHeader(request, realm2);

    // Assert
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    assertEquals(
        "Basic aHR0cHM6Ly9leGFtcGxlLm9yZy9leGFtcGxlOmh0dHBzOi8vZXhhbXBsZS5vcmcvZXhhbXBsZQ==",
        actualPerRequestAuthorizationHeaderResult);
  }
}
