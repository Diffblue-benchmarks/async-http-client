package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class FileLikePartDiffblueTest {
  /**
   * Method under test: {@link FileLikePart#getFileName()}
   */
  @Test
  void testGetFileName() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getFileName());
  }

  /**
   * Method under test: {@link FileLikePart#toString()}
   */
  @Test
  void testToString() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(
        "ByteArrayPart name=https://example.org/example contentType=application/octet-stream charset=null"
            + " transferEncoding=null contentId=null dispositionType=null filename=null",
        (new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).toString());
  }
}
