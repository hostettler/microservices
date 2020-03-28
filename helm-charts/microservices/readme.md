The pinfo microservices example project Helm Chart
==================================================


Installation of kubernetes
--------------------------
Kubernetes is installed usually as part of the local Docker engine. It must be enabled though.
For more information on how to install kubernetes, please refer to the [following instructions](https://kubernetes.io/docs/setup/)

Once installed the next thing is to deploy the dashboard. The following command will download and install the dashboard.

	$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-rc6/aio/deploy/recommended.yaml

Then a proxy must be setup:

	$ kubectl proxy

Finally to view the dashboard, point your browser to the [following address](http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/)

Installation of Helm and the main repositories
------------------------------------------
To install helm please refer to https://helm.sh/docs/intro/install/

	$ helm init
	$ helm repo add stable https://kubernetes-charts.storage.googleapis.com/
	$ helm repo add bitnami https://charts.bitnami.com/bitnami
	$ helm repo update

Switching between the different clusters
----------------------------------------
	$ kubectl cluster-info
	$ kubectl config get-contexts
	$ kubectl config use-context context-name

Configuring the cluster for the pinfo application 
-------------------------------------------------
	$ kubectl create secret generic keycloak-realm-secret --from-file=../../docker-compose/iam-config/realms.json
	$ kubectl delete configmap instrument-scripts
	$ kubectl create configmap instrument-scripts  --from-file ./test-data/200_instruments.sql.gz
	$ kubectl delete configmap counterparty-scripts
	$ kubectl create configmap counterparty-scripts  --from-file ./test-data/100_counterparties.sql.gz

Start the application
---------------------
from the chart directory (the one with `Chart.yaml` in it):

	$ helm install pinfo-v1 .

Upgrade the application
-----------------------
After a change in one of the files of the chart you can run the following command to update the configuration. from the chart directory (the one with `Chart.yaml` in it):

	$ helm upgrade pinfo-v1 .

Stop the application	
--------------------
	$ helm delete pinfo-v1