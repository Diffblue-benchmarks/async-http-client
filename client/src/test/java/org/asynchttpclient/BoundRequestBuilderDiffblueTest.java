package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.DefaultNameResolver;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BoundRequestBuilderDiffblueTest {
  /**
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  void testExecute() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    Uri uri = new Uri(Uri.HTTP, "https://example.org/example", "https://example.org/example", 8080,
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  void testExecute2() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", null, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  void testExecute3() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        channelPoolPartitioning, RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    verify(channelPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  void testExecute4() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Cookie> cookies = new ArrayList<>();
    cookies.add(new DefaultCookie("https://example.org/example", "https://example.org/example"));
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        channelPoolPartitioning, RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    verify(channelPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  void testExecute5() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);

    ArrayList<Param> formParams = new ArrayList<>();
    formParams.add(new Param("https://example.org/example", "https://example.org/example"));
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Part> bodyParts = new ArrayList<>();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        channelPoolPartitioning, RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    verify(channelPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  void testExecute6() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);

    ArrayList<Part> bodyParts = new ArrayList<>();
    bodyParts.add(new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        channelPoolPartitioning, RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    verify(channelPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  void testExecute7() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("charset=");
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, nonProxyHosts,
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        channelPoolPartitioning, RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    verify(channelPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  void testExecute8() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", null, realm,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute()}
   */
  @Test
  void testExecute9() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        channelPoolPartitioning, RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    boundRequestBuilder.addQueryParam("https://example.org/example", "https://example.org/example");

    // Act
    ListenableFuture<Response> actualExecuteResult = boundRequestBuilder.execute();

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    verify(channelPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  void testExecute10() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    Uri uri = new Uri(Uri.HTTP, "https://example.org/example", "https://example.org/example", 8080,
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  void testExecute11() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", null, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  void testExecute12() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        channelPoolPartitioning, RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    verify(channelPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  void testExecute13() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Cookie> cookies = new ArrayList<>();
    cookies.add(new DefaultCookie("https://example.org/example", "https://example.org/example"));
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        channelPoolPartitioning, RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    verify(channelPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  void testExecute14() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);

    ArrayList<Param> formParams = new ArrayList<>();
    formParams.add(new Param("https://example.org/example", "https://example.org/example"));
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    ArrayList<Cookie> cookies = new ArrayList<>();
    byte[] byteData = "AXAXAXAX".getBytes("UTF-8");
    ArrayList<byte[]> compositeByteData = new ArrayList<>();
    ByteBuffer byteBufferData = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Part> bodyParts = new ArrayList<>();
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        channelPoolPartitioning, RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    verify(channelPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  void testExecute15() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);

    ArrayList<Part> bodyParts = new ArrayList<>();
    bodyParts.add(new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        channelPoolPartitioning, RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    verify(channelPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  void testExecute16() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);

    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("charset=");
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, nonProxyHosts,
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    when(realm2.getPassword()).thenReturn("https://example.org/example");
    when(realm2.getPrincipal()).thenReturn("https://example.org/example");
    when(realm2.getCharset()).thenReturn(null);
    when(realm2.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm2.isUsePreemptiveAuth()).thenReturn(true);
    ChannelPoolPartitioning channelPoolPartitioning = mock(ChannelPoolPartitioning.class);
    when(channelPoolPartitioning.getPartitionKey(Mockito.<Uri>any(), Mockito.<String>any(), Mockito.<ProxyServer>any()))
        .thenReturn("Partition Key");
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        channelPoolPartitioning, RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onConnectionPoolAttempt();
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(handler).onConnectionPoolAttempt();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(realm2).getCharset();
    verify(realm2).getPassword();
    verify(realm2).getPrincipal();
    verify(realm2).getScheme();
    verify(realm2).isUsePreemptiveAuth();
    verify(channelPoolPartitioning).getPartitionKey(isA(Uri.class), eq("https://example.org/example"),
        isA(ProxyServer.class));
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test: {@link BoundRequestBuilder#execute(AsyncHandler)}
   */
  @Test
  void testExecute17() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(null);
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    Uri uri = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");
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
    DefaultRequest prototype = new DefaultRequest("https://example.org/example", uri, address, localAddress, headers,
        cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData, streamData,
        bodyGenerator, formParams, bodyParts, "https://example.org/example", null, realm,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    BoundRequestBuilder boundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(), prototype);
    AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doNothing().when(handler).onThrowable(Mockito.<Throwable>any());

    // Act
    ListenableFuture<Object> actualExecuteResult = boundRequestBuilder.execute(handler);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(handler).onThrowable(isA(Throwable.class));
    verify(realm).getCharset();
    verify(realm).getPassword();
    verify(realm).getPrincipal();
    verify(realm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    assertTrue(actualExecuteResult instanceof ListenableFuture.CompletedFailure);
    assertEquals("UTF-8", boundRequestBuilder.charset.name());
    assertTrue(actualExecuteResult.isDone());
  }

  /**
   * Method under test:
   * {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String, boolean, boolean)}
   */
  @Test
  void testNewBoundRequestBuilder() {
    // Arrange and Act
    BoundRequestBuilder actualBoundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(),
        "https://example.org/example", true, true);

    // Assert
    HttpHeaders httpHeaders = actualBoundRequestBuilder.headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertTrue(actualBoundRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualBoundRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof ChannelPoolPartitioning.PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualBoundRequestBuilder.method);
    assertNull(actualBoundRequestBuilder.byteData);
    assertNull(actualBoundRequestBuilder.byteBufData);
    assertNull(actualBoundRequestBuilder.file);
    assertNull(actualBoundRequestBuilder.streamData);
    assertNull(actualBoundRequestBuilder.followRedirect);
    assertNull(actualBoundRequestBuilder.stringData);
    assertNull(actualBoundRequestBuilder.virtualHost);
    assertNull(actualBoundRequestBuilder.address);
    assertNull(actualBoundRequestBuilder.localAddress);
    assertNull(actualBoundRequestBuilder.byteBufferData);
    assertNull(actualBoundRequestBuilder.charset);
    assertNull(actualBoundRequestBuilder.readTimeout);
    assertNull(actualBoundRequestBuilder.requestTimeout);
    assertNull(actualBoundRequestBuilder.cookies);
    assertNull(actualBoundRequestBuilder.compositeByteData);
    assertNull(actualBoundRequestBuilder.formParams);
    assertNull(actualBoundRequestBuilder.queryParams);
    assertNull(actualBoundRequestBuilder.bodyParts);
    assertNull(actualBoundRequestBuilder.realm);
    assertNull(actualBoundRequestBuilder.signatureCalculator);
    assertNull(actualBoundRequestBuilder.proxyServer);
    assertNull(actualBoundRequestBuilder.bodyGenerator);
    assertNull(actualBoundRequestBuilder.uri);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertEquals(0L, actualBoundRequestBuilder.rangeOffset);
    assertEquals(ChannelPoolPartitioning.PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.RAW, actualBoundRequestBuilder.uriEncoder);
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Method under test:
   * {@link BoundRequestBuilder#BoundRequestBuilder(AsyncHttpClient, String, boolean, boolean)}
   */
  @Test
  void testNewBoundRequestBuilder2() {
    // Arrange and Act
    BoundRequestBuilder actualBoundRequestBuilder = new BoundRequestBuilder(new DefaultAsyncHttpClient(),
        "https://example.org/example", false, false);

    // Assert
    HttpHeaders httpHeaders = actualBoundRequestBuilder.headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertTrue(actualBoundRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualBoundRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof ChannelPoolPartitioning.PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualBoundRequestBuilder.method);
    assertNull(actualBoundRequestBuilder.byteData);
    assertNull(actualBoundRequestBuilder.byteBufData);
    assertNull(actualBoundRequestBuilder.file);
    assertNull(actualBoundRequestBuilder.streamData);
    assertNull(actualBoundRequestBuilder.followRedirect);
    assertNull(actualBoundRequestBuilder.stringData);
    assertNull(actualBoundRequestBuilder.virtualHost);
    assertNull(actualBoundRequestBuilder.address);
    assertNull(actualBoundRequestBuilder.localAddress);
    assertNull(actualBoundRequestBuilder.byteBufferData);
    assertNull(actualBoundRequestBuilder.charset);
    assertNull(actualBoundRequestBuilder.readTimeout);
    assertNull(actualBoundRequestBuilder.requestTimeout);
    assertNull(actualBoundRequestBuilder.cookies);
    assertNull(actualBoundRequestBuilder.compositeByteData);
    assertNull(actualBoundRequestBuilder.formParams);
    assertNull(actualBoundRequestBuilder.queryParams);
    assertNull(actualBoundRequestBuilder.bodyParts);
    assertNull(actualBoundRequestBuilder.realm);
    assertNull(actualBoundRequestBuilder.signatureCalculator);
    assertNull(actualBoundRequestBuilder.proxyServer);
    assertNull(actualBoundRequestBuilder.bodyGenerator);
    assertNull(actualBoundRequestBuilder.uri);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertEquals(0L, actualBoundRequestBuilder.rangeOffset);
    assertEquals(ChannelPoolPartitioning.PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualBoundRequestBuilder.uriEncoder);
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }
}
