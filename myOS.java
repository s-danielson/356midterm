import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class myOS {

	public static void main(String[] args) throws FileNotFoundException {
		
		int jobs_in_system = 0;
		
		File input = new File("C:\\Users\\BRAVERUNNER\\Desktop\\scool\\CPS356");
		
		Scanner input_read = getFile(input);
		
		while (input_read.hasNext() || jobs_in_system > 0) {
			
			
		}

	}
	
	
	/* verify file */
	public static Scanner getFile(File file) {
		try 
		{ Scanner s = new Scanner(file); 
		  return s;    } 
		catch(FileNotFoundException ex) 
		{ System.out.println("File not found."); 
		  return null; }
	}

}
