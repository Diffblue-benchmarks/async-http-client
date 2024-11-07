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
import org.asynchttpclient.Response;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TransferCompletionHandlerDiffblueTest {
  /**
   * Test {@link TransferCompletionHandler#TransferCompletionHandler()}.
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#TransferCompletionHandler()}
   */
  @Test
  @DisplayName("Test new TransferCompletionHandler()")
  void testNewTransferCompletionHandler() throws Exception {
    // Arrange and Act
    TransferCompletionHandler actualTransferCompletionHandler = new TransferCompletionHandler();

    // Assert
    assertNull(actualTransferCompletionHandler.onCompleted());
    assertEquals(AsyncHandler.State.CONTINUE, actualTransferCompletionHandler.onContentWritten());
    assertEquals(AsyncHandler.State.CONTINUE, actualTransferCompletionHandler.onHeadersWritten());
  }

  /**
   * Test {@link TransferCompletionHandler#TransferCompletionHandler(boolean)}.
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#TransferCompletionHandler(boolean)}
   */
  @Test
  @DisplayName("Test new TransferCompletionHandler(boolean)")
  void testNewTransferCompletionHandler2() throws Exception {
    // Arrange and Act
    TransferCompletionHandler actualTransferCompletionHandler = new TransferCompletionHandler(true);

    // Assert
    assertNull(actualTransferCompletionHandler.onCompleted());
    assertEquals(AsyncHandler.State.CONTINUE, actualTransferCompletionHandler.onContentWritten());
    assertEquals(AsyncHandler.State.CONTINUE, actualTransferCompletionHandler.onHeadersWritten());
  }

  /**
   * Test {@link TransferCompletionHandler#addTransferListener(TransferListener)}.
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#addTransferListener(TransferListener)}
   */
  @Test
  @DisplayName("Test addTransferListener(TransferListener)")
  void testAddTransferListener() {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertSame(transferCompletionHandler, transferCompletionHandler.addTransferListener(mock(TransferListener.class)));
  }

  /**
   * Test
   * {@link TransferCompletionHandler#removeTransferListener(TransferListener)}.
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#removeTransferListener(TransferListener)}
   */
  @Test
  @DisplayName("Test removeTransferListener(TransferListener)")
  void testRemoveTransferListener() {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertSame(transferCompletionHandler,
        transferCompletionHandler.removeTransferListener(mock(TransferListener.class)));
  }

  /**
   * Test {@link TransferCompletionHandler#onHeadersReceived(HttpHeaders)}.
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onHeadersReceived(HttpHeaders); given TransferCompletionHandler()")
  void testOnHeadersReceived_givenTransferCompletionHandler() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, transferCompletionHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link TransferCompletionHandler#onHeadersReceived(HttpHeaders)}.
   * <ul>
   *   <li>Then calls
   * {@link TransferListener#onResponseHeadersReceived(HttpHeaders)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onHeadersReceived(HttpHeaders); then calls onResponseHeadersReceived(HttpHeaders)")
  void testOnHeadersReceived_thenCallsOnResponseHeadersReceived() throws Exception {
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
   * Test
   * {@link TransferCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}.
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}
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
   * Test
   * {@link TransferCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}.
   * <ul>
   *   <li>Then calls
   * {@link TransferListener#onResponseHeadersReceived(HttpHeaders)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onTrailingHeadersReceived(HttpHeaders); then calls onResponseHeadersReceived(HttpHeaders)")
  void testOnTrailingHeadersReceived_thenCallsOnResponseHeadersReceived() throws Exception {
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
   * Test
   * {@link TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart)")
  void testOnBodyPartReceived() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler(true);

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, transferCompletionHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Test
   * {@link TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.</li>
   *   <li>Then return {@code CONTINUE}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart); given TransferCompletionHandler(); then return 'CONTINUE'")
  void testOnBodyPartReceived_givenTransferCompletionHandler_thenReturnContinue() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, transferCompletionHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Test
   * {@link TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   * <ul>
   *   <li>Then calls {@link TransferListener#onBytesReceived(byte[])}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart); then calls onBytesReceived(byte[])")
  void testOnBodyPartReceived_thenCallsOnBytesReceived() throws Exception {
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
   * Test {@link TransferCompletionHandler#onCompleted(Response)} with
   * {@code Response}.
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransferCompletionHandler#onCompleted(Response)}
   */
  @Test
  @DisplayName("Test onCompleted(Response) with 'Response'; given TransferCompletionHandler(); then return 'null'")
  void testOnCompletedWithResponse_givenTransferCompletionHandler_thenReturnNull() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    Response response = (new Response.ResponseBuilder()).build();

    // Act and Assert
    assertNull(transferCompletionHandler.onCompleted(response));
  }

  /**
   * Test {@link TransferCompletionHandler#onCompleted(Response)} with
   * {@code Response}.
   * <ul>
   *   <li>Then calls {@link TransferListener#onRequestResponseCompleted()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransferCompletionHandler#onCompleted(Response)}
   */
  @Test
  @DisplayName("Test onCompleted(Response) with 'Response'; then calls onRequestResponseCompleted()")
  void testOnCompletedWithResponse_thenCallsOnRequestResponseCompleted() throws Exception {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onRequestResponseCompleted();

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    transferCompletionHandler.addTransferListener(t);
    Response response = (new Response.ResponseBuilder()).build();

    // Act
    Response actualOnCompletedResult = transferCompletionHandler.onCompleted(response);

    // Assert
    verify(t).onRequestResponseCompleted();
    assertNull(actualOnCompletedResult);
  }

  /**
   * Test {@link TransferCompletionHandler#onHeadersWritten()}.
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransferCompletionHandler#onHeadersWritten()}
   */
  @Test
  @DisplayName("Test onHeadersWritten(); given TransferCompletionHandler()")
  void testOnHeadersWritten_givenTransferCompletionHandler() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, (new TransferCompletionHandler()).onHeadersWritten());
  }

  /**
   * Test {@link TransferCompletionHandler#onHeadersWritten()}.
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}
   * headers {@link DefaultHttpHeaders#DefaultHttpHeaders()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransferCompletionHandler#onHeadersWritten()}
   */
  @Test
  @DisplayName("Test onHeadersWritten(); given TransferCompletionHandler() headers DefaultHttpHeaders()")
  void testOnHeadersWritten_givenTransferCompletionHandlerHeadersDefaultHttpHeaders() {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    transferCompletionHandler.headers(new DefaultHttpHeaders());

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, transferCompletionHandler.onHeadersWritten());
  }

  /**
   * Test {@link TransferCompletionHandler#onHeadersWritten()}.
   * <ul>
   *   <li>Then calls
   * {@link TransferListener#onRequestHeadersSent(HttpHeaders)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransferCompletionHandler#onHeadersWritten()}
   */
  @Test
  @DisplayName("Test onHeadersWritten(); then calls onRequestHeadersSent(HttpHeaders)")
  void testOnHeadersWritten_thenCallsOnRequestHeadersSent() {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onRequestHeadersSent(Mockito.<HttpHeaders>any());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    transferCompletionHandler.addTransferListener(t);
    transferCompletionHandler.headers(new DefaultHttpHeaders());

    // Act
    AsyncHandler.State actualOnHeadersWrittenResult = transferCompletionHandler.onHeadersWritten();

    // Assert
    verify(t).onRequestHeadersSent(isA(HttpHeaders.class));
    assertEquals(AsyncHandler.State.CONTINUE, actualOnHeadersWrittenResult);
  }

  /**
   * Test
   * {@link TransferCompletionHandler#onContentWriteProgress(long, long, long)}.
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#onContentWriteProgress(long, long, long)}
   */
  @Test
  @DisplayName("Test onContentWriteProgress(long, long, long); given TransferCompletionHandler()")
  void testOnContentWriteProgress_givenTransferCompletionHandler() {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        (new TransferCompletionHandler()).onContentWriteProgress(10L, 81985529216486895L, 81985529216486895L));
  }

  /**
   * Test
   * {@link TransferCompletionHandler#onContentWriteProgress(long, long, long)}.
   * <ul>
   *   <li>Then calls {@link TransferListener#onBytesSent(long, long, long)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link TransferCompletionHandler#onContentWriteProgress(long, long, long)}
   */
  @Test
  @DisplayName("Test onContentWriteProgress(long, long, long); then calls onBytesSent(long, long, long)")
  void testOnContentWriteProgress_thenCallsOnBytesSent() {
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
   * Test {@link TransferCompletionHandler#onThrowable(Throwable)}.
   * <ul>
   *   <li>Given {@link TransferListener}
   * {@link TransferListener#onThrowable(Throwable)} does nothing.</li>
   *   <li>Then calls {@link TransferListener#onThrowable(Throwable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransferCompletionHandler#onThrowable(Throwable)}
   */
  @Test
  @DisplayName("Test onThrowable(Throwable); given TransferListener onThrowable(Throwable) does nothing; then calls onThrowable(Throwable)")
  void testOnThrowable_givenTransferListenerOnThrowableDoesNothing_thenCallsOnThrowable() {
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
}
