pipeline {
    agent any

    options {
        timeout(time: 30, unit: 'MINUTES')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Smoke Tests') {
            steps {
                sh 'mvn -B -ntp clean test -Dtest=SmokeTestRunner -Dsurefire.failIfNoSpecifiedTests=false -Dheadless=true -Dbrowser=chrome'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/reports/**, target/cucumber-reports/**', allowEmptyArchive: true
            sh 'mkdir -p target/latest-report && cp -r target/reports/ExtentReport*/Spark/. target/latest-report/ || true'
            publishHTML(target: [
                reportDir: 'target/latest-report',
                reportFiles: 'index.html',
                reportName: 'Extent Report',
                keepAll: true,
                allowMissing: true,
                alwaysLinkToLastBuild: true
            ])
        }
    }
}
