package nl.hannahsten.texifyidea.inspections.latex

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Test

class PackageCouldNotBeFound : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "test/resources/inspections/latex/packagenotfound"
    }

    override fun setUp() {
        super.setUp()
        myFixture.enableInspections(LatexPackageCouldNotBeFound())
    }

    @Test
    fun testPackageCouldNotBeFoundWarnings() {
        val testName = getTestName(false)
        myFixture.configureByFile("$testName.tex")
        myFixture.checkHighlighting(true, false, false, false)
    }
}