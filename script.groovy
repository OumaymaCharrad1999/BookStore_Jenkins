def buildJar() {
    echo "building the application..."
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'NexusCredentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t bookstore:${IMAGE_NAME} --build-arg BUILD_ID="${IMAGE_NAME}" .'
        sh "echo $PASS | docker login --username $USER --password-stdin 20.224.230.246:8083/repository/docker-hosted/ "
        sh 'docker push 20.224.230.246:8083/repository/docker-hosted/'
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
