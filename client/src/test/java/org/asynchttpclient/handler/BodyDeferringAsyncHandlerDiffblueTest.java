package org.asynchttpclient.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
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
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BodyDeferringAsyncHandlerDiffblueTest {
  /**
   * Method under test:
   * {@link BodyDeferringAsyncHandler.BodyDeferringInputStream#BodyDeferringInputStream(Future, BodyDeferringAsyncHandler, InputStream)}
   */
  @Test
  void testBodyDeferringInputStreamNewBodyDeferringInputStream() throws IOException {
    // Arrange
    CompletableFuture<Response> future = new CompletableFuture<>();
    BodyDeferringAsyncHandler bdah = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));

    // Act and Assert
    assertEquals(8, (new BodyDeferringAsyncHandler.BodyDeferringInputStream(future, bdah,
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))).read(new byte[8]));
  }

  /**
   * Method under test: {@link BodyDeferringAsyncHandler#onThrowable(Throwable)}
   */
  @Test
  void testOnThrowable() throws IOException {
    // Arrange
    BodyDeferringAsyncHandlerTest.CountingOutputStream os = mock(
        BodyDeferringAsyncHandlerTest.CountingOutputStream.class);
    doNothing().when(os).close();
    doNothing().when(os).flush();

    // Act
    (new BodyDeferringAsyncHandler(os)).onThrowable(ChannelClosedException.INSTANCE);

    // Assert
    verify(os).close();
    verify(os).flush();
  }

  /**
   * Method under test:
   * {@link BodyDeferringAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  void testOnStatusReceived() {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        bodyDeferringAsyncHandler.onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel())));
  }

  /**
   * Method under test:
   * {@link BodyDeferringAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnHeadersReceived() {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, bodyDeferringAsyncHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Method under test:
   * {@link BodyDeferringAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnHeadersReceived2() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        (new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1))).onHeadersReceived(mock(EmptyHttpHeaders.class)));
  }

  /**
   * Method under test:
   * {@link BodyDeferringAsyncHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnTrailingHeadersReceived() {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        bodyDeferringAsyncHandler.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Method under test:
   * {@link BodyDeferringAsyncHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnTrailingHeadersReceived2() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, (new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1)))
        .onTrailingHeadersReceived(mock(EmptyHttpHeaders.class)));
  }

  /**
   * Method under test: {@link BodyDeferringAsyncHandler#onRetry()}
   */
  @Test
  void testOnRetry() {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> (new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1))).onRetry());
  }

  /**
   * Method under test:
   * {@link BodyDeferringAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived() throws Exception {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, bodyDeferringAsyncHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Method under test:
   * {@link BodyDeferringAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived2() throws Exception {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    AsyncHandler.State actualOnBodyPartReceivedResult = bodyDeferringAsyncHandler.onBodyPartReceived(
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
   * Method under test: {@link BodyDeferringAsyncHandler#closeOut()}
   */
  @Test
  void testCloseOut() throws IOException {
    // Arrange
    BodyDeferringAsyncHandlerTest.CountingOutputStream os = mock(
        BodyDeferringAsyncHandlerTest.CountingOutputStream.class);
    doNothing().when(os).close();
    doNothing().when(os).flush();

    // Act
    (new BodyDeferringAsyncHandler(os)).closeOut();

    // Assert
    verify(os).close();
    verify(os).flush();
  }

  /**
   * Method under test: {@link BodyDeferringAsyncHandler#onCompleted()}
   */
  @Test
  void testOnCompleted() throws IOException {
    // Arrange, Act and Assert
    assertNull((new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1))).onCompleted());
  }

  /**
   * Method under test: {@link BodyDeferringAsyncHandler#onCompleted()}
   */
  @Test
  void testOnCompleted2() throws IOException {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));
    bodyDeferringAsyncHandler.onThrowable(ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(IOException.class, () -> bodyDeferringAsyncHandler.onCompleted());
  }

  /**
   * Method under test: {@link BodyDeferringAsyncHandler#getResponse()}
   */
  @Test
  void testGetResponse() throws IOException, InterruptedException {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));
    bodyDeferringAsyncHandler.onThrowable(null);

    // Act and Assert
    assertNull(bodyDeferringAsyncHandler.getResponse());
  }

  /**
   * Method under test: {@link BodyDeferringAsyncHandler#getResponse()}
   */
  @Test
  void testGetResponse2() throws IOException, InterruptedException {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(new ByteArrayOutputStream(1));
    bodyDeferringAsyncHandler.onThrowable(ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(IOException.class, () -> bodyDeferringAsyncHandler.getResponse());
  }

  /**
   * Method under test:
   * {@link BodyDeferringAsyncHandler#BodyDeferringAsyncHandler(OutputStream)}
   */
  @Test
  void testNewBodyDeferringAsyncHandler() {
    // Arrange and Act
    BodyDeferringAsyncHandler actualBodyDeferringAsyncHandler = new BodyDeferringAsyncHandler(
        new ByteArrayOutputStream(1));

    // Assert
    assertEquals(AsyncHandler.State.CONTINUE, actualBodyDeferringAsyncHandler.onHeadersReceived(null));
    assertEquals(AsyncHandler.State.CONTINUE, actualBodyDeferringAsyncHandler.onStatusReceived(null));
    assertEquals(AsyncHandler.State.CONTINUE, actualBodyDeferringAsyncHandler.onTrailingHeadersReceived(null));
  }
}
