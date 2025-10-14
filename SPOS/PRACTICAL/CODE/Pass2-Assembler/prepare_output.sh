#!/usr/bin/env bash
set -euo pipefail

PASS1_DIR="$(cd "$(dirname "$0")/../Pass1-Assembler" && pwd)"
PASS2_DIR="$(cd "$(dirname "$0")" && pwd)"

SRC_FILE="$PASS1_DIR/output.txt"
DEST_FILE="$PASS2_DIR/Output.txt"

echo "[Pass2 setup] Ensuring Output.txt exists..."
if [[ -f "$DEST_FILE" ]]; then
  echo "[Pass2 setup] Output.txt already present in Pass2-Assembler."
else
  if [[ -f "$SRC_FILE" ]]; then
    echo "[Pass2 setup] Found Pass1 output at: $SRC_FILE"
    cp "$SRC_FILE" "$DEST_FILE"
    echo "[Pass2 setup] Copied to: $DEST_FILE"
  else
    echo "[Pass2 setup] ERROR: Could not find Pass1 output at: $SRC_FILE" >&2
    echo "Run Pass1 first to generate output.txt, or create Pass2-Assembler/Output.txt manually." >&2
    exit 1
  fi
fi

echo "[Pass2 setup] Compiling Pass2..."
javac Pass2.java

echo "[Pass2 setup] Ready. Now run: java Pass2"