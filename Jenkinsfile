#!/usr/bin/env groovy
def gv
pipeline {
    agent any
    options{
        timeout(time: 5, unit:'MINUTES')
    }
    tools {
        maven 'maven'
    }
    stages {
    
        stage("Initialization") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        
        stage('Increment Version') {
            steps {
                script {
                    echo 'Incrementing App Version'
                    sh 'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version"
                }
            }
        }
    
        stage('Build App') {
            steps {
                script {
                    echo "Building jar File"
                    gv.buildJar()
                }
            }
        }
        
        stage('Build Image') {
            steps {
                script {
                     echo "Building Image"
                     gv.buildImage()
                }
            }
        }
        
        stage('Commit Version Update') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'gitlabCredentals', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'git config --global user.email "ayadi.01.mohamed@gmail.com"'
                        sh 'git config --global user.name "ayadi.01.mohamed"'
                        sh "git remote set-url origin https://${USER}:${PASS}@gitlab.com/ayadi.01.mohamed/bookstore.git"
                        sh 'git add .'
                        sh 'git commit -m "Change Version To ${IMAGE_NAME}"'
                        sh 'git push origin HEAD:development'
                        sh 'git checkout -b ${IMAGE_NAME}'
                        sh 'git push origin HEAD:${IMAGE_NAME}'
                    }
                }
            }
        }

         stage('Deploying') {
            steps {
                script {
                    echo 'Deploying the Application'
                    gv.deployApp()
                }
            }
        }
       
    }
}
