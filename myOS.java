import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class myOS {

	public static void main(String[] args) throws FileNotFoundException {
		
		int jobs_in_system = 0;
		
		File input = new File("C:\\Users\\Spencer\\Desktop\\3rd Year - 2nd Semester\\CPS356\\******insert filename******");
		
		Scanner input_read = new Scanner(input);
		
		
		while (input_read.hasNext() || jobs_in_system > 0) {
			
			switch(input_read.next()) {
				
				case "JOB_ARRIVAL":
					
					pcb_node job = new pcb_node();
					job.set_pcb(input_read.nextInt(), input_read.nextInt(), 
							input_read.nextInt(), input_read.nextInt());
					
					
					
					
				case "TIME_QUANTUM_EXPIRATION":
					
					
				case "JOB_TERMINATION":
					
					
				case "DISPLAY_STATUS":
					
					
					
			
			
			
			}
			
		}

	}
	
	
	/* verify file */
	public static Scanner getFile(File file) {
		try 
		{ Scanner s = new Scanner(file); 
		  return s;    } 
		catch(FileNotFoundException ex) 
		{ System.out.println("File not found."); 
		  return null; }
	}

}
