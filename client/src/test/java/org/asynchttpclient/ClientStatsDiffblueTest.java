package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClientStatsDiffblueTest {
  /**
   * Test {@link ClientStats#ClientStats(Map)}.
   * <p>
   * Method under test: {@link ClientStats#ClientStats(Map)}
   */
  @Test
  @DisplayName("Test new ClientStats(Map)")
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
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ClientStats#toString()}
   *   <li>{@link ClientStats#getStatsPerHost()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
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
   * Test {@link ClientStats#getTotalConnectionCount()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientStats#getTotalConnectionCount()}
   */
  @Test
  @DisplayName("Test getTotalConnectionCount(); then return zero")
  void testGetTotalConnectionCount_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ClientStats(new HashMap<>())).getTotalConnectionCount());
  }

  /**
   * Test {@link ClientStats#getTotalActiveConnectionCount()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientStats#getTotalActiveConnectionCount()}
   */
  @Test
  @DisplayName("Test getTotalActiveConnectionCount(); then return zero")
  void testGetTotalActiveConnectionCount_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ClientStats(new HashMap<>())).getTotalActiveConnectionCount());
  }

  /**
   * Test {@link ClientStats#getTotalIdleConnectionCount()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientStats#getTotalIdleConnectionCount()}
   */
  @Test
  @DisplayName("Test getTotalIdleConnectionCount(); then return zero")
  void testGetTotalIdleConnectionCount_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0L, (new ClientStats(new HashMap<>())).getTotalIdleConnectionCount());
  }

  /**
   * Test {@link ClientStats#equals(Object)}, and {@link ClientStats#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ClientStats#equals(Object)}
   *   <li>{@link ClientStats#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
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
   * Test {@link ClientStats#equals(Object)}, and {@link ClientStats#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ClientStats#equals(Object)}
   *   <li>{@link ClientStats#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ClientStats clientStats = new ClientStats(new HashMap<>());

    // Act and Assert
    assertEquals(clientStats, clientStats);
    int expectedHashCodeResult = clientStats.hashCode();
    assertEquals(expectedHashCodeResult, clientStats.hashCode());
  }

  /**
   * Test {@link ClientStats#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientStats#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    HashMap<String, HostStats> statsPerHost = new HashMap<>();
    statsPerHost.put("foo", new HostStats(3L, 1L));
    ClientStats clientStats = new ClientStats(statsPerHost);

    // Act and Assert
    assertNotEquals(clientStats, new ClientStats(new HashMap<>()));
  }

  /**
   * Test {@link ClientStats#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientStats#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ClientStats(new HashMap<>()), null);
  }

  /**
   * Test {@link ClientStats#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientStats#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ClientStats(new HashMap<>()), "Different type to ClientStats");
  }
}
