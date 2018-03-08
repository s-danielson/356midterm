package os;

public class Pcb_Node {
	private static int arrival_time;
	private static int completion_time;
	private static int job_id;
	private static int mem_size;
	private static int total_run_time;
	private static boolean completed = false;
	
	/*public pcb_node() {
		int arrival_time;
		int completion_time;
		int job_id;
		int total_run_time;
		int mem_size;
	}*/
	
	public void set_pcb(int at, int jid, int ms, int trt) {
		arrival_time = at;
		job_id = jid;
		mem_size = ms;
		total_run_time = trt;
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
	
}