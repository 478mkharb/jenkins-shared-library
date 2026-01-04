def call(Map config = [:]) {

    if (!config.nvdApiKey) {
        error "OWASP Dependency Check: nvdApiKey is mandatory"
    }

    def projectName = config.get('project', 'app')
    def failScore   = config.get('failOnCVSS', 7)
    def scanDir     = config.get('scanDir', '.')
    def outDir      = config.get('outDir', 'dependency-check-report')

    withEnv(["NVD_API_KEY=${config.nvdApiKey}"]) {
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
