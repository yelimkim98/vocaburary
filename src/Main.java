import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import vocabook.HashMapPutDuplicateStrategy;
import vocabook.Koreans;
import vocabook.VocaBook;
import vocabook.VocaBookFactory;

public class Main {

    private static final int SEARCH = 1;
    private static final int PRINT_ALL = 2;
    private static final int ADD_NEW_WORD = 3;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String VOCA_FILE_NAME = "voca1800.txt";

    private static VocaBook vocaBook;

    public static void main(String[] args) {
        try {
            vocaBook = VocaBookFactory.createFromVocaDataFile(VOCA_FILE_NAME,
                HashMapPutDuplicateStrategy.USE_ORIGINAL);
            int command ;

            do {
                System.out.print("원하는 기능을 선택하세요.\n"
                    + " 1 : 영어 단어뜻 검색\n"
                    + " 2 : 전체 단어 보기\n"
                    + " 3 : 영어 단어 추가\n"
                    + " 0 : 종료\n");
                System.out.print(">> ");
                command = Integer.parseInt(scanner.nextLine());
                execute(command);
                System.out.println();
            } while (command != 0);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void execute(int commend) {
        if (commend == SEARCH) {
            search();
        }
        else if (commend == PRINT_ALL) {
            printAll();
        }
        else if (commend == ADD_NEW_WORD) {
            addNewWord();
        }
        else {
            throw new IllegalArgumentException("존재하지 않는 명령입니다.");
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

    private static void printAll() {
        System.out.println(vocaBook.toStringAll());
    }

    private static void addNewWord() {
        System.out.println("추가할 영어 단어를 입력하세요.");
        System.out.print(">> ");
        String english = scanner.nextLine();

        System.out.println("한국어 뜻을 입력하세요.");
        System.out.println(">> ");
        String[] meaningsInput = scanner.nextLine().split(",");

        List<String> meanings = Arrays.stream(meaningsInput)
            .map(String::trim)
            .collect(Collectors.toList());

        vocaBook.addWord(english, meanings);
        System.out.println("단어가 추가되었습니다.");
    }
}
