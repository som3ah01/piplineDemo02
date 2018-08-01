pipeline {
    agent any
    parameters{
        string(name: 'tomcat-aws',defaultValue: '35.173.195.74',description: 'AWS Server')
        string(name: 'tomcat-pem',defaultValue: '/home/som3ah01/DEV/workspaces/jankens/DevOpsDemo/piplineDemo02/tomcat-demo.pem',description: 'AWS pem Server')
        
    }
    triggers{
        pollSCM('* * * * *')
    }
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
        stage('Deploy to AWS'){
            steps{
                sh "scp -i /home/som3ah01/DEV/workspaces/jankens/DevOpsDemo/piplineDemo02/tomcat-demo.pem **/target/*.war ec2-user@${params.tomcat-aws}:/var/lib/tomcat/webapps"

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