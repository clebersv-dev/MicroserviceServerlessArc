FROM mysql:5.6
ENV MYSQL_ROOT_PASSWORD=admin
ENV MYSQL_DATABASE=banco_generic

COPY ./sql-scripts/ /docker-entrypoint-initdb.d/