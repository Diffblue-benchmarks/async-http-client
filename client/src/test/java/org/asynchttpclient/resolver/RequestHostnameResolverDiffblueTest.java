package org.asynchttpclient.resolver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.resolver.NameResolver;
import java.net.InetSocketAddress;
import org.asynchttpclient.AbstractBasicTest;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.RequestBuilderBase;
import org.junit.jupiter.api.Test;

class RequestHostnameResolverDiffblueTest {
  /**
   * Method under test:
   * {@link RequestHostnameResolver#resolve(NameResolver, InetSocketAddress, AsyncHandler)}
   */
  @Test
  void testResolve() {
    // Arrange
    InetSocketAddress unresolvedAddress = InetSocketAddress.createUnresolved("foo", 1);

    // Act and Assert
    assertTrue(
        RequestHostnameResolver.INSTANCE
            .resolve(RequestBuilderBase.DEFAULT_NAME_RESOLVER, unresolvedAddress,
                new AbstractBasicTest.AsyncCompletionHandlerAdapter())
            .isDone());
  }
}
