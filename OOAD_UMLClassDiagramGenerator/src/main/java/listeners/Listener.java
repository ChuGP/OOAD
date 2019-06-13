package listeners;

import com.sun.javafx.iio.ImageStorage;
import diagrams.RelationType;
import diagrams.UMLClassDiagramDrawer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Listener {
    UMLClassDiagramDrawer drawer;
    private ListenerHandler listenerHandler;
    private Point start,end;
    private RelationType relation= RelationType.Association;
    public Listener(UMLClassDiagramDrawer drawer, ListenerHandler listenerHandler){
        this.listenerHandler=listenerHandler;
        this.drawer=drawer;
        start=new Point(0,0);
        end=new Point(0,0);
    }

    public ActionListener openFileListener=new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int returnValue=fileChooser.showOpenDialog(null);
            if(returnValue==JFileChooser.APPROVE_OPTION)
                listenerHandler.executeOpenDiagram(fileChooser.getSelectedFile().getPath());
        }
    };

    public ActionListener saveListener=new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option=fileChooser.showSaveDialog(null);
            if(option==JFileChooser.APPROVE_OPTION){
                String path=fileChooser.getSelectedFile().getPath();
                if(!path.contains(".diagram"))
                    path+=".diagram";
                listenerHandler.executeSaveDiagram(path);
            }
        }
    };

    public ActionListener outputListener =new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option=fileChooser.showSaveDialog(null);
            if(option==JFileChooser.APPROVE_OPTION){
                String path=fileChooser.getSelectedFile().getPath();
                String fileName=fileChooser.getName(fileChooser.getSelectedFile());
                String desktopPath= FileSystemView.getFileSystemView().getHomeDirectory().getPath();
                int type=JOptionPane.INFORMATION_MESSAGE;
                String[]formats=new String[]{"png","jpg"};
                String format=(String)JOptionPane.showInputDialog(null,"請選擇格式","選擇輸出格式",type,null,formats,formats[0]);
                if(format==null)
                    return;
                BufferedImage bufferedImage=new BufferedImage(drawer.getWidth(),drawer.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D g=bufferedImage.createGraphics();
                drawer.print(g);
                try{
                    ImageIO.write(bufferedImage,format,new File(path+"."+format));
                    ImageIO.write(bufferedImage,format,new File(desktopPath+"\\"+fileName+"."+format));
                }catch (IOException exception){
                    exception.printStackTrace();}
            }
        }
    };

    public ItemListener relationListener = e -> {
        if(e.getStateChange()== ItemEvent.SELECTED) {
            JRadioButton button = (JRadioButton) e.getItem();
            relation=RelationType.valueOf(button.getText());
        }
    };

    public ItemListener addRelation=new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange()== ItemEvent.SELECTED)
                drawer.addMouseListener(addRelationAdapter);
            else if(e.getStateChange()== ItemEvent.DESELECTED)
                drawer.removeMouseListener(addRelationAdapter);
        }
    };

    public ItemListener removeRelation=new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange()== ItemEvent.SELECTED)
                drawer.addMouseListener(removeRelationAdapter);
            else if(e.getStateChange()== ItemEvent.DESELECTED)
                drawer.removeMouseListener(removeRelationAdapter);
        }
    };

    public ItemListener moveUnit=new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange()== ItemEvent.SELECTED)
                drawer.addMouseListener(moveUnitAdapter);
            else if(e.getStateChange()== ItemEvent.DESELECTED)
                drawer.removeMouseListener(moveUnitAdapter);
        }
    };

    public ItemListener removeUnit=new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange()== ItemEvent.SELECTED)
                drawer.addMouseListener(removeUnitAdapter);
            else if(e.getStateChange()== ItemEvent.DESELECTED)
                drawer.removeMouseListener(removeUnitAdapter);
        }
    };

    private MouseAdapter addRelationAdapter=new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            start=e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            end=e.getPoint();
            listenerHandler.executeAddRelation((Point)start.clone(),(Point)end.clone(),relation);
        }
    };

    private MouseAdapter removeRelationAdapter=new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            listenerHandler.executeRemoveRelation(e.getPoint());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);

        }
    };

    private MouseAdapter moveUnitAdapter=new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            start=e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            end=e.getPoint();
            listenerHandler.executeMoveUnit((Point)start.clone(),(Point)end.clone());
        }
    };

    private MouseAdapter removeUnitAdapter=new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            listenerHandler.executeRemoveUnit(e.getPoint());
        }
    };
}
