grammar Kinescript;

// Parser rules

program: statement+ ;

statement: (assignment | definition | invocation | for ) ';'? ;

assignment: ID '=' expr ;

definition: ID '(' args? ')' '{' statement* '}' ;

invocation: ID '(' args? ')' ;


expr: ID | STRING  | invocation  | '(' expr ')' ;

for: 'for' '(' INT 'to' INT ')' '{' statement+ '}' ;

args: arg (' ' arg)*;

arg: ID | STRING | INT ;


// Lexer rules

ID: [a-zA-Z]+ ;

STRING: '"' .*? '"' ;

INT: [0-9]+ ;

WS: [ \t\r\n]+ -> skip ; // This rule will match any number of spaces, tabs, carriage returns, and newlines and skip them
