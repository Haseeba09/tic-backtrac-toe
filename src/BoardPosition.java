/**
 *
 * @author Haseeb
 */
public class BoardPosition {
    private String playedChar;
    private int positionNumber;
    
    public BoardPosition(){
        
    }
    
    public BoardPosition(int positionNumber){
        this.positionNumber = positionNumber;
       
    }
    
    public BoardPosition(String playedChar, int positionNumber){
        this.playedChar = playedChar;
        this.positionNumber = positionNumber;
         System.out.println(playedChar);
    }
    
    public void setPlayedChar(String playedChar){
        this.playedChar = playedChar;
    } 
    
    public void setPositionNumber(int positionNumber){
        this.positionNumber = positionNumber;
    }
    
    public String getPlayedChar(){
        return playedChar;
    } 
    
    public int getPositionNumber(){
        return positionNumber;
    }
}
