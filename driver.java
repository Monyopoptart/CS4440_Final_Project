public class driver {
  public static void main(String[] args) {
    ReadWord reader = new ReadWord("words.txt");

    String word = reader.getRandomWord();
    System.out.println("Picked word: " + word);
  }
}