package me.gabrielsalvador.timing;

import controlP5.ControllerView;
import processing.core.PGraphics;

class SequencerView implements ControllerView<SequencerController> {
    private final SequencerController _controller;
    SequencerView(SequencerController controller) {
        _controller = controller;
    }

    public void display(PGraphics theGraphics, SequencerController theController) {

        theGraphics.pushStyle();

        float[] positon = _controller.getPosition();
        theGraphics.translate(positon[0],positon[1]);
        theGraphics.rect(0.0F, 0.0F, (float)_controller.getWidth(), (float)_controller.getHeight());
        float stepX = (float)_controller.getWidth() / (float)_controller.getDivisionTime();
        float stepY = (float)_controller.getHeight() / (float)_controller.getDivisionPitch();
        theGraphics.strokeWeight(1.0F);

        for(int x = 0; x < _controller.getDivisionTime() ; ++x) {
            for(int y = 0; y < _controller.getDivisionPitch() ; ++y) {
                theGraphics.noStroke();
                theGraphics.fill(_controller.getSteps()[x][y] ? _controller.getColor().getActive() : _controller.getColor().getBackground());
                theGraphics.rect((float)x * stepX, (float)y * stepY, stepX, stepY);
                if (x > 0) {
                    theGraphics.stroke(30.0F, 30.0F, 30.0F);
                    theGraphics.line((float)((int)((float)x * stepX)), 0.0F, (float)((int)((float)x * stepX)), (float)_controller.getHeight());
                    theGraphics.noStroke();
                }

                if (y > 0) {
                    theGraphics.stroke(30.0F, 30.0F, 30.0F);
                    theGraphics.line(0.0F, (float)((int)((float)y * stepY)), (float)_controller.getWidth(), (float)((int)((float)y * stepY)));
                    theGraphics.noStroke();
                }
            }
        }

        if (_controller.isInside()) {
        }

        theGraphics.fill(_controller.getColor().getActive());
        theGraphics.rect((float)_controller.getPlayhead() * stepX, 0.0F, 1.0F, (float)_controller.getHeight());
        if (_controller.isLabelVisible()) {
            _controller.getCaptionLabel().draw(theGraphics, 0, 0, theController);
        }

        theGraphics.popStyle();
    }


}