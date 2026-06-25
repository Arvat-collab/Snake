import javax.swing.*;


public class Snake {


    public static void main(String[] args) {
       int boardWidth = 500;
       int boardHeight = 500;
    
       JFrame frame = new JFrame("Snake");
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(boardWidth , boardHeight );
      
      Snakerer Snakerer = new Snakerer(boardWidth, boardHeight);
      frame.add(Snakerer);
      frame.pack();
      Snakerer.requestFocus();
    }
    
   
}
