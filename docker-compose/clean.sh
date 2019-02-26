#!/bin/bash
docker stop zookeeper kafka counterparty-service instrument-service valuation-service
docker rm zookeeper kafka counterparty-service instrument-service valuation-service
docker rmi -f unige/web-sso unige/api-gateway unige/regulatory-service unige/valuation-service unige/instrument-service unige/counterparty-service
