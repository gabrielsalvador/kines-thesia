package me.gabrielsalvador.gui.processing.ui;

import controlP5.*;
import me.gabrielsalvador.core.CallbackWrapper;
import me.gabrielsalvador.gui.processing.Main;
import me.gabrielsalvador.core.PObjectProperty;
import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.kinescript.lang.Kinescript;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;



public class CodeEditor extends CustomGroup implements PropertyEditor{



    ArrayList<PObjectProperty> properties;
    List<ControllerInterface<?>> children = new ArrayList<>();
    Textlabel titleLabel = new Textlabel(Main.getInstance().getCP5(), "titleLabel");

    MultilineTextfield codeTextbox = new MultilineTextfield(Main.getInstance().getCP5(), "Enter command here");
    {
        codeTextbox.getCaptionLabel().hide();
        codeTextbox.addListenerFor(ACTION_BROADCAST, event -> {
            compile();
        })
        .setAutoClear(false)
        .setHeight(100);
    }
    Textlabel feedbackLabel = new Textlabel(Main.getInstance().getCP5(), "feedbackLabel");
    Button compileButton = new Button(Main.getInstance().getCP5(), "Compile").addListenerFor(ACTION_PRESS, event -> {
        compile();
    });

    Button availableObjectsButton = new Button(Main.getInstance().getCP5(), "Available Objects");
    public CodeEditor(ControlP5 theControlP5, String theName,ArrayList<PObjectProperty> properties) {
        super(theControlP5, theName);

        this.properties = properties;

        //title
        children.add(titleLabel);
        //code textbox
        children.add(codeTextbox);
        if(!properties.isEmpty()) {
           for (PObjectProperty p : properties) {
               Object property = p.getValue();
               if(property instanceof CallbackWrapper wrapper) {
                   if (wrapper.isKFunction()) {
                       codeTextbox.setText(wrapper.getKFunction().getSourceCode());
                   }
               }
           }
        }
//        //feedback label
        children.add(feedbackLabel);

//        //compile button
        children.add(compileButton);

        //available objects button
        availableObjectsButton.addListenerFor(PRESS, event -> {
//            App.getInstance().showToolTip();
        });
        availableObjectsButton.moveTo(this);

        ;




        children.forEach(child -> {
            addChildVertically(child);
            child.setWidth(getWidth());
        });

        setHeight(150);

    }

    public void compile() {

      try {
          KFunction function = Kinescript.compileFunction(codeTextbox.getText());
          for (PObjectProperty p : properties) {
              p.setValue(new CallbackWrapper(function));
          }

          feedbackLabel.setText("Compiled successfully");
      } catch (InputMismatchException e) {
          feedbackLabel.setText("Error compiling function: " + e.getMessage());
          e.printStackTrace();
      }

        catch (Exception e) {
          feedbackLabel.setText("Error compiling function: " + e.getMessage());
          e.printStackTrace();
      }



    }




    @Override
    public void resize(int width, int height) {
        setWidth(width);
    }


    @Override
    public Group setWidth(int theWidth) {
        super.setWidth(theWidth);

        //available objects button
        availableObjectsButton.setPosition((float) getWidth() /2 + 20, 5);
        availableObjectsButton.setSize(130, 20);

        for (ControllerInterface child : children) {
            child.setWidth(Math.round(theWidth));
        }
        return this;
    }

    @Override
    public Group setHeight(int theHeight) {
        super.setHeight(theHeight);

        titleLabel.setPosition(0, 0);
        titleLabel.setHeight(30);



        codeTextbox.setPosition(0, titleLabel.getHeight());
        codeTextbox.setHeight(theHeight/2 - 10);

        feedbackLabel.setPosition(0, codeTextbox.getHeight() + feedbackLabel.getHeight());
        feedbackLabel.setHeight(theHeight/4 - 10);

        compileButton.setPosition(0, codeTextbox.getHeight() + feedbackLabel.getHeight() + compileButton.getHeight());
        compileButton.setHeight(theHeight/4 - 10);


        return this;
    }

    @Override
    public int getHeightForInspector() {
        return 700;
    }
}
