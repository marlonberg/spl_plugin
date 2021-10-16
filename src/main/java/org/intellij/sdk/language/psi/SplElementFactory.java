package org.intellij.sdk.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import org.apache.tools.ant.types.selectors.TypeSelector;
import org.intellij.sdk.language.SplFileType;

public class SplElementFactory {

    public static PsiElement createCRLF(Project project) {
        final SplFile file = createFile(project, "proc main(){\n}");
        return file.getFirstChild().getNode().findChildByType(SplTypes.LCURL).getTreeNext().getPsi();
    }

    public static PsiElement createWhitespace(Project project) {
        final SplFile file = createFile(project, "proc main(){ }");
        return file.getFirstChild().getNode().findChildByType(SplTypes.LCURL).getTreeNext().getPsi();
    }

    public static SplVarname createVar(Project project, String name){
        String dummyFile = String.format("proc main(){var %s : dummy;}", name);
        final SplFile file = createFile(project, dummyFile);

        return (SplVarname) file.getFirstChild().getNode()
                .findChildByType(SplTypes.VARDECLLIST)
                .findChildByType(SplTypes.VARDECL)
                .findChildByType(SplTypes.VARNAME).getPsi();
    }

    public static SplVardecl createVardecl(Project project, String name){
        String dummyFile = String.format("proc main(){var %s : typename;}", name);
        final SplFile file = createFile(project, dummyFile);

        return (SplVardecl) file.getFirstChild().getNode()
                .findChildByType(SplTypes.VARDECLLIST)
                .findChildByType(SplTypes.VARDECL).getPsi();
    }

    public static SplProcname createProc(Project project, String name){
        String dummyFile = String.format("proc %s(){}", name);
        final SplFile file = createFile(project, dummyFile);

        return (SplProcname) file.getFirstChild().getNode()
                .findChildByType(SplTypes.PROCNAME).getPsi();
    }

    public static SplProcdecl createProcdecl(Project project, String name){
        String dummyFile = String.format("proc %s(){}", name);
        final SplFile file = createFile(project, dummyFile);

        return (SplProcdecl) file.getFirstChild().getNode().getPsi();
    }

    public static SplTypename createType(Project project, String name){
        String dummyFile = String.format("type %s = dummy;", name);
        final SplFile file = createFile(project, dummyFile);

        return (SplTypename) file.getFirstChild().getNode()
                .findChildByType(SplTypes.TYPENAME).getPsi();
    }

    public static SplTypedecl createTypedecl(Project project, String name){
        String dummyFile = String.format("type %s = int;", name);
        final SplFile file = createFile(project, dummyFile);

        return (SplTypedecl) file.getFirstChild().getNode().getPsi();
    }


    public static SplFile createFile(Project project, String text) {
        String name = "dummy.spl";
        return (SplFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, SplFileType.INSTANCE, text);
    }
}
