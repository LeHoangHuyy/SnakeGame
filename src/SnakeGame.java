
import controller.GameController;
import javax.swing.JFrame;
import model.GameLogic;
import view.GamePanel;



public class SnakeGame extends JFrame {
    public SnakeGame() {
        int tileSize = 20;
        int gameWidth = 750;
        int gameHeight = 750;
        GameLogic logic = new GameLogic(gameWidth, gameHeight, tileSize);
        GamePanel view = new GamePanel(logic);
        @SuppressWarnings("unused")             // Đây là annotation để bỏ qua cảnh báo biến có thể không được sử dụng đến 
        GameController controller = new GameController(logic, view, 150);

        add(view);
        pack();
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args){
        new SnakeGame();
    }
}