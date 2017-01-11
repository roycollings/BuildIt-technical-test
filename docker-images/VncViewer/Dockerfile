FROM ubuntu:16.04

ARG UID
ARG GID
ARG DISPLAY

ENV DISPLAY $DISPLAY
ENV HOME /home/developer

VOLUME /tmp/.X11-unix /tmp/.X11-unix

# Install a vncviewer
RUN apt-get update && apt-get install -y tightvncserver && apt-get install -y xtightvncviewer

# Set up the dev user (mapped to host) to allow us to connect to the display
RUN mkdir -p ${HOME} && \
    echo "developer:x:${UID}:${GID}:Developer,,,:/home/developer:/bin/bash" >> /etc/passwd && \
    echo "developer:x:${UID}:" >> /etc/group && \
    chown ${UID}:${GID} -R ${HOME}

USER developer

# Run the app as a default action
CMD /usr/bin/xtightvncviewer localhost:5900

#
# Build it with:
# docker build -t vncviewer --build-arg UID=$UID --build-arg GID=$GID --build-arg DISPLAY=$DISPLAY .
#
# ... then run it with:
# docker run -ti --rm --net=host vncviewer
