
import java.io.PrintWriter;
import java.util.Scanner;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class CSVMaker {
	
	PrintWriter writer;
	Table table;	
	PApplet p;
	
	CSVMaker(PApplet p){
		try{
			table = new Table();
		    table.addColumn("PWM Value (8bit)");
		    this.p = p;

		} catch (Exception e) {
		   // do something
		}
	}
	

	public void resultint(int pwmSignal){	
		  TableRow newRow = table.addRow();
		  newRow.setInt("PWM Value (8bit)",pwmSignal);
	}
	
	public void close(){
		System.out.println("Enter file name: ");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		System.out.println("File name: " + username);
		p.saveTable(table, "graphs/"+username+".csv");
	}


	public void makeGraph() {
		for (int i=0; i<200; i++){
			resultint(i);
		}
		for (int i=0; i<200; i++){
			resultint(200-i);
		}
		close();
	}	

}
