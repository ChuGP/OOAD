package generator;

import java.util.List;
import adapter.ClassDetailInfoDTO;
import shapes.ClassFormat;
import shapes.ConcreteFormat;
import shapes.InterfaceFormat;

public class ClassUnitGenerator {
	private int x,y,width,height;
	private String className;
	private List<String> methods,variables;

	public ClassUnitGenerator(){
		setUpRec();
	}

	public ClassUnitGenerator(String className, List<String> methods,List<String>variables){
		setClassAttributes(className,methods,variables);
		setUpRec();
	}

	public ClassUnitGenerator(ClassDetailInfoDTO dto){
		setClassAttributes(dto);
		setUpRec();
	}

	private void setUpRec(){
		x=50;
		y=50;
		width=200;
		height=200;
	}

	public void setClassAttributes(ClassDetailInfoDTO dto){
		setClassAttributes(dto.getClassName(),dto.getMethods(),dto.getVariables());
	}

	public void setClassAttributes(String className, List<String> methods,List<String>variables){
		this.className=className;
		this.methods=methods;
		this.variables=variables;
	}

	public ClassFormat generateConcreteClassFormat() {
		ClassFormat classFormat=new ConcreteFormat(className,x,y,width,height);
		classFormat.setMethods(methods);
		classFormat.setVariables(variables);
		return classFormat;
	}

	public ClassFormat generateInterfaceClassFormat() {
		ClassFormat classFormat=new InterfaceFormat(className,x,y,width,height);
		classFormat.setMethods(methods);
		classFormat.setVariables(variables);
		return classFormat;
	}


}
