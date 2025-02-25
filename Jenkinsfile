pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME = 'spe_miniproject'
        GITHUB_REPO_URL = 'https://github.com/Sengar001/SPE_miniproject.git'
    }
    stages {
        stage('Checkout') {
            steps {
                script {
                    git branch: 'master', url: "${GITHUB_REPO_URL}"
                }
            }
        }
        stage('Build & Test') {
            steps {
                script {
                    sh 'mvn clean package'
                    sh 'mvn test'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE_NAME}", '.')
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        sh 'docker tag spe_miniproject yourdockerhub/spe_miniproject:latest'
                        sh 'docker push yourdockerhub/spe_miniproject:latest'
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
