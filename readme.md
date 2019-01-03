This project demonstrates a micro-service architecture. The objective is to help the students of the University of Geneva to quickly bootstrap their PInfo project.
Please note that, while this is a simple demonstration, it showcases the following real life challenges of microservices architecture:
- Performance of service aggregation
- Security and especially SSO
- UI frontend



The solution is composed of .. microservices and demonstrates a simple financial application. The application manipulates financial data and provide simple calculation by combining several internal and external services.:
1) The counterparty service is responsabible to list, search, update counterparty data. This is the definition of the financial instutions.
2) The instrument service searches and keeps up to date financial instruments. An instrument basicall represent a position of a financial institution. For instance, a loan granted to another counterparty.
3) A simple portofolio valuation that calculates the cumulated current value of the instruments
4) A dummy regulatory reporting service that list all the counterparties with an exposure that is greater than $25,000
5) A SSO service based in keycloak
