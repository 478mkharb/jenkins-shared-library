def call() {
    stage('OWASP Dependency Check') {
        withCredentials([string(credentialsId: 'nvd-api-key', variable: 'NVD_API_KEY')]) {
            sh '''
            echo "===== OWASP DEPENDENCY CHECK ====="

            mvn org.owasp:dependency-check-maven:check \
              -Dnvd.api.key=$NVD_API_KEY \
              -Dformat=ALL \
              -DfailBuildOnCVSS=7

            '''
        }
    }
}
