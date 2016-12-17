

/**
 *
 * @author Haseeb
 */
public class PotentialBoard {
        
    private String[] positions = new String[9];
        
    public PotentialBoard(){
        
    }
    
    public void setBoardPiece(BoardPosition bp){
        int positionNumber = bp.getPositionNumber();
        
        switch (positionNumber){
            case 11: positions[0] = bp.getPlayedChar();
                     break;
            case 12: positions[1] = bp.getPlayedChar();
                     break;
            case 13: positions[2] = bp.getPlayedChar();
                     break;
            case 21: positions[3] = bp.getPlayedChar();
                     break;
            case 22: positions[4] = bp.getPlayedChar();
                     break;
            case 23: positions[5] = bp.getPlayedChar();
                     break;
            case 31: positions[6] = bp.getPlayedChar();
                     break;
            case 32: positions[7] = bp.getPlayedChar();
                     break;
            case 33: positions[8] = bp.getPlayedChar();
                     break;
            default: System.out.println("you messed up with the positionChar");
                     break;
        }
        
    }
    
    public String getCharAt(int i){
        return positions[i];
    }
    
    public void setCharAt(int i, String s){
        positions[i] = s;
    }
    
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
    
    public boolean runWinScenarios(){
       if (determineGame(positions[0], positions[1], positions[2])) return true;
       if (determineGame(positions[3], positions[4], positions[5])) return true;
       if (determineGame(positions[6], positions[7], positions[8])) return true;
       if (determineGame(positions[0], positions[3], positions[6])) return true;
       if (determineGame(positions[1], positions[4], positions[7])) return true;
       if (determineGame(positions[2], positions[5], positions[8])) return true;
       if (determineGame(positions[0], positions[4], positions[8])) return true;
       if (determineGame(positions[6], positions[4], positions[2])) return true;
       
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


