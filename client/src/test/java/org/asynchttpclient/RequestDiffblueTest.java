package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.DefaultNameResolver;
import io.netty.resolver.NameResolver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.Test;

class RequestDiffblueTest {
  /**
   * Method under test: {@link Request#toBuilder()}
   */
  @Test
  void testToBuilder() throws IOException, URISyntaxException {
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
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf byteBufData = new DuplicatedByteBuf(buffer);
    ByteArrayInputStream streamData = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    BodyGenerator bodyGenerator = mock(BodyGenerator.class);
    ArrayList<Param> formParams = new ArrayList<>();
    ArrayList<Part> bodyParts = new ArrayList<>();
    Realm realm = mock(Realm.class);
    ArrayList<String> nonProxyHosts = new ArrayList<>();
    ProxyServer proxyServer = new ProxyServer("https://example.org/example", 8080, 8080, realm, nonProxyHosts,
        ProxyType.HTTP);

    Realm realm2 = mock(Realm.class);
    DefaultRequest defaultRequest = new DefaultRequest("https://example.org/example", uri, address, localAddress,
        headers, cookies, byteData, compositeByteData, "https://example.org/example", byteBufferData, byteBufData,
        streamData, bodyGenerator, formParams, bodyParts, "https://example.org/example", proxyServer, realm2,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true, null, null, 1L, null,
        mock(ChannelPoolPartitioning.class), RequestBuilderBase.DEFAULT_NAME_RESOLVER);

    // Act
    RequestBuilder actualToBuilderResult = defaultRequest.toBuilder();

    // Assert
    ByteBuf byteBuf = actualToBuilderResult.byteBufData;
    assertTrue(byteBuf instanceof DuplicatedByteBuf);
    HttpHeaders httpHeaders = actualToBuilderResult.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertTrue(actualToBuilderResult.nameResolver instanceof DefaultNameResolver);
    ProxyServer proxyServer2 = actualToBuilderResult.proxyServer;
    assertEquals("https://example.org/example", proxyServer2.getHost());
    Uri uri2 = actualToBuilderResult.uri;
    assertEquals("https://example.org/example", uri2.getFragment());
    assertEquals("https://example.org/example", uri2.getHost());
    assertEquals("https://example.org/example", uri2.getNonEmptyPath());
    assertEquals("https://example.org/example", uri2.getPath());
    assertEquals("https://example.org/example", uri2.getQuery());
    assertEquals("https://example.org/example", uri2.getScheme());
    assertEquals("https://example.org/example", uri2.getUserInfo());
    assertEquals("https://example.org/example", actualToBuilderResult.method);
    assertEquals("https://example.org/example", actualToBuilderResult.stringData);
    assertEquals("https://example.org/example", actualToBuilderResult.virtualHost);
    assertEquals("https://example.org/example://https://example.org/example:8080", uri2.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example?https://example.org/example", uri2.toJavaNetURI().toString());
    assertEquals("https://example.org/example:8080", uri2.getAuthority());
    File file = actualToBuilderResult.file;
    assertEquals("test.txt", file.getName());
    assertNull(actualToBuilderResult.charset);
    assertNull(actualToBuilderResult.cookies);
    assertNull(actualToBuilderResult.formParams);
    assertNull(actualToBuilderResult.queryParams);
    assertNull(actualToBuilderResult.bodyParts);
    assertNull(proxyServer2.getCustomHeaders());
    assertNull(actualToBuilderResult.signatureCalculator);
    ByteBuffer byteBuffer = actualToBuilderResult.byteBufferData;
    assertEquals(0, byteBuffer.position());
    assertEquals(0L, actualToBuilderResult.readTimeout.toNanos());
    assertEquals(0L, actualToBuilderResult.requestTimeout.toNanos());
    assertEquals(1L, actualToBuilderResult.rangeOffset);
    byte[] byteArray = new byte[8];
    assertEquals(8, actualToBuilderResult.streamData.read(byteArray));
    assertEquals(8, byteBuffer.capacity());
    assertEquals(8, byteBuffer.limit());
    assertEquals(80, uri2.getSchemeDefaultPort());
    assertEquals(8080, proxyServer2.getPort());
    assertEquals(8080, proxyServer2.getSecuredPort());
    assertEquals(8080, uri2.getExplicitPort());
    assertEquals(8080, uri2.getPort());
    assertEquals(ProxyType.HTTP, proxyServer2.getProxyType());
    assertEquals(UriEncoder.FIXING, actualToBuilderResult.uriEncoder);
    assertFalse(uri2.isSecured());
    assertFalse(uri2.isWebSocket());
    assertTrue(file.isAbsolute());
    assertTrue(byteBuffer.hasRemaining());
    assertTrue(byteBuffer.hasArray());
    List<String> nonProxyHosts2 = proxyServer2.getNonProxyHosts();
    assertTrue(nonProxyHosts2.isEmpty());
    assertTrue(actualToBuilderResult.compositeByteData.isEmpty());
    assertTrue(actualToBuilderResult.followRedirect);
    assertEquals(buffer, byteBuf);
    assertEquals(headers, httpHeaders);
    assertSame(nonProxyHosts, nonProxyHosts2);
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
