package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.Param;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.request.body.multipart.InputStreamPart;
import org.junit.jupiter.api.Test;

class InputStreamMultipartPartDiffblueTest {
  /**
   * Method under test: {@link InputStreamMultipartPart#getContentLength()}
   */
  @Test
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange
    InputStreamPart part = new InputStreamPart("https://example.org/example",
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), "https://example.org/example");

    // Act and Assert
    assertEquals(-1L, (new InputStreamMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Method under test: {@link InputStreamMultipartPart#getContentLength()}
   */
  @Test
  void testGetContentLength2() throws UnsupportedEncodingException {
    // Arrange
    InputStreamPart part = new InputStreamPart("Name", mock(DataInputStream.class), "https://example.org/example");

    // Act and Assert
    assertEquals(-1L, (new InputStreamMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Method under test:
   * {@link InputStreamMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  void testTransferContentTo() throws IOException {
    // Arrange
    InputStreamPart part = new InputStreamPart("https://example.org/example",
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), "https://example.org/example");

    InputStreamMultipartPart inputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, inputStreamMultipartPart
        .transferContentTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Method under test:
   * {@link InputStreamMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  void testTransferContentTo2() throws IOException {
    // Arrange
    InputStreamPart part = new InputStreamPart("https://example.org/example",
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), "https://example.org/example");

    InputStreamMultipartPart inputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, inputStreamMultipartPart.transferContentTo(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
  }

  /**
   * Method under test:
   * {@link InputStreamMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  void testTransferContentTo3() throws IOException {
    // Arrange
    InputStreamPart part = mock(InputStreamPart.class);
    when(part.getContentLength()).thenReturn(3L);
    when(part.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[]{}));
    when(part.getCharset()).thenReturn(null);
    when(part.getFileName()).thenReturn("https://example.org/example");
    when(part.getContentId()).thenReturn("https://example.org/example");
    when(part.getContentType()).thenReturn("text/plain");
    when(part.getDispositionType()).thenReturn("https://example.org/example");
    when(part.getName()).thenReturn("https://example.org/example");
    when(part.getTransferEncoding()).thenReturn("https://example.org/example");
    when(part.getCustomHeaders()).thenReturn(new ArrayList<>());
    InputStreamMultipartPart inputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Act
    long actualTransferContentToResult = inputStreamMultipartPart.transferContentTo((WritableByteChannel) null);

    // Assert
    verify(part, atLeast(1)).getFileName();
    verify(part).getContentLength();
    verify(part).getInputStream();
    verify(part, atLeast(1)).getCharset();
    verify(part).getContentId();
    verify(part).getContentType();
    verify(part).getCustomHeaders();
    verify(part, atLeast(1)).getDispositionType();
    verify(part, atLeast(1)).getName();
    verify(part).getTransferEncoding();
    assertEquals(0L, actualTransferContentToResult);
    assertEquals(MultipartState.POST_CONTENT, inputStreamMultipartPart.getState());
  }

  /**
   * Method under test:
   * {@link InputStreamMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  void testTransferContentTo4() throws IOException {
    // Arrange
    InputStreamPart part = mock(InputStreamPart.class);
    when(part.getContentLength()).thenReturn(0L);
    when(part.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[]{}));
    when(part.getCharset()).thenReturn(null);
    when(part.getFileName()).thenReturn("https://example.org/example");
    when(part.getContentId()).thenReturn("https://example.org/example");
    when(part.getContentType()).thenReturn("text/plain");
    when(part.getDispositionType()).thenReturn("https://example.org/example");
    when(part.getName()).thenReturn("https://example.org/example");
    when(part.getTransferEncoding()).thenReturn("https://example.org/example");
    when(part.getCustomHeaders()).thenReturn(new ArrayList<>());
    InputStreamMultipartPart inputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Act
    long actualTransferContentToResult = inputStreamMultipartPart.transferContentTo((WritableByteChannel) null);

    // Assert
    verify(part, atLeast(1)).getFileName();
    verify(part).getContentLength();
    verify(part).getInputStream();
    verify(part, atLeast(1)).getCharset();
    verify(part).getContentId();
    verify(part).getContentType();
    verify(part).getCustomHeaders();
    verify(part, atLeast(1)).getDispositionType();
    verify(part, atLeast(1)).getName();
    verify(part).getTransferEncoding();
    assertEquals(0L, actualTransferContentToResult);
    assertEquals(MultipartState.POST_CONTENT, inputStreamMultipartPart.getState());
  }

  /**
   * Method under test: {@link InputStreamMultipartPart#close()}
   */
  @Test
  void testClose() throws IOException {
    // Arrange
    DataInputStream inputStream = mock(DataInputStream.class);
    doNothing().when(inputStream).close();
    InputStreamPart part = new InputStreamPart("Name", inputStream, "https://example.org/example");

    // Act
    (new InputStreamMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).close();

    // Assert that nothing has changed
    verify(inputStream).close();
  }

  /**
   * Method under test: {@link InputStreamMultipartPart#close()}
   */
  @Test
  void testClose2() throws IOException {
    // Arrange
    DataInputStream inputStream = mock(DataInputStream.class);
    doThrow(ChannelClosedException.INSTANCE).when(inputStream).close();
    InputStreamPart part = new InputStreamPart("Name", inputStream, "https://example.org/example");

    // Act
    (new InputStreamMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).close();

    // Assert that nothing has changed
    verify(inputStream).close();
  }

  /**
   * Method under test:
   * {@link InputStreamMultipartPart#InputStreamMultipartPart(InputStreamPart, byte[])}
   */
  @Test
  void testNewInputStreamMultipartPart() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    InputStreamPart part = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example");

    // Act
    InputStreamMultipartPart actualInputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    InputStreamPart inputStreamPart = actualInputStreamMultipartPart.part;
    assertEquals("application/octet-stream", inputStreamPart.getContentType());
    assertEquals("https://example.org/example", inputStreamPart.getFileName());
    assertEquals("https://example.org/example", inputStreamPart.getName());
    assertNull(inputStreamPart.getContentId());
    assertNull(inputStreamPart.getDispositionType());
    assertNull(inputStreamPart.getTransferEncoding());
    assertNull(inputStreamPart.getCharset());
    assertNull(inputStreamPart.getCustomHeaders());
    assertEquals(-1L, inputStreamPart.getContentLength());
    assertEquals(-1L, actualInputStreamMultipartPart.getContentLength());
    assertEquals(-1L, actualInputStreamMultipartPart.length());
    InputStream inputStream2 = inputStreamPart.getInputStream();
    byte[] byteArray = new byte[8];
    assertEquals(8, inputStream2.read(byteArray));
    assertEquals(MultipartState.PRE_CONTENT, actualInputStreamMultipartPart.getState());
    assertFalse(actualInputStreamMultipartPart.isTargetSlow());
    assertSame(inputStream, inputStream2);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualInputStreamMultipartPart.boundary);
  }

  /**
   * Method under test:
   * {@link InputStreamMultipartPart#InputStreamMultipartPart(InputStreamPart, byte[])}
   */
  @Test
  void testNewInputStreamMultipartPart2() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    InputStreamPart part = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    InputStreamMultipartPart actualInputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    InputStreamPart inputStreamPart = actualInputStreamMultipartPart.part;
    assertEquals("application/octet-stream", inputStreamPart.getContentType());
    List<Param> customHeaders = inputStreamPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals("https://example.org/example", inputStreamPart.getFileName());
    assertEquals("https://example.org/example", inputStreamPart.getName());
    assertNull(inputStreamPart.getContentId());
    assertNull(inputStreamPart.getDispositionType());
    assertNull(inputStreamPart.getTransferEncoding());
    assertNull(inputStreamPart.getCharset());
    assertEquals(-1L, inputStreamPart.getContentLength());
    assertEquals(-1L, actualInputStreamMultipartPart.getContentLength());
    assertEquals(-1L, actualInputStreamMultipartPart.length());
    InputStream inputStream2 = inputStreamPart.getInputStream();
    byte[] byteArray = new byte[8];
    assertEquals(8, inputStream2.read(byteArray));
    assertEquals(MultipartState.PRE_CONTENT, actualInputStreamMultipartPart.getState());
    assertFalse(actualInputStreamMultipartPart.isTargetSlow());
    assertSame(inputStream, inputStream2);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualInputStreamMultipartPart.boundary);
  }

  /**
   * Method under test:
   * {@link InputStreamMultipartPart#InputStreamMultipartPart(InputStreamPart, byte[])}
   */
  @Test
  void testNewInputStreamMultipartPart3() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    InputStreamPart part = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example");
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    InputStreamMultipartPart actualInputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    InputStreamPart inputStreamPart = actualInputStreamMultipartPart.part;
    assertEquals("application/octet-stream", inputStreamPart.getContentType());
    List<Param> customHeaders = inputStreamPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals("https://example.org/example", inputStreamPart.getFileName());
    assertEquals("https://example.org/example", inputStreamPart.getDispositionType());
    assertEquals("https://example.org/example", inputStreamPart.getName());
    assertNull(inputStreamPart.getContentId());
    assertNull(inputStreamPart.getTransferEncoding());
    assertNull(inputStreamPart.getCharset());
    assertEquals(-1L, inputStreamPart.getContentLength());
    assertEquals(-1L, actualInputStreamMultipartPart.getContentLength());
    assertEquals(-1L, actualInputStreamMultipartPart.length());
    InputStream inputStream2 = inputStreamPart.getInputStream();
    byte[] byteArray = new byte[8];
    assertEquals(8, inputStream2.read(byteArray));
    assertEquals(MultipartState.PRE_CONTENT, actualInputStreamMultipartPart.getState());
    assertFalse(actualInputStreamMultipartPart.isTargetSlow());
    assertSame(inputStream, inputStream2);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualInputStreamMultipartPart.boundary);
  }

  /**
   * Method under test:
   * {@link InputStreamMultipartPart#InputStreamMultipartPart(InputStreamPart, byte[])}
   */
  @Test
  void testNewInputStreamMultipartPart4() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    InputStreamPart part = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example");
    ArrayList<Param> customHeaders = new ArrayList<>();
    part.setCustomHeaders(customHeaders);

    // Act
    InputStreamMultipartPart actualInputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    InputStreamPart inputStreamPart = actualInputStreamMultipartPart.part;
    assertEquals("application/octet-stream", inputStreamPart.getContentType());
    assertEquals("https://example.org/example", inputStreamPart.getFileName());
    assertEquals("https://example.org/example", inputStreamPart.getName());
    assertNull(inputStreamPart.getContentId());
    assertNull(inputStreamPart.getDispositionType());
    assertNull(inputStreamPart.getTransferEncoding());
    assertNull(inputStreamPart.getCharset());
    assertEquals(-1L, inputStreamPart.getContentLength());
    assertEquals(-1L, actualInputStreamMultipartPart.getContentLength());
    assertEquals(-1L, actualInputStreamMultipartPart.length());
    InputStream inputStream2 = inputStreamPart.getInputStream();
    byte[] byteArray = new byte[8];
    assertEquals(8, inputStream2.read(byteArray));
    assertEquals(MultipartState.PRE_CONTENT, actualInputStreamMultipartPart.getState());
    assertFalse(actualInputStreamMultipartPart.isTargetSlow());
    List<Param> customHeaders2 = inputStreamPart.getCustomHeaders();
    assertTrue(customHeaders2.isEmpty());
    assertSame(inputStream, inputStream2);
    assertSame(customHeaders, customHeaders2);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualInputStreamMultipartPart.boundary);
  }
}
