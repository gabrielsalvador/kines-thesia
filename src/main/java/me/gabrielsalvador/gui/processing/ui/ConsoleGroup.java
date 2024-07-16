package me.gabrielsalvador.gui.processing.ui;

import controlP5.*;
import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.kinescript.lang.Kinescript;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ConsoleGroup extends CustomGroup implements PropertyChangeListener, CallbackListener {

    boolean consoleExpanded = false;
    private MultilineTextfield input;
    private Textarea consoleOutput;
    Button expandButton;

    public ConsoleGroup(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);

        consoleOutput = new Textarea(theControlP5, "consoleOutput");
        consoleOutput.moveTo(this).getCaptionLabel().hide();
        //consoleOutput background transparent black
        consoleOutput.setColorBackground(0x45000000);
        cp5.addConsole(consoleOutput);

        //input
        input = new MultilineTextfield(theControlP5, "input");
        input.moveTo(this).getCaptionLabel().hide();
        input.setAutoClear(true);

        input.getKeyMapping().put(10, () -> {
            if(!theControlP5.isShiftDown()) {
                input._myTextBuffer.insert(input._myTextBufferIndex, '\n');
                input.setIndex(input._myTextBufferIndex + 1);
            }else {
                String text = input.getText();
                try {
                    KFunction f = Kinescript.compileFunction(text);
                    f.execute();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });


//        ? key
        input.getKeyMapping().put(47, () -> {
            if(theControlP5.isAltDown()) {
                toggleConsole();
            }else {
                //normal behavior
                input._myTextBuffer.insert(input._myTextBufferIndex, '/');
                input.setIndex(input._myTextBufferIndex + 1);
            }
        });





        //expand button
        expandButton = new Button(theControlP5, "expandButton");
        expandButton.moveTo(this).setLabel("▲"); //▼
        expandButton.addListenerFor(ControlP5Constants.ACTION_PRESS, controlEvent -> {
            toggleConsole();
        });







    }

    @Override
    public void resize(int width, int height) {
        super.setWidth(width);
        super.setHeight(height);
    }

    @Override
    public Group setSize(int theWidth, int theHeight) {
        setWidth(theWidth);
        setHeight(theHeight);
        return this;
    }

    @Override
    public Group setWidth(int theWidth) {
        super.setWidth(theWidth);
        consoleOutput.setWidth(theWidth);

        input.setWidth(theWidth - 45);

        //expand button
        expandButton.setPosition(theWidth - 40, expandButton.getPosition()[1]);
        expandButton.setWidth(40);





        return this;
    }

    @Override
    public Group setHeight(int theHeight) {
        super.setHeight(theHeight);
        consoleOutput.setHeight(theHeight - 40);

        //input
        input.setPosition(0, theHeight - 40);
        input.setHeight(40);

        //expand button
        expandButton.setPosition(expandButton.getPosition()[0] , theHeight - 40);
        expandButton.setHeight(40);


        return this;
    }



    @Override
    public void controlEvent(CallbackEvent callbackEvent) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }


    public void toggleConsole() {
        if(!consoleExpanded){
            input.setPosition(0, -300);
            input.setHeight(getHeight() +300);
            consoleOutput.setHeight(getHeight() + 60);
            consoleOutput.setPosition(0, -580);
            expandButton.setLabel("▼");
            bringToFront();
            consoleExpanded = !consoleExpanded;
        }else {
            input.setPosition(0, getHeight() - 40);
            input.setHeight(40);
            consoleOutput.setHeight(getHeight() - 40);
            consoleOutput.setPosition(0, 0);
            expandButton.setLabel("▲");
            bringToFront();

            consoleExpanded = !consoleExpanded;
        }
    }
}
