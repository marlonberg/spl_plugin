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

public class SplExprImpl extends ASTWrapperPsiElement implements SplExpr {

  public SplExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SplVisitor visitor) {
    visitor.visitExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SplVisitor) accept((SplVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SplDim getDim() {
    return findChildByClass(SplDim.class);
  }

  @Override
  @Nullable
  public SplExpr getExpr() {
    return findChildByClass(SplExpr.class);
  }

  @Override
  @Nullable
  public SplUminus getUminus() {
    return findChildByClass(SplUminus.class);
  }

  @Override
  @Nullable
  public SplVarusage getVarusage() {
    return findChildByClass(SplVarusage.class);
  }

}
