package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.asynchttpclient.Param;
import org.asynchttpclient.request.body.multipart.FilePart;
import org.junit.jupiter.api.Test;

class FileMultipartPartDiffblueTest {
  /**
   * Method under test: {@link FileMultipartPart#close()}
   */
  @Test
  void testClose() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    FilePart part = mock(FilePart.class);
    when(part.getCharset()).thenReturn(null);
    when(part.getFile()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "").toFile());
    when(part.getFileName()).thenReturn("https://example.org/example");
    when(part.getContentId()).thenReturn("https://example.org/example");
    when(part.getContentType()).thenReturn("text/plain");
    when(part.getDispositionType()).thenReturn("https://example.org/example");
    when(part.getName()).thenReturn("https://example.org/example");
    when(part.getTransferEncoding()).thenReturn("https://example.org/example");
    when(part.getCustomHeaders()).thenReturn(new ArrayList<>());

    // Act
    (new FileMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).close();

    // Assert that nothing has changed
    verify(part, atLeast(1)).getFileName();
    verify(part).getFile();
    verify(part, atLeast(1)).getCharset();
    verify(part).getContentId();
    verify(part).getContentType();
    verify(part).getCustomHeaders();
    verify(part, atLeast(1)).getDispositionType();
    verify(part, atLeast(1)).getName();
    verify(part).getTransferEncoding();
  }

  /**
   * Method under test:
   * {@link FileMultipartPart#FileMultipartPart(FilePart, byte[])}
   */
  @Test
  void testNewFileMultipartPart() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    FilePart part = mock(FilePart.class);
    when(part.getCharset()).thenReturn(null);
    when(part.getFile()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    when(part.getFileName()).thenReturn("https://example.org/example");
    when(part.getContentId()).thenReturn("https://example.org/example");
    when(part.getContentType()).thenReturn("text/plain");
    when(part.getDispositionType()).thenReturn("https://example.org/example");
    when(part.getName()).thenReturn("https://example.org/example");
    when(part.getTransferEncoding()).thenReturn("https://example.org/example");
    when(part.getCustomHeaders()).thenReturn(new ArrayList<>());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FileMultipartPart(part, "AXAXAXAX".getBytes("UTF-8")));

    verify(part, atLeast(1)).getFileName();
    verify(part).getFile();
    verify(part, atLeast(1)).getCharset();
    verify(part).getContentId();
    verify(part).getContentType();
    verify(part).getCustomHeaders();
    verify(part, atLeast(1)).getDispositionType();
    verify(part, atLeast(1)).getName();
    verify(part).getTransferEncoding();
  }

  /**
   * Method under test:
   * {@link FileMultipartPart#FileMultipartPart(FilePart, byte[])}
   */
  @Test
  void testNewFileMultipartPart2() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    FilePart part = mock(FilePart.class);
    when(part.getCharset()).thenReturn(null);
    when(part.getFile()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "").toFile());
    when(part.getFileName()).thenReturn("https://example.org/example");
    when(part.getContentId()).thenReturn("https://example.org/example");
    when(part.getContentType()).thenReturn("text/plain");
    when(part.getDispositionType()).thenReturn("https://example.org/example");
    when(part.getName()).thenReturn("https://example.org/example");
    when(part.getTransferEncoding()).thenReturn("https://example.org/example");
    when(part.getCustomHeaders()).thenReturn(new ArrayList<>());

    // Act
    FileMultipartPart actualFileMultipartPart = new FileMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    verify(part, atLeast(1)).getFileName();
    verify(part).getFile();
    verify(part, atLeast(1)).getCharset();
    verify(part).getContentId();
    verify(part).getContentType();
    verify(part).getCustomHeaders();
    verify(part, atLeast(1)).getDispositionType();
    verify(part, atLeast(1)).getName();
    verify(part).getTransferEncoding();
    assertEquals(16384L, actualFileMultipartPart.getContentLength());
    assertEquals(16649L, actualFileMultipartPart.length());
    assertEquals(MultipartState.PRE_CONTENT, actualFileMultipartPart.getState());
    assertFalse(actualFileMultipartPart.isTargetSlow());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualFileMultipartPart.boundary);
  }

  /**
   * Method under test:
   * {@link FileMultipartPart#FileMultipartPart(FilePart, byte[])}
   */
  @Test
  void testNewFileMultipartPart3() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    ArrayList<Param> paramList = new ArrayList<>();
    paramList.add(new Param("https://example.org/example", "https://example.org/example"));
    FilePart part = mock(FilePart.class);
    when(part.getCharset()).thenReturn(null);
    when(part.getFile()).thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    when(part.getFileName()).thenReturn("https://example.org/example");
    when(part.getContentId()).thenReturn("https://example.org/example");
    when(part.getContentType()).thenReturn("text/plain");
    when(part.getDispositionType()).thenReturn("https://example.org/example");
    when(part.getName()).thenReturn("https://example.org/example");
    when(part.getTransferEncoding()).thenReturn("https://example.org/example");
    when(part.getCustomHeaders()).thenReturn(paramList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new FileMultipartPart(part, "AXAXAXAX".getBytes("UTF-8")));

    verify(part, atLeast(1)).getFileName();
    verify(part).getFile();
    verify(part, atLeast(1)).getCharset();
    verify(part).getContentId();
    verify(part).getContentType();
    verify(part, atLeast(1)).getCustomHeaders();
    verify(part, atLeast(1)).getDispositionType();
    verify(part, atLeast(1)).getName();
    verify(part).getTransferEncoding();
  }
}
