package org.asynchttpclient.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.AsyncHandler.State;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.Response;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.handler.BodyDeferringAsyncHandler.BodyDeferringInputStream;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BodyDeferringAsyncHandlerDiffblueTest {
  @InjectMocks private BodyDeferringAsyncHandler bodyDeferringAsyncHandler;

  @Mock private OutputStream outputStream;

  /**
   * Test BodyDeferringInputStream {@link BodyDeferringInputStream#getAsapResponse()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link BodyDeferringInputStream#getAsapResponse()}
   */
  @Test
  @DisplayName("Test BodyDeferringInputStream getAsapResponse(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response BodyDeferringInputStream.getAsapResponse()"})
  void testBodyDeferringInputStreamGetAsapResponse_thenReturnNull()
      throws IOException, InterruptedException {
    // Arrange
    BodyDeferringAsyncHandler bdah = new BodyDeferringAsyncHandler(new ByteArrayOutputStream());
    bdah.onThrowable(null);
    CompletableFuture<Response> future = new CompletableFuture<>();

    BodyDeferringInputStream bodyDeferringInputStream =
        new BodyDeferringInputStream(
            future, bdah, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertNull(bodyDeferringInputStream.getAsapResponse());
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#BodyDeferringAsyncHandler(OutputStream)}.
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#BodyDeferringAsyncHandler(OutputStream)}
   */
  @Test
  @DisplayName("Test new BodyDeferringAsyncHandler(OutputStream)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BodyDeferringAsyncHandler.<init>(OutputStream)"})
  void testNewBodyDeferringAsyncHandler() {
    // Arrange and Act
    BodyDeferringAsyncHandler actualBodyDeferringAsyncHandler =
        new BodyDeferringAsyncHandler(new ByteArrayOutputStream());

    // Assert
    assertEquals(State.CONTINUE, actualBodyDeferringAsyncHandler.onHeadersReceived(null));
    assertEquals(State.CONTINUE, actualBodyDeferringAsyncHandler.onStatusReceived(null));
    assertEquals(State.CONTINUE, actualBodyDeferringAsyncHandler.onTrailingHeadersReceived(null));
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onThrowable(Throwable)}.
   *
   * <ul>
   *   <li>Given {@link OutputStream} {@link OutputStream#close()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#onThrowable(Throwable)}
   */
  @Test
  @DisplayName("Test onThrowable(Throwable); given OutputStream close() does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BodyDeferringAsyncHandler.onThrowable(Throwable)"})
  void testOnThrowable_givenOutputStreamCloseDoesNothing() throws IOException {
    // Arrange
    doNothing().when(outputStream).close();
    doNothing().when(outputStream).flush();

    // Act
    bodyDeferringAsyncHandler.onThrowable(ChannelClosedException.INSTANCE);

    // Assert
    verify(outputStream).close();
    verify(outputStream).flush();
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onThrowable(Throwable)}.
   *
   * <ul>
   *   <li>Given {@link OutputStream} {@link OutputStream#close()} throw {@link
   *       ChannelClosedException#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#onThrowable(Throwable)}
   */
  @Test
  @DisplayName("Test onThrowable(Throwable); given OutputStream close() throw INSTANCE")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BodyDeferringAsyncHandler.onThrowable(Throwable)"})
  void testOnThrowable_givenOutputStreamCloseThrowInstance() throws IOException {
    // Arrange
    doThrow(ChannelClosedException.INSTANCE).when(outputStream).close();
    doThrow(ChannelClosedException.INSTANCE).when(outputStream).flush();

    // Act
    bodyDeferringAsyncHandler.onThrowable(ChannelClosedException.INSTANCE);

    // Assert
    verify(outputStream).close();
    verify(outputStream).flush();
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   *
   * <p>Method under test: {@link
   * BodyDeferringAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State BodyDeferringAsyncHandler.onStatusReceived(org.asynchttpclient.HttpResponseStatus)"
  })
  void testOnStatusReceived() {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler =
        new BodyDeferringAsyncHandler(new ByteArrayOutputStream());
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus responseStatus =
        new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    State actualOnStatusReceivedResult = bodyDeferringAsyncHandler.onStatusReceived(responseStatus);

    // Assert
    assertEquals(State.CONTINUE, actualOnStatusReceivedResult);
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onHeadersReceived(HttpHeaders)}.
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onHeadersReceived(HttpHeaders)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AsyncHandler.State BodyDeferringAsyncHandler.onHeadersReceived(HttpHeaders)"})
  void testOnHeadersReceived() {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler =
        new BodyDeferringAsyncHandler(new ByteArrayOutputStream());

    // Act and Assert
    assertEquals(
        State.CONTINUE, bodyDeferringAsyncHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onTrailingHeadersReceived(HttpHeaders)}.
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onTrailingHeadersReceived(HttpHeaders)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State BodyDeferringAsyncHandler.onTrailingHeadersReceived(HttpHeaders)"
  })
  void testOnTrailingHeadersReceived() {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler =
        new BodyDeferringAsyncHandler(new ByteArrayOutputStream());

    // Act and Assert
    assertEquals(
        State.CONTINUE,
        bodyDeferringAsyncHandler.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onRetry()}.
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#onRetry()}
   */
  @Test
  @DisplayName("Test onRetry()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BodyDeferringAsyncHandler.onRetry()"})
  void testOnRetry() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> new BodyDeferringAsyncHandler(new ByteArrayOutputStream()).onRetry());
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <ul>
   *   <li>Then return {@code CONTINUE}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BodyDeferringAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart); then return 'CONTINUE'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State BodyDeferringAsyncHandler.onBodyPartReceived(HttpResponseBodyPart)"
  })
  void testOnBodyPartReceived_thenReturnContinue() throws Exception {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler =
        new BodyDeferringAsyncHandler(new ByteArrayOutputStream());
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    State actualOnBodyPartReceivedResult =
        bodyDeferringAsyncHandler.onBodyPartReceived(new EagerResponseBodyPart(buf, true));

    // Assert
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <ul>
   *   <li>Then throw {@link ChannelClosedException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BodyDeferringAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart); then throw ChannelClosedException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State BodyDeferringAsyncHandler.onBodyPartReceived(HttpResponseBodyPart)"
  })
  void testOnBodyPartReceived_thenThrowChannelClosedException() throws Exception {
    // Arrange
    doThrow(ChannelClosedException.INSTANCE).when(outputStream).write(Mockito.<byte[]>any());
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertThrows(
        ChannelClosedException.class,
        () -> bodyDeferringAsyncHandler.onBodyPartReceived(new EagerResponseBodyPart(buf, true)));
    verify(outputStream).write(isA(byte[].class));
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#closeOut()}.
   *
   * <ul>
   *   <li>Given {@link OutputStream} {@link OutputStream#close()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#closeOut()}
   */
  @Test
  @DisplayName("Test closeOut(); given OutputStream close() does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BodyDeferringAsyncHandler.closeOut()"})
  void testCloseOut_givenOutputStreamCloseDoesNothing() throws IOException {
    // Arrange
    doNothing().when(outputStream).close();
    doNothing().when(outputStream).flush();

    // Act
    bodyDeferringAsyncHandler.closeOut();

    // Assert
    verify(outputStream).close();
    verify(outputStream).flush();
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#closeOut()}.
   *
   * <ul>
   *   <li>Given {@link OutputStream} {@link OutputStream#close()} throw {@link
   *       ChannelClosedException#INSTANCE}.
   *   <li>Then throw {@link ChannelClosedException}.
   * </ul>
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#closeOut()}
   */
  @Test
  @DisplayName(
      "Test closeOut(); given OutputStream close() throw INSTANCE; then throw ChannelClosedException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BodyDeferringAsyncHandler.closeOut()"})
  void testCloseOut_givenOutputStreamCloseThrowInstance_thenThrowChannelClosedException()
      throws IOException {
    // Arrange
    doThrow(ChannelClosedException.INSTANCE).when(outputStream).close();
    doThrow(ChannelClosedException.INSTANCE).when(outputStream).flush();

    // Act and Assert
    assertThrows(ChannelClosedException.class, () -> bodyDeferringAsyncHandler.closeOut());
    verify(outputStream).close();
    verify(outputStream).flush();
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onCompleted()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#onCompleted()}
   */
  @Test
  @DisplayName("Test onCompleted(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response BodyDeferringAsyncHandler.onCompleted()"})
  void testOnCompleted_thenReturnNull() throws IOException {
    // Arrange, Act and Assert
    assertNull(new BodyDeferringAsyncHandler(new ByteArrayOutputStream()).onCompleted());
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onCompleted()}.
   *
   * <ul>
   *   <li>Then throw {@link ChannelClosedException}.
   * </ul>
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#onCompleted()}
   */
  @Test
  @DisplayName("Test onCompleted(); then throw ChannelClosedException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response BodyDeferringAsyncHandler.onCompleted()"})
  void testOnCompleted_thenThrowChannelClosedException() throws IOException {
    // Arrange
    doThrow(ChannelClosedException.INSTANCE).when(outputStream).close();
    doThrow(ChannelClosedException.INSTANCE).when(outputStream).flush();

    // Act and Assert
    assertThrows(ChannelClosedException.class, () -> bodyDeferringAsyncHandler.onCompleted());
    verify(outputStream).close();
    verify(outputStream).flush();
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onCompleted()}.
   *
   * <ul>
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#onCompleted()}
   */
  @Test
  @DisplayName("Test onCompleted(); then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response BodyDeferringAsyncHandler.onCompleted()"})
  void testOnCompleted_thenThrowIOException() throws IOException {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler =
        new BodyDeferringAsyncHandler(new ByteArrayOutputStream());
    bodyDeferringAsyncHandler.onThrowable(ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(IOException.class, () -> bodyDeferringAsyncHandler.onCompleted());
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#getResponse()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#getResponse()}
   */
  @Test
  @DisplayName("Test getResponse(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response BodyDeferringAsyncHandler.getResponse()"})
  void testGetResponse_thenReturnNull() throws IOException, InterruptedException {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler =
        new BodyDeferringAsyncHandler(new ByteArrayOutputStream());
    bodyDeferringAsyncHandler.onThrowable(null);

    // Act and Assert
    assertNull(bodyDeferringAsyncHandler.getResponse());
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#getResponse()}.
   *
   * <ul>
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#getResponse()}
   */
  @Test
  @DisplayName("Test getResponse(); then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response BodyDeferringAsyncHandler.getResponse()"})
  void testGetResponse_thenThrowIOException() throws IOException, InterruptedException {
    // Arrange
    BodyDeferringAsyncHandler bodyDeferringAsyncHandler =
        new BodyDeferringAsyncHandler(new ByteArrayOutputStream());
    bodyDeferringAsyncHandler.onThrowable(ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(IOException.class, () -> bodyDeferringAsyncHandler.getResponse());
  }
}
