import java.io.Console;

public class main {

  public static void main(String[]args){
    Cell[][] gameBoard = null;
    GameOfLife gol = new GameOfLife(Configuration.PULSAR);

    do{
      gameBoard = gol.getCurrentGrid();
      displayGrid(gameBoard);
      gol.iterate();

    } while(checkUserInput());
  }

  public static boolean checkUserInput(){
    Console c = System.console();
    if (c != null){
      c.format("Press Enter to continue with next iteration");
      c.readLine();
      return true;
    }
    else {
      return false;
    }

  }

  public static void displayGrid(Cell[][] gb){
    // Flush Console
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



