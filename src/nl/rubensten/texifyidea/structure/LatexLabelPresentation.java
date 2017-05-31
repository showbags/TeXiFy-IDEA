package nl.rubensten.texifyidea.structure;

import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import nl.rubensten.texifyidea.TexifyIcons;
import nl.rubensten.texifyidea.psi.LatexCommands;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

/**
 * @author Ruben Schellekens
 */
public class LatexLabelPresentation implements ItemPresentation {

    private final String labelName;
    private final String locationString;

    public LatexLabelPresentation(LatexCommands labelCommand) {
        if (!labelCommand.getCommandToken().getText().equals("\\label")) {
            throw new IllegalArgumentException("command is no \\label-command");
        }

        // Get label name.
        List<String> required = labelCommand.getRequiredParameters();
        if (required.isEmpty()) {
            throw new IllegalArgumentException("\\label has no label name");
        }
        this.labelName = required.get(0);

        // Location string.
        FileDocumentManager manager = FileDocumentManager.getInstance();
        Document document = manager.getDocument(labelCommand.getContainingFile().getVirtualFile());
        int line = document.getLineNumber(labelCommand.getTextOffset()) + 1;
        this.locationString = labelCommand.getContainingFile().getName() + ":" + line;
    }

    @Nullable
    @Override
    public String getPresentableText() {
        return labelName;
    }

    @Nullable
    @Override
    public String getLocationString() {
        return locationString;
    }

    @Nullable
    @Override
    public Icon getIcon(boolean b) {
        return TexifyIcons.DOT_LABEL;
    }
}
