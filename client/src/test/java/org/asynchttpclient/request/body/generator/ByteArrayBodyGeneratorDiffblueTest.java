package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import java.io.UnsupportedEncodingException;
import org.asynchttpclient.request.body.Body;
import org.junit.jupiter.api.Test;

class ByteArrayBodyGeneratorDiffblueTest {
  /**
   * Method under test: {@link ByteArrayBodyGenerator.ByteBody#getContentLength()}
   */
  @Test
  void testByteBodyGetContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(8L, ((new ByteArrayBodyGenerator("AXAXAXAX".getBytes("UTF-8"))).new ByteBody()).getContentLength());
  }

  /**
   * Method under test:
   * {@link ByteArrayBodyGenerator.ByteBody#ByteBody(ByteArrayBodyGenerator)}
   */
  @Test
  void testByteBodyNewByteBody() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(8L, ((new ByteArrayBodyGenerator("AXAXAXAX".getBytes("UTF-8"))).new ByteBody()).getContentLength());
  }

  /**
   * Method under test:
   * {@link ByteArrayBodyGenerator.ByteBody#transferTo(ByteBuf)}
   */
  @Test
  void testByteBodyTransferTo() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayBodyGenerator.ByteBody byteBody = (new ByteArrayBodyGenerator(
        "AXAXAXAX".getBytes("UTF-8"))).new ByteBody();

    // Act and Assert
    assertEquals(Body.BodyState.CONTINUE,
        byteBody.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Method under test:
   * {@link ByteArrayBodyGenerator.ByteBody#transferTo(ByteBuf)}
   */
  @Test
  void testByteBodyTransferTo2() {
    // Arrange
    ByteArrayBodyGenerator.ByteBody byteBody = (new ByteArrayBodyGenerator(new byte[]{})).new ByteBody();

    // Act and Assert
    assertEquals(Body.BodyState.CONTINUE,
        byteBody.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Method under test:
   * {@link ByteArrayBodyGenerator.ByteBody#transferTo(ByteBuf)}
   */
  @Test
  void testByteBodyTransferTo3() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayBodyGenerator.ByteBody byteBody = (new ByteArrayBodyGenerator(
        "AXAXAXAX".getBytes("UTF-8"))).new ByteBody();

    // Act and Assert
    assertEquals(Body.BodyState.CONTINUE, byteBody.transferTo(new DuplicatedByteBuf(Unpooled.compositeBuffer(3))));
  }

  /**
   * Method under test:
   * {@link ByteArrayBodyGenerator.ByteBody#transferTo(ByteBuf)}
   */
  @Test
  void testByteBodyTransferTo4() {
    // Arrange
    ByteArrayBodyGenerator.ByteBody byteBody = (new ByteArrayBodyGenerator(new byte[]{})).new ByteBody();

    // Act and Assert
    assertEquals(Body.BodyState.CONTINUE, byteBody.transferTo(new DuplicatedByteBuf(Unpooled.compositeBuffer(3))));
  }

  /**
   * Method under test: {@link ByteArrayBodyGenerator#createBody()}
   */
  @Test
  void testCreateBody() throws UnsupportedEncodingException {
    // Arrange and Act
    Body actualCreateBodyResult = (new ByteArrayBodyGenerator("AXAXAXAX".getBytes("UTF-8"))).createBody();

    // Assert
    assertTrue(actualCreateBodyResult instanceof ByteArrayBodyGenerator.ByteBody);
    assertEquals(8L, actualCreateBodyResult.getContentLength());
  }
}
