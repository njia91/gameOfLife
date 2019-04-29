import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class Test_GameOfLife {

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {
  }

  Cell[][] createSimpleWorldConf(){
    Cell[][] gw = new Cell[3][3];

    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        gw[row][col] = new Cell();
      }
    }
    return gw;
  }

  @Test
  void SingleCellShouldDie(){
    Cell[][] customConf = createSimpleWorldConf();

    GameOfLife gol = new GameOfLife(customConf);

    gol.iterate();
    Cell[][] currentConf = gol.getCurrentGrid();

    assertFalse(currentConf[1][1].isAlive());
  }

  @Test
  void DeadCellWith3AliveNeighboursShouldLive(){
    Cell[][] customConf = createSimpleWorldConf();
    customConf[1][0].setCurrentState(State.ALIVE);
    customConf[1][1].setCurrentState(State.ALIVE);
    customConf[2][1].setCurrentState(State.ALIVE);

    GameOfLife gol = new GameOfLife(customConf);

    gol.iterate();
    Cell[][] currentConf = gol.getCurrentGrid();

    assertTrue(currentConf[2][0].isAlive());
    assertFalse(currentConf[0][0].isAlive());
    assertFalse(currentConf[0][1].isAlive());
    assertFalse(currentConf[0][2].isAlive());
    assertFalse(currentConf[1][2].isAlive());
    assertFalse(currentConf[2][2].isAlive());
  }

  @Test
  void AliveCellWithMoreThan3NeighbourShouldDie(){
    Cell[][] customConf = createSimpleWorldConf();
    customConf[1][1].setCurrentState(State.ALIVE);
    customConf[1][2].setCurrentState(State.ALIVE);
    customConf[2][1].setCurrentState(State.ALIVE);
    customConf[1][0].setCurrentState(State.ALIVE);
    customConf[0][1].setCurrentState(State.ALIVE);

    GameOfLife gol = new GameOfLife(customConf);

    gol.iterate();
    Cell[][] currentConf = gol.getCurrentGrid();

    assertFalse(currentConf[1][1].isAlive());
  }

  @Test
  void TestMultipleIterations(){
    Cell[][] customConf = createSimpleWorldConf();
    customConf[0][1].setCurrentState(State.ALIVE);
    customConf[1][1].setCurrentState(State.ALIVE);
    customConf[2][1].setCurrentState(State.ALIVE);

    GameOfLife gol = new GameOfLife(customConf);

    gol.iterate();
    Cell[][] currentConf = gol.getCurrentGrid();

    assertTrue(currentConf[1][1].isAlive());
    assertTrue(currentConf[1][2].isAlive());
    assertTrue(currentConf[1][0].isAlive());


    gol.iterate();
    currentConf = gol.getCurrentGrid();

    assertTrue(currentConf[0][1].isAlive());
    assertTrue(currentConf[1][1].isAlive());
    assertTrue(currentConf[2][1].isAlive());

  }

}