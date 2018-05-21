#!/usr/bin/python3

from subprocess import Popen, PIPE
import threading
import os
import signal
import time
import csv
import platform


def run_server() -> object:
    prc = Popen("java -cp ../build/classes/server/ com/vk/dell/emc/client_server/Server", shell=True)
    return prc.pid


def run_client(threadsNumber: object) -> object:
    prc = Popen("java -cp ../build/classes/client/ com/vk/dell/emc/client_server/MainClient " + str(threadsNumber),
                shell=True, stdout=PIPE)
    print("client started with " + str(threadsNumber) + " threads")
    out = prc.communicate()
    return threadsNumber, float(out[0].decode())


def print_to_csv(list: object) -> object:
    with open('results\result.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile, delimiter=',')
        for pair in list:
            writer.writerow([pair[0], pair[1]])


def is_tool_installed(app: object) -> object:
    from shutil import which
    return which(app) is not None


def plot() -> object:
    prc = Popen("gnuplot -c plot_result", shell=True)
    prc.wait()


if __name__ == '__main__':
    server_pid = run_server()
    time.sleep(1)

    result = []
    max_threads_number = 100;
    i = 1
    while i <= max_threads_number:
        result.append(run_client(i))
        if i < 10:
            i += 1
        elif i >= 10:
            i += 10

    os.kill(server_pid, signal.SIGTERM)
    print_to_csv(result)
    print("Result is written to \'results/result.cvs\'")
    if platform.system() == 'Linux' and is_tool_installed("gnuplot"):
        plot()
        print("Graph is drawn and saved to \'results/result.png\'")
