public class pcb_node {
	public static int arrival_time;
	public static int completion_time;
	public static int job_id;
	public static int mem_size;
	public static int total_run_time;
	public static boolean completed = false;
	
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
