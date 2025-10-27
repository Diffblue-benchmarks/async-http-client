package org.asynchttpclient.handler.resumable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PropertiesBasedResumableProcessorDiffblueTest {
  /**
   * Method under test:
   * {@link PropertiesBasedResumableProcessor#put(String, long)}
   */
  @Test
  void testPut() {
    // Arrange
    PropertiesBasedResumableProcessor propertiesBasedResumableProcessor = new PropertiesBasedResumableProcessor();

    // Act
    propertiesBasedResumableProcessor.put("https://example.org/example", 1L);

    // Assert
    Map<String, Long> loadResult = propertiesBasedResumableProcessor.load();
    assertEquals(2, loadResult.size());
    assertEquals(1L, loadResult.get("https://example.org/example").longValue());
    assertTrue(loadResult.containsKey("http://localhost/test2.url"));
  }

  /**
   * Method under test: {@link PropertiesBasedResumableProcessor#load()}
   */
  @Test
  void testLoad() {
    // Arrange and Act
    Map<String, Long> actualLoadResult = (new PropertiesBasedResumableProcessor()).load();

    // Assert
    assertEquals(1, actualLoadResult.size());
    assertEquals(50L, actualLoadResult.get("http://localhost/test2.url").longValue());
  }
}
