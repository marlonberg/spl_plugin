// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.intellij.sdk.language.psi.SplTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SplParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return splFile(b, l + 1);
  }

  /* ********************************************************** */
  // exprlist (COMMA arglist)* COMMENT*
  public static boolean arglist(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arglist")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARGLIST, "<arglist>");
    r = exprlist(b, l + 1);
    r = r && arglist_1(b, l + 1);
    r = r && arglist_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA arglist)*
  private static boolean arglist_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arglist_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arglist_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arglist_1", c)) break;
    }
    return true;
  }

  // COMMA arglist
  private static boolean arglist_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arglist_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && arglist(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT*
  private static boolean arglist_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arglist_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "arglist_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ARRAY LBRACK INTLIT RBRACK OF arr | ARRAY LBRACK INTLIT RBRACK OF typeusage
  public static boolean arr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arr")) return false;
    if (!nextTokenIs(b, ARRAY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = arr_0(b, l + 1);
    if (!r) r = arr_1(b, l + 1);
    exit_section_(b, m, ARR, r);
    return r;
  }

  // ARRAY LBRACK INTLIT RBRACK OF arr
  private static boolean arr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ARRAY, LBRACK, INTLIT, RBRACK, OF);
    r = r && arr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ARRAY LBRACK INTLIT RBRACK OF typeusage
  private static boolean arr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ARRAY, LBRACK, INTLIT, RBRACK, OF);
    r = r && typeusage(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACK exprlist RBRACK dim?
  public static boolean dim(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dim")) return false;
    if (!nextTokenIs(b, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACK);
    r = r && exprlist(b, l + 1);
    r = r && consumeToken(b, RBRACK);
    r = r && dim_3(b, l + 1);
    exit_section_(b, m, DIM, r);
    return r;
  }

  // dim?
  private static boolean dim_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dim_3")) return false;
    dim(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (INTLIT | varusage dim?) ((PLUS|MINUS|STAR|SLASH|EQ|NE|LT|LE|GT|GE) expr)? | LPAREN expr RPAREN | uminus expr
  public static boolean expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, EXPR, "<expr>");
    r = expr_0(b, l + 1);
    if (!r) r = expr_1(b, l + 1);
    if (!r) r = expr_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (INTLIT | varusage dim?) ((PLUS|MINUS|STAR|SLASH|EQ|NE|LT|LE|GT|GE) expr)?
  private static boolean expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr_0_0(b, l + 1);
    r = r && expr_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INTLIT | varusage dim?
  private static boolean expr_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INTLIT);
    if (!r) r = expr_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // varusage dim?
  private static boolean expr_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varusage(b, l + 1);
    r = r && expr_0_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // dim?
  private static boolean expr_0_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_0_0_1_1")) return false;
    dim(b, l + 1);
    return true;
  }

  // ((PLUS|MINUS|STAR|SLASH|EQ|NE|LT|LE|GT|GE) expr)?
  private static boolean expr_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_0_1")) return false;
    expr_0_1_0(b, l + 1);
    return true;
  }

  // (PLUS|MINUS|STAR|SLASH|EQ|NE|LT|LE|GT|GE) expr
  private static boolean expr_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr_0_1_0_0(b, l + 1);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS|MINUS|STAR|SLASH|EQ|NE|LT|LE|GT|GE
  private static boolean expr_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_0_1_0_0")) return false;
    boolean r;
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, STAR);
    if (!r) r = consumeToken(b, SLASH);
    if (!r) r = consumeToken(b, EQ);
    if (!r) r = consumeToken(b, NE);
    if (!r) r = consumeToken(b, LT);
    if (!r) r = consumeToken(b, LE);
    if (!r) r = consumeToken(b, GT);
    if (!r) r = consumeToken(b, GE);
    return r;
  }

  // LPAREN expr RPAREN
  private static boolean expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // uminus expr
  private static boolean expr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = uminus(b, l + 1);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // expr (COMMA expr)* COMMENT*
  public static boolean exprlist(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exprlist")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRLIST, "<exprlist>");
    r = expr(b, l + 1);
    r = r && exprlist_1(b, l + 1);
    r = r && exprlist_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA expr)*
  private static boolean exprlist_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exprlist_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!exprlist_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "exprlist_1", c)) break;
    }
    return true;
  }

  // COMMA expr
  private static boolean exprlist_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exprlist_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT*
  private static boolean exprlist_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exprlist_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "exprlist_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // REF varname COLON typeusage
  //                 |   varname COLON typeusage
  public static boolean param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param")) return false;
    if (!nextTokenIs(b, "<param>", IDENT, REF)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAM, "<param>");
    r = param_0(b, l + 1);
    if (!r) r = param_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // REF varname COLON typeusage
  private static boolean param_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REF);
    r = r && varname(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && typeusage(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // varname COLON typeusage
  private static boolean param_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varname(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && typeusage(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // param (param| COMMA param)* COMMENT*
  public static boolean paramlist(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paramlist")) return false;
    if (!nextTokenIs(b, "<paramlist>", IDENT, REF)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAMLIST, "<paramlist>");
    r = param(b, l + 1);
    r = r && paramlist_1(b, l + 1);
    r = r && paramlist_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (param| COMMA param)*
  private static boolean paramlist_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paramlist_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!paramlist_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "paramlist_1", c)) break;
    }
    return true;
  }

  // param| COMMA param
  private static boolean paramlist_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paramlist_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = param(b, l + 1);
    if (!r) r = paramlist_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA param
  private static boolean paramlist_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paramlist_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && param(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT*
  private static boolean paramlist_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paramlist_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "paramlist_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // PROC procname LPAREN paramlist? RPAREN LCURL vardecllist? statementlist? RCURL
  public static boolean procdecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procdecl")) return false;
    if (!nextTokenIs(b, PROC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PROC);
    r = r && procname(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && procdecl_3(b, l + 1);
    r = r && consumeTokens(b, 0, RPAREN, LCURL);
    r = r && procdecl_6(b, l + 1);
    r = r && procdecl_7(b, l + 1);
    r = r && consumeToken(b, RCURL);
    exit_section_(b, m, PROCDECL, r);
    return r;
  }

  // paramlist?
  private static boolean procdecl_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procdecl_3")) return false;
    paramlist(b, l + 1);
    return true;
  }

  // vardecllist?
  private static boolean procdecl_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procdecl_6")) return false;
    vardecllist(b, l + 1);
    return true;
  }

  // statementlist?
  private static boolean procdecl_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procdecl_7")) return false;
    statementlist(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENT
  public static boolean procname(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procname")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENT);
    exit_section_(b, m, PROCNAME, r);
    return r;
  }

  /* ********************************************************** */
  // IDENT
  public static boolean procusage(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procusage")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENT);
    exit_section_(b, m, PROCUSAGE, r);
    return r;
  }

  /* ********************************************************** */
  // (COMMENT|typedecl|procdecl)+
  static boolean splFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "splFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = splFile_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!splFile_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "splFile", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT|typedecl|procdecl
  private static boolean splFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "splFile_0")) return false;
    boolean r;
    r = consumeToken(b, COMMENT);
    if (!r) r = typedecl(b, l + 1);
    if (!r) r = procdecl(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // SEMIC
  //                 |   varusage ASGN exprlist SEMIC
  //                 |   varusage dim ASGN exprlist SEMIC
  //                 |   IF LPAREN exprlist RPAREN statement (ELSE statement)?
  //                 |   WHILE LPAREN exprlist RPAREN statement
  //                 |   procusage LPAREN RPAREN SEMIC
  //                 |   procusage LPAREN arglist RPAREN SEMIC
  //                 |   LCURL statementlist RCURL
  public static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = consumeToken(b, SEMIC);
    if (!r) r = statement_1(b, l + 1);
    if (!r) r = statement_2(b, l + 1);
    if (!r) r = statement_3(b, l + 1);
    if (!r) r = statement_4(b, l + 1);
    if (!r) r = statement_5(b, l + 1);
    if (!r) r = statement_6(b, l + 1);
    if (!r) r = statement_7(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // varusage ASGN exprlist SEMIC
  private static boolean statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varusage(b, l + 1);
    r = r && consumeToken(b, ASGN);
    r = r && exprlist(b, l + 1);
    r = r && consumeToken(b, SEMIC);
    exit_section_(b, m, null, r);
    return r;
  }

  // varusage dim ASGN exprlist SEMIC
  private static boolean statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varusage(b, l + 1);
    r = r && dim(b, l + 1);
    r = r && consumeToken(b, ASGN);
    r = r && exprlist(b, l + 1);
    r = r && consumeToken(b, SEMIC);
    exit_section_(b, m, null, r);
    return r;
  }

  // IF LPAREN exprlist RPAREN statement (ELSE statement)?
  private static boolean statement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IF, LPAREN);
    r = r && exprlist(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && statement(b, l + 1);
    r = r && statement_3_5(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ELSE statement)?
  private static boolean statement_3_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_3_5")) return false;
    statement_3_5_0(b, l + 1);
    return true;
  }

  // ELSE statement
  private static boolean statement_3_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_3_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE);
    r = r && statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHILE LPAREN exprlist RPAREN statement
  private static boolean statement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, WHILE, LPAREN);
    r = r && exprlist(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // procusage LPAREN RPAREN SEMIC
  private static boolean statement_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = procusage(b, l + 1);
    r = r && consumeTokens(b, 0, LPAREN, RPAREN, SEMIC);
    exit_section_(b, m, null, r);
    return r;
  }

  // procusage LPAREN arglist RPAREN SEMIC
  private static boolean statement_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = procusage(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && arglist(b, l + 1);
    r = r && consumeTokens(b, 0, RPAREN, SEMIC);
    exit_section_(b, m, null, r);
    return r;
  }

  // LCURL statementlist RCURL
  private static boolean statement_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LCURL);
    r = r && statementlist(b, l + 1);
    r = r && consumeToken(b, RCURL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (COMMENT* statement COMMENT*)+
  public static boolean statementlist(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statementlist")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENTLIST, "<statementlist>");
    r = statementlist_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!statementlist_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "statementlist", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // COMMENT* statement COMMENT*
  private static boolean statementlist_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statementlist_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = statementlist_0_0(b, l + 1);
    r = r && statement(b, l + 1);
    r = r && statementlist_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT*
  private static boolean statementlist_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statementlist_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "statementlist_0_0", c)) break;
    }
    return true;
  }

  // COMMENT*
  private static boolean statementlist_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statementlist_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "statementlist_0_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // TYPE typename EQ typeusage SEMIC
  //                 | TYPE typename EQ arr SEMIC
  public static boolean typedecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typedecl")) return false;
    if (!nextTokenIs(b, TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = typedecl_0(b, l + 1);
    if (!r) r = typedecl_1(b, l + 1);
    exit_section_(b, m, TYPEDECL, r);
    return r;
  }

  // TYPE typename EQ typeusage SEMIC
  private static boolean typedecl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typedecl_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TYPE);
    r = r && typename(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && typeusage(b, l + 1);
    r = r && consumeToken(b, SEMIC);
    exit_section_(b, m, null, r);
    return r;
  }

  // TYPE typename EQ arr SEMIC
  private static boolean typedecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typedecl_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TYPE);
    r = r && typename(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && arr(b, l + 1);
    r = r && consumeToken(b, SEMIC);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENT
  public static boolean typename(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typename")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENT);
    exit_section_(b, m, TYPENAME, r);
    return r;
  }

  /* ********************************************************** */
  // IDENT
  public static boolean typeusage(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeusage")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENT);
    exit_section_(b, m, TYPEUSAGE, r);
    return r;
  }

  /* ********************************************************** */
  // MINUS
  public static boolean uminus(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "uminus")) return false;
    if (!nextTokenIs(b, MINUS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MINUS);
    exit_section_(b, m, UMINUS, r);
    return r;
  }

  /* ********************************************************** */
  // VAR varname COLON typeusage SEMIC
  //                 |   VAR varname COLON arr SEMIC
  public static boolean vardecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vardecl")) return false;
    if (!nextTokenIs(b, VAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = vardecl_0(b, l + 1);
    if (!r) r = vardecl_1(b, l + 1);
    exit_section_(b, m, VARDECL, r);
    return r;
  }

  // VAR varname COLON typeusage SEMIC
  private static boolean vardecl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vardecl_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VAR);
    r = r && varname(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && typeusage(b, l + 1);
    r = r && consumeToken(b, SEMIC);
    exit_section_(b, m, null, r);
    return r;
  }

  // VAR varname COLON arr SEMIC
  private static boolean vardecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vardecl_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VAR);
    r = r && varname(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && arr(b, l + 1);
    r = r && consumeToken(b, SEMIC);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (COMMENT* vardecl)+
  public static boolean vardecllist(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vardecllist")) return false;
    if (!nextTokenIs(b, "<vardecllist>", COMMENT, VAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARDECLLIST, "<vardecllist>");
    r = vardecllist_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!vardecllist_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "vardecllist", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // COMMENT* vardecl
  private static boolean vardecllist_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vardecllist_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = vardecllist_0_0(b, l + 1);
    r = r && vardecl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT*
  private static boolean vardecllist_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vardecllist_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "vardecllist_0_0", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // IDENT
  public static boolean varname(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varname")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENT);
    exit_section_(b, m, VARNAME, r);
    return r;
  }

  /* ********************************************************** */
  // IDENT
  public static boolean varusage(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varusage")) return false;
    if (!nextTokenIs(b, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENT);
    exit_section_(b, m, VARUSAGE, r);
    return r;
  }

}
