package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.fazecast.jSerialComm.SerialPort;

/**
 * Class containing all the control logic and event listeners for the Keypad
 * controller GUI.
 * 
 * @author Ethan Bradley
 */
public class Controller {

	private static Keypad keypad;
	private static SerialPort port = null;
	private static ActionListener portSelectListener = e -> portSelected(e);	
	private static KeyAdapter keyFieldListener = new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			attemptEnableOverwrite();
		}
	};

	/**
	 * Action listener for the tip button.
	 * 
	 * Opens up a JOptionPane to provide instructions to the user on how to find out
	 * which COM port is the 1Keyboard.
	 * 
	 * @param e
	 */
	public static void tipButtonFired(ActionEvent e) {
		StringBuilder message = new StringBuilder();
		message.append("1. Disconnect the 1Keyboard from your computer and click the reload button.\n\n");
		message.append("2. Click on the 'Reload' button and keep note of which devices appear.\n\n");
		message.append("3. Connect the 1Keyboard to your computer and click the reload button again.\n\n");
		message.append("4. Select the newest option in the list!");
		JOptionPane.showMessageDialog(keypad.getFrame(), message, "1Keyboard Identification",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Sends the overwrite serial command to a 1Keyboard device.
	 * 
	 * Whichever character or special keycode has been selected will be flashed to
	 * the 1Keyboard and it will now type that key
	 * 
	 * @param e
	 */
	public static void overwriteFired(ActionEvent e) {
		if (port.openPort()) {
			int code;
			if (keypad.getKeycodeFieldCheck().isSelected()) {
				code = keypad.getKeycodeField().getText().charAt(0);
			} else {
				code = ((ArduinoKeycodes) keypad.getSpecialKeycodeBox().getSelectedItem()).toInt();
			}
			
			pooter("changeKey," + code);
			port.closePort();
		}
		
		updateKeyChar();
	}

	/**
	 * Action Listener for the reload button. Repopulates the COM device ComboBox
	 * Temporarily removes the action listener from the ComboBox while all the items
	 * are being added.
	 * 
	 * @param e
	 */
	public static void reloadButtonFired(ActionEvent e) {
		JComboBox<SerialPort> box = keypad.getComDeviceBox();
		box.removeActionListener(portSelectListener);
		keypad.getCurrentKeyField().setText("DISCONNECTED");
		box.removeAllItems();

		for (SerialPort p : SerialPort.getCommPorts()) {
			box.addItem(p);
		}

		box.addActionListener(portSelectListener);
		port = null;
		attemptEnableOverwrite();
	}

	/**
	 * Action listener for the Any character radio button and the special keycode
	 * radio button.
	 * 
	 * Enables the associated keycode selector component and disables the opposing
	 * selector.
	 */
	public static void radioButtonFired(ActionEvent e) {
		Object o = e.getSource();
		if (o != keypad.getKeycodeBoxCheck() && o != keypad.getKeycodeFieldCheck())
			return;

		boolean fieldCheckSelected = keypad.getKeycodeFieldCheck().isSelected();
		keypad.getKeycodeField().setEnabled(fieldCheckSelected);
		keypad.getSpecialKeycodeBox().setEnabled(!fieldCheckSelected);
		
		attemptEnableOverwrite();
	}
	
	/**
	 * TODO: Implement a SwingWorker thread that waits indefinitely unless the
	 * keypad gui has been modified.
	 */
	private static void attemptEnableOverwrite() {
		JButton overwriteButton = keypad.getOverwriteButton();
		
		boolean isKeycodeFieldMode = keypad.getKeycodeFieldCheck().isSelected();
		boolean isKeycodeFieldEmpty = keypad.getKeycodeField().getText().isEmpty();
		boolean portSelected = port != null;
		
		boolean canEnable = portSelected && (!isKeycodeFieldMode || (isKeycodeFieldMode && !isKeycodeFieldEmpty));
		overwriteButton.setEnabled(canEnable);
	}

	/**
	 * Sends the getKeyChar serial command to the 1Keyboard.
	 * Updates the current key text field with the response from the 1Keyboard
	 */
	private static void updateKeyChar() {
		char keyChar = getKeyChar();
		ArduinoKeycodes keycode = ArduinoKeycodes.fromInt(keyChar);
		if (keycode == null) { // is "type-able"
			keypad.getCurrentKeyField().setText("" + keyChar);
		} else { // not "type-able"
			keypad.getCurrentKeyField().setText(ArduinoKeycodes.fromInt(keyChar).toString());
		}
	}

	/**
	 * Sends the getKeyChar serial command to the 1Keyboard.
	 * 
	 * @return the keycode that the 1Keyboard is currently set to.
	 */
	private static char getKeyChar() {
		String acc = "ERROR";
		port.openPort();
		if (port.isOpen()) {
			pooter("getKeyChar");
			sleep(50);
			acc = scooper();
			port.closePort();
		}

		return (char) Short.parseShort(acc.trim());
	}

	/**
	 * Reads the serial response from a 1Keyboard device after being issued a serial
	 * command.
	 * 
	 * @return String containing the 1Keyboard command response
	 */
	private static String scooper() {
		int size = port.bytesAvailable();
		byte[] buffer = new byte[size];

		port.readBytes(buffer, size);
		String acc = "";
		for (byte b : buffer) {
			acc += (char) b;
		}

		return acc.trim();
	}

	/**
	 * Sends a serial command to the 1Keyboard device.
	 * 
	 * All commands are terminated with an '&' so that the 1Keyboard knows when to
	 * stop reading the command. This marginally improves the response time.
	 * 
	 * @param data
	 */
	private static void pooter(String data) {
		PrintWriter pooter = new PrintWriter(port.getOutputStream());
		pooter.println(data + "&");
		pooter.flush();
		pooter.close();
	}

	/**
	 * Action listener for the COM Device ComboBox.
	 * 
	 * Sets the port to use for Serial communication. This is not to be directly by
	 * the Keypad Controller GUI. There must be a reference to the ActionListener in
	 * order to be able to temporarily remove the listener.
	 * 
	 * @param e
	 */
	private static void portSelected(ActionEvent e) {
		if (e.getSource() != keypad.getComDeviceBox())
			return;

		JComboBox<SerialPort> box = keypad.getComDeviceBox();
		port = (SerialPort) box.getSelectedItem();
		port.setBaudRate(9600);
		updateKeyChar();
		attemptEnableOverwrite();
	}

	/**
	 * Accessor method for the comDeviceBox ActionListener
	 * @return
	 */
	public static ActionListener portSelected() {
		return portSelectListener;
	}
	
	/**
	 * Accessor method for the keycode field KeyListener
	 * @return
	 */
	public static KeyListener fieldTyped() {
		return keyFieldListener;
	}

	public static void setGui(Keypad keypad) {
		Controller.keypad = keypad;
	}

	public static void sleep(long n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
		}
	}
}
