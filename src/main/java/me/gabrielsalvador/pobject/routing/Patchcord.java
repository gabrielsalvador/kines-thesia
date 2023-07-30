package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.views.View;
import processing.core.PGraphics;

import java.io.Serial;
import java.io.Serializable;

public class Patchcord<T> extends PObject implements Serializable {
    private final Outlet<T> _outlet;
    private final Inlet<T> _inlet;

    public Patchcord(Outlet<T> outlet, Inlet<T> inlet) {
        this._outlet = outlet;
        this._inlet = inlet;
        this.setView(new PatchcordView(this));
    }
    public Outlet<T> getOutlet() {
        return this._outlet;
    }

    public Inlet<T> getInlet() {
        return this._inlet;
    }

    @Serial
    private void readObject(java.io.ObjectInputStream aInputStream) throws ClassNotFoundException, java.io.IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        this.setView(new PatchcordView(this));
    }

    public void send(T message) {
        this._inlet.receive(message);
    }

    class PatchcordView implements View<PObject> {

        private Patchcord _model;
        public PatchcordView(Patchcord pObject) {
            super();
            _model = pObject;

        }

        @Override
        public PObject getModel() {
            return null;
        }

        @Override
        public void display(PGraphics graphics) {
            //line from outlet to inlet
            PObject inlet = (PObject) _model._inlet;
            PObject outlet = (PObject) _model._outlet;

            graphics.line(
                    outlet.getPosition()[0],
                    outlet.getPosition()[1],
                    inlet.getPosition()[0],
                    inlet.getPosition()[1]
            );



        }

        @Override
        public boolean isMouseOver(int mouseX, int mouseY) {
            return false;
        }
    }
}
