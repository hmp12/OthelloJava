import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class menu extends JPanel{
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image bg = new ImageIcon("image/background.jpg").getImage();
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);

	}
	
	
	public menu(frame f) {
		System.out.println();
		setLayout(new GridLayout(2, 1));
		
		JPanel head = new JPanel();
		JPanel body = new JPanel();
		JPanel foot = new JPanel();
		head.setBackground(new Color(0, 0, 0, 0));
		body.setBackground(new Color(0, 0, 0, 0));
		foot.setBackground(new Color(0, 0, 0, 0));
		
		add(head);
		add(body);
		
		JLabel label = new JLabel("Othello", JLabel.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, f.getHeight()/4));
		head.add(label);

		
		JButton play1 = new JButton("1 Player");
		play1.setFont(new Font("Arial", Font.PLAIN, f.getHeight()/15));
		play1.addActionListener(f);
		play1.setMargin(new Insets(10, 20, 10, 20));
		f.play1 = play1;
		
		JButton play2 = new JButton("2 Player");
		play2.setFont(new Font("Arial", Font.PLAIN, f.getHeight()/15));
		play2.addActionListener(f);
		play2.setMargin(new Insets(10, 20, 10, 20));
		f.play2 = play2;

		JButton rule = new JButton("AI vs AI");
		rule.setFont(new Font("Arial", Font.PLAIN, f.getHeight()/15));
		rule.addActionListener(f);
		rule.setMargin(new Insets(10, 34, 10, 34));
		f.rule = rule;
		
		JButton quit = new JButton("Quit");
		quit.setFont(new Font("Arial", Font.PLAIN, f.getHeight()/15));
		quit.addActionListener(f);
		quit.setMargin(new Insets(10, 138, 10, 138));
		f.quit = quit;
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		body.add(panel);
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addComponent(play1)
			.addComponent(play2)
			.addComponent(rule)
			.addComponent(quit)
		);
		
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(play1)
				.addComponent(play2)
				.addComponent(rule)
				.addComponent(quit)
					
		);
		
	}

}