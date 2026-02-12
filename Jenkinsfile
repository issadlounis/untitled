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




        stage('Slack & Notification') {
            parallel {
                stage('slack') {
                    steps {
                        bat """
                            curl -X POST ^
                            -H "Content-type: application/json" ^
                            --data "{\\"text\\":\\"Hello World!!!\\"}" ^
                            %SLACK_WEBHOOK%
                        """
                    }
                }

                stage('notification') {
                    steps {
                        /*post {
                            failure {*/
                                        mail(subject: "Notification Slack",
                                                body: "Message envoyé",
                                                to: "louniscntsid@gmail.com"
                                        )
                            /*}

                            success {
                                       mail(subject: "Notification Slack",
                                               body: "Message envoyé",
                                               to: "louniscntsid@gmail.com"
                                       )
                            }
                        }*/
                    }
                }
            }
        }

        stage('tag') {
            steps {
                bat 'git tag -a v%VERSION% -m "Release version %VERSION%"'
                bat 'git push origin v%VERSION%'

                bat '''
                    curl -X POST https://api.github.com/repos/issadlounis/untitled/releases \
                      -H "Authorization: Bearer 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" \
                      -H "Accept: application/vnd.github+json" \
                      -H "Content-Type: application/json" \
                      -d '{
                        "tag_name": "v%VERSION%",
                        "name": "Release v%VERSION%",
                        "body": "Production release",
                        "draft": false,
                        "prerelease": false
                      }'

                '''
            }
        }

    }
}
