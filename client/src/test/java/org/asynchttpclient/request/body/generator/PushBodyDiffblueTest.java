package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import java.util.LinkedList;
import java.util.Queue;
import org.asynchttpclient.request.body.Body;
import org.junit.jupiter.api.Test;

class PushBodyDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>{@link PushBody#close()}
   *   <li>{@link PushBody#getContentLength()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    PushBody pushBody = new PushBody(new LinkedList<>());

    // Act
    pushBody.close();

    // Assert that nothing has changed
    assertEquals(-1L, pushBody.getContentLength());
  }

  /**
   * Method under test: {@link PushBody#transferTo(ByteBuf)}
   */
  @Test
  void testTransferTo() {
    // Arrange
    PushBody pushBody = new PushBody(new LinkedList<>());

    // Act and Assert
    assertEquals(Body.BodyState.SUSPEND,
        pushBody.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Method under test: {@link PushBody#transferTo(ByteBuf)}
   */
  @Test
  void testTransferTo2() {
    // Arrange
    PushBody pushBody = new PushBody(new LinkedList<>());
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);

    // Act
    Body.BodyState actualTransferToResult = pushBody
        .transferTo(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(Body.BodyState.SUSPEND, actualTransferToResult);
  }

  /**
   * Method under test: {@link PushBody#PushBody(Queue)}
   */
  @Test
  void testNewPushBody() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new PushBody(new LinkedList<>())).getContentLength());
  }

  /**
   * Method under test: {@link PushBody#PushBody(Queue)}
   */
  @Test
  void testNewPushBody2() {
    // Arrange
    LinkedList<BodyChunk> queue = new LinkedList<>();
    queue.add(new BodyChunk(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));

    // Act and Assert
    assertEquals(-1L, (new PushBody(queue)).getContentLength());
  }

  /**
   * Method under test: {@link PushBody#PushBody(Queue)}
   */
  @Test
  void testNewPushBody3() {
    // Arrange
    LinkedList<BodyChunk> queue = new LinkedList<>();
    queue.add(new BodyChunk(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));
    queue.add(new BodyChunk(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));

    // Act and Assert
    assertEquals(-1L, (new PushBody(queue)).getContentLength());
  }

  /**
   * Method under test: {@link PushBody#PushBody(Queue)}
   */
  @Test
  void testNewPushBody4() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    BodyChunk bodyChunk = new BodyChunk(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))),
        true);

    LinkedList<BodyChunk> queue = new LinkedList<>();
    queue.add(bodyChunk);

    // Act
    PushBody actualPushBody = new PushBody(queue);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(-1L, actualPushBody.getContentLength());
  }
}
