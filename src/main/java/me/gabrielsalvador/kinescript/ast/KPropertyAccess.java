package me.gabrielsalvador.kinescript.ast;

import me.gabrielsalvador.pobject.HasPProperties;
import me.gabrielsalvador.pobject.PObjectProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static me.gabrielsalvador.kinescript.ast.KFunction.getScope;

public class KPropertyAccess extends KExpression {
    private KExpression obj;
    KExpression indexOrPropName;

    public KPropertyAccess(KExpression obj, KExpression indexOrPropName) {
        super(ExpressionType.PROPERTY_ACCESS);
        this.obj = obj;
        this.indexOrPropName = indexOrPropName;
    }


    @Override
    public Object evaluate(Map<String, Object> scope) {
        Object objResult = obj.evaluate(scope);
        Object propIdentifier = indexOrPropName.evaluate(scope);
        Object returnValue = null;
        
        if((propIdentifier instanceof String) ){
            if (objResult instanceof Map) {
                returnValue = ((Map<?, ?>) objResult).get(propIdentifier);
            }
            else if(objResult instanceof HasPProperties){
                ArrayList<PObjectProperty> properties = ((HasPProperties) objResult).getProperties();
                for (PObjectProperty property : properties) {
                    if (property.getName().equals(propIdentifier)) {
                        returnValue = property.getValue();
                        break;
                    }
                }


            }
            else {
                throw new RuntimeException("Property access not supported on this type of object");
            }

        }else if((propIdentifier instanceof Integer)) {
            if (objResult instanceof List) {
                List<?> list = (List<?>) objResult;
                Integer index = (Integer) propIdentifier;
                if (index < 0 || index >= list.size()) {
                    throw new RuntimeException("Index out of bounds");
                }
                returnValue = list.get(index);
            } else {
                throw new RuntimeException("Property access not supported on this type of object");
            }
        }

        if (returnValue instanceof KExpression) {
            returnValue = ((KExpression) returnValue).evaluate(scope);
        }

        return returnValue;
    }

    @Override
    public String toString() {
        return evaluate(getScope()).toString();
    }
}