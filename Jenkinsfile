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
                    docker.build("bookstore-api-tests")
                }
            }
        }

        stage('Run API Tests') {
            steps {
                script {
                    docker.image("bookstore-api-tests").inside {
                        sh 'mvn clean test'
                    }
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', allowEmptyArchive: true
        }
        failure {
            echo '❌ API Tests Failed'
        }
        success {
            echo '✅ API Tests Passed Successfully'
        }
    }
}
