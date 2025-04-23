
public class driver {
  public static void main(String[] args) {
    ReadWord reader = new ReadWord("words.txt");

    String word = reader.getRandomWord();
    
    Thread thread = new Thread(() -> {
      System.out.println("Picked word: " + word);
    });

    thread.start();
  }
}