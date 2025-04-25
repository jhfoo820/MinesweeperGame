import java.util.*;

public class BombSquare extends GameSquare {
    public int bombcount;
    public boolean isClicked = false;
    private boolean thisSquareHasBomb = false;
    public static final int MINE_PROBABILITY = 10;
    private int[] checkX = {-1, 0, 1};
    private int[] checkY = {-1, 0, 1};
    private int boardheight;
    private int boardwidth;

    public BombSquare(int x, int y, GameBoard board) {
        super(x, y, "images/blank.png", board);
        Random r = new Random();
        thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);
        boardheight = (board.getHeight() - 20) / 20;
        boardwidth = (board.getWidth() - 20) / 20;
    }

    public int CountBomb() {
        bombcount = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                BombSquare a = (BombSquare) board.getSquareAt(this.xLocation + i, this.yLocation + j);
                if (a != null && a.thisSquareHasBomb) {
                    bombcount++;
                }
            }
        }
        return bombcount;
    }

    public void setWhiteSpace() {
        for (int dx : checkX) {
            for (int dy : checkY) {
                int newX = this.xLocation + dx;
                int newY = this.yLocation + dy;

                if (reachedBoundary(newX, newY)) continue;

                BombSquare neighbor = (BombSquare) board.getSquareAt(newX, newY);
                if (neighbor != null && !neighbor.isClicked) {
                    neighbor.setImage("images/0.png");
                    neighbor.isClicked = true;
                }
            }
        }
    }

    private boolean reachedBoundary(int x, int y) {
        return x < 0 || x >= boardwidth || y < 0 || y >= boardheight;
    }

    public void clicked() {
        if (isClicked || board.isGameOver()) return;

        if (thisSquareHasBomb) {
            setImage("images/bomb.png");
            isClicked = true;
            board.endGame();  // <-- Trigger game over
        } else if (CountBomb() == 0) {
            setWhiteSpace();
        } else {
            setImage("images/" + CountBomb() + ".png");
            isClicked = true;
        }
    }
}
