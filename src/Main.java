/*--------------------------------------#
|  _  _   __   ____  ____  ____  ____   |
| / )( \ / _\ / ___)(  __)(  __)(  _ \  |
| ) __ (/    \\___ \ ) _)  ) _)  ) _ (  |
| \_)(_/\_/\_/(____/(____)(____)(____/  |
|                                       |
|              12.16.2016               |
|             Tic Tac Toe w/            |
|              psuedo AI                |
|                                       |
#--------------------------------------*/
import javax.swing.JFrame;

public class Main {

    public static void main(String[] arg){
        
        JFrame window = new MyWindow();
        window.setSize(500, 500);
        window.setLocation(100, 200);
        window.setTitle("p2 Haseeb Amin");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);  
    }
}