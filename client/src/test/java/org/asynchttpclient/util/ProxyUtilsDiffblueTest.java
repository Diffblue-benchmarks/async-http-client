package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.handler.codec.http.DefaultHttpHeaders;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProxyUtilsDiffblueTest {
  /**
   * Test {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link ArrayList#ArrayList()} add empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName(
      "Test getProxyServer(AsyncHttpClientConfig, Request); given empty string; when ArrayList() add empty string; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServer ProxyUtils.getProxyServer(AsyncHttpClientConfig, Request)"})
  void testGetProxyServer_givenEmptyString_whenArrayListAddEmptyString_thenReturnNull()
      throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);

    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("");
    nonProxyHosts.add("https://example.org/example");
    ProxyServer proxyServer =
        new ProxyServer(
            "https://example.org/example",
            8080,
            8080,
            mock(Realm.class),
            nonProxyHosts,
            ProxyType.HTTP);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf byteBufData =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
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
            RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    ProxyServer actualProxyServer = ProxyUtils.getProxyServer(config, request);

    // Assert
    assertNull(actualProxyServer);
  }

  /**
   * Test {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then calls {@link ProxyServer#isIgnoredForHost(String)}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName(
      "Test getProxyServer(AsyncHttpClientConfig, Request); given 'false'; then calls isIgnoredForHost(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServer ProxyUtils.getProxyServer(AsyncHttpClientConfig, Request)"})
  void testGetProxyServer_givenFalse_thenCallsIsIgnoredForHost()
      throws UnsupportedEncodingException {
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
    ProxyUtils.getProxyServer(config, request);

    // Assert
    verify(proxyServer).isIgnoredForHost("https://example.org/example");
    verify(uri).getHost();
  }

  /**
   * Test {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}.
   *
   * <ul>
   *   <li>Given {@code hostname}.
   *   <li>Then return NonProxyHosts size is one.
   * </ul>
   *
   * <p>Method under test: {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName(
      "Test getProxyServer(AsyncHttpClientConfig, Request); given 'hostname'; then return NonProxyHosts size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServer ProxyUtils.getProxyServer(AsyncHttpClientConfig, Request)"})
  void testGetProxyServer_givenHostname_thenReturnNonProxyHostsSizeIsOne()
      throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);

    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("hostname");
    ProxyServer proxyServer =
        new ProxyServer(
            "https://example.org/example",
            8080,
            8080,
            mock(Realm.class),
            nonProxyHosts,
            ProxyType.HTTP);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf byteBufData =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
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
            RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    ProxyServer actualProxyServer = ProxyUtils.getProxyServer(config, request);

    // Assert
    List<String> nonProxyHosts2 = actualProxyServer.getNonProxyHosts();
    assertEquals(1, nonProxyHosts2.size());
    assertEquals("hostname", nonProxyHosts2.get(0));
    assertEquals("https://example.org/example", actualProxyServer.getHost());
    assertNull(actualProxyServer.getCustomHeaders());
    assertEquals(8080, actualProxyServer.getPort());
    assertEquals(8080, actualProxyServer.getSecuredPort());
    assertEquals(ProxyType.HTTP, actualProxyServer.getProxyType());
    assertSame(nonProxyHosts, nonProxyHosts2);
  }

  /**
   * Test {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}.
   *
   * <ul>
   *   <li>Given {@code hostname}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code hostname}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName(
      "Test getProxyServer(AsyncHttpClientConfig, Request); given 'hostname'; when ArrayList() add 'hostname'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServer ProxyUtils.getProxyServer(AsyncHttpClientConfig, Request)"})
  void testGetProxyServer_givenHostname_whenArrayListAddHostname_thenReturnNull()
      throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);

    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("hostname");
    nonProxyHosts.add("https://example.org/example");
    ProxyServer proxyServer =
        new ProxyServer(
            "https://example.org/example",
            8080,
            8080,
            mock(Realm.class),
            nonProxyHosts,
            ProxyType.HTTP);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf byteBufData =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
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
            RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    ProxyServer actualProxyServer = ProxyUtils.getProxyServer(config, request);

    // Assert
    assertNull(actualProxyServer);
  }

  /**
   * Test {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}.
   *
   * <ul>
   *   <li>Given {@link ProxyServerSelector} {@link ProxyServerSelector#select(Uri)} return {@code
   *       null}.
   *   <li>Then calls {@link ProxyServerSelector#select(Uri)}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName(
      "Test getProxyServer(AsyncHttpClientConfig, Request); given ProxyServerSelector select(Uri) return 'null'; then calls select(Uri)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServer ProxyUtils.getProxyServer(AsyncHttpClientConfig, Request)"})
  void testGetProxyServer_givenProxyServerSelectorSelectReturnNull_thenCallsSelect()
      throws UnsupportedEncodingException {
    // Arrange
    ProxyServerSelector proxyServerSelector = mock(ProxyServerSelector.class);
    when(proxyServerSelector.select(Mockito.<Uri>any())).thenReturn(null);

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getProxyServerSelector()).thenReturn(proxyServerSelector);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf byteBufData =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
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
            null,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    ProxyServer actualProxyServer = ProxyUtils.getProxyServer(config, request);

    // Assert
    verify(config).getProxyServerSelector();
    verify(proxyServerSelector).select(isA(Uri.class));
    assertNull(actualProxyServer);
  }

  /**
   * Test {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}.
   *
   * <ul>
   *   <li>Then return NonProxyHosts Empty.
   * </ul>
   *
   * <p>Method under test: {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName(
      "Test getProxyServer(AsyncHttpClientConfig, Request); then return NonProxyHosts Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServer ProxyUtils.getProxyServer(AsyncHttpClientConfig, Request)"})
  void testGetProxyServer_thenReturnNonProxyHostsEmpty() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf byteBufData =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer =
        new ProxyServer(
            "https://example.org/example", 8080, 8080, realm, new ArrayList<>(), ProxyType.HTTP);
    Realm realm2 = mock(Realm.class);
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
            realm2,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    ProxyServer actualProxyServer = ProxyUtils.getProxyServer(config, request);

    // Assert
    assertEquals("https://example.org/example", actualProxyServer.getHost());
    assertNull(actualProxyServer.getCustomHeaders());
    assertEquals(8080, actualProxyServer.getPort());
    assertEquals(8080, actualProxyServer.getSecuredPort());
    assertEquals(ProxyType.HTTP, actualProxyServer.getProxyType());
    assertTrue(actualProxyServer.getNonProxyHosts().isEmpty());
  }

  /**
   * Test {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}.
   *
   * <ul>
   *   <li>Then return NonProxyHosts is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName(
      "Test getProxyServer(AsyncHttpClientConfig, Request); then return NonProxyHosts is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServer ProxyUtils.getProxyServer(AsyncHttpClientConfig, Request)"})
  void testGetProxyServer_thenReturnNonProxyHostsIsNull() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf byteBufData =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer =
        new ProxyServer(
            "https://example.org/example", 8080, 8080, mock(Realm.class), null, ProxyType.HTTP);
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
            RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    ProxyServer actualProxyServer = ProxyUtils.getProxyServer(config, request);

    // Assert
    assertEquals("https://example.org/example", actualProxyServer.getHost());
    assertNull(actualProxyServer.getNonProxyHosts());
    assertNull(actualProxyServer.getCustomHeaders());
    assertEquals(8080, actualProxyServer.getPort());
    assertEquals(8080, actualProxyServer.getSecuredPort());
    assertEquals(ProxyType.HTTP, actualProxyServer.getProxyType());
  }

  /**
   * Test {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} add {@code https://example.org/example}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName(
      "Test getProxyServer(AsyncHttpClientConfig, Request); when ArrayList() add 'https://example.org/example'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServer ProxyUtils.getProxyServer(AsyncHttpClientConfig, Request)"})
  void testGetProxyServer_whenArrayListAddHttpsExampleOrgExample_thenReturnNull()
      throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);

    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("https://example.org/example");
    ProxyServer proxyServer =
        new ProxyServer(
            "https://example.org/example",
            8080,
            8080,
            mock(Realm.class),
            nonProxyHosts,
            ProxyType.HTTP);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf byteBufData =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
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
            RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    ProxyServer actualProxyServer = ProxyUtils.getProxyServer(config, request);

    // Assert
    assertNull(actualProxyServer);
  }

  /**
   * Test {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}.
   *
   * <ul>
   *   <li>When {@link AsyncHttpClientConfig} {@link AsyncHttpClientConfig#getProxyServerSelector()}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyUtils#getProxyServer(AsyncHttpClientConfig, Request)}
   */
  @Test
  @DisplayName(
      "Test getProxyServer(AsyncHttpClientConfig, Request); when AsyncHttpClientConfig getProxyServerSelector() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServer ProxyUtils.getProxyServer(AsyncHttpClientConfig, Request)"})
  void testGetProxyServer_whenAsyncHttpClientConfigGetProxyServerSelectorReturnNull()
      throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getProxyServerSelector()).thenReturn(null);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf byteBufData =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
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
            null,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    ProxyServer actualProxyServer = ProxyUtils.getProxyServer(config, request);

    // Assert
    verify(config).getProxyServerSelector();
    assertNull(actualProxyServer);
  }

  /**
   * Test {@link ProxyUtils#createProxyServerSelector(Properties)} with {@code properties}.
   *
   * <p>Method under test: {@link ProxyUtils#createProxyServerSelector(Properties)}
   */
  @Test
  @DisplayName("Test createProxyServerSelector(Properties) with 'properties'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServerSelector ProxyUtils.createProxyServerSelector(Properties)"})
  void testCreateProxyServerSelectorWithProperties() {
    // Arrange and Act
    ProxyServerSelector actualCreateProxyServerSelectorResult =
        ProxyUtils.createProxyServerSelector(new Properties());
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    ProxyServer actualSelectResult = actualCreateProxyServerSelectorResult.select(uri);

    // Assert
    assertNull(actualSelectResult);
  }

  /**
   * Test {@link ProxyUtils#getJdkDefaultProxyServerSelector()}.
   *
   * <p>Method under test: {@link ProxyUtils#getJdkDefaultProxyServerSelector()}
   */
  @Test
  @DisplayName("Test getJdkDefaultProxyServerSelector()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServerSelector ProxyUtils.getJdkDefaultProxyServerSelector()"})
  void testGetJdkDefaultProxyServerSelector() {
    // Arrange and Act
    ProxyServerSelector actualJdkDefaultProxyServerSelector =
        ProxyUtils.getJdkDefaultProxyServerSelector();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    ProxyServer actualSelectResult = actualJdkDefaultProxyServerSelector.select(uri);

    // Assert
    assertNull(actualSelectResult);
  }

  /**
   * Test {@link ProxyUtils#getJdkDefaultProxyServerSelector()}.
   *
   * <p>Method under test: {@link ProxyUtils#getJdkDefaultProxyServerSelector()}
   */
  @Test
  @DisplayName("Test getJdkDefaultProxyServerSelector()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServerSelector ProxyUtils.getJdkDefaultProxyServerSelector()"})
  void testGetJdkDefaultProxyServerSelector2() {
    // Arrange and Act
    ProxyServerSelector actualJdkDefaultProxyServerSelector =
        ProxyUtils.getJdkDefaultProxyServerSelector();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "User Info",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    ProxyServer actualSelectResult = actualJdkDefaultProxyServerSelector.select(uri);

    // Assert
    assertNull(actualSelectResult);
  }
}
