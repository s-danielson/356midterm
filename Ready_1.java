package os;

import java.util.ArrayList;

public class Ready_1 {
	
	private ArrayList<Pcb_Node> queue = new ArrayList<Pcb_Node>();
	
	public Ready_1() {
		
	}
	
	public Pcb_Node get_job(int i) {
		return queue.get(i);
	}
	
	public void add_job(Pcb_Node job) {
		queue.add(job);
	}
	
	
}
