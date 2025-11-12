package org.asynchttpclient.netty.ws;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.buffer.UnpooledHeapByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.DefaultChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.SucceededFuture;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NettyWebSocketDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NettyWebSocket#NettyWebSocket(Channel, HttpHeaders)}
   *   <li>{@link NettyWebSocket#toString()}
   *   <li>{@link NettyWebSocket#getUpgradeHeaders()}
   *   <li>{@link NettyWebSocket#isReady()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NettyWebSocket.<init>(Channel, HttpHeaders)",
    "HttpHeaders NettyWebSocket.getUpgradeHeaders()",
    "boolean NettyWebSocket.isReady()",
    "String NettyWebSocket.toString()"
  })
  void testGettersAndSetters() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    DefaultHttpHeaders upgradeHeaders = new DefaultHttpHeaders();

    // Act
    NettyWebSocket actualNettyWebSocket = new NettyWebSocket(channel, upgradeHeaders);
    String actualToStringResult = actualNettyWebSocket.toString();
    HttpHeaders actualUpgradeHeaders = actualNettyWebSocket.getUpgradeHeaders();

    // Assert
    assertEquals(
        "NettyWebSocket{channel=[id: 0xembedded, L:embedded - R:embedded]}", actualToStringResult);
    assertFalse(actualNettyWebSocket.isReady());
    assertSame(upgradeHeaders, actualUpgradeHeaders);
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)} with {@code ByteBuf}, {@code
   * boolean}, {@code int}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendTextFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendTextFrame(ByteBuf, boolean, int)"})
  void testSendTextFrameWithByteBufBooleanInt() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendTextFrameResult =
        nettyWebSocket.sendTextFrame(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true, 1);

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)} with {@code ByteBuf}, {@code
   * boolean}, {@code int}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendTextFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendTextFrame(ByteBuf, boolean, int)"})
  void testSendTextFrameWithByteBufBooleanInt2() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    Future<Void> actualSendTextFrameResult =
        nettyWebSocket.sendTextFrame(new ReadOnlyByteBuf(buffer), true, 1);

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)} with {@code ByteBuf}, {@code
   * boolean}, {@code int}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendTextFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendTextFrame(ByteBuf, boolean, int)"})
  void testSendTextFrameWithByteBufBooleanInt3() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    CompositeByteBuf buffer = Unpooled.compositeBuffer(3);

    // Act
    Future<Void> actualSendTextFrameResult =
        nettyWebSocket.sendTextFrame(new ReadOnlyByteBuf(buffer), true, 1);

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)} with {@code ByteBuf}, {@code
   * boolean}, {@code int}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendTextFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendTextFrame(ByteBuf, boolean, int)"})
  void testSendTextFrameWithByteBufBooleanInt4() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendTextFrameResult =
        nettyWebSocket.sendTextFrame(
            new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3), true, 1);

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)} with {@code ByteBuf}, {@code
   * boolean}, {@code int}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendTextFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'; given 'true'; then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendTextFrame(ByteBuf, boolean, int)"})
  void testSendTextFrameWithByteBufBooleanInt_givenTrue_thenCallsCapacity() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.refCnt()).thenReturn(1);
    when(buffer.touch(Mockito.<Object>any()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);

    // Act
    Future<Void> actualSendTextFrameResult =
        nettyWebSocket.sendTextFrame(new ReadOnlyByteBuf(buffer2), true, 1);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).touch(isA(Object.class));
    verify(buffer).writerIndex();
    verify(buffer).refCnt();
    verify(buffer).release();
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)} with {@code ByteBuf}, {@code
   * boolean}, {@code int}.
   *
   * <ul>
   *   <li>When compositeBuffer three.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendTextFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'; when compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendTextFrame(ByteBuf, boolean, int)"})
  void testSendTextFrameWithByteBufBooleanInt_whenCompositeBufferThree()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendTextFrameResult =
        nettyWebSocket.sendTextFrame(Unpooled.compositeBuffer(3), true, 1);

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(String, boolean, int)} with {@code String}, {@code
   * boolean}, {@code int}.
   *
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendTextFrame(String, boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendTextFrame(String, boolean, int) with 'String', 'boolean', 'int'; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendTextFrame(String, boolean, int)"})
  void testSendTextFrameWithStringBooleanInt_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendTextFrameResult =
        nettyWebSocket.sendTextFrame("https://example.org/example", true, 1);

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(String, boolean, int)} with {@code String}, {@code
   * boolean}, {@code int}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendTextFrame(String, boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendTextFrame(String, boolean, int) with 'String', 'boolean', 'int'; when empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendTextFrame(String, boolean, int)"})
  void testSendTextFrameWithStringBooleanInt_whenEmptyString()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendTextFrameResult = nettyWebSocket.sendTextFrame("", true, 1);

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(String)} with {@code String}.
   *
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendTextFrame(String)}
   */
  @Test
  @DisplayName("Test sendTextFrame(String) with 'String'; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendTextFrame(String)"})
  void testSendTextFrameWithString_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendTextFrameResult =
        nettyWebSocket.sendTextFrame("https://example.org/example");

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(String)} with {@code String}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendTextFrame(String)}
   */
  @Test
  @DisplayName(
      "Test sendTextFrame(String) with 'String'; when empty string; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendTextFrame(String)"})
  void testSendTextFrameWithString_whenEmptyString_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendTextFrameResult = nettyWebSocket.sendTextFrame("");

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(byte[], boolean, int)} with {@code byte[]}, {@code
   * boolean}, {@code int}.
   *
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendBinaryFrame(byte[], boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendBinaryFrame(byte[], boolean, int) with 'byte[]', 'boolean', 'int'; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendBinaryFrame(byte[], boolean, int)"})
  void testSendBinaryFrameWithByteBooleanInt_thenReturnDefaultChannelPromise()
      throws UnsupportedEncodingException, InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendBinaryFrameResult =
        nettyWebSocket.sendBinaryFrame("AXAXAXAX".getBytes("UTF-8"), true, 1);

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(byte[], boolean, int)} with {@code byte[]}, {@code
   * boolean}, {@code int}.
   *
   * <ul>
   *   <li>When empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendBinaryFrame(byte[], boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendBinaryFrame(byte[], boolean, int) with 'byte[]', 'boolean', 'int'; when empty array of byte")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendBinaryFrame(byte[], boolean, int)"})
  void testSendBinaryFrameWithByteBooleanInt_whenEmptyArrayOfByte()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendBinaryFrameResult =
        nettyWebSocket.sendBinaryFrame(new byte[] {}, true, 1);

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)} with {@code ByteBuf}, {@code
   * boolean}, {@code int}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendBinaryFrame(ByteBuf, boolean, int)"})
  void testSendBinaryFrameWithByteBufBooleanInt() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendBinaryFrameResult =
        nettyWebSocket.sendBinaryFrame(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true, 1);

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)} with {@code ByteBuf}, {@code
   * boolean}, {@code int}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendBinaryFrame(ByteBuf, boolean, int)"})
  void testSendBinaryFrameWithByteBufBooleanInt2() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    Future<Void> actualSendBinaryFrameResult =
        nettyWebSocket.sendBinaryFrame(new ReadOnlyByteBuf(buffer), true, 1);

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)} with {@code ByteBuf}, {@code
   * boolean}, {@code int}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendBinaryFrame(ByteBuf, boolean, int)"})
  void testSendBinaryFrameWithByteBufBooleanInt3() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    CompositeByteBuf buffer = Unpooled.compositeBuffer(3);

    // Act
    Future<Void> actualSendBinaryFrameResult =
        nettyWebSocket.sendBinaryFrame(new ReadOnlyByteBuf(buffer), true, 1);

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)} with {@code ByteBuf}, {@code
   * boolean}, {@code int}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendBinaryFrame(ByteBuf, boolean, int)"})
  void testSendBinaryFrameWithByteBufBooleanInt4() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendBinaryFrameResult =
        nettyWebSocket.sendBinaryFrame(
            new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3), true, 1);

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)} with {@code ByteBuf}, {@code
   * boolean}, {@code int}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendBinaryFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'; given 'true'; then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendBinaryFrame(ByteBuf, boolean, int)"})
  void testSendBinaryFrameWithByteBufBooleanInt_givenTrue_thenCallsCapacity() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.refCnt()).thenReturn(1);
    when(buffer.touch(Mockito.<Object>any()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);

    // Act
    Future<Void> actualSendBinaryFrameResult =
        nettyWebSocket.sendBinaryFrame(new ReadOnlyByteBuf(buffer2), true, 1);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).touch(isA(Object.class));
    verify(buffer).writerIndex();
    verify(buffer).refCnt();
    verify(buffer).release();
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)} with {@code ByteBuf}, {@code
   * boolean}, {@code int}.
   *
   * <ul>
   *   <li>When compositeBuffer three.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendBinaryFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'; when compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendBinaryFrame(ByteBuf, boolean, int)"})
  void testSendBinaryFrameWithByteBufBooleanInt_whenCompositeBufferThree()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendBinaryFrameResult =
        nettyWebSocket.sendBinaryFrame(Unpooled.compositeBuffer(3), true, 1);

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(byte[])} with {@code byte[]}.
   *
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendBinaryFrame(byte[])}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(byte[]) with 'byte[]'; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendBinaryFrame(byte[])"})
  void testSendBinaryFrameWithByte_thenReturnDefaultChannelPromise()
      throws UnsupportedEncodingException, InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendBinaryFrameResult =
        nettyWebSocket.sendBinaryFrame("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(byte[])} with {@code byte[]}.
   *
   * <ul>
   *   <li>When empty array of {@code byte}.
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendBinaryFrame(byte[])}
   */
  @Test
  @DisplayName(
      "Test sendBinaryFrame(byte[]) with 'byte[]'; when empty array of byte; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendBinaryFrame(byte[])"})
  void testSendBinaryFrameWithByte_whenEmptyArrayOfByte_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendBinaryFrameResult = nettyWebSocket.sendBinaryFrame(new byte[] {});

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(byte[], boolean, int)} with {@code byte[]},
   * {@code boolean}, {@code int}.
   *
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendContinuationFrame(byte[], boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendContinuationFrame(byte[], boolean, int) with 'byte[]', 'boolean', 'int'; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendContinuationFrame(byte[], boolean, int)"})
  void testSendContinuationFrameWithByteBooleanInt_thenReturnDefaultChannelPromise()
      throws UnsupportedEncodingException, InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendContinuationFrameResult =
        nettyWebSocket.sendContinuationFrame("AXAXAXAX".getBytes("UTF-8"), true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(byte[], boolean, int)} with {@code byte[]},
   * {@code boolean}, {@code int}.
   *
   * <ul>
   *   <li>When empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendContinuationFrame(byte[], boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendContinuationFrame(byte[], boolean, int) with 'byte[]', 'boolean', 'int'; when empty array of byte")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendContinuationFrame(byte[], boolean, int)"})
  void testSendContinuationFrameWithByteBooleanInt_whenEmptyArrayOfByte()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendContinuationFrameResult =
        nettyWebSocket.sendContinuationFrame(new byte[] {}, true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)} with {@code ByteBuf},
   * {@code boolean}, {@code int}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendContinuationFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendContinuationFrame(ByteBuf, boolean, int)"})
  void testSendContinuationFrameWithByteBufBooleanInt()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendContinuationFrameResult =
        nettyWebSocket.sendContinuationFrame(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)} with {@code ByteBuf},
   * {@code boolean}, {@code int}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendContinuationFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendContinuationFrame(ByteBuf, boolean, int)"})
  void testSendContinuationFrameWithByteBufBooleanInt2()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    Future<Void> actualSendContinuationFrameResult =
        nettyWebSocket.sendContinuationFrame(new ReadOnlyByteBuf(buffer), true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)} with {@code ByteBuf},
   * {@code boolean}, {@code int}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendContinuationFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendContinuationFrame(ByteBuf, boolean, int)"})
  void testSendContinuationFrameWithByteBufBooleanInt3()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    CompositeByteBuf buffer = Unpooled.compositeBuffer(3);

    // Act
    Future<Void> actualSendContinuationFrameResult =
        nettyWebSocket.sendContinuationFrame(new ReadOnlyByteBuf(buffer), true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)} with {@code ByteBuf},
   * {@code boolean}, {@code int}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendContinuationFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendContinuationFrame(ByteBuf, boolean, int)"})
  void testSendContinuationFrameWithByteBufBooleanInt4()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendContinuationFrameResult =
        nettyWebSocket.sendContinuationFrame(
            new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3), true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)} with {@code ByteBuf},
   * {@code boolean}, {@code int}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendContinuationFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'; given 'true'; then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendContinuationFrame(ByteBuf, boolean, int)"})
  void testSendContinuationFrameWithByteBufBooleanInt_givenTrue_thenCallsCapacity() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.refCnt()).thenReturn(1);
    when(buffer.touch(Mockito.<Object>any()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);

    // Act
    Future<Void> actualSendContinuationFrameResult =
        nettyWebSocket.sendContinuationFrame(new ReadOnlyByteBuf(buffer2), true, 1);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).touch(isA(Object.class));
    verify(buffer).writerIndex();
    verify(buffer).refCnt();
    verify(buffer).release();
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)} with {@code ByteBuf},
   * {@code boolean}, {@code int}.
   *
   * <ul>
   *   <li>When compositeBuffer three.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendContinuationFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'; when compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendContinuationFrame(ByteBuf, boolean, int)"})
  void testSendContinuationFrameWithByteBufBooleanInt_whenCompositeBufferThree()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendContinuationFrameResult =
        nettyWebSocket.sendContinuationFrame(Unpooled.compositeBuffer(3), true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(String, boolean, int)} with {@code String},
   * {@code boolean}, {@code int}.
   *
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendContinuationFrame(String, boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendContinuationFrame(String, boolean, int) with 'String', 'boolean', 'int'; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendContinuationFrame(String, boolean, int)"})
  void testSendContinuationFrameWithStringBooleanInt_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendContinuationFrameResult =
        nettyWebSocket.sendContinuationFrame("https://example.org/example", true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(String, boolean, int)} with {@code String},
   * {@code boolean}, {@code int}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendContinuationFrame(String, boolean, int)}
   */
  @Test
  @DisplayName(
      "Test sendContinuationFrame(String, boolean, int) with 'String', 'boolean', 'int'; when empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendContinuationFrame(String, boolean, int)"})
  void testSendContinuationFrameWithStringBooleanInt_whenEmptyString()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendContinuationFrameResult =
        nettyWebSocket.sendContinuationFrame("", true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPingFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPingFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPingFrame(ByteBuf)"})
  void testSendPingFrameWithByteBuf() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPingFrameResult =
        nettyWebSocket.sendPingFrame(
            new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3));

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPingFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPingFrame(ByteBuf) with 'ByteBuf'; given 'true'; then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPingFrame(ByteBuf)"})
  void testSendPingFrameWithByteBuf_givenTrue_thenCallsCapacity() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.refCnt()).thenReturn(1);
    when(buffer.touch(Mockito.<Object>any()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);

    // Act
    Future<Void> actualSendPingFrameResult =
        nettyWebSocket.sendPingFrame(new ReadOnlyByteBuf(buffer2));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).touch(isA(Object.class));
    verify(buffer).writerIndex();
    verify(buffer).refCnt();
    verify(buffer).release();
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>When compositeBuffer three.
   *   <li>Then return {@link Future#get()} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPingFrame(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test sendPingFrame(ByteBuf) with 'ByteBuf'; when compositeBuffer three; then return get() is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPingFrame(ByteBuf)"})
  void testSendPingFrameWithByteBuf_whenCompositeBufferThree_thenReturnGetIsNull()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPingFrameResult =
        nettyWebSocket.sendPingFrame(Unpooled.compositeBuffer(3));

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>When {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link
   *       AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPingFrame(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test sendPingFrame(ByteBuf) with 'ByteBuf'; when EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPingFrame(ByteBuf)"})
  void testSendPingFrameWithByteBuf_whenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPingFrameResult =
        nettyWebSocket.sendPingFrame(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>When {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link
   *       AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPingFrame(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test sendPingFrame(ByteBuf) with 'ByteBuf'; when EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPingFrame(ByteBuf)"})
  void testSendPingFrameWithByteBuf_whenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator2()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    Future<Void> actualSendPingFrameResult =
        nettyWebSocket.sendPingFrame(new ReadOnlyByteBuf(buffer));

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>When {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is compositeBuffer
   *       three.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPingFrame(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test sendPingFrame(ByteBuf) with 'ByteBuf'; when ReadOnlyByteBuf(ByteBuf) with buffer is compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPingFrame(ByteBuf)"})
  void testSendPingFrameWithByteBuf_whenReadOnlyByteBufWithBufferIsCompositeBufferThree()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    CompositeByteBuf buffer = Unpooled.compositeBuffer(3);

    // Act
    Future<Void> actualSendPingFrameResult =
        nettyWebSocket.sendPingFrame(new ReadOnlyByteBuf(buffer));

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(byte[])} with {@code byte[]}.
   *
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPingFrame(byte[])}
   */
  @Test
  @DisplayName("Test sendPingFrame(byte[]) with 'byte[]'; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPingFrame(byte[])"})
  void testSendPingFrameWithByte_thenReturnDefaultChannelPromise()
      throws UnsupportedEncodingException, InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPingFrameResult =
        nettyWebSocket.sendPingFrame("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(byte[])} with {@code byte[]}.
   *
   * <ul>
   *   <li>When empty array of {@code byte}.
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPingFrame(byte[])}
   */
  @Test
  @DisplayName(
      "Test sendPingFrame(byte[]) with 'byte[]'; when empty array of byte; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPingFrame(byte[])"})
  void testSendPingFrameWithByte_whenEmptyArrayOfByte_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPingFrameResult = nettyWebSocket.sendPingFrame(new byte[] {});

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame()}.
   *
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPingFrame()}
   */
  @Test
  @DisplayName("Test sendPingFrame(); then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPingFrame()"})
  void testSendPingFrame_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPingFrameResult = nettyWebSocket.sendPingFrame();

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf payload =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf2() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ReadOnlyByteBuf payload = new ReadOnlyByteBuf(buffer);

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.capacity());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf3() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    UnpooledDirectByteBuf payload = new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3);

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.refCnt());
    assertTrue(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf4() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    UnpooledHeapByteBuf payload = new UnpooledHeapByteBuf(new AdaptiveByteBufAllocator(), 1, 3);

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.refCnt());
    assertEquals(0, payload.capacity());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf5() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    CompositeByteBuf payload = Unpooled.compositeBuffer(3);
    payload.addComponent(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.refCnt());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf6() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    CompositeByteBuf buffer = Unpooled.compositeBuffer(3);
    ReadOnlyByteBuf payload = new ReadOnlyByteBuf(buffer);

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    ByteBuf unwrapResult = payload.unwrap();
    assertTrue(unwrapResult instanceof CompositeByteBuf);
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.capacity());
    assertEquals(0, unwrapResult.refCnt());
    assertFalse(unwrapResult.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf7() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    ReadOnlyByteBuf payload =
        new ReadOnlyByteBuf(new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3));

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    ByteBuf unwrapResult = payload.unwrap();
    assertTrue(unwrapResult instanceof UnpooledDirectByteBuf);
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, unwrapResult.refCnt());
    assertEquals(1, payload.capacity());
    assertTrue(unwrapResult.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf8() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    CompositeByteBuf payload = Unpooled.compositeBuffer(3);
    payload.addComponent(new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3));

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.refCnt());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf9() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    ReadOnlyByteBuf payload =
        new ReadOnlyByteBuf(new UnpooledHeapByteBuf(new AdaptiveByteBufAllocator(), 1, 3));

    // Act
    nettyWebSocket.sendPongFrame(payload);

    // Assert
    ByteBuf unwrapResult = payload.unwrap();
    assertTrue(unwrapResult instanceof UnpooledHeapByteBuf);
    assertEquals(0, unwrapResult.capacity());
    assertEquals(0, payload.capacity());
    assertEquals(0, unwrapResult.refCnt());
    assertFalse(unwrapResult.isWritable());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf10() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    UnpooledDirectByteBuf payload = new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3);
    payload.writerIndex(1);

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(1, payload.refCnt());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf11() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    UnpooledHeapByteBuf payload = new UnpooledHeapByteBuf(new AdaptiveByteBufAllocator(), 1, 3);
    payload.writerIndex(1);

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(1, payload.refCnt());
    assertEquals(1, payload.capacity());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf12() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    CompositeByteBuf payload = Unpooled.compositeBuffer(3);
    payload.addComponent(new UnpooledHeapByteBuf(new AdaptiveByteBufAllocator(), 1, 3));

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.refCnt());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>Given compositeBuffer three.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'; given compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf_givenCompositeBufferThree()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    CompositeByteBuf payload = Unpooled.compositeBuffer(3);
    payload.addComponent(Unpooled.compositeBuffer(3));

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.refCnt());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>Given forty-two.
   *   <li>Then compositeBuffer three refCnt is one.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test sendPongFrame(ByteBuf) with 'ByteBuf'; given forty-two; then compositeBuffer three refCnt is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf_givenFortyTwo_thenCompositeBufferThreeRefCntIsOne()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    CompositeByteBuf payload = Unpooled.compositeBuffer(3);
    payload.writeByte(42);
    payload.addComponent(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(1, payload.refCnt());
    assertTrue(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is {@link
   *       DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test sendPongFrame(ByteBuf) with 'ByteBuf'; given ReadOnlyByteBuf(ByteBuf) with buffer is DuplicatedByteBuf(ByteBuf)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf_givenReadOnlyByteBufWithBufferIsDuplicatedByteBuf()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    CompositeByteBuf payload = Unpooled.compositeBuffer(3);
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    payload.addComponent(new ReadOnlyByteBuf(buffer));

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.refCnt());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>When compositeBuffer three.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'; when compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf_whenCompositeBufferThree()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    CompositeByteBuf payload = Unpooled.compositeBuffer(3);

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.refCnt());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>When {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link
   *       AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test sendPongFrame(ByteBuf) with 'ByteBuf'; when EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(ByteBuf)"})
  void testSendPongFrameWithByteBuf_whenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPongFrameResult =
        nettyWebSocket.sendPongFrame(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(byte[])} with {@code byte[]}.
   *
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(byte[])}
   */
  @Test
  @DisplayName("Test sendPongFrame(byte[]) with 'byte[]'; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(byte[])"})
  void testSendPongFrameWithByte_thenReturnDefaultChannelPromise()
      throws UnsupportedEncodingException, InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPongFrameResult =
        nettyWebSocket.sendPongFrame("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(byte[])} with {@code byte[]}.
   *
   * <ul>
   *   <li>When empty array of {@code byte}.
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame(byte[])}
   */
  @Test
  @DisplayName(
      "Test sendPongFrame(byte[]) with 'byte[]'; when empty array of byte; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame(byte[])"})
  void testSendPongFrameWithByte_whenEmptyArrayOfByte_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(new byte[] {});

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame()}.
   *
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendPongFrame()}
   */
  @Test
  @DisplayName("Test sendPongFrame(); then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendPongFrame()"})
  void testSendPongFrame_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame();

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendCloseFrame(int, String)} with {@code int}, {@code String}.
   *
   * <ul>
   *   <li>When {@code 1007}.
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendCloseFrame(int, String)}
   */
  @Test
  @DisplayName(
      "Test sendCloseFrame(int, String) with 'int', 'String'; when '1007'; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendCloseFrame(int, String)"})
  void testSendCloseFrameWithIntString_when1007_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendCloseFrameResult = nettyWebSocket.sendCloseFrame(1007, "");

    // Assert
    assertTrue(actualSendCloseFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendCloseFrameResult.get());
    assertTrue(actualSendCloseFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendCloseFrame(int, String)} with {@code int}, {@code String}.
   *
   * <ul>
   *   <li>When {@code 3000}.
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendCloseFrame(int, String)}
   */
  @Test
  @DisplayName(
      "Test sendCloseFrame(int, String) with 'int', 'String'; when '3000'; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendCloseFrame(int, String)"})
  void testSendCloseFrameWithIntString_when3000_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendCloseFrameResult = nettyWebSocket.sendCloseFrame(3000, "");

    // Assert
    assertTrue(actualSendCloseFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendCloseFrameResult.get());
    assertTrue(actualSendCloseFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendCloseFrame(int, String)} with {@code int}, {@code String}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendCloseFrame(int, String)}
   */
  @Test
  @DisplayName(
      "Test sendCloseFrame(int, String) with 'int', 'String'; when minus one; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendCloseFrame(int, String)"})
  void testSendCloseFrameWithIntString_whenMinusOne_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendCloseFrameResult = nettyWebSocket.sendCloseFrame(-1, "");

    // Assert
    assertTrue(actualSendCloseFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendCloseFrameResult.get());
    assertTrue(actualSendCloseFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendCloseFrame(int, String)} with {@code int}, {@code String}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendCloseFrame(int, String)}
   */
  @Test
  @DisplayName(
      "Test sendCloseFrame(int, String) with 'int', 'String'; when minus one; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendCloseFrame(int, String)"})
  void testSendCloseFrameWithIntString_whenMinusOne_thenReturnDefaultChannelPromise2()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendCloseFrameResult =
        nettyWebSocket.sendCloseFrame(-1, "https://example.org/example");

    // Assert
    assertTrue(actualSendCloseFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendCloseFrameResult.get());
    assertTrue(actualSendCloseFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendCloseFrame(int, String)} with {@code int}, {@code String}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendCloseFrame(int, String)}
   */
  @Test
  @DisplayName(
      "Test sendCloseFrame(int, String) with 'int', 'String'; when 'null'; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendCloseFrame(int, String)"})
  void testSendCloseFrameWithIntString_whenNull_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendCloseFrameResult = nettyWebSocket.sendCloseFrame(-1, null);

    // Assert
    assertTrue(actualSendCloseFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendCloseFrameResult.get());
    assertTrue(actualSendCloseFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendCloseFrame(int, String)} with {@code int}, {@code String}.
   *
   * <ul>
   *   <li>When one thousand.
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendCloseFrame(int, String)}
   */
  @Test
  @DisplayName(
      "Test sendCloseFrame(int, String) with 'int', 'String'; when one thousand; then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendCloseFrame(int, String)"})
  void testSendCloseFrameWithIntString_whenOneThousand_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendCloseFrameResult = nettyWebSocket.sendCloseFrame(1000, "");

    // Assert
    assertTrue(actualSendCloseFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendCloseFrameResult.get());
    assertTrue(actualSendCloseFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendCloseFrame()}.
   *
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendCloseFrame()}
   */
  @Test
  @DisplayName("Test sendCloseFrame(); then return DefaultChannelPromise")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendCloseFrame()"})
  void testSendCloseFrame_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendCloseFrameResult = nettyWebSocket.sendCloseFrame();

    // Assert
    assertTrue(actualSendCloseFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendCloseFrameResult.get());
    assertTrue(actualSendCloseFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendCloseFrame()}.
   *
   * <ul>
   *   <li>Then return {@link SucceededFuture}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#sendCloseFrame()}
   */
  @Test
  @DisplayName("Test sendCloseFrame(); then return SucceededFuture")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Future NettyWebSocket.sendCloseFrame()"})
  void testSendCloseFrame_thenReturnSucceededFuture()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.handleFrame(new CloseWebSocketFrame());

    // Act
    Future<Void> actualSendCloseFrameResult = nettyWebSocket.sendCloseFrame();

    // Assert
    assertTrue(actualSendCloseFrameResult instanceof SucceededFuture);
    assertNull(actualSendCloseFrameResult.get());
    assertTrue(actualSendCloseFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#isOpen()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#isOpen()}
   */
  @Test
  @DisplayName("Test isOpen(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyWebSocket.isOpen()"})
  void testIsOpen_thenReturnTrue() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act and Assert
    assertTrue(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#addWebSocketListener(WebSocketListener)}.
   *
   * <p>Method under test: {@link NettyWebSocket#addWebSocketListener(WebSocketListener)}
   */
  @Test
  @DisplayName("Test addWebSocketListener(WebSocketListener)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"WebSocket NettyWebSocket.addWebSocketListener(WebSocketListener)"})
  void testAddWebSocketListener() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    WebSocket actualAddWebSocketListenerResult =
        nettyWebSocket.addWebSocketListener(mock(WebSocketListener.class));

    // Assert
    assertSame(nettyWebSocket, actualAddWebSocketListenerResult);
  }

  /**
   * Test {@link NettyWebSocket#removeWebSocketListener(WebSocketListener)}.
   *
   * <p>Method under test: {@link NettyWebSocket#removeWebSocketListener(WebSocketListener)}
   */
  @Test
  @DisplayName("Test removeWebSocketListener(WebSocketListener)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"WebSocket NettyWebSocket.removeWebSocketListener(WebSocketListener)"})
  void testRemoveWebSocketListener() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    WebSocket actualRemoveWebSocketListenerResult =
        nettyWebSocket.removeWebSocketListener(mock(WebSocketListener.class));

    // Assert
    assertSame(nettyWebSocket, actualRemoveWebSocketListenerResult);
  }

  /**
   * Test {@link NettyWebSocket#bufferFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>Given {@link BinaryWebSocketFrame#BinaryWebSocketFrame()}.
   *   <li>Then calls {@link BinaryWebSocketFrame#retain()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#bufferFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test bufferFrame(WebSocketFrame); given BinaryWebSocketFrame(); then calls retain()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.bufferFrame(WebSocketFrame)"})
  void testBufferFrame_givenBinaryWebSocketFrame_thenCallsRetain() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    BinaryWebSocketFrame frame = mock(BinaryWebSocketFrame.class);
    when(frame.retain()).thenReturn(new BinaryWebSocketFrame());

    // Act
    nettyWebSocket.bufferFrame(frame);

    // Assert
    verify(frame).retain();
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames2() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames3() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new TextWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames4() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new CloseWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertNull(nettyWebSocket.getLocalAddress());
    assertNull(nettyWebSocket.getRemoteAddress());
    assertFalse(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames5() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(
        new BinaryWebSocketFrame(true, 1, new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames6() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new ContinuationWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames7() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new PingWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames8() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new PongWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames9() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new PongWebSocketFrame(true, 1, Unpooled.compositeBuffer(3)));

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames10() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(
        new PongWebSocketFrame(
            true, 1, new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3)));

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames11() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException()).when(l).onPongFrame(Mockito.<byte[]>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new PongWebSocketFrame(true, 1, Unpooled.compositeBuffer(3)));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.processBufferedFrames());
    verify(l).onPongFrame(isA(byte[].class));
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames12() {
    // Arrange
    CompositeByteBuf binaryData = Unpooled.compositeBuffer(3);
    binaryData.addComponent(
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    PongWebSocketFrame frame = new PongWebSocketFrame(true, 1, binaryData);

    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onPongFrame(Mockito.<byte[]>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(frame);

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    verify(l).onPongFrame(isA(byte[].class));
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames13() {
    // Arrange
    CompositeByteBuf binaryData = Unpooled.compositeBuffer(3);
    binaryData.addComponent(
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    binaryData.addComponent(
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    PongWebSocketFrame frame = new PongWebSocketFrame(true, 1, binaryData);

    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onPongFrame(Mockito.<byte[]>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(frame);

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    verify(l).onPongFrame(isA(byte[].class));
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames14() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException())
        .when(l)
        .onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.processBufferedFrames());
    verify(l).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames15() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException())
        .when(l)
        .onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new TextWebSocketFrame());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.processBufferedFrames());
    verify(l).onTextFrame("", true, 0);
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames16() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new TextWebSocketFrame("https://example.org/example"));

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    verify(l).onTextFrame("https://example.org/example", true, 0);
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames17() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException()).when(l).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException())
        .when(l)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new CloseWebSocketFrame());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.processBufferedFrames());
    verify(l).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <ul>
   *   <li>Given compositeBuffer three writeByte forty-two.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames(); given compositeBuffer three writeByte forty-two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames_givenCompositeBufferThreeWriteByteFortyTwo() {
    // Arrange
    CompositeByteBuf binaryData = Unpooled.compositeBuffer(3);
    binaryData.writeByte(42);
    binaryData.addComponent(
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    PongWebSocketFrame frame = new PongWebSocketFrame(true, 1, binaryData);

    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onPongFrame(Mockito.<byte[]>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(frame);

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    verify(l).onPongFrame(isA(byte[].class));
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName(
      "Test processBufferedFrames(); given DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames_givenDuplicatedByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf binaryData =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame(binaryData));

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName(
      "Test processBufferedFrames(); given ReadOnlyByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames_givenReadOnlyByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    ReadOnlyByteBuf binaryData =
        new ReadOnlyByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame(true, 1, binaryData));

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onBinaryFrame(byte[], boolean,
   *       int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName(
      "Test processBufferedFrames(); given WebSocketListener onBinaryFrame(byte[], boolean, int) does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames_givenWebSocketListenerOnBinaryFrameDoesNothing() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    verify(l).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onClose(WebSocket, int, String)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName(
      "Test processBufferedFrames(); given WebSocketListener onClose(WebSocket, int, String) does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames_givenWebSocketListenerOnCloseDoesNothing() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new CloseWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    verify(l).onClose(isA(WebSocket.class), eq(-1), eq(""));
    assertNull(nettyWebSocket.getLocalAddress());
    assertNull(nettyWebSocket.getRemoteAddress());
    assertFalse(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onError(Throwable)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName(
      "Test processBufferedFrames(); given WebSocketListener onError(Throwable) does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames_givenWebSocketListenerOnErrorDoesNothing() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException())
        .when(l)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new CloseWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    verify(l).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onError(isA(Throwable.class));
    assertNull(nettyWebSocket.getLocalAddress());
    assertNull(nettyWebSocket.getRemoteAddress());
    assertFalse(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onPongFrame(byte[])} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName(
      "Test processBufferedFrames(); given WebSocketListener onPongFrame(byte[]) does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames_givenWebSocketListenerOnPongFrameDoesNothing() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onPongFrame(Mockito.<byte[]>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new PongWebSocketFrame(true, 1, Unpooled.compositeBuffer(3)));

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    verify(l).onPongFrame(isA(byte[].class));
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onTextFrame(String, boolean,
   *       int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName(
      "Test processBufferedFrames(); given WebSocketListener onTextFrame(String, boolean, int) does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames_givenWebSocketListenerOnTextFrameDoesNothing() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new TextWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    verify(l).onTextFrame("", true, 0);
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   *
   * <ul>
   *   <li>Then calls {@link WebSocketListener#onPingFrame(byte[])}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames(); then calls onPingFrame(byte[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.processBufferedFrames()"})
  void testProcessBufferedFrames_thenCallsOnPingFrame() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onPingFrame(Mockito.<byte[]>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new PingWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    verify(l).onPingFrame(isA(byte[].class));
    assertTrue(nettyWebSocket.isOpen());
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    nettyWebSocket.handleFrame(new BinaryWebSocketFrame());

    // Assert that nothing has changed
    assertTrue(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame2() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException())
        .when(l)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new CloseWebSocketFrame());

    // Assert
    verify(l).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onError(isA(Throwable.class));
    assertNull(nettyWebSocket.getLocalAddress());
    assertNull(nettyWebSocket.getRemoteAddress());
    assertFalse(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame3() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(
        new BinaryWebSocketFrame(true, 1, new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert that nothing has changed
    verify(l).onBinaryFrame(isA(byte[].class), eq(true), eq(1));
    assertTrue(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onBinaryFrame(byte[], boolean,
   *       int)} throw {@link IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); given WebSocketListener onBinaryFrame(byte[], boolean, int) throw IllegalArgumentException()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_givenWebSocketListenerOnBinaryFrameThrowIllegalArgumentException() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException())
        .when(l)
        .onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> nettyWebSocket.handleFrame(new BinaryWebSocketFrame()));
    verify(l).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onClose(WebSocket, int, String)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); given WebSocketListener onClose(WebSocket, int, String) does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_givenWebSocketListenerOnCloseDoesNothing() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new CloseWebSocketFrame());

    // Assert
    verify(l).onClose(isA(WebSocket.class), eq(-1), eq(""));
    assertNull(nettyWebSocket.getLocalAddress());
    assertNull(nettyWebSocket.getRemoteAddress());
    assertFalse(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onError(Throwable)} throw {@link
   *       IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); given WebSocketListener onError(Throwable) throw IllegalArgumentException()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_givenWebSocketListenerOnErrorThrowIllegalArgumentException() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException()).when(l).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException())
        .when(l)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> nettyWebSocket.handleFrame(new CloseWebSocketFrame()));
    verify(l).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onPingFrame(byte[])} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); given WebSocketListener onPingFrame(byte[]) does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_givenWebSocketListenerOnPingFrameDoesNothing() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onPingFrame(Mockito.<byte[]>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new PingWebSocketFrame());

    // Assert that nothing has changed
    verify(l).onPingFrame(isA(byte[].class));
    assertTrue(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onPingFrame(byte[])} throw
   *       {@link IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); given WebSocketListener onPingFrame(byte[]) throw IllegalArgumentException()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_givenWebSocketListenerOnPingFrameThrowIllegalArgumentException() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException()).when(l).onPingFrame(Mockito.<byte[]>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(new PingWebSocketFrame()));
    verify(l).onPingFrame(isA(byte[].class));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onPongFrame(byte[])} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); given WebSocketListener onPongFrame(byte[]) does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_givenWebSocketListenerOnPongFrameDoesNothing() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onPongFrame(Mockito.<byte[]>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new PongWebSocketFrame());

    // Assert that nothing has changed
    verify(l).onPongFrame(isA(byte[].class));
    assertTrue(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onPongFrame(byte[])} throw
   *       {@link IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); given WebSocketListener onPongFrame(byte[]) throw IllegalArgumentException()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_givenWebSocketListenerOnPongFrameThrowIllegalArgumentException() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException()).when(l).onPongFrame(Mockito.<byte[]>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(new PongWebSocketFrame()));
    verify(l).onPongFrame(isA(byte[].class));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onTextFrame(String, boolean,
   *       int)} throw {@link IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); given WebSocketListener onTextFrame(String, boolean, int) throw IllegalArgumentException()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_givenWebSocketListenerOnTextFrameThrowIllegalArgumentException() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException())
        .when(l)
        .onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(new TextWebSocketFrame()));
    verify(l).onTextFrame("", true, 0);
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>When {@link BinaryWebSocketFrame#BinaryWebSocketFrame()}.
   *   <li>Then calls {@link WebSocketListener#onBinaryFrame(byte[], boolean, int)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); when BinaryWebSocketFrame(); then calls onBinaryFrame(byte[], boolean, int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_whenBinaryWebSocketFrame_thenCallsOnBinaryFrame() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new BinaryWebSocketFrame());

    // Assert that nothing has changed
    verify(l).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
    assertTrue(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>When {@link ContinuationWebSocketFrame#ContinuationWebSocketFrame()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); when ContinuationWebSocketFrame()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_whenContinuationWebSocketFrame() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(mock(WebSocketListener.class));

    // Act
    nettyWebSocket.handleFrame(new ContinuationWebSocketFrame());

    // Assert that nothing has changed
    assertTrue(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>When {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); when DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_whenDuplicatedByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    DuplicatedByteBuf binaryData =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    nettyWebSocket.handleFrame(new BinaryWebSocketFrame(binaryData));

    // Assert that nothing has changed
    verify(l).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
    assertTrue(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); when 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_whenNull() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(mock(WebSocketListener.class));

    // Act
    nettyWebSocket.handleFrame(null);

    // Assert that nothing has changed
    assertTrue(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>When {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); when ReadOnlyByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_whenReadOnlyByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    ReadOnlyByteBuf binaryData =
        new ReadOnlyByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    nettyWebSocket.handleFrame(new BinaryWebSocketFrame(true, 1, binaryData));

    // Assert that nothing has changed
    verify(l).onBinaryFrame(isA(byte[].class), eq(true), eq(1));
    assertTrue(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>When {@link TextWebSocketFrame#TextWebSocketFrame(String)} with text is {@code
   *       https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); when TextWebSocketFrame(String) with text is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_whenTextWebSocketFrameWithTextIsHttpsExampleOrgExample() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new TextWebSocketFrame("https://example.org/example"));

    // Assert that nothing has changed
    verify(l).onTextFrame("https://example.org/example", true, 0);
    assertTrue(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   *
   * <ul>
   *   <li>When {@link TextWebSocketFrame#TextWebSocketFrame()}.
   *   <li>Then calls {@link WebSocketListener#onTextFrame(String, boolean, int)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName(
      "Test handleFrame(WebSocketFrame); when TextWebSocketFrame(); then calls onTextFrame(String, boolean, int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.handleFrame(WebSocketFrame)"})
  void testHandleFrame_whenTextWebSocketFrame_thenCallsOnTextFrame() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new TextWebSocketFrame());

    // Assert that nothing has changed
    verify(l).onTextFrame("", true, 0);
    assertTrue(nettyWebSocket.isOpen());
  }

  /**
   * Test {@link NettyWebSocket#onError(Throwable)}.
   *
   * <p>Method under test: {@link NettyWebSocket#onError(Throwable)}
   */
  @Test
  @DisplayName("Test onError(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.onError(Throwable)"})
  void testOnError() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame());

    // Act
    nettyWebSocket.onError(ChannelClosedException.INSTANCE);

    // Assert
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onError(Throwable)}.
   *
   * <p>Method under test: {@link NettyWebSocket#onError(Throwable)}
   */
  @Test
  @DisplayName("Test onError(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.onError(Throwable)"})
  void testOnError2() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());

    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.retain())
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf binaryData = new DuplicatedByteBuf(buffer);
    BinaryWebSocketFrame frame = new BinaryWebSocketFrame(true, 1, binaryData);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(frame);

    // Act
    nettyWebSocket.onError(ChannelClosedException.INSTANCE);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).retain();
    verify(buffer).writerIndex();
    verify(buffer).release();
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onError(Throwable)}.
   *
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is {@link
   *       DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)}.
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#onError(Throwable)}
   */
  @Test
  @DisplayName(
      "Test onError(Throwable); given ReadOnlyByteBuf(ByteBuf) with buffer is DuplicatedByteBuf(ByteBuf); then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.onError(Throwable)"})
  void testOnError_givenReadOnlyByteBufWithBufferIsDuplicatedByteBuf_thenCallsCapacity() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());

    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.retain())
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);
    ReadOnlyByteBuf binaryData = new ReadOnlyByteBuf(buffer2);
    BinaryWebSocketFrame frame = new BinaryWebSocketFrame(true, 1, binaryData);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(frame);

    // Act
    nettyWebSocket.onError(ChannelClosedException.INSTANCE);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).retain();
    verify(buffer).writerIndex();
    verify(buffer).release();
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onError(Throwable)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onError(Throwable)} throw {@link
   *       IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#onError(Throwable)}
   */
  @Test
  @DisplayName(
      "Test onError(Throwable); given WebSocketListener onError(Throwable) throw IllegalArgumentException()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.onError(Throwable)"})
  void testOnError_givenWebSocketListenerOnErrorThrowIllegalArgumentException() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException()).when(l).onError(Mockito.<Throwable>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame());

    // Act
    nettyWebSocket.onError(ChannelClosedException.INSTANCE);

    // Assert
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onClose(int, String)}.
   *
   * <p>Method under test: {@link NettyWebSocket#onClose(int, String)}
   */
  @Test
  @DisplayName("Test onClose(int, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.onClose(int, String)"})
  void testOnClose() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException())
        .when(l)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());

    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.retain())
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf binaryData = new DuplicatedByteBuf(buffer);
    BinaryWebSocketFrame frame = new BinaryWebSocketFrame(true, 1, binaryData);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(frame);

    // Act
    nettyWebSocket.onClose(1, "https://example.org/example");

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).retain();
    verify(buffer).writerIndex();
    verify(buffer).release();
    verify(l).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onClose(int, String)}.
   *
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is {@link
   *       DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)}.
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#onClose(int, String)}
   */
  @Test
  @DisplayName(
      "Test onClose(int, String); given ReadOnlyByteBuf(ByteBuf) with buffer is DuplicatedByteBuf(ByteBuf); then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.onClose(int, String)"})
  void testOnClose_givenReadOnlyByteBufWithBufferIsDuplicatedByteBuf_thenCallsCapacity() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException())
        .when(l)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());

    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.retain())
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);
    ReadOnlyByteBuf binaryData = new ReadOnlyByteBuf(buffer2);
    BinaryWebSocketFrame frame = new BinaryWebSocketFrame(true, 1, binaryData);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(frame);

    // Act
    nettyWebSocket.onClose(1, "https://example.org/example");

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).retain();
    verify(buffer).writerIndex();
    verify(buffer).release();
    verify(l).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onClose(int, String)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onClose(WebSocket, int, String)}
   *       does nothing.
   *   <li>Then calls {@link WebSocketListener#onClose(WebSocket, int, String)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#onClose(int, String)}
   */
  @Test
  @DisplayName(
      "Test onClose(int, String); given WebSocketListener onClose(WebSocket, int, String) does nothing; then calls onClose(WebSocket, int, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.onClose(int, String)"})
  void testOnClose_givenWebSocketListenerOnCloseDoesNothing_thenCallsOnClose() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame());

    // Act
    nettyWebSocket.onClose(1, "https://example.org/example");

    // Assert
    verify(l).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
  }

  /**
   * Test {@link NettyWebSocket#onClose(int, String)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onError(Throwable)} does
   *       nothing.
   *   <li>Then calls {@link WebSocketListener#onError(Throwable)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#onClose(int, String)}
   */
  @Test
  @DisplayName(
      "Test onClose(int, String); given WebSocketListener onError(Throwable) does nothing; then calls onError(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.onClose(int, String)"})
  void testOnClose_givenWebSocketListenerOnErrorDoesNothing_thenCallsOnError() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException())
        .when(l)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame());

    // Act
    nettyWebSocket.onClose(1, "https://example.org/example");

    // Assert
    verify(l).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onClose(int, String)}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#onClose(int, String)}
   */
  @Test
  @DisplayName("Test onClose(int, String); then does not throw")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.onClose(int, String)"})
  void testOnClose_thenDoesNotThrow() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act and Assert
    assertDoesNotThrow(() -> nettyWebSocket.onClose(1, "https://example.org/example"));
  }

  /**
   * Test {@link NettyWebSocket#onClose(int, String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link NettyWebSocket#onClose(int, String)}
   */
  @Test
  @DisplayName("Test onClose(int, String); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyWebSocket.onClose(int, String)"})
  void testOnClose_thenThrowIllegalArgumentException() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException()).when(l).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException())
        .when(l)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> nettyWebSocket.onClose(1, "https://example.org/example"));
    verify(l).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l).onError(isA(Throwable.class));
  }
}
