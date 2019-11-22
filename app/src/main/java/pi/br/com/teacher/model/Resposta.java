package pi.br.com.teacher.model;

import androidx.annotation.NonNull;

public class Resposta {
    private int statusCode;
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
