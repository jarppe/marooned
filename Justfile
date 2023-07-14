set dotenv-load := true
project := "marooned"


help:
  @just --list


# Run CLJS tests
cljs-test:
  @npx shadow-cljs compile test


# Check for outdated deps
outdated:
  @clj -M:outdated


# Initialize dev setup:
init:
  npm i
  clojure -A:dev:test -P
  @echo "\n\nReady"


# Make icons:
make-icons:
  inkscape -w 512 -h 512  ./public/icon/marooned.svg  -o ./public/icon/marooned.512.png
  inkscape -w 256 -h 256  ./public/icon/marooned.svg  -o ./public/icon/marooned.256.png
  inkscape -w 128 -h 128  ./public/icon/marooned.svg  -o ./public/icon/marooned.128.png
  inkscape -w 64  -h 64   ./public/icon/marooned.svg  -o ./public/icon/marooned.64.png
  inkscape -w 32  -h 32   ./public/icon/marooned.svg  -o ./public/icon/marooned.32.png
