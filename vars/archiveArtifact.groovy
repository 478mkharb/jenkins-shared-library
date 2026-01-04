def call() {
      archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
}
