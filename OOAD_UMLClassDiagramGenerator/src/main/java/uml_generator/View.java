package uml_generator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import ClassDetailInfo.*;
import adapter.ClassDetailInfoDTO;
import diagrams.UMLClassDiagram;
import diagrams.UMLClassDiagramDrawer;
import generator.ArrangeCalculator;
import generator.ClassUnitGenerator;
import listeners.Listener;
import listeners.ListenerHandler;
import shapes.ClassFormat;
import viewTool.Tool;
public class View {
	private JTextField textName;
	private JTable variableTable;
	private Map<String,ClassDetailInfo> variableList = new TreeMap<>();
	private ClassMemberAbstract memberVariable = new MemberVariable();
	private ClassMemberAbstract memberFunction = new MemberFunction();
	private ClassMemberAbstract relationship = new ClassRelationship();
	private JTable functionTable;
	private JTable relationshipTable;
    private Tool tool = new Tool();
    private int counter = 1;
    private ClassDetailInfo classDetailInfo = new ClassDetailInfo();
	UMLClassDiagram diagram;
	ClassUnitGenerator unitGenerator;
	UMLClassDiagramDrawer drawer;
	ArrangeCalculator arrangeCalculator;
	ListenerHandler listenerHandler;
	Listener listener;
	public JPanel setUpDrawer(int width,int height){
		int x=width-50;
		diagram=new UMLClassDiagram(width,height);
		unitGenerator=new ClassUnitGenerator();
		drawer=new UMLClassDiagramDrawer(diagram);
		arrangeCalculator =new ArrangeCalculator(diagram);
		arrangeCalculator.arrange();
		listenerHandler=new ListenerHandler(diagram);
		listener=new Listener(drawer,listenerHandler);
		ButtonGroup group=new ButtonGroup();
		JRadioButton moveUnit=new JRadioButton("Move Unit");
		JRadioButton addRelation=new JRadioButton("Add Relation");
		JRadioButton removeRelation=new JRadioButton("Remove Relation");
		JRadioButton removeUnit=new JRadioButton("Remove Unit");
		JButton outputButton=new JButton("Output");
		JButton saveButton=new JButton("Save");
		JButton openFileButton=new JButton("Open File");
		openFileButton.addActionListener(listener.openFileListener);
		outputButton.addActionListener(listener.outputListener);
		saveButton.addActionListener(listener.saveListener);
		moveUnit.addItemListener(listener.moveUnit);
		addRelation.addItemListener(listener.addRelation);
		removeRelation.addItemListener(listener.removeRelation);
		removeUnit.addItemListener(listener.removeUnit);
		openFileButton.setBounds(x-300,50,75,30);
		outputButton.setBounds(x-200,50,75,30);
		saveButton.setBounds(x-100,50,75,30);
		moveUnit.setBounds(x-450,0,100,50);
		addRelation.setBounds(x-350,0,100,50);
		removeRelation.setBounds(x-250,0,150,50);
		removeUnit.setBounds(x-100,0,100,50);
		group.add(moveUnit);
		group.add(addRelation);
		group.add(removeRelation);
		group.add(removeUnit);
		drawer.add(openFileButton);
		drawer.add(outputButton);
		drawer.add(saveButton);
		drawer.add(moveUnit);
		drawer.add(addRelation);
		drawer.add(removeRelation);
		drawer.add(removeUnit);
		drawer.setVisible(true);
		return drawer;
	}
	public void guiView( JPanel contentPane) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int width=(int)d.getWidth(),height=(int)d.getHeight();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("新細明體", Font.PLAIN, 20));
		tabbedPane.setBounds(0, 0, 1902, 1033);
		contentPane.add(tabbedPane);
		
		JPanel Input = new JPanel();
		tabbedPane.addTab("Input Class Info", null, Input, null);
		Input.setLayout(null);

		JPanel drawer = setUpDrawer(width,height); // 這邊要把你的panel替換過來!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//		JPanel drawer=new JPanel();
		drawer.setBounds(0,0,width,height);
//		drawer.add(label);

		tabbedPane.addTab("Sketch", null, drawer, null);
		drawer.setLayout(null);

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
		
		// Read Name Button
		JButton btnReadName = new JButton("Read Name");
		btnReadName.setForeground(Color.WHITE);
		btnReadName.setBounds(1239, 28, 185, 39);
		btnReadName.setBackground(new Color(240, 240, 240));
		btnReadName.setFont(new Font("Arial", Font.PLAIN, 26));
		btnReadName.setBackground(Color.BLACK);
		btnReadName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				if ( textName.getText() != null && !textName.getText().equals(""))
					classDetailInfo.setClassName(textName.getText());
				else
					classDetailInfo.setClassName("");
				tool.showOnTextArea(textShowDetail, classDetailInfo);
				
			}
		});
		Input.add(btnReadName);
		
		JButton btnReadVariable = new JButton("Read Variable");
		btnReadVariable.setForeground(Color.WHITE);
		btnReadVariable.setBounds(33, 693, 247, 46);
		btnReadVariable.setFont(new Font("Arial", Font.PLAIN, 26));
		btnReadVariable.setBackground(Color.BLACK);
		Input.add(btnReadVariable);
		
		// Read Variable Button
		btnReadVariable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ClassMemberAbstract> member = tool.getTableValue(variableTable,memberVariable,"Variable");
				classDetailInfo.clearMemberVariable();
				for ( int i=0; i < member.size();i++)
				{
					classDetailInfo.setMemberVariable(member.get(i));
					String ans = member.get(i).getReference()+ " " + member.get(i).getType() + " " + member.get(i).getName() ;
					textShowDetail.append(ans);
				}
				variableList.put(classDetailInfo.getClassName(), classDetailInfo);
				tool.showOnTextArea(textShowDetail, classDetailInfo);
			}
		});
		
		JButton btnReadRelationship = new JButton("Read Relationship");
		btnReadRelationship.setForeground(Color.WHITE);
		btnReadRelationship.setBackground(Color.BLACK);
		//Read Relationship Button
		btnReadRelationship.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		      ArrayList<ClassMemberAbstract> member = tool.getTableValue(relationshipTable,relationship,"Relation");
			  classDetailInfo.clearClassRelarionship();
			  for ( int i=0; i < member.size();i++)
			  {
			    classDetailInfo.setClassRelarionship(member.get(i));
			    String ans = member.get(i).getReference()+ " " + member.get(i).getName() ;
				textShowDetail.append(ans);
			  }
			  variableList.put(classDetailInfo.getClassName(), classDetailInfo);
			  tool.showOnTextArea(textShowDetail, classDetailInfo);
			}
		});
		btnReadRelationship.setFont(new Font("Arial", Font.PLAIN, 16));
		btnReadRelationship.setBounds(1137, 692, 168, 47);
		Input.add(btnReadRelationship);
		
		JButton btnShowSketch = new JButton("Show Sketch \u2192");
		// Draw Sketch Button
		btnShowSketch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				tool.clearTableValue(variableTable);
				tool.clearTableValue(functionTable);
				tool.clearTableValue(relationshipTable);
				textShowDetail.setText("");
				tabbedPane.setSelectedComponent(drawer);
				if (classDetailInfo.getClassName().equals("")){
					classDetailInfo.setClassName("NewClass"+counter);
					counter++;
				}
				ClassDetailInfoDTO dto=new ClassDetailInfoDTO(classDetailInfo);
				unitGenerator.setClassAttributes(dto);
				ClassFormat classFormat=unitGenerator.generateConcreteClassFormat();
				diagram.addToDiagram(classFormat);
				classDetailInfo = new  ClassDetailInfo();
			}
		});
		btnShowSketch.setFont(new Font("Arial", Font.PLAIN, 26));
		btnShowSketch.setBounds(1484, 772, 399, 39);
		Input.add(btnShowSketch);
		
		JButton btnClearVariable = new JButton("Clear Variable");
		// Clear Variable Button
		btnClearVariable.setBackground(Color.WHITE);
		btnClearVariable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tool.clearTableValue(variableTable);
				classDetailInfo.clearMemberVariable();
				tool.showOnTextArea(textShowDetail, classDetailInfo);
			}
		});
		
		JButton btnReadFunction = new JButton("Read Function");
		btnReadFunction.setForeground(Color.WHITE);
		btnReadFunction.setBounds(539, 693, 290, 46);
		btnReadFunction.setBackground(Color.BLACK);
		//Read Function Button
		btnReadFunction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<ClassMemberAbstract> member = tool.getTableValue(functionTable,memberFunction,"Function");
				classDetailInfo.clearMemberFunction();
				for ( int i=0; i < member.size();i++)
				{
					classDetailInfo.setMemberFunction(member.get(i));
					String ans = member.get(i).getReference()+ " " + member.get(i).getType() + " " + member.get(i).getName() ;
					textShowDetail.append(ans);
				}
				variableList.put(classDetailInfo.getClassName(), classDetailInfo);
				tool.showOnTextArea(textShowDetail, classDetailInfo);
                 
			}
		});
		btnReadFunction.setFont(new Font("Arial", Font.PLAIN, 26));
		Input.add(btnReadFunction);
		btnClearVariable.setFont(new Font("Arial", Font.PLAIN, 26));
		btnClearVariable.setBounds(278, 693, 247, 46);
		Input.add(btnClearVariable);
		
		JButton btnClearFunction = new JButton("Clear Function");
		//Clear Function Button
		btnClearFunction.setBackground(Color.WHITE);
		btnClearFunction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool.clearTableValue(functionTable);
				classDetailInfo.clearMemberFunction();
				tool.showOnTextArea(textShowDetail, classDetailInfo);
			}
		});
		btnClearFunction.setFont(new Font("Arial", Font.PLAIN, 26));
		btnClearFunction.setBounds(828, 693, 296, 46);
		Input.add(btnClearFunction);
		
		JButton btnClearRelationship = new JButton("Clear Relationship");
		// Clear Relationship Button
		btnClearRelationship.setBackground(Color.WHITE);
		btnClearRelationship.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool.clearTableValue(relationshipTable);
				classDetailInfo.clearClassRelarionship();
				tool.showOnTextArea(textShowDetail, classDetailInfo);
			}
		});
		btnClearRelationship.setFont(new Font("Arial", Font.PLAIN, 16));
		btnClearRelationship.setBounds(1296, 693, 174, 46);
		Input.add(btnClearRelationship);
		
		JButton btnClearName = new JButton("Clear Name");
		//Clear Name Button
		btnClearName.setFont(new Font("Arial", Font.PLAIN, 26));
		btnClearName.setBackground(Color.WHITE);
		btnClearName.setBounds(1239, 68, 185, 39);
		Input.add(btnClearName);
		
		// Clear Name Button
		btnClearName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textName.setText(null);
				textShowDetail.setText("");
				classDetailInfo.clearClassName();
				tool.showOnTextArea(textShowDetail, classDetailInfo);
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
