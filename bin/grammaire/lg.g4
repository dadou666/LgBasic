grammar lg;
module : ( element   )*;

element : type | fonction ;
type  :   'type'   ID  ( superType | )  '{' champs  '}' ;

superType :      (':' (ID | id_externe ) )  ;
champs : champ *;
champ : typeRef ':' ID ;
typeRef : (ID |id_externe );
attribut : ID '='  tmpCode ;
attributs : attribut * ;
code :(  ('(' code ')' ) |  (appel| objet | var  | ('(' (  si) ')' ) ) ) operationOuAcces *    ;
var :  (ID |id_externe);
appel :  ( ID | id_externe )   '(' tmpCode * ')' ;
objet :  ( ID| id_externe ) '{' attributs  '}';
id_externe : ID '$' ID;
acces :  '.' ID ;
operation : operateur tmpCode;
operationOuAcces :  (acces |operation )  ;
operateur : '->' |'=>' | '+' | '-' |'*' |'/'|'>' | '<'  | '&' |'|' |'=' ;
testType : code 'is' negation typeRef    ;
testEgalite : code '==' code;
testDifference : code '!=' code;
si :  'if' (testType | testEgalite |testDifference) 'then' code 'else' ( si |code );
negation : '!' | ;  
fonction : 'fonction'   (ID|operateur)  champs '|' tmpCode;

tmpCode :  appel | si  |code | '(' tmpCode ')'  ;


    // match keyword hello followed by an identifier
ENTIER : [1-9][0-9]*ID('$'ID|);
ENTIER_EXTERNE : [1-9][0-9]*ID('$'ID|);
ID : [a-zA-Z_][a-zA-Z0-9_]*  ;             // match lower-case identifiers

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines