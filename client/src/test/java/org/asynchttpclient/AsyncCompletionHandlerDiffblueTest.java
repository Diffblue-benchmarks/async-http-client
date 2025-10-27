package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
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
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import java.nio.ByteBuffer;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.handler.TransferCompletionHandler;
import org.asynchttpclient.handler.TransferListener;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.NettyResponse;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AsyncCompletionHandlerDiffblueTest {
  /**
   * Method under test:
   * {@link AsyncCompletionHandler#onStatusReceived(HttpResponseStatus)}
   */
  @Test
  void testOnStatusReceived() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();
    Uri uri = mock(Uri.class);
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
  }

  /**
   * Method under test:
   * {@link AsyncCompletionHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnHeadersReceived() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, asyncCompletionHandlerBase.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Method under test:
   * {@link AsyncCompletionHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnHeadersReceived2() throws Exception {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        (new AsyncCompletionHandlerBase()).onHeadersReceived(mock(EmptyHttpHeaders.class)));
  }

  /**
   * Method under test:
   * {@link AsyncCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, asyncCompletionHandlerBase.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Method under test:
   * {@link AsyncCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived2() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    AsyncHandler.State actualOnBodyPartReceivedResult = asyncCompletionHandlerBase.onBodyPartReceived(
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
   * {@link AsyncCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived3() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(0);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    AsyncHandler.State actualOnBodyPartReceivedResult = asyncCompletionHandlerBase.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(0), isA(byte[].class), eq(0), eq(1));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(AsyncHandler.State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Method under test:
   * {@link AsyncCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnTrailingHeadersReceived() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        asyncCompletionHandlerBase.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Method under test:
   * {@link AsyncCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnTrailingHeadersReceived2() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        transferCompletionHandler.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Method under test:
   * {@link AsyncCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnTrailingHeadersReceived3() throws Exception {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        (new AsyncCompletionHandlerBase()).onTrailingHeadersReceived(mock(EmptyHttpHeaders.class)));
  }

  /**
   * Method under test: {@link AsyncCompletionHandler#onCompleted()}
   */
  @Test
  void testOnCompleted() throws Exception {
    // Arrange, Act and Assert
    assertNull((new AsyncCompletionHandlerBase()).onCompleted());
  }

  /**
   * Method under test: {@link AsyncCompletionHandler#onCompleted()}
   */
  @Test
  void testOnCompleted2() throws Exception {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onRequestResponseCompleted();

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    transferCompletionHandler.addTransferListener(t);

    // Act
    Response actualOnCompletedResult = transferCompletionHandler.onCompleted();

    // Assert
    verify(t).onRequestResponseCompleted();
    assertNull(actualOnCompletedResult);
  }

  /**
   * Method under test: {@link AsyncCompletionHandler#onThrowable(Throwable)}
   */
  @Test
  void testOnThrowable() {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onThrowable(Mockito.<Throwable>any());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    transferCompletionHandler.addTransferListener(t);

    // Act
    transferCompletionHandler.onThrowable(ChannelClosedException.INSTANCE);

    // Assert
    verify(t).onThrowable(isA(Throwable.class));
  }

  /**
   * Method under test: {@link AsyncCompletionHandler#onHeadersWritten()}
   */
  @Test
  void testOnHeadersWritten() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, (new AsyncCompletionHandlerBase()).onHeadersWritten());
  }

  /**
   * Method under test: {@link AsyncCompletionHandler#onHeadersWritten()}
   */
  @Test
  void testOnHeadersWritten2() {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    transferCompletionHandler.addTransferListener(mock(TransferListener.class));

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, transferCompletionHandler.onHeadersWritten());
  }

  /**
   * Method under test: {@link AsyncCompletionHandler#onContentWritten()}
   */
  @Test
  void testOnContentWritten() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, (new AsyncCompletionHandlerBase()).onContentWritten());
  }

  /**
   * Method under test: {@link AsyncCompletionHandler#onContentWritten()}
   */
  @Test
  void testOnContentWritten2() {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    transferCompletionHandler.addTransferListener(mock(TransferListener.class));

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, transferCompletionHandler.onContentWritten());
  }

  /**
   * Method under test:
   * {@link AsyncCompletionHandler#onContentWriteProgress(long, long, long)}
   */
  @Test
  void testOnContentWriteProgress() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        (new AsyncCompletionHandlerBase()).onContentWriteProgress(10L, 81985529216486895L, 81985529216486895L));
  }

  /**
   * Method under test:
   * {@link AsyncCompletionHandler#onContentWriteProgress(long, long, long)}
   */
  @Test
  void testOnContentWriteProgress2() {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onBytesSent(anyLong(), anyLong(), anyLong());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    transferCompletionHandler.addTransferListener(t);

    // Act
    AsyncHandler.State actualOnContentWriteProgressResult = transferCompletionHandler.onContentWriteProgress(10L,
        81985529216486895L, 81985529216486895L);

    // Assert
    verify(t).onBytesSent(eq(10L), eq(81985529216486895L), eq(81985529216486895L));
    assertEquals(AsyncHandler.State.CONTINUE, actualOnContentWriteProgressResult);
  }
}
