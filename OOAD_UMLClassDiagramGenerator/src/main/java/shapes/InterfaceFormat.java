package shapes;

import java.awt.*;

public class InterfaceFormat extends ClassFormat {
	public InterfaceFormat(String className, int x, int y, int width, int height) {
		super(className, x, y, width, height);
	}

	@Override
	protected int drawClassName(Graphics g,int x,int y){
		int lineHeight=g.getFontMetrics().getHeight();
		g.drawString("<<interface>>",x,y);
		y+=lineHeight;
		y=super.drawClassName(g,x,y);
		return y;
	}

	@Override
	protected int drawVariable(Graphics g,int x, int y){
		y+=2*g.getFontMetrics().getHeight();
		return y;
	}
}
