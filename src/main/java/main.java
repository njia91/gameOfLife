import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Tiny and simple Game of Life implementation with an simple GUI.
 *
 * @author Michael Andersson
 */
public class main {

  public static void main(String[]args){
    Cell[][] gameBoard = null;
    Scanner scanner = new Scanner(System.in);
    GameOfLife gol = null;
    System.out.println("Welcome to the Game of Life");
    System.out.println("Select initial configuration, enter an integer between 1 and 2\n" +
            "1: Glider\n" +
            "2: Pulsar");
    String usrInput = scanner.nextLine();

    switch (usrInput){
      case "1":
        gol = new GameOfLife(Configuration.GLIDER);
        break;
      case "2":
        gol = new GameOfLife(Configuration.PULSAR);
        break;
      default:
        System.err.println("Invalid input. Please choose an integer between 1 and 2.");
        exit(1);
    }


    do{
      gameBoard = gol.getCurrentGrid();
      displayGrid(gameBoard);

      System.out.println("Press Enter to continue to next generation or Ctrl + C to terminate program.");
      scanner.nextLine();
      gol.evolveToNextGeneration();
      // Flush Console
    } while(true);
  }


  public static void displayGrid(Cell[][] gb){
    System.out.print("\033[H\033[2J");
    System.out.flush();
    for (int row = 0; row < gb.length; row++){
      for (int col = 0; col < gb[0].length; col++){
        String output = gb[row][col].isAlive() ? "0" : "-";
        System.out.print(output);
      }
      System.out.println();
    }
  }
}



