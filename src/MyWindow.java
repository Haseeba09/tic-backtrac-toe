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
    private PotentialBoard gameBoard;
    
    private String lastPlayed;
    
    private static int buttonPressCount, winCount = 0;
       
    Font buttonFont = new Font("Courier New", Font.BOLD, 56);
    Font messageFont = new Font("Courier New", Font.BOLD, 18);
    
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
        buttons[1] = b12 = new JButton(" ");
        buttons[2] = b13 = new JButton(" ");
        buttons[3] = b21 = new JButton(" ");
        buttons[4] = b22 = new JButton(" ");
        buttons[5] = b23 = new JButton(" ");
        buttons[6] = b31 = new JButton(" ");
        buttons[7] = b32 = new JButton(" ");
        buttons[8] = b33 = new JButton(" ");
        
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
                    
                    for(int i = 0; i<9; i++){
                       if(buttons[i].isEnabled()){
                            
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

                               //   recursiveFunction(pb);
                               gameCheck(gameBoard);
                               return;
                            }
                           
                           
                           
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

                               //   recursiveFunction(pb);
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

                    //   recursiveFunction(pb);
                            gameCheck(gameBoard);
                            break;
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
//      
//        private PotentialBoard recursiveFunction(PotentialBoard pb){
//            PotentialBoard newBoard = new PotentialBoard();
//            newBoard = pb;
//            String lastExperiment = lastPlayed;
//            
//            
//            
//            for (int i=0; i<9; i++){
//                
//                if (newBoard.getCharAt(i).equals(" ")){
//                    if(lastExperiment.equals("X")){
//                        newBoard.setCharAt(i, "O");
//                        System.out.println("O");
//                        lastExperiment = "O";
//                    }
//                    else if (lastExperiment.equals("O")){
//                        newBoard.setCharAt(i, "X");
//                        System.out.println("X");
//                        lastExperiment = "X";
//                    }
//                    
//                    break;
//                }
//                
//            
//            }
//            if(newBoard.runWinScenarios()){
//                    return newBoard;
//                }
//                else{
//                   // recursiveFunction(newBoard);
//                }
//
//            return newBoard;
//        }

    }
}


