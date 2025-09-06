package com.romecka.fakeforge.architecture

import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures

import static com.tngtech.archunit.library.Architectures.layeredArchitecture

@AnalyzeClasses(packages = 'com.romecka.fakeforge', importOptions = ImportOption.DoNotIncludeTests)
class ArchitectureSpec {

    private static final String APPLICATION = 'application'

    private static final String DOMAIN = 'domain'

    private static final String INFRASTRUCTURE = 'infrastructure'

    private static final String ROOT = 'root'

    private static final String UTILS = 'utils'

    @ArchTest
    static final ArchRule APPLICATION_ACCESS = layers()
        .whereLayer(APPLICATION).mayOnlyAccessLayers(DOMAIN, UTILS)
        .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(ROOT)

    @ArchTest
    static final ArchRule DOMAIN_ACCESS = layers()
        .whereLayer(DOMAIN).mayOnlyAccessLayers(UTILS)

    @ArchTest
    static final ArchRule INFRASTRUCTURE_ACCESS = layers()
        .whereLayer(INFRASTRUCTURE).mayOnlyAccessLayers(DOMAIN, UTILS)
        .whereLayer(INFRASTRUCTURE).mayNotBeAccessedByAnyLayer()

    @ArchTest
    static final ArchRule UTILS_ACCESS = layers()
        .whereLayer(UTILS).mayNotAccessAnyLayer()

    private static Architectures.LayeredArchitecture layers() {
        return layeredArchitecture().consideringOnlyDependenciesInAnyPackage('com.romecka.fakeforge..')
            .layer(ROOT).definedBy('com.romecka.fakeforge')
            .layer(APPLICATION).definedBy('com.romecka.fakeforge.application..')
            .layer(DOMAIN).definedBy('com.romecka.fakeforge.domain..')
            .layer(UTILS).definedBy('com.romecka.fakeforge.utils..')
            .layer(INFRASTRUCTURE).definedBy('com.romecka.fakeforge.infrastructure..')
    }

}
