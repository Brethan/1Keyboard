package main;

/**
 * 
 * Thank you Excel.
 * 
 * @author Ethan Bradley
 *
 */
public enum ArduinoKeycodes {
	KEY_LEFT_CTRL, KEY_LEFT_SHIFT, KEY_LEFT_ALT, KEY_LEFT_GUI, KEY_RIGHT_CTRL, KEY_RIGHT_SHIFT, KEY_RIGHT_ALT,
	KEY_RIGHT_GUI, KEY_TAB, KEY_CAPS_LOCK, KEY_BACKSPACE, KEY_RETURN, KEY_MENU, KEY_INSERT, KEY_DELETE, KEY_HOME,
	KEY_END, KEY_PAGE_UP, KEY_PAGE_DOWN, KEY_UP_ARROW, KEY_DOWN_ARROW, KEY_LEFT_ARROW, KEY_RIGHT_ARROW, KEY_NUM_LOCK,
	KEY_KP_SLASH, KEY_KP_ASTERISK, KEY_KP_MINUS, KEY_KP_PLUS, KEY_KP_ENTER, KEY_KP_1, KEY_KP_2, KEY_KP_3, KEY_KP_4,
	KEY_KP_5, KEY_KP_6, KEY_KP_7, KEY_KP_8, KEY_KP_9, KEY_KP_0, KEY_KP_DOT, KEY_ESC, KEY_F1, KEY_F2, KEY_F3, KEY_F4,
	KEY_F5, KEY_F6, KEY_F7, KEY_F8, KEY_F9, KEY_F10, KEY_F11, KEY_F12, KEY_F13, KEY_F14, KEY_F15, KEY_F16, KEY_F17,
	KEY_F18, KEY_F19, KEY_F20, KEY_F21, KEY_F22, KEY_F23, KEY_F24, KEY_PRINT_SCREEN, KEY_SCROLL_LOCK, KEY_PAUSE;

	public static ArduinoKeycodes fromString(String alias) {
		return switch (alias) {
		case "LEFT CTRL" -> KEY_LEFT_CTRL;
		case "LEFT SHIFT" -> KEY_LEFT_SHIFT;
		case "LEFT ALT" -> KEY_LEFT_ALT;
		case "LEFT GUI" -> KEY_LEFT_GUI;
		case "RIGHT CTRL" -> KEY_RIGHT_CTRL;
		case "RIGHT SHIFT" -> KEY_RIGHT_SHIFT;
		case "RIGHT ALT" -> KEY_RIGHT_ALT;
		case "RIGHT GUI" -> KEY_RIGHT_GUI;
		case "TAB" -> KEY_TAB;
		case "CAPS LOCK" -> KEY_CAPS_LOCK;
		case "BACKSPACE" -> KEY_BACKSPACE;
		case "RETURN" -> KEY_RETURN;
		case "MENU" -> KEY_MENU;
		case "INSERT" -> KEY_INSERT;
		case "DELETE" -> KEY_DELETE;
		case "HOME" -> KEY_HOME;
		case "END" -> KEY_END;
		case "PAGE UP" -> KEY_PAGE_UP;
		case "PAGE DOWN" -> KEY_PAGE_DOWN;
		case "UP ARROW" -> KEY_UP_ARROW;
		case "DOWN ARROW" -> KEY_DOWN_ARROW;
		case "LEFT ARROW" -> KEY_LEFT_ARROW;
		case "RIGHT ARROW" -> KEY_RIGHT_ARROW;
		case "NUM LOCK" -> KEY_NUM_LOCK;
		case "KP SLASH" -> KEY_KP_SLASH;
		case "KP ASTERISK" -> KEY_KP_ASTERISK;
		case "KP MINUS" -> KEY_KP_MINUS;
		case "KP PLUS" -> KEY_KP_PLUS;
		case "KP ENTER" -> KEY_KP_ENTER;
		case "KP 1" -> KEY_KP_1;
		case "KP 2" -> KEY_KP_2;
		case "KP 3" -> KEY_KP_3;
		case "KP 4" -> KEY_KP_4;
		case "KP 5" -> KEY_KP_5;
		case "KP 6" -> KEY_KP_6;
		case "KP 7" -> KEY_KP_7;
		case "KP 8" -> KEY_KP_8;
		case "KP 9" -> KEY_KP_9;
		case "KP 0" -> KEY_KP_0;
		case "KP DOT" -> KEY_KP_DOT;
		case "ESC" -> KEY_ESC;
		case "F1" -> KEY_F1;
		case "F2" -> KEY_F2;
		case "F3" -> KEY_F3;
		case "F4" -> KEY_F4;
		case "F5" -> KEY_F5;
		case "F6" -> KEY_F6;
		case "F7" -> KEY_F7;
		case "F8" -> KEY_F8;
		case "F9" -> KEY_F9;
		case "F10" -> KEY_F10;
		case "F11" -> KEY_F11;
		case "F12" -> KEY_F12;
		case "F13" -> KEY_F13;
		case "F14" -> KEY_F14;
		case "F15" -> KEY_F15;
		case "F16" -> KEY_F16;
		case "F17" -> KEY_F17;
		case "F18" -> KEY_F18;
		case "F19" -> KEY_F19;
		case "F20" -> KEY_F20;
		case "F21" -> KEY_F21;
		case "F22" -> KEY_F22;
		case "F23" -> KEY_F23;
		case "F24" -> KEY_F24;
		case "PRINT SCREEN" -> KEY_PRINT_SCREEN;
		case "SCROLL LOCK" -> KEY_SCROLL_LOCK;
		case "PAUSE" -> KEY_PAUSE;
		default -> throw new IllegalArgumentException("Unexpected value: " + alias);
		};
	}
	
	public static ArduinoKeycodes fromInt(int code) {
		return switch (code) {
		case 0x80 -> KEY_LEFT_CTRL;
		case 0x81 -> KEY_LEFT_SHIFT;
		case 0x82 -> KEY_LEFT_ALT;
		case 0x83 -> KEY_LEFT_GUI;
		case 0x84 -> KEY_RIGHT_CTRL;
		case 0x85 -> KEY_RIGHT_SHIFT;
		case 0x86 -> KEY_RIGHT_ALT;
		case 0x87 -> KEY_RIGHT_GUI;
		case 0xB3 -> KEY_TAB;
		case 0xC1 -> KEY_CAPS_LOCK;
		case 0xB2 -> KEY_BACKSPACE;
		case 0xB0 -> KEY_RETURN;
		case 0xED -> KEY_MENU;
		case 0xD1 -> KEY_INSERT;
		case 0xD4 -> KEY_DELETE;
		case 0xD2 -> KEY_HOME;
		case 0xD5 -> KEY_END;
		case 0xD3 -> KEY_PAGE_UP;
		case 0xD6 -> KEY_PAGE_DOWN;
		case 0xDA -> KEY_UP_ARROW;
		case 0xD9 -> KEY_DOWN_ARROW;
		case 0xD8 -> KEY_LEFT_ARROW;
		case 0xD7 -> KEY_RIGHT_ARROW;
		case 0xDB -> KEY_NUM_LOCK;
		case 0xDC -> KEY_KP_SLASH;
		case 0xDD -> KEY_KP_ASTERISK;
		case 0xDE -> KEY_KP_MINUS;
		case 0xDF -> KEY_KP_PLUS;
		case 0xE0 -> KEY_KP_ENTER;
		case 0xE1 -> KEY_KP_1;
		case 0xE2 -> KEY_KP_2;
		case 0xE3 -> KEY_KP_3;
		case 0xE4 -> KEY_KP_4;
		case 0xE5 -> KEY_KP_5;
		case 0xE6 -> KEY_KP_6;
		case 0xE7 -> KEY_KP_7;
		case 0xE8 -> KEY_KP_8;
		case 0xE9 -> KEY_KP_9;
		case 0xEA -> KEY_KP_0;
		case 0xEB -> KEY_KP_DOT;
		case 0xB1 -> KEY_ESC;
		case 0xC2 -> KEY_F1;
		case 0xC3 -> KEY_F2;
		case 0xC4 -> KEY_F3;
		case 0xC5 -> KEY_F4;
		case 0xC6 -> KEY_F5;
		case 0xC7 -> KEY_F6;
		case 0xC8 -> KEY_F7;
		case 0xC9 -> KEY_F8;
		case 0xCA -> KEY_F9;
		case 0xCB -> KEY_F10;
		case 0xCC -> KEY_F11;
		case 0xCD -> KEY_F12;
		case 0xF0 -> KEY_F13;
		case 0xF1 -> KEY_F14;
		case 0xF2 -> KEY_F15;
		case 0xF3 -> KEY_F16;
		case 0xF4 -> KEY_F17;
		case 0xF5 -> KEY_F18;
		case 0xF6 -> KEY_F19;
		case 0xF7 -> KEY_F20;
		case 0xF8 -> KEY_F21;
		case 0xF9 -> KEY_F22;
		case 0xFA -> KEY_F23;
		case 0xFB -> KEY_F24;
		case 0xCE -> KEY_PRINT_SCREEN;
		case 0xCF -> KEY_SCROLL_LOCK;
		case 0xD0 -> KEY_PAUSE;
		default -> null;
		};
	}
	
	public String toString() {
		return switch (this) {
		case KEY_LEFT_CTRL -> "LEFT CTRL";
		case KEY_LEFT_SHIFT -> "LEFT SHIFT";
		case KEY_LEFT_ALT -> "LEFT ALT";
		case KEY_LEFT_GUI -> "LEFT GUI";
		case KEY_RIGHT_CTRL -> "RIGHT CTRL";
		case KEY_RIGHT_SHIFT -> "RIGHT SHIFT";
		case KEY_RIGHT_ALT -> "RIGHT ALT";
		case KEY_RIGHT_GUI -> "RIGHT GUI";
		case KEY_TAB -> "TAB";
		case KEY_CAPS_LOCK -> "CAPS LOCK";
		case KEY_BACKSPACE -> "BACKSPACE";
		case KEY_RETURN -> "RETURN";
		case KEY_MENU -> "MENU";
		case KEY_INSERT -> "INSERT";
		case KEY_DELETE -> "DELETE";
		case KEY_HOME -> "HOME";
		case KEY_END -> "END";
		case KEY_PAGE_UP -> "PAGE UP";
		case KEY_PAGE_DOWN -> "PAGE DOWN";
		case KEY_UP_ARROW -> "UP ARROW";
		case KEY_DOWN_ARROW -> "DOWN ARROW";
		case KEY_LEFT_ARROW -> "LEFT ARROW";
		case KEY_RIGHT_ARROW -> "RIGHT ARROW";
		case KEY_NUM_LOCK -> "NUM LOCK";
		case KEY_KP_SLASH -> "KP SLASH";
		case KEY_KP_ASTERISK -> "KP ASTERISK";
		case KEY_KP_MINUS -> "KP MINUS";
		case KEY_KP_PLUS -> "KP PLUS";
		case KEY_KP_ENTER -> "KP ENTER";
		case KEY_KP_1 -> "KP 1";
		case KEY_KP_2 -> "KP 2";
		case KEY_KP_3 -> "KP 3";
		case KEY_KP_4 -> "KP 4";
		case KEY_KP_5 -> "KP 5";
		case KEY_KP_6 -> "KP 6";
		case KEY_KP_7 -> "KP 7";
		case KEY_KP_8 -> "KP 8";
		case KEY_KP_9 -> "KP 9";
		case KEY_KP_0 -> "KP 0";
		case KEY_KP_DOT -> "KP DOT";
		case KEY_ESC -> "ESC";
		case KEY_F1 -> "F1";
		case KEY_F2 -> "F2";
		case KEY_F3 -> "F3";
		case KEY_F4 -> "F4";
		case KEY_F5 -> "F5";
		case KEY_F6 -> "F6";
		case KEY_F7 -> "F7";
		case KEY_F8 -> "F8";
		case KEY_F9 -> "F9";
		case KEY_F10 -> "F10";
		case KEY_F11 -> "F11";
		case KEY_F12 -> "F12";
		case KEY_F13 -> "F13";
		case KEY_F14 -> "F14";
		case KEY_F15 -> "F15";
		case KEY_F16 -> "F16";
		case KEY_F17 -> "F17";
		case KEY_F18 -> "F18";
		case KEY_F19 -> "F19";
		case KEY_F20 -> "F20";
		case KEY_F21 -> "F21";
		case KEY_F22 -> "F22";
		case KEY_F23 -> "F23";
		case KEY_F24 -> "F24";
		case KEY_PRINT_SCREEN -> "PRINT SCREEN";
		case KEY_SCROLL_LOCK -> "SCROLL LOCK";
		case KEY_PAUSE -> "PAUSE";
		};
	}

	public int toInt() {
		return switch (this) {
		case KEY_LEFT_CTRL -> 0x80;
		case KEY_LEFT_SHIFT -> 0x81;
		case KEY_LEFT_ALT -> 0x82;
		case KEY_LEFT_GUI -> 0x83;
		case KEY_RIGHT_CTRL -> 0x84;
		case KEY_RIGHT_SHIFT -> 0x85;
		case KEY_RIGHT_ALT -> 0x86;
		case KEY_RIGHT_GUI -> 0x87;
		case KEY_TAB -> 0xB3;
		case KEY_CAPS_LOCK -> 0xC1;
		case KEY_BACKSPACE -> 0xB2;
		case KEY_RETURN -> 0xB0;
		case KEY_MENU -> 0xED;
		case KEY_INSERT -> 0xD1;
		case KEY_DELETE -> 0xD4;
		case KEY_HOME -> 0xD2;
		case KEY_END -> 0xD5;
		case KEY_PAGE_UP -> 0xD3;
		case KEY_PAGE_DOWN -> 0xD6;
		case KEY_UP_ARROW -> 0xDA;
		case KEY_DOWN_ARROW -> 0xD9;
		case KEY_LEFT_ARROW -> 0xD8;
		case KEY_RIGHT_ARROW -> 0xD7;
		case KEY_NUM_LOCK -> 0xDB;
		case KEY_KP_SLASH -> 0xDC;
		case KEY_KP_ASTERISK -> 0xDD;
		case KEY_KP_MINUS -> 0xDE;
		case KEY_KP_PLUS -> 0xDF;
		case KEY_KP_ENTER -> 0xE0;
		case KEY_KP_1 -> 0xE1;
		case KEY_KP_2 -> 0xE2;
		case KEY_KP_3 -> 0xE3;
		case KEY_KP_4 -> 0xE4;
		case KEY_KP_5 -> 0xE5;
		case KEY_KP_6 -> 0xE6;
		case KEY_KP_7 -> 0xE7;
		case KEY_KP_8 -> 0xE8;
		case KEY_KP_9 -> 0xE9;
		case KEY_KP_0 -> 0xEA;
		case KEY_KP_DOT -> 0xEB;
		case KEY_ESC -> 0xB1;
		case KEY_F1 -> 0xC2;
		case KEY_F2 -> 0xC3;
		case KEY_F3 -> 0xC4;
		case KEY_F4 -> 0xC5;
		case KEY_F5 -> 0xC6;
		case KEY_F6 -> 0xC7;
		case KEY_F7 -> 0xC8;
		case KEY_F8 -> 0xC9;
		case KEY_F9 -> 0xCA;
		case KEY_F10 -> 0xCB;
		case KEY_F11 -> 0xCC;
		case KEY_F12 -> 0xCD;
		case KEY_F13 -> 0xF0;
		case KEY_F14 -> 0xF1;
		case KEY_F15 -> 0xF2;
		case KEY_F16 -> 0xF3;
		case KEY_F17 -> 0xF4;
		case KEY_F18 -> 0xF5;
		case KEY_F19 -> 0xF6;
		case KEY_F20 -> 0xF7;
		case KEY_F21 -> 0xF8;
		case KEY_F22 -> 0xF9;
		case KEY_F23 -> 0xFA;
		case KEY_F24 -> 0xFB;
		case KEY_PRINT_SCREEN -> 0xCE;
		case KEY_SCROLL_LOCK -> 0xCF;
		case KEY_PAUSE -> 0xD0;
		};
	}
}
