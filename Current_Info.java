package os;

public class Current_Info implements constants{
	
	private static int available_memory = MEM_CAP;
	
	public Current_Info() {
		
	}
	
	// for when a new process enters main memory
	public void less_mem(int mem) {
		available_memory = available_memory - mem;
	}
	
	// for when a process leaves main memory
	public void more_mem(int mem) {
		available_memory = available_memory + mem;
	}
	
	public int available_mem() {
		return available_memory;
	}
}
