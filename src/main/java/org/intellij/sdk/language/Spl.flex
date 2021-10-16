package org.intellij.sdk.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.psi.SplTypes;
import com.intellij.psi.TokenType;

%%

%class SplLexer
%public
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}


%%

[ \t\n]+ {
          return TokenType.WHITE_SPACE;
        }


\/\/.*		{
		  return SplTypes.COMMENT;
		}

array		{
		  return SplTypes.ARRAY;
		}
		
if      {
          return SplTypes.IF;
        }
    
else    {
          return SplTypes.ELSE;
        }
        
of      {
          return SplTypes.OF;
        }
        
proc    {
          return SplTypes.PROC;
        }

ref     {
          return SplTypes.REF;
        }

type    {
          return SplTypes.TYPE;
        }

var     {
          return SplTypes.VAR;
        }

while   {
          return SplTypes.WHILE;
        }

\(      {
          return SplTypes.LPAREN;
        }

\)      {
          return SplTypes.RPAREN;
        }

\[      {
          return SplTypes.LBRACK;
        }

\]      {
          return SplTypes.RBRACK;
        }

\{		{
		  return SplTypes.LCURL;
		}

\}		{
		  return SplTypes.RCURL;
		}

\=      {
          return SplTypes.EQ;
        }

\#      {
          return SplTypes.NE;
        }

\<      {
          return SplTypes.LT;
        }

\<\=     {
          return SplTypes.LE;
        }

\>      {
          return SplTypes.GT;
        }

\>\=     {
          return SplTypes.GE;
        }

\:\=    {
          return SplTypes.ASGN;
        }

\:      {
          return SplTypes.COLON;
        }

\,      {
          return SplTypes.COMMA;
        }

\;      {
          return SplTypes.SEMIC;
        }

\+      {
          return SplTypes.PLUS;
        }

\-      {
          return SplTypes.MINUS;
        }

\*      {
          return SplTypes.STAR;
        }

\/      {
          return SplTypes.SLASH;
        }

[_a-zA-Z][_a-zA-Z0-9]*    {
          return SplTypes.IDENT;
        }

[0-9]+	{
		  return SplTypes.INTLIT;
		}

(0x[0-9a-fA-F]+)    {
          return SplTypes.INTLIT;
        }

(\'.\') {
          return SplTypes.INTLIT;
        }

(\'\\n\')   {
          return SplTypes.INTLIT;
        }

\'		{
		  throw new RuntimeException(
		    "illegal use of apostrophe"
		  );
		}

.		{
		  throw new RuntimeException(
		    "illegal character 0x" +
		    Integer.toString((int) yytext().charAt(0), 16)
		  );
		}


