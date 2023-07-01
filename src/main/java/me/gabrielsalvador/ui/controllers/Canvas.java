package me.gabrielsalvador.ui.controllers;

import controlP5.*;
import me.gabrielsalvador.tools.Tool;
import me.gabrielsalvador.tools.ToolManager;
import me.gabrielsalvador.ui.views.CanvasView;
import processing.core.PGraphics;


// Custom controller class that extends Controller
public class Canvas extends Controller<Canvas> {

  private ToolManager _toolManager;
  
  public Canvas(ControlP5 cp5, String theName, int theX, int theY, int theWidth, int theHeight) {
    super(cp5, theName, theX, theY, theWidth, theHeight);
    _myControllerView = new CanvasView();
    _toolManager = ToolManager.getInstance();
  }

  @Override
  public void onEnter() {
    _toolManager.getCurrentTool().onEnter();
  }

  @Override
  public void onLeave() {
    _toolManager.getCurrentTool().onLeave();
  }

  @Override
  public void onRelease() {
    _toolManager.getCurrentTool().onRelease();
  }

  @Override
  public void onReleaseOutside() {
    _toolManager.getCurrentTool().onReleaseOutside();
  }

  @Override
  public void onPress() {
    _toolManager.getCurrentTool().onPress();
  }

  @Override
  public void onDrag() {
    _toolManager.getCurrentTool().onDrag();
  }

  @Override
  public void onMove() {
    _toolManager.getCurrentTool().onMove();
  }

  @Override
  public void onClick() {
    _toolManager.getCurrentTool().onClick();
  }

  @Override
  public void onScroll(int theRotationValue) {
    _toolManager.getCurrentTool().onScroll();
  }

  
  public void onKey(char theChar, int theKeyCode) {
    _toolManager.getCurrentTool().onKey();
  }


  
  @Override
  public void draw(PGraphics graphics) {
    
    	
		graphics.pushMatrix( );
		graphics.translate( x( position ) , y( position ) );
		getView().display( graphics, this );
		graphics.popMatrix( );

	}

 
    
  

}
