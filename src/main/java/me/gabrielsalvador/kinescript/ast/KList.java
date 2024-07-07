package me.gabrielsalvador.kinescript.ast;

import java.util.ArrayList;
import java.util.List;

public class KList extends ArrayList<Object> {

    public KList(ArrayList<?> objects) {
        super(objects);
    }
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("List:\n{\n");
        for (Object object : this) {
            builder.append("    ").append(object).append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
