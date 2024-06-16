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



    private transient Map<String, Object> scope = new HashMap<>();
    private transient List<KStatement> statements = new ArrayList<>();
    private String sourceCode;

    public KFunction( int parameterNumber, List<KStatement> statements) {


        this.statements = statements;

    }


    @Override
    public Object execute(Map<String, Object> parentScope) {

        scope.putAll(parentScope);

        for (KStatement statement : statements) {
            statement.execute(scope);
        }

        return null;
    }

    public Map<String, Object> getScope() {
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
