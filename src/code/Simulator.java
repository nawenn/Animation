package code;


import javax.swing.JFrame;

public class Simulator {

	
	public static void createAndShowGUI() {
		JFrame frame = new JFrame();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setContentPane(new Animation());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1375, 750);
		frame.setLocation(120, 100);
		frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
