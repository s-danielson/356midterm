import java.util.ArrayList;

public class job_sched_queue {

	ArrayList<pcb_node> scheduler = new ArrayList<pcb_node>();
	
	public void insert_job_queue(pcb_node job) {
		
		if (job.get_mem_size() > 512) {
		
		} else
		scheduler.add(job);
	}
}
