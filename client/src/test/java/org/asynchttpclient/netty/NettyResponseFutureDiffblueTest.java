package org.asynchttpclient.netty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilderBase;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.netty.channel.ChannelState;
import org.asynchttpclient.netty.channel.NoopConnectionSemaphore;
import org.asynchttpclient.netty.request.NettyRequest;
import org.asynchttpclient.netty.timeout.TimeoutsHolder;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NettyResponseFutureDiffblueTest {
  /**
   * Method under test: {@link NettyResponseFuture#takePartitionKeyLock()}
   */
  @Test
  void testTakePartitionKeyLock() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    Object actualTakePartitionKeyLockResult = nettyResponseFuture.takePartitionKeyLock();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertNull(actualTakePartitionKeyLockResult);
  }

  /**
   * Method under test: {@link NettyResponseFuture#isDone()}
   */
  @Test
  void testIsDone() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    boolean actualIsDoneResult = nettyResponseFuture.isDone();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertFalse(actualIsDoneResult);
  }

  /**
   * Method under test: {@link NettyResponseFuture#isCancelled()}
   */
  @Test
  void testIsCancelled() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    boolean actualIsCancelledResult = nettyResponseFuture.isCancelled();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertFalse(actualIsCancelledResult);
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link NettyResponseFuture#setAsyncHandler(AsyncHandler)}
   *   <li>{@link NettyResponseFuture#setChannelState(ChannelState)}
   *   <li>{@link NettyResponseFuture#setConnectAllowed(boolean)}
   *   <li>{@link NettyResponseFuture#setCurrentRequest(Request)}
   *   <li>
   * {@link NettyResponseFuture#setDontWriteBodyBecauseExpectContinue(boolean)}
   *   <li>{@link NettyResponseFuture#setHeadersAlreadyWrittenOnContinue(boolean)}
   *   <li>{@link NettyResponseFuture#setKeepAlive(boolean)}
   *   <li>{@link NettyResponseFuture#setNettyRequest(NettyRequest)}
   *   <li>{@link NettyResponseFuture#setProxyRealm(Realm)}
   *   <li>{@link NettyResponseFuture#setRealm(Realm)}
   *   <li>{@link NettyResponseFuture#setReuseChannel(boolean)}
   *   <li>{@link NettyResponseFuture#setStreamConsumed(boolean)}
   *   <li>{@link NettyResponseFuture#setTargetRequest(Request)}
   *   <li>{@link NettyResponseFuture#toString()}
   *   <li>{@link NettyResponseFuture#channel()}
   *   <li>{@link NettyResponseFuture#getAsyncHandler()}
   *   <li>{@link NettyResponseFuture#getChannelState()}
   *   <li>{@link NettyResponseFuture#getCurrentRequest()}
   *   <li>{@link NettyResponseFuture#getLastTouch()}
   *   <li>{@link NettyResponseFuture#getNettyRequest()}
   *   <li>{@link NettyResponseFuture#getProxyRealm()}
   *   <li>{@link NettyResponseFuture#getProxyServer()}
   *   <li>{@link NettyResponseFuture#getRealm()}
   *   <li>{@link NettyResponseFuture#getStart()}
   *   <li>{@link NettyResponseFuture#getTargetRequest()}
   *   <li>{@link NettyResponseFuture#isConnectAllowed()}
   *   <li>{@link NettyResponseFuture#isDontWriteBodyBecauseExpectContinue()}
   *   <li>{@link NettyResponseFuture#isHeadersAlreadyWrittenOnContinue()}
   *   <li>{@link NettyResponseFuture#isKeepAlive()}
   *   <li>{@link NettyResponseFuture#isReuseChannel()}
   *   <li>{@link NettyResponseFuture#isStreamConsumed()}
   *   <li>{@link NettyResponseFuture#toCompletableFuture()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    ProxyServer proxyServer2 = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, null, null, 3,
        connectionPoolPartitioning, connectionSemaphore, proxyServer2);

    // Act
    nettyResponseFuture.setAsyncHandler(null);
    nettyResponseFuture.setChannelState(ChannelState.NEW);
    nettyResponseFuture.setConnectAllowed(true);
    Uri uri2 = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address2 = mock(InetAddress.class);
    InetAddress localAddress2 = mock(InetAddress.class);
    DefaultHttpHeaders headers2 = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies2 = new ArrayList<>();
    byte[] byteData2 = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData2 = new ArrayList<>();
    ByteBuffer byteBufferData2 = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf byteBufData2 = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ByteArrayInputStream streamData2 = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator2 = mock(BodyGenerator.class);
    ArrayList<Param> formParams2 = new ArrayList<>();
    ArrayList<Part> bodyParts2 = new ArrayList<>();
    ProxyServer proxyServer3 = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest currentRequest = new DefaultRequest("https://example.org/example", uri2, address2, localAddress2,
        headers2, cookies2, byteData2, compositeByteData2, "https://example.org/example", byteBufferData2, byteBufData2,
        streamData2, bodyGenerator2, formParams2, bodyParts2, "https://example.org/example", proxyServer3, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    nettyResponseFuture.setCurrentRequest(currentRequest);
    nettyResponseFuture.setDontWriteBodyBecauseExpectContinue(true);
    nettyResponseFuture.setHeadersAlreadyWrittenOnContinue(true);
    nettyResponseFuture.setKeepAlive(true);
    nettyResponseFuture.setNettyRequest(null);
    nettyResponseFuture.setProxyRealm(null);
    nettyResponseFuture.setRealm(null);
    nettyResponseFuture.setReuseChannel(true);
    nettyResponseFuture.setStreamConsumed(true);
    Uri uri3 = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address3 = mock(InetAddress.class);
    InetAddress localAddress3 = mock(InetAddress.class);
    DefaultHttpHeaders headers3 = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies3 = new ArrayList<>();
    byte[] byteData3 = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData3 = new ArrayList<>();
    ByteBuffer byteBufferData3 = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf byteBufData3 = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ByteArrayInputStream streamData3 = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator3 = mock(BodyGenerator.class);
    ArrayList<Param> formParams3 = new ArrayList<>();
    ArrayList<Part> bodyParts3 = new ArrayList<>();
    ProxyServer proxyServer4 = new ProxyServer("https://example.org/example", 8080, 8080, null, new ArrayList<>(),
        ProxyType.HTTP);

    DefaultRequest targetRequest = new DefaultRequest("https://example.org/example", uri3, address3, localAddress3,
        headers3, cookies3, byteData3, compositeByteData3, "https://example.org/example", byteBufferData3, byteBufData3,
        streamData3, bodyGenerator3, formParams3, bodyParts3, "https://example.org/example", proxyServer4, null,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    nettyResponseFuture.setTargetRequest(targetRequest);
    nettyResponseFuture.toString();
    nettyResponseFuture.channel();
    nettyResponseFuture.getAsyncHandler();
    ChannelState actualChannelState = nettyResponseFuture.getChannelState();
    Request actualCurrentRequest = nettyResponseFuture.getCurrentRequest();
    nettyResponseFuture.getLastTouch();
    nettyResponseFuture.getNettyRequest();
    nettyResponseFuture.getProxyRealm();
    ProxyServer actualProxyServer = nettyResponseFuture.getProxyServer();
    nettyResponseFuture.getRealm();
    nettyResponseFuture.getStart();
    Request actualTargetRequest = nettyResponseFuture.getTargetRequest();
    boolean actualIsConnectAllowedResult = nettyResponseFuture.isConnectAllowed();
    boolean actualIsDontWriteBodyBecauseExpectContinueResult = nettyResponseFuture
        .isDontWriteBodyBecauseExpectContinue();
    boolean actualIsHeadersAlreadyWrittenOnContinueResult = nettyResponseFuture.isHeadersAlreadyWrittenOnContinue();
    boolean actualIsKeepAliveResult = nettyResponseFuture.isKeepAlive();
    boolean actualIsReuseChannelResult = nettyResponseFuture.isReuseChannel();
    boolean actualIsStreamConsumedResult = nettyResponseFuture.isStreamConsumed();
    nettyResponseFuture.toCompletableFuture();

    // Assert that nothing has changed
    assertEquals(ChannelState.NEW, actualChannelState);
    assertTrue(actualIsConnectAllowedResult);
    assertTrue(actualIsDontWriteBodyBecauseExpectContinueResult);
    assertTrue(actualIsHeadersAlreadyWrittenOnContinueResult);
    assertTrue(actualIsKeepAliveResult);
    assertTrue(actualIsReuseChannelResult);
    assertTrue(actualIsStreamConsumedResult);
    assertSame(currentRequest, actualCurrentRequest);
    assertSame(targetRequest, actualTargetRequest);
    assertSame(proxyServer2, actualProxyServer);
  }

  /**
   * Method under test: {@link NettyResponseFuture#abort(Throwable)}
   */
  @Test
  void testAbort() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onThrowable(Mockito.<Throwable>any());
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    nettyResponseFuture.abort(ChannelClosedException.INSTANCE);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(asyncHandler).onThrowable(isA(Throwable.class));
    assertTrue(nettyResponseFuture.isDone());
  }

  /**
   * Method under test: {@link NettyResponseFuture#abort(Throwable)}
   */
  @Test
  void testAbort2() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    doThrow(new CancellationException("foo")).when(asyncHandler).onThrowable(Mockito.<Throwable>any());
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    nettyResponseFuture.abort(ChannelClosedException.INSTANCE);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(asyncHandler).onThrowable(isA(Throwable.class));
    assertTrue(nettyResponseFuture.isDone());
  }

  /**
   * Method under test: {@link NettyResponseFuture#abort(Throwable)}
   */
  @Test
  void testAbort3() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onThrowable(Mockito.<Throwable>any());
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, null,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    nettyResponseFuture.abort(ChannelClosedException.INSTANCE);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(asyncHandler).onThrowable(isA(Throwable.class));
    assertTrue(nettyResponseFuture.isDone());
  }

  /**
   * Method under test: {@link NettyResponseFuture#touch()}
   */
  @Test
  void testTouch() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    nettyResponseFuture.touch();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
  }

  /**
   * Method under test:
   * {@link NettyResponseFuture#addListener(Runnable, Executor)}
   */
  @Test
  void testAddListener() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    ListenableFuture<Object> actualAddListenerResult = nettyResponseFuture.addListener(mock(Runnable.class),
        mock(Executor.class));

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertTrue(actualAddListenerResult instanceof NettyResponseFuture);
    assertSame(nettyResponseFuture, actualAddListenerResult);
  }

  /**
   * Method under test: {@link NettyResponseFuture#getUri()}
   */
  @Test
  void testGetUri() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    Uri actualUri = nettyResponseFuture.getUri();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertSame(uri, actualUri);
  }

  /**
   * Method under test: {@link NettyResponseFuture#cancelTimeouts()}
   */
  @Test
  void testCancelTimeouts() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    nettyResponseFuture.cancelTimeouts();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
  }

  /**
   * Method under test:
   * {@link NettyResponseFuture#incrementAndGetCurrentRedirectCount()}
   */
  @Test
  void testIncrementAndGetCurrentRedirectCount() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    int actualIncrementAndGetCurrentRedirectCountResult = nettyResponseFuture.incrementAndGetCurrentRedirectCount();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(1, actualIncrementAndGetCurrentRedirectCountResult);
  }

  /**
   * Method under test: {@link NettyResponseFuture#getTimeoutsHolder()}
   */
  @Test
  void testGetTimeoutsHolder() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    TimeoutsHolder actualTimeoutsHolder = nettyResponseFuture.getTimeoutsHolder();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertNull(actualTimeoutsHolder);
  }

  /**
   * Method under test:
   * {@link NettyResponseFuture#setTimeoutsHolder(TimeoutsHolder)}
   */
  @Test
  void testSetTimeoutsHolder() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    nettyResponseFuture.setTimeoutsHolder(mock(TimeoutsHolder.class));

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
  }

  /**
   * Method under test: {@link NettyResponseFuture#isInAuth()}
   */
  @Test
  void testIsInAuth() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    boolean actualIsInAuthResult = nettyResponseFuture.isInAuth();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertFalse(actualIsInAuthResult);
  }

  /**
   * Method under test: {@link NettyResponseFuture#setInAuth(boolean)}
   */
  @Test
  void testSetInAuth() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    nettyResponseFuture.setInAuth(true);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
  }

  /**
   * Method under test: {@link NettyResponseFuture#isAndSetInAuth(boolean)}
   */
  @Test
  void testIsAndSetInAuth() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    boolean actualIsAndSetInAuthResult = nettyResponseFuture.isAndSetInAuth(true);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertFalse(actualIsAndSetInAuthResult);
  }

  /**
   * Method under test: {@link NettyResponseFuture#isInProxyAuth()}
   */
  @Test
  void testIsInProxyAuth() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    boolean actualIsInProxyAuthResult = nettyResponseFuture.isInProxyAuth();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertFalse(actualIsInProxyAuthResult);
  }

  /**
   * Method under test: {@link NettyResponseFuture#setInProxyAuth(boolean)}
   */
  @Test
  void testSetInProxyAuth() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    nettyResponseFuture.setInProxyAuth(true);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
  }

  /**
   * Method under test: {@link NettyResponseFuture#isAndSetInProxyAuth(boolean)}
   */
  @Test
  void testIsAndSetInProxyAuth() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    boolean actualIsAndSetInProxyAuthResult = nettyResponseFuture.isAndSetInProxyAuth(true);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertFalse(actualIsAndSetInProxyAuthResult);
  }

  /**
   * Method under test:
   * {@link NettyResponseFuture#attachChannel(Channel, boolean)}
   */
  @Test
  void testAttachChannel() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    nettyResponseFuture.attachChannel(new EmbeddedChannel(), true);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
  }

  /**
   * Method under test: {@link NettyResponseFuture#incrementRetryAndCheck()}
   */
  @Test
  void testIncrementRetryAndCheck() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    boolean actualIncrementRetryAndCheckResult = nettyResponseFuture.incrementRetryAndCheck();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertTrue(actualIncrementRetryAndCheckResult);
  }

  /**
   * Method under test: {@link NettyResponseFuture#isReplayPossible()}
   */
  @Test
  void testIsReplayPossible() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    boolean actualIsReplayPossibleResult = nettyResponseFuture.isReplayPossible();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertTrue(actualIsReplayPossibleResult);
  }

  /**
   * Method under test: {@link NettyResponseFuture#getPartitionKey()}
   */
  @Test
  void testGetPartitionKey() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(connectionPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(),
        Mockito.<ProxyServer>any())).thenReturn("Partition Key");
    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    Object actualPartitionKey = nettyResponseFuture.getPartitionKey();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(connectionPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
    assertEquals("Partition Key", actualPartitionKey);
  }

  /**
   * Method under test: {@link NettyResponseFuture#getPartitionKey()}
   */
  @Test
  void testGetPartitionKey2() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(connectionPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(),
        Mockito.<ProxyServer>any())).thenThrow(new CancellationException("foo"));
    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act and Assert
    assertThrows(CancellationException.class, () -> nettyResponseFuture.getPartitionKey());
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(connectionPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
  }

  /**
   * Method under test: {@link NettyResponseFuture#acquirePartitionLockLazily()}
   */
  @Test
  void testAcquirePartitionLockLazily() throws IOException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(connectionPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(),
        Mockito.<ProxyServer>any())).thenReturn("Partition Key");
    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act
    nettyResponseFuture.acquirePartitionLockLazily();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(connectionPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
  }

  /**
   * Method under test: {@link NettyResponseFuture#acquirePartitionLockLazily()}
   */
  @Test
  void testAcquirePartitionLockLazily2() throws IOException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest originalRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(connectionPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(),
        Mockito.<ProxyServer>any())).thenThrow(new CancellationException("foo"));
    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    NettyResponseFuture<Object> nettyResponseFuture = new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3,
        connectionPoolPartitioning, connectionSemaphore,
        new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP));

    // Act and Assert
    assertThrows(CancellationException.class, () -> nettyResponseFuture.acquirePartitionLockLazily());
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(connectionPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
  }
}
