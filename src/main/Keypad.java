package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import com.fazecast.jSerialComm.SerialPort;

/**
 * Provides the control interface for remapping a MCU programmed with the
 * 1Keyboard "firmware".
 * 
 * @author Ethan Bradley
 */
public class Keypad {

	private JFrame frmKeypadController;
	
	public JFrame getFrame() {
		return frmKeypadController;
	}

	private JTextField keycodeField;
	private JComboBox<ArduinoKeycodes> specialKeycodeBox;
	private JRadioButton keycodeFieldCheck;
	private JRadioButton keycodeBoxCheck;
	private JButton overwriteButton;
	private JButton reloadButton;
	private JButton tipButton;
	private JTextField currentKeyField;
	private JComboBox<SerialPort> comDeviceBox;
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public Keypad() {
		Controller.setGui(this);
		frmKeypadController = new JFrame();
		frmKeypadController.setTitle("Keypad Controller");
		frmKeypadController.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKeypadController.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel westPanel = new JPanel();
		frmKeypadController.getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		westPanel.add(horizontalStrut);
		
		JPanel eastPanel = new JPanel();
		frmKeypadController.getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.X_AXIS));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		eastPanel.add(horizontalStrut_1);
		
		JPanel centerPanel = new JPanel();
		frmKeypadController.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		JPanel keycodePanel = new JPanel();
		
		JPanel serialPanel = new JPanel();
		
		GroupLayout gl_centerPanel = new GroupLayout(centerPanel);
		gl_centerPanel.setHorizontalGroup(
			gl_centerPanel.createParallelGroup(Alignment.TRAILING)
				.addComponent(keycodePanel, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
				.addComponent(serialPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
		);
		gl_centerPanel.setVerticalGroup(
			gl_centerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerPanel.createSequentialGroup()
					.addComponent(keycodePanel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(serialPanel, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
		);
		
		comDeviceBox = new JComboBox<>();
		comDeviceBox.setSelectedIndex(-1);
		comDeviceBox.addActionListener(Controller.portSelected());
		comDeviceBox.setRenderer(new BoxRenderer());
		
		reloadButton = new JButton("Reload");
		reloadButton.addActionListener(Controller::reloadButtonFired);
		
		currentKeyField = new JTextField();
		currentKeyField.setEditable(false);
		currentKeyField.setText("DISCONNECTED");
		currentKeyField.setColumns(12);
		
		JLabel currentKeyLabel = new JLabel("Current Key:");
		
		tipButton = new JButton("?");
		tipButton.addActionListener(Controller::tipButtonFired);
		
		tipButton.setFont(UIManager.getFont("Button.font"));
		
		overwriteButton = new JButton("Write Keycode");
		overwriteButton.setEnabled(false);
		overwriteButton.addActionListener(Controller::overwriteFired);
		GroupLayout gl_serialPanel = new GroupLayout(serialPanel);
		gl_serialPanel.setHorizontalGroup(
			gl_serialPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_serialPanel.createSequentialGroup()
					.addGroup(gl_serialPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_serialPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(currentKeyLabel, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(currentKeyField, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
						.addGroup(gl_serialPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(overwriteButton, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
					.addGroup(gl_serialPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_serialPanel.createSequentialGroup()
							.addGap(30)
							.addComponent(reloadButton, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tipButton)
							.addContainerGap())
						.addGroup(gl_serialPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(comDeviceBox, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))))
		);
		gl_serialPanel.setVerticalGroup(
			gl_serialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_serialPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_serialPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_serialPanel.createSequentialGroup()
							.addComponent(comDeviceBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_serialPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(tipButton)
								.addComponent(reloadButton)
								.addComponent(overwriteButton)))
						.addGroup(gl_serialPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(currentKeyLabel)
							.addComponent(currentKeyField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		serialPanel.setLayout(gl_serialPanel);
		
		keycodeFieldCheck = new JRadioButton("Any Character");
		keycodeFieldCheck.addActionListener(Controller::radioButtonFired);
		
		keycodeBoxCheck = new JRadioButton("Special Keycode");
		keycodeBoxCheck.addActionListener(Controller::radioButtonFired);
		
		ButtonGroup group = new ButtonGroup();
		group.add(keycodeFieldCheck);
		group.add(keycodeBoxCheck);
		keycodeFieldCheck.setSelected(true);
		
		
		keycodeField = new JTextField();
		keycodeField.addKeyListener(Controller.fieldTyped());
		JLabel keycodeFieldLabel = new JLabel("Enter a character:");
		keycodeFieldLabel.setLabelFor(keycodeField);
		
		Document d = new LimitedTextField(1, keycodeFieldLabel, 25, 15);
		keycodeField.setDocument(d);
		keycodeField.setColumns(1);
		
		specialKeycodeBox = new JComboBox<>();
		specialKeycodeBox.setEnabled(false);
		specialKeycodeBox.setModel(new DefaultComboBoxModel<ArduinoKeycodes>(ArduinoKeycodes.values()));
		
		GroupLayout gl_keycodePanel = new GroupLayout(keycodePanel);
		gl_keycodePanel.setHorizontalGroup(
			gl_keycodePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_keycodePanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_keycodePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(keycodeBoxCheck)
						.addComponent(keycodeFieldCheck))
					.addGap(97)
					.addGroup(gl_keycodePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(specialKeycodeBox, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_keycodePanel.createSequentialGroup()
							.addComponent(keycodeFieldLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(keycodeField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_keycodePanel.setVerticalGroup(
			gl_keycodePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_keycodePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_keycodePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(keycodeFieldCheck)
						.addComponent(keycodeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(keycodeFieldLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addGroup(gl_keycodePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(keycodeBoxCheck)
						.addComponent(specialKeycodeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6))
		);
		keycodePanel.setLayout(gl_keycodePanel);
		centerPanel.setLayout(gl_centerPanel);
		
		JPanel southPanel = new JPanel();
		frmKeypadController.getContentPane().add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(10);
		southPanel.add(verticalStrut);
		
		JPanel northPanel = new JPanel();
		frmKeypadController.getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
		frmKeypadController.setVisible(true);
		frmKeypadController.setSize(544, 200);
		frmKeypadController.pack();
		frmKeypadController.setLocationRelativeTo(null);
		
		
	}


	public JTextField getKeycodeField() {
		return keycodeField;
	}


	public JComboBox<ArduinoKeycodes> getSpecialKeycodeBox() {
		return specialKeycodeBox;
	}


	public JRadioButton getKeycodeFieldCheck() {
		return keycodeFieldCheck;
	}


	public JRadioButton getKeycodeBoxCheck() {
		return keycodeBoxCheck;
	}


	public JButton getOverwriteButton() {
		return overwriteButton;
	}


	public JButton getReloadButton() {
		return reloadButton;
	}


	public JButton getTipButton() {
		return tipButton;
	}


	public JTextField getCurrentKeyField() {
		return currentKeyField;
	}


	public JComboBox<SerialPort> getComDeviceBox() {
		return comDeviceBox;
	}
}

/**
 * Document class for the keycode character text field. Limits the amount of
 * text that can be inserted into the text field to the specified amount in the
 * constructor.
 * 
 * @author Ethan Bradley
 */
class LimitedTextField extends PlainDocument {
	private static final long serialVersionUID = -2976042958260549937L;
	private int lineLimit;
	private int xOffset;
	private int yOffset;
	private Component parent;
	
	
	public LimitedTextField(int lineLimit, Component parent) {
		this.lineLimit = lineLimit;
		this.parent = parent;
		setXOffset(0);
		setYOffset(0);
	}
	
	public LimitedTextField(int lineLimit, Component parent, int xOffset, int yOffset) {
		this(lineLimit, parent);
		setXOffset(xOffset);
		setYOffset(yOffset);
	}
	
	public void setXOffset(int xOffset) {
		this.xOffset = xOffset;
	}
	
	public void setYOffset(int yOffset) {
		this.yOffset = yOffset;
	}
	
	/**
	 * Recursively obtains the absolute x position of a specified component
	 * 
	 * @param c
	 * @return
	 */
	public int getGlobalX(Component c) {
		if (c.getParent() == null) {
			return c.getX();
		}
		return c.getX() + getGlobalX(c.getParent());
	}
	
	/**
	 * Recursively obtains the absolute Y position of a specified component
	 * 
	 * @param c
	 * @return
	 */
	public int getGlobalY(Component c) {
		if (c.getParent() == null) {
			return c.getY();
		}
		return c.getY() + getGlobalY(c.getParent());
	}
	
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if (str == null)
			return;
		
		if ((getLength() + str.length()) <= lineLimit) {
			super.insertString(offs, str, a);
		} else {
			int x = getGlobalX(parent) + xOffset;
			int y = getGlobalY(parent) + yOffset;
			String warning = "You only get one :)";
			JLabel l = new JLabel(warning);
			l.setForeground(Color.BLUE);
			l.setBackground(Color.YELLOW);
			l.setOpaque(true);
			Popup p = PopupFactory.getSharedInstance().getPopup(parent, l, x, y);
			p.show();
			Timer t = new Timer(5_000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					p.hide();
					
				}
				
			});
			t.setRepeats(false);
			t.start();
		}
	}
}
