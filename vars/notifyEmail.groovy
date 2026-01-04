
def call(String status) {

    
    def subjectPrefix = status == 'SUCCESS' ? 'SUCCESS' : (status == 'UNSTABLE' ? 'UNSTABLE' : 'FAILURE')
    def attachLog = status != 'SUCCESS'

    emailext(
        subject: "${subjectPrefix}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
        body: """
        <h3>Build Status: ${status}</h3>
        <p><b>Job:</b> ${env.JOB_NAME}</p>
        <p><b>Build Number:</b> #${env.BUILD_NUMBER}</p>
        <p><b>Branch:</b> ${env.GIT_BRANCH ?: env.BRANCH_NAME ?: 'N/A'}</p>
        <p><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
        """,
        mimeType: 'text/html',
        attachLog: attachLog,
        to: '478mkharb@gmail.com' 
    )
}
