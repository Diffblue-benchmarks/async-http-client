package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PartVisitorDiffblueTest {
  /**
   * Method under test: {@link PartVisitor.ByteBufVisitor#withBytes(byte[])}
   */
  @Test
  void testByteBufVisitorWithBytes() throws UnsupportedEncodingException {
    // Arrange
    CompositeByteBuf target = mock(CompositeByteBuf.class);
    when(target.writeBytes(Mockito.<byte[]>any())).thenReturn(Unpooled.compositeBuffer(3));
    PartVisitor.ByteBufVisitor byteBufVisitor = new PartVisitor.ByteBufVisitor(target);

    // Act
    byteBufVisitor.withBytes("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    verify(target).writeBytes(isA(byte[].class));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of
   * {@link PartVisitor.CounterPartVisitor}
   *   <li>{@link PartVisitor.CounterPartVisitor#withByte(byte)}
   *   <li>{@link PartVisitor.CounterPartVisitor#withBytes(byte[])}
   *   <li>{@link PartVisitor.CounterPartVisitor#getCount()}
   * </ul>
   */
  @Test
  void testCounterPartVisitorGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange and Act
    PartVisitor.CounterPartVisitor actualCounterPartVisitor = new PartVisitor.CounterPartVisitor();
    actualCounterPartVisitor.withByte((byte) 'A');
    actualCounterPartVisitor.withBytes("AXAXAXAX".getBytes("UTF-8"));

    // Assert that nothing has changed
    assertEquals(9, actualCounterPartVisitor.getCount());
  }
}
