package os;

public class Current_Info implements constants{
	
	private int available_memory = MEM_CAP;
	private static int time_passed = 0;
	private static char internal_event = 'Z';
	private static int internal_arrival_time;
	private static Pcb_Node finished_job;
	private static Pcb_Node hold_job = null;
	private static boolean change_job = false;
	
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
		if (job != null)
			change_job = true;
		else 
			change_job = false;
	}
	
	public void set_change_job(boolean b) {
		change_job = b;
	}
	
	public boolean change_job() {
		return change_job;
	}
	public Pcb_Node get_held_job() {
		return hold_job;
	}
}
