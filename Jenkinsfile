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
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests - Grid') {
            steps {
                bat 'mvn test'
            }
        }

		stage('Generar Reporte Surefire') {
		    steps {
		        bat "mvn surefire-report:report-only"
		    }
		}
		
        stage('Publicar Reportes') {
            steps {
		        publishHTML([ 
		            reportDir: 'test-output', //Carpeta donde Jenkins buscará el archivo HTML
		            reportFiles: 'index.html', //El archivo(s) HTML a mostrar. (TestNG genera un index.html)
		            reportName: 'Reporte TestNG', //Nombre que aparecerá en Jenkins para el reporte
		            allowMissing: false, //Si el archivo no se encuentra, falla el build. Si lo pones en true, solo muestra advertencia
		            alwaysLinkToLastBuild: true, //Si true, crea un enlace en Jenkins al último reporte disponible, útil para acceso rápido.
		            keepAll: true //Si true, guarda el reporte HTML para cada ejecución del Job, no solo el último.
		        ])
		    }
        }
	}

    post {
        always {
            archiveArtifacts artifacts: '**/test-output/**', allowEmptyArchive: true
        }
    }
}
