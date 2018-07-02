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
code :(  ('(' code ')' ) |  (appel| objet | var  | ('(' (  si) ')' ) ) ) (operationOuAcces | )    ;
var :  ID ;
appel :  ( ID | id_externe )   '(' tmpCode * ')' ;
objet :  ( ID| id_externe ) '{' attributs  '}';
id_externe : ID '$' ID;
acces :  '.' ID ;
operation : operateur tmpCode;
operationOuAcces :  (operation  | acces)  ;
operateur : '->' |'=>' | '+' | '-' |'*' |'/'|'>' | '<'  | '&' |'|' |'=' ;
testType : code 'est' negation typeRef    ;
testEgalite : code '==' code;
testDifference : code '<>' code;
si :  'si' (testType | testEgalite |testDifference) 'alors' code 'sinon' ( si |code );
negation : '!' | ;  
fonction : 'fonction'   (ID|operateur)  champs '|' tmpCode;

tmpCode :     literal|appel |si  |code |  '(' tmpCode ')'  ;
ref: (ID| id_externe);
literal :'[' ref ref* ']';


ID : [a-zA-Z0-9_][a-zA-Z0-9_]*  ;             // match lower-case identifiers

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines