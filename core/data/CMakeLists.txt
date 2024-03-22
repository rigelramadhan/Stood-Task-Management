cmake_minimum_required(VERSION 3.10.2)

# Set the name of your project
project("Stood")

# Add the directory containing your native source code
add_library(
        native-lib
        SHARED
        src/main/jni/native-lib.cpp
)