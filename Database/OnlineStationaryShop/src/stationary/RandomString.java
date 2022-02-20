package stationary;

import java.io.Serializable;

public class RandomString implements Serializable {
    public long change = 185619;
    public RandomString(){}

    String getRandomString(long change)
    {
        String fixed = "ORD";
        fixed = fixed + String.valueOf(change);
        return fixed;
    }

    public String getSessionId(long n)
    {
        this.change = ++n;
        return getRandomString(change);
    }
}
