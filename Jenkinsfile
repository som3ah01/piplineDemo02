pipeline {
    agent any
    
    tools {
        maven 'LocalMaven'
    }

    stages{
        stage('Build'){
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }
        stage('Deploy to staging'){
            steps{
                build job: 'deploy-to-stage'

            }
        }
        stage('Deploy to production'){
            steps{
                timeout(time:5 , unit:'DAYS'){
                    input message: 'QA Manager Aprove To Production Deployment?'
                }
                build job:'deploy-to-prod'
            }
            post{
                success{
                    echo 'Done successfully deploy-to-prod'
                }
                failure{
                    echo 'fail to deploy-to-prod'
                }

            }
        }
    }
}