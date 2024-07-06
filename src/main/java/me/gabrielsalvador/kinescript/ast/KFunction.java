package me.gabrielsalvador.kinescript.ast;

import me.gabrielsalvador.kinescript.lang.Kinescript;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public class KFunction implements KStatement, Serializable {



    private static HashMap<String, Object> scope = new HashMap<>();
    {scope.put("scope", scope);}
    private transient List<KStatement> statements = new ArrayList<>();
    private String sourceCode;

    public KFunction( int parameterNumber, List<KStatement> statements) {
        this.statements = statements;
    }


    @Override
    public Object execute(Map<String, Object> parentScope) {

        if(scope == null) scope = new HashMap<>();
        parentScope.putAll(scope);

        for (KStatement statement : statements) {
            Object result = statement.execute(parentScope);
            if(result != null){
                System.out.println(result);
            }
        }

        return null;
    }


    public Object execute() {
        return execute(scope);
    }

    static public Map<String, Object> getScope() {
        return scope;
    }



    public List<KStatement> getStatements() {
        return new ArrayList<>(statements);
    }

    public String getSourceCode() {
        return sourceCode;
    }
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }




    @Serial private void writeObject(java.io.ObjectOutputStream out)
            throws java.io.IOException {
        out.writeUTF(sourceCode);
    }

   @Serial private void readObject(java.io.ObjectInputStream in)  throws java.io.IOException, ClassNotFoundException {
        sourceCode = in.readUTF();
        statements = Kinescript.compileFunction(sourceCode).getStatements();
    }
}
