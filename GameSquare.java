import javax.swing.*;

public abstract class GameSquare extends JButton {
    protected int xLocation;
    protected int yLocation;
    protected GameBoard board;

    public GameSquare(int x, int y, String filename, GameBoard board) {
        super(new ImageIcon(filename));
        this.xLocation = x;
        this.yLocation = y;
        this.board = board;
    }

    public void setImage(String filename) {
        this.setIcon(new ImageIcon(filename));
    }

    public abstract void clicked();
}
