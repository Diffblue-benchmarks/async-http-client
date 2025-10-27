package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;

class ParamDiffblueTest {
  /**
   * Method under test: {@link Param#map2ParamList(Map)}
   */
  @Test
  void testMap2ParamList() {
    // Arrange and Act
    List<Param> actualMap2ParamListResult = Param.map2ParamList(new HashMap<>());

    // Assert
    assertTrue(actualMap2ParamListResult.isEmpty());
  }

  /**
   * Method under test: {@link Param#map2ParamList(Map)}
   */
  @Test
  void testMap2ParamList2() {
    // Arrange and Act
    List<Param> actualMap2ParamListResult = Param.map2ParamList(null);

    // Assert
    assertNull(actualMap2ParamListResult);
  }

  /**
   * Method under test: {@link Param#map2ParamList(Map)}
   */
  @Test
  void testMap2ParamList3() {
    // Arrange
    HashMap<String, List<String>> map = new HashMap<>();
    map.put("foo", new ArrayList<>());

    // Act
    List<Param> actualMap2ParamListResult = Param.map2ParamList(map);

    // Assert
    assertTrue(actualMap2ParamListResult.isEmpty());
  }

  /**
   * Method under test: {@link Param#map2ParamList(Map)}
   */
  @Test
  void testMap2ParamList4() {
    // Arrange
    HashMap<String, List<String>> map = new HashMap<>();
    map.computeIfPresent("foo", mock(BiFunction.class));
    map.put("foo", new ArrayList<>());

    // Act
    List<Param> actualMap2ParamListResult = Param.map2ParamList(map);

    // Assert
    assertTrue(actualMap2ParamListResult.isEmpty());
  }

  /**
   * Method under test: {@link Param#map2ParamList(Map)}
   */
  @Test
  void testMap2ParamList5() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");

    HashMap<String, List<String>> map = new HashMap<>();
    map.put("foo", stringList);

    // Act
    List<Param> actualMap2ParamListResult = Param.map2ParamList(map);

    // Assert
    assertEquals(1, actualMap2ParamListResult.size());
    Param getResult = actualMap2ParamListResult.get(0);
    assertEquals("foo", getResult.getName());
    assertEquals("foo", getResult.getValue());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link Param#equals(Object)}
   *   <li>{@link Param#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Param param = new Param("https://example.org/example", "https://example.org/example");
    Param param2 = new Param("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(param, param2);
    int expectedHashCodeResult = param.hashCode();
    assertEquals(expectedHashCodeResult, param2.hashCode());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link Param#equals(Object)}
   *   <li>{@link Param#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    Param param = new Param(null, "https://example.org/example");
    Param param2 = new Param(null, "https://example.org/example");

    // Act and Assert
    assertEquals(param, param2);
    int expectedHashCodeResult = param.hashCode();
    assertEquals(expectedHashCodeResult, param2.hashCode());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link Param#equals(Object)}
   *   <li>{@link Param#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual3() {
    // Arrange
    Param param = new Param("https://example.org/example", null);
    Param param2 = new Param("https://example.org/example", null);

    // Act and Assert
    assertEquals(param, param2);
    int expectedHashCodeResult = param.hashCode();
    assertEquals(expectedHashCodeResult, param2.hashCode());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link Param#equals(Object)}
   *   <li>{@link Param#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Param param = new Param("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(param, param);
    int expectedHashCodeResult = param.hashCode();
    assertEquals(expectedHashCodeResult, param.hashCode());
  }

  /**
   * Method under test: {@link Param#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Param param = new Param("Name", "https://example.org/example");

    // Act and Assert
    assertNotEquals(param, new Param("https://example.org/example", "https://example.org/example"));
  }

  /**
   * Method under test: {@link Param#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Param param = new Param(null, "https://example.org/example");

    // Act and Assert
    assertNotEquals(param, new Param("https://example.org/example", "https://example.org/example"));
  }

  /**
   * Method under test: {@link Param#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Param param = new Param("https://example.org/example", null);

    // Act and Assert
    assertNotEquals(param, new Param("https://example.org/example", "https://example.org/example"));
  }

  /**
   * Method under test: {@link Param#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Param("https://example.org/example", "https://example.org/example"), null);
  }

  /**
   * Method under test: {@link Param#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Param("https://example.org/example", "https://example.org/example"), "Different type to Param");
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link Param#Param(String, String)}
   *   <li>{@link Param#getName()}
   *   <li>{@link Param#getValue()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    Param actualParam = new Param("https://example.org/example", "https://example.org/example");
    String actualName = actualParam.getName();

    // Assert
    assertEquals("https://example.org/example", actualName);
    assertEquals("https://example.org/example", actualParam.getValue());
  }
}
