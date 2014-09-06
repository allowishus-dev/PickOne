package GUI;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controller.Student;

public class AllStudentsTable extends JScrollPane{
	private ArrayList<Student> students;
	private DefaultTableModel tableModel;
	private static JTable table = new JTable();
	
	public AllStudentsTable(ArrayList<Student> students) {
		super(table);
		
		this.students = students;
		
		tableModel = new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) { // annulerer standard indstillingen for om cellerne kan ændres
	            return false;
	        }
		};
		
		// sætter at tabellen skal have 4 kolonner
		tableModel.setColumnCount(2);
		tableModel.setColumnIdentifiers(new String[] { "Id", "Student Name"});

		initTable();
		
		table.setModel(tableModel);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// preselecter første række i tabellen
//		table.setRowSelectionInterval(0, 0);
		
		// ændrer fordelingen af størrelserne på tabel cellerne 
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(280);

    }
	
	public JTable getTable() {
		return table;
	}

	public void initTable() {
		tableModel.setRowCount(students.size());
		
		int row = 0;

		// udfylder tabellen fra superklassen
		for (Student s : students) {
			tableModel.setValueAt(row, row, 0);
			tableModel.setValueAt(s.getName(), row, 1);
			row++;			
        }
		
	}
	
	public void initTable(ArrayList<Student> students) {
		this.students = students;
		tableModel.setRowCount(students.size());
		
		int row = 0;

		// udfylder tabellen fra superklassen
		for (Student s : students) {
			tableModel.setValueAt(row, row, 0);
			tableModel.setValueAt(s.getName(), row, 1);
			row++;			
        }
		
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}
}
