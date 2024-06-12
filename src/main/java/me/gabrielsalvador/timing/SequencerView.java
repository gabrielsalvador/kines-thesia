package me.gabrielsalvador.timing;

import controlP5.ControllerView;
import me.gabrielsalvador.utils.Stopwatch;
import processing.core.PGraphics;

class SequencerView implements ControllerView<SequencerController> {
    private final SequencerController _controller;
    SequencerView(SequencerController controller) {
        _controller = controller;
    }

    public void display(PGraphics theGraphics, SequencerController theController) {
        theGraphics.pushStyle();

        float[] position = _controller.getPosition();
        theGraphics.translate(position[0], position[1]);
        theGraphics.rect(0.0F, 0.0F, (float) _controller.getWidth(), (float) _controller.getHeight());
        float stepX = (float) _controller.getWidth() / (float) _controller.getDivisionTime();
        float stepY = (float) _controller.getHeight() / (float) _controller.getDivisionPitch();

        theGraphics.strokeWeight(1.0F);

        boolean[][] steps = _controller.getSteps();
        int divisionTime = _controller.getDivisionTime();
        int divisionPitch = _controller.getDivisionPitch();
        float controllerHeight = (float) _controller.getHeight();
        float controllerWidth = (float) _controller.getWidth();
        int colorActive = _controller.getColor().getActive();
        int colorBackground = _controller.getColor().getBackground();

        theGraphics.noStroke();

        for (int x = 0; x < divisionTime; ++x) {
            float xPos = x * stepX;

            if (x/(_controller.getDivisionTime()/4) % 2 == 0) {
                colorBackground = _controller.getColor().getBackground();
                //color format is 0xAARRGGBB
                //add 0x00111111 to the color to make it darker
                colorBackground += 0x00111111;

            }else {
                colorBackground = _controller.getColor().getBackground();
            }

            for (int y = 0; y < divisionPitch; ++y) {
                int yOffset = _controller.getOffset();
                if(y + yOffset < 0 || y + yOffset >= divisionPitch) {
                    theGraphics.fill(colorBackground);
                } else{
                    theGraphics.fill(steps[x][y + yOffset] ? colorActive : colorBackground);
                }
                    theGraphics.rect(xPos, controllerHeight - ((y + 1) * stepY), stepX, stepY);
            }
        }

        theGraphics.stroke(30.0F, 30.0F, 30.0F);

        for (int x = 1; x < divisionTime; ++x) {
            float xPos = x * stepX;

            theGraphics.line(xPos, 0.0F, xPos, controllerHeight);
        }

        theGraphics.getStyle();

        for (int y = 1; y < divisionPitch; ++y) {
            float yPos = y * stepY;
            theGraphics.line(0.0F, yPos, controllerWidth, yPos);
        }

        theGraphics.noStroke();
        theGraphics.fill(colorActive);
        theGraphics.rect(_controller.getPlayhead() * stepX, 0.0F, 1.0F, controllerHeight);

        if (_controller.isLabelVisible()) {
            _controller.getCaptionLabel().draw(theGraphics, 0, 0, theController);
        }

        theGraphics.popStyle();
    }


}