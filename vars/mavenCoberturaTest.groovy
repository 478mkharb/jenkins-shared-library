def call() {
    stage('Maven Test') {
        sh '''
        mvn clean test package cobertura:cobertura
        mvn cobertura:cobertura
        '''
    }
}
