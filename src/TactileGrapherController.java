import processing.core.PApplet;
import processing.data.Table;

public class TactileGrapherController extends PApplet {
	
	TactileGrapherUserScreen tactileGrapherUserScreen;
	Button quit;
	//CSVMaker csvFile = new CSVMaker(this);
	CSVReader reader = new CSVReader(this);
	int selector = 0;
	Table table;
	
	boolean once = false;
	
	TactileGrapherController(Arduinos ard){		
		String[] a = {""};
		PApplet.runSketch(a, this);	
		tactileGrapherUserScreen = new TactileGrapherUserScreen(ard,reader);
		
	}
	
	public void settings() {
		size(500,500);
	}

	public void setup(){
		quit = new Button(this,1,8,"Quit",height/2);
		reader.loadTabel();

		//csvFile.makeGraph();
		
		delay(3000);  
		
	}
	
	public void draw() {

		background(255);
		quit.drawButton();
 
		  
	}
	
	  public void mouseReleased(){

		  quit.click();	  
		  if (quit.isSelected){		
			  quit.isSelected = false;
			  this.running(false);
			 
		  } else {
			  this.running(true);
			  //quit.isSelected = true;
		  }
	  }
	
		public void running(boolean g){
			if(!g){
				this.noLoop();
				surface.setVisible(false);
				tactileGrapherUserScreen.running(false);
			} else {
				this.loop();
				surface.setVisible(true);
				tactileGrapherUserScreen.running(true);
			  }
		  }
}
