package me.gabrielsalvador.pobject.routing;

public interface Inlet<T> {
    void receive(T message);
    PatchSocket getPatchSocket();
    void setPatchSocket(PatchSocket patchSocket);
}
