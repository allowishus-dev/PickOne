package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.Controller;
import java.awt.Color;

public class MainView extends JFrame {
	private AllStudentsTable table;
	JFrame frame = this;
	JPanel topPanel;
	JPanel centerPanel;
	JPanel bottomPanel;
	
	public MainView(final Controller ctl) {
		setSize(300, 400);
		setTitle("Pick One! random student selecter");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout(0, 0));
		
		topPanel = new JPanel(new FlowLayout());
		topPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		centerPanel = new JPanel(new FlowLayout());
		centerPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(centerPanel, BorderLayout.CENTER);		
		
		bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);		
		
		table = new AllStudentsTable(ctl.getAllStudents());
		topPanel.add(table);
		
		JButton btnDraw = new JButton("Draw!");
		btnDraw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer i = ctl.draw();
				if (i != null) {
					JOptionPane.showMessageDialog(frame, "The number from random.org is: \n" + i + "\n\nSo the draw is:\n" + table.getTable().getValueAt(i, 1), "The draw", JOptionPane.INFORMATION_MESSAGE);
					table.getTableModel().removeRow(i);
					table.initTable();
				}
				else {
					JOptionPane.showMessageDialog(frame, "Something when wrong in getting a random number from random.org", "Connection Error!", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			
		});
		
		btnDraw.setPreferredSize(new Dimension(300, 75));
		
		centerPanel.add(btnDraw);
		
		JButton btnRemove = new JButton("Remove selected");
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i = table.getTable().getSelectedRow();
				if (i != -1) {
					ctl.remove(i);
					table.initTable();
				}
			}
			
		});
		
		bottomPanel.add(btnRemove);

		JButton btnTextFile = new JButton("Load students from text file");
		btnTextFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ctl.loadTextFile();
				table.initTable(ctl.getAllStudents());
			}
			
		});
		
		bottomPanel.add(btnTextFile);
		
		pack();
		setVisible(true);
		
	}
}
