package vocabook;

import java.util.List;

public class Koreans {

    private List<String> koreans;

    public Koreans(List<String> koreans) {
        this.koreans = koreans;
    }

    @Override
    public String toString() {
        return String.join(", ", koreans);
    }
}
