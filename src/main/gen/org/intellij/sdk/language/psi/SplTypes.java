// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.intellij.sdk.language.psi.impl.*;

public interface SplTypes {

  IElementType ARGLIST = new SplElementType("ARGLIST");
  IElementType ARR = new SplElementType("ARR");
  IElementType DIM = new SplElementType("DIM");
  IElementType EXPR = new SplElementType("EXPR");
  IElementType EXPRLIST = new SplElementType("EXPRLIST");
  IElementType PARAM = new SplElementType("PARAM");
  IElementType PARAMLIST = new SplElementType("PARAMLIST");
  IElementType PROCDECL = new SplElementType("PROCDECL");
  IElementType PROCNAME = new SplElementType("PROCNAME");
  IElementType PROCUSAGE = new SplElementType("PROCUSAGE");
  IElementType STATEMENT = new SplElementType("STATEMENT");
  IElementType STATEMENTLIST = new SplElementType("STATEMENTLIST");
  IElementType TYPEDECL = new SplElementType("TYPEDECL");
  IElementType TYPENAME = new SplElementType("TYPENAME");
  IElementType TYPEUSAGE = new SplElementType("TYPEUSAGE");
  IElementType UMINUS = new SplElementType("UMINUS");
  IElementType VARDECL = new SplElementType("VARDECL");
  IElementType VARDECLLIST = new SplElementType("VARDECLLIST");
  IElementType VARNAME = new SplElementType("VARNAME");
  IElementType VARUSAGE = new SplElementType("VARUSAGE");

  IElementType ARRAY = new SplTokenType("ARRAY");
  IElementType ASGN = new SplTokenType("ASGN");
  IElementType COLON = new SplTokenType("COLON");
  IElementType COMMA = new SplTokenType("COMMA");
  IElementType COMMENT = new SplTokenType("COMMENT");
  IElementType ELSE = new SplTokenType("ELSE");
  IElementType EQ = new SplTokenType("EQ");
  IElementType GE = new SplTokenType("GE");
  IElementType GT = new SplTokenType("GT");
  IElementType IDENT = new SplTokenType("IDENT");
  IElementType IF = new SplTokenType("IF");
  IElementType INTLIT = new SplTokenType("INTLIT");
  IElementType LBRACK = new SplTokenType("LBRACK");
  IElementType LCURL = new SplTokenType("LCURL");
  IElementType LE = new SplTokenType("LE");
  IElementType LPAREN = new SplTokenType("LPAREN");
  IElementType LT = new SplTokenType("LT");
  IElementType MINUS = new SplTokenType("MINUS");
  IElementType NE = new SplTokenType("NE");
  IElementType OF = new SplTokenType("OF");
  IElementType PLUS = new SplTokenType("PLUS");
  IElementType PROC = new SplTokenType("PROC");
  IElementType RBRACK = new SplTokenType("RBRACK");
  IElementType RCURL = new SplTokenType("RCURL");
  IElementType REF = new SplTokenType("REF");
  IElementType RPAREN = new SplTokenType("RPAREN");
  IElementType SEMIC = new SplTokenType("SEMIC");
  IElementType SLASH = new SplTokenType("SLASH");
  IElementType STAR = new SplTokenType("STAR");
  IElementType TYPE = new SplTokenType("TYPE");
  IElementType VAR = new SplTokenType("VAR");
  IElementType WHILE = new SplTokenType("WHILE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARGLIST) {
        return new SplArglistImpl(node);
      }
      else if (type == ARR) {
        return new SplArrImpl(node);
      }
      else if (type == DIM) {
        return new SplDimImpl(node);
      }
      else if (type == EXPR) {
        return new SplExprImpl(node);
      }
      else if (type == EXPRLIST) {
        return new SplExprlistImpl(node);
      }
      else if (type == PARAM) {
        return new SplParamImpl(node);
      }
      else if (type == PARAMLIST) {
        return new SplParamlistImpl(node);
      }
      else if (type == PROCDECL) {
        return new SplProcdeclImpl(node);
      }
      else if (type == PROCNAME) {
        return new SplProcnameImpl(node);
      }
      else if (type == PROCUSAGE) {
        return new SplProcusageImpl(node);
      }
      else if (type == STATEMENT) {
        return new SplStatementImpl(node);
      }
      else if (type == STATEMENTLIST) {
        return new SplStatementlistImpl(node);
      }
      else if (type == TYPEDECL) {
        return new SplTypedeclImpl(node);
      }
      else if (type == TYPENAME) {
        return new SplTypenameImpl(node);
      }
      else if (type == TYPEUSAGE) {
        return new SplTypeusageImpl(node);
      }
      else if (type == UMINUS) {
        return new SplUminusImpl(node);
      }
      else if (type == VARDECL) {
        return new SplVardeclImpl(node);
      }
      else if (type == VARDECLLIST) {
        return new SplVardecllistImpl(node);
      }
      else if (type == VARNAME) {
        return new SplVarnameImpl(node);
      }
      else if (type == VARUSAGE) {
        return new SplVarusageImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
