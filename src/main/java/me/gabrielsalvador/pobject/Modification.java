package me.gabrielsalvador.pobject;

public class Modification<T extends PObject> {
    private final Class<T> type;

    public Modification(Class<T> type) {
        this.type = type;
    }

    public boolean isAddition = true;

    public T apply() {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create an instance of " + type.getName(), e);
        }
    }
}
