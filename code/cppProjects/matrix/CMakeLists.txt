cmake_minimum_required(VERSION 3.15)

project(MATRIX)

include_directories(${PROJECT_SOURCE_DIR}/inc)
file(GLOB SRC ${CMAKE_CURRENT_SOURCE_DIR}/src/*.cpp)

# list(REMOVE_ITEM SRC ${PROJECT_SOURCE_DIR}/main.cpp)

set(EXECUTABLE_OUT_PATH ${PROJECT_SOURCE_DIR}/build)

add_executable(test ${SRC})

