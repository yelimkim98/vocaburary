package vocabook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VocaBookFactory {

    public static VocaBook createFromVocaDataFile(String vocaDataFileName,
            HashMapPutDuplicateStrategy hashMapPutDuplicateStrategy) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(vocaDataFileName));
        VocaBook vocaBook = new VocaBook(hashMapPutDuplicateStrategy);

        while (true) {
            String line = br.readLine();

            if (Objects.isNull(line)) {
                break;
            }
            saveOneLineTo(vocaBook, line);
        }
        return vocaBook;
    }

    private static void saveOneLineTo(VocaBook vocaBook, String line) {
        String[] split = line.split(",");

        String english = split[0];
        List<String> koreans = new ArrayList<>();

        for (int i = 1; i < split.length; i++) {
            koreans.add(split[i].trim());
        }
        vocaBook.addWord(english, koreans);
    }
}
