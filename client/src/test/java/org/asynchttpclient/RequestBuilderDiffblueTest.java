package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.resolver.DefaultNameResolver;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.Test;

class RequestBuilderDiffblueTest {
  /**
   * Method under test: {@link RequestBuilder#RequestBuilder()}
   */
  @Test
  void testNewRequestBuilder() {
    // Arrange and Act
    RequestBuilder actualRequestBuilder = new RequestBuilder();

    // Assert
    HttpHeaders httpHeaders = actualRequestBuilder.headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof ChannelPoolPartitioning.PerHostChannelPoolPartitioning);
    assertEquals("GET", actualRequestBuilder.method);
    assertNull(actualRequestBuilder.byteData);
    assertNull(actualRequestBuilder.byteBufData);
    assertNull(actualRequestBuilder.file);
    assertNull(actualRequestBuilder.streamData);
    assertNull(actualRequestBuilder.followRedirect);
    assertNull(actualRequestBuilder.stringData);
    assertNull(actualRequestBuilder.virtualHost);
    assertNull(actualRequestBuilder.address);
    assertNull(actualRequestBuilder.localAddress);
    assertNull(actualRequestBuilder.byteBufferData);
    assertNull(actualRequestBuilder.charset);
    assertNull(actualRequestBuilder.readTimeout);
    assertNull(actualRequestBuilder.requestTimeout);
    assertNull(actualRequestBuilder.cookies);
    assertNull(actualRequestBuilder.compositeByteData);
    assertNull(actualRequestBuilder.formParams);
    assertNull(actualRequestBuilder.queryParams);
    assertNull(actualRequestBuilder.bodyParts);
    assertNull(actualRequestBuilder.realm);
    assertNull(actualRequestBuilder.signatureCalculator);
    assertNull(actualRequestBuilder.proxyServer);
    assertNull(actualRequestBuilder.bodyGenerator);
    assertNull(actualRequestBuilder.uri);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertEquals(0L, actualRequestBuilder.rangeOffset);
    assertEquals(ChannelPoolPartitioning.PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualRequestBuilder.uriEncoder);
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Method under test:
   * {@link RequestBuilder#RequestBuilder(String, boolean, boolean)}
   */
  @Test
  void testNewRequestBuilder2() {
    // Arrange and Act
    RequestBuilder actualRequestBuilder = new RequestBuilder("https://example.org/example", true, true);

    // Assert
    HttpHeaders httpHeaders = actualRequestBuilder.headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof ChannelPoolPartitioning.PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualRequestBuilder.method);
    assertNull(actualRequestBuilder.byteData);
    assertNull(actualRequestBuilder.byteBufData);
    assertNull(actualRequestBuilder.file);
    assertNull(actualRequestBuilder.streamData);
    assertNull(actualRequestBuilder.followRedirect);
    assertNull(actualRequestBuilder.stringData);
    assertNull(actualRequestBuilder.virtualHost);
    assertNull(actualRequestBuilder.address);
    assertNull(actualRequestBuilder.localAddress);
    assertNull(actualRequestBuilder.byteBufferData);
    assertNull(actualRequestBuilder.charset);
    assertNull(actualRequestBuilder.readTimeout);
    assertNull(actualRequestBuilder.requestTimeout);
    assertNull(actualRequestBuilder.cookies);
    assertNull(actualRequestBuilder.compositeByteData);
    assertNull(actualRequestBuilder.formParams);
    assertNull(actualRequestBuilder.queryParams);
    assertNull(actualRequestBuilder.bodyParts);
    assertNull(actualRequestBuilder.realm);
    assertNull(actualRequestBuilder.signatureCalculator);
    assertNull(actualRequestBuilder.proxyServer);
    assertNull(actualRequestBuilder.bodyGenerator);
    assertNull(actualRequestBuilder.uri);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertEquals(0L, actualRequestBuilder.rangeOffset);
    assertEquals(ChannelPoolPartitioning.PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.RAW, actualRequestBuilder.uriEncoder);
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Method under test:
   * {@link RequestBuilder#RequestBuilder(String, boolean, boolean)}
   */
  @Test
  void testNewRequestBuilder3() {
    // Arrange and Act
    RequestBuilder actualRequestBuilder = new RequestBuilder("https://example.org/example", false, false);

    // Assert
    HttpHeaders httpHeaders = actualRequestBuilder.headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof ChannelPoolPartitioning.PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualRequestBuilder.method);
    assertNull(actualRequestBuilder.byteData);
    assertNull(actualRequestBuilder.byteBufData);
    assertNull(actualRequestBuilder.file);
    assertNull(actualRequestBuilder.streamData);
    assertNull(actualRequestBuilder.followRedirect);
    assertNull(actualRequestBuilder.stringData);
    assertNull(actualRequestBuilder.virtualHost);
    assertNull(actualRequestBuilder.address);
    assertNull(actualRequestBuilder.localAddress);
    assertNull(actualRequestBuilder.byteBufferData);
    assertNull(actualRequestBuilder.charset);
    assertNull(actualRequestBuilder.readTimeout);
    assertNull(actualRequestBuilder.requestTimeout);
    assertNull(actualRequestBuilder.cookies);
    assertNull(actualRequestBuilder.compositeByteData);
    assertNull(actualRequestBuilder.formParams);
    assertNull(actualRequestBuilder.queryParams);
    assertNull(actualRequestBuilder.bodyParts);
    assertNull(actualRequestBuilder.realm);
    assertNull(actualRequestBuilder.signatureCalculator);
    assertNull(actualRequestBuilder.proxyServer);
    assertNull(actualRequestBuilder.bodyGenerator);
    assertNull(actualRequestBuilder.uri);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders.size());
    assertEquals(0L, actualRequestBuilder.rangeOffset);
    assertEquals(ChannelPoolPartitioning.PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualRequestBuilder.uriEncoder);
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }
}
