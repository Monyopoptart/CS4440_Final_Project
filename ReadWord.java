import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ReadWord {
    private List<String> words = new ArrayList<>();


    public ReadWord (String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                words.add(line);
            }
             
            br.close();
            fr.close();

        } catch (IOException e) {
        }
    }

    public String getRandomWord() {
        if (words.isEmpty()) {
            System.out.println("No words availible");
        }

        Random random = new Random();
        int index = random.nextInt(words.size());

        return words.get(index);
    }
    
}
