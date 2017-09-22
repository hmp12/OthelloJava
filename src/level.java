import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class level extends JPanel {
	
	JTextField level, corner, edge;
	public level(frame f) {

		
		//f.container.add(this);
		setLayout(new GridLayout(2, 2, 1, 1));
		//setLayout(new GridBagLayout());
		
		
		add(new JLabel("Level", JLabel.CENTER));
		level = new JTextField(20);
		add(level);
		
		//add(new JLabel("Corner"));
		corner = new JTextField();
		//add(corner);
		
		//add(new JLabel("edge"));
		edge = new JTextField();
		//add(edge);
		
		JButton play = new JButton("Play");
		play.setFont(new Font("Arial", Font.PLAIN, 50));
		play.addActionListener(f);
		add(play);
		f.play = play;
		
	}

}
