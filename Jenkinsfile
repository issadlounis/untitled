pipeline {
    agent any

    stages {

        stage('test') {
            steps {
                bat 'mvn test'
                junit 'target/surefire-reports/*.xml'

                cucumber reportTitle: 'API Report',
                    fileIncludePattern: 'target/example-report.json'

                recordCoverage(tools: [[parser: 'JACOCO']],
                    id: 'jacoco', name: 'JaCoCo Coverage',
                    sourceCodeRetention: 'EVERY_BUILD',
                    qualityGates: [
                        [threshold: 60.0, metric: 'LINE', baseline: 'PROJECT', unstable: true],
                        [threshold: 60.0, metric: 'BRANCH', baseline: 'PROJECT', unstable: true]
                    ])
            }
        }

    }
}