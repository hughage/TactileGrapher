import com.leapmotion.leap.Vector;

import processing.core.PApplet;

public class TactileGrapherUserScreen extends PApplet {
	
	Arduinos arduino;
	Leap myLeap;
	Cursors cursor;
	Vector index; 
	CSVReader reader;
	
	int[] dataToRender;
	
	float xSpaceing;
	
	TactileGrapherUserScreen(Arduinos ard, CSVReader r){	
		this.reader = r;
		this.arduino = ard;
		String[] a = {""};
		PApplet.runSketch(a, this);		
	}
	
	public void settings() {
		fullScreen();
		//smooth(8);
		
	}

	public void setup(){
		println("Width: "+width);
		println("Height: "+height);
		  myLeap = new Leap(width,height,width);
		  index = myLeap.getPalmPos();
		  cursor = new  Cursors (232,123,234,this);
		  xSpaceing = (float) width/reader.getNumberOfValues();
	}
	
	public void draw() {
		
		//lights();
		
		  if (myLeap.leap.isConnected()){
		    myLeap.update();		    
		    index = myLeap.getPalmPos();
		  }
		  
		  if (myLeap.palmInIdealVolume()){
			  background(255);	   
		  } else {
				 background(200); 
				// background(255); 
		  }

		  noFill();
	    
		  getData();
		  drawData();
		  cursor.update(index);	
		  generateHaptics();
 
		  
	}
	
	void getData(){
		dataToRender = reader.getData();
	}
	
	public void drawData(){
		
		int max = reader.getMax();
		int min = reader.getMin();
		int magnitude = 50; //of selection wave
		
		int pickUpSpace = width/10;
		
		for(int i=0; i<dataToRender.length; i++){
			fill(50,147,230);
			int y = height - (int)map(dataToRender[i],min,max,height/10,height-(height/10));
			int x = (int)(xSpaceing*i);
			ellipse(x,y,xSpaceing,xSpaceing);		
			fill(50,230,147);
			int y2 = height/2;
			
			if(cursor.xPos<x+pickUpSpace && cursor.xPos>x-pickUpSpace){
				int t=cursor.xPos;
				float t2 = sin(map(t,x-pickUpSpace,x+pickUpSpace,(-PI)/2,3*(PI/2)));
				t2 = magnitude + (t2*magnitude);
				ellipse(x,y2+t2,xSpaceing,xSpaceing);
			} else {
				ellipse(x,y2,xSpaceing,xSpaceing);
			}
			
		}
	}
	
	void generateHaptics(){
		if(myLeap.palmInIdealVolume()){
		int indexPositionToRender = 0; //index within data array that finger position is closest to. 	
		int pos=cursor.xPos;	
		float hapticRenderValue; 
		indexPositionToRender = (int)map(pos,0,width,0,dataToRender.length);
		int tempData = dataToRender[indexPositionToRender];
		int max = reader.getMax();
		int min = reader.getMin();
		hapticRenderValue = map(tempData,min,max,0,1);
		arduino.setB(hapticRenderValue);
		}
		
	}
	
	
	
	
	public void running(boolean g){
		  if(!g){
		  this.noLoop();
		  surface.setVisible(false);
		  arduino.off();
		  } else {
			  this.loop();
			  surface.setVisible(true);
		  }
	  }
}
