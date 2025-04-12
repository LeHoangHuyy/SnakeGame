Nguyên lý hoạt động của game Snake

1.Con rắn (Snake):
    - Được biểu diễn bằng một chuỗi các ô vuông liên kết với nhau.
    - CÓ một đầu và một đuôi.
    - Di chuyển liên tục theo 1 trong 4 hướng: lên, xuống, trái, phải.
    - Khi di chuyển, ô vuông đầu tiên sẽ tiến về phía trước và các ô vuông còn lại sẽ di chuyển theo vị trí ô vuông phía trước nó.  - Khi ăn thức ăn, một ô vuông mới sẽ được thêm vào đuôi của con rắn, làm cho nó dài ơn.

2.Thức ăn (Food):
    - Là một đối tượng xuất hiện ngẫu nhiên trên màn hình.
    - Khi đầu con rắn chạm vào thức ăn, con rắn sẽ ăn nó và dài ra.
    - Sau khi bị ăn, thức ăn sẽ biến mất và một mẩu thức ăn mới sẽ xuất hiện ở vị trí ngẫu nhiên khác.

3.Màn chơi (GamePanel):
    - Là khi vực hình chữ nhật nơi diễn ra trò chơi.
    - Được chia thành các ô vuông nhỏ để dễ dàng quản lý vị trí của con rắn và thức ăn.

4.Điều khiển (GameController):
    - Người chơi điểu khiển hướng di chuyển của đầu con rắn bằng các phím mũi tên hoặc phím WASD.
    - Việc điều khiển chỉ thay đổi hướng di chuyển hiện tại, không thể quay đầu 180 độ quay lập tức được.

5.Kết thúc trò chơi:
    Trò chơi kết thúc khi đầu rắn va chạm vào:
    - Tường (Wall): Nếu con rắn đi ra ngoài ranh giới của màn chơi.
    - Chính bản thân nó (Self): Nếu đầu con rắn chạm vào bất kỳ phần nào của thân nó.
    - Vật cản (Obstacle): Nếu đầu con rắn chạm vào vật cản.

6.Điểm số (Score):
    Điểm số thườn được tăng lên mỗi khi con rắn ăn được thức ăn.