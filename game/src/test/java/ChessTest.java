import org.junit.Assert;
import org.junit.Test;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessTest {
    private GameSettings gs = new GameSettings();
    private Computer computer = new Computer();
    private Draw draw = new Draw();

    @Test
    public void estimateTest() {
        gs.move(7, 8, gs.WHITE);
        gs.move(6, 8, gs.WHITE);
        gs.move(5, 8, gs.WHITE);
        gs.move(4, 8, gs.WHITE);
        gs.move(3, 8, gs.WHITE);
        Assert.assertEquals(100000, gs.estimate(gs.WHITE));
        gs.unMove(7, 8);
        Assert.assertEquals(10000, gs.estimate(gs.WHITE));
        gs.unMove(3, 8);
        Assert.assertEquals(250, gs.estimate(gs.WHITE));
    }

    @Test
    public void isWinTest() {
        gs.move(7,7, gs.WHITE);
        gs.move(6,6, gs.WHITE);
        gs.move(5,5, gs.WHITE);
        gs.move(4,4, gs.WHITE);
        gs.move(4,5, gs.WHITE);
        gs.move(3,3, gs.WHITE);
        Assert.assertEquals(gs.WHITE, gs.isWin(7,7, gs.WHITE));
        gs.unMove(5,5);
        Assert.assertEquals(0, gs.isWin(7,7, gs.WHITE));
    }

    @Test
    public void playTheChessTest() {
        gs.move(8,8, gs.BLACK);
        draw.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                gs.move(7,8, gs.WHITE);
                int[] result = computer.nextStep(gs.BLACK);
                // computer next step is (7, 9)
                Assert.assertEquals(7, result[0]);
                Assert.assertEquals(9, result[1]);
                gs.move(6,8, gs.WHITE);
                result = computer.nextStep(gs.BLACK);
                // computer next step is (9, 7)
                Assert.assertEquals(9, result[0]);
                Assert.assertEquals(7, result[1]);
            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });

    }
}
