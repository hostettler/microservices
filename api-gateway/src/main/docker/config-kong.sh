#Creates the services.
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=counterparty-service" --data-urlencode "url=http://counterparty-service:8080/counterparties"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=instrument-service" --data-urlencode "url=http://instrument-service:8080/instrument"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=init-instrument-service" --data-urlencode "url=http://instrument-service:8080/instrument/propagateAllInstruments"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=valuation-service" --data-urlencode "url=http://valuation-service:8080/valuation"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=regulatory-service" --data-urlencode "url=http://regulatory-service:8080/regulatory"
#Creates the routes
curl -S -s -i -X POST  --url http://api-gateway:8001/services/counterparty-service/routes --data-urlencode "paths[]=/api/v1/counterparty" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/instrument-service/routes   --data-urlencode "paths[]=/api/v1/instrument"
curl -S -s -i -X POST  --url http://api-gateway:8001/services/init-instrument-service/routes   --data-urlencode "paths[]=/api/v1/init-instrument"  
curl -S -s -i -X POST  --url http://api-gateway:8001/services/valuation-service/routes    --data-urlencode "paths[]=/api/v1/valuation" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/regulatory-service/routes   --data-urlencode "paths[]=/api/v1/regulatory" 
#Enable the Open ID Plugin
curl -S -s -i -X POST  --url http://api-gateway:8001/plugins --data "name=jwt" --data "enabled=true"
curl -S -s -i -X POST  --url http://api-gateway:8001/consumers  --data "username=api-sso-proxied"   --data "custom_id=api-sso-proxied"
curl -S -s -i -X POST  --url http://api-gateway:8001/consumers/api-sso-proxied/jwt   -F "algorithm=RS256"  -F "rsa_public_key=@/tmp/keycloak_rsa_provider-key-pub.pem" -F "key=https://localhost/auth/realms/apigw"
curl -S -s -i -X POST  --url http://api-gateway:8001/consumers  --data "username=api-sso-not-proxied"   --data "custom_id=api-sso-not-proxied"
curl -S -s -i -X POST  --url http://api-gateway:8001/consumers/api-sso-not-proxied/jwt   -F "algorithm=RS256"  -F "rsa_public_key=@/tmp/keycloak_rsa_provider-key-pub.pem" -F "key=http://iam:8080/auth/realms/apigw"
