.PHONY: publish publishLocal
publish:
	-./gradlew clean assemble uploadArchives --no-daemon --no-parallel

publishLocal:
	-./gradlew clean assemble publishToMavenLocal --no-daemon --no-parallel

.PHONY: depsUpdates
depsUpdates:
	-./gradlew dependencyUpdates

.PHONY: restoreSecrets
restoreSecrets:
	-PASSWORD_STORE_DIR="./.pass-store" pass show "proj/wire/sample-remote-config/google-services.json" | base64 -d > "./sample-remote-config/google-services.json"
