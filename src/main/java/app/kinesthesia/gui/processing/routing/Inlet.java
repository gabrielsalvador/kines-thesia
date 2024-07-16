package app.kinesthesia.gui.processing.routing;

public interface Inlet extends Routable{
    void receive(Object message);
}
