FROM httpd:2.4

RUN sed -i 's/#LoadModule proxy_module/LoadModule proxy_module/' /usr/local/apache2/conf/httpd.conf && \
    sed -i 's/#LoadModule proxy_http_module/LoadModule proxy_http_module/' /usr/local/apache2/conf/httpd.conf

COPY reverse-proxy.conf /usr/local/apache2/conf/extra/reverse-proxy.conf

RUN echo "Include conf/extra/reverse-proxy.conf" >> /usr/local/apache2/conf/httpd.conf

COPY index.html /usr/local/apache2/htdocs/index.html
COPY script.js /usr/local/apache2/htdocs/script.js