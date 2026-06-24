import javax.swing.*;


public class Snake {


    public static void main(String[] args) {
       int boardWidth = 500;
       int boardHeight = 500;
    
       JFrame frame = new JFrame("Snake");
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(boardWidth , boardHeight );
      
      snakerer snakerer = new snakerer(boardWidth, boardHeight);
      frame.add(snakerer);
      frame.pack();
      snakerer.requestFocus();
    }
    
   
}
