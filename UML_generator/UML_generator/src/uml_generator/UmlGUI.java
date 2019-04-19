package uml_generator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.JTabbedPane;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import java.awt.Component;

import ClassDetailInfo.*;
public class UmlGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTable variableTable;
	private Map<String,ClassDetailInfo> variableList = new TreeMap<>();
	
	private ClassDetailInfo gp = new ClassDetailInfo();
	private ClassMemberAbstract variableInfo = new MemberVariable();
	private ClassMemberAbstract MemberFunction = new MemberFunction();
//	private MemberVariable variableInfo = new MemberVariable();
//	private MemberFunction functionInfo = new MemberFunction();
//	private ClassRelarionship relationship = new ClassRelarionship();
	
	private JTable functionTable;
	private JTable relationshipTable;

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
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		

		// Label
		JLabel className = new JLabel("Class Name");
		className.setBounds(420, 77, 226, 54);
		className.setFont(new Font("Arial", Font.PLAIN, 40));
		contentPane.add(className);
		// Label
		
		// 輸入Class Name的textarea
		textName = new JTextField();
		textName.setBounds(651, 79, 469, 54);
		textName.setFont(new Font("新細明體", Font.PLAIN, 40));
		contentPane.add(textName);
		textName.setColumns(20);
		
		// 顯示框(textArea)
		JTextArea textShowDetail = new JTextArea();
		textShowDetail.setBounds(1377, 226, 490, 722);
		textShowDetail.setBackground(new Color(211, 211, 211));
		textShowDetail.setForeground(Color.BLACK);
		textShowDetail.setFont(new Font("Arial", Font.PLAIN, 30));
		contentPane.add(textShowDetail);
		// Read Name Button
		JButton btnReadName = new JButton("Read Name");
		btnReadName.setBounds(1134, 85, 193, 39);
		btnReadName.setBackground(new Color(240, 240, 240));
		btnReadName.setFont(new Font("Arial", Font.PLAIN, 26));
//		
		btnReadName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(textName.getText());
				gp.set_class_name(textName.getText());
				textName.setText(null);
				String temp = "Class Name: " + gp.get_class_name() + "\n";
				textShowDetail.append(temp);
				
			}
		});
		contentPane.add(btnReadName);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Member Variable");
		lblNewLabel.setBounds(441, 180, 266, 41);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 35));
		contentPane.add(lblNewLabel);
		
		
		
		JLabel lblClassDetailInfo = new JLabel("Class Detail Info");
		lblClassDetailInfo.setBounds(1458, 170, 284, 51);
		lblClassDetailInfo.setFont(new Font("Arial", Font.PLAIN, 40));
		contentPane.add(lblClassDetailInfo);
		
		JScrollPane scrollPaneVariable = new JScrollPane();
		scrollPaneVariable.setBounds(295, 226, 610, 207);
		contentPane.add(scrollPaneVariable);
		
		variableTable = new JTable();
		variableTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		variableTable.setToolTipText("");
		variableTable.setFont(new Font("Arial", Font.PLAIN, 20));
		variableTable.setRowHeight(25);
		
		variableTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Reference", "Variable Type", "Variable Name"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		variableTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		variableTable.getColumnModel().getColumn(2).setPreferredWidth(300);
		
		// 把ComboBox加入tabel1
		
		TableColumn referenceColumn = variableTable.getColumn("Reference");
		scrollPaneVariable.setViewportView(variableTable);
		
		
		
		
		
		JButton btnReadVariable = new JButton("Read Variable");
		btnReadVariable.setBounds(295, 439, 610, 59);
		btnReadVariable.setFont(new Font("Arial", Font.PLAIN, 26));
		contentPane.add(btnReadVariable);
		btnReadVariable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassMemberAbstract member = getTableValue(variableTable,variableInfo);
				gp.set_MemberVariable(member);
				variableList.put(gp.get_class_name(), gp);
				for (  ClassMemberAbstract temp: gp.get_MemberVariable())
				{
				  String ans = temp.get_Reference()+ " " + temp.get_Type()+ " " + temp.get_Name()+"\n";
				  System.out.println( ans+"\n");
				}
				
			}
		});
		JLabel lblMemberFunction = new JLabel("Member Function");
		lblMemberFunction.setBounds(441, 533, 266, 41);
		lblMemberFunction.setFont(new Font("Arial", Font.PLAIN, 35));
		contentPane.add(lblMemberFunction);
		
		JScrollPane scrollPaneFunction = new JScrollPane();
		scrollPaneFunction.setBounds(295, 579, 610, 310);
		contentPane.add(scrollPaneFunction);
		
		functionTable = new JTable();
		functionTable.setFont(new Font("Arial", Font.PLAIN, 20));
		functionTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Reference", "Function Type", "Function Name && Parameter"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		functionTable.getColumnModel().getColumn(0).setPreferredWidth(120);
		functionTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		functionTable.getColumnModel().getColumn(2).setPreferredWidth(336);
		functionTable.setRowHeight(25);
		scrollPaneFunction.setViewportView(functionTable);
		
		TableColumn referenceColumn_function = functionTable.getColumn("Reference");
		
		
		
		
		
		
		JButton btnReadFunction = new JButton("Read Function");
		btnReadFunction.setBounds(295, 894, 610, 59);
		btnReadFunction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClassMemberAbstract member = getTableValue(functionTable,MemberFunction);
				gp.set_MemberFunction(member);
				variableList.put(gp.get_class_name(), gp);
				for (  ClassMemberAbstract temp: gp.get_MemberFunction())
				{
				  String ans = temp.get_Reference()+ " " + temp.get_Type()+ " " + temp.get_Name()+"\n";
				  System.out.println( ans+"\n");
				}
			}
		});
		
		btnReadFunction.setFont(new Font("Arial", Font.PLAIN, 26));
		contentPane.add(btnReadFunction);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 5, 5);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		
		JLabel labelRelation = new JLabel("Class Relationship");
		labelRelation.setFont(new Font("Arial", Font.PLAIN, 35));
		labelRelation.setBounds(1014, 170, 284, 41);
		contentPane.add(labelRelation);
		
		JScrollPane scrollPaneRelationship = new JScrollPane();
		scrollPaneRelationship.setBounds(972, 222, 355, 531);
		contentPane.add(scrollPaneRelationship);
		
		// Relationship_table
		relationshipTable = new JTable();
		relationshipTable.setFont(new Font("Arial", Font.PLAIN, 20));
		relationshipTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Relationship", "Class Name"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		TableColumn referenceColumn_relationship = relationshipTable.getColumn("Relationship");
		relationshipTable.setRowHeight(25);
		scrollPaneRelationship.setViewportView(relationshipTable);
		
		JButton btnReadRelationship = new JButton("Read Relationship");
		btnReadRelationship.setFont(new Font("Arial", Font.PLAIN, 26));
		btnReadRelationship.setBounds(972, 766, 355, 59);
		contentPane.add(btnReadRelationship);
		
		JComboBox comboBox_variable = new JComboBox();
		comboBox_variable.addItem("Public");
		comboBox_variable.addItem("Private");
		comboBox_variable.addItem("Protected");
		referenceColumn.setCellEditor(new DefaultCellEditor(comboBox_variable));
		
		JComboBox comboBox_function = new JComboBox();
		comboBox_function.addItem("Public");
		comboBox_function.addItem("Private");
		comboBox_function.addItem("Protected");
		referenceColumn_function.setCellEditor(new DefaultCellEditor(comboBox_function));
	    
		JComboBox comboBox_relationship = new JComboBox();
		comboBox_relationship.addItem("Extends");
		comboBox_relationship.addItem("Implements");
		comboBox_relationship.addItem("Aggregation");
		referenceColumn_relationship.setCellEditor(new DefaultCellEditor(comboBox_relationship));
		
		
	}
	
	public ClassMemberAbstract getTableValue(JTable temp,ClassMemberAbstract member)
	{
		int row = temp.getModel().getRowCount();
		int column = temp.getModel().getColumnCount();
		Object selected;
		
		for (int i=0; i < row; i++)
		{	  
		  for (int j=0; j < column; j++)
		  {
			selected = temp.getModel().getValueAt(i,j);
		    if ( selected != null)
		    {
		      if ( j == 0 ) member.set_Reference(selected.toString());
			  else if ( j == 1 ) member.set_Type(selected.toString());
			  else member.set_Name(selected.toString());
//			  System.out.println(selected.toString());
		    }	    
		  }	  
		}
		
		return member;
//		gp.set_MemberVariable(member);
//		System.out.println(gp.get_MemberVariable().get(0).get_Reference());
//		variableList.put(gp.get_class_name(), gp);
	}
	
	
}
