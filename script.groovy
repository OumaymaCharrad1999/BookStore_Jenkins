def buildJar() {
    echo "Building the Application..."
    sh 'mvn clean package'
}


def buildImage() {
    echo "Building the Docker Image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhubCredentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t ayadinou/bookstore:${IMAGE_NAME} --build-arg BUILD_ID="${IMAGE_NAME}" .'
        sh 'echo $PASS |docker login -u $USER --password-stdin  '
        sh 'docker push ayadinou/bookstore:${IMAGE_NAME}'
    }
}


def deployApp() {
    echo 'Deploying the Application...'
    
    sh 'cd deployment'
    sh 'echo "IMAGE_NAME=${IMAGE_NAME}">.env'
    sh 'cd ..'
    sh 'sshpass -p \"PPPdevops2022!\" scp -r ./deployment/ deployuser@20.232.192.220:/home/deployuser/Bookstore/'
    sh 'sshpass -p \"PPPdevops2022!\" ssh deployuser@20.232.192.220 \"cd /home/deployuser/Bookstore/deployment && docker-compose up\"'
}


return this
