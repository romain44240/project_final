pipeline {
    agent any

    environment {
        DOCKER_IMAGE_FRONT = 'front'
        DOCKER_IMAGE_BACK = 'back'
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'github' ,
                    url: 'git@github.com:romain44240/project_final.git',
                    branch: 'main'
                    
                stash name: 'code', includes: '**/*'
            }
        }

        stage('Build Angular') {
            agent {
                docker {
                    image 'node:20'
                    args '--network project_final_default'
                }
            }
            steps {
                unstash 'code'
                dir('projet-angular') {
                    sh 'npm install'
                    sh 'npm run build --prod'
                }
            }
        }

        stage('Build Spring') {
            agent {
                docker {
                    image 'maven:3.9.9-amazoncorretto-21'
                    args '--network project_final_default -u root'
                }
            }
            steps {
                dir('projet-final') {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Analyse SonarQube') {
            agent {
                docker {
                    image 'maven:3.9.9-amazoncorretto-21'
                    args '--network project_final_default -u root'
                }
            }
            steps {
                withSonarQubeEnv('SonarQube') {
                    unstash 'code'
            // si on veut faire avec le front
//             dir('projet-angular') {
//                 sh '''
//                 npm install
//                 npm run test -- --code-coverage --watch=false --browsers=ChromeHeadlessNoSandbox
//                 '''
//             }
                    dir('projet-final') {
                        sh 'mvn test sonar:sonar -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml'
                    }
                }
            }
        }


        stage('Build Docker Images And Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh 'docker build -t $DOCKER_USER/$DOCKER_IMAGE_FRONT ./projet-angular'
                    sh 'docker build -t $DOCKER_USER/$DOCKER_IMAGE_BACK ./projet-final'
                    sh 'docker push $DOCKER_USER/$DOCKER_IMAGE_FRONT'
                    sh 'docker push $DOCKER_USER/$DOCKER_IMAGE_BACK'
                }
            }
        }
    }
}