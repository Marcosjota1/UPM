/**
 * The game of life
 *
 * @author Marc Oliveras <admin@oligalma.com> 
 * @link http://www.oligalma.com
 * @copyright 2021 Oligalma
 * @license GPL License v3
 */

package the_game_of_life;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;

public class TheGameOfLife extends JApplet {
	private static final int ROWS_NUMBER = 25;
	private static final int COLS_NUMBER = 45;
	private int stationary;
	private boolean leftButtonPressed;
	private boolean change = false;
	private boolean click = false;
	private boolean isStandalone = false;
	private Vivarium vivarium = new Vivarium(ROWS_NUMBER, COLS_NUMBER, false);
	private JLabel[][] area = new JLabel[ROWS_NUMBER][COLS_NUMBER];
	private JPanel jPanel1 = new JPanel();
	private JPanel jPanel2 = new JPanel();
	private JPanel jPanel3 = new JPanel();
	private JPanel jPanel4 = new JPanel();
	private GridLayout gridLayout1 = new GridLayout();
	private JButton nextgenButton = new JButton();
	private JButton aboutButton = new JButton();
	private JButton eraseButton = new JButton();
	private JCheckBox ballCheckbox = new JCheckBox();
	private JLabel stationaryLabel = new JLabel();

	// Initialize the applet
	@Override
	public void init() {
		try {
			this.setSize(new Dimension(420, 320));
			jPanel1.setLayout(null);
			jPanel2.setBackground(Color.black);
			jPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
			jPanel2.setBounds(new Rectangle(16, 11, 362, 227));
			jPanel2.setLayout(gridLayout1);
			gridLayout1.setColumns(COLS_NUMBER);
			gridLayout1.setHgap(0);
			gridLayout1.setRows(ROWS_NUMBER);
			gridLayout1.setVgap(0);
			jPanel3.setBorder(BorderFactory.createRaisedBevelBorder());
			jPanel3.setBounds(new Rectangle(19, 268, 384, 39));
			jPanel3.setLayout(null);
			nextgenButton.setBounds(new Rectangle(182, 6, 83, 26));
			nextgenButton.setMargin(new Insets(2, 1, 2, 1));
			nextgenButton.setText("Next");
			
			nextgenButton.addKeyListener(new KeyListener(){
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyChar() == KeyEvent.VK_ENTER)
						nextGen();
				}

				@Override
				public void keyReleased(KeyEvent e) {}

				@Override
				public void keyTyped(KeyEvent e) {}	
			});
			nextgenButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					nextGen();
				}
			});
			aboutButton.setBounds(new Rectangle(345, 9, 28, 20));
			aboutButton.setToolTipText("");
			aboutButton.setMargin(new Insets(2, 1, 2, 1));
			aboutButton.setText("?");
			aboutButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					showAbout();		
				}
				
			});
			eraseButton.setText("Clear");
			eraseButton.setMargin(new Insets(2, 1, 2, 1));
			eraseButton.setBounds(new Rectangle(86, 6, 83, 26));
			eraseButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < area.length; i++)
						for (int j = 0; j < area[0].length; j++)
							area[i][j].setBackground(Color.white);
					
					vivarium.clearArea();
					stationaryLabel.setText("0");				
				}	
			});
			ballCheckbox.setText("Ball");
			ballCheckbox.setBounds(new Rectangle(10, 10, 71, 18));
			ballCheckbox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					vivarium.setBall(ballCheckbox.isSelected());
					change = true;		
				}
			});
			stationaryLabel.setFont(new java.awt.Font("Dialog", 1, 12));
			stationaryLabel.setBorder(BorderFactory.createRaisedBevelBorder());
			stationaryLabel.setPreferredSize(new Dimension(4, 4));
			stationaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
			stationaryLabel.setText("0");
			stationaryLabel.setBounds(new Rectangle(283, 7, 42, 25));
			jPanel4.setBackground(new Color(192, 255, 192));
			jPanel4.setBorder(BorderFactory.createLoweredBevelBorder());
			jPanel4.setBounds(new Rectangle(14, 8, 392, 250));
			jPanel4.setLayout(null);
			jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
			this.getContentPane().add(jPanel1, BorderLayout.CENTER);
			jPanel3.add(ballCheckbox, null);
			jPanel3.add(stationaryLabel, null);
			jPanel3.add(eraseButton, null);
			jPanel3.add(nextgenButton, null);
			jPanel3.add(aboutButton, null);
			jPanel1.add(jPanel4, null);
			jPanel4.add(jPanel2, null);
			jPanel1.add(jPanel3, null);
			
			for (int i = 0; i < area.length; i++)
			{
				for (int j = 0; j < area[0].length; j++) {
					area[i][j] = new JLabel();
					area[i][j].setBackground(Color.white);
					area[i][j].setOpaque(true);
					area[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
					area[i][j].addMouseListener(new MouseListener(){
						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {
							if (click) {
								Object o = e.getSource();
								if (leftButtonPressed)
									((JLabel) o).setBackground(Color.red);
								else
									((JLabel) o).setBackground(Color.white);
							}
							
							boolean[][] area2 = new boolean[ROWS_NUMBER][COLS_NUMBER];		
							for (int i = 0; i < area2.length; i++)
							{
								for (int j = 0; j < area2[0].length; j++) {
									if (area[i][j].getBackground().equals(Color.red))
										area2[i][j] = true;
									else
										area2[i][j] = false;
								}
							}
							
							vivarium.setArea(area2);						
						}

						@Override
						public void mouseExited(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {
							change = true;
							click = true;
							Object o = e.getSource();
							if (e.getButton() == MouseEvent.BUTTON1) {
								leftButtonPressed = true;
								((JLabel) o).setBackground(Color.red);
							} else if (e.getButton() == MouseEvent.BUTTON3) {
								leftButtonPressed = false;
								((JLabel) o).setBackground(Color.white);
							}						
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							click = false;			
						}			
					});
					jPanel2.add(area[i][j]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void nextGen()
	{
		if (change) {					
			change = false;
			
			stationary = vivarium.getStationary();
			
			if (stationary == -1)
				stationaryLabel.setText("X");
			else
				stationaryLabel.setText(Integer.toString(stationary));
		}
		
		if (stationary > 0) {
			if (Integer.parseInt(stationaryLabel.getText()) > 0)
				stationaryLabel.setText(Integer.toString(Integer.parseInt(stationaryLabel.getText()) - 1));
		}

		vivarium.nextGen();
		
		boolean[][] area2 = vivarium.getArea();
		for (int i = 0; i < area2.length; i++)
		{
			for (int j = 0; j < area2[0].length; j++) {
				if (area2[i][j] == true)
					area[i][j].setBackground(Color.red);
				else
					area[i][j].setBackground(Color.white);
			}
		}				
	}		
	
	// Main method
	public static void main(String[] args) {
		TheGameOfLife applet = new TheGameOfLife();
		applet.isStandalone = true;
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(3);
		frame.setTitle("The game of life");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(425, 350);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
		frame.setVisible(true);
	}

	private void showAbout() {
		JOptionPane.showMessageDialog(this,
						"THE GAME OF LIFE 1.0\n\nby Marc Oliveras, " + Calendar.getInstance().get(Calendar.YEAR) + ". All rights reserved.\n\nadmin@oligalma.com\n\nhttp://www.oligalma.com",
						"About...", JOptionPane.PLAIN_MESSAGE);
	}

}