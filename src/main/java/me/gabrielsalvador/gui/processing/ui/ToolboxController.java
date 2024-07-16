package me.gabrielsalvador.gui.processing.ui;

import controlP5.*;
import me.gabrielsalvador.gui.processing.Config;
import me.gabrielsalvador.gui.processing.DisplayName;
import me.gabrielsalvador.gui.processing.Main;
import me.gabrielsalvador.gui.processing.SkipProcessing;
import me.gabrielsalvador.gui.processing.tools.Tool;
import me.gabrielsalvador.gui.processing.tools.ToolManager;
import processing.core.PImage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public
class ToolboxController extends Group {

    final private Main app = Main.getInstance();
    final private Map<Class<? extends Tool>, Button> _children = new HashMap<>();

    public ToolboxController(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);

    }



    public void didSetupLayout() {
        /*Gets all the tools available in the code and builds the ToolBox*/
        ToolManager toolManager = ToolManager.getInstance();
        Set<Class<? extends Tool>> tools = toolManager.getTools();
        for (Class<? extends Tool> tool : tools) {
            if (tool.isAnnotationPresent(SkipProcessing.class)) {
                continue;
            }
            String uii = UUID.randomUUID().toString();
            char getShortcut = toolManager.getShortcutForTool(tool);
            Button b = new Button(cp5, uii).setHeight(50).registerTooltip(getShortcut + "");
            //icon
            String path = Config.ICON_FOLDER_PATH + "tools/" + tool.getSimpleName().toLowerCase();
            PImage normal = app.loadImage(path + ".png");
//            PImage active = app.loadImage(path + "-active.png");
//            PImage hover = app.loadImage(path + "-hover.png");
            b.setImage(normal);

            b.setSize(32,62);

            //naming
            if (tool.isAnnotationPresent(DisplayName.class)) {
                DisplayName displayName = tool.getAnnotation(DisplayName.class);
                String displayText = displayName.value()+ "[" + toolManager.getShortcutForTool(tool) + "]";
                b.getCaptionLabel().setText(displayText);
                b.registerTooltip("use shortcuts! " + toolManager.getShortcutForTool(tool));

            } else {
                b.getCaptionLabel().setText(tool.getSimpleName());
            }

            b.addListener(new ControlListener() {
                @Override
                public void controlEvent(ControlEvent controlEvent) {
                    ToolManager.getInstance().selectTool(tool);
                }
            });
            b.setSwitch(true);
            _children.put(tool, b);


            ToolManager.getInstance().addPropertyChangeListener("currentTool", new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    updateButtonStates((Tool) evt.getOldValue(), (Tool) evt.getNewValue());
                }
            });
            b.setWidth(getWidth());
            addChildVertically(b);
        }



    }


    //if a tool is selected, update the button state

    private void updateButtonStates(Tool oldTool, Tool newTool) {
        if (oldTool != null) {
            Button oldButton = _children.get(oldTool.getClass());
            if (oldButton != null) oldButton.updateOn(false);
        }
        if (newTool != null) {
            Button newButton = _children.get(newTool.getClass());
            if (newButton != null) newButton.updateOn(true);
        }
    }


}
