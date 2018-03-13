package os;

import java.util.ArrayList;

public class Finished {

	ArrayList<Pcb_Node> queue = new ArrayList<Pcb_Node>();
	
	public Finished() {
		
	}
	
	public int size() {
		return queue.size();
	}
	
	public Pcb_Node get_job(int i) {
		return queue.get(i);
	}
	
	public void add_job(Pcb_Node job) {
		queue.add(job);
	}
	
	public boolean is_empty() {
		return queue.isEmpty();
	}
}
