FROM kong:1.1rc1-centos
LABEL description="Centos 7 + Kong 0.14 + kong-oidc plugin"
#RUN USER=root usermod -a -G root kong

RUN yum install -y git unzip && yum clean all
RUN USER=root luarocks install kong-oidc
RUN yum install -y dos2unix

COPY ./docker-entrypoint.sh /docker-entrypoint.sh
RUN dos2unix /docker-entrypoint.sh
RUN chmod 777 /docker-entrypoint.sh

COPY ./nginx-custom-static-sso.template  /usr/local/kong/template/nginx-custom-static-sso.template

COPY ./config-kong.sh /tmp
RUN dos2unix /tmp/config-kong.sh
RUN chmod 777 /tmp/config-kong.sh


