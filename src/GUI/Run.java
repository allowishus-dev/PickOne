package GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.marsG.simplerandomorglib.RandomIntegerClient;

import Controller.Controller;
import Controller.Student;
import FileWrapper.FileWrapper;

public class Run {
	
	private static Controller ctl = new Controller();

	
	public static void main(String[] args) {
		
		try {
			ctl.loadStudentList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MainView mw = new MainView(ctl);

	}

}
