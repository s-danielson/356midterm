package os;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class myOS implements constants {

	public static void main(String[] args) {
		
		// declare queues and variables
		Current_Info info = new Current_Info();
		Job_Scheduler job_scheduler = new Job_Scheduler();
		Ready_1 ready_queue1 = new Ready_1();
		Display disp = new Display();
		
		int jobs_in_system = 0;
		
		File input = new File("C:\\Users\\Spencer\\Desktop\\3rd Year - 2nd Semester\\CPS356\\******insert filename******");
		
		Scanner input_read = getFile(input);
		
		while (input_read.hasNext() || jobs_in_system > 0) {
			
			//enter conditionals for getting external and internal input****
			switch(input_read.next()) {
				
				case "JOB_ARRIVAL":
					jobs_in_system++;
					
					Pcb_Node job = new Pcb_Node();
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