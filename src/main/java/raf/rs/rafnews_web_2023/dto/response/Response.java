package raf.rs.rafnews_web_2023.dto.response;

public class Response {
    private String jwt;
    private int status;
    private String message;

    private Response(String jwt, int status, String message) {
        this.jwt = jwt;
        this.status = status;
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public static class ResponseBuilder {
        private String jwt;
        private int status;
        private String message;

        public ResponseBuilder setJwt(String jwt) {
            this.jwt = jwt;
            return this;
        }

        public ResponseBuilder setStatus(int status) {
            this.status = status;
            return this;
        }

        public ResponseBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Response build() {
            return new Response(jwt, status, message);
        }
    }
}
