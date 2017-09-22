import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class play extends JPanel implements MouseListener{
	Reversi r = new Reversi();
	frame f;
	
	final int X, Y, Z, N;
	int p[] = {0, 0, 0};
	int x, y, c;
	int n;
	
	AI ai = new AI();

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//g2.setColor(Color.CYAN);
		//g2.fillRect(0, 0, 1400, 1000);
		
		drawTable(g2);
		drawCircle(g2);	
		printPlayer(g2);
		
		
		
	}
	
	
	public void drawCircle(Graphics2D g) {
		int i,j;
		p[1] = 0;
		p[2] = 0;
		for (i=0;i<N;i++)
			for (j=0;j<N;j++) {
				if (r.getAvailable()[i][j] != 0) {
					g.setColor(Color.darkGray);
					g.fillOval(X + i*Z + 2*Z/5,Y + j*Z +2*Z/5, Z/5, Z/5);
				}
				if (r.getTable()[i][j] != 0) {
					if (r.getTable()[i][j] == 1) g.setColor(Color.black);
					else g.setColor(Color.white);
					g.fillOval(X + i*Z + Z/28,Y + j*Z + Z/28, Z*13/14, Z*13/14);
					
					p[r.getTable()[i][j]]++;
				}
			}
	}
	
	public void drawTable(Graphics2D g) {
		int i;
		Image bg = new ImageIcon("image/background.jpg").getImage();
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		/*
		g.setColor(Color.gray);
		g.fillRect(X, Y, N*Z, N*Z);
		
		g.setColor(Color.black);
			
		for (i=0; i<9; i++) {
			g.drawLine(X + i*Z, Y, X + i*Z, Y + N*Z);
			g.drawLine(X, Y + i*Z, X + N*Z, Y + i*Z);
		}	
		*/
		Image tb = new ImageIcon("image/table.jpg").getImage();
		g.drawImage(tb, X, Y, Z*8, Z*8, this);
	}
	
	public void printPlayer(Graphics2D g) {
		String s;
		g.setFont(new Font("Arial", Font.PLAIN, f.getHeight()/15));
		
		g.setColor(Color.black);
		s = new Integer(p[1]).toString();
		g.drawString(s, X/2, Y + Z*3);
		if (r.getMode()>0) g.drawString("YOU", X*5/12, Y + Z*2);
		else g.drawString("Player "+1, X/3, Y + Z*2);
		
		g.setColor(Color.white);
		s = new Integer(p[2]).toString();
		g.drawString(s, X/2, Y + Z*6);
		if (r.getMode()>0) g.drawString("AI Player", X/3, Y + Z*5);
		else g.drawString("Player "+2, X/3, Y + Z*5);
		
		g.setColor(Color.darkGray);
		if (r.getPlayer() == 1) g.fillOval(X/4 ,Y + Z*9/5, Z/5, Z/5);
		else g.fillOval(X/4 ,Y + Z*24/5, Z/5, Z/5);
	}
	
	public play(frame frame) {
		f = frame;
		X = f.width/3;
		Y = f.height/10;
		Z = Y;
		N = 8;
		setLayout(new GridLayout(9, 1));
		JPanel head = new JPanel();
		head.setBackground(new Color(0, 0, 0, 0));
		add(head);
		JButton back = new JButton("Back");
		back.setFont(new Font("Arial", Font.PLAIN, f.getHeight()/15));
		back.setLocation(450, 450);
		back.addActionListener(f);
		head.add(back);
		f.back = back;		
		/*
		JButton undo = new JButton("Undo");
		undo.setFont(new Font("Arial", Font.PLAIN, f.getHeight()/15));
		undo.setLocation(450, 450);
		undo.addActionListener(f);
		head.add(undo);	
		*/
		addMouseListener(this);
	}
	
	public void start(int m) {
		r.init(m);
		repaint();	
	}
	
	
	public void mousePressed(MouseEvent me) {   
	    
	}

	public void mouseReleased(MouseEvent me) {
	      
	}

	public void mouseEntered(MouseEvent me) {
	       
	}

	public void mouseExited(MouseEvent me) {
	}      

	public void mouseClicked(MouseEvent me) {
		System.out.println("Player Clicked");
		x = me.getX();
	    y = me.getY();
	    if (x>X && x<X+N*Z && y>Y && y<Y+N*Z) {
	    	x = (x-X)/Z;
	    	y = (y-Y)/Z;
	    	int m = r.choice(x, y, false);
	    	if (m>0) {
		    	if (!r.isFinish()) {
		    		r.changePlayer();
		    		
		    	}
	    	} 
	    	
	    	repaint();
	    	
	    	 	
	    	
	    	Runnable AIPlay = new Runnable() {
	    	    public void run() { 
	    	    	if (m>0 && r.getMode()>0) {
		    			ai.init(r, r.getTable(), 7, 10, 5, 0, 60, 2);
		    			if (!r.isFinish()) 
		    	    		r.changePlayer();	
		    	    	else finish();
		    			repaint();
		    		}
	    	    }
	    	};
	    	SwingUtilities.invokeLater(AIPlay);	    	
	    }
	    
	}
	
	void AITest() {
    	
		repaint();
		
    	Runnable AI = new Runnable() {
    	    public void run() { 
    	    	if (r.isFinish()) {
    	    		finish();
    	    		return;
    	    	}
    	    	
    	    	if (r.getPlayer() == 1) ai.init(r, r.getTable(), 6, 10, 5, 0, 60, 1);
    			if (r.getPlayer() == 2) ai.init(r, r.getTable(), 7, 10, 5, 0, 60, 2);
    			
    			if (!r.isFinish()) {
    				r.changePlayer();
    				
    			}
    			AITest();
    			
    	    }
    	};
    	SwingUtilities.invokeLater(AI);
		
	}
	public void finish() {
		repaint();
		
    	Runnable finish = new Runnable() {
			public void run() {
				int winner, answer;
				if (p[1] > p[2]) winner = 1;
				else winner = 2;
				answer = JOptionPane.showConfirmDialog(null, "Player "+winner+" win", "Continue play?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (answer == JOptionPane.YES_OPTION) {
					start(r.getMode());
				}
				else {
					f.card.next(f.container);
				}
			}
    	};	
    	
    	SwingUtilities.invokeLater(finish);
	}
}
