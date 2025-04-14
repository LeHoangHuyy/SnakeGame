package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.GameLogic;

public class GamePanel extends JPanel {

    private final GameLogic logic;

    public GamePanel(GameLogic logic) {
        this.logic = logic;
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        setPreferredSize(new Dimension(logic.getGameWidth(), logic.getGameHeight()));
        setBackground(Color.white);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (logic.isRunning()) {
            // Vẽ thức ăn
            g.setColor(Color.red);
            Point food = logic.getFoodPosition();
            g.fillOval(food.x, food.y, logic.getTileSize(), logic.getTileSize());

            // Vẽ rắn
            List<Point> snakeBody = logic.getSnakeBody();
            for (int i = 0; i < snakeBody.size(); i++) {
                g.setColor(i == 0 ? Color.green : new Color(45, 180, 0));
                Point part = snakeBody.get(i);
                g.fillRect(part.x, part.y, logic.getTileSize(), logic.getTileSize());
            }

//            // Vẽ điểm số
//            g.setColor(Color.WHITE);
//            g.setFont(new Font("Ink Free", Font.BOLD, 40));
//            FontMetrics metrics = getFontMetrics(g.getFont());
//            g.drawString("Score: " + logic.getScore(),
//                    (logic.getGameWidth() - metrics.stringWidth("Score: " + logic.getScore())) / 2,
//                    g.getFont().getSize());
//            //
        }
//        } else {
//            gameOver(g);
//        }
    }

    private void gameOver(Graphics g) {
        // Thông báo Game Over
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over",
                (logic.getGameWidth() - metrics1.stringWidth("Game Over")) / 2,
                logic.getGameHeight() / 2);

        // Thông báo điểm số
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Ink Free", Font.BOLD, 40));
//        FontMetrics metrics2 = getFontMetrics(g.getFont());
//        g.drawString("Score: " + logic.getScore(),
//                (logic.getGameWidth() - metrics2.stringWidth("Score: " + logic.getScore())) / 2,
//                g.getFont().getSize() * 2);
    }
}
