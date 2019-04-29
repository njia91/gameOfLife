enum Configuration {
  GLIDER, PULSAR
}

public class GameOfLife {

  private Cell[][] grid;

  private int numOfRows;
  private int numOfColumns;


  /**
   * Init a grid with pre-installed cell configuration.
   * @param initialConf
   */
  public GameOfLife(Configuration initialConf) {
        switch (initialConf){
      case GLIDER:
        numOfColumns  = 20;
        numOfRows     = 20;
        grid = new Cell[numOfRows][numOfColumns];
        createAllCells();
        initGlider();
        break;
      case PULSAR:
        numOfRows     = 15;
        numOfColumns  = 15;
        grid = new Cell[numOfRows][numOfColumns];
        createAllCells();
        initPulsar();
        break;
      default:
        break;
    }
  }

  /**
   * Creates a Game of life with custom configuration.
   * @param customGameWorld Matrix of custom cell configuration
   */
  public GameOfLife(Cell[][] customGameWorld){
    this.numOfRows = customGameWorld.length;
    this.numOfColumns = customGameWorld[0].length;
    grid = customGameWorld;
  }

  private void createAllCells(){
    for (int row = 0; row < numOfRows; row++) {
      for (int col = 0; col < numOfColumns; col++) {
        grid[row][col] = new Cell();
      }
    }
  }

  private void initGlider(){
    grid[17][0].setCurrentState(State.ALIVE);
    grid[17][1].setCurrentState(State.ALIVE);
    grid[17][2].setCurrentState(State.ALIVE);
    grid[18][2].setCurrentState(State.ALIVE);
    grid[19][1].setCurrentState(State.ALIVE);
  }

  private void initPulsar(){
    grid[1][3].setCurrentState(State.ALIVE);
    grid[1][4].setCurrentState(State.ALIVE);
    grid[1][5].setCurrentState(State.ALIVE);
    grid[3][1].setCurrentState(State.ALIVE);
    grid[4][1].setCurrentState(State.ALIVE);
    grid[5][1].setCurrentState(State.ALIVE);
    grid[3][6].setCurrentState(State.ALIVE);
    grid[4][6].setCurrentState(State.ALIVE);
    grid[5][6].setCurrentState(State.ALIVE);
    grid[6][3].setCurrentState(State.ALIVE);
    grid[6][4].setCurrentState(State.ALIVE);
    grid[6][5].setCurrentState(State.ALIVE);

    grid[1][9].setCurrentState(State.ALIVE);
    grid[1][10].setCurrentState(State.ALIVE);
    grid[1][11].setCurrentState(State.ALIVE);
    grid[3][8].setCurrentState(State.ALIVE);
    grid[4][8].setCurrentState(State.ALIVE);
    grid[5][8].setCurrentState(State.ALIVE);
    grid[3][13].setCurrentState(State.ALIVE);
    grid[4][13].setCurrentState(State.ALIVE);
    grid[5][13].setCurrentState(State.ALIVE);
    grid[6][9].setCurrentState(State.ALIVE);
    grid[6][10].setCurrentState(State.ALIVE);
    grid[6][11].setCurrentState(State.ALIVE);

    grid[8][3].setCurrentState(State.ALIVE);
    grid[8][4].setCurrentState(State.ALIVE);
    grid[8][5].setCurrentState(State.ALIVE);
    grid[9][1].setCurrentState(State.ALIVE);
    grid[10][1].setCurrentState(State.ALIVE);
    grid[11][1].setCurrentState(State.ALIVE);
    grid[13][3].setCurrentState(State.ALIVE);
    grid[13][4].setCurrentState(State.ALIVE);
    grid[13][5].setCurrentState(State.ALIVE);
    grid[9][6].setCurrentState(State.ALIVE);
    grid[10][6].setCurrentState(State.ALIVE);
    grid[11][6].setCurrentState(State.ALIVE);

    grid[8][9].setCurrentState(State.ALIVE);
    grid[8][10].setCurrentState(State.ALIVE);
    grid[8][11].setCurrentState(State.ALIVE);
    grid[9][8].setCurrentState(State.ALIVE);
    grid[10][8].setCurrentState(State.ALIVE);
    grid[11][8].setCurrentState(State.ALIVE);
    grid[9][13].setCurrentState(State.ALIVE);
    grid[10][13].setCurrentState(State.ALIVE);
    grid[11][13].setCurrentState(State.ALIVE);
    grid[13][9].setCurrentState(State.ALIVE);
    grid[13][10].setCurrentState(State.ALIVE);
    grid[13][11].setCurrentState(State.ALIVE);

  }

  /**
   * Returns copy of current grid.
   * @return grid
   */
  public Cell[][] getCurrentGrid() {
    return grid;
  }

  /**
   * Iterates over all cells in grid and evolve them to next generation.
   */
  public void iterate(){
    int numOfAliveNeighbours;
    Cell currentCell;
    for (int row = 0; row < numOfRows; row++) {
      for (int col = 0; col < numOfColumns; col++) {
        numOfAliveNeighbours = countNumOfAliveNeighbours(row, col);
        currentCell = grid[row][col];
        doLogicForNextEvolution(currentCell, numOfAliveNeighbours);
      }
    }

    evolveAllCells();
  }

  private void doLogicForNextEvolution(Cell currentCell, int numOfAliveNeighbours){
    if (currentCell.isAlive()){
      currentCell.cellLivesNextGeneration();
      if (numOfAliveNeighbours < 2 || numOfAliveNeighbours > 3){
        currentCell.cellDiesNextGeneration();
      }
    }
    else {
      currentCell.cellDiesNextGeneration();
      if (numOfAliveNeighbours == 3){
        currentCell.cellLivesNextGeneration();
      }
    }
  }


  private void evolveAllCells(){
    for (int row = 0; row < numOfRows; row++) {
      for (int col = 0; col < numOfColumns; col++) {
        grid[row][col].evolve();
      }
    }
  }

  private int countNumOfAliveNeighbours(int rowPos, int colPos){
    int numOfAliveNeighbours = 0;
    for (int rowNr  = rowPos - 1; rowNr <= rowPos + 1; rowNr++) {
      for (int colNr =  colPos - 1; colNr <= colPos + 1; colNr++) {

        // To prevent OutOfBoundsException
        if (rowNr >= 0 && rowNr < numOfRows && colNr >= 0 && colNr  < numOfColumns
                && (rowNr != rowPos || colNr != colPos) && grid[rowNr][colNr].isAlive()){
          numOfAliveNeighbours++;
        }
      }
    }
    return numOfAliveNeighbours;
  }
}
