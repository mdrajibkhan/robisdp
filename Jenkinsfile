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
		steps{
	  	sshagent(['2847b83d-c19e-47b2-b48c-1b4fd970240e'])} {
    		  sh """
		    cd /var/lib/jenkins/workspace/Jenkins_Pipeline_Test_Project/
		    scp -o StrictHostKeyChecking=no target/robi.jar root@34.229.142.34:/opt/tomcat/tomcat9/webapps
		    ssh root@34.229.142.34 /opt/tomcat/tomcat9/bin/shutdown.sh
		    ssh root@34.229.142.34 /opt/tomcat/tomcat9/bin/startup.sh

		"""
		}
	}  
    }
}
