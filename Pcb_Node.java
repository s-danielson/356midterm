package os;

public class Pcb_Node {
	private char pcb_event_id;
	private int arrival_time = 0;
	private int completion_time = 0;
	private int job_id = 0;
	private int mem_size = 0;
	private int total_run_time = 0;
	private int remaining_run_time = 0;
	
	/*public pcb_node() {
		int arrival_time;
		int completion_time;
		int job_id;
		int total_run_time;
		int mem_size;
	}*/
	
	public void set_pcb(char eid, int at, int jid, int ms, int trt) {
		pcb_event_id = eid;
		arrival_time = at;
		job_id = jid;
		mem_size = ms;
		total_run_time = remaining_run_time = trt;
	}
	
	// getters
	public char get_event_id() {
		return pcb_event_id;
	}
	
	public int get_arrival_time() {
		return arrival_time;
	}
	
	public int get_completion_time() {
		return completion_time;
	}
	
	public int get_job_id() {
		return job_id;
	}
	
	public int get_mem_size() {
		return mem_size;
	}
	
	public int get_total_run_time() {
		return total_run_time;
	}
	
	public int get_remaining_run_time() {
		return remaining_run_time;
	}
	
	// setters
	public void sub_remaining_run_time(int time) {
		remaining_run_time -= time;
	}
	
	public void set_completion_time(int time) {
		completion_time = time;
	}
		
	
}