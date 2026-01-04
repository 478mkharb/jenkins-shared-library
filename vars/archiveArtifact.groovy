def call() {
      archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
}
