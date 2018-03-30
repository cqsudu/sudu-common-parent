# deploy command
mvn clean deploy -P release -Dgpg.passphrase=this is gpk open password -Dmaven.test.skip=true