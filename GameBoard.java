import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JFrame implements ActionListener {
    private JPanel boardPanel = new JPanel();
    private int boardHeight;
    private int boardWidth;
    private GameSquare[][] board;
    private boolean gameOver = false;  // <-- NEW

    public GameBoard(String title, int width, int height) {
        super();

        this.boardWidth = width;
        this.boardHeight = height;
        this.board = new GameSquare[width][height];

        setTitle(title);
        setSize(20 + width * 20, 20 + height * 20);
        setContentPane(boardPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel.setLayout(new GridLayout(height, width));

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[x][y] = new BombSquare(x, y, this);
                board[x][y].addActionListener(this);
                boardPanel.add(board[x][y]);
            }
        }

        setVisible(true);
    }

    public GameSquare getSquareAt(int x, int y) {
        if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight) {
            return null;
        }
        return board[x][y];
    }

public void endGame() {
    gameOver = true;

    // Set custom font for JOptionPane
    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 16)));
    UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial", Font.PLAIN, 14)));

    // Use a bomb icon for the popup (make sure bomb_icon.png exists)
    ImageIcon icon = new ImageIcon("images/bomb_icon.png");

    JOptionPane.showMessageDialog(
        this,
        "<html><div style='text-align: center;'><b>💥 Boom!</b><br>You clicked a bomb.<br><br><b>Game Over!</b></div></html>",
        "Game Over",
        JOptionPane.INFORMATION_MESSAGE,
        icon
    );
}

    public boolean isGameOver() {
        return gameOver;
    }

    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        GameSquare square = (GameSquare) e.getSource();
        square.clicked();
    }
}
