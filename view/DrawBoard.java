package org.sosylab.view;

import static java.util.Objects.requireNonNull;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;
import javax.swing.JPanel;
import org.sosylab.model.Cell;
import org.sosylab.model.Model;

/**
 * manage a panel of gameboard.
 */
public class DrawBoard extends JPanel implements MouseListener {

  @Serial
  private static final long serialVersionUID = 1L;
  private Model model;

  private int cellWidth;
  private int cellHeight;


  /**
   * construct a drawboard.
   *
   * @param model the current game
   */
  public DrawBoard(Model model) {

    this.model = requireNonNull(model);

    addMouseListener(this);

    cellWidth = Math.floorDiv(getWidth(), model.getColumns());
    cellHeight = Math.floorDiv(getHeight(), model.getRows());
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    changeCells(e);
    repaint();

  }

  private void changeCells(MouseEvent e) {
    Point point = e.getPoint();

    if (model.isCellAlive(Math.floorDiv(point.x, cellWidth), Math.floorDiv(point.y, cellHeight))) {
      model.setCellDead(Math.floorDiv(point.x, cellWidth), Math.floorDiv(point.y, cellHeight));

    } else {
      model.setCellAlive(Math.floorDiv(point.x, cellWidth), Math.floorDiv(point.y, cellHeight));

    }

  }

  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }


  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    Graphics2D g2D = (Graphics2D) g;

    g2D.setColor(Color.RED);
    g2D.fillRect(0, 0, getWidth(), getHeight());

    cellWidth = Math.floorDiv(getWidth(), model.getColumns());
    cellHeight = Math.floorDiv(getHeight(), model.getRows());

    for (Cell cell : model.getPopulation()) {
      g2D.setColor(Color.WHITE);
      g2D.fillRect(cellWidth * cell.getColumn(), cellHeight * cell.getRow(), cellWidth, cellHeight);
    }


  }


}
