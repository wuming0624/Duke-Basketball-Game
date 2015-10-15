// This entire file is part of my masterpiece.
// Wuming Zhang
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameObject {
	private double mySpeed;
	private Image myImage;
	private ImageView myBasket;
	private double speedCoeff;
	private int speedUpTimes;
	private int myXDirection;
	private int myYDirection;
	public GameObject(double speed, String s, double coeff) {
		myXDirection = 1;
		myYDirection = 1;
		speedUpTimes = 0;
		speedCoeff = coeff;
		mySpeed = speed;
		myImage = new Image(getClass().getClassLoader().getResourceAsStream(s));
		myBasket = new ImageView(myImage);
	}	
	public double getMySpeed(){
		return speedCoeff*mySpeed;
	}
	public int getXDirection(){
		return myXDirection;
	}
	public void setXDirection(int X){
		myXDirection = X;
	}
	public int getYDirection(){
		return myYDirection;
	}
	public void setYDirection(int Y){
		myYDirection = Y;
	}
	public void setMySpeed(double myNewSpeed){
		mySpeed = myNewSpeed;
	}
	public void setMySpeedUpTimes(int sut){
		speedUpTimes = sut;
	}
	public void setMyCoeff(int coefficient)
	{
		if (coefficient < 40 && speedUpTimes == 0)
		{
			speedCoeff = 1;
			speedUpTimes++;
		}
		if (coefficient >= 40 && coefficient < 60 && speedUpTimes == 1)
		{
			speedCoeff = 1.4;
			speedUpTimes++;
		}
		if (coefficient >= 60 && coefficient < 80 && speedUpTimes == 2)
		{
			speedCoeff = 1.8;
			speedUpTimes++;
		}
		if (coefficient >= 80 && coefficient < 90 && speedUpTimes == 3)
		{
			speedCoeff = 2.2;
			speedUpTimes++;
		}
		if (coefficient >= 90 && speedUpTimes == 3)
		{
			speedCoeff = 3;
			speedUpTimes++;
		}		
	}
	public void resetEverything(){
		myXDirection = 1;
		myYDirection = 1;
		speedUpTimes = 0;
		speedCoeff = 1;
	}
	public ImageView getImage(){
		return myBasket;
	}

}
