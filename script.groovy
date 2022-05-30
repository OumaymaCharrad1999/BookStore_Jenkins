def buildJar() {
    echo "building the application..."
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'NexusCredentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t ayadinou/maven-build-repo::${IMAGE_NAME}.'
        sh "echo $PASS | docker login http://20.224.230.246:8083/repository/docker-hosted/"
        sh 'docker push http://20.224.230.246:8083/repository/docker-hosted/'
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
