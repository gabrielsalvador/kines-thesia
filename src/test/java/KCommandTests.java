

import me.gabrielsalvador.kinescript.ast.KExpression;
import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.kinescript.builtins.KRandom;
import me.gabrielsalvador.kinescript.lang.Kinescript;
import me.gabrielsalvador.kinescript.lang.KinescriptLexer;
import me.gabrielsalvador.kinescript.lang.KinescriptParser;
import me.gabrielsalvador.midi.MidiManager;
import me.gabrielsalvador.utils.Stopwatch;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.time.Duration;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


@PrepareForTest(Kinescript.class)
public class KCommandTests {

    @BeforeAll
    public static void setup() {
        //init midi
        MidiManager.getInstance();
    }

    @Test
    public void test() {

        String input = """
                function(a,c,d){
                    a = 4
                    midi(1,34,127)
                    print(a)
                }
                """;

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
        assertEquals(innerFunction.getStatements().size(), 3);

        //assert 4 is printed
        //assertEquals( innerFunction.getStatements().get(1).execute(new HashMap<>()), 4);

    }


    @Test
    public void testExpr() {
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


    @Test
    public void testInvocationAsArg() {
        String input = "print(random())";
        KinescriptLexer lexer = new KinescriptLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KinescriptParser parser = new KinescriptParser(tokens);
        parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
        KinescriptParser.ProgramContext tree = parser.program();

        Kinescript kinescript = new Kinescript();

        KFunction function = (KFunction) kinescript.visitProgram(tree);

        long start = System.nanoTime();
        function.execute(new HashMap<>());
        long end = System.nanoTime();
        System.out.println("Execution time: " + Duration.ofNanos(end - start).toMillis() + "ms");

    }

    @Test
    public void testAssignment() {
        String input = "a = add(\"droplet\",0,0)";

        KinescriptLexer lexer = new KinescriptLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KinescriptParser parser = new KinescriptParser(tokens);
        parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
        KinescriptParser.ProgramContext tree = parser.program();

        // Create an instance of Kinescript
        Kinescript kinescript = new Kinescript();

        // Use the visitProgram method on the tree
        KFunction program = (KFunction) kinescript.visitProgram(tree);

        HashMap scope = new HashMap();
        long start = System.nanoTime();
//        program.execute(scope);
        long end = System.nanoTime();
        System.out.println("Execution time: " + Duration.ofNanos(end - start).toMillis() + "ms");


    }


    @Test
    public void testOperation() throws Exception {
        // Create an instance of Kinescript
        Kinescript kinescript = new Kinescript();
        KRandom mockKRandom = PowerMockito.mock(KRandom.class);
        //when mockKRandom.execute() is called, return 1
        PowerMockito.when(mockKRandom.execute(Mockito.any())).thenReturn(1);
        Kinescript.addBuiltInFunction("random", mockKRandom);

        String input = "a = random() + random() + random(); print(a)";

        KinescriptLexer lexer = new KinescriptLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KinescriptParser parser = new KinescriptParser(tokens);
        parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
        KinescriptParser.ProgramContext tree = parser.program();


        // Use the visitProgram method on the tree
        KFunction program = (KFunction) kinescript.visitProgram(tree);


        HashMap scope = new HashMap();
        long start = System.nanoTime();
        program.execute(scope);
        long end = System.nanoTime();
        System.out.println("Execution time: " + Duration.ofNanos(end - start).toMillis() + "ms");

        //assert that KRandom is called 3 times
        Mockito.verify(mockKRandom, Mockito.times(3)).execute(Mockito.any());

    }


    @Test
    public void testExprCrunch() {
        String input = "1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10";

        KinescriptLexer lexer = new KinescriptLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KinescriptParser parser = new KinescriptParser(tokens);
        parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
        KinescriptParser.ProgramContext tree = parser.program();

        // Create an instance of Kinescript
        Kinescript kinescript = new Kinescript();

        // Use the visitProgram method on the tree
        KFunction program = (KFunction) kinescript.visitProgram(tree);

        HashMap scope = new HashMap();
        long start = System.nanoTime();
        program.execute(scope);
        KExpression statement = (KExpression) program.getStatements().get(0);
        assertEquals(55.0, statement.evaluate(scope));

        long end = System.nanoTime();
        System.out.println("Execution time: " + Duration.ofNanos(end - start).toMillis() + "ms");
    }


    @Test
    public void testOperations() {
        String input = "a = 234*193/2 ";

        KinescriptLexer lexer = new KinescriptLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KinescriptParser parser = new KinescriptParser(tokens);
        parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
        KinescriptParser.ProgramContext tree = parser.program();

        Kinescript kinescript = new Kinescript();

        KFunction program = (KFunction) kinescript.visitProgram(tree);

        HashMap scope = new HashMap();
        long start = System.nanoTime();
        program.execute(scope);
        long end = System.nanoTime();
        System.out.println("Execution time: " + Duration.ofNanos(end - start).toMillis() + "ms");

        assertEquals(22581.0, scope.get("a"));
    }


    @Test
    public void testLargeProgram() {


        String input = """
                x = function(){
                    print("hello")
                    for(1 to 100 as i) {
                        b = function(arg) {
                            print('arg')
                            midi(1,i,127)
                        }
                        b(i)
                    }
                }
                x()
                """;

        MidiManager.getInstance(); //init midi

        KinescriptLexer lexer = new KinescriptLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KinescriptParser parser = new KinescriptParser(tokens);
        parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
        KinescriptParser.ProgramContext tree = parser.program();

        Kinescript kinescript = new Kinescript();
        KFunction program = (KFunction) kinescript.visitProgram(tree);

        Stopwatch.start();
        program.execute(new HashMap<>());
        long time = Stopwatch.stopAndPrint();

        assertTrue(time < 10_000_000);
    }

}
