package org.asynchttpclient.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TransferCompletionHandlerDiffblueTest {
  /**
   * Method under test:
   * {@link TransferCompletionHandler#addTransferListener(TransferListener)}
   */
  @Test
  void testAddTransferListener() {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertSame(transferCompletionHandler, transferCompletionHandler.addTransferListener(mock(TransferListener.class)));
  }

  /**
   * Method under test:
   * {@link TransferCompletionHandler#removeTransferListener(TransferListener)}
   */
  @Test
  void testRemoveTransferListener() {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertSame(transferCompletionHandler,
        transferCompletionHandler.removeTransferListener(mock(TransferListener.class)));
  }

  /**
   * Method under test:
   * {@link TransferCompletionHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnHeadersReceived() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, transferCompletionHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Method under test:
   * {@link TransferCompletionHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnHeadersReceived2() throws Exception {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onResponseHeadersReceived(Mockito.<HttpHeaders>any());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    transferCompletionHandler.addTransferListener(t);

    // Act
    AsyncHandler.State actualOnHeadersReceivedResult = transferCompletionHandler
        .onHeadersReceived(new DefaultHttpHeaders());

    // Assert
    verify(t).onResponseHeadersReceived(isA(HttpHeaders.class));
    assertEquals(AsyncHandler.State.CONTINUE, actualOnHeadersReceivedResult);
  }

  /**
   * Method under test:
   * {@link TransferCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnTrailingHeadersReceived() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        transferCompletionHandler.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Method under test:
   * {@link TransferCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnTrailingHeadersReceived2() throws Exception {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onResponseHeadersReceived(Mockito.<HttpHeaders>any());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    transferCompletionHandler.addTransferListener(t);

    // Act
    AsyncHandler.State actualOnTrailingHeadersReceivedResult = transferCompletionHandler
        .onTrailingHeadersReceived(new DefaultHttpHeaders());

    // Assert
    verify(t).onResponseHeadersReceived(isA(HttpHeaders.class));
    assertEquals(AsyncHandler.State.CONTINUE, actualOnTrailingHeadersReceivedResult);
  }

  /**
   * Method under test:
   * {@link TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, transferCompletionHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Method under test:
   * {@link TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived2() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler(true);

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, transferCompletionHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Method under test:
   * {@link TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived3() throws Exception {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onBytesReceived(Mockito.<byte[]>any());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    transferCompletionHandler.addTransferListener(t);

    // Act
    AsyncHandler.State actualOnBodyPartReceivedResult = transferCompletionHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));

    // Assert
    verify(t).onBytesReceived(isA(byte[].class));
    assertEquals(AsyncHandler.State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Method under test: {@link TransferCompletionHandler#onHeadersWritten()}
   */
  @Test
  void testOnHeadersWritten() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, (new TransferCompletionHandler()).onHeadersWritten());
  }

  /**
   * Method under test: {@link TransferCompletionHandler#onHeadersWritten()}
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
   * Method under test: {@link TransferCompletionHandler#onHeadersWritten()}
   */
  @Test
  void testOnHeadersWritten3() {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onRequestHeadersSent(Mockito.<HttpHeaders>any());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    transferCompletionHandler.headers(new DefaultHttpHeaders());
    transferCompletionHandler.addTransferListener(t);

    // Act
    AsyncHandler.State actualOnHeadersWrittenResult = transferCompletionHandler.onHeadersWritten();

    // Assert
    verify(t).onRequestHeadersSent(isA(HttpHeaders.class));
    assertEquals(AsyncHandler.State.CONTINUE, actualOnHeadersWrittenResult);
  }

  /**
   * Method under test:
   * {@link TransferCompletionHandler#onContentWriteProgress(long, long, long)}
   */
  @Test
  void testOnContentWriteProgress() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        (new TransferCompletionHandler()).onContentWriteProgress(10L, 81985529216486895L, 81985529216486895L));
  }

  /**
   * Method under test:
   * {@link TransferCompletionHandler#onContentWriteProgress(long, long, long)}
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

  /**
   * Method under test: {@link TransferCompletionHandler#onThrowable(Throwable)}
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
   * Method under test:
   * {@link TransferCompletionHandler#TransferCompletionHandler()}
   */
  @Test
  void testNewTransferCompletionHandler() throws Exception {
    // Arrange and Act
    TransferCompletionHandler actualTransferCompletionHandler = new TransferCompletionHandler();

    // Assert
    assertNull(actualTransferCompletionHandler.onCompleted());
    assertEquals(AsyncHandler.State.CONTINUE, actualTransferCompletionHandler.onContentWritten());
    assertEquals(AsyncHandler.State.CONTINUE, actualTransferCompletionHandler.onHeadersWritten());
  }

  /**
   * Method under test:
   * {@link TransferCompletionHandler#TransferCompletionHandler(boolean)}
   */
  @Test
  void testNewTransferCompletionHandler2() throws Exception {
    // Arrange and Act
    TransferCompletionHandler actualTransferCompletionHandler = new TransferCompletionHandler(true);

    // Assert
    assertNull(actualTransferCompletionHandler.onCompleted());
    assertEquals(AsyncHandler.State.CONTINUE, actualTransferCompletionHandler.onContentWritten());
    assertEquals(AsyncHandler.State.CONTINUE, actualTransferCompletionHandler.onHeadersWritten());
  }
}
