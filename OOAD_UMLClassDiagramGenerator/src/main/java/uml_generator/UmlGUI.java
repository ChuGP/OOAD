package uml_generator;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
public class UmlGUI extends JFrame {
	private JPanel contentPane;
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
		setBounds(0, 0, width, height);
		
		contentPane = new JPanel();
		contentPane.setPreferredSize(screensize);

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
