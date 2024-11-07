package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.util.LinkedList;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.request.body.Body;
import org.asynchttpclient.request.body.generator.PushBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NettyBodyBodyDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NettyBodyBody#NettyBodyBody(Body, AsyncHttpClientConfig)}
   *   <li>{@link NettyBodyBody#getBody()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters() {
    // Arrange
    PushBody body = new PushBody(new LinkedList<>());

    // Act
    Body actualBody = (new NettyBodyBody(body, mock(AsyncHttpClientConfig.class))).getBody();

    // Assert
    assertTrue(actualBody instanceof PushBody);
    assertSame(body, actualBody);
  }

  /**
   * Test {@link NettyBodyBody#getContentLength()}.
   * <ul>
   *   <li>Given {@link PushBody#PushBody(Queue)} with queue is
   * {@link LinkedList#LinkedList()}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyBodyBody#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength(); given PushBody(Queue) with queue is LinkedList(); then return minus one")
  void testGetContentLength_givenPushBodyWithQueueIsLinkedList_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1L,
        (new NettyBodyBody(new PushBody(new LinkedList<>()), mock(AsyncHttpClientConfig.class))).getContentLength());
  }
}
