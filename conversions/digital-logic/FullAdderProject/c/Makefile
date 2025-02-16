CC = gcc
CFLAGS = -Wall -Wextra -std=c99

SRC = src/gate.c src/wire.c src/circuit.c src/main.c
INCLUDE = -Iinclude

all: fulladder

fulladder: $(SRC)
	$(CC) $(CFLAGS) $(SRC) $(INCLUDE) -o fulladder

clean:
	rm -f fulladder