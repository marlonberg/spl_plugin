package org.intellij.sdk.language;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import org.intellij.sdk.language.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

class SplCreateTypedeclQuickFix extends BaseIntentionAction {

    private final SplTypeusage key;

    SplCreateTypedeclQuickFix(SplTypeusage key) {
        this.key = key;
    }

    @NotNull
    @Override
    public String getText() {
        return "Create Type Declaration '" + key.getText() + "'";
    }

    @NotNull
    @Override
    public String getFamilyName() {
        return "Create Type Declaration";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws
            IncorrectOperationException {
        ApplicationManager.getApplication().invokeLater(() -> {
            Collection<VirtualFile> virtualFiles =
                    FileTypeIndex.getFiles(SplFileType.INSTANCE, GlobalSearchScope.allScope(project));
            if (virtualFiles.size() == 1) {
                createTypedecl(project, virtualFiles.iterator().next());
            } else {
                final FileChooserDescriptor descriptor =
                        FileChooserDescriptorFactory.createSingleFileDescriptor(SplFileType.INSTANCE);
                descriptor.setRoots(ProjectUtil.guessProjectDir(project));
                final VirtualFile file1 = FileChooser.chooseFile(descriptor, project, null);
                if (file1 != null) {
                    createTypedecl(project, file1);
                }
            }
        });
    }

    private void createTypedecl(final Project project, final VirtualFile file) {
        WriteCommandAction.writeCommandAction(project).run(() -> {
            SplFile splFile = (SplFile) PsiManager.getInstance(project).findFile(file);

            SplTypedecl typedecl = SplElementFactory.createTypedecl(project, key.getText());
            splFile.getNode().addChild(SplElementFactory.createTypedecl(project, key.getText()).getNode(), splFile.findChildByClass(SplProcdecl.class).getNode());
            splFile.getNode().addChild(SplElementFactory.createCRLF(project).getNode(), splFile.findChildByClass(SplProcdecl.class).getNode());
            splFile.getNode().addChild(SplElementFactory.createCRLF(project).getNode(), splFile.findChildByClass(SplProcdecl.class).getNode());
            ((Navigatable) typedecl.getLastChild().getNavigationElement()).navigate(true);
            FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(2, 0, false, false, false);
        });
    }

}
