package me.gabrielsalvador.ui.controllers;

import controlP5.*;
import me.gabrielsalvador.ui.views.CanvasView;
import processing.core.PGraphics;


// Custom controller class that extends Controller
public class Canvas extends Controller<Canvas> {

  
  public Canvas(ControlP5 cp5, String theName, int theX, int theY, int theWidth, int theHeight) {
    super(cp5, theName, theX, theY, theWidth, theHeight);
    _myControllerView = new CanvasView();
  }

  @Override
  public void onEnter() {
    // This method is called when the mouse enters the area of the controller
    System.out.println("Mouse entered");
  }

  @Override
  public void onLeave() {
    // This method is called when the mouse leaves the area of the controller
    System.out.println("Mouse left");
  }

  @Override
  public void onRelease() {
    // This method is called when the mouse button is released over the controller
    System.out.println("Mouse released");
  }

  @Override
  public void onReleaseOutside() {
    // This method is called when the mouse button is released outside of the controller
    System.out.println("Mouse released outside");
  }

  @Override
  public void onPress() {
    // This method is called when the mouse button is pressed over the controller
    System.out.println("Mouse pressed");
  }

  @Override
  public void onDrag() {
    // This method is called when the mouse is dragged while the mouse button is pressed
    System.out.println("Mouse dragged");
  }

  @Override
  public void onMove() {
    // This method is called when the mouse is moved over the controller
    System.out.println("Mouse moved");
  }

  @Override
  public void onClick() {
    // This method is called when the mouse button is clicked (pressed and released) on the controller
    System.out.println("Mouse clicked");
  }

  @Override
  public void onScroll(int theRotationValue) {
    // This method is called when the mouse wheel is scrolled over the controller
    System.out.println("Mouse wheel scrolled: " + theRotationValue);
  }

  
  public void onKey(char theChar, int theKeyCode) {
    // This method is called when a key is pressed while the mouse is over the controller
    System.out.println("Key pressed: " + theChar + ", code: " + theKeyCode);
  }


  
  @Override
  public void draw(PGraphics graphics) {
    
    	
		graphics.pushMatrix( );
		graphics.translate( x( position ) , y( position ) );
		// getView().display( graphics , this );
		getView().display( graphics, this );
		// theGraphics.popMatrix( );
		graphics.popMatrix( );

	}

 
    
  

}
