package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MiscUtilsDiffblueTest {
  /**
   * Test {@link MiscUtils#isNonEmpty(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(byte[])}
   */
  @Test
  @DisplayName("Test isNonEmpty(byte[]) with 'byte[]'; when 'AXAXAXAX' Bytes is 'UTF-8'; then return 'true'")
  void testIsNonEmptyWithByte_whenAxaxaxaxBytesIsUtf8_thenReturnTrue() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertTrue(MiscUtils.isNonEmpty("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(byte[])}
   */
  @Test
  @DisplayName("Test isNonEmpty(byte[]) with 'byte[]'; when empty array of byte; then return 'false'")
  void testIsNonEmptyWithByte_whenEmptyArrayOfByte_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MiscUtils.isNonEmpty(new byte[]{}));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(byte[])}
   */
  @Test
  @DisplayName("Test isNonEmpty(byte[]) with 'byte[]'; when 'null'; then return 'false'")
  void testIsNonEmptyWithByte_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MiscUtils.isNonEmpty((byte[]) null));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(Collection)} with {@code Collection}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(Collection)}
   */
  @Test
  @DisplayName("Test isNonEmpty(Collection) with 'Collection'; given '42'; when ArrayList() add '42'; then return 'true'")
  void testIsNonEmptyWithCollection_given42_whenArrayListAdd42_thenReturnTrue() {
    // Arrange
    ArrayList<Object> collection = new ArrayList<>();
    collection.add("42");

    // Act and Assert
    assertTrue(MiscUtils.isNonEmpty(collection));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(Collection)} with {@code Collection}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(Collection)}
   */
  @Test
  @DisplayName("Test isNonEmpty(Collection) with 'Collection'; given '42'; when ArrayList() add '42'; then return 'true'")
  void testIsNonEmptyWithCollection_given42_whenArrayListAdd42_thenReturnTrue2() {
    // Arrange
    ArrayList<Object> collection = new ArrayList<>();
    collection.add("42");
    collection.add("42");

    // Act and Assert
    assertTrue(MiscUtils.isNonEmpty(collection));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(Collection)} with {@code Collection}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(Collection)}
   */
  @Test
  @DisplayName("Test isNonEmpty(Collection) with 'Collection'; when ArrayList(); then return 'false'")
  void testIsNonEmptyWithCollection_whenArrayList_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MiscUtils.isNonEmpty(new ArrayList<>()));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(Collection)} with {@code Collection}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(Collection)}
   */
  @Test
  @DisplayName("Test isNonEmpty(Collection) with 'Collection'; when 'null'; then return 'false'")
  void testIsNonEmptyWithCollection_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MiscUtils.isNonEmpty((Collection<?>) null));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(Map)} with {@code Map}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code 42} is {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(Map)}
   */
  @Test
  @DisplayName("Test isNonEmpty(Map) with 'Map'; given '42'; when HashMap() '42' is '42'; then return 'true'")
  void testIsNonEmptyWithMap_given42_whenHashMap42Is42_thenReturnTrue() {
    // Arrange
    HashMap<Object, Object> map = new HashMap<>();
    map.put("42", "42");

    // Act and Assert
    assertTrue(MiscUtils.isNonEmpty(map));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(Map)} with {@code Map}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(Map)}
   */
  @Test
  @DisplayName("Test isNonEmpty(Map) with 'Map'; when HashMap(); then return 'false'")
  void testIsNonEmptyWithMap_whenHashMap_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MiscUtils.isNonEmpty(new HashMap<>()));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(Map)} with {@code Map}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(Map)}
   */
  @Test
  @DisplayName("Test isNonEmpty(Map) with 'Map'; when 'null'; then return 'false'")
  void testIsNonEmptyWithMap_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MiscUtils.isNonEmpty((Map<?, ?>) null));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(Object[])} with {@code Object[]}.
   * <ul>
   *   <li>When array of {@link Object} with {@code Array}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(Object[])}
   */
  @Test
  @DisplayName("Test isNonEmpty(Object[]) with 'Object[]'; when array of Object with 'Array'; then return 'true'")
  void testIsNonEmptyWithObject_whenArrayOfObjectWithArray_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(MiscUtils.isNonEmpty(new Object[]{"Array"}));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(Object[])} with {@code Object[]}.
   * <ul>
   *   <li>When empty array of {@link Object}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(Object[])}
   */
  @Test
  @DisplayName("Test isNonEmpty(Object[]) with 'Object[]'; when empty array of Object; then return 'false'")
  void testIsNonEmptyWithObject_whenEmptyArrayOfObject_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MiscUtils.isNonEmpty(new Object[]{}));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(Object[])} with {@code Object[]}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(Object[])}
   */
  @Test
  @DisplayName("Test isNonEmpty(Object[]) with 'Object[]'; when 'null'; then return 'false'")
  void testIsNonEmptyWithObject_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MiscUtils.isNonEmpty((Object[]) null));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(String)} with {@code String}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(String)}
   */
  @Test
  @DisplayName("Test isNonEmpty(String) with 'String'; when empty string; then return 'false'")
  void testIsNonEmptyWithString_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MiscUtils.isNonEmpty(""));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(String)} with {@code String}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(String)}
   */
  @Test
  @DisplayName("Test isNonEmpty(String) with 'String'; when 'https://example.org/example'; then return 'true'")
  void testIsNonEmptyWithString_whenHttpsExampleOrgExample_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(MiscUtils.isNonEmpty("https://example.org/example"));
  }

  /**
   * Test {@link MiscUtils#isNonEmpty(String)} with {@code String}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isNonEmpty(String)}
   */
  @Test
  @DisplayName("Test isNonEmpty(String) with 'String'; when 'null'; then return 'false'")
  void testIsNonEmptyWithString_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MiscUtils.isNonEmpty((String) null));
  }

  /**
   * Test {@link MiscUtils#withDefault(Object, Object)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code Def}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#withDefault(Object, Object)}
   */
  @Test
  @DisplayName("Test withDefault(Object, Object); when 'null'; then return 'Def'")
  void testWithDefault_whenNull_thenReturnDef() {
    // Arrange, Act and Assert
    assertEquals("Def", MiscUtils.withDefault(null, "Def"));
  }

  /**
   * Test {@link MiscUtils#withDefault(Object, Object)}.
   * <ul>
   *   <li>When {@code Value}.</li>
   *   <li>Then return {@code Value}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#withDefault(Object, Object)}
   */
  @Test
  @DisplayName("Test withDefault(Object, Object); when 'Value'; then return 'Value'")
  void testWithDefault_whenValue_thenReturnValue() {
    // Arrange, Act and Assert
    assertEquals("Value", MiscUtils.withDefault("Value", "Def"));
  }

  /**
   * Test {@link MiscUtils#isEmpty(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isEmpty(String)}
   */
  @Test
  @DisplayName("Test isEmpty(String); when empty string; then return 'true'")
  void testIsEmpty_whenEmptyString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(MiscUtils.isEmpty(""));
  }

  /**
   * Test {@link MiscUtils#isEmpty(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isEmpty(String)}
   */
  @Test
  @DisplayName("Test isEmpty(String); when 'https://example.org/example'; then return 'false'")
  void testIsEmpty_whenHttpsExampleOrgExample_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MiscUtils.isEmpty("https://example.org/example"));
  }

  /**
   * Test {@link MiscUtils#isEmpty(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#isEmpty(String)}
   */
  @Test
  @DisplayName("Test isEmpty(String); when 'null'; then return 'true'")
  void testIsEmpty_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(MiscUtils.isEmpty(null));
  }

  /**
   * Test {@link MiscUtils#closeSilently(Closeable)}.
   * <ul>
   *   <li>Given {@link ChannelClosedException#INSTANCE}.</li>
   *   <li>When {@link Closeable} {@link Closeable#close()} throw
   * {@link ChannelClosedException#INSTANCE}.</li>
   *   <li>Then calls {@link Closeable#close()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#closeSilently(Closeable)}
   */
  @Test
  @DisplayName("Test closeSilently(Closeable); given INSTANCE; when Closeable close() throw INSTANCE; then calls close()")
  void testCloseSilently_givenInstance_whenCloseableCloseThrowInstance_thenCallsClose() throws IOException {
    // Arrange
    Closeable closeable = mock(Closeable.class);
    doThrow(ChannelClosedException.INSTANCE).when(closeable).close();

    // Act
    MiscUtils.closeSilently(closeable);

    // Assert that nothing has changed
    verify(closeable).close();
  }

  /**
   * Test {@link MiscUtils#closeSilently(Closeable)}.
   * <ul>
   *   <li>When {@link Closeable} {@link Closeable#close()} does nothing.</li>
   *   <li>Then calls {@link Closeable#close()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#closeSilently(Closeable)}
   */
  @Test
  @DisplayName("Test closeSilently(Closeable); when Closeable close() does nothing; then calls close()")
  void testCloseSilently_whenCloseableCloseDoesNothing_thenCallsClose() throws IOException {
    // Arrange
    Closeable closeable = mock(Closeable.class);
    doNothing().when(closeable).close();

    // Act
    MiscUtils.closeSilently(closeable);

    // Assert that nothing has changed
    verify(closeable).close();
  }

  /**
   * Test {@link MiscUtils#getCause(Throwable)}.
   * <ul>
   *   <li>Then {@link IOException#IOException(String, Throwable)} with {@code foo}
   * and {@link ChannelClosedException#INSTANCE} Cause is
   * {@link ChannelClosedException#INSTANCE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#getCause(Throwable)}
   */
  @Test
  @DisplayName("Test getCause(Throwable); then IOException(String, Throwable) with 'foo' and INSTANCE Cause is INSTANCE")
  void testGetCause_thenIOExceptionWithFooAndInstanceCauseIsInstance() {
    // Arrange
    IOException t = new IOException("foo", ChannelClosedException.INSTANCE);

    // Act
    Throwable actualCause = MiscUtils.getCause(t);

    // Assert
    assertTrue(actualCause instanceof ChannelClosedException);
    assertEquals("Channel closed", actualCause.getLocalizedMessage());
    assertEquals("Channel closed", actualCause.getMessage());
    assertNull(actualCause.getCause());
    assertEquals(0, actualCause.getSuppressed().length);
    assertSame(((ChannelClosedException) actualCause).INSTANCE, t.getCause());
  }

  /**
   * Test {@link MiscUtils#getCause(Throwable)}.
   * <ul>
   *   <li>When {@link ChannelClosedException#INSTANCE}.</li>
   *   <li>Then {@link ChannelClosedException#INSTANCE} Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MiscUtils#getCause(Throwable)}
   */
  @Test
  @DisplayName("Test getCause(Throwable); when INSTANCE; then INSTANCE Cause is 'null'")
  void testGetCause_whenInstance_thenInstanceCauseIsNull() {
    // Arrange
    ChannelClosedException t = ChannelClosedException.INSTANCE;

    // Act
    Throwable actualCause = MiscUtils.getCause(t);

    // Assert
    assertTrue(actualCause instanceof ChannelClosedException);
    assertEquals("Channel closed", actualCause.getLocalizedMessage());
    assertEquals("Channel closed", actualCause.getMessage());
    assertNull(actualCause.getCause());
    assertNull(t.getCause());
    assertEquals(0, actualCause.getSuppressed().length);
  }
}
