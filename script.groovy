def buildJar() {
    echo "building the application..."
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhubCredentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t bookstore:${IMAGE_NAME} --build-arg BUILD_ID="${IMAGE_NAME}" .'
        sh 'echo $PASS |docker login -u $USER --password-stdin  '
        sh 'docker push bookstore:${IMAGE_NAME}'
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
