pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage("Source") {
            steps {
                git branch: 'main', url: 'https://github.com/zoudoum/wallet'
            }
        }
        stage("Build") {
            steps {
                bat 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
            }
        }
        stage("SonarQube Analysis") {
            steps {
                bat 'mvn sonar:sonar'
            }
        }
        stage('Approve Deployment') {
            input {
                message "Do you want to proceed for deployment?"
            }
            steps {
                 bat 'echo "Deploying into Server"'
            }
        }
    }
    post {
        aborted {
            echo "Sending message to agent"
        }
        failure {
            echo "Sending message to agent"
        }
        success {
       echo "Sending message to agent"
        }
    }
}
