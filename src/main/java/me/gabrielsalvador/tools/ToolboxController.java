package me.gabrielsalvador.tools;

import controlP5.*;
import me.gabrielsalvador.common.DisplayName;
import me.gabrielsalvador.common.SkipProcessing;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public
class ToolboxController extends Group {

    final private Map<Class<? extends Tool>, Button> _children = new HashMap<>();

    public ToolboxController(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);

    }



    public void didSetupLayout() {
        /*Gets all the tools available in the code and builds the ToolBox*/
        Set<Class<? extends Tool>> tools = ToolManager.getInstance().getTools();
        for (Class<? extends Tool> tool : tools) {
            if (tool.isAnnotationPresent(SkipProcessing.class)) {
                continue;
            }
            String uii = UUID.randomUUID().toString();
            Button b = new Button(cp5, uii);

            if (tool.isAnnotationPresent(DisplayName.class)) {
                DisplayName displayName = tool.getAnnotation(DisplayName.class);
                b.getCaptionLabel().setText(displayName.value());
            } else {
                b.getCaptionLabel().setText(tool.getSimpleName());
            }

            b.addListener(new ControlListener() {
                @Override
                public void controlEvent(ControlEvent controlEvent) {
                    ToolManager.getInstance().pushTool(tool);
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
