
import me.gabrielsalvador.kinescript.KgrammarBaseListener;
import me.gabrielsalvador.kinescript.KgrammarLexer;
import me.gabrielsalvador.kinescript.KgrammarParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
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

        // Create a parser that feeds off the toKen buffer
        KgrammarParser parser = new KgrammarParser(tokens);

        // Begin parsing at the 'commands' rule
        KgrammarParser.CommandsContext tree = parser.commands();

        // Create a generic parse tree walKer that can trigger callbacKs
        ParseTreeWalker walKer = new ParseTreeWalker();

        // Create a listener and connect it to the walKer
        KgrammarBaseListener listener = new KgrammarBaseListener();

        walKer.walk(listener, tree);

        //end time
        long endTime = System.nanoTime();

        //print the time it tooK to parse

        Duration duration = Duration.ofNanos(endTime - startTime);
        System.out.println("Time to parse: " + duration.toMillis() + "ms");


    }
}
