
import me.gabrielsalvador.kinescript.lang.KgrammarBaseListener;
import me.gabrielsalvador.kinescript.lang.KgrammarLexer;
import me.gabrielsalvador.kinescript.lang.KgrammarParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class KCommandTests {


    @Test
    public void test(){

        String input = "midisend 1 64 127";

        //start time so we can measure how long it taKes to parse
        long startTime = System.nanoTime();

        // Create a lexer that feeds off of the input
        KgrammarLexer lexer = new KgrammarLexer(CharStreams.fromString(input));

        // Create a buffer of toKens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the token buffer
        KgrammarParser parser = new KgrammarParser(tokens);

        // Set the prediction mode to SLL
        parser.getInterpreter().setPredictionMode(PredictionMode.SLL);

        KgrammarParser.CommandsContext tree = null;

        try {
            // Try parsing in SLL mode
            tree = parser.commands();
        } catch (Exception e) {
            // If the parser fails, reset it and try again in LL mode
            tokens.reset(); // rewind input stream
            parser.reset();
            parser.getInterpreter().setPredictionMode(PredictionMode.LL);
            tree = parser.commands();

        }

        // Create a generic parse tree walker that can trigger callbacks
        ParseTreeWalker walker = new ParseTreeWalker();

        // Create a listener and connect it to the walker
        KgrammarBaseListener listener = new KgrammarBaseListener();

        walker.walk(listener, tree);

        //end time
        long endTime = System.nanoTime();

        //print the time it tooK to parse

        Duration duration = Duration.ofNanos(endTime - startTime);
        System.out.println("Time to parse: " + duration.toMillis() + "ms");


    }
}
