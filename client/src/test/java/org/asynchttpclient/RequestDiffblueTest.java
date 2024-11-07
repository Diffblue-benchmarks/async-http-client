package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.NameResolver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RequestDiffblueTest {
  /**
   * Test {@link Request#toBuilder()}.
   * <p>
   * Method under test: {@link Request#toBuilder()}
   */
  @Test
  @DisplayName("Test toBuilder()")
  void testToBuilder() throws IOException {
    // Arrange
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();
    when(headers.iterator()).thenReturn(entryList.iterator());
    Uri uri = mock(Uri.class);
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
    DefaultRequest defaultRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm, file, true,
        null, null, 1L, Charset.forName("UTF-8"), mock(ChannelPoolPartitioning.class), mock(NameResolver.class));

    // Act
    RequestBuilder actualToBuilderResult = defaultRequest.toBuilder();

    // Assert
    verify(headers).iterator();
    HttpHeaders httpHeaders = actualToBuilderResult.headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals("UTF-8", actualToBuilderResult.charset.name());
    assertEquals("https://example.org/example", actualToBuilderResult.method);
    assertEquals("https://example.org/example", actualToBuilderResult.stringData);
    assertEquals("https://example.org/example", actualToBuilderResult.virtualHost);
    File file2 = actualToBuilderResult.file;
    assertEquals("test.txt", file2.getName());
    assertNull(actualToBuilderResult.cookies);
    assertNull(actualToBuilderResult.formParams);
    assertNull(actualToBuilderResult.queryParams);
    assertNull(actualToBuilderResult.bodyParts);
    assertNull(actualToBuilderResult.signatureCalculator);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    ByteBuffer byteBuffer = actualToBuilderResult.byteBufferData;
    assertEquals(0, byteBuffer.position());
    assertEquals(0L, actualToBuilderResult.readTimeout.toNanos());
    assertEquals(0L, actualToBuilderResult.requestTimeout.toNanos());
    assertEquals(1L, actualToBuilderResult.rangeOffset);
    byte[] byteArray = new byte[8];
    assertEquals(8, actualToBuilderResult.streamData.read(byteArray));
    assertEquals(8, byteBuffer.capacity());
    assertEquals(8, byteBuffer.limit());
    assertEquals(UriEncoder.FIXING, actualToBuilderResult.uriEncoder);
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
    assertTrue(file2.isAbsolute());
    assertTrue(byteBuffer.hasRemaining());
    assertTrue(byteBuffer.hasArray());
    assertTrue(actualToBuilderResult.compositeByteData.isEmpty());
    assertTrue(actualToBuilderResult.followRedirect);
    InetAddress expectedAddress = actualToBuilderResult.address;
    assertSame(expectedAddress, defaultRequest.getAddress());
    BodyGenerator expectedBodyGenerator = actualToBuilderResult.bodyGenerator;
    assertSame(expectedBodyGenerator, defaultRequest.getBodyGenerator());
    ByteBuf expectedByteBufData = actualToBuilderResult.byteBufData;
    assertSame(expectedByteBufData, defaultRequest.getByteBufData());
    ByteBuffer expectedByteBufferData = actualToBuilderResult.byteBufferData;
    assertSame(expectedByteBufferData, defaultRequest.getByteBufferData());
    byte[] expectedByteData = actualToBuilderResult.byteData;
    assertSame(expectedByteData, defaultRequest.getByteData());
    ChannelPoolPartitioning expectedChannelPoolPartitioning = actualToBuilderResult.channelPoolPartitioning;
    assertSame(expectedChannelPoolPartitioning, defaultRequest.getChannelPoolPartitioning());
    Charset expectedCharset = actualToBuilderResult.charset;
    assertSame(expectedCharset, defaultRequest.getCharset());
    List<byte[]> expectedCompositeByteData = actualToBuilderResult.compositeByteData;
    assertSame(expectedCompositeByteData, defaultRequest.getCompositeByteData());
    File expectedFile = actualToBuilderResult.file;
    assertSame(expectedFile, defaultRequest.getFile());
    InetAddress expectedLocalAddress = actualToBuilderResult.localAddress;
    assertSame(expectedLocalAddress, defaultRequest.getLocalAddress());
    NameResolver<InetAddress> expectedNameResolver = actualToBuilderResult.nameResolver;
    assertSame(expectedNameResolver, defaultRequest.getNameResolver());
    ProxyServer expectedProxyServer = actualToBuilderResult.proxyServer;
    assertSame(expectedProxyServer, defaultRequest.getProxyServer());
    Realm expectedRealm = actualToBuilderResult.realm;
    assertSame(expectedRealm, defaultRequest.getRealm());
    Duration duration = actualToBuilderResult.requestTimeout;
    assertSame(duration, defaultRequest.getReadTimeout());
    assertSame(duration, defaultRequest.getRequestTimeout());
    InputStream expectedStreamData = actualToBuilderResult.streamData;
    assertSame(expectedStreamData, defaultRequest.getStreamData());
    Uri expectedUri = actualToBuilderResult.uri;
    assertSame(expectedUri, defaultRequest.getUri());
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, byteBuffer.array());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualToBuilderResult.byteData);
  }
}
