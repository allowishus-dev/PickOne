package Controller;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;

import FileWrapper.FileWrapper;

import com.marsG.simplerandomorglib.RandomIntegerClient;

public class Controller {
	private static FileWrapper fw = new FileWrapper();
	private static ArrayList<Student> allStudents = new ArrayList<Student>();
	
	public Integer makeRandomInt(int max) {
		Integer ranI = null;
		
		RandomIntegerClient cl = new RandomIntegerClient("allowishus@sol.dk");
		try {
			ranI = Integer.parseInt(cl.getRandomIntDecimal(0, max, 1).get(0));
			
		}
		catch (ConnectTimeoutException cte) {
			System.out.println("Not so fast! You're sharing random.org with others!");
		}
		catch (SocketTimeoutException ste) {
			System.out.println("Socket Time Out!");
		}
		catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ranI;

	}
	
	public void remove(Student student) {
		allStudents.remove(student);
	}
	
	public void remove(int i) {
		allStudents.remove(i);
		fw.saveStudentList(allStudents);
	}

	public void setAllStudents(ArrayList<Student> allStudents) {
		this.allStudents = allStudents;		
	}

	public ArrayList<Student> getAllStudents() {
		return allStudents;
	}
	
	public void loadTextFile() {
		allStudents = fw.loadTextFile();
	}
	
	public Integer draw() {
		int left = allStudents.size();
		
		if (left > 1) {
			Integer ranI = null;
			
			ranI = makeRandomInt(left-1);
			
			Student foundStudent = allStudents.get(ranI);
			
//			System.out.println(ranI + "\n" + foundStudent.getName());
			
			remove(foundStudent);
			
			saveStudentList();
			
			return ranI;
			
		}
		else {
			return null;
		}
	}

	public void saveStudentList() {
		fw.saveStudentList(allStudents);
		
	}

	public void loadStudentList() throws Exception {
		allStudents = fw.loadStudentList();
		fw.saveStudentList(allStudents);
		
	}
}
