#!/bin/bash
function mkcpdir() {
  echo "Copying $1 to $2"
  mkdir -p -- "$2"
  cp -r "$1" "$2"
}

for platform in fabric forge quilt; do
  mkcpdir "test-levels/New World" "$platform/run/saves/"
done
