package os;

public class Current_Info implements constants{
	
	private int available_memory = MEM_CAP;
	private static int time_passed = 0;
	private static char internal_event = 'Z';
	private static int internal_arrival_time;
	private static Pcb_Node finished_job;
	private Pcb_Node hold_job = new Pcb_Node();
	private boolean held_job = false;
	
	public Current_Info() {
		
	}
	
	// for when a new process enters main memory
	public void less_mem(int mem) {
		available_memory -= mem;
	}
	
	// for when a process leaves main memory
	public void more_mem(int mem) {
		available_memory += mem;
	}
	
	public int available_mem() {
		return available_memory;
	}
	
	public void add_time(int time) {
		time_passed += time;
	}
	
	public void set_time(int time) {
		time_passed = time;
	}
	
	public int get_time() {
		return time_passed;
	}
	
	public void set_internal_event(char it) {
		internal_event = it;
	}
	
	public void set_internal_arrival_time(int time) {
		internal_arrival_time = time;
	}
	
	public char get_internal_event() {
		return internal_event;
	}
	
	public int get_internal_arrival_time() {
		return internal_arrival_time;
	}
	
	public void set_finished_job(Pcb_Node job) {
		finished_job = job;
	}
	
	public Pcb_Node get_finished_job() {
		return finished_job;
	}
	
	public void hold_job(Pcb_Node job) {
			hold_job = job;
			hold_job.set_pcb(
					job.get_event_id(), 
					job.get_arrival_time(),
				job.get_job_id(), 
				job.get_mem_size(), 
				job.get_total_run_time());
	}
	
	public void set_held_job(boolean b) {
		held_job = b;
	}
	
	public boolean held_job() {
		return held_job;
	}

	public Pcb_Node get_held_job() {
		return hold_job;
	}
}
