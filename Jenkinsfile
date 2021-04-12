currentBuild.displayName = "online-shopping-#"+currentBuild.number
pipeline {
    agent any
    environment{
    	path= "/usr/local/src/apache-maven/bin:$PATH"
    }
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', credentialsId: 'af2b9693-3995-4539-8fe8-b935ee22a1f4', url: 'https://github.com/mdrajibkhan/robisdp.git'
            }
       
       }
	stage('Test') {
            steps {
                sh './mvnw test'
                // bat '.\\mvnw test'
            }
	    
       stage ("Maven Build"){
		steps{

		  sh "mvn clean package"
		  sh "mv target/*.jar target/robi.jar"
	
	
		}
	}  
    }
}
