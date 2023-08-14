package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * Hastily made prank window that pretends to update the operating system to
 * Windows Vista of all things.
 * 
 * It comes equipped with a scary looking console window that emulates the
 * installation of files, but is really just insulting you.
 * 
 * Serves as the entry point of the program.
 * 
 * I don't know how to use Maven and I refuse to learn.
 * 
 * @author Ethan Bradley
 */
public class VistaUpdater extends JFrame {

	private static final long serialVersionUID = 4902643639723178512L;

	public static void main(String[] args) {
		try {
			System.out.println(System.getProperty("java.version"));
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			VistaUpdater p = new VistaUpdater();
			JTextArea area = p.getTextArea();
			Thread t = new Thread(() -> startProgressBar(p.getProgressBar(), area, new TextGenerator()));
			t.start();
			p.giveThread(t);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private Thread thread;

	private void giveThread(Thread t) {
		this.thread = t;

	}

	/**
	 * Starts the prank progress bar + text generator thread.
	 * The text generator is a Java port from Zack Freedman's insult generator for
	 * python.
	 * 
	 * The progress bar is modelled by y = 95 - 1.005^(913 - x) which is an
	 * exponential function that approaches 95.
	 * <br>
	 * <a href="https://www.desmos.com/calculator/uu3ud6llef">The curve can be seen
	 * here</a>
	 * 
	 * This should have been done in a swing worker, blocks the event loop.
	 * 
	 * @param bar
	 * @param area
	 * @param gen
	 */
	private static void startProgressBar(JProgressBar bar, JTextArea area, TextGenerator gen) {
		List<String> exclusions = new ArrayList<>();
		int x = 0;
		final double N = 913D;
		while (!Thread.interrupted()) {
			exclusions.clear();
			String hit = gen.hit(Integer.MAX_VALUE, exclusions, 0.7);
			double a = 95 - Math.pow(1.005, (-x + N));
			area.append(hit + "\n");
			area.setCaretPosition(area.getDocument().getLength());
			int val = (int) Math.ceil(bar.getMaximum() * (a / 100));
			bar.setValue(val);
			x++;
			try {
				Thread.sleep(75);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	private JTextField txtUpgradingToWindows;
	private JTextArea textArea;
	private JProgressBar progressBar;

	/**
	 * Generated using Eclipse's window builder.
	 */
	public VistaUpdater() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaUpdater.class.getResource("/resources/vista.png")));
		setTitle("Innovation Awaits!");
		setResizable(false);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.CENTER);

		JPanel panel_6 = new JPanel();

		ActionListener a = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thread.interrupt();
				((JButton) e.getSource()).removeActionListener(this);
				dispose();
				new Keypad();
			}
		};

		JButton btnNewButton = new JButton("Good. I want this.");
		btnNewButton.addActionListener(a);

		progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(146, 40));
		progressBar.setMaximum(1000000);
		progressBar.setValue(5);

		JLabel lblNewLabel = new JLabel("");
		ImageIcon i = new ImageIcon(VistaUpdater.class.getResource("/resources/vista.png"));
		lblNewLabel.setIcon(new ImageIcon(i.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));

		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
				gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
						.addGroup(gl_panel_5.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnNewButton)
										.addGroup(gl_panel_5.createSequentialGroup()
												.addComponent(lblNewLabel)
												.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
												.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 430,
														GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		gl_panel_5.setVerticalGroup(
				gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
								.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
								.addGap(35)
								.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_5.createSequentialGroup()
												.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 38,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnNewButton))
										.addComponent(lblNewLabel))
								.addGap(40)));
		panel_6.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		textArea = new JTextArea();
		textArea.setColumns(80);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		textArea.setEditable(false);
		panel_6.add(scrollPane);
		panel_5.setLayout(gl_panel_5);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		txtUpgradingToWindows = new JTextField();
		txtUpgradingToWindows.setHorizontalAlignment(SwingConstants.CENTER);
		txtUpgradingToWindows.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		txtUpgradingToWindows.setText("UPGRADING TO WINDOWS VISTA!");
		txtUpgradingToWindows.setEditable(false);
		panel_1.add(txtUpgradingToWindows);
		txtUpgradingToWindows.setColumns(10);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.WEST);

		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, BorderLayout.EAST);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(550, 500);
		setVisible(true);
		setLocationRelativeTo(null);

	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
