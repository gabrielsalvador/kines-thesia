package me.gabrielsalvador.ui.controllers;

import controlP5.*;
import controlP5.events.ReleasedOutsideListener;
import me.gabrielsalvador.tools.ToolManager;
import me.gabrielsalvador.ui.views.CanvasView;
import processing.core.PGraphics;
import processing.event.KeyEvent;

// Custom controller class that extends Controller
public class Canvas extends Controller<Canvas> implements ReleasedOutsideListener {

  private ToolManager _toolManager;

  public Canvas(ControlP5 cp5, String name) {
    super(cp5, name);
    _myControllerView = new CanvasView();
    _toolManager = ToolManager.getInstance();
  }

  @Override
  public void onEnter() {
    _toolManager.get_currentTool().onEnter();
  }

  @Override
  public void onLeave() {
    _toolManager.get_currentTool().onLeave();
  }

  @Override
  public void onRelease() {
    _toolManager.get_currentTool().onRelease();
  }

  @Override
  public void onPress() {
    isActive = inside();
    _toolManager.get_currentTool().onPress();
  }

  @Override
  public void mousePressed() {
    super.mousePressed();
  }

  @Override
  public void mouseReleasedOutside() {
    isActive = false;
  }

  @Override
  public void onDrag() {
    _toolManager.get_currentTool().onDrag();
  }

  @Override
  public void onMove() {
    _toolManager.get_currentTool().onMove();
  }

  @Override
  public void onClick() {
    // x and y are relative to the canvas
    int x = cp5.getPointer().getX() - (int) absolutePosition[0];
    int y = cp5.getPointer().getY() - (int) absolutePosition[1];
    _toolManager.get_currentTool().onClick(x, y);
  }

  @Override
  public void onScroll(int theRotationValue) {
    _toolManager.get_currentTool().onScroll();
  }

  @Override
  public void keyEvent(KeyEvent theKeyEvent) {
    if (isUserInteraction && isActive && theKeyEvent.getAction() == KeyEvent.PRESS) {
      _toolManager.keyEvent(theKeyEvent);
     
    }
  }

  @Override
  public void draw(PGraphics graphics) {

    graphics.pushMatrix();
    graphics.translate(x(position), y(position));
    getView().display(graphics, this);
    graphics.popMatrix();

  }

}
