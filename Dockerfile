FROM gradle:7.5.0-jdk17
WORKDIR /opt/app
COPY ./build/libs/JazzClassics-backend-1.0-SNAPSHOT.jar ./

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar todolist-backend-0.0.1-SNAPSHOT.jar"]