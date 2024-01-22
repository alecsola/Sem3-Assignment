FROM gradle:7.5.0-jdk17
WORKDIR /opt/app
COPY ./build/libs/JazzClassicsSem3Assignment-1.0-SNAPSHOT.jar ./

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar JazzClassicsSem3Assignment-1.0-SNAPSHOT.jar"]