
import controller.GameController;
import java.io.IOException;
import javax.swing.JFrame;
import model.GameLogic;
import view.GamePanel;



public class SnakeGame extends JFrame {
    @SuppressWarnings("CallToPrintStackTrace")
    public SnakeGame() {
        int tileSize = 20;
        int gameWidth = 740;
        int gameHeight = 740;
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new SnakeGame();
    }
}