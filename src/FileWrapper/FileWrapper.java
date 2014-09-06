package FileWrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Controller.Student;

public class FileWrapper {
	private String fileName = "students.dat";
	private String textFileName = "students.txt";
	
	public void saveStudentList(ArrayList<Student> allStudents) {
		
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		try
	    {
			File yourFile = new File(fileName);
			yourFile.createNewFile();
			
			fileOut = new FileOutputStream(fileName, false);
	       	out = new ObjectOutputStream(fileOut);
	         
		   	out.writeObject(allStudents);
	    }
		catch(IOException i)
	    {
	        i.printStackTrace();
	    }
		finally {
			try {
				out.close();
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Student> loadStudentList() throws Exception {
		ArrayList<Student> allStudents = new ArrayList<Student>();
	    
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		
		try
	    {
	       fileIn = new FileInputStream(fileName);
	       in = new ObjectInputStream(fileIn);
	       
    		   allStudents = (ArrayList<Student>) in.readObject();
	    }
		catch(IOException | ClassNotFoundException i)
	    {
	       throw new Exception("Could not load data");
	    }
		finally {
		    in.close();
		    fileIn.close();
		}
		return allStudents;
	}
	
	public ArrayList<Student> loadTextFile() {
		ArrayList<Student> students = new ArrayList<Student>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(textFileName))) {
	        String line = br.readLine();

	        while (line != null) {
	        	students.add(new Student(line));
	            line = br.readLine();
	        }
	        
	    }
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveStudentList(students);
		
		return students;
	}

}
