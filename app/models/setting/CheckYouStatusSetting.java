package models.setting;

public enum CheckYouStatusSetting {

    OK         (20, "ok."),
    NO_RESULT  (50, "no result.");

    public Integer code;
    public String message;

    private CheckYouStatusSetting(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}