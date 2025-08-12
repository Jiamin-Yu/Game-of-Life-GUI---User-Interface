package org.sosylab.view;

import static java.util.Objects.requireNonNull;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.Serial;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.sosylab.model.Cell;
import org.sosylab.model.Model;
import org.sosylab.model.Shape;
import org.sosylab.model.ShapeCollection;

/**
 * Manage the view of the game.
 */
public class GameOfLifeView extends JFrame implements View {

  @Serial
  private static final long serialVersionUID = 1L;

  private Model model;
  private Controller controller;

  private DrawBoard drawBoard;
  private JSplitPane splitPane;
  private JPanel topPanel;
  private JPanel bottomPanel;
  private JButton buttonStart;



  /**
   * construct the view with the controller and current game.
   *
   * @param model the current game
   * @param controller controller of the game
   */

  public GameOfLifeView(Model model, Controller controller) {
    super("Game of Life");

    this.model = requireNonNull(model);
    this.controller = requireNonNull(controller);
    drawBoard = new DrawBoard(model);

    splitPane = new JSplitPane();

    topPanel = drawBoard;
    bottomPanel = new JPanel();
    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));


    setPreferredSize(new Dimension(400, 400));
    getContentPane().setLayout(new GridLayout());
    getContentPane().add(splitPane);

    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    splitPane.setDividerLocation(300);
    splitPane.setTopComponent(topPanel);
    splitPane.setBottomComponent(bottomPanel);


    JButton buttonNext = new JButton("Next");
    buttonStart = new JButton("Start");
    JButton buttonClear = new JButton("Clear");
    JTextField textField = new JTextField("Generation:" + model.getGenerations());

    buttonNext.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        controller.step();
        drawBoard.paintComponent(drawBoard.getGraphics());
        textField.setText("Generation:" + model.getGenerations());
      }
    });

    buttonStart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean control;

        if (buttonStart.getText().equals("Start")) {

          buttonStart.setText("Stop");
          textField.setText("Generation:" + model.getGenerations());
          control = true;
          controller.stepIndefinitely(control);
          drawBoard.paintComponent(drawBoard.getGraphics());
        } else {
          buttonStart.setText("Start");
          control = false;
          controller.stepIndefinitely(control);
          textField.setText("Generation:" + model.getGenerations());
        }



        /*
        if(buttonStart.getText().equals("Stop")) {
          //control = controller.stopStepping();
          //controller.stepIndefinitely(control);
          buttonStart.setText("Start");
          textField.setText("Generation:" + model.getGenerations());
        }

         */



      }
    });



    buttonClear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.clearBoard();
        drawBoard.paintComponent(drawBoard.getGraphics());
        textField.setText("Generation:" + model.getGenerations());
      }
    });

    String[] shapeString = {"Block", "Boat", "Blinker", "Toad", "Glider", "Spaceship", "Pulsar"};
    JComboBox<String> shapeList = new JComboBox<>(shapeString);

    shapeList.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.clearBoard();
        if (shapeList.getSelectedItem().equals("Block")) {
          Shape[] shape = ShapeCollection.getShapes();
          Shape block = shape[0];
          for (Cell cell : block.getCells()) {
            model.setCellAlive(cell.getColumn(),
                cell.getRow());
          }
          drawBoard.paintComponent(drawBoard.getGraphics());
        }

        if (shapeList.getSelectedItem().equals("Boat")) {
          Shape[] shape = ShapeCollection.getShapes();
          Shape boat = shape[1];
          for (Cell cell : boat.getCells()) {
            model.setCellAlive(cell.getColumn(), cell.getRow());
          }
          drawBoard.paintComponent(drawBoard.getGraphics());
        }

        if (shapeList.getSelectedItem().equals("Blinker")) {
          Shape[] shape = ShapeCollection.getShapes();
          Shape blinker = shape[2];
          for (Cell cell : blinker.getCells()) {
            model.setCellAlive(cell.getColumn(), cell.getRow());
          }
          drawBoard.paintComponent(drawBoard.getGraphics());
        }

        if (shapeList.getSelectedItem().equals("Toad")) {
          Shape[] shape = ShapeCollection.getShapes();
          Shape toad = shape[3];
          for (Cell cell : toad.getCells()) {
            model.setCellAlive(cell.getColumn(), cell.getRow());
          }
          drawBoard.paintComponent(drawBoard.getGraphics());
        }
        if (shapeList.getSelectedItem().equals("Glider")) {
          Shape[] shape = ShapeCollection.getShapes();
          Shape glider = shape[4];
          for (Cell cell : glider.getCells()) {
            model.setCellAlive(cell.getColumn(), cell.getRow());
          }
          drawBoard.paintComponent(drawBoard.getGraphics());
        }
        if (shapeList.getSelectedItem().equals("Spaceship")) {
          Shape[] shape = ShapeCollection.getShapes();
          Shape spaceship = shape[5];
          for (Cell cell : spaceship.getCells()) {
            model.setCellAlive(cell.getColumn(), cell.getRow());
          }
          drawBoard.paintComponent(drawBoard.getGraphics());
        }
        if (shapeList.getSelectedItem().equals("Pulsar")) {
          Shape[] shape = ShapeCollection.getShapes();
          Shape pulsar = shape[6];
          for (Cell cell : pulsar.getCells()) {
            model.setCellAlive(cell.getColumn(), cell.getRow());
          }
          drawBoard.paintComponent(drawBoard.getGraphics());
        }

      }
    });


    for (int i = 0; i < 5; ++i) {
      bottomPanel.add(shapeList);
      bottomPanel.add(buttonNext);
      bottomPanel.add(buttonStart);
      bottomPanel.add(buttonClear);
      bottomPanel.add(textField);

    }

    //new GridLayout(1, 5)

    pack();
    addEventListener();


  }


  @Override
  public void showGame() {
    new GameOfLifeView(model, controller).setVisible(true);
  }


  private void addEventListener() {}


  @Override
  public void showErrorMessage(String message) {
    // TODO insert code here
  }

  @Override
  public void startStepping() {
    // TODO insert code here
  }

  @Override
  public boolean stopStepping() {
    if (buttonStart.getText().equals("Stop")) {
      return true;
    }
    return true;



  }

  @Override
  public void dispose() {
    // drawBoard.dispose();
    controller.dispose();
    super.dispose();
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    SwingUtilities.invokeLater(() -> handleChangeEvent(event));
  }




  /**
   * The observable (= model) has just published that its state has changed. The GUI needs to be
   * updated accordingly here.
   *
   * @param event The event that was fired by the model.
   */
  private void handleChangeEvent(PropertyChangeEvent event) {

    // the next lines are for demonstration purposes
    if (event.getPropertyName().equals(Model.STATE_CHANGED)) {
      System.out.println("Model has changed its state.");

    }
    //repaint the whole gameboard
  }



}
