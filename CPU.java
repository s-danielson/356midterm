package os;

public class CPU implements constants{
	
	private static Pcb_Node job_on_CPU = null;
	private static int arrival_time = 0;
	private static int remaining_burst_time = READY_QUEUE_1_QUANTUM;
	private static boolean job_finished = false;
	private static int from = 1;
	
	public CPU() {
		
	}
	
	public boolean job_finished() {
		return job_finished;
	}
	
	public void add(Pcb_Node job, int quantum, Current_Info info) {
		job_on_CPU = job;
		arrival_time = info.get_time();
		job_finished = false;
		if (quantum > job_on_CPU.get_total_run_time())
			remaining_burst_time = job_on_CPU.get_total_run_time();
		else
			remaining_burst_time = quantum;
	}
	
	public Pcb_Node get_job() {
		return job_on_CPU;
	}
	
	public int get_arrival_time() {
		return arrival_time;
	}
	
	public int get_remaining_burst_time() {
		return remaining_burst_time;
	}
	
	public int get_from() {
		return from;
	}
	
	public void set_from(int i) {
		from = i;
	}
	
	public void set_job(Pcb_Node job) {
		job_on_CPU = job;
	}
	public void set_job_finished(boolean status) {
		job_finished = status;
	}
	public void cycle1(Ready_1 queue1, Ready_2 queue2, Current_Info info) {
		
		if (job_on_CPU.get_remaining_run_time() > remaining_burst_time) {
			info.add_time(remaining_burst_time); // add time to total time
			job_on_CPU.sub_remaining_run_time(remaining_burst_time);
			queue2.add_job(job_on_CPU);
			info.set_internal_event('E');
		} else {
			info.add_time(job_on_CPU.get_remaining_run_time());
			info.set_finished_job(job_on_CPU);
			info.set_internal_event('T');
		}
		
		//from = 0;
		job_finished = true;
		
	}
	
	public void cycle2(Ready_2 queue2, Current_Info info) {
		
		// if the remaining run time exceeds the time quantum, 
			// send to back of ready 2
		if (job_on_CPU.get_remaining_run_time() > remaining_burst_time) {
			info.add_time(remaining_burst_time); // add time to total time
			queue2.add_job(job_on_CPU); // add job to back of rq2
			job_on_CPU.sub_remaining_run_time(remaining_burst_time);
			info.set_internal_event('E');

		} else {
			info.add_time(job_on_CPU.get_remaining_run_time());
			info.set_finished_job(job_on_CPU);
			info.set_internal_event('T');
		}
		
		//from = 0;
		job_finished = true;

	}
	
	public int cycle_time(int time, Current_Info info) {
		System.out.println("1:" + remaining_burst_time + ":");
		if ((time - info.get_time()) > remaining_burst_time) {
			System.out.println("test1");
			return 0;
		} else if ((time - info.get_time()) == remaining_burst_time) {
			System.out.println("test2");
			return 0;
		} else { 
			System.out.println("2:" + (time - info.get_time()) + ":");
			remaining_burst_time -= (time - info.get_time());
			job_on_CPU.sub_remaining_run_time(time - info.get_time());
			info.set_time(time);
			System.out.println("test3");
			return 1;
		}
	}
}
	
