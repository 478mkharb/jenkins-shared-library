def call() {
    stage('Maven Test') {
        sh '''
        mvn clean test cobertura:cobertura
        mvn cobertura:cobertura
        '''
    }
}
