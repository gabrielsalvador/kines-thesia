package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.tools.RoutingTool;
import me.gabrielsalvador.tools.ToolManager;

import java.io.Serial;
import java.util.ArrayList;

public class PSocket<T extends Routable> extends PObject{
    private PObject _owner;
    private Class<T> type;
    private String name;
    private  ArrayList<RoutingConnection> _routings = new ArrayList<RoutingConnection>();

    public PSocket(PObject _owner) {
        super();
        this._owner = _owner;
        setView(new RoutingSocketView(this));
    }

    public void setOwner(PObject owner) {
        _owner = owner;
    }
    public PObject getOwner() {
        return _owner;
    }

    @Override
    public float[] getPosition() {
        float[] position = _owner.getPosition();
        float[] ownerSize = _owner.getSize();
        return new float[]{position[0], position[1] + ownerSize[1] + getSize()[1] };
    }

    @Override
    public float[] getSize() {
        return new float[]{RoutingSocketView.SIZE_X, RoutingSocketView.SIZE_X};
    }

    @Serial
    private void readObject(java.io.ObjectInputStream aInputStream) throws ClassNotFoundException, java.io.IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        setView(new RoutingSocketView(this));

    }

    public void onPress(int x, int y){
        ArrayList<PObject> objects = AppState.getInstance().getPObjects();
        for (PObject object : objects) {
            if (!(object instanceof PSocket)) continue;

            PSocket<?> socket = (PSocket<?>) object;
            if (!socket.getView().isMouseOver(x, y)) continue;
            if (socket.getOwner() == this.getOwner()) continue;
            if (!(socket.getOwner() instanceof Routable)) continue;

            if (socket.getOwner() instanceof Inlet && this.getOwner() instanceof Outlet) {
                createAndAddConnection(this, socket);
                return;
            } else if (socket.getOwner() instanceof Outlet && this.getOwner() instanceof Inlet) {
                createAndAddConnection(socket, this);
                return;
            }
        }

    }

    private void createAndAddConnection(PSocket<?> source, PSocket<?> destination) {
        RoutingConnection connection = new RoutingConnection(source, destination);
        AppState.getInstance().addPObject(connection);
    }
    public   void addRouting(RoutingConnection r){
        _routings.add(r);
    }

    public  ArrayList<RoutingConnection> getRoutings(){
        return _routings;
    }
    @Override
    public void onEnter(int x, int y) {
        ToolManager.getInstance().pushTool(RoutingTool.class);
    }

    @Override
    public void onLeave(int x, int y) {
        //if not in the middle of a connection
        if (ToolManager.getInstance().getCurrentTool() instanceof RoutingTool routingTool) {
            if (!routingTool.isRouting()) {
                ToolManager.getInstance().popTool();
            }
        }
    }
}

