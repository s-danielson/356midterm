package os;

import java.util.ArrayList;

public class Ready_1 implements constants{
	
	private ArrayList<Pcb_Node> queue = new ArrayList<Pcb_Node>();
	
	public Ready_1() {
		
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
	
	public void cycle(CPU cpu, Current_Info info) {
		System.out.println("-"+queue.get(0).get_total_run_time()+"-");
		cpu.add(queue.get(0), READY_QUEUE_1_QUANTUM, info);
		cpu.set_from(1); // from ready queue 1
		cpu.set_job_finished(false);
		//info.set_time(queue.get(0).get_arrival_time());
		queue.remove(0);
	}
	
}
