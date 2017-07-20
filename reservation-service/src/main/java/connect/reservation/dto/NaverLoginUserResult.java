package connect.reservation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaverLoginUserResult {
	private String resultCode;
    private String message;
    private NaverLoginUser response;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NaverLoginUser getResponse() {
        return response;
    }

    public void setResponse(NaverLoginUser response) {
        this.response = response;
    }
}
