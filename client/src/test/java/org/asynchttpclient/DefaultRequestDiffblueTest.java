package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.DefaultNameResolver;
import io.netty.resolver.NameResolver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;

class DefaultRequestDiffblueTest {
  /**
   * Method under test: {@link DefaultRequest#getUrl()}
   */
  @Test
  void testGetUrl() throws UnsupportedEncodingException {
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
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example?https://example.org/example",
        (new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER)).getUrl());
  }

  /**
   * Method under test: {@link DefaultRequest#getUrl()}
   */
  @Test
  void testGetUrl2() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
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
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/examplehttps://example"
            + ".org/example?https://example.org/example",
        (new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER)).getUrl());
  }

  /**
   * Method under test: {@link DefaultRequest#getUrl()}
   */
  @Test
  void testGetUrl3() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
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
    assertEquals("https://example.org/example",
        (new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER)).getUrl());
  }

  /**
   * Method under test: {@link DefaultRequest#getQueryParams()}
   */
  @Test
  void testGetQueryParams() throws UnsupportedEncodingException {
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

    // Act
    List<Param> actualQueryParams = (new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER)).getQueryParams();

    // Assert
    assertEquals(1, actualQueryParams.size());
    Param getResult = actualQueryParams.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertNull(getResult.getValue());
  }

  /**
   * Method under test: {@link DefaultRequest#getQueryParams()}
   */
  @Test
  void testGetQueryParams2() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", null, "https://example.org/example");

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
    assertTrue((new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies,
        byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER)).getQueryParams().isEmpty());
  }

  /**
   * Method under test: {@link DefaultRequest#getQueryParams()}
   */
  @Test
  void testGetQueryParams3() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "", "https://example.org/example");

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
    assertTrue((new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies,
        byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER)).getQueryParams().isEmpty());
  }

  /**
   * Method under test: {@link DefaultRequest#toString()}
   */
  @Test
  void testToString() throws UnsupportedEncodingException {
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
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example?https://example.org/example\thttps://example.org/example\theaders:",
        (new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER)).toString());
  }

  /**
   * Method under test: {@link DefaultRequest#toString()}
   */
  @Test
  void testToString2() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
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
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/examplehttps://example"
            + ".org/example?https://example.org/example\thttps://example.org/example\theaders:",
        (new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER)).toString());
  }

  /**
   * Method under test: {@link DefaultRequest#toString()}
   */
  @Test
  void testToString3() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
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
    assertEquals("https://example.org/example\thttps://example.org/example\theaders:",
        (new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER)).toString());
  }

  /**
   * Method under test: {@link DefaultRequest#toString()}
   */
  @Test
  void testToString4() throws UnsupportedEncodingException {
    // Arrange
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    headers.add((CharSequence) AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "Value");
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
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
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example?https://example.org/example\thttps://example.org/example\theaders:\tacquireFreeCha"
            + "nnelTimeout:Value",
        (new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER)).toString());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link DefaultRequest#getAddress()}
   *   <li>{@link DefaultRequest#getBodyGenerator()}
   *   <li>{@link DefaultRequest#getBodyParts()}
   *   <li>{@link DefaultRequest#getByteBufData()}
   *   <li>{@link DefaultRequest#getByteBufferData()}
   *   <li>{@link DefaultRequest#getByteData()}
   *   <li>{@link DefaultRequest#getChannelPoolPartitioning()}
   *   <li>{@link DefaultRequest#getCharset()}
   *   <li>{@link DefaultRequest#getCompositeByteData()}
   *   <li>{@link DefaultRequest#getCookies()}
   *   <li>{@link DefaultRequest#getFile()}
   *   <li>{@link DefaultRequest#getFollowRedirect()}
   *   <li>{@link DefaultRequest#getFormParams()}
   *   <li>{@link DefaultRequest#getHeaders()}
   *   <li>{@link DefaultRequest#getLocalAddress()}
   *   <li>{@link DefaultRequest#getMethod()}
   *   <li>{@link DefaultRequest#getNameResolver()}
   *   <li>{@link DefaultRequest#getProxyServer()}
   *   <li>{@link DefaultRequest#getRangeOffset()}
   *   <li>{@link DefaultRequest#getReadTimeout()}
   *   <li>{@link DefaultRequest#getRealm()}
   *   <li>{@link DefaultRequest#getRequestTimeout()}
   *   <li>{@link DefaultRequest#getStreamData()}
   *   <li>{@link DefaultRequest#getStringData()}
   *   <li>{@link DefaultRequest#getUri()}
   *   <li>{@link DefaultRequest#getVirtualHost()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() throws IOException {
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
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest defaultRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    defaultRequest.getAddress();
    defaultRequest.getBodyGenerator();
    List<Part> actualBodyParts = defaultRequest.getBodyParts();
    ByteBuf actualByteBufData = defaultRequest.getByteBufData();
    ByteBuffer actualByteBufferData = defaultRequest.getByteBufferData();
    byte[] actualByteData = defaultRequest.getByteData();
    defaultRequest.getChannelPoolPartitioning();
    Charset actualCharset = defaultRequest.getCharset();
    List<byte[]> actualCompositeByteData = defaultRequest.getCompositeByteData();
    List<Cookie> actualCookies = defaultRequest.getCookies();
    File actualFile = defaultRequest.getFile();
    Boolean actualFollowRedirect = defaultRequest.getFollowRedirect();
    List<Param> actualFormParams = defaultRequest.getFormParams();
    HttpHeaders actualHeaders = defaultRequest.getHeaders();
    defaultRequest.getLocalAddress();
    String actualMethod = defaultRequest.getMethod();
    NameResolver<InetAddress> actualNameResolver = defaultRequest.getNameResolver();
    ProxyServer actualProxyServer = defaultRequest.getProxyServer();
    long actualRangeOffset = defaultRequest.getRangeOffset();
    Duration actualReadTimeout = defaultRequest.getReadTimeout();
    Realm actualRealm = defaultRequest.getRealm();
    Duration actualRequestTimeout = defaultRequest.getRequestTimeout();
    InputStream actualStreamData = defaultRequest.getStreamData();
    String actualStringData = defaultRequest.getStringData();
    Uri actualUri = defaultRequest.getUri();

    // Assert
    assertTrue(actualNameResolver instanceof DefaultNameResolver);
    assertEquals("https://example.org/example", actualMethod);
    assertEquals("https://example.org/example", actualStringData);
    assertEquals("https://example.org/example", defaultRequest.getVirtualHost());
    assertEquals("test.txt", actualFile.getName());
    assertNull(actualCharset);
    assertNull(actualRealm);
    assertEquals(0L, actualReadTimeout.toNanos());
    assertEquals(1L, actualRangeOffset);
    assertEquals(8, actualStreamData.read(new byte[8]));
    assertTrue(actualFile.isAbsolute());
    assertTrue(actualBodyParts.isEmpty());
    assertTrue(actualCompositeByteData.isEmpty());
    assertTrue(actualCookies.isEmpty());
    assertTrue(actualFormParams.isEmpty());
    assertTrue(actualFollowRedirect);
    assertSame(byteBufData, actualByteBufData);
    assertSame(headers, actualHeaders);
    assertSame(streamData, actualStreamData);
    assertSame(bodyParts, actualBodyParts);
    assertSame(compositeByteData, actualCompositeByteData);
    assertSame(cookies, actualCookies);
    assertSame(formParams, actualFormParams);
    assertSame(uri, actualUri);
    assertSame(byteBufferData, actualByteBufferData);
    Duration duration = actualRequestTimeout.ZERO;
    assertSame(duration, actualReadTimeout);
    assertSame(duration, actualRequestTimeout);
    assertSame(defaultRequest.proxyServer, actualProxyServer);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteData);
  }
}
