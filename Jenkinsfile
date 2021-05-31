pipeline {
  environment {
    registry = "hjwoou/ourdus-be"
    registryCredential = 'docker-hub'
    dockerImage = ''
    workdir = "${env.WORKSPACE}/ourdus-spring"
    dockerfile = "Dockerfile.prod"
    deployDockerPath = "/home/ec2-user/ourdus/Ourdus/docker-compose-prod.yml"
    deployService = "OurdusBE-Spring"
  }
  
  agent any

  stages {
    stage('Setting-prod') {
      steps {
        sh "cp /var/jenkins_home/files/application-prod.properties ${env.workdir}/src/main/resources"
      }
    }
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

    stage('Docker-compose-SSH-rebuild') {
      steps([$class: 'BapSshPromotionPublisherPlugin']) {
        sshPublisher(
          continueOnError: false, failOnError: true,
          publishers: [
            sshPublisherDesc(
              configName: "ourdus-ssh",
              verbose: true,
              transfers: [
                sshTransfer(
                    execCommand: "docker-compose -f ${env.deployDockerPath} up -d --no-deps --build ${env.deployService}"
                    )
              ]
            )
          ]
        )
      }
    }

  }
}
