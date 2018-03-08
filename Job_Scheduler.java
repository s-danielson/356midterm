package os;
import java.util.ArrayList;

public class Job_Scheduler implements constants {

	ArrayList<Pcb_Node> queue = new ArrayList<Pcb_Node>();
	
	public Job_Scheduler() {
		
	}
	
	public void insert_to_job_queue(Pcb_Node job) {
		if (job.get_mem_size() > MEM_CAP) {
			// REJECT JOB
		} else {
			queue.add(job);
		}
	}
	
	public void send_to_ready_queue(Ready_1 rq1, Current_Info info) {
		
		int i = 0;
		int job_mem_size = queue.get(i).get_mem_size();
		Pcb_Node job = queue.get(i);
		
		while (job_mem_size < info.available_mem()) {
			// remove available memory & add job to ready queue 1
			info.less_mem(job_mem_size);
			rq1.add_job(job);
			// remove from job queue and increment
			queue.remove(i);
			i++;
			job_mem_size = queue.get(i).get_mem_size();
		}
			
	}
	
	
	
}