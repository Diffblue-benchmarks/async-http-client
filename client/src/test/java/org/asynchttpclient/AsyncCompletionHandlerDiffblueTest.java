package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import java.nio.ByteBuffer;
import org.asynchttpclient.handler.TransferCompletionHandler;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.NettyResponse;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AsyncCompletionHandlerDiffblueTest {
  /**
   * Test {@link AsyncCompletionHandler#onStatusReceived(HttpResponseStatus)}.
   * <p>
   * Method under test:
   * {@link AsyncCompletionHandler#onStatusReceived(HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  void testOnStatusReceived() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act
    AsyncHandler.State actualOnStatusReceivedResult = asyncCompletionHandlerBase
        .onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel()));

    // Assert
    Response onCompletedResult = asyncCompletionHandlerBase.onCompleted();
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
    assertEquals(AsyncHandler.State.CONTINUE, actualOnStatusReceivedResult);
    assertFalse(responseBodyAsByteBuffer.hasRemaining());
    assertFalse(headers.iterator().hasNext());
    assertFalse(onCompletedResult.hasResponseBody());
    assertFalse(onCompletedResult.hasResponseHeaders());
    assertFalse(onCompletedResult.isRedirected());
    assertTrue(headers.isEmpty());
    assertTrue(responseBodyAsByteBuffer.hasArray());
    assertTrue(onCompletedResult.getCookies().isEmpty());
    assertTrue(onCompletedResult.hasResponseStatus());
    assertSame(uri, onCompletedResult.getUri());
  }

  /**
   * Test {@link AsyncCompletionHandler#onHeadersReceived(HttpHeaders)}.
   * <p>
   * Method under test:
   * {@link AsyncCompletionHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onHeadersReceived(HttpHeaders)")
  void testOnHeadersReceived() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, asyncCompletionHandlerBase.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link AsyncCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   * <ul>
   *   <li>Then return {@code CONTINUE}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AsyncCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart); then return 'CONTINUE'")
  void testOnBodyPartReceived_thenReturnContinue() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, asyncCompletionHandlerBase.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Test {@link AsyncCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}.
   * <ul>
   *   <li>Given {@link AsyncCompletionHandlerBase} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AsyncCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onTrailingHeadersReceived(HttpHeaders); given AsyncCompletionHandlerBase (default constructor)")
  void testOnTrailingHeadersReceived_givenAsyncCompletionHandlerBase() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        asyncCompletionHandlerBase.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link AsyncCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}.
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AsyncCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onTrailingHeadersReceived(HttpHeaders); given TransferCompletionHandler()")
  void testOnTrailingHeadersReceived_givenTransferCompletionHandler() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        transferCompletionHandler.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link AsyncCompletionHandler#onCompleted()}.
   * <ul>
   *   <li>Given {@link AsyncCompletionHandlerBase} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncCompletionHandler#onCompleted()}
   */
  @Test
  @DisplayName("Test onCompleted(); given AsyncCompletionHandlerBase (default constructor); then return 'null'")
  void testOnCompleted_givenAsyncCompletionHandlerBase_thenReturnNull() throws Exception {
    // Arrange, Act and Assert
    assertNull((new AsyncCompletionHandlerBase()).onCompleted());
  }

  /**
   * Test {@link AsyncCompletionHandler#onHeadersWritten()}.
   * <p>
   * Method under test: {@link AsyncCompletionHandler#onHeadersWritten()}
   */
  @Test
  @DisplayName("Test onHeadersWritten()")
  void testOnHeadersWritten() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, (new AsyncCompletionHandlerBase()).onHeadersWritten());
  }

  /**
   * Test {@link AsyncCompletionHandler#onContentWritten()}.
   * <p>
   * Method under test: {@link AsyncCompletionHandler#onContentWritten()}
   */
  @Test
  @DisplayName("Test onContentWritten()")
  void testOnContentWritten() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, (new AsyncCompletionHandlerBase()).onContentWritten());
  }

  /**
   * Test {@link AsyncCompletionHandler#onContentWriteProgress(long, long, long)}.
   * <p>
   * Method under test:
   * {@link AsyncCompletionHandler#onContentWriteProgress(long, long, long)}
   */
  @Test
  @DisplayName("Test onContentWriteProgress(long, long, long)")
  void testOnContentWriteProgress() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        (new AsyncCompletionHandlerBase()).onContentWriteProgress(10L, 81985529216486895L, 81985529216486895L));
  }
}
