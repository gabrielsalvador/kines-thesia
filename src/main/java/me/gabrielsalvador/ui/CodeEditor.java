package me.gabrielsalvador.ui;

import controlP5.*;
import me.gabrielsalvador.core.App;
import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.kinescript.lang.Kinescript;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.utils.CallbackWrapper;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;



public class CodeEditor extends CustomGroup implements PropertyEditor{



    ArrayList<PObjectProperty> properties;
    List<ControllerInterface<?>> children = new ArrayList<>();
    Textlabel titleLabel = new Textlabel(App.getInstance().getCP5(), "titleLabel");

    MultilineTextfield codeTextbox = new MultilineTextfield(App.getInstance().getCP5(), "Enter command here");
    {
        codeTextbox.getCaptionLabel().hide();
        codeTextbox.addListenerFor(ControlP5Constants.ACTION_BROADCAST, event -> {
            compile();
        })
        .setAutoClear(false)
        .setHeight(100)
        ;
    }
    Textlabel feedbackLabel = new Textlabel(App.getInstance().getCP5(), "feedbackLabel");
    Button compileButton = new Button(App.getInstance().getCP5(), "Compile").addListenerFor(ControlP5Constants.ACTION_CLICK, event -> {
        compile();
    });

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

        for (ControllerInterface child : controllers.get()) {
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
        return 400;
    }
}
