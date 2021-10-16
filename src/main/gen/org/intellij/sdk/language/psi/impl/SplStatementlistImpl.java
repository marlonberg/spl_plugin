// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.sdk.language.psi.SplTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.intellij.sdk.language.psi.*;

public class SplStatementlistImpl extends ASTWrapperPsiElement implements SplStatementlist {

  public SplStatementlistImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SplVisitor visitor) {
    visitor.visitStatementlist(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SplVisitor) accept((SplVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SplStatement> getStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SplStatement.class);
  }

}
