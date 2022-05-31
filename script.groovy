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
    sh 'sshpass -p \"PPPdevops2022!\" scp -r ./deployment/ localuser@40.114.225.176:/home/localuser/Bookstore/'
    sh 'sshpass -p \"PPPdevops2022\" ssh localuser@40.114.225.176 \"cd /home/localuser/Bookstore/deployment && docker-compose up\"'
}


return this
