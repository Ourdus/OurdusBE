pipeline {
  environment {
    registry = "hjwoou/ourdus-be"
    registryCredential = 'docker-hub'
    dockerImage = ''
    workdir = "${env.WORKSPACE}/ourdus-spring"
    dockerfile = "Dockerfile.prod"
  }
  
  agent any

  stages {
    stage('Build') {
      steps {
        dir("${env.workdir}") {
          sh "chmod +x gradlew"
          sh "./gradlew clean build"
        }
        sh "mkdir -p build/dependency"
        dir("${env.workdir}/build/dependency") {
          sh "jar -xf ../libs/*.jar"
        }
      }
    }

    stage('Dokcer-image-build') {
      steps{
        dir("${env.WORKSPACE}") {
          script {
            dockerImage = docker.build("${env.registry}:${env.BUILD_NUMBER}", "-f ${env.dockerfile} .")
          }
        }
      }
    }
    stage('Docker-image-push') {
      steps {
        script {
          docker.withRegistry('https://registry.hub.docker.com', registryCredential) {
            dockerImage.push()
            dockerImage.push("latest")
          }
        }
      }
    }
    stage('Docker-image-remove') {
      steps{
        sh "docker rmi ${env.registry}:${env.BUILD_NUMBER}"
      }
    }
  }
}
