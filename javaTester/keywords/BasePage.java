package keywords;

public abstract class BasePage {
    //dung de the hien tinh chat truu tuong cua OOP trong Java (abstraction)

    //Abstract method
    public abstract boolean isPageDisplayed();

    //non-abstract method
    public void clickToElement() {

    }

    //access modifier - Pham vi truy cap
    //private : dung duy nhat trong class
    //default: dung class nay, class ben ngoai, nhung phai cung package
    //procted: ke thua moi dung duoc, cung package
    //public: full

    //thuoc tinh - properties / field / public
    private String fullName;
    public String phoneNumber;
    String city;
    protected String address;


    //ham - method
    private void setFullName() {

    }

    void setCity() {

    }

    protected void setAddress() {

    }

    public void setPhoneNumber() {

    }
}
