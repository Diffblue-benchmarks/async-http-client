package org.asynchttpclient.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpVersion;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.Response;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.handler.BodyDeferringAsyncHandler.BodyDeferringInputStream;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BodyDeferringAsyncHandlerDiffblueTest {
  /**
   * Test BodyDeferringInputStream
   * {@link BodyDeferringInputStream#BodyDeferringInputStream(Future, BodyDeferringAsyncHandler, InputStream)}.
   * <p>
   * Method under test:
   * {@link BodyDeferringAsyncHandler.BodyDeferringInputStream#BodyDeferringInputStream(Future, BodyDeferringAsyncHandler, InputStream)}
   */
  @Test
  @DisplayName("Test BodyDeferringInputStream new BodyDeferringInputStream(Future, BodyDeferringAsyncHandler, InputStream)")
  void testBodyDeferringInputStreamNewBodyDeferringInputStream() throws IOException {
    // Arrange
    CompletableFuture<Response> future = new CompletableFuture<>();
    BodyDeferringAsyncHandler bdah = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));

    // Act and Assert
    assertEquals(8, (new BodyDeferringAsyncHandler.BodyDeferringInputStream(future, bdah,
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))).read(new byte[8]));
  }

  /**
   * Test
   * {@link BodyDeferringAsyncHandler#BodyDeferringAsyncHandler(OutputStream)}.
   * <p>
   * Method under test:
   * {@link BodyDeferringAsyncHandler#BodyDeferringAsyncHandler(OutputStream)}
   */
  @Test
  @DisplayName("Test new BodyDeferringAsyncHandler(OutputStream)")
  void testNewBodyDeferringAsyncHandler() {
    // Arrange and Act
    BodyDeferringAsyncHandler actualBodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(
        new ByteArrayOutputStream(1));

    // Assert
    assertEquals(AsyncHandler.State.CONTINUE, actualBodyDeferringAsyncHandler.onHeadersReceived(null));
    assertEquals(AsyncHandler.State.CONTINUE, actualBodyDeferringAsyncHandler.onStatusReceived(null));
    assertEquals(AsyncHandler.State.CONTINUE, actualBodyDeferringAsyncHandler.onTrailingHeadersReceived(null));
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   * <ul>
   *   <li>Then return {@code CONTINUE}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link BodyDeferringAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus); then return 'CONTINUE'")
  void testOnStatusReceived_thenReturnContinue() {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        bodyDeferringAsyncHandler.onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel())));
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onHeadersReceived(HttpHeaders)}.
   * <p>
   * Method under test:
   * {@link BodyDeferringAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onHeadersReceived(HttpHeaders)")
  void testOnHeadersReceived() {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, bodyDeferringAsyncHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test
   * {@link BodyDeferringAsyncHandler#onTrailingHeadersReceived(HttpHeaders)}.
   * <p>
   * Method under test:
   * {@link BodyDeferringAsyncHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onTrailingHeadersReceived(HttpHeaders)")
  void testOnTrailingHeadersReceived() {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        bodyDeferringAsyncHandler.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onRetry()}.
   * <p>
   * Method under test: {@link BodyDeferringAsyncHandler#onRetry()}
   */
  @Test
  @DisplayName("Test onRetry()")
  void testOnRetry() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> (new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1))).onRetry());
  }

  /**
   * Test
   * {@link BodyDeferringAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   * <ul>
   *   <li>Given {@link ByteArrayOutputStream#ByteArrayOutputStream(int)} with
   * one.</li>
   *   <li>Then return {@code CONTINUE}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link BodyDeferringAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart); given ByteArrayOutputStream(int) with one; then return 'CONTINUE'")
  void testOnBodyPartReceived_givenByteArrayOutputStreamWithOne_thenReturnContinue() throws Exception {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, bodyDeferringAsyncHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onCompleted()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyDeferringAsyncHandler#onCompleted()}
   */
  @Test
  @DisplayName("Test onCompleted(); then return 'null'")
  void testOnCompleted_thenReturnNull() throws IOException {
    // Arrange, Act and Assert
    assertNull((new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1))).onCompleted());
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onCompleted()}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyDeferringAsyncHandler#onCompleted()}
   */
  @Test
  @DisplayName("Test onCompleted(); then throw IOException")
  void testOnCompleted_thenThrowIOException() throws IOException {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));
    bodyDeferringAsyncHandler.onThrowable(ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(IOException.class, () -> bodyDeferringAsyncHandler.onCompleted());
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#getResponse()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyDeferringAsyncHandler#getResponse()}
   */
  @Test
  @DisplayName("Test getResponse(); then return 'null'")
  void testGetResponse_thenReturnNull() throws IOException, InterruptedException {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));
    bodyDeferringAsyncHandler.onThrowable(null);

    // Act and Assert
    assertNull(bodyDeferringAsyncHandler.getResponse());
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#getResponse()}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyDeferringAsyncHandler#getResponse()}
   */
  @Test
  @DisplayName("Test getResponse(); then throw IOException")
  void testGetResponse_thenThrowIOException() throws IOException, InterruptedException {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));
    bodyDeferringAsyncHandler.onThrowable(ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(IOException.class, () -> bodyDeferringAsyncHandler.getResponse());
  }
}
