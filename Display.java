package os;

public class Display {
	
	public Display() {
		
	}
	
	public void show(int specified_time, Job_Scheduler js) {
		
		// JOB SCHEDULING QUEUE
		System.out.println("The contents of the JOB SCHEDULING QUEUE");
		System.out.println("----------------------------------------\n");
		if (js.queue.isEmpty()) {
			System.out.println("The Job Scheduling Queue is empty");
		}
		else {
			System.out.println("Job #  Arr. Time  Mem. Req.  Run Time");
			System.out.println("-------------------------------------");
			for (int i = 0; i < js.queue.size(); i++) {
				int job_num = js.queue.get(i).get_job_id();
				int arrival_time = js.queue.get(i).get_arrival_time();
				int mem_required = js.queue.get(i).get_mem_size();
				int run_time; // NOT YET IMPLEMENTED
				
				System.out.format("%5d%11d%11d",job_num,arrival_time,
						mem_required);
				
			}
		}
	}
}
