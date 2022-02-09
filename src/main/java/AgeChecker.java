import java.util.*;

public class AgeChecker {

  public static void main(String[] args) {
    HashMap<Long, List<String>> facts = new HashMap<>();

    facts.put(
      60L,
      List.of(
        "Planet of the apes released this decade",
        "Scott Williams was born"
      )
    );
    facts.put(
      70L,
      List.of(
        "this decade had the appearance of the first commercial microprocessor",
        "Scott Williams was born"
      )
    );
    facts.put(
      80L,
      List.of("The Game boy launched this decade", "Scott Williams was born")
    );
    facts.put(
      90L,
      List.of("Tiger Woods won his first Masters", "Scott Williams was born")
    );
    facts.put(
      0L,
      List.of("The Nintendo DS launched this decade", "Scott Williams was born")
    );

    Scanner console = new Scanner(System.in);
    long dec = 0;
    long year = 2022;
    /*

        Ask the user do you want to multiply or add.
        Enter 0 for add, 1 for multiply

         */
    System.out.println("Enter what age you will be this year");

    long age = console.nextLong();

    dec = year - age;

    dec = dec % 100;

    dec = (dec - dec % 10);
    if (dec < 10) {
      System.out.println("Result = 00");
    } else {
      System.out.println("Result = " + dec);
    }

    List<String> yourDecadesFacts = facts.getOrDefault(
      dec,
      Collections.emptyList()
    );

    boolean isThereAFact = !yourDecadesFacts.isEmpty();

    Random r = new Random();
    int pos = r.nextInt(yourDecadesFacts.size());

    if (isThereAFact) {
      System.out.println(yourDecadesFacts.get(pos));
    } else {
      System.out.println("We don't have a fact for this decade");
    }
  }
}
