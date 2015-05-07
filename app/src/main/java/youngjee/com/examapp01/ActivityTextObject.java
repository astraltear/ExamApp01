package youngjee.com.examapp01;

import java.io.Serializable;

public class ActivityTextObject implements Serializable {


    private String text1, text2;

    public ActivityTextObject() {
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
