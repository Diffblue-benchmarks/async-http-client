package org.asynchttpclient.netty.request;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.channel.ChannelProgressiveFuture;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.Cookie;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.asynchttpclient.AbstractBasicTest;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilderBase;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.handler.TransferCompletionHandler;
import org.asynchttpclient.netty.NettyResponseFuture;
import org.asynchttpclient.netty.channel.ConnectionSemaphore;
import org.asynchttpclient.netty.channel.NoopConnectionSemaphore;
import org.asynchttpclient.netty.request.body.NettyByteArrayBody;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;

class WriteProgressListenerDiffblueTest {
  /**
   * Method under test:
   * {@link WriteProgressListener#WriteProgressListener(NettyResponseFuture, boolean, long)}
   */
  @Test
  void testNewWriteProgressListener() throws UnsupportedEncodingException {
    // Arrange
    Request originalRequest = mock(Request.class);
    AsyncHandler<Object> asyncHandler = mock(AsyncHandler.class);
    HttpVersion httpVersion = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpRequest httpRequest = new DefaultFullHttpRequest(httpVersion,
        HttpMethod.valueOf("https://example.org/example"), "https://example.org/example");

    // Act
    WriteProgressListener actualWriteProgressListener = new WriteProgressListener(
        new NettyResponseFuture<>(originalRequest, asyncHandler,
            new NettyRequest(httpRequest, new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8"))), 3,
            mock(ChannelPoolPartitioning.class), mock(ConnectionSemaphore.class), mock(ProxyServer.class)),
        true, 1L);

    // Assert
    assertNull(actualWriteProgressListener.progressAsyncHandler);
    assertTrue(actualWriteProgressListener.notifyHeaders);
  }

  /**
   * Method under test:
   * {@link WriteProgressListener#operationComplete(ChannelProgressiveFuture)}
   */
  @Test
  void testOperationComplete() throws UnsupportedEncodingException {
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

    AbstractBasicTest.AsyncCompletionHandlerAdapter asyncHandler = new AbstractBasicTest.AsyncCompletionHandlerAdapter();
    DefaultFullHttpRequest httpRequest = new DefaultFullHttpRequest(mock(HttpVersion.class), mock(HttpMethod.class),
        "https://example.org/example");

    NettyRequest nettyRequest = new NettyRequest(httpRequest, new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8")));

    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);
    WriteProgressListener writeProgressListener = new WriteProgressListener(
        new NettyResponseFuture<>(originalRequest, asyncHandler, nettyRequest, 3, connectionPoolPartitioning,
            connectionSemaphore,
            new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP)),
        true, 1L);
    ChannelProgressiveFuture cf = mock(ChannelProgressiveFuture.class);
    when(cf.channel()).thenReturn(new EmbeddedChannel());
    when(cf.cause()).thenReturn(ChannelClosedException.INSTANCE);

    // Act
    writeProgressListener.operationComplete(cf);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(cf).channel();
    verify(cf).cause();
    assertTrue(writeProgressListener.future.isDone());
  }

  /**
   * Method under test:
   * {@link WriteProgressListener#operationProgressed(ChannelProgressiveFuture, long, long)}
   */
  @Test
  void testOperationProgressed() throws UnsupportedEncodingException {
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

    AbstractBasicTest.AsyncCompletionHandlerAdapter asyncHandler = new AbstractBasicTest.AsyncCompletionHandlerAdapter();
    DefaultFullHttpRequest httpRequest = new DefaultFullHttpRequest(mock(HttpVersion.class), mock(HttpMethod.class),
        "https://example.org/example");

    NettyRequest nettyRequest = new NettyRequest(httpRequest, new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8")));

    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);

    // Act
    (new WriteProgressListener(
        new NettyResponseFuture<>(originalRequest, asyncHandler, nettyRequest, 3, connectionPoolPartitioning,
            connectionSemaphore,
            new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP)),
        true, 1L)).operationProgressed(mock(ChannelProgressiveFuture.class), 1L, 1L);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
  }

  /**
   * Method under test:
   * {@link WriteProgressListener#operationProgressed(ChannelProgressiveFuture, long, long)}
   */
  @Test
  void testOperationProgressed2() throws UnsupportedEncodingException {
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

    DefaultFullHttpRequest httpRequest = new DefaultFullHttpRequest(mock(HttpVersion.class), mock(HttpMethod.class),
        "https://example.org/example");

    NettyRequest nettyRequest = new NettyRequest(httpRequest, new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8")));

    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);

    // Act
    (new WriteProgressListener(
        new NettyResponseFuture<>(originalRequest, null, nettyRequest, 3, connectionPoolPartitioning,
            connectionSemaphore,
            new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP)),
        true, 1L)).operationProgressed(mock(ChannelProgressiveFuture.class), 1L, 1L);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
  }

  /**
   * Method under test:
   * {@link WriteProgressListener#operationProgressed(ChannelProgressiveFuture, long, long)}
   */
  @Test
  void testOperationProgressed3() throws UnsupportedEncodingException {
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

    AbstractBasicTest.AsyncCompletionHandlerAdapter asyncHandler = new AbstractBasicTest.AsyncCompletionHandlerAdapter();
    DefaultFullHttpRequest httpRequest = new DefaultFullHttpRequest(mock(HttpVersion.class), mock(HttpMethod.class),
        "https://example.org/example");

    NettyRequest nettyRequest = new NettyRequest(httpRequest, new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8")));

    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);

    // Act
    (new WriteProgressListener(
        new NettyResponseFuture<>(originalRequest, asyncHandler, nettyRequest, 3, connectionPoolPartitioning,
            connectionSemaphore,
            new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP)),
        false, 1L)).operationProgressed(mock(ChannelProgressiveFuture.class), 1L, 1L);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
  }

  /**
   * Method under test:
   * {@link WriteProgressListener#WriteProgressListener(NettyResponseFuture, boolean, long)}
   */
  @Test
  void testNewWriteProgressListener2() throws UnsupportedEncodingException {
    // Arrange
    Request originalRequest = mock(Request.class);
    TransferCompletionHandler asyncHandler = new TransferCompletionHandler();
    HttpVersion httpVersion = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpRequest httpRequest = new DefaultFullHttpRequest(httpVersion,
        HttpMethod.valueOf("https://example.org/example"), "https://example.org/example");

    // Act
    WriteProgressListener actualWriteProgressListener = new WriteProgressListener(
        new NettyResponseFuture<>(originalRequest, asyncHandler,
            new NettyRequest(httpRequest, new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8"))), 3,
            mock(ChannelPoolPartitioning.class), mock(ConnectionSemaphore.class), mock(ProxyServer.class)),
        true, 1L);

    // Assert
    assertTrue(actualWriteProgressListener.progressAsyncHandler instanceof TransferCompletionHandler);
    assertTrue(actualWriteProgressListener.notifyHeaders);
  }
}
