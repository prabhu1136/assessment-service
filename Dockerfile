FROM amazoncorretto:11.0.4

WORKDIR /home/app/

COPY firebase-admin-sdk.json .

ADD target/*.jar /home/app/app.jar
ADD config/dev/rds-combined-ca-bundle.pem /home/app/rds-combined-ca-bundle.pem
ENTRYPOINT [ \
    "java",\
    "-Djava.security.egd=file:/dev/./urandom",\
    "-jar",\
    "/home/app/app.jar"\
]
