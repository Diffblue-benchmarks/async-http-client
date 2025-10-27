package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.request.body.Body;
import org.asynchttpclient.request.body.multipart.part.ByteArrayMultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartPart;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MultipartBodyDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>{@link MultipartBody#getBoundary()}
   *   <li>{@link MultipartBody#getContentLength()}
   *   <li>{@link MultipartBody#getContentType()}
   * </ul>
   */
  @Test
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

  /**
   * Method under test: {@link MultipartBody#transferTo(ByteBuf)}
   */
  @Test
  void testTransferTo() throws IOException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    MultipartBody newMultipartBodyResult = MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());

    // Act and Assert
    assertEquals(Body.BodyState.CONTINUE,
        newMultipartBodyResult.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Method under test: {@link MultipartBody#transferTo(ByteBuf)}
   */
  @Test
  void testTransferTo2() throws IOException {
    // Arrange
    DefaultHttpHeaders requestHeaders = mock(DefaultHttpHeaders.class);
    when(requestHeaders.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");
    MultipartBody newMultipartBodyResult = MultipartUtils.newMultipartBody(new ArrayList<>(), requestHeaders);

    // Act
    Body.BodyState actualTransferToResult = newMultipartBodyResult
        .transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    verify(requestHeaders).get(isA(CharSequence.class));
    assertEquals(Body.BodyState.CONTINUE, actualTransferToResult);
  }

  /**
   * Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  void testNewMultipartBody() throws UnsupportedEncodingException {
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
   * Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  void testNewMultipartBody2() throws UnsupportedEncodingException {
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
   * Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  void testNewMultipartBody3() throws UnsupportedEncodingException {
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
}
