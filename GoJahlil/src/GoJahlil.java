// This entire file is part of my masterpiece.
// Wuming Zhang

import java.util.ArrayList;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;




class GoJahlil {
    private final String TITLE = "Go Jahlil!";
    private int KEY_INPUT_SPEED = 40;
    private double GROWTH_RATE = 1.1;
    private int SPEED_COEFF = 50;
    
    private int PlusShot = 4;
    private int MinusJustise = 10;
    private int MinusGrayson = 5;
    private final int menuWidth = 1035;
    private final int menuHeight = 690;
    private final int offenseWidth = 1200;
    private final int offenseHeight = 675;
    private final int winWidth = 1200;
    private final int winHeight = 675;
    private final int loseWidth = 1152;
    private final int loseHeight = 677;
    private final int practiceWidth = 1024;
    private final int practiceHeight = 669;
    private final int longDistBtw = 280;
    private final int shortDistBtw = 145;
    

    private Scene myScene;
    private ArrayList<GameObject> myBasket = new ArrayList<GameObject>();
    private ArrayList<GameObject> myGrayson = new ArrayList<GameObject>();
    private ArrayList<GameObject> myJustise = new ArrayList<GameObject>();
    private ArrayList<GameObject> myPracticeBall = new ArrayList<GameObject>();

    private GameObject myGameBall;
   
    private GameObject myJa;
    private GameObject mySmallJa;
    private GameObject bang;
    private GameObject rejection;
    private GameObject reaper;
    
    private double myX; 
    private double myY;
    
    private boolean checkIn = false;
    private int stayTime;
    private int stayTime2;
    private int stayTime3;
    private int myScore;
    private int myMode;
    private int myRecord;
    
    private Stage s;
    
    private Text t;
    private Text tRec;
    
    public GoJahlil(Stage s) {
    	this.s = s;
    	for (int i = 0; i <= 3; i++)
    	{
    		myBasket.add(new GameObject(Math.random()*3+1, "basket.png", 1));
    		myJustise.add(new GameObject(Math.random()*4+2, "justise.png", 1));
    	}  
    	for (int i = 0; i <= 7; i++)
    	{
    		myGrayson.add(new GameObject(Math.random()*4+2, "grayson.png", 1));
    	} 
    	for (int i = 0; i <= 19; i++)
    	{
    		myPracticeBall.add(new GameObject(Math.random()*1+1, "ball.png", 1));
    	} 
    }


    /**
     * Returns name of the game.
     */
    public String getTitle () {
        return TITLE;
    }

    /**
     * Create the game's scene
     */
    public Scene init (int width, int height, Stage sta) {
    	
    	
    	Group root = new Group();
    	putSceneElements(root, width, height);

    	initGameObjects();
        return myScene;
    }


/**
 * To initialize all the objects of the game
 * Note: this method is long, but it is inevitable because all the objects are customized and cannot be merged
 */
    
	private void initGameObjects() {
		myScore = 20;
    	myRecord = 0;
		
		for (int i = 0; i <= 3; i++)
    	{
			resetToDefault(myBasket,i,Math.random()*3+1);
			resetToDefault(myJustise,i,Math.random()*4+2);
    	}    	
    	for (int i = 0; i <= 7; i++)
    	{
    		resetToDefault(myGrayson,i,Math.random()*4+2);
    	}   
    	for (int i = 0; i <= 19; i++)
    	{
    		resetToDefault(myPracticeBall, i, Math.random()*1+1);
    	} 
    	    	
    	t = new Text (550, 60, Integer.toString(myScore));
        tRec = new Text (500, 40, Integer.toString(myRecord));
        
        myGameBall = new GameObject(10,"ball.png",1);
   
        rejection = new GameObject(1, "rejection.png", 1);
        rejection.getImage().setVisible(false);
       
        myJa = new GameObject(1, "head.png", 1);
        
        bang = new GameObject(1, "bang.png", 1);
        bang.getImage().setVisible(false);
        
        reaper = new GameObject(1, "reaper.png", 1);
        reaper.getImage().setVisible(false);
        
        mySmallJa = new GameObject(1, "smallhead.png", 1);
        
	}


	private void resetToDefault(ArrayList<GameObject> b, int i, double speed) {
		b.get(i).setMySpeed(speed);
		b.get(i).resetEverything();
	}


	private void putSceneElements(Group root, int width, int height) {
		
        myScene = new Scene(root, width, height, Color.WHITE);
		Canvas canvas = new Canvas( menuWidth, menuHeight );
        root.getChildren().add( canvas );
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Image background = new Image( "Jahlil1.jpg" );
        gc.drawImage( background, 0, 0 );
        
        Image logo = new Image("logo3.png");
        gc.drawImage( logo, 0, 32 );
                
        addButton(root, "Offense Mode", 1, 50, 300);
        addButton(root, "Practise Mode", 2, 50, 340);
	}

	private Button addButton(Group root, String str, int modeNum, int posX, int posY) {
		Button btn = new Button();
        btn.setText(str);
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            public void handle(ActionEvent event) {
            	myMode = modeNum;
            	if (modeNum == 1)
            	{
            		s.setScene(OffenseScene(s));
            	}
            	else
            	{
            		s.setScene(PracticeScene(s));
            	}
            }
        });      
        btn.setLayoutX(posX);
        btn.setLayoutY(posY);       
        root.getChildren().add(btn);        
        return btn;
	}
    

    public Scene ResultScene(Stage sta, int width, int height, String str){
    	
    	Group root = new Group();
    	setupScene(root, width, height, str);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        
    	return myScene;
    }
    	
    public Scene PracticeScene(Stage sta)
    {
    	Group root = new Group();
        
    	setupScene(root,practiceWidth,practiceHeight,"Stadium.jpg");
        
        for (int i = 0; i <= 19; i++)
        {
        	myPracticeBall.get(i).getImage().setX(practiceWidth*Math.random());
        	myPracticeBall.get(i).getImage().setY(400*Math.random());
        }
        for (int i = 0; i <= 19; i++)
        {
        	root.getChildren().add(myPracticeBall.get(i).getImage());
        }
        mySmallJa.getImage().setX(512);
        mySmallJa.getImage().setY(610);
        root.getChildren().add(mySmallJa.getImage());
        
        root.getChildren().add(tRec);
       
        myScene.setOnKeyPressed(e -> handleKeyInput2(e.getCode()));
    	return myScene;	
    }
    
	public Scene OffenseScene(Stage sta) {

    	Group root = new Group();
        
    	setupScene(root,offenseWidth,offenseHeight,"Offense.png");
        
    	resetObjectToDefault(myBasket, 4, longDistBtw, 0);
    	resetObjectToDefault(myGrayson, 8, shortDistBtw, 0);
    	resetObjectToDefault(myJustise, 4, longDistBtw, 650);
               
        myJa.getImage().setX(600 -myJa.getImage().getBoundsInLocal().getWidth() / 2);
        myJa.getImage().setY(610 - myJa.getImage().getBoundsInLocal().getHeight() / 2); 
        
        myGameBall.getImage().setX(myJa.getImage().getX());
        myGameBall.getImage().setY(myJa.getImage().getY());
        rootAdd(root);
        
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        return myScene;
    }


	private void rootAdd(Group root) {
		for (int j = 0; j <= 3; j++)
        {
        	root.getChildren().add(myBasket.get(j).getImage());
        	root.getChildren().add(myJustise.get(j).getImage());
        }
        
        for (int j = 0; j <= 7; j++)
        {
        	root.getChildren().add(myGrayson.get(j).getImage());
        }

        root.getChildren().add(myJa.getImage());
        root.getChildren().add(myGameBall.getImage());
        root.getChildren().add(bang.getImage());
        root.getChildren().add(rejection.getImage());
        root.getChildren().add(reaper.getImage());
	}


	private void setupScene(Group root,int width, int height, String str) {
		myScene = new Scene(root, width, height, Color.WHITE);
   	
    	root.getChildren().add(t);
    	
        Canvas canvas = new Canvas( width, height );
        root.getChildren().add( canvas );
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Image img = new Image( str );
        gc.drawImage( img, 0, 0 );
        
        
	}

	private void resetObjectToDefault(ArrayList<GameObject> myObject, int numObject, int widthBtw, int posY){
		for (int i = 0; i <= numObject-1; i++)
        {
			myObject.get(i).getImage().setX(widthBtw*i+widthBtw*Math.random());
			myObject.get(i).getImage().setY(posY);  
        }
	}  

    private void setEnemySpeedCoeff(int c, ArrayList b, int num){
    	for (int i = 0; i <= num-1; i++)
    	{
    		((GameObject) b.get(i)).setMyCoeff(c);
    	}
    }
    
    public void step (double elapsedTime) {
        // update the score
    	if (myMode == 1)
    	{
    		stepOffense(elapsedTime);
    	}
    	if (myMode == 2)
    	{
    		stepPractice(elapsedTime);
    	}
    }

    private void stepPractice(double elapsedTime) {
    	
    	displayText(tRec, Integer.toString(myRecord/60)+" s", 50);
    	
    	for (int i = 0; i <= 19; i++)
    	{
    		if (Math.floorMod(myRecord, 300) == 0)
    		{
    			myPracticeBall.get(i).setMySpeed(myPracticeBall.get(i).getMySpeed() + 0.5);
    		}
    		if (myPracticeBall.get(i).getImage().getX() < 0 || myPracticeBall.get(i).getImage().getX() > practiceWidth - myPracticeBall.get(i).getImage().getBoundsInLocal().getWidth()/2)
    		{
    			myPracticeBall.get(i).setXDirection(myPracticeBall.get(i).getXDirection()*(-1));
    		}
    		if (myPracticeBall.get(i).getImage().getY() < 0 || myPracticeBall.get(i).getImage().getY() > practiceHeight - myPracticeBall.get(i).getImage().getBoundsInLocal().getHeight()/2)
    		{
    			myPracticeBall.get(i).setYDirection(myPracticeBall.get(i).getYDirection()*(-1));
    		}
    		myPracticeBall.get(i).getImage().setX( myPracticeBall.get(i).getImage().getX() + myPracticeBall.get(i).getXDirection() * myPracticeBall.get(i).getMySpeed() * SPEED_COEFF * elapsedTime);
    		myPracticeBall.get(i).getImage().setY( myPracticeBall.get(i).getImage().getY() + myPracticeBall.get(i).getYDirection() * myPracticeBall.get(i).getMySpeed() * SPEED_COEFF * elapsedTime);
    		
    		if (myPracticeBall.get(i).getImage().getLayoutBounds().intersects(mySmallJa.getImage().getBoundsInLocal()))
    		{
    			myRecord = 0;
    			s.setScene(ResultScene(s, loseWidth, loseHeight, "Lose.jpg"));
    		}
     	}
    	
    	myRecord++;
    	
    }
    
    

	private void displayText(Text text, String str, int num) {
		text.setText(str);
    	text.setFont(Font.font ("Verdana",FontWeight.BOLD, num));
    	text.setFill(Color.DARKBLUE);
	}
	
	private void stepOffense(double elapsedTime) {
		
		checkWinOrLose();
    	
    	setEnemySpeedCoeff(myScore,myGrayson,8);
    	setEnemySpeedCoeff(myScore-50,myJustise,4);
    	
    	displayText(t, Integer.toString(myScore),40);

    	bang.getImage().setVisible(false);
    	rejection.getImage().setVisible(false);
    	
    	stayTime = keepDisp(stayTime, bang, 20);
    	stayTime2 = keepDisp(stayTime2, rejection, 20);
    	stayTime3 = keepDisp(stayTime3, reaper, 60);
    	
    	setObjectMovement(elapsedTime);  
        
        resetObjectPosition();     
	}

/**
 * This method is long because different object has different behavior in the case of repositionin. 
 */
	private void resetObjectPosition() {
		for (int l = 0; l <= 3; l++)
        {
        	resetObject(l, myBasket, longDistBtw, 2000);   
        	if (myScore > 70)
 	    	{
 	        	resetObject(l, myJustise, longDistBtw, 1000);
 	    	}
        }

        for (int l = 0; l <= 7; l++)
        {
        	resetObject(l,myGrayson,shortDistBtw,700);
        }        
        if (checkIn == false)
        {
        	if (myGameBall.getImage().getX() <= 0 || myGameBall.getImage().getX() > offenseWidth || myGameBall.getImage().getY() <= 0 || myGameBall.getImage().getY() > offenseWidth)
        	{
        		myGameBall.getImage().setX(myJa.getImage().getX());
        		myGameBall.getImage().setY(myJa.getImage().getY());
        		checkIn = true;
        	}
        }      
        
             
        for (int m = 0; m <= 3; m++)
        {
    		if (Math.abs(getDiffX(myGameBall, myBasket.get(m))) < 45 && Math.abs(getDiffY(myGameBall, myBasket.get(m))) < 45)
        	{
            	fancyEffect(m, bang, myBasket, longDistBtw);
            	stayTime++;
            	myScore = myScore+PlusShot;
    		}
        }       
        
        getAttacked(8, myGrayson, MinusGrayson, shortDistBtw);
        
        if (myScore > 70)
        {
        	getAttacked(4, myJustise, MinusJustise, longDistBtw);
        }
	}


	private void setObjectMovement(double elapsedTime) {
		double xDiff = myX - (myJa.getImage().getX() + myJa.getImage().getFitWidth());
        double yDiff = myY - (myJa.getImage().getY() + myJa.getImage().getFitHeight());
        double lDiff = Math.sqrt(xDiff*xDiff+yDiff*yDiff);
        if (myX > 0 && myY > 0 && checkIn == false)
        {
        	myGameBall.getImage().setX(myGameBall.getImage().getX() + myGameBall.getMySpeed() * 3 * (xDiff / lDiff) * SPEED_COEFF * elapsedTime);
        	myGameBall.getImage().setY(myGameBall.getImage().getY() + myGameBall.getMySpeed() * 3 * (yDiff / lDiff) * SPEED_COEFF * elapsedTime);
        }   		
		for (int k = 0; k <= 3; k++)
    	{
    		setMyY(elapsedTime, k, myBasket, 1);
    		if (myScore > 70)
    		{
    			setMyY(elapsedTime, k, myJustise, 1);
    		}
    		else
    		{
    			myJustise.get(k).getImage().setY(700);
    		}
      	}   		
    	for (int k = 0; k <= 7; k++)
    	{
    		setMyY(elapsedTime, k, myGrayson, 1);
    		if (myScore > 85)
    		{
    			myGrayson.get(k).getImage().setX(myGrayson.get(k).getImage().getX() + (Math.random()*6-3)*myGrayson.get(k).getMySpeed() * SPEED_COEFF * elapsedTime);
    		}
     	}   	
        myGameBall.getImage().setRotate(myGameBall.getImage().getRotate()+1);
	}


	private void checkWinOrLose() {
		if (myScore >= 100)
    	{
    		s.setScene(ResultScene(s, winWidth, winHeight, "Win.jpg"));
    	}
    	if (myScore < 0)
    	{
    		s.setScene(ResultScene(s, loseWidth, loseHeight, "Lose.jpg"));
    	}
	}


	public void getAttacked(int numDefender, ArrayList<GameObject> b, int minus, int distBtw) {
		for (int m = 0; m <=numDefender-1; m++)
        {
        	if (Math.abs(getDiffX(myGameBall, b.get(m))) < 45 && Math.abs(getDiffY(myGameBall, b.get(m))) < 45)
        	{
        		if (ballStillInHand())
            	{
        			stayTime3++;
            		myScore = myScore - minus;
            	}        		
        		fancyEffect(m, rejection, b, distBtw);
            	stayTime2++;
            }
        }
	}
	
	private boolean ballStillInHand() {
		return myGameBall.getImage().getX() == myJa.getImage().getX() && myGameBall.getImage().getY() == myJa.getImage().getY();
	}
	
	private double getDiffX(GameObject myObject1, GameObject myObject2) {
		return  (myObject1.getImage().getX()+myObject1.getImage().getBoundsInLocal().getWidth()/2) - (myObject2.getImage().getX() + myObject2.getImage().getBoundsInLocal().getWidth()/2) ;
	}
	private double getDiffY(GameObject myObject1, GameObject myObject2) {
		return  (myObject1.getImage().getY()+myObject1.getImage().getBoundsInLocal().getHeight()/2) - (myObject2.getImage().getY() + myObject2.getImage().getBoundsInLocal().getHeight()/2) ;
	}


	private void fancyEffect(int m, GameObject b, ArrayList<GameObject> a, int num) {
		b.getImage().setX(getDiffX(a.get(m), b) + b.getImage().getX());
		b.getImage().setY(getDiffY(a.get(m), b) + b.getImage().getY());
		b.getImage().setVisible(true);
		myGameBall.getImage().setX(myJa.getImage().getX());
		myGameBall.getImage().setY(myJa.getImage().getY());
		checkIn = true;
		
		a.get(m).getImage().setX(num*m+num*Math.random());
		a.get(m).getImage().setY(0);
		a.get(m).setMySpeed(4*Math.random()+2);
	}


	private void resetObject(int l, ArrayList<GameObject> a, int num, int num2) {
		if (a.get(l).getImage().getY() > num2)
		{
			a.get(l).getImage().setX(num*l+num*Math.random());
			a.get(l).getImage().setY(0);
			a.get(l).setMySpeed(4*Math.random()+2);
		}
	}


	private void setMyY(double elapsedTime, int k, ArrayList<GameObject> b, double coeff) {
		b.get(k).getImage().setY(b.get(k).getImage().getY() + coeff * b.get(k).getMySpeed() * SPEED_COEFF * elapsedTime);
	}


	private int keepDisp(int time, GameObject b, int num) {
		if (time > 0 && time < num)
    	{
    		b.getImage().setVisible(true);
    		if (num == 60)
    		{
    			b.getImage().setX(myScene.getWidth()/2 - b.getImage().getBoundsInLocal().getWidth()/2);
    			b.getImage().setY(myScene.getHeight()/2 - b.getImage().getBoundsInLocal().getHeight()/2);
    		}
    		else
    		{
    			b.getImage().setX(b.getImage().getX());
    			b.getImage().setY(b.getImage().getY());
    		}
    		time++;
    	}
    	else
    	{
    		b.getImage().setVisible(false);
    		if (time >= num)
    		{
    			time = 0;
    		}
    	}
		return time;
	}


	/**
	 * This method is long because there are a lot of different inputs with different actions. They cannot be merged together
	 */
    private void handleKeyInput (KeyCode code) {
        switch (code) {
            case D:
            	if (ballStillInHand())
                {
            		myJa.getImage().setX(myJa.getImage().getX() + KEY_INPUT_SPEED); 
            		myGameBall.getImage().setX(myJa.getImage().getX());
                }
            	else
            	{
            		myJa.getImage().setX(myJa.getImage().getX() + KEY_INPUT_SPEED);
            	}                
                break;
            case A:
            	if (ballStillInHand())
                {
            		myJa.getImage().setX(myJa.getImage().getX() - KEY_INPUT_SPEED); 
            		myGameBall.getImage().setX(myJa.getImage().getX());
                }
            	else
            	{
            		myJa.getImage().setX(myJa.getImage().getX() - KEY_INPUT_SPEED);
            	}
                break;
            case W:
            	if (ballStillInHand())
                {
            		myJa.getImage().setY(myJa.getImage().getY() - KEY_INPUT_SPEED); 
            		myGameBall.getImage().setY(myJa.getImage().getY());
                }
            	else
            	{
            		myJa.getImage().setY(myJa.getImage().getY() - KEY_INPUT_SPEED);
            	}
                break;
            case S:
            	if (ballStillInHand())
                {
            		myJa.getImage().setY(myJa.getImage().getY() + KEY_INPUT_SPEED); 
            		myGameBall.getImage().setY(myJa.getImage().getY());
                }
            	else
            	{
            		myJa.getImage().setY(myJa.getImage().getY() - KEY_INPUT_SPEED);
            	}
                break;
            case ESCAPE:
            	s.setScene(init(menuWidth,menuHeight,s));
            	myMode = 0;
                break;
            case UP:            	
        		PlusShot = 10;            		               
        		break;
            case DOWN:            	
        		PlusShot = 4;            		               
        		break;
            case LEFT:            	
        		SPEED_COEFF = 10;            		               
        		break;
            case RIGHT:            	
            	SPEED_COEFF  = 50;            		               
        		break;
            default:

        }
    }
    
    private void handleKeyInput2 (KeyCode code) {
        switch (code) {
            case D:            	
            		mySmallJa.getImage().setX(mySmallJa.getImage().getX() + KEY_INPUT_SPEED);             		               
                break;
            case A:
            		mySmallJa.getImage().setX(mySmallJa.getImage().getX() - KEY_INPUT_SPEED);
                break;
            case W:
            		mySmallJa.getImage().setY(mySmallJa.getImage().getY() - KEY_INPUT_SPEED);
                break;
            case S:
            		mySmallJa.getImage().setY(mySmallJa.getImage().getY() + KEY_INPUT_SPEED); 
            	break;
            case ESCAPE:
            	s.setScene(init(menuWidth,menuHeight,s));
            	myMode = 0;
                break;

            default:

        }
    }

    // What to do each time a key is pressed
    private void handleMouseInput (double x, double y) {
    	if (ballStillInHand())
    	{
    		myScore = myScore - 1;
    		myX = x;
    		myY = y;
    	}
    	
    	checkIn = false;
     
    }
}


