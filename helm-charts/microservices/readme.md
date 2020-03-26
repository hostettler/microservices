kubectl create secret generic keycloak-realm-secret --from-file=../../docker-compose/iam-config/realms.json
kubectl delete configmap instrument-scripts
kubectl create configmap instrument-scripts  --from-file ./test-data/200_instruments.sql.gz
kubectl delete configmap counterparty-scripts
kubectl create configmap counterparty-scripts  --from-file ./test-data/100_counterparties.sql.gz