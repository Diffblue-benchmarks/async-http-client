package org.asynchttpclient.resolver;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.resolver.NameResolver;
import io.netty.util.concurrent.Future;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.asynchttpclient.AbstractBasicTest;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.RequestBuilderBase;
import org.asynchttpclient.test.EventCollectingHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RequestHostnameResolverDiffblueTest {
  /**
   * Test
   * {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   * <ul>
   *   <li>Then {@link EventCollectingHandler} (default constructor)
   * {@link EventCollectingHandler#firedEvents} size is two.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}
   */
  @Test
  @DisplayName("Test resolve(NameResolver, InetSocketAddress, AsyncHandler); then EventCollectingHandler (default constructor) firedEvents size is two")
  void testResolve_thenEventCollectingHandlerFiredEventsSizeIsTwo() {
    // Arrange
    InetSocketAddress unresolvedAddress = InetSocketAddress.createUnresolved("foo", 1);
    EventCollectingHandler asyncHandler = new EventCollectingHandler();

    // Act
    Future<List<InetSocketAddress>> actualResolveResult = RequestHostnameResolver.INSTANCE
        .resolve(RequestBuilderBase.DEFAULT_NAME_RESOLVER, unresolvedAddress, asyncHandler);

    // Assert
    assertEquals(2, asyncHandler.firedEvents.size());
    assertTrue(actualResolveResult.isDone());
  }

  /**
   * Test
   * {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   * <ul>
   *   <li>When createUnresolved {@code 42} and one.</li>
   *   <li>Then return {@link java.util.concurrent.Future#get()} size is one.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}
   */
  @Test
  @DisplayName("Test resolve(NameResolver, InetSocketAddress, AsyncHandler); when createUnresolved '42' and one; then return get() size is one")
  void testResolve_whenCreateUnresolved42AndOne_thenReturnGetSizeIsOne()
      throws InterruptedException, ExecutionException {
    // Arrange
    InetSocketAddress unresolvedAddress = InetSocketAddress.createUnresolved("42", 1);

    // Act and Assert
    List<InetSocketAddress> getResult = RequestHostnameResolver.INSTANCE
        .resolve(RequestBuilderBase.DEFAULT_NAME_RESOLVER, unresolvedAddress,
            new AbstractBasicTest.AsyncCompletionHandlerAdapter())
        .get();
    assertEquals(1, getResult.size());
    InetSocketAddress getResult2 = getResult.get(0);
    InetAddress address = getResult2.getAddress();
    assertEquals("0.0.0.42", address.getCanonicalHostName());
    assertEquals("0.0.0.42", address.getHostAddress());
    assertEquals("0.0.0.42", address.getHostName());
    assertEquals("0.0.0.42", getResult2.getHostName());
    assertEquals("0.0.0.42", getResult2.getHostString());
    assertEquals(1, getResult2.getPort());
    assertFalse(address.isAnyLocalAddress());
    assertFalse(address.isLinkLocalAddress());
    assertFalse(address.isLoopbackAddress());
    assertFalse(address.isMCGlobal());
    assertFalse(address.isMCLinkLocal());
    assertFalse(address.isMCNodeLocal());
    assertFalse(address.isMCOrgLocal());
    assertFalse(address.isMCSiteLocal());
    assertFalse(address.isMulticastAddress());
    assertFalse(address.isSiteLocalAddress());
    assertFalse(getResult2.isUnresolved());
    assertArrayEquals(new byte[]{0, 0, 0, '*'}, address.getAddress());
  }

  /**
   * Test
   * {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   * <ul>
   *   <li>When createUnresolved {@code 42} and one.</li>
   *   <li>Then return {@link java.util.concurrent.Future#get()} size is one.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}
   */
  @Test
  @DisplayName("Test resolve(NameResolver, InetSocketAddress, AsyncHandler); when createUnresolved '42' and one; then return get() size is one")
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
    InetAddress address = getResult2.getAddress();
    assertEquals("0.0.0.42", address.getCanonicalHostName());
    assertEquals("0.0.0.42", address.getHostAddress());
    assertEquals("0.0.0.42", address.getHostName());
    assertEquals("0.0.0.42", getResult2.getHostName());
    assertEquals("0.0.0.42", getResult2.getHostString());
    assertEquals(1, getResult2.getPort());
    assertEquals(2, asyncHandler.firedEvents.size());
    assertFalse(address.isAnyLocalAddress());
    assertFalse(address.isLinkLocalAddress());
    assertFalse(address.isLoopbackAddress());
    assertFalse(address.isMCGlobal());
    assertFalse(address.isMCLinkLocal());
    assertFalse(address.isMCNodeLocal());
    assertFalse(address.isMCOrgLocal());
    assertFalse(address.isMCSiteLocal());
    assertFalse(address.isMulticastAddress());
    assertFalse(address.isSiteLocalAddress());
    assertFalse(getResult2.isUnresolved());
    assertArrayEquals(new byte[]{0, 0, 0, '*'}, address.getAddress());
  }

  /**
   * Test
   * {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   * <ul>
   *   <li>When {@link RequestBuilderBase#DEFAULT_NAME_RESOLVER}.</li>
   *   <li>Then return Done.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}
   */
  @Test
  @DisplayName("Test resolve(NameResolver, InetSocketAddress, AsyncHandler); when DEFAULT_NAME_RESOLVER; then return Done")
  void testResolve_whenDefault_name_resolver_thenReturnDone() {
    // Arrange
    InetSocketAddress unresolvedAddress = InetSocketAddress.createUnresolved("foo", 1);

    // Act and Assert
    assertTrue(
        RequestHostnameResolver.INSTANCE
            .resolve(RequestBuilderBase.DEFAULT_NAME_RESOLVER, unresolvedAddress,
                new AbstractBasicTest.AsyncCompletionHandlerAdapter())
            .isDone());
  }

  /**
   * Test
   * {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Done.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}
   */
  @Test
  @DisplayName("Test resolve(NameResolver, InetSocketAddress, AsyncHandler); when 'null'; then return Done")
  void testResolve_whenNull_thenReturnDone() {
    // Arrange, Act and Assert
    assertTrue(RequestHostnameResolver.INSTANCE
        .resolve(RequestBuilderBase.DEFAULT_NAME_RESOLVER, InetSocketAddress.createUnresolved("foo", 1), null)
        .isDone());
  }
}
