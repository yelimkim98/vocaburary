import java.io.IOException;
import vocabook.Koreans;
import vocabook.VocaBook;
import vocabook.VocaBookFactory;
import vocabook.HashMapPutDuplicateStrategy;

public class Main {

    private static final String VOCA_FILE_NAME = "C:/Users/LG_PC/Desktop/정현 자바과제2 (1)/voca1800.txt";

    public static void main(String[] args) {
        try {
            VocaBook vocaBook = VocaBookFactory.createFromVocaDataFile(VOCA_FILE_NAME,
                HashMapPutDuplicateStrategy.USE_ORIGINAL);

            Koreans koreans = vocaBook.searchKoreanMeaning("intellect");
            System.out.println(koreans.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
