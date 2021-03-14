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
		  sh "mv target/*.jar target/robi.jar"
	
		}
	}

	stage("deploy-dev"){
	  	sshagent(['811c1996-30c7-44df-b32a-d26e349240c5']) {
    		  sh """
		    scp -o StrictHostKeyChecking=no target/robi.jar ec2-user@34.229.142.34:/opt/tomcat/tomcat9/webapps
		    ssh ec2-user@34.229.142.34 /opt/tomcat/tomcat9/bin/shutdown.sh
		    ssh ec2-user@34.229.142.34 /opt/tomcat/tomcat9/bin/startup.sh

		"""
		}
	}  
    }
}
