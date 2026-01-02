pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/pradeeppandey70/BookStore_API_Automation.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
					echo 'Building Docker image...'
                    bat 'docker build -t bookstore-api-tests .'
                }
            }
        }

        stage('Run API Tests') {
            steps {
                script {
                    echo 'Running API test in Docker...'
                    bat 'docker run bookstore-api-tests'
                    }
              }
        }

    }

    post {
		success {
            echo '✅ API Tests Passed Successfully'
        }
        failure {
            echo '❌ API Tests Failed'
        }
        
    }
}
