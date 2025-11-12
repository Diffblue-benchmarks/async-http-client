package org.asynchttpclient.resolver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.DefaultEventLoop;
import io.netty.resolver.NameResolver;
import io.netty.util.concurrent.DefaultProgressivePromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import org.asynchttpclient.AbstractBasicTest;
import org.asynchttpclient.AbstractBasicTest.AsyncCompletionHandlerAdapter;
import org.asynchttpclient.AsyncHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RequestHostnameResolverDiffblueTest {
  /**
   * Test {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   *
   * <ul>
   *   <li>Given {@link DefaultProgressivePromise#DefaultProgressivePromise(EventExecutor)} with
   *       executor is {@link DefaultEventLoop#DefaultEventLoop()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress,
   * AsyncHandler)}
   */
  @Test
  @DisplayName(
      "Test resolve(NameResolver, InetSocketAddress, AsyncHandler); given DefaultProgressivePromise(EventExecutor) with executor is DefaultEventLoop()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Future RequestHostnameResolver.resolve(NameResolver, InetSocketAddress, AsyncHandler)"
  })
  void testResolve_givenDefaultProgressivePromiseWithExecutorIsDefaultEventLoop() {
    // Arrange
    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));
    InetSocketAddress unresolvedAddress = InetSocketAddress.createUnresolved("localhost", 8080);

    // Act
    RequestHostnameResolver.INSTANCE.resolve(
        nameResolver, unresolvedAddress, new AsyncCompletionHandlerAdapter());

    // Assert
    verify(nameResolver).resolveAll("localhost");
  }

  /**
   * Test {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   *
   * <ul>
   *   <li>Then calls {@link DefaultProgressivePromise#addListener(GenericFutureListener)}.
   * </ul>
   *
   * <p>Method under test: {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress,
   * AsyncHandler)}
   */
  @Test
  @DisplayName(
      "Test resolve(NameResolver, InetSocketAddress, AsyncHandler); then calls addListener(GenericFutureListener)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Future RequestHostnameResolver.resolve(NameResolver, InetSocketAddress, AsyncHandler)"
  })
  void testResolve_thenCallsAddListener() {
    // Arrange
    DefaultProgressivePromise<List<InetAddress>> defaultProgressivePromise =
        mock(DefaultProgressivePromise.class);
    when(defaultProgressivePromise.addListener(
            Mockito.<GenericFutureListener<Future<List<InetAddress>>>>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any())).thenReturn(defaultProgressivePromise);
    InetSocketAddress unresolvedAddress = InetSocketAddress.createUnresolved("localhost", 8080);

    // Act
    RequestHostnameResolver.INSTANCE.resolve(
        nameResolver, unresolvedAddress, new AsyncCompletionHandlerAdapter());

    // Assert
    verify(nameResolver).resolveAll("localhost");
    verify(defaultProgressivePromise).addListener(isA(GenericFutureListener.class));
  }

  /**
   * Test {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   *
   * <ul>
   *   <li>Then calls {@link AsyncCompletionHandlerAdapter#onHostnameResolutionAttempt(String)}.
   * </ul>
   *
   * <p>Method under test: {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress,
   * AsyncHandler)}
   */
  @Test
  @DisplayName(
      "Test resolve(NameResolver, InetSocketAddress, AsyncHandler); then calls onHostnameResolutionAttempt(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Future RequestHostnameResolver.resolve(NameResolver, InetSocketAddress, AsyncHandler)"
  })
  void testResolve_thenCallsOnHostnameResolutionAttempt() {
    // Arrange
    DefaultProgressivePromise<List<InetAddress>> defaultProgressivePromise =
        mock(DefaultProgressivePromise.class);
    when(defaultProgressivePromise.addListener(
            Mockito.<GenericFutureListener<Future<List<InetAddress>>>>any()))
        .thenReturn(new DefaultProgressivePromise<>(new DefaultEventLoop()));

    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);
    when(nameResolver.resolveAll(Mockito.<String>any())).thenReturn(defaultProgressivePromise);
    InetSocketAddress unresolvedAddress = InetSocketAddress.createUnresolved("localhost", 8080);

    AsyncCompletionHandlerAdapter asyncHandler = mock(AsyncCompletionHandlerAdapter.class);
    doNothing().when(asyncHandler).onHostnameResolutionAttempt(Mockito.<String>any());

    // Act
    RequestHostnameResolver.INSTANCE.resolve(nameResolver, unresolvedAddress, asyncHandler);

    // Assert
    verify(nameResolver).resolveAll("localhost");
    verify(defaultProgressivePromise).addListener(isA(GenericFutureListener.class));
    verify(asyncHandler).onHostnameResolutionAttempt("localhost");
  }

  /**
   * Test {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}.
   *
   * <ul>
   *   <li>When {@link NameResolver}.
   *   <li>Then return Done.
   * </ul>
   *
   * <p>Method under test: {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress,
   * AsyncHandler)}
   */
  @Test
  @DisplayName(
      "Test resolve(NameResolver, InetSocketAddress, AsyncHandler); when NameResolver; then return Done")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Future RequestHostnameResolver.resolve(NameResolver, InetSocketAddress, AsyncHandler)"
  })
  void testResolve_whenNameResolver_thenReturnDone() {
    // Arrange
    NameResolver<InetAddress> nameResolver = mock(NameResolver.class);

    // Act and Assert
    assertTrue(
        RequestHostnameResolver.INSTANCE
            .resolve(nameResolver, InetSocketAddress.createUnresolved("localhost", 8080), null)
            .isDone());
  }
}
