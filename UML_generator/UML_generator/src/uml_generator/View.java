package uml_generator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

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
import ClassDetailInfo.ClassRelarionship;
import ClassDetailInfo.MemberFunction;
import ClassDetailInfo.MemberVariable;
import viewTool.Tool;

public class View {
	private JFrame _frame;
	private JPanel contentPane;
	private JTextField textName;
	private JTable variableTable;
	private Map<String,ClassDetailInfo> variableList = new TreeMap<>();
	
	private ClassDetailInfo gp = new ClassDetailInfo();
	private ClassMemberAbstract memberVariable = new MemberVariable();
	private ClassMemberAbstract memberFunction = new MemberFunction();
	private ClassMemberAbstract relationship = new ClassRelarionship();
	private JTable functionTable;
	private JTable relationshipTable;
    private Tool tool = new Tool();
	public View() {}
	
	public void guiView( JPanel contentPane ) {		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("新細明體", Font.PLAIN, 20));
		tabbedPane.setBounds(0, 0, 1902, 1033);
		contentPane.add(tabbedPane);
		
		JPanel Input = new JPanel();
		tabbedPane.addTab("Input Class Info", null, Input, null);
		Input.setLayout(null);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Sketch", null, panel, null);
		panel.setLayout(null);

		// Label
		JLabel className = new JLabel("Class Name");
		className.setBounds(427, 39, 272, 59);
		className.setFont(new Font("Arial", Font.PLAIN, 50));
		Input.add(className);
		// Label
		
		// 輸入Class Name的textarea
		textName = new JTextField();
		textName.setBounds(719, 29, 506, 78);
		textName.setFont(new Font("MS Gothic", Font.PLAIN, 50));
		Input.add(textName);
		textName.setColumns(20);
		
		// 顯示框(textArea)
		JTextArea textShowDetail = new JTextArea();
		textShowDetail.setEditable(false);
		textShowDetail.setBounds(1484, 246, 399, 521);
		textShowDetail.setBackground(new Color(211, 211, 211));
		textShowDetail.setForeground(Color.BLACK);
		textShowDetail.setFont(new Font("Footlight MT Light", Font.PLAIN, 30));
		Input.add(textShowDetail);
		// Read Name Button
		JButton btnReadName = new JButton("Read Name");
		btnReadName.setForeground(Color.WHITE);
		btnReadName.setBounds(1239, 28, 185, 39);
		btnReadName.setBackground(new Color(240, 240, 240));
		btnReadName.setFont(new Font("Arial", Font.PLAIN, 26));
		btnReadName.setBackground(Color.BLACK);
		btnReadName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				if ( textName.getText() != null && textName.getText() != "" ) {
					gp.setClassName(textName.getText());
					textName.setText(null);
					tool.showOnTextArea(textShowDetail, gp);
				}
				
				
			}
		});
		Input.add(btnReadName);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Member Variable");
		lblNewLabel.setBounds(122, 194, 257, 41);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 35));
		Input.add(lblNewLabel);
		
		
		
		JLabel lblClassDetailInfo = new JLabel("Class Detail Info");
		lblClassDetailInfo.setBounds(1544, 186, 284, 47);
		lblClassDetailInfo.setFont(new Font("Arial", Font.PLAIN, 40));
		Input.add(lblClassDetailInfo);
		
		JScrollPane scrollPaneVariable = new JScrollPane();
		scrollPaneVariable.setBounds(33, 246, 492, 434);
		Input.add(scrollPaneVariable);
		
		variableTable = new JTable();
		JTableHeader headVariable = variableTable.getTableHeader(); // 创建表格标题对象
		headVariable.setPreferredSize(new Dimension(headVariable.getWidth(), 25));// 设置表头大小
		headVariable.setFont(new Font("楷体", Font.PLAIN, 22));// 设置表格字体

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
		variableTable.getColumnModel().getColumn(0).setPreferredWidth(124);
		variableTable.getColumnModel().getColumn(1).setPreferredWidth(168);
		variableTable.getColumnModel().getColumn(2).setPreferredWidth(196);
		
		// 把ComboBox加入tabel1
		
		TableColumn referenceColumn = variableTable.getColumn("Reference");
		scrollPaneVariable.setViewportView(variableTable);
		
		
		
		
		
		JButton btnReadVariable = new JButton("Read Variable");
		btnReadVariable.setForeground(Color.WHITE);
		btnReadVariable.setBounds(33, 693, 247, 46);
		btnReadVariable.setFont(new Font("Arial", Font.PLAIN, 26));
		btnReadVariable.setBackground(Color.BLACK);
		Input.add(btnReadVariable);
		
		btnReadVariable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ClassMemberAbstract> member = tool.getTableValue(variableTable,memberVariable,"Variable");
				gp.clearMemberVariable();
				for ( int i=0; i < member.size();i++)
				{
					gp.setMemberVariable(member.get(i));
					String ans = member.get(i).getReference()+ " " + member.get(i).getType() + " " + member.get(i).getName() ;
					textShowDetail.append(ans);
				}
				variableList.put(gp.getClassName(), gp);
				tool.showOnTextArea(textShowDetail, gp);
			}
		});
		
		JLabel lblMemberFunction = new JLabel("Member Function");
		lblMemberFunction.setBounds(674, 194, 266, 41);
		lblMemberFunction.setFont(new Font("Arial", Font.PLAIN, 35));
		Input.add(lblMemberFunction);
		
		JScrollPane scrollPaneFunction = new JScrollPane();
		scrollPaneFunction.setBounds(539, 246, 585, 434);
		Input.add(scrollPaneFunction);
		
		functionTable = new JTable();
		JTableHeader headFunction= functionTable.getTableHeader(); // 创建表格标题对象
		headFunction.setPreferredSize(new Dimension(headFunction.getWidth(), 22));// 设置表头大小
		headFunction.setFont(new Font("楷体", Font.PLAIN, 22));// 设置表格字体
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
		functionTable.getColumnModel().getColumn(0).setPreferredWidth(135);
		functionTable.getColumnModel().getColumn(1).setPreferredWidth(204);
		functionTable.getColumnModel().getColumn(2).setPreferredWidth(395);
		functionTable.setRowHeight(25);
		scrollPaneFunction.setViewportView(functionTable);
		
		TableColumn referenceColumn_function = functionTable.getColumn("Reference");
		
		
		
		
		
		
		JButton btnReadFunction = new JButton("Read Function");
		btnReadFunction.setForeground(Color.WHITE);
		btnReadFunction.setBounds(539, 693, 290, 46);
		btnReadFunction.setBackground(Color.BLACK);
		btnReadFunction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<ClassMemberAbstract> member = tool.getTableValue(functionTable,memberFunction,"Function");
				gp.clearMemberFunction();
				for ( int i=0; i < member.size();i++)
				{
					gp.setMemberFunction(member.get(i));
					String ans = member.get(i).getReference()+ " " + member.get(i).getType() + " " + member.get(i).getName() ;
					textShowDetail.append(ans);
				}
				variableList.put(gp.getClassName(), gp);
				tool.showOnTextArea(textShowDetail, gp);

			}
		});
		
		btnReadFunction.setFont(new Font("Arial", Font.PLAIN, 26));
		Input.add(btnReadFunction);
		
		JLabel labelRelation = new JLabel("Class Relationship");
		labelRelation.setFont(new Font("Arial", Font.PLAIN, 35));
		labelRelation.setBounds(1157, 194, 281, 41);
		Input.add(labelRelation);
		
		JScrollPane scrollPaneRelationship = new JScrollPane();
		scrollPaneRelationship.setBounds(1138, 246, 332, 434);
		Input.add(scrollPaneRelationship);
		
		// Relationship_table
		relationshipTable = new JTable();
		JTableHeader headRelationship= relationshipTable.getTableHeader(); // 创建表格标题对象
		headRelationship.setPreferredSize(new Dimension(headRelationship.getWidth(), 35));// 设置表头大小
		headRelationship.setFont(new Font("楷体", Font.PLAIN, 22));// 设置表格字体
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
		btnReadRelationship.setForeground(Color.WHITE);
		btnReadRelationship.setBackground(Color.BLACK);
		btnReadRelationship.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		      ArrayList<ClassMemberAbstract> member = tool.getTableValue(relationshipTable,relationship,"Relation");
			  gp.clearClassRelarionship();
			  for ( int i=0; i < member.size();i++)
			  {
			    gp.setClassRelarionship(member.get(i));
			    String ans = member.get(i).getReference()+ " " + member.get(i).getName() ;
				textShowDetail.append(ans);
			  }
			  variableList.put(gp.getClassName(), gp);
			  tool.showOnTextArea(textShowDetail, gp);
			}
		});
		btnReadRelationship.setFont(new Font("Arial", Font.PLAIN, 16));
		btnReadRelationship.setBounds(1137, 692, 168, 47);
		Input.add(btnReadRelationship);
		
		JButton btnDrawSketch = new JButton("Draw Sketch \u2192");
		btnDrawSketch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool.clearTableValue(variableTable);
				tool.clearTableValue(functionTable);
				tool.clearTableValue(relationshipTable);
				textShowDetail.setText("");
			}
		});
		btnDrawSketch.setFont(new Font("Arial", Font.PLAIN, 26));
		btnDrawSketch.setBounds(1484, 772, 399, 39);
		Input.add(btnDrawSketch);
		
		JButton btnClearVariable = new JButton("Clear Variable");
		btnClearVariable.setBackground(Color.WHITE);
		btnClearVariable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tool.clearTableValue(variableTable);
				gp.clearMemberVariable();
				tool.showOnTextArea(textShowDetail, gp);
			}
		});
		btnClearVariable.setFont(new Font("Arial", Font.PLAIN, 26));
		btnClearVariable.setBounds(278, 693, 247, 46);
		Input.add(btnClearVariable);
		
		JButton btnClearFunction = new JButton("Clear Function");
		btnClearFunction.setBackground(Color.WHITE);
		btnClearFunction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool.clearTableValue(functionTable);
				gp.clearMemberFunction();
				tool.showOnTextArea(textShowDetail, gp);
			}
		});
		btnClearFunction.setFont(new Font("Arial", Font.PLAIN, 26));
		btnClearFunction.setBounds(828, 693, 296, 46);
		Input.add(btnClearFunction);
		
		JButton btnClearRelationship = new JButton("Clear Relationship");
		btnClearRelationship.setBackground(Color.WHITE);
		btnClearRelationship.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool.clearTableValue(relationshipTable);
				gp.clearClassRelarionship();
				tool.showOnTextArea(textShowDetail, gp);
			}
		});
		btnClearRelationship.setFont(new Font("Arial", Font.PLAIN, 16));
		btnClearRelationship.setBounds(1296, 693, 174, 46);
		Input.add(btnClearRelationship);
		
		JButton btnClearName = new JButton("Clear Name");
		btnClearName.setFont(new Font("Arial", Font.PLAIN, 26));
		btnClearName.setBackground(Color.WHITE);
		btnClearName.setBounds(1239, 68, 185, 39);
		Input.add(btnClearName);
		
		
		btnClearName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textShowDetail.setText("");
				gp.clearClassName();
				tool.showOnTextArea(textShowDetail, gp);
			}
		});
		
		JComboBox comboBox_variable = new JComboBox();
		comboBox_variable.setFont(new Font("標楷", Font.PLAIN, 20));
		comboBox_variable.addItem("Public");
		comboBox_variable.addItem("Private");
		comboBox_variable.addItem("Protected");
		referenceColumn.setCellEditor(new DefaultCellEditor(comboBox_variable));
		
		JComboBox comboBox_function = new JComboBox();
		comboBox_function.setFont(new Font("標楷", Font.PLAIN, 20));
		comboBox_function.addItem("Public");
		comboBox_function.addItem("Private");
		comboBox_function.addItem("Protected");
		referenceColumn_function.setCellEditor(new DefaultCellEditor(comboBox_function));
	    
		JComboBox comboBox_relationship = new JComboBox();
		comboBox_relationship.setFont(new Font("標楷", Font.PLAIN, 20));
		comboBox_relationship.addItem("Extension");
		comboBox_relationship.addItem("Implementation");
		comboBox_relationship.addItem("Association");
		comboBox_relationship.addItem("Aggregation");
		comboBox_relationship.addItem("Composition");
		comboBox_relationship.addItem("Dependency");
		referenceColumn_relationship.setCellEditor(new DefaultCellEditor(comboBox_relationship));
	}

}