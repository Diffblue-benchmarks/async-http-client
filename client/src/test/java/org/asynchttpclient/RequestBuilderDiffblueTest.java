package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.resolver.DefaultNameResolver;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.channel.ChannelPoolPartitioning.PerHostChannelPoolPartitioning;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RequestBuilderDiffblueTest {
  /**
   * Test {@link RequestBuilder#RequestBuilder()}.
   * <p>
   * Method under test: {@link RequestBuilder#RequestBuilder()}
   */
  @Test
  @DisplayName("Test new RequestBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RequestBuilder.<init>()"})
  void testNewRequestBuilder() {
    // Arrange and Act
    RequestBuilder actualRequestBuilder = new RequestBuilder();

    // Assert
    assertTrue(actualRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
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
    assertEquals(0L, actualRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualRequestBuilder.uriEncoder);
  }

  /**
   * Test {@link RequestBuilder#RequestBuilder(String, boolean, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return {@link RequestBuilderBase#uriEncoder} is {@code FIXING}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilder#RequestBuilder(String, boolean, boolean)}
   */
  @Test
  @DisplayName("Test new RequestBuilder(String, boolean, boolean); when 'false'; then return uriEncoder is 'FIXING'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RequestBuilder.<init>(String, boolean, boolean)"})
  void testNewRequestBuilder_whenFalse_thenReturnUriEncoderIsFixing() {
    // Arrange and Act
    RequestBuilder actualRequestBuilder = new RequestBuilder("https://example.org/example", false, false);

    // Assert
    assertTrue(actualRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
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
    assertEquals(0L, actualRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualRequestBuilder.uriEncoder);
  }

  /**
   * Test {@link RequestBuilder#RequestBuilder(String, boolean, boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then return {@link RequestBuilderBase#uriEncoder} is {@code RAW}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilder#RequestBuilder(String, boolean, boolean)}
   */
  @Test
  @DisplayName("Test new RequestBuilder(String, boolean, boolean); when 'true'; then return uriEncoder is 'RAW'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RequestBuilder.<init>(String, boolean, boolean)"})
  void testNewRequestBuilder_whenTrue_thenReturnUriEncoderIsRaw() {
    // Arrange and Act
    RequestBuilder actualRequestBuilder = new RequestBuilder("https://example.org/example", true, true);

    // Assert
    assertTrue(actualRequestBuilder.headers instanceof DefaultHttpHeaders);
    assertTrue(actualRequestBuilder.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestBuilder.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
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
    assertEquals(0L, actualRequestBuilder.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.RAW, actualRequestBuilder.uriEncoder);
  }
}
