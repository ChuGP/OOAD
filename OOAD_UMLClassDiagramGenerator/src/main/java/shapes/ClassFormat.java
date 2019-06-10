package shapes;

import diagrams.Drawable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public abstract class ClassFormat extends Rectangle implements Drawable{
	private String className;
	private List<Relation> relations;
	private List<String> methods;
	private List<String> variables;
	public ClassFormat(String className,int x,int y,int width,int height) {
		super(x,y,width,height);
		setClassName(className);
		relations=new ArrayList<>();
	}

	@Override
	public boolean contains(Point2D p){
		if(width<=0 || height<=0)
			return false;
		else{
			if((p.getX()>=x && p.getX()<=x+width) && (p.getY()>=y && p.getY()<=y+height))
				return true;
		}

		return false;
	}

	@Override
	public void setLocation(int x,int y){
		int offsetX=x-this.x,offsetY=y-this.y;
		Point offset=new Point(offsetX,offsetY);
		super.setLocation(x,y);
		notifyRelations(offset,this);

	}

	public void notifyRelations(Point offset,ClassFormat classFormat){
		for(Relation relation:relations)
			relation.update(offset.clone(),classFormat);
	}

	public void regisiterRelation(Relation relation){
		relations.add(relation);
	}

	public void unregisiterRelation(Relation relation){
		relations.remove(relation);
	}

	public String getClassName(){
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<String> getMethods() {
		return methods;
	}

	public void setMethods(List<String> methods) {
		this.methods = methods;
	}

	public List<String> getVariables() {
		return variables;
	}

	public void setVariables(List<String> variables) {
		this.variables = variables;
	}
	
	protected int drawVariable(Graphics g,int x,int y) {
		FontMetrics m =g.getFontMetrics();
		int lineHeight=m.getHeight();
		for(String variable:variables) {
			y+=lineHeight;
			g.drawString(variable, x, y);
		}
		return y;
	}
	
	protected int drawMethod(Graphics g,int x,int y) {
		FontMetrics m =g.getFontMetrics();
		int lineHeight=m.getHeight();
		for(String method:methods) {
			y+=lineHeight;
			g.drawString(method, x, y);
		}
		return y;
	}
	
	protected int drawClassName(Graphics g,int x,int y) {
		g.drawString(className, x, y);
		return y;
	}
	
	@Override
	public void draw(Graphics g) {
		FontMetrics m =g.getFontMetrics();
		int lineHeight=m.getHeight();
		int width=this.width,height=this.height;
		int x=this.x,y=this.y,middle=(x*2+width)/2,padding=5;

		g.drawRect(x,y,width,height);
//		y=drawClassName(g,middle,y+lineHeight);
		y=drawClassName(g,x+padding,y+lineHeight);
		y+=lineHeight;
		g.drawLine(x, y,x+width,y);
		
		y=drawVariable(g,x+padding,y);
		
		y+=lineHeight;
		g.drawLine(x, y,x+width,y);
		
		y=drawMethod(g,x+padding,y);
	}
}
