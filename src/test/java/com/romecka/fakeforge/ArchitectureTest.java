package com.romecka.fakeforge;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@SuppressWarnings("unused")
@AnalyzeClasses(packages = "com.romecka.fakeforge", importOptions = DoNotIncludeTests.class)
class ArchitectureTest {

    private static final String APPLICATION = "application";
    private static final String DOMAIN = "domain";
    private static final String INFRASTRUCTURE = "infrastructure";
    private static final String ROOT = "root";

    @ArchTest
    static final ArchRule APPLICATION_ACCESS = layers()
            .whereLayer(APPLICATION).mayOnlyAccessLayers(DOMAIN)
            .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(ROOT);

    @ArchTest
    static final ArchRule DOMAIN_ACCESS = layers()
            .whereLayer(DOMAIN).mayNotAccessAnyLayer();

    @ArchTest
    static final ArchRule INFRASTRUCTURE_ACCESS = layers()
            .whereLayer(INFRASTRUCTURE).mayOnlyAccessLayers(DOMAIN)
            .whereLayer(INFRASTRUCTURE).mayNotBeAccessedByAnyLayer();

    private static Architectures.LayeredArchitecture layers() {
        return layeredArchitecture().consideringOnlyDependenciesInAnyPackage("com.romecka.fakeforge..")
                .layer(ROOT).definedBy("com.romecka.fakeforge")
                .layer(APPLICATION).definedBy("com.romecka.fakeforge.application..")
                .layer(DOMAIN).definedBy("com.romecka.fakeforge.domain..")
                .layer(INFRASTRUCTURE).definedBy("com.romecka.fakeforge.infrastructure..");
    }

}
