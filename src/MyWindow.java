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
    private PotentialBoard gameBoard;
    
    private String lastPlayed;
    
    private static int buttonPressCount, winCount = 0;
       
    Font buttonFont = new Font("Courier New", Font.BOLD, 56);
    Font messageFont = new Font("Courier New", Font.BOLD, 18);
    private JButton newGameButton = new JButton();
    private  JButton[] buttons = new JButton[9];
    private BoardPosition[] boardPositions = new BoardPosition[9];
        
    public MyWindow() {
        message = new JTextField("Play Tic Tac Toe: O's Turn!");
        
        gameBoard = new PotentialBoard();
        
        Container contentPane = getContentPane();
        contentPane.add(message, "North");
        
         JPanel panel = new JPanel(); 
        panel.setLayout(new GridLayout(3, 3)); 
        contentPane.add(panel, "Center");
        
        JPanel panel2 = new JPanel();
        contentPane.add(panel2, "South");
        
        /*--------------------#
        |     0  |  1  |  2   |
        |     -------------   |
        |     3  |  4  |  5   |
        |     -------------   |
        |     6  |  7  |  8   |
        |                     |
        |    Board Layout for |
        |    Reference.       |
        #--------------------*/
        
        buttons[0] = new JButton(" ");
        buttons[1] = new JButton(" ");
        buttons[2] = new JButton(" ");
        buttons[3] = new JButton(" ");
        buttons[4] = new JButton(" ");
        buttons[5] = new JButton(" ");
        buttons[6] = new JButton(" ");
        buttons[7] = new JButton(" ");
        buttons[8] = new JButton(" ");
        
        boardPositions[0] = new BoardPosition(" ",11);
        boardPositions[1] = new BoardPosition(" ",12);
        boardPositions[2] = new BoardPosition(" ",13);
        boardPositions[3] = new BoardPosition(" ",21);
        boardPositions[4] = new BoardPosition(" ",22);
        boardPositions[5] = new BoardPosition(" ",23);
        boardPositions[6] = new BoardPosition(" ",31);
        boardPositions[7] = new BoardPosition(" ",32);
        boardPositions[8] = new BoardPosition(" ",33);
                
        for(int i=0; i<9; i++){
            buttons[i].setFont(buttonFont);
            //buttons[i].setText("_");
        }
        
        message.setFont(messageFont);
        
        ButtonObserver observer = new ButtonObserver();

        for(int i=0; i<9; i++){
            buttons[i].addActionListener(observer);
        }
        
        newGameButton.setText("New Game");
        newGameButton.addActionListener(observer);
        panel2.add(newGameButton);
        
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
            
            if(source == newGameButton){
                System.out.println("HEY");
                buttonPressCount = 0;
                winCount = 0;
                for(int i=0; i<9; i++){
                    buttons[i].setEnabled(true);
                    buttons[i].setText(" ");
                    boardPositions[i].setPlayedChar(" ");
                    gameBoard.setBoardPiece(boardPositions[i]);
                }
                lastPlayed = " ";
                message.setText("Play Tic Tac Toe: O's Turn!");
            }
        }

        private void sourceCheck(Object source, JButton buttonClicked){
            if (source==buttonClicked){
                
                buttonClicked.setText("O");
                lastPlayed = "O";
                message.setText("X's Turn!");
                     
              
                buttonClicked.setEnabled(false);
                
                //int index;
                
                for(int i=0; i<9; i++){
                    boardPositions[i].setPlayedChar(buttons[i].getText());
                }
                
                buttonPressCount++;
               
                
                //runAllGames();
                for(int i=0; i<9; i++){
                    
                    gameBoard.setBoardPiece(boardPositions[i]);
                    
                }
                gameCheck(gameBoard);
                computerPlay();
                
                
                 if(winCount == 0 && buttonPressCount == 9){
                         message.setText("Game Over: DRAW - No winner!!!");
                  }  
            }         
        }
        
        private void computerPlay(){
                    PotentialBoard pb = new PotentialBoard();
                    pb = gameBoard;
                    
                    if(buttonPressCount == 1){
                        if(!(buttons[0].isEnabled() 
                               && buttons[2].isEnabled()
                               && buttons[6].isEnabled()
                               && buttons[8].isEnabled())){
                            buttons[4].setText("X");
                               lastPlayed = "X";
                               System.out.println(4);
                               buttons[4].setEnabled(false);
                               for (int i2 = 0; i2 < 9; i2++) {
                                   boardPositions[i2].setPlayedChar(buttons[i2].getText());
                                   gameBoard.setBoardPiece(boardPositions[4]);
                               }
                               buttonPressCount++;

                               gameCheck(gameBoard);
                               return;
                            
                        }
                        else if(!(buttons[5].isEnabled())){
                            buttons[8].setText("X");
                               lastPlayed = "X";
                               System.out.println(4);
                               buttons[8].setEnabled(false);
                               for (int i2 = 0; i2 < 9; i2++) {
                                   boardPositions[i2].setPlayedChar(buttons[i2].getText());
                                   gameBoard.setBoardPiece(boardPositions[8]);
                               }
                               buttonPressCount++;

                               gameCheck(gameBoard);
                               return;
                        }
                        else if(!(buttons[7].isEnabled())){
                            buttons[8].setText("X");
                               lastPlayed = "X";
                               System.out.println(4);
                               buttons[8].setEnabled(false);
                               for (int i2 = 0; i2 < 9; i2++) {
                                   boardPositions[i2].setPlayedChar(buttons[i2].getText());
                                   gameBoard.setBoardPiece(boardPositions[8]);
                               }
                               buttonPressCount++;

                               gameCheck(gameBoard);
                               return;
                        }
                    }
                    if(buttonPressCount == 3){
                        if(!(buttons[2].isEnabled()) 
                               && !(buttons[6].isEnabled())){
                                    if(buttons[7].isEnabled()){
                                        buttons[7].setText("X");
                                        lastPlayed = "X";
                                        System.out.println(4);
                                        buttons[7].setEnabled(false);
                                        for (int i2 = 0; i2 < 9; i2++) {
                                            boardPositions[i2].setPlayedChar(buttons[i2].getText());
                                            gameBoard.setBoardPiece(boardPositions[7]);
                                        }
                                        buttonPressCount++;

                                        gameCheck(gameBoard);
                                        return;
                                    }
                        }
                        else if (!(buttons[4].isEnabled())
                                && !(buttons[8].isEnabled())){
                            if(buttons[6].isEnabled()){
                                        buttons[6].setText("X");
                                        lastPlayed = "X";
                                        System.out.println(4);
                                        buttons[6].setEnabled(false);
                                        for (int i2 = 0; i2 < 9; i2++) {
                                            boardPositions[i2].setPlayedChar(buttons[i2].getText());
                                            gameBoard.setBoardPiece(boardPositions[6]);
                                        }
                                        buttonPressCount++;

                                        gameCheck(gameBoard);
                                        return;
                                    }
                        }
                        else if (!(buttons[2].isEnabled()) &&
                                !(buttons[5].isEnabled())){
                            if(buttons[7].isEnabled()){
                                        buttons[7].setText("X");
                                        lastPlayed = "X";
                                        System.out.println(4);
                                        buttons[7].setEnabled(false);
                                        for (int i2 = 0; i2 < 9; i2++) {
                                            boardPositions[i2].setPlayedChar(buttons[i2].getText());
                                            gameBoard.setBoardPiece(boardPositions[7]);
                                        }
                                        buttonPressCount++;

                                        gameCheck(gameBoard);
                                        return;
                                    }
                        }
                        else if (!(buttons[7].isEnabled()) &&
                                !(buttons[6].isEnabled())){
                            if(buttons[3].isEnabled()){
                                        buttons[3].setText("X");
                                        lastPlayed = "X";
                                        System.out.println(4);
                                        buttons[3].setEnabled(false);
                                        for (int i2 = 0; i2 < 9; i2++) {
                                            boardPositions[i2].setPlayedChar(buttons[i2].getText());
                                            gameBoard.setBoardPiece(boardPositions[3]);
                                        }
                                        buttonPressCount++;

                                        gameCheck(gameBoard);
                                        return;
                                    }
                        }
                    }
                    
                    for(int i = 0; i<9; i++){
                       
                        if(buttons[i].isEnabled()){
                            pb.setCharAt(i, "X");
                        
                           if (pb.runWinScenarios()) {
                               buttons[i].setText("X");
                               lastPlayed = "X";
                               System.out.println(i);
                               buttons[i].setEnabled(false);
                               for (int i2 = 0; i2 < 9; i2++) {
                                   boardPositions[i2].setPlayedChar(buttons[i2].getText());
                                   gameBoard.setBoardPiece(boardPositions[i]);
                               }
                               buttonPressCount++;

                               gameCheck(gameBoard);
                               return;
                            }
                           
                           pb.setCharAt(i, "O");
                        
                           if (pb.runWinScenarios()) {
                               pb.setCharAt(i, "X");
                               buttons[i].setText("X");
                               lastPlayed = "X";
                               System.out.println(i);
                               buttons[i].setEnabled(false);
                               for (int i2 = 0; i2 < 9; i2++) {
                                   boardPositions[i2].setPlayedChar(buttons[i2].getText());
                                   gameBoard.setBoardPiece(boardPositions[i]);
                               }
                               buttonPressCount++;

                               gameCheck(gameBoard);
                               return;
                            }
                           
                           
                           
                           
                           else{
                               pb.setCharAt(i, " ");
                           }
                           
                       }
                    }
                  for (int i = 0; i < 9; i++) {
                        if (buttons[i].isEnabled()) {
                            buttons[i].setText("X");
                            lastPlayed = "X";
                            buttons[i].setEnabled(false);
                            for (int i2 = 0; i2 < 9; i2++) {
                                boardPositions[i2].setPlayedChar(buttons[i2].getText());
                                gameBoard.setBoardPiece(boardPositions[i]);
                            }
                            buttonPressCount++;

                            gameCheck(gameBoard);
                            break;
                }

            }
        }
        }
        private void gameCheck(PotentialBoard pb){
            if(pb.runWinScenarios()){
                 message.setText("Game Over: Winner is " +  lastPlayed+  "!");
                
                for(int i=0; i<9; i++){
                    buttons[i].setEnabled(false);
                }
                
                winCount++;
            }
        }

}


