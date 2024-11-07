package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.NameResolver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultRequestDiffblueTest {
  /**
   * Test
   * {@link DefaultRequest#DefaultRequest(String, Uri, InetAddress, InetAddress, HttpHeaders, List, byte[], List, String, ByteBuffer, ByteBuf, InputStream, BodyGenerator, List, List, String, ProxyServer, Realm, File, Boolean, Duration, Duration, long, Charset, ChannelPoolPartitioning, NameResolver)}.
   * <ul>
   *   <li>Then return CompositeByteData is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link DefaultRequest#DefaultRequest(String, Uri, InetAddress, InetAddress, HttpHeaders, List, byte[], List, String, ByteBuffer, ByteBuf, InputStream, BodyGenerator, List, List, String, ProxyServer, Realm, File, Boolean, Duration, Duration, long, Charset, ChannelPoolPartitioning, NameResolver)}
   */
  @Test
  @DisplayName("Test new DefaultRequest(String, Uri, InetAddress, InetAddress, HttpHeaders, List, byte[], List, String, ByteBuffer, ByteBuf, InputStream, BodyGenerator, List, List, String, ProxyServer, Realm, File, Boolean, Duration, Duration, long, Charset, ChannelPoolPartitioning, NameResolver); then return CompositeByteData is ArrayList()")
  void testNewDefaultRequest_thenReturnCompositeByteDataIsArrayList() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    HttpHeaders headers = mock(HttpHeaders.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");

    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    compositeByteData.add("AXAXAXAX".getBytes("UTF-8"));
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
    assertSame(compositeByteData,
        (new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)))
                .getCompositeByteData());
  }

  /**
   * Test
   * {@link DefaultRequest#DefaultRequest(String, Uri, InetAddress, InetAddress, HttpHeaders, List, byte[], List, String, ByteBuffer, ByteBuf, InputStream, BodyGenerator, List, List, String, ProxyServer, Realm, File, Boolean, Duration, Duration, long, Charset, ChannelPoolPartitioning, NameResolver)}.
   * <ul>
   *   <li>Then return CompositeByteData size is two.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link DefaultRequest#DefaultRequest(String, Uri, InetAddress, InetAddress, HttpHeaders, List, byte[], List, String, ByteBuffer, ByteBuf, InputStream, BodyGenerator, List, List, String, ProxyServer, Realm, File, Boolean, Duration, Duration, long, Charset, ChannelPoolPartitioning, NameResolver)}
   */
  @Test
  @DisplayName("Test new DefaultRequest(String, Uri, InetAddress, InetAddress, HttpHeaders, List, byte[], List, String, ByteBuffer, ByteBuf, InputStream, BodyGenerator, List, List, String, ProxyServer, Realm, File, Boolean, Duration, Duration, long, Charset, ChannelPoolPartitioning, NameResolver); then return CompositeByteData size is two")
  void testNewDefaultRequest_thenReturnCompositeByteDataSizeIsTwo() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    HttpHeaders headers = mock(HttpHeaders.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");

    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    compositeByteData.add("AXAXAXAX".getBytes("UTF-8"));
    compositeByteData.add("AXAXAXAX".getBytes("UTF-8"));
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
    List<byte[]> compositeByteData2 = (new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true,
        null, null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)))
            .getCompositeByteData();
    assertEquals(2, compositeByteData2.size());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), compositeByteData2.get(1));
  }

  /**
   * Test
   * {@link DefaultRequest#DefaultRequest(String, Uri, InetAddress, InetAddress, HttpHeaders, List, byte[], List, String, ByteBuffer, ByteBuf, InputStream, BodyGenerator, List, List, String, ProxyServer, Realm, File, Boolean, Duration, Duration, long, Charset, ChannelPoolPartitioning, NameResolver)}.
   * <ul>
   *   <li>Then return Cookies is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link DefaultRequest#DefaultRequest(String, Uri, InetAddress, InetAddress, HttpHeaders, List, byte[], List, String, ByteBuffer, ByteBuf, InputStream, BodyGenerator, List, List, String, ProxyServer, Realm, File, Boolean, Duration, Duration, long, Charset, ChannelPoolPartitioning, NameResolver)}
   */
  @Test
  @DisplayName("Test new DefaultRequest(String, Uri, InetAddress, InetAddress, HttpHeaders, List, byte[], List, String, ByteBuffer, ByteBuf, InputStream, BodyGenerator, List, List, String, ProxyServer, Realm, File, Boolean, Duration, Duration, long, Charset, ChannelPoolPartitioning, NameResolver); then return Cookies is ArrayList()")
  void testNewDefaultRequest_thenReturnCookiesIsArrayList() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Cookie> cookies = new ArrayList<>();
    cookies.add(new DefaultCookie("https://example.org/example", "https://example.org/example"));
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
    assertSame(cookies,
        (new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class))).getCookies());
  }

  /**
   * Test
   * {@link DefaultRequest#DefaultRequest(String, Uri, InetAddress, InetAddress, HttpHeaders, List, byte[], List, String, ByteBuffer, ByteBuf, InputStream, BodyGenerator, List, List, String, ProxyServer, Realm, File, Boolean, Duration, Duration, long, Charset, ChannelPoolPartitioning, NameResolver)}.
   * <ul>
   *   <li>Then return Cookies size is two.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link DefaultRequest#DefaultRequest(String, Uri, InetAddress, InetAddress, HttpHeaders, List, byte[], List, String, ByteBuffer, ByteBuf, InputStream, BodyGenerator, List, List, String, ProxyServer, Realm, File, Boolean, Duration, Duration, long, Charset, ChannelPoolPartitioning, NameResolver)}
   */
  @Test
  @DisplayName("Test new DefaultRequest(String, Uri, InetAddress, InetAddress, HttpHeaders, List, byte[], List, String, ByteBuffer, ByteBuf, InputStream, BodyGenerator, List, List, String, ProxyServer, Realm, File, Boolean, Duration, Duration, long, Charset, ChannelPoolPartitioning, NameResolver); then return Cookies size is two")
  void testNewDefaultRequest_thenReturnCookiesSizeIsTwo() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Cookie> cookies = new ArrayList<>();
    cookies.add(new DefaultCookie("https://example.org/example", "https://example.org/example"));
    DefaultCookie defaultCookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    cookies.add(defaultCookie);
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
    List<Cookie> cookies2 = (new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class))).getCookies();
    assertEquals(2, cookies2.size());
    assertSame(defaultCookie, cookies2.get(1));
  }

  /**
   * Test {@link DefaultRequest#getUrl()}.
   * <p>
   * Method under test: {@link DefaultRequest#getUrl()}
   */
  @Test
  @DisplayName("Test getUrl()")
  void testGetUrl() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.toUrl()).thenReturn("https://example.org/example");
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

    // Act
    String actualUrl = (new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies,
        byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null,
        1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class))).getUrl();

    // Assert
    verify(uri).toUrl();
    assertEquals("https://example.org/example", actualUrl);
  }

  /**
   * Test {@link DefaultRequest#getQueryParams()}.
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getQuery()} return empty string.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultRequest#getQueryParams()}
   */
  @Test
  @DisplayName("Test getQueryParams(); given Uri getQuery() return empty string; then return Empty")
  void testGetQueryParams_givenUriGetQueryReturnEmptyString_thenReturnEmpty() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getQuery()).thenReturn("");
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

    // Act
    List<Param> actualQueryParams = (new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true,
        null, null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)))
            .getQueryParams();

    // Assert
    verify(uri).getQuery();
    assertTrue(actualQueryParams.isEmpty());
  }

  /**
   * Test {@link DefaultRequest#getQueryParams()}.
   * <ul>
   *   <li>Given {@link Uri} {@link Uri#getQuery()} return {@code null}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultRequest#getQueryParams()}
   */
  @Test
  @DisplayName("Test getQueryParams(); given Uri getQuery() return 'null'; then return Empty")
  void testGetQueryParams_givenUriGetQueryReturnNull_thenReturnEmpty() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getQuery()).thenReturn(null);
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

    // Act
    List<Param> actualQueryParams = (new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true,
        null, null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)))
            .getQueryParams();

    // Assert
    verify(uri).getQuery();
    assertTrue(actualQueryParams.isEmpty());
  }

  /**
   * Test {@link DefaultRequest#getQueryParams()}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultRequest#getQueryParams()}
   */
  @Test
  @DisplayName("Test getQueryParams(); then return size is one")
  void testGetQueryParams_thenReturnSizeIsOne() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.getQuery()).thenReturn("https://example.org/example");
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

    // Act
    List<Param> actualQueryParams = (new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true,
        null, null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)))
            .getQueryParams();

    // Assert
    verify(uri, atLeast(1)).getQuery();
    assertEquals(1, actualQueryParams.size());
    Param getResult = actualQueryParams.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertNull(getResult.getValue());
  }

  /**
   * Test {@link DefaultRequest#toString()}.
   * <p>
   * Method under test: {@link DefaultRequest#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  void testToString() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.toUrl()).thenReturn("https://example.org/example");

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    entryList.add(new AbstractMap.SimpleEntry<>("\theaders:", "\theaders:"));
    Iterator<Map.Entry<String, String>> iteratorResult = entryList.iterator();
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.isEmpty()).thenReturn(false);
    when(headers.iterator()).thenReturn(iteratorResult);
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

    // Act
    String actualToStringResult = (new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true,
        null, null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)))
            .toString();

    // Assert
    verify(headers).isEmpty();
    verify(headers).iterator();
    verify(uri).toUrl();
    assertEquals("https://example.org/example\thttps://example.org/example\theaders:\t\theaders::\theaders:",
        actualToStringResult);
  }

  /**
   * Test {@link DefaultRequest#toString()}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultRequest#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return a string")
  void testToString_thenReturnAString() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.isEmpty()).thenReturn(true);

    ArrayList<Param> formParams = new ArrayList<>();
    formParams.add(new Param("https://example.org/example", "https://example.org/example"));
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf byteBufData = mock(ByteBuf.class);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    File file = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    String actualToStringResult = (new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true,
        null, null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)))
            .toString();

    // Assert
    verify(headers).isEmpty();
    verify(uri).toUrl();
    assertEquals(
        "https://example.org/example\thttps://example.org/example\theaders:\tformParams:\thttps://example.org/example"
            + ":https://example.org/example",
        actualToStringResult);
  }

  /**
   * Test {@link DefaultRequest#toString()}.
   * <ul>
   *   <li>Then return
   * {@code https://example.org/example https://example.org/example headers:}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultRequest#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return 'https://example.org/example https://example.org/example headers:'")
  void testToString_thenReturnHttpsExampleOrgExampleHttpsExampleOrgExampleHeaders()
      throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.isEmpty()).thenReturn(true);
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

    // Act
    String actualToStringResult = (new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true,
        null, null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)))
            .toString();

    // Assert
    verify(headers).isEmpty();
    verify(uri).toUrl();
    assertEquals("https://example.org/example\thttps://example.org/example\theaders:", actualToStringResult);
  }

  /**
   * Test {@link DefaultRequest#toString()}.
   * <ul>
   *   <li>Then return
   * {@code https://example.org/example https://example.org/example headers:}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultRequest#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return 'https://example.org/example https://example.org/example headers:'")
  void testToString_thenReturnHttpsExampleOrgExampleHttpsExampleOrgExampleHeaders2()
      throws UnsupportedEncodingException {
    // Arrange
    Uri uri = mock(Uri.class);
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.isEmpty()).thenReturn(false);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
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

    // Act
    String actualToStringResult = (new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true,
        null, null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)))
            .toString();

    // Assert
    verify(headers).isEmpty();
    verify(headers).iterator();
    verify(uri).toUrl();
    assertEquals("https://example.org/example\thttps://example.org/example\theaders:", actualToStringResult);
  }
}
