package todo.dwarak.todo.model;

public enum ErrorCodes {

    PERSON_NOT_FOUND(1001),
    TODO_NOT_FOUND(1002);

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int code() {
        return this.code;
    }

}
