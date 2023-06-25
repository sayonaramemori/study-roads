cmake_minimum_required(VERSION 3.15)

project(MATRIX)

include_directories(${PROJECT_SOURCE_DIR}/inc)

#set(CUR ${PROJECT_SOURCE_DIR})
#set(SRC ${CUR}/src/main.cpp ${CUR}/src/read_only.cpp ${CUR}/src/read_with_mark.cpp)

file(GLOB SRC ${CMAKE_CURRENT_SOURCE_DIR}/src/*.cpp)

# list(REMOVE_ITEM SRC ${PROJECT_SOURCE_DIR}/main.cpp)
# add_library(myRead ${SRC})

set(LIBRARY_OUTPUT_PATH ~/myLib)

set(EXECUTABLE_OUTPUT_PATH ${PROJECT_SOURCE_DIR}/build)

add_definitions(-DDEBUG)
add_executable(test ${SRC})
