package org.asynchttpclient.resolver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.DefaultEventLoop;
import io.netty.resolver.DefaultNameResolver;
import io.netty.resolver.NameResolver;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.asynchttpclient.AbstractBasicTest;
import org.asynchttpclient.AbstractBasicTest.AsyncCompletionHandlerAdapter;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.RequestBuilderBase;
import org.asynchttpclient.test.EventCollectingHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RequestHostnameResolverDiffblueTest {
  /**
   * Test {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link DefaultEventLoop#DefaultEventLoop()} addShutdownHook {@code null}.</li>
   *   <li>Then return Done.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}
   */
  @Test
  @DisplayName("Test resolve(NameResolver, InetSocketAddress, AsyncHandler); given 'null'; when DefaultEventLoop() addShutdownHook 'null'; then return Done")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "io.netty.util.concurrent.Future RequestHostnameResolver.resolve(NameResolver, InetSocketAddress, AsyncHandler)"})
  void testResolve_givenNull_whenDefaultEventLoopAddShutdownHookNull_thenReturnDone() {
    // Arrange
    DefaultEventLoop executor = new DefaultEventLoop();
    executor.addShutdownHook(null);
    DefaultNameResolver nameResolver = new DefaultNameResolver(executor);

    // Act and Assert
    assertTrue(
        RequestHostnameResolver.INSTANCE.resolve(nameResolver, InetSocketAddress.createUnresolved("foo", 1), null)
            .isDone());
  }

  /**
   * Test {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   * <ul>
   *   <li>Then {@link EventCollectingHandler} (default constructor) {@link EventCollectingHandler#firedEvents} size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}
   */
  @Test
  @DisplayName("Test resolve(NameResolver, InetSocketAddress, AsyncHandler); then EventCollectingHandler (default constructor) firedEvents size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "io.netty.util.concurrent.Future RequestHostnameResolver.resolve(NameResolver, InetSocketAddress, AsyncHandler)"})
  void testResolve_thenEventCollectingHandlerFiredEventsSizeIsTwo() {
    // Arrange
    InetSocketAddress unresolvedAddress = InetSocketAddress.createUnresolved("foo", 1);
    EventCollectingHandler asyncHandler = new EventCollectingHandler();

    // Act
    io.netty.util.concurrent.Future<List<InetSocketAddress>> actualResolveResult = RequestHostnameResolver.INSTANCE
        .resolve(RequestBuilderBase.DEFAULT_NAME_RESOLVER, unresolvedAddress, asyncHandler);

    // Assert
    assertEquals(2, asyncHandler.firedEvents.size());
    assertTrue(actualResolveResult.isDone());
  }

  /**
   * Test {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   * <ul>
   *   <li>When createUnresolved {@code 42} and one.</li>
   *   <li>Then return {@link Future#get()} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}
   */
  @Test
  @DisplayName("Test resolve(NameResolver, InetSocketAddress, AsyncHandler); when createUnresolved '42' and one; then return get() size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "io.netty.util.concurrent.Future RequestHostnameResolver.resolve(NameResolver, InetSocketAddress, AsyncHandler)"})
  void testResolve_whenCreateUnresolved42AndOne_thenReturnGetSizeIsOne()
      throws InterruptedException, ExecutionException {
    // Arrange
    InetSocketAddress unresolvedAddress = InetSocketAddress.createUnresolved("42", 1);

    // Act and Assert
    List<InetSocketAddress> getResult = RequestHostnameResolver.INSTANCE
        .resolve(RequestBuilderBase.DEFAULT_NAME_RESOLVER, unresolvedAddress, new AsyncCompletionHandlerAdapter())
        .get();
    assertEquals(1, getResult.size());
    InetSocketAddress getResult2 = getResult.get(0);
    assertEquals("0.0.0.42", getResult2.getHostName());
    assertEquals("0.0.0.42", getResult2.getHostString());
    assertEquals(1, getResult2.getPort());
    assertFalse(getResult2.isUnresolved());
  }

  /**
   * Test {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   * <ul>
   *   <li>When createUnresolved {@code 42} and one.</li>
   *   <li>Then return {@link Future#get()} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}
   */
  @Test
  @DisplayName("Test resolve(NameResolver, InetSocketAddress, AsyncHandler); when createUnresolved '42' and one; then return get() size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "io.netty.util.concurrent.Future RequestHostnameResolver.resolve(NameResolver, InetSocketAddress, AsyncHandler)"})
  void testResolve_whenCreateUnresolved42AndOne_thenReturnGetSizeIsOne2()
      throws InterruptedException, ExecutionException {
    // Arrange
    InetSocketAddress unresolvedAddress = InetSocketAddress.createUnresolved("42", 1);
    EventCollectingHandler asyncHandler = new EventCollectingHandler();

    // Act and Assert
    List<InetSocketAddress> getResult = RequestHostnameResolver.INSTANCE
        .resolve(RequestBuilderBase.DEFAULT_NAME_RESOLVER, unresolvedAddress, asyncHandler)
        .get();
    assertEquals(1, getResult.size());
    InetSocketAddress getResult2 = getResult.get(0);
    assertEquals("0.0.0.42", getResult2.getHostName());
    assertEquals("0.0.0.42", getResult2.getHostString());
    assertEquals(1, getResult2.getPort());
    assertEquals(2, asyncHandler.firedEvents.size());
    assertFalse(getResult2.isUnresolved());
  }

  /**
   * Test {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   * <ul>
   *   <li>When {@link RequestBuilderBase#DEFAULT_NAME_RESOLVER}.</li>
   *   <li>Then return Done.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}
   */
  @Test
  @DisplayName("Test resolve(NameResolver, InetSocketAddress, AsyncHandler); when DEFAULT_NAME_RESOLVER; then return Done")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "io.netty.util.concurrent.Future RequestHostnameResolver.resolve(NameResolver, InetSocketAddress, AsyncHandler)"})
  void testResolve_whenDefault_name_resolver_thenReturnDone() {
    // Arrange
    InetSocketAddress unresolvedAddress = InetSocketAddress.createUnresolved("foo", 1);

    // Act and Assert
    assertTrue(RequestHostnameResolver.INSTANCE
        .resolve(RequestBuilderBase.DEFAULT_NAME_RESOLVER, unresolvedAddress, new AsyncCompletionHandlerAdapter())
        .isDone());
  }

  /**
   * Test {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Done.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}
   */
  @Test
  @DisplayName("Test resolve(NameResolver, InetSocketAddress, AsyncHandler); when 'null'; then return Done")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "io.netty.util.concurrent.Future RequestHostnameResolver.resolve(NameResolver, InetSocketAddress, AsyncHandler)"})
  void testResolve_whenNull_thenReturnDone() {
    // Arrange, Act and Assert
    assertTrue(RequestHostnameResolver.INSTANCE
        .resolve(RequestBuilderBase.DEFAULT_NAME_RESOLVER, InetSocketAddress.createUnresolved("foo", 1), null)
        .isDone());
  }
}
