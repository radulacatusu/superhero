FROM java:8
VOLUME /tmp
ADD target/superhero-1.0-SNAPSHOT.jar superhero.jar
RUN bash -c 'touch /superhero.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -jar /superhero.jar"]