import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class frame extends JFrame implements ActionListener {
	final int width;
	final int height;
	
	JButton quit, play1, play2, rule, back, play;
	CardLayout card;
	JPanel container;
	menu m;
	play p;
	level l;
	
	public frame() {
		super("Othello-HMP");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)screenSize.getWidth()*3/4;
		height = (int)screenSize.getHeight()*9/10;
		
		setSize(width, height);
		setVisible(true);	
		setResizable(false);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Image image = new ImageIcon("image/icon.png").getImage();
		setIconImage(image);
		
		card = new CardLayout();
		container = new JPanel();
		container.setLayout(card);
		add(container);
		
		m = new menu(this);
		this.container.add(m);
		
		l = new level(this);
		//this.container.add(l);
		
		p = new play(this);
		this.container.add(p);
		
	}
	
	
	

	public void actionPerformed(ActionEvent ae) {
		Object b = ae.getSource();
		if (b == quit) System.exit(0);
		else if (b == play1) {
			card.next(container);
			p.start(7);
		}
		else if (b == play2) {
			card.next(container);
			//card.next(container);
			p.start(0);
		}
		else if (b == rule) {
			card.next(container);
			//card.next(container);
			p.start(-1);
			p.AITest();
		}
		else if (b == back) {
			card.previous(container);	
			//card.previous(container);	
		}
		else {
			card.next(container);
			if (b == play) {
				int i = Integer.parseInt(l.level.getText()); 
				p.start(i);
			}
		}
	}
	
	
}
