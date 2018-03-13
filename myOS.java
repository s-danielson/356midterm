package os;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class myOS implements constants {

	public static void main(String[] args) {
		
		// declare queues and variables
		Current_Info info = new Current_Info();
		Job_Scheduler job_scheduler = new Job_Scheduler();
		Ready_1 ready_queue1 = new Ready_1();
		Ready_2 ready_queue2 = new Ready_2();
		CPU cpu = new CPU();
		Display disp = new Display();
		Finished finished = new Finished();
		char event_id = 'A';
		int arrival_time = 0;
		boolean first_job = true;
		int jobs_in_system = 0;
		
		File input = new File("C:\\Users\\Spencer\\Desktop\\3rd Year - 2nd Semester\\CPS356\\midterm\\p2stdin_1.txt");
		
		Scanner input_read = getFile(input);
		
		while (input_read.hasNext() || jobs_in_system > 0) {
			
			if (info.get_internal_event() != 'Z') { // internal events have priority
				event_id = info.get_internal_event();
				arrival_time = info.get_time();
			} else if (input_read.hasNext()) { // external events run if no internal events have occurred
				event_id = input_read.next().charAt(0);
				arrival_time = input_read.nextInt();
			}
			
			show_job(event_id, arrival_time);
			
			switch(event_id) {
				
				case 'A':
					
					Pcb_Node job = new Pcb_Node();
					job.set_pcb(event_id, arrival_time, input_read.nextInt(), 
							input_read.nextInt(), input_read.nextInt());
					
					boolean rejected = job_scheduler.insert_to_job_queue(job);
					
					if (!job_scheduler.is_empty() && !rejected) {
						
						job_scheduler.send_to_ready_queue(ready_queue1, cpu, info);
						
						if (first_job) {
							info.set_time(arrival_time);
							ready_queue1.cycle(cpu, info);
							first_job = false;
						} else {
							system_catch_up(arrival_time, ready_queue1, ready_queue2, cpu, info);
						}
					}
					
					if (info.get_internal_event() != 'Z') {
						info.hold_job(job);
					}
					
					break;
					
				case 'E':
					info.set_internal_event('Z');
					break;
					
				case 'T':
					finished.add_job(info.get_finished_job()); // add job to finished queue
					info.get_finished_job().set_completion_time(info.get_time()); // set completion time
					info.more_mem(info.get_finished_job().get_mem_size()); // add mem back to system

					if (ready_queue1.is_empty() && cpu.job_finished())
						job_scheduler.send_to_ready_queue(ready_queue1, cpu, info);
					
					jobs_in_system--; // subtract job from system
					
					run_cpu(ready_queue1, ready_queue2, cpu, info); // cycle to new job
					//system_catch_up(arrival_time, ready_queue1, ready_queue2, cpu, info);
					info.set_change_job(false);
					break;
					
				case 'D':
					system_catch_up(arrival_time, ready_queue1, ready_queue2, cpu, info);
					disp.show(job_scheduler, ready_queue1, cpu, finished, info);
					break;
					
			}
			
			// means internal event declared and job is being held
			if (info.change_job()) {
				// do nothing
			} else if (info.get_internal_event() != 'Z' && !info.change_job()) {
				show_job(event_id, arrival_time);
				info.set_internal_event('Z');
			} else {
				show_job(event_id, arrival_time);
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
	
	// shows a job and its arrival time
	public static void show_job(char event, int arrival_time) {
		System.out.println("Event: " + event + "  Time: " + arrival_time);
	}
	
	// catches the system up to the indicated arrival time
	public static void system_catch_up(int arrival_time, Ready_1 ready_queue1,
			Ready_2 ready_queue2, CPU cpu, Current_Info info) {
		
		while (info.get_time() != arrival_time && !cpu.job_finished()) {
			
			if (cpu.cycle_time(arrival_time, info) == 0) {
				run_cpu(ready_queue1, ready_queue2, cpu, info);
			}
			
		}
	}
	
	// runs the contents of the cpu system (all queues and cpu itself)
	public static void run_cpu(Ready_1 ready_queue1, Ready_2 ready_queue2, 
			CPU cpu, Current_Info info) {
		
		if (!ready_queue1.is_empty() || cpu.get_from() == 1) {

			if (cpu.job_finished()) {
				ready_queue1.cycle(cpu, info);
			} else {
				cpu.cycle1(ready_queue1, ready_queue2, info);
			}
		} else if (!ready_queue2.is_empty() || cpu.get_from() == 2) {

			if (cpu.job_finished()) {
				ready_queue2.cycle(ready_queue1, cpu, info);
			} else {
				cpu.cycle2(ready_queue2, info);
			}
		}
	}

}