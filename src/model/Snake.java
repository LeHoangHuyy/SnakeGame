package model;

import enums.Direction;
import interfaces.Movable;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake implements Movable {

	private final List<Point> body; // Chứa các đốt của thân rắn
	private Direction direction; // Hướng di chuyển tiếp theo lên xuống trái phải của rắn
	private final int tileSize; // Kích thước ô vuông chứa 1 đốt rắn

	// Khởi tạo giá trị cho con rắn, rắn hướng mặt về phía bên phải
	public Snake(int startX, int startY, int initLength, int tileSize) {
		this.tileSize = tileSize;
		this.body = new ArrayList<>();
		for (int i = 0; i < initLength; i++) {
			body.add(new Point(startX - i * tileSize, startY));
		}
		this.direction = Direction.RIGHT;
	}

	// Trả về thân rắn
	public List<Point> getBody() {
		return body;
	}

	// Trả về hướng di chuyển tiếp theo của rắn
	public Direction getDirection() {
		return direction;
	}

	// Cài đặt lại hướng di chuyển tiếp theo của rắn
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	// Trả về đầu rắn
	public Point getHeadPosition() {
		return body.get(0);
	}

	// Rắn khi di chuyển
	@Override
	public void move() {
		Point head = body.get(0);
		int newX = head.x;
		int newY = head.y;

		switch (direction) {
		case UP -> newY -= tileSize;
		case DOWN -> newY += tileSize;
		case LEFT -> newX -= tileSize;
		case RIGHT -> newX += tileSize;
		}

		// Tịnh tiến các đốt từ thứ 2 trở đi về phía đốt đầu khi di chuyển (đốt đầu là
		// đầu rắn)
		for (int i = body.size() - 1; i > 0; i--) {
			body.set(i, new Point(body.get(i - 1)));
		}
		body.set(0, new Point(newX, newY));
	}

	// Rắn dài ra
	public void grow() {
		body.add(new Point(body.get(body.size() - 1)));
	}

	public void grow(int count) {
		for (int i = 0; i < count; i++) {
			body.add(new Point(body.get(body.size() - 1)));
		}
	}

	// Kiểm tra đã ăn thức ăn chưa
	public boolean isEating(Point foodPosition) {
		return body.get(0).equals(foodPosition);
	}

	public void delete() {
		body.remove(0);
	}

	public int gettitle() {
		return tileSize;
	}

	public Direction getdi() {
		return direction;
	}


	

}
