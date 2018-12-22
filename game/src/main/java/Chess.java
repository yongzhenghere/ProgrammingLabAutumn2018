import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Chess {
    private GameSettings gs = GameSettings.getInstance();
    private Frame frame = new Frame("Gomoku");
    private Draw draw = new Draw();
    private Computer computer = Computer.getComputer();

    public void play(){
        gs.move(15 / 2 + 1, 15 / 2 + 1, gs.BLACK);

        draw.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int i = (x - 60) / 40 + 1;
                int j = (y - 60) / 40 + 1;
                if(gs.isEmpty(i, j)) {
                    gs.move(i, j, gs.WHITE);
                    if(gs.isWin(i, j, gs.WHITE) != 0) {
                        JOptionPane.showMessageDialog(frame, "Game over, player win!",
                                "News", JOptionPane.INFORMATION_MESSAGE );
                        gs.restart();
                    }
                    draw.repaint();
                    int comp[] = computer.nextStep(gs.BLACK);
                    gs.move(comp[0], comp[1], gs.BLACK);
                    if(gs.isWin(comp[0], comp[1], gs.BLACK) != 0) {
                        JOptionPane.showMessageDialog(frame, "Game over, computer win!",
                                "News", JOptionPane.INFORMATION_MESSAGE);
                        gs.restart();
                    }
                    draw.repaint();
                }
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

        frame.add(draw);
        frame.setSize(750, 710);
        frame.setVisible(true);
    }
}

