package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.request.body.Body;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.request.body.multipart.InputStreamPart;
import org.asynchttpclient.request.body.multipart.MultipartBody;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.request.body.multipart.StringPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NettyMultipartBodyDiffblueTest {
  /**
   * Test {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}.
   * <p>
   * Method under test: {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test new NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NettyMultipartBody.<init>(List, HttpHeaders, AsyncHttpClientConfig)"})
  void testNewNettyMultipartBody() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertTrue((new NettyMultipartBody(parts, new DefaultHttpHeaders(), mock(AsyncHttpClientConfig.class)))
        .getBody() instanceof MultipartBody);
  }

  /**
   * Test {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}.
   * <p>
   * Method under test: {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test new NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NettyMultipartBody.<init>(List, HttpHeaders, AsyncHttpClientConfig)"})
  void testNewNettyMultipartBody2() {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new StringPart("https://example.org/example", "https://example.org/example"));

    // Act and Assert
    assertTrue((new NettyMultipartBody(parts, new DefaultHttpHeaders(), mock(AsyncHttpClientConfig.class)))
        .getBody() instanceof MultipartBody);
  }

  /**
   * Test {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}.
   * <p>
   * Method under test: {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test new NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NettyMultipartBody.<init>(List, HttpHeaders, AsyncHttpClientConfig)"})
  void testNewNettyMultipartBody3() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    byteArrayPart.addCustomHeader("https://example.org/example", "https://example.org/example");

    ArrayList<Part> parts = new ArrayList<>();
    parts.add(byteArrayPart);

    // Act and Assert
    assertTrue((new NettyMultipartBody(parts, new DefaultHttpHeaders(), mock(AsyncHttpClientConfig.class)))
        .getBody() instanceof MultipartBody);
  }

  /**
   * Test {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}.
   * <p>
   * Method under test: {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test new NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NettyMultipartBody.<init>(List, HttpHeaders, AsyncHttpClientConfig)"})
  void testNewNettyMultipartBody4() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    byteArrayPart.setCustomHeaders(new ArrayList<>());

    ArrayList<Part> parts = new ArrayList<>();
    parts.add(byteArrayPart);

    // Act and Assert
    assertTrue((new NettyMultipartBody(parts, new DefaultHttpHeaders(), mock(AsyncHttpClientConfig.class)))
        .getBody() instanceof MultipartBody);
  }

  /**
   * Test {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}.
   * <p>
   * Method under test: {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test new NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NettyMultipartBody.<init>(List, HttpHeaders, AsyncHttpClientConfig)"})
  void testNewNettyMultipartBody5() {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));
    parts.add(new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));

    // Act and Assert
    assertTrue((new NettyMultipartBody(parts, new DefaultHttpHeaders(), mock(AsyncHttpClientConfig.class)))
        .getBody() instanceof MultipartBody);
  }

  /**
   * Test {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}.
   * <ul>
   *   <li>Given {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with array of {@code byte} with {@code A} and one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test new NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig); given ByteArrayInputStream(byte[]) with array of byte with 'A' and one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NettyMultipartBody.<init>(List, HttpHeaders, AsyncHttpClientConfig)"})
  void testNewNettyMultipartBody_givenByteArrayInputStreamWithArrayOfByteWithAAndOne() {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new InputStreamPart("https://example.org/example",
        new ByteArrayInputStream(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}), "https://example.org/example", 3L));

    // Act and Assert
    assertTrue((new NettyMultipartBody(parts, new DefaultHttpHeaders(), mock(AsyncHttpClientConfig.class)))
        .getBody() instanceof MultipartBody);
  }

  /**
   * Test {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}.
   * <ul>
   *   <li>Then return ContentLength is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test new NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig); then return ContentLength is minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NettyMultipartBody.<init>(List, HttpHeaders, AsyncHttpClientConfig)"})
  void testNewNettyMultipartBody_thenReturnContentLengthIsMinusOne() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new InputStreamPart("https://example.org/example", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")),
        "https://example.org/example"));

    // Act
    NettyMultipartBody actualNettyMultipartBody = new NettyMultipartBody(parts, new DefaultHttpHeaders(),
        mock(AsyncHttpClientConfig.class));

    // Assert
    Body body = actualNettyMultipartBody.getBody();
    assertTrue(body instanceof MultipartBody);
    assertEquals(-1L, actualNettyMultipartBody.getContentLength());
    assertEquals(-1L, body.getContentLength());
  }

  /**
   * Test {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then Body return {@link MultipartBody}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyMultipartBody#NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test new NettyMultipartBody(List, HttpHeaders, AsyncHttpClientConfig); when ArrayList(); then Body return MultipartBody")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NettyMultipartBody.<init>(List, HttpHeaders, AsyncHttpClientConfig)"})
  void testNewNettyMultipartBody_whenArrayList_thenBodyReturnMultipartBody() {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();

    // Act and Assert
    assertTrue((new NettyMultipartBody(parts, new DefaultHttpHeaders(), mock(AsyncHttpClientConfig.class)))
        .getBody() instanceof MultipartBody);
  }
}
