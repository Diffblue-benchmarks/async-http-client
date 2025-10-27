package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.channel.FileRegion;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.request.body.RandomAccessBody;
import org.asynchttpclient.request.body.multipart.MultipartBody;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.request.body.multipart.part.MultipartPart;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BodyFileRegionDiffblueTest {
  /**
   * Method under test: {@link BodyFileRegion#position()}
   */
  @Test
  void testPosition() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();

    // Act and Assert
    assertEquals(0L,
        (new BodyFileRegion(new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))))
            .position());
  }

  /**
   * Method under test: {@link BodyFileRegion#count()}
   */
  @Test
  void testCount() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();

    // Act and Assert
    assertEquals(0L,
        (new BodyFileRegion(new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))))
            .count());
  }

  /**
   * Method under test: {@link BodyFileRegion#transfered()}
   */
  @Test
  void testTransfered() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();

    // Act and Assert
    assertEquals(0L,
        (new BodyFileRegion(new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))))
            .transfered());
  }

  /**
   * Method under test: {@link BodyFileRegion#BodyFileRegion(RandomAccessBody)}
   */
  @Test
  void testNewBodyFileRegion() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();

    // Act
    BodyFileRegion actualBodyFileRegion = new BodyFileRegion(
        new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));

    // Assert
    assertEquals(0L, actualBodyFileRegion.transfered());
    assertEquals(0L, actualBodyFileRegion.transferred());
    assertEquals(1, actualBodyFileRegion.refCnt());
  }

  /**
   * Method under test: {@link BodyFileRegion#transferred()}
   */
  @Test
  void testTransferred() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();

    // Act and Assert
    assertEquals(0L,
        (new BodyFileRegion(new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))))
            .transferred());
  }

  /**
   * Method under test: {@link BodyFileRegion#retain()}
   */
  @Test
  void testRetain() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    BodyFileRegion bodyFileRegion = new BodyFileRegion(
        new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));

    // Act
    FileRegion actualRetainResult = bodyFileRegion.retain();

    // Assert
    assertEquals(2, bodyFileRegion.refCnt());
    assertSame(bodyFileRegion, actualRetainResult);
  }

  /**
   * Method under test: {@link BodyFileRegion#retain(int)}
   */
  @Test
  void testRetain2() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    BodyFileRegion bodyFileRegion = new BodyFileRegion(
        new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));

    // Act
    FileRegion actualRetainResult = bodyFileRegion.retain(1);

    // Assert
    assertEquals(2, bodyFileRegion.refCnt());
    assertSame(bodyFileRegion, actualRetainResult);
  }

  /**
   * Method under test: {@link BodyFileRegion#touch()}
   */
  @Test
  void testTouch() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    BodyFileRegion bodyFileRegion = new BodyFileRegion(
        new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertSame(bodyFileRegion, bodyFileRegion.touch());
  }

  /**
   * Method under test: {@link BodyFileRegion#touch(Object)}
   */
  @Test
  void testTouch2() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    BodyFileRegion bodyFileRegion = new BodyFileRegion(
        new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertSame(bodyFileRegion, bodyFileRegion.touch("Hint"));
  }

  /**
   * Method under test:
   * {@link BodyFileRegion#transferTo(WritableByteChannel, long)}
   */
  @Test
  void testTransferTo() throws IOException {
    // Arrange
    MultipartBody body = mock(MultipartBody.class);
    when(body.transferTo(Mockito.<WritableByteChannel>any())).thenReturn(1L);
    BodyFileRegion bodyFileRegion = new BodyFileRegion(body);

    // Act
    long actualTransferToResult = bodyFileRegion.transferTo(null, 1L);

    // Assert
    verify(body).transferTo((WritableByteChannel) isNull());
    assertEquals(1L, actualTransferToResult);
    assertEquals(1L, bodyFileRegion.transfered());
    assertEquals(1L, bodyFileRegion.transferred());
  }

  /**
   * Method under test:
   * {@link BodyFileRegion#transferTo(WritableByteChannel, long)}
   */
  @Test
  void testTransferTo2() throws IOException {
    // Arrange
    MultipartBody body = mock(MultipartBody.class);
    when(body.transferTo(Mockito.<WritableByteChannel>any())).thenReturn(0L);
    BodyFileRegion bodyFileRegion = new BodyFileRegion(body);

    // Act
    long actualTransferToResult = bodyFileRegion.transferTo(null, 1L);

    // Assert
    verify(body).transferTo((WritableByteChannel) isNull());
    assertEquals(0L, actualTransferToResult);
    assertEquals(0L, bodyFileRegion.transfered());
    assertEquals(0L, bodyFileRegion.transferred());
  }

  /**
   * Method under test:
   * {@link BodyFileRegion#transferTo(WritableByteChannel, long)}
   */
  @Test
  void testTransferTo3() throws IOException {
    // Arrange
    MultipartBody body = mock(MultipartBody.class);
    when(body.transferTo(Mockito.<WritableByteChannel>any())).thenThrow(ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(ChannelClosedException.class, () -> (new BodyFileRegion(body)).transferTo(null, 1L));
    verify(body).transferTo((WritableByteChannel) isNull());
  }

  /**
   * Method under test: {@link BodyFileRegion#deallocate()}
   */
  @Test
  void testDeallocate() {
    // Arrange
    MultipartBody body = mock(MultipartBody.class);
    doNothing().when(body).close();

    // Act
    (new BodyFileRegion(body)).deallocate();

    // Assert
    verify(body).close();
  }

  /**
   * Method under test: {@link BodyFileRegion#deallocate()}
   */
  @Test
  void testDeallocate2() throws IOException {
    // Arrange
    RandomAccessBody body = mock(RandomAccessBody.class);
    doThrow(ChannelClosedException.INSTANCE).when(body).close();

    // Act
    (new BodyFileRegion(body)).deallocate();

    // Assert that nothing has changed
    verify(body).close();
  }
}
