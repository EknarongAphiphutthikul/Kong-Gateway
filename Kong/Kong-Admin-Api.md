# **Kong Admin Api**

### Certificates
- List Certificates
    > curl -X GET --url http://kong.dev.demo:8001/certificates
- Create Certificates
    > curl -X POST --url http://kong.dev.demo:8001/certificates \
    -H 'Content-Type: multipart/form-data' \
    -F snis[]=springboot.demo.com \
    -F cert=@./RestApi/RestApiDemoHttps/src/main/resources/server-pem.crt \
    -F key=@./RestApi/RestApiDemoHttps/src/main/resources/server-key.pem
- Delete Certificates
    > curl -X DELETE --url http://kong.dev.demo:8001/certificates/(certificateid)

### Service
- List Service
    > curl -X GET --url http://kong.dev.demo:8001/services
- Create Service
    > curl -X POST --url http://kong.dev.demo:8001/services \
     --data 'name=http-springboot' \
     --data 'protocol=http' \
     --data 'host=restapi.demo.com' \
     --data 'port=9500' \
     --data 'path=/demo-http'

     > curl -X POST --url http://kong.dev.demo:8001/certificates/(certificateid)/services \
     --data 'name=https-springboot' \
     --data 'protocol=https' \
     --data 'host=springboot.demo.com' \
     --data 'port=9600' \
     --data 'path=/demo-https'
     
- Delete Service
    > curl -X DELETE --url http://kong.dev.demo:8001/services/(serviceid or name)

### Route
- List Route
    > 
- Create Route
    > 
- Delete Route
    > 