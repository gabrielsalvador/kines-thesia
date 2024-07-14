import Konva from "../../../../../konva/konva";



export class Router extends Konva.Circle {

    isRouting = false
    startPoint: any = null
    endPoint: any = null


    constructor(config: any) {
        super(config);
        this._className = 'Router';

        this.on('mousedown touchstart', this._onMouseDown);
        this.on('mouseup touchend', this._onMouseUp);

        this.draggable(false);

    }

    _sceneFunc(context: any) {
        context.beginPath();
        context.arc(0, 0, this.radius(), 0, Math.PI * 2, false);
        context.closePath();
        context.fillStrokeShape(this);

        if(this.isRouting && this.startPoint && this.endPoint) {
        
            context.beginPath();
            //move to offset
            context.moveTo(0,0);
            context.lineTo(this.endPoint.x, this.endPoint.y);
            context.stroke();
            context.closePath();
        }
    }

    _onMouseDown(e: any) {
        this.isRouting = true;
        this.startPoint = this.getStage().getPointerPosition();
        
        // fill red
        this.fill('red');
        window.addEventListener('mouseup', this._onGlobalMouseUp);
        window.addEventListener('mousemove', (event) => this._onMouseMove(event));


    }

    
    _onMouseMove(e: any) {
        //referece to the object
        this.endPoint = this.getStage().getPointerPosition();
        this.draw();
    }

    _onGlobalMouseUp = (e: any) => {
        // Call your existing mouseup logic
        this._onMouseUp(e);
        // Remove the global event listener to clean up
        window.removeEventListener('mouseup', this._onGlobalMouseUp);
        window.removeEventListener('mousemove', this._onMouseMove);
    }

    _onMouseUp(e: any) {
        console.log('mouse up');
        this.isRouting = false;
        this.draw();
        this.fill('white');
        // Note: No need to remove the global event listener here since it's handled in _onGlobalMouseUp

        //if the end point is on a node, connect the two nodes
        if(this.endPoint) {
            var endNode = this.getStage().getIntersection(this.endPoint);
            if(endNode && endNode.getClassName() === 'Node') {
                console.log('connecting nodes');
                this.connectNodes(endNode);
            }
        }
    }


}