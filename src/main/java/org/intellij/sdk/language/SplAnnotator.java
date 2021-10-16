package org.intellij.sdk.language;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.intellij.sdk.language.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SplAnnotator implements Annotator {

    public static Map<String, String> bibProcs = Map.ofEntries(
            Map.entry("printi", "printi(i: int): Gibt den Wert von i auf dem Textbildschirm aus."),
            Map.entry("printc", "printc(i: int): Gibt das Zeichen mit dem ASCII-Code i auf dem Textbildschirm aus."),
            Map.entry("readi", "readi(ref i: int): Liest eine ganze Zahl von der Tastatur ein und speichert sie in i. Die Eingabe erfolgt zeilenweise gepuffert mit Echo."),
            Map.entry("readc", "readc(ref i: int): Liest ein Zeichen von der Tastatur ein und speichert seinen ASCII-Code in i. Die Eingabe erfolgt ungepuffert und ohne Echo."),
            Map.entry("exit", "exit(): Beendet das laufende Programm und kehrt nicht zum Aufrufer zuruck."),
            Map.entry("time", "time(ref i: int): Gibt in i die seit dem Start des Programms vergangene Zeit in Sekunden zuruck."),
            Map.entry("clearAll", "clearAll(color: int): Löscht den Graphikbildschirm mit der Farbe color. Farben werden durch Angabe der R-, G- und B-Komponenten nach dem Muster 0x00RRGGBB gebildet. Es stehen also fur jede Komponente die Werte 0..255 zur Verfugung."),
            Map.entry("setPixel", "setPixel(x: int, y: int, color: int): Setzt den Pixel mit den Koordinaten x und y auf die Farbe color. Grenzen: 0 <= x < 640, 0 <= y < 480."),
            Map.entry("drawLine", "drawLine(x1: int, y1: int, x2: int, y2: int, color: int): Zeichnet eine gerade Linie von (x1|y1) nach (x2|y2) mit der Farbe color. Grenzen wie bei setPixel."),
            Map.entry("drawCircle", "drawCircle(x0: int, y0: int, radius: int, color: int): Zeichnet einen Kreis um den Mittelpunkt (x0|y0) mit dem Radius radius und der Farbe color."));


    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {

        if ((element instanceof SplVarname)) {
            SplVarname varname = (SplVarname) element;

            // Get the list of vars for given key
            List<PsiElement> vars = SplUtil.findUsage(element.getProject(), varname, SplVarusage.class);
            if (vars.isEmpty()) {
                holder.newAnnotation(HighlightSeverity.INFORMATION, "Unused var")
                        .range(varname.getTextRange())
                        .highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
                        .withFix(new SplRemoveVardeclQuickFix(varname))
                        .create();
            } else {
                // Found at least one var, force the text attributes to Spl syntax value character
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(varname.getTextRange()).create();

            }
        } else if ((element instanceof SplProcname)) {
            SplProcname procname = (SplProcname) element;

            if (Objects.equals(procname.getName(), "main")){
                return;
            }

            // Get the list of vars for given key
            List<PsiElement> procs = SplUtil.findUsage(element.getProject(), procname, SplProcusage.class);
            if (procs.isEmpty()) {
                holder.newAnnotation(HighlightSeverity.INFORMATION, "Unused proc")
                        .range(procname.getTextRange())
                        .highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
                        .withFix(new SplRemoveProcdeclQuickFix(procname))
                        .create();
            } else {
                // Found at least one var, force the text attributes to Spl syntax value character
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(procname.getTextRange()).create();

            }
        } else if ((element instanceof SplTypename)) {
            SplTypename typename = (SplTypename) element;

            // Get the list of vars for given key
            List<PsiElement> types = SplUtil.findUsage(element.getProject(), typename, SplTypeusage.class);
            if (types.isEmpty()) {
                holder.newAnnotation(HighlightSeverity.INFORMATION, "Unused type")
                        .range(typename.getTextRange())
                        .highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
                        .withFix(new SplRemoveTypedeclQuickFix(typename))
                        .create();
            } else {
                // Found at least one var, force the text attributes to Spl syntax value character
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(typename.getTextRange()).create();

            }
        } else if ((element instanceof SplVarusage)) {
            SplVarusage varusage = (SplVarusage) element;

            // Get the list of vars for given key
            List<PsiNamedElement> decls = SplUtil.findDecl(element.getProject(), varusage, SplVarname.class);
            if (decls.isEmpty()) {
                holder.newAnnotation(HighlightSeverity.INFORMATION, "Var " + varusage.getText() + " was never declared")
                        .range(varusage.getTextRange())
                        .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                        .withFix(new SplCreateVardeclQuickFix(varusage))
                        .create();
            } else {
                // Found at least one var, force the text attributes to Spl syntax value character
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(varusage.getTextRange()).create();

            }
        } else if ((element instanceof SplProcusage)) {
            SplProcusage procusage = (SplProcusage) element;

            // Get the list of vars for given key
            List<PsiNamedElement> decls = SplUtil.findDecl(element.getProject(), procusage, SplProcname.class);
            if (decls.isEmpty() && !bibProcs.containsKey(procusage.getText())) {
                holder.newAnnotation(HighlightSeverity.INFORMATION, "Proc " + procusage.getText() + " was never declared")
                        .range(procusage.getTextRange())
                        .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                        .withFix(new SplCreateProcdeclQuickFix(procusage))
                        .create();
            } else {
                // Found at least one var, force the text attributes to Spl syntax value character
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(procusage.getTextRange()).create();

            }
        } else if ((element instanceof SplTypeusage)) {
            SplTypeusage typeusage = (SplTypeusage) element;

            // Get the list of vars for given key
            List<PsiNamedElement> decls = SplUtil.findDecl(element.getProject(), typeusage, SplTypename.class);
            if (decls.isEmpty() && !Objects.equals(typeusage.getText(), "int")) {
                holder.newAnnotation(HighlightSeverity.INFORMATION, "Type " + typeusage.getText() + " was never declared")
                        .range(typeusage.getTextRange())
                        .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                        .withFix(new SplCreateTypedeclQuickFix(typeusage))
                        .create();
            } else {
                // Found at least one var, force the text attributes to Spl syntax value character
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(typeusage.getTextRange()).create();

            }
        }
    }

}
