
public class Computer {
    private static GameSettings gs = GameSettings.getInstance();
    private static int depth = 1;
    private static int compColor = gs.BLACK;
    private static Computer computer = new Computer();

    public static Computer getComputer() {
        return computer;
    }

    public int[] nextStep(int color) {
        int array[] = new int[2];
        int digit = Integer.MIN_VALUE;

        for (int x = 1; x < 15; x++) {
            for (int y = 1; y < 15; y++) {
                if (!gs.isEmpty(x, y)) continue;
                gs.move(x, y, color); // if computer at empty place board[x][y]
                int val = -alpha_beta(0, Integer.MIN_VALUE, Integer.MAX_VALUE, color % 2 + 1, x, y);
                if (val >= digit) {
                    digit = val;
                    array[0] = x;
                    array[1] = y;
                }
                gs.unMove(x, y);
            }
        }
        return array;
    }

    public int alpha_beta(int depth, int alpha, int beta, int color, int preX, int preY) {
        if (depth >= computer.depth || gs.isWin(preX, preY, color % 2 + 1) != 0) {
            int digit = gs.estimate(compColor) - gs.estimate(gs.WHITE); // computer gets score +, player gets score -
            if (depth % 2 == 0) digit = -digit;
            return digit;
        }

        for (int x = 1; x < 15; x++) {
            for (int y = 1; y < 15; y++) {
                if (!gs.isEmpty(x, y)) continue;
                gs.move(x, y, color); // if then player at other empty place
                int val = -alpha_beta(depth + 1, -beta, -alpha, color % 2 + 1, x, y);
                gs.unMove(x, y);
                if (val >= beta) return beta;
                if (val > alpha) alpha = val;
            }
        }
        return alpha;
    }
}
