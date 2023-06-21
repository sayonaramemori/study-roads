cmake_minimum_required(VERSION 3.15)
set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_COMPILER "C:/MinGW/bin/g++.exe")

include_directories(${CMAKE_CURRENT_SOURCE_DIR}/inc)

project(TEST)

file(GLOB SRC ${CMAKE_CURRENT_SOURCE_DIR}/src/*.cpp)

add_executable(test ${SRC})

