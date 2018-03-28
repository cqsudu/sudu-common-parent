FROM maven:3.5.3-jdk-8

WORKDIR /product/services/sudu-common-parent
COPY . /product/services/sudu-common-parent/

RUN mvn clean install -Dmaven.test.skip=true