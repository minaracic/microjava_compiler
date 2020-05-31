
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext());}
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"break"     { return new_symbol(sym.BREAK, yytext());}
"class" 	{ return new_symbol(sym.CLASS, yytext()); }
"abstract" 	{ return new_symbol(sym.ABSTRACT, yytext()); }
"else" 		{ return new_symbol(sym.ELSE, yytext()); }
"const"     { return new_symbol(sym.CONST, yytext());}
"if" 	    { return new_symbol(sym.IF, yytext()); }
"new" 	    { return new_symbol(sym.NEW, yytext()); }
"read" 		{ return new_symbol(sym.READ, yytext()); }
"for"       { return new_symbol(sym.FOR, yytext());}
"extends" 	{ return new_symbol(sym.EXTENDS, yytext()); }
"continue" 	{ return new_symbol(sym.CONTINUE, yytext()); }


"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPARENT, yytext()); }
")" 		{ return new_symbol(sym.RPARENT, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIV, yytext()); }
"%" 		{ return new_symbol(sym.MODULE, yytext()); }
"==" 		{ return new_symbol(sym.ISEQUAL, yytext()); }
"!=" 		{ return new_symbol(sym.ISNOTEQUAL, yytext()); }
">" 		{ return new_symbol(sym.GRE, yytext()); }
">="		{ return new_symbol(sym.GREEQUAL, yytext()); }
"<" 		{ return new_symbol(sym.SML, yytext()); }
"<=" 		{ return new_symbol(sym.SMLEQUAL, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }
"++" 		{ return new_symbol(sym.PLUSPLUS, yytext()); }
"--" 		{ return new_symbol(sym.MINUSMINUS, yytext()); }
"."			{ return new_symbol(sym.DOT, yytext()); }
"[" 		{ return new_symbol(sym.LBRACKET, yytext()); }
"]"			{ return new_symbol(sym.RBRACKET, yytext()); }
"+="		{ return new_symbol(sym.PLUSEQ, yytext()); }
"-="		{ return new_symbol(sym.MINUSEQ, yytext()); }
"*="		{ return new_symbol(sym.MULEQ, yytext()); }
"/="		{ return new_symbol(sym.DIVEQ, yytext()); }
"%="		{ return new_symbol(sym.MODEQ, yytext()); }

<YYINITIAL> "//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

true	{return new_symbol (sym.BOOLCONST, new Boolean(true)); }
false	{return new_symbol (sym.BOOLCONST, new Boolean(false)); }
'.' { return new_symbol(sym.CHARCONST, new Character(yytext().charAt(1))); } 
[0-9]+  { return new_symbol(sym.NUMCONST, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }


. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }











