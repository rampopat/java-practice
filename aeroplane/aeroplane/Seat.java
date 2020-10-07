package aeroplane;

import java.util.NoSuchElementException;

public class Seat {

  // Thes values may be edited
  public static final int MAX_ROW_VALUE = 50;
  public static final char MAX_LETTER_VALUE = 'F';
  public static final int[] EXIT_ROWS = {1, 10, 30};
  public static final int CREW_FINAL_ROW = 1;
  public static final int BUSINESS_FINAL_ROW = 15;

  // Should be left unless changing order of sections
  public static final Seat CREW_START_SEAT = new Seat(1, 'A');
  public static final Seat CREW_END_SEAT = new Seat(CREW_FINAL_ROW, MAX_LETTER_VALUE);
  public static final Seat BUSINESS_START_SEAT = new Seat(CREW_FINAL_ROW + 1, 'A');
  public static final Seat BUSINESS_END_SEAT = new Seat(BUSINESS_FINAL_ROW, MAX_LETTER_VALUE);
  public static final Seat ECONOMY_START_SEAT = new Seat(BUSINESS_FINAL_ROW + 1, 'A');
  public static final Seat ECONOMY_END_SEAT = new Seat(MAX_ROW_VALUE, MAX_LETTER_VALUE);

  private final int row;
  private final char letter;

  public Seat(int row, char letter) {
    this.row = row;
    this.letter = letter;
  }

  @Override
  public String toString() {
    return Integer.toString(row) + letter;
  }

  public boolean isEmergencyExit() {
    for (int exitRow : EXIT_ROWS) {
      if (row == exitRow) {
        return true;
      }
    }
    return false;
  }

  public boolean hasNext() {
    return (row < MAX_ROW_VALUE
        || row == MAX_ROW_VALUE && letter < MAX_LETTER_VALUE);
  }

  public Seat next() {
    if (hasNext()) {
      if (letter < MAX_LETTER_VALUE) {
        return new Seat(row, (char) (letter + 1));
      } else {
        return new Seat(row + 1, 'A');
      }
    } else {
      throw new NoSuchElementException();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Seat)) {
      return false;
    } else {
      Seat other = (Seat) o;
      return (row == other.row && letter == other.letter);
    }
  }

  @Override
  public int hashCode() {
    return row * (1 + MAX_LETTER_VALUE - 'A') + letter; // (row * number of letters) + letter
  }

}
