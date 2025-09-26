package org.asynchttpclient.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.AsyncHandler.State;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.Response;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.handler.BodyDeferringAsyncHandler.BodyDeferringInputStream;
import org.asynchttpclient.handler.BodyDeferringAsyncHandlerTest.CountingOutputStream;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BodyDeferringAsyncHandlerDiffblueTest {
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
   * Test BodyDeferringInputStream {@link BodyDeferringInputStream#BodyDeferringInputStream(Future,
   * BodyDeferringAsyncHandler, InputStream)}.
   *
   * <p>Method under test: {@link BodyDeferringInputStream#BodyDeferringInputStream(Future,
   * BodyDeferringAsyncHandler, InputStream)}
   */
  @Test
  @DisplayName(
      "Test BodyDeferringInputStream new BodyDeferringInputStream(Future, BodyDeferringAsyncHandler, InputStream)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BodyDeferringInputStream.<init>(Future, BodyDeferringAsyncHandler, InputStream)"
  })
  void testBodyDeferringInputStreamNewBodyDeferringInputStream() throws IOException {
    // Arrange
    CompletableFuture<Response> future = new CompletableFuture<>();
    BodyDeferringAsyncHandler bdah = new BodyDeferringAsyncHandler(new ByteArrayOutputStream());

    // Act
    BodyDeferringInputStream actualBodyDeferringInputStream =
        new BodyDeferringInputStream(
            future, bdah, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    assertEquals(8, actualBodyDeferringInputStream.read(new byte[8]));
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
   *   <li>Given {@link CountingOutputStream} {@link CountingOutputStream#close()} throw {@link
   *       ChannelClosedException#INSTANCE}.
   *   <li>Then calls {@link CountingOutputStream#close()}.
   * </ul>
   *
   * <p>Method under test: {@link BodyDeferringAsyncHandler#onThrowable(Throwable)}
   */
  @Test
  @DisplayName(
      "Test onThrowable(Throwable); given CountingOutputStream close() throw INSTANCE; then calls close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BodyDeferringAsyncHandler.onThrowable(Throwable)"})
  void testOnThrowable_givenCountingOutputStreamCloseThrowInstance_thenCallsClose()
      throws IOException {
    // Arrange
    CountingOutputStream os = mock(CountingOutputStream.class);
    doThrow(ChannelClosedException.INSTANCE).when(os).close();
    doThrow(ChannelClosedException.INSTANCE).when(os).flush();

    // Act
    new BodyDeferringAsyncHandler(os).onThrowable(ChannelClosedException.INSTANCE);

    // Assert
    verify(os).close();
    verify(os).flush();
  }

  /**
   * Test {@link BodyDeferringAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   *
   * <ul>
   *   <li>Then return {@code CONTINUE}.
   * </ul>
   *
   * <p>Method under test: {@link
   * BodyDeferringAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus); then return 'CONTINUE'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State BodyDeferringAsyncHandler.onStatusReceived(org.asynchttpclient.HttpResponseStatus)"
  })
  void testOnStatusReceived_thenReturnContinue() {
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
