# Import base image
FROM openjdk:12


# Create log file directory and set permission
RUN groupadd -r ms-kubejourney && useradd -r --create-home -g ms-kubejourney ms-kubejourney
RUN if [ ! -d /var/log/kube-journey/ ];then mkdir /var/log/kube-journey/;fi
RUN chown -R ms-kubejourney:ms-kubejourney /var/log/kube-journey

# Move project artifact
ADD target/ms-kubejourney-*.jar /home/ms-kubejourney/
USER ms-kubejourney

# Launch application server
ENTRYPOINT exec $JAVA_HOME/bin/java $XMX $XMS -jar -Dspring.profiles.active=$ENVIRONMENT  /home/ms-kubejourney/ms-kubejourney-*.jar
