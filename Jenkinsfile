pipeline {
    agent any

    stages {

        stage('test') {
            steps {
                bat 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('cucumber') {
            steps {
                cucumber reportTitle: 'API Report',
                        fileIncludePattern: 'target/example-report.json'
            }
        }

        stage('jacoco') {
            steps {

                recordCoverage(tools: [[parser: 'JACOCO']],
                        id: 'jacoco', name: 'JaCoCo Coverage',
                        sourceCodeRetention: 'EVERY_BUILD',
                        qualityGates: [
                            [threshold: 60.0, metric: 'LINE', baseline: 'PROJECT', unstable: true],
                            [threshold: 60.0, metric: 'BRANCH', baseline: 'PROJECT', unstable: true]
                        ]
                )

            }
        }

        stage('deploy') {
            steps {
                bat 'mvn deploy';
            }
        }

        /*stage('slack') {
            steps {
                bat '''
                curl -X POST -H 'Content-type: application/json' --data '{"text":"Hello, World!"}' https://hooks.slack.com/services/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
                '''
            }
        }*/

    }
}