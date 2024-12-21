package pageUIs;

public class RegisterPageUI {
    //public: bat ki vi tri nao trong framwork deu truy cap toi bien nay duoc
    //static: bien nay co the goi toi ma ko can khoi tao doi tuong len - co the truy cap thong qua pham vi class
    //RegisterPageUI.REGISTER_LINK
    //new RegisterPage().REGISTER_LINK
    //final: bien nay da duoc gan roi thi ko duowc phep gan lai bang gia tri moi

    public static final String FIRST_NAME_TEXTBOX = "//input[@id='FirstName']";
    public static final String LAST_NAME_TEXTBOX = "//input[@id='LastName']";
    public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
    public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
    public static final String CONFIRM_PASSWORD_TEXTBOX = "//input[@id='ConfirmPassword']";
    public static final String REGISTER_BUTTON = "//button[@id='register-button']";
    public static final String REGISTER_SUCCESS_MESSAGE = "//div[@class='result']";
    public static final String LOGOUT_LINK = "//a[@class='ico-logout']";
}
