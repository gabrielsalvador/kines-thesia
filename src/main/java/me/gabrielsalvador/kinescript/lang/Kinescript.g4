grammar Kinescript;

// Parser rules

program: statement+ ;

statement: (assignment | definition | invocation | for ) ';'? ;

assignment: ID '=' expr ;

definition: ID '(' args? ')' '{' statement* '}' ;

expr: ID | INT | STRING  | invocation  | '(' expr ')' ;

invocation: ID '(' args? ')' ;

for: 'for' '(' INT 'to' INT ')' '{' statement+ '}' ;

args: arg (',' arg)*;

arg: expr ;

// Lexer rules

ID: [a-zA-Z]+ ;

STRING: '"' .*? '"' | '\'' .*? '\'' ;

INT: [0-9]+ ;

WS: [ \t\r\n]+ -> skip ; // This rule will match any number of spaces, tabs, carriage returns, and newlines and skip them
