dependencies {

    implementation(project(":buildPlatform"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("com.pholser:junit-quickcheck-core:0.8.2")
    testImplementation("com.pholser:junit-quickcheck-generators:0.8.2")
}
