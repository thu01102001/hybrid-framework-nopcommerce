package commons;

import java.util.Random;

//ham dung chung cho layer testcase
public class BaseTest {
    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }
}
