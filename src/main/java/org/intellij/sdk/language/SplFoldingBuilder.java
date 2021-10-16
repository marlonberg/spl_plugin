package org.intellij.sdk.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.sdk.language.psi.SplProcdecl;
import org.intellij.sdk.language.psi.SplStatement;
import org.intellij.sdk.language.psi.SplTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SplFoldingBuilder extends FoldingBuilderEx implements DumbAware {

    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        // Initialize the group of folding regions that will expand/collapse together.
        // Initialize the list of folding regions
        List<FoldingDescriptor> descriptors = new ArrayList<>();
        // Get a collection of the literal expressions in the document below root
        Collection<SplProcdecl> procdecls =
                PsiTreeUtil.findChildrenOfType(root, SplProcdecl.class);
        Collection<SplStatement> statements =
                PsiTreeUtil.findChildrenOfType(root, SplStatement.class);


        // Evaluate the collection
        for (final SplProcdecl decl : procdecls) {
            descriptors.add(new FoldingDescriptor(decl.getNode(),
                    new TextRange(decl.getNode().findChildByType(SplTypes.LCURL).getTextRange().getEndOffset(),
                            decl.getTextRange().getEndOffset() - 1)));

        }

        for (final SplStatement statement : statements) {
            descriptors.add(new FoldingDescriptor(statement.getNode(),
                    new TextRange(statement.getTextRange().getStartOffset() + 1,
                            statement.getTextRange().getEndOffset() - 1)));

        }

        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    /**
     * Gets the Spl Language 'value' string corresponding to the 'key'
     *
     * @param node Node corresponding to PsiLiteralExpression containing a string in the format
     *             SIMPLE_PREFIX_STR + SIMPLE_SEPARATOR_STR + Key, where Key is
     *             defined by the Spl language file.
     */
    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        String retTxt = "...";
        return retTxt;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }

}
