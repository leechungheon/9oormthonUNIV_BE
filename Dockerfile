FROM ubuntu:latest
LABEL authors="leech"

ENTRYPOINT ["top", "-b"]