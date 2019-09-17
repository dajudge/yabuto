FROM openjdk:8u191-jdk-alpine3.8

RUN	apk add bash curl && \
    mkdir -p /opt && \
    curl -qL https://archive.apache.org/dist/maven/maven-3/3.6.2/binaries/apache-maven-3.6.2-bin.tar.gz | tar xvz -C /opt && \
	ln -s /opt/apache-maven-3.6.0 /opt/maven && \
	ln -s /opt/maven/bin/mvn /usr/local/bin/mvn

CMD ["bash"]