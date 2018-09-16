package ttt_ai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIPlayer extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6790057138472555715L;
	private JFrame f;
	private JPanel p;
	private Object source;
	int playerChoice = 0;
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	public GUIPlayer() {
		GridLayout buttonLayout = new GridLayout(3, 3);
		
		f = new JFrame("Calculator");
		f.setVisible(true);
		f.setSize(200, 200);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout());
		f.setResizable(false);

		p = new JPanel();
		p.setBackground(Color.white);
		p.setLayout(buttonLayout);
		p.setPreferredSize(new Dimension(200, 200));
		
		for (int i = 0; i < 9; i++) {
			JButton b = new JButton("-");
			b.addActionListener(this);
			p.add(b);
			buttons.add(b);
		}
		f.add(p);
	}
	
	public void update(float[] board, char playerFrom) {
		if (playerFrom == 'X') {
			for (int i = 0; i < 9; i++) {
				if (board[i] == 1) {
					buttons.get(i).setText("X");
				} else if (board[i] == -1) {
					buttons.get(i).setText("O");
				}
			}
		} else {
			for (int i = 0; i < 9; i++) {
				if (board[i] == 1) {
					buttons.get(i).setText("O");
				} else if (board[i] == -1) {
					buttons.get(i).setText("X");
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		source = e.getSource();
		for (int i = 0; i < 9; i++) {
			if (source == buttons.get(i) && buttons.get(i).getText() == "-") {
				playerChoice = i;
				this.notifyAll();
			}
		}
		
	}

}
