.PHONY: depsUpdates
depsUpdates:
	-./gradlew dependencyUpdates

.PHONY: restoreSecrets
restoreSecrets:
	-PASSWORD_STORE_DIR="./.pass-store" pass show "proj/wire/sample-firebase-config/google-services.json" | base64 -d > "./sample-firebase-config/google-services.json"
	-PASSWORD_STORE_DIR="./.pass-store" pass show "proj/wire/sample-firebase-messaging/google-services.json" | base64 -d > "./sample-firebase-messaging/google-services.json"
