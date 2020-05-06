//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Game;

import Controller.KeyboardController;
import java.awt.Color;

public abstract class ControlledGameObject extends GameObject implements Moveable {
    KeyboardController control;

    public ControlledGameObject(int xPosition, int yPosition, Color color, KeyboardController control) {
        super(xPosition, yPosition, color);
        this.control = control;
    }
}
