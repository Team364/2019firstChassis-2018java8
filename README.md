# Team364-2018java

# Notes:
This codebase uses GradleRIO. To initialize the project, run:

`./gradlew`

To build and deploy, run these commands:

`./gradlew build --offline`

`./gradlew deploy --offline`

If on Mac/Linux, and running those commands gives a permission denied error, run:

`chmod +x gradlew`

# Build Failure:

If the build fails because of :compileJava, then install the Java JDK.

On macOS, install Homebrew (https://brew.sh/), and then run in terminal:

`brew cask install caskroom/versions/java8`

This will install the jdk.

On Linux, just run in terminal:

`sudo apt-get install openjdk-8-jre`

`sudo apt-get install openjdk-8-jdk`
