pipeline {
    agent any

    stages {

        stage('test') {
            steps {
                bat 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }

    }
}
