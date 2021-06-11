package vocabook;

import static vocabook.HashMapPutDuplicateStrategy.CHANGE;
import static vocabook.HashMapPutDuplicateStrategy.ERROR;
import static vocabook.HashMapPutDuplicateStrategy.USE_ORIGINAL;

import java.util.HashMap;
import java.util.List;

public class VocaBook {

    private HashMapPutDuplicateStrategy hashMapPutDuplicateStrategy;
    private HashMap<String, Koreans> voca = new HashMap<>();

    public VocaBook() {
        this(ERROR);
    }

    public VocaBook(HashMapPutDuplicateStrategy hashMapPutDuplicateStrategy) {
        this.hashMapPutDuplicateStrategy = hashMapPutDuplicateStrategy;
    }

    public boolean isWordExist(String english) {
        return voca.containsKey(english);
    }

    public void addWord(String english, List<String> koreanMeaning) {
        if (isWordExist(english)) {
            if (hashMapPutDuplicateStrategy == ERROR) {
                String errorMessage = english + "는 이미 존재하는 영어단어입니다.\n"
                    + "기존 뜻 : " + voca.get(english).toString() + "\n"
                    + " 새  뜻 : " + new Koreans(koreanMeaning).toString();
                throw new IllegalArgumentException(errorMessage);
            }
            else if (hashMapPutDuplicateStrategy == USE_ORIGINAL) {
                return;
            }
            else if (hashMapPutDuplicateStrategy == CHANGE) {
                voca.put(english, new Koreans(koreanMeaning));
            }
        }
        voca.put(english, new Koreans(koreanMeaning));
    }

    public Koreans searchKoreanMeaning(String english) {
        if (voca.containsKey(english)) {
            return voca.get(english);
        }
        throw new IllegalArgumentException(english + "는 단어장에 없습니다.");
    }
}
