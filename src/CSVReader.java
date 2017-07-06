import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class CSVReader  extends PApplet {

	Table table;
	PApplet p;
	String graphsFolder = "graphs/";
	int[] values;
	int maximum;
	int minimum;
	
	CSVReader(PApplet parent){
		this.p= parent;
	}
	
	public void loadTabel(){
		this.table = p.loadTable("graphs/UpDown.csv", "header");
		processData();
		printData();
	}
	
	public void loadTabel(String inString){
		String temp = graphsFolder+inString+".csv";
		this.table = p.loadTable(temp, "header");
		processData();
		printData();
	}
	
	public void processData(){
		  values = new int[table.getRowCount()];
		  for (int i = 0; i<table.getRowCount(); i++){
		    TableRow row = table.getRow(i);    
		    int id = row.getInt("PWM Value (8bit)");
		    values[i] = id;
		    if(i==0){
		    	maximum = id;
		    	minimum = id;
		    } else {
		    if(id>maximum){
		    	maximum =id;
		    }
		    
		    if(id<minimum){
		    	minimum = id; 
		    }
		    }
		  }
	
	}
	
	public int[] getData(){
		return values;
	}
	
	public int getNumberOfValues(){
		return values.length;
	}
	
	public int getMin(){
		return minimum;
	}
	
	public int getMax(){
		return maximum;
	}
			
	public void printData(){
	  for (TableRow row : table.rows()) {
	    int id = row.getInt("PWM Value (8bit)");	    
	    println(id);
	  }
		println(maximum + " Maximum value"); 
		println(minimum + " Minimum value"); 
	  
	}
   
}