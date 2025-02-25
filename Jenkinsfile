pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'Sengar001/calculator:latest'
        GITHUB_REPO_URL = 'https://github.com/Sengar001/SPE_miniproject.git'
        OPTION = 1
        NUMBER = 2
        EXP = 3
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Checkout the code from the GitHub repository
                    git branch: 'master', url: "${GITHUB_REPO_URL}"
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    sh "mvn test"
                }
            }
        }

        stage('Build Docker Image') {
             steps {
                 sh 'docker build -t $DOCKER_IMAGE .'
                 }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DockerHubCred', usernameVariable: 'sengar001', passwordVariable: 'Sengar@001')]) {
                    script {
                        def loginStatus = sh(script: 'echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin', returnStatus: true)
                        if (loginStatus != 0) {
                            error("❌ Docker login failed. Check credentials and try again.")
                        }

                        def pushStatus = sh(script: 'docker push $DOCKER_IMAGE', returnStatus: true)
                        if (pushStatus != 0) {
                            error("❌ Docker image push failed. Check DockerHub repository permissions.")
                        }
                    }
                }
            }
        }


        stage('Run Ansible Playbook') {
            steps {
                script {
                    ansiblePlaybook(
                        playbook: 'deploy.yml',
                        inventory: 'inventory'
                    )
                }
            }
        }
    }
}