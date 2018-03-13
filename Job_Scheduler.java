package os;
import java.util.ArrayList;

public class Job_Scheduler implements constants {

	private ArrayList<Pcb_Node> queue = new ArrayList<Pcb_Node>();
	
	public Job_Scheduler() {
		
	}
	
	public Pcb_Node get_job(int i) {
		return queue.get(i);
	}
	
	public int size() {
		return queue.size();
	}
	
	public boolean is_empty() {
		return queue.isEmpty();
	}
	
	public boolean insert_to_job_queue(Pcb_Node job) {
		if (job.get_mem_size() > MEM_CAP) {
			System.out.println("Job rejected");
			return true;
		} else {
			queue.add(job);
			return false;
		}
	}
	
	public void send_to_ready_queue(Ready_1 rq1, CPU cpu, Current_Info info) {
		
		int job_mem_size = queue.get(0).get_mem_size();
		while (job_mem_size <= info.available_mem()) {
			// remove available memory & add job to ready queue 1
			info.less_mem(job_mem_size);
			rq1.add_job(queue.get(0));
			// remove from job queue and refresh value, unless queue is empty
			queue.remove(0);
			if (queue.isEmpty())
				break;
			job_mem_size = queue.get(0).get_mem_size();
		}
			
	}
	
	
	
}