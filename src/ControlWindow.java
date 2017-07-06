import processing.core.PApplet;

public class ControlWindow extends PApplet {
	
	Arduinos arduino;
	TactileGrapherController tactileGrapherController;
	
	String[] windows = {"Single Finger JND", "Single Finger JND Tester ", "Multi Finger JND", "Multi Finger JND Tester","Grasp Size Test", "Pinch Test", "Pinch Test 2", "Print Results", "Quit"};
	boolean initiated[];

	
	ControlWindow(){
		arduino = new Arduinos(this);	  
		String[] a = {""};
		PApplet.runSketch(a, this);
	}
	
	  public void settings() {
			smooth(8);
			size(500,500);
			//fullScreen(P3D);
		  }
	  
	  public void setup(){
		    	  
		  tactileGrapherController = new TactileGrapherController(arduino);
		  	  
	  }
	  public void draw() {
		  background(255);
	  }
	  
	  public void mouseReleased(){}
				
	  }



