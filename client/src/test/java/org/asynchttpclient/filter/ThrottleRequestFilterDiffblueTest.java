package org.asynchttpclient.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.Cookie;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.RequestBuilderBase;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.exception.FilterException;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;

class ThrottleRequestFilterDiffblueTest {
  /**
   * Method under test: {@link ThrottleRequestFilter#filter(FilterContext)}
   */
  @Test
  void testFilter() throws UnsupportedEncodingException, FilterException {
    // Arrange
    ThrottleRequestFilter throttleRequestFilter = new ThrottleRequestFilter(3);
    FilterContext<Object> ctx = mock(FilterContext.class);
    when(ctx.replayRequest()).thenReturn(true);
    when(ctx.getIOException()).thenReturn(ChannelClosedException.INSTANCE);
    when(ctx.getAsyncHandler()).thenReturn(mock(AsyncHandler.class));
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(1));

    NettyResponseStatus nettyResponseStatus = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    when(ctx.getResponseStatus()).thenReturn(nettyResponseStatus);
    Uri uri2 = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

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
    Realm realm = mock(Realm.class);
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest defaultRequest = new DefaultRequest("https://example.org/example", uri2, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    when(ctx.getRequest()).thenReturn(defaultRequest);

    // Act
    FilterContext<Object> actualFilterResult = throttleRequestFilter.filter(ctx);

    // Assert
    verify(ctx, atLeast(1)).getAsyncHandler();
    verify(ctx).getIOException();
    verify(ctx).getRequest();
    verify(ctx).getResponseStatus();
    verify(ctx).replayRequest();
    IOException iOException = actualFilterResult.getIOException();
    assertTrue(iOException instanceof ChannelClosedException);
    assertEquals("Channel closed", iOException.getLocalizedMessage());
    assertEquals("Channel closed", iOException.getMessage());
    assertNull(actualFilterResult.getResponseHeaders());
    assertNull(iOException.getCause());
    assertEquals(0, iOException.getSuppressed().length);
    assertSame(defaultRequest, actualFilterResult.getRequest());
    assertSame(nettyResponseStatus, actualFilterResult.getResponseStatus());
  }
}
