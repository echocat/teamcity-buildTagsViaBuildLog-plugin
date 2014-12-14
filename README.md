# Build tags via build log plugin for TeamCity

This plugin is for TeamCity. It adds programmatically build tags via the build log.

## How to use

Upload the BuildTagsViaBuildLog.zip into TeamCity. Restart teamcity to make it active.

To add build tags to your build just write a message of the following format into your build logs:

```
  ##teamcity[addBuildTag 'cooltag']
```

The example above would add the tag "cooltag" (without the quotes) to your build.

For further information about this pattern style see [Build Script Interaction with TeamCity](https://confluence.jetbrains.com/display/TCD8/Build+Script+Interaction+with+TeamCity).

## Motivation

TeamCity comes along with several build script interaction possibilities out-of-the-box. However, it lacks
the opportunity to conditionally add custom tags while the build is running. For instance, when running integration
tests to add the version of the software the tests are running against.

## Kudos

This plugin implementation is inspired by [carlspring's stackoverflow answer](http://stackoverflow.com/questions/6545710/programatically-pin-a-build-in-teamcity).

## How to build

Build it with maven from inside the root directory:

```
  mvn clean package
```

Find the .zip file in the target folder and upload it on your TeamCity plugin administration page.

## Compatibility

Honestly, the plugin is only tested with TeamCity 8.1.5 at the moment. Nevertheless, I guess it should work with no
problem with a TeamCity 8.x.x or later. Just try it out!

Happy continuous building!
