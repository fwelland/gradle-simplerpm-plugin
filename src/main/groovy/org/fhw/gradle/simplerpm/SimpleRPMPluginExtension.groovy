package org.fhw.gradle.simplerpm

class SimpleRPMPluginExtension {
    def String artifact_to_include
    def String spec_file
    def Map rpm_macro_map
    def String rpmVersion
    def String rpmBaseName
    def String rpmRelease = '1'
}
