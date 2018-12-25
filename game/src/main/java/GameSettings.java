
public class GameSettings {
    public final int EMPTY = 0;
    public final int BLACK = 1;
    public final int WHITE = 2;
    private Draw draw = new Draw();

    public int[][] board = new int[15][15];

    private static final GameSettings gs = new GameSettings();
    public static GameSettings getInstance() {
        return gs;
    }

    public boolean isEmpty(int x, int y) {
        return board[x][y] == EMPTY;
    }

    public void move(int x, int y, int color) {
        board[x][y] = color;
    }

    public void unMove(int x, int y) {
        board[x][y] = EMPTY;
    }

    // Valuation function, assigning values to different locations
    public int estimate(int color) {
        int changeX[] = {1, 0, 1, 1};
        int changeY[] = {0, 1, 1, -1};
        int digit = 0;

        for(int x = 1; x < 15; x++) {
            for (int y = 1; y < 15; y++) {
                if (board[x][y] != color)
                    continue;

                int num[][] = new int[2][10];

                for (int i = 0; i < 4; i++) {
                    int count = 1;
                    int flag1 = 0, flag2 = 0;
                    int tempX = x + changeX[i];
                    int tempY = y + changeY[i];
                    while (tempX > 0 && tempX < 15 && tempY > 0 && tempY < 15 && board[tempX][tempY] == color) {
                        tempX += changeX[i];
                        tempY += changeY[i];
                        count++;
                    }

                    if(tempX > 0 && tempX < 15 && tempY > 0 && tempY < 15 && board[tempX][tempY] == EMPTY)
                        flag1 = 1;

                    tempX = x - changeX[i];
                    tempY = y - changeY[i];
                    while (tempX > 0 && tempX < 15  && tempY > 0 && tempY < 15 && board[tempX][tempY] == color) {
                        tempX -= changeX[i];
                        tempY -= changeY[i];
                        count++;
                    }

                    if(tempX > 0 && tempX < 15 && tempY > 0 && tempY < 15 && board[tempX][tempY] == EMPTY)
                        flag2 = 1;

                    /* num[i][j], if i == 1, both sides of the piece are empty, i == 0, one side of the piece is empty
                     j equals how many the same color pieces connect together */
                    if(flag1 + flag2 > 0) num[flag1 + flag2 - 1][count]++;
                }

               // if (count)
                /* 0 means this position is empty, 1 means there are pieces in this position in the annotation./
                 They represent chessboards in different situations */
                // 011111, 111110, 11111
                if(num[0][5] + num[1][5] > 0)
                    digit = Math.max(digit, 100000);
                // 011110 || 01111(11110) * 2 || 01111(11110) + 01110
                else if(num[1][4] > 0 || num[0][4] > 1 || (num[0][4] > 0 && num[1][3] > 0))
                    digit = Math.max(digit, 10000);
                // 01110 * 2
                else if(num[1][3] > 1)
                    digit = Math.max(digit, 5000);
                // 0111(1110) + 01110
                else if(num[1][3] > 0 && num[0][3] > 0)
                    digit = Math.max(digit, 1000);
                // 01111(11110)
                else if(num[0][4] > 0)
                    digit = Math.max(digit, 500);
                // 01110
                else if(num[1][3] > 0)
                    digit = Math.max(digit, 250);
                // 0110 * 2
                else if(num[1][2] > 1)
                    digit = Math.max(digit, 100);
                // 0111(1110)
                else if(num[0][3] > 0)
                    digit = Math.max(digit, 50);
                // 0110
                else if(num[1][2] > 0)
                    digit = Math.max(digit, 5);
                // 011(110)
                else if(num[0][2] > 0)
                    digit = Math.max(digit, 1);
            }
        }
        return digit;
    }

    public int isWin(int x, int y, int color) {
        int changeX[] = {1, 0, 1, 1};
        int changeY[] = {0, 1, 1, -1};

        for (int i = 0; i < 4; i++) {
            int count = 1;
            int tempX = x + changeX[i];
            int tempY = y + changeY[i];
            while (tempX > 0 && tempX < 15 && tempY > 0 && tempY < 15 && board[tempX][tempY] == color) {
                tempX += changeX[i];
                tempY += changeY[i];
                count++;
            }
            tempX = x - changeX[i];
            tempY = y - changeY[i];
            while (tempX > 0 && tempX < 15 && tempY > 0 && tempY < 15 && board[tempX][tempY] == color) {
                tempX -= changeX[i];
                tempY -= changeY[i];
                count++;
            }
            if(count >= 5) {
                return color;
            }
        }
        return 0;
    }

    public void restart() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = 0;
            }
        }
        draw.repaint();
        gs.move(15 / 2 + 1, 15 / 2 + 1, gs.BLACK);
    }
}
