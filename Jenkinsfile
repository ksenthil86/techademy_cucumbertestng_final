pipeline {
    agent any
    
    stages {
        stage('Cucumber BDD TestNG Tests') {
            steps {
                // Get some code from a GitHub repository
                git branch:'CaseStudy_Advanced',url: 'https://github.com/ksenthil86/techademy_cucumbertestng_final.git'
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
