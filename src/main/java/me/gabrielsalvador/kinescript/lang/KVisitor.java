package me.gabrielsalvador.kinescript.lang;

import me.gabrielsalvador.kinescript.KCommand;
import me.gabrielsalvador.kinescript.KCommandFactory;

public class KVisitor extends KgrammarBaseListener{

    @Override
    public void enterCommand(KgrammarParser.CommandContext ctx) {
        super.enterCommand(ctx);

        String command = ctx.getText();

        KCommand kCommand = KCommandFactory.getCommand(command);

    }
}
