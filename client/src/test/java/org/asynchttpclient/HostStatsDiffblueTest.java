package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

class HostStatsDiffblueTest {
  /**
   * Method under test: {@link HostStats#getHostConnectionCount()}
   */
  @Test
  void testGetHostConnectionCount() {
    // Arrange, Act and Assert
    assertEquals(4L, (new HostStats(3L, 1L)).getHostConnectionCount());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link HostStats#equals(Object)}
   *   <li>{@link HostStats#hashCode()}
   * </ul>
   */
  @Test
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
   * Methods under test:
   * <ul>
   *   <li>{@link HostStats#equals(Object)}
   *   <li>{@link HostStats#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    HostStats hostStats = new HostStats(3L, 1L);

    // Act and Assert
    assertEquals(hostStats, hostStats);
    int expectedHashCodeResult = hostStats.hashCode();
    assertEquals(expectedHashCodeResult, hostStats.hashCode());
  }

  /**
   * Method under test: {@link HostStats#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    HostStats hostStats = new HostStats(1L, 1L);

    // Act and Assert
    assertNotEquals(hostStats, new HostStats(3L, 1L));
  }

  /**
   * Method under test: {@link HostStats#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    HostStats hostStats = new HostStats(3L, 2L);

    // Act and Assert
    assertNotEquals(hostStats, new HostStats(3L, 1L));
  }

  /**
   * Method under test: {@link HostStats#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new HostStats(3L, 1L), null);
  }

  /**
   * Method under test: {@link HostStats#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new HostStats(3L, 1L), "Different type to HostStats");
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link HostStats#toString()}
   *   <li>{@link HostStats#getHostActiveConnectionCount()}
   *   <li>{@link HostStats#getHostIdleConnectionCount()}
   * </ul>
   */
  @Test
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
   * Method under test: {@link HostStats#HostStats(long, long)}
   */
  @Test
  void testNewHostStats() {
    // Arrange and Act
    HostStats actualHostStats = new HostStats(3L, 1L);

    // Assert
    assertEquals(1L, actualHostStats.getHostIdleConnectionCount());
    assertEquals(3L, actualHostStats.getHostActiveConnectionCount());
    assertEquals(4L, actualHostStats.getHostConnectionCount());
  }
}
