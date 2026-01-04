def call(String status = 'SUCCESS') {

    def subjectPrefix = status == 'SUCCESS' ? 'SUCCESS' : '❌ FAILURE'

    emailext(
        subject: "${subjectPrefix}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
        body: """
        <h3>Build ${status}</h3>
        <p><b>Job:</b> ${env.JOB_NAME}</p>
        <p><b>Build:</b> #${env.BUILD_NUMBER}</p>
        <p><b>Branch:</b> ${env.GIT_BRANCH ?: 'N/A'}</p>
        <p><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
        """,
        mimeType: 'text/html',
        attachLog: status != 'SUCCESS'
    )
}
