pipeline {
  environment {
    registry = "hjwoou/ourdus-be"
    registryCredential = 'docker-hub'
    dockerImage = ''
    buildPath = "${env.WORKSPACE}/ourdus-spring/build"
  }
  
  agent any

  stages {
    stage('Clone-Git') {
      steps {
        git 'https://github.com/Ourdus/OurdusBE.git'
      }
    }

    stage('Build') {
      steps {
        withGradle {
          sh '${WORKSPACE}/ourdus-spring/gradlew build'
        }
        sh "mkdir -p ${env.buildPath}/dependency && (cd ${env.buildPath}/dependency; jar -xf ${env.buildPath}/libs/*.jar)"
      }
    }
  }
  
  stages {
    stage('Dokcer-image-build') {
      steps{
        script {
          dockerImage = docker.build(${env.registry} + ":$BUILD_NUMBER", "-f Dockerfile.prod")
        }
      }
    }
    stage('Docker-image-push') {
      steps {
        script {
          docker.withRegistry('https://registry.hub.docker.com', registryCredential) {
            dockerImage.push()
          }
        }
      }
    }
    stage('Docker-image-remove') {
      steps{
        sh "docker rmi ${env.registry}:$BUILD_NUMBER"
      }
    }
  }
}
