cmake_minimum_required(VERSION 3.10.2)

# Set the name of your project
project("Stood")

# Add the directory containing your native source code
add_library(
        native-lib
        SHARED
        native-lib.cpp
)

# Include directories if needed
# include_directories(path/to/include)

# Find the Android log library
find_library(
        log-lib
        log
)

# Link your native library with necessary libraries
target_link_libraries(
        native-lib
        ${log-lib}
)