package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParamDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Param#Param(String, String)}
   *   <li>{@link Param#getName()}
   *   <li>{@link Param#getValue()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters() {
    // Arrange and Act
    Param actualParam = new Param("https://example.org/example", "https://example.org/example");
    String actualName = actualParam.getName();

    // Assert
    assertEquals("https://example.org/example", actualName);
    assertEquals("https://example.org/example", actualParam.getValue());
  }

  /**
   * Test {@link Param#map2ParamList(Map)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Param#map2ParamList(Map)}
   */
  @Test
  @DisplayName("Test map2ParamList(Map); given ArrayList() add 'foo'; then return size is one")
  void testMap2ParamList_givenArrayListAddFoo_thenReturnSizeIsOne() {
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
   * Test {@link Param#map2ParamList(Map)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is
   * {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Param#map2ParamList(Map)}
   */
  @Test
  @DisplayName("Test map2ParamList(Map); given ArrayList(); when HashMap() 'foo' is ArrayList(); then return Empty")
  void testMap2ParamList_givenArrayList_whenHashMapFooIsArrayList_thenReturnEmpty() {
    // Arrange
    HashMap<String, List<String>> map = new HashMap<>();
    map.put("foo", new ArrayList<>());

    // Act
    List<Param> actualMap2ParamListResult = Param.map2ParamList(map);

    // Assert
    assertTrue(actualMap2ParamListResult.isEmpty());
  }

  /**
   * Test {@link Param#map2ParamList(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Param#map2ParamList(Map)}
   */
  @Test
  @DisplayName("Test map2ParamList(Map); when HashMap(); then return Empty")
  void testMap2ParamList_whenHashMap_thenReturnEmpty() {
    // Arrange and Act
    List<Param> actualMap2ParamListResult = Param.map2ParamList(new HashMap<>());

    // Assert
    assertTrue(actualMap2ParamListResult.isEmpty());
  }

  /**
   * Test {@link Param#map2ParamList(Map)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Param#map2ParamList(Map)}
   */
  @Test
  @DisplayName("Test map2ParamList(Map); when 'null'; then return 'null'")
  void testMap2ParamList_whenNull_thenReturnNull() {
    // Arrange and Act
    List<Param> actualMap2ParamListResult = Param.map2ParamList(null);

    // Assert
    assertNull(actualMap2ParamListResult);
  }

  /**
   * Test {@link Param#equals(Object)}, and {@link Param#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Param#equals(Object)}
   *   <li>{@link Param#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
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
   * Test {@link Param#equals(Object)}, and {@link Param#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Param#equals(Object)}
   *   <li>{@link Param#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
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
   * Test {@link Param#equals(Object)}, and {@link Param#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Param#equals(Object)}
   *   <li>{@link Param#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
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
   * Test {@link Param#equals(Object)}, and {@link Param#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Param#equals(Object)}
   *   <li>{@link Param#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Param param = new Param("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(param, param);
    int expectedHashCodeResult = param.hashCode();
    assertEquals(expectedHashCodeResult, param.hashCode());
  }

  /**
   * Test {@link Param#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Param#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Param param = new Param("Name", "https://example.org/example");

    // Act and Assert
    assertNotEquals(param, new Param("https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Param#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Param#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Param param = new Param(null, "https://example.org/example");

    // Act and Assert
    assertNotEquals(param, new Param("https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Param#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Param#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Param param = new Param("https://example.org/example", null);

    // Act and Assert
    assertNotEquals(param, new Param("https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Param#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Param#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Param("https://example.org/example", "https://example.org/example"), null);
  }

  /**
   * Test {@link Param#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Param#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Param("https://example.org/example", "https://example.org/example"), "Different type to Param");
  }
}
