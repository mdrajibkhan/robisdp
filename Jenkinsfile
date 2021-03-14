pipeline {
    agent any

    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/mdrajibkhan/robisdp.git'
            }
       
       }
       stage ("Maven Build"){
		steps{

		  sh "mvn clean package"
		}
	}   
    }
}
