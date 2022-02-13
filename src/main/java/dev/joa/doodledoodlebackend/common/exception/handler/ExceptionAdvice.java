package dev.joa.doodledoodlebackend.common.exception.handler;

import dev.joa.doodledoodlebackend.common.api.BaseResponse;
import dev.joa.doodledoodlebackend.common.exception.DoodleDoodleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 파라미터 에러 처리
     *
     * @param exception 파라미터 에러
     * @return 400 에러 메시지 응답
     */
    @ExceptionHandler({
            BindException.class,
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleInvalidRequestException(Exception exception) {
        String message = exception.getMessage();

        if (exception instanceof BindException) {
            message = ((BindException) exception).getBindingResult()
                                                 .getAllErrors()
                                                 .get(0)
                                                 .getDefaultMessage();
        }

        exceptionLogging(exception);
        return new BaseResponse(HttpStatus.BAD_REQUEST, message);
    }

    /**
     * 서버 에러 처리
     *
     * @param exception 로직 에러
     * @return 500 에러 메시지 응답
     */
    @ExceptionHandler(DoodleDoodleException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleDoodleDoodleException(Exception exception) {
        exceptionLogging(exception);
        return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    private void exceptionLogging(Exception exception) {
        log.error(String.format("[doodle-doodle] %s", exception.getMessage()), exception);
    }
}
