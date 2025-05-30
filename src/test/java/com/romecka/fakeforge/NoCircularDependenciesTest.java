package com.romecka.fakeforge;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

@AnalyzeClasses(packages = "com.romecka.fakeforge")
public class NoCircularDependenciesTest {

    @ArchTest
    static final ArchRule no_cycles_between_packages = SlicesRuleDefinition.slices()
            .matching("com.romecka.fakeforge.(*)..")
            .should().beFreeOfCycles();

    @ArchTest
    static final ArchRule no_class_cycles = SlicesRuleDefinition.slices()
            .matching("com.romecka.fakeforge.(**)")
            .should().beFreeOfCycles();

}
