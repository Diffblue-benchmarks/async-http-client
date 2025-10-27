package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.UnsupportedEncodingException;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.junit.jupiter.api.Test;

class FileLikeMultipartPartDiffblueTest {
  /**
   * Method under test:
   * {@link FileLikeMultipartPart#visitDispositionHeader(PartVisitor)}
   */
  @Test
  void testVisitDispositionHeader() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    PartVisitor.CounterPartVisitor visitor = new PartVisitor.CounterPartVisitor();

    // Act
    byteArrayMultipartPart.visitDispositionHeader(visitor);

    // Assert
    assertEquals(68, visitor.getCount());
  }

  /**
   * Method under test:
   * {@link FileLikeMultipartPart#visitDispositionHeader(PartVisitor)}
   */
  @Test
  void testVisitDispositionHeader2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart(null, "AXAXAXAX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    PartVisitor.CounterPartVisitor visitor = new PartVisitor.CounterPartVisitor();

    // Act
    byteArrayMultipartPart.visitDispositionHeader(visitor);

    // Assert
    assertEquals(Integer.SIZE, visitor.getCount());
  }

  /**
   * Method under test:
   * {@link FileLikeMultipartPart#visitDispositionHeader(PartVisitor)}
   */
  @Test
  void testVisitDispositionHeader3() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    PartVisitor.CounterPartVisitor visitor = new PartVisitor.CounterPartVisitor();

    // Act
    byteArrayMultipartPart.visitDispositionHeader(visitor);

    // Assert
    assertEquals(86, visitor.getCount());
  }
}
