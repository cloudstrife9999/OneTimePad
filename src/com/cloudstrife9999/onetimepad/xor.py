#!/usr/bin/python

import sys


def find_key(hex_input, non_hex_desired_output):
    print hex(int(non_hex_desired_output.encode("hex"), 16)^int(hex_input, 16))[2:].zfill(len(hex_input))


if __name__ == "__main__":
    find_key(sys.argv[1], sys.argv[2])
