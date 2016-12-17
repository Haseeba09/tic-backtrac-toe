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
                
                
                for(int i=0; i<9; i++){
                    boardPositions[i].setPlayedChar(buttons[i].getText());
                }
                
                buttonPressCount++;

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
                    //Special opening moves
                    if(buttonPressCount == 1){
                        if((buttons[0].getText().equals("O") 
                               || buttons[2].getText().equals("O")
                               || buttons[6].getText().equals("O")
                               || buttons[8].getText().equals("O"))){
                            makePlayForX(4);
                            return;
                            
                        }
                        else if((buttons[5].getText().equals("O"))){
                            makePlayForX(8);
                            return;
                        }
                        else if((buttons[7].getText().equals("O"))){
                            makePlayForX(8);
                            return;
                        }
                    }
                    //special second moves
                    if(buttonPressCount == 3){
                        if((buttons[2].getText().equals("O")) 
                               && (buttons[6].getText().equals("O"))){
                            
                            makePlayForX(7);
                            return;
                        }
                        else if ((buttons[4].getText().equals("O"))
                                && (buttons[8].getText().equals("O"))){
                            
                               makePlayForX(6);
                               return;
                        }
                        else if ((buttons[2].getText().equals("O")) &&
                                (buttons[5].getText().equals("O"))){
                            
                                makePlayForX(7);
                                return;
                        }
                         else if ((buttons[1].getText().equals("O")) &&
                                (buttons[5].getText().equals("O"))){
                            
                                makePlayForX(6);
                                return;
                        }
                        else if ((buttons[7].getText().equals("O")) &&
                                (buttons[5].getText().equals("O"))){
                                
                                makePlayForX(3);
                                return;
                        }
                    }
                    //see if there is anywhere x wins
                    for(int i = 0; i<9; i++){
                       
                        if(buttons[i].isEnabled()){
                            pb.setCharAt(i, "X");
                        
                           if (pb.runWinScenarios()) {
                               makePlayForX(i);
                               return;

                            }
                           else{
                               pb.setCharAt(i, " ");
                           }
                        }
                    }
                    //see if there is anywhere o wins
                    for(int i = 0; i<9; i++){
                        if(buttons[i].isEnabled()){
                           pb.setCharAt(i, "O");
                        
                           if (pb.runWinScenarios()) {
                               makePlayForX(i);
                               return;

                            }
                            else{
                               pb.setCharAt(i, " ");
                           }
                        }
                    }
                    //if nothing else, place in next available
                  for (int i = 0; i < 9; i++) {
                        if (buttons[i].isEnabled()) {
                            makePlayForX(i);
                            break;
                }

            }
        }
    }

    private void makePlayForX(int index){
        if (buttons[index].isEnabled()) {
                        buttons[index].setText("X");
                        lastPlayed = "X";
                        buttons[index].setEnabled(false);
                        for (int i2 = 0; i2 < 9; i2++) {
                            boardPositions[i2].setPlayedChar(buttons[i2].getText());
                            gameBoard.setBoardPiece(boardPositions[index]);
                        }
                        buttonPressCount++;
                        message.setText("O's Turn!");

                        gameCheck(gameBoard);
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


