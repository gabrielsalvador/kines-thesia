grammar Kinescript;

// Parser rules

program: command ('\n' command)* WS* EOF;

command: name ' '+ args
        | '(' command ')' ;

name: ID ;

args: arg (' ' arg)*;

arg: ID | STRING | NUMBER ;


// Lexer rules

ID: [a-zA-Z]+ ;

STRING: '"' .*? '"' ;

NUMBER: [0-9]+ ;

WS: [/s\n]+ -> skip ;
