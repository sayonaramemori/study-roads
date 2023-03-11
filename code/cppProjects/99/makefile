srcPath=./src
objPath=./obj
incPath=./inc
cmd=g++
target=$(wildcard $(srcPath)/*.cpp)
obj=$(patsubst $(srcPath)/%.cpp,$(objPath)/%.o,$(target))
para=-I $(incPath) -Wall -D DBG
All:main
main:$(obj)
	$(cmd) $^ -o $@ $(para)
$(objPath)/%.o:$(srcPath)/%.cpp
	$(cmd) -c $^ -o $@ $(para)
clean:
	-rm $(obj) -f
.PHONY:All clean
