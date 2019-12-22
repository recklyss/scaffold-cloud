pipeline {
    agent {
        node { label "master" }
    }
    stages {
        stage('Prepare Git Code') {
            steps {
                echo "Prepare Git Code"

                echo 'Preparing end..'
            }
        }
        stage("Build") {
            steps {
                echo 'Build end..'
            }
        }
        stage("Get Dockerfile & Bootstart.sh") {
          steps {
            echo 'Get Dockerfile end..'
            }
        }
        stage("DockerBuild") {
          steps {
                echo 'DockerBuild end..'}
        }
        stage("DockerPush") {
          steps {

                echo 'DockerPush end..'
                }

        }
    }
}