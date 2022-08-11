package gft.LojaAPI.exception;

public class LojaAPIException extends RuntimeException{
    private String message;

    public LojaAPIException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {this.message = message;}
}
