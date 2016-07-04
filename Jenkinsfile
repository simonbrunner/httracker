node {
    // ---------------------------------------------------------------------------------- SCM Checkout
    stage 'Checkout'

    // Checkout code from repository
    echo "Checking out httracker from SCM"
    checkout scm

    // ---------------------------------------------------------------------------------- Build Stage
    stage 'Build'
    echo "Building httracker"

    // Specify the used maven version
    def mvnHome = tool 'M3'
    sh "${mvnHome}/bin/mvn --version"

    // Finally launch the build process (without running any tests)
    sh "${mvnHome}/bin/mvn package -DskipTests=true"

    // ---------------------------------------------------------------------------------- Unit tests
    stage 'Unittest'
    echo "Running Unittests"

    sh "${mvnHome}/bin/mvn -B -Dmaven.test.failure.ignore test"
    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])

    // ---------------------------------------------------------------------------------- Integration tests
    stage 'Integrationtest'
    echo "Running Integrationtests"

    sh "${mvnHome}/bin/mvn -B -Dmaven.test.failure.ignore verify"
    step([$class: 'JUnitResultArchiver', testResults: '**/target/failsafe-reports/*.xml'])

    // step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
}