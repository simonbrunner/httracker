node {
    echo "Building httracker"

    git url: 'https://github.com/simonbrunner/httracker.git'

    def mvnHome = tool 'M3'
    sh "${mvnHome}/bin/mvn --version"

    sh "${mvnHome}/bin/mvn package -DskipTests=true"

    # sh "${mvnHome}/bin/mvn -B -Dmaven.test.failure.ignore verify"
    # step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
    # step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
}