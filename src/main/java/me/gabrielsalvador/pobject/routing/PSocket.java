package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.BodyComponent;
import me.gabrielsalvador.pobject.components.HologramBody;
import me.gabrielsalvador.pobject.views.RectangleShape;
import me.gabrielsalvador.pobject.views.PSocketView;
import me.gabrielsalvador.tools.RoutingTool;
import me.gabrielsalvador.tools.ToolManager;
import org.jbox2d.common.Vec2;

import java.io.Serial;
import java.util.ArrayList;

public class PSocket<T extends Routable> extends PObject {
    private PObject _owner;
    private Class<T> type;
    private String name;
    private ArrayList<RoutingConnection> _routings = new ArrayList<RoutingConnection>();

    public PSocket(PObject _owner, Class<T> type) {
        super();
        this._owner = _owner;

        HologramBody ownersBody = (HologramBody)_owner.getBodyComponent();
        float yOffSet = type == Inlet.class ? -30 : 10;

        BodyComponent myBody = ownersBody.createChild(new Vec2(0,yOffSet), new Vec2(PSocketView.SIZE_X, PSocketView.SIZE_X ));
        addComponent(BodyComponent.class, myBody);
        initialize();
    }

    @Override
    protected void initialize() {
        setView(new PSocketView(this));

    }

    public void setOwner(PObject owner) {
        _owner = owner;
    }

    public PObject getOwner() {
        return _owner;
    }


    public float[] getPosition() {
        BodyComponent body = (BodyComponent) getComponent(BodyComponent.class);
        Vec2 position = body.getPosition();
        float[] ownerSize = ((RectangleShape) body.getShape()).getBoundayBox();
        return new float[]{position.x, position.y + ownerSize[0] + getSize()[1]};
    }


    public float[] getSize() {
        return new float[]{PSocketView.SIZE_X, PSocketView.SIZE_X};
    }

    @Serial
    private void readObject(java.io.ObjectInputStream aInputStream) throws ClassNotFoundException, java.io.IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        initialize();

    }

    public void onPress(int x, int y) {
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

    public void addRouting(RoutingConnection r) {
        _routings.add(r);
    }

    public ArrayList<RoutingConnection> getRoutings() {
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

