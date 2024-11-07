package org.asynchttpclient.filter;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.NameResolver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.DefaultRequest;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.filter.FilterContext.FilterContextBuilder;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FilterContextDiffblueTest {
  /**
   * Test FilterContextBuilder
   * {@link FilterContextBuilder#FilterContextBuilder(FilterContext)}.
   * <p>
   * Method under test:
   * {@link FilterContext.FilterContextBuilder#FilterContextBuilder(FilterContext)}
   */
  @Test
  @DisplayName("Test FilterContextBuilder new FilterContextBuilder(FilterContext)")
  void testFilterContextBuilderNewFilterContextBuilder() throws UnsupportedEncodingException {
    // Arrange
    FilterContext<Object> clone = mock(FilterContext.class);
    when(clone.replayRequest()).thenReturn(true);
    when(clone.getIOException()).thenReturn(ChannelClosedException.INSTANCE);
    when(clone.getAsyncHandler()).thenReturn(mock(AsyncHandler.class));
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(1));

    when(clone.getResponseStatus()).thenReturn(new NettyResponseStatus(uri, response, new EmbeddedChannel()));
    Uri uri2 = mock(Uri.class);
    InetAddress address = mock(InetAddress.class);
    InetAddress localAddress = mock(InetAddress.class);
    HttpHeaders headers = mock(HttpHeaders.class);
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
    DefaultRequest defaultRequest = new DefaultRequest("https://example.org/example", uri2, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true,
        null, null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    when(clone.getRequest()).thenReturn(defaultRequest);

    // Act
    FilterContext.FilterContextBuilder<Object> actualFilterContextBuilder = new FilterContext.FilterContextBuilder<>(
        clone);

    // Assert
    verify(clone).getAsyncHandler();
    verify(clone).getIOException();
    verify(clone).getRequest();
    verify(clone).getResponseStatus();
    verify(clone).replayRequest();
    assertSame(defaultRequest, actualFilterContextBuilder.getRequest());
  }
}
