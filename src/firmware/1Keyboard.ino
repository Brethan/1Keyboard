#include <FlashStorage_SAMD.h>
#include <Keyboard.h>
#include <Keypad.h>

// this is kind of pointless but whatever
#define ROWS 1
#define COLS 1

void serialEvent();
// Permanently writes to the Flash storage on the 1Keyboard
unsigned char commit(unsigned char newChar) {
    EEPROM.put(0, newChar);
    if (!EEPROM.getCommitASAP()) {
        EEPROM.commit();
    }

    return newChar;
}

bool keyChanged = false;
unsigned char keyChar;
char keys[ROWS][COLS] = {{'a'}};

byte rowPins[ROWS] = {10};
byte colPins[COLS] = {9};

Keypad keypad = Keypad(makeKeymap(keys), rowPins, colPins, ROWS, COLS);

void setup() {
    // put your setup code here, to run once:
    Serial.begin(9600);
    Keyboard.begin();

    int value = EEPROM.read(0);

    // On first boot after programming, set typed key to KEY_DELETE
    // Flash memory is set to 0xFF to erase it while programming
    if (value == 0xFF) {
        commit(0, KEY_DELETE);
        value = KEY_DELETE;
    }

    keyChar = (unsigned char)value;
}

// KeyState has a HOLD state, but it doesn't activate for 3-ish loops.
bool keyOff = true;
void loop() {
    // put your main code here, to run repeatedly:
    char key = keypad.getKey();
    KeyState state = keypad.getState();

    switch (state) {
        case PRESSED:
            if (keyOff) {
                keyOff = false;
                Keyboard.press(keyChar);
                delay(15);
            }
            break;
        case RELEASED:
            keyOff = true;
            Keyboard.releaseAll();
            break;
    }

    // Check for updates from the Keypad Controller
    if (Serial.available()) {
        serialEvent();
    }

    delay(15);
}

void serialEvent() {
    // commands are terminated with '&'
    String command = Serial.readStringUntil('&');
    if (command.indexOf("getKeyChar") > -1) {
        // Respond with the current typed key
        Serial.println(keyChar);
    } else if (command.indexOf("changeKey,") > -1) {
        // Overwrite the current typed key
        int begin = command.indexOf(',') + 1;

        if (begin < command.length()) {
            // C++ moment.
            unsigned char potentialChar = (unsigned char)command.substring(command.indexOf(',') + 1).toInt();
            if (potentialChar) {
                keyChar = commit(potentialChar);
            }
        }
    }

    while (Serial.available()) {
        Serial.read();
    }
}
