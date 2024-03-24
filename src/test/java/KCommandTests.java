

import me.gabrielsalvador.kinescript.ast.KExpression;
import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.kinescript.lang.Kinescript;
import me.gabrielsalvador.kinescript.lang.KinescriptLexer;
import me.gabrielsalvador.kinescript.lang.KinescriptParser;
import me.gabrielsalvador.midi.MidiManager;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KCommandTests {

    @BeforeAll
    public static void setup() {
        //init midi
        MidiManager.getInstance();
    }

    @Test
    public void test(){
        String input = "main(a,c,d){\n" +
                "a = 4\n" +
                "midi(1,34,127)" +
                "print(a)\n" +
                "}";

        KinescriptLexer lexer = new KinescriptLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KinescriptParser parser = new KinescriptParser(tokens);
        parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
        KinescriptParser.ProgramContext tree = parser.program();

        // Create an instance of Kinescript
        Kinescript kinescript = new Kinescript();

        // Use the visitProgram method on the tree
        KFunction function = (KFunction) kinescript.visitProgram(tree);

        long start = System.nanoTime();
        function.execute(new HashMap<>());
        long end = System.nanoTime();
        System.out.println("Execution time: " + Duration.ofNanos(end - start).toMillis() + "ms");

        assertNotNull(function);

        KFunction innerFunction = (KFunction) function.getStatements().get(0);
        assertEquals(  innerFunction.getStatements().size(), 3);

        //assert 4 is printed
        //assertEquals( innerFunction.getStatements().get(1).execute(new HashMap<>()), 4);

    }


    @Test
    public void testExpr(){
        String input = "random()";
        KinescriptLexer lexer = new KinescriptLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KinescriptParser parser = new KinescriptParser(tokens);
        parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
        KinescriptParser.ExprContext tree = parser.expr();


        Kinescript kinescript = new Kinescript();

        KExpression expression = (KExpression) kinescript.visitExpr(tree);
        int result = (int) expression.evaluate(new HashMap<>());

        System.out.println(result);


    }

}
