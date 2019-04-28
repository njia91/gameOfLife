enum State {
  ALIVE, DEAD, UNKNOWN
}

public class Cell {

  private State currentState;
  private State nextState;

  public Cell() {
    currentState = State.DEAD;
    nextState = State.UNKNOWN;
  }

  public void setCurrentState(State currentState) {
    this.currentState = currentState;
  }

  public boolean isAlive(){
    return currentState == State.ALIVE;
  }

  public void cellDiesNextGeneration(){
    nextState = State.DEAD;
  }

  public void cellLivesNextGeneration(){
    nextState = State.ALIVE;
  }

  public void evolve(){
    if (nextState != State.UNKNOWN){
      currentState = nextState;
      nextState = State.UNKNOWN;
    }
  }

}
