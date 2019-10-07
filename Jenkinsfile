node{  
  stage('Checkout'){
    dir('currency'){
      checkout scm
    }
  }
  
  stage ('Compile Stage') {
    dir('currency'){
      withMaven(maven : 'maven_3_6_2') {
        sh 'mvn clean compile'
      }
    }
  }
  
  stage ('Test Stage') {
    withMaven(maven : 'maven_3_6_2') {
      sh 'mvn test'
    }
  }
  
  stage('Create Docker Image'){
    dir('currency'){
      sh 'docker build . -t currency-exchange-jenkins'
    }
  }
  
  stage('Build App'){
    sh 'docker run -d -p 8070:5000 currency-exchange-jenkins'
  }
  
  stage('Clean up'){
    deleteDir()
  }
}
