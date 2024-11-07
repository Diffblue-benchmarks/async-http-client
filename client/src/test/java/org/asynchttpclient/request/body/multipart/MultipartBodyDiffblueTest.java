package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.request.body.multipart.part.ByteArrayMultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MultipartBodyDiffblueTest {
  /**
   * Test {@link MultipartBody#MultipartBody(List, String, byte[])}.
   * <ul>
   *   <li>Then return ContentLength is one hundred thirty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  @DisplayName("Test new MultipartBody(List, String, byte[]); then return ContentLength is one hundred thirty-two")
  void testNewMultipartBody_thenReturnContentLengthIsOneHundredThirtyTwo() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    parts.add(new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8")));
    byte[] boundary = "AXAXAXAX".getBytes("UTF-8");

    // Act
    MultipartBody actualMultipartBody = new MultipartBody(parts, "https://example.org/example", boundary);

    // Assert
    assertEquals("https://example.org/example", actualMultipartBody.getContentType());
    assertEquals(132L, actualMultipartBody.getContentLength());
    assertSame(boundary, actualMultipartBody.getBoundary());
  }

  /**
   * Test {@link MultipartBody#MultipartBody(List, String, byte[])}.
   * <ul>
   *   <li>Then return ContentLength is two hundred sixty-four.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  @DisplayName("Test new MultipartBody(List, String, byte[]); then return ContentLength is two hundred sixty-four")
  void testNewMultipartBody_thenReturnContentLengthIsTwoHundredSixtyFour() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    parts.add(new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8")));
    ByteArrayPart part2 = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    parts.add(new ByteArrayMultipartPart(part2, "AXAXAXAX".getBytes("UTF-8")));
    byte[] boundary = "AXAXAXAX".getBytes("UTF-8");

    // Act
    MultipartBody actualMultipartBody = new MultipartBody(parts, "https://example.org/example", boundary);

    // Assert
    assertEquals("https://example.org/example", actualMultipartBody.getContentType());
    assertEquals(264L, actualMultipartBody.getContentLength());
    assertSame(boundary, actualMultipartBody.getBoundary());
  }

  /**
   * Test {@link MultipartBody#MultipartBody(List, String, byte[])}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return ContentLength is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  @DisplayName("Test new MultipartBody(List, String, byte[]); when ArrayList(); then return ContentLength is zero")
  void testNewMultipartBody_whenArrayList_thenReturnContentLengthIsZero() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    byte[] boundary = "AXAXAXAX".getBytes("UTF-8");

    // Act
    MultipartBody actualMultipartBody = new MultipartBody(parts, "https://example.org/example", boundary);

    // Assert
    assertEquals("https://example.org/example", actualMultipartBody.getContentType());
    assertEquals(0L, actualMultipartBody.getContentLength());
    assertSame(boundary, actualMultipartBody.getBoundary());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MultipartBody#getBoundary()}
   *   <li>{@link MultipartBody#getContentLength()}
   *   <li>{@link MultipartBody#getContentType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    MultipartBody multipartBody = new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act
    byte[] actualBoundary = multipartBody.getBoundary();
    long actualContentLength = multipartBody.getContentLength();

    // Assert
    assertEquals("https://example.org/example", multipartBody.getContentType());
    assertEquals(0L, actualContentLength);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBoundary);
  }
}
