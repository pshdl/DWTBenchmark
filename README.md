DWTBenchmark
============

A collection of outputs in Go, C, Dart and Java generated from a Discrete Wavelet transformation programmed in PSHDL

On my local machine the following times have been observed, after some warm-up:

* clang -O3 8,8s
* gcc -O3 14,8s
* java 28s (you can download the interpreter package from [here](http://maven.pshdl.org/org/pshdl/interpreter/0.1.99/interpreter-0.1.99.jar))
* [go](golang.org) 30,8s
* [dart](http://dartlang.org) 33,8s

##Background

The given benchmark is the FPGA ready implementation of a discrete wavelet transformation as it is used in JPEG2000. For this a VFBC controller with DDR is simulated that contains the input data. The output is then written to another area in the same memory.

This benchmark simulates the behaviour of that IP Core. It is heavy on bit masking, shifting and memory moving.