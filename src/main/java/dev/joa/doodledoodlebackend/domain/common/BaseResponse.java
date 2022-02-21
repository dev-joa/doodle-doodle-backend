package dev.joa.doodledoodlebackend.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"header", "result"})
public class BaseResponse<T> {

    private Header header = new Header();
    private T result;

    public BaseResponse() {
        this(null);
    }

    public BaseResponse(T result) {
        this(HttpStatus.OK, result);
    }

    public BaseResponse(HttpStatus statusCode, T result) {
        setHeaderWithBaseStatusCode(statusCode);
        this.result = result;
    }

    private void setHeaderWithBaseStatusCode(HttpStatus statusCode) {
        setHeaderWithBaseStatusCodeAndMessage(statusCode, statusCode.getReasonPhrase());
    }

    private void setHeaderWithBaseStatusCodeAndMessage(HttpStatus statusCode, String message) {
        this.header.setResultCode(statusCode.value());
        this.header.setResultMessage(message);
        this.header.setIsSuccessful();
    }

    @Getter @Setter
    private static class Header {

        private int resultCode;
        private String resultMessage;
        private Boolean isSuccessful;

        public Header() {
            this.resultCode = HttpStatus.OK.value();
            this.resultMessage = HttpStatus.OK.getReasonPhrase();
            setIsSuccessful();
        }

        public void setIsSuccessful() {
            isSuccessful = (resultCode == HttpStatus.OK.value()) || (resultCode == HttpStatus.CREATED.value());
        }
    }
}
