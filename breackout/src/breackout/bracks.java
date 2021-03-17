package breackout;

import javax.swing.JFrame;

public class bracks {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
		obj.setBounds(10,10,700,600);
		obj.setTitle("Breackout");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
	} // end main

} // end class bracks
