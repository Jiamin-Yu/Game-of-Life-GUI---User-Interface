package org.sosylab.view;

import static java.util.Objects.requireNonNull;

import javax.swing.SwingWorker;
import org.sosylab.model.Model;
import org.sosylab.model.Shape;

/**
   Manages the interaction between view and game.
 */
public class GameOfLifeController implements Controller {

  private Model model;
  private View view;


  /**
   * constrcut a game of life controller.
   *
   * @param gameOfLife the current game
   */
  public GameOfLifeController(Model gameOfLife) {
    model = requireNonNull(gameOfLife);
  }

  @Override
  public void setView(View view) {
    this.view = requireNonNull(view);
  }

  @Override
  public void start() {
    view.showGame();
  }

  @Override
  public void clearBoard() {
    model.clear();
  }

  @Override
  public void setCellAlive(int column, int row, boolean alive) {
    if (alive) {
      model.setCellAlive(column, row);
    }
  }

  @Override
  public boolean step() {
    model.next();
    return true;
  }

  @Override
  public void stepIndefinitely(boolean control) {

    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

      @Override
      protected Void doInBackground() throws Exception {

        // do stuff
        if (control == true) {
          while (true) {
            model.next();
          }
        } else {
          stopStepping();
        }

        //Thread.sleep(10000);
        return null;
      }

        @Override
        protected void done() {
          //publish();
        }
    };
    worker.execute();

  }

  @Override
  public void setStepSpeed(int value) {

  }

  @Override
  public boolean stopStepping() {
    return false;
  }

  @Override
  public void setShape(Shape shape) {
  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void dispose() {
    model.removePropertyChangeListener(view);
  }
}
