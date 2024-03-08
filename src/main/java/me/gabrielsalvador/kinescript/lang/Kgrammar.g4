grammar Kgrammar;

// Parser rules

commands: (command) ;

command: name ' '+ args ;

name: ID ;

args: arg (' ' arg)*;

arg: ID | STRING | NUMBER ;


// Lexer rules

ID: [a-zA-Z]+ ;

STRING: '"' .*? '"' ;

NUMBER: [0-9]+ ;

WS: [/s]+ -> skip ;
