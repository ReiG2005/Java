import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tic_Tac_Toe {
    private static boolean xTurn = true;
    private static JButton[] buttons = new JButton[9];
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3));
        Font gameFont = new Font("Arial", Font.BOLD, 40);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(gameFont);
            frame.add(buttons[i]); 

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    if (clickedButton.getText().equals("")) {
                        if (xTurn) {
                            clickedButton.setText("X");
                        } else {
                            clickedButton.setText("O");
                        }
                        xTurn = !xTurn;
                        checkWinner();
                    }
                }
            });
        }
        frame.setVisible(true);
    }
    
    private static void checkWinner() {
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, 
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
                {0, 4, 8}, {2, 4, 6}
        };
        for (int[] condition : winConditions) {
            String b1 = buttons[condition[0]].getText();
            String b2 = buttons[condition[1]].getText();
            String b3 = buttons[condition[2]].getText();
            if (!b1.equals("") && b1.equals(b2) && b2.equals(b3)) {
                JOptionPane.showMessageDialog(null, "Player " + b1 + " wins!");
                resetGame();
                return;
            }
        }
        boolean tie = true;
        for (JButton b : buttons) {
            if (b.getText().equals("")) {
                tie = false;
                break;
            }
        }
        if (tie) {
            JOptionPane.showMessageDialog(null, "It's a tie!");
            resetGame();
        }
    }
    private static void resetGame() {
        for (JButton b : buttons) {
            b.setText("");
        }
        xTurn = true;
    }
}
