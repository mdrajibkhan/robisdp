pipeline {
    agent any
    environment{
    	path= "/usr/local/src/apache-maven/bin:$PATH"
    }
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/mdrajibkhan/robisdp.git'
            }
       
       }
       stage ("Maven Build"){
		steps{

		  sh "mvn clean package"
		  sh "mv target/*.jar target/robisdp.jar"
	   }
	}
	stage ("deploy-dev"){
		steps{
		
		sshagent(['cf892b75-e31d-4f64-b094-e316a22c804a']) {:
 	       sh """
		scp -o StrictHostKeyChecking=no target/robisdp.jar root@34.229.142.34:/opt/
		  ssh root@34.229.142.34 /opt/tomcat9/bin/shutdown.sh
		  ssh root@34.229.142.34 /opt/tomcat9/bin/startup.sh

		"""
		}
	}  
    }
}
