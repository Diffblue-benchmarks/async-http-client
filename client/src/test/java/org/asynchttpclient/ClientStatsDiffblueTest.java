package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;

class ClientStatsDiffblueTest {
  /**
   * Method under test: {@link ClientStats#getTotalConnectionCount()}
   */
  @Test
  void testGetTotalConnectionCount() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ClientStats(new HashMap<>())).getTotalConnectionCount());
  }

  /**
   * Method under test: {@link ClientStats#getTotalConnectionCount()}
   */
  @Test
  void testGetTotalConnectionCount2() {
    // Arrange
    HashMap<String, HostStats> statsPerHost = new HashMap<>();
    statsPerHost.computeIfPresent("foo", mock(BiFunction.class));

    // Act and Assert
    assertEquals(0L, (new ClientStats(statsPerHost)).getTotalConnectionCount());
  }

  /**
   * Method under test: {@link ClientStats#getTotalActiveConnectionCount()}
   */
  @Test
  void testGetTotalActiveConnectionCount() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ClientStats(new HashMap<>())).getTotalActiveConnectionCount());
  }

  /**
   * Method under test: {@link ClientStats#getTotalActiveConnectionCount()}
   */
  @Test
  void testGetTotalActiveConnectionCount2() {
    // Arrange
    HashMap<String, HostStats> statsPerHost = new HashMap<>();
    statsPerHost.computeIfPresent("foo", mock(BiFunction.class));

    // Act and Assert
    assertEquals(0L, (new ClientStats(statsPerHost)).getTotalActiveConnectionCount());
  }

  /**
   * Method under test: {@link ClientStats#getTotalIdleConnectionCount()}
   */
  @Test
  void testGetTotalIdleConnectionCount() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ClientStats(new HashMap<>())).getTotalIdleConnectionCount());
  }

  /**
   * Method under test: {@link ClientStats#getTotalIdleConnectionCount()}
   */
  @Test
  void testGetTotalIdleConnectionCount2() {
    // Arrange
    HashMap<String, HostStats> statsPerHost = new HashMap<>();
    statsPerHost.computeIfPresent("foo", mock(BiFunction.class));

    // Act and Assert
    assertEquals(0L, (new ClientStats(statsPerHost)).getTotalIdleConnectionCount());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ClientStats#equals(Object)}
   *   <li>{@link ClientStats#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    ClientStats clientStats = new ClientStats(new HashMap<>());
    ClientStats clientStats2 = new ClientStats(new HashMap<>());

    // Act and Assert
    assertEquals(clientStats, clientStats2);
    int expectedHashCodeResult = clientStats.hashCode();
    assertEquals(expectedHashCodeResult, clientStats2.hashCode());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ClientStats#equals(Object)}
   *   <li>{@link ClientStats#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ClientStats clientStats = new ClientStats(new HashMap<>());

    // Act and Assert
    assertEquals(clientStats, clientStats);
    int expectedHashCodeResult = clientStats.hashCode();
    assertEquals(expectedHashCodeResult, clientStats.hashCode());
  }

  /**
   * Method under test: {@link ClientStats#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    HashMap<String, HostStats> statsPerHost = new HashMap<>();
    statsPerHost.put("foo", new HostStats(3L, 1L));
    ClientStats clientStats = new ClientStats(statsPerHost);

    // Act and Assert
    assertNotEquals(clientStats, new ClientStats(new HashMap<>()));
  }

  /**
   * Method under test: {@link ClientStats#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    HashMap<String, HostStats> statsPerHost = new HashMap<>();
    statsPerHost.computeIfPresent("foo", mock(BiFunction.class));
    statsPerHost.put("foo", new HostStats(3L, 1L));
    ClientStats clientStats = new ClientStats(statsPerHost);

    // Act and Assert
    assertNotEquals(clientStats, new ClientStats(new HashMap<>()));
  }

  /**
   * Method under test: {@link ClientStats#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ClientStats(new HashMap<>()), null);
  }

  /**
   * Method under test: {@link ClientStats#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ClientStats(new HashMap<>()), "Different type to ClientStats");
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ClientStats#toString()}
   *   <li>{@link ClientStats#getStatsPerHost()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    ClientStats clientStats = new ClientStats(new HashMap<>());

    // Act
    String actualToStringResult = clientStats.toString();

    // Assert
    assertEquals("There are 0 total connections, 0 are active and 0 are idle.", actualToStringResult);
    assertTrue(clientStats.getStatsPerHost().isEmpty());
  }

  /**
   * Method under test: {@link ClientStats#ClientStats(Map)}
   */
  @Test
  void testNewClientStats() {
    // Arrange and Act
    ClientStats actualClientStats = new ClientStats(new HashMap<>());

    // Assert
    assertEquals(0L, actualClientStats.getTotalActiveConnectionCount());
    assertEquals(0L, actualClientStats.getTotalConnectionCount());
    assertEquals(0L, actualClientStats.getTotalIdleConnectionCount());
    assertTrue(actualClientStats.getStatsPerHost().isEmpty());
  }

  /**
   * Method under test: {@link ClientStats#ClientStats(Map)}
   */
  @Test
  void testNewClientStats2() {
    // Arrange
    HashMap<String, HostStats> statsPerHost = new HashMap<>();
    statsPerHost.computeIfPresent("foo", mock(BiFunction.class));

    // Act
    ClientStats actualClientStats = new ClientStats(statsPerHost);

    // Assert
    assertEquals(0L, actualClientStats.getTotalActiveConnectionCount());
    assertEquals(0L, actualClientStats.getTotalConnectionCount());
    assertEquals(0L, actualClientStats.getTotalIdleConnectionCount());
    assertTrue(actualClientStats.getStatsPerHost().isEmpty());
  }
}
