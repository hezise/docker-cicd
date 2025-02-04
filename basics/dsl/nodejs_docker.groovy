job('NodeJS Docker example') {
    scm {
        git('git://github.com/wardviaene/docker-demo.git', 'master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    
    steps {
        dockerBuildAndPublish {
            repositoryName('hezyse/testing_docker')
            tag('${BUILD_NUMBER,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

