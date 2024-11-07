package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.channel.FileRegion;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.request.body.RandomAccessBody;
import org.asynchttpclient.request.body.multipart.MultipartBody;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.request.body.multipart.part.MultipartPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BodyFileRegionDiffblueTest {
  /**
   * Test {@link BodyFileRegion#BodyFileRegion(RandomAccessBody)}.
   * <ul>
   *   <li>Then return transfered is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyFileRegion#BodyFileRegion(RandomAccessBody)}
   */
  @Test
  @DisplayName("Test new BodyFileRegion(RandomAccessBody); then return transfered is zero")
  void testNewBodyFileRegion_thenReturnTransferedIsZero() throws UnsupportedEncodingException {
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
   * Test {@link BodyFileRegion#position()}.
   * <p>
   * Method under test: {@link BodyFileRegion#position()}
   */
  @Test
  @DisplayName("Test position()")
  void testPosition() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();

    // Act and Assert
    assertEquals(0L,
        (new BodyFileRegion(new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))))
            .position());
  }

  /**
   * Test {@link BodyFileRegion#count()}.
   * <p>
   * Method under test: {@link BodyFileRegion#count()}
   */
  @Test
  @DisplayName("Test count()")
  void testCount() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();

    // Act and Assert
    assertEquals(0L,
        (new BodyFileRegion(new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))))
            .count());
  }

  /**
   * Test {@link BodyFileRegion#transfered()}.
   * <p>
   * Method under test: {@link BodyFileRegion#transfered()}
   */
  @Test
  @DisplayName("Test transfered()")
  void testTransfered() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();

    // Act and Assert
    assertEquals(0L,
        (new BodyFileRegion(new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))))
            .transfered());
  }

  /**
   * Test {@link BodyFileRegion#transferred()}.
   * <p>
   * Method under test: {@link BodyFileRegion#transferred()}
   */
  @Test
  @DisplayName("Test transferred()")
  void testTransferred() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();

    // Act and Assert
    assertEquals(0L,
        (new BodyFileRegion(new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))))
            .transferred());
  }

  /**
   * Test {@link BodyFileRegion#retain()}.
   * <p>
   * Method under test: {@link BodyFileRegion#retain()}
   */
  @Test
  @DisplayName("Test retain()")
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
   * Test {@link BodyFileRegion#retain(int)} with {@code int}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then {@link BodyFileRegion#BodyFileRegion(RandomAccessBody)} with body is
   * {@link MultipartBody#MultipartBody(List, String, byte[])} refCnt is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyFileRegion#retain(int)}
   */
  @Test
  @DisplayName("Test retain(int) with 'int'; when one; then BodyFileRegion(RandomAccessBody) with body is MultipartBody(List, String, byte[]) refCnt is two")
  void testRetainWithInt_whenOne_thenBodyFileRegionWithBodyIsMultipartBodyRefCntIsTwo()
      throws UnsupportedEncodingException {
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
   * Test {@link BodyFileRegion#touch()}.
   * <p>
   * Method under test: {@link BodyFileRegion#touch()}
   */
  @Test
  @DisplayName("Test touch()")
  void testTouch() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    BodyFileRegion bodyFileRegion = new BodyFileRegion(
        new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertSame(bodyFileRegion, bodyFileRegion.touch());
  }

  /**
   * Test {@link BodyFileRegion#touch(Object)} with {@code Object}.
   * <p>
   * Method under test: {@link BodyFileRegion#touch(Object)}
   */
  @Test
  @DisplayName("Test touch(Object) with 'Object'")
  void testTouchWithObject() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    BodyFileRegion bodyFileRegion = new BodyFileRegion(
        new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));

    // Act and Assert
    assertSame(bodyFileRegion, bodyFileRegion.touch("Hint"));
  }

  /**
   * Test {@link BodyFileRegion#transferTo(WritableByteChannel, long)}.
   * <ul>
   *   <li>Given {@link MultipartBody}
   * {@link MultipartBody#transferTo(WritableByteChannel)} return one.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link BodyFileRegion#transferTo(WritableByteChannel, long)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel, long); given MultipartBody transferTo(WritableByteChannel) return one; when 'null'; then return one")
  void testTransferTo_givenMultipartBodyTransferToReturnOne_whenNull_thenReturnOne() throws IOException {
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
   * Test {@link BodyFileRegion#transferTo(WritableByteChannel, long)}.
   * <ul>
   *   <li>Given {@link MultipartBody}
   * {@link MultipartBody#transferTo(WritableByteChannel)} return zero.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link BodyFileRegion#transferTo(WritableByteChannel, long)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel, long); given MultipartBody transferTo(WritableByteChannel) return zero; when 'null'; then return zero")
  void testTransferTo_givenMultipartBodyTransferToReturnZero_whenNull_thenReturnZero() throws IOException {
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
   * Test {@link BodyFileRegion#deallocate()}.
   * <ul>
   *   <li>Given {@link RandomAccessBody} {@link Closeable#close()} throw
   * {@link ChannelClosedException#INSTANCE}.</li>
   *   <li>Then calls {@link Closeable#close()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyFileRegion#deallocate()}
   */
  @Test
  @DisplayName("Test deallocate(); given RandomAccessBody close() throw INSTANCE; then calls close()")
  void testDeallocate_givenRandomAccessBodyCloseThrowInstance_thenCallsClose() throws IOException {
    // Arrange
    RandomAccessBody body = mock(RandomAccessBody.class);
    doThrow(ChannelClosedException.INSTANCE).when(body).close();

    // Act
    (new BodyFileRegion(body)).deallocate();

    // Assert that nothing has changed
    verify(body).close();
  }
}
