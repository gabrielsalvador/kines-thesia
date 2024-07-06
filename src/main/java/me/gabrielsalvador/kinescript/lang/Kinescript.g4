grammar Kinescript;

// Parser rules

program: statement+ ;

statement: (assignment | definition | invocation | expr | for ) ';'? ;

assignment: ID '=' expr
          | ID '=' definition;

definition: 'function' '(' args? ')' '{' statement* '}' ;

expr
    :   expr STAR expr                          # OperationExpression
    |   expr PLUS expr                          # OperationExpression
    |   expr MINUS expr                         # OperationExpression
    |   expr DIV expr                           # OperationExpression
    |   INT                                     # IntExpression
    |   ID                                      # IdExpression
    |   STRING                                  # StringExpression
    |   expr '.' ID                             # PropertyDotExpression
    |   expr '[' expr ']'                       # PropertyIndexExpression
    |   invocation                              # InvocationExpression
    |   '(' expr ')'                            # ParenExpression
    |   '-' expr                                # NegateExpression
    |   range                                   # RangeExpression
    |   '{' keyValuePair? (',' keyValuePair)* '}'                   # ObjectExpression
    ;

keyValuePair: ID ':' expr ;




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
