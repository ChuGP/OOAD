package uml_generator;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import ClassDetailInfo.ClassDetailInfo;
import ClassDetailInfo.ClassMemberAbstract;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Component;
public class UmlGUI extends JFrame {

	private JPanel contentPane;
	private ClassDetailInfo gp = new ClassDetailInfo();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UmlGUI frame = new UmlGUI();
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
	public UmlGUI() {		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setType(Type.POPUP);
		setTitle("      \t\tUML_GENERATOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)screensize.getHeight() ;
		int width = (int)screensize.getWidth() ;
		setBounds(0, 0, 1920, 1080);
		
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(1920, 1080));

		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		View view = new View();  // 我刻意移過去的
		view.guiView(contentPane);
//		System.out.println(view.getVariableList());
		
	}
}
