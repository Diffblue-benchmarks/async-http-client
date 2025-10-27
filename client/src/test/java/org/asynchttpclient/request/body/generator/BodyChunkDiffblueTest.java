package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import org.junit.jupiter.api.Test;

class BodyChunkDiffblueTest {
  /**
   * Method under test: {@link BodyChunk#BodyChunk(ByteBuf, boolean)}
   */
  @Test
  void testNewBodyChunk() {
    // Arrange
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());

    // Act
    BodyChunk actualBodyChunk = new BodyChunk(new DuplicatedByteBuf(buffer), true);

    // Assert
    ByteBuf byteBuf = actualBodyChunk.buffer;
    assertTrue(byteBuf instanceof DuplicatedByteBuf);
    assertTrue(actualBodyChunk.last);
    assertEquals(buffer, byteBuf);
  }
}
