import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;

public class DxBall extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
   /* int Counter = 0;
    DxBall(int Counter)
    {
       this.Counter = Counter; 
    }*/
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("DxBall");
        Group root = new Group();
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp, 500, 350, Color.WHITE);
       // DxBall Dx = new DxBall(0);
        
        bp.setPadding(new Insets(10, 20, 10, 20));

        final Image im1=new Image(DxBall.class.getResourceAsStream("OpenPage.png"));
        final ImageView iv1 = new ImageView(im1);
        final Image imo=new Image(DxBall.class.getResourceAsStream("Play.png"));
        final ImageView ivo = new ImageView(imo);
        ivo.setFitHeight(100);
        ivo.setFitWidth(120);
        iv1.setFitHeight(350);
        iv1.setFitWidth(500);
        bp.getChildren().add(iv1);
        Button Play = new Button();
        Play.setGraphic(ivo);
        bp.setCenter(Play);
       Label ScoreLabel = new Label();
        //ScoreLabel.setText("Score: "+(Counter));
        ScoreLabel.setFont(new Font("Impact", 30));
        ScoreLabel.setTextFill(Color.CHOCOLATE);
        bp.setRight(ScoreLabel);
        
        
        //
       // Start First Stage
       Play.setOnAction((ActionEvent e)->
        {
          //BackGroundMusic.pause();
           DxBallABC Dx1 = new DxBallABC(); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        });
       // root.getChildren().add(bp);
      
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

//First Stage
class DxBallABC extends Application implements Runnable {
    Group root;
    Scene scene;
    Stage stage;
    Thread thread;
    boolean suspended=true;//For Thread Pause And Resume
    boolean stopped=false;// For Thread Start And Stop
    
    int Counter = 0;//For Count Score
    Label scoreLabel;
    Circle ball;
    Rectangle[] r = new Rectangle[48];
    Rectangle rectangleBar;
    URL resource2;
    Media media2;
    MediaPlayer CollisionImpact;

    @Override
    public void run() {
        boolean hDirection = true; //true indicates that the direction is rightward
        boolean vDirection = true; //true indicates that the direction is upward
        double dX = 3;
        double dY = 3;
        try {
            while (true) {
                if(suspended){
           
                thread.sleep(40);
                double ballLeftBound = ball.getCenterX() - ball.getRadius();
                double ballRightBound = ball.getCenterX() + ball.getRadius();
                double ballTopBound = ball.getCenterY() - ball.getRadius();
                double ballBottomBound = ball.getCenterY() + ball.getRadius();
                
                //Moving Horizontally
                if (hDirection == true) {
                    ball.setCenterX(ball.getCenterX() + dX);  //Moves rightward
                    if (ballRightBound > scene.getWidth()) {
                        hDirection = false;
                    }
                } else {
                    ball.setCenterX(ball.getCenterX() - dX); //Moves leftward
                    if (ballLeftBound < 0) {
                        hDirection = true;
                    }
                }
                   
                //Moving Verically
                if (vDirection == true) {
                    ball.setCenterY(ball.getCenterY() - dY); //Moves upward
                    if (ballTopBound < 0) {
                        vDirection = false;
                    }
                } else {
                    
                    ball.setCenterY(ball.getCenterY() + dY);  //Moves downward
                    
                    if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>rectangleBar.getX())&&(ball.getCenterX()<(rectangleBar.getX()+(rectangleBar.getWidth()/2))))
                    {
                          vDirection = true;
                            hDirection = false;
                    }
                    else if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>(rectangleBar.getX()+(rectangleBar.getWidth()/2)))&&(ball.getCenterX()<(rectangleBar.getX()+rectangleBar.getWidth())))
                    {
                         vDirection = true;
                         hDirection = true;
                    }
                }
                if (ballBottomBound > scene.getHeight()) {
                
                       
                         break;                    
                }
                for(int i=0;i<48;i++)
                {
                     if (ball.intersects(r[i].getBoundsInLocal())&&r[i].getWidth()==20)
                     {
                         if(vDirection==true)
                         {
                             vDirection=false;
                         }
                        
                     }
                }
                Platform.runLater(()->{;
                for(int i=0;i<48;i++)
                {
               /* if((ballBottomBound ==r[i].getY())&&
                   (ball.getCenterX()>=r[i].getX())&&(ball.getCenterX()<=(r[i].getX()+r[i].getWidth())||
                        (ballTopBound ==r[i].getY())&&
                   (ball.getCenterX()>=r[i].getX())&&(ball.getCenterX()<=(r[i].getX()+r[i].getWidth()))))
                {
                    r[i].setVisible(false);
                }
                  if((ballRightBound ==r[i].getX())&&
                   (ball.getCenterX()>=r[i].getX())&&(ball.getCenterY()<=(r[i].getY()+r[i].getHeight())||
                        (ballLeftBound ==r[i].getX())&&
                   (ball.getCenterX()>=r[i].getX())&&(ball.getCenterY()<=(r[i].getY()+r[i].getHeight()))))
                {
                    r[i].setVisible(false);
                }
                   if((ballRightBound ==r[i].getX())&&
                   (ball.getCenterX()>=r[i].getX())&&(ball.getCenterX()<=(r[i].getX()+r[i].getWidth())||
                        (ballLeftBound ==r[i].getX())&&
                   (ball.getCenterX()>=r[i].getX())&&(ball.getCenterX()<=(r[i].getX()+r[i].getWidth()))))
                {
                    r[i].setVisible(false);
                }*/
                     
                
                    if (ball.intersects(r[i].getBoundsInLocal()))
                    {
                          
                          r[i].setVisible(false);
                          
                          if(r[i].getWidth()==20){
                          //CollisionImpact.play();
                          scoreLabel.setText("Score: "+(++Counter)*10);
                          scoreLabel.setFont(new Font("Impact", 30));//Font Add
                          scoreLabel.setTextFill(Color.CORAL);//Color Add
                          }
                          r[i].setWidth(21);
                    }
                 }
                });
                }
            }
        } catch (Exception ex) {

        }
    }
     public void GameOver()
    {
       
    }
   
   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        scene = new Scene(root, 500, 300);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
        
        //Add Image
        final Image im1=new Image(DxBall.class.getResourceAsStream("Background2.png"));
        final ImageView iv1 = new ImageView(im1);
        root.getChildren().add(iv1);
        
        //Add Image For Rectangle Actor in Scene
        Image imageAC = new Image("GameActorG.jpg");
        ImagePattern imagePatternAC = new ImagePattern(imageAC);//Image Pattern For Add Image in Rectangle
        
        Image imageB= new Image("GameActorR.jpg");
        ImagePattern imagePatternB = new ImagePattern(imageB);//Image Pattern For Add Image in Rectangle
        
        //Add Image For Circle
        Image imageC = new Image("Ball Actor.png");
        ImagePattern imagePatternC = new ImagePattern(imageC);
        
        //Add Image For Rectangle Bar
        Image imageRB = new Image("Reactangle Bar.jpg");
        ImagePattern imagePatternRB = new ImagePattern(imageRB);
        
        

        //Add Audio Track
        final URL resource = getClass().getResource("GamesBackground1.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer BackGroundMusic = new MediaPlayer(media);
       // mediaPlayer.play();
       //ADD Collision Track
        final URL resource2 = getClass().getResource("CollisionImpact.mp3");
        final Media media2 = new Media(resource2.toString());
        final MediaPlayer CollisionImpact = new MediaPlayer(media2);
        
        //DxBallABC Dx =new DxBallABC();
        //score Label
        scoreLabel = new Label("Score 0");
        scoreLabel.setFont(new Font("Impact", 30));//Font Add
        scoreLabel.setTextFill(Color.CORAL);
       
        //prepare rectangle Scene
        double rex, rey=22,rw=20,rh=20;
        int index=0; 
        //Scene OF Charatcter A
        rex=121;rey=11;
        for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
        rex=142;rey=11;
        for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
        for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
        
        rex=142;rey=63.5;
         for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
         
         
         //Scene For B
         rex=231;rey=11;
          for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternB);
            rey+=21;
            index++;
        }
           rex=252; rey-=21;
            for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternB);
            rex+=21;
            index++;
        }
          rex=252;rey=11;
            for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternB);
            rex+=21;
            index++;
        }
         rex=294;rey=21.5;
              for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternB);
            rey+=21;
            index++;
        }
              rex=252;
                 for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternB);
            rex+=21;
            index++;
        }
              rex=294;rey=84.5;
              for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternB);
            rey+=21;
            index++;
        }
         //Scene  For C
         rex=320;rey=11;     
         for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
          rex=342;rey-=21;
          for(int i=0;i<3;i++)
         {
           r[index]=new Rectangle(rex,rey,rw,rh);
           r[index].setFill(imagePatternAC);
           rex+=21;
           index++;
         }
         rex=341;rey=11;
          for(int i=0;i<3;i++)
         {
           r[index]=new Rectangle(rex,rey,rw,rh);
           r[index].setFill(imagePatternAC);
           rex+=21;
           index++;
         } rex=383; rey=32;
           r[index]=new Rectangle(rex,rey,rw,rh);
           r[index++].setFill(imagePatternAC);
           rex=383; rey=95;
           r[index]=new Rectangle(rex,rey,rw,rh);
           r[index++].setFill(imagePatternAC);
              
        
         root.getChildren().addAll(r);
        
        //preparing Rectangle Bar
        double recx = (scene.getWidth()/2)-50;
        double recy =scene.getHeight()-10;
        rectangleBar = new Rectangle(recx,recy,100,8);//set X axis,Y axis,Height and Width
        rectangleBar.setFill(imagePatternRB);//set Image instead of Color By Image Pattern Class
        
        //Preparing the Ball
        double radious = 8;
        double centerX = scene.getWidth() / 2;
        double centerY = recy-8;
        ball = new Circle(centerX, centerY, radious);//set Center X, Center Y axis and Radious
        ball.setFill(imagePatternC);// Set Image Instead Of Color By Image Pattern Class
        root.getChildren().add(ball);
        root.getChildren().add(rectangleBar);
        
        //Button for moving bar and bal
       // Button buttonr = new Button("Pause");
       // Button buttonl = new Button("Resume");
        Button Stage2 = new Button("Next Stage");
        
        Stage2.setOnAction((ActionEvent e)->
        {
           BackGroundMusic.pause();
           DxBallDEF Dx1 = new DxBallDEF(Counter); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        });
        
        //Button action for moving bar Right or Pause
        double rx = 20;
       /* buttonr.setOnAction((ActionEvent e) -> {
         
            suspended = false;
         // rectangleBar.setX(rectangleBar.getX()+rx);
         //ball.setCenterX(ball.getCenterX()+rx);
       });*/
        
        //Button action for moving bar Left or Resume
       // buttonl.setOnAction((ActionEvent e) -> {
          // suspended =true;
         // rectangleBar.setX(rectangleBar.getX()-rx);
         // ball.setCenterX(ball.getCenterX()-rx);
      // });
         EventHandler<KeyEvent> Suspend;
         Suspend = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.P){
                  BackGroundMusic.pause();   
                 suspended = false;
                 event.consume();
            }
            }
        };
          EventHandler<KeyEvent> Resume;
         Resume = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R){
                     BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
                 suspended = true;
                 event.consume();
            }
            }
        };
              
       
                //Button action for Start Ball
       /* Button button1 = new Button("Start");
        button1.setOnAction((ActionEvent e) -> {
             BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
             thread.start();// Call Thread Function
       });*/
        
        //keyBoard Switch Move Right For RightArrow Button
         EventHandler<KeyEvent> MoveRight;
         MoveRight = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.RIGHT){
                 rectangleBar.setX(rectangleBar.getX()+rx);
                 event.consume();
            }
            }
        };
         //keyBoard Switch Move Right For LeftArrow Button
          EventHandler<KeyEvent> MoveLeft;
            MoveLeft = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT){
                 rectangleBar.setX(rectangleBar.getX()-rx);
                event.consume();
            }
            }
        };
                EventHandler<KeyEvent> Start;
            Start = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                  BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
             thread.start();// Call Thread Function
                event.consume();
            }
            }
        };
               EventHandler<KeyEvent> Menu;
            Menu = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE){
                 BackGroundMusic.pause();
           DxBall Dx1 = new DxBall(); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            }
            }
        };
         
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveRight);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveLeft);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Start);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Suspend);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Resume);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Menu);
       
         // hbox.getChildren().add(button1);
         // hbox.getChildren().add(buttonr);
         // hbox.getChildren().add(buttonl);
          hbox.getChildren().add(Stage2);
          vbox.getChildren().add(hbox);
          vbox.getChildren().add(scoreLabel);
          root.getChildren().add(vbox);
         
        
        thread = new Thread(this);
       // thread.start(); //calls the run function
    }
}
 class DxBallDEF extends Application implements Runnable {
    Group root;
    Scene scene;
    Stage stage;
    Thread thread;
    boolean suspended=true;//For Thread Pause And Resume
    boolean stopped=false;// For Thread Start And Stop
    int Counter =0;
   DxBallDEF(int Counter)
   {
       this.Counter = Counter;
   }
    Label scoreLabel;
    Circle ball;
    final int no = 39;
    Rectangle[] r = new Rectangle[no];
    Rectangle rectangleBar;
    URL resource2;
    Media media2;
    MediaPlayer CollisionImpact;

    @Override
    public void run() {
        boolean hDirection = true; //true indicates that the direction is rightward
        boolean vDirection = true; //true indicates that the direction is upward
        double dX = 3.3;
        double dY = 3.3;
        try {
            while (true) {
                if(suspended){
           
                thread.sleep(40);
                double ballLeftBound = ball.getCenterX() - ball.getRadius();
                double ballRightBound = ball.getCenterX() + ball.getRadius();
                double ballTopBound = ball.getCenterY() - ball.getRadius();
                double ballBottomBound = ball.getCenterY() + ball.getRadius();
                
                //Moving Horizontally
                if (hDirection == true) {
                    ball.setCenterX(ball.getCenterX() + dX);  //Moves rightward
                    if (ballRightBound > scene.getWidth()) {
                        hDirection = false;
                    }
                } else {
                    ball.setCenterX(ball.getCenterX() - dX); //Moves leftward
                    if (ballLeftBound < 0) {
                        hDirection = true;
                    }
                }
                   
                //Moving Verically
                if (vDirection == true) {
                    ball.setCenterY(ball.getCenterY() - dY); //Moves upward
                    if (ballTopBound < 0) {
                        vDirection = false;
                    }
                } else {
                    
                    ball.setCenterY(ball.getCenterY() + dY);  //Moves downward
                    
                    if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>rectangleBar.getX())&&(ball.getCenterX()<(rectangleBar.getX()+(rectangleBar.getWidth()/2))))
                    {
                          vDirection = true;
                            hDirection = false;
                    }
                    else if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>(rectangleBar.getX()+(rectangleBar.getWidth()/2)))&&(ball.getCenterX()<(rectangleBar.getX()+rectangleBar.getWidth())))
                    {
                         vDirection = true;
                         hDirection = true;
                    }
                }
                for(int i=0;i<no;i++)
                {
                     if (ball.intersects(r[i].getBoundsInLocal())&&r[i].getWidth()==20)
                     {
                         if(vDirection==true)
                         {
                             vDirection=false;
                         }
                        
                     }
                }
                Platform.runLater(()->{;
                for(int i=0;i<no;i++)
                {
             
                    if (ball.intersects(r[i].getBoundsInLocal()))
                    {
                          
                          r[i].setVisible(false);
                          
                          if(r[i].getWidth()==20){
                        //  CollisionImpact.play();
                          scoreLabel.setText("Score: "+(++Counter)*10);
                          scoreLabel.setFont(new Font("Impact", 30));//Font Add
                          scoreLabel.setTextFill(Color.CORAL);//Color Add
                          }
                          r[i].setWidth(21);
                    }
                 }
                });
                }
            }
        } catch (Exception ex) {

        }
    }
   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        scene = new Scene(root, 500, 300);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
        
        //Add Image
        final Image im1=new Image(DxBall.class.getResourceAsStream("Background2.png"));
        final ImageView iv1 = new ImageView(im1);
        root.getChildren().add(iv1);
        
        //Add Image For Rectangle Actor in Scene
        Image imageAC = new Image("GameActorG.jpg");
        ImagePattern imagePatternAC = new ImagePattern(imageAC);//Image Pattern For Add Image in Rectangle
        
        Image imageB= new Image("GameActorR.jpg");
        ImagePattern imagePatternB = new ImagePattern(imageB);//Image Pattern For Add Image in Rectangle
        
        //Add Image For Circle
        Image imageC = new Image("Ball Actor.png");
        ImagePattern imagePatternC = new ImagePattern(imageC);
        
        //Add Image For Rectangle Bar
        Image imageRB = new Image("Reactangle Bar.jpg");
        ImagePattern imagePatternRB = new ImagePattern(imageRB);
        
        

        //Add Audio Track
        final URL resource = getClass().getResource("GamesBackground1.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer BackGroundMusic = new MediaPlayer(media);
       // mediaPlayer.play();
       //ADD Collision Track
        final URL resource2 = getClass().getResource("CollisionImpact.mp3");
        final Media media2 = new Media(resource2.toString());
        final MediaPlayer CollisionImpact = new MediaPlayer(media2);
        
        
        //score Label
        scoreLabel = new Label("Score 0");
        scoreLabel.setFont(new Font("Impact", 30));//Font Add
        scoreLabel.setTextFill(Color.CORAL);
       
        //prepare rectangle Scene
        double rex, rey=22,rw=20,rh=20;
        int index=0;
        rex=120;
        //Scene For DDDDDD
        for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
        rex+=21;rey-=21;
        for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
        rey=22; rex-=42;
         for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
         rey+=21;
          for(int i=0;i<4;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
          //Scene For E
          rex=210;rey=22;
        for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternB);
            rey+=21;
            index++; 
        }
        rex+=21;rey-=21;
          for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternB);
            rex+=21;
            index++;
        }
          rex=231;rey=22;
        for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternB);
            rex+=21;
            index++;
        };
        rex=231;rey=74.5;
          for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternB);
            rex+=21;
            index++;
        }
            //Scene For F
          rex=300;rey=22;
        for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rey+=21;
            index++; 
        }
        rex+=21;rey=22;
         for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        };
        rex = 321;rey=74.5;
         for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh); 
            r[index].setFill(imagePatternAC);
            
            rex+=21;
            index++;
        };
        
          
        
        
         root.getChildren().addAll(r);
        
        //preparing Rectangle Bar
        double recx = (scene.getWidth()/2)-50;
        double recy =scene.getHeight()-10;
        rectangleBar = new Rectangle(recx,recy,100,8);//set X axis,Y axis,Height and Width
        rectangleBar.setFill(imagePatternRB);//set Image instead of Color By Image Pattern Class
        
        //Preparing the Ball
        double radious = 8;
        double centerX = scene.getWidth() / 2;
        double centerY = recy-8;
        ball = new Circle(centerX, centerY, radious);//set Center X, Center Y axis and Radious
        ball.setFill(imagePatternC);// Set Image Instead Of Color By Image Pattern Class
        root.getChildren().add(ball);
        root.getChildren().add(rectangleBar);
        
      double rx=20;
      Button Stage2 = new Button("Next Stage");
        
        Stage2.setOnAction((ActionEvent e)->
        {
           BackGroundMusic.pause();
           DxBallGH Dx1 = new DxBallGH(Counter); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        });
       
       EventHandler<KeyEvent> Suspend;
         Suspend = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.P){
                     BackGroundMusic.pause();
                 suspended = false;
                 event.consume();
            }
            }
        };
          EventHandler<KeyEvent> Resume;
         Resume = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R){
                 suspended = true;
                  BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
                 event.consume();
            }
            }
        };
           EventHandler<KeyEvent> Start;
            Start = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                  BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
             thread.start();// Call Thread Function
                event.consume();
            }
            }
        };
        
        //keyBoard Switch Move Right For RightArrow Button
         EventHandler<KeyEvent> MoveRight;
         MoveRight = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.RIGHT){
                 rectangleBar.setX(rectangleBar.getX()+rx);
                 event.consume();
            }
            }
        };
         //keyBoard Switch Move Right For LeftArrow Button
          EventHandler<KeyEvent> MoveLeft;
            MoveLeft = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT){
                 rectangleBar.setX(rectangleBar.getX()-rx);
                event.consume();
            }
            }
        };
             EventHandler<KeyEvent> Menu;
            Menu = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE){
                 BackGroundMusic.pause();
           DxBall Dx1 = new DxBall(); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            }
            }
        };
         
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveRight);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveLeft);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Start);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Suspend);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Resume);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Menu);
       
          hbox.getChildren().add(Stage2);
          vbox.getChildren().add(hbox);
          vbox.getChildren().add(scoreLabel);
          root.getChildren().add(vbox);
         
        
        thread = new Thread(this);
       // thread.start(); //calls the run function
    }
}
class DxBallGH extends Application implements Runnable {
    Group root;
    Scene scene;
    Stage stage;
    Thread thread;
    boolean suspended=true;//For Thread Pause And Resume
    boolean stopped=false;// For Thread Start And Stop
    
    int Counter = 0;//For Count Score
     DxBallGH(int Counter)
   {
       this.Counter = Counter;
   }
    Label scoreLabel;
    Circle ball;
    final int no = 33;
    Rectangle[] r = new Rectangle[no];
    Rectangle rectangleBar;
    URL resource2;
    Media media2;
    MediaPlayer CollisionImpact;

    @Override
    public void run() {
        boolean hDirection = true; //true indicates that the direction is rightward
        boolean vDirection = true; //true indicates that the direction is upward
        double dX = 3.5;
        double dY = 3.5;
        try {
            while (true) {
                if(suspended){
           
                thread.sleep(40);
                double ballLeftBound = ball.getCenterX() - ball.getRadius();
                double ballRightBound = ball.getCenterX() + ball.getRadius();
                double ballTopBound = ball.getCenterY() - ball.getRadius();
                double ballBottomBound = ball.getCenterY() + ball.getRadius();
                
                //Moving Horizontally
                if (hDirection == true) {
                    ball.setCenterX(ball.getCenterX() + dX);  //Moves rightward
                    if (ballRightBound > scene.getWidth()) {
                        hDirection = false;
                    }
                } else {
                    ball.setCenterX(ball.getCenterX() - dX); //Moves leftward
                    if (ballLeftBound < 0) {
                        hDirection = true;
                    }
                }
                   
                //Moving Verically
                if (vDirection == true) {
                    ball.setCenterY(ball.getCenterY() - dY); //Moves upward
                    if (ballTopBound < 0) {
                        vDirection = false;
                    }
                } else {
                    
                    ball.setCenterY(ball.getCenterY() + dY);  //Moves downward
                    
                    if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>rectangleBar.getX())&&(ball.getCenterX()<(rectangleBar.getX()+(rectangleBar.getWidth()/2))))
                    {
                          vDirection = true;
                            hDirection = false;
                    }
                    else if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>(rectangleBar.getX()+(rectangleBar.getWidth()/2)))&&(ball.getCenterX()<(rectangleBar.getX()+rectangleBar.getWidth())))
                    {
                         vDirection = true;
                         hDirection = true;
                    }
                }
                
                 if (ballBottomBound > scene.getHeight()) {
                    break;                    
                }
                for(int i=0;i<no;i++)
                {
                     if (ball.intersects(r[i].getBoundsInLocal())&&r[i].getWidth()==20)
                     {
                         if(vDirection==true)
                         {
                             vDirection=false;
                         }
                        
                     }
                }
                Platform.runLater(()->{;
                for(int i=0;i<no;i++)
                {
              
                    if (ball.intersects(r[i].getBoundsInLocal()))
                    {
                          
                          r[i].setVisible(false);
                          
                          if(r[i].getWidth()==20){
                        //  CollisionImpact.play();
                          scoreLabel.setText("Score: "+(++Counter)*10);
                          scoreLabel.setFont(new Font("Impact", 30));//Font Add
                          scoreLabel.setTextFill(Color.CORAL);//Color Add
                          }
                          r[i].setWidth(21);
                    }
                 }
                });
                }
            }
        } catch (Exception ex) {

        }
    }
   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        scene = new Scene(root, 500, 300);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
        
        //Add Image
        final Image im1=new Image(DxBall.class.getResourceAsStream("Background2.png"));
        final ImageView iv1 = new ImageView(im1);
        root.getChildren().add(iv1);
        
        //Add Image For Rectangle Actor in Scene
        Image imageAC = new Image("GameActorG.jpg");
        ImagePattern imagePatternAC = new ImagePattern(imageAC);//Image Pattern For Add Image in Rectangle
        
        Image imageB= new Image("GameActorR.jpg");
        ImagePattern imagePatternB = new ImagePattern(imageB);//Image Pattern For Add Image in Rectangle
        
        //Add Image For Circle
        Image imageC = new Image("Ball Actor.png");
        ImagePattern imagePatternC = new ImagePattern(imageC);
        
        //Add Image For Rectangle Bar
        Image imageRB = new Image("Reactangle Bar.jpg");
        ImagePattern imagePatternRB = new ImagePattern(imageRB);
        
        

        //Add Audio Track
        final URL resource = getClass().getResource("GamesBackground1.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer BackGroundMusic = new MediaPlayer(media);
       // mediaPlayer.play();
       //ADD Collision Track
        final URL resource2 = getClass().getResource("CollisionImpact.mp3");
        final Media media2 = new Media(resource2.toString());
        final MediaPlayer CollisionImpact = new MediaPlayer(media2);
        
       
        //score Label
        scoreLabel = new Label("Score 0");
        scoreLabel.setFont(new Font("Impact", 30));//Font Add
        scoreLabel.setTextFill(Color.CORAL);
       
        //prepare rectangle Scene
        double rex, rey=22,rw=20,rh=20;
        int index=0;
        rex=180;
       // Scene For GGG
        for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
        rex=201;rey-=21;
        for(int i=0;i<4;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
        rex-=21;rey-=21;
        for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rey-=21;
            index++;
        }
        rey+=21;rex-=21;
        for(int i=0;i<2;i++)
        {
             r[index]=new Rectangle(rex,rey,rw,rh);
              r[index].setFill(imagePatternAC);
            rex-=21;
            index++;
        }
        rex=201;rey=22;
        for(int i=0;i<4;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
        //Scene for H
        rex=300;rey=22;
          for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternB);
            rey+=21;
            index++;
        }
          rex+=21;rey=74.5;
             for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternB);
            rex+=21;
            index++;
        }
       rey=22;
          for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternB);
            rey+=21;
            index++;
        }
        
          
       root.getChildren().addAll(r);//Add Rectangle Actor
        
        //preparing Rectangle Bar
        double recx = (scene.getWidth()/2)-50;
        double recy =scene.getHeight()-10;
        rectangleBar = new Rectangle(recx,recy,100,8);//set X axis,Y axis,Height and Width
        rectangleBar.setFill(imagePatternRB);//set Image instead of Color By Image Pattern Class
        
        //Preparing the Ball
        double radious = 8;
        double centerX = scene.getWidth() / 2;
        double centerY = recy-8;
        ball = new Circle(centerX, centerY, radious);//set Center X, Center Y axis and Radious
        ball.setFill(imagePatternC);// Set Image Instead Of Color By Image Pattern Class
        root.getChildren().add(ball);
        root.getChildren().add(rectangleBar);
        
        
        //Button action for moving bar Right or Pause
        double rx = 20;
     
         Button Stage2 = new Button("Next Stage");
        
        Stage2.setOnAction((ActionEvent e)->
        {
           BackGroundMusic.pause();
           DxBallIJK Dx1 = new DxBallIJK(Counter); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        });
       
       EventHandler<KeyEvent> Suspend;
         Suspend = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.P){
                  BackGroundMusic.pause();  
                 suspended = false;
                 event.consume();
            }
            }
        };
          EventHandler<KeyEvent> Resume;
         Resume = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R){
                     BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
                 suspended = true;
                 event.consume();
            }
            }
        };
           EventHandler<KeyEvent> Start;
            Start = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                  BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
             thread.start();// Call Thread Function
                event.consume();
            }
            }
        };
        
        //keyBoard Switch Move Right For RightArrow Button
         EventHandler<KeyEvent> MoveRight;
         MoveRight = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.RIGHT){
                 rectangleBar.setX(rectangleBar.getX()+rx);
                 event.consume();
            }
            }
        };
         //keyBoard Switch Move Right For LeftArrow Button
          EventHandler<KeyEvent> MoveLeft;
            MoveLeft = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT){
                 rectangleBar.setX(rectangleBar.getX()-rx);
                event.consume();
            }
            }
        };
             EventHandler<KeyEvent> Menu;
            Menu = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE){
                 BackGroundMusic.pause();
           DxBall Dx1 = new DxBall(); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            }
            }
        };
         
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveRight);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveLeft);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Start);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Suspend);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Resume);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Menu);
       
          hbox.getChildren().add(Stage2);
          vbox.getChildren().add(hbox);
          vbox.getChildren().add(scoreLabel);
          root.getChildren().add(vbox);
         
        
        thread = new Thread(this);
    }
}

 class DxBallIJK extends Application implements Runnable {
    Group root;
    Scene scene;
    Stage stage;
    Thread thread;
    boolean suspended=true;//For Thread Pause And Resume
    boolean stopped=false;// For Thread Start And Stop
    
    int Counter = 0;//For Count Score
	
	 DxBallIJK(int Counter)
   {
       this.Counter = Counter;
   }
    Label scoreLabel;
    Circle ball;
    final int no = 35;
    Rectangle[] r = new Rectangle[no];
    Rectangle rectangleBar;
    URL resource2;
    Media media2;
    MediaPlayer CollisionImpact;

    @Override
    public void run() {
        boolean hDirection = true; //true indicates that the direction is rightward
        boolean vDirection = true; //true indicates that the direction is upward
        double dX = 3.8;
        double dY = 3.8;
        try {
            while (true) {
                if(suspended){
           
                thread.sleep(40);
                double ballLeftBound = ball.getCenterX() - ball.getRadius();
                double ballRightBound = ball.getCenterX() + ball.getRadius();
                double ballTopBound = ball.getCenterY() - ball.getRadius();
                double ballBottomBound = ball.getCenterY() + ball.getRadius();
                
                //Moving Horizontally
                if (hDirection == true) {
                    ball.setCenterX(ball.getCenterX() + dX);  //Moves rightward
                    if (ballRightBound > scene.getWidth()) {
                        hDirection = false;
                    }
                } else {
                    ball.setCenterX(ball.getCenterX() - dX); //Moves leftward
                    if (ballLeftBound < 0) {
                        hDirection = true;
                    }
                }
                   
                //Moving Verically
                if (vDirection == true) {
                    ball.setCenterY(ball.getCenterY() - dY); //Moves upward
                    if (ballTopBound < 0) {
                        vDirection = false;
                    }
                } else {
                    
                    ball.setCenterY(ball.getCenterY() + dY);  //Moves downward
                    
                    if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>rectangleBar.getX())&&(ball.getCenterX()<(rectangleBar.getX()+(rectangleBar.getWidth()/2))))
                    {
                          vDirection = true;
                            hDirection = false;
                    }
                    else if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>(rectangleBar.getX()+(rectangleBar.getWidth()/2)))&&(ball.getCenterX()<(rectangleBar.getX()+rectangleBar.getWidth())))
                    {
                         vDirection = true;
                         hDirection = true;
                    }
                }
                if (ballBottomBound > scene.getHeight()) {
                    break;                    
                }
                for(int i=0;i<no;i++)
                {
                     if (ball.intersects(r[i].getBoundsInLocal())&&r[i].getWidth()==20)
                     {
                         if(vDirection==true)
                         {
                             vDirection=false;
                         }
                        
                     }
                }
                Platform.runLater(()->{;
                for(int i=0;i<no;i++)
                {
               
                    if (ball.intersects(r[i].getBoundsInLocal()))
                    {
                          
                          r[i].setVisible(false);
                          
                          if(r[i].getWidth()==20){
                        //  CollisionImpact.play();
                          scoreLabel.setText("Score: "+(++Counter)*10);
                          scoreLabel.setFont(new Font("Impact", 30));//Font Add
                          scoreLabel.setTextFill(Color.CORAL);//Color Add
                          }
                          r[i].setWidth(21);
                    }
                 }
                });
                }
            }
        } catch (Exception ex) {

        }
    }
   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        scene = new Scene(root, 500, 300);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
        
        //Add Image
        final Image im1=new Image(DxBall.class.getResourceAsStream("Background2.png"));
        final ImageView iv1 = new ImageView(im1);
        root.getChildren().add(iv1);
        
        //Add Image For Rectangle Actor in Scene
        Image imageAC = new Image("GameActorG.jpg");
        ImagePattern imagePatternAC = new ImagePattern(imageAC);//Image Pattern For Add Image in Rectangle
        
        Image imageB= new Image("GameActorR.jpg");
        ImagePattern imagePatternB = new ImagePattern(imageB);//Image Pattern For Add Image in Rectangle
        
        //Add Image For Circle
        Image imageC = new Image("Ball Actor.png");
        ImagePattern imagePatternC = new ImagePattern(imageC);
        
        //Add Image For Rectangle Bar
        Image imageRB = new Image("Reactangle Bar.jpg");
        ImagePattern imagePatternRB = new ImagePattern(imageRB);
        
        

        //Add Audio Track
        final URL resource = getClass().getResource("GamesBackground1.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer BackGroundMusic = new MediaPlayer(media);
       // mediaPlayer.play();
       //ADD Collision Track
        final URL resource2 = getClass().getResource("CollisionImpact.mp3");
        final Media media2 = new Media(resource2.toString());
        final MediaPlayer CollisionImpact = new MediaPlayer(media2);
        
       
        //score Label
        scoreLabel = new Label("Score 0");
        scoreLabel.setFont(new Font("Impact", 30));//Font Add
        scoreLabel.setTextFill(Color.CORAL);
       
        //prepare rectangle Scene
        double rex, rey=22,rw=20,rh=20;
        int index=0;
        rex=120;
        //Scene For I
        for(int i=0 ;i<3; i++)
        {
           r[index]=new Rectangle(rex,rey,rw,rh);
           r[index].setFill(imagePatternAC);
           rex+=21;
           index++;
        }
        rex-=42;rey=43;
         for(int i=0 ;i<4; i++)
        {
           r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternAC);
           rey+=21;
           index++;
        }
         rex-=21;
          for(int i=0 ;i<3; i++)
        {
           r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternAC);
           rex+=21;
           index++;
        }
          //scene For J
          rex=190;rey-=21;
          r[index]=new Rectangle(rex,rey,rw,rh);
           r[index++].setFill(imagePatternB);
          rex=190;rey+=21;
            for(int i=0 ;i<4; i++)
        {
           r[index]=new Rectangle(rex,rey,rw,rh);
           r[index].setFill(imagePatternB);
           rex+=21;
           index++;
        }
            rex-=21;
        for(int i=0 ;i<5; i++)  
          {
           r[index]=new Rectangle(rex,rey,rw,rh);
           r[index].setFill(imagePatternB);
           rey-=21;
           index++;
         }
        rex-=21;
         for(int i=0 ;i<3; i++)  
          {
           r[index]=new Rectangle(rex,rey,rw,rh);
           r[index].setFill(imagePatternB);
           rex+=21;
           index++;
         }
        //scene For K
        rex=300;
        rey=22;
         for(int i=0 ;i<6; i++)  
          {
           r[index]=new Rectangle(rex,rey,rw,rh);
           r[index].setFill(imagePatternAC);
           rey+=21;
           index++;
         }
         rex=363;rey=22;
          for(int i=0 ;i<3; i++)  
          {
           r[index]=new Rectangle(rex,rey,rw,rh);
           r[index].setFill(imagePatternAC);
           rex-=21;
           rey+=21;
           index++;
         }
          rex+=21;
           for(int i=0 ;i<3; i++)  
          {
           r[index]=new Rectangle(rex,rey,rw,rh);
           r[index].setFill(imagePatternAC);
           rex+=21;
           rey+=21;
           index++;
         }
         
        
        root.getChildren().addAll(r);
        
        //preparing Rectangle Bar
        double recx = (scene.getWidth()/2)-50;
        double recy =scene.getHeight()-10;
        rectangleBar = new Rectangle(recx,recy,100,8);//set X axis,Y axis,Height and Width
        rectangleBar.setFill(imagePatternRB);//set Image instead of Color By Image Pattern Class
        
        //Preparing the Ball
        double radious = 8;
        double centerX = scene.getWidth() / 2;
        double centerY = recy-8;
        ball = new Circle(centerX, centerY, radious);//set Center X, Center Y axis and Radious
        ball.setFill(imagePatternC);// Set Image Instead Of Color By Image Pattern Class
        root.getChildren().add(ball);
        root.getChildren().add(rectangleBar);
         Button Stage2 = new Button("Next Stage");
        
        Stage2.setOnAction((ActionEvent e)->
        {
           BackGroundMusic.pause();
           DxBallLMN Dx1 = new DxBallLMN(Counter); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        });
       
       EventHandler<KeyEvent> Suspend;
         Suspend = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.P){
               BackGroundMusic.pause();   suspended = false;
                 event.consume();
            }
            }
        };
          EventHandler<KeyEvent> Resume;
         Resume = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R){
                     BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
                 suspended = true;
                 event.consume();
            }
            }
        };
           EventHandler<KeyEvent> Start;
            Start = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                  BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
             thread.start();// Call Thread Function
                event.consume();
            }
            }
        };
          double rx =20;
        
        //keyBoard Switch Move Right For RightArrow Button
         EventHandler<KeyEvent> MoveRight;
         MoveRight = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.RIGHT){
                 rectangleBar.setX(rectangleBar.getX()+rx);
                 event.consume();
            }
            }
        };
         //keyBoard Switch Move Right For LeftArrow Button
          EventHandler<KeyEvent> MoveLeft;
            MoveLeft = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT){
                 rectangleBar.setX(rectangleBar.getX()-rx);
                event.consume();
            }
            }
        };
             EventHandler<KeyEvent> Menu;
            Menu = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE){
                 BackGroundMusic.pause();
           DxBall Dx1 = new DxBall(); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            }
            }
        };
         
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveRight);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveLeft);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Start);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Suspend);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Resume);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Menu);
       
          hbox.getChildren().add(Stage2);
          vbox.getChildren().add(hbox);
          vbox.getChildren().add(scoreLabel);
          root.getChildren().add(vbox);
         
        
        thread = new Thread(this);
        
        }
}

 class DxBallLMN extends Application implements Runnable {
    Group root;
    Scene scene;
    Stage stage;
    Thread thread;
    boolean suspended=true;//For Thread Pause And Resume
    boolean stopped=false;// For Thread Start And Stop
    
     int Counter = 0;//For Count Score
	  DxBallLMN(int Counter)
   {
       this.Counter = Counter;
   }
    Label scoreLabel;
    Circle ball;
    final int no = 42;
    Rectangle[] r = new Rectangle[no];
    Rectangle rectangleBar;
    URL resource2;
    Media media2;
    MediaPlayer CollisionImpact;

    @Override
    public void run() {
        boolean hDirection = true; //true indicates that the direction is rightward
        boolean vDirection = true; //true indicates that the direction is upward
        double dX = 4;
        double dY = 4;
        try {
            while (true) {
                if(suspended){
           
                thread.sleep(40);
                double ballLeftBound = ball.getCenterX() - ball.getRadius();
                double ballRightBound = ball.getCenterX() + ball.getRadius();
                double ballTopBound = ball.getCenterY() - ball.getRadius();
                double ballBottomBound = ball.getCenterY() + ball.getRadius();
                
                //Moving Horizontally
                if (hDirection == true) {
                    ball.setCenterX(ball.getCenterX() + dX);  //Moves rightward
                    if (ballRightBound > scene.getWidth()) {
                        hDirection = false;
                    }
                } else {
                    ball.setCenterX(ball.getCenterX() - dX); //Moves leftward
                    if (ballLeftBound < 0) {
                        hDirection = true;
                    }
                }
                   
                //Moving Verically
                if (vDirection == true) {
                    ball.setCenterY(ball.getCenterY() - dY); //Moves upward
                    if (ballTopBound < 0) {
                        vDirection = false;
                    }
                } else {
                    
                    ball.setCenterY(ball.getCenterY() + dY);  //Moves downward
                    
                    if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>rectangleBar.getX())&&(ball.getCenterX()<(rectangleBar.getX()+(rectangleBar.getWidth()/2))))
                    {
                          vDirection = true;
                            hDirection = false;
                    }
                    else if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>(rectangleBar.getX()+(rectangleBar.getWidth()/2)))&&(ball.getCenterX()<(rectangleBar.getX()+rectangleBar.getWidth())))
                    {
                         vDirection = true;
                         hDirection = true;
                    }
                }
                if (ballBottomBound > scene.getHeight()) {
                    break;                    
                }
                for(int i=0;i<no;i++)
                {
                     if (ball.intersects(r[i].getBoundsInLocal())&&r[i].getWidth()==20)
                     {
                         if(vDirection==true)
                         {
                             vDirection=false;
                         }
                        
                     }
                }
                Platform.runLater(()->{;
                for(int i=0;i<no;i++)
                {
              if (ball.intersects(r[i].getBoundsInLocal()))
                    {
                          
                          r[i].setVisible(false);
                          if(r[i].getWidth()==20){
                        //  CollisionImpact.play();
                          scoreLabel.setText("Score: "+(++Counter)*10);
                          scoreLabel.setFont(new Font("Impact", 30));//Font Add
                          scoreLabel.setTextFill(Color.CORAL);//Color Add
                          }
                          r[i].setWidth(21);
                    }
                 }
                });
                }
            }
        } catch (Exception ex) {

        }
    }
   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        scene = new Scene(root, 500, 300);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
        
        //Add Image
        final Image im1=new Image(DxBall.class.getResourceAsStream("Background2.png"));
        final ImageView iv1 = new ImageView(im1);
        root.getChildren().add(iv1);
        
        //Add Image For Rectangle Actor in Scene
        Image imageAC = new Image("GameActorG.jpg");
        ImagePattern imagePatternAC = new ImagePattern(imageAC);//Image Pattern For Add Image in Rectangle
        
        Image imageB= new Image("GameActorR.jpg");
        ImagePattern imagePatternB = new ImagePattern(imageB);//Image Pattern For Add Image in Rectangle
        
        //Add Image For Circle
        Image imageC = new Image("Ball Actor.png");
        ImagePattern imagePatternC = new ImagePattern(imageC);
        
        //Add Image For Rectangle Bar
        Image imageRB = new Image("Reactangle Bar.jpg");
        ImagePattern imagePatternRB = new ImagePattern(imageRB);
        
        

        //Add Audio Track
        final URL resource = getClass().getResource("GamesBackground1.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer BackGroundMusic = new MediaPlayer(media);
       // mediaPlayer.play();
       //ADD Collision Track
        final URL resource2 = getClass().getResource("CollisionImpact.mp3");
        final Media media2 = new Media(resource2.toString());
        final MediaPlayer CollisionImpact = new MediaPlayer(media2);
        
        
        //score Label
        scoreLabel = new Label("Score 0");
        scoreLabel.setFont(new Font("Impact", 30));//Font Add
        scoreLabel.setTextFill(Color.CORAL);
       
        //prepare rectangle Scene
        double rex, rey=22,rw=20,rh=20;
        int index=0;
        //Scene For L
        rex=120;
         for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
         rex+=21;rey-=21;
         for(int i=0;i<4;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
         //Scene For M
         rex=230;rey=22;
            for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
        rex=314;rey=22;
           for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
           rex=230+21; rey=43;
           r[index]=new Rectangle(rex,rey,rw,rh);
            r[index++].setFill(imagePatternAC);
           rex=230+21+21;rey+=21;
           r[index]=new Rectangle(rex,rey,rw,rh);
            r[index++].setFill(imagePatternAC);
           rex+=21;rey-=21;
           r[index]=new Rectangle(rex,rey,rw,rh);
            r[index++].setFill(imagePatternAC);
           
       //Scene For N
       rex=340;rey=22;
          for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternB);
            rey+=21;
            index++;
        }
          rey=32.5;rex=361;
          for(int i=0;i<5;i++)
            {
             r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternB);
             rey+=21;
             rex+=10.5;
             index++;
            }
          rex+=10.5;rey=22;
            for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternB);
            rey+=21;
            index++;
        }
            
        root.getChildren().addAll(r);//Add Rectangle Actor
		
		//preparing Rectangle Bar
        double recx = (scene.getWidth()/2)-50;
        double recy =scene.getHeight()-10;
        rectangleBar = new Rectangle(recx,recy,100,8);//set X axis,Y axis,Height and Width
        rectangleBar.setFill(imagePatternRB);//set Image instead of Color By Image Pattern Class
        
        //Preparing the Ball
        double radious = 8;
        double centerX = scene.getWidth() / 2;
        double centerY = recy-8;
        ball = new Circle(centerX, centerY, radious);//set Center X, Center Y axis and Radious
        ball.setFill(imagePatternC);// Set Image Instead Of Color By Image Pattern Class
        root.getChildren().add(ball);
        root.getChildren().add(rectangleBar);
         Button Stage2 = new Button("Next Stage");
        
        Stage2.setOnAction((ActionEvent e)->
        {
           BackGroundMusic.pause();
           DxBallOPQ Dx1 = new DxBallOPQ(Counter); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        });
       
       EventHandler<KeyEvent> Suspend;
         Suspend = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.P){
                 BackGroundMusic.pause(); suspended = false;
                 event.consume();
            }
            }
        };
          EventHandler<KeyEvent> Resume;
         Resume = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R){
                     BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
                 suspended = true;
                 event.consume();
            }
            }
        };
           EventHandler<KeyEvent> Start;
            Start = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                  BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
             thread.start();// Call Thread Function
                event.consume();
            }
            }
        };
          double rx =20;
        
        //keyBoard Switch Move Right For RightArrow Button
         EventHandler<KeyEvent> MoveRight;
         MoveRight = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.RIGHT){
                 rectangleBar.setX(rectangleBar.getX()+rx);
                 event.consume();
            }
            }
        };
         //keyBoard Switch Move Right For LeftArrow Button
          EventHandler<KeyEvent> MoveLeft;
            MoveLeft = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT){
                 rectangleBar.setX(rectangleBar.getX()-rx);
                event.consume();
            }
            }
        };
             EventHandler<KeyEvent> Menu;
            Menu = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE){
                 BackGroundMusic.pause();
           DxBall Dx1 = new DxBall(); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            }
            }
        };
         
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveRight);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveLeft);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Start);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Suspend);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Resume);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Menu);
       
          hbox.getChildren().add(Stage2);
          vbox.getChildren().add(hbox);
          vbox.getChildren().add(scoreLabel);
          root.getChildren().add(vbox);
         
        
        thread = new Thread(this);
    }
}
class DxBallOPQ extends Application implements Runnable {
    Group root;
    Scene scene;
    Stage stage;
    Thread thread;
    boolean suspended=true;//For Thread Pause And Resume
    boolean stopped=false;// For Thread Start And Stop
    
     int Counter = 0;//For Count Score
      DxBallOPQ(int Counter)
   {
       this.Counter = Counter;
   }
     
    Label scoreLabel;
    Circle ball;
    final int no = 53;
    Rectangle[] r = new Rectangle[no];
    Rectangle rectangleBar;
    URL resource2;
    Media media2;
    MediaPlayer CollisionImpact;

    @Override
    public void run() {
        boolean hDirection = true; //true indicates that the direction is rightward
        boolean vDirection = true; //true indicates that the direction is upward
        double dX = 4.25;
        double dY = 4.25;
        try {
            while (true) {
                if(suspended){
           
                thread.sleep(40);
                double ballLeftBound = ball.getCenterX() - ball.getRadius();
                double ballRightBound = ball.getCenterX() + ball.getRadius();
                double ballTopBound = ball.getCenterY() - ball.getRadius();
                double ballBottomBound = ball.getCenterY() + ball.getRadius();
                
                //Moving Horizontally
                if (hDirection == true) {
                    ball.setCenterX(ball.getCenterX() + dX);  //Moves rightward
                    if (ballRightBound > scene.getWidth()) {
                        hDirection = false;
                    }
                } else {
                    ball.setCenterX(ball.getCenterX() - dX); //Moves leftward
                    if (ballLeftBound < 0) {
                        hDirection = true;
                    }
                }
                   
                //Moving Verically
                if (vDirection == true) {
                    ball.setCenterY(ball.getCenterY() - dY); //Moves upward
                    if (ballTopBound < 0) {
                        vDirection = false;
                    }
                } else {
                    
                    ball.setCenterY(ball.getCenterY() + dY);  //Moves downward
                    
                    if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>rectangleBar.getX())&&(ball.getCenterX()<(rectangleBar.getX()+(rectangleBar.getWidth()/2))))
                    {
                          vDirection = true;
                            hDirection = false;
                    }
                    else if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>(rectangleBar.getX()+(rectangleBar.getWidth()/2)))&&(ball.getCenterX()<(rectangleBar.getX()+rectangleBar.getWidth())))
                    {
                         vDirection = true;
                         hDirection = true;
                    }
                }
                if (ballBottomBound > scene.getHeight()) {
                    break;                    
                }
                for(int i=0;i<no;i++)
                {
                     if (ball.intersects(r[i].getBoundsInLocal())&&r[i].getWidth()==20)
                     {
                         if(vDirection==true)
                         {
                             vDirection=false;
                         }
                        
                     }
                }
                Platform.runLater(()->{;
                for(int i=0;i<no;i++)
                {
                    if (ball.intersects(r[i].getBoundsInLocal()))
                    {
                          
                          r[i].setVisible(false);
                          
                          if(r[i].getWidth()==20){
                        //  CollisionImpact.play();
                          scoreLabel.setText("Score: "+(++Counter)*10);
                          scoreLabel.setFont(new Font("Impact", 30));//Font Add
                          scoreLabel.setTextFill(Color.CORAL);//Color Add
                          }
                          r[i].setWidth(21);
                    }
                 }
                });
                }
            }
        } catch (Exception ex) {

        }
    }
   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        scene = new Scene(root, 500, 300);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
        
        //Add Image
        final Image im1=new Image(DxBall.class.getResourceAsStream("Background2.png"));
        final ImageView iv1 = new ImageView(im1);
        root.getChildren().add(iv1);
        
        //Add Image For Rectangle Actor in Scene
        Image imageAC = new Image("GameActorG.jpg");
        ImagePattern imagePatternAC = new ImagePattern(imageAC);//Image Pattern For Add Image in Rectangle
        
        Image imageB= new Image("GameActorR.jpg");
        ImagePattern imagePatternB = new ImagePattern(imageB);//Image Pattern For Add Image in Rectangle
        
        //Add Image For Circle
        Image imageC = new Image("Ball Actor.png");
        ImagePattern imagePatternC = new ImagePattern(imageC);
        
        //Add Image For Rectangle Bar
        Image imageRB = new Image("Reactangle Bar.jpg");
        ImagePattern imagePatternRB = new ImagePattern(imageRB);
        
        

        //Add Audio Track
        final URL resource = getClass().getResource("GamesBackground1.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer BackGroundMusic = new MediaPlayer(media);
       // mediaPlayer.play();
       //ADD Collision Track
        final URL resource2 = getClass().getResource("CollisionImpact.mp3");
        final Media media2 = new Media(resource2.toString());
        final MediaPlayer CollisionImpact = new MediaPlayer(media2);
        
       
        //score Label
        scoreLabel = new Label("Score 0");
        scoreLabel.setFont(new Font("Impact", 30));//Font Add
        scoreLabel.setTextFill(Color.CORAL);
       
        //prepare rectangle Scene
        double rex, rey=22,rw=20,rh=20;
        int index=0;
        //Scene For OOOO
        rex=120;
         for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			 r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
        rex+=21;rey-=21;
          for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			 r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
          rex=141;rey=22;
            for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			 r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
            for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			 r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
            //Scene For P
         rex=230;rey=22;
           for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			 r[index].setFill(imagePatternB);
            rey+=21;
            index++;
        }
           rex=251;rey=22;
           for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternB);
            rex+=21;
            index++;
        }
           rey=32.5;
              for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternB);
            rey+=23;
            index++;
        }
              rex=251;rey=74.5;
           for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternB);
            rex+=21;
            index++;
        }
           //scene For Q
         rex=340;rey=22;
             for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			 r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
          rex=361;rey-=21;
             for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			 r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        } 
         rex=361;rey=22;
                for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			 r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
              for(int i=0;i<5;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			 r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
        rex=382;rey=85;
              for(int i=0;i<4;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			 r[index].setFill(imagePatternAC);
            rey+=21;
            rex+=21;
            index++;
        }
         
      root.getChildren().addAll(r);//Add Rectangle Actor
    //preparing Rectangle Bar
        double recx = (scene.getWidth()/2)-50;
        double recy =scene.getHeight()-10;
        rectangleBar = new Rectangle(recx,recy,100,8);//set X axis,Y axis,Height and Width
        rectangleBar.setFill(imagePatternRB);//set Image instead of Color By Image Pattern Class
        
        //Preparing the Ball
        double radious = 8;
        double centerX = scene.getWidth() / 2;
        double centerY = recy-8;
        ball = new Circle(centerX, centerY, radious);//set Center X, Center Y axis and Radious
        ball.setFill(imagePatternC);// Set Image Instead Of Color By Image Pattern Class
        root.getChildren().add(ball);
        root.getChildren().add(rectangleBar);
         Button Stage2 = new Button("Next Stage");
        
        Stage2.setOnAction((ActionEvent e)->
        {
           BackGroundMusic.pause();
           DxBallRST Dx1 = new DxBallRST(Counter); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        });
       
       EventHandler<KeyEvent> Suspend;
         Suspend = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.P){
                 suspended = false;
                 event.consume();
            }
            }
        };
          EventHandler<KeyEvent> Resume;
         Resume = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R){
                     BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
                 suspended = true;
                 event.consume();
            }
            }
        };
           EventHandler<KeyEvent> Start;
            Start = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                  BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
             thread.start();// Call Thread Function
                event.consume();
            }
            }
        };
          double rx =20;
        
        //keyBoard Switch Move Right For RightArrow Button
         EventHandler<KeyEvent> MoveRight;
         MoveRight = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.RIGHT){
                 rectangleBar.setX(rectangleBar.getX()+rx);
                 event.consume();
            }
            }
        };
         //keyBoard Switch Move Right For LeftArrow Button
          EventHandler<KeyEvent> MoveLeft;
            MoveLeft = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT){
                 rectangleBar.setX(rectangleBar.getX()-rx);
                event.consume();
            }
            }
        };
             EventHandler<KeyEvent> Menu;
            Menu = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE){
                 BackGroundMusic.pause();
           DxBall Dx1 = new DxBall(); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            }
            }
        };
         
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveRight);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveLeft);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Start);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Suspend);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Resume);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Menu);
       
          hbox.getChildren().add(Stage2);
          vbox.getChildren().add(hbox);
          vbox.getChildren().add(scoreLabel);
          root.getChildren().add(vbox);
         
        
        thread = new Thread(this);
    }
}
class DxBallRST extends Application implements Runnable {
    Group root;
    Scene scene;
    Stage stage;
    Thread thread;
    boolean suspended=true;//For Thread Pause And Resume
    boolean stopped=false;// For Thread Start And Stop
    
     int Counter = 0;//For Count Score
	  DxBallRST(int Counter)
   {
       this.Counter = Counter;
   }
    Label scoreLabel;
    Circle ball;
    final int no = 49;
    Rectangle[] r = new Rectangle[no];
    Rectangle rectangleBar;
    URL resource2;
    Media media2;
    MediaPlayer CollisionImpact;

    @Override
    public void run() {
        boolean hDirection = true; //true indicates that the direction is rightward
        boolean vDirection = true; //true indicates that the direction is upward
        double dX = 4.5;
        double dY = 4.5;
        try {
            while (true) {
                if(suspended){
           
                thread.sleep(40);
                double ballLeftBound = ball.getCenterX() - ball.getRadius();
                double ballRightBound = ball.getCenterX() + ball.getRadius();
                double ballTopBound = ball.getCenterY() - ball.getRadius();
                double ballBottomBound = ball.getCenterY() + ball.getRadius();
                
                //Moving Horizontally
                if (hDirection == true) {
                    ball.setCenterX(ball.getCenterX() + dX);  //Moves rightward
                    if (ballRightBound > scene.getWidth()) {
                        hDirection = false;
                    }
                } else {
                    ball.setCenterX(ball.getCenterX() - dX); //Moves leftward
                    if (ballLeftBound < 0) {
                        hDirection = true;
                    }
                }
                   
                //Moving Verically
                if (vDirection == true) {
                    ball.setCenterY(ball.getCenterY() - dY); //Moves upward
                    if (ballTopBound < 0) {
                        vDirection = false;
                    }
                } else {
                    
                    ball.setCenterY(ball.getCenterY() + dY);  //Moves downward
                    
                    if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>rectangleBar.getX())&&(ball.getCenterX()<(rectangleBar.getX()+(rectangleBar.getWidth()/2))))
                    {
                          vDirection = true;
                            hDirection = false;
                    }
                    else if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>(rectangleBar.getX()+(rectangleBar.getWidth()/2)))&&(ball.getCenterX()<(rectangleBar.getX()+rectangleBar.getWidth())))
                    {
                         vDirection = true;
                         hDirection = true;
                    }
                }
                if (ballBottomBound > scene.getHeight()) {
                    break;                    
                }
                for(int i=0;i<no;i++)
                {
                     if (ball.intersects(r[i].getBoundsInLocal())&&r[i].getWidth()==20)
                     {
                         if(vDirection==true)
                         {
                             vDirection=false;
                         }
                        
                     }
                }
                Platform.runLater(()->{;
                for(int i=0;i<no;i++)
                {
                    if (ball.intersects(r[i].getBoundsInLocal()))
                    {
                          
                          r[i].setVisible(false);
                          
                          if(r[i].getWidth()==20){
                        //  CollisionImpact.play();
                          scoreLabel.setText("Score: "+(++Counter)*10);
                          scoreLabel.setFont(new Font("Impact", 30));//Font Add
                          scoreLabel.setTextFill(Color.CORAL);//Color Add
                          }
                          r[i].setWidth(21);
                    }
                 }
                });
                }
            }
        } catch (Exception ex) {

        }
    }
   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        scene = new Scene(root, 500, 300);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
        
        //Add Image
        final Image im1=new Image(DxBall.class.getResourceAsStream("Background2.png"));
        final ImageView iv1 = new ImageView(im1);
        root.getChildren().add(iv1);
        
        //Add Image For Rectangle Actor in Scene
        Image imageAC = new Image("GameActorG.jpg");
        ImagePattern imagePatternAC = new ImagePattern(imageAC);//Image Pattern For Add Image in Rectangle
        
        Image imageB= new Image("GameActorR.jpg");
        ImagePattern imagePatternB = new ImagePattern(imageB);//Image Pattern For Add Image in Rectangle
        
        //Add Image For Circle
        Image imageC = new Image("Ball Actor.png");
        ImagePattern imagePatternC = new ImagePattern(imageC);
        
        //Add Image For Rectangle Bar
        Image imageRB = new Image("Reactangle Bar.jpg");
        ImagePattern imagePatternRB = new ImagePattern(imageRB);
        
        

        //Add Audio Track
        final URL resource = getClass().getResource("GamesBackground1.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer BackGroundMusic = new MediaPlayer(media);
       // mediaPlayer.play();
       //ADD Collision Track
        final URL resource2 = getClass().getResource("CollisionImpact.mp3");
        final Media media2 = new Media(resource2.toString());
        final MediaPlayer CollisionImpact = new MediaPlayer(media2);
       
        //score Label
        scoreLabel = new Label("Score 0");
        scoreLabel.setFont(new Font("Impact", 30));//Font Add
        scoreLabel.setTextFill(Color.CORAL);
       
        //prepare rectangle Scene
        double rex, rey=22,rw=20,rh=20;
        int index=0;
        //Scene For R
        rex=120;
         for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
         rex=141;rey=22;
         for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        } 
         rex=141;rey=64;
        for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
        rey=32.5;
        for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
        rex=151.5;rey=85;
        for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternAC);
            rex+=21;
            rey+=10.5;
            index++;
        }
        rex-=10.5;rey+=10.5;
         r[index]=new Rectangle(rex,rey,rw,rh);
         r[index++].setFill(imagePatternAC);
        
         //Scene For S]
         rex=230;rey=22;
          for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternB);
           if(i==3)
               {
               rey+=21;
               }
           rey+=21;
           index++;
               
        }
          rex=314;rey=22;
            for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternB);
           if(i==1)
               {
               rey+=21;
               }
           rey+=21;
           index++;
               
        }
           rex=251;rey=22;
                for(int i=0;i<9;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternB);
             rex+=21;
           if(i==2||i==5)
               {
               rey+=63;
               rex=251;
               }
          
           index++;
        }
                //Scene For T
        rex=340;rey=22;
         for(int i=0;i<5;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
         rex=382;rey=43;
          for(int i=0;i<5;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
    root.getChildren().addAll(r);//Add Rectangle Actor
	
	
//preparing Rectangle Bar
        double recx = (scene.getWidth()/2)-50;
        double recy =scene.getHeight()-10;
        rectangleBar = new Rectangle(recx,recy,100,10);//set X axis,Y axis,Height and Width
        rectangleBar.setFill(imagePatternRB);//set Image instead of Color By Image Pattern Class
        
        //Preparing the Ball
        double radious = 8;
        double centerX = scene.getWidth() / 2;
        double centerY = recy-8;
        ball = new Circle(centerX, centerY, radious);//set Center X, Center Y axis and Radious
        ball.setFill(imagePatternC);// Set Image Instead Of Color By Image Pattern Class
        root.getChildren().add(ball);
        root.getChildren().add(rectangleBar);
         Button Stage2 = new Button("Next Stage");
        
        Stage2.setOnAction((ActionEvent e)->
        {
           BackGroundMusic.pause();
           DxBallUV Dx1 = new DxBallUV(Counter); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        });
       
       EventHandler<KeyEvent> Suspend;
         Suspend = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.P){
                 BackGroundMusic.pause(); suspended = false;
                 event.consume();
            }
            }
        };
          EventHandler<KeyEvent> Resume;
         Resume = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R){
                     BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
                 suspended = true;
                 event.consume();
            }
            }
        };
           EventHandler<KeyEvent> Start;
            Start = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                  BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
             thread.start();// Call Thread Function
                event.consume();
            }
            }
        };
          double rx =20;
        
        //keyBoard Switch Move Right For RightArrow Button
         EventHandler<KeyEvent> MoveRight;
         MoveRight = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.RIGHT){
                 rectangleBar.setX(rectangleBar.getX()+rx);
                 event.consume();
            }
            }
        };
         //keyBoard Switch Move Right For LeftArrow Button
          EventHandler<KeyEvent> MoveLeft;
            MoveLeft = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT){
                 rectangleBar.setX(rectangleBar.getX()-rx);
                event.consume();
            }
            }
        };
             EventHandler<KeyEvent> Menu;
            Menu = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE){
                 BackGroundMusic.pause();
           DxBall Dx1 = new DxBall(); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            }
            }
        };
         
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveRight);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveLeft);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Start);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Suspend);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Resume);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Menu);
       
          hbox.getChildren().add(Stage2);
          vbox.getChildren().add(hbox);
          vbox.getChildren().add(scoreLabel);
          root.getChildren().add(vbox);
         
        
        thread = new Thread(this);
}
}

 class DxBallUV extends Application implements Runnable {
    Group root;
    Scene scene;
    Stage stage;
    Thread thread;
    boolean suspended=true;//For Thread Pause And Resume
    boolean stopped=false;// For Thread Start And Stop
    
     int Counter = 0;//For Count Score
	 
 DxBallUV(int Counter)
   {
       this.Counter = Counter;
   }
    Label scoreLabel;
    Circle ball;
    final int no = 27;
    Rectangle[] r = new Rectangle[no];
    Rectangle rectangleBar;
    URL resource2;
    Media media2;
    MediaPlayer CollisionImpact;

    @Override
    public void run() {
        boolean hDirection = true; //true indicates that the direction is rightward
        boolean vDirection = true; //true indicates that the direction is upward
        double dX = 4.75;
        double dY = 4.75;
        try {
            while (true) {
                if(suspended){
           
                thread.sleep(40);
                double ballLeftBound = ball.getCenterX() - ball.getRadius();
                double ballRightBound = ball.getCenterX() + ball.getRadius();
                double ballTopBound = ball.getCenterY() - ball.getRadius();
                double ballBottomBound = ball.getCenterY() + ball.getRadius();
                
                //Moving Horizontally
                if (hDirection == true) {
                    ball.setCenterX(ball.getCenterX() + dX);  //Moves rightward
                    if (ballRightBound > scene.getWidth()) {
                        hDirection = false;
                    }
                } else {
                    ball.setCenterX(ball.getCenterX() - dX); //Moves leftward
                    if (ballLeftBound < 0) {
                        hDirection = true;
                    }
                }
                   
                //Moving Verically
                if (vDirection == true) {
                    ball.setCenterY(ball.getCenterY() - dY); //Moves upward
                    if (ballTopBound < 0) {
                        vDirection = false;
                    }
                } else {
                    
                    ball.setCenterY(ball.getCenterY() + dY);  //Moves downward
                    
                    if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>rectangleBar.getX())&&(ball.getCenterX()<(rectangleBar.getX()+(rectangleBar.getWidth()/2))))
                    {
                          vDirection = true;
                            hDirection = false;
                    }
                    else if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>(rectangleBar.getX()+(rectangleBar.getWidth()/2)))&&(ball.getCenterX()<(rectangleBar.getX()+rectangleBar.getWidth())))
                    {
                         vDirection = true;
                         hDirection = true;
                    }
                }
                if (ballBottomBound > scene.getHeight()) {
                    break;                    
                }
                for(int i=0;i<no;i++)
                {
                     if (ball.intersects(r[i].getBoundsInLocal())&&r[i].getWidth()==20)
                     {
                         if(vDirection==true)
                         {
                             vDirection=false;
                         }
                        
                     }
                }
                Platform.runLater(()->{;
                for(int i=0;i<no;i++)
                {
               if (ball.intersects(r[i].getBoundsInLocal()))
                    {
                          
                          r[i].setVisible(false);
                          
                          if(r[i].getWidth()==20){
                        //  CollisionImpact.play();
                          scoreLabel.setText("Score: "+(++Counter)*10);
                          scoreLabel.setFont(new Font("Impact", 30));//Font Add
                          scoreLabel.setTextFill(Color.CORAL);//Color Add
                          }
                          r[i].setWidth(21);
                    }
                 }
                });
                }
            }
        } catch (Exception ex) {

        }
    }
   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        scene = new Scene(root, 500, 300);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
        
        //Add Image
        final Image im1=new Image(DxBall.class.getResourceAsStream("Background2.png"));
        final ImageView iv1 = new ImageView(im1);
        root.getChildren().add(iv1);
        
        //Add Image For Rectangle Actor in Scene
        Image imageAC = new Image("GameActorG.jpg");
        ImagePattern imagePatternAC = new ImagePattern(imageAC);//Image Pattern For Add Image in Rectangle
        
        Image imageB= new Image("GameActorR.jpg");
        ImagePattern imagePatternB = new ImagePattern(imageB);//Image Pattern For Add Image in Rectangle
        
        //Add Image For Circle
        Image imageC = new Image("Ball Actor.png");
        ImagePattern imagePatternC = new ImagePattern(imageC);
        
        //Add Image For Rectangle Bar
        Image imageRB = new Image("Reactangle Bar.jpg");
        ImagePattern imagePatternRB = new ImagePattern(imageRB);
        
        

        //Add Audio Track
        final URL resource = getClass().getResource("GamesBackground1.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer BackGroundMusic = new MediaPlayer(media);
       // mediaPlayer.play();
       //ADD Collision Track
        final URL resource2 = getClass().getResource("CollisionImpact.mp3");
        final Media media2 = new Media(resource2.toString());
        final MediaPlayer CollisionImpact = new MediaPlayer(media2);
       
        //score Label
        scoreLabel = new Label("Score 0");
        scoreLabel.setFont(new Font("Impact", 30));//Font Add
        scoreLabel.setTextFill(Color.CORAL);
       
        //prepare rectangle Scene
        double rex, rey=22,rw=20,rh=20;
        int index=0;
        //Scene For U
        rex=120;
         for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternB);
            rey+=21;
            index++;
        }
         rex=141;rey-=21;
           for(int i=0;i<4;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternB);
            rex+=21;
            index++;
        }
           rex-=21;rey=22;
           for(int i=0;i<5;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternB);
            rey+=21;
            index++;
        } 
           //Scene for V
           rex=230;rey=22;
           for(int i=0;i<6;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternAC);
            rey+=21;
            rex+=10.5;
            index++;
        } 
           rex+=10.5;
               for(int i=0;i<6;i++)
        {  
            rey-=21;
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternAC);
            rex+=10.5;
            index++;
        } 
       root.getChildren().addAll(r);//Add Rectangle Actor
      //preparing Rectangle Bar
        double recx = (scene.getWidth()/2)-50;
        double recy =scene.getHeight()-10;
        rectangleBar = new Rectangle(recx,recy,100,10);//set X axis,Y axis,Height and Width
        rectangleBar.setFill(imagePatternRB);//set Image instead of Color By Image Pattern Class
        
        //Preparing the Ball
        double radious = 8;
        double centerX = scene.getWidth() / 2;
        double centerY = recy-8;
        ball = new Circle(centerX, centerY, radious);//set Center X, Center Y axis and Radious
        ball.setFill(imagePatternC);// Set Image Instead Of Color By Image Pattern Class
        root.getChildren().add(ball);
        root.getChildren().add(rectangleBar);
         Button Stage2 = new Button("Next Stage");
        
        Stage2.setOnAction((ActionEvent e)->
        {
           BackGroundMusic.pause();
           DxBallW Dx1 = new DxBallW(Counter); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        });
       
       EventHandler<KeyEvent> Suspend;
         Suspend = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.P){
               BackGroundMusic.pause();   suspended = false;
                 event.consume();
            }
            }
        };
          EventHandler<KeyEvent> Resume;
         Resume = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R){
                     BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
                 suspended = true;
                 event.consume();
            }
            }
        };
           EventHandler<KeyEvent> Start;
            Start = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                  BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
             thread.start();// Call Thread Function
                event.consume();
            }
            }
        };
          double rx =20;
        
        //keyBoard Switch Move Right For RightArrow Button
         EventHandler<KeyEvent> MoveRight;
         MoveRight = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.RIGHT){
                 rectangleBar.setX(rectangleBar.getX()+rx);
                 event.consume();
            }
            }
        };
         //keyBoard Switch Move Right For LeftArrow Button
          EventHandler<KeyEvent> MoveLeft;
            MoveLeft = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT){
                 rectangleBar.setX(rectangleBar.getX()-rx);
                event.consume();
            }
            }
        };
             EventHandler<KeyEvent> Menu;
            Menu = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE){
                 BackGroundMusic.pause();
           DxBall Dx1 = new DxBall(); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            }
            }
        };
         
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveRight);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveLeft);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Start);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Suspend);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Resume);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Menu);
       
          hbox.getChildren().add(Stage2);
          vbox.getChildren().add(hbox);
          vbox.getChildren().add(scoreLabel);
          root.getChildren().add(vbox);
         
        
        thread = new Thread(this);
    }
}
class DxBallW extends Application implements Runnable {
    Group root;
    Scene scene;
    Stage stage;
    Thread thread;
    boolean suspended=true;//For Thread Pause And Resume
    boolean stopped=false;// For Thread Start And Stop
    int Counter = 0;//For Count Score
	 DxBallW(int Counter)
   {
       this.Counter = Counter;
   }

    Label scoreLabel;
    Circle ball;
    final int no = 20;
    Rectangle[] r = new Rectangle[no];
    Rectangle rectangleBar;
    URL resource2;
    Media media2;
    MediaPlayer CollisionImpact;

    @Override
    public void run() {
        boolean hDirection = true; //true indicates that the direction is rightward
        boolean vDirection = true; //true indicates that the direction is upward
        double dX = 5.5;
        double dY = 5.5;
        try {
            while (true) {
                if(suspended){
           
                thread.sleep(40);
                double ballLeftBound = ball.getCenterX() - ball.getRadius();
                double ballRightBound = ball.getCenterX() + ball.getRadius();
                double ballTopBound = ball.getCenterY() - ball.getRadius();
                double ballBottomBound = ball.getCenterY() + ball.getRadius();
                
                //Moving Horizontally
                if (hDirection == true) {
                    ball.setCenterX(ball.getCenterX() + dX);  //Moves rightward
                    if (ballRightBound > scene.getWidth()) {
                        hDirection = false;
                    }
                } else {
                    ball.setCenterX(ball.getCenterX() - dX); //Moves leftward
                    if (ballLeftBound < 0) {
                        hDirection = true;
                    }
                }
                   
                //Moving Verically
                if (vDirection == true) {
                    ball.setCenterY(ball.getCenterY() - dY); //Moves upward
                    if (ballTopBound < 0) {
                        vDirection = false;
                    }
                } else {
                    
                    ball.setCenterY(ball.getCenterY() + dY);  //Moves downward
                    
                    if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>rectangleBar.getX())&&(ball.getCenterX()<(rectangleBar.getX()+(rectangleBar.getWidth()/2))))
                    {
                          vDirection = true;
                            hDirection = false;
                    }
                    else if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>(rectangleBar.getX()+(rectangleBar.getWidth()/2)))&&(ball.getCenterX()<(rectangleBar.getX()+rectangleBar.getWidth())))
                    {
                         vDirection = true;
                         hDirection = true;
                    }
                }
                if (ballBottomBound > scene.getHeight()) {
                    break;                    
                }
                for(int i=0;i<no;i++)
                {
                     if (ball.intersects(r[i].getBoundsInLocal())&&r[i].getWidth()==20)
                     {
                         if(vDirection==true)
                         {
                             vDirection=false;
                         }
                        
                     }
                }
                Platform.runLater(()->{;
                for(int i=0;i<no;i++)
                {
              if (ball.intersects(r[i].getBoundsInLocal()))
                    {
                          r[i].setVisible(false);
                           if(r[i].getWidth()==20){
                        //  CollisionImpact.play();
                          scoreLabel.setText("Score: "+(++Counter)*10);
                          scoreLabel.setFont(new Font("Impact", 30));//Font Add
                          scoreLabel.setTextFill(Color.CORAL);//Color Add
                          }
                          r[i].setWidth(21);
                    }
                 }
                });
                }
            }
        } catch (Exception ex) {

        }
    }
   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        scene = new Scene(root, 500, 300);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
        
        //Add Image
        final Image im1=new Image(DxBall.class.getResourceAsStream("Background2.png"));
        final ImageView iv1 = new ImageView(im1);
        root.getChildren().add(iv1);
        
        //Add Image For Rectangle Actor in Scene
        Image imageAC = new Image("GameActorG.jpg");
        ImagePattern imagePatternAC = new ImagePattern(imageAC);//Image Pattern For Add Image in Rectangle
        
        Image imageB= new Image("GameActorR.jpg");
        ImagePattern imagePatternB = new ImagePattern(imageB);//Image Pattern For Add Image in Rectangle
        
        //Add Image For Circle
        Image imageC = new Image("Ball Actor.png");
        ImagePattern imagePatternC = new ImagePattern(imageC);
        
        //Add Image For Rectangle Bar
        Image imageRB = new Image("Reactangle Bar.jpg");
        ImagePattern imagePatternRB = new ImagePattern(imageRB);
        
        

        //Add Audio Track
        final URL resource = getClass().getResource("GamesBackground1.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer BackGroundMusic = new MediaPlayer(media);
       // mediaPlayer.play();
       //ADD Collision Track
        final URL resource2 = getClass().getResource("CollisionImpact.mp3");
        final Media media2 = new Media(resource2.toString());
        final MediaPlayer CollisionImpact = new MediaPlayer(media2);
  
        //score Label
        scoreLabel = new Label("Score 0");
        scoreLabel.setFont(new Font("Impact", 30));//Font Add
        scoreLabel.setTextFill(Color.CORAL);
       
        //prepare rectangle Scene
        double rex, rey=22,rw=20,rh=20;
        int index=0;
        //Scene For R
        rex=120;
         for(int i=0;i<5;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternAC);
            rey+=21;
            rex+=21;
            index++;
        }
           for(int i=0;i<5;i++)
        {
            rey-=21;
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternAC);
            rex+=21;
            index++;
        }
           rex=183;
             for(int i=0;i<5;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternB);
            rey+=21;
            rex+=21;
            index++;
        }
           for(int i=0;i<5;i++)
        {
            rey-=21;
            r[index]=new Rectangle(rex,rey,rw,rh);
			r[index].setFill(imagePatternB);
            rex+=21;
            index++;
        }
      root.getChildren().addAll(r);//Add Rectangle Actor
      //preparing Rectangle Bar
        double recx = (scene.getWidth()/2)-50;
        double recy =scene.getHeight()-10;
        rectangleBar = new Rectangle(recx,recy,100,12);//set X axis,Y axis,Height and Width
        rectangleBar.setFill(imagePatternRB);//set Image instead of Color By Image Pattern Class
        
        //Preparing the Ball
        double radious = 8;
        double centerX = scene.getWidth() / 2;
        double centerY = recy-8;
        ball = new Circle(centerX, centerY, radious);//set Center X, Center Y axis and Radious
        ball.setFill(imagePatternC);// Set Image Instead Of Color By Image Pattern Class
        root.getChildren().add(ball);
        root.getChildren().add(rectangleBar);
         Button Stage2 = new Button("Next Stage");
        
        Stage2.setOnAction((ActionEvent e)->
        {
           BackGroundMusic.pause();
           DxBallXYZ Dx1 = new DxBallXYZ(Counter); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        });
       
       EventHandler<KeyEvent> Suspend;
         Suspend = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.P){
                 BackGroundMusic.pause(); suspended = false;
                 event.consume();
            }
            }
        };
          EventHandler<KeyEvent> Resume;
         Resume = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R){
                     BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
                 suspended = true;
                 event.consume();
            }
            }
        };
           EventHandler<KeyEvent> Start;
            Start = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                  BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
             thread.start();// Call Thread Function
                event.consume();
            }
            }
        };
          double rx =20;
        
        //keyBoard Switch Move Right For RightArrow Button
         EventHandler<KeyEvent> MoveRight;
         MoveRight = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.RIGHT){
                 rectangleBar.setX(rectangleBar.getX()+rx);
                 event.consume();
            }
            }
        };
         //keyBoard Switch Move Right For LeftArrow Button
          EventHandler<KeyEvent> MoveLeft;
            MoveLeft = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT){
                 rectangleBar.setX(rectangleBar.getX()-rx);
                event.consume();
            }
            }
        };
             EventHandler<KeyEvent> Menu;
            Menu = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE){
                 BackGroundMusic.pause();
           DxBall Dx1 = new DxBall(); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            }
            }
        };
         
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveRight);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveLeft);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Start);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Suspend);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Resume);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Menu);
       
          hbox.getChildren().add(Stage2);
          vbox.getChildren().add(hbox);
          vbox.getChildren().add(scoreLabel);
          root.getChildren().add(vbox);
         
        
        thread = new Thread(this);  
      
    }
}

class DxBallXYZ extends Application implements Runnable {
    Group root;
    Scene scene;
    Stage stage;
    Thread thread;
    boolean suspended=true;//For Thread Pause And Resume
    boolean stopped=false;// For Thread Start And Stop
    
    int Counter = 0;//For Count Score
	
 DxBallXYZ(int Counter)
   {
       this.Counter = Counter;
   }
    Label scoreLabel;
    Circle ball;
    final int no = 17;
    Rectangle[] r = new Rectangle[no];
    Rectangle rectangleBar;
    URL resource2;
    Media media2;
    MediaPlayer CollisionImpact;

    @Override
    public void run() {
        boolean hDirection = true; //true indicates that the direction is rightward
        boolean vDirection = true; //true indicates that the direction is upward
        double dX = 5.5;
        double dY = 5.5;
        try {
            while (true) {
                if(suspended){
           
                thread.sleep(40);
                double ballLeftBound = ball.getCenterX() - ball.getRadius();
                double ballRightBound = ball.getCenterX() + ball.getRadius();
                double ballTopBound = ball.getCenterY() - ball.getRadius();
                double ballBottomBound = ball.getCenterY() + ball.getRadius();
                
                //Moving Horizontally
                if (hDirection == true) {
                    ball.setCenterX(ball.getCenterX() + dX);  //Moves rightward
                    if (ballRightBound > scene.getWidth()) {
                        hDirection = false;
                    }
                } else {
                    ball.setCenterX(ball.getCenterX() - dX); //Moves leftward
                    if (ballLeftBound < 0) {
                        hDirection = true;
                    }
                }
                   
                //Moving Verically
                if (vDirection == true) {
                    ball.setCenterY(ball.getCenterY() - dY); //Moves upward
                    if (ballTopBound < 0) {
                        vDirection = false;
                    }
                } else {
                    
                    ball.setCenterY(ball.getCenterY() + dY);  //Moves downward
                    
                    if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>rectangleBar.getX())&&(ball.getCenterX()<(rectangleBar.getX()+(rectangleBar.getWidth()/2))))
                    {
                          vDirection = true;
                            hDirection = false;
                    }
                    else if((ballBottomBound >rectangleBar.getY()) &&
                        (ball.getCenterX()>(rectangleBar.getX()+(rectangleBar.getWidth()/2)))&&(ball.getCenterX()<(rectangleBar.getX()+rectangleBar.getWidth())))
                    {
                         vDirection = true;
                         hDirection = true;
                    }
                }
                if (ballBottomBound > scene.getHeight()) {
                    break;                    
                }
                for(int i=0;i<no;i++)
                {
                     if (ball.intersects(r[i].getBoundsInLocal())&&r[i].getWidth()==20)
                     {
                         if(vDirection==true)
                         {
                             vDirection=false;
                         }
                        
                     }
                }
                Platform.runLater(()->{;
                for(int i=0;i<no;i++)
                {
                    if (ball.intersects(r[i].getBoundsInLocal()))
                    {
                          
                          r[i].setVisible(false);
                          
                          if(r[i].getWidth()==20){
                        //  CollisionImpact.play();
                          scoreLabel.setText("Score: "+(++Counter)*10);
                          scoreLabel.setFont(new Font("Impact", 30));//Font Add
                          scoreLabel.setTextFill(Color.CORAL);//Color Add
                          }
                          r[i].setWidth(21);
                    }
                 }
                });
                }
            }
        } catch (Exception ex) {

        }
    }
   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        scene = new Scene(root, 500, 300);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
        
        //Add Image
        final Image im1=new Image(DxBall.class.getResourceAsStream("Background2.png"));
        final ImageView iv1 = new ImageView(im1);
        root.getChildren().add(iv1);
        
        //Add Image For Rectangle Actor in Scene
        Image imageAC = new Image("GameActorG.jpg");
        ImagePattern imagePatternAC = new ImagePattern(imageAC);//Image Pattern For Add Image in Rectangle
        
        Image imageB= new Image("GameActorR.jpg");
        ImagePattern imagePatternB = new ImagePattern(imageB);//Image Pattern For Add Image in Rectangle
        
        //Add Image For Circle
        Image imageC = new Image("Ball Actor.png");
        ImagePattern imagePatternC = new ImagePattern(imageC);
        
        //Add Image For Rectangle Bar
        Image imageRB = new Image("Reactangle Bar.jpg");
        ImagePattern imagePatternRB = new ImagePattern(imageRB);
        
        

        //Add Audio Track
        final URL resource = getClass().getResource("GamesBackground1.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer BackGroundMusic = new MediaPlayer(media);
       // mediaPlayer.play();
       //ADD Collision Track
        final URL resource2 = getClass().getResource("CollisionImpact.mp3");
        final Media media2 = new Media(resource2.toString());
        final MediaPlayer CollisionImpact = new MediaPlayer(media2);
        
        
        //score Label
        scoreLabel = new Label("Score 0");
        scoreLabel.setFont(new Font("Impact", 30));//Font Add
        scoreLabel.setTextFill(Color.CORAL);
       
        //prepare rectangle Scene
        double rex, rey=22,rw=20,rh=20;
        int index=0;
        rex=120;
        //Scene For X
        for(int i=0;i<5;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
            r[index].setFill(imagePatternB);
            rey+=21;
            rex+=21;
            index++;
        }
        rex=204;rey=22;
         for(int i=0;i<5;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternB);
            rey+=21;
            rex-=21;
            index++;
        }
         //Scene For Y
         rex=230;rey=22;
          for(int i=0;i<3;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rey+=21;
            rex+=21;
            index++;
        } 
          rex=314;rey=22;
           for(int i=0;i<2;i++)
        {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rey+=21;
            rex-=21;
            index++;
        }
           rey+=21;
           for(int i=0;i<2;i++)
            {
            r[index]=new Rectangle(rex,rey,rw,rh);
             r[index].setFill(imagePatternAC);
            rey+=21;
            index++;
        }
       //scene for z
     /*  rex=340;rey=22;
          for(int i=0;i<2;i++)
            {
            r[index]=new Rectangle(rex,rey,rw,rh);
            rey+=21;
            index++;
           }  
        */
        
        
        root.getChildren().addAll(r);//Add Rectangle Actor
        
       double recx = (scene.getWidth()/2)-50;
        double recy =scene.getHeight()-10;
        rectangleBar = new Rectangle(recx,recy,100,12);//set X axis,Y axis,Height and Width
        rectangleBar.setFill(imagePatternRB);//set Image instead of Color By Image Pattern Class
        
        //Preparing the Ball
        double radious = 8;
        double centerX = scene.getWidth() / 2;
        double centerY = recy-8;
        ball = new Circle(centerX, centerY, radious);//set Center X, Center Y axis and Radious
        ball.setFill(imagePatternC);// Set Image Instead Of Color By Image Pattern Class
        root.getChildren().add(ball);
        root.getChildren().add(rectangleBar);
         Button Stage2 = new Button("Next Stage");
        
        Stage2.setOnAction((ActionEvent e)->
        {
           BackGroundMusic.pause();
           DxBall Dx1 = new DxBall(); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        });
       
       EventHandler<KeyEvent> Suspend;
         Suspend = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.P){
                 BackGroundMusic.pause(); suspended = false;
                 event.consume();
            }
            }
        };
          EventHandler<KeyEvent> Resume;
         Resume = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R){ BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
                    
                 suspended = true;
                 event.consume();
            }
            }
        };
           EventHandler<KeyEvent> Start;
            Start = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                  BackGroundMusic.play();//Start Background Music
             BackGroundMusic.setCycleCount(10);
             thread.start();// Call Thread Function
                event.consume();
            }
            }
        };
          double rx =20;
        
        //keyBoard Switch Move Right For RightArrow Button
         EventHandler<KeyEvent> MoveRight;
         MoveRight = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.RIGHT){
                 rectangleBar.setX(rectangleBar.getX()+rx);
                 event.consume();
            }
            }
        };
         //keyBoard Switch Move Right For LeftArrow Button
          EventHandler<KeyEvent> MoveLeft;
            MoveLeft = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT){
                 rectangleBar.setX(rectangleBar.getX()-rx);
                event.consume();
            }
            }
        };
             EventHandler<KeyEvent> Menu;
            Menu = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE){
                 BackGroundMusic.pause();
           DxBall Dx1 = new DxBall(); 
           try {
            Dx1.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            }
            }
        };
         
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveRight);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,MoveLeft);// Add Event Handler Into Scene
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Start);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Suspend);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Resume);
       scene.addEventHandler(KeyEvent.KEY_PRESSED,Menu);
       
          hbox.getChildren().add(Stage2);
          vbox.getChildren().add(hbox);
          vbox.getChildren().add(scoreLabel);
          root.getChildren().add(vbox);
         
        
        thread = new Thread(this);
        
       // thread.start(); //calls the run function
    }
}
