package org.asynchttpclient.handler.resumable;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.DefaultNameResolver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.AbstractBasicTest;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilderBase;
import org.asynchttpclient.Response;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.handler.TransferCompletionHandler;
import org.asynchttpclient.handler.TransferListener;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.LazyResponseBodyPart;
import org.asynchttpclient.netty.NettyResponse;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ResumableAsyncHandlerDiffblueTest {
  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  void testOnStatusReceived() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(1));

    // Act
    AsyncHandler.State actualOnStatusReceivedResult = resumableAsyncHandler
        .onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel()));

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    HttpHeaders headers = onCompletedResult.getHeaders();
    assertTrue(headers instanceof EmptyHttpHeaders);
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertEquals("", onCompletedResult.getResponseBody());
    assertEquals("Unknown Status (1)", onCompletedResult.getStatusText());
    assertNull(onCompletedResult.getContentType());
    assertEquals(-1, onCompletedResult.getResponseBodyAsStream().read(new byte[]{}));
    assertEquals(0, headers.size());
    ByteBuffer responseBodyAsByteBuffer = onCompletedResult.getResponseBodyAsByteBuffer();
    assertEquals(0, responseBodyAsByteBuffer.capacity());
    assertEquals(0, responseBodyAsByteBuffer.limit());
    assertEquals(0, responseBodyAsByteBuffer.position());
    assertEquals(0, responseBodyAsByteBuffer.array().length);
    assertEquals(0, onCompletedResult.getResponseBodyAsBytes().length);
    assertEquals(1, onCompletedResult.getStatusCode());
    assertEquals(AsyncHandler.State.ABORT, actualOnStatusReceivedResult);
    assertFalse(responseBodyAsByteBuffer.hasRemaining());
    assertFalse(headers.iterator().hasNext());
    assertFalse(onCompletedResult.hasResponseBody());
    assertFalse(onCompletedResult.hasResponseHeaders());
    assertFalse(onCompletedResult.isRedirected());
    assertTrue(headers.isEmpty());
    assertTrue(responseBodyAsByteBuffer.hasArray());
    assertTrue(onCompletedResult.getCookies().isEmpty());
    assertTrue(onCompletedResult.hasResponseStatus());
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived2() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler(true);

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived3() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    AsyncHandler.State actualOnBodyPartReceivedResult = resumableAsyncHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(AsyncHandler.State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived4() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler
        .onBodyPartReceived(new LazyResponseBodyPart(new DuplicatedByteBuf(Unpooled.compositeBuffer(3)), true)));
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived5() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        resumableAsyncHandler
            .onBodyPartReceived(new LazyResponseBodyPart(
                new DuplicatedByteBuf(
                    new ReadOnlyByteBuf(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())))),
                true)));
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived6() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler.onBodyPartReceived(
        new LazyResponseBodyPart(new DuplicatedByteBuf(new ReadOnlyByteBuf(Unpooled.compositeBuffer(3))), true)));
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived7() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler
        .onBodyPartReceived(new LazyResponseBodyPart(new EmptyByteBuf(new AdaptiveByteBufAllocator()), true)));
  }

  /**
   * Method under test: {@link ResumableAsyncHandler#onCompleted()}
   */
  @Test
  void testOnCompleted() throws Exception {
    // Arrange, Act and Assert
    assertNull((new ResumableAsyncHandler()).onCompleted());
  }

  /**
   * Method under test: {@link ResumableAsyncHandler#onCompleted()}
   */
  @Test
  void testOnCompleted2() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri(Uri.HTTP, "https://example.org/example", "https://example.org/example", 8080,
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
    resumableAsyncHandler.adjustRequestRange(new DefaultRequest("https://example.org/example", uri, address,
        localAddress, headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData,
        byteBufData, streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer,
        realm2, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER));

    // Act and Assert
    assertNull(resumableAsyncHandler.onCompleted());
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnHeadersReceived() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnHeadersReceived2() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler(1L);

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnHeadersReceived3() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("42");

    // Act
    AsyncHandler.State actualOnHeadersReceivedResult = resumableAsyncHandler.onHeadersReceived(headers);

    // Assert
    verify(headers).get(isA(CharSequence.class));
    assertEquals(AsyncHandler.State.CONTINUE, actualOnHeadersReceivedResult);
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnTrailingHeadersReceived() {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        resumableAsyncHandler.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnTrailingHeadersReceived2() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        (new ResumableAsyncHandler()).onTrailingHeadersReceived(mock(EmptyHttpHeaders.class)));
  }

  /**
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  void testAdjustRequestRange() throws IOException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri(Uri.HTTP, "https://example.org/example", "https://example.org/example", 8080,
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
    Request actualAdjustRequestRangeResult = resumableAsyncHandler
        .adjustRequestRange(new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
            cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
            streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER));

    // Assert
    HttpHeaders headers2 = actualAdjustRequestRangeResult.getHeaders();
    assertTrue(headers2 instanceof DefaultHttpHeaders);
    assertTrue(actualAdjustRequestRangeResult.getNameResolver() instanceof DefaultNameResolver);
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    assertEquals("UTF-8", actualAdjustRequestRangeResult.getCharset().name());
    assertEquals("http://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
        + "://example.org/example", actualAdjustRequestRangeResult.getUrl());
    List<Param> queryParams = actualAdjustRequestRangeResult.getQueryParams();
    assertEquals(1, queryParams.size());
    Param getResult = queryParams.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", actualAdjustRequestRangeResult.getMethod());
    assertEquals("https://example.org/example", actualAdjustRequestRangeResult.getStringData());
    assertEquals("https://example.org/example", actualAdjustRequestRangeResult.getVirtualHost());
    File file = actualAdjustRequestRangeResult.getFile();
    assertEquals("test.txt", file.getName());
    assertNull(getResult.getValue());
    Duration readTimeout = actualAdjustRequestRangeResult.getReadTimeout();
    assertEquals(0L, readTimeout.toNanos());
    assertEquals(1L, actualAdjustRequestRangeResult.getRangeOffset());
    InputStream streamData2 = actualAdjustRequestRangeResult.getStreamData();
    byte[] byteArray = new byte[8];
    assertEquals(8, streamData2.read(byteArray));
    assertTrue(file.isAbsolute());
    List<Part> bodyParts2 = actualAdjustRequestRangeResult.getBodyParts();
    assertTrue(bodyParts2.isEmpty());
    List<byte[]> compositeByteData2 = actualAdjustRequestRangeResult.getCompositeByteData();
    assertTrue(compositeByteData2.isEmpty());
    assertTrue(actualAdjustRequestRangeResult.getFollowRedirect());
    assertEquals(headers, headers2);
    assertEquals(uri, actualAdjustRequestRangeResult.getUri());
    assertSame(byteBufData, actualAdjustRequestRangeResult.getByteBufData());
    assertSame(streamData, streamData2);
    assertSame(compositeByteData, compositeByteData2);
    assertSame(proxyServer, actualAdjustRequestRangeResult.getProxyServer());
    assertSame(bodyParts2, actualAdjustRequestRangeResult.getCookies());
    assertSame(bodyParts2, actualAdjustRequestRangeResult.getFormParams());
    assertSame(readTimeout, actualAdjustRequestRangeResult.getRequestTimeout());
    assertSame(byteBufferData, actualAdjustRequestRangeResult.getByteBufferData());
    byte[] expectedByteData = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedByteData, actualAdjustRequestRangeResult.getByteData());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  void testAdjustRequestRange2() throws IOException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
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
    Request actualAdjustRequestRangeResult = resumableAsyncHandler
        .adjustRequestRange(new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
            cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
            streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER));

    // Assert
    HttpHeaders headers2 = actualAdjustRequestRangeResult.getHeaders();
    assertTrue(headers2 instanceof DefaultHttpHeaders);
    assertTrue(actualAdjustRequestRangeResult.getNameResolver() instanceof DefaultNameResolver);
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    assertEquals("UTF-8", actualAdjustRequestRangeResult.getCharset().name());
    List<Param> queryParams = actualAdjustRequestRangeResult.getQueryParams();
    assertEquals(1, queryParams.size());
    Param getResult = queryParams.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", actualAdjustRequestRangeResult.getMethod());
    assertEquals("https://example.org/example", actualAdjustRequestRangeResult.getStringData());
    assertEquals("https://example.org/example", actualAdjustRequestRangeResult.getVirtualHost());
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
        + "://example.org/example", actualAdjustRequestRangeResult.getUrl());
    File file = actualAdjustRequestRangeResult.getFile();
    assertEquals("test.txt", file.getName());
    assertNull(getResult.getValue());
    Duration readTimeout = actualAdjustRequestRangeResult.getReadTimeout();
    assertEquals(0L, readTimeout.toNanos());
    assertEquals(1L, actualAdjustRequestRangeResult.getRangeOffset());
    InputStream streamData2 = actualAdjustRequestRangeResult.getStreamData();
    byte[] byteArray = new byte[8];
    assertEquals(8, streamData2.read(byteArray));
    assertTrue(file.isAbsolute());
    List<Part> bodyParts2 = actualAdjustRequestRangeResult.getBodyParts();
    assertTrue(bodyParts2.isEmpty());
    List<byte[]> compositeByteData2 = actualAdjustRequestRangeResult.getCompositeByteData();
    assertTrue(compositeByteData2.isEmpty());
    assertTrue(actualAdjustRequestRangeResult.getFollowRedirect());
    assertEquals(headers, headers2);
    assertEquals(uri, actualAdjustRequestRangeResult.getUri());
    assertSame(byteBufData, actualAdjustRequestRangeResult.getByteBufData());
    assertSame(streamData, streamData2);
    assertSame(compositeByteData, compositeByteData2);
    assertSame(proxyServer, actualAdjustRequestRangeResult.getProxyServer());
    assertSame(bodyParts2, actualAdjustRequestRangeResult.getCookies());
    assertSame(bodyParts2, actualAdjustRequestRangeResult.getFormParams());
    assertSame(readTimeout, actualAdjustRequestRangeResult.getRequestTimeout());
    assertSame(byteBufferData, actualAdjustRequestRangeResult.getByteBufferData());
    byte[] expectedByteData = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedByteData, actualAdjustRequestRangeResult.getByteData());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  void testAdjustRequestRange3() throws IOException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
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
    Request actualAdjustRequestRangeResult = resumableAsyncHandler
        .adjustRequestRange(new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
            cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
            streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER));

    // Assert
    HttpHeaders headers2 = actualAdjustRequestRangeResult.getHeaders();
    assertTrue(headers2 instanceof DefaultHttpHeaders);
    assertTrue(actualAdjustRequestRangeResult.getNameResolver() instanceof DefaultNameResolver);
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    assertEquals("UTF-8", actualAdjustRequestRangeResult.getCharset().name());
    List<Param> queryParams = actualAdjustRequestRangeResult.getQueryParams();
    assertEquals(1, queryParams.size());
    Param getResult = queryParams.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", actualAdjustRequestRangeResult.getMethod());
    assertEquals("https://example.org/example", actualAdjustRequestRangeResult.getStringData());
    assertEquals("https://example.org/example", actualAdjustRequestRangeResult.getVirtualHost());
    File file = actualAdjustRequestRangeResult.getFile();
    assertEquals("test.txt", file.getName());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example?https:/"
        + "/example.org/example", actualAdjustRequestRangeResult.getUrl());
    assertNull(getResult.getValue());
    Duration readTimeout = actualAdjustRequestRangeResult.getReadTimeout();
    assertEquals(0L, readTimeout.toNanos());
    assertEquals(1L, actualAdjustRequestRangeResult.getRangeOffset());
    InputStream streamData2 = actualAdjustRequestRangeResult.getStreamData();
    byte[] byteArray = new byte[8];
    assertEquals(8, streamData2.read(byteArray));
    assertTrue(file.isAbsolute());
    List<Part> bodyParts2 = actualAdjustRequestRangeResult.getBodyParts();
    assertTrue(bodyParts2.isEmpty());
    List<byte[]> compositeByteData2 = actualAdjustRequestRangeResult.getCompositeByteData();
    assertTrue(compositeByteData2.isEmpty());
    assertTrue(actualAdjustRequestRangeResult.getFollowRedirect());
    assertEquals(headers, headers2);
    assertEquals(uri, actualAdjustRequestRangeResult.getUri());
    assertSame(byteBufData, actualAdjustRequestRangeResult.getByteBufData());
    assertSame(streamData, streamData2);
    assertSame(compositeByteData, compositeByteData2);
    assertSame(proxyServer, actualAdjustRequestRangeResult.getProxyServer());
    assertSame(bodyParts2, actualAdjustRequestRangeResult.getCookies());
    assertSame(bodyParts2, actualAdjustRequestRangeResult.getFormParams());
    assertSame(readTimeout, actualAdjustRequestRangeResult.getRequestTimeout());
    assertSame(byteBufferData, actualAdjustRequestRangeResult.getByteBufferData());
    byte[] expectedByteData = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedByteData, actualAdjustRequestRangeResult.getByteData());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  void testAdjustRequestRange4() throws IOException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
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
    Request actualAdjustRequestRangeResult = resumableAsyncHandler
        .adjustRequestRange(new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
            cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
            streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
            mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER));

    // Assert
    HttpHeaders headers2 = actualAdjustRequestRangeResult.getHeaders();
    assertTrue(headers2 instanceof DefaultHttpHeaders);
    assertTrue(actualAdjustRequestRangeResult.getNameResolver() instanceof DefaultNameResolver);
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    assertEquals("UTF-8", actualAdjustRequestRangeResult.getCharset().name());
    List<Param> queryParams = actualAdjustRequestRangeResult.getQueryParams();
    assertEquals(1, queryParams.size());
    Param getResult = queryParams.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", actualAdjustRequestRangeResult.getMethod());
    assertEquals("https://example.org/example", actualAdjustRequestRangeResult.getStringData());
    assertEquals("https://example.org/example", actualAdjustRequestRangeResult.getVirtualHost());
    File file = actualAdjustRequestRangeResult.getFile();
    assertEquals("test.txt", file.getName());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example?https:"
        + "//example.org/example", actualAdjustRequestRangeResult.getUrl());
    assertNull(getResult.getValue());
    Duration readTimeout = actualAdjustRequestRangeResult.getReadTimeout();
    assertEquals(0L, readTimeout.toNanos());
    assertEquals(1L, actualAdjustRequestRangeResult.getRangeOffset());
    InputStream streamData2 = actualAdjustRequestRangeResult.getStreamData();
    byte[] byteArray = new byte[8];
    assertEquals(8, streamData2.read(byteArray));
    assertTrue(file.isAbsolute());
    List<Part> bodyParts2 = actualAdjustRequestRangeResult.getBodyParts();
    assertTrue(bodyParts2.isEmpty());
    List<byte[]> compositeByteData2 = actualAdjustRequestRangeResult.getCompositeByteData();
    assertTrue(compositeByteData2.isEmpty());
    assertTrue(actualAdjustRequestRangeResult.getFollowRedirect());
    assertEquals(headers, headers2);
    assertEquals(uri, actualAdjustRequestRangeResult.getUri());
    assertSame(byteBufData, actualAdjustRequestRangeResult.getByteBufData());
    assertSame(streamData, streamData2);
    assertSame(compositeByteData, compositeByteData2);
    assertSame(proxyServer, actualAdjustRequestRangeResult.getProxyServer());
    assertSame(bodyParts2, actualAdjustRequestRangeResult.getCookies());
    assertSame(bodyParts2, actualAdjustRequestRangeResult.getFormParams());
    assertSame(readTimeout, actualAdjustRequestRangeResult.getRequestTimeout());
    assertSame(byteBufferData, actualAdjustRequestRangeResult.getByteBufferData());
    byte[] expectedByteData = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedByteData, actualAdjustRequestRangeResult.getByteData());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
  }

  /**
   * Method under test: {@link ResumableAsyncHandler#ResumableAsyncHandler()}
   */
  @Test
  void testNewResumableAsyncHandler() throws Exception {
    // Arrange, Act and Assert
    assertNull((new ResumableAsyncHandler()).onCompleted());
    assertNull((new ResumableAsyncHandler(1L)).onCompleted());
    assertNull((new ResumableAsyncHandler(1L, new AbstractBasicTest.AsyncCompletionHandlerAdapter())).onCompleted());
    assertNull((new ResumableAsyncHandler(new AbstractBasicTest.AsyncCompletionHandlerAdapter())).onCompleted());
    assertNull((new ResumableAsyncHandler(new PropertiesBasedResumableProcessor())).onCompleted());
    assertNull((new ResumableAsyncHandler((ResumableAsyncHandler.ResumableProcessor) null)).onCompleted());
    assertNull((new ResumableAsyncHandler(new PropertiesBasedResumableProcessor(), true)).onCompleted());
    assertNull((new ResumableAsyncHandler(null, true)).onCompleted());
    assertNull((new ResumableAsyncHandler(true)).onCompleted());
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#ResumableAsyncHandler(long, AsyncHandler)}
   */
  @Test
  void testNewResumableAsyncHandler2() throws Exception {
    // Arrange
    TransferCompletionHandler decoratedAsyncHandler = new TransferCompletionHandler();
    decoratedAsyncHandler.addTransferListener(mock(TransferListener.class));

    // Act and Assert
    assertNull((new ResumableAsyncHandler(1L, decoratedAsyncHandler)).onCompleted());
  }

  /**
   * Method under test:
   * {@link ResumableAsyncHandler#ResumableAsyncHandler(AsyncHandler)}
   */
  @Test
  void testNewResumableAsyncHandler3() throws Exception {
    // Arrange
    TransferCompletionHandler decoratedAsyncHandler = new TransferCompletionHandler();
    decoratedAsyncHandler.addTransferListener(mock(TransferListener.class));

    // Act and Assert
    assertNull((new ResumableAsyncHandler(decoratedAsyncHandler)).onCompleted());
  }
}
