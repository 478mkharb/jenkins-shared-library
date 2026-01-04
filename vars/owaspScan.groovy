def call(Map config = [:]) {
    // Extract configuration with defaults
    def projectName = config.get('project', 'app')
    def failScore   = config.get('failOnCVSS', 7)
    def scanDir     = config.get('scanDir', '.')
    def outDir      = config.get('outDir', 'dependency-check-report')
    def nvdApiKey   = config.nvdApiKey

    if (!nvdApiKey) {
        error "OWASP Dependency Check: nvdApiKey is mandatory"
    }

    // Check if dependency-check.sh exists in PATH
    def dcExists = sh(
        script: 'command -v dependency-check.sh || true',
        returnStdout: true
    ).trim()

    if (!dcExists) {
        echo "OWASP Dependency Check CLI not found. Skipping scan."
        return
    }

    // Run the scan inside catchError so it doesn't fail the pipeline completely
    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
        withEnv(["NVD_API_KEY=${nvdApiKey}"]) {
            sh """
            dependency-check.sh \
                --project ${projectName} \
                --scan ${scanDir} \
                --format XML \
                --out ${outDir} \
                --nvdApiKey \$NVD_API_KEY \
                --failOnCVSS ${failScore}
            """
        }
    }
}
