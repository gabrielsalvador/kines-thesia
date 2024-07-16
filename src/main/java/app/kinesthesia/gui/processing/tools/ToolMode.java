package app.kinesthesia.gui.processing.tools;


import app.kinesthesia.gui.processing.ProcessingGuiMain;
import app.kinesthesia.gui.processing.Config;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

public class ToolMode {
    private PImage _cursorIcon;
    private final String _name;

    /* Keys that must be pressed to switch to this mode */
    private boolean requiresAlt = false; 
    private boolean requiresCtrl = false;
    private boolean requiresMeta = false;
    private boolean requiresShift = false;

    public ToolMode(String name) {
        _name = name;
    }


    /**
     * Checks if there are keys being pressed that should make the tool switch to this mode
     * @param applet
     * @return
     */
    public boolean shouldSwitchMode(PApplet applet) {
        KeyEvent event = applet.keyEvent;        
        if (event == null) {
            return false;
        }

        if (requiresAlt && event.isAltDown()) {
            return true;
        }
        if (requiresCtrl && event.isControlDown()) {
            return true;
        }
        if (requiresMeta && event.isMetaDown()) {
            return true;
        }
        return requiresShift && event.isShiftDown();


    }

    public PImage getCursorIcon() {
        return _cursorIcon;
    }

    public ToolMode setIcon(String path) {
        PApplet papplet = ProcessingGuiMain.getInstance();
        _cursorIcon = papplet.loadImage(Config.ICON_FOLDER_PATH + path);
        return this;
    }

    public String getName() {
        return _name;
    }

    public ToolMode setModifierKeys(int key){
        switch(key){
            case KeyEvent.ALT:
                requiresAlt = true;
                break;
            case KeyEvent.CTRL:
                requiresCtrl = true;
                break;
            case KeyEvent.META:
                requiresMeta = true;
                break;
            case KeyEvent.SHIFT:
                requiresShift = true;
                break;
            default:
                System.out.println("ToolMode: Invalid key");
                break;
        }
        return this;
    }

}
