package native_jdbc_programing.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import native_jdbc_programing.dao.impl.DepartmentDaoImpl;
import native_jdbc_programing.dao.impl.TitleDaoImpl;
import native_jdbc_programing.dto.Department;
import native_jdbc_programing.dto.Employee;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTabbedPane;

public class DepartmentUI extends JFrame {

	private JPanel contentPane;
	private JPanel pDept;
	private JPanel pBtn;
	private JPanel pList;
	private JLabel lblDeptNo;
	private JTextField tfDeptNo;
	private JLabel lblDeptName;
	private JTextField tfDeptName;
	private JLabel lblFloor;
	private JTextField tfFloor;
	private JButton btnAdd;
	private JButton btnSub;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnCancel;
	private JButton btnUpdate;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField tfDept2;
	private JButton btnDept2;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		extracted();
	}

	public static void extracted() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentUI frame = new DepartmentUI();
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
	public DepartmentUI() {
		setTitle("부서정보");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pDept = new JPanel();
		contentPane.add(pDept);
		pDept.setLayout(new GridLayout(0, 2, 10, 0));

		lblDeptNo = new JLabel("부서번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblDeptNo);

		tfDeptNo = new JTextField();
		pDept.add(tfDeptNo);
		tfDeptNo.setColumns(10);

		lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblDeptName);

		tfDeptName = new JTextField();
		tfDeptName.setColumns(10);
		pDept.add(tfDeptName);

		lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblFloor);

		tfFloor = new JTextField();
		tfFloor.setColumns(10);
		pDept.add(tfFloor);

		pBtn = new JPanel();
		contentPane.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(insert());
		pBtn.add(btnAdd);

		btnSub = new JButton("삭제");
		btnSub.addActionListener(delete());
		pBtn.add(btnSub);

		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(update());
		pBtn.add(btnUpdate);

		btnCancel = new JButton("종료");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pBtn.add(btnCancel);

		pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		pList.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(getModel());
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 10, 0));
		
		lblNewLabel = new JLabel("부서별 사원");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel);
		
		tfDept2 = new JTextField();
		panel.add(tfDept2);
		tfDept2.setColumns(10);
		
		btnDept2 = new JButton("검색");
		btnDept2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_1.setModel(new DefaultTableModel(
						getEmployeeBydept(),
						new String[] {
							"사원번호", "사원명", "직책", "직속상사", "급여", "부서번호"
						}
					));
				
			}
		});
		panel.add(btnDept2);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			getEmployeeBydept(),
			new String[] {
				"사원번호", "사원명", "직책", "직속상사", "급여", "부서번호"
			}
		));
		scrollPane_1.setViewportView(table_1);

	}

	private Object[][] getEmployeeBydept() {
		try {
		Department a = new Department(Integer.parseInt(tfDept2.getText()));
		List<Employee> list = DepartmentDaoImpl.getInstance().selectDepartmentByDeptno(a);
		Object[][] arr = new Object[list.size()][6];
		for (int i = 0; i < list.size(); i++) {
			arr[i][0] = list.get(i).getEmpno();
			arr[i][1] = list.get(i).getEmpname();
			arr[i][2] = list.get(i).getTitle().getTno();
			arr[i][3] = list.get(i).getManager().getEmpno();
			arr[i][4] = list.get(i).getSalary();
			arr[i][5] = list.get(i).getDept().getDeptNo();

		}

		return arr;
		}catch(NumberFormatException e) {
			
		}catch(NullPointerException e) {
			
		}
		return null;
	}

	public ActionListener update() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int deptNo = Integer.parseInt(tfDeptNo.getText().trim());
					String deptName = tfDeptName.getText().trim();
					int floor = Integer.parseInt(tfFloor.getText().trim());

					Department department = new Department(deptNo, deptName, floor);
					DepartmentDaoImpl.getInstance().updateDepartment(department);
					table.setModel(getModel());
				} catch (NumberFormatException e1) {

				}
			}
		};
	}

	public ActionListener delete() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int no = Integer.parseInt(tfDeptNo.getText().trim());
				Department department = new Department(no);
				DepartmentDaoImpl.getInstance().deleteDepartment(department);
				table.setModel(getModel());
			}
		};
	}

	public ActionListener insert() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int deptNo = Integer.parseInt(tfDeptNo.getText().trim());
					String deptName = tfDeptName.getText().trim();
					int floor = Integer.parseInt(tfFloor.getText().trim());

					Department department = new Department(deptNo, deptName, floor);
					DepartmentDaoImpl.getInstance().insertDepartment(department);
					table.setModel(getModel());
				} catch (NumberFormatException e1) {

				}
			}
		};
	}

	public DefaultTableModel getModel() {
		return new DefaultTableModel(getDepartmentList(), getColumn());
	}

	public String[] getColumn() {
		return new String[] { "부서번호", "부서명", "위치" };
	}

	private Object[][] getDepartmentList() {
		List<Department> list = DepartmentDaoImpl.getInstance().selectDepartmentByAll();
		Object[][] arr = new Object[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			arr[i][0] = list.get(i).getDeptNo();
			arr[i][1] = list.get(i).getDeptName();
			arr[i][2] = list.get(i).getFloor();

		}

		return arr;
	}

}
