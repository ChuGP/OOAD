package diagrams;
import java.util.*;
import java.util.List;

import shapes.ClassFormat;
import shapes.Relation;

public class UMLClassDiagram extends Observable{
	private Map<String,ClassFormat> classFormatCollection;
	private List<Relation> relations;
	private int width,height;
	public UMLClassDiagram(int width,int height) {
		super();
		classFormatCollection=new TreeMap<>();
		relations=new ArrayList<>();
		setWidth(width);
		setHeight(height);
	}

	public void setDiagram(UMLClassDiagram diagram){
		classFormatCollection.clear();
		relations.clear();
		Iterator<Map.Entry<String,ClassFormat>>classFormatIterator=diagram.createClassFormatIterator();
		while (classFormatIterator.hasNext()) {
			ClassFormat classFormat=classFormatIterator.next().getValue();
			classFormatCollection.put(classFormat.getClassName(),classFormat);
		}
		Iterator<Relation>relationIterator=diagram.createRelationIterator();
		while(relationIterator.hasNext()){
			Relation relation=relationIterator.next();
			relations.add(relation);
		}
		setHeight(diagram.getHeight());
		setWidth(diagram.getWidth());
		notifyObservers();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void notifyObservers(Object object){
		setChanged();
		super.notifyObservers(object);
	}

	@Override
	public void notifyObservers(){
		setChanged();
		super.notifyObservers();
	}

	public void addToDiagram(ClassFormat format){
		classFormatCollection.put(format.getClassName(), format);
		notifyObservers(format);
	}

	public void addToDiagram(Relation relation){
		relations.add(relation);
		notifyObservers(relation);

	}

	public void setClassFormatLocation(String className, int x, int y) throws Exception{
		getClassFormat(className).setLocation(x, y);
		notifyObservers();
	}

	public Iterator<Map.Entry<String,ClassFormat>> createClassFormatIterator(){
		return classFormatCollection.entrySet().iterator();
	}

	public Iterator<Relation> createRelationIterator(){
		return relations.iterator();
	}

	public ClassFormat getClassFormat(String className)throws Exception{
		if(!classFormatCollection.containsKey(className))
			throw new Exception("The class '"+className+"' is not in the diagram");
		else
			return classFormatCollection.get(className);
	}

	public void removeClassFormat(ClassFormat classFormat){
		if(classFormat!=null && classFormatCollection.containsKey(classFormat.getClassName())) {
			classFormatCollection.remove(classFormat.getClassName());
			notifyObservers();
		}
	}

	public void removeRelation(Relation relation){
		if(relation!=null && relations.contains(relation)){
			relations.remove(relation);
			notifyObservers();
		}
	}

}
