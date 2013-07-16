
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

/**
 * A basic panel that displays a small up or down arrow.
 * 
 * @author David Gilbert
 */
public class ArrowPanel extends JPanel {

  /** A constant for the up arrow. */
  public static final int UP = 0;

  /** A constant for the down arrow. */
  public static final int DOWN = 1;

  /** The arrow type. */
  private int type = UP;

  /** The available area. */
  private Rectangle2D available = new Rectangle2D.Float();

  /**
   * Creates a new arrow panel.
   * 
   * @param type
   *          the arrow type.
   */
  public ArrowPanel(final int type) {
    this.type = type;
    setPreferredSize(new Dimension(14, 9));
  }

  /**
   * Paints the arrow panel.
   * 
   * @param g
   *          the graphics device for drawing on.
   */
  public void paintComponent(final Graphics g) {

    super.paintComponent(g);
    final Graphics2D g2 = (Graphics2D) g;

    // first determine the size of the drawing area...
    final Dimension size = getSize();
    final Insets insets = getInsets();
    this.available.setRect(insets.left, insets.top, size.getWidth() - insets.left - insets.right,
        size.getHeight() - insets.top - insets.bottom);
    g2.translate(insets.left, insets.top);
    g2.fill(getArrow(this.type));

  }

  /**
   * Returns a shape for the arrow.
   * 
   * @param t
   *          the arrow type.
   * 
   * @return the arrow shape.
   */
  private Shape getArrow(final int t) {
    switch (t) {
    case UP:
      return getUpArrow();
    case DOWN:
      return getDownArrow();
    default:
      return getUpArrow();
    }
  }

  /**
   * Returns an up arrow.
   * 
   * @return an up arrow.
   */
  private Shape getUpArrow() {
    final Polygon result = new Polygon();
    result.addPoint(7, 2);
    result.addPoint(2, 7);
    result.addPoint(12, 7);
    return result;
  }

  /**
   * Returns a down arrow.
   * 
   * @return a down arrow.
   */
  private Shape getDownArrow() {
    final Polygon result = new Polygon();
    result.addPoint(7, 7);
    result.addPoint(2, 2);
    result.addPoint(12, 2);
    return result;
  }

}

