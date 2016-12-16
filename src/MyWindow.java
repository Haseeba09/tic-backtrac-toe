
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


class MyWindow extends JFrame {
    
    private JTextField message;
    private JButton b11, b12, b13, b21, b22, b23,b31,b32,b33;
    
    private static int buttonPressCount, winCount = 0;
       
    Font buttonFont = new Font("Courier New", Font.BOLD, 56);
    Font messageFont = new Font("Courier New", Font.BOLD, 18);
    
    private  JButton[] buttons = new JButton[9];
        
    public MyWindow() {
        message = new JTextField("Play Tic Tac Toe: O's Turn!");
        
        Container contentPane = getContentPane();
        contentPane.add(message, "North");
        
         JPanel panel = new JPanel(); 
        panel.setLayout(new GridLayout(3, 3)); 
        contentPane.add(panel, "Center");
        
        buttons[0] = b11 = new JButton(" ");
        buttons[1] = b12 = new JButton(" ");
        buttons[2] = b13 = new JButton(" ");
        buttons[3] = b21 = new JButton(" ");
        buttons[4] = b22 = new JButton(" ");
        buttons[5] = b23 = new JButton(" ");
        buttons[6] = b31 = new JButton(" ");
        buttons[7] = b32 = new JButton(" ");
        buttons[8] = b33 = new JButton(" ");
                
        for(int i=0; i<9; i++){
            buttons[i].setFont(buttonFont);
        }
        
        message.setFont(messageFont);
        
        ButtonObserver observer = new ButtonObserver();

        for(int i=0; i<9; i++){
            buttons[i].addActionListener(observer);
        }
        
        for(int i=0; i<9; i++){
            panel.add(buttons[i]);
        }       
    }

class ButtonObserver implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
               
            for(int i=0; i<9; i++){
                sourceCheck(source, buttons[i]);
            }                       
        }

        private void sourceCheck(Object source, JButton buttonClicked){
            if (source==buttonClicked){
                
                buttonClicked.setText("O");
                message.setText("X's Turn!");
                       
              
                buttonClicked.setEnabled(false);
                
                buttonPressCount++;
               
                computerPlay();
                runAllGames();
                
                 if(winCount == 0 && buttonPressCount == 9){
                         message.setText("Game Over: DRAW - No winner!!!");
                  }  
            }         
        }
        
        private void computerPlay(){
            for (int i=0; i<9; i++){
                if (buttons[i].isEnabled()){
                    buttons[i].setText("X");
                    buttons[i].setEnabled(false);
                    buttonPressCount++;
                    break;
                }
            }
        }
        
        private void runAllGames(){
            gameCheck(b11, b12, b13);
            gameCheck(b21, b22, b23);
            gameCheck(b31, b32, b33);
            gameCheck(b11, b21, b31);
            gameCheck(b12, b22, b32);
            gameCheck(b13, b23, b33);
            gameCheck(b11, b22, b33);
            gameCheck(b13, b22, b31);
        }
        
        private void gameCheck(JButton button1, JButton button2, JButton button3) {
          if(button1.getText().equals(button2.getText()) && button2.getText().equals(button3.getText()) && (!" ".equals(button1.getText()))){
                           
                message.setText("Game Over: Winner is " + button1.getText() + "!");
                
                for(int i=0; i<9; i++){
                    buttons[i].setEnabled(false);
                }
                
                winCount++;
            } 
        }   
    }    
}