

/**
 *
 * @author Haseeb
 */
public class PotentialBoard {
    //These chars correspond to the buttons
    //they are initialized here in the manner 
    //in which the appear on the board
    private String position11, position12, position13;
    private String position21, position22, position23;
    private String position31, position32, position33;
    
        
    public PotentialBoard(){
        
    }
    
    public void setBoardPiece(BoardPosition bp){
        int positionNumber = bp.getPositionNumber();
        
        switch (positionNumber){
            case 11: position11 = bp.getPlayedChar();
                     break;
            case 12: position12 = bp.getPlayedChar();
                     break;
            case 13: position13 = bp.getPlayedChar();
                     break;
            case 21: position21 = bp.getPlayedChar();
                     break;
            case 22: position22 = bp.getPlayedChar();
                     break;
            case 23: position23 = bp.getPlayedChar();
                     break;
            case 31: position31 = bp.getPlayedChar();
                     break;
            case 32: position32 = bp.getPlayedChar();
                     break;
            case 33: position33 = bp.getPlayedChar();
                     break;
            default: System.out.println("you messed up with the positionChar");
                     break;
        }
        
    }
    
    public boolean runWinScenarios(){
       if (determineGame(position11, position12, position13)) return true;
       if (determineGame(position21, position22, position23)) return true;
       if (determineGame(position31, position32, position33)) return true;
       if (determineGame(position11, position21, position31)) return true;
       if (determineGame(position12, position22, position32)) return true;
       if (determineGame(position13, position23, position33)) return true;
       if (determineGame(position11, position22, position33)) return true;
       if (determineGame(position13, position22, position31)) return true;
       
       return false;
    }
    
    public boolean determineGame(String s1, String s2, String s3){
         if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) return false;     
         if(s1.equals(s2) && 
                 s2.equals(s3) &&
                 (!(" ".equals(s1)))){
             return true;
         }
        return false;
    }
    
    
    
}


