package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.FileRegion;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.request.body.RandomAccessBody;
import org.asynchttpclient.request.body.multipart.MultipartBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BodyFileRegionDiffblueTest {
  @InjectMocks private BodyFileRegion bodyFileRegion;

  @Mock private RandomAccessBody randomAccessBody;

  /**
   * Test {@link BodyFileRegion#BodyFileRegion(RandomAccessBody)}.
   *
   * <p>Method under test: {@link BodyFileRegion#BodyFileRegion(RandomAccessBody)}
   */
  @Test
  @DisplayName("Test new BodyFileRegion(RandomAccessBody)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BodyFileRegion.<init>(RandomAccessBody)"})
  void testNewBodyFileRegion() throws UnsupportedEncodingException {
    // Arrange
    MultipartBody body =
        new MultipartBody(
            new ArrayList<>(), "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act
    BodyFileRegion actualBodyFileRegion = new BodyFileRegion(body);

    // Assert
    assertEquals(0L, actualBodyFileRegion.transfered());
    assertEquals(0L, actualBodyFileRegion.transferred());
    assertEquals(1, actualBodyFileRegion.refCnt());
  }

  /**
   * Test {@link BodyFileRegion#position()}.
   *
   * <p>Method under test: {@link BodyFileRegion#position()}
   */
  @Test
  @DisplayName("Test position()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long BodyFileRegion.position()"})
  void testPosition() throws UnsupportedEncodingException {
    // Arrange
    MultipartBody body =
        new MultipartBody(
            new ArrayList<>(), "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, new BodyFileRegion(body).position());
  }

  /**
   * Test {@link BodyFileRegion#count()}.
   *
   * <p>Method under test: {@link BodyFileRegion#count()}
   */
  @Test
  @DisplayName("Test count()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long BodyFileRegion.count()"})
  void testCount() throws UnsupportedEncodingException {
    // Arrange
    MultipartBody body =
        new MultipartBody(
            new ArrayList<>(), "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, new BodyFileRegion(body).count());
  }

  /**
   * Test {@link BodyFileRegion#transfered()}.
   *
   * <p>Method under test: {@link BodyFileRegion#transfered()}
   */
  @Test
  @DisplayName("Test transfered()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long BodyFileRegion.transfered()"})
  void testTransfered() throws UnsupportedEncodingException {
    // Arrange
    MultipartBody body =
        new MultipartBody(
            new ArrayList<>(), "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, new BodyFileRegion(body).transfered());
  }

  /**
   * Test {@link BodyFileRegion#transferred()}.
   *
   * <p>Method under test: {@link BodyFileRegion#transferred()}
   */
  @Test
  @DisplayName("Test transferred()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long BodyFileRegion.transferred()"})
  void testTransferred() throws UnsupportedEncodingException {
    // Arrange
    MultipartBody body =
        new MultipartBody(
            new ArrayList<>(), "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, new BodyFileRegion(body).transferred());
  }

  /**
   * Test {@link BodyFileRegion#retain()}.
   *
   * <p>Method under test: {@link BodyFileRegion#retain()}
   */
  @Test
  @DisplayName("Test retain()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"FileRegion BodyFileRegion.retain()"})
  void testRetain() throws UnsupportedEncodingException {
    // Arrange
    MultipartBody body =
        new MultipartBody(
            new ArrayList<>(), "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    BodyFileRegion bodyFileRegion = new BodyFileRegion(body);

    // Act
    FileRegion actualRetainResult = bodyFileRegion.retain();

    // Assert
    assertEquals(2, bodyFileRegion.refCnt());
    assertSame(bodyFileRegion, actualRetainResult);
  }

  /**
   * Test {@link BodyFileRegion#retain(int)} with {@code int}.
   *
   * <ul>
   *   <li>Then {@link BodyFileRegion#BodyFileRegion(RandomAccessBody)} with body is {@link
   *       MultipartBody#MultipartBody(List, String, byte[])} refCnt is two.
   * </ul>
   *
   * <p>Method under test: {@link BodyFileRegion#retain(int)}
   */
  @Test
  @DisplayName(
      "Test retain(int) with 'int'; then BodyFileRegion(RandomAccessBody) with body is MultipartBody(List, String, byte[]) refCnt is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"FileRegion BodyFileRegion.retain(int)"})
  void testRetainWithInt_thenBodyFileRegionWithBodyIsMultipartBodyRefCntIsTwo()
      throws UnsupportedEncodingException {
    // Arrange
    MultipartBody body =
        new MultipartBody(
            new ArrayList<>(), "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    BodyFileRegion bodyFileRegion = new BodyFileRegion(body);

    // Act
    FileRegion actualRetainResult = bodyFileRegion.retain(1);

    // Assert
    assertEquals(2, bodyFileRegion.refCnt());
    assertSame(bodyFileRegion, actualRetainResult);
  }

  /**
   * Test {@link BodyFileRegion#touch()}.
   *
   * <p>Method under test: {@link BodyFileRegion#touch()}
   */
  @Test
  @DisplayName("Test touch()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"FileRegion BodyFileRegion.touch()"})
  void testTouch() throws UnsupportedEncodingException {
    // Arrange
    MultipartBody body =
        new MultipartBody(
            new ArrayList<>(), "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    BodyFileRegion bodyFileRegion = new BodyFileRegion(body);

    // Act
    FileRegion actualTouchResult = bodyFileRegion.touch();

    // Assert
    assertSame(bodyFileRegion, actualTouchResult);
  }

  /**
   * Test {@link BodyFileRegion#touch(Object)} with {@code Object}.
   *
   * <p>Method under test: {@link BodyFileRegion#touch(Object)}
   */
  @Test
  @DisplayName("Test touch(Object) with 'Object'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"FileRegion BodyFileRegion.touch(Object)"})
  void testTouchWithObject() throws UnsupportedEncodingException {
    // Arrange
    MultipartBody body =
        new MultipartBody(
            new ArrayList<>(), "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    BodyFileRegion bodyFileRegion = new BodyFileRegion(body);

    // Act
    FileRegion actualTouchResult = bodyFileRegion.touch("Hint");

    // Assert
    assertSame(bodyFileRegion, actualTouchResult);
  }

  /**
   * Test {@link BodyFileRegion#transferTo(WritableByteChannel, long)}.
   *
   * <ul>
   *   <li>Given {@link RandomAccessBody} {@link RandomAccessBody#transferTo(WritableByteChannel)}
   *       return one.
   *   <li>When {@code null}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link BodyFileRegion#transferTo(WritableByteChannel, long)}
   */
  @Test
  @DisplayName(
      "Test transferTo(WritableByteChannel, long); given RandomAccessBody transferTo(WritableByteChannel) return one; when 'null'; then return one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long BodyFileRegion.transferTo(WritableByteChannel, long)"})
  void testTransferTo_givenRandomAccessBodyTransferToReturnOne_whenNull_thenReturnOne()
      throws IOException {
    // Arrange
    when(randomAccessBody.transferTo(Mockito.<WritableByteChannel>any())).thenReturn(1L);

    // Act
    long actualTransferToResult = bodyFileRegion.transferTo(null, 1L);

    // Assert
    verify(randomAccessBody).transferTo((WritableByteChannel) isNull());
    assertEquals(1L, actualTransferToResult);
    assertEquals(1L, bodyFileRegion.transfered());
    assertEquals(1L, bodyFileRegion.transferred());
  }

  /**
   * Test {@link BodyFileRegion#transferTo(WritableByteChannel, long)}.
   *
   * <ul>
   *   <li>Given {@link RandomAccessBody} {@link RandomAccessBody#transferTo(WritableByteChannel)}
   *       return zero.
   *   <li>When {@code null}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link BodyFileRegion#transferTo(WritableByteChannel, long)}
   */
  @Test
  @DisplayName(
      "Test transferTo(WritableByteChannel, long); given RandomAccessBody transferTo(WritableByteChannel) return zero; when 'null'; then return zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long BodyFileRegion.transferTo(WritableByteChannel, long)"})
  void testTransferTo_givenRandomAccessBodyTransferToReturnZero_whenNull_thenReturnZero()
      throws IOException {
    // Arrange
    when(randomAccessBody.transferTo(Mockito.<WritableByteChannel>any())).thenReturn(0L);

    // Act
    long actualTransferToResult = bodyFileRegion.transferTo(null, 1L);

    // Assert
    verify(randomAccessBody).transferTo((WritableByteChannel) isNull());
    assertEquals(0L, actualTransferToResult);
    assertEquals(0L, bodyFileRegion.transfered());
    assertEquals(0L, bodyFileRegion.transferred());
  }

  /**
   * Test {@link BodyFileRegion#transferTo(WritableByteChannel, long)}.
   *
   * <ul>
   *   <li>Then throw {@link ChannelClosedException}.
   * </ul>
   *
   * <p>Method under test: {@link BodyFileRegion#transferTo(WritableByteChannel, long)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel, long); then throw ChannelClosedException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long BodyFileRegion.transferTo(WritableByteChannel, long)"})
  void testTransferTo_thenThrowChannelClosedException() throws IOException {
    // Arrange
    when(randomAccessBody.transferTo(Mockito.<WritableByteChannel>any()))
        .thenThrow(ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(ChannelClosedException.class, () -> bodyFileRegion.transferTo(null, 1L));
    verify(randomAccessBody).transferTo((WritableByteChannel) isNull());
  }

  /**
   * Test {@link BodyFileRegion#deallocate()}.
   *
   * <ul>
   *   <li>Given {@link RandomAccessBody} {@link RandomAccessBody#close()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link BodyFileRegion#deallocate()}
   */
  @Test
  @DisplayName("Test deallocate(); given RandomAccessBody close() does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BodyFileRegion.deallocate()"})
  void testDeallocate_givenRandomAccessBodyCloseDoesNothing() throws IOException {
    // Arrange
    doNothing().when(randomAccessBody).close();

    // Act
    bodyFileRegion.deallocate();

    // Assert
    verify(randomAccessBody).close();
  }

  /**
   * Test {@link BodyFileRegion#deallocate()}.
   *
   * <ul>
   *   <li>Given {@link RandomAccessBody} {@link RandomAccessBody#close()} throw {@link
   *       ChannelClosedException#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link BodyFileRegion#deallocate()}
   */
  @Test
  @DisplayName("Test deallocate(); given RandomAccessBody close() throw INSTANCE")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BodyFileRegion.deallocate()"})
  void testDeallocate_givenRandomAccessBodyCloseThrowInstance() throws IOException {
    // Arrange
    doThrow(ChannelClosedException.INSTANCE).when(randomAccessBody).close();

    // Act
    bodyFileRegion.deallocate();

    // Assert
    verify(randomAccessBody).close();
  }
}
