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
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.asynchttpclient.Param;
import org.asynchttpclient.request.body.multipart.FilePart;
import org.asynchttpclient.request.body.multipart.PartBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileMultipartPartDiffblueTest {
  /**
   * Test {@link FileMultipartPart#FileMultipartPart(FilePart, byte[])}.
   * <p>
   * Method under test:
   * {@link FileMultipartPart#FileMultipartPart(FilePart, byte[])}
   */
  @Test
  @DisplayName("Test new FileMultipartPart(FilePart, byte[])")
  void testNewFileMultipartPart() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    ArrayList<Param> paramList = new ArrayList<>();
    paramList.add(new Param("https://example.org/example", "https://example.org/example"));
    FilePart part = mock(FilePart.class);
    when(part.getCharset()).thenReturn(Charset.forName("UTF-8"));
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

  /**
   * Test {@link FileMultipartPart#FileMultipartPart(FilePart, byte[])}.
   * <ul>
   *   <li>Given forName {@code UTF-8}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FileMultipartPart#FileMultipartPart(FilePart, byte[])}
   */
  @Test
  @DisplayName("Test new FileMultipartPart(FilePart, byte[]); given forName 'UTF-8'; then throw IllegalArgumentException")
  void testNewFileMultipartPart_givenForNameUtf8_thenThrowIllegalArgumentException()
      throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    FilePart part = mock(FilePart.class);
    when(part.getCharset()).thenReturn(Charset.forName("UTF-8"));
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
   * Test {@link FileMultipartPart#FileMultipartPart(FilePart, byte[])}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link FilePart} {@link PartBase#getCharset()} return
   * {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FileMultipartPart#FileMultipartPart(FilePart, byte[])}
   */
  @Test
  @DisplayName("Test new FileMultipartPart(FilePart, byte[]); given 'null'; when FilePart getCharset() return 'null'")
  void testNewFileMultipartPart_givenNull_whenFilePartGetCharsetReturnNull() throws UnsupportedEncodingException {
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
   * Test {@link FileMultipartPart#FileMultipartPart(FilePart, byte[])}.
   * <ul>
   *   <li>Then return ContentLength is {@code 65536}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link FileMultipartPart#FileMultipartPart(FilePart, byte[])}
   */
  @Test
  @DisplayName("Test new FileMultipartPart(FilePart, byte[]); then return ContentLength is '65536'")
  void testNewFileMultipartPart_thenReturnContentLengthIs65536() throws UnsupportedEncodingException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    FilePart part = mock(FilePart.class);
    when(part.getCharset()).thenReturn(Charset.forName("UTF-8"));
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
    assertEquals(65536L, actualFileMultipartPart.getContentLength());
    assertEquals(65816L, actualFileMultipartPart.length());
    assertEquals(MultipartState.PRE_CONTENT, actualFileMultipartPart.getState());
    assertFalse(actualFileMultipartPart.isTargetSlow());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualFileMultipartPart.boundary);
  }
}
