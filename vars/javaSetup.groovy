def call() {
    stage('Java Setup') {
        sh '''
        echo "===== JAVA SETUP ====="
        export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
        export PATH=$JAVA_HOME/bin:$PATH

        java -version
        mvn -version
        '''
    }
}
