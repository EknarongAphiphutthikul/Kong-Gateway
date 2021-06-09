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

### CA Certificates
- List CA Certificates
    > curl -X GET --url http://kong.dev.demo:8001/ca_certificates
- Create CA Certificates
    > curl -X POST --url http://kong.dev.demo:8001/ca_certificates \
    -H 'Content-Type: multipart/form-data' \
    -F cert=@./RestApi/RestApiDemoHttps/src/main/resources/demo-com-ca.crt
- Delete CA Certificates
    > curl -X DELETE --url http://kong.dev.demo:8001/ca_certificates/(ca certificate id)
### SNIs
- List SNIs
    > curl -X GET --url http://kong.dev.demo:8001/snis
- Create SNIs
   > curl -X POST --url http://kong.dev.demo:8001/certificates/(certificate id)/snis \
    --data 'name=*.demo.com'
- Delete SNIs
   > curl -X DELETE --url http://kong.dev.demo:8001/snis/(sni id)

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
    > curl -X GET --url http://kong.dev.demo:8001/routes
- Delete Route
    > curl -X DELETE --url http://kong.dev.demo:8001/routes/(routes name or id)

### Upstreams
- List Upstreams
    > curl -X GET --url http://kong.dev.demo:8001/upstreams
- Delete Upstreams
    > curl -X DELETE --url http://kong.dev.demo:8001/upstreams/(upstream name or id)

### TARGETS
- List Target
    > curl -X GET --url http://kong.dev.demo:8001/upstreams/(upstream id)/targets
- Delete Target
    > curl -X DELETE --url http://kong.dev.demo:8001/upstreams/(upstream id)/targets/(target name or id)

### Create Loadbalancing
- Create Certificates
    > curl -X POST --url http://kong.dev.demo:8001/certificates \
    -H 'Content-Type: multipart/form-data' \
    -F snis[]=springboot-https-upstreams \
    -F cert=@./RestApi/RestApiDemoHttps/src/main/resources/demo-com-ca.crt \
    -F key=@./RestApi/RestApiDemoHttps/src/main/resources/demo-com-ca-plaintext.key

     > curl -X POST --url http://kong.dev.demo:8001/certificates \
    -H 'Content-Type: multipart/form-data' \
    -F snis[]=kong.dev.demo \
    -F cert=@./Kong/cert/kongcrt-pem.crt \
    -F key=@./Kong/cert/kongkey.pem

- create upstreams
    > curl -X POST http://kong.dev.demo:8001/upstreams \
    --data "name=springboot-http-upstreams" \
    --data "algorithm=round-robin" \
    --data "slots=1000" \
    --data "healthchecks.active.concurrency=1" \
    --data "healthchecks.active.timeout=2" \
    --data "healthchecks.active.healthy.interval=5" \
    --data "healthchecks.active.healthy.http_statuses[]=200" \
    --data "healthchecks.active.healthy.successes=1" \
    --data "healthchecks.active.http_path=/demo-http/actuator/health" \
    --data "healthchecks.active.https_verify_certificate=false" \
    --data "healthchecks.active.type=http" \
    --data "healthchecks.active.unhealthy.http_failures=3" \
    --data "healthchecks.active.unhealthy.interval=5" \
    --data "healthchecks.active.unhealthy.timeouts=2"

     > curl -X POST http://kong.dev.demo:8001/upstreams \
    --data "name=springboot-https-upstreams" \
    --data "algorithm=round-robin" \
    --data "slots=1000" \
    --data "healthchecks.active.concurrency=1" \
    --data "healthchecks.active.timeout=2" \
    --data "healthchecks.active.healthy.interval=5" \
    --data "healthchecks.active.healthy.http_statuses[]=200" \
    --data "healthchecks.active.healthy.successes=1" \
    --data "healthchecks.active.http_path=/demo-https/actuator/health" \
    --data "healthchecks.active.https_verify_certificate=false" \
    --data "healthchecks.active.type=https" \
    --data "healthchecks.active.unhealthy.http_failures=3" \
    --data "healthchecks.active.unhealthy.interval=5" \
    --data "healthchecks.active.unhealthy.timeouts=2"

    <!-- > curl -X POST http://kong.dev.demo:8001/certificates/(certificate id)/upstreams \
    --data "name=springboot-https-upstreams" \
    --data "algorithm=round-robin" \
    --data "slots=1000" \
    --data "healthchecks.active.concurrency=1" \
    --data "healthchecks.active.timeout=2" \
    --data "healthchecks.active.healthy.interval=5" \
    --data "healthchecks.active.healthy.http_statuses[]=200" \
    --data "healthchecks.active.healthy.successes=1" \
    --data "healthchecks.active.http_path=/demo-https/actuator/health" \
    --data "healthchecks.active.https_verify_certificate=true" \
    --data "healthchecks.active.type=https" \
    --data "healthchecks.active.unhealthy.http_failures=3" \
    --data "healthchecks.active.unhealthy.interval=5" \
    --data "healthchecks.active.unhealthy.timeouts=2" \
    --data "healthchecks.active.https_sni=springboot.demo.com" \
    --data "client_certificate=client_certificate.id=(certificate id)" -->

- create target
    > curl -X POST http://kong.dev.demo:8001/upstreams/springboot-http-upstreams/targets \
    --data "target=restapi1.demo.com:9500" \
    --data "weight=100"

    > curl -X POST http://kong.dev.demo:8001/upstreams/springboot-http-upstreams/targets \
    --data "target=restapi2.demo.com:9500" \
    --data "weight=100"

    > curl -X POST http://kong.dev.demo:8001/upstreams/springboot-https-upstreams/targets \
    --data "target=springboot1.demo.com:9600" \
    --data "weight=100"

    > curl -X POST http://kong.dev.demo:8001/upstreams/springboot-https-upstreams/targets \
    --data "target=springboot2.demo.com:9600" \
    --data "weight=100"

- create Service
    > curl -X POST http://kong.dev.demo:8001/services/ \
    --data "name=springboot-http-service-upstream" \
    --data 'protocol=http' \
    --data "host=springboot-http-upstreams" \
     --data 'port=9500' \
    --data "path=/demo-http"

    > curl -X POST http://kong.dev.demo:8001/certificates/(certificateid)/services/ \
    --data "name=springboot-https-service-upstream" \
    --data 'protocol=https' \
    --data "host=springboot-https-upstreams" \
     --data 'port=9600' \
    --data "path=/demo-https" 

- create Route
    > curl -X POST http://kong.dev.demo:8001/services/springboot-http-service-upstream/routes/ \
    --data "name=http-v1-http-upstream" \
    --data "paths[]=/v1/" \
    --data "protocols[]=http" \
    --data "strip_path=false" \
    --data "path_handling=v0"

    > curl -X POST http://kong.dev.demo:8001/services/springboot-http-service-upstream/routes/ \
    --data "name=http-v2-http-upstream" \
    --data "paths[]=/v2/" \
    --data "protocols[]=http" \
    --data "strip_path=false" \
    --data "path_handling=v0"

    > curl -X POST http://kong.dev.demo:8001/services/springboot-https-service-upstream/routes/ \
    --data "name=http-v1-https-upstream" \
    --data "paths[]=/v1/" \
    --data "protocols[]=https" \
    --data "strip_path=false" \
    --data "path_handling=v0"

    > curl -X POST http://kong.dev.demo:8001/services/springboot-https-service-upstream/routes/ \
    --data "name=http-v2-https-upstream" \
    --data "paths[]=/v2/" \
    --data "protocols[]=https" \
    --data "strip_path=false" \
    --data "path_handling=v0"

