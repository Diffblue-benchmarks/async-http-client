package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ResponseDiffblueTest {
  /**
   * Method under test: {@link Response.ResponseBuilder#accumulate(HttpHeaders)}
   */
  @Test
  void testResponseBuilderAccumulate() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.add(Mockito.<HttpHeaders>any())).thenReturn(new DefaultHttpHeaders());

    Response.ResponseBuilder responseBuilder = new Response.ResponseBuilder();
    responseBuilder.accumulate(headers);

    // Act
    responseBuilder.accumulate((HttpHeaders) null);

    // Assert
    verify(headers).add(isNull());
  }

  /**
   * Method under test:
   * {@link Response.ResponseBuilder#accumulate(HttpResponseBodyPart)}
   */
  @Test
  void testResponseBuilderAccumulate2() {
    // Arrange
    Response.ResponseBuilder responseBuilder = new Response.ResponseBuilder();
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    responseBuilder.accumulate(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true));

    // Assert that nothing has changed
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
  }

  /**
   * Method under test:
   * {@link Response.ResponseBuilder#accumulate(HttpResponseBodyPart)}
   */
  @Test
  void testResponseBuilderAccumulate3() {
    // Arrange
    Response.ResponseBuilder responseBuilder = new Response.ResponseBuilder();
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(0);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    responseBuilder.accumulate(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(0), isA(byte[].class), eq(0), eq(1));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
  }

  /**
   * Method under test: {@link Response.ResponseBuilder#build()}
   */
  @Test
  void testResponseBuilderBuild() {
    // Arrange, Act and Assert
    assertNull((new Response.ResponseBuilder()).build());
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link Response.ResponseBuilder}
   */
  @Test
  void testResponseBuilderNewResponseBuilder() {
    // Arrange, Act and Assert
    assertNull((new Response.ResponseBuilder()).build());
  }

  /**
   * Method under test: {@link Response.ResponseBuilder#reset()}
   */
  @Test
  void testResponseBuilderReset() {
    // Arrange
    Response.ResponseBuilder responseBuilder = new Response.ResponseBuilder();

    // Act
    responseBuilder.reset();

    // Assert
    assertNull(responseBuilder.build());
  }

  /**
   * Method under test: {@link Response.ResponseBuilder#reset()}
   */
  @Test
  void testResponseBuilderReset2() {
    // Arrange
    Response.ResponseBuilder responseBuilder = new Response.ResponseBuilder();
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    responseBuilder.accumulate(new NettyResponseStatus(uri, response, new EmbeddedChannel()));

    // Act
    responseBuilder.reset();

    // Assert
    assertNull(responseBuilder.build());
  }
}
