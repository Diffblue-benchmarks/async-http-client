package org.asynchttpclient.handler.resumable;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.buffer.SwappedByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.Headers;
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
import java.time.Duration;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.asynchttpclient.AbstractBasicTest;
import org.asynchttpclient.AbstractBasicTest.AsyncCompletionHandlerAdapter;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.AsyncHandler.State;
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.handler.resumable.ResumableAsyncHandler.ResumableProcessor;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.LazyResponseBodyPart;
import org.asynchttpclient.netty.NettyResponse;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ResumableAsyncHandlerDiffblueTest {
  /**
   * Test {@link ResumableAsyncHandler#ResumableAsyncHandler()}.
   *
   * <p>Method under test: {@link ResumableAsyncHandler#ResumableAsyncHandler()}
   */
  @Test
  @DisplayName("Test new ResumableAsyncHandler()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResumableAsyncHandler.<init>()"})
  void testNewResumableAsyncHandler() throws Exception {
    // Arrange, Act and Assert
    assertNull(new ResumableAsyncHandler().onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#ResumableAsyncHandler(long)}.
   *
   * <p>Method under test: {@link ResumableAsyncHandler#ResumableAsyncHandler(long)}
   */
  @Test
  @DisplayName("Test new ResumableAsyncHandler(long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResumableAsyncHandler.<init>(long)"})
  void testNewResumableAsyncHandler2() throws Exception {
    // Arrange, Act and Assert
    assertNull(new ResumableAsyncHandler(1L).onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#ResumableAsyncHandler(long, AsyncHandler)}.
   *
   * <p>Method under test: {@link ResumableAsyncHandler#ResumableAsyncHandler(long, AsyncHandler)}
   */
  @Test
  @DisplayName("Test new ResumableAsyncHandler(long, AsyncHandler)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResumableAsyncHandler.<init>(long, AsyncHandler)"})
  void testNewResumableAsyncHandler3() throws Exception {
    // Arrange and Act
    ResumableAsyncHandler actualResumableAsyncHandler =
        new ResumableAsyncHandler(1L, new AsyncCompletionHandlerAdapter());

    // Assert
    assertNull(actualResumableAsyncHandler.onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#ResumableAsyncHandler(AsyncHandler)}.
   *
   * <p>Method under test: {@link ResumableAsyncHandler#ResumableAsyncHandler(AsyncHandler)}
   */
  @Test
  @DisplayName("Test new ResumableAsyncHandler(AsyncHandler)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResumableAsyncHandler.<init>(AsyncHandler)"})
  void testNewResumableAsyncHandler4() throws Exception {
    // Arrange, Act and Assert
    assertNull(new ResumableAsyncHandler(new AsyncCompletionHandlerAdapter()).onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#ResumableAsyncHandler(boolean)}.
   *
   * <p>Method under test: {@link ResumableAsyncHandler#ResumableAsyncHandler(boolean)}
   */
  @Test
  @DisplayName("Test new ResumableAsyncHandler(boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResumableAsyncHandler.<init>(boolean)"})
  void testNewResumableAsyncHandler5() throws Exception {
    // Arrange, Act and Assert
    assertNull(new ResumableAsyncHandler(true).onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#ResumableAsyncHandler(ResumableProcessor)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#ResumableAsyncHandler(ResumableProcessor)}
   */
  @Test
  @DisplayName("Test new ResumableAsyncHandler(ResumableProcessor); when 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResumableAsyncHandler.<init>(ResumableProcessor)"})
  void testNewResumableAsyncHandler_whenNull() throws Exception {
    // Arrange, Act and Assert
    assertNull(new ResumableAsyncHandler((ResumableProcessor) null).onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#ResumableAsyncHandler(ResumableProcessor, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#ResumableAsyncHandler(ResumableProcessor,
   * boolean)}
   */
  @Test
  @DisplayName("Test new ResumableAsyncHandler(ResumableProcessor, boolean); when 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResumableAsyncHandler.<init>(ResumableProcessor, boolean)"})
  void testNewResumableAsyncHandler_whenNull2() throws Exception {
    // Arrange, Act and Assert
    assertNull(new ResumableAsyncHandler(null, true).onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#ResumableAsyncHandler(ResumableProcessor)}.
   *
   * <ul>
   *   <li>When {@link PropertiesBasedResumableProcessor} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#ResumableAsyncHandler(ResumableProcessor)}
   */
  @Test
  @DisplayName(
      "Test new ResumableAsyncHandler(ResumableProcessor); when PropertiesBasedResumableProcessor (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResumableAsyncHandler.<init>(ResumableProcessor)"})
  void testNewResumableAsyncHandler_whenPropertiesBasedResumableProcessor() throws Exception {
    // Arrange, Act and Assert
    assertNull(new ResumableAsyncHandler(new PropertiesBasedResumableProcessor()).onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#ResumableAsyncHandler(ResumableProcessor, boolean)}.
   *
   * <ul>
   *   <li>When {@link PropertiesBasedResumableProcessor} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#ResumableAsyncHandler(ResumableProcessor,
   * boolean)}
   */
  @Test
  @DisplayName(
      "Test new ResumableAsyncHandler(ResumableProcessor, boolean); when PropertiesBasedResumableProcessor (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResumableAsyncHandler.<init>(ResumableProcessor, boolean)"})
  void testNewResumableAsyncHandler_whenPropertiesBasedResumableProcessor2() throws Exception {
    // Arrange, Act and Assert
    assertNull(
        new ResumableAsyncHandler(new PropertiesBasedResumableProcessor(), true).onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   *
   * <p>Method under test: {@link
   * ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "State ResumableAsyncHandler.onStatusReceived(org.asynchttpclient.HttpResponseStatus)"
  })
  void testOnStatusReceived() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
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

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    State actualOnStatusReceivedResult = resumableAsyncHandler.onStatusReceived(status);

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertEquals("Unknown Status (1)", onCompletedResult.getStatusText());
    assertEquals(1, onCompletedResult.getStatusCode());
    assertEquals(State.ABORT, actualOnStatusReceivedResult);
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsByteBuffer().array());
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsBytes());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   *
   * <p>Method under test: {@link
   * ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "State ResumableAsyncHandler.onStatusReceived(org.asynchttpclient.HttpResponseStatus)"
  })
  void testOnStatusReceived2() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
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
        new DefaultFullHttpResponse(
            version, HttpResponseStatus.valueOf(200, "https://example.org/example"));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    resumableAsyncHandler.onStatusReceived(status);

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsByteBuffer().array());
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsBytes());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   *
   * <p>Method under test: {@link
   * ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "State ResumableAsyncHandler.onStatusReceived(org.asynchttpclient.HttpResponseStatus)"
  })
  void testOnStatusReceived3() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri =
        new Uri(
            "https://example.org/example",
            null,
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(
            version, HttpResponseStatus.valueOf(200, "https://example.org/example"));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    resumableAsyncHandler.onStatusReceived(status);

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertSame(uri, onCompletedResult.getUri());
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsByteBuffer().array());
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsBytes());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   *
   * <p>Method under test: {@link
   * ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "State ResumableAsyncHandler.onStatusReceived(org.asynchttpclient.HttpResponseStatus)"
  })
  void testOnStatusReceived4() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            -1,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(
            version, HttpResponseStatus.valueOf(200, "https://example.org/example"));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    resumableAsyncHandler.onStatusReceived(status);

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertSame(uri, onCompletedResult.getUri());
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsByteBuffer().array());
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsBytes());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   *
   * <p>Method under test: {@link
   * ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "State ResumableAsyncHandler.onStatusReceived(org.asynchttpclient.HttpResponseStatus)"
  })
  void testOnStatusReceived5() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            null,
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(
            version, HttpResponseStatus.valueOf(200, "https://example.org/example"));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    resumableAsyncHandler.onStatusReceived(status);

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertSame(uri, onCompletedResult.getUri());
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsByteBuffer().array());
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsBytes());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   *
   * <p>Method under test: {@link
   * ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "State ResumableAsyncHandler.onStatusReceived(org.asynchttpclient.HttpResponseStatus)"
  })
  void testOnStatusReceived6() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            null,
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(
            version, HttpResponseStatus.valueOf(200, "https://example.org/example"));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    resumableAsyncHandler.onStatusReceived(status);

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertSame(uri, onCompletedResult.getUri());
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsByteBuffer().array());
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsBytes());
  }

  /**
   * Test {@link ResumableAsyncHandler#onStatusReceived(HttpResponseStatus)}.
   *
   * <p>Method under test: {@link
   * ResumableAsyncHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "State ResumableAsyncHandler.onStatusReceived(org.asynchttpclient.HttpResponseStatus)"
  })
  void testOnStatusReceived7() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
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
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(206));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    resumableAsyncHandler.onStatusReceived(status);

    // Assert
    Response onCompletedResult = resumableAsyncHandler.onCompleted();
    assertTrue(onCompletedResult instanceof NettyResponse);
    assertEquals("Partial Content", onCompletedResult.getStatusText());
    assertEquals(206, onCompletedResult.getStatusCode());
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsByteBuffer().array());
    assertArrayEquals(new byte[] {}, onCompletedResult.getResponseBodyAsBytes());
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"State ResumableAsyncHandler.onBodyPartReceived(HttpResponseBodyPart)"})
  void testOnBodyPartReceived() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    State actualOnBodyPartReceivedResult =
        resumableAsyncHandler.onBodyPartReceived(new EagerResponseBodyPart(buf, true));

    // Assert
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"State ResumableAsyncHandler.onBodyPartReceived(HttpResponseBodyPart)"})
  void testOnBodyPartReceived2() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    CompositeByteBuf buf = Unpooled.compositeBuffer(3);

    // Act
    State actualOnBodyPartReceivedResult =
        resumableAsyncHandler.onBodyPartReceived(new LazyResponseBodyPart(buf, true));

    // Assert
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"State ResumableAsyncHandler.onBodyPartReceived(HttpResponseBodyPart)"})
  void testOnBodyPartReceived3() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act
    State actualOnBodyPartReceivedResult =
        resumableAsyncHandler.onBodyPartReceived(
            new LazyResponseBodyPart(new EmptyByteBuf(new AdaptiveByteBufAllocator()), true));

    // Assert
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <ul>
   *   <li>Given {@link ResumableAsyncHandler#ResumableAsyncHandler(boolean)} with accumulateBody is
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName(
      "Test onBodyPartReceived(HttpResponseBodyPart); given ResumableAsyncHandler(boolean) with accumulateBody is 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"State ResumableAsyncHandler.onBodyPartReceived(HttpResponseBodyPart)"})
  void testOnBodyPartReceived_givenResumableAsyncHandlerWithAccumulateBodyIsTrue()
      throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler(true);
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    State actualOnBodyPartReceivedResult =
        resumableAsyncHandler.onBodyPartReceived(new EagerResponseBodyPart(buf, true));

    // Assert
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <ul>
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart); then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"State ResumableAsyncHandler.onBodyPartReceived(HttpResponseBodyPart)"})
  void testOnBodyPartReceived_thenCallsCapacity() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.nioBuffer(anyInt(), anyInt()))
        .thenReturn(ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8")));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer2);

    // Act
    State actualOnBodyPartReceivedResult =
        resumableAsyncHandler.onBodyPartReceived(new LazyResponseBodyPart(buf, true));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).nioBuffer(1, 0);
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <ul>
   *   <li>When {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is {@link
   *       DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName(
      "Test onBodyPartReceived(HttpResponseBodyPart); when ReadOnlyByteBuf(ByteBuf) with buffer is DuplicatedByteBuf(ByteBuf)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"State ResumableAsyncHandler.onBodyPartReceived(HttpResponseBodyPart)"})
  void testOnBodyPartReceived_whenReadOnlyByteBufWithBufferIsDuplicatedByteBuf() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer);

    // Act
    State actualOnBodyPartReceivedResult =
        resumableAsyncHandler.onBodyPartReceived(new LazyResponseBodyPart(buf, true));

    // Assert
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <ul>
   *   <li>When {@link SwappedByteBuf#SwappedByteBuf(ByteBuf)} with buf is compositeBuffer three.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName(
      "Test onBodyPartReceived(HttpResponseBodyPart); when SwappedByteBuf(ByteBuf) with buf is compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"State ResumableAsyncHandler.onBodyPartReceived(HttpResponseBodyPart)"})
  void testOnBodyPartReceived_whenSwappedByteBufWithBufIsCompositeBufferThree() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    CompositeByteBuf buf = Unpooled.compositeBuffer(3);
    SwappedByteBuf buf2 = new SwappedByteBuf(buf);

    // Act
    State actualOnBodyPartReceivedResult =
        resumableAsyncHandler.onBodyPartReceived(new LazyResponseBodyPart(buf2, true));

    // Assert
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <ul>
   *   <li>When {@link SwappedByteBuf#SwappedByteBuf(ByteBuf)} with buf is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName(
      "Test onBodyPartReceived(HttpResponseBodyPart); when SwappedByteBuf(ByteBuf) with buf is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"State ResumableAsyncHandler.onBodyPartReceived(HttpResponseBodyPart)"})
  void testOnBodyPartReceived_whenSwappedByteBufWithBufIsEmptyByteBuf() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
    SwappedByteBuf buf = new SwappedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    State actualOnBodyPartReceivedResult =
        resumableAsyncHandler.onBodyPartReceived(new LazyResponseBodyPart(buf, true));

    // Assert
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link ResumableAsyncHandler#onCompleted()}.
   *
   * <ul>
   *   <li>Given {@link ResumableAsyncHandler#ResumableAsyncHandler()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onCompleted()}
   */
  @Test
  @DisplayName("Test onCompleted(); given ResumableAsyncHandler(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response ResumableAsyncHandler.onCompleted()"})
  void testOnCompleted_givenResumableAsyncHandler_thenReturnNull() throws Exception {
    // Arrange, Act and Assert
    assertNull(new ResumableAsyncHandler().onCompleted());
  }

  /**
   * Test {@link ResumableAsyncHandler#onCompleted()}.
   *
   * <ul>
   *   <li>Then Headers return {@link EmptyHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onCompleted()}
   */
  @Test
  @DisplayName("Test onCompleted(); then Headers return EmptyHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response ResumableAsyncHandler.onCompleted()"})
  void testOnCompleted_thenHeadersReturnEmptyHttpHeaders() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();
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

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    resumableAsyncHandler.onStatusReceived(status);

    // Act
    Response actualOnCompletedResult = resumableAsyncHandler.onCompleted();

    // Assert
    assertTrue(actualOnCompletedResult.getHeaders() instanceof EmptyHttpHeaders);
    assertTrue(actualOnCompletedResult instanceof NettyResponse);
    assertEquals("", actualOnCompletedResult.getResponseBody());
    assertEquals("Unknown Status (1)", actualOnCompletedResult.getStatusText());
    assertNull(actualOnCompletedResult.getContentType());
    int actualReadResult = actualOnCompletedResult.getResponseBodyAsStream().read(new byte[] {});
    assertEquals(-1, actualReadResult);
    assertEquals(1, actualOnCompletedResult.getStatusCode());
    assertFalse(actualOnCompletedResult.hasResponseBody());
    assertFalse(actualOnCompletedResult.hasResponseHeaders());
    assertFalse(actualOnCompletedResult.isRedirected());
    assertTrue(actualOnCompletedResult.getCookies().isEmpty());
    assertTrue(actualOnCompletedResult.hasResponseStatus());
    assertSame(uri, actualOnCompletedResult.getUri());
    assertArrayEquals(new byte[] {}, actualOnCompletedResult.getResponseBodyAsBytes());
  }

  /**
   * Test {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link EmptyHttpHeaders} {@link EmptyHttpHeaders#get(CharSequence)} return {@code
   *       42}.
   *   <li>Then calls {@link EmptyHttpHeaders#get(CharSequence)}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName(
      "Test onHeadersReceived(HttpHeaders); given '42'; when EmptyHttpHeaders get(CharSequence) return '42'; then calls get(CharSequence)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"State ResumableAsyncHandler.onHeadersReceived(HttpHeaders)"})
  void testOnHeadersReceived_given42_whenEmptyHttpHeadersGetReturn42_thenCallsGet()
      throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("42");

    // Act
    State actualOnHeadersReceivedResult = resumableAsyncHandler.onHeadersReceived(headers);

    // Assert
    verify(headers).get(isA(CharSequence.class));
    assertEquals(State.CONTINUE, actualOnHeadersReceivedResult);
  }

  /**
   * Test {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link EmptyHttpHeaders} {@link EmptyHttpHeaders#get(CharSequence)} return {@code
   *       null}.
   *   <li>Then calls {@link EmptyHttpHeaders#get(CharSequence)}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName(
      "Test onHeadersReceived(HttpHeaders); given 'null'; when EmptyHttpHeaders get(CharSequence) return 'null'; then calls get(CharSequence)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"State ResumableAsyncHandler.onHeadersReceived(HttpHeaders)"})
  void testOnHeadersReceived_givenNull_whenEmptyHttpHeadersGetReturnNull_thenCallsGet()
      throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn(null);

    // Act
    State actualOnHeadersReceivedResult = resumableAsyncHandler.onHeadersReceived(headers);

    // Assert
    verify(headers).get(isA(CharSequence.class));
    assertEquals(State.CONTINUE, actualOnHeadersReceivedResult);
  }

  /**
   * Test {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}.
   *
   * <ul>
   *   <li>When {@link DefaultHttpHeaders#DefaultHttpHeaders()}.
   *   <li>Then return {@code CONTINUE}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName(
      "Test onHeadersReceived(HttpHeaders); when DefaultHttpHeaders(); then return 'CONTINUE'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"State ResumableAsyncHandler.onHeadersReceived(HttpHeaders)"})
  void testOnHeadersReceived_whenDefaultHttpHeaders_thenReturnContinue() throws Exception {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(State.CONTINUE, resumableAsyncHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link ResumableAsyncHandler#onTrailingHeadersReceived(HttpHeaders)}.
   *
   * <p>Method under test: {@link ResumableAsyncHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onTrailingHeadersReceived(HttpHeaders)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"State ResumableAsyncHandler.onTrailingHeadersReceived(HttpHeaders)"})
  void testOnTrailingHeadersReceived() {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    // Act and Assert
    assertEquals(
        State.CONTINUE, resumableAsyncHandler.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   *
   * <p>Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Request ResumableAsyncHandler.adjustRequestRange(Request)"})
  void testAdjustRequestRange() throws UnsupportedEncodingException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");
    when(uri.toUrl()).thenReturn("https://example.org/example");

    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn(null);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> resumableAsyncHandler.adjustRequestRange(request));
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getScheme();
    verify(uri).toUrl();
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   *
   * <ul>
   *   <li>Given {@code http}.
   *   <li>Then return Uri toJavaNetURI toString is a string.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName(
      "Test adjustRequestRange(Request); given 'http'; then return Uri toJavaNetURI toString is a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Request ResumableAsyncHandler.adjustRequestRange(Request)"})
  void testAdjustRequestRange_givenHttp_thenReturnUriToJavaNetURIToStringIsAString()
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

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(request);

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
    assertEquals(
        "http://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
            + "://example.org/example",
        actualAdjustRequestRangeResult.getUri().toJavaNetURI().toString());
    assertEquals(
        "http://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
            + "://example.org/example",
        actualAdjustRequestRangeResult.getUrl());
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(
        expectedArrayResult, actualAdjustRequestRangeResult.getByteBufferData().array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualAdjustRequestRangeResult.getByteData());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   *
   * <ul>
   *   <li>Given {@code https}.
   *   <li>Then return Uri Scheme is {@code https}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); given 'https'; then return Uri Scheme is 'https'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Request ResumableAsyncHandler.adjustRequestRange(Request)"})
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
    when(headers.get(Mockito.<CharSequence>any())).thenReturn(null);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(request);

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
    assertEquals(
        "https://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
            + "://example.org/example",
        uri2.toJavaNetURI().toString());
    assertEquals(
        "https://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
            + "://example.org/example",
        actualAdjustRequestRangeResult.getUrl());
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(
        expectedArrayResult, actualAdjustRequestRangeResult.getByteBufferData().array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualAdjustRequestRangeResult.getByteData());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   *
   * <ul>
   *   <li>Given {@link ResumableAsyncHandler#ResumableAsyncHandler(long)} with byteTransferred is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName(
      "Test adjustRequestRange(Request); given ResumableAsyncHandler(long) with byteTransferred is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Request ResumableAsyncHandler.adjustRequestRange(Request)"})
  void testAdjustRequestRange_givenResumableAsyncHandlerWithByteTransferredIsOne()
      throws UnsupportedEncodingException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler(1L);

    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");
    when(uri.toUrl()).thenReturn("https://example.org/example");

    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn(null);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> resumableAsyncHandler.adjustRequestRange(request));
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getScheme();
    verify(uri).toUrl();
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   *
   * <ul>
   *   <li>Given {@code ws}.
   *   <li>When {@link Uri} {@link Uri#getScheme()} return {@code ws}.
   *   <li>Then return Uri Scheme is {@code ws}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName(
      "Test adjustRequestRange(Request); given 'ws'; when Uri getScheme() return 'ws'; then return Uri Scheme is 'ws'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Request ResumableAsyncHandler.adjustRequestRange(Request)"})
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
    when(headers.get(Mockito.<CharSequence>any())).thenReturn(null);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(request);

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
    assertEquals(
        "ws://https://example.org/example@https://example.org/example:8080https://example.org/example?https:/"
            + "/example.org/example",
        uri2.toJavaNetURI().toString());
    assertEquals(
        "ws://https://example.org/example@https://example.org/example:8080https://example.org/example?https:/"
            + "/example.org/example",
        actualAdjustRequestRangeResult.getUrl());
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(
        expectedArrayResult, actualAdjustRequestRangeResult.getByteBufferData().array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualAdjustRequestRangeResult.getByteData());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   *
   * <ul>
   *   <li>Given {@code wss}.
   *   <li>Then return Uri Scheme is {@code wss}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); given 'wss'; then return Uri Scheme is 'wss'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Request ResumableAsyncHandler.adjustRequestRange(Request)"})
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
    when(headers.get(Mockito.<CharSequence>any())).thenReturn(null);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(request);

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
    assertEquals(
        "wss://https://example.org/example@https://example.org/example:8080https://example.org/example?https:"
            + "//example.org/example",
        uri2.toJavaNetURI().toString());
    assertEquals(
        "wss://https://example.org/example@https://example.org/example:8080https://example.org/example?https:"
            + "//example.org/example",
        actualAdjustRequestRangeResult.getUrl());
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(
        expectedArrayResult, actualAdjustRequestRangeResult.getByteBufferData().array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualAdjustRequestRangeResult.getByteData());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   *
   * <ul>
   *   <li>Then Headers return {@link DefaultHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); then Headers return DefaultHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Request ResumableAsyncHandler.adjustRequestRange(Request)"})
  void testAdjustRequestRange_thenHeadersReturnDefaultHttpHeaders()
      throws UnsupportedEncodingException {
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
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);

    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn(null);
    SimpleEntry<String, String> simpleEntry = new SimpleEntry<>("charset=", "charset=");

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
    entryList.add(simpleEntry);
    when(headers.iterator()).thenReturn(entryList.iterator());
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(request);

    // Assert
    assertTrue(actualAdjustRequestRangeResult instanceof DefaultRequest);
    assertArrayEquals(
        "AXAXAXAX".getBytes("UTF-8"), actualAdjustRequestRangeResult.getByteBufferData().array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualAdjustRequestRangeResult.getByteData());
    HttpHeaders headers2 = actualAdjustRequestRangeResult.getHeaders();
    assertTrue(headers2 instanceof DefaultHttpHeaders);
    assertFalse(headers2.isEmpty());
    assertEquals(1, headers2.size());
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headers2).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertFalse(unwrapResult.isEmpty());
    Iterator<Entry<CharSequence, CharSequence>> iteratorResult = unwrapResult.iterator();
    Entry<CharSequence, CharSequence> actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertEquals(simpleEntry, actualNextResult);
    assertEquals(1, unwrapResult.size());
    verify(uri).getPort();
    verify(uri).getFragment();
    verify(uri).getHost();
    verify(uri).getPath();
    verify(uri).getQuery();
    verify(uri, atLeast(1)).getScheme();
    verify(uri).getUserInfo();
    verify(uri).toUrl();
    verify(headers).iterator();
    verify(headers).get(isA(CharSequence.class));
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   *
   * <ul>
   *   <li>When {@link HttpHeaders} {@link HttpHeaders#get(CharSequence)} return {@code
   *       https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName(
      "Test adjustRequestRange(Request); when HttpHeaders get(CharSequence) return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Request ResumableAsyncHandler.adjustRequestRange(Request)"})
  void testAdjustRequestRange_whenHttpHeadersGetReturnHttpsExampleOrgExample()
      throws UnsupportedEncodingException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");
    when(uri.toUrl()).thenReturn("https://example.org/example");

    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> resumableAsyncHandler.adjustRequestRange(request));
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getScheme();
    verify(uri).toUrl();
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   *
   * <ul>
   *   <li>When {@link Uri} {@link Uri#getQuery()} return empty string.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); when Uri getQuery() return empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Request ResumableAsyncHandler.adjustRequestRange(Request)"})
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
    when(headers.get(Mockito.<CharSequence>any())).thenReturn(null);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(request);

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
    assertEquals(
        "http://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri2.toJavaNetURI().toString());
    assertEquals(
        "http://https://example.org/example@https://example.org/example:8080https://example.org/example",
        actualAdjustRequestRangeResult.getUrl());
    assertNull(uri2.getQuery());
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(
        expectedArrayResult, actualAdjustRequestRangeResult.getByteBufferData().array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualAdjustRequestRangeResult.getByteData());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   *
   * <ul>
   *   <li>When {@link Uri} {@link Uri#getQuery()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); when Uri getQuery() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Request ResumableAsyncHandler.adjustRequestRange(Request)"})
  void testAdjustRequestRange_whenUriGetQueryReturnNull()
      throws UnsupportedEncodingException, URISyntaxException {
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
    when(headers.get(Mockito.<CharSequence>any())).thenReturn(null);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act
    Request actualAdjustRequestRangeResult = resumableAsyncHandler.adjustRequestRange(request);

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
    assertEquals(
        "http://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri2.toJavaNetURI().toString());
    assertEquals(
        "http://https://example.org/example@https://example.org/example:8080https://example.org/example",
        actualAdjustRequestRangeResult.getUrl());
    assertNull(uri2.getQuery());
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(
        expectedArrayResult, actualAdjustRequestRangeResult.getByteBufferData().array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualAdjustRequestRangeResult.getByteData());
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   *
   * <ul>
   *   <li>When {@link Uri} {@link Uri#getScheme()} return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName(
      "Test adjustRequestRange(Request); when Uri getScheme() return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Request ResumableAsyncHandler.adjustRequestRange(Request)"})
  void testAdjustRequestRange_whenUriGetSchemeReturnHttpsExampleOrgExample()
      throws UnsupportedEncodingException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn("https://example.org/example");
    when(uri.toUrl()).thenReturn("https://example.org/example");

    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn(null);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> resumableAsyncHandler.adjustRequestRange(request));
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getScheme();
    verify(uri).toUrl();
  }

  /**
   * Test {@link ResumableAsyncHandler#adjustRequestRange(Request)}.
   *
   * <ul>
   *   <li>When {@link Uri} {@link Uri#getScheme()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableAsyncHandler#adjustRequestRange(Request)}
   */
  @Test
  @DisplayName("Test adjustRequestRange(Request); when Uri getScheme() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Request ResumableAsyncHandler.adjustRequestRange(Request)"})
  void testAdjustRequestRange_whenUriGetSchemeReturnNull() throws UnsupportedEncodingException {
    // Arrange
    ResumableAsyncHandler resumableAsyncHandler = new ResumableAsyncHandler();

    Uri uri = mock(Uri.class);
    when(uri.getScheme()).thenReturn(null);
    when(uri.toUrl()).thenReturn("https://example.org/example");

    HttpHeaders headers = mock(HttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn(null);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest request =
        new DefaultRequest(
            "https://example.org/example",
            uri,
            address,
            localAddress,
            headers,
            cookies,
            byteData,
            compositeByteData,
            "https://example.org/example",
            byteBufferData,
            byteBufData,
            streamData,
            bodyGenerator,
            formParams,
            bodyParts,
            "https://example.org/example",
            proxyServer,
            realm,
            file,
            true,
            requestTimeout,
            readTimeout,
            1L,
            Charset.forName("UTF-8"),
            mock(ChannelPoolPartitioning.class),
            mock(NameResolver.class));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> resumableAsyncHandler.adjustRequestRange(request));
    verify(headers).get(isA(CharSequence.class));
    verify(headers).iterator();
    verify(uri).getScheme();
    verify(uri).toUrl();
  }
}
