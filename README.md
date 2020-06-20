# CoroutineAppArchitecture-NBR
This is a Library to use life cycle extension with App Architecture component with Kotlin Coroutines.

There is a class  to make API call using Coroutines and store the data to the local database using Room Database.
[NetworkBoundResource](https://github.com/ishwarverma39/CorotuineAppArchitecture-NBR/blob/master/common/src/main/java/com/livtech/common/core/network/NetworkBoundResource.kt)

# How to Use
  Step 1. Add the JitPack repository to your build file
  Add it in your root build.gradle at the end of repositories:
	
    allprojects {
		 repositories {
			 ...
			 maven { url 'https://jitpack.io' }
		 }
	 }

  Step 2. Add the dependency to app build.gradle

	dependencies {
	            implementation "com.github.ishwarverma39:coroutine-app-architecture-NBR:v1.1.0"
	}
