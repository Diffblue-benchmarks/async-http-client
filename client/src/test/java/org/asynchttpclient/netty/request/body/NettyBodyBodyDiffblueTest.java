package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import org.asynchttpclient.AbstractBasicTest;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.RequestBuilderBase;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.netty.NettyResponseFuture;
import org.asynchttpclient.netty.channel.NoopConnectionSemaphore;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.Body;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.generator.PushBody;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.request.body.multipart.MultipartBody;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.request.body.multipart.part.ByteArrayMultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartPart;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;

class NettyBodyBodyDiffblueTest {
  /**
   * Method under test: {@link NettyBodyBody#getContentLength()}
   */
  @Test
  void testGetContentLength() {
    // Arrange, Act and Assert
    assertEquals(-1L,
        (new NettyBodyBody(new PushBody(new LinkedList<>()), mock(AsyncHttpClientConfig.class))).getContentLength());
  }

  /**
   * Method under test: {@link NettyBodyBody#write(Channel, NettyResponseFuture)}
   */
  @Test
  void testWrite() throws UnsupportedEncodingException {
    // Arrange
    NettyBodyBody nettyBodyBody = new NettyBodyBody(new PushBody(new LinkedList<>()),
        mock(AsyncHttpClientConfig.class));
    EmbeddedChannel channel = new EmbeddedChannel();
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
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);

    // Act
    nettyBodyBody.write(channel,
        new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3, connectionPoolPartitioning,
            connectionSemaphore,
            new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP)));

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
  }

  /**
   * Method under test: {@link NettyBodyBody#write(Channel, NettyResponseFuture)}
   */
  @Test
  void testWrite2() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isDisableZeroCopy()).thenReturn(true);
    NettyBodyBody nettyBodyBody = new NettyBodyBody(new MultipartBody(new ArrayList<>(), "https://example.org/example",
        new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1}), config);
    EmbeddedChannel channel = new EmbeddedChannel();
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
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);

    // Act
    nettyBodyBody.write(channel,
        new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3, connectionPoolPartitioning,
            connectionSemaphore,
            new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP)));

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(config).isDisableZeroCopy();
  }

  /**
   * Method under test: {@link NettyBodyBody#write(Channel, NettyResponseFuture)}
   */
  @Test
  void testWrite3() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    parts.add(new ByteArrayMultipartPart(
        new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}),
        new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));
    MultipartBody body = new MultipartBody(parts, "https://example.org/example",
        new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1});

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isDisableZeroCopy()).thenReturn(true);
    NettyBodyBody nettyBodyBody = new NettyBodyBody(body, config);
    EmbeddedChannel channel = new EmbeddedChannel();
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
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);

    // Act
    nettyBodyBody.write(channel,
        new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3, connectionPoolPartitioning,
            connectionSemaphore,
            new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP)));

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(config).isDisableZeroCopy();
  }

  /**
   * Method under test: {@link NettyBodyBody#write(Channel, NettyResponseFuture)}
   */
  @Test
  void testWrite4() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    parts.add(new ByteArrayMultipartPart(
        new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}),
        new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));
    parts.add(new ByteArrayMultipartPart(
        new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}),
        new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));
    MultipartBody body = new MultipartBody(parts, "https://example.org/example",
        new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1});

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isDisableZeroCopy()).thenReturn(true);
    NettyBodyBody nettyBodyBody = new NettyBodyBody(body, config);
    EmbeddedChannel channel = new EmbeddedChannel();
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
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);

    // Act
    nettyBodyBody.write(channel,
        new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3, connectionPoolPartitioning,
            connectionSemaphore,
            new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP)));

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(config).isDisableZeroCopy();
  }

  /**
   * Method under test: {@link NettyBodyBody#write(Channel, NettyResponseFuture)}
   */
  @Test
  void testWrite5() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isDisableZeroCopy()).thenReturn(false);
    NettyBodyBody nettyBodyBody = new NettyBodyBody(new MultipartBody(new ArrayList<>(), "https://example.org/example",
        new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1}), config);
    EmbeddedChannel channel = new EmbeddedChannel();
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
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);

    // Act
    nettyBodyBody.write(channel,
        new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3, connectionPoolPartitioning,
            connectionSemaphore,
            new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP)));

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(config).isDisableZeroCopy();
  }

  /**
   * Method under test: {@link NettyBodyBody#write(Channel, NettyResponseFuture)}
   */
  @Test
  void testWrite6() throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isDisableZeroCopy()).thenReturn(true);
    NettyBodyBody nettyBodyBody = new NettyBodyBody(new MultipartBody(new ArrayList<>(), "https://example.org/example",
        new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1}), config);

    EmbeddedChannel channel = new EmbeddedChannel();
    channel.writeOutbound("Msgs");
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
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);

    // Act
    nettyBodyBody.write(channel,
        new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3, connectionPoolPartitioning,
            connectionSemaphore,
            new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP)));

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(config).isDisableZeroCopy();
  }

  /**
   * Method under test: {@link NettyBodyBody#write(Channel, NettyResponseFuture)}
   */
  @Test
  void testWrite7() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    parts.add(null);
    MultipartBody body = new MultipartBody(parts, "https://example.org/example",
        new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1});

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isDisableZeroCopy()).thenReturn(true);
    NettyBodyBody nettyBodyBody = new NettyBodyBody(body, config);
    EmbeddedChannel channel = new EmbeddedChannel();
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
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);

    // Act
    nettyBodyBody.write(channel,
        new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3, connectionPoolPartitioning,
            connectionSemaphore,
            new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP)));

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(config).isDisableZeroCopy();
  }

  /**
   * Method under test: {@link NettyBodyBody#write(Channel, NettyResponseFuture)}
   */
  @Test
  void testWrite8() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    parts.add(new ByteArrayMultipartPart(
        new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}),
        new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));
    MultipartBody body = new MultipartBody(parts, "https://example.org/example",
        new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1});

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isDisableZeroCopy()).thenReturn(false);
    NettyBodyBody nettyBodyBody = new NettyBodyBody(body, config);
    EmbeddedChannel channel = new EmbeddedChannel();
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
    ChannelPoolPartitioning connectionPoolPartitioning = mock(ChannelPoolPartitioning.class);
    NoopConnectionSemaphore connectionSemaphore = new NoopConnectionSemaphore();
    Realm realm3 = mock(Realm.class);

    // Act
    nettyBodyBody.write(channel,
        new NettyResponseFuture<>(originalRequest, asyncHandler, null, 3, connectionPoolPartitioning,
            connectionSemaphore,
            new ProxyServer("https://example.org/example", 8080, 8080, realm3, new ArrayList<>(), ProxyType.HTTP)));

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(config).isDisableZeroCopy();
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link NettyBodyBody#NettyBodyBody(Body, AsyncHttpClientConfig)}
   *   <li>{@link NettyBodyBody#getBody()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    PushBody body = new PushBody(new LinkedList<>());

    // Act
    Body actualBody = (new NettyBodyBody(body, mock(AsyncHttpClientConfig.class))).getBody();

    // Assert
    assertTrue(actualBody instanceof PushBody);
    assertSame(body, actualBody);
  }
}
