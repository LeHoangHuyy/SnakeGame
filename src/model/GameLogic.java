
package model;

import enums.Direction;
import interfaces.CollisionChecker;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Ex.ObstacleEX;
import Ex.SelfEX;
import Ex.WallEX;

public class GameLogic {

    private static final Connection DriverManager = null;
	private final int gameWidth;            // Chiều ngang màn hình game
    private final int gameHeight;           // Chiều cao màn hình game
    private final int tileSize;             // Kích cỡ ô vuông chứa 1 đốt rắn
    private int score;                      // Điểm
    private boolean isRunning;              // Kiểm tra di chuyển 
    private Snake snake;
    private final int snakeLength = 3;      // Khởi tạo chiều dài rắn ban đầu
    private Food normalFood;
    private SpecialFood specialFood;
    private int foodCounter;
    private final int specialFoodInterval = 5;
    private List<CollisionChecker> collisionChecker;      // Kiểm tra va chạm gồm bản thân, tường,...
    private List<Point> obstacles; //map
    private String linkmap;
    
    // Khởi tạo giá trị ban đầu
    public GameLogic(int gameWidth, int gameHeight, int tileSize,String linkmap) throws IOException {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.tileSize = tileSize;
        this.linkmap=linkmap;
        initGame(linkmap);
    }

    // Khởi tạo game
    public final void initGame(String linkmap) throws IOException {
        collisionChecker = new ArrayList<>();
        initialObstracles(linkmap);
        collisionChecker.add(new WallCollisionChecker());      // Va chạm với tường
        collisionChecker.add(new SelfCollisionChecker());      // Va cạm với bản thân
        collisionChecker.add(new ObstacleCollisionChecker(obstacles));      // Va chạm vật cản

        int startX = gameWidth / 2 / tileSize * tileSize;
        int startY = gameHeight / 2 / tileSize * tileSize;
        snake = new Snake(startX, startY, snakeLength, tileSize);       // Khởi tạo rắn ở giữa màn hình

        normalFood = new Food(gameWidth, gameHeight, tileSize);
        normalFood.createRandomPosition(snake.getBody(), obstacles);

        specialFood = new SpecialFood(gameWidth, gameHeight, tileSize);
        specialFood.getPosition().setLocation(-1, -1);          // Đặt thức ăn đặc biệt ở ngoài map

        score = 0;
        isRunning = true;
    }

    // Tạo vật cả từ file
    public void initialObstracles(String linkmap) throws IOException {
        obstacles = new ArrayList<>();
        File file = new File(linkmap);     // Đường dẫn tương đối
        //System.out.println(System.getProperty("user.dir"));     // Kiểm tra mày đang ở vị trí nào trên cấu trúc thư mục

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int row = 0;

            while ((line = br.readLine()) != null) {
                String[] elements = line.split("\\s+");

                for (int col = 0; col < elements.length; col++) {
                    if (elements[col].equals("1")) {
                        obstacles.add(new Point(col * tileSize, row * tileSize));
                    }
                }
                row++;
            }
        }
        
    }

    // Cập nhật con răn khi di chuyển
    public void update() throws SelfEX, WallEX,ObstacleEX {
        if (!isRunning) {
            return;
        }

        snake.move();
        try
        {
        	for (CollisionChecker checker : collisionChecker)
            checker.checkCollision(snake, gameWidth, gameHeight);
        }
        finally {
			
		}
        if (snake.isEating(normalFood.getPosition())) {     // Nếu rắn ăn phải thức ăn thường
            normalFood.onEat(snake, this);          // Rắn ăn thức ăn
            foodCounter++;                      // Tăng biến đếm nếu rắn không ăn thức ăn đặc biệt

            if (foodCounter % specialFoodInterval == 0) {        // Đến 1 mức điểm nào đó thì xuất hiện thức ăn đặc biệt
                specialFood.createRandomPosition(snake.getBody(), obstacles);
                normalFood.getPosition().setLocation(-1, -1);

            } else {
                specialFood.getPosition().setLocation(-1, -1);// Không đến mức điểm nào đó thì thức ăn xuất hiện ở ngoài map
                normalFood.createRandomPosition(snake.getBody(), obstacles);
                
            }
        } else if (snake.isEating(specialFood.getPosition())) {      // Nếu ăn ăn thức ăn đặc biệt
            specialFood.onEat(snake, this);     // Rắn ăn thức ăn
            foodCounter = 0;                // Rest biến đếm về 0
            normalFood.createRandomPosition(snake.getBody(), obstacles);  // Xuất hiện thức ăn thường sau khi ăn food đặc biệt
            specialFood.getPosition().setLocation(-1, -1);     // Food đặc biệt xuất hiện ở ngoài map
        }
        if(score<0 || snake.getBody().isEmpty())
        {
        	isRunning=false;
        	return;
        }
    }
    // Thay đổi hướng di chuyển
    public void changeDirection(Direction newDirection) {
        Direction currDirection = snake.getDirection();
        if ((newDirection == Direction.UP && currDirection != Direction.DOWN)
                || (newDirection == Direction.DOWN && currDirection != Direction.UP)
                || (newDirection == Direction.LEFT && currDirection != Direction.RIGHT)
                || (newDirection == Direction.RIGHT && currDirection != Direction.LEFT)) {
            snake.setDirection(newDirection);
        }
    }

    // Trả về điểm
    public int getScore() {
        return score;
    }

    // Kiểm tra có đang di chuyển không
    public boolean isRunning() {
        return isRunning;
    }

    // Dừng game
    public void stopGame() {
        isRunning = false;
    }

    // Trả về chiều ngang game
    public int getGameWidth() {
        return gameWidth;
    }

    // Trả về chiều cao game
    public int getGameHeight() {
        return gameHeight;
    }

    // Trả về kích cỡ ô vuông chứa 1 đốt rắn
    public int getTileSize() {
        return tileSize;
    }

    // Trả về con rắn
    public List<Point> getSnakeBody() {
        return snake.getBody();
    }

    // Tăng 1 điểm
    public void increaseScore() {
        score++;
    }
    public void increaseScore(int diem) {
        score+=diem;
    }
    // Tăng pointValue điểm
    public void increaseScoreRed() {
        score *=2;
    }

    // Trả về vị trí thức ăn
    public Point getFoodPosition(){
        if (foodCounter % specialFoodInterval == 0 && foodCounter != 0){
            return specialFood.getPosition();
        } 
        
        return normalFood.getPosition();
    }
    
    // Trả về số điểm đã đếm
    public int getFoodCounter() {
        return foodCounter;
    }

    // Trả về điểm đặc biệt để xuất hiện thức ăn đặc biệt
    public int getSpecialFoodInterval() {
        return specialFoodInterval;
    }

    // Trả về list vị trí vật cản
    public List<Point> getObstacles() {
        return obstacles;
    }

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	public boolean getRunning()
	{
		return isRunning;
	}
	//quay dau
	public void quaydau() {
		switch (snake.getDirection()) {
		case UP -> doichieu(Direction.RIGHT,Direction.LEFT);
		case DOWN -> doichieu(Direction.RIGHT,Direction.LEFT);
		case LEFT -> doichieu(Direction.UP,Direction.DOWN);
		case RIGHT -> doichieu(Direction.UP,Direction.DOWN);
		}
	}
	public void doichieu(Direction a, Direction b)
	{
    Point head = snake.getHeadPosition();
      //Point headnext;
      int newX = (int) head.getX();
      int newY = (int) head.getY();
      switch (a) {
      case UP ->
          newY -= snake.gettitle();
      case DOWN ->
          newY += snake.gettitle();
      case LEFT ->
          newX -= snake.gettitle();
      case RIGHT ->
          newX += snake.gettitle();
      }
      Point headnext = new Point(newX, newY);
      
      for (Point obstacle : obstacles) {
          if (headnext.equals(obstacle)) {
              snake.setDirection(b);
              return;
          }
      }
      if(snake.getBody().size()>3)
      {
      	
          List<Point> body = snake.getBody();
          for (int i = 1; i < body.size(); i++) {
              if (headnext.equals(body.get(i))) {
            	  snake.setDirection(b);
                  return;
                  
              }
          }
      }
      
      snake.setDirection(a);
		//move();
	}
	public void resetGame() throws IOException {
        snake.getBody().clear();
        initGame(linkmap);
    }
	public final String Url="jdbc:mysql://localhost/snake";
	public final String user="root";
	public final String password="";
    public void saveData() throws SQLException
    {
//    	final String DRIVE_CLASS ="com.mysql.cj.jdbc.Driver";

    	Connection conn=null;
    	Statement stmt= null;
    	try
    	{
    		conn = ((java.sql.DriverManager) DriverManager).getConnection(Url,user,password);
    		//conn = getConnect("localhost", "snake", user, password);
    		stmt=conn.createStatement();
    		String sql ="insert into food values('dfsg',20,740,740)";
    		int x=stmt.executeUpdate(sql);
    		if(x>=1)
    		{
    			System.out.println("yes");
    		}
    	}
    	finally {
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
		}
    	
    }
    public void loadGameData() throws SQLException
    {
    	Connection conn=null;
    	Statement stmt= null;
    	try
    	{
    		conn = ((java.sql.DriverManager) DriverManager).getConnection(Url,user,password);
    		//conn = getConnect("localhost", "snake", user, password);
    		stmt=conn.createStatement();
    		String sql ="select * from food";
    		
    	}
    	finally {
    		if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
			
		}
    }
//    public Connection getConnect(String sever,String data,String user,String password) throws SQLException
//    {
//    	Connection conn = null;
//    	String Url="jdbc:mysql://"+sever+"/"+data; 
//    	Properties pro = new Properties();
//    	pro.put("user", user);
//    	pro.put("password", password);
//    	try
//    	{
//    		com.mysql.cj.jdbc.Driver driver= new com.mysql.cj.jdbc.Driver();
//    		conn = driver.connect(Url, pro);
//    		
//    	}
//    	finally {
//			
//		}
//		
//    	return conn;
//    }
    public Food getFood()
    {
    	return normalFood;
    }
}
