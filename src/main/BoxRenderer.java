package main;

import java.awt.Component;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.fazecast.jSerialComm.SerialPort;

/**
 * Adds a tool tip for items inside of a ComboBox which just displays the same
 * text as the entry in the list. This is useful if the ComboBox is not wide
 * enough to show all of the characters in the String.
 * 
 * @author Ethan Bradley
 */
public class BoxRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1781193690766706725L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		if (value == null || !(value instanceof SerialPort))
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		SerialPort port = ((SerialPort) value);
		List<String> a = new LinkedList<>(Arrays.asList(port.getDescriptivePortName().split(" ")));
		a.add(0, a.remove(a.size() - 1));
		
		setToolTipText(port.getDescriptivePortName());
		StringBuilder acc = new StringBuilder("");
		for (String str : a) {
			acc.append(str + ' ');
		}
		
		return super.getListCellRendererComponent(list, acc.toString(), index, isSelected, cellHasFocus);
	}

	 
}
