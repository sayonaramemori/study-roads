cmake_minimum_required(VERSION 3.20)
project(mdgen)
set(CMAKE_CXX_STANDARD 11)
set(EXECUTABLE_OUTPUT_PATH ${PROJECT_SOURCE_DIR})
include_directories(${PROJECT_SOURCE_DIR}/inc)
file(GLOB SRC ${PROJECT_SOURCE_DIR}/src/*.cpp)
add_executable(mdgen ${SRC})

