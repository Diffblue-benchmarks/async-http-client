package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.NameResolver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RequestDiffblueTest {
  /**
   * Test {@link Request#toBuilder()}.
   *
   * <p>Method under test: {@link Request#toBuilder()}
   */
  @Test
  @DisplayName("Test toBuilder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Request.toBuilder()"})
  void testToBuilder() throws IOException {
    // Arrange
    HttpHeaders headers = mock(HttpHeaders.class);

    ArrayList<Entry<String, String>> entryList = new ArrayList<>();
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
    Duration requestTimeout = Duration.ofSeconds(1L);
    Duration readTimeout = Duration.ofSeconds(1L);

    DefaultRequest defaultRequest =
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
    RequestBuilder actualToBuilderResult = defaultRequest.toBuilder();

    // Assert
    verify(headers).iterator();
    assertTrue(actualToBuilderResult.headers instanceof DefaultHttpHeaders);
    assertEquals("https://example.org/example", actualToBuilderResult.method);
    assertEquals("https://example.org/example", actualToBuilderResult.stringData);
    assertEquals("https://example.org/example", actualToBuilderResult.virtualHost);
    assertNull(actualToBuilderResult.cookies);
    assertNull(actualToBuilderResult.formParams);
    assertNull(actualToBuilderResult.queryParams);
    assertNull(actualToBuilderResult.bodyParts);
    assertNull(actualToBuilderResult.signatureCalculator);
    assertEquals(1L, actualToBuilderResult.rangeOffset);
    byte[] byteArray = new byte[8];
    assertEquals(8, actualToBuilderResult.streamData.read(byteArray));
    assertEquals(UriEncoder.FIXING, actualToBuilderResult.uriEncoder);
    assertTrue(actualToBuilderResult.compositeByteData.isEmpty());
    assertTrue(actualToBuilderResult.followRedirect);
    assertSame(actualToBuilderResult.address, defaultRequest.getAddress());
    assertSame(actualToBuilderResult.bodyGenerator, defaultRequest.getBodyGenerator());
    assertSame(actualToBuilderResult.byteBufData, defaultRequest.getByteBufData());
    assertSame(actualToBuilderResult.byteBufferData, defaultRequest.getByteBufferData());
    assertSame(actualToBuilderResult.byteData, defaultRequest.getByteData());
    assertSame(
        actualToBuilderResult.channelPoolPartitioning, defaultRequest.getChannelPoolPartitioning());
    assertSame(actualToBuilderResult.charset, defaultRequest.getCharset());
    assertSame(actualToBuilderResult.compositeByteData, defaultRequest.getCompositeByteData());
    assertSame(actualToBuilderResult.file, defaultRequest.getFile());
    assertSame(actualToBuilderResult.localAddress, defaultRequest.getLocalAddress());
    assertSame(actualToBuilderResult.nameResolver, defaultRequest.getNameResolver());
    assertSame(actualToBuilderResult.proxyServer, defaultRequest.getProxyServer());
    assertSame(actualToBuilderResult.readTimeout, defaultRequest.getReadTimeout());
    assertSame(actualToBuilderResult.realm, defaultRequest.getRealm());
    assertSame(actualToBuilderResult.requestTimeout, defaultRequest.getRequestTimeout());
    assertSame(actualToBuilderResult.streamData, defaultRequest.getStreamData());
    assertSame(actualToBuilderResult.uri, defaultRequest.getUri());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualToBuilderResult.byteData);
  }
}
