cmake_minimum_required(VERSION 3.20)
project(stringNumber)
set(CMAKE_CXX_STANDARD 11)

add_definitions(-DDEBUG)
include_directories(${PROJECT_SOURCE_DIR}/inc)

set(EXECUTABLE_OUTPUT_PATH ${PROJECT_SOURCE_DIR}/build)

file(GLOB SRC ${PROJECT_SOURCE_DIR}/src/*.cpp)

add_executable(stringNu ${SRC})

