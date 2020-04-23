test -d temp/ || mkdir temp/
echo "Compiling..." \
    && javac -d temp/ -encoding UTF-8 src/*.java \
    && echo "Compiled!"
