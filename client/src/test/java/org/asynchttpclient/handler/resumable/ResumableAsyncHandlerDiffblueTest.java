package org.asynchttpclient.handler.resumable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
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
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.NameResolver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import org.asynchttpclient.AbstractBasicTest;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.LazyResponseBodyPart;
import org.asynchttpclient.netty.NettyResponse;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ResumableAsyncHandlerDiffblueTest {
  /**
   * Test {@link ResumableAsyncHandler#ResumableAsyncHandler()}.
   * <p>
   * Method under test: {@link ResumableAsyncHandler#ResumableAsyncHandler()}
   */
  @Test
  @DisplayName("Test new ResumableAsyncHandler()")
  void testNewResumableAsyncHandler() throws Exception {
    // Arrange, Act and Assert
    assertNull((new ResumableAsyncHandler()).onCompleted());
    assertNull((new ResumableAsyncHandler(1L)).onCompleted());
    assertNull((new ResumableAsyncHandler(1L, new AbstractBasicTest.AsyncCompletionHandlerAdapter())).onCompleted());
    assertNull((new ResumableAsyncHandler(new AbstractBasicTest.AsyncCompletionHandlerAdapter())).onCompleted());
    assertNull((new ResumableAsyncHandler(true)).onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#ResumableAsyncHandler(ResumableProcessor)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#ResumableAsyncHandler(ResumableAsyncHandler.ResumableProcessor)}
   */
  @Test
  @DisplayName("Test new ResumableAsyncHandler(ResumableProcessor); when 'null'")
  void testNewResumableAsyncHandler_whenNull() throws Exception {
    // Arrange, Act and Assert
    assertNull((new ResumableAsyncHandler((ResumableAsyncHandler.ResumableProcessor) null)).onCompleted());
    assertNull((new ResumableAsyncHandler(null, true)).onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#ResumableAsyncHandler(ResumableProcessor)}.
   * <ul>
   *   <li>When {@link PropertiesBasedResumableProcessor} (default
   * constructor).</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#ResumableAsyncHandler(ResumableAsyncHandler.ResumableProcessor)}
   */
  @Test
  @DisplayName("Test new ResumableAsyncHandler(ResumableProcessor); when PropertiesBasedResumableProcessor (default constructor)")
  void testNewResumableAsyncHandler_whenPropertiesBasedResumableProcessor() throws Exception {
    // Arrange, Act and Assert
    assertNull((new ResumableAsyncHandler(new PropertiesBasedResumableProcessor())).onCompleted());
    assertNull((new ResumableAsyncHandler(new PropertiesBasedResumableProcessor(), true)).onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  void testOnStatusReceived() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(1));

    // Act
    AsyncHandler.State actualOnStatusReceivedResult = resumableAsyncHandler
        .onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel()));

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertEquals("Unknown Status (1)", onCompletedResult.getStatusText());
    assertEquals(1, onCompletedResult.getStatusCode());
    assertEquals(AsyncHandler.State.ABORT, actualOnStatusReceivedResult);
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  void testOnStatusReceived2() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(200));

    // Act
    resumableAsyncHandler.onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel()));

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertSame(uri, onCompletedResult.getUri());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  void testOnStatusReceived3() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(206));

    // Act
    resumableAsyncHandler.onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel()));

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertEquals("Partial Content", onCompletedResult.getStatusText());
    assertEquals(206, onCompletedResult.getStatusCode());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  void testOnStatusReceived4() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri("https://example.org/example", null, "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(200));

    // Act
    resumableAsyncHandler.onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel()));

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertSame(uri, onCompletedResult.getUri());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  void testOnStatusReceived5() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(200));

    // Act
    resumableAsyncHandler.onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel()));

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertSame(uri, onCompletedResult.getUri());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  void testOnStatusReceived6() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        null, "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(200));

    // Act
    resumableAsyncHandler.onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel()));

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertSame(uri, onCompletedResult.getUri());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  void testOnStatusReceived7() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", null, "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(200));

    // Act
    resumableAsyncHandler.onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel()));

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertSame(uri, onCompletedResult.getUri());
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart)")
  void testOnBodyPartReceived() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart)")
  void testOnBodyPartReceived2() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler
        .onBodyPartReceived(new LazyResponseBodyPart(new EmptyByteBuf(new AdaptiveByteBufAllocator()), true)));
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   * <ul>
   *   <li>Given {@link ResumableAsyncHandler#ResumableAsyncHandler(boolean)} with
   * accumulateBody is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart); given ResumableAsyncHandler(boolean) with accumulateBody is 'true'")
  void testOnBodyPartReceived_givenResumableAsyncHandlerWithAccumulateBodyIsTrue() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler(true);

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   * <ul>
   *   <li>When {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is
   * compositeBuffer three.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart); when DuplicatedByteBuf(ByteBuf) with buffer is compositeBuffer three")
  void testOnBodyPartReceived_whenDuplicatedByteBufWithBufferIsCompositeBufferThree() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler
        .onBodyPartReceived(new LazyResponseBodyPart(new DuplicatedByteBuf(Unpooled.compositeBuffer(3)), true)));
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   * <ul>
   *   <li>When {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is
   * compositeBuffer three.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart); when ReadOnlyByteBuf(ByteBuf) with buffer is compositeBuffer three")
  void testOnBodyPartReceived_whenReadOnlyByteBufWithBufferIsCompositeBufferThree() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler.onBodyPartReceived(
        new LazyResponseBodyPart(new DuplicatedByteBuf(new ReadOnlyByteBuf(Unpooled.compositeBuffer(3))), true)));
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   * <ul>
   *   <li>When {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is
   * {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart); when ReadOnlyByteBuf(ByteBuf) with buffer is DuplicatedByteBuf(ByteBuf)")
  void testOnBodyPartReceived_whenReadOnlyByteBufWithBufferIsDuplicatedByteBuf() throws Exception {
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
   * Test {@link ResumableAsyncHandler#onCompleted()}.
   * <ul>
   *   <li>Given {@link ResumableAsyncHandler#ResumableAsyncHandler()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResumableAsyncHandler#onCompleted()}
   */
  @Test
  @DisplayName("Test onCompleted(); given ResumableAsyncHandler(); then return 'null'")
  void testOnCompleted_givenResumableAsyncHandler_thenReturnNull() throws Exception {
    // Arrange, Act and Assert
    assertNull((new ResumableAsyncHandler()).onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#onCompleted()}.
   * <ul>
   *   <li>Then Headers return {@link EmptyHttpHeaders}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResumableAsyncHandler#onCompleted()}
   */
  @Test
  @DisplayName("Test onCompleted(); then Headers return EmptyHttpHeaders")
  void testOnCompleted_thenHeadersReturnEmptyHttpHeaders() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    resumableAsyncHandler.onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel()));

    // Act
    Response actualOnCompletedResult = resumableAsyncHandler.onCompleted();

    // Assert
    HttpHeaders headers = actualOnCompletedResult.getHeaders();
    assertTrue(headers instanceof EmptyHttpHeaders);
    assertTrue(actualOnCompletedResult instanceof NettyResponse);
    assertEquals("", actualOnCompletedResult.getResponseBody());
    assertEquals("Unknown Status (1)", actualOnCompletedResult.getStatusText());
    assertNull(actualOnCompletedResult.getContentType());
    assertEquals(-1, actualOnCompletedResult.getResponseBodyAsStream().read(new byte[]{}));
    assertEquals(0, headers.size());
    ByteBuffer responseBodyAsByteBuffer = actualOnCompletedResult.getResponseBodyAsByteBuffer();
    assertEquals(0, responseBodyAsByteBuffer.capacity());
    assertEquals(0, responseBodyAsByteBuffer.limit());
    assertEquals(0, responseBodyAsByteBuffer.position());
    assertEquals(0, responseBodyAsByteBuffer.array().length);
    assertEquals(0, actualOnCompletedResult.getResponseBodyAsBytes().length);
    assertEquals(1, actualOnCompletedResult.getStatusCode());
    assertFalse(responseBodyAsByteBuffer.hasRemaining());
    assertFalse(headers.iterator().hasNext());
    assertFalse(actualOnCompletedResult.hasResponseBody());
    assertFalse(actualOnCompletedResult.hasResponseHeaders());
    assertFalse(actualOnCompletedResult.isRedirected());
    assertTrue(headers.isEmpty());
    assertTrue(responseBodyAsByteBuffer.hasArray());
    assertTrue(actualOnCompletedResult.getCookies().isEmpty());
    assertTrue(actualOnCompletedResult.hasResponseStatus());
    assertSame(uri, actualOnCompletedResult.getUri());
  }

  /**
   * Test {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link EmptyHttpHeaders} {@link HttpHeaders#get(CharSequence)}
   * return {@code 42}.</li>
   *   <li>Then calls {@link HttpHeaders#get(CharSequence)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onHeadersReceived(HttpHeaders); given '42'; when EmptyHttpHeaders get(CharSequence) return '42'; then calls get(CharSequence)")
  void testOnHeadersReceived_given42_whenEmptyHttpHeadersGetReturn42_thenCallsGet() throws Exception {
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
   * Test {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}.
   * <ul>
   *   <li>Given {@link ResumableAsyncHandler#ResumableAsyncHandler(long)} with
   * byteTransferred is one.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onHeadersReceived(HttpHeaders); given ResumableAsyncHandler(long) with byteTransferred is one")
  void testOnHeadersReceived_givenResumableAsyncHandlerWithByteTransferredIsOne() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler(1L);

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}.
   * <ul>
   *   <li>When {@link DefaultHttpHeaders#DefaultHttpHeaders()}.</li>
   *   <li>Then return {@code CONTINUE}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onHeadersReceived(HttpHeaders); when DefaultHttpHeaders(); then return 'CONTINUE'")
  void testOnHeadersReceived_whenDefaultHttpHeaders_thenReturnContinue() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, resumableAsyncHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link ResumableAsyncHandler#onTrailingHeadersReceived(HttpHeaders)}.
   * <p>
   * Method under test:
   * {@link ResumableAsyncHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onTrailingHeadersReceived(HttpHeaders)")
  void testOnTrailingHeadersReceived() {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        resumableAsyncHandler.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   * <ul>
   *   <li>Given {@code http}.</li>
   *   <li>Then return Uri Scheme is {@code http}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); given 'http'; then return Uri Scheme is 'http'")
  void testAdjustRequestRange_givenHttp_thenReturnUriSchemeIsHttp()
      throws UnsupportedEncodingException, URISyntaxException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");

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
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    verify(uri).toUrl();
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    Uri uri2 = actualAdjustRequestRangeResult.getUri();
    assertEquals("http", uri2.getScheme());
    assertEquals("http://https://example.org/example:8080", uri2.getBaseUrl());
    assertEquals("http://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
        + "://example.org/example", uri2.toJavaNetURI().toString());
    assertEquals("http://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
        + "://example.org/example", actualAdjustRequestRangeResult.getUrl());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   * <ul>
   *   <li>Given {@code https}.</li>
   *   <li>Then return Uri Scheme is {@code https}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); given 'https'; then return Uri Scheme is 'https'")
  void testAdjustRequestRange_givenHttps_thenReturnUriSchemeIsHttps()
      throws UnsupportedEncodingException, URISyntaxException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("https");
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");

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
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    verify(uri).toUrl();
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    Uri uri2 = actualAdjustRequestRangeResult.getUri();
    assertEquals("https", uri2.getScheme());
    assertEquals("https://https://example.org/example:8080", uri2.getBaseUrl());
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
        + "://example.org/example", uri2.toJavaNetURI().toString());
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
        + "://example.org/example", actualAdjustRequestRangeResult.getUrl());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   * <ul>
   *   <li>Given {@code ws}.</li>
   *   <li>When {@link Uri} {@link Uri#getScheme()} return {@code ws}.</li>
   *   <li>Then return Uri Scheme is {@code ws}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); given 'ws'; when Uri getScheme() return 'ws'; then return Uri Scheme is 'ws'")
  void testAdjustRequestRange_givenWs_whenUriGetSchemeReturnWs_thenReturnUriSchemeIsWs()
      throws UnsupportedEncodingException, URISyntaxException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("ws");
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");

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
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    verify(uri).toUrl();
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    Uri uri2 = actualAdjustRequestRangeResult.getUri();
    assertEquals("ws", uri2.getScheme());
    assertEquals("ws://https://example.org/example:8080", uri2.getBaseUrl());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example?https:/"
        + "/example.org/example", uri2.toJavaNetURI().toString());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example?https:/"
        + "/example.org/example", actualAdjustRequestRangeResult.getUrl());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   * <ul>
   *   <li>Given {@code wss}.</li>
   *   <li>Then return Uri Scheme is {@code wss}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); given 'wss'; then return Uri Scheme is 'wss'")
  void testAdjustRequestRange_givenWss_thenReturnUriSchemeIsWss()
      throws UnsupportedEncodingException, URISyntaxException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("wss");
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");

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
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    verify(uri).toUrl();
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    Uri uri2 = actualAdjustRequestRangeResult.getUri();
    assertEquals("wss", uri2.getScheme());
    assertEquals("wss://https://example.org/example:8080", uri2.getBaseUrl());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example?https:"
        + "//example.org/example", uri2.toJavaNetURI().toString());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example?https:"
        + "//example.org/example", actualAdjustRequestRangeResult.getUrl());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   * <ul>
   *   <li>Then return Cookies is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); then return Cookies is ArrayList()")
  void testAdjustRequestRange_thenReturnCookiesIsArrayList() throws UnsupportedEncodingException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());

    ArrayList<Cookie> cookies = new ArrayList<>();
    cookies.add(new DefaultCookie("https://example.org/example", "https://example.org/example"));
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
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
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    verify(uri).toUrl();
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    assertEquals(cookies, actualAdjustRequestRangeResult.getCookies());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   * <ul>
   *   <li>When {@link HttpHeaders} {@link HttpHeaders#get(CharSequence)} return
   * {@code null}.</li>
   *   <li>Then return Uri Scheme is {@code http}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); when HttpHeaders get(CharSequence) return 'null'; then return Uri Scheme is 'http'")
  void testAdjustRequestRange_whenHttpHeadersGetReturnNull_thenReturnUriSchemeIsHttp()
      throws UnsupportedEncodingException, URISyntaxException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("https://example.org/example");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn(null);

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
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    verify(uri).toUrl();
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    Uri uri2 = actualAdjustRequestRangeResult.getUri();
    assertEquals("http", uri2.getScheme());
    assertEquals("http://https://example.org/example:8080", uri2.getBaseUrl());
    assertEquals("http://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
        + "://example.org/example", uri2.toJavaNetURI().toString());
    assertEquals("http://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
        + "://example.org/example", actualAdjustRequestRangeResult.getUrl());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   * <ul>
   *   <li>When {@link Uri} {@link Uri#getQuery()} return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); when Uri getQuery() return empty string")
  void testAdjustRequestRange_whenUriGetQueryReturnEmptyString()
      throws UnsupportedEncodingException, URISyntaxException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn("");
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");

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
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    verify(uri).toUrl();
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    Uri uri2 = actualAdjustRequestRangeResult.getUri();
    assertEquals("http://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri2.toJavaNetURI().toString());
    assertEquals("http://https://example.org/example@https://example.org/example:8080https://example.org/example",
        actualAdjustRequestRangeResult.getUrl());
    assertNull(uri2.getQuery());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   * <ul>
   *   <li>When {@link Uri} {@link Uri#getQuery()} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); when Uri getQuery() return 'null'")
  void testAdjustRequestRange_whenUriGetQueryReturnNull() throws UnsupportedEncodingException, URISyntaxException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = mock(Uri.class);
    when(uri.getPort()).thenReturn(8080);
    when(uri.getFragment()).thenReturn("https://example.org/example");
    when(uri.getHost()).thenReturn("https://example.org/example");
    when(uri.getQuery()).thenReturn(null);
    when(uri.getUserInfo()).thenReturn("https://example.org/example");
    when(uri.getPath()).thenReturn("https://example.org/example");
    when(uri.getScheme()).thenReturn("http");
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");

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
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(
        new DefaultRequest("https://example.org/example", uri, address, localAddress, headers, cookies, byteData,
            compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData, bodyGenerator,
            formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true, null, null, 1L,
            Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class)));

    // Assert
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getPort();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    verify(uri).toUrl();
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    Uri uri2 = actualAdjustRequestRangeResult.getUri();
    assertEquals("http://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri2.toJavaNetURI().toString());
    assertEquals("http://https://example.org/example@https://example.org/example:8080https://example.org/example",
        actualAdjustRequestRangeResult.getUrl());
    assertNull(uri2.getQuery());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   * <ul>
   *   <li>When {@link Uri} {@link Uri#getScheme()} return
   * {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); when Uri getScheme() return 'https://example.org/example'")
  void testAdjustRequestRange_whenUriGetSchemeReturnHttpsExampleOrgExample() throws UnsupportedEncodingException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");

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

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> resumableAsyncHandler.adjustRequestRange(new DefaultRequest("https://example.org/example", uri, address,
            localAddress, headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData,
            byteBufData, streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer,
            realm, file, true, null, null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class))));
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getScheme();
    verify(uri).toUrl();
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   * <ul>
   *   <li>When {@link Uri} {@link Uri#getScheme()} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); when Uri getScheme() return 'null'")
  void testAdjustRequestRange_whenUriGetSchemeReturnNull() throws UnsupportedEncodingException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn(null);
    when(uri.toUrl()).thenReturn("https://example.org/example");
    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");

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

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> resumableAsyncHandler.adjustRequestRange(new DefaultRequest("https://example.org/example", uri, address,
            localAddress, headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData,
            byteBufData, streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer,
            realm, file, true, null, null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class))));
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getScheme();
    verify(uri).toUrl();
  }
}
