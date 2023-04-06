This is a lightweight library that allows users to automatically detect the coding language of an
input text. The library is based off of [highlight.js](https://github.com/highlightjs/highlight.js)' language detection.

## Table of Contents

* [Installation](https://github.com/Valkryst/VCodeLanguageDetection#installation)
    * [Gradle](https://github.com/Valkryst/VCodeLanguageDetection#-gradle)
    * [Maven](https://github.com/Valkryst/VCodeLanguageDetection#-maven)
    * [sbt](https://github.com/Valkryst/VCodeLanguageDetection#-scala-sbt)
* [Usage](https://github.com/Valkryst/VCodeLanguageDetection#usage)
* [Supported Languages](https://github.com/Valkryst/VCodeLanguageDetection#supported-languages)

## Installation

_VCodeLanguageDetection_ is hosted on the
[JitPack package repository](https://jitpack.io/#Valkryst/VCodeLanguageDetection) which supports Gradle, Maven,
and sbt.

### ![Gradle](https://i.imgur.com/qtc6bXq.png?1) Gradle

Add JitPack to your `build.gradle` at the end of repositories.

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Add _VCodeLanguageDetection_ as a dependency.

```
dependencies {
	implementation 'com.github.Valkryst:VCodeLanguageDetection:1.0.0'
}
```

### ![Maven](https://i.imgur.com/2TZzobp.png?1) Maven

Add JitPack as a repository.

``` xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add _VCodeLanguageDetection_ as a dependency.

```xml
<dependency>
    <groupId>com.github.Valkryst</groupId>
    <artifactId>VCodeLanguageDetection</artifactId>
    <version>1.0.0</version>
</dependency>
```

### ![Scala SBT](https://i.imgur.com/Nqv3mVd.png?1) Scala SBT

Add JitPack as a resolver.

```
resolvers += "jitpack" at "https://jitpack.io"
```

Add _VCodeLanguageDetection_ as a dependency.

```
libraryDependencies += "com.github.Valkryst" % "VCodeLanguageDetection" % "1.0.0"
```

## Usage

Get an instance of _LanguageDetector_, and then call the _detectLanguage_ method with the code whose language you wish
to detect.

```java
public class Example {
  public static void main(final String[] args) {
    final var code = """
      #include <stdio.h>
              
      int main() {
          printf("Hello World");
          return 0;
      }
    """;

    final var detector = LanguageDetector.getInstance();
    final var language = detector.detect(code);

    if (language.isPresent()) {
      System.out.println(language.get().getName());
    } else {
      System.out.println("No language detected.");
    }
  }
}
```

If you're using [RSyntaxTextArea](https://github.com/bobbylight/RSyntaxTextArea), you can use the following method to
detect the language and return the appropriate syntax style.

```java 
private String detectSyntaxStyle(final @NonNull String code) {
    final var language = LanguageDetector.getInstance().detect(code);

    if (language.isEmpty()) {
        return RSyntaxTextArea.SYNTAX_STYLE_NONE;
    }

    try {
        final var styleName = "SYNTAX_STYLE_" + language.get().getName().toUpperCase();
        final var field = SyntaxConstants.class.getDeclaredField(styleName);
        return (String) field.get(null);
    } catch (final NoSuchFieldException | IllegalAccessException e) {
        return RSyntaxTextArea.SYNTAX_STYLE_NONE;
    }
}
```

## Supported Languages

* C
* Java
* Python