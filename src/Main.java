import java.io.IOException;
import java.util.Scanner;
import vocabook.Koreans;
import vocabook.VocaBook;
import vocabook.VocaBookFactory;
import vocabook.HashMapPutDuplicateStrategy;

public class Main {

    private static final String VOCA_FILE_NAME = "C:/Users/LG_PC/Desktop/정현 자바과제2 (1)/voca1800.txt";
    private static final Scanner scanner = new Scanner(System.in);

    private static VocaBook vocaBook;

    public static void main(String[] args) {
        try {
            vocaBook = VocaBookFactory.createFromVocaDataFile(VOCA_FILE_NAME,
                HashMapPutDuplicateStrategy.USE_ORIGINAL);

            System.out.println("원하는 기능을 선택하세요.\n"
                + " 1 : 영어 단어뜻 검색");
            System.out.print(">> ");
            int input = Integer.parseInt(scanner.nextLine());

            execute(input);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void execute(int commend) {
        if (commend == 1) {
            search();
        }
    }

    private static void search() {
        System.out.println("찾을 영단어를 입력하세요 : ");
        String input = scanner.nextLine();

        try {
            Koreans koreans = vocaBook.searchKoreanMeaning(input);
            System.out.println(koreans.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
