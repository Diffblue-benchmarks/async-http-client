package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.channel.nio.NioEventLoopGroup;
import org.asynchttpclient.netty.channel.ChannelManager;
import org.asynchttpclient.netty.channel.DefaultChannelPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultAsyncHttpClientDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DefaultAsyncHttpClient#channelManager()}
   *   <li>{@link DefaultAsyncHttpClient#getConfig()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    // Act
    ChannelManager actualChannelManagerResult = defaultAsyncHttpClient.channelManager();
    AsyncHttpClientConfig actualConfig = defaultAsyncHttpClient.getConfig();

    // Assert
    assertTrue(actualChannelManagerResult.getEventLoopGroup() instanceof NioEventLoopGroup);
    assertTrue(actualConfig instanceof DefaultAsyncHttpClientConfig);
    assertTrue(actualChannelManagerResult.getChannelPool() instanceof DefaultChannelPool);
    ClientStats clientStats = actualChannelManagerResult.getClientStats();
    assertEquals(0L, clientStats.getTotalActiveConnectionCount());
    assertEquals(0L, clientStats.getTotalConnectionCount());
    assertEquals(0L, clientStats.getTotalIdleConnectionCount());
    assertTrue(clientStats.getStatsPerHost().isEmpty());
    assertTrue(actualChannelManagerResult.isOpen());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DefaultAsyncHttpClient#channelManager()}
   *   <li>{@link DefaultAsyncHttpClient#getConfig()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters2() {
    // Arrange
    DefaultAsyncHttpClient defaultAsyncHttpClient = new DefaultAsyncHttpClient();

    // Act
    ChannelManager actualChannelManagerResult = defaultAsyncHttpClient.channelManager();
    AsyncHttpClientConfig actualConfig = defaultAsyncHttpClient.getConfig();

    // Assert
    assertTrue(actualChannelManagerResult.getEventLoopGroup() instanceof NioEventLoopGroup);
    assertTrue(actualConfig instanceof DefaultAsyncHttpClientConfig);
    assertTrue(actualChannelManagerResult.getChannelPool() instanceof DefaultChannelPool);
    ClientStats clientStats = actualChannelManagerResult.getClientStats();
    assertEquals(0L, clientStats.getTotalActiveConnectionCount());
    assertEquals(0L, clientStats.getTotalConnectionCount());
    assertEquals(0L, clientStats.getTotalIdleConnectionCount());
    assertTrue(clientStats.getStatsPerHost().isEmpty());
    assertTrue(actualChannelManagerResult.isOpen());
  }
}
