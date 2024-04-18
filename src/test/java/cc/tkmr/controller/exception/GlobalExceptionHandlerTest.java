package cc.tkmr.controller.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cc.tkmr.service.exception.BusinessException;
import cc.tkmr.service.exception.NotFoundException;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private BusinessException businessException;

    @Mock
    private NotFoundException notFoundException;

    @BeforeEach
    void setUp() {
    }

    @Test
    void handleBusinessException() {
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleBusinessException(businessException);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(responseEntity.getBody()).isEqualTo("Business error.");
    }

    @Test
    void handleNotFoundException() {
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleNotFoundException();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isEqualTo("Resource not found.");
    }

    @Test
    void handleUnexpectedException() {
        Throwable unexpectedException = mock(Throwable.class);
        when(unexpectedException.getMessage()).thenReturn("Unexpected error.");

        ResponseEntity<String> responseEntity = globalExceptionHandler.handleUnexpectedException(unexpectedException);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseEntity.getBody()).isEqualTo("Unexpected server error.");
    }
}