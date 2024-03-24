package me.gabrielsalvador.pobject.components.controllers;

import controlP5.*;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.kinescript.lang.Kinescript;
import me.gabrielsalvador.pobject.PObjectProperty;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class CodeEditor extends Group {



    private final PObjectProperty _property;
    List<ControllerInterface<?>> children = new ArrayList<>();


    MultilineTextfield codeTextbox = new MultilineTextfield(Sinesthesia.getInstance().getCP5(), "Enter command here");
    {
        codeTextbox.getCaptionLabel().hide();
        codeTextbox.addListenerFor(ControlP5Constants.ACTION_BROADCAST, event -> {
            compile();
        })
        .setAutoClear(false)
        .setHeight(100)
        ;
    }
    Textlabel feedbackLabel = new Textlabel(Sinesthesia.getInstance().getCP5(), "feedbackLabel");
    Button compileButton = new Button(Sinesthesia.getInstance().getCP5(), "Compile").addListenerFor(ControlP5Constants.ACTION_CLICK, event -> {
        compile();
    });


    public void compile() {

      try {
          KFunction function = Kinescript.compileFunction(codeTextbox.getText());
          _property.setValue(function);
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


    public CodeEditor(PObjectProperty property, ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
        _property = property;

        //init code editor
        KFunction function = (KFunction) _property.getValue();
        if (function != null){
            codeTextbox.setText(function.getSourceCode());
        }
        children.add(codeTextbox);

        //init feedback label
        children.add(feedbackLabel);

        //init compile button
        children.add(compileButton);

        children.forEach(child -> {
            addChildVertically(child);
            addChildVertically(new Spacer(theControlP5, "spacer" + children.indexOf(child)));
            child.setWidth(getWidth());
        });

    }




    @Override
    public Group setWidth(int theWidth) {
        super.setWidth(theWidth);

        for (ControllerInterface child : controllers.get()) {
            child.setWidth(Math.round(theWidth));
        }
        return this;
    }
}
