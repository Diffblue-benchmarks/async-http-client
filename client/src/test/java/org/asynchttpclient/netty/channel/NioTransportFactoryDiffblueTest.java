package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import java.util.Iterator;
import java.util.concurrent.ThreadFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NioTransportFactoryDiffblueTest {
  /**
   * Test {@link NioTransportFactory#newEventLoopGroup(int, ThreadFactory)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then next terminationFuture return {@link DefaultPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NioTransportFactory#newEventLoopGroup(int, ThreadFactory)}
   */
  @Test
  @DisplayName("Test newEventLoopGroup(int, ThreadFactory); when zero; then next terminationFuture return DefaultPromise")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NioEventLoopGroup NioTransportFactory.newEventLoopGroup(int, ThreadFactory)"})
  void testNewEventLoopGroup_whenZero_thenNextTerminationFutureReturnDefaultPromise() {
    // Arrange and Act
    NioEventLoopGroup actualNewEventLoopGroupResult = NioTransportFactory.INSTANCE.newEventLoopGroup(0, null);

    // Assert
    Iterator<EventExecutor> iteratorResult = actualNewEventLoopGroupResult.iterator();
    EventExecutor actualNextResult = iteratorResult.next();
    EventExecutor nextResult = iteratorResult.next();
    EventExecutor nextResult2 = iteratorResult.next();
    boolean actualHasNextResult = iteratorResult.hasNext();
    EventLoop nextResult3 = actualNewEventLoopGroupResult.next();
    assertTrue(nextResult3 instanceof NioEventLoop);
    assertTrue(nextResult instanceof NioEventLoop);
    assertTrue(nextResult2 instanceof NioEventLoop);
    assertTrue(nextResult3.terminationFuture() instanceof DefaultPromise);
    assertTrue(actualHasNextResult);
    assertSame(actualNewEventLoopGroupResult, nextResult3.parent());
    assertSame(nextResult3, nextResult3.next());
    assertSame(nextResult3, actualNextResult);
  }
}
