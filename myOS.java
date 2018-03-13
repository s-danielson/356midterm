package os;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class myOS implements constants {

	//  event id and arrival time are global for use in methods
	static char event_id = 'A';
	static int arrival_time = 0;
	
	public static void main(String[] args) {
		
		// declare queues and variables
		Current_Info info = new Current_Info();
		Job_Scheduler job_scheduler = new Job_Scheduler();
		Ready_1 ready_queue1 = new Ready_1();
		Ready_2 ready_queue2 = new Ready_2();
		CPU cpu = new CPU();
		Display disp = new Display();
		Finished finished = new Finished();
		boolean first_job = true;
		int jobs_in_system = 0;
		
		File input = new File("C:\\Users\\Spencer\\Desktop\\3rd Year - 2nd Semester\\CPS356\\midterm\\test.txt");
		
		Scanner input_read = getFile(input);
		
		while (input_read.hasNext() || jobs_in_system > 0) {
			
			Pcb_Node job = new Pcb_Node();
			
			if (info.held_job()) {
				System.out.println(":held::"+info.get_held_job().get_event_id());
				event_id = info.get_held_job().get_event_id();
			} else if (info.get_internal_event() != 'Z') { // internal events have priority
				event_id = info.get_internal_event();
			} else if(input_read.hasNext()) { // external events run if no internal events have occurred
				event_id = input_read.next().charAt(0);
				arrival_time = input_read.nextInt();
				
				if (!first_job)
					system_catch_up(arrival_time, ready_queue1, ready_queue2, cpu, info);
				
				if (info.get_internal_event() != 'Z') {
					System.out.println(":held:");
					info.hold_job(job);
					info.set_held_job(true);
					event_id = info.get_internal_event();
				}
			}
			
			switch(event_id) {
			
				case 'A':
					// means an internal event previously ran
					if (info.held_job()) {
						System.out.println(":release:");
						job = info.get_held_job();
						info.set_held_job(false);
						system_catch_up(arrival_time, ready_queue1, ready_queue2, cpu, info);
					} else { // set pcb if no previous internal event ran
						System.out.println(":create:"+event_id);
						job.set_pcb(event_id, arrival_time, input_read.nextInt(), 
								input_read.nextInt(), input_read.nextInt());
						
					}
					
					// catch the system up, unless first job
					if (first_job) {
						info.set_time(arrival_time);
						cpu.add(job, READY_QUEUE_1_QUANTUM, info);
						//ready_queue1.cycle(cpu, info);
						first_job = false;
					}
					
					// check for internal event
					if (!first_job) {
						boolean rejected = job_scheduler.insert_to_job_queue(job);
						
						show_job(event_id, arrival_time);
						
						if (!job_scheduler.is_empty() && !rejected) {
							job_scheduler.send_to_ready_queue(ready_queue1, cpu, info);
						}
					}
					
					break;
					
				case 'E':
					info.set_internal_event('Z');
					break;
					
				case 'T':
					finished.add_job(info.get_finished_job()); // add job to finished queue
					info.get_finished_job().set_completion_time(info.get_time()); // set completion time
					info.more_mem(info.get_finished_job().get_mem_size()); // add mem back to system
					System.out.println(":" + info.get_time() + "::");
					if (ready_queue1.is_empty() && cpu.job_finished())
						job_scheduler.send_to_ready_queue(ready_queue1, cpu, info);
					
					show_job(info.get_internal_event(),info.get_time());
					
					jobs_in_system--; // subtract job from system
					
					run_cpu(ready_queue1, ready_queue2, cpu, info); // cycle to new job
					System.out.println("::" + info.held_job() + ":");
					info.set_internal_event('Z');
					break;
					
				case 'D':
					system_catch_up(arrival_time, ready_queue1, ready_queue2, cpu, info);
					show_job(event_id, arrival_time);
					disp.show(job_scheduler, ready_queue1, cpu, finished, info);
					break;
			}
			
			
		}
		
	}
	
	// not called yet
	public Pcb_Node get_internal_event(Current_Info info) {
		// hold the external job in main
		if (info.get_internal_event() != 'Z') {
			event_id = info.get_internal_event();
		} 
		
		return info.get_held_job();
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
			System.out.println("sys catch up test");
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
				System.out.println("RQ1 test");
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