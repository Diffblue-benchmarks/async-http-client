package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.Response.ResponseBuilder;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ResponseDiffblueTest {
  /**
   * Test ResponseBuilder {@link ResponseBuilder#accumulate(HttpResponseBodyPart)}
   * with {@code bodyPart}.
   * <ul>
   *   <li>Given three.</li>
   *   <li>Then calls {@link EagerResponseBodyPart#length()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link Response.ResponseBuilder#accumulate(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test ResponseBuilder accumulate(HttpResponseBodyPart) with 'bodyPart'; given three; then calls length()")
  void testResponseBuilderAccumulateWithBodyPart_givenThree_thenCallsLength() {
    // Arrange
    Response.ResponseBuilder responseBuilder = new Response.ResponseBuilder();
    EagerResponseBodyPart bodyPart = mock(EagerResponseBodyPart.class);
    when(bodyPart.length()).thenReturn(3);

    // Act
    responseBuilder.accumulate(bodyPart);

    // Assert
    verify(bodyPart).length();
  }

  /**
   * Test ResponseBuilder {@link ResponseBuilder#accumulate(HttpHeaders)} with
   * {@code headers}.
   * <ul>
   *   <li>Then calls {@link HttpHeaders#add(HttpHeaders)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Response.ResponseBuilder#accumulate(HttpHeaders)}
   */
  @Test
  @DisplayName("Test ResponseBuilder accumulate(HttpHeaders) with 'headers'; then calls add(HttpHeaders)")
  void testResponseBuilderAccumulateWithHeaders_thenCallsAdd() {
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
   * Test ResponseBuilder {@link ResponseBuilder#build()}.
   * <p>
   * Method under test: {@link Response.ResponseBuilder#build()}
   */
  @Test
  @DisplayName("Test ResponseBuilder build()")
  void testResponseBuilderBuild() {
    // Arrange, Act and Assert
    assertNull((new Response.ResponseBuilder()).build());
  }

  /**
   * Test ResponseBuilder new {@link ResponseBuilder} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of
   * {@link Response.ResponseBuilder}
   */
  @Test
  @DisplayName("Test ResponseBuilder new ResponseBuilder (default constructor)")
  void testResponseBuilderNewResponseBuilder() {
    // Arrange, Act and Assert
    assertNull((new Response.ResponseBuilder()).build());
  }
}
