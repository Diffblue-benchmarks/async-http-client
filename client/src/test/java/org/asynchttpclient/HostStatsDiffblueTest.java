package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HostStatsDiffblueTest {
  /**
   * Test {@link HostStats#HostStats(long, long)}.
   * <p>
   * Method under test: {@link HostStats#HostStats(long, long)}
   */
  @Test
  @DisplayName("Test new HostStats(long, long)")
  void testNewHostStats() {
    // Arrange and Act
    HostStats actualHostStats = new HostStats(3L, 1L);

    // Assert
    assertEquals(1L, actualHostStats.getHostIdleConnectionCount());
    assertEquals(3L, actualHostStats.getHostActiveConnectionCount());
    assertEquals(4L, actualHostStats.getHostConnectionCount());
  }

  /**
   * Test {@link HostStats#getHostConnectionCount()}.
   * <p>
   * Method under test: {@link HostStats#getHostConnectionCount()}
   */
  @Test
  @DisplayName("Test getHostConnectionCount()")
  void testGetHostConnectionCount() {
    // Arrange, Act and Assert
    assertEquals(4L, (new HostStats(3L, 1L)).getHostConnectionCount());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link HostStats#toString()}
   *   <li>{@link HostStats#getHostActiveConnectionCount()}
   *   <li>{@link HostStats#getHostIdleConnectionCount()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters() {
    // Arrange
    HostStats hostStats = new HostStats(3L, 1L);

    // Act
    String actualToStringResult = hostStats.toString();
    long actualHostActiveConnectionCount = hostStats.getHostActiveConnectionCount();

    // Assert
    assertEquals("There are 4 total connections, 3 are active and 1 are idle.", actualToStringResult);
    assertEquals(1L, hostStats.getHostIdleConnectionCount());
    assertEquals(3L, actualHostActiveConnectionCount);
  }

  /**
   * Test {@link HostStats#equals(Object)}, and {@link HostStats#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link HostStats#equals(Object)}
   *   <li>{@link HostStats#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    HostStats hostStats = new HostStats(3L, 1L);
    HostStats hostStats2 = new HostStats(3L, 1L);

    // Act and Assert
    assertEquals(hostStats, hostStats2);
    int expectedHashCodeResult = hostStats.hashCode();
    assertEquals(expectedHashCodeResult, hostStats2.hashCode());
  }

  /**
   * Test {@link HostStats#equals(Object)}, and {@link HostStats#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link HostStats#equals(Object)}
   *   <li>{@link HostStats#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    HostStats hostStats = new HostStats(3L, 1L);

    // Act and Assert
    assertEquals(hostStats, hostStats);
    int expectedHashCodeResult = hostStats.hashCode();
    assertEquals(expectedHashCodeResult, hostStats.hashCode());
  }

  /**
   * Test {@link HostStats#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link HostStats#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    HostStats hostStats = new HostStats(1L, 1L);

    // Act and Assert
    assertNotEquals(hostStats, new HostStats(3L, 1L));
  }

  /**
   * Test {@link HostStats#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link HostStats#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    HostStats hostStats = new HostStats(3L, 2L);

    // Act and Assert
    assertNotEquals(hostStats, new HostStats(3L, 1L));
  }

  /**
   * Test {@link HostStats#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link HostStats#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new HostStats(3L, 1L), null);
  }

  /**
   * Test {@link HostStats#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link HostStats#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new HostStats(3L, 1L), "Different type to HostStats");
  }
}
