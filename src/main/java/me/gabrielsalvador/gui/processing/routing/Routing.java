package me.gabrielsalvador.gui.routing;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Routing {
    SetInlet[] inlets() default {};
    SetOutlet[] outlets() default {};
}