.PHONY: publish publishLocal
publish:
	-./gradlew clean assemble uploadArchives --no-daemon --no-parallel

publishLocal:
	-./gradlew clean assemble publishToMavenLocal --no-daemon --no-parallel
