package os;

import java.util.ArrayList;

public class Ready_2 implements constants{
	
	private ArrayList<Pcb_Node> queue = new ArrayList<Pcb_Node>();
	
	public Ready_2() {
		
	}
	
	public Pcb_Node get_job(int i) {
		return queue.get(i);
	}
	
	public int size() {
		return queue.size();
	}
	
	public void add_job(Pcb_Node job) {
		queue.add(job);
	}
	
	public void remove_job(int i) {
		queue.remove(i);
	}
	
	public boolean is_empty() {
		return queue.isEmpty();
	}
	
	public void cycle(Ready_1 rq1, CPU cpu, Current_Info info) {
		cpu.add(queue.get(0), READY_QUEUE_2_QUANTUM, info);
		cpu.set_from(2);
		cpu.set_job_finished(false);
		queue.remove(0);
	}
}
