package mbanse2740ex3g;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PayrollForm extends JFrame {
	
	private Payroll payroll;
	private JPanel contentPane;
	private JList employeeList;
	private JTextField hoursTextField;
	private JLabel totalHoursLabel;
	private JLabel grossPayLabel;
	private DefaultListModel employeeListModel;
	private JTextField empIdTextField;
	private JTextField empNameTextField;
	private JTextField payRateTextField;
	private JButton addHoursButton;
	private JButton clearHoursButton;
	private JButton updateButton;
	private PayrollObjMapper payrollObjMapper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setTitle("MBanse 2740 Ex3G Payroll");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectEmployee = new JLabel("Select employee:");
		lblSelectEmployee.setBounds(31, 27, 142, 16);
		contentPane.add(lblSelectEmployee);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(31, 56, 392, 141);
		contentPane.add(scrollPane);
		
//		employeeList = new JList();
//		employeeListModel = new DefaultListModel();
	/*	employeeListModel.addElement(new Payroll (101, "Mitch Banse", 10.0));
		employeeListModel.addElement(new Payroll (102, "Nate Wurm", 20.0));
		employeeListModel.addElement(new Payroll (103, "Josh Diersen", 30.0));
		employeeListModel.addElement(new Payroll (104, "Melissa Hanson", 40.0));
		employeeListModel.addElement(new Payroll (105, "Lisa Lang", 50.0));*/
		
		payrollObjMapper = new PayrollObjMapper("exercise3g.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		employeeList = new JList(employeeListModel);
		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_employeeList_mouseClicked(arg0);
			}
		});
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(employeeList);
		
		JLabel lblNewLabel = new JLabel("employee ID (>100):");
		lblNewLabel.setBounds(29, 226, 130, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmployeeName = new JLabel("employee Name:");
		lblEmployeeName.setBounds(31, 255, 114, 16);
		contentPane.add(lblEmployeeName);
		
		JLabel lblPayRate = new JLabel("Pay Rate (7.25-100):");
		lblPayRate.setBounds(31, 287, 128, 16);
		contentPane.add(lblPayRate);
		
		JLabel lblEnterHours = new JLabel("Enter Hours (0.1-20):");
		lblEnterHours.setBounds(31, 316, 128, 16);
		contentPane.add(lblEnterHours);
		
		hoursTextField = new JTextField();
		hoursTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				do_hoursTextField_focusGained(arg0);
			}
		});
		hoursTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		hoursTextField.setText("0.00");
		hoursTextField.setBounds(262, 313, 69, 22);
		contentPane.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		JLabel lblTotalHours = new JLabel("Total hours:");
		lblTotalHours.setBounds(31, 345, 98, 16);
		contentPane.add(lblTotalHours);
		
		totalHoursLabel = new JLabel("0.00");
		totalHoursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHoursLabel.setBounds(213, 345, 72, 16);
		contentPane.add(totalHoursLabel);
		
		JLabel lblGrossPay = new JLabel("Gross pay:");
		lblGrossPay.setBounds(31, 374, 98, 16);
		contentPane.add(lblGrossPay);
		
		grossPayLabel = new JLabel("0.00");
		grossPayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grossPayLabel.setBounds(193, 374, 92, 16);
		contentPane.add(grossPayLabel);
		
		addHoursButton = new JButton("+");
		addHoursButton.setEnabled(false);
		addHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_addHoursButton_actionPerformed(arg0);
			}
		});
		addHoursButton.setBounds(346, 312, 41, 25);
		contentPane.add(addHoursButton);
		
		clearHoursButton = new JButton("Clear");
		clearHoursButton.setEnabled(false);
		clearHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_clearHoursButton_actionPerformed(arg0);
			}
		});
		clearHoursButton.setBounds(399, 312, 84, 25);
		contentPane.add(clearHoursButton);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnClearForm_actionPerformed(e);
			}
		});
		btnClearForm.setBounds(326, 400, 97, 25);
		contentPane.add(btnClearForm);
		
		empIdTextField = new JTextField();
		empIdTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				do_empIdTextField_focusGained(arg0);
			}
		});
		empIdTextField.setText("000");
		empIdTextField.setBounds(252, 223, 79, 22);
		contentPane.add(empIdTextField);
		empIdTextField.setColumns(10);
		
		empNameTextField = new JTextField();
		empNameTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				do_empNameTextField_focusGained(e);
			}
		});
		empNameTextField.setBounds(175, 252, 156, 22);
		contentPane.add(empNameTextField);
		empNameTextField.setColumns(10);
		
		payRateTextField = new JTextField();
		payRateTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				do_payRateTextField_focusGained(e);
			}
		});
		payRateTextField.setText("7.25");
		payRateTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		payRateTextField.setBounds(252, 284, 79, 22);
		contentPane.add(payRateTextField);
		payRateTextField.setColumns(10);
		
		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_updateButton_actionPerformed(arg0);
			}
		});
		updateButton.setBounds(203, 400, 97, 25);
		contentPane.add(updateButton);
	}
	protected void do_employeeList_mouseClicked(MouseEvent arg0) {
		Payroll payroll = (Payroll)employeeList.getSelectedValue();
		this.empIdTextField.setText(Integer.toString(payroll.getId()));
		this.empNameTextField.setText(payroll.getName());
		DecimalFormat dollarFmt = new DecimalFormat("#,##0.00");
		this.payRateTextField.setText(dollarFmt.format(payroll.getPayRate()));
		DecimalFormat hoursFmt = new DecimalFormat("##0.00");
		this.totalHoursLabel.setText(hoursFmt.format(payroll.getHours()));
		DecimalFormat payFmt = new DecimalFormat("$#,##0.00");
		this.grossPayLabel.setText(payFmt.format(payroll.calcGrossPay()));
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
		this.addHoursButton.setEnabled(true);
		this.clearHoursButton.setEnabled(true);
		this.updateButton.setEnabled(true);
	}
	
	protected void do_addHoursButton_actionPerformed(ActionEvent arg0) {
		Payroll payroll = (Payroll)employeeList.getSelectedValue();
		boolean goodHrs = payroll.addHours(Double.parseDouble(this.hoursTextField.getText()));
		if (goodHrs) {
			DecimalFormat hoursFmt = new DecimalFormat("##0.00");
			this.totalHoursLabel.setText(hoursFmt.format(payroll.getHours()));
			DecimalFormat payFmt = new DecimalFormat("$#,##0.00");
			this.grossPayLabel.setText(payFmt.format(payroll.calcGrossPay()));
			this.hoursTextField.setText("0.00");
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid Hours. \nMust be between .10 and 20.");
		}
		this.hoursTextField.requestFocus();
	}
	
	protected void do_clearHoursButton_actionPerformed(ActionEvent arg0) {
		Payroll payroll = (Payroll)employeeList.getSelectedValue();
		payroll.setHours(0.00);
		this.grossPayLabel.setText("0.00");
		this.totalHoursLabel.setText("0.00");
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
	}
	
	protected void do_btnClearForm_actionPerformed(ActionEvent e) {
		this.empIdTextField.setText("0");
		this.empNameTextField.setText("");
		this.grossPayLabel.setText("0.00");
		this.totalHoursLabel.setText("0.00");
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
		this.employeeList.clearSelection();
		this.addHoursButton.setEnabled(false);
		this.clearHoursButton.setEnabled(false);
		this.updateButton.setEnabled(false);
	}
	
	protected void do_hoursTextField_focusGained(FocusEvent arg0) {
		this.hoursTextField.selectAll();
	}
	
	protected void do_updateButton_actionPerformed(ActionEvent arg0) {
		int id = Integer.parseInt(empIdTextField.getText());
		double payRate = Double.parseDouble(payRateTextField.getText());
		String name = empNameTextField.getText();
		Payroll payroll = (Payroll)employeeList.getSelectedValue();
		if (!payroll.setId(id)) {
			JOptionPane.showMessageDialog(null, "Invalid Employee ID. \nMust be > 100");
			empIdTextField.setText("100");
			empIdTextField.requestFocus();
		}
		else if (!payroll.setPayRate(payRate)) {
			JOptionPane.showMessageDialog(null, "Invalid Pay Rate. \nMust be between 7.25 and 100.0");
			payRateTextField.setText("7.25");
			payRateTextField.requestFocus();
		}
		else if (!payroll.setName(name)) {
			JOptionPane.showMessageDialog(null, "Invalid Name. \nMust enter a name.");
			empNameTextField.setText(payroll.getName());
			empNameTextField.requestFocus();
		}
		else {
			payroll.setId(id);
			payroll.setPayRate(payRate);
			payroll.setName(name);
		}
		employeeList.repaint();
		
	}
	
	protected void do_empIdTextField_focusGained(FocusEvent arg0) {
		this.empIdTextField.selectAll();
	}
	
	protected void do_empNameTextField_focusGained(FocusEvent e) {
		this.empNameTextField.selectAll();
	}
	
	protected void do_payRateTextField_focusGained(FocusEvent e) {
		this.payRateTextField.selectAll();
	}
	
	protected void do_this_windowClosing(WindowEvent arg0) {
		if (payrollObjMapper != null) {
			payrollObjMapper.writeAllPayroll(employeeListModel);
		}
	}
}
