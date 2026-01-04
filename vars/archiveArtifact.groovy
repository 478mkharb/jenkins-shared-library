def call() {
    stage('Archive Artifact') {
        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }
}
