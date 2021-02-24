package native_jdbc_programing.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import native_jdbc_programing.dao.impl.TitleDaoImpl;
import native_jdbc_programing.dto.Title;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class TitleUI extends JFrame {

	private JPanel contentPane;
	private JTextField tfTno;
	private JTextField tfTname;
	private JTable tTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TitleUI frame = new TitleUI();
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
	public TitleUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 287, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel pTitle = new JPanel();
		contentPane.add(pTitle);
		pTitle.setLayout(new GridLayout(0, 2, 20, 0));
		
		JLabel lblTno = new JLabel("직책번호");
		lblTno.setHorizontalAlignment(SwingConstants.RIGHT);
		pTitle.add(lblTno);
		
		tfTno = new JTextField();
		pTitle.add(tfTno);
		tfTno.setColumns(10);
		
		JLabel lblTname = new JLabel("직책명");
		lblTname.setHorizontalAlignment(SwingConstants.RIGHT);
		pTitle.add(lblTname);
		
		tfTname = new JTextField();
		tfTname.setColumns(10);
		pTitle.add(tfTname);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		JButton btnAdd = new JButton("추가");
		pBtn.add(btnAdd);
		
		JButton btnUpdate = new JButton("수정");
		pBtn.add(btnUpdate);
		
		JButton btnSub = new JButton("삭제");
		pBtn.add(btnSub);
		
		JButton btnCancel = new JButton("종료");
		pBtn.add(btnCancel);
		
		JPanel pLIst = new JPanel();
		contentPane.add(pLIst);
		pLIst.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pLIst.add(scrollPane, BorderLayout.CENTER);
		
		tTable = new JTable();
		setTable();
		scrollPane.setViewportView(tTable);
	}

	public void setTable() {
		List<Title> list = TitleDaoImpl.getInstance().selectTitleByAll();
		tTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"직책번호", "직책명"
			}
		));
	}

}
