package org.asynchttpclient.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.AsyncHandler.State;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.Response;
import org.asynchttpclient.Response.ResponseBuilder;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TransferCompletionHandlerDiffblueTest {
  /**
   * Test {@link TransferCompletionHandler#TransferCompletionHandler()}.
   *
   * <p>Method under test: {@link TransferCompletionHandler#TransferCompletionHandler()}
   */
  @Test
  @DisplayName("Test new TransferCompletionHandler()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TransferCompletionHandler.<init>()"})
  void testNewTransferCompletionHandler() throws Exception {
    // Arrange and Act
    TransferCompletionHandler actualTransferCompletionHandler = new TransferCompletionHandler();

    // Assert
    assertNull(actualTransferCompletionHandler.onCompleted());
    assertEquals(State.CONTINUE, actualTransferCompletionHandler.onContentWritten());
    assertEquals(State.CONTINUE, actualTransferCompletionHandler.onHeadersWritten());
  }

  /**
   * Test {@link TransferCompletionHandler#TransferCompletionHandler(boolean)}.
   *
   * <p>Method under test: {@link TransferCompletionHandler#TransferCompletionHandler(boolean)}
   */
  @Test
  @DisplayName("Test new TransferCompletionHandler(boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TransferCompletionHandler.<init>(boolean)"})
  void testNewTransferCompletionHandler2() throws Exception {
    // Arrange and Act
    TransferCompletionHandler actualTransferCompletionHandler = new TransferCompletionHandler(true);

    // Assert
    assertNull(actualTransferCompletionHandler.onCompleted());
    assertEquals(State.CONTINUE, actualTransferCompletionHandler.onContentWritten());
    assertEquals(State.CONTINUE, actualTransferCompletionHandler.onHeadersWritten());
  }

  /**
   * Test {@link TransferCompletionHandler#addTransferListener(TransferListener)}.
   *
   * <p>Method under test: {@link TransferCompletionHandler#addTransferListener(TransferListener)}
   */
  @Test
  @DisplayName("Test addTransferListener(TransferListener)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TransferCompletionHandler TransferCompletionHandler.addTransferListener(TransferListener)"
  })
  void testAddTransferListener() {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act
    TransferCompletionHandler actualAddTransferListenerResult =
        transferCompletionHandler.addTransferListener(mock(TransferListener.class));

    // Assert
    assertSame(transferCompletionHandler, actualAddTransferListenerResult);
  }

  /**
   * Test {@link TransferCompletionHandler#removeTransferListener(TransferListener)}.
   *
   * <p>Method under test: {@link
   * TransferCompletionHandler#removeTransferListener(TransferListener)}
   */
  @Test
  @DisplayName("Test removeTransferListener(TransferListener)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TransferCompletionHandler TransferCompletionHandler.removeTransferListener(TransferListener)"
  })
  void testRemoveTransferListener() {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act
    TransferCompletionHandler actualRemoveTransferListenerResult =
        transferCompletionHandler.removeTransferListener(mock(TransferListener.class));

    // Assert
    assertSame(transferCompletionHandler, actualRemoveTransferListenerResult);
  }

  /**
   * Test {@link TransferCompletionHandler#onHeadersReceived(HttpHeaders)}.
   *
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.
   * </ul>
   *
   * <p>Method under test: {@link TransferCompletionHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onHeadersReceived(HttpHeaders); given TransferCompletionHandler()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AsyncHandler.State TransferCompletionHandler.onHeadersReceived(HttpHeaders)"})
  void testOnHeadersReceived_givenTransferCompletionHandler() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertEquals(
        State.CONTINUE, transferCompletionHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link TransferCompletionHandler#onHeadersReceived(HttpHeaders)}.
   *
   * <ul>
   *   <li>Then calls {@link TransferListener#onResponseHeadersReceived(HttpHeaders)}.
   * </ul>
   *
   * <p>Method under test: {@link TransferCompletionHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName(
      "Test onHeadersReceived(HttpHeaders); then calls onResponseHeadersReceived(HttpHeaders)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AsyncHandler.State TransferCompletionHandler.onHeadersReceived(HttpHeaders)"})
  void testOnHeadersReceived_thenCallsOnResponseHeadersReceived() throws Exception {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onResponseHeadersReceived(Mockito.<HttpHeaders>any());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler(true);
    transferCompletionHandler.addTransferListener(t);

    // Act
    State actualOnHeadersReceivedResult =
        transferCompletionHandler.onHeadersReceived(new DefaultHttpHeaders());

    // Assert
    verify(t).onResponseHeadersReceived(isA(HttpHeaders.class));
    assertEquals(State.CONTINUE, actualOnHeadersReceivedResult);
  }

  /**
   * Test {@link TransferCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}.
   *
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.
   * </ul>
   *
   * <p>Method under test: {@link TransferCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onTrailingHeadersReceived(HttpHeaders); given TransferCompletionHandler()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State TransferCompletionHandler.onTrailingHeadersReceived(HttpHeaders)"
  })
  void testOnTrailingHeadersReceived_givenTransferCompletionHandler() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act and Assert
    assertEquals(
        State.CONTINUE,
        transferCompletionHandler.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link TransferCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}.
   *
   * <ul>
   *   <li>Then calls {@link TransferListener#onResponseHeadersReceived(HttpHeaders)}.
   * </ul>
   *
   * <p>Method under test: {@link TransferCompletionHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName(
      "Test onTrailingHeadersReceived(HttpHeaders); then calls onResponseHeadersReceived(HttpHeaders)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State TransferCompletionHandler.onTrailingHeadersReceived(HttpHeaders)"
  })
  void testOnTrailingHeadersReceived_thenCallsOnResponseHeadersReceived() throws Exception {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onResponseHeadersReceived(Mockito.<HttpHeaders>any());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler(true);
    transferCompletionHandler.addTransferListener(t);

    // Act
    State actualOnTrailingHeadersReceivedResult =
        transferCompletionHandler.onTrailingHeadersReceived(new DefaultHttpHeaders());

    // Assert
    verify(t).onResponseHeadersReceived(isA(HttpHeaders.class));
    assertEquals(State.CONTINUE, actualOnTrailingHeadersReceivedResult);
  }

  /**
   * Test {@link TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <p>Method under test: {@link
   * TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State TransferCompletionHandler.onBodyPartReceived(HttpResponseBodyPart)"
  })
  void testOnBodyPartReceived() throws Exception {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onBytesReceived(Mockito.<byte[]>any());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler(false);
    transferCompletionHandler.addTransferListener(t);
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    State actualOnBodyPartReceivedResult =
        transferCompletionHandler.onBodyPartReceived(new EagerResponseBodyPart(buf, true));

    // Assert
    verify(t).onBytesReceived(isA(byte[].class));
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <p>Method under test: {@link
   * TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State TransferCompletionHandler.onBodyPartReceived(HttpResponseBodyPart)"
  })
  void testOnBodyPartReceived2() throws Exception {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onBytesReceived(Mockito.<byte[]>any());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler(true);
    transferCompletionHandler.addTransferListener(t);
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    State actualOnBodyPartReceivedResult =
        transferCompletionHandler.onBodyPartReceived(new EagerResponseBodyPart(buf, true));

    // Assert
    verify(t).onBytesReceived(isA(byte[].class));
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TransferCompletionHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart); given TransferCompletionHandler()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State TransferCompletionHandler.onBodyPartReceived(HttpResponseBodyPart)"
  })
  void testOnBodyPartReceived_givenTransferCompletionHandler() throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    State actualOnBodyPartReceivedResult =
        transferCompletionHandler.onBodyPartReceived(new EagerResponseBodyPart(buf, true));

    // Assert
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link TransferCompletionHandler#onCompleted(Response)} with {@code Response}.
   *
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TransferCompletionHandler#onCompleted(Response)}
   */
  @Test
  @DisplayName(
      "Test onCompleted(Response) with 'Response'; given TransferCompletionHandler(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response TransferCompletionHandler.onCompleted(Response)"})
  void testOnCompletedWithResponse_givenTransferCompletionHandler_thenReturnNull()
      throws Exception {
    // Arrange
    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler();

    // Act
    Response actualOnCompletedResult =
        transferCompletionHandler.onCompleted(new ResponseBuilder().build());

    // Assert
    assertNull(actualOnCompletedResult);
  }

  /**
   * Test {@link TransferCompletionHandler#onCompleted(Response)} with {@code Response}.
   *
   * <ul>
   *   <li>Then calls {@link TransferListener#onRequestResponseCompleted()}.
   * </ul>
   *
   * <p>Method under test: {@link TransferCompletionHandler#onCompleted(Response)}
   */
  @Test
  @DisplayName(
      "Test onCompleted(Response) with 'Response'; then calls onRequestResponseCompleted()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response TransferCompletionHandler.onCompleted(Response)"})
  void testOnCompletedWithResponse_thenCallsOnRequestResponseCompleted() throws Exception {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onRequestResponseCompleted();

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler(true);
    transferCompletionHandler.addTransferListener(t);

    // Act
    Response actualOnCompletedResult =
        transferCompletionHandler.onCompleted(new ResponseBuilder().build());

    // Assert
    verify(t).onRequestResponseCompleted();
    assertNull(actualOnCompletedResult);
  }

  /**
   * Test {@link TransferCompletionHandler#onHeadersWritten()}.
   *
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.
   * </ul>
   *
   * <p>Method under test: {@link TransferCompletionHandler#onHeadersWritten()}
   */
  @Test
  @DisplayName("Test onHeadersWritten(); given TransferCompletionHandler()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AsyncHandler.State TransferCompletionHandler.onHeadersWritten()"})
  void testOnHeadersWritten_givenTransferCompletionHandler() {
    // Arrange, Act and Assert
    assertEquals(State.CONTINUE, new TransferCompletionHandler().onHeadersWritten());
  }

  /**
   * Test {@link TransferCompletionHandler#onHeadersWritten()}.
   *
   * <ul>
   *   <li>Then calls {@link TransferListener#onRequestHeadersSent(HttpHeaders)}.
   * </ul>
   *
   * <p>Method under test: {@link TransferCompletionHandler#onHeadersWritten()}
   */
  @Test
  @DisplayName("Test onHeadersWritten(); then calls onRequestHeadersSent(HttpHeaders)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AsyncHandler.State TransferCompletionHandler.onHeadersWritten()"})
  void testOnHeadersWritten_thenCallsOnRequestHeadersSent() {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onRequestHeadersSent(Mockito.<HttpHeaders>any());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler(true);
    transferCompletionHandler.headers(new DefaultHttpHeaders());
    transferCompletionHandler.addTransferListener(t);

    // Act
    State actualOnHeadersWrittenResult = transferCompletionHandler.onHeadersWritten();

    // Assert
    verify(t).onRequestHeadersSent(isA(HttpHeaders.class));
    assertEquals(State.CONTINUE, actualOnHeadersWrittenResult);
  }

  /**
   * Test {@link TransferCompletionHandler#onContentWriteProgress(long, long, long)}.
   *
   * <ul>
   *   <li>Given {@link TransferCompletionHandler#TransferCompletionHandler()}.
   * </ul>
   *
   * <p>Method under test: {@link TransferCompletionHandler#onContentWriteProgress(long, long,
   * long)}
   */
  @Test
  @DisplayName("Test onContentWriteProgress(long, long, long); given TransferCompletionHandler()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State TransferCompletionHandler.onContentWriteProgress(long, long, long)"
  })
  void testOnContentWriteProgress_givenTransferCompletionHandler() {
    // Arrange, Act and Assert
    assertEquals(
        State.CONTINUE,
        new TransferCompletionHandler()
            .onContentWriteProgress(10L, 81985529216486895L, 81985529216486895L));
  }

  /**
   * Test {@link TransferCompletionHandler#onContentWriteProgress(long, long, long)}.
   *
   * <ul>
   *   <li>Then calls {@link TransferListener#onBytesSent(long, long, long)}.
   * </ul>
   *
   * <p>Method under test: {@link TransferCompletionHandler#onContentWriteProgress(long, long,
   * long)}
   */
  @Test
  @DisplayName(
      "Test onContentWriteProgress(long, long, long); then calls onBytesSent(long, long, long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State TransferCompletionHandler.onContentWriteProgress(long, long, long)"
  })
  void testOnContentWriteProgress_thenCallsOnBytesSent() {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onBytesSent(anyLong(), anyLong(), anyLong());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler(true);
    transferCompletionHandler.addTransferListener(t);

    // Act
    State actualOnContentWriteProgressResult =
        transferCompletionHandler.onContentWriteProgress(
            10L, 81985529216486895L, 81985529216486895L);

    // Assert
    verify(t).onBytesSent(10L, 81985529216486895L, 81985529216486895L);
    assertEquals(State.CONTINUE, actualOnContentWriteProgressResult);
  }

  /**
   * Test {@link TransferCompletionHandler#onThrowable(Throwable)}.
   *
   * <ul>
   *   <li>Given {@link TransferListener} {@link TransferListener#onThrowable(Throwable)} does
   *       nothing.
   *   <li>Then calls {@link TransferListener#onThrowable(Throwable)}.
   * </ul>
   *
   * <p>Method under test: {@link TransferCompletionHandler#onThrowable(Throwable)}
   */
  @Test
  @DisplayName(
      "Test onThrowable(Throwable); given TransferListener onThrowable(Throwable) does nothing; then calls onThrowable(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TransferCompletionHandler.onThrowable(Throwable)"})
  void testOnThrowable_givenTransferListenerOnThrowableDoesNothing_thenCallsOnThrowable() {
    // Arrange
    TransferListener t = mock(TransferListener.class);
    doNothing().when(t).onThrowable(Mockito.<Throwable>any());

    TransferCompletionHandler transferCompletionHandler = new TransferCompletionHandler(true);
    transferCompletionHandler.addTransferListener(t);

    // Act
    transferCompletionHandler.onThrowable(ChannelClosedException.INSTANCE);

    // Assert
    verify(t).onThrowable(isA(Throwable.class));
  }
}
