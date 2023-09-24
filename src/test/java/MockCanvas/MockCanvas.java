package MockCanvas;

import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.views.View;

public class MockCanvas {

    PObject _currentlyHovering;

    public void updateHoveredObject(int mouseX, int mouseY) {

        for (PObject pObject : AppState.getInstance().getPObjects()) {
            for (Component component : pObject.getComponents().values()) {
                View<Component> view = component.getView();
                if (view == null) {  continue; }
                boolean isHovered = view.isMouseOver(mouseX, mouseY);
                if (isHovered) {
                    pObject.setIsHovered(isHovered, mouseX, mouseY);
                    _currentlyHovering = pObject;
                    return;
                }
            }
        }
        //if it reaches this point it means that no object is being hovered
        if(_currentlyHovering != null){
            _currentlyHovering.setIsHovered(false, mouseX, mouseY);
            _currentlyHovering = null;
        }
    }
}
