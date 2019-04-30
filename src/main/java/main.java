import java.util.Scanner;

/**
 * Tiny and simple Game of Life implementation with an simple GUI.
 *
 * @author Michael Andersson
 */
public class main {

  public static void main(String[]args){
    Cell[][] gameBoard = null;
    GameOfLife gol = new GameOfLife(Configuration.PULSAR);
    Scanner scanner = new Scanner(System.in);


    System.out.println("Welcome to the Game of Life");
    do{
      gameBoard = gol.getCurrentGrid();
      displayGrid(gameBoard);

      System.out.println("Press Enter to continue to next generation or Ctrl + C to terminate session");
      scanner.nextLine();
      gol.evolveToNextGeneration();
      // Flush Console
      System.out.print("\033[H\033[2J");
      System.out.flush();
    } while(true);
  }


  public static void displayGrid(Cell[][] gb){
    for (int row = 0; row < gb.length; row++){
      for (int col = 0; col < gb[0].length; col++){
        String output = gb[row][col].isAlive() ? "0" : "-";
        System.out.print(output);
      }
      System.out.println();
    }
  }
}



