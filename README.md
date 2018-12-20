# DrewMovies2

Initial code for a project implementing custom Java and Android libraries into an Application. The library integration supports holding joke logic within a separate java library for reuseability and maintainence. Along with a separate Android library used to display the jokes. The purpose of this separation is for modularity when working on cross-platform applications. 

## Getting Started

If you want to see the application in action, clone the project and unzip it to your desired folder location. Open the project in Android Studio and build. 

Note: Android Studio may ask you to update certain dependencies. This is not reccomended as the project hasn't been tested using other configurations. Ignore these updates and run in an emulator with an Android APK level declared in the app folder's build.gradle file.

Once installed, you will need to follow the instructions in the Setup Cloud SDK
section [here](https://cloud.google.com/endpoints/docs/frameworks/java/migrating-android):

Note: You do not need to follow the rest of steps in the migration guide, only
the Setup Cloud SDK.

Start or stop your local server by using the gradle tasks as shown in the following
screenshot:

<img src="/FinalProject/GCE-server-gradle-tasks.png" height="500">

Once your local GCE server is started you should see the following at 
[localhost:8080](http://localhost:8080)

<img src="https://raw.githubusercontent.com/GoogleCloudPlatform/gradle-appengine-templates/77e9910911d5412e5efede5fa681ec105a0f02ad/doc/img/devappserver-endpoints.png">

Now you are ready to test it out for yourself! 

## Why this Project

As Android projects grow in complexity, it becomes necessary to customize the
behavior of the Gradle build tool, allowing automation of repetitive tasks.
Particularly, factoring functionality into libraries and creating product
flavors allow for much bigger projects with minimal added complexity.

## Acomplishments

I successfully and customly implemented Gradle in building an Android App and used
Gradle to manage an app of increasing complexity. Including:

* Add free and paid flavors to an app, and set up your build to share code between them
* Factor reusable functionality into a Java library
* Factor reusable Android functionality into an Android library
* Configure a multi project build to compile your libraries and app
* Use the Gradle App Engine plugin to deploy a backend
* Configure an integration test suite that runs against the local App Engine development server

### Built With

* __GCE__ (Google Cloud Endpoints) - To serve as a local host for the Java Library supplying information to the application, in a production application this would be run on a server. See [GCE SDK Docs](https://cloud.google.com/sdk/docs/) for more information.

### Authors

* __Udacity__ - _Initial mockup and guidelines_ - [Udacity](https://udacity.com)
* __Mr. Andrew__ - _Main source of code and implementation following provided guidelines_ - [Mr.Andrew](http://github.com/Mrandrew7of9)

### License

This project is generally licensed under the Apache License 2.0 with the exception that you must site credit to the original author of the project - see [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) for more details.

### Acknowledgments

* Thank you to Google and Udacity for the foundational knowledge and motivation to complete this project
* Thank you to all developers who have provided open source libraries, code snippets, reviews, and knowledge on the internet for others to use and learn from.

### Initial Code

[This repository](https://github.com/udacity/ud867/tree/master/FinalProject) is the starting point for the project, provided by Udacity.

