all: server

server: mongoose.c server.cpp
	g++ mongoose.c server.cpp -o server -std=c++11 -Wall


.PHONY: clean

clean:
	rm -rf *.o *.exe
