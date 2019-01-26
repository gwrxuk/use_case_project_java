FROM ubuntu:16.04

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean

RUN apt install -y maven
RUN export M2_HOME=/usr/
RUN export M2=$M2_HOME/bin
RUN export MAVEN_OPTS="-Xms256m -Xmx512m"
RUN export PATH=$M2:$PATH
RUN export JAVA_HOME=/usr
RUN export PATH=$PATH:$JAVA_HOME/bin/
RUN mkdir /server
COPY usecase /server
COPY run.sh /server
WORKDIR /server
CMD ["bash","run.sh"]
