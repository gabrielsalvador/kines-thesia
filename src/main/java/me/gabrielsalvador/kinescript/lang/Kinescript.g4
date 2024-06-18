grammar Kinescript;

// Parser rules

program: statement+ ;

statement: (assignment | definition | invocation | expr | for ) ';'? ;

assignment: ID '=' expr
          | ID '=' definition;

definition: 'function' '(' args? ')' '{' statement* '}' ;

expr
    :   expr STAR expr
    |   expr PLUS expr
    |   expr MINUS expr
    |   expr DIV expr
    |   INT
    |   ID
    |   STRING
    |   invocation
    |   '(' expr ')'
    |   '-' expr
    |   range

    ;

range : INT 'to' INT
      | NOTE 'to' NOTE ;


NOTE: [A-G]('#'|'b')?[0-9]? ;


STAR: '*' ;
PLUS: '+' ;
MINUS: '-' ;
DIV: '/' ;


invocation: ID '(' args? ')' ;

for: 'for' '(' INT 'to' INT ('as' ID)? ')' '{' statement+ '}' ;

args: arg (',' arg)*;

arg: expr ;

// Lexer rules

OPERATOR: '+' | '-' | '*' | '/' ;

ID: [a-zA-Z]+ ;

STRING: '"' .*? '"' | '\'' .*? '\'' ;

INT: [0-9]+ ;

WS: [ \t\r\n]+ -> skip ; // This rule will match any number of spaces, tabs, carriage returns, and newlines and skip them
