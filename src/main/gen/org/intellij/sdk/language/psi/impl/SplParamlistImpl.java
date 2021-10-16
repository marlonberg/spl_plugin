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

public class SplParamlistImpl extends ASTWrapperPsiElement implements SplParamlist {

  public SplParamlistImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SplVisitor visitor) {
    visitor.visitParamlist(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SplVisitor) accept((SplVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SplParam> getParamList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SplParam.class);
  }

}
