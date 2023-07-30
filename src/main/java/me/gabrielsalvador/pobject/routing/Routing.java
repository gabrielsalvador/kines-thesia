package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.pobject.routing.SetInlet;
import me.gabrielsalvador.pobject.routing.SetOutlet;

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