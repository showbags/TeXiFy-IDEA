package nl.hannahsten.texifyidea.reference

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.tree.TokenSet
import nl.hannahsten.texifyidea.LatexLexerAdapter
import nl.hannahsten.texifyidea.psi.LatexTypes

class LatexUsagesProvider : FindUsagesProvider {
    override fun getWordsScanner(): WordsScanner? {
        return DefaultWordsScanner(LatexLexerAdapter(),
                // Identifiers.
                TokenSet.create(LatexTypes.COMMAND_TOKEN, LatexTypes.COMMANDS,
                        LatexTypes.BEGIN_COMMAND, LatexTypes.BEGIN_TOKEN,
                        LatexTypes.END_COMMAND, LatexTypes.END_TOKEN,
                        LatexTypes.OPEN_BRACE, LatexTypes.OPEN_BRACKET,
                        LatexTypes.OPEN_BRACE, LatexTypes.OPEN_PAREN,
                        LatexTypes.M_OPEN_BRACKET, LatexTypes.CLOSE_BRACE,
                        LatexTypes.CLOSE_BRACKET, LatexTypes.CLOSE_PAREN,
                        LatexTypes.M_CLOSE_BRACKET, LatexTypes.PARAMETER,
                        LatexTypes.REQUIRED_PARAM, LatexTypes.OPTIONAL_PARAM,
                        LatexTypes.DISPLAY_MATH_START, LatexTypes.DISPLAY_MATH_END,
                        LatexTypes.INLINE_MATH_START, LatexTypes.INLINE_MATH_END,
                        LatexTypes.NORMAL_TEXT_WORD, LatexTypes.NORMAL_TEXT,
                        LatexTypes.STAR
                ),
                // Comments.
                TokenSet.create(LatexTypes.COMMENT_TOKEN, LatexTypes.COMMENT),
                // Literals.
                TokenSet.create(
                        LatexTypes.ENVIRONMENT_CONTENT, LatexTypes.CONTENT,
                        LatexTypes.MATH_CONTENT, LatexTypes.ENVIRONMENT,
                        LatexTypes.MATH_ENVIRONMENT, LatexTypes.DISPLAY_MATH,
                        LatexTypes.INLINE_MATH, LatexTypes.NO_MATH_CONTENT,
                        LatexTypes.GROUP, LatexTypes.OPEN_GROUP
                )
        )
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
        return element.node.text
    }

    override fun getDescriptiveName(element: PsiElement): String {
        return element.node.elementType.toString() + element.text
    }

    override fun getType(element: PsiElement): String {
        return element.node.elementType.toString()
    }

    override fun getHelpId(psiElement: PsiElement): String? {
        return null
    }

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        return psiElement is PsiNameIdentifierOwner
    }
}