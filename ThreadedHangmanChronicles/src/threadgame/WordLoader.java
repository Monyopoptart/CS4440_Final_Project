package threadgame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class WordLoader {
    public static String loadWord() {
        try {
            List<String> words = Files.readAllLines(Paths.get("words.txt"));
            return words.get(new Random().nextInt(words.size())).trim();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read words.txt", e);
        }
    }
}