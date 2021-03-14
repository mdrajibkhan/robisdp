pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
       
       }
       
       stage('Build') {
            steps {
                echo 'Building'
                deploy contextPath: '/var/lib/jenkins/workspace/Jenkins_Pipeline_Test_Project/', war: '"**/*.war"'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing'
            }
        }
        stage('Release') {
            steps {
                echo 'Releasing'
            }
        }
    }
}
