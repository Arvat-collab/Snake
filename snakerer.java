import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


//tile
public class snakerer extends JPanel implements ActionListener, KeyListener {   
  Tile apple;
  Tile snakehead;
  Random random;
  Timer gameLoop;
  int speedX;
  int speedY;
  ArrayList<Tile> snakebody;
  Tile bodypart;
  boolean gameOver = false;
   private class Tile {
     int x;
     int y;
     Tile(int x, int y) {
       this.x = x;
       this.y = y;
       
      
     }


   }
      int boardWidth;
      int boardHeight;
      int cellSize = 25;
        //background color, constructor 
        snakerer(int boardWidth, int boardHeight) {
          this.boardWidth = boardWidth;
          this.boardHeight = boardHeight;
          setPreferredSize(new Dimension(boardWidth , boardHeight ));
          setBackground(Color.pink);
          // tiles and random
          random = new Random();
          apple = new Tile(15,15);
          snakehead = new Tile(5,5);
          food();
          gameLoop = new Timer(67, this);
          gameLoop.start();
         snakebody = new ArrayList<Tile>();
        speedX = 0;
        speedY = 1;

       addKeyListener(this);
       setFocusable(true);
    
        }
         public void paintComponent(Graphics g) {
             super.paintComponent(g);
             draw(g);
             g.setFont(new Font("Arial", Font.BOLD, 16));
          if (gameOver) {
            g.setColor(Color.red);
            g.drawString("GAME OVER!" + String.valueOf(snakebody.size()), cellSize  - 16, cellSize - 16);}
            else {
              g.drawString("Score: " + String.valueOf(snakebody.size()), cellSize - 16, cellSize - 16);
            }
         }
         //Score
          
          

         //Snake
         public void draw (Graphics g) {
          g.setColor(Color.blue);
          g.fillRect(snakehead.x * cellSize, snakehead.y * cellSize, cellSize, cellSize);

         //Grid
         for (int i = 0; i < boardWidth / cellSize; i++) 
            g.drawLine(i * cellSize, 0, i * cellSize, boardHeight);
          for (int i = 0; i < boardHeight / cellSize; i++)
            g.drawLine(0, i * cellSize, boardWidth, i * cellSize);
          
          //Apple
          g.setColor(Color.red);
          g.fillRect(apple.x * cellSize, apple.y * cellSize, cellSize, cellSize);
         

          //Snake corp
          g.setColor(Color.black);
        for (int i = 0; i < snakebody.size(); i++) {
         Tile bodypart = snakebody.get(i);
            g.fillRect(bodypart.x * cellSize, bodypart.y * cellSize, cellSize, cellSize);
            
        }
         for (int i = 0; i < snakebody.size(); i++) {
           Tile snakebodypart = snakebody.get(i);
           if(Collision(snakehead, snakebodypart)) {
             gameOver = true;
            
          }}
           
          }
          
       
        
 


        
      


         public void move() {
          
         if (Collision(snakehead, apple)) {
             snakebody.add(new Tile(apple.x, apple.y));
             placeapple();
         }

         for (int i = snakebody.size() - 1; i > 0; i--) {
          Tile currentBodyPart = snakebody.get(i);
          Tile previousBodyPart = snakebody.get(i - 1);
          currentBodyPart.x = previousBodyPart.x;
          currentBodyPart.y = previousBodyPart.y;
         }

         if (snakebody.size() > 0) {
          Tile firstBodyPart = snakebody.get(0);
          firstBodyPart.x = snakehead.x;
          firstBodyPart.y = snakehead.y;
         }
         snakehead.x += speedX;
          snakehead.y += speedY;
           if (snakehead.x*cellSize < 0 || snakehead.x*cellSize >= boardWidth || snakehead.y*cellSize < 0 || snakehead.y*cellSize >= boardHeight) {
        gameOver = true;}

            }
         
        
      

        

        
          public boolean Collision(Tile tile1,Tile tile2) {
           return (tile1.x == tile2.x && tile1.y == tile2.y);
         
          }
       
        public void placeapple() {
          apple.x = random.nextInt(boardWidth / cellSize);
          apple.y = random.nextInt(boardHeight / cellSize);

        }
        
      

        
        //game loop part
        @Override
        public void actionPerformed(ActionEvent e) {
           move();
          repaint();
          if (gameOver) {
            gameLoop.stop();
           
          }
        }
        @Override
        public void keyTyped(KeyEvent e) {
          
        }
        @Override
        public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_UP && speedY != 1) {
            speedX = 0;
            speedY = -1;
          }
          else if (e.getKeyCode() == KeyEvent.VK_DOWN && speedY != -1) {
            speedX = 0;
            speedY = 1;
          }
          else if (e.getKeyCode() == KeyEvent.VK_LEFT && speedX != 1) {
            speedX = -1;
            speedY = 0;
          }
          else if (e.getKeyCode() == KeyEvent.VK_RIGHT && speedX != -1) {
            speedX = 1;
            speedY = 0;
          }
        }
        @Override
        public void keyReleased(KeyEvent e) {
         
        }
        
      }

