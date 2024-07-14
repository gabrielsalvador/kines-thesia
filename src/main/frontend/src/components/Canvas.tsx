import React from "react"
import Konva from "../../../../../../konva/konva"
import useRef from 'react';
import { Router } from "../../common/router"


sad


export const Canvas = () => {

  const canvasRef = React.useRef(null)

  React.useEffect(() => {
    if (!canvasRef.current) return

    var width = window.innerWidth;
    var height = window.innerHeight;

    var stage = new Konva.Stage({
      container: 'container',
      width: width,
      height: height,
    });

    var layer = new Konva.Layer();
    stage.add(layer);

    var rect1 = new Konva.Rect({
      x: 60,
      y: 60,
      width: 100,
      height: 90,
      fill: 'red',
      name: 'rect',
      draggable: true,
    });
    layer.add(rect1);

    var rect2 = new Konva.Rect({
      x: 250,
      y: 100,
      width: 150,
      height: 90,
      fill: 'green',
      name: 'rect',
      draggable: true,
    });
    layer.add(rect2);

    var tr = new Konva.Transformer();

    let router = new Router(
      {
        x: 0,
        y: -10,
        radius: 10,
        name: 'top-left',
        draggable: true,
        stroke: 'rgb(0, 161, 255)',
        fill: 'white',
        strokeWidth: 1,
      })
      

    tr.addCustomAnchor(router);

      
    layer.add(tr);

    // by default select all shapes
    tr.nodes([rect1, rect2]);

    // add a new feature, lets add ability to draw selection rectangle
    var selectionRectangle = new Konva.Rect({
      fill: 'rgba(0,0,255,0.5)',
      visible: false,
      // disable events to not interrupt with events
      listening: false,
    });
    layer.add(selectionRectangle);

    var x1: number, y1: number, x2, y2;
    var selecting = false;
    stage.on('mousedown touchstart', (e: { target: any; evt: { preventDefault: () => void } }) => {
      // do nothing if we mousedown on any shape
      if (e.target !== stage) {
        return;
      }
      e.evt.preventDefault();
      x1 = stage.getPointerPosition().x;
      y1 = stage.getPointerPosition().y;
      x2 = stage.getPointerPosition().x;
      y2 = stage.getPointerPosition().y;

      selectionRectangle.width(0);
      selectionRectangle.height(0);
      selecting = true;
    });

    stage.on('mousemove touchmove', (e: { evt: { preventDefault: () => void } }) => {
      // do nothing if we didn't start selection
      if (!selecting) {
        return;
      }
      e.evt.preventDefault();
      x2 = stage.getPointerPosition().x;
      y2 = stage.getPointerPosition().y;

      selectionRectangle.setAttrs({
        visible: true,
        x: Math.min(x1, x2),
        y: Math.min(y1, y2),
        width: Math.abs(x2 - x1),
        height: Math.abs(y2 - y1),
      });
    });

    stage.on('mouseup touchend', (e: { evt: { preventDefault: () => void } }) => {
      // do nothing if we didn't start selection
      selecting = false;
      if (!selectionRectangle.visible()) {
        return;
      }
      e.evt.preventDefault();
      // update visibility in timeout, so we can check it in click event
      selectionRectangle.visible(false);
      var shapes = stage.find('.rect');
      var box = selectionRectangle.getClientRect();
      var selected = shapes.filter((shape: { getClientRect: () => any }) =>
        Konva.Util.haveIntersection(box, shape.getClientRect())
      );
      tr.nodes(selected);
    });

    // clicks should select/deselect shapes
    stage.on('click tap', function (e: { target: { hasName: (arg0: string) => any }; evt: { shiftKey: any; ctrlKey: any; metaKey: any } }) {
      // if we are selecting with rect, do nothing
      if (selectionRectangle.visible()) {
        return;
      }

      // if click on empty area - remove all selections
      if (e.target === stage) {
        tr.nodes([]);
        return;
      }

      // do nothing if clicked NOT on our rectangles
      if (!e.target.hasName('rect')) {
        return;
      }

      // do we pressed shift or ctrl?
      const metaPressed = e.evt.shiftKey || e.evt.ctrlKey || e.evt.metaKey;
      const isSelected = tr.nodes().indexOf(e.target) >= 0;

      if (!metaPressed && !isSelected) {
        // if no key pressed and the node is not selected
        // select just one
        tr.nodes([e.target]);
      } else if (metaPressed && isSelected) {
        // if we pressed keys and node was selected
        // we need to remove it from selection:
        const nodes = tr.nodes().slice(); // use slice to have new copy of array
        // remove node from array
        nodes.splice(nodes.indexOf(e.target), 1);
        tr.nodes(nodes);
      } else if (metaPressed && !isSelected) {
        // add the node into selection
        const nodes = tr.nodes().concat([e.target]);
        tr.nodes(nodes);
      }
    });


  }, [])



  return <div id="container" ref={canvasRef}></div>

}

export default Canvas