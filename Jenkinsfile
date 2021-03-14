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
	  	sshagent(['811c1996-30c7-44df-b32a-d26e349240c5'])} {
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
