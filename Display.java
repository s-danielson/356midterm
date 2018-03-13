package os;

public class Display extends myOS {
	
	public Display() {
		
	}
	
	public void show(Job_Scheduler js, Ready_1 rq1, CPU cpu, Finished finished, Current_Info info) {
		
		// JOB SCHEDULING QUEUE
		System.out.println("\nThe contents of the JOB SCHEDULING QUEUE");
		System.out.println("----------------------------------------\n");
		if (js.is_empty()) {
			System.out.println("The Job Scheduling Queue is empty\n\n");
		}
		else {
			System.out.println("Job #  Arr. Time  Mem. Req.  Run Time");
			System.out.println("-------------------------------------");
			for (int i = 0; i < js.size(); i++) {
				int job_num = js.get_job(i).get_job_id();
				int arrival_time = js.get_job(i).get_arrival_time();
				int mem_required = js.get_job(i).get_mem_size();
				int run_time = js.get_job(i).get_total_run_time();
				
				System.out.format("%5d%11d%11d%10d\n",job_num,arrival_time,
						mem_required,run_time);
			}
		}
		
		System.out.println("\n\n");
		
		// FIRST LEVEL READY QUEUE
		System.out.println("The contents of the FIRST LEVEL READY QUEUE");
		System.out.println("-------------------------------------------\n");
		if (rq1.is_empty()) {
			System.out.println("The First Level Ready Queue is empty\n\n");
		}
		else {
			System.out.println("Job #  Arr. Time  Mem. Req.  Run Time");
			System.out.println("-------------------------------------");
			for (int i = 0; i < rq1.size(); i++) {
				int job_num = rq1.get_job(i).get_job_id();
				int arrival_time = rq1.get_job(i).get_arrival_time();
				int mem_required = rq1.get_job(i).get_mem_size();
				int run_time = rq1.get_job(i).get_total_run_time();
				
				System.out.format("%5d%11d%11d%10d\n",job_num,arrival_time,
						mem_required,run_time);
			}
		}
		
		System.out.println("\n\n");
		
		// CPU CONTENTS
		System.out.println("The CPU  Start Time  CPU burst time left");
		System.out.println("-------  ----------  -------------------\n");
		int job_id = cpu.get_job().get_job_id();
		int start_time = cpu.get_arrival_time();
		int remaining_burst_time = cpu.get_job().get_remaining_run_time();
		System.out.format("%7d%12d%21d\n",job_id,start_time,remaining_burst_time);
		
		System.out.println("\n\n");
		
		// FINISHED LIST
		System.out.println("\nThe contents of the FINAL FINISHED LIST");
		System.out.println("---------------------------------------\n");
		System.out.println("Job #  Arr. Time  Mem. Req.  Run Time  Start Time  Com. Time");
		System.out.println("-----  ---------  ---------  --------  ----------  ---------\n");
		for (int i = 0; i < finished.size(); i++) {
			int job_num = finished.get_job(i).get_job_id();
			int arrival_time = finished.get_job(i).get_arrival_time();
			int mem_required = finished.get_job(i).get_mem_size();
			int run_time = finished.get_job(i).get_total_run_time();
			start_time = arrival_time;
			int completion_time = finished.get_job(i).get_completion_time();
			
			System.out.format("%5d%11d%11d%10d%12d%11d\n",job_num,arrival_time,
					mem_required,run_time,start_time,completion_time);
		}
		
		System.out.println("\n\n");
		
		System.out.format("There are %d blocks of memory available in the system\n\n",info.available_mem());
	}
}
