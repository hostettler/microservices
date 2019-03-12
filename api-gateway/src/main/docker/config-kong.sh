#Creates the services.
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=counterparty-service" --data "url=http://counterparty-service:8080/counterparties"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=instrument-service" --data "url=http://instrument-service:8080/instrument"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=valuation-service" --data "url=http://valuation-service:8080/valuation"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=regulatory-service" --data "url=http://regulatory-service:8080/regulatory"
#Creates the routes
curl -S -s -i -X POST  --url http://api-gateway:8001/services/counterparty-service/routes --data "paths[]=/api/v1/counterparty" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/instrument-service/routes   --data "paths[]=/api/v1/instrument" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/valuation-service/routes    --data "paths[]=/api/v1/valuation" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/regulatory-service/routes   --data "paths[]=/api/v1/regulatory" 
#Enable the Open ID Plugin
curl -S -s -i -X POST  --url http://api-gateway:8001/plugins --data "name=oidc" --data "config.client_id=api-gateway" --data "config.client_secret=798751a9-d274-4335-abf6-80611cd19ba1" --data "config.discovery=https%3A%2F%2Ffinancial-app.com%2Fauth%2Frealms%2Fmaster%2F.well-known%2Fopenid-configuration"