def call() {
    stage('Gitleaks Scan') {
        sh '''
        echo "===== GITLEAKS ====="
        gitleaks detect \
          --source . \
          --report-format json \
          --report-path gitleaks.json || true
        '''
    }
}
