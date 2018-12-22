import java.awt.*;

public class Draw extends Canvas {
    private GameSettings gs = GameSettings.getInstance();

    public void paint(Graphics g) {
        // draw background
        g.setColor(Color.black);
        g.fillRect(32, 32, 15 * 40 + 15, 15 * 40 + 15);
        g.setColor(Color.gray);
        g.fillRect(32, 32, 15 * 40 + 7, 15 * 40 + 7);
        g.setColor(Color.black);

        //draw lines
        for(int i = 0; i < 16; i++) {
            g.drawLine(i * 40 + 40, 40, i * 40 + 40, 16 * 40);
            g.drawLine(40, i * 40 + 40, 16 * 40, i * 40 + 40);
        }

        g.fillOval(40 + 8 * 40 - 6, 40 + 8 * 40 - 6, 12, 12);
        g.fillOval(40 + 4 * 40 - 6, 40 + 4 * 40 - 6, 12, 12);
        g.fillOval(40 + 4 * 40 - 6, 40 + 12 * 40 - 6, 12, 12);
        g.fillOval(40 + 12 * 40 - 6, 40 + 4 * 40 - 6, 12, 12);
        g.fillOval(40 + 12 * 40 - 6, 40 + 12 * 40 - 6, 12, 12);

        //draw chess pieces
        for(int i = 1; i < 15; i++) {
            for(int j = 1; j < 15; j++) {
                drawChessPieces(g, i, j);
            }
        }
    }
    public void drawChessPieces(Graphics g, int i, int j) {
        if (gs.board[i][j] == 1) {
            g.setColor(Color.black);
            g.fillOval(40 * (i + 1) - 19, 40 * (j + 1) - 19, 38, 38 );
        }
        if (gs.board[i][j] == 2) {
            g.setColor(Color.white);
            g.fillOval(40 * (i + 1) - 19, 40 * (j + 1) - 19, 38, 38 );
        }
    }
}
