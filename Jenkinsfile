pipeline {
    agent any

    tools {
        maven 'Maven 3.9.11'   // nombre configurado en Jenkins
        jdk 'JDK 17'          // nombre configurado en Jenkins
    }

    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=true"
    }

    stages {
        /*stage('Checkout') {
            steps {
                git branch: 'develop', url: 'https://github.com/JavierAntunezE/Selenium-avanzado.git'
            }
        }*/

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests - Grid') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Publicar Reportes') {
            steps {
                publishTestNGResults testResultsPattern: '**/test-output/testng-results.xml'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/test-output/**', allowEmptyArchive: true
        }
    }
}
