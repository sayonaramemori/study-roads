mkdir src obj inc -p
touch manual.md
rm makefile -f

touch makefile

echo 'srcPath=./src' >> makefile
echo 'objPath=./obj' >> makefile
echo 'incPath=./inc' >> makefile
echo 'cmd=g++' >> makefile
echo 'target=$(wildcard $(srcPath)/*.cpp)' >> makefile
echo 'obj=$(patsubst $(srcPath)/%.cpp,$(objPath)/%.o,$(target))' >> makefile
echo 'para=-I $(incPath) -Wall -D DBG' >> makefile
echo 'All:main' >> makefile
echo 'main:$(obj)' >> makefile
echo $'\t$(cmd) $^ -o $@ $(para)' >> makefile
echo '$(objPath)/%.o:$(srcPath)/%.cpp' >> makefile
echo $'\t$(cmd) -c $^ -o $@ $(para)' >> makefile
echo 'clean:' >> makefile
echo $'\t-rm $(obj) -f' >> makefile
echo '.PHONY:All clean' >> makefile



