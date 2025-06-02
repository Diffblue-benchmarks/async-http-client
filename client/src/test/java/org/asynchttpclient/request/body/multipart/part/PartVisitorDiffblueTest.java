package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import java.io.UnsupportedEncodingException;
import org.asynchttpclient.request.body.multipart.part.PartVisitor.ByteBufVisitor;
import org.asynchttpclient.request.body.multipart.part.PartVisitor.CounterPartVisitor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PartVisitorDiffblueTest {
  /**
   * Test ByteBufVisitor {@link ByteBufVisitor#withBytes(byte[])}.
   * <ul>
   *   <li>Then calls {@link CompositeByteBuf#writeBytes(byte[])}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ByteBufVisitor#withBytes(byte[])}
   */
  @Test
  @DisplayName("Test ByteBufVisitor withBytes(byte[]); then calls writeBytes(byte[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ByteBufVisitor.withBytes(byte[])"})
  void testByteBufVisitorWithBytes_thenCallsWriteBytes() throws UnsupportedEncodingException {
    // Arrange
    CompositeByteBuf target = mock(CompositeByteBuf.class);
    when(target.writeBytes(Mockito.<byte[]>any())).thenReturn(Unpooled.compositeBuffer(3));
    ByteBufVisitor byteBufVisitor = new ByteBufVisitor(target);

    // Act
    byteBufVisitor.withBytes("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    verify(target).writeBytes(isA(byte[].class));
  }

  /**
   * Test CounterPartVisitor getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link CounterPartVisitor}
   *   <li>{@link CounterPartVisitor#withByte(byte)}
   *   <li>{@link CounterPartVisitor#withBytes(byte[])}
   *   <li>{@link CounterPartVisitor#getCount()}
   * </ul>
   */
  @Test
  @DisplayName("Test CounterPartVisitor getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CounterPartVisitor.<init>()", "int CounterPartVisitor.getCount()",
      "void CounterPartVisitor.withByte(byte)", "void CounterPartVisitor.withBytes(byte[])"})
  void testCounterPartVisitorGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange and Act
    CounterPartVisitor actualCounterPartVisitor = new CounterPartVisitor();
    actualCounterPartVisitor.withByte((byte) 'A');
    actualCounterPartVisitor.withBytes("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(9, actualCounterPartVisitor.getCount());
  }
}
