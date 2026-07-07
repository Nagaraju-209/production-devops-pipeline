pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven-3.8.7'
    }

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(
            numToKeepStr: '20',
            daysToKeepStr: '10'
        ))
    }

    environment {
        APP_NAME = "production-devops-pipeline"
        APP_DIR = "app/springboot-app"
    }

    stages {

        stage('Environment Check') {
            steps {
                sh 'java -version'
                sh 'javac -version'
                sh 'mvn -version'
                sh 'git --version'
                sh 'docker --version'
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
    steps {
        dir("${APP_DIR}") {
            sh 'mvn clean compile'
        }
    }
}

stage('Unit Test') {
    steps {
        dir("${APP_DIR}") {
            sh 'mvn test'
        }
    }
}

stage('Package') {
    steps {
        dir("${APP_DIR}") {
            sh 'mvn package -DskipTests'
        }
    }
}

stage('Archive Artifact') {
    steps {
        archiveArtifacts artifacts: 'app/springboot-app/target/*.jar', fingerprint: true
    }
}

    }

    post {

    success {
        echo 'Build completed successfully.'
    }

    failure {
        echo 'Build failed.'
    }

    always {
        junit 'app/springboot-app/target/surefire-reports/*.xml'
        cleanWs()
    }
}
}