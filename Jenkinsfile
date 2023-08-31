pipeline {
    agent any
    
    stages {
        stage('API RestAssured Tests') {
            steps {
                // Get some code from a GitHub repository
                git branch:'main',url: 'https://github.com/ksenthil86/techademy_restassured.git'
                bat "mvn test"
            }

            post {
                 always {
                    testNG()
                    
                }
            }
        }
        
        stage('UI TestNG Tests') {
            steps {
                // Get some code from a GitHub repository
                git branch:'main',url: 'https://github.com/ksenthil86/techademy_cucumbertestng.git'
                bat "mvn test"
            }

            post {
                 always {
                    testNG()
                }
            }
        }
    }
}
