# Kong-Gateway
![](https://github.com/EknarongAphiphutthikul/Kong-Gateway/KongDemo.jpg)
## Kong
                
----
- [gen certificate](https://github.com/EknarongAphiphutthikul/Kong-Gateway/blob/main/Kong/cert/script_gen_cert.txt)
- [docker compose for postgres](https://github.com/EknarongAphiphutthikul/Kong-Gateway/blob/main/Kong/postgres-docker-compose.yaml)
- [docker compose for kong](https://github.com/EknarongAphiphutthikul/Kong-Gateway/blob/main/Kong/kong-docker-compose.yaml)
- [docker compose for konga](https://github.com/EknarongAphiphutthikul/Kong-Gateway/blob/main/Kong/konga-docker-compose.yaml)
- [script start docker compose](https://github.com/EknarongAphiphutthikul/Kong-Gateway/blob/main/Kong/script_create_compose.txt)
- [kong admin api](https://github.com/EknarongAphiphutthikul/Kong-Gateway/blob/main/Kong/Kong-Admin-Api.md)

## Rest Api Server
                
----
start server by [docker-compose](https://github.com/EknarongAphiphutthikul/Kong-Gateway/blob/main/RestApi/restapi-demo-docker-compose.yaml) using the command [script-create-rest-api-server](https://github.com/EknarongAphiphutthikul/Kong-Gateway/blob/main/RestApi/script_manage_compose_restapi.txt)
### RestApiDemoHttp
- demo rest api with out ssl
- [source code](https://github.com/EknarongAphiphutthikul/Kong-Gateway/tree/main/RestApi/RestApiDemoHttp)
### RestApiDemoHttps
- demo rest api with ssl
- generate certificate and private key for RestApiDemoHttps [script_gen_p12.txt](https://github.com/EknarongAphiphutthikul/Kong-Gateway/blob/main/RestApi/RestApiDemoHttps/src/main/resources/script_gen_p12.txt)
- [source code](https://github.com/EknarongAphiphutthikul/Kong-Gateway/tree/main/RestApi/RestApiDemoHttps)

## Rest Api Client
                
----
- connect to Kong
- [source code](https://github.com/EknarongAphiphutthikul/Kong-Gateway/tree/main/RestApi/ClientRestApiDemo)