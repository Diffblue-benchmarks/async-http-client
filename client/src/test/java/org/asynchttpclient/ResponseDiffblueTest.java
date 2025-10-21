package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.Response.ResponseBuilder;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ResponseDiffblueTest {
  /**
   * Test ResponseBuilder {@link ResponseBuilder#accumulate(HttpResponseBodyPart)} with {@code bodyPart}.
   * <ul>
   *   <li>Given three.</li>
   *   <li>Then calls {@link EagerResponseBodyPart#length()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseBuilder#accumulate(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test ResponseBuilder accumulate(HttpResponseBodyPart) with 'bodyPart'; given three; then calls length()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResponseBuilder.accumulate(HttpResponseBodyPart)"})
  void testResponseBuilderAccumulateWithBodyPart_givenThree_thenCallsLength() {
    // Arrange
    ResponseBuilder responseBuilder = new ResponseBuilder();
    EagerResponseBodyPart bodyPart = mock(EagerResponseBodyPart.class);
    when(bodyPart.length()).thenReturn(3);

    // Act
    responseBuilder.accumulate(bodyPart);

    // Assert
    verify(bodyPart).length();
  }

  /**
   * Test ResponseBuilder {@link ResponseBuilder#accumulate(HttpHeaders)} with {@code headers}.
   * <ul>
   *   <li>Then calls {@link HttpHeaders#add(HttpHeaders)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResponseBuilder#accumulate(HttpHeaders)}
   */
  @Test
  @DisplayName("Test ResponseBuilder accumulate(HttpHeaders) with 'headers'; then calls add(HttpHeaders)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResponseBuilder.accumulate(HttpHeaders)"})
  void testResponseBuilderAccumulateWithHeaders_thenCallsAdd() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.add(Mockito.<HttpHeaders>any())).thenReturn(new DefaultHttpHeaders());

    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.accumulate(headers);

    // Act
    responseBuilder.accumulate((HttpHeaders) null);

    // Assert
    verify(headers).add(isNull());
  }

  /**
   * Test ResponseBuilder {@link ResponseBuilder#build()}.
   * <p>
   * Method under test: {@link ResponseBuilder#build()}
   */
  @Test
  @DisplayName("Test ResponseBuilder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResponseBuilder.accumulate(org.asynchttpclient.HttpResponseStatus)",
      "Response ResponseBuilder.build()"})
  void testResponseBuilderBuild() {
    // Arrange, Act and Assert
    assertNull((new ResponseBuilder()).build());
  }

  /**
   * Test ResponseBuilder new {@link ResponseBuilder} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ResponseBuilder}
   */
  @Test
  @DisplayName("Test ResponseBuilder new ResponseBuilder (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResponseBuilder.<init>()"})
  void testResponseBuilderNewResponseBuilder() {
    // Arrange, Act and Assert
    assertNull((new ResponseBuilder()).build());
  }
}
