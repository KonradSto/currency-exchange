FROM ubuntu

RUN apt-get -y update && apt-get install -y \
	curl \
	openjdk-8-jdk \
	git  \
	wget

RUN wget --no-verbose -O /tmp/apache-maven-3.5.0.tar.gz http://archive.apache.org/dist/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.tar.gz

# verify checksum
RUN echo "35c39251d2af99b6624d40d801f6ff02 /tmp/apache-maven-3.5.0.tar.gz" | md5sum -c

RUN tar xzf /tmp/apache-maven-3.5.0.tar.gz -C /opt/
RUN ln -s /opt/apache-maven-3.5.0 /opt/maven && ln -s /opt/maven/bin/mvn /usr/local/bin
RUN rm -f /tmp/apache-maven-3.5.0.tar.gz
ENV MAVEN_HOME /opt/maven

RUN mkdir /usr/src/myapp
WORKDIR /usr/src/myapp

# install node.js
RUN curl -sL curl -sL https://deb.nodesource.com/setup_8.x | bash -

# https://docs.npmjs.com/getting-started/fixing-npm-permissions
RUN apt-get install -y nodejs

RUN git clone https://github.com/KonradSto/currency-exchange.git

WORKDIR /usr/src/myapp/currency-exchange

RUN mvn clean install

RUN chmod 777 /tmp

CMD ["java", "-jar","/usr/src/myapp/currency-exchange/target/currency-exchange-app-0.0.1-SNAPSHOT.jar"]
