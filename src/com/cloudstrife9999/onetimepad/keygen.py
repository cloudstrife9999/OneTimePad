#!/usr/bin/python

import sys
import os
import binascii


def hex_gen(length):
    print binascii.b2a_hex(os.urandom(length))


if __name__ == "__main__":
    hex_gen(int(sys.argv[1], 10))
