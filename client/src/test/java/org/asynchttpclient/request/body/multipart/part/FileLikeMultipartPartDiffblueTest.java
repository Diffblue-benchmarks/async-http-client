package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.request.body.multipart.part.PartVisitor.CounterPartVisitor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FileLikeMultipartPartDiffblueTest {
  /**
   * Test {@link FileLikeMultipartPart#visitDispositionHeader(PartVisitor)}.
   * <ul>
   *   <li>Then {@link CounterPartVisitor} (default constructor) Count is eighty-six.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileLikeMultipartPart#visitDispositionHeader(PartVisitor)}
   */
  @Test
  @DisplayName("Test visitDispositionHeader(PartVisitor); then CounterPartVisitor (default constructor) Count is eighty-six")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileLikeMultipartPart.visitDispositionHeader(PartVisitor)"})
  void testVisitDispositionHeader_thenCounterPartVisitorCountIsEightySix() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    CounterPartVisitor visitor = new CounterPartVisitor();

    // Act
    byteArrayMultipartPart.visitDispositionHeader(visitor);

    // Assert
    assertEquals(86, visitor.getCount());
  }

  /**
   * Test {@link FileLikeMultipartPart#visitDispositionHeader(PartVisitor)}.
   * <ul>
   *   <li>Then {@link CounterPartVisitor} (default constructor) Count is sixty-eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileLikeMultipartPart#visitDispositionHeader(PartVisitor)}
   */
  @Test
  @DisplayName("Test visitDispositionHeader(PartVisitor); then CounterPartVisitor (default constructor) Count is sixty-eight")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileLikeMultipartPart.visitDispositionHeader(PartVisitor)"})
  void testVisitDispositionHeader_thenCounterPartVisitorCountIsSixtyEight() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    CounterPartVisitor visitor = new CounterPartVisitor();

    // Act
    byteArrayMultipartPart.visitDispositionHeader(visitor);

    // Assert
    assertEquals(68, visitor.getCount());
  }

  /**
   * Test {@link FileLikeMultipartPart#visitDispositionHeader(PartVisitor)}.
   * <ul>
   *   <li>Then {@link CounterPartVisitor} (default constructor) Count is {@link Integer#SIZE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileLikeMultipartPart#visitDispositionHeader(PartVisitor)}
   */
  @Test
  @DisplayName("Test visitDispositionHeader(PartVisitor); then CounterPartVisitor (default constructor) Count is SIZE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileLikeMultipartPart.visitDispositionHeader(PartVisitor)"})
  void testVisitDispositionHeader_thenCounterPartVisitorCountIsSize() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart(null, "AXAXAXAX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    CounterPartVisitor visitor = new CounterPartVisitor();

    // Act
    byteArrayMultipartPart.visitDispositionHeader(visitor);

    // Assert
    assertEquals(Integer.SIZE, visitor.getCount());
  }
}
