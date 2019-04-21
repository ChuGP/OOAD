package viewTool;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextArea;

import ClassDetailInfo.ClassDetailInfo;
import ClassDetailInfo.ClassMemberAbstract;
import ClassDetailInfo.ClassRelarionship;
import ClassDetailInfo.MemberFunction;
import ClassDetailInfo.MemberVariable;

public class Tool {
	public Tool() {}
	public void clearTableValue(JTable temp)
	{
		int row = temp.getModel().getRowCount();
		int column = temp.getModel().getColumnCount();	
		for (int i=0; i < row; i++)
		{	  
		  for (int j=0; j < column; j++)
		  {
			temp.getModel().setValueAt("",i,j); 
		  }	  
		}
		
	}
	
	public void showOnTextArea(JTextArea _textShowDetail, ClassDetailInfo _gp) 
	{
		_textShowDetail.setText("");
		if ( ! _gp.getClassName().isEmpty())
		  _textShowDetail.append("Class Name : " + _gp.getClassName() +"\n");
		
		if ( ! _gp.getMemberVariable().isEmpty() )
		  _textShowDetail.append("MemberVariable : " +"\n");
		for ( ClassMemberAbstract temp : _gp.getMemberVariable())
		{
			String ans = temp.getReference()+ " " + temp.getType() + " " + temp.getName() + "\n";
			_textShowDetail.append(ans);
		}
		
		if ( ! _gp.getMemberFunction().isEmpty() )
		  _textShowDetail.append("MemberFunction : " +"\n");
		
		for ( ClassMemberAbstract temp : _gp.getMemberFunction())
		{
			String ans = temp.getReference()+ " " + temp.getType() + " " + temp.getName() + "\n";
			_textShowDetail.append(ans);
		}
		
		if ( ! _gp.getClassRelarionship().isEmpty() ) 
			_textShowDetail.append("ClassRelarionship : " +"\n");
		for ( ClassMemberAbstract temp : _gp.getClassRelarionship())
		{
			String ans = temp.getReference()+ " " + temp.getName() + "\n";
			_textShowDetail.append(ans);
		}
	}
	
	public ArrayList<ClassMemberAbstract> getTableValue(JTable temp,ClassMemberAbstract member,String choice)
	{
		//要改
		
		ArrayList<ClassMemberAbstract> result =new ArrayList<ClassMemberAbstract>();
		int row = temp.getModel().getRowCount();
		int column = temp.getModel().getColumnCount();
		Object selected=null;
		for (int i=0; i < row; i++)
		{
		  if ( choice == "Variable" ) member = new MemberVariable(); // 三個型別共用function
		  else if ( choice == "Function") member = new MemberFunction();
		  else if ( choice == "Relation" ) member = new ClassRelarionship();
		  else return null;
		  
		  for (int j=0; j < column; j++)
		  {
			selected = temp.getModel().getValueAt(i,j);
		    if ( selected != "" && selected != null )
		    {
		      if ( j == 0 ) member.setReference(selected.toString());
			  else if ( j == 1 && temp.getColumnName(j) != "Class Name"  ) member.setType(selected.toString()); // Class Name不是呼叫setType
			  else member.setName(selected.toString());
		    }	    
		  }	  
		  
		  if ( selected != "" && selected != null) result.add(member);
		  else break;
		    
		}
		
		return result;

	}

}
