import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoPlayer extends AbstractPlayer {

  public AutoPlayer(CardPile left, CardPile right, String name) {
    super(left, right, name);
  }

  protected int selectCard() {
    List<Integer> chooseFrom = new ArrayList<>();

    for (int i = 0; i < cards.length; i++) {
      chooseFrom.add(i);
      for (int j = 0; j < i; j++) {
        if (cards[i].getRank() == cards[j].getRank()) {
          if (chooseFrom.contains(i)) {
            chooseFrom.remove((Integer) i);
          }
          if (chooseFrom.contains(j)) {
            chooseFrom.remove((Integer) j);
          }
        }
      }
    }

    Random r = new Random();
    if (chooseFrom.isEmpty()) {
      return r.nextInt(4);
    } else {
      return chooseFrom.get(r.nextInt(chooseFrom.size()));
    }
  }

}