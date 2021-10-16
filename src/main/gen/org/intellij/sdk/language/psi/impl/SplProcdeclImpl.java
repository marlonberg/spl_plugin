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

public class SplProcdeclImpl extends ASTWrapperPsiElement implements SplProcdecl {

  public SplProcdeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SplVisitor visitor) {
    visitor.visitProcdecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SplVisitor) accept((SplVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SplParamlist getParamlist() {
    return findChildByClass(SplParamlist.class);
  }

  @Override
  @NotNull
  public SplProcname getProcname() {
    return findNotNullChildByClass(SplProcname.class);
  }

  @Override
  @Nullable
  public SplStatementlist getStatementlist() {
    return findChildByClass(SplStatementlist.class);
  }

  @Override
  @Nullable
  public SplVardecllist getVardecllist() {
    return findChildByClass(SplVardecllist.class);
  }

}
